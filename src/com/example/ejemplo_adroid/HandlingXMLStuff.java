package com.example.ejemplo_adroid;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class HandlingXMLStuff extends DefaultHandler{

	XMLDataCollected info = new XMLDataCollected();
	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		// TODO Auto-generated method stub
		if(localName.equals("city")){
			String city = attributes.getValue("data");
			info.setCity(city);
		}else if(localName.equals("temp_f")){
			String temp = attributes.getValue("data");
			info.setTemp(Integer.parseInt(temp));
		}
	}
	public String getInformation() {
		// TODO Auto-generated method stub
		return info.dataToString();
	}
	
}
