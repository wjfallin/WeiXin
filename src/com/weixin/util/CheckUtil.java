package com.weixin.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;
public class CheckUtil {
   private static final String token = "weijing";
  /**
   * ΢�ŷ�������֤
   * @param signature
   * @param timestamp
   * @param nonce
   * @return
   */
   public static boolean checkSignature(String signature,String timestamp,String nonce){
	   String[] arr = new String[]{token,timestamp,nonce};
	   //����
	   Arrays.sort(arr);
	   
	  //ƴ���ַ���
	   StringBuffer content = new StringBuffer();
	   for(int i=0;i<arr.length;i++){
		   content.append(arr[i]);
	   }
	   //sha1����
	   String temp = getSha1(content.toString());
	   
	   return temp.equals(signature);
   }
      //SHA1���ܷ���
	   public static String getSha1(String str) {
	        try {
	            MessageDigest digest = java.security.MessageDigest
	                    .getInstance("SHA-1"); //�����SHA����ֻ��Ҫ��"SHA-1"�ĳ�"SHA"����
	            digest.update(str.getBytes());
	            byte messageDigest[] = digest.digest();
	            // Create Hex String
	            StringBuffer hexStr = new StringBuffer();
	            // �ֽ�����ת��Ϊ ʮ������ ��
	            for (int i = 0; i < messageDigest.length; i++) {
	                String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
	                if (shaHex.length() < 2) {
	                    hexStr.append(0);
	                }
	                hexStr.append(shaHex);
	            }
	            return hexStr.toString();

	        } catch (NoSuchAlgorithmException e) {
	            e.printStackTrace();
	        }
	        return null;
	    }

}
