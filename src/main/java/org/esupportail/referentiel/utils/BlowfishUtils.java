package org.esupportail.referentiel.utils;

import java.io.Serializable;
import java.math.BigInteger;
import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

/**
 * @author Matthieu Manginot : matthieu.manginot@univ-nancy2.fr
 *
 */
public class BlowfishUtils implements Serializable {
	/**
	 * Logger
	 */
	/**
	 * 
	 */
	final transient static Logger logger = LoggerFactory.getLogger(BlowfishUtils.class);
	/**
	 * Taille de la clï¿½
	 */
	public final static int KEY_SIZE = 128;  // [32..448]

	/**
	 * Retourne toutes les informations de la clï¿½ sous forme d'un tableau de
	 * bytes. Elle peut ainsi ï¿½tre stockï¿½e puis reconstruite ultï¿½rieurement en
	 * utilisant la mï¿½thode setSecretKey(byte[] keyData)
	 * @param secretKey 
	 * @return byte[]
	 */
	public static byte[] getSecretKeyInBytes(Key secretKey) {
		return secretKey.getEncoded();
	}

	/**
	 * Permet de reconstruire la clï¿½ secrï¿½te ï¿½ partir de ses donnï¿½es, stockï¿½es 
	 * dans un tableau de bytes.
	 * @param keyData 
	 * @return Key
	 */
	public static Key getSecretKeyFromBytes(byte[] keyData) {
		return new SecretKeySpec(keyData, "Blowfish");    
	}

	/**
	 * Gï¿½nï¿½ration d'une clï¿½
	 * @return Key
	 */
	public static Key generateKey() {
		try {
			KeyGenerator keyGen = KeyGenerator.getInstance("Blowfish");
			keyGen.init(KEY_SIZE);
			return keyGen.generateKey();  
		}
		catch (Exception e) {
			logger.error(""+e);
			return null;
		} 
	}

	/**
	 * Cryptage d'un tableau de bytes
	 * @param secretKey 
	 * @param plaintext
	 * @return byte[]
	 */
	public static byte[] crypt(Key secretKey, byte[] plaintext) {
		try {
			Cipher cipher = Cipher.getInstance("Blowfish");
			cipher.init(Cipher.ENCRYPT_MODE, secretKey);
			return cipher.doFinal(plaintext);    
		}
		catch (Exception e) {logger.error(""+e);} 
		return null;
	}

	/**
	 * Cryptage d'un string
	 * @param secretKey 
	 * @param plaintext
	 * @return byte[] 
	 */
	public static byte[] crypt(Key secretKey, String plaintext) {
		return crypt(secretKey, plaintext.getBytes());
	}

	/**
	 * Dï¿½cryptage en tableau de bytes
	 * @param secretKey 
	 * @param ciphertext
	 * @return byte[]
	 */
	public static byte[] decryptInBytes(Key secretKey, byte[] ciphertext) {
		try {
			Cipher cipher = Cipher.getInstance("Blowfish");
			cipher.init(Cipher.DECRYPT_MODE, secretKey);
			return cipher.doFinal(ciphertext);
		}
		catch (Exception e) {
			logger.error("{}",e.getMessage());
		} 
		return new byte[0];
	}

	/**
	 * Dï¿½cryptage en string
	 * @param secretKey 
	 * @param ciphertext
	 * @return String
	 */
	public static String decryptInString(Key secretKey, byte[] ciphertext) {
		if(secretKey!=null && ciphertext!=null){
			return new String(decryptInBytes(secretKey, ciphertext));
		}
		return null;
	}
	

	
	/**
	 * Clï¿½ de cryptage/dï¿½cryptage Blowfish
	 */
	private String blowfishKey;

	/**
	 * @return the blowfishKey
	 */
	public String getBlowfishKey() {
		return blowfishKey;
	}

	/**
	 * @param blowfishKey the blowfishKey to set
	 */
	public void setBlowfishKey(String blowfishKey) {
		this.blowfishKey = blowfishKey;
	}

	/**
	* Encode la chaine passée en parametre avec Blowfish
	* @param chaine
	* @return la chaine cryptï¿½e en BigInteger
	*/
    
	public String encode(String chaine) {
		BigInteger bi = new BigInteger(blowfishKey);
		byte[] ciphertext = BlowfishUtils.crypt(BlowfishUtils.getSecretKeyFromBytes(bi.toByteArray()), chaine);   
		return new BigInteger(ciphertext)+"";
	}
	
	/**
	 * @param chaine
	 * @return chaine dï¿½cryptï¿½ avec la clï¿½ Blowfish
	 */
	public String decode(String chaine){
		String plainText = "";
		if(StringUtils.hasText(chaine) && StringUtils.hasText(blowfishKey)){
			byte[] encrypText = new BigInteger(chaine).toByteArray();
			BigInteger bi = new BigInteger(blowfishKey);
			plainText = BlowfishUtils.decryptInString(BlowfishUtils.getSecretKeyFromBytes(bi.toByteArray()), encrypText);
		}
		return plainText;
	}
}
