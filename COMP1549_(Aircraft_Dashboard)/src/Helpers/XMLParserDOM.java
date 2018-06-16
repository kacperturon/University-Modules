/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Helpers;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * @author Kacper
 * @co-author Samuel
 */
public class XMLParserDOM implements IXMLParser {
    //Running XML Dom file
    @Override
    public void parseXML(IXMLValueListener listener) throws Exception {
        DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
        Document document = docBuilder.parse(new File("gaugeInput.xml"));
        NodeList nodeList = document.getElementsByTagName("gauge");
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
             if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) node;
                String type = eElement.getElementsByTagName("type").item(0).getTextContent();
                double value = Double.parseDouble(eElement.getElementsByTagName("value").item(0).getTextContent());
                listener.retriveValue(ConstantGauges.valueOf(type), value, i==nodeList.getLength()-1?true:false);
             }
        }
    }
    
}
