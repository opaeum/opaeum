/*
 * Created on Jun 14, 2004
 *
 * Copyright Klasse Objecten
 */
package nl.klasse.octopus.codegen.umlToJava.expgenerators.creators;

import java.util.List;

import nl.klasse.octopus.codegen.umlToJava.expgenerators.visitors.OclUtilityCreator;

import org.eclipse.ocl.uml.OperationCallExp;
import org.eclipse.uml2.uml.Operation;
import org.opaeum.java.metamodel.OJClass;

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
	@SuppressWarnings("rawtypes") 
	public String makeOperCall(OperationCallExp exp, String source, List args, Operation referedOp, List params) {
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
