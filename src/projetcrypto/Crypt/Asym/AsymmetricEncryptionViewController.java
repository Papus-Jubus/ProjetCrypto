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
import java.net.URL;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.fxml.Initializable;
import projetcrypto.utils.ChiffrementAsy;


import projetcrypto.utils.Utils;



public class AsymmetricEncryptionViewController implements Initializable{

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
  
    @FXML
    private Button btnEncryptFile;
    @FXML
    private Button loadFileChiffreBtn;
    @FXML
    private TextField chiffreFileText;
    @FXML
    private TextField message;
    @FXML
    private ComboBox<String> btnMode;
    
    
    @Override 
      public void initialize(URL url, ResourceBundle rb) {
          
          //On met la liste des algorithmes et Provider dans les combobox
          algorithmComboBox.setItems(FXCollections.observableArrayList("RSA", "DSA"));   
         // providerCombo.setItems(FXCollections.observableArrayList("SunRsaSign","SunJCE","BouncyCastle","SunMSCAPI", "SUN","SUNEC"));
          btnMode.setItems(FXCollections.observableArrayList("Chiffrement","Dechiffrement"));
          
        
          
          btnEncryptFile.setOnAction(e->{
              try {
                 // enCryptFile( e,algo, provider, clair, fichierCle, chiffre);
                  enCryptFile(e);
              } catch (Exception ex) {
                  Logger.getLogger(AsymmetricEncryptionViewController.class.getName()).log(Level.SEVERE, null, ex);
              }
          });
          
          
          }

    /**
     * Méthode appelée lors du clic sur le bouton pour charger un fichier texte à chiffrer.
     */
    @FXML
    private void loadTextFile(ActionEvent event) {
         Utils.loadFileNameC( loadTextFileButton, textFilePathTextField);
           }

    /**
     * Méthode appelée lors du clic sur le bouton pour charger un fichier clé.
     */
    @FXML
    private void loadKeyFile(ActionEvent event) {
         Utils.loadFileNameC( loadKeyFileButton, keyFilePathTextField);
    }
    
    
    /**
     * Method pour retourner le chemin du fichier chiffre
     * @return 
     */
    @FXML
private void loadFileChiffreBtn(ActionEvent event) {
        Utils.loadFileNameC( loadFileChiffreBtn, chiffreFileText);
        
    }




    // Méthode pour obtenir la sélection de l'algorithme
    public String getSelectedAlgorithm() {
        return algorithmComboBox.getValue();
    }

    @FXML
    private void enCryptFile(ActionEvent event) {
        String algo = algorithmComboBox.getValue();
       // String provider = providerCombo.getValue();
        String clair = textFilePathTextField.getText();
        String fichierCle = keyFilePathTextField.getText();
        String chiffre = chiffreFileText.getText();
       
        String mode = btnMode.getValue();
        
        try {
            if(mode.equals("Chiffrement")){
                PublicKey clePub = ChiffrementAsy.getPub(algo, fichierCle);
            ChiffrementAsy.crypt("RSA", clePub, clair,chiffre );
            message.setText(ChiffrementAsy.message);
            
             System.out.println("======================================="+ChiffrementAsy.message);
            }else{
                PrivateKey clePrivee = ChiffrementAsy.getPriv(algo, fichierCle);
            ChiffrementAsy.decrypt("RSA",  clePrivee, clair,chiffre );
            message.setText(ChiffrementAsy.message);
            
             System.out.println("======================================="+ChiffrementAsy.message);
            }
             
        } catch (Exception ex) {
            Logger.getLogger(AsymmetricEncryptionViewController.class.getName()).log(Level.SEVERE, null, ex);
           
           message.setText(ex.getMessage());
        }
        
    }   

    @FXML
    private void HandleMode(ActionEvent event) {
    }

}
