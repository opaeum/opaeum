package org.opaeum.papyrus.classdiagram.editparts;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gmf.runtime.diagram.core.edithelpers.CreateElementRequestAdapter;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateViewAndElementRequest;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.papyrus.uml.diagram.clazz.edit.parts.DataTypeAttributeCompartmentEditPart;
import org.eclipse.papyrus.uml.diagram.clazz.edit.parts.EnumerationEditPart;
import org.eclipse.papyrus.uml.diagram.clazz.edit.parts.EnumerationEnumerationLiteralCompartmentEditPart;
import org.eclipse.papyrus.uml.diagram.clazz.edit.parts.EnumerationNameEditPart;
import org.eclipse.papyrus.uml.diagram.clazz.part.UMLVisualIDRegistry;
import org.eclipse.papyrus.uml.diagram.clazz.providers.UMLElementTypes;
import org.eclipse.papyrus.uml.diagram.common.figure.node.EnumerationFigure;

public class OpaeumEnumerationEditPart extends EnumerationEditPart{
	public static class OpaeumEnumerationFigure extends EnumerationFigure{
		private static final String LITERALS_COMPARTMENT = "literalsCompartment";
		private static final String ATTRIBUTES_COMPARTMENT = "attributesCompartment";
		public OpaeumEnumerationFigure(){
			super();
			remove(getEnumerationLiteralCompartmentFigure());
			super.createContentPane(Arrays.asList(LITERALS_COMPARTMENT, ATTRIBUTES_COMPARTMENT));
		}
		public IFigure getOwnedAttributesCompartmentFigure(){
			return getCompartment(ATTRIBUTES_COMPARTMENT);
		}
	}
	public OpaeumEnumerationEditPart(View view){
		super(view);
	}
	public OpaeumEnumerationFigure getPrimaryShape(){
		return (OpaeumEnumerationFigure) primaryShape;
	}
	protected IFigure createNodeShape(){
		return primaryShape = new OpaeumEnumerationFigure();
	}
	protected boolean addFixedChild(EditPart childEditPart){
		if(childEditPart instanceof EnumerationNameEditPart){
			((EnumerationNameEditPart) childEditPart).setLabel(getPrimaryShape().getNameLabel());
			return true;
		}
		if(childEditPart instanceof EnumerationEnumerationLiteralCompartmentEditPart){
			IFigure pane = getPrimaryShape().getEnumerationLiteralCompartmentFigure();
			setupContentPane(pane); // FIXME each comparment should handle his content pane in his own way
			pane.add(((EnumerationEnumerationLiteralCompartmentEditPart) childEditPart).getFigure());
			return true;
		}
		if(childEditPart instanceof DataTypeAttributeCompartmentEditPart){
			IFigure pane = getPrimaryShape().getOwnedAttributesCompartmentFigure();
			setupContentPane(pane); // FIXME each comparment should handle his content pane in his own way
			pane.add(((DataTypeAttributeCompartmentEditPart) childEditPart).getFigure());
			return true;
		}
		return false;
	}
	@Override
	protected List getModelChildren(){
		return super.getModelChildren();
	}
	/**
	 * @generated
	 */
	protected boolean removeFixedChild(EditPart childEditPart){
		if(childEditPart instanceof EnumerationNameEditPart){
			return true;
		}
		if(childEditPart instanceof EnumerationEnumerationLiteralCompartmentEditPart){
			IFigure pane = getPrimaryShape().getEnumerationLiteralCompartmentFigure();
			setupContentPane(pane); // FIXME each comparment should handle his content pane in his own way
			pane.remove(((EnumerationEnumerationLiteralCompartmentEditPart) childEditPart).getFigure());
			return true;
		}
		if(childEditPart instanceof DataTypeAttributeCompartmentEditPart){
			IFigure pane = getPrimaryShape().getOwnedAttributesCompartmentFigure();
			setupContentPane(pane); // FIXME each comparment should handle his content pane in his own way
			pane.remove(((DataTypeAttributeCompartmentEditPart) childEditPart).getFigure());
			return true;
		}
		return false;
	}
	protected IFigure getContentPaneFor(IGraphicalEditPart editPart){
		if(editPart instanceof EnumerationEnumerationLiteralCompartmentEditPart){
			return getPrimaryShape().getEnumerationLiteralCompartmentFigure();
		}
		if(editPart instanceof DataTypeAttributeCompartmentEditPart){
			return getPrimaryShape().getOwnedAttributesCompartmentFigure();
		}
		return getContentPane();
	}
	public EditPart getTargetEditPart(Request request){
		if(request instanceof CreateViewAndElementRequest){
			CreateElementRequestAdapter adapter = ((CreateViewAndElementRequest) request).getViewAndElementDescriptor()
					.getCreateElementRequestAdapter();
			IElementType type = (IElementType) adapter.getAdapter(IElementType.class);
			if(type == UMLElementTypes.EnumerationLiteral_3017){
				return getChildBySemanticHint(UMLVisualIDRegistry.getType(EnumerationEnumerationLiteralCompartmentEditPart.VISUAL_ID));
			}
			if(type == UMLElementTypes.Property_3018){
				return getChildBySemanticHint(UMLVisualIDRegistry.getType(DataTypeAttributeCompartmentEditPart.VISUAL_ID));
			}
		}
		return super.getTargetEditPart(request);
	}
}