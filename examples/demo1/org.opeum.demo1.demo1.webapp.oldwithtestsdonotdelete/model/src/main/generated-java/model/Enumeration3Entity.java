package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.opaeum.audit.AbstractPersistentEnum;

@Table(name="enumeration3")
@Entity(name="Enumeration3Entity")
public class Enumeration3Entity extends AbstractPersistentEnum {
	@Column(name="attribute1")
	private String attribute1;

	/** Constructor for Enumeration3Entity
	 * 
	 * @param e 
	 */
	public Enumeration3Entity(Enumeration3 e) {
		super(e);
		this.attribute1=e.getAttribute1();
	}
	
	/** Default constructor for Enumeration3Entity
	 */
	public Enumeration3Entity() {
	}


}