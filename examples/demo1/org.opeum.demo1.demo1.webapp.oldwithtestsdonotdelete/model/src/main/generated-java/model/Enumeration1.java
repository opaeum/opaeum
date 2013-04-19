package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.opaeum.annotation.NumlMetaInfo;
import org.opaeum.annotation.PropertyMetaInfo;
import org.opaeum.runtime.domain.IEnum;
import org.opaeum.runtime.environment.SimpleTypeRuntimeStrategyFactory;

@NumlMetaInfo(uuid="model.uml@_Il3dALkpEeGPU4u_bLx6fA")public enum Enumeration1 implements IEnum, Serializable {
	ENUMERATIONLITERAL1(12340,"model.uml@_KKpCcLkpEeGPU4u_bLx6fA",664316455779765488l);
	private long opaeumId;
	private Integer property1;
	private String uuid;
	/** Constructor for Enumeration1
	 * 
	 * @param Property1 
	 * @param uuid 
	 * @param opaeumId 
	 */
	private Enumeration1(Integer Property1, String uuid, long opaeumId) {
		this.setProperty1(Property1);
		this.uuid=uuid;
		this.opaeumId=opaeumId;
	}

	public long getOpaeumId() {
		return this.opaeumId;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=3890410752592916058l,strategyFactory=SimpleTypeRuntimeStrategyFactory.class,uuid="model.uml@_Kl8eYLkpEeGPU4u_bLx6fA")
	@NumlMetaInfo(uuid="model.uml@_Kl8eYLkpEeGPU4u_bLx6fA")
	public Integer getProperty1() {
		Integer result = this.property1;
		
		return result;
	}
	
	public String getUid() {
		String result = getUuid();
		
		return result;
	}
	
	public String getUuid() {
		return this.uuid;
	}
	
	static public Set<Enumeration1> getValues() {
		return new HashSet<Enumeration1>(java.util.Arrays.asList(values()));
	}
	
	public void z_internalAddToProperty1(Integer val) {
		this.property1=val;
	}
	
	private void setProperty1(Integer property1) {
		this.z_internalAddToProperty1(property1);
	}

}