package net.sf.nakeduml.javageneration.jbpm5.statemachine;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.sf.nakeduml.feature.visit.VisitAfter;
import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.javageneration.NakedStateMap;
import net.sf.nakeduml.javageneration.basicjava.SimpleActivityMethodImplementor;
import net.sf.nakeduml.javageneration.jbpm5.AbstractJavaProcessVisitor;
import net.sf.nakeduml.javageneration.jbpm5.Jbpm5Util;
import net.sf.nakeduml.javageneration.oclexpressions.ValueSpecificationUtil;
import net.sf.nakeduml.metamodel.activities.INakedActivity;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedBehavior;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedOpaqueBehavior;
import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.metamodel.models.INakedModel;
import net.sf.nakeduml.metamodel.statemachines.INakedState;
import net.sf.nakeduml.metamodel.statemachines.INakedStateMachine;
import net.sf.nakeduml.metamodel.statemachines.INakedTransition;
import nl.klasse.octopus.model.IClassifier;
import nl.klasse.octopus.stdlib.IOclLibrary;

import org.nakeduml.java.metamodel.OJClass;
import org.nakeduml.java.metamodel.OJOperation;
import org.nakeduml.java.metamodel.OJPackage;
import org.nakeduml.java.metamodel.OJPathName;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedClass;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedOperation;
import org.nakeduml.runtime.domain.IProcessObject;
import org.nakeduml.runtime.domain.IProcessStep;

public class StateMachineImplementor extends AbstractJavaProcessVisitor {
	private OJAnnotatedClass javaStateMachine;

	@VisitAfter(matchSubclasses = true)
	public void visitModel(INakedModel m) {
	}

	@VisitBefore(matchSubclasses = true)
	public void state(INakedState state) {
		NakedStateMap map = new NakedStateMap(state);
		if (state.getKind().isRestingState()) {
			OJOperation getter = new OJAnnotatedOperation();
			getter.setReturnType(new OJPathName("boolean"));
			getter.setName("get" + state.getMappingInfo().getJavaName().getCapped());
			javaStateMachine.addToOperations(getter);
			if (state.getStateMachine().isClassifierBehavior()) {
				OJAnnotatedClass context = findJavaClass(state.getStateMachine().getContext());
				OJOperation copy = getter.getCopy();
				copy.getBody().addToStatements("return getClassifierBehavior()!=null && getClassifierBehavior()." + copy.getName() + "()");
				context.addToOperations(copy);
			}
			getter.getBody().addToStatements(
					"return isStepActive(" + javaStateMachine.getName() + "State." + Jbpm5Util.stepLiteralName(state) + ")");
		}
		
		implementMethodIfRequired(state, map.getOnEntryMethod(), state.getEntry());
		implementMethodIfRequired(state, map.getOnExitMethod(), state.getExit());
		implementMethodIfRequired(state, map.getDoActivityMethod(), state.getDoActivity());
	}

	private void implementMethodIfRequired(INakedState state, String methodName, INakedBehavior behavior) {
		if (behavior != null) {
			OJAnnotatedOperation onEntry = new OJAnnotatedOperation(methodName);
			javaStateMachine.addToOperations(onEntry);
			if (behavior instanceof INakedActivity) {
				SimpleActivityMethodImplementor impl = new SimpleActivityMethodImplementor();
				impl.initialize(javaModel, config, textWorkspace, super.transformationContext);
				impl.setWorkspace(workspace);
				impl.implementActivityOn((INakedActivity) behavior, onEntry);
			} else if (behavior instanceof INakedOpaqueBehavior) {
				INakedOpaqueBehavior b=(INakedOpaqueBehavior) behavior;
				IClassifier voidType = getOclEngine().getOclLibrary().lookupStandardType(IOclLibrary.OclVoidTypeName);
				onEntry.getBody().addToStatements(ValueSpecificationUtil.expressValue(onEntry, b.getBody(),state.getStateMachine(),voidType));
			}
		}
	};

	@VisitBefore(matchSubclasses = true)
	public void transition(INakedTransition transition) {
		if (transition.getGuard() != null && transition.getGuard().isValidOclValue() && transition.getSource().getKind().isChoice()) {
			OJOperation getter = new OJAnnotatedOperation();
			getter.setReturnType(new OJPathName("boolean"));
			getter.setName(Jbpm5Util.getGuardMethod(transition));
			javaStateMachine.addToOperations(getter);
			IClassifier booleanType = getOclEngine().getOclLibrary().lookupStandardType(IOclLibrary.BooleanTypeName);
			String expression = ValueSpecificationUtil.expressValue(getter, transition.getGuard(), transition.getStateMachine(),
					booleanType);
			getter.getBody().addToStatements("return " + expression);
		}
	};

	@VisitBefore(matchSubclasses = true)
	public void visitStateMachine(INakedStateMachine umlStateMachine) {
		javaStateMachine = findJavaClass(umlStateMachine);
		addImports(javaStateMachine);
		javaStateMachine.setName(umlStateMachine.getMappingInfo().getJavaName().toString());
		OJPackage statePackage = findOrCreatePackage(javaStateMachine.getPathName().getHead());
		statePackage.addToClasses(javaStateMachine);
		OJPathName stateClass = statePackage.getPathName();
		stateClass.addToNames(umlStateMachine.getMappingInfo().getJavaName() + "State");
		implementProcessInterfaceOperations(javaStateMachine, stateClass, umlStateMachine);
		OJOperation execute = implementExecute(javaStateMachine, umlStateMachine);
		execute.getBody().addToStatements("this.setProcessInstanceId(processInstance.getId())");

	}

	private void addImports(OJClass javaStateMachine) {
		javaStateMachine.addToImports(new OJPathName(Set.class.getName()));
		javaStateMachine.addToImports(new OJPathName(HashSet.class.getName()));
		javaStateMachine.addToImports(new OJPathName(List.class.getName()));
		javaStateMachine.addToImports(new OJPathName(ArrayList.class.getName()));
		javaStateMachine.addToImports(new OJPathName(Timestamp.class.getName()));
		javaStateMachine.addToImports(IProcessObject.class.getName());
		javaStateMachine.addToImports(IProcessStep.class.getName());
	}

	@Override
	protected Collection<? extends INakedElement> getTopLevelFlows(INakedBehavior umlBehavior) {
		return ((INakedStateMachine)umlBehavior).getRegions();
	}
}
