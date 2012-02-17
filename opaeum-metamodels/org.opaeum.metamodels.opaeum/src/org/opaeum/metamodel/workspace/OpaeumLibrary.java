package org.opaeum.metamodel.workspace;

import java.util.HashMap;
import java.util.Map;

import nl.klasse.octopus.stdlib.IOclLibrary;
import nl.klasse.octopus.stdlib.internal.types.StdlibPrimitiveType;

import org.opaeum.metamodel.core.INakedClassifier;
import org.opaeum.metamodel.core.INakedEntity;
import org.opaeum.metamodel.core.INakedInterface;
import org.opaeum.metamodel.core.INakedSimpleType;
import org.opaeum.metamodel.core.INakedStructuredDataType;
import org.opaeum.metamodel.statemachines.INakedStateMachine;

public class OpaeumLibrary{
	private Map<String,MappedType> typeMap = new HashMap<String,MappedType>();
	// Built in types
	private INakedSimpleType emailAddressType;
	private INakedSimpleType dateType;
	private INakedStructuredDataType durationType;
	private INakedSimpleType defaultType;
	private INakedSimpleType realType;
	private INakedSimpleType stringType;
	private INakedSimpleType integerType;
	private INakedSimpleType booleanType;
	private INakedInterface businessRole;
	private INakedInterface taskObject;
	private INakedEntity opaeumPerson;
	private INakedInterface processResponsibilityObject;
	private INakedInterface taskResponsibilityObject;
	private IOclLibrary oclLibrary;
	private INakedStateMachine taskRequest;
	private INakedStateMachine processRequest;
	private INakedInterface processObject;
	private INakedStateMachine abstractRequest;
	public void setAbstractRequest(INakedStateMachine abstractRequest){
		this.abstractRequest = abstractRequest;
	}
	public void setProcessObject(INakedInterface processObject){
		this.processObject = processObject;
	}
	public OpaeumLibrary(IOclLibrary oclLibrary){
		super();
		this.setOclLibrary(oclLibrary);
	}
	public void setBooleanType(INakedSimpleType booleanType){
		this.booleanType = booleanType;
	}
	public void setDateType(INakedSimpleType dateType){
		this.dateType = dateType;
	}
	public void setDefaultType(INakedSimpleType defaultType){
		this.defaultType = defaultType;
	}
	public void setEmailAddressType(INakedSimpleType emailAddressType){
		this.emailAddressType = emailAddressType;
	}
	public void setIntegerType(INakedSimpleType integerType){
		this.integerType = integerType;
	}
	public void setRealType(INakedSimpleType realType){
		this.realType = realType;
	}
	public void setStringType(INakedSimpleType stringType){
		this.stringType = stringType;
	}
	public INakedSimpleType getBooleanType(){
		return booleanType;
	}
	public INakedSimpleType getDateType(){
		return dateType;
	}
	public INakedSimpleType getDefaultType(){
		return defaultType;
	}
	public INakedSimpleType getEmailAddressType(){
		return emailAddressType;
	}
	public INakedSimpleType getIntegerType(){
		return integerType;
	}
	public INakedSimpleType getRealType(){
		return realType;
	}
	public INakedSimpleType getStringType(){
		return stringType;
	}
	public Map<String,MappedType> getTypeMap(){
		return typeMap;
	}
	public INakedClassifier lookupStandardType(StdlibPrimitiveType standardType){
		if(standardType.getName().equals("Integer")){
			return getIntegerType();
		}
		if(standardType.getName().equals("Real")){
			return getRealType();
		}
		if(standardType.getName().equals("String")){
			return getStringType();
		}
		if(standardType.getName().equals("Boolean")){
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
	public INakedStructuredDataType getDurationType(){
		return durationType;
	}
	public void setDurationType(INakedStructuredDataType durationType){
		this.durationType = durationType;
	}
	public INakedEntity getOpaeumPerson(){
		return opaeumPerson;
	}
	public void setOpaeumPerson(INakedEntity opaeumPerson){
		this.opaeumPerson = opaeumPerson;
	}
	public INakedInterface getProcessResponsibilityObject(){
		return processResponsibilityObject;
	}
	public void setProcessResponsibilityObject(INakedInterface processResponsibilityObject){
		this.processResponsibilityObject = processResponsibilityObject;
	}
	public INakedInterface getTaskResponsibilityObject(){
		return taskResponsibilityObject;
	}
	public void setTaskResponsibilityObject(INakedInterface taskResponsibilityObject){
		this.taskResponsibilityObject = taskResponsibilityObject;
	}
	public INakedInterface getProcessObject(){
		return this.processObject;
	}
	public INakedStateMachine getAbstractRequest(){
		return abstractRequest;
	}
	public INakedStateMachine getProcessRequest(){
		return processRequest;
	}
	public void setProcessRequest(INakedStateMachine processRequest){
		this.processRequest = processRequest;
	}
}
