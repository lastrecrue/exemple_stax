package ma.example.stax;

import java.util.ArrayList;


public class Channel {
	private int id;
	private String idEpg;
	public String getIdEpg() {
		return idEpg;
	}
	public void setIdEpg(String idEpg) {
		this.idEpg = idEpg;
	}
	//private String name;
	private ArrayList<DisplayName> displayNameList= new ArrayList<DisplayName>();
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public ArrayList<DisplayName> getDisplayNameList() {
		return displayNameList;
	}
	public void setDisplayNameList(ArrayList<DisplayName> displayNameList) {
		this.displayNameList = displayNameList;
	}
	
}
