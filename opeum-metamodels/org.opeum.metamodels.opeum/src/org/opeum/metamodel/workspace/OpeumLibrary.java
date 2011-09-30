package org.opeum.metamodel.workspace;

import java.util.HashMap;
import java.util.Map;

import nl.klasse.octopus.stdlib.IOclLibrary;
import nl.klasse.octopus.stdlib.internal.types.StdlibPrimitiveType;

import org.opeum.metamodel.core.INakedClassifier;
import org.opeum.metamodel.core.INakedInterface;
import org.opeum.metamodel.core.INakedSimpleType;
import org.opeum.metamodel.statemachines.INakedStateMachine;

public class OpeumLibrary {
	private Map<String, MappedType> typeMap = new HashMap<String, MappedType>();
	// Built in types
	private INakedSimpleType emailAddressType;
	private INakedSimpleType dateType;
	private INakedSimpleType defaultType;
	private INakedSimpleType realType;
	private INakedSimpleType stringType;
	private INakedSimpleType integerType;
	private INakedSimpleType booleanType;
	private INakedInterface businessRole;
	private INakedInterface taskObject;
	private IOclLibrary oclLibrary;
	private INakedStateMachine taskRequest;

	public OpeumLibrary(IOclLibrary oclLibrary) {
		super();
		this.setOclLibrary(oclLibrary);
	}

	public void setBooleanType(INakedSimpleType booleanType) {
		this.booleanType = booleanType;
	}

	public void setDateType(INakedSimpleType dateType) {
		this.dateType = dateType;
	}

	public void setDefaultType(INakedSimpleType defaultType) {
		this.defaultType = defaultType;
	}

	public void setEmailAddressType(INakedSimpleType emailAddressType) {
		this.emailAddressType = emailAddressType;
	}

	public void setIntegerType(INakedSimpleType integerType) {
		this.integerType = integerType;
	}

	public void setRealType(INakedSimpleType realType) {
		this.realType = realType;
	}

	public void setStringType(INakedSimpleType stringType) {
		this.stringType = stringType;
	}

	public INakedSimpleType getBooleanType() {
		return booleanType;
	}

	public INakedSimpleType getDateType() {
		return dateType;
	}

	public INakedSimpleType getDefaultType() {
		return defaultType;
	}

	public INakedSimpleType getEmailAddressType() {
		return emailAddressType;
	}

	public INakedSimpleType getIntegerType() {
		return integerType;
	}

	public INakedSimpleType getRealType() {
		return realType;
	}

	public INakedSimpleType getStringType() {
		return stringType;
	}

	public Map<String, MappedType> getTypeMap() {
		return typeMap;
	}

	public INakedClassifier lookupStandardType(StdlibPrimitiveType standardType) {
		if (standardType.getName().equals("Integer")) {
			return getIntegerType();
		}
		if (standardType.getName().equals("Real")) {
			return getRealType();
		}
		if (standardType.getName().equals("String")) {
			return getStringType();
		}
		if (standardType.getName().equals("Boolean")) {
			return getBooleanType();
		}
		return null;
	}

	public void setBusinessRole(INakedInterface businessRole){
		this.businessRole = businessRole;
	}

	public INakedInterface getBusinessRole(){
		return businessRole;
	}

	public void setTaskObject(INakedInterface taskObject){
		this.taskObject = taskObject;
	}

	public INakedInterface getTaskObject(){
		return taskObject;
	}

	private void setOclLibrary(IOclLibrary oclLibrary){
		this.oclLibrary = oclLibrary;
	}

	public IOclLibrary getOclLibrary(){
		return oclLibrary;
	}

	public INakedStateMachine getTaskRequest(){
		return this.taskRequest;
	}

	public void setTaskRequest(INakedStateMachine taskRequest){
		this.taskRequest = taskRequest;
	}
}
