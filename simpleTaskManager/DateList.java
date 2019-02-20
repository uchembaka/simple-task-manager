package simpleTaskManager;

import java.io.Serializable;
import java.util.ArrayList;

public class DateList implements Serializable{

	public static DateList  table = null;
	private ArrayList<TasksDate> dateArr;
	
	private DateList() {
		dateArr = new ArrayList<TasksDate>();
	}
	
	public static DateList getDateList() {
		if(table == null) {
			return table = new DateList();
		}
		return table;
	}//getTaskList()
	
	public void addItem(TasksDate task) {
		dateArr.add(task);
	}//addTask()
	
	public void deleteItem(int index) {
		dateArr.remove(index);
	}//deleteTask()
	
	public int getSize() {//retruns the number of tasks
		return dateArr.size();
	}//getSize()
	
	
	public TasksDate getItem(int index) {
		return dateArr.get(index);
	}

}
