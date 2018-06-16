/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Helpers;

import java.io.FileInputStream;
import java.io.InputStream;
import javax.xml.stream.*;


/**
 * @author Kacper
 * @co-author Samuel
 */
public class XMLParserStAx implements IXMLParser{
    //Running XML StAX file
    @Override
    public void parseXML(IXMLValueListener listener) throws Exception{
        XMLInputFactory inputFactory = XMLInputFactory.newInstance();
        InputStream in = new FileInputStream("gaugeInput.xml");
        XMLStreamReader streamReader = inputFactory.createXMLStreamReader(in);
        String type="", value="";
        while (streamReader.hasNext()) {
            if (streamReader.isStartElement()) {
                switch (streamReader.getLocalName()) {
                    case "type": {
                        type = streamReader.getElementText();
                        break;
                    }
                    case "value": {
                        value = streamReader.getElementText();
                        break;
                    }
                }
            }
            else if(streamReader.isEndElement() && streamReader.getLocalName().equals("gauge")){
                listener.retriveValue(ConstantGauges.valueOf(type), Double.parseDouble(value), false);
            }
            else if(streamReader.isEndElement() && streamReader.getLocalName().equals("gauge_input")){
                listener.retriveValue(ConstantGauges.valueOf(type), Double.parseDouble(value), true);
            }
            streamReader.next();

        }
    }
    
}
