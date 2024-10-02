package projetcrypto.utils;

import java.security.Security;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

import projetcrypto.utils.Utils;

public class TestChiffrementAsymetrique {
        
	public static void main(String[] args) throws Exception{ 
           
		// TODO Auto-generated method stub
                //ChiffrementAsymetrique.genKey("RSA", 1024, "SunRsaSign");
		
                RSAPublicKey rsaKpub = (RSAPublicKey)ChiffrementAsymetrique.getPub("RSA", "Pubkey.txt");
		RSAPrivateKey rsaKPriv = (RSAPrivateKey)ChiffrementAsymetrique.getPriv("RSA", "Privkey.txt");

		System.out.println("clé publique rsa: " + Utils.toHex(rsaKPriv.getEncoded()));
		
		ChiffrementAsymetrique.crypt("RSA", "BC", rsaKpub, "Claire.txt", "Chiffre.txt");
		
//		ChiffrementAsymetrique.decrypt("RSA", "BC", rsaKPriv, "Chiffre.txt", "Dechiffre.txt");
		
//		ChiffrementAsymetrique.sign(rsaKPriv, "Claire.txt", "Signature.txt", "SHA256", "SHA256withRSA");
//	ChiffrementAsymetrique.verify(rsaKpub, "Claire.txt", "Signature.txt", "SHA256", "SHA256withRSA");
	}

}
