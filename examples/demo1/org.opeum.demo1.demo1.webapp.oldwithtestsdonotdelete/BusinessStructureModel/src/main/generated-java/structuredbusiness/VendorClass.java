package structuredbusiness;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.opaeum.hibernate.domain.AbstractPersistentEnum;

@Table(name="vendor")
@Entity(name="VendorClass")
public class VendorClass extends AbstractPersistentEnum {


	/** Constructor for VendorClass
	 * 
	 * @param e 
	 */
	public VendorClass(Vendor e) {
		super(e);
	}
	
	/** Default constructor for VendorClass
	 */
	public VendorClass() {
	}


}