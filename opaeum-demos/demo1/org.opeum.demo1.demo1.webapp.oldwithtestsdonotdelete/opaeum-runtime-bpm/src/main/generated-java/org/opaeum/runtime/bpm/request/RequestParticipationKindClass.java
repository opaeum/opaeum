package org.opaeum.runtime.bpm.request;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.opaeum.audit.AbstractPersistentEnum;

@Table(name="request_participation_kind")
@Entity(name="RequestParticipationKindClass")
public class RequestParticipationKindClass extends AbstractPersistentEnum {


	/** Constructor for RequestParticipationKindClass
	 * 
	 * @param e 
	 */
	public RequestParticipationKindClass(RequestParticipationKind e) {
		super(e);
	}
	
	/** Default constructor for RequestParticipationKindClass
	 */
	public RequestParticipationKindClass() {
	}


}