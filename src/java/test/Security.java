package test;

import java.nio.charset.StandardCharsets;
import java.security.*;
import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.ServletContext;
import org.apache.commons.codec.binary.Base64;

public class Security {

    public static String encrypt(String strToEncrypt, ServletContext servletContext) {
        try {
            String encryptionKey = servletContext.getInitParameter("ENCRYPTION_KEY");
            String cipherAlgorithm = servletContext.getInitParameter("CIPHER_ALGORITHM");
            Cipher cipher = getCipherInstance(cipherAlgorithm);
            final SecretKeySpec secretKey = getSecretKey(encryptionKey, "AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return Base64.encodeBase64String(cipher.doFinal(strToEncrypt.getBytes(StandardCharsets.UTF_8)));
        } catch (Exception e) {
            return null;
        }
    }

    public static String decrypt(String codeDecrypt, ServletContext servletContext) {
        try {
            String encryptionKey = servletContext.getInitParameter("ENCRYPTION_KEY");
            String cipherAlgorithm = servletContext.getInitParameter("CIPHER_ALGORITHM");
            Cipher cipher = getCipherInstance(cipherAlgorithm);
            final SecretKeySpec secretKey = getSecretKey(encryptionKey, "AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return new String(cipher.doFinal(Base64.decodeBase64(codeDecrypt)), StandardCharsets.UTF_8).trim();
        } catch (Exception e) {
            return null;
        }
    }

    private static Cipher getCipherInstance(String cipherAlgorithm)
            throws NoSuchAlgorithmException, NoSuchPaddingException {
        return Cipher.getInstance(cipherAlgorithm);
    }

    private static SecretKeySpec getSecretKey(String encryptionKey, String algorithm) {
        return new SecretKeySpec(encryptionKey.getBytes(StandardCharsets.UTF_8), algorithm);
    }
}
