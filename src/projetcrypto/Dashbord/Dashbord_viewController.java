/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package projetcrypto.Dashbord;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class Dashbord_viewController   {

     
    
     @FXML
    private void handleKeyGeneration(ActionEvent event) {
        // Gestion de la génération des clés
        System.out.println("Génération des clés...");
        // Naviguer vers la vue de génération de clés (si nécessaire)
        navigateToView(event,  "/projetcrypto/genkey/KeyGeneratorView.fxml");
    }

    @FXML
    private void handleSymmetricEncryption(ActionEvent event) {
        // Gestion du chiffrement symétrique
        System.out.println("Chiffrement Symétrique...");
        navigateToView(event,"/projetcrypto/Crypt/Sym/SymmetricEncryptionView.fxml");
    }

    @FXML
    private void handleAsymmetricEncryption(ActionEvent event) {
        // Gestion du chiffrement asymétrique
        System.out.println("Chiffrement Asymétrique...");
        navigateToView(event, "/projetcrypto/Crypt/Asym/AsymmetricEncryptionView.fxml");
    }

    @FXML
    private void handleDiffieHellman(ActionEvent event) {
        // Gestion du partage de clé Diffie-Hellman
        System.out.println("Partage de clé Diffie-Hellman...");
        navigateToView(event, "/projetcrypto/Hash/Hashing.fxml");
    }

    @FXML
    private void handleHashing(ActionEvent event) {
        // Gestion du hashage
        System.out.println("Hashage...");
        navigateToView(event, "/projetcrypto/Hash/Hashing.fxml");
    }

    @FXML
    private void handleDigitalSignature(ActionEvent event) {
        // Gestion de la signature numérique
        System.out.println("Signature Numérique...");
        navigateToView(event, "/projetcrypto/Sign/DigitalSignatureView.fxml");
    }

    /**
     * Méthode utilitaire pour naviguer vers une autre vue.
     * @param event L'événement déclencheur de l'action.
     * @param viewPath Le chemin du fichier FXML de la vue.
     */
    private void navigateToView(ActionEvent event, String viewPath) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(viewPath));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Erreur lors du chargement de la vue : " + viewPath);
        }
}
}
