package org.opaeum.runtime.bpm.request;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Inheritance;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.AccessType;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.Proxy;
import org.opaeum.annotation.NumlMetaInfo;
import org.opaeum.runtime.bpm.requestobject.IRequestObject;

@NumlMetaInfo(applicationIdentifier="opaeum_hibernate_tests",uuid="252060@_6MA8UI2-EeCrtavWRHwoHg")
@Proxy(lazy=false)
@Filter(name="noDeletedObjects")
@org.hibernate.annotations.Entity(dynamicUpdate=true)
@AccessType(	"field")
@MappedSuperclass
@Inheritance(strategy=javax.persistence.InheritanceType.JOINED)
@DiscriminatorColumn(discriminatorType=javax.persistence.DiscriminatorType.STRING,name="type_descriminator")
abstract public class AbstractRequest extends AbstractRequestGenerated {
	/** This constructor is intended for easy initialization in unit tests
	 * 
	 * @param owningObject 
	 */
	public AbstractRequest(IRequestObject owningObject) {
	}
	
	/** Default constructor for AbstractRequest
	 */
	public AbstractRequest() {
	}

}