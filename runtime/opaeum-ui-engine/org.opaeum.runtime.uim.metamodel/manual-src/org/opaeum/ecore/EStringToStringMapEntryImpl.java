package org.opaeum.ecore;

import java.util.Map;

import org.w3c.dom.Element;

public class EStringToStringMapEntryImpl extends EObjectImpl implements EStringToStringMapEntry{
	String key;
	String value;
	public String getKey(){
		return key;
	}
	public void setKey(String key){
		this.key = key;
	}
	public String getValue(){
		return value;
	}
	public void setValue(String value){
		this.value = value;
	}
	
	@Override
	public void populateReferencesFromXml(Element currentPropertyValueNode){
		// TODO Auto-generated method stub
		
	}
	@Override
	public void buildTreeFromXml(Element xml){
		key=xml.getAttribute("key");
		value=xml.getAttribute("value");
	}
}
