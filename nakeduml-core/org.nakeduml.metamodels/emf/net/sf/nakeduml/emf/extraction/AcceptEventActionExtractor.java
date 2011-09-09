package net.sf.nakeduml.emf.extraction;

import java.util.List;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.metamodel.actions.internal.NakedAcceptCallActionImpl;
import net.sf.nakeduml.metamodel.actions.internal.NakedAcceptEventActionImpl;
import net.sf.nakeduml.metamodel.activities.INakedOutputPin;
import net.sf.nakeduml.metamodel.bpm.internal.NakedAcceptDeadlineActionImpl;
import net.sf.nakeduml.metamodel.bpm.internal.NakedAcceptTaskEventActionImpl;
import net.sf.nakeduml.metamodel.core.internal.NakedElementImpl;
import net.sf.nakeduml.metamodel.core.internal.StereotypeNames;

import org.eclipse.uml2.uml.AcceptCallAction;
import org.eclipse.uml2.uml.AcceptEventAction;
import org.eclipse.uml2.uml.Activity;
import org.eclipse.uml2.uml.Element;

@StepDependency(phase = EmfExtractionPhase.class,requires = ActionExtractor.class,after = {
	ActionExtractor.class
})
public class AcceptEventActionExtractor extends AbstractActionExtractor{
	@VisitBefore
	public void visitAcceptEventAction(AcceptEventAction emfAction, NakedAcceptEventActionImpl nakedAction){
		initAction(emfAction, nakedAction);
		Activity emfActivity = getActivity(emfAction);
		List<INakedOutputPin> result = populatePins(emfActivity, emfAction.getResults());
		nakedAction.setResult(result);
	}
	@Override
	protected NakedElementImpl createElementFor(Element e,Class<?> peerClass){
		if(e instanceof AcceptCallAction){
			return new NakedAcceptCallActionImpl();
		}else if(e instanceof AcceptEventAction){
			AcceptEventAction emfAction=(AcceptEventAction) e;
			if(StereotypesHelper.hasKeyword(emfAction, StereotypeNames.ACCEPT_DEADLINE_ACTION)){
				return new NakedAcceptDeadlineActionImpl();
			}else if(StereotypesHelper.hasKeyword(emfAction, StereotypeNames.ACCEPT_TASK_EVENT_ACTION)){
				return new NakedAcceptTaskEventActionImpl();
			}else{
				return new NakedAcceptEventActionImpl();
			}
		}
		return null;
	}
	@VisitBefore
	public void visitAcceptCallAction(AcceptCallAction emfAction,NakedAcceptCallActionImpl nakedAction){
		initAction(emfAction, nakedAction);
		Activity emfActivity = getActivity(emfAction);
		nakedAction.setReturnInfo((INakedOutputPin) initializePin(emfActivity, emfAction.getReturnInformation()));
		List<INakedOutputPin> result = populatePins(emfActivity, emfAction.getResults());
		nakedAction.setResult(result);
	}
}
