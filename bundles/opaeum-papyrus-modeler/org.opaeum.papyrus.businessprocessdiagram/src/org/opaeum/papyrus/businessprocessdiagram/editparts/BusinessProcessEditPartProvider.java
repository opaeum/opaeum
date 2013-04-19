package org.opaeum.papyrus.businessprocessdiagram.editparts;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.papyrus.uml.diagram.activity.edit.parts.ActivityActivityContentCompartmentEditPart;
import org.eclipse.papyrus.uml.diagram.activity.edit.parts.CallBehaviorActionEditPart;
import org.eclipse.papyrus.uml.diagram.activity.edit.parts.OpaqueActionEditPart;
import org.eclipse.papyrus.uml.diagram.activity.figures.CallBehaviorActionFigure;
import org.eclipse.papyrus.uml.diagram.activity.part.UMLVisualIDRegistry;
import org.eclipse.papyrus.uml.diagram.activity.providers.CustomUMLEditPartProvider;
import org.eclipse.papyrus.uml.diagram.common.figure.node.IPapyrusNodeNamedElementFigure;
import org.eclipse.uml2.uml.ActivityNode;
import org.eclipse.uml2.uml.Element;
import org.opaeum.eclipse.EmfActionUtil;
import org.opaeum.emf.extraction.StereotypesHelper;
import org.opaeum.metamodel.core.internal.StereotypeNames;
import org.opaeum.papyrus.uml.diagram.businessprocess.edit.parts.OpaeumActivityActivityContentCompartmentEditPart;

public class BusinessProcessEditPartProvider extends CustomUMLEditPartProvider{
	public BusinessProcessEditPartProvider(){
		setFactory(new UMLEditPartFactorsy());
		setAllowCaching(true);
	}
	public static class UMLEditPartFactorsy extends org.eclipse.papyrus.uml.diagram.activity.edit.part.CustomUMLEditPartFactory{
		public EditPart createEditPart(EditPart context,Object model){
			if(model instanceof View){
				View view = (View) model;
				switch(UMLVisualIDRegistry.getVisualID(view)){
				case OpaqueActionEditPart.VISUAL_ID:
					return new OpaqueActionEditPart(view){
						private Figure imageFigure;
						@Override
						protected void refreshVisuals(){
							super.refreshVisuals();
							if((IPapyrusNodeNamedElementFigure) getPrimaryShape() != null && resolveSemanticElement() != null){
								IPapyrusNodeNamedElementFigure l = (IPapyrusNodeNamedElementFigure) getPrimaryShape();
								Element element = (Element) getAdapter(Element.class);
								if(EmfActionUtil.isEmbeddedTask((ActivityNode) element)){
									l.getTaggedLabel().setText("<Embedded Task>");
								}else{
									l.getTaggedLabel().setText("<OCL Action>");
								}
							}
							// ((PapyrusNodeFigure) getPrimaryShape()).setIsUsingGradient(true);
						}
						protected IFigure createNodeShape(){
							this.imageFigure = new OpaeumRoundedNodeFigure();
							primaryShape = imageFigure;
							return primaryShape;
						}
					};
				case ActivityActivityContentCompartmentEditPart.VISUAL_ID:
					return new OpaeumActivityActivityContentCompartmentEditPart(view);
				case CallBehaviorActionEditPart.VISUAL_ID:
					return new CallBehaviorActionEditPart(view){
						private Figure imageFigure;
						@Override
						protected void refreshVisuals(){
							super.refreshVisuals();
							if((IPapyrusNodeNamedElementFigure) getPrimaryShape() != null && resolveSemanticElement() != null){
								IPapyrusNodeNamedElementFigure l = (IPapyrusNodeNamedElementFigure) getPrimaryShape();
								Element element = (Element) getAdapter(Element.class);
								if(StereotypesHelper.hasStereotype((ActivityNode) element, StereotypeNames.CALL_BUSINES_PROCESS_ACTION)){
									l.getTaggedLabel().setText("<Business Process Call>");
								}else{
									l.getTaggedLabel().setText("<Method Call>");
								}
							}
							// ((PapyrusNodeFigure) getPrimaryShape()).setIsUsingGradient(true);
						}
						protected IFigure createNodeShape(){
							this.imageFigure = new CallBehaviorActionFigure(){
								@Override
								public Label getTaggedLabel(){
									if(super.getTaggedLabel()==null){
										initTagLabel("dummy");
									}
									return super.getTaggedLabel();
								}
							};
							primaryShape = imageFigure;
							return primaryShape;
						}
					};
				}
			}
			return super.createEditPart(context, model);
		}
	}
}
