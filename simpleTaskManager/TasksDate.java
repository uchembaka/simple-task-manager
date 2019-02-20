package simpleTaskManager;

import java.io.Serializable;
import java.time.LocalDate;

public class TasksDate implements Serializable{
	 private int startIndex;
	 private int endIndex;
	 private LocalDate date;
	 private int index;
	 
	public TasksDate(int index, LocalDate date, int startIndex, int endIndex) {
		this.index = index;
		this.date = date;
		this.startIndex = startIndex;
		this.endIndex = endIndex;
	}
	
	public LocalDate getDate() {
		return date;
	}
	
	public int getStartIndex() {
		return startIndex;
	}
	
	public int getEndIndex() {
		return endIndex;
	}
	
	
	public void setStartIndex(int index) {
		startIndex = index;
	}
	
	public void setEndIndex(int index) {
		endIndex = index;
	}
	
	public void setIndex(int index) {
		this.index = index;
	}
	
	public int getIndex() {
		return index;
	}

}
