package com.weixin.util;

import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.weixin.po.AccessToken;

import net.sf.json.JSONObject;


public class AccessUtil {
   private static final String APPID = "wxbf9250274f5cfb3c";
   private static final String APPSECRET = "950b7aaea33930e93eb2a4b063ebca6a";
   private static final String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
   
   public static JSONObject doGetStr(String url){
	   CloseableHttpClient httpClient = HttpClients.createDefault();
	   HttpGet httpGet = new HttpGet(url);
	   JSONObject jsonObject = null;
	   try {
	    CloseableHttpResponse response = httpClient.execute(httpGet);
		HttpEntity entity =response.getEntity();
		if(entity != null){
			String result = EntityUtils.toString(entity,"UTF-8");
			jsonObject = JSONObject.fromObject(result);
			
		}
	   } catch (ClientProtocolException e) {
			e.printStackTrace();
	
	   } catch (IOException e) {
		e.printStackTrace();
	 }
	   return jsonObject;
   } 
   
 public static JSONObject doPostStr(String url,String outStr){
	   CloseableHttpClient httpClient = HttpClients.createDefault();
	   HttpPost httpPost = new HttpPost(url);
	   JSONObject jsonObject = null;
	   
	   httpPost.setEntity(new StringEntity(outStr,"UTF-8"));
	   try {
		CloseableHttpResponse response1 = httpClient.execute(httpPost);
		String result = EntityUtils.toString(response1.getEntity(),"UTF-8");
		jsonObject = JSONObject.fromObject(result);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	   
	   return jsonObject;
   }
 
 public static AccessToken getAccessToken(){
	 AccessToken token = new AccessToken();
	 String url = ACCESS_TOKEN_URL.replace("APPID",APPID).replace("APPSECRET", APPSECRET);
	 JSONObject jsonObject = doGetStr(url);
	 
	 if(jsonObject != null){
		 token.setAccess_Token(jsonObject.getString("access_token"));
		 token.setExpire_in(jsonObject.getInt("expires_in"));
	 }
	 return token;
 }
}
