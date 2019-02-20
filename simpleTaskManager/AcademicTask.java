/**
 * 
 */
package simpleTaskManager;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * @author Uche Mbaka
 *
 */
public class AcademicTask extends SimTask{
	private String type;
	
	public AcademicTask(String taskID, String name, String state, LocalTime startTime, String type, boolean optional, String note) {
		super(taskID, name, state, startTime, optional, note);
		this.type =  type;
	}

}
