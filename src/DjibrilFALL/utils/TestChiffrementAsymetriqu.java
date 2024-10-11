
import java.security.PrivateKey;
import java.security.PublicKey;
import projetcrypto.utils.ChiffrementAsy;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */


/**
 *
 * @author Lenovo
 */
public class TestChiffrementAsymetriqu {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
        
        // Générer une paire de clés RSA
            System.out.println("Génération de la paire de clés RSA...");
            ChiffrementAsy.genKey("RSA", 2048);

            // Récupérer la clé publique et la clé privée
            PublicKey publicKey = ChiffrementAsy.getPub("RSA", "Pubkey.txt");
            PrivateKey privateKey = ChiffrementAsy.getPriv("RSA", "Privkey.txt");

            // Message à signer
            String messageFichier = "claire.txt";
            String signatureFichier = "signature.txt";

            // Étape 1: Signer le message
            System.out.println("Signature du fichier...");
            ChiffrementAsy.sign(privateKey, messageFichier, signatureFichier, "SHA-256", "SHA256withRSA");

            // Étape 2: Vérifier la signature
            System.out.println("Vérification de la signature...");
            ChiffrementAsy.verify(publicKey, messageFichier, signatureFichier, "SHA-256", "SHA256withRSA");
    }
    
}
