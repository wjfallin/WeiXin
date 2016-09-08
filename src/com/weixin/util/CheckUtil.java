package com.weixin.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;
public class CheckUtil {
   private static final String token = "weijing";
  /**
   * 微信服务器验证
   * @param signature
   * @param timestamp
   * @param nonce
   * @return
   */
   public static boolean checkSignature(String signature,String timestamp,String nonce){
	   String[] arr = new String[]{token,timestamp,nonce};
	   //排序
	   Arrays.sort(arr);
	   
	  //拼接字符串
	   StringBuffer content = new StringBuffer();
	   for(int i=0;i<arr.length;i++){
		   content.append(arr[i]);
	   }
	   //sha1加密
	   String temp = getSha1(content.toString());
	   
	   return temp.equals(signature);
   }
      //SHA1加密方法
	   public static String getSha1(String str) {
	        try {
	            MessageDigest digest = java.security.MessageDigest
	                    .getInstance("SHA-1"); //如果是SHA加密只需要将"SHA-1"改成"SHA"即可
	            digest.update(str.getBytes());
	            byte messageDigest[] = digest.digest();
	            // Create Hex String
	            StringBuffer hexStr = new StringBuffer();
	            // 字节数组转换为 十六进制 数
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
