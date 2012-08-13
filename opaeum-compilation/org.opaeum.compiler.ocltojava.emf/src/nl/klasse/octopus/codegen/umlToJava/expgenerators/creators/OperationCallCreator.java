package nl.klasse.octopus.codegen.umlToJava.expgenerators.creators;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import nl.klasse.octopus.codegen.helpers.CommonNames;
import nl.klasse.octopus.codegen.umlToJava.expgenerators.visitors.OclUtilityCreator;
import nl.klasse.octopus.codegen.umlToJava.maps.ClassifierMap;
import nl.klasse.octopus.codegen.umlToJava.maps.OperationMap;
import nl.klasse.octopus.codegen.umlToJava.maps.StateMap;
import nl.klasse.tools.common.Check;
import nl.klasse.tools.common.StringHelpers;
import nl.klasse.tools.common.Util;

import org.eclipse.ocl.uml.OCLExpression;
import org.eclipse.ocl.uml.OperationCallExp;
import org.eclipse.ocl.uml.StateExp;
import org.eclipse.ocl.uml.TypeExp;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Enumeration;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.PrimitiveType;
import org.eclipse.uml2.uml.State;
import org.opaeum.eclipse.EmfOperationUtil;
import org.opaeum.java.metamodel.OJClass;
import org.opaeum.java.metamodel.OJField;
import org.opaeum.java.metamodel.OJParameter;

@SuppressWarnings("rawtypes")
public class OperationCallCreator {
	private OJClass myClass = null;

	public OperationCallCreator(OJClass myClass) {
		super();
		this.myClass = myClass;
	}

	public String operationCallExp(OperationCallExp exp, StringBuffer source,
			boolean isStatic, List<OJParameter> params) {
		String result = "";
		List<String> args = makeArgs(exp, isStatic, params);
		Operation referedOp = exp.getReferredOperation();
		Classifier sourceType = (exp.getSource() == null ? null : exp
				.getSource().getType());
		if (sourceType == null) {
			if (referedOp.getName().equals("allInstances")) { // on OclAny
				result = buildAllInstances(exp, source, args, referedOp);
			} else {
				result = buildClassOp(args, referedOp);
			}
		} else if (exp.getReferredOperation().getUpper() == -1
				|| exp.getReferredOperation().getUpper() > 1) {
			CollectionOperCallCreator maker1 = new CollectionOperCallCreator(
					myClass);
			result = maker1.collectionOperCall(exp, source.toString(), args,
					referedOp, isStatic, params);
		} else if (sourceType instanceof PrimitiveType) {
			BasicTypeOperCallCreator maker2 = new BasicTypeOperCallCreator(
					myClass);
			result = maker2.makeOperCall(exp, source.toString(), args,
					referedOp, params);
		} else if (sourceType instanceof Enumeration) {
			EnumTypeOperCallCreator maker3 = new EnumTypeOperCallCreator(
					myClass);
			result = maker3.makeOperCall(exp, source.toString(), args,
					referedOp, params);
		} else {
			result = makeOperCall(exp, source, args, referedOp, params);
		}
		return result;
	}

	private String buildClassOp(List args, Operation referedOp) {
		ClassifierMap OWNER = new ClassifierMap(
				(Classifier) referedOp.getOwner());
		myClass.addToImports(OWNER.javaTypePath());
		String className = OWNER.javaType();
		OperationMap OPERATION = new OperationMap(referedOp);
		String opName = OPERATION.javaOperName();
		String arguments = Util.collectionToString(args, ", ");
		return className + "." + opName + "(" + arguments + ")";
	}

	private List<String> makeArgs(OperationCallExp exp, boolean isStatic,
			List<OJParameter> params) {
		List<String> result = new ArrayList<String>();
		Iterator it = exp.getArgument().iterator();
		while (it.hasNext()) {
			OCLExpression arg = (OCLExpression) it.next();
			ExpressionCreator myExpMaker = new ExpressionCreator(myClass);
			String expStr = myExpMaker.makeExpression(arg, isStatic, params);
			result.add(expStr);
		}
		return result;
	}

	private String makeOperCall(OperationCallExp exp, StringBuffer source,
			List args, Operation referedOp, List params) {
		String result = "";
		if (referedOp != null) {
			if (referedOp.getName().equals("oclIsNew")) { // on OclAny
				// TODO decide whether to implement OclIsNew
				result = source
						+ " /* no implementation of oclIsNew available */ ";
			} else if (referedOp.getName().equals("oclIsUndefined")) { // on
																		// OclAny
				result = buildIsUndefined(exp, source, args);
			} else if (referedOp.getName().equals("oclInState")
					|| referedOp.getName().equals("oclIsInState")) { // on
																		// OclAny
				result = buildOclInState(exp, source, args);
			} else if (referedOp.getName().equals("oclIsKindOf")) { // on OclAny
				result = buildIsKindOf(exp, source, args);
			} else if (referedOp.getName().equals("oclIsTypeOf")) { // on OclAny
				result = buildIsTypeOf(exp, source, args);
			} else if (referedOp.getName().equals("oclAsType")) { // on OclAny
				result = buildAsType(exp, source, args);
			} else if (referedOp.getName().equals("asSet")) { // on OclAny
				result = buildAsSet(exp, source.toString(), args, params);
			} else if (referedOp.getName().equals("=")) { // on OclAny
				OCLExpression xx = (OCLExpression) exp.getArgument().get(0);

				result = source + ".equals(" + args.get(0) + ")";

			} else if (referedOp.getName().equals("<>")) { // on OclAny
				OCLExpression xx = (OCLExpression) exp.getArgument().get(0);
				result = "!" + source + ".equals(" + args.get(0) + ")";
			} else {
				result = buildModelOp(exp, source.toString(), args, referedOp);
			}
		}
		return result;
	}

	private String buildAsSet(OperationCallExp exp, String source, List args,
			List params) {
		String result = "";
		myClass.addToImports(OclUtilityCreator.getStdlibPath());
		result = OclUtilityCreator.getStdlibPath().getTypeName() + "."
				+ "objectAsSet(" + source + ")";
		return result;
	}

	private String buildAllInstances(OperationCallExp exp, StringBuffer source,
			List args, Operation referedOp) {
		ClassifierMap OWNER = new ClassifierMap(
				(Classifier) referedOp.getOwner());
		String className = OWNER.javaType();
		OperationMap OPERATION = new OperationMap(referedOp);
		String opName = OPERATION.javaOperName(); // should be "allInstances"
		String arguments = Util.collectionToString(args, ", ");
		// indicate that this class needs the allInstances oper implemented
		if (myClass != null) {
			OJField ff = myClass
					.findField(CommonNames.USES_ALLINSTANCES_FIELD_NAME);
			if (ff != null)
				ff.setInitExp("true");
		}
		// return the operation call
		return className + "." + opName + "(" + arguments + ")";
	}

	private String buildIsUndefined(OperationCallExp exp, StringBuffer source,
			List args) {
		String result = "";
		result = StringHelpers.addBrackets(source.toString()) + " == null";
		return result;
	}

	private String buildAsType(OperationCallExp exp, StringBuffer source,
			List args) {
		Classifier argType = ((TypeExp) exp.getArgument().get(0))
				.getReferredType();
		String typeStr = new ClassifierMap(argType).javaType();
		String result = "((" + typeStr + ") "
				+ StringHelpers.addBrackets(source.toString()) + ")";
		return result;
	}

	private String buildIsKindOf(OperationCallExp exp, StringBuffer source,
			List args) {
		String result = "";
		// Classifier argType =
		// ((TypeExp)exp.getArgument().get(0)).getReferredType();
		// Classifier sourceType = exp.getSource().getType();
		// TODO check necessity!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		// if (!Conformance.conformsTo(sourceType, argType)) {
		// result = "false /* types of oclIsKindOf do not conform */";
		// } else {
		result = StringHelpers.addBrackets(source.toString()) + " instanceof "
				+ args.get(0);
		// }
		return result;
	}

	private String buildIsTypeOf(OperationCallExp exp, StringBuffer source,
			List args) {
		String result = "";
		result = StringHelpers.addBrackets(source.toString())
				+ ".getClass() == " + args.get(0) + ".class";
		return result;
	}

	/**
	 * @param exp
	 * @param source
	 * @param args
	 */
	private String buildOclInState(OperationCallExp exp, StringBuffer source,
			List args) {
		// exp.getReferedOp().getName() should equal 'oclInState'
		String result = "";
		StateExp arg = (StateExp) exp.getArgument().get(0);
		State usedState = arg.getReferredState();
		StateMap STATE = new StateMap(usedState);
		result = source + "." + STATE.getter() + "() == true";
		return result;
	}

	private String buildModelOp(OperationCallExp exp, String source, List args,
			Operation referedOp) {
		Check.pre("OperationCallTransformer.buildModelOp: source is empty",
				source != null && source.length() > 0);
		source = StringHelpers.addBrackets(source);
		String result = "";
		OperationMap OPERATION = new OperationMap(referedOp);
		String operationName = OPERATION.javaOperName();
		String arguments = Util.collectionToString(args, ", ");
		// if ( referedOp.isInfix() ) {
		// result = source + " " + operationName + " " +
		// StringHelpers.addBrackets(arguments);
		// } else
		// no infix model opers allowed: they are all transformed into normal
		// ops
		if (EmfOperationUtil.isPrefix(referedOp)) {
			source = StringHelpers.addBrackets(source);
			result = operationName + " " + source; // only one argument allowed
		} else {
			result = source + "." + operationName + "(" + arguments + ")";
		}
		return result;
	}
}