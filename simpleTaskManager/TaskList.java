/**
 * 
 */
package simpleTaskManager;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author Uche Mbaka
 *
 */
public class TaskList implements Serializable{
	public static TaskList  table = null;
	private ArrayList<SimTask> taskArr;
	
	private TaskList() {
		taskArr = new ArrayList<SimTask>();
	}
	
	public static TaskList getTaskList() {
		if(table == null) {
			return table = new TaskList();
		}
		return table;
	}//getTaskList()
	
	public void addItem(SimTask task) {
		taskArr.add(task);
	}//addTask()
	
	public void deleteItem(int index) {
		taskArr.get(index).setDeleted();
//		taskArr.remove(index);
	}//deleteTask()
	
	public int getSize() {//returns the number of tasks
		return taskArr.size();
	}//getSize()
	
	
	public SimTask getItem(int index) {
		return taskArr.get(index);
	}


}
