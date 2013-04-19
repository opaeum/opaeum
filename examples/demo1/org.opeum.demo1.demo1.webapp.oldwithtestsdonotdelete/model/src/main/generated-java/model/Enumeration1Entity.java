package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.opaeum.hibernate.domain.AbstractPersistentEnum;

@Table(name="enumeration1")
@Entity(name="Enumeration1Entity")
public class Enumeration1Entity extends AbstractPersistentEnum {
	@Column(name="property1")
	private Integer property1;

	/** Constructor for Enumeration1Entity
	 * 
	 * @param e 
	 */
	public Enumeration1Entity(Enumeration1 e) {
		super(e);
		this.property1=e.getProperty1();
	}
	
	/** Default constructor for Enumeration1Entity
	 */
	public Enumeration1Entity() {
	}


}