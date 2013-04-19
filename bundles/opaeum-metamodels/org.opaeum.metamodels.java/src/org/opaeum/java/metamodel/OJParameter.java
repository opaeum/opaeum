package org.opaeum.java.metamodel;

import java.util.Set;

import org.opaeum.java.metamodel.generated.OJParameterGEN;

public class OJParameter extends OJParameterGEN{
	protected boolean isFinal;
	public OJParameter(String name){
		super(name);
	}
	public OJParameter(String string,OJPathName ojPathName){
		this(string);
		setType(ojPathName);
	}
	public OJParameter(){
		super("");
	}
	public String toJavaString(){
		if(getType() == null){
			System.err.println("type of param " + getName() + " is null");
			return "";
		}
		StringBuilder result = new StringBuilder();
		if(isFinal){
			result.append("final ");
		}
		result.append(getType().getTypeNameWithTypeArguments());
		result.append(" ");
		result.append(getName());
		return result.toString();
	}
	public void renameAll(Set<OJPathName> renamePathNames,String newName){
		getType().renameAll(renamePathNames, newName);
	}
	public OJParameter getDeepCopy(){
		OJParameter result = new OJParameter(getName());
		this.copyDeepInfoInto(result);
		return result;
	}
	public void copyDeepInfoInto(OJParameter copy){
		super.copyDeepInfoInto(copy);
		if(getType() != null){
			copy.setType(getType().getDeepCopy());
			copy.setFinal(isFinal);
		}
	}
	public void setFinal(boolean b){
		this.isFinal = b;
	}
	public void setName(String string){
		super.name=string;
		
	}
}