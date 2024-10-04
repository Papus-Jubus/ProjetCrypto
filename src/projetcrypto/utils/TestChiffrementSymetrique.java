/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package projetcrypto.utils;

import javax.crypto.SecretKey;

/**
 *
 * @author Lenovo
 */
public class TestChiffrementSymetrique {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
        
        // 1. Générer une clé AES de 128 bits
			System.out.println("Génération de la clé AES...");
			ChiffrementSymetrique.genKey("AES", 128);

			// 2. Récupérer la clé générée
			     SecretKey secretKey = ChiffrementSymetrique.getKey("AES", "SecretKey.txt");
			System.out.println("Clé récupérée : " + secretKey.toString());

			// 3. Chiffrer un fichier texte
			System.out.println("Chiffrement du fichier 'clair.txt'...");
			ChiffrementSymetrique.crypt("AES", secretKey, "clair.txt", "chiffre.txt");

			// 4. Déchiffrer le fichier chiffré
			System.out.println("Déchiffrement du fichier 'chiffre.txt'...");
			ChiffrementSymetrique.decrypt("AES", secretKey, "chiffre.txt", "dechiffre.txt");

			// 5. Générer un hash du fichier clair
			System.out.println("Hashage du fichier 'clair.txt'...");
			ChiffrementSymetrique.hashFile("SHA-256", "clair.txt");
    }
    
}
