package org.kgusta.data_parsing;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ExchangeRateParseXml {
    public static final String DAILYRATE = "./src/main/resources/data/daily/daily.xml";

    public static void main(String[] args) {
        try {
            extractDailyRate();
        } catch (IOException | SAXException | ParserConfigurationException e) {
            e.printStackTrace();
        }
    }
    public static void extractDailyRate() throws IOException, SAXException, ParserConfigurationException {
        System.out.println(DAILYRATE);
        File file = new File(DAILYRATE);

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document document = db.parse(file);

        document.getDocumentElement().normalize();

        System.out.println("Root element: " + document.getDocumentElement().getNodeName());

        NodeList nodeList = document.getElementsByTagName("Currency");

        for (int i = 0; i< nodeList.getLength();i++) {
            Node node = nodeList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                System.out.println("Currency name :" + element.getAttribute("ISOCode"));
                System.out.println("Nominal: " + element.getElementsByTagName("Nominal").item(0).getTextContent());
                System.out.println("Value: " + element.getElementsByTagName("Value").item(0).getTextContent());
            }
        }

     }
}
