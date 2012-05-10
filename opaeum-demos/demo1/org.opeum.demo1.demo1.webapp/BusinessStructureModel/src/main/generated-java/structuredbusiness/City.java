package structuredbusiness;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.opaeum.annotation.NumlMetaInfo;
import org.opaeum.annotation.PropertyMetaInfo;
import org.opaeum.runtime.domain.IEnum;

@NumlMetaInfo(uuid="914890@_HSme4JKIEeGFkOm2e1MJNQ")public enum City implements IEnum, Serializable {
	JOHANNESBURG(Province.GAUTENG,"914890@_Ib1X0JKIEeGFkOm2e1MJNQ",370895807509634180l),
	CAPETOWN(Province.WESTERNCAPE,"914890@_UXveQJKKEeGFkOm2e1MJNQ",6617031855133737322l),
	PRETORIA(Province.GAUTENG,"914890@_40zbwJKLEeGVguC2OnqOLw",9013938801218636412l),
	DURBAN(Province.KWAZULUNATAL,"914890@_AR5fcJKMEeGVguC2OnqOLw",9203863868638345078l);
	private long opaeumId;
	private Province province;
	private String uuid;
	/** Constructor for City
	 * 
	 * @param province 
	 * @param uuid 
	 * @param opaeumId 
	 */
	private City(Province province, String uuid, long opaeumId) {
		this.setProvince(province);
		this.uuid=uuid;
		this.opaeumId=opaeumId;
	}

	public long getOpaeumId() {
		return this.opaeumId;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=4452015602518132608l,opposite="city",uuid="914890@_DmOEBJKZEeGVguC2OnqOLw")
	@NumlMetaInfo(uuid="914890@_DmOEBJKZEeGVguC2OnqOLw")
	public Province getProvince() {
		Province result = this.province;
		
		return result;
	}
	
	public String getUid() {
		String result = getUuid();
		
		return result;
	}
	
	public String getUuid() {
		return this.uuid;
	}
	
	static public Set<City> getValues() {
		return new HashSet<City>(java.util.Arrays.asList(values()));
	}
	
	public void z_internalAddToProvince(Province val) {
		this.province=val;
	}
	
	private void setProvince(Province province) {
		if ( this.getProvince()!=null ) {
		
		}
		if ( province!=null ) {
			province.z_internalAddToCity(this);
			this.z_internalAddToProvince(province);
		}
	}

}