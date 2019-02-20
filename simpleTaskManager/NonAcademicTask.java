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
public class NonAcademicTask extends SimTask{

	private String category;
	
	public NonAcademicTask(String taskID, String name, String state, LocalTime startTime, boolean optional, String note) {
		super(taskID, name, state, startTime, optional, note);
	}

}
