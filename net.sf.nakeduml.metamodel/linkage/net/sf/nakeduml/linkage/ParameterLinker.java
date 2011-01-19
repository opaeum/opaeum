package net.sf.nakeduml.linkage;

import java.util.List;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.visit.VisitAfter;
import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.metamodel.actions.IActionWithTarget;
import net.sf.nakeduml.metamodel.actions.INakedAcceptEventAction;
import net.sf.nakeduml.metamodel.actions.INakedCallAction;
import net.sf.nakeduml.metamodel.actions.INakedCreateObjectAction;
import net.sf.nakeduml.metamodel.actions.INakedReadStructuralFeatureAction;
import net.sf.nakeduml.metamodel.actions.INakedReadVariableAction;
import net.sf.nakeduml.metamodel.actions.INakedSendSignalAction;
import net.sf.nakeduml.metamodel.actions.INakedWriteStructuralFeatureAction;
import net.sf.nakeduml.metamodel.actions.INakedWriteVariableAction;
import net.sf.nakeduml.metamodel.activities.INakedActivityEdge;
import net.sf.nakeduml.metamodel.activities.INakedInputPin;
import net.sf.nakeduml.metamodel.activities.INakedObjectFlow;
import net.sf.nakeduml.metamodel.activities.INakedOutputPin;
import net.sf.nakeduml.metamodel.activities.INakedPin;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedBehavior;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedSignal;
import net.sf.nakeduml.metamodel.core.INakedParameter;
import net.sf.nakeduml.metamodel.core.INakedProperty;
import net.sf.nakeduml.metamodel.core.INakedTypedElement;
import net.sf.nakeduml.validation.activities.ActivityValidationRule;

@StepDependency(phase = LinkagePhase.class, after = { MappedTypeLinker.class }, before = { ObjectFlowLinker.class }, requires = {})
public class ParameterLinker extends AbstractModelElementLinker {
	@VisitBefore(matchSubclasses = true)
	public void linkParameters(INakedBehavior b) {
		if(b.getSpecification()!=null){
			List<? extends INakedParameter> fromParams = b.getSpecification().getOwnedParameters();
			List<? extends INakedParameter> toParams= b.getOwnedParameters();
			for(int i =0;i<fromParams.size() && i<toParams.size();i++){
				INakedParameter to = toParams.get(i);
				INakedParameter from = fromParams.get(i);
				to.setLinkedParameter(from);
				to.setName(from.getName());
				to.getMappingInfo().setJavaName(from.getMappingInfo().getJavaName());
			}
			//TODO set types????
		}
	}
}
