package net.sf.nakeduml.javametamodel;

import java.util.Map;

import net.sf.nakeduml.javametamodel.generated.OJParameterGEN;

public class OJParameter extends OJParameterGEN {
	/******************************************************
	 * The constructor for this classifier.
	*******************************************************/	
	public OJParameter() {
		super();
	}

	public String toJavaString(){
		if (getType() == null) {
			System.err.println("type of param " + getName() + " is null");
			return "";
		} 
		String result = getType().getCollectionTypeName();
		result = result + " " + getName();
		return result;
	}

	public void renameAll(Map<String, OJPathName> renamePathNames, String newName) {
		getType().renameAll(renamePathNames, newName);
	}
	
	public OJParameter getDeepCopy() {
		OJParameter result = new OJParameter();
		this.copyDeepInfoInto(result);
		return result;
	}
	
	public void copyDeepInfoInto(OJParameter copy) {
		super.copyDeepInfoInto(copy);
		if ( getType() != null ) {
			copy.setType(getType().getDeepCopy());
		}
	}	
}