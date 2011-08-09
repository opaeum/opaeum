package net.sf.nakeduml.emf.extraction;

import java.util.ArrayList;
import java.util.List;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedBehavior;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedConstraint;
import net.sf.nakeduml.metamodel.core.INakedTypedElement;
import net.sf.nakeduml.metamodel.core.INakedValueSpecification;
import net.sf.nakeduml.metamodel.core.internal.NakedConstraintImpl;
import net.sf.nakeduml.metamodel.statemachines.INakedState;
import net.sf.nakeduml.metamodel.statemachines.internal.NakedTransitionImpl;
import nl.klasse.octopus.model.OclUsageType;
import nl.klasse.octopus.model.internal.parser.parsetree.ParsedOclString;
import nl.klasse.octopus.stdlib.IOclLibrary;

import org.eclipse.uml2.uml.Constraint;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.StateMachine;
import org.eclipse.uml2.uml.Transition;

@StepDependency(phase = EmfExtractionPhase.class,requires = {StateExtractor.class},after = {StateExtractor.class})
public class TransitionExtractor extends CommonBehaviorExtractor{
	protected List<INakedTypedElement> getEnvironment(){
		List<INakedTypedElement> result = new ArrayList<INakedTypedElement>();
		return result;
	}
	@VisitBefore
	public void visitTransition(Transition emfTransition, NakedTransitionImpl nakedTransition){
		StateMachine sm = getStateMachine(emfTransition);
		nakedTransition.setOwnerElement(getNakedPeer(sm));
		INakedState source = (INakedState) getNakedPeer(emfTransition.getSource());
		INakedState target = (INakedState) getNakedPeer(emfTransition.getTarget());
		nakedTransition.setSource(source);
		nakedTransition.setTarget(target);
		List<INakedTypedElement> env = new ArrayList<INakedTypedElement>();
		if(!emfTransition.getTriggers().isEmpty()){
			nakedTransition.setTrigger(buildTrigger(sm, emfTransition.getTriggers().iterator().next()));
			env.addAll(nakedTransition.getParameters());
		}
		// TODO think of how to make the environment available to guards
		// ---Add as parameters to operation
		Constraint emfGuard = emfTransition.getGuard();
		if(emfGuard != null && emfGuard.getSpecification() != null){
			INakedConstraint constraint = new NakedConstraintImpl();
			initialize(constraint, emfGuard, emfTransition);
			INakedBehavior nakedStateMachine = (INakedBehavior) getNakedPeer(getStateMachine(emfTransition));
			INakedValueSpecification vs = getValueSpecification(constraint, emfGuard.getSpecification(), OclUsageType.PRE);
			if(vs != null){
				if(vs.getValue() instanceof ParsedOclString){
					((ParsedOclString) vs.getValue()).setContext(nakedStateMachine, nakedTransition);
				}
				constraint.setSpecification(vs);
				nakedTransition.setGuardConstraint(constraint);
				vs.setType(getOclLibrary().lookupStandardType(IOclLibrary.BooleanTypeName));
			}
		}
		nakedTransition.setEffect(getOwnedBehavior(nakedTransition, emfTransition.getEffect()));
	}
	protected INakedClassifier getNearestContext(Transition node){
		StateMachine sm = getStateMachine(node);
		return super.getNearestContext(sm);
	}
	private StateMachine getStateMachine(Transition node){
		Element e = node;
		while(!(e instanceof StateMachine) && e != null){
			e = e.getOwner();
		}
		StateMachine sm = (StateMachine) e;
		return sm;
	}
}
