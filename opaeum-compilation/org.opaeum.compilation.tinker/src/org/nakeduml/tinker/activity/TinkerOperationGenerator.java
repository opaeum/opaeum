package org.nakeduml.tinker.activity;

import nl.klasse.octopus.model.ParameterDirectionKind;

import org.eclipse.uml2.uml.INakedBehavior;
import org.eclipse.uml2.uml.INakedClassifier;
import org.eclipse.uml2.uml.INakedOperation;
import org.eclipse.uml2.uml.INakedParameter;
import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitBefore;
import org.opaeum.java.metamodel.OJIfStatement;
import org.opaeum.java.metamodel.OJParameter;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.javageneration.StereotypeAnnotator;
import org.opaeum.javageneration.maps.NakedOperationMap;
import org.opaeum.javageneration.maps.NakedStructuralFeatureMap;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.name.NameConverter;

@StepDependency(phase = TinkerActivityPhase.class, requires = { TinkerActivityNodeGenerator.class }, after = { TinkerActivityNodeGenerator.class })
public class TinkerOperationGenerator extends StereotypeAnnotator {

	@VisitBefore(matchSubclasses = true, match = { INakedOperation.class })
	public void visitOperation(INakedOperation oper) {
		NakedOperationMap map = OJUtil.buildOperationMap(oper);
		INakedClassifier owner = oper.getOwner();
		OJAnnotatedClass ownerClass = findJavaClass(owner);
		OJAnnotatedOperation operation = new OJAnnotatedOperation(oper.getName());
		operation.setReturnType(map.javaReturnTypePath());
		ownerClass.addToOperations(operation);
		
		
		for (INakedParameter param : oper.getOwnedParameters()) {
			if (param.getDirection() == ParameterDirectionKind.IN || param.getDirection() == ParameterDirectionKind.INOUT) {
				OJParameter ojParameter = new OJParameter(param.getName(), OJUtil.classifierPathname(param.getNakedBaseType()));
				operation.addToParameters(ojParameter);
			}
		}
		
		for (INakedBehavior method : oper.getMethods()) {
			NakedStructuralFeatureMap compositeMap = OJUtil.buildStructuralFeatureMap(method.getEndToComposite().getOtherEnd());
			OJPathName activityPathName = OJUtil.classifierPathname(method);
			operation.getBody().addToStatements(activityPathName.getLast() + " " + NameConverter.decapitalize(method.getName()) + " = new " + activityPathName.getLast() + "(this)");
			
			if (method.getArgumentParameters().size() != oper.getOwnedParameters().size()) {
				throw new IllegalStateException("Operation parameters and behavior parameters do not match up");
			}
			int count = 0;
			for (INakedParameter param : method.getArgumentParameters()) {
				if (param.getDirection() == ParameterDirectionKind.IN || param.getDirection() == ParameterDirectionKind.INOUT) {
					operation.getBody().addToStatements(NameConverter.decapitalize(method.getName())+ ".set" + NameConverter.capitalize(param.getName()) + "(" + oper.getOwnedParameters().get(count).getName() + ")");
				}
				count++;
			}
			operation.getBody().addToStatements(NameConverter.decapitalize(method.getName()) + ".execute()");
			OJIfStatement ifFinished = new OJIfStatement("!" + NameConverter.decapitalize(method.getName()) + ".isFinished()");
			ifFinished.addToThenPart(compositeMap.adder() + "(" + NameConverter.decapitalize(method.getName()) + ")");
			operation.getBody().addToStatements(ifFinished);
		}
	}
	
}
