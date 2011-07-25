package org.nakeduml.topcased;

import java.util.Collections;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.topcased.modeler.di.model.Diagram;
import org.topcased.modeler.diagrams.model.util.DiagramsUtils;
import org.topcased.modeler.editor.Modeler;
import org.topcased.modeler.editor.TopcasedAdapterFactoryEditingDomain;
import org.topcased.modeler.extensions.DiagramsManager;
import org.topcased.modeler.internal.ModelerPlugin;
import org.topcased.modeler.internal.actions.ChangeActiveDiagramAction;
import org.topcased.modeler.internal.actions.CreateDiagramAction;

public class EditPartUtil{

	public static IAction createDiagramAction(EObject diagramOwner, EObject source,Modeler editor,String id){
		IAction action = null;
		List<Diagram> existingDiagramList = (List<Diagram>) (diagramOwner==null?Collections.emptyList():DiagramsUtils.findAllExistingDiagram(editor.getDiagrams(), diagramOwner));
		if(existingDiagramList != null && existingDiagramList.size() > 0){
			action = new ChangeActiveDiagramAction(editor, existingDiagramList.get(0));
		}else if(EcoreUtil.isAncestor(editor.getDiagrams().getModel(), source)){
			action = createNewDiagram(editor, source,id);
		}
		return action;
	}
	public static IAction createNewDiagram(Modeler editor,EObject modelObject, String id){
		if(TopcasedAdapterFactoryEditingDomain.isEObjectReadOnly(modelObject)){
			MessageDialog.openWarning(ModelerPlugin.getActiveWorkbenchShell(), "Warning", "your originalElement is read only you can't create diagrams");
		}else{
			return new CreateDiagramAction(editor, modelObject, DiagramsManager.getInstance().find(id), true);
		}
		return null;
	}

}
