package structuredbusiness;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.opaeum.audit.AbstractPersistentEnum;

@Table(name="province")
@Entity(name="ProvinceClass")
public class ProvinceClass extends AbstractPersistentEnum {


	/** Constructor for ProvinceClass
	 * 
	 * @param e 
	 */
	public ProvinceClass(Province e) {
		super(e);
	}
	
	/** Default constructor for ProvinceClass
	 */
	public ProvinceClass() {
	}


}