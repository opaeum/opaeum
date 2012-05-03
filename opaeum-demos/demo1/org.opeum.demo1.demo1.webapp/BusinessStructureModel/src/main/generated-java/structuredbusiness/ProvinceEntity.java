package structuredbusiness;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.opaeum.audit.AbstractPersistentEnum;

@Table(name="province")
@Entity(name="ProvinceEntity")
public class ProvinceEntity extends AbstractPersistentEnum {


	/** Constructor for ProvinceEntity
	 * 
	 * @param e 
	 */
	public ProvinceEntity(Province e) {
		super(e);
	}
	
	/** Default constructor for ProvinceEntity
	 */
	public ProvinceEntity() {
	}


}