/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ibuildapp.module.replacement;

import android.util.Log;
import android.util.Xml;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author mike
 */
public class EntityParser {
    
    public EntityParser(String xmlData){
        this.xmlData = xmlData;
    }
    
    private String xmlData = "";
    private String htmlData = "";
    
    public String parse(){
        try{
            Xml.parse(xmlData, new SaxHandler());
            Log.d("", "");
        }catch(SAXException sAXEx){
            Log.d("", "");
        }catch(Exception ex){
            Log.d("", "");
        }
        
        return getHtmlData();
    }

    /**
     * @param xmlData the xmlData to set
     */
    public void setXmlData(String xmlData) {
        this.xmlData = xmlData;
    }

    /**
     * @return the htmlData
     */
    public String getHtmlData() {
        return htmlData;
    }
    
    private class SaxHandler extends DefaultHandler{
        
        private StringBuilder sb = new StringBuilder();

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            super.startElement(uri, localName, qName, attributes);
            
            if(localName.equalsIgnoreCase("data")){
                sb.setLength(0);
            }else{
                sb.append("<").append(localName).append(" ");
                if(attributes.getLength() > 0){
                    for(int i = 0; i < attributes.getLength(); i++){
                        sb.append(attributes.getLocalName(i)).append("=\"").
                                append(attributes.getValue(i)).
                                append("\"").append(" ");
                    }
                }
                sb.append(">").append("\n");
            }
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            super.characters(ch, start, length);
            
            sb.append(new String(ch, start, length));
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            super.endElement(uri, localName, qName);
            
            if(!localName.equalsIgnoreCase("data")){
                sb.append("</").append(localName).append(">").append("\n");
            }
        }

        @Override
        public void endDocument() throws SAXException {
            super.endDocument();
            
            htmlData = sb.toString();
        }
        
    }
    
}
