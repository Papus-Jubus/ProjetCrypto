/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package projetcrypto.genkey;


import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import projetcrypto.utils.ChiffrementAsymetrique;

/**
 * FXML Controller class
 *
 * @author MSI
 */


public class KeyGeneratorViewController implements Initializable {

    @FXML
    private ComboBox<String> type_cryptographie;
    @FXML
    private Button btnGenererKey;
    @FXML
    private ComboBox<String> listeAlgoCombo;
    @FXML
    private ComboBox<Integer> comboTailleCle;
    @FXML
    private ComboBox<String> comboProvider;
    @FXML
    private Label labelMessage;
    
  // String selectedValue = type_cryptographie.getValue(); //on recupere la valeur du type de chiffrement
 
    /**
     * Initializes the controller class.
     */
    
  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       // type_cryptographie.setItems(FXCollections.observableArrayList("Symétrique", "Asymétrique"));
       // // Valeur par défaut
       
        ObservableList<String> options = FXCollections.observableArrayList(
        "Symetrique",
        "Asymetrique"
        
    );
    type_cryptographie.setItems(options);
    type_cryptographie.setValue("Asymétrique");
    
    type_cryptographie.setOnAction(e->{
      
        String type_crypto = type_cryptographie.getValue();
        
        if(type_crypto.equals("Symetrique")){
              listeAlgoCombo.setItems(FXCollections.observableArrayList("AES", "DES","3DES"));
              comboTailleCle.setItems(FXCollections.observableArrayList(64,128,192));
              comboProvider.setItems(FXCollections.observableArrayList("sunJCE"));
        
    }else{
     listeAlgoCombo.setItems(FXCollections.observableArrayList("RSA", "DSA"));   
     comboProvider.setItems(FXCollections.observableArrayList("SunRsaSign", "SUN","SUNEC"));
     comboTailleCle.setItems(FXCollections.observableArrayList(64,128,192,256,512,1024,2048));
        
}
    });
    /**
     * 
     * //On fixe les tailles des cles en fonction de DES et 3DES 
    String algo = listeAlgoCombo.getValue();
    if(algo.equals("DES") | algo.equals("3DES"))
    listeAlgoCombo.setOnAction(e->{
        if(algo.equals("DES")){
            comboTailleCle.setValue("64");
        }else if(algo.equals("3DES")){
            comboTailleCle.setValue("168");
        }
    });
     */
    
    }

    @FXML
    private void handletypechiffrement(ActionEvent event) {
    }

    @FXML
    private void GenerateKey(ActionEvent event) {
        String algo = listeAlgoCombo.getValue();
        int taille = comboTailleCle.getValue();
        String provider = comboProvider.getValue();
       
        try {
            ChiffrementAsymetrique.genKey(algo, taille, provider);
             labelMessage.setText(ChiffrementAsymetrique.message);
        } catch (Exception ex) {
            Logger.getLogger(KeyGeneratorViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    

    @FXML
    private void handleListAlgo(ActionEvent event) {
                
}
    

    @FXML
    private void HandleTAilleCle(ActionEvent event) {
    }

    @FXML
    private void handletypechiffrement(KeyEvent event) {
        
    }

    @FXML
    private void setProvider(ActionEvent event) {
    }
   
}
