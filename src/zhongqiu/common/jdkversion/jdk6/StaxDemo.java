package zhongqiu.common.jdkversion.jdk6;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

//STAX处理xml
public class StaxDemo {
	public static void main(String[] arg) throws XMLStreamException, FileNotFoundException {
		readXMLByStAX();
		writeXMLByStAX();
	}

	public static void readXMLByStAX() throws XMLStreamException, FileNotFoundException {
		XMLInputFactory factory = XMLInputFactory.newInstance();
		XMLEventReader reader = factory.createXMLEventReader(new FileInputStream("test.xml"));//StaxDemo.class.getResourceAsStream("test.xml")
		XMLEvent event;
		StringBuffer parsingResult = new StringBuffer();
		while (reader.hasNext()) {
			event = reader.nextEvent();
			if (event.isStartElement()) {
				StartElement se = event.asStartElement();
				parsingResult.append("<");
				parsingResult.append(se.getName());
				if (se.getName().getLocalPart().equals("catalog")) {
					parsingResult.append("id=");
					parsingResult.append(se.getAttributeByName(new QName("id")).getValue());
					parsingResult.append("");
				}
				parsingResult.append(">");
			} else if (event.isCharacters()) {
				parsingResult.append(event.asCharacters().getData());
			} else if (event.isEndElement()) {
				parsingResult.append("</");
				parsingResult.append(event.asEndElement().getName());
				parsingResult.append(">");
			}
		}
		System.out.println(parsingResult);
	}

	public static void writeXMLByStAX() throws XMLStreamException, FileNotFoundException {
		XMLOutputFactory factory = XMLOutputFactory.newInstance();
		XMLStreamWriter writer = factory.createXMLStreamWriter(new FileOutputStream("output.xml"));
		writer.writeStartDocument();
		writer.writeCharacters(" ");
		writer.writeComment("testing comment");
		writer.writeCharacters(" ");
		writer.writeStartElement("catalogs");
		writer.writeNamespace("myNS", "http://blog.csdn.net/Chinajash");
		writer.writeAttribute("owner", "sina");
		writer.writeCharacters(" ");
		writer.writeStartElement("http://blog.csdn.net/Chinajash", "catalog");
		writer.writeAttribute("id", "007");
		writer.writeCharacters("Apparel");
		// 写入catalog元素的结束标签
		writer.writeEndElement();
		// 写入catalogs元素的结束标签
		writer.writeEndElement();
		// 结束 XML 文档
		writer.writeEndDocument();
		writer.close();
		System.out.println("ok");
	}
}
