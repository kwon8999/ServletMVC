package parsing;

import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import Dto.WeatherDto;


public class ParsingXmlWeather {
	
	public List<WeatherDto> ParsingWeather(){
		List<WeatherDto> list = new ArrayList<WeatherDto>();
		WeatherDto dto = null;

		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse("http://www.kma.go.kr/weather/forecast/mid-term-rss3.jsp?stnId=109");
			
			Element element = doc.getDocumentElement();
			Node locationNode = element.getElementsByTagName("location").item(0);
			NodeList node = locationNode.getChildNodes();
			
//			System.out.println(list.getLength());  31개

			for(int i = 0 ; i < node.getLength() ; i++){
				
				
				
//				if(node.item(i).getNodeName().equals("city")){
//				dto.setCity(node.item(0).getTextContent());
//				
//				}
				
				if(node.item(i).getNodeName().equals("data")){
					dto = new WeatherDto();
					System.out.println(node.item(0).getTextContent());

//					Node data = element.getElementsByTagName("data").item(i); okjsp에 글올려 가며 얻은 수익... node.item 으로 그자리를 받을수 있었다ㅡ.ㅡ
					
					Node data = node.item(i);
					NodeList dataList = data.getChildNodes();

					for(int j = 0 ; j < dataList.getLength() ; j++){
						
						
						if(dataList.item(j).getNodeName().equals("tmEf")){
							dto.setTmEf(dataList.item(j).getTextContent());
//							System.out.println(dataList.item(j).getTextContent());
							
						}else if(dataList.item(j).getNodeName().equals("wf")){
							dto.setWf(dataList.item(j).getTextContent());
							
						}else if(dataList.item(j).getNodeName().equals("tmn")){
							dto.setTmn(dataList.item(j).getTextContent());
							
						}else if(dataList.item(j).getNodeName().equals("tmx")){
							dto.setTmx(dataList.item(j).getTextContent());
							
						}else if(dataList.item(j).getNodeName().equals("reliability")){
							dto.setReliability(dataList.item(j).getTextContent());
							
						}
					}
					System.out.println(dto.getTmEf()+"  "+ dto.getReliability() +"  "+ dto.getTmn() + "  " + dto.getTmx());
					list.add(dto);	
				}
			}

		}catch(Exception e){
			System.out.println("Weather Parsing Err : " + e);
		}
		System.out.println("list.size()" +  list.size());
		return list;
	}
}