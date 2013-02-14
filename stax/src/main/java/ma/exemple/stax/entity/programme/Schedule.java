package ma.exemple.stax.entity.programme;

import java.util.Date;

public class Schedule {
	private Date startDate;
	private Date stopDate;
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getStopDate() {
		return stopDate;
	}
	public void setStopDate(Date stopDate) {
		this.stopDate = stopDate;
	}
}
