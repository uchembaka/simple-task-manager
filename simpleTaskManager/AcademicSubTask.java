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
public class AcademicSubTask extends AcademicTask{
	private int subNum; 
	private double weight;
	private String reference;
	
	public AcademicSubTask(String taskID, String name, String state, LocalTime startTime, String type, boolean optional, double weight, String reference, String note) {
		super(taskID, name, state, startTime, type, optional, note);
		this.weight = weight;
		this.reference = reference;
	}

}
