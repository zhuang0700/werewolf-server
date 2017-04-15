package com.telan.weixincenter.utils;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

public class DESUtil4PHP {

	private static final String AESTYPE ="AES/ECB/PKCS5Padding";  
	private static final String KEY ="BJjlfSoHoT33Wjkd"; 
	 
	/**
	* @Title: AES_Encrypt 
	* @Description:(加密) 
	* @author create by yushengwei @ 2015年9月25日 下午2:49:29 
	* @param @param plainText
	* @param @return 
	* @return String 返回类型 
	* @throws
	 */
	public static String AES_Encrypt(String plainText) { 
	        byte[] encrypt = null; 
	        try{ 
	            Key key = generateKey(KEY); 
	            Cipher cipher = Cipher.getInstance(AESTYPE); 
	            cipher.init(Cipher.ENCRYPT_MODE, key); 
	            encrypt = cipher.doFinal(plainText.getBytes());     
	        }catch(Exception e){ 
	            e.printStackTrace(); 
	        }
	        return new String(Base64.encodeBase64(encrypt)); 
	    } 
	
	/**
	* @Title: AES_Decrypt 
	* @Description:(解密) 
	* @author create by yushengwei @ 2015年9月25日 下午2:49:55 
	* @param @param encryptData
	* @param @return 
	* @return String 返回类型 
	* @throws
	 */
	public static String AES_Decrypt(String encryptData) {
	        byte[] decrypt = null; 
	        try{ 
	            Key key = generateKey(KEY); 
	            Cipher cipher = Cipher.getInstance(AESTYPE); 
	            cipher.init(Cipher.DECRYPT_MODE, key); 
	            decrypt = cipher.doFinal(Base64.decodeBase64(encryptData)); 
	        }catch(Exception e){ 
	            e.printStackTrace(); 
	        } 
	        return new String(decrypt).trim(); 
	    } 
	 
	    private static Key generateKey(String key)throws Exception{ 
	        try{            
	            SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(), "AES"); 
	            return keySpec; 
	        }catch(Exception e){ 
	            e.printStackTrace(); 
	            throw e; 
	        } 
	 
	    } 
	 
	    public static void main(String[] args) { 
	         
	        String plainText = "http://mtest.mythicflora.com/microshop/item/sharedetail?itemId=163";
	         
	        String encText = AES_Encrypt(plainText);
	        String decString = AES_Decrypt("ebb5RGnJwrMPsGuM4LBo pLM9Yf/eOwjj8ferY2RWMEF7iHiWCn7MsI4ScqEPAKym83YZ1PJvitnQMBtHlRePdkcThqXbnv7RzuipzbTA="); 
	         
	        System.out.println(encText); 
	        System.out.println(decString); 
	 
	    }  
}
