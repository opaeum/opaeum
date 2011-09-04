package net.sf.nakeduml.emf.extraction;

import java.util.ArrayList;
import java.util.List;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedTypedElement;
import net.sf.nakeduml.metamodel.statemachines.INakedState;
import net.sf.nakeduml.metamodel.statemachines.internal.NakedTransitionImpl;

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
		INakedState source = (INakedState) getNakedPeer(emfTransition.getSource());
		INakedState target = (INakedState) getNakedPeer(emfTransition.getTarget());
		nakedTransition.setSource(source);
		nakedTransition.setTarget(target);
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
