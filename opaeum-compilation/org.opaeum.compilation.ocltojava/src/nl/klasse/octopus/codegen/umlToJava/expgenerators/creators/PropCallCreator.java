/*
 * Created on Jan 8, 2004
 *
 * Copyright Klasse Objecten
 */
package nl.klasse.octopus.codegen.umlToJava.expgenerators.creators;

import java.util.List;

import nl.klasse.octopus.codegen.umlToJava.maps.ClassifierMap;
import nl.klasse.octopus.codegen.umlToJava.maps.NavToAssocClassMap;
import nl.klasse.octopus.codegen.umlToJava.maps.StructuralFeatureMap;
import nl.klasse.octopus.expressions.IAssociationClassCallExp;
import nl.klasse.octopus.expressions.IAssociationEndCallExp;
import nl.klasse.octopus.expressions.IAttributeCallExp;
import nl.klasse.octopus.expressions.IIterateExp;
import nl.klasse.octopus.expressions.IIteratorExp;
import nl.klasse.octopus.expressions.IOperationCallExp;
import nl.klasse.octopus.expressions.IPropertyCallExp;
import nl.klasse.octopus.expressions.internal.types.IterateExp;
import nl.klasse.octopus.expressions.internal.types.IteratorExp;
import nl.klasse.octopus.expressions.internal.types.LoopExp;
import nl.klasse.octopus.expressions.internal.types.ModelPropertyCallExp;
import nl.klasse.octopus.expressions.internal.types.OperationCallExp;
import nl.klasse.octopus.expressions.internal.types.PropertyCallExp;
import nl.klasse.octopus.model.IAssociationEnd;
import nl.klasse.tools.common.StringHelpers;

import org.opaeum.java.metamodel.OJClass;
import org.opaeum.java.metamodel.OJParameter;

/**
 * PropCallTransformer : 
 */
public class PropCallCreator {
	private OJClass		myClass		 = null;

	/**
	 * 
	 */
	public PropCallCreator(OJClass myClass) {
		super();
		this.myClass = myClass;
	}
	/**
	 * @param exp
	 * @return
	 */
	public StringBuffer makeExpression(PropertyCallExp in, StringBuffer source, boolean isStatic, List<OJParameter> params) {
		StringBuffer thisNode = new StringBuffer();
		StringBuffer newSource = privMakeExpNode(in, source, isStatic, params);
		if (in.getAppliedProperty() != null) {
			thisNode.append(makeExpression(in.getAppliedProperty(), newSource, isStatic, params));
		} else {
			thisNode.append(newSource);
		}
		return thisNode;
	}

	/** Is called only when in is a reference to a class property.
	 * @param in
	 * @return
	 */
	public StringBuffer makeExpressionNode(IPropertyCallExp in, boolean isStatic, List<OJParameter> params) {
		// TODO precondition
		return privMakeExpNode(in, null, isStatic, params);
	}

	private StringBuffer privMakeExpNode(IPropertyCallExp in, StringBuffer source, boolean isStatic, List<OJParameter> params) {
		StringBuffer newSource = new StringBuffer();
		if (in instanceof LoopExp) {
			if (in instanceof IIterateExp) {
				LoopExpCreator maker = new LoopExpCreator(myClass);
				newSource.append(maker.iterateExp((IterateExp)in, source, isStatic, params));
			} else if (in instanceof IIteratorExp) {
				LoopExpCreator maker = new LoopExpCreator(myClass);
				newSource.append(maker.iteratorExp((IteratorExp)in, source, isStatic, params));
			}
		} else if (in instanceof ModelPropertyCallExp) {
			if (in instanceof IAttributeCallExp) {
				newSource.append(attributeCallExp((IAttributeCallExp)in, source));			
			} else if (in instanceof IAssociationEndCallExp) {
				newSource.append(associationEndCallExp((IAssociationEndCallExp)in, source));
			} else if (in instanceof IAssociationClassCallExp) {
				newSource.append(associationClassCallExp((IAssociationClassCallExp)in, source));
			} else if (in instanceof IOperationCallExp ) {
				OperationCallCreator maker = new OperationCallCreator(myClass);
				newSource.append(maker.operationCallExp((OperationCallExp)in, source, isStatic, params));
			}
		} else {
			// error, no other subtypes of PropertyCallExp
			System.err.println("unspecified option in PropCallGenerator.privMakeExpNode:");
			System.err.println("\t" + "type is " + in.getClass() + ", " + in.asOclString());
		}
		return newSource;
	}
			
	private String attributeCallExp(IAttributeCallExp exp, StringBuffer source){
		StructuralFeatureMap mapper = new StructuralFeatureMap(exp.getReferredAttribute());
		String getterName = mapper.getter();
		if ( exp.getReferredAttribute().hasClassScope() || source == null) {
			ClassifierMap classmap = new ClassifierMap(exp.getReferredAttribute().getOwner());
			String classname = classmap.javaType();
			myClass.addToImports(classmap.javaTypePath());
			return classname + "." + getterName + "()";
		} else {
			String sourceStr = StringHelpers.addBrackets(source.toString());
			return sourceStr + "." + getterName + "()";
		}
	}
	private String associationEndCallExp(IAssociationEndCallExp exp, StringBuffer source){
		StructuralFeatureMap mapper = new StructuralFeatureMap(exp.getReferredAssociationEnd());
		String sourceStr = StringHelpers.addBrackets(source.toString());
		return sourceStr + "." + mapper.getter() + "()";
	}
	
	private String associationClassCallExp(IAssociationClassCallExp exp, StringBuffer source){
		IAssociationEnd navSource = exp.getNavigationSource();
		NavToAssocClassMap mapper = new NavToAssocClassMap(navSource);
		String sourceStr = StringHelpers.addBrackets(source.toString());
		return sourceStr + "." + mapper.getter() + "()";
	}
}
