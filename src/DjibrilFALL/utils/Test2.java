/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package projetcrypto.utils;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import javax.crypto.Cipher;

import java.security.Provider;
import java.security.Security;
import java.util.Enumeration;

/**
 *
 * @author Lenovo
 */
public class Test2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
       
                      
             try {
      Provider p[] = Security.getProviders();
      for (int i = 0; i < p.length; i++) {
          System.out.println(p[i]);
          for (Enumeration e = p[i].keys(); e.hasMoreElements();)
              System.out.println("\t" + e.nextElement());
      }
    } catch (Exception e) {
      System.out.println(e);
    }
  }
    }
    

