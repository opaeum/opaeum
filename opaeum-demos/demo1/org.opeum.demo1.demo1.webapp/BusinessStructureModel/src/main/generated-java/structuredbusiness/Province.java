package structuredbusiness;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.opaeum.annotation.NumlMetaInfo;
import org.opaeum.annotation.PropertyMetaInfo;
import org.opaeum.runtime.domain.IEnum;

@NumlMetaInfo(uuid="914890@_CkljUJJWEeGFkOm2e1MJNQ")public enum Province implements IEnum, Serializable {
	GAUTENG(new HashSet<City>(java.util.Arrays.asList(City.JOHANNESBURG)),"914890@__I3NEJKGEeGFkOm2e1MJNQ",360849098396972512l),
	WESTERNCAPE(new HashSet<City>(java.util.Arrays.asList(City.CAPETOWN)),"914890@_AeDeYJKHEeGFkOm2e1MJNQ",4498978506497688454l),
	KWAZULUNATAL(new HashSet<City>(java.util.Arrays.asList(City.DURBAN)),"914890@_BT_A0JKHEeGFkOm2e1MJNQ",1197396414679960874l),
	MPUMALANGA(new HashSet<City>(),"914890@_CuGOYJKHEeGFkOm2e1MJNQ",3058547728091853860l);
	private Set<City> city = new HashSet<City>();
	private long opaeumId;
	private String uuid;
	/** Constructor for Province
	 * 
	 * @param city 
	 * @param uuid 
	 * @param opaeumId 
	 */
	private Province(Set<City> city, String uuid, long opaeumId) {
		this.setCity(city);
		this.uuid=uuid;
		this.opaeumId=opaeumId;
	}

	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=7131124045743016994l,opposite="province",uuid="914890@_DmOEAZKZEeGVguC2OnqOLw")
	@NumlMetaInfo(uuid="914890@_DmOEAZKZEeGVguC2OnqOLw")
	public Set<City> getCity() {
		Set<City> result = this.city;
		
		return result;
	}
	
	public long getOpaeumId() {
		return this.opaeumId;
	}
	
	public String getUid() {
		String result = getUuid();
		
		return result;
	}
	
	public String getUuid() {
		return this.uuid;
	}
	
	static public Set<Province> getValues() {
		return new HashSet<Province>(java.util.Arrays.asList(values()));
	}
	
	public void z_internalAddToCity(City val) {
		this.city.add(val);
	}
	
	private void addAllToCity(Set<City> city) {
		for ( City o : city ) {
			addToCity(o);
		}
	}
	
	private void addToCity(City city) {
		if ( city!=null ) {
			city.z_internalAddToProvince(this);
			z_internalAddToCity(city);
		}
	}
	
	private void setCity(Set<City> city) {
		this.addAllToCity(city);
	}

}