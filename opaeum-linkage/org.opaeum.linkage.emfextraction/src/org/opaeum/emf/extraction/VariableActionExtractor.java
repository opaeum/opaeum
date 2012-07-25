package org.opaeum.emf.extraction;

import org.eclipse.uml2.uml.Activity;
import org.eclipse.uml2.uml.AddVariableValueAction;
import org.eclipse.uml2.uml.ClearVariableAction;
import org.eclipse.uml2.uml.INakedActivityVariable;
import org.eclipse.uml2.uml.INakedInputPin;
import org.eclipse.uml2.uml.INakedOutputPin;
import org.eclipse.uml2.uml.ReadVariableAction;
import org.eclipse.uml2.uml.RemoveVariableValueAction;
import org.eclipse.uml2.uml.Variable;
import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitBefore;
import org.opaeum.metamodel.actions.internal.NakedAddVariableValueActionImpl;
import org.opaeum.metamodel.actions.internal.NakedClearVariableActionImpl;
import org.opaeum.metamodel.actions.internal.NakedReadVariableActionImpl;
import org.opaeum.metamodel.actions.internal.NakedRemoveVariableValueActionImpl;

@StepDependency(phase = EmfExtractionPhase.class, requires = { FeatureExtractor.class, ActivityStructureExtractor.class }, after = {
		FeatureExtractor.class, ActivityStructureExtractor.class })
public class VariableActionExtractor extends AbstractActionExtractor {
	@VisitBefore
	public void visitAddVariableValueAction(AddVariableValueAction emfAction, NakedAddVariableValueActionImpl nakedAction) {
		initAction(emfAction, nakedAction);
		Activity emfActivity = getActivity(emfAction);
		Variable variable = emfAction.getVariable();
		nakedAction.setVariable((INakedActivityVariable) getNakedPeer(variable));
		nakedAction.setValue((INakedInputPin) initializePin(emfActivity, emfAction.getValue()));
		nakedAction.setReplaceAll(emfAction.isReplaceAll());
	}

	@VisitBefore
	public void visitReadVariableAction(ReadVariableAction emfAction, NakedReadVariableActionImpl nakedAction) {
		initAction(emfAction, nakedAction);
		Activity emfActivity = getActivity(emfAction);
		nakedAction.setVariable((INakedActivityVariable) getNakedPeer(emfAction.getVariable()));
		nakedAction.setResult((INakedOutputPin) initializePin(emfActivity, emfAction.getResult()));
		assignPartition(nakedAction, emfAction);
	}

	@VisitBefore
	public void visitRemoveVariableValueAction(RemoveVariableValueAction emfAction, NakedRemoveVariableValueActionImpl nakedAction) {
		Activity emfActivity = getActivity(emfAction);
		nakedAction.setVariable((INakedActivityVariable) getNakedPeer(emfAction.getVariable()));
		nakedAction.setValue((INakedInputPin) initializePin(emfActivity, emfAction.getValue()));
	}

	@VisitBefore
	public void visitClearVariableAction(ClearVariableAction emfAction, NakedClearVariableActionImpl nakedAction) {
		initAction(emfAction, nakedAction);
		nakedAction.setVariable((INakedActivityVariable) getNakedPeer(emfAction.getVariable()));
	}
}
