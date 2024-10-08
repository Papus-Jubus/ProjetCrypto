package projetcrypto.Dashbord;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class Dashbord_viewController {

    @FXML
    private AnchorPane Container_id;  // L'AnchorPane où les vues seront chargées
    @FXML
    private HBox tableauDeBord1;
    @FXML
    private HBox tableauDeBord;
    @FXML
    public  TextField labelMessage;
    @FXML
    private Button btn_keygenerator;
    @FXML
    private Button btn_symEncryption;
    @FXML
    private Button btn_AsymEncryption;
    @FXML
    private Button btn_DiffieHelman;
    @FXML
    private Button btn_Hash;
    @FXML
    private Button btn_signature;

    @FXML
    private void handleKeyGeneration(ActionEvent event) {
        System.out.println("Génération des clés...");
        loadView("/projetcrypto/genkey/KeyGeneratorView.fxml");
        setLabelMessageText("GENERATION DES CLES");
    }

    @FXML
    private void handleSymmetricEncryption(ActionEvent event) {
        System.out.println("Chiffrement Symétrique...");
        loadView("/projetcrypto/Crypt/Sym/SymmetricEncryptionView.fxml");
        setLabelMessageText("CHIFFREMENT SYMETRIQUE");
    }

    @FXML
    private void handleAsymmetricEncryption(ActionEvent event) {
        System.out.println("Chiffrement Asymétrique...");
        loadView("/projetcrypto/Crypt/Asym/AsymmetricEncryptionView.fxml");
         setLabelMessageText("CHIFFREMENT ASYMETRIQUE");
    }

    @FXML
    private void handleDiffieHellman(ActionEvent event) {
        System.out.println("Partage de clé Diffie-Hellman...");
        loadView("/projetcrypto/DiffieHellman/DiffieHellmanView.fxml");
         setLabelMessageText("DIFFIE HELMANN");
    }

    @FXML
    private void handleHashing(ActionEvent event) {
        System.out.println("Hashage...");
        loadView("/projetcrypto/Hash/HashingView.fxml");
         setLabelMessageText("HASHING");
    }

    @FXML
    private void handleDigitalSignature(ActionEvent event) {
        System.out.println("Signature Numérique...");
        loadView("/projetcrypto/Sign/DigitalSignatureView.fxml");
         setLabelMessageText("SIGNATURE");
    }

    /**
     * Charge la vue dans l'AnchorPane (Container_id) à droite.
     * @param viewPath Chemin vers le fichier FXML de la vue à charger.
     */
    private void loadView(String viewPath) {
        try {
            // Charger le contenu FXML
            Parent view = FXMLLoader.load(getClass().getResource(viewPath));
            // Nettoyer le conteneur actuel
            Container_id.getChildren().clear();
            // Ajouter la nouvelle vue dans le conteneur
            Container_id.getChildren().add(view);
            // Ajuster les contraintes de redimensionnement pour qu'il prenne toute la place
            AnchorPane.setTopAnchor(view, 0.0);
            AnchorPane.setBottomAnchor(view, 0.0);
            AnchorPane.setLeftAnchor(view, 0.0);
            AnchorPane.setRightAnchor(view, 0.0);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Erreur lors du chargement de la vue : " + viewPath);
        }
    }

    public  void setLabelMessageText(String MessString) {
        labelMessage.setText(MessString);
    }
}
