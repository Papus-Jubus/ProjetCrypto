package projetcrypto.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * General utilities for the second chapter examples.
 */
public class Utils
{
    private static String digits = "0123456789abcdef";

    /**
     * Return length many bytes of the passed in byte array as a hex string.
     *
     * @param data the bytes to be converted.
     * @param length the number of bytes in the data block to be converted.
     * @return a hex representation of length bytes of data.
     */
    public static String toHex(byte[] data, int length)
    {
        StringBuffer    buf = new StringBuffer();

        for (int i = 0; i != length; i++)
        {
            int v = data[i] & 0xff;

            buf.append(digits.charAt(v >>4));
            buf.append(digits.charAt(v & 0xf));
        }

        return buf.toString();
    }
    
    /**
     * Methode permettant de prendre le nom d un fichier choisi
     */
    
     public static void loadFileNameC(Button btnLoader, TextField chamText) {
    FileChooser fileChooser = new FileChooser();
    fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Fichiers Texte", "*.txt"));

    Stage stage = (Stage) btnLoader.getScene().getWindow();
    File selectedFile = fileChooser.showOpenDialog(stage);

    if (selectedFile != null) {
        // Récupère uniquement le nom du fichier et l'affiche dans le champ texte
        chamText.setText(selectedFile.getName());
    }
}

    /**
     * Return the passed in byte array as a hex string.
     *
     * @param data the bytes to be converted.
     * @return a hex representation of data.
     */
    public static String toHex(byte[] data)
    {
        return toHex(data, data.length);
    }
    
    /**
     * METHODE QUI PERMET DE RECUPERER UN CHEMIN D UN FICHIER SUR UN CHAMP TEXTFIELD
     */
    
    private void loadFileChiffreBtn(ActionEvent event,Button btnLoader,TextField chamText) 
    
    {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Fichiers Texte", "*.txt"));

        Stage stage = (Stage) btnLoader.getScene().getWindow();
        File selectedFile = fileChooser.showOpenDialog(stage);

        if (selectedFile != null) {
            chamText.setText(selectedFile.getAbsolutePath());

            try (BufferedReader reader = new BufferedReader(new FileReader(selectedFile))) {
                StringBuilder fileContent = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    fileContent.append(line).append("\n");
                }
                System.out.println(fileContent.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    
    
   

}

