/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package projetcrypto;

import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author MSI
 */
public class Projetcrypto extends Application {
    
    @Override
    public void start(Stage primaryStage) {
         
        Button btn = new Button();
        btn.setText("Say 'Hello World'");
          btn.setStyle("-fx-background-color: red;");
         
        btn.setOnAction((ActionEvent event)-> {
              
            System.out.println("Hello World!");
            
            
            //navigateToView(event, "C:\\projetcrypto\\src\\projetcrypto\\genkey\\KeyGeneratorView");
            //navigateToView(event, "/projetcrypto/genkey/KeyGeneratorView.fxml");
            navigateToView(event, "/projetcrypto/Dashbord/Dashbord_view.fxml");
          

        });
        
       
        
        StackPane root = new StackPane();
        
        root.getChildren().add(btn);
        
        Scene scene = new Scene(root, 300, 250);
        
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        
        
        
        
        
        
    } 
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
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
       
        private void navigateToViewer( String viewPath) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(viewPath));
            Scene scene = new Scene(root, 300, 250);
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Erreur lors du chargement de la vue : " + viewPath);
        }
    
}
