/*
 * Created on Jan 8, 2004
 *
 * Copyright Klasse Objecten
 */
package nl.klasse.octopus.codegen.umlToJava.expgenerators.creators;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import nl.klasse.octopus.codegen.helpers.CommonNames;
import nl.klasse.octopus.codegen.umlToJava.expgenerators.visitors.OclUtilityCreator;
import nl.klasse.octopus.codegen.umlToJava.maps.ClassifierMap;
import nl.klasse.octopus.codegen.umlToJava.maps.OperationMap;
import nl.klasse.octopus.codegen.umlToJava.maps.StateMap;
import nl.klasse.octopus.expressions.IOclStateLiteralExp;
import nl.klasse.octopus.expressions.IOclTypeLiteralExp;
import nl.klasse.octopus.expressions.IOclUndefinedLiteralExp;
import nl.klasse.octopus.expressions.IOperationCallExp;
import nl.klasse.octopus.expressions.internal.types.OclExpression;
import nl.klasse.octopus.expressions.internal.types.OperationCallExp;
import nl.klasse.octopus.model.IClassifier;
import nl.klasse.octopus.model.IEnumerationType;
import nl.klasse.octopus.model.IOperation;
import nl.klasse.octopus.model.IState;
import nl.klasse.octopus.stdlib.internal.types.StdlibPrimitiveType;
import nl.klasse.tools.common.Check;
import nl.klasse.tools.common.StringHelpers;
import nl.klasse.tools.common.Util;

import org.opeum.java.metamodel.OJClass;
import org.opeum.java.metamodel.OJField;
import org.opeum.java.metamodel.OJParameter;

/**
 * PropCallTransformer : 
 */
@SuppressWarnings("rawtypes")
public class OperationCallCreator {
	private OJClass	myClass		 		= null;

	public OperationCallCreator(OJClass myClass) {
		super();
		this.myClass = myClass;
	}

	public String operationCallExp(OperationCallExp exp, StringBuffer source, boolean isStatic, List<OJParameter> params) {
		String result = "";
		List<String> args = makeArgs(exp, isStatic, params);
		IOperation referedOp = exp.getReferredOperation();
		IClassifier sourceType = (exp.getSource() == null ? null : exp.getSource().getNodeType());
		if (sourceType == null){
			if (referedOp.getName().equals("allInstances")) {	// on OclAny
				result = buildAllInstances(exp, source, args, referedOp);
			} else {
				result = buildClassOp(args, referedOp);		
			}	
		} else if ( sourceType.isCollectionKind() ){
			CollectionOperCallCreator maker1 = new CollectionOperCallCreator(myClass);
			result = maker1.collectionOperCall(exp, source.toString(), args, referedOp, isStatic, params);	
		} else if ( sourceType instanceof StdlibPrimitiveType) {
			BasicTypeOperCallCreator maker2 = new BasicTypeOperCallCreator(myClass);
			result = maker2.makeOperCall(exp, source.toString(), args, referedOp, params);
		} else if ( sourceType instanceof IEnumerationType) {
			EnumTypeOperCallCreator maker3 = new EnumTypeOperCallCreator(myClass);
			result = maker3.makeOperCall(exp, source.toString(), args, referedOp, params);
		} else {
			result = makeOperCall(exp, source, args, referedOp, params);
		}
		return result ;
	}

	/**
	 * @param args
	 * @param referedOp
	 * @return
	 */
	private String buildClassOp(List args, IOperation referedOp) {
		ClassifierMap OWNER = new ClassifierMap(referedOp.getOwner());
		myClass.addToImports(OWNER.javaTypePath());
		String className = OWNER.javaType();
		OperationMap OPERATION = new OperationMap(referedOp);
		String opName = OPERATION.javaOperName();
		String arguments = Util.collectionToString(args, ", ");
		return className + "." + opName + "(" + arguments + ")";
	}

	/**
	 * @param exp
	 * @return
	 */
	private List<String> makeArgs(IOperationCallExp exp, boolean isStatic, List<OJParameter> params) {
		List<String> result = new ArrayList<String>();
		Iterator it = exp.getArguments().iterator();
		while( it.hasNext()){
			OclExpression arg = (OclExpression) it.next();
			ExpressionCreator myExpMaker = new ExpressionCreator(myClass);	
			String expStr = myExpMaker.makeExpression(arg, isStatic, params);
			result.add(expStr);
		}
		return result;
	}

	private String makeOperCall(OperationCallExp exp, StringBuffer source,	List args, IOperation referedOp, List params) {
		String result = "";
		if (referedOp != null) {
			if (referedOp.getName().equals("oclIsNew")){				// on OclAny
				// TODO decide whether to implement OclIsNew
				result = source + " /* no implementation of oclIsNew available */ ";
			} else if (referedOp.getName().equals("oclIsUndefined")) {	// on OclAny
				result = buildIsUndefined(exp, source, args);
			} else if (referedOp.getName().equals("oclInState") || referedOp.getName().equals("oclIsInState")){		// on OclAny
				result = buildOclInState(exp, source, args);
			} else if (referedOp.getName().equals("oclIsKindOf")) {		// on OclAny
				result = buildIsKindOf(exp, source, args);
			} else if (referedOp.getName().equals("oclIsTypeOf")) {		// on OclAny
				result = buildIsTypeOf(exp, source, args);
			} else if (referedOp.getName().equals("oclAsType")) {		// on OclAny
				result = buildAsType(exp, source, args);
			} else if (referedOp.getName().equals("asSet")) {			// on OclAny
				result = buildAsSet(exp, source.toString(), args, params);
			} else if (referedOp.getName().equals("=")) {				// on OclAny
				OclExpression xx = (OclExpression) exp.getArguments().get(0);
				if (xx instanceof IOclUndefinedLiteralExp ) {
					result = source + " == null";
				} else {
					result = source + ".equals(" + args.get(0) + ")";
				}
			} else if (referedOp.getName().equals("<>")) {				// on OclAny
				OclExpression xx = (OclExpression) exp.getArguments().get(0);
				if (xx instanceof IOclUndefinedLiteralExp ) {
					result = source + " != null";
				} else {
					result = "!" + source + ".equals(" + args.get(0) + ")";
				}
			} else {
				result = buildModelOp(exp, source.toString(), args, referedOp);
			}
		}
		return result;
	}

	private String buildAsSet(OperationCallExp exp, String source, List args, List params) {
		String result = "";
		myClass.addToImports(OclUtilityCreator.getStdlibPath());
		result = OclUtilityCreator.getStdlibPath().getTypeName() + "." + "objectAsSet(" + source + ")";			
		return result;
	}

	/**
	 * @param exp
	 * @param source
	 * @param args
	 * @return
	 */
	private String buildAllInstances(OperationCallExp exp, StringBuffer source, List args, IOperation referedOp) {
		ClassifierMap OWNER = new ClassifierMap(referedOp.getOwner());
		String className = OWNER.javaType();
		OperationMap OPERATION = new OperationMap(referedOp);
		String opName = OPERATION.javaOperName();	// should be "allInstances"
		String arguments = Util.collectionToString(args, ", ");
		// indicate that this class needs the allInstances oper implemented
		if (myClass != null) {
			OJField ff = myClass.findField(CommonNames.USES_ALLINSTANCES_FIELD_NAME);
			if (ff != null) ff.setInitExp("true");
		}
		// return the operation call
		return className + "." + opName + "(" + arguments + ")";
	}

	/**
	 * @param exp
	 * @param source
	 * @param args
	 * @return
	 */
	private String buildIsUndefined(IOperationCallExp exp, StringBuffer source, List args) {
		String result = "";
		result = StringHelpers.addBrackets(source.toString()) + " == null";
		return result;
	}
	private String buildAsType(IOperationCallExp exp, StringBuffer source, List args) {
		IClassifier argType = ((IOclTypeLiteralExp)exp.getArguments().get(0)).getReferredClassifier();
		String typeStr = new ClassifierMap(argType).javaType();
		String result = "((" + typeStr +  ") " + StringHelpers.addBrackets(source.toString()) + ")";
		return result;
	}
	private String buildIsKindOf(IOperationCallExp exp, StringBuffer source, List args) {
		String result = "";
//		IClassifier argType = ((IOclTypeLiteralExp)exp.getArguments().get(0)).getReferredClassifier();
//		IClassifier sourceType = exp.getSource().getNodeType();
		//TODO check necessity!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
//		if (!Conformance.conformsTo(sourceType, argType)) {
//			result = "false /* types of oclIsKindOf do not conform */";			
//		} else {
			result = StringHelpers.addBrackets(source.toString()) + " instanceof " + args.get(0);
//		}
		return result;
	}
	private String buildIsTypeOf(IOperationCallExp exp, StringBuffer source, List args) {
		String result = "";
		result = StringHelpers.addBrackets(source.toString()) + ".getClass() == " + args.get(0) + ".class";
		return result;
	}
	/**
	 * @param exp
	 * @param source
	 * @param args
	 */
	private String buildOclInState(IOperationCallExp exp, StringBuffer source, List args) {
		// exp.getReferedOp().getName() should equal 'oclInState'
		String result = "";
		IOclStateLiteralExp arg = (IOclStateLiteralExp) exp.getArguments().get(0);
		IState usedState = arg.getReferredState();
		StateMap STATE = new StateMap(usedState);
		result = source + "." + STATE.getter() + "() == true";
		return result;		
	}
	
	private String buildModelOp(IOperationCallExp exp, String source, List args, IOperation referedOp) {
		Check.pre("OperationCallTransformer.buildModelOp: source is empty", 
					source != null && source.length() > 0);
		source = StringHelpers.addBrackets(source);
		String result = "";
		OperationMap OPERATION = new OperationMap(referedOp);
		String operationName = OPERATION.javaOperName();
		String arguments = Util.collectionToString(args, ", ");
//		if ( referedOp.isInfix() ) {
//			result = source + " " + operationName + " "  + StringHelpers.addBrackets(arguments);
//		} else 
		// no infix model opers allowed: they are all transformed into normal ops
		if ( referedOp.isPrefix() ) {
			source = StringHelpers.addBrackets(source);
			result = operationName + " " + source;		// only one argument allowed
		} else {
			result = source + "." + operationName + "("  + arguments + ")";
		}
		return result;
	}
}
