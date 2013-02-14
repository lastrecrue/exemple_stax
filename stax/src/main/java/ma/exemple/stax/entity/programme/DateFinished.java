package ma.exemple.stax.entity.programme;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateFinished {
	private Date date;
	private String lang;
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getLang() {
		return lang;
	}
	public void setLang(String lang) {
		this.lang = lang;
	}
	public void setDate(String date){//xml : <date lang="en">20130211</date>
		int year = Integer.valueOf(date.substring(0, 4));
		int month = Integer.valueOf(date.substring(4, 6));
		int day = Integer.valueOf(date.substring(6, 8));
		Calendar calendar = new GregorianCalendar(year, month - 1, day);
		this.date=calendar.getTime();
	}
}
