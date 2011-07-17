package net.sf.nakeduml.emf.extraction;

import java.util.List;

import org.eclipse.uml2.uml.AcceptCallAction;
import org.eclipse.uml2.uml.AcceptEventAction;
import org.eclipse.uml2.uml.Activity;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.metamodel.actions.internal.NakedAcceptCallActionImpl;
import net.sf.nakeduml.metamodel.actions.internal.NakedAcceptEventActionImpl;
import net.sf.nakeduml.metamodel.activities.INakedOutputPin;
import net.sf.nakeduml.metamodel.bpm.internal.NakedAcceptDeadlineActionImpl;
import net.sf.nakeduml.metamodel.bpm.internal.NakedAcceptTaskEventActionImpl;
import net.sf.nakeduml.metamodel.core.internal.StereotypeNames;

@StepDependency(phase = EmfExtractionPhase.class,requires = ActionExtractor.class,after = {
	ActionExtractor.class
})
public class AcceptEventActionExtractor extends AbstractActionExtractor{
	@VisitBefore
	public void visitAcceptEventAction(AcceptEventAction emfAction){
		NakedAcceptEventActionImpl nakedAction;
		if(StereotypesHelper.hasKeyword(emfAction, StereotypeNames.ACCEPT_DEADLINE_ACTION)){
			nakedAction = new NakedAcceptDeadlineActionImpl();
		}else if(StereotypesHelper.hasKeyword(emfAction, StereotypeNames.ACCEPT_TASK_EVENT_ACTION)){
			nakedAction = new NakedAcceptTaskEventActionImpl();
		}else{
			nakedAction = new NakedAcceptEventActionImpl();
		}
		super.initialize(nakedAction, emfAction, emfAction.getOwner());
		initAction(emfAction, nakedAction);
		Activity emfActivity = getActivity(emfAction);
		if(!emfAction.getTriggers().isEmpty()){
			// we only support one trigger
			nakedAction.setTrigger(buildTrigger(emfActivity, emfAction.getTriggers().iterator().next()));
		}
		List<INakedOutputPin> result = populatePins(emfActivity, emfAction.getResults());
		nakedAction.setResult(result);
	}
	@VisitBefore
	public void visitAcceptCallAction(AcceptCallAction emfAction,NakedAcceptCallActionImpl nakedAction){
		initAction(emfAction, nakedAction);
		Activity emfActivity = getActivity(emfAction);
		if(!emfAction.getTriggers().isEmpty()){
			// we only support one trigger
			nakedAction.setTrigger(buildTrigger(emfActivity, emfAction.getTriggers().iterator().next()));
		}
		nakedAction.setReturnInfo((INakedOutputPin) initializePin(emfActivity, emfAction.getReturnInformation()));
		List<INakedOutputPin> result = populatePins(emfActivity, emfAction.getResults());
		nakedAction.setResult(result);
	}
}
