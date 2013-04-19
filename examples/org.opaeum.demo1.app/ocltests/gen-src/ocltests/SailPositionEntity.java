package ocltests;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.opaeum.hibernate.domain.AbstractPersistentOpaeumIdEnum;

@Table(name="sail_position")
@Entity(name="SailPositionEntity")
public class SailPositionEntity extends AbstractPersistentOpaeumIdEnum {


	/** Constructor for SailPositionEntity
	 * 
	 * @param e 
	 */
	public SailPositionEntity(SailPosition e) {
		super(e);
	}
	
	/** Default constructor for SailPositionEntity
	 */
	public SailPositionEntity() {
	}


}