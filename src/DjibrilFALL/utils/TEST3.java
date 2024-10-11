/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package projetcrypto.utils;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import java.security.Security;
/**
 *
 * @author Lenovo
 */
public class TEST3 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        
          
        // TODO code application logic here
         ChiffrementAsy.genKey("RSA", 1024);
    }
    
}
