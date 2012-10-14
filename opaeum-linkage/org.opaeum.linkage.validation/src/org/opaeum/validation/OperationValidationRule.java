package org.opaeum.validation;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.opaeum.metamodel.validation.IValidationRule;

public enum OperationValidationRule implements IValidationRule{
	INTERFACE_OPERATION_STATIC("Interface operations cannot be static","{0} is marked as static but belongs to an interface",pkg.getFeature_IsStatic()),
	REDEFINED_OPERATION_IS_QUERY_CORRESPOND("Operations must have the same 'isQuery' setting as their redefined operations",
			"{0} is marked as a query, but it redefines {1} which is not a query",pkg.getOperation_RedefinedOperation()),
	REDEFINED_OPERATION_NOT_QUERY_CORRESPOND("Operations must have the same 'isQuery' setting as their redefined operations",
			"{0} is not marked as a query, but redefines {1} which is marked as a query",pkg.getOperation_RedefinedOperation()),
	BODY_CONDITIONS_REQUIRE_QUERY_OPERATION("Operations with body conditions must be marked as queries","{0} has a body condition but is not marked as a query",pkg.getOperation_IsQuery());
	private String description;
	private String messagePattern;
	private EStructuralFeature[] feature;
	private OperationValidationRule(String description,String messagePattern,EStructuralFeature ... feature){
		this.description = description;
		this.messagePattern = messagePattern;
		this.feature = feature;
	}
	@Override
	public EStructuralFeature[] getFeatures(){
		return feature;
	}
	public String getDescription(){
		return this.description;
	}
	public String getMessagePattern(){
		return messagePattern;
	}
}
