package structuredbusiness;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.opaeum.audit.AbstractPersistentEnum;

@Table(name="appliance_type")
@Entity(name="ApplianceTypeEntity")
public class ApplianceTypeEntity extends AbstractPersistentEnum {


	/** Constructor for ApplianceTypeEntity
	 * 
	 * @param e 
	 */
	public ApplianceTypeEntity(ApplianceType e) {
		super(e);
	}
	
	/** Default constructor for ApplianceTypeEntity
	 */
	public ApplianceTypeEntity() {
	}


}