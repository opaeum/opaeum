package structuredbusiness;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.opaeum.hibernate.domain.AbstractPersistentEnum;

@Table(name="city")
@Entity(name="CityClass")
public class CityClass extends AbstractPersistentEnum {


	/** Constructor for CityClass
	 * 
	 * @param e 
	 */
	public CityClass(City e) {
		super(e);
	}
	
	/** Default constructor for CityClass
	 */
	public CityClass() {
	}


}