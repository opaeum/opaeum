package org.opaeum.topcased.activitydiagram.method;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;
import org.opaeum.topcased.activitydiagram.OpaeumActivityPaletteManager;
import org.topcased.modeler.di.model.Diagram;
import org.topcased.modeler.editor.IPaletteManager;
import org.topcased.modeler.uml.activitydiagram.internal.structuredactivitynode.edit.StructuredActivityNodeDiagramEditPart;

public class MethodStructuredActivityNodeConfiguration extends MethodConfiguration{
	private OpaeumActivityPaletteManager paletteManager;
	private MethodEditPartFactory editPartFactory;
	@Override
	public String getId(){
		return new String("org.topcased.modeler.uml.structuredactivitynodediagram");
	}
	@Override
	public String getName(){
		return new String("Structured Activity Node Diagram");
	}
	@Override
	public IPaletteManager getPaletteManager(){
		if(paletteManager == null){
			paletteManager = new MethodStructuredNodePaletteManager(super.getCreationUtils());
		}
		return paletteManager;
	}
	@Override
	public EditPartFactory getEditPartFactory(){
		if(editPartFactory == null){
			editPartFactory = new MethodEditPartFactory(){
				@Override
				public EditPart createEditPart(EditPart context,Object model){
					if(model instanceof Diagram){
						return new StructuredActivityNodeDiagramEditPart((Diagram) model);
					}
					return super.createEditPart(context, model);
				}
			};
		}
		return editPartFactory;
	}
}