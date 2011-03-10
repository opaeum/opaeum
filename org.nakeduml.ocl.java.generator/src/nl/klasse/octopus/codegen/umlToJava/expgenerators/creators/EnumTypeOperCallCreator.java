/*
 * Created on Jun 14, 2004
 *
 * Copyright Klasse Objecten
 */
package nl.klasse.octopus.codegen.umlToJava.expgenerators.creators;

import java.util.List;

import nl.klasse.octopus.codegen.umlToJava.expgenerators.visitors.OclUtilityCreator;
import nl.klasse.octopus.expressions.internal.types.OperationCallExp;
import nl.klasse.octopus.model.IOperation;

import org.nakeduml.java.metamodel.OJClass;

/**
 * BasisOperCallGenerator : 
 */
public class EnumTypeOperCallCreator {
	private OJClass myClass = null;

	/**
	 * 
	 */
	public EnumTypeOperCallCreator(OJClass myClass) {
		super();
		this.myClass = myClass;
	}

	public String makeOperCall(OperationCallExp exp, String source, List args, IOperation referedOp, List params) {
		String result = "";
		String argStr = (String) args.get(0);
		if (referedOp != null) {
			if (referedOp.getName().equals("=")) {
				result = source + ".equals( " + argStr + ")";
			} else if (referedOp.getName().equals("<>")) {
				result = "!" + source + ".equals( " + argStr + ")";
			} else if (referedOp.getName().equals("asBag")) {
				myClass.addToImports(OclUtilityCreator.getStdlibPath());
				result = OclUtilityCreator.getStdlibPath().getLast() + ".objectAsBag(" + source + ")";
			} else if (referedOp.getName().equals("asSequence")) {
				myClass.addToImports(OclUtilityCreator.getStdlibPath());
				result = OclUtilityCreator.getStdlibPath().getLast() + ".objectAsSequence(" + source + ")";
			} else if (referedOp.getName().equals("asOrderedSet")) {
				myClass.addToImports(OclUtilityCreator.getStdlibPath());
				result = OclUtilityCreator.getStdlibPath().getLast() + ".objectAsOrderedSet(" + source + ")";
			} else if (referedOp.getName().equals("asSet")) {
				myClass.addToImports(OclUtilityCreator.getStdlibPath());
				result = OclUtilityCreator.getStdlibPath().getLast() + ".objectAsSet(" + source + ")";
			} else {
				System.err.println("unspecified option in EnumTypeOperCallGenerator.makeOperCall:");
				System.err.println("\t" + referedOp.getName());
			}
		}
		return result;
	}
}
