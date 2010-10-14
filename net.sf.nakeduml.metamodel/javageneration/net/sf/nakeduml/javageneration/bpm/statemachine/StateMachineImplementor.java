package net.sf.nakeduml.javageneration.bpm.statemachine;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.sf.nakeduml.feature.visit.VisitAfter;
import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.javageneration.bpm.AbstractBehaviorVisitor;
import net.sf.nakeduml.javageneration.bpm.BpmUtil;
import net.sf.nakeduml.javametamodel.OJClass;
import net.sf.nakeduml.javametamodel.OJOperation;
import net.sf.nakeduml.javametamodel.OJPackage;
import net.sf.nakeduml.javametamodel.OJPathName;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedClass;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedOperation;
import net.sf.nakeduml.metamodel.models.INakedModel;
import net.sf.nakeduml.metamodel.statemachines.INakedState;
import net.sf.nakeduml.metamodel.statemachines.INakedStateMachine;
import net.sf.nakeduml.util.AbstractProcess;
import net.sf.nakeduml.util.AbstractProcessStep;

public class StateMachineImplementor extends AbstractBehaviorVisitor{
	@VisitAfter(matchSubclasses=true)
	public void visitModel(INakedModel m){
//		Map<Class,OJPathName> map = new HashMap<Class,OJPathName>();
//		map.put(AbstractProcess.class, ReflectionUtil.getUtilInterface(AbstractProcess.class));
//		map.put(AbstractProcessStep.class, ReflectionUtil.getUtilInterface(AbstractProcessStep.class));
//		map.put(ActiveEntity.class, ReflectionUtil.getUtilInterface(ActiveEntity.class));
//		OJAnnotatedClass ap= ReflectionUtil.duplicateInterface(AbstractProcess.class,map);
//		createTextPath(ap, JavaTextSource.GEN_SRC);
//		OJAnnotatedClass aps=ReflectionUtil.duplicateInterface(AbstractProcessStep.class,map);
//		createTextPath(aps, JavaTextSource.GEN_SRC);
//		OJAnnotatedClass ae=ReflectionUtil.duplicateInterface(ActiveEntity.class,map);
//		createTextPath(ae, JavaTextSource.GEN_SRC);
	}
	@VisitBefore(matchSubclasses = true)
	public void state(INakedState state){
		if(state.getKind().isRestingState()){
			INakedStateMachine umlStateMachine = state.getStateMachine();
			OJClass javaStateMachine = findJavaClass(umlStateMachine);
			OJOperation getter = new OJAnnotatedOperation();
			getter.setReturnType(new OJPathName("boolean"));
			getter.setName("get" + state.getMappingInfo().getJavaName().getCapped());
			javaStateMachine.addToOperations(getter);
			if(state.getStateMachine().isClassifierBehavior()){
				OJAnnotatedClass context=findJavaClass(state.getStateMachine().getContext());
				OJOperation copy = getter.getCopy();
				copy.getBody().addToStatements("return getClassifierBehavior()!=null && getClassifierBehavior()." +copy.getName() + "()");
				context.addToOperations(copy);
			}
			getter.getBody().addToStatements(
					"return isStepActive(" + javaStateMachine.getName() + "State." + BpmUtil.stepLiteralName(state) + ")");
		}
	};
	@VisitBefore(matchSubclasses = true)
	public void class_After(INakedStateMachine umlStateMachine){
		// TODO FOR each region with a history state, we would have to store a
		// variable
		OJAnnotatedClass javaStateMachine = findJavaClass(umlStateMachine);
		addImports(javaStateMachine);
		javaStateMachine.setName(umlStateMachine.getMappingInfo().getJavaName().toString());
		super.implementRelationshipsWithContextAndProcess(umlStateMachine, javaStateMachine);
		OJPackage statePackage = findOrCreatePackage(javaStateMachine.getPathName().getHead());
		statePackage.addToClasses(javaStateMachine);
		OJPathName stateClass = statePackage.getPathName();
		stateClass.addToNames(umlStateMachine.getMappingInfo().getJavaName() + "State");
		implementProcessInterfaceOperations(javaStateMachine, stateClass, umlStateMachine);
		implementSpecificationOrStartClassifierBehaviour(umlStateMachine);
		OJOperation execute = implementExecute(javaStateMachine, umlStateMachine);
		execute.getBody().addToStatements("processInstance.signal()");
	}
	private void addImports(OJClass javaStateMachine){
		javaStateMachine.addToImports(new OJPathName(Set.class.getName()));
		javaStateMachine.addToImports(new OJPathName(HashSet.class.getName()));
		javaStateMachine.addToImports(new OJPathName(List.class.getName()));
		javaStateMachine.addToImports(new OJPathName(ArrayList.class.getName()));
		javaStateMachine.addToImports(new OJPathName(Timestamp.class.getName()));
		javaStateMachine.addToImports(AbstractProcess.class.getName());
		javaStateMachine.addToImports(AbstractProcessStep.class.getName());
		javaStateMachine.addToImports(AbstractProcessStep.class.getName());
	}
}
