package org.opaeum.metamodel.models.internal;

import java.util.HashMap;
import java.util.Map;

import org.opaeum.metamodel.core.INakedInstanceSpecification;
import org.opaeum.metamodel.core.internal.NakedRootObjectImpl;
import org.opaeum.metamodel.models.INakedModel;

public class NakedModelImpl extends NakedRootObjectImpl implements INakedModel{
	public static final String META_CLASS = "model";
	private boolean isLibrary;
	private Map<String,String> providedImplementationCode = new HashMap<String,String>();
	private static final long serialVersionUID = -8628461048243090233L;
	private boolean isRegeneratingLibrary;
	String viewPoint;
	@Override
	public String getMetaClass(){
		return "model";
	}
	public String getViewpoint(){
		return this.viewPoint;
	}
	public void setViewpoint(String viewPoint){
		this.viewPoint = viewPoint;
	}
	public void setLibrary(boolean isLibrary){
		this.isLibrary = isLibrary;
	}
	public boolean isLibrary(){
		return isLibrary;
	}
	public boolean isRegeneratingLibrary(){
		return isRegeneratingLibrary;
	}
	@Override
	public void addStereotype(INakedInstanceSpecification stereotype){
		super.addStereotype(stereotype);
		if(stereotype.hasValueForFeature("regenerate")){
			isRegeneratingLibrary=Boolean.TRUE.equals(stereotype.getFirstValueFor("regenerate").getValue());
		}
	}
	public void putImplementationCode(String artifactName, String code){
		providedImplementationCode.put(artifactName, code);
	}
	@Override
	public String getImplementationCodeFor(String artifactName){
		return providedImplementationCode.get(artifactName);
	}
}
