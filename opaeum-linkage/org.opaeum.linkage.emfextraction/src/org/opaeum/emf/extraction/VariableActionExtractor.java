package org.opeum.emf.extraction;

import org.eclipse.uml2.uml.Activity;
import org.eclipse.uml2.uml.AddVariableValueAction;
import org.eclipse.uml2.uml.ClearVariableAction;
import org.eclipse.uml2.uml.ReadVariableAction;
import org.eclipse.uml2.uml.RemoveVariableValueAction;
import org.eclipse.uml2.uml.Variable;
import org.opeum.feature.StepDependency;
import org.opeum.feature.visit.VisitBefore;
import org.opeum.metamodel.actions.internal.NakedAddVariableValueActionImpl;
import org.opeum.metamodel.actions.internal.NakedClearVariableActionImpl;
import org.opeum.metamodel.actions.internal.NakedReadVariableActionImpl;
import org.opeum.metamodel.actions.internal.NakedRemoveVariableValueActionImpl;
import org.opeum.metamodel.activities.INakedActivityVariable;
import org.opeum.metamodel.activities.INakedInputPin;
import org.opeum.metamodel.activities.INakedOutputPin;

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
