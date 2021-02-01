package com.keepitclean.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.stereotype.Component;

@Entity
@Table(name = "REQUEST")
@EntityListeners(AuditingEntityListener.class) // Research
@Component
public class Request implements Serializable, Comparable<Request>{

	private static final long serialVersionUID = 628025234804147842L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="Request_Id", nullable = false)
	private long requestId;
	
	@Column(name = "Query_Date", nullable = false, updatable = false)
	@CreatedDate
	@Temporal(value = TemporalType.DATE)
	private Date queryDate;
	
	@Column(name="Prefered_Date", nullable = false)
	private String preferedDate = "";
	
	@Column(name="Survey_Day", nullable = false)
	private boolean isSurveyDone = false;
	
	@Autowired
	@OneToOne
	private Job job;
	
	private static String selectedDay = "";
	
	public Request() {
		queryDate = new Date();
	}
	
	public Request( 
	String preferedDate) {
		this.preferedDate = preferedDate;
		queryDate = new Date();
	}

	public Date getQueryDate() {
		return queryDate;
	}
	
	public void setQueryDate(Date queryDate) {
		this.queryDate = queryDate;
	}
	
	@Override
	public int compareTo(Request request) {
		return preferedDate.compareTo(request.getPreferedDate());
	}

	public String getPreferedDate() {
		
		return preferedDate;
	}
	
	private String formatDate(String date) {
		
		String preferedDate = "";
		
		preferedDate += date.substring(date.lastIndexOf(" "));
		preferedDate += '-';
		preferedDate += findMonth(date.substring(4, 7));
		preferedDate += '-';
		preferedDate += date.substring(8, 10);
		
		return preferedDate;
	}
	
	private String findMonth(String month) {
		
		String monthAsDigits = "";
		switch(month) {
		case "Jan" : monthAsDigits = "01"; break;
		case "Feb" : monthAsDigits = "02"; break;
		case "Mar" : monthAsDigits = "03"; break;
		case "Apr" : monthAsDigits = "04"; break;
		case "May" : monthAsDigits = "05"; break;
		case "Jun" : monthAsDigits = "06"; break;
		case "Jul" : monthAsDigits = "07"; break;
		case "Aug" : monthAsDigits = "08"; break;
		case "Sep" : monthAsDigits = "09"; break;
		case "Oct" : monthAsDigits = "10"; break;
		case "Nov" : monthAsDigits = "11"; break;
		default : monthAsDigits = "12";
		}
		
		return monthAsDigits;
	}
	
	public void setPreferedDate(String date) {
		
		preferedDate = date;
	}
	
	public String getSelectedDay() {
		return selectedDay;
	}
	
	public boolean getIsSurveyDone() {
		return isSurveyDone;
	}

	public void setIsSurveyDone(boolean isDone) {
		this.isSurveyDone = isDone;
	}

	public void setSelectedDay(String selectedDay) {
		
		if(preferedDate.equals("") ) {
			final long DAY_MILLISECONDS = 60000*60*24;
			Date prefDate;
			if(selectedDay.equals("Today")) {
				prefDate = new Date(System.currentTimeMillis());
			}
			else if(selectedDay.equals("Tommorow")) {
				prefDate = new Date(System.currentTimeMillis()+DAY_MILLISECONDS);
			}
			else if(selectedDay.equals("Monday")) {
				switch(Calendar.DAY_OF_WEEK) {
				case 1:
					prefDate = new Date(System.currentTimeMillis()+DAY_MILLISECONDS*7);
					break;
				case 2:
					prefDate = new Date(System.currentTimeMillis()+DAY_MILLISECONDS*6);
					break;
				case 3:
					prefDate = new Date(System.currentTimeMillis()+DAY_MILLISECONDS*5);
					break;
				case 4:
					prefDate = new Date(System.currentTimeMillis()+DAY_MILLISECONDS*4);
					break;
				case 5:
					prefDate = new Date(System.currentTimeMillis()+DAY_MILLISECONDS*3);
					break;
				case 6:
					prefDate = new Date(System.currentTimeMillis()+DAY_MILLISECONDS*2);
					break;
				default:
					prefDate = new Date(System.currentTimeMillis()+DAY_MILLISECONDS*1);
				}
			}
			else if(selectedDay.equals("Tuesday")) {
				switch(Calendar.DAY_OF_WEEK) {
				case 1:
					prefDate = new Date(System.currentTimeMillis()+DAY_MILLISECONDS*1);
					break;
				case 2:
					prefDate = new Date(System.currentTimeMillis()+DAY_MILLISECONDS*7);
					break;
				case 3:
					prefDate = new Date(System.currentTimeMillis()+DAY_MILLISECONDS*6);
					break;
				case 4:
					prefDate = new Date(System.currentTimeMillis()+DAY_MILLISECONDS*5);
					break;
				case 5:
					prefDate = new Date(System.currentTimeMillis()+DAY_MILLISECONDS*4);
					break;
				case 6:
					prefDate = new Date(System.currentTimeMillis()+DAY_MILLISECONDS*3);
					break;
				default:
					prefDate = new Date(System.currentTimeMillis()+DAY_MILLISECONDS*2);
					
				}	
			}
			else if(selectedDay.equals("Wednesday")) {
				switch(Calendar.DAY_OF_WEEK) {
				case 1:
					prefDate = new Date(System.currentTimeMillis()+DAY_MILLISECONDS*2);
					break;
				case 2:
					prefDate = new Date(System.currentTimeMillis()+DAY_MILLISECONDS*1);
					break;
				case 3:
					prefDate = new Date(System.currentTimeMillis()+DAY_MILLISECONDS*7);
					break;
				case 4:
					prefDate = new Date(System.currentTimeMillis()+DAY_MILLISECONDS*6);
					break;
				case 5:
					prefDate = new Date(System.currentTimeMillis()+DAY_MILLISECONDS*5);
					break;
				case 6:
					prefDate = new Date(System.currentTimeMillis()+DAY_MILLISECONDS*4);
					break;
				default:
					prefDate = new Date(System.currentTimeMillis()+DAY_MILLISECONDS*3);
				}
			}
			else if(selectedDay.equals("Thursday")) {
				switch(Calendar.DAY_OF_WEEK) {
				case 1:
					prefDate = new Date(System.currentTimeMillis()+DAY_MILLISECONDS*3);
					break;
				case 2:
					prefDate = new Date(System.currentTimeMillis()+DAY_MILLISECONDS*2);
					break;
				case 3:
					prefDate = new Date(System.currentTimeMillis()+DAY_MILLISECONDS*1);
					break;
				case 4:
					prefDate = new Date(System.currentTimeMillis()+DAY_MILLISECONDS*7);
					break;
				case 5:
					prefDate = new Date(System.currentTimeMillis()+DAY_MILLISECONDS*6);
					break;
				case 6:
					prefDate = new Date(System.currentTimeMillis()+DAY_MILLISECONDS*5);
					break;
				default:
					prefDate = new Date(System.currentTimeMillis()+DAY_MILLISECONDS*4);
				}
			}
			else if(selectedDay.equals("Friday")) {
				switch(Calendar.DAY_OF_WEEK) {
				case 1:
					prefDate = new Date(System.currentTimeMillis()+DAY_MILLISECONDS*4);
					break;
				case 2:
					prefDate = new Date(System.currentTimeMillis()+DAY_MILLISECONDS*3);
					break;
				case 3:
					prefDate = new Date(System.currentTimeMillis()+DAY_MILLISECONDS*2);
					break;
				case 4:
					prefDate = new Date(System.currentTimeMillis()+DAY_MILLISECONDS*1);
					break;
				case 5:
					prefDate = new Date(System.currentTimeMillis()+DAY_MILLISECONDS*7);
					break;
				case 6:
					prefDate = new Date(System.currentTimeMillis()+DAY_MILLISECONDS*6);
					break;
				default:
					prefDate = new Date(System.currentTimeMillis()+DAY_MILLISECONDS*5);
				}
			}
			else if(selectedDay.equals("Surtaday")) {
				switch(Calendar.DAY_OF_WEEK) {
				case 1:
					prefDate = new Date(System.currentTimeMillis()+DAY_MILLISECONDS*5);
					break;
				case 2:
					prefDate = new Date(System.currentTimeMillis()+DAY_MILLISECONDS*4);
					break;
				case 3:
					prefDate = new Date(System.currentTimeMillis()+DAY_MILLISECONDS*3);
					break;
				case 4:
					prefDate = new Date(System.currentTimeMillis()+DAY_MILLISECONDS*2);
					break;
				case 5:
					prefDate = new Date(System.currentTimeMillis()+DAY_MILLISECONDS*1);
					break;
				case 6:
					prefDate = new Date(System.currentTimeMillis()+DAY_MILLISECONDS*7);
					break;
				default:
					prefDate = new Date(System.currentTimeMillis()+DAY_MILLISECONDS*6);
				}
			}
			else {
				switch(Calendar.DAY_OF_WEEK) {
				case 1:
					prefDate = new Date(System.currentTimeMillis()+DAY_MILLISECONDS*6);
					break;
				case 2:
					prefDate = new Date(System.currentTimeMillis()+DAY_MILLISECONDS*5);
					break;
				case 3:
					prefDate = new Date(System.currentTimeMillis()+DAY_MILLISECONDS*4);
					break;
				case 4:
					prefDate = new Date(System.currentTimeMillis()+DAY_MILLISECONDS*3);
					break;
				case 5:
					prefDate = new Date(System.currentTimeMillis()+DAY_MILLISECONDS*2);
					break;
				case 6:
					prefDate = new Date(System.currentTimeMillis()+DAY_MILLISECONDS*1);
					break;
				default:
					prefDate = new Date(System.currentTimeMillis()+DAY_MILLISECONDS*7);
				}
			}
			
			preferedDate = formatDate(prefDate.toString());
		}
		
	}

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}
	
	public long getRequestId() { return requestId;}

}
