package projetcrypto.Hash;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import projetcrypto.utils.HashingUtil;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;

public class HashingViewController {

    @FXML
    private ComboBox<String> hashAlgorithmComboBox;

    @FXML
    private ComboBox<String> keyAlgorithmComboBox;

    @FXML
    private Button loadDataFileButton;

    @FXML
    private TextField dataFilePathTextField;

    @FXML
    private Button loadResultFileButton; // Nouveau bouton pour charger le fichier de résultats
    @FXML
    private TextField resultFilePathTextField; // Champ pour afficher le chemin du fichier de résultats

    @FXML
    private Button loadKeyFileButton;

    @FXML
    private TextField keyFilePathTextField;

    @FXML
    private Button hashButton;

    @FXML
    private Button saveResultButton; // Bouton pour sauvegarder le résultat

    @FXML
    private Label resultLabel;

    @FXML
    private TextField keyInputTextField; // Champ pour la clé

    private FileChooser fileChooser = new FileChooser();
    private String lastResult; // Pour stocker le dernier résultat du hachage
    private File resultFile; // Fichier de résultats

    @FXML
    private void initialize() {
        // Initialisation des algorithmes
        hashAlgorithmComboBox.getItems().addAll("SHA-256", "SHA-512", "SHA-1");
        keyAlgorithmComboBox.getItems().addAll("Aucune clé", "HMAC", "MAC");

        hashButton.setDisable(true);
       
        saveResultButton.setDisable(true); // Désactiver le bouton de sauvegarde par défaut
        
       
        keyInputTextField.setDisable(true); // Désactiver le champ de saisie de la clé par défaut
        
             
        
    }

    @FXML
    private void onHashAlgorithmSelected(ActionEvent event) {
        // Activer ou désactiver le champ de saisie de la clé selon l'algorithme
        String selectedKeyAlgorithm = keyAlgorithmComboBox.getValue();
        
        // Activer le champ de saisie de la clé si HMAC ou MAC est sélectionné
        keyInputTextField.setDisable(!("HMAC".equals(selectedKeyAlgorithm) || "MAC".equals(selectedKeyAlgorithm)));
    }

    @FXML
    private void loadDataFile(ActionEvent event) {
        // Logique pour charger le fichier à hacher
        File file = fileChooser.showOpenDialog(loadDataFileButton.getScene().getWindow());
        if (file != null) {
            dataFilePathTextField.setText(file.getAbsolutePath());
            hashButton.setDisable(false); // Activer le bouton de hachage
        }
    }

    @FXML
    private void loadResultFile(ActionEvent event) {
        // Logique pour charger le fichier de résultats
        resultFile = fileChooser.showSaveDialog(loadResultFileButton.getScene().getWindow());
        if (resultFile != null) {
            resultFilePathTextField.setText(resultFile.getAbsolutePath());
            saveResultButton.setDisable(false); // Activer le bouton de sauvegarde
        }
    }

    @FXML
    private void loadKeyFile(ActionEvent event) {
        // Logique pour charger le fichier clé
        File file = fileChooser.showOpenDialog(loadKeyFileButton.getScene().getWindow());
        if (file != null) {
            keyFilePathTextField.setText(file.getAbsolutePath());
        }
    }

    @FXML
    private void hashData(ActionEvent event) {
        String algoHash = hashAlgorithmComboBox.getValue();
        String keyOption = keyAlgorithmComboBox.getValue();
        String dataFile = dataFilePathTextField.getText();
        String key = keyInputTextField.getText(); // Récupérer la clé saisie

        try {
            String data = new String(Files.readAllBytes(new File(dataFile).toPath()));
            String result;

            // Logique de hachage
            if ("Aucune clé".equals(keyOption)) {
                result = HashingUtil.hash(data);
            } else if ("HMAC".equals(keyOption)) {
                if (key.isEmpty()) {
                    resultLabel.setText("Veuillez entrer une clé pour HMAC.");
                    return;
                }
                result = HashingUtil.hashWithKey(data, key);
            } else { // Pour "MAC", vous pouvez ajouter la logique de hachage appropriée
                result = HashingUtil.computeMac(data, key);
            }

            lastResult = result; // Stocker le dernier résultat
            resultLabel.setText("Résultat : " + result);
            saveResultButton.setDisable(false); // Activer le bouton de sauvegarde
        } catch (Exception e) {
            resultLabel.setText("Erreur lors du hachage : " + e.getMessage());
        }
    }

    @FXML
    private void saveResultToFile(ActionEvent event) {
        // Logique pour sauvegarder le résultat dans un fichier texte
        if (lastResult == null) {
            resultLabel.setText("Aucun résultat à sauvegarder.");
            return;
        }

        if (resultFile == null) {
            resultLabel.setText("Veuillez d'abord choisir un fichier pour sauvegarder le résultat.");
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(resultFile))) {
            writer.write(lastResult);
            resultLabel.setText("Résultat sauvegardé dans : " + resultFile.getAbsolutePath());
        } catch (IOException e) {
            resultLabel.setText("Erreur lors de la sauvegarde : " + e.getMessage());
        }
    }
}
