

/**
 *
 * @author Lenovo
 */


package projetcrypto.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.Key;
import java.security.MessageDigest;
import java.security.Security;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

public class ChiffrementSymetrique {
	
	private static String chemin = "C:\\crypto\\Sym\\";

	// Gestion automatique du provider Bouncy Castle
	static {
        Security.addProvider(new BouncyCastleProvider());
    }

	// Génération de clé symétrique
	public static void genKey(String algo, int tailleCle) throws Exception {
		KeyGenerator keyGen = KeyGenerator.getInstance(algo); // Utilisation automatique du provider
		keyGen.init(tailleCle);
		
		SecretKey key = keyGen.generateKey();
		saveKey(key, "SecretKey.txt");
	}
	
	public static String message = "";

	// Sauvegarde de la clé dans un fichier
	public static void saveKey(Key key, String fichier) throws Exception {
		FileOutputStream fos = new FileOutputStream(chemin + fichier);
		fos.write(key.getEncoded());
		fos.close();
		System.out.println("La clé symétrique est bien enregistrée.");
		message = "Clé générée et enregistrée avec succès";
	}
	
	// Récupération de la clé symétrique depuis un fichier
	public static SecretKey getKey(String algo, String fichierCle) throws Exception {
		FileInputStream fis = new FileInputStream(chemin + fichierCle);
		byte[] keyBytes = new byte[fis.available()];
		fis.read(keyBytes);
		fis.close();
		
		SecretKeySpec keySpec = new SecretKeySpec(keyBytes, algo);
		return keySpec;
	}

	// Chiffrement symétrique
	public static void crypt(
			String algo,
			SecretKey key,
			String fichierClaire,
			String fichierChiffre
		) throws Exception {
		
		FileInputStream fis = new FileInputStream(chemin + fichierClaire);
		FileOutputStream fos = new FileOutputStream(chemin + fichierChiffre);
		Cipher c = Cipher.getInstance(algo); // Gestion auto du provider
		c.init(Cipher.ENCRYPT_MODE, key);
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
		message = "Chiffrement bien effectué avec l'algorithme: " + algo;
		System.out.println(message);
	}

	// Déchiffrement symétrique
	public static void decrypt(
			String algo,
			SecretKey key,
			String fichierChiffre,
			String fichierDechiffre
		) throws Exception {
		
		FileInputStream fis = new FileInputStream(chemin + fichierChiffre);
		FileOutputStream fos = new FileOutputStream(chemin + fichierDechiffre);
		Cipher c = Cipher.getInstance(algo); // Gestion auto du provider
		c.init(Cipher.DECRYPT_MODE, key);
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
                message="Déchiffrement bien effectué avec l'algorithme: " + algo;
		System.out.println(message);
	}

	// Hashage d'un fichier
	public static void hashFile(String algoHash, String fichierClaire) throws Exception {
		FileInputStream fis = new FileInputStream(chemin + fichierClaire);
		MessageDigest md = MessageDigest.getInstance(algoHash);
		
		byte[] buffer = new byte[1024];
		int bytesRead;
		
		while ((bytesRead = fis.read(buffer)) != -1) {
			md.update(buffer, 0, bytesRead);
		}
		
		byte[] hash = md.digest();
		fis.close();
		System.out.println("Hash du fichier: " + bytesToHex(hash));
	}

	// Conversion des bytes en format hexadécimal
	private static String bytesToHex(byte[] bytes) {
		StringBuilder sb = new StringBuilder();
		for (byte b : bytes) {
			sb.append(String.format("%02x", b));
		}
		return sb.toString();
	}
}

