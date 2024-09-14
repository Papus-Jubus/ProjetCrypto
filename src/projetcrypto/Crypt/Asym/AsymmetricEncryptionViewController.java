package projetcrypto.Crypt.Asym;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class AsymmetricEncryptionViewController {

    @FXML
    private Button loadTextFileButton;  // Bouton pour charger le texte à chiffrer

    @FXML
    private TextField textFilePathTextField;  // Champ pour afficher le chemin du fichier texte

    @FXML
    private Button loadKeyFileButton;  // Bouton pour charger la clé

    @FXML
    private TextField keyFilePathTextField;  // Champ pour afficher le chemin du fichier clé

    @FXML
    private ComboBox<String> algorithmComboBox;  // ComboBox pour choisir l'algorithme

    /**
     * Méthode appelée lors du clic sur le bouton pour charger un fichier texte à chiffrer.
     */
    @FXML
    private void loadTextFile(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Fichiers Texte", "*.txt"));

        Stage stage = (Stage) loadTextFileButton.getScene().getWindow();
        File selectedFile = fileChooser.showOpenDialog(stage);

        if (selectedFile != null) {
            textFilePathTextField.setText(selectedFile.getAbsolutePath());

            try (BufferedReader reader = new BufferedReader(new FileReader(selectedFile))) {
                StringBuilder fileContent = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    fileContent.append(line).append("\n");
                }
                System.out.println(fileContent.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Méthode appelée lors du clic sur le bouton pour charger un fichier clé.
     */
    @FXML
    private void loadKeyFile(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Fichiers Texte", "*.txt"));

        Stage stage = (Stage) loadKeyFileButton.getScene().getWindow();
        File selectedFile = fileChooser.showOpenDialog(stage);

        if (selectedFile != null) {
            keyFilePathTextField.setText(selectedFile.getAbsolutePath());

            try (BufferedReader reader = new BufferedReader(new FileReader(selectedFile))) {
                StringBuilder fileContent = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    fileContent.append(line).append("\n");
                }
                System.out.println(fileContent.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // Méthode pour obtenir la sélection de l'algorithme
    public String getSelectedAlgorithm() {
        return algorithmComboBox.getValue();
    }
}
