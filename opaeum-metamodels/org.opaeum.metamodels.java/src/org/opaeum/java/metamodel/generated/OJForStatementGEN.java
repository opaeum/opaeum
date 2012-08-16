package org.opaeum.java.metamodel.generated;

import org.opaeum.java.metamodel.OJBlock;
import org.opaeum.java.metamodel.OJElement;
import org.opaeum.java.metamodel.OJForStatement;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.OJStatement;

abstract public class OJForStatementGEN extends OJStatement{
	private String elemName = "";
	private String collection = "";
	private OJPathName elemType = null;
	private OJBlock body = null;
	protected OJForStatementGEN(){
		super();
	}
	protected OJForStatementGEN(String name,String comment,String elemName,String collection){
		super();
		super.setName(name);
		super.setComment(comment);
		this.setElemName(elemName);
		this.setCollectionExpression(collection);
	}
	public String getElemName(){
		return elemName;
	}
	public void setElemName(String element){
		if(elemName != element){
			elemName = element;
		}
	}
	public String getCollectionExpression(){
		return collection;
	}
	public void setCollectionExpression(String element){
		if(collection != element){
			collection = element;
		}
	}
	public OJPathName getElemType(){
		return elemType;
	}
	public void setElemType(OJPathName element){
		if(elemType != element){
			elemType = element;
		}
	}
	public OJBlock getBody(){
		return body;
	}
	public void setBody(OJBlock element){
		if(body != element){
			body = element;
		}
	}
	public String toString(){
		String result = "";
		result = super.toString();
		if(this.getElemName() != null){
			result = result + " elemName:" + this.getElemName() + ", ";
		}
		if(this.getCollectionExpression() != null){
			result = result + " collection:" + this.getCollectionExpression();
		}
		return result;
	}
	public OJElement getCopy(){
		OJForStatement result = new OJForStatement();
		this.copyInfoInto(result);
		return result;
	}
	public void copyInfoInto(OJForStatement copy){
		super.copyInfoInto(copy);
		copy.setElemName(getElemName());
		copy.setCollectionExpression(getCollectionExpression());
		if(getElemType() != null){
			copy.setElemType(getElemType());
		}
		if(getBody() != null){
			copy.setBody(getBody());
		}
	}
}