package parsing;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;


public class ParsingXmlNow {

	public String ParsingXmlnow(){
		String now = "";

		try {
			
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse("http://www.kma.go.kr/XML/weather/sfc_web_map.xml");
			
			XPath path = XPathFactory.newInstance().newXPath();
			
			NodeList cols = (NodeList)path.evaluate("//local", doc, XPathConstants.NODESET);
			for (int i = 0; i < cols.getLength(); i++) {
				if(cols.item(i).getTextContent().equals("서울")){
					System.out.println(cols.item(i).getAttributes().getNamedItem("desc").getTextContent());
					now = cols.item(i).getAttributes().getNamedItem("desc").getTextContent();
					if(now.equals("맑음")){
						now = "<img src =/img/1.png style='width: 30px;height: 25px;'/>";
					}else if(now.equals("구름조금")){
						now = "<img src =/img/2.png style='width: 30px;height: 25px;'/>";
					}else if(now.equals("구름많음")){
						now = "<img src =/img/3.png style='width: 30px;height: 25px;'/>";
					}else if(now.equals("강풍")){
						now = "<img src =/img/4.png style='width: 30px;height: 25px;'/>";
					}else if(now.equals("비")){
						now = "<img src =/img/5.png style='width: 30px;height: 25px;'/>";
					}else if(now.equals("태풍")){
						now = "<img src =/img/6.png style='width: 30px;height: 25px;'/>";
					}
					break;
				}
				
			}
			
		} catch (Exception e) {
			System.out.println("오늘의 날씨 에러  : " + e.getMessage());
		}
		return now;
	}
}
