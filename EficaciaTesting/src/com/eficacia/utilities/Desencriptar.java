package com.eficacia.utilities;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import com.sun.org.apache.xml.internal.security.exceptions.Base64DecodingException;
import com.sun.org.apache.xml.internal.security.utils.Base64;

public class Desencriptar {

	public static void main(String[] args) throws IOException, NoSuchAlgorithmException, Base64DecodingException, IllegalBlockSizeException, BadPaddingException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException {
		Desencriptar des = new Desencriptar();
		String enc = des.encriptar("ViEr1148");
		System.out.println("ENCRIPTAR: " + enc);
		System.out.println("DESENCRIPTAR: " + des.desencriptar(enc));
	}

	
	public String encriptar(String contrasenaPlana) throws NoSuchAlgorithmException, UnsupportedEncodingException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException{
		final String llaveCifrado = "ABCDEFGHIJKLMOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";
		
		final MessageDigest md = MessageDigest.getInstance("md5");
		final byte[] digestOfPassword = md.digest(llaveCifrado.getBytes("utf-8"));
		final byte[] llaveBytes = Arrays.copyOf(digestOfPassword, 24);
		for (int j = 0, k = 16; j < 8;) {
			llaveBytes[k++] = llaveBytes[j++];
        }
		final SecretKey llave = new SecretKeySpec(llaveBytes, "DESede");
        final Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, llave);
		final byte[] contrasenaPlanaBytes = contrasenaPlana.getBytes("utf-8");
		final byte[] textoCifradoBytes = cipher.doFinal(contrasenaPlanaBytes);
		final String textoCifradoCadena = new sun.misc.BASE64Encoder().encode(textoCifradoBytes);
		
		return textoCifradoCadena;
	}
	
	public String desencriptar(String textoEncriptado) throws IOException, NoSuchAlgorithmException, Base64DecodingException, IllegalBlockSizeException, BadPaddingException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException {
		String llaveCifrado = "ABCDEFGHIJKLMOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";
		//String textoEncriptado = "2GauKY6ONR2xsV4IrlrhpA==";
		//String textoEncriptado = "0GQ/gr9o+SOxsV4IrlrhpA==";

		byte[] Array_a_Descifrar =  Base64.decode(textoEncriptado);
		
		final MessageDigest md = MessageDigest.getInstance("md5");
        final byte[] digestOfPassword = md.digest(llaveCifrado.getBytes("utf-8"));
        final byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
        for (int j = 0, k = 16; j < 8;) {
            keyBytes[k++] = keyBytes[j++];
        }

        final SecretKey key = new SecretKeySpec(keyBytes, "DESede");
        final Cipher decipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
        decipher.init(Cipher.DECRYPT_MODE, key);

        final byte[] plainText = decipher.doFinal(Array_a_Descifrar);
        return new String(plainText, "UTF-8");
      //  System.out.println("DESENCRIPTAR: " + new String(plainText, "UTF-8"));
	}
	
}
