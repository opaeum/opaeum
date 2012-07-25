package org.opaeum.metamodel.core.internal;

import nl.klasse.octopus.model.IPrimitiveType;

import org.eclipse.uml2.uml.INakedPrimitiveType;

public class NakedPrimitiveTypeImpl extends NakedSimpleDataTypeImpl implements INakedPrimitiveType{
	private static final long serialVersionUID = -286943525142230375L;
	public IPrimitiveType oclType;
	public String getMetaClass(){
		return INakedPrimitiveType.META_CLASS;
	}
	public IPrimitiveType getOclType(){
		if(hasSupertype()){
			INakedPrimitiveType iNakedPrimitiveType = (INakedPrimitiveType) getSupertype();
			return iNakedPrimitiveType.getOclType();
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
