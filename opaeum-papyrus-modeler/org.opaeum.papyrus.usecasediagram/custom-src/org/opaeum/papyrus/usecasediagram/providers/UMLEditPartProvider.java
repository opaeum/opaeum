package org.opaeum.papyrus.usecasediagram.providers;

import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.papyrus.uml.diagram.usecase.edit.parts.ActorAppliedStereotypeEditPartCN;
import org.eclipse.papyrus.uml.diagram.usecase.edit.parts.ActorAppliedStereotypeEditPartTN;
import org.eclipse.papyrus.uml.diagram.usecase.edit.parts.ActorEditPartTN;
import org.eclipse.papyrus.uml.diagram.usecase.edit.parts.UMLEditPartFactory;
import org.eclipse.papyrus.uml.diagram.usecase.edit.parts.UseCaseEditPartTN;
import org.eclipse.papyrus.uml.diagram.usecase.part.UMLVisualIDRegistry;

public class UMLEditPartProvider extends org.eclipse.papyrus.uml.diagram.usecase.providers.UMLEditPartProvider{
	public UMLEditPartProvider(){
		setFactory(new UMLEditPartFactory(){
			@Override
			public EditPart createEditPart(EditPart context,Object model){
				if(model instanceof View){
					View view = (View) model;
					switch(UMLVisualIDRegistry.getVisualID(view)){
					case ActorEditPartTN.VISUAL_ID:
						return new ActorEditPartTN(view);
					case ActorAppliedStereotypeEditPartCN.VISUAL_ID:
						return new ActorAppliedStereotypeEditPartCN(view){
							protected String getLabelText(){
								return "<<Business Actor>>";
							}
						};
					case UseCaseEditPartTN.VISUAL_ID:
						return new UseCaseEditPartTN(view){
						};
					}
				}
				return super.createEditPart(context, model);
			}
		});
	}
}
