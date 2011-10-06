/*
 * Created on Jun 14, 2004
 *
 * Copyright Klasse Objecten
 */
package nl.klasse.octopus.codegen.umlToJava.expgenerators.creators;

import java.util.List;

import nl.klasse.octopus.codegen.umlToJava.expgenerators.visitors.OclUtilityCreator;
import nl.klasse.octopus.codegen.umlToJava.maps.ClassifierMap;
import nl.klasse.octopus.expressions.IOclTypeLiteralExp;
import nl.klasse.octopus.expressions.internal.types.OperationCallExp;
import nl.klasse.octopus.model.IClassifier;
import nl.klasse.octopus.model.IOperation;
import nl.klasse.octopus.oclengine.internal.OclEngine;
import nl.klasse.octopus.stdlib.IOclLibrary;
import nl.klasse.tools.common.StringHelpers;

import org.opaeum.java.metamodel.OJClass;

/**
 * BasisOperCallGenerator : 
 */
@SuppressWarnings("rawtypes")
public class BasicTypeOperCallCreator {
	private OJClass myClass = null;

	/**
	 * 
	 */
	public BasicTypeOperCallCreator(OJClass myClass) {
		super();
		this.myClass = myClass;
	}

	public String makeOperCall(OperationCallExp exp, String source, List args, IOperation referedOp, List params) {
		String result = "";
		source = StringHelpers.addBrackets(source);
		IClassifier sourceType = (exp.getSource() == null ? null : exp.getSource().getNodeType());
		if ( sourceType == OclEngine.getCurrentOclLibrary().lookupStandardType(IOclLibrary.BooleanTypeName) ){
			result = booleanOperCall(exp, source, args, referedOp, params);				 
		} else if ( sourceType == OclEngine.getCurrentOclLibrary().lookupStandardType(IOclLibrary.StringTypeName) ){
			result = stringOperCall(exp, source, args, referedOp, params);				 
		} else if ( sourceType == OclEngine.getCurrentOclLibrary().lookupStandardType(IOclLibrary.IntegerTypeName) ){
			result = integerOperCall(exp, source, args, referedOp, params);				 
		} else if ( sourceType == OclEngine.getCurrentOclLibrary().lookupStandardType(IOclLibrary.RealTypeName) ){
			result = realOperCall(exp, source, args, referedOp, params);	
		} else {
			System.err.println("unspecified option in BasicTypeOperCallGenerator.makeOperCall:");
			System.err.println("\t" + sourceType.getPathName());
		}			 
		return result;
	}
	
	/**
	 * @param exp
	 * @param source
	 * @param args
	 * @param referedOp
	 * @param params
	 * @return
	 */
	private String integerOperCall(OperationCallExp exp, String source, List args, IOperation referedOp, List params) {
		String result = "";
		if (referedOp != null) {
			if (referedOp.getName().equals("asBag")) {
				myClass.addToImports(OclUtilityCreator.getStdlibPath());
				result = OclUtilityCreator.getStdlibPath().getLast() + ".intAsBag(" + source + ")";
			} else if (referedOp.getName().equals("asSequence")) {
				myClass.addToImports(OclUtilityCreator.getStdlibPath());
				result = OclUtilityCreator.getStdlibPath().getLast() + ".intAsSequence(" + source + ")";
			} else if (referedOp.getName().equals("asOrderedSet")) {
				myClass.addToImports(OclUtilityCreator.getStdlibPath());
				result = OclUtilityCreator.getStdlibPath().getLast() + ".intAsOrderedSet(" + source + ")";
			} else if (referedOp.getName().equals("asSet")) {
				myClass.addToImports(OclUtilityCreator.getStdlibPath());
				result = OclUtilityCreator.getStdlibPath().getLast() + ".intAsSet(" + source + ")";
			} else if (referedOp.getName().equals("toString")) {
				result = "new Integer(" + source + ").toString()";
			} else {
				result = numericOperCall(exp, source, args, referedOp, params);
			}
		}
		return result;
	}

	/**
	 * @param exp
	 * @param source
	 * @param args
	 * @param referedOp
	 * @param params
	 * @return
	 */
	private String realOperCall(OperationCallExp exp, String source, List args, IOperation referedOp, List params) {
		String result = "";
		if (referedOp != null) {
			if (referedOp.getName().equals("asBag")) {
				myClass.addToImports(OclUtilityCreator.getStdlibPath());
				result = OclUtilityCreator.getStdlibPath().getLast() + ".realAsBag(" + source + ")";
			} else if (referedOp.getName().equals("asSequence")) {
				myClass.addToImports(OclUtilityCreator.getStdlibPath());
				result = OclUtilityCreator.getStdlibPath().getLast() + ".realAsSequence(" + source + ")";
			} else if (referedOp.getName().equals("asOrderedSet")) {
				myClass.addToImports(OclUtilityCreator.getStdlibPath());
				result = OclUtilityCreator.getStdlibPath().getLast() + ".realAsOrderedSet(" + source + ")";
			} else if (referedOp.getName().equals("asSet")) {
				myClass.addToImports(OclUtilityCreator.getStdlibPath());
				result = OclUtilityCreator.getStdlibPath().getLast() + ".realAsSet(" + source + ")";
			} else if (referedOp.getName().equals("toString")) {
				result = "new Float(" + source + ").toString()";
			} else {
				result = numericOperCall(exp, source, args, referedOp, params);
			}
		}
		return result;
	}

	private String booleanOperCall(OperationCallExp exp, String source, List args, IOperation referedOp, List params) {
		String result = "";
		String argStr = "";
		if (referedOp != null) {
			if (!args.isEmpty()) {
				argStr = (String) args.get(0);
				argStr = StringHelpers.addBrackets(argStr);
			}
			if (referedOp.getName().equals("not")) {
				result = "!" + source;
			} else if (referedOp.getName().equals("or")) {
				result = source.toString() + " || " + argStr;
			} else if (referedOp.getName().equals("xor")) {
				result = source.toString() + " ^ " + argStr;
			} else if (referedOp.getName().equals("and")) {
				result = source.toString() + " && " + argStr;
			} else if (referedOp.getName().equals("implies")) {
				result = "(" + source + " ? " + args.get(0) + " : true)";
			} else if (referedOp.getName().equals("=")) {
				result = source + " == " + argStr;
			} else if (referedOp.getName().equals("<>")) {
				result = source + " != " + argStr;
			} else if (referedOp.getName().equals("asBag")) {
				myClass.addToImports(OclUtilityCreator.getStdlibPath());
				result = OclUtilityCreator.getStdlibPath().getLast() + ".boolAsBag(" + source + ")";
			} else if (referedOp.getName().equals("asSequence")) {
				myClass.addToImports(OclUtilityCreator.getStdlibPath());
				result = OclUtilityCreator.getStdlibPath().getLast() + ".boolAsSequence(" + source + ")";
			} else if (referedOp.getName().equals("asOrderedSet")) {
				myClass.addToImports(OclUtilityCreator.getStdlibPath());
				result = OclUtilityCreator.getStdlibPath().getLast() + ".boolAsOrderedSet(" + source + ")";
			} else if (referedOp.getName().equals("asSet")) {
				myClass.addToImports(OclUtilityCreator.getStdlibPath());
				result = OclUtilityCreator.getStdlibPath().getLast() + ".boolAsSet(" + source + ")";
			} else if (referedOp.getName().equals("toString")) {
				result = "new Boolean(" + source + ").toString()";
			} else if (referedOp.getName().equals("oclIsUndefined")) {
				result = "false";
				// TODO better error message mechanism
				System.err.println("Operation oclIsUndefined called for a Boolean value. This always results in 'false'.");
			} else {
				System.err.println("unspecified option in BasicTypeOperCallGenerator.booleanOperCall:");
				System.err.println("\t'" + referedOp.getName() + "'");
			}
		}
		return result;
	}

	private String numericOperCall(OperationCallExp exp, String source, List args, IOperation referedOp, List params) {
		String result = "";
		String argStr = "";
		if (referedOp != null) {
			if (!args.isEmpty()) {
				argStr = (String) args.get(0);
				argStr = StringHelpers.addBrackets(argStr);
			}
			if (referedOp.getName().equals("div")) {
				result = source + "d / " + argStr;
			} else if (referedOp.getName().equals("mod")) {
				result = source + " % " + argStr;
			} else if (referedOp.getName().equals("abs")) {
				result = "Math.abs(" + source + ")";
			} else if (referedOp.getName().equals("max")) {
				result = "Math.max(" + source  + ", " + argStr + ")";
			} else if (referedOp.getName().equals("min")) {
				result = "Math.min(" + source  + ", " + argStr + ")";
			} else if (referedOp.getName().equals("floor")) {
				result = "Math.floor(" + source + ")";
			} else if (referedOp.getName().equals("round")) {
				result = "Math.round(" + source + ")";
			} else if (referedOp.getName().equals("oclAsType")) {
				IClassifier argType = ((IOclTypeLiteralExp)exp.getArguments().get(0)).getReferredClassifier();
				ClassifierMap mapper = new ClassifierMap(argType);
				String typeStr = mapper.javaType();
				result = "(" + typeStr +  ") " + source;
			} else if (referedOp.getName().equals("=")) {
				result = result + source + " == " + argStr;
			} else if (referedOp.getName().equals(">=")) {
				result = result + source + " >= " + argStr;
			} else if (referedOp.getName().equals("<=")) {
				result = result + source + " <= " + argStr;
			} else if (referedOp.getName().equals("<>")) {
				result = result + source + " != " + argStr;
			} else if (referedOp.getName().equals("<")) {
				result = result + source + " < " + argStr;
			} else if (referedOp.getName().equals(">")) {
				result = result + source + " > " + argStr;
			} else if (referedOp.getName().equals("+")) {
				source = StringHelpers.addBrackets(source);
				result = result + source + " + " + argStr;
			} else if (referedOp.getName().equals("*")) {
				source = StringHelpers.addBrackets(source);
				result = result + source + " * " + argStr;
			} else if (referedOp.getName().equals("/")) {
				source = StringHelpers.addBrackets(source);
				result = result + source + "d / " + argStr;
			} else if (referedOp.getName().equals("-") ){
				if (referedOp.isPrefix() ) {
					String sourceStr = source;
					sourceStr = StringHelpers.addBrackets(sourceStr);
					result = "-" + sourceStr;
				} else {
					result = source + " - " + argStr;
				}
			} else if (referedOp.getName().equals("oclIsUndefined")) {
				result = "false";
				// TODO better error message mechanism
				System.err.println("Operation oclIsUndefined called for an Integer value. This always results in 'false'.");
			} else {
				System.err.println("unspecified option in BasicTypeOperCallGenerator.numericOperCall:");
				System.err.println("\t" + referedOp.getName());
			}
		}
		return result;
	}	

	private String stringOperCall(OperationCallExp exp, String source, List args, IOperation referedOp, List params) {
		String result = "";
		String argStr = "";
		if (referedOp != null) {
			if (!args.isEmpty()) {
				argStr = (String) args.get(0);
				argStr = StringHelpers.addBrackets(argStr);
			}
			if (referedOp.getName().equals("size")) {
				source = StringHelpers.addBrackets(source);
				result = source + ".length()";
			} else if (referedOp.getName().equals("concat")) {
				source = StringHelpers.addBrackets(source);
				result = source + ".concat(" + argStr + ")";
			} else if (referedOp.getName().equals("+")) {
				source = StringHelpers.addBrackets(source);
				result = source + " + " + argStr;
			} else if (referedOp.getName().equals("toInteger")) {
				result = "Integer.parseInt(" + source + ")";
			} else if (referedOp.getName().equals("toReal")) {
				result = "Float.parseFloat(" + source + ")";
			} else if (referedOp.getName().equals("toUpper")) {
				source = StringHelpers.addBrackets(source);
				result = source + ".toUpperCase()";
			} else if (referedOp.getName().equals("toLower")) {
				source = StringHelpers.addBrackets(source);
				result = source + ".toLowerCase()";
			} else if (referedOp.getName().equals("substring")) {
				source = StringHelpers.addBrackets(source);
				String argStr2 = (String) args.get(1);
				argStr2 = StringHelpers.addBrackets(argStr2);
				result = source + ".substring(" + argStr + "-1, " + argStr2 + ")";
			} else if (referedOp.getName().equals("replaceAll")) {
				source = StringHelpers.addBrackets(source);
				String argStr2 = (String) args.get(1);
				argStr2 = StringHelpers.addBrackets(argStr2);
				result = source + ".replaceAll(" + argStr + ", " + argStr2 + ")";
			} else if (referedOp.getName().equals("=")) {
				source = StringHelpers.addBrackets(source);
				result = source + ".equals(" + argStr + ")";
			} else if (referedOp.getName().equals("<>")) {
				source = StringHelpers.addBrackets(source);
				result = "!" + source + ".equals(" + argStr + ")";
			} else if (referedOp.getName().equals("asBag")) {
				myClass.addToImports(OclUtilityCreator.getStdlibPath());
				result = OclUtilityCreator.getStdlibPath().getLast() + ".stringAsBag(" + source + ")";
			} else if (referedOp.getName().equals("asSequence")) {
				myClass.addToImports(OclUtilityCreator.getStdlibPath());
				result = OclUtilityCreator.getStdlibPath().getLast() + ".stringAsSequence(" + source + ")";
			} else if (referedOp.getName().equals("asOrderedSet")) {
				myClass.addToImports(OclUtilityCreator.getStdlibPath());
				result = OclUtilityCreator.getStdlibPath().getLast() + ".stringAsOrderedSet(" + source + ")";
			} else if (referedOp.getName().equals("asSet")) {
				myClass.addToImports(OclUtilityCreator.getStdlibPath());
				result = OclUtilityCreator.getStdlibPath().getLast() + ".stringAsSet(" + source + ")";
			} else if (referedOp.getName().equals("oclIsUndefined")) {
				result = StringHelpers.addBrackets(source) + " == null";
			} else {
				System.err.println("unspecified option in BasicTypeOperCallGenerator.stringOperCall:");
				System.err.println("\t'" + referedOp.getName() + "'");
			}
		}
		return result;
	}


}
