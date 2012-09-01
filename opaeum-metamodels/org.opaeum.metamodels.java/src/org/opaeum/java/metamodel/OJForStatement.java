package org.opaeum.java.metamodel;

import java.util.Set;

import org.opaeum.java.metamodel.generated.OJForStatementGEN;
import org.opaeum.java.metamodel.utilities.JavaStringHelpers;

public class OJForStatement extends OJForStatementGEN{
	public OJForStatement(){
		super();
		setBody(new OJBlock());
	}
	public OJForStatement(String elementName,OJPathName type,String collection){
		super();
		setCollectionExpression(collection);
		setElemName(elementName);
		setElemType(type);
		setBody(new OJBlock());
	}
	public String toJavaString(){
		String result = "for ( " + getElemType().getTypeNameWithTypeArguments() + " " + getElemName() + " : " + getCollectionExpression()
				+ " ) {\n";
		result = result + JavaStringHelpers.indent(getBody().toJavaString(), 1) + "\n}";
		return result;
	}
	public OJForStatement getDeepCopy(){
		OJForStatement copy = new OJForStatement();
		copyDeepInfoInto(copy);
		return copy;
	}
	public void copyDeepInfoInto(OJForStatement copy){
		super.copyDeepInfoInto(copy);
		copy.setElemName(getElemName());
		copy.setCollectionExpression(getCollectionExpression());
		if(getElemType() != null){
			copy.setElemType(getElemType().getDeepCopy());
		}
		if(getBody() != null){
			copy.setBody(getBody().getDeepCopy());
		}
	}
	public void renameAll(Set<OJPathName> renamePathNames,String suffix){
		if(getElemType() != null){
			getElemType().renameAll(renamePathNames, suffix);
		}
		setCollectionExpression(replaceAll(getCollectionExpression(), renamePathNames, suffix));
		if(getBody() != null){
			getBody().renameAll(renamePathNames, suffix);
		}
	}
}