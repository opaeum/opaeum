package org.opaeum.runtime.bpm.request;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.Table;

import org.opaeum.annotation.NumlMetaInfo;

@NumlMetaInfo(uuid="252060@_zFmsEIoVEeCLqpffVZYAlw")
@Table(name="task_request_token")
@Inheritance(strategy=javax.persistence.InheritanceType.SINGLE_TABLE)
@Entity(name="TaskRequestToken")
@DiscriminatorValue(	"252060@_zFmsEIoVEeCLqpffVZYAlw")
@DiscriminatorColumn(discriminatorType=javax.persistence.DiscriminatorType.STRING)
public class TaskRequestToken<SME extends TaskRequest> extends AbstractRequestToken<SME> {


	/** Constructor for TaskRequestToken
	 * 
	 * @param parentToken 
	 */
	public TaskRequestToken(TaskRequestToken parentToken) {
	super(parentToken);
	}
	
	/** Constructor for TaskRequestToken
	 */
	public TaskRequestToken() {
	}


}