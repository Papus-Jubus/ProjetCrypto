package projetcrypto.DiffieHellman;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.PublicKey;
import java.util.Base64;
import java.util.ResourceBundle;
import projetcrypto.utils.DiffieHellman;

public class DiffieHellmanUIController implements Initializable {

    @FXML
    private ComboBox<Integer> participantsComboBox;

    @FXML
    private ComboBox<String> algorithmComboBox;

    @FXML
    private Button generateAliceButton;

    @FXML
    private Button generateBobButton;

    @FXML
    private Button generateCharlieButton;

    @FXML
    private Button generateSharedKeyButton;

    @FXML
    private Button compareKeysButton;

    @FXML
    private TextField sharedKeyTextField;

    @FXML
    private TextField messageTextField;

    private DiffieHellman[] participants;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Initialisation des options
        participantsComboBox.getItems().addAll(2, 3); // Options pour 2 ou 3 participants
        algorithmComboBox.getItems().addAll("DH", "ECDH"); // Options d'algorithmes
        algorithmComboBox.setDisable(true);
        generateAliceButton.setDisable(true);
        generateBobButton.setDisable(true);
        generateCharlieButton.setDisable(true);
        sharedKeyTextField.setDisable(true);
        compareKeysButton.setDisable(true);
    }

    @FXML
    public void updateParticipantOptions() {
        Integer selectedParticipants = participantsComboBox.getValue();
        if (selectedParticipants != null) {
            algorithmComboBox.setDisable(false); // Activer l'algorithme lorsque le nombre de participants est sélectionné
            generateAliceButton.setDisable(false);
            generateBobButton.setDisable(false);
            generateCharlieButton.setDisable(selectedParticipants < 3); // Activer uniquement pour 3 participants
            participants = new DiffieHellman[selectedParticipants]; // Créer un tableau de participants
        }
    }

    @FXML
    public void generateAliceKeys() {
        try {
            DiffieHellman alice = new DiffieHellman();
            // Stocker la clé publique et privée d'Alice dans des fichiers .txt
            alice.saveKeys("C:\\crypto\\diffieHellman\\alicePub.txt", "C:\\crypto\\diffieHellman\\alicePriv.txt");
            participants[0] = alice; // Stocker l'instance d'Alice
            messageTextField.setText("Clé publique d'Alice générée et stockée.");
        } catch (Exception e) {
            e.printStackTrace();
            messageTextField.setText("Erreur lors de la génération de la clé d'Alice.");
        }
    }

    @FXML
    public void generateBobKeys() {
        try {
            DiffieHellman bob = new DiffieHellman();
            // Stocker la clé publique et privée de Bob dans des fichiers .txt
            bob.saveKeys("C:\\crypto\\diffieHellman\\bobPub.txt", "C:\\crypto\\diffieHellman\\bobPriv.txt");
            participants[1] = bob; // Stocker l'instance de Bob
            messageTextField.setText("Clé publique de Bob générée et stockée.");
        } catch (Exception e) {
            e.printStackTrace();
            messageTextField.setText("Erreur lors de la génération de la clé de Bob.");
        }
    }

    @FXML
    public void generateCharlieKeys() {
        try {
            DiffieHellman charlie = new DiffieHellman();
            // Stocker la clé publique et privée de Charlie dans des fichiers .txt
            charlie.saveKeys("C:\\crypto\\diffieHellman\\charliePub.txt", "C:\\crypto\\diffieHellman\\charliePriv.txt");
            participants[2] = charlie; // Stocker l'instance de Charlie
            messageTextField.setText("Clé publique de Charlie générée et stockée.");
        } catch (Exception e) {
            e.printStackTrace();
            messageTextField.setText("Erreur lors de la génération de la clé de Charlie.");
        }
    }

    @FXML
public void generateSharedKey() {
    try {
        // Assurez-vous que toutes les clés publiques sont chargées correctement
        if (participants.length == 2) {
            participants[0].generateSharedSecret(participants[1].getPublicKey());
            saveSecretKey("C:\\crypto\\diffieHellman\\aliceSecretKey.txt", participants[0]);
            saveSecretKey("C:\\crypto\\diffieHellman\\bobSecretKey.txt", participants[1]); 
        } else if (participants.length == 3) {
            participants[0].generateSharedSecret(participants[1].getPublicKey(), participants[2].getPublicKey());
            participants[1].generateSharedSecret(participants[0].getPublicKey(), participants[2].getPublicKey());
            participants[2].generateSharedSecret(participants[0].getPublicKey(), participants[1].getPublicKey());

            saveSecretKey("C:\\crypto\\diffieHellman\\aliceSecretKey.txt", participants[0]);
            saveSecretKey("C:\\crypto\\diffieHellman\\bobSecretKey.txt", participants[1]);
            saveSecretKey("C:\\crypto\\diffieHellman\\charlieSecretKey.txt", participants[2]); // Enregistrer la clé secrète de Charlie
        }

        // Récupérer et afficher la clé secrète partagée
        sharedKeyTextField.setText(Base64.getEncoder().encodeToString(participants[0].getSecretKey().getEncoded()));
        messageTextField.setText("Clé secrète générée.");
    } catch (Exception e) {
        e.printStackTrace();
        messageTextField.setText("Erreur lors de la génération de la clé secrète.");
    }
}

private void saveSecretKey(String path, DiffieHellman participant) throws IOException {
    
    if (participant.getSecretKey() != null) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
            writer.write(Base64.getEncoder().encodeToString(participant.getSecretKey().getEncoded()));
        }
    } else {
        messageTextField.setText("Clé secrète de " + path + " n'a pas été générée.");
    }
}
    @FXML
    public void compareKeys() {
        try {
            // Comparer les clés secrètes
            if (participants[0].getSecretKey().equals(participants[1].getSecretKey()) &&
                (participants.length < 3 || participants[0].getSecretKey().equals(participants[2].getSecretKey()))) {
                messageTextField.setText("Les clés secrètes sont identiques.");
            } else {
                messageTextField.setText("Les clés secrètes sont différentes.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            messageTextField.setText("Erreur lors de la comparaison des clés.");
        }
    }
}
