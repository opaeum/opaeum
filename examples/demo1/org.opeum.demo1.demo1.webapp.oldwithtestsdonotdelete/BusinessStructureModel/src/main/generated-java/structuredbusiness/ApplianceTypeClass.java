package structuredbusiness;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.opaeum.audit.AbstractPersistentEnum;

@Table(name="appliance_type")
@Entity(name="ApplianceTypeClass")
public class ApplianceTypeClass extends AbstractPersistentEnum {


	/** Constructor for ApplianceTypeClass
	 * 
	 * @param e 
	 */
	public ApplianceTypeClass(ApplianceType e) {
		super(e);
	}
	
	/** Default constructor for ApplianceTypeClass
	 */
	public ApplianceTypeClass() {
	}


}