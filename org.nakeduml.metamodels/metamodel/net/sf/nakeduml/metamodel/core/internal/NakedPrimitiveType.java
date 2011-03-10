package net.sf.nakeduml.metamodel.core.internal;

import net.sf.nakeduml.metamodel.core.INakedPrimitiveType;
import nl.klasse.octopus.model.IPrimitiveType;

public class NakedPrimitiveType extends NakedSimpleDataTypeImpl implements INakedPrimitiveType{
	private static final long serialVersionUID = -286943525142230375L;
	public IPrimitiveType oclType;
	public IPrimitiveType getOclType(){
		if(hasSupertype()){
			return ((INakedPrimitiveType) getSupertype()).getOclType();
		}else{
			return this.oclType;
		}
	}
	public void setOclType(IPrimitiveType oclType){
		this.oclType = oclType;
	}
	public boolean isOclPrimitive(){
		return true;
	}
	@Override
	public String getMappedImplementationType() {
		if(getSupertype()!=null){
			return getSupertype().getMappedImplementationType();
		}
		return super.getMappedImplementationType();
	}
	
}
