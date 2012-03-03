package org.nakeduml.tinker.activity;

import nl.klasse.octopus.model.ParameterDirectionKind;

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
import org.opaeum.metamodel.commonbehaviors.INakedBehavior;
import org.opaeum.metamodel.core.INakedClassifier;
import org.opaeum.metamodel.core.INakedOperation;
import org.opaeum.metamodel.core.INakedParameter;
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
				OJParameter p = new OJParameter();
				NakedStructuralFeatureMap pMap = OJUtil.buildStructuralFeatureMap(owner, param);
				p.setName(pMap.fieldname());
				p.setType(pMap.javaTypePath());
				operation.addToParameters(p);
				operation.getOwner().addToImports(pMap.javaTypePath());
			}
		}
		
		for (INakedBehavior method : oper.getMethods()) {
			NakedStructuralFeatureMap compositeMap = OJUtil.buildStructuralFeatureMap(method.getEndToComposite().getOtherEnd());
			OJPathName activityPathName = OJUtil.classifierPathname(method);
			operation.getBody().addToStatements(activityPathName.getLast() + " " + NameConverter.decapitalize(method.getName()) + " = new " + activityPathName.getLast() + "(this)");
			
			if (method.getArgumentParameters().size() != oper.getOwnedParameters().size()) {
				throw new IllegalStateException("Operation parameters and behavior parameters do not match up");
			}
			String executeParams = "";
			boolean first = true;
			for (INakedParameter param : oper.getArgumentParameters()) {
				NakedStructuralFeatureMap pMap = OJUtil.buildStructuralFeatureMap(owner, param);
				if (!first) {
					executeParams += ", ";
					first = false;
				}
				executeParams += pMap.fieldname();
			}
			operation.getBody().addToStatements(NameConverter.decapitalize(method.getName()) + ".execute("+executeParams+")");
			OJIfStatement ifFinished = new OJIfStatement(NameConverter.decapitalize(method.getName()) + ".isFinished()");
			ifFinished.addToThenPart(NameConverter.decapitalize(method.getName()) + ".markDeleted()");
			operation.getBody().addToStatements(ifFinished);
		}
	}
	
}
