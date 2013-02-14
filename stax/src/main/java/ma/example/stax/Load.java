package ma.example.stax;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.xml.stream.Location;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLReporter;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import ma.example.stax.entity.channel.Channel;

public abstract class Load {
	private static final String RESOURCES_FOLDER = "/";
	private XMLStreamReader xmlStreamReader;

	public Load(String fileLocation) {
		try {
            FileInputStream fileInputStream = new FileInputStream(fileLocation);
            XMLInputFactory factory = XMLInputFactory.newInstance();
            factory.setXMLReporter(
    				new XMLReporter() {
    	            public void report(String message, String typeErreur, Object source, Location location) throws XMLStreamException {
    					System.out.println("Erreur de type : " + typeErreur + ", message : " + message);
    	            }
    	       });
            xmlStreamReader = factory.createXMLStreamReader(fileInputStream);

            while (xmlStreamReader.hasNext()) {
                get(xmlStreamReader);
            }
            xmlStreamReader.close();
        } catch (XMLStreamException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (FileNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
	}
	public Map<String,String> getAttributes(XMLStreamReader xmlStreamReader){
		Map<String,String> attributeList = new HashMap<String,String>();		
		for(int i=0; i<xmlStreamReader.getAttributeCount(); i++){
			attributeList.put(xmlStreamReader.getAttributeLocalName(i), xmlStreamReader.getAttributeValue(i));
		}
		return attributeList;	
	}
	public abstract void get(XMLStreamReader xmlStreamReader); 
	
}
