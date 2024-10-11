/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package projetcrypto.utils;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

/**
 *
 * @author Lenovo
 */
public class Test4 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
         //ChiffrementAsy.genKey("RSA", 1024);
         
         RSAPublicKey rsaKpub = (RSAPublicKey)ChiffrementAsy.getPub("RSA", "Pubkey.txt");
		RSAPrivateKey rsaKPriv = (RSAPrivateKey)ChiffrementAsy.getPriv("RSA", "Privkey.txt");

		System.out.println("clé publique rsa: " + Utils.toHex(rsaKPriv.getEncoded()));
                
                ChiffrementAsy.crypt("RSA", rsaKpub, "Claire.txt", "Chiffre.txt");
                
                ChiffrementAsy.decrypt("RSA",  rsaKPriv, "Chiffre.txt", "TestDechiffre.txt");
           
    }
    
}
