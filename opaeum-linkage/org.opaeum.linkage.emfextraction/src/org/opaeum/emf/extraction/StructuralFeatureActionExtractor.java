package org.opaeum.emf.extraction;

import org.eclipse.uml2.uml.Activity;
import org.eclipse.uml2.uml.AddStructuralFeatureValueAction;
import org.eclipse.uml2.uml.ClearStructuralFeatureAction;
import org.eclipse.uml2.uml.INakedInputPin;
import org.eclipse.uml2.uml.INakedOutputPin;
import org.eclipse.uml2.uml.INakedProperty;
import org.eclipse.uml2.uml.ReadStructuralFeatureAction;
import org.eclipse.uml2.uml.RemoveStructuralFeatureValueAction;
import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitBefore;
import org.opaeum.metamodel.actions.internal.NakedAddStructuralFeatureValueActionImpl;
import org.opaeum.metamodel.actions.internal.NakedClearStructuralFeatureActionImpl;
import org.opaeum.metamodel.actions.internal.NakedReadStructuralFeatureActionImpl;
import org.opaeum.metamodel.actions.internal.NakedRemoveStructuralFeatureValueActionImpl;

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
