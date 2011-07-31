package org.nakeduml.topcased.activitydiagram.bpm.edit;

import net.sf.nakeduml.emf.workspace.UmlElementCache;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.jface.action.IAction;
import org.eclipse.uml2.uml.NamedElement;
import org.nakeduml.topcased.uml.NakedUmlPlugin;
import org.nakeduml.uml2uim.AbstractUimGenerationAction;
import org.nakeduml.uml2uim.SynchronizeAction;
import org.topcased.draw2d.figures.ComposedLabel;
import org.topcased.modeler.di.model.GraphNode;
import org.topcased.modeler.uml.activitydiagram.edit.OpaqueActionEditPart;

public class SimpleTaskEditPart extends OpaqueActionEditPart{
	public SimpleTaskEditPart(GraphNode obj){
		super(obj);
	}
	@Override
	public void performRequest(Request request){
		if(request.getType() == RequestConstants.REQ_OPEN){
			NamedElement e = (NamedElement) getEObject();
			String uuid = UmlElementCache.getId(e);
			URI uri = AbstractUimGenerationAction.getFileUri(e, uuid);
			if(!AbstractUimGenerationAction.getFile(uri).exists()){
				SynchronizeAction.doSynchronize(e,NakedUmlPlugin.findNakedUmlEditor(e) );
			}
			AbstractUimGenerationAction.openEditor(e, uuid);
		}else{
			super.performRequest(request);
		}
	}
	protected void refreshHeaderLabel(){
		super.refreshHeaderLabel();
		ComposedLabel composedLabel = (ComposedLabel) getLabel();
		composedLabel.setPrefix("<<simpleTask>>");
	}
}
