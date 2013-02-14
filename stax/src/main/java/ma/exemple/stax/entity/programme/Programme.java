package ma.exemple.stax.entity.programme;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import ma.example.stax.entity.channel.Channel;

public class Programme {
	private int id;
	private Integer idEpg;
	private Integer regularId;
	private Channel channel;
	private ArrayList<Schedule> scheduleList = new ArrayList<Schedule>();
	private ArrayList<Title> titleList = new ArrayList<Title>();
	private ArrayList<Desc> descList = new ArrayList<Desc>();
	private ArrayList<Category> categoryList = new ArrayList<Category>();
	private DateFinished dateFinished;
	private Icon icon;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Integer getIdEpg() {
		return idEpg;
	}
	public void setIdEpg(Integer idEpg) {
		this.idEpg = idEpg;
	}
	public Integer getRegularId() {
		return regularId;
	}
	public void setRegularId(Integer regularId) {
		this.regularId = regularId;
	}
	
	public Channel getChannel() {
		return channel;
	}
	public void setChannel(Channel channel) {
		this.channel = channel;
	}
	
	public DateFinished getDateFinished() {
		return dateFinished;
	}
	public void setDateFinished(DateFinished dateFinished) {
		this.dateFinished = dateFinished;
	}
	public ArrayList<Schedule> getScheduleList() {
		return scheduleList;
	}
	public void setScheduleList(ArrayList<Schedule> scheduleList) {
		this.scheduleList = scheduleList;
	}
	public ArrayList<Title> getTitleList() {
		return titleList;
	}
	public void setTitleList(ArrayList<Title> titleList) {
		this.titleList = titleList;
	}
	public ArrayList<Desc> getDescList() {
		return descList;
	}
	public void setDescList(ArrayList<Desc> descList) {
		this.descList = descList;
	}
	public ArrayList<Category> getCategoryList() {
		return categoryList;
	}
	public void setCategoryList(ArrayList<Category> categoryList) {
		this.categoryList = categoryList;
	}
	
	public Icon getIcon() {
		return icon;
	}
	public void setIcon(Icon icon) {
		this.icon = icon;
	}
	
	public void addSchedule(String start, String stop){//xml : start="20130211103000 +0000" stop="20130211104500 +0000"
		int startYear = Integer.valueOf(start.substring(0, 4));
		int startMonth = Integer.valueOf(start.substring(4, 6));
		int startDay = Integer.valueOf(start.substring(6, 8));
		int startHour = Integer.valueOf(start.substring(8, 10));
		int startMinute = Integer.valueOf(start.substring(10, 12));
		int startSecond = Integer.valueOf(start.substring(12, 14));
		int stopYear = Integer.valueOf(start.substring(0, 4));
		int stopMonth = Integer.valueOf(start.substring(4, 6));
		int stopDay = Integer.valueOf(start.substring(6, 8));
		int stopHour = Integer.valueOf(start.substring(8, 10));
		int stopMinute = Integer.valueOf(start.substring(10, 12));
		int stopSecond = Integer.valueOf(start.substring(12, 14));
		Schedule schedule = new Schedule();
		Calendar calendar = new GregorianCalendar(startYear, startMonth - 1, startDay, startHour, startMinute, startSecond);
		schedule.setStartDate(calendar.getTime());
		calendar = new GregorianCalendar(stopYear, stopMonth - 1, stopDay, stopHour, stopMinute, stopSecond);
		schedule.setStopDate(calendar.getTime());
		scheduleList.add(schedule);
	}
	
}
