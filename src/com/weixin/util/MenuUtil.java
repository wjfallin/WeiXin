package com.weixin.util;

import net.sf.json.JSONObject;

import com.weixin.menupo.*;


public class MenuUtil {
    
	private static final String CREATE_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
	
	public static Menu initMenu(){
		Menu menu = new Menu();
		ViewButton button1 = new ViewButton();
		button1.setName("进入商城");
		button1.setType("view");
		button1.setUrl("http://www.163.com");
		
		ClickButton button21 = new ClickButton();
		button21.setName("测试菜单1");
		button21.setType("click");
		button21.setKey("2-1");
		
		ClickButton button22 = new ClickButton();
		button22.setName("测试菜单2");
		button22.setType("click");
		button22.setKey("2-2");
		
		Button button2 = new Button();
		button2.setName("测试菜单");
		button2.setSub_button(new Button[]{button21,button22});
		
		ClickButton button31 = new ClickButton();
		button31.setName("地理位置");
		button31.setType("location_select");
		button31.setKey("3-1");
		
		ClickButton button32 = new ClickButton();
		button32.setName("扫二维码");
		button32.setType("scancode_push");
		button32.setKey("3-2");
		
		Button button3 = new Button();
		button3.setName("更多");
		button3.setSub_button(new Button[]{button31,button32});
		
		menu.setButton(new Button[]{button1,button2,button3});
		return menu;
	}
	
	public static int createMenu(String token,String menu){
		int result = 0;
		String url = CREATE_MENU_URL.replace("ACCESS_TOKEN",token);
		JSONObject jsonObject = AccessUtil.doPostStr(url, menu);
		if(jsonObject != null){
			result = jsonObject.getInt("errcode");					
		}
		return result;
	}
}
