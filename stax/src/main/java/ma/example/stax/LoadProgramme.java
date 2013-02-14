package ma.example.stax;

import java.util.ArrayList;
import java.util.Map;
import java.util.Stack;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import ma.example.stax.entity.channel.Channel;
import ma.exemple.stax.entity.programme.Category;
import ma.exemple.stax.entity.programme.DateFinished;
import ma.exemple.stax.entity.programme.Desc;
import ma.exemple.stax.entity.programme.Programme;
import ma.exemple.stax.entity.programme.Title;

public class LoadProgramme extends Load {

	private ArrayList<Programme> programmeList;
	private Programme programme;
	private Stack<String> flagList;
	public LoadProgramme(String fileLocation) {
		super(fileLocation);
	}
	
	public ArrayList<Programme> getProgrammeList() {
		return programmeList;
	}

	public void setProgrammeList(ArrayList<Programme> programmeList) {
		this.programmeList = programmeList;
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
				if (elementName.equals("programme")) {
					programme = new Programme();
					//TODO programme.setChannel(new LoadChannel().getChannelByIdEpg(Integer.valueOf(atrributeList.get("id"))));
					Channel channel = new Channel();
					channel.setIdEpg(Integer.valueOf(atrributeList.get("channel")));
					programme.setChannel(channel);
					programme.setIdEpg(Long.valueOf(atrributeList.get("id")));
					programme.setRegularId(Integer.valueOf(atrributeList.get("regular_id")));
					programme.addSchedule(atrributeList.get("start"),atrributeList.get("stop"));
				}
				else if (elementName.equals("title")) {
					Title title = new Title();
					title.setLang(atrributeList.get("lang"));
					programme.getTitleList().add(title);
				}
				else if (elementName.equals("desc")) {
					Desc desc = new Desc();
					desc.setLang(atrributeList.get("lang"));
					programme.getDescList().add(desc);
				}
				else if (elementName.equals("category") && flagList.contains("programme")) {
					Category category = new Category();
					category.setLang(atrributeList.get("lang"));
					programme.getCategoryList().add(category);
				}
				else if (elementName.equals("date")) {
					DateFinished dateFinished = new DateFinished();
					dateFinished.setLang(atrributeList.get("lang"));
					programme.setDateFinished(dateFinished);
				}
				break;
			case XMLStreamReader.CHARACTERS:
				if (!(xmlStreamReader.isWhiteSpace()) && flagList != null && !flagList.isEmpty()) {
					if (flagList.lastElement().equals("title")){
						ArrayList<Title> titleList = programme.getTitleList();
						Title last = titleList.get(titleList.size()-1);
						last.setTitle(xmlStreamReader.getText());
					}
					else if (flagList.lastElement().equals("desc")){
						ArrayList<Desc> descList = programme.getDescList();
						Desc last = descList.get(descList.size()-1);
						last.setDesc(xmlStreamReader.getText());
					}
					else if (flagList.lastElement().equals("category") && flagList.contains("programme")){
						ArrayList<Category> categoryList = programme.getCategoryList();
						Category last = categoryList.get(categoryList.size()-1);
						last.setCategory(xmlStreamReader.getText());
					}
					else if (flagList.lastElement().equals("date"))
						programme.getDateFinished().setDate(xmlStreamReader.getText());
				}
				break;
			case XMLStreamReader.END_ELEMENT:
				if (xmlStreamReader.getLocalName().equals("programme")) {
					if (programmeList == null)
						programmeList = new ArrayList<Programme>();
					programmeList.add(programme);
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

}
