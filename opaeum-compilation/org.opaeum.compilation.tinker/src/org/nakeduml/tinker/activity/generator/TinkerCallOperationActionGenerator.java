package org.nakeduml.tinker.activity.generator;

import java.util.ArrayList;
import java.util.List;

import nl.klasse.octopus.model.ParameterDirectionKind;

import org.nakeduml.tinker.activity.TinkerActivityPhase;
import org.nakeduml.tinker.activity.maps.ConcreteEmulatedClassifier;
import org.nakeduml.tinker.activity.maps.TinkerActivityNodeMapFactory;
import org.nakeduml.tinker.activity.maps.TinkerStructuralFeatureMap;
import org.nakeduml.tinker.generator.TinkerAttributeImplementor;
import org.nakeduml.tinker.generator.TinkerBehaviorUtil;
import org.nakeduml.tinker.generator.TinkerGenerationUtil;
import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitBefore;
import org.opaeum.java.metamodel.OJIfStatement;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.javageneration.maps.NakedStructuralFeatureMap;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.metamodel.actions.INakedCallOperationAction;
import org.opaeum.metamodel.activities.INakedInputPin;
import org.opaeum.metamodel.activities.INakedOutputPin;
import org.opaeum.metamodel.activities.INakedParameterNode;
import org.opaeum.metamodel.activities.INakedPin;
import org.opaeum.metamodel.commonbehaviors.INakedBehavior;
import org.opaeum.metamodel.core.INakedElement;
import org.opaeum.metamodel.core.INakedEnumeration;
import org.opaeum.metamodel.core.INakedParameter;
import org.opaeum.metamodel.core.INakedProperty;
import org.opaeum.metamodel.core.INakedSimpleType;

@StepDependency(phase = TinkerActivityPhase.class, requires = { TinkerCallActionGenerator.class }, after = { TinkerCallActionGenerator.class })
public class TinkerCallOperationActionGenerator extends AbstractTinkerActivityNodeGenerator {

	@VisitBefore(matchSubclasses = false, match = { INakedCallOperationAction.class })
	public void visitCallOperationAction(INakedCallOperationAction oa) {
		OJAnnotatedClass actionClass = findJavaClassForActivityNode(oa);
		actionClass.setSuperclass(TinkerBehaviorUtil.tinkerCallOperationActionPathName.getCopy());
		actionClass.addToImports(TinkerBehaviorUtil.tinkerControlTokenPathName);
		addCallOperationActionGetTarget(actionClass, oa);
		implementExecute(actionClass, oa);
		implementGetOperation(actionClass, oa);
		implementCopyOutParametersToOutputPins(actionClass, oa);
		implementGetBehavior(actionClass, oa);
	}

	private void implementGetBehavior(OJAnnotatedClass actionClass, INakedCallOperationAction oa) {
		if (!oa.getOperation().getMethods().isEmpty()) {
			INakedBehavior method = oa.getOperation().getMethods().iterator().next();
			ConcreteEmulatedClassifier activityClassifier = new ConcreteEmulatedClassifier(oa.getNameSpace(), oa);
			TinkerStructuralFeatureMap map = TinkerActivityNodeMapFactory.getNodeToActivityAssociationMap(activityClassifier, method, oa);
			TinkerAttributeImplementor tinkerAttributeImplementor = new TinkerAttributeImplementor();
			tinkerAttributeImplementor.setJavaModel(this.javaModel);
			tinkerAttributeImplementor.implementAttributeFully(activityClassifier, map);

			OJAnnotatedOperation getBehavior = new OJAnnotatedOperation("getBehavior");
			TinkerGenerationUtil.addOverrideAnnotation(getBehavior);
			getBehavior.getBody().addToStatements("return " + map.getter() + "()");
			getBehavior.setReturnType(map.javaBaseTypePath());
			actionClass.addToOperations(getBehavior);
		} else {
			OJAnnotatedOperation getBehavior = new OJAnnotatedOperation("getBehavior");
			TinkerGenerationUtil.addOverrideAnnotation(getBehavior);
			getBehavior.getBody().addToStatements("return null");
			getBehavior.setReturnType(TinkerBehaviorUtil.tinkerAbstractActivityPathName.getCopy());
			actionClass.addToOperations(getBehavior);
		}
	}

	private void implementCopyOutParametersToOutputPins(OJAnnotatedClass actionClass, INakedCallOperationAction oa) {
		OJAnnotatedOperation copyOutParametersToOutputPins = new OJAnnotatedOperation("copyOutParametersToOutputPins");
		TinkerGenerationUtil.addOverrideAnnotation(copyOutParametersToOutputPins);
		actionClass.addToOperations(copyOutParametersToOutputPins);

		if (!oa.getOperation().getMethods().isEmpty()) {
			INakedBehavior method = oa.getOperation().getMethods().iterator().next();
			List<INakedParameterNode> parameterNodes = getParameterNodes(method);

			int paramCount = 0;
			for (INakedParameterNode parameterNode : parameterNodes) {
				if (parameterNode.getOutgoing().isEmpty()
						&& (parameterNode.getParameter().isReturn() || parameterNode.getParameter().getDirection() == ParameterDirectionKind.OUT || parameterNode.getParameter()
								.getDirection() == ParameterDirectionKind.INOUT)) {

					INakedOutputPin pin = findCorrespondingOutputPin(parameterNode.getParameter(), paramCount++, oa.getResult());
					TinkerStructuralFeatureMap pinMap = TinkerActivityNodeMapFactory.get(pin);

					ConcreteEmulatedClassifier activityClassifier = new ConcreteEmulatedClassifier(oa.getActivity().getNameSpace(), oa.getActivity());
					TinkerStructuralFeatureMap methodParameterNodeMap = TinkerActivityNodeMapFactory.getNodeToActivityAssociationMap(activityClassifier, parameterNode);

					NakedStructuralFeatureMap parameterMap = OJUtil.buildStructuralFeatureMap(activityClassifier, parameterNode.getParameter());
					if (pin.getNakedMultiplicity().isOne()) {
						actionClass.addToImports(TinkerBehaviorUtil.tinkerSingleObjectToken.getCopy());

						copyOutParametersToOutputPins.getBody().addToStatements(
								pinMap.getter() + "().addIncomingToken(new SingleObjectToken<" + parameterMap.javaBaseTypePath().getLast() + ">(" + pinMap.getter()
										+ "().getName(), getBehavior()." + methodParameterNodeMap.getter() + "().getReturnParameterValues().get(0)))");
					} else {
						actionClass.addToImports(TinkerBehaviorUtil.tinkerCollectionObjectTokenInteratorPathName.getCopy());

						copyOutParametersToOutputPins.getBody().addToStatements(
								pinMap.getter() + "().addIncomingToken(new CollectionObjectTokenInterator<" + parameterMap.javaBaseTypePath().getLast() + ">(" + pinMap.getter()
										+ "().getName(), getBehavior()." + methodParameterNodeMap.getter() + "().getReturnParameterValues()))");
					}
				}
			}
		}

	}

	private void implementGetOperation(OJAnnotatedClass actionClass, INakedCallOperationAction oa) {

	}

	private void implementExecute(OJAnnotatedClass actionClass, INakedCallOperationAction oa) {
		if (oa.getOperation().getReturnParameter() != null && !oa.getOperation().getReturnParameter().isReturn()) {
			throw new IllegalStateException("A return parameter with isReturn as false does not make sense over here. For operation " + oa.getOperation().getName());
		}

		OJAnnotatedOperation execute = new OJAnnotatedOperation("execute");
		TinkerGenerationUtil.addOverrideAnnotation(execute);
		execute.setReturnType(new OJPathName("boolean"));
		if (!oa.getOperation().getMethods().isEmpty()) {
			INakedBehavior method = oa.getOperation().getMethods().iterator().next();
			OJPathName activityPathName = OJUtil.classifierPathname(method);
			actionClass.addToImports(activityPathName.getCopy());
			execute.getBody().addToStatements(activityPathName.getLast() + " a = new " + activityPathName.getLast() + "(getTarget().getValue())");

			ConcreteEmulatedClassifier activityClassifier = new ConcreteEmulatedClassifier(oa.getNameSpace(), oa);
			TinkerStructuralFeatureMap mapA = TinkerActivityNodeMapFactory.getNodeToActivityAssociationMap(activityClassifier, method, oa);
			execute.getBody().addToStatements(mapA.setter() + "(a)");
			execute.getBody().addToStatements("a.setCallAction(this)");

			// //////////////////////////////////////////

			List<INakedInputPin> arguments = oa.getArguments();
			List<INakedOutputPin> results = oa.getResult();
			ConcreteEmulatedClassifier concreteEmulatedClassifier = new ConcreteEmulatedClassifier(oa.getNameSpace(), oa);

			if (method.getOwnedParameters().size() != oa.getOperation().getOwnedParameters().size()) {
				throw new IllegalStateException("Operation parameters and behavior parameters do not match up");
			}
			String executeParams = "";
			boolean first = true;
			int paramCount = 0;
			for (INakedParameter param : oa.getOperation().getOwnedParameters()) {
				INakedPin pin;
				if (param.getDirection() == ParameterDirectionKind.IN || param.getDirection() == ParameterDirectionKind.INOUT) {
					pin = findCorrespondingInputPin(param, paramCount++, arguments);
					if (pin == null) {
						throw new IllegalStateException("Could not find a inputpin for in parameter " + param.getName() + " for operation " + oa.getOperation().getName()
								+ " in CallOperationAction " + oa.getName());
					}
				} else {
					// Out parameter
					pin = findCorrespondingOutputPin(param, paramCount++, results);
					if (pin == null) {
						throw new IllegalStateException("Could not find a outputpin for out parameter " + param.getName() + " for operation " + oa.getOperation().getName()
								+ " in CallOperationAction " + oa.getName());
					}
				}
				if (!param.isReturn()) {
					TinkerStructuralFeatureMap map = TinkerActivityNodeMapFactory.getPinVariableInActionAssociationMap(concreteEmulatedClassifier, pin);
					if (!first) {
						executeParams += ", ";
					}
					first = false;
					executeParams += map.fieldname();
				}
			}

			if (oa.getOperation().getReturnParameter() != null && oa.getOperation().getReturnParameter().isReturn()) {
				NakedStructuralFeatureMap map = OJUtil.buildStructuralFeatureMap(oa.getOperation().getOwner(), oa.getOperation().getReturnParameter());
				execute.getBody().addToStatements(map.javaBaseTypePath().getLast() + " result = a.execute(" + executeParams + ")");
				actionClass.addToImports(map.javaBaseTypePath());
			} else {
				execute.getBody().addToStatements("a.execute(" + executeParams + ")");
			}

			execute.getBody().addToStatements("return a.isFinished()");
		} else {
			// TODO
			execute.getBody().addToStatements("getTarget().getValue()." + oa.getOperation().getName() + "()");
			execute.getBody().addToStatements("return true");
		}

		actionClass.addToOperations(execute);
	}

	private INakedInputPin findCorrespondingInputPin(INakedParameter param, int i, List<INakedInputPin> arguments) {
		int count = 0;
		for (INakedInputPin iNakedOutputPin : arguments) {
			if (i == count++) {
				return iNakedOutputPin;
			}
		}
		return null;
	}

	private INakedOutputPin findCorrespondingOutputPin(INakedParameter param, int paramCount, List<INakedOutputPin> arguments) {
		int count = 0;
		for (INakedOutputPin iNakedOutputPin : arguments) {
			if (paramCount == count++) {
				return iNakedOutputPin;
			}
		}
		return null;
	}

	private void addCallOperationActionGetTarget(OJAnnotatedClass actionClass, INakedCallOperationAction oa) {
		OJAnnotatedOperation getTarget = new OJAnnotatedOperation("getTarget");
		TinkerGenerationUtil.addOverrideAnnotation(getTarget);
		INakedInputPin target = oa.getTarget();

		TinkerStructuralFeatureMap map = TinkerActivityNodeMapFactory.get(target);
		getTarget.setReturnType(map.javaBaseTypePath());
		getTarget.getBody().addToStatements("return " + map.getter() + "()");

		actionClass.addToOperations(getTarget);
	}

	private List<INakedParameterNode> getParameterNodes(INakedBehavior method) {
		List<INakedParameterNode> result = new ArrayList<INakedParameterNode>();
		for (INakedElement e : method.getOwnedElements()) {
			if (e instanceof INakedParameterNode) {
				result.add((INakedParameterNode) e);
			}
		}
		return result;
	}
}
