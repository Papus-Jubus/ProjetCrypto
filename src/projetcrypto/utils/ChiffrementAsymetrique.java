package projetcrypto.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.DigestInputStream;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;


public class ChiffrementAsymetrique {
	
	private static String chemin = "C:\\M2TDSI\\Asym\\";

	public static void genKey(String algo, int tailleCle, String prov) throws Exception{
		KeyPairGenerator kpg = KeyPairGenerator.getInstance(algo, prov);
		kpg.initialize(tailleCle);
		
		KeyPair kp = kpg.generateKeyPair();
		
		saveKey(kp.getPublic(), "Pubkey.txt");
		saveKey(kp.getPrivate(), "Privkey.txt");
		
	}
	public static String message="";
       
	public static void saveKey(Key k, String fichier) throws Exception {
		FileOutputStream fos = new FileOutputStream(chemin + fichier);
		if (k.getFormat().equalsIgnoreCase("x.509")) {
			fos.write(k.getEncoded());
		}else if (k.getFormat().equalsIgnoreCase("pkcs#8")) {
			fos.write(k.getEncoded());
		}
		
		fos.close();
		System.out.println("La clé " + k.getFormat() + " est bien enregistrée");
                message = "Cle Generee et enregistree avec succes";
	}
	
	public static PublicKey getPub(String algo, String fichierClePub) throws Exception{
		FileInputStream fis = new FileInputStream(chemin + fichierClePub);
		
		byte[] cle = new byte[fis.available()];
		
		fis.read(cle);
		
		X509EncodedKeySpec kx509spec = new X509EncodedKeySpec(cle);
		
		KeyFactory kf = KeyFactory.getInstance(algo);
		
		PublicKey kpub = kf.generatePublic(kx509spec);
		
		fis.close();
		
		return kpub;
	}
	
	public static PrivateKey getPriv(String algo, String fichierClePriv) throws Exception{
		FileInputStream fis = new FileInputStream(chemin + fichierClePriv);
		
		byte[] clePriv = new byte[fis.available()];
		
		fis.read(clePriv);
		
		PKCS8EncodedKeySpec privKeySpec = new PKCS8EncodedKeySpec(clePriv);
		
		KeyFactory kf =  KeyFactory.getInstance(algo);
		
		PrivateKey kpriv = kf.generatePrivate(privKeySpec);
		
		fis.close();
		
		return kpriv;
		
	}
	
	public static void crypt(
			String algo,
			String provider,
			PublicKey pub,
			String fichierClaire,
			String fichierChiffre
		) throws Exception{
		
		FileInputStream fis = new FileInputStream(chemin + fichierClaire);
		FileOutputStream fos = new FileOutputStream(chemin + fichierChiffre);
		Cipher c = Cipher.getInstance(algo, provider);
		c.init(Cipher.ENCRYPT_MODE, pub);
		CipherInputStream cis = new CipherInputStream(fis, c);
		
		byte[] nombreBytesLu = new byte[8];
		int i = cis.read(nombreBytesLu);
		
		while(i != -1) {
			fos.write(nombreBytesLu, 0, i);
			i = cis.read(nombreBytesLu);
		}
		fis.close();
		fos.close();
		cis.close();
		System.out.println("Chiffrement bien effectué avec l'algorithme: " + algo);
	}
	
	public static void decrypt(
			String algo,
			String provider,
			PrivateKey priv,
			String fichierChiffre,
			String fichierDechiffre
		) throws Exception {
		
		FileInputStream fis = new FileInputStream(chemin + fichierChiffre);
		FileOutputStream fos = new FileOutputStream(chemin + fichierDechiffre);
		Cipher c = Cipher.getInstance(algo, provider);
		c.init(Cipher.DECRYPT_MODE, priv);
		CipherInputStream cis = new CipherInputStream(fis, c);
		
		byte[] nombreBytesLu = new byte[8];
		int i = cis.read(nombreBytesLu);
		
		while(i != -1) {
			fos.write(nombreBytesLu, 0, i);
			i = cis.read(nombreBytesLu);
		}
		fis.close();
		fos.close();
		cis.close();
		System.out.println("Déchiffrement bien effectué avec l'algorithme: " + algo);
	}
	
	public static void sign(
			PrivateKey privateKey, 
			String ficIn, 
			String ficOut,
			String algoHash,
			String algoSign
			) throws Exception {
		FileInputStream fis = new FileInputStream(chemin + ficIn);
		FileOutputStream fos = new FileOutputStream(chemin + ficOut);
		
		MessageDigest md = MessageDigest.getInstance(algoHash);
		DigestInputStream dis = new DigestInputStream(fis, md);
		Signature sig = Signature.getInstance(algoSign);
		
		byte[] mess = new byte[fis.available()];
		fis.read(mess);
		
		dis.read(mess);
		byte[] hash = md.digest();
		sig.initSign(privateKey);
		sig.update(hash);
		byte[] signature = sig.sign();
		fos.write(signature);
		fis.close();
		fos.close();
		dis.close();
		System.out.println("Signature effectuée !");
	}
	
	public static void verify(
			PublicKey publicKey, 
			String ficMessage, 
			String ficSignature, 
			String AlgoHash, 
			String algoSign) throws Exception {
		
		FileInputStream fisM = new FileInputStream(chemin + ficMessage);
		FileInputStream fisS = new FileInputStream(chemin + ficSignature);
		MessageDigest md = MessageDigest.getInstance(AlgoHash);
		DigestInputStream dis = new DigestInputStream(fisM, md);
		Signature sig = Signature.getInstance(algoSign);
		
		byte[] mess = new byte[fisM.available()];
		byte[] sign = new byte[fisS.available()];
		
		fisM.read(mess);
		fisS.read(sign);
		dis.read(mess);
		
		byte[] hash = md.digest();
		sig.initVerify(publicKey);
		sig.update(hash);
		boolean verif = sig.verify(sign);
		System.out.println("Verification: " + verif);
		fisM.close();
		fisS.close();
		dis.close();
	}
	
/*
 * 	Examen: Créer une application avec interface graphique:
 * 	- Generer des clés
 *  - Faire le partage de clé Diffie-Hellman
 *  - Faire les chiffrements symetrique et asymetrique
 *  - Faire le hashage avec clé et sans clé
 *  - Faire de la signature numérique
 *  - Voir comment stocker les fichiers dans des bases de données 
 *  
 *  ---------------------
 *  Interface de connexion
 *  Ajouter et supprimer un utilisateur
 *  
 *  ---------------------
 *  Mettre le code dans un meme package portant notre nom,
 *  Creer un ficher jar de l'application
 *  Si on a une base de donnée, exporter le ficier sql
 *  Faire un rapport sous latex: fichier tex et fichier pdf
 *  
 *  Chemins:C:/projetcrypto/|
 *  						|- crypt/
 *  						|  |-- sym/
 *  						|  |-- asym/
 *  						|- hash/
 *  						|- sign/
 *  Zipper les fichiers: 
 *  - package
 *  - fichier jar
 *  - fichier sql
 *  - fichier tex
 *  - fichier pdf
 *  dans un fichier portant notre nom et prenom
 */
	
	
}
