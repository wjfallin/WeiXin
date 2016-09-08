package com.weixin.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.DocumentException;

import com.weixin.po.TextMessage;
import com.weixin.util.CheckUtil;
import com.weixin.util.messageUtil;

public class weixinServlet extends HttpServlet {
  
  
protected void doGet(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {
     String signature = req.getParameter("signature");
     String timestamp = req.getParameter("timestamp");
     String nonce = req.getParameter("nonce");
     String echostr = req.getParameter("echostr");
     
     PrintWriter out = resp.getWriter();
     if(CheckUtil.checkSignature(signature, timestamp, nonce)){
    	 out.print(echostr);
     }
}
  
  @Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
	    req.setCharacterEncoding("UTF-8");
	    resp.setCharacterEncoding("UTF-8");
	      PrintWriter out = resp.getWriter();
		try {
			Map<String,String> map = messageUtil.xmlToMap(req);
			String fromUserName = map.get("FromUserName");
			String toUserName = map.get("ToUserName");
			String createTime = map.get("CreateTime");
			String msgType = map.get("MsgType");

						
			if(messageUtil.MESSAGE_TEXT.equals(msgType)){
				String message = messageUtil.initTextMessage(fromUserName,toUserName,map);
				out.print("success");
				out.print(message);
			}else if(messageUtil.MESSAGE_EVENT.equals(msgType)){
				if(messageUtil.MESSAGE_SUBSCRIBE.equals(map.get("Event"))){
				String newsMessage = messageUtil.initNewsMessage(fromUserName, toUserName, map);
				//out.print("success");
				System.out.println(newsMessage);
				out.print(newsMessage);
				}
			}

		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			out.close();
		}
	}
}
