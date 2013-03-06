package org.opaeum.papyrus.businessprocessdiagram;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.gmf.runtime.diagram.core.preferences.PreferencesHint;
import org.eclipse.papyrus.uml.diagram.activity.part.UMLDiagramEditorPlugin;
import org.eclipse.papyrus.uml.diagram.common.commands.CreateBehavioredClassifierDiagramCommand;
import org.eclipse.uml2.uml.UMLPackage;

public class CreateBusinessProcessDiagramCommand extends CreateBehavioredClassifierDiagramCommand{
	@Override
	protected String getDiagramNotationID(){
		return org.eclipse.papyrus.uml.diagram.activity.edit.parts.ActivityDiagramEditPart.MODEL_ID;
	}
	@Override
	protected PreferencesHint getPreferenceHint(){
		return UMLDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT;
	}
	@Override
	protected EClass getBehaviorEClass(){
		return UMLPackage.eINSTANCE.getActivity();
	}
	@Override
	protected String getDefaultDiagramName(){
		return "BusinessProcessDiagram"; //$NON-NLS-1$
	}
}
