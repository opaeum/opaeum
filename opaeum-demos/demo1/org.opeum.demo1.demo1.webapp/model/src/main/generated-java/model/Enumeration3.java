package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.opaeum.annotation.NumlMetaInfo;
import org.opaeum.annotation.PropertyMetaInfo;
import org.opaeum.runtime.domain.IEnum;
import org.opaeum.runtime.environment.SimpleTypeRuntimeStrategyFactory;

@NumlMetaInfo(uuid="model.uml@_pFDMgLkmEeGPU4u_bLx6fA")public enum Enumeration3 implements IEnum, Serializable {
;
	private String attribute1;
	private long opaeumId;
	private String uuid;
	/** Constructor for Enumeration3
	 * 
	 * @param Attribute1 
	 * @param uuid 
	 * @param opaeumId 
	 */
	private Enumeration3(String Attribute1, String uuid, long opaeumId) {
		this.setAttribute1(Attribute1);
		this.uuid=uuid;
		this.opaeumId=opaeumId;
	}

	static public Enumeration3 fromAttribute1(String Attribute1) {
		return null;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=3344128764459795050l,strategyFactory=SimpleTypeRuntimeStrategyFactory.class,uuid="model.uml@_9Qw34LkmEeGPU4u_bLx6fA")
	@NumlMetaInfo(uuid="model.uml@_9Qw34LkmEeGPU4u_bLx6fA")
	public String getAttribute1() {
		String result = this.attribute1;
		
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
	
	static public Set<Enumeration3> getValues() {
		return new HashSet<Enumeration3>(java.util.Arrays.asList(values()));
	}
	
	public void setAttribute1(String attribute1) {
		this.z_internalAddToAttribute1(attribute1);
	}
	
	public void z_internalAddToAttribute1(String val) {
		this.attribute1=val;
	}
	
	public void z_internalRemoveFromAttribute1(String val) {
		if ( getAttribute1()!=null && val!=null && val.equals(getAttribute1()) ) {
			this.attribute1=null;
			this.attribute1=null;
		}
	}

}