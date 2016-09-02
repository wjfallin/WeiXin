package com.weixin.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;








import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.thoughtworks.xstream.XStream;
import com.weixin.po.News;
import com.weixin.po.NewsMessage;
import com.weixin.po.TextMessage;
import com.weixin.servlet.weixinServlet;

public class messageUtil {
	
	  public static final String MESSAGE_TEXT = "text";
	  public static final String MESSAGE_NEWS = "news";  
	  public static final String MESSAGE_IMAGE = "image";
	  public static final String MESSAGE_VOICE = "voice";
	  public static final String MESSAGE_VIDEO = "video";
	  public static final String MESSAGE_LINK = "link";
	  public static final String MESSAGE_LOCATION = "location";
	  public static final String MESSAGE_EVENT = "event";
	  public static final String MESSAGE_SUBSCRIBE = "subscribe";
	  public static final String MESSAGE_UNSUBSCRIBE = "unsubscribe";
	  public static final String MESSAGE_CLICK = "CLICK";
	  public static final String MESSAGE_VIEW = "VIEW";
/**
 * XML����ת��ΪMap����
 * @param req
 * @return
 * @throws IOException
 * @throws DocumentException
 */
  public static Map<String,String> xmlToMap(HttpServletRequest req) throws IOException,DocumentException{
	  Map<String,String> map = new HashMap<String,String>();
	  SAXReader reader = new SAXReader();
	  InputStream ins = req.getInputStream();
	  Document doc =  reader.read(ins);
	  
	  Element root = doc.getRootElement();
	  List<Element> list = root.elements();
	  
	  for(Element e:list){
		  map.put(e.getName(),e.getText());
	  }
	  ins.close();
	  return map;
	  }
  
  /**
   * ��װ�ı���Ϣ
   * @param textmessage
   * @return
   */
  public static String textMessageToXml(TextMessage textmessage){
	  XStream xstream = new XStream();
	  xstream.alias("xml", textmessage.getClass());
	  return xstream.toXML(textmessage);
  }
  /**
   * �ظ��ı���Ϣ
   * @param fromUserName
   * @param toUserName
   * @param map
   * @return
   */
  public static String initTextMessage(String fromUserName,String toUserName,Map<String,String> map){
	  TextMessage textMessage = new TextMessage();
	  textMessage.setFromUserName(toUserName);
	  textMessage.setToUserName(fromUserName);
	  textMessage.setCreateTime(new Date().getTime());
	  textMessage.setMsgType("text");
	  textMessage.setContent("�����͵���Ϣ�ǣ�"+map.get("content"));
	  
	  return textMessageToXml(textMessage);
  }
  
  public static String newsMessageToXML(NewsMessage newsmessage){
	  XStream xstream = new XStream();
	  xstream.alias("xml", newsmessage.getClass());
	  xstream.alias("item", new News().getClass());
	  return xstream.toXML(newsmessage);
  }
  /**
   * �ظ�ͼ����Ϣ
   * @param news
   * @return
   */
  public static String initNewsMessage(String fromUserName,String toUserName,Map<String,String> map){
      News news = new News();
      NewsMessage newsMessage = new NewsMessage();
      List<News> list = new ArrayList();
      news.setDescription("����һ������ͼ����Ϣ");
      news.setPicUrl("");
      news.setTitle("ͼ����Ϣ");
      news.setUrl("www.baidu.com");
      list.add(news);
      
      newsMessage.setFromUserName(map.get("toUserName"));
      newsMessage.setToUserName(map.get("fromUserName"));
      newsMessage.setCreateTime(new Date().getTime());
      newsMessage.setMsgType(MESSAGE_NEWS);
      newsMessage.setArticlecount(list.size());
      newsMessage.setArticles(list);
      
      return newsMessageToXML(newsMessage);
     
  }
  
}
