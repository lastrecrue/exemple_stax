package ma.example.stax;



import javax.xml.stream.Location;
import javax.xml.stream.XMLReporter;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;

import java.util.List;

public class XML2SQL {

    public static final String RESOURCES_FOLDER = "src/main/resources";
    private static List<Channel> channelList = new ArrayList<Channel>();
    private static Stack<String> flagList = new Stack<String>();
    private static Channel channel;
    private static DisplayName displayName;

    @SuppressWarnings("restriction")
	public void print(String fileLocation) {
        try {
            FileInputStream fileInputStream = new FileInputStream(fileLocation);
            XMLInputFactory factory = XMLInputFactory.newInstance();
            factory.setXMLReporter(
    				new XMLReporter() {
    	            public void report(String message, String typeErreur, Object source, Location location) throws XMLStreamException {
    					System.out.println("Erreur de type : " + typeErreur + ", message : " + message);
    	            }
    	       });
            XMLStreamReader xmlStreamReader = factory.createXMLStreamReader(fileInputStream);

            while (xmlStreamReader.hasNext()) {
                printEventInfo(xmlStreamReader);
            }
            xmlStreamReader.close();
        } catch (XMLStreamException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (FileNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    @SuppressWarnings("restriction")
	private static void printEventInfo(XMLStreamReader reader) throws XMLStreamException {
    	
        int eventCode = reader.next();
        switch (eventCode) {
            case XMLStreamReader.START_ELEMENT :
                if(reader.getLocalName().equals("channel")){
                	flagList.push("channel");
                	channel=new Channel();
                	channel.setIdEpg(reader.getAttributeValue(0));
                }
                else if(reader.getLocalName().equals("display-name")){
                	flagList.push("display-name");
                	displayName=new DisplayName();
                	displayName.setLang(reader.getAttributeValue(0));
                }
                break;
            case XMLStreamReader.END_ELEMENT :
                if(reader.getLocalName().equals("display-name"))
                	channel.getDisplayNameList().add(displayName);
                if(reader.getLocalName().equals("channel"))
                	channelList.add(channel);
                if(!flagList.isEmpty())
                	flagList.pop();
                break;
           
            case XMLStreamReader.CHARACTERS :
            	if(!(reader.isWhiteSpace())){
                	if(flagList!=null && !flagList.isEmpty() && flagList.lastElement().equals("display-name"))
                		displayName.setName(reader.getText());
            	}
                break;
        }
    }

    public static void main(String[] args) {
        XML2SQL eventsPrinter = new XML2SQL();
        eventsPrinter.print(RESOURCES_FOLDER + "/epg.xml");
        Iterator<Channel> it = channelList.iterator();
        while(it.hasNext()){
        	Channel ch = it.next();
        	System.out.println(ch.getIdEpg());
        	Iterator<DisplayName> it2 = ch.getDisplayNameList().iterator();
        	while(it2.hasNext()){
        		DisplayName dn = it2.next();
        		System.out.println("*"+dn.getName()+" : "+dn.getLang());
        	}
        }
    }
}
