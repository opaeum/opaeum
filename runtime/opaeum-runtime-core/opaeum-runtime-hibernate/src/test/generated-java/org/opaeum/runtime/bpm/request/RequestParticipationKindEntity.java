package org.opaeum.runtime.bpm.request;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.opaeum.hibernate.domain.AbstractPersistentEnum;

@Table(name="request_participation_kind",schema="bpm")
@Entity(name="RequestParticipationKindEntity")
public class RequestParticipationKindEntity extends AbstractPersistentEnum {


	/** Constructor for RequestParticipationKindEntity
	 * 
	 * @param e 
	 */
	public RequestParticipationKindEntity(RequestParticipationKind e) {
		super(e);
	}
	
	/** Default constructor for RequestParticipationKindEntity
	 */
	public RequestParticipationKindEntity() {
	}


}