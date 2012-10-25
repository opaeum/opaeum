package ocltests;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.opaeum.audit.AbstractPersistentEnum;

@Table(name="sail_position")
@Entity(name="SailPositionClass")
public class SailPositionClass extends AbstractPersistentEnum {


	/** Constructor for SailPositionClass
	 * 
	 * @param e 
	 */
	public SailPositionClass(SailPosition e) {
		super(e);
	}
	
	/** Default constructor for SailPositionClass
	 */
	public SailPositionClass() {
	}


}