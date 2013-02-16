package org.opaeum.runtime.bpm.opaeumsimpletypes;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.opaeum.hibernate.domain.AbstractPersistentEnum;

@Table(name="month")
@Entity(name="MonthEntity")
public class MonthEntity extends AbstractPersistentEnum {

	private static final long serialVersionUID = 207855465984037855L;

	/** Constructor for MonthEntity
	 * 
	 * @param e 
	 */
	public MonthEntity(Month e) {
		super(e);
	}
	
	/** Default constructor for MonthEntity
	 */
	public MonthEntity() {
	}


}