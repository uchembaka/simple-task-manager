//imports
package simpleTaskManager;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;

/**
 * @author Uche Mbaka
 *
 */
public class SimTask implements Serializable{
	private String taskID;
	private String name;
	private String state;
	private LocalTime startTime;
	private LocalTime endTime;
	private boolean optional;
	private String note;
	private LocalDate dateCreated = LocalDate.now();
	private boolean deleted = false;
	
	public SimTask(String taskID, String name, String state, LocalTime startTime, boolean optional, String note) {
		this.taskID = taskID;
		this.name = name;
		this.state = state;
		this.startTime = startTime;
		this.optional = optional;
		this.note = note;
	}//constructor()
	
	public String getID() {
		return taskID;
	}//getID()
	
	public String getName() {
		return name;
	}//getName()
	
	public boolean getOptional() {
		return optional;
	}//getOptional
	
	public String getNote() {
		return note;
	}//getNote()
	
	public String getState() {
		return state;
	}//getState()
	
	public String getStartTime() {
		String start = "";
		if(startTime == null) {
			return start;
		}else {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh : mm : ss");
			start = startTime.format(formatter);
			return start;
		}
	}//getStartTime()
	
	public String getEndTime() {
		String end = "";
		if(endTime == null) {
			return end;
		}else {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh : mm : ss");
			end = endTime.format(formatter);
			return end;
		}
	}//getEndTime()
	
	public LocalDate getDateCreated() {
		return dateCreated;
	}
	
	public boolean isDeleted() {
		return deleted;
	}
	
	public void setState(String state) {
		this.state = state;
	}//setState()
	
	public void setStartTime(LocalTime start) {
		startTime = start;
	}//setStartTime()
	
	public void setEndTime(LocalTime end) {
		endTime = end;
	}//setEndTime()
	
	public void setName(String name) {
		this.name = name;
	}//setName()
	
	public void setOptional(boolean value) {
		optional = value;
	}//setOptional()
	
	public void setNote(String note) {
		this.note = note;
	}//setNote()
	
	public void setDeleted() {
		deleted = true;
	}
	
	public static String genID(int size) {
		LocalDate localDate = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy");
		String formattedString = localDate.format(formatter).replace(" ", "");
		return "ST"+formattedString+"-"+(size+1);
	}

}
