/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package projetcrypto;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Lenovo
 */
public class DiffieHellmanController implements Initializable {

    @FXML
    private ComboBox<?> participantsComboBox;
    @FXML
    private ComboBox<?> algorithmComboBox;
    @FXML
    private Button generateButton;
    @FXML
    private Button clearButton;
    @FXML
    private TextField publicKeysTextField;
    @FXML
    private TextField sharedKeyTextField;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void updateParticipantOptions(ActionEvent event) {
    }

    @FXML
    private void generateKey(ActionEvent event) {
    }

    @FXML
    private void clearFields(ActionEvent event) {
    }
    
}
