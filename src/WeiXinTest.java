import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;
import org.xml.sax.SAXException;

import com.weixin.po.AccessToken;
import com.weixin.util.AccessUtil;


public class WeiXinTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
    SAXReader reader = new SAXReader();
    File file = new File("D:/MyEclipseWorkSpace/WeiXin/WebRoot/WEB-INF/web.xml");
    FileInputStream ins;
    String text = null;
	try {
		ins = new FileInputStream(file);
		Document doc = reader.read(ins);
		text = doc.asXML();
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (DocumentException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	System.out.println(text);
	}
}
