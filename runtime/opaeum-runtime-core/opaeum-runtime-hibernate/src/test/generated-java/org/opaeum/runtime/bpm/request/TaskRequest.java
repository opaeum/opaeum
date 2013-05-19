package org.opaeum.runtime.bpm.request;

import javax.persistence.DiscriminatorValue;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.AccessType;
import org.hibernate.annotations.Filter;
import org.opaeum.annotation.NumlMetaInfo;
import org.opaeum.runtime.bpm.requestobject.IRequestObject;

@NumlMetaInfo(applicationIdentifier="opaeum_hibernate_tests",uuid="252060@_zFmsEIoVEeCLqpffVZYAlw")
@Filter(name="noDeletedObjects")
@org.hibernate.annotations.Entity(dynamicUpdate=true)
@AccessType(	"field")
@MappedSuperclass
@DiscriminatorValue(	"task_request")
public class TaskRequest extends TaskRequestGenerated {
	/** This constructor is intended for easy initialization in unit tests
	 * 
	 * @param owningObject 
	 */
	public TaskRequest(IRequestObject owningObject) {
	}
	
	/** Default constructor for TaskRequest
	 */
	public TaskRequest() {
	}

}