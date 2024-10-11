package projetcrypto.Sign;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import projetcrypto.utils.ChiffrementAsy;
import projetcrypto.utils.Utils;

import java.io.File;
import java.security.PrivateKey;
import java.security.PublicKey;

public class DigitalSignatureViewController {

    @FXML
    private ComboBox<String> hashAlgorithmComboBox;

    @FXML
    private ComboBox<String> signAlgorithmComboBox;

    @FXML
    private Button loadMessageFileButton;

    @FXML
    private TextField messageFilePathTextField;

    @FXML
    private Button loadKeyFileButton;

    @FXML
    private TextField keyFilePathTextField;

    @FXML
    private Button loadSignatureFileButton;

    @FXML
    private TextField signatureFilePathTextField;

    @FXML
    private Button signButton;

    @FXML
    private Button verifyButton;

    @FXML
    private Label resultLabel;

    private FileChooser fileChooser = new FileChooser();
    @FXML
    private ComboBox<String> AlgoCleGeneree;

    @FXML
    private void initialize() {
        // Initialisation des algorithmes
        hashAlgorithmComboBox.getItems().addAll("SHA-256", "SHA-512");
        AlgoCleGeneree.getItems().addAll("RSA", "DSA");
        signAlgorithmComboBox.getItems()
                .addAll("SHA256withRSA", "SHA384withRSA"
                        ,"SHA512withRSA","SHA256withECDSA"
                        ,"SHA384withECDSA","SHA512withECDSA");

        signButton.setDisable(true);
        verifyButton.setDisable(true);
    }

    @FXML
    private void loadMessageFile(ActionEvent event) {
        File messageFile = Utils.loadFileName(loadMessageFileButton, messageFilePathTextField);
        if (messageFile != null) {
            signButton.setDisable(false);
            verifyButton.setDisable(false);
        }
    }

    @FXML
    private void loadKeyFile(ActionEvent event) {
        Utils.loadFileNameC(loadKeyFileButton, keyFilePathTextField);
    }

    @FXML
    private void loadSignatureFile(ActionEvent event) {
        Utils.loadFileNameC(loadSignatureFileButton, signatureFilePathTextField);
    }

    @FXML
    private void signMessage(ActionEvent event) {
        try {
            String algoHash = hashAlgorithmComboBox.getValue();
            String algoSign = signAlgorithmComboBox.getValue();
            String messageFile = messageFilePathTextField.getText();
            String algoCle = AlgoCleGeneree.getValue();
            String signatureFile = signatureFilePathTextField.getText();
            String clePriveText=keyFilePathTextField.getText();

            PrivateKey privateKey = ChiffrementAsy.getPriv(algoCle, clePriveText);
            ChiffrementAsy.sign(privateKey, messageFile, signatureFile, algoHash, algoSign);
//ChiffrementAsy.sign(privateKey, messageFichier, signatureFichier, "SHA-256", "SHA256withRSA");
            resultLabel.setText("Message signé avec succès.");
        } catch (Exception e) {
            resultLabel.setText("Erreur lors de la signature : " + e.getMessage());
            System.out.println(e.getMessage());
        }
    }

    @FXML
    private void verifySignature(ActionEvent event) {
        try {
             String algoHash = hashAlgorithmComboBox.getValue();
            String algoSign = signAlgorithmComboBox.getValue();
            String messageFile = messageFilePathTextField.getText();
            String algoCle = AlgoCleGeneree.getValue();
            String signatureFile = signatureFilePathTextField.getText();
            String clePriveText=keyFilePathTextField.getText();

            PublicKey pubKey = ChiffrementAsy.getPub(algoCle, clePriveText);
            ChiffrementAsy.verify(pubKey, messageFile, signatureFile, algoHash, algoSign);

            resultLabel.setText("Signature vérifiée avec succès.");
        } catch (Exception e) {
            resultLabel.setText("Erreur lors de la vérification : " + e.getMessage());
        }
    }

    @FXML
    private void setKey(ActionEvent event) {
    }
}
