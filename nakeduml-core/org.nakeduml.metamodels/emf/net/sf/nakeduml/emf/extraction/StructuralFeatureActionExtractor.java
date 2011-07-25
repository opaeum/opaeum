package net.sf.nakeduml.emf.extraction;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.metamodel.actions.internal.NakedAddStructuralFeatureValueActionImpl;
import net.sf.nakeduml.metamodel.actions.internal.NakedClearStructuralFeatureActionImpl;
import net.sf.nakeduml.metamodel.actions.internal.NakedReadStructuralFeatureActionImpl;
import net.sf.nakeduml.metamodel.actions.internal.NakedRemoveStructuralFeatureValueActionImpl;
import net.sf.nakeduml.metamodel.activities.INakedInputPin;
import net.sf.nakeduml.metamodel.activities.INakedOutputPin;
import net.sf.nakeduml.metamodel.core.INakedProperty;

import org.eclipse.uml2.uml.Activity;
import org.eclipse.uml2.uml.AddStructuralFeatureValueAction;
import org.eclipse.uml2.uml.ClearStructuralFeatureAction;
import org.eclipse.uml2.uml.ReadStructuralFeatureAction;
import org.eclipse.uml2.uml.RemoveStructuralFeatureValueAction;

@StepDependency(phase = EmfExtractionPhase.class,requires = {
		FeatureExtractor.class,ActivityStructureExtractor.class
},after = {
		FeatureExtractor.class,ActivityStructureExtractor.class
})
public class StructuralFeatureActionExtractor extends AbstractActionExtractor{
	@VisitBefore
	public void visitAddStructuralFeatureValueAction(AddStructuralFeatureValueAction emfAction,NakedAddStructuralFeatureValueActionImpl nakedAction){
		initAction(emfAction, nakedAction);
		Activity emfActivity = getActivity(emfAction);
		nakedAction.setFeature((INakedProperty) getNakedPeer(emfAction.getStructuralFeature()));
		nakedAction.setObject((INakedInputPin) initializePin(emfActivity, emfAction.getObject()));
		nakedAction.setValue((INakedInputPin) initializePin(emfActivity, emfAction.getValue()));
		nakedAction.setReplaceAll(emfAction.isReplaceAll());
	}
	@VisitBefore
	public void visitClearStructuralFeatureValueAction(ClearStructuralFeatureAction emfAction,NakedClearStructuralFeatureActionImpl nakedAction){
		initAction(emfAction, nakedAction);
		Activity emfActivity = getActivity(emfAction);
		nakedAction.setFeature((INakedProperty) getNakedPeer(emfAction.getStructuralFeature()));
		nakedAction.setObject((INakedInputPin) initializePin(emfActivity, emfAction.getObject()));
	}
	@VisitBefore
	public void visitRemoveStructuralFeatureValueAction(RemoveStructuralFeatureValueAction emfAction,NakedRemoveStructuralFeatureValueActionImpl nakedAction){
		initAction(emfAction, nakedAction);
		Activity emfActivity = getActivity(emfAction);
		nakedAction.setFeature((INakedProperty) getNakedPeer(emfAction.getStructuralFeature()));
		nakedAction.setObject((INakedInputPin) initializePin(emfActivity, emfAction.getObject()));
		nakedAction.setValue((INakedInputPin) initializePin(emfActivity, emfAction.getValue()));
	}
	@VisitBefore
	public void visitReadStructuralFeatureAction(ReadStructuralFeatureAction emfAction,NakedReadStructuralFeatureActionImpl nakedAction){
		initAction(emfAction, nakedAction);
		Activity emfActivity = getActivity(emfAction);
		nakedAction.setFeature((INakedProperty) getNakedPeer(emfAction.getStructuralFeature()));
		nakedAction.setObject((INakedInputPin) initializePin(emfActivity, emfAction.getObject()));
		nakedAction.setResult((INakedOutputPin) initializePin(emfActivity, emfAction.getResult()));
	}
}
