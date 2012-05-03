package structuredbusiness;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.opaeum.audit.AbstractPersistentEnum;

@Table(name="vendor")
@Entity(name="VendorEntity")
public class VendorEntity extends AbstractPersistentEnum {


	/** Constructor for VendorEntity
	 * 
	 * @param e 
	 */
	public VendorEntity(Vendor e) {
		super(e);
	}
	
	/** Default constructor for VendorEntity
	 */
	public VendorEntity() {
	}


}