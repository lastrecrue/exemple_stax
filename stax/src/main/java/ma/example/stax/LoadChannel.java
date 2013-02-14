package ma.example.stax;

import java.util.ArrayList;
import java.util.Map;
import java.util.Stack;

import java.util.Iterator;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import ma.example.stax.entity.channel.Category;
import ma.example.stax.entity.channel.Channel;
import ma.example.stax.entity.channel.Country;
import ma.example.stax.entity.channel.DisplayName;
import ma.example.stax.entity.channel.Icon;
import ma.example.stax.entity.channel.Logo;
import ma.example.stax.entity.channel.Region;
import ma.example.stax.entity.channel.XmlFeed;

public class LoadChannel extends Load {
	public LoadChannel(String fileLocation) {
		super(fileLocation);
	}

	private ArrayList<Channel> channelList;
	private Stack<String> flagList;
	private Channel channel;

	public ArrayList<Channel> getChannelList() {

		return channelList;
	}

	public void setChannelList(ArrayList<Channel> channelList) {
		this.channelList = channelList;
	}

	@Override
	public void get(XMLStreamReader xmlStreamReader) {
		try {
			int eventCode = xmlStreamReader.next();
			switch (eventCode) {
			case XMLStreamReader.START_ELEMENT:
				String elementName = xmlStreamReader.getLocalName();
				if(flagList==null)
					flagList = new Stack<String>();
				flagList.push(elementName);
				Map<String,String> atrributeList = getAttributes(xmlStreamReader);
				if (elementName.equals("channel")) {
					channel = new Channel();
					channel.setIdEpg(Integer.valueOf(atrributeList.get("id")));
					channel.setOrder(atrributeList.get("order"));
					channel.setSlug(atrributeList.get("slug"));
					channel.setLicense(atrributeList.get("License"));
				} else if (elementName.equals("region")) {
					channel.setRegion(new Region());
					channel.getRegion().setLang(atrributeList.get("lang"));
				}
				else if (elementName.equals("country")) {
					channel.setCountry(new Country());
					channel.getCountry().setLang(atrributeList.get("lang"));
				}
				else if (elementName.equals("category")) {
					channel.setCategory(new Category());
					channel.getCategory().setLang(atrributeList.get("lang"));
				}
				else if (elementName.equals("display-name")) {
					DisplayName displayName = new DisplayName();
					displayName.setLang(atrributeList.get("lang"));
					channel.getDisplayNameList().add(displayName);
				}
				else if (elementName.equals("logo")) {
					channel.setLogo(new Logo());
					channel.getLogo().setSrc(atrributeList.get("src"));
				}
				else if (elementName.equals("icon")) {
					channel.setIcon(new Icon());
					channel.getIcon().setSrc(atrributeList.get("src"));
				}
				else if (elementName.equals("xml-feed")) {
					channel.setXmlFeed(new XmlFeed());
					channel.getXmlFeed().setSrc(atrributeList.get("src"));
				}
				break;
				
			case XMLStreamReader.CHARACTERS:
				if (!(xmlStreamReader.isWhiteSpace()) && flagList != null && !flagList.isEmpty()) {
					if (flagList.lastElement().equals("display-name")){
						ArrayList<DisplayName> displayNameList = channel.getDisplayNameList();
						DisplayName last = displayNameList.get(displayNameList.size()-1);
						last.setName(xmlStreamReader.getText());
					}
					else if (flagList.lastElement().equals("region"))
						channel.getRegion().setLang(xmlStreamReader.getText());
					else if (flagList.lastElement().equals("country"))
						channel.getCountry().setLang(xmlStreamReader.getText());
					else if (flagList.lastElement().equals("category"))
						channel.getCategory().setLang(xmlStreamReader.getText());
					else if (flagList.lastElement().equals("logo"))
						channel.getLogo().setSrc(xmlStreamReader.getText());
					else if (flagList.lastElement().equals("icon"))
						channel.getIcon().setSrc(xmlStreamReader.getText());
					else if (flagList.lastElement().equals("xml-feed"))
						channel.getXmlFeed().setSrc(xmlStreamReader.getText());
				}
				break;
				
			case XMLStreamReader.END_ELEMENT:
				if (xmlStreamReader.getLocalName().equals("channel")) {
					if (channelList == null)
						channelList = new ArrayList<Channel>();
					channelList.add(channel);
				}
				if (!flagList.isEmpty())
					flagList.pop();
				break;

			
			}
		} catch (XMLStreamException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public Channel getChannelByIdEpg(Integer idEpg){
		Iterator<Channel> it = channelList.iterator();
		while(it.hasNext()){
			Channel channel = it.next();
			if(channel.getIdEpg().equals(idEpg))
				return channel;
		}
		return null;
	}
}
