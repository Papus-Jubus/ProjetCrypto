/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */



package projetcrypto.Crypt.Sym;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import projetcrypto.utils.ChiffrementSymetrique;
import projetcrypto.utils.Utils;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.SecretKey;

public class SymmetricEncryptionViewController implements Initializable {

    @FXML
    private Button loadTextFileButton;

    @FXML
    private TextField textFilePathTextField;

    @FXML
    private Button loadKeyFileButton;

    @FXML
    private TextField keyFilePathTextField;

    @FXML
    private ComboBox<String> algorithmComboBox;

    @FXML
    private ComboBox<String> btnMode;

    @FXML
    private Button btnEncryptFile;

    @FXML
    private Button loadFileChiffreBtn;

    @FXML
    private TextField chiffreFileText;

    @FXML
    private TextField message;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Remplir les ComboBox avec des options
        algorithmComboBox.setItems(FXCollections.observableArrayList("AES", "DES", "TripleDES"));
        btnMode.setItems(FXCollections.observableArrayList("Chiffrement", "Déchiffrement"));

        // Configurer l'action pour le bouton de chiffrement/déchiffrement
        btnEncryptFile.setOnAction(e -> {
            try {
                enCryptFile(e);
            } catch (Exception ex) {
                Logger.getLogger(SymmetricEncryptionViewController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    @FXML
    private void loadTextFile(ActionEvent event) {
        Utils.loadFileNameC(loadTextFileButton, textFilePathTextField);
    }

    @FXML
    private void loadKeyFile(ActionEvent event) {
        Utils.loadFileNameC(loadKeyFileButton, keyFilePathTextField);
    }

    @FXML
    private void loadFileChiffreBtn(ActionEvent event) {
        Utils.loadFileNameC(loadFileChiffreBtn, chiffreFileText);
    }

    @FXML
    private void enCryptFile(ActionEvent event) throws Exception {
        String algo = algorithmComboBox.getValue();
        String mode = btnMode.getValue();
        String fichierTexte = textFilePathTextField.getText();
        String fichierCle = keyFilePathTextField.getText();
        String fichierChiffre = chiffreFileText.getText();
        
        SecretKey secretKey = ChiffrementSymetrique.getKey(algo, fichierCle);

        try {
            if (mode.equals("Chiffrement")) {
                ChiffrementSymetrique.crypt(algo, secretKey,fichierTexte , fichierChiffre);
                message.setText(ChiffrementSymetrique.message);
            } else {
                ChiffrementSymetrique.decrypt(algo, secretKey,  fichierTexte,fichierChiffre);
                message.setText(ChiffrementSymetrique.message);
            }
        } catch (Exception ex) {
            Logger.getLogger(SymmetricEncryptionViewController.class.getName()).log(Level.SEVERE, null, ex);
            message.setText(ex.getMessage());
        }
    }
    
    @FXML
    private void HandleMode(ActionEvent event) {
        // Handle mode changes if necessary
    }
}

