package structuredbusiness;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Index;
import org.hibernate.annotations.Type;
import org.opaeum.audit.AbstractPersistentEnum;

@Table(name="city")
@Entity(name="CityEntity")
public class CityEntity extends AbstractPersistentEnum {
	@Type(type="structuredbusiness.ProvinceResolver")
	@Index(columnNames="province",name="idx_city_province")
	@Column(name="province",nullable=true)
	private Province province;

	/** Constructor for CityEntity
	 * 
	 * @param e 
	 */
	public CityEntity(City e) {
		super(e);
		this.province=e.getProvince();
	}
	
	/** Default constructor for CityEntity
	 */
	public CityEntity() {
	}


}