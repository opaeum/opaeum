package org.opaeum.papyrus.usecasediagram.providers;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.ImageFigure;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.papyrus.uml.diagram.usecase.edit.parts.ActorAppliedStereotypeEditPartTN;
import org.eclipse.papyrus.uml.diagram.usecase.edit.parts.ActorEditPartTN;
import org.eclipse.papyrus.uml.diagram.usecase.edit.parts.ActorNameEditPartTN;
import org.eclipse.papyrus.uml.diagram.usecase.edit.parts.UMLEditPartFactory;
import org.eclipse.papyrus.uml.diagram.usecase.edit.parts.UseCaseDiagramEditPart;
import org.eclipse.papyrus.uml.diagram.usecase.part.UMLDiagramEditorPlugin;
import org.eclipse.papyrus.uml.diagram.usecase.part.UMLVisualIDRegistry;

public class UMLEditPartProvider extends
		org.eclipse.papyrus.uml.diagram.usecase.providers.UMLEditPartProvider {
	public UMLEditPartProvider() {
		setFactory(new UMLEditPartFactory() {
			@Override
			public EditPart createEditPart(EditPart context, Object model) {
				if (model instanceof View) {
					View view = (View) model;
					switch (UMLVisualIDRegistry.getVisualID(view)) {
					case UseCaseDiagramEditPart.VISUAL_ID:
						return new UseCaseDiagramEditPart(view);
					case ActorEditPartTN.VISUAL_ID:
						return new ActorEditPartTN(view) {
							@Override
							protected void refreshVisuals() {
								super.refreshVisuals();
							}

							@Override
							protected IFigure createNodeShape() {
								return primaryShape = new ImageFigure(
										UMLDiagramEditorPlugin
												.getInstance()
												.getBundledImage(
														"images/BusinessActor.jpg"));
							}
						};
					case ActorNameEditPartTN.VISUAL_ID:
						return new ActorNameEditPartTN(view) {
							@Override
							protected void refreshVisuals() {
								super.refreshVisuals();
							}
						};
					case ActorAppliedStereotypeEditPartTN.VISUAL_ID:
						return new ActorAppliedStereotypeEditPartTN(view) {
							@Override
							protected void refreshVisuals() {
								super.refreshVisuals();
								getFigure().setVisible(true);
								setLabelTextHelper(getFigure(),
										"<<Business Actor>>");
							}
						};
					default:
						return super.createEditPart(context, model);
					}
				}
				return super.createEditPart(context, model);
			}
		});
	}
}
