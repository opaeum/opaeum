package nl.klasse.octopus.codegen.umlToJava.expgenerators.creators;

import java.util.List;

import nl.klasse.octopus.codegen.umlToJava.maps.ClassifierMap;
import nl.klasse.octopus.codegen.umlToJava.maps.NavToAssocClassMap;
import nl.klasse.octopus.codegen.umlToJava.maps.StructuralFeatureMap;
import nl.klasse.tools.common.StringHelpers;

import org.eclipse.ocl.uml.AssociationClassCallExp;
import org.eclipse.ocl.uml.CallExp;
import org.eclipse.ocl.uml.FeatureCallExp;
import org.eclipse.ocl.uml.IterateExp;
import org.eclipse.ocl.uml.IteratorExp;
import org.eclipse.ocl.uml.LoopExp;
import org.eclipse.ocl.uml.OperationCallExp;
import org.eclipse.ocl.uml.PropertyCallExp;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Property;
import org.opaeum.java.metamodel.OJClass;
import org.opaeum.java.metamodel.OJParameter;

public class PropCallCreator {
	private OJClass myClass = null;

	public PropCallCreator(OJClass myClass) {
		super();
		this.myClass = myClass;
	}


	public StringBuffer makeExpression(CallExp in, StringBuffer source,
			boolean isStatic, List<OJParameter> params) {
		StringBuffer thisNode = new StringBuffer();
		StringBuffer newSource = privMakeExpNode(in, source, isStatic, params);
		thisNode.append(newSource);
		return thisNode;
	}

	/**
	 * Is called only when in is a reference to a class property.
	 * 
	 * @param in
	 * @return
	 */
	public StringBuffer makeExpressionNode(CallExp in, boolean isStatic,
			List<OJParameter> params) {
		// TODO precondition
		return privMakeExpNode(in, null, isStatic, params);
	}

	private StringBuffer privMakeExpNode(CallExp in, StringBuffer source,
			boolean isStatic, List<OJParameter> params) {
		StringBuffer newSource = new StringBuffer();
		if (in instanceof LoopExp) {
			if (in instanceof IterateExp) {
				LoopExpCreator maker = new LoopExpCreator(myClass);
				newSource.append(maker.iterateExp((IterateExp) in, source,
						isStatic, params));
			} else if (in instanceof IteratorExp) {
				LoopExpCreator maker = new LoopExpCreator(myClass);
				newSource.append(maker.iteratorExp((IteratorExp) in, source,
						isStatic, params));
			}
		} else if (in instanceof FeatureCallExp) {
			if (in instanceof PropertyCallExp) {
				newSource
						.append(attributeCallExp((PropertyCallExp) in, source));
			} else if (in instanceof PropertyCallExp) {
				newSource.append(associationEndCallExp((PropertyCallExp) in,
						source));
			} else if (in instanceof AssociationClassCallExp) {
				newSource.append(associationClassCallExp(
						(AssociationClassCallExp) in, source));
			} else if (in instanceof OperationCallExp) {
				OperationCallCreator maker = new OperationCallCreator(myClass);
				newSource.append(maker.operationCallExp((OperationCallExp) in,
						source, isStatic, params));
			}
		} else {
			// error, no other subtypes of PropertyCallExp
			System.err
					.println("unspecified option in PropCallGenerator.privMakeExpNode:");
			System.err.println("\t" + "type is " + in.getClass() + ", "
					+ in.getQualifiedName());
		}
		return newSource;
	}

	private String attributeCallExp(PropertyCallExp exp, StringBuffer source) {
		StructuralFeatureMap mapper = new StructuralFeatureMap(
				exp.getReferredProperty());
		String getterName = mapper.getter();
		if (exp.getReferredProperty().isStatic() || source == null) {
			ClassifierMap classmap = new ClassifierMap((Classifier) exp
					.getReferredProperty().getOwner());
			String classname = classmap.javaType();
			myClass.addToImports(classmap.javaTypePath());
			return classname + "." + getterName + "()";
		} else {
			String sourceStr = StringHelpers.addBrackets(source.toString());
			return sourceStr + "." + getterName + "()";
		}
	}

	private String associationEndCallExp(PropertyCallExp exp,
			StringBuffer source) {
		StructuralFeatureMap mapper = new StructuralFeatureMap(
				exp.getReferredProperty());
		String sourceStr = StringHelpers.addBrackets(source.toString());
		return sourceStr + "." + mapper.getter() + "()";
	}

	private String associationClassCallExp(AssociationClassCallExp exp,
			StringBuffer source) {
		Property navSource = exp.getNavigationSource();
		NavToAssocClassMap mapper = new NavToAssocClassMap(navSource);
		String sourceStr = StringHelpers.addBrackets(source.toString());
		return sourceStr + "." + mapper.getter() + "()";
	}
}
