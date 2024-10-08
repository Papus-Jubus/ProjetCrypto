package projetcrypto.utils;

import javax.crypto.KeyAgreement;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PublicKey;
import java.security.PrivateKey;
import java.security.KeyFactory;
import java.security.spec.X509EncodedKeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.MessageDigest;

public class DiffieHellman {
    private KeyPair keyPair; // Paires de clés (publique et privée)
    private SecretKey secretKey; // Clé secrète partagée

    public DiffieHellman() throws Exception {
        // Générer la paire de clés Diffie-Hellman
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("DH");
        keyPairGenerator.initialize(2048); // Taille de clé
        keyPair = keyPairGenerator.generateKeyPair();
    }

    public PublicKey getPublicKey() {
        return keyPair.getPublic(); // Retourne la clé publique
    }

    public PrivateKey getPrivateKey() {
        return keyPair.getPrivate(); // Retourne la clé privée
    }

    public void saveKeys(String publicKeyPath, String privateKeyPath) throws IOException {
        // Stocker la clé publique dans un fichier texte
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(publicKeyPath))) {
            writer.write(java.util.Base64.getEncoder().encodeToString(keyPair.getPublic().getEncoded()));
        }

        // Stocker la clé privée dans un fichier texte
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(privateKeyPath))) {
            writer.write(java.util.Base64.getEncoder().encodeToString(keyPair.getPrivate().getEncoded()));
        }
    }

    public void loadKeys(String publicKeyPath, String privateKeyPath) throws Exception {
        // Charger la clé publique à partir d'un fichier
        byte[] publicKeyBytes = Files.readAllBytes(Paths.get(publicKeyPath));
        X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(publicKeyBytes);
        PublicKey publicKey = KeyFactory.getInstance("DH").generatePublic(publicKeySpec);

        // Charger la clé privée à partir d'un fichier
        byte[] privateKeyBytes = Files.readAllBytes(Paths.get(privateKeyPath));
        PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
        PrivateKey privateKey = KeyFactory.getInstance("DH").generatePrivate(privateKeySpec);
    }

    public void generateSharedSecret(PublicKey... otherPublicKeys) throws Exception {
        // Créer un objet KeyAgreement
        KeyAgreement keyAgreement = KeyAgreement.getInstance("DH");
        keyAgreement.init(keyPair.getPrivate()); // Initialiser avec la clé privée

        // Effectuer la phase pour toutes les clés publiques reçues
        for (PublicKey otherPublicKey : otherPublicKeys) {
            keyAgreement.doPhase(otherPublicKey, true);
        }

        // Générer la clé secrète partagée
        byte[] sharedSecret = keyAgreement.generateSecret(); // Obtenir les octets de la clé secrète

        // Utiliser un hachage pour dériver une clé AES à partir de la clé secrète
        MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
        byte[] key = sha256.digest(sharedSecret);
        secretKey = new SecretKeySpec(key, 0, 16, "AES"); // Prendre les premiers 16 octets pour AES
    }

    public SecretKey getSecretKey() {
        return secretKey; // Retourne la clé secrète
    }
}
