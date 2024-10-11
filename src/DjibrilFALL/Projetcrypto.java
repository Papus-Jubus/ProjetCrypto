package projetcrypto;

import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Projetcrypto extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        
        // Create login and password fields
        Label usernameLabel = new Label("Nom d'utilisateur :admin 😊");
        TextField usernameField = new TextField();
        usernameField.setPromptText("admin");
        
        Label passwordLabel = new Label("Mot de passe :");
        PasswordField passwordField = new PasswordField();
         passwordField.setPromptText("admin123");
       
        
        // Create login button
        Button loginBtn = new Button("Login");
        loginBtn.setStyle("-fx-background-color: green; -fx-text-fill: white;");
        
        // Handle login button click
        loginBtn.setOnAction((ActionEvent event) -> {
            String username = usernameField.getText();
            String password = passwordField.getText();
            
            // Simulate login (replace this with your authentication logic)
            if (isValidCredentials(username, password)) {
                System.out.println("Connexion réussie !");
                navigateToView(event, "/projetcrypto/Dashbord/Dashbord_view.fxml");
            } else {
                System.out.println("Échec de la connexion : identifiants incorrects.");
            }
        });
        
        // Create the layout
        VBox root = new VBox(10);  // Spacing of 10 between elements
        root.getChildren().addAll(usernameLabel, usernameField, passwordLabel, passwordField, loginBtn);
        root.setStyle("-fx-padding: 20; -fx-alignment: center;");
        
        // Set the scene
        Scene scene = new Scene(root, 300, 250);
        
        primaryStage.setTitle(" FlexTech Crypto ");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    // Simulate checking login credentials
    private boolean isValidCredentials(String username, String password) {
        // Replace this with real authentication logic
        return "admin".equals(username) && "admin123".equals(password);
    }
    
    // Navigate to a new view
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

    public static void main(String[] args) {
        launch(args);
    }
}
