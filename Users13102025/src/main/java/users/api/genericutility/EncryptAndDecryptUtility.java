package users.api.genericutility;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
//Copied from the Google. Get understand this program.

public class EncryptAndDecryptUtility {
	
	    private final String secretKey;

	    public EncryptAndDecryptUtility(String secretKey) {
	        this.secretKey = secretKey;
	    }

	    public String encrypt(String data) throws Exception {
	        SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(), "AES");
	        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
	        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
	        byte[] encryptedBytes = cipher.doFinal(data.getBytes());
	        return Base64.getEncoder().encodeToString(encryptedBytes);
	    }

	    public String decrypt(String encryptedData) throws Exception {
	        SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(), "AES");
	        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
	        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
	        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedData));
	        return new String(decryptedBytes);
	    }

	    public static void main(String[] args) throws Exception {
	         String key = "YourSecretKey123"; // Replace with your secret key (must be 16, 24, or 32 bytes long)
	         EncryptAndDecryptUtility aesUtil = new EncryptAndDecryptUtility(key);

	        String data = "Sensitive data to be encrypted";
	        String encryptedData = aesUtil.encrypt(data);
	        System.out.println("Encrypted data: " + encryptedData);

	        String decryptedData = aesUtil.decrypt(encryptedData);
	        System.out.println("Decrypted data: " + decryptedData);
	    }
	}

