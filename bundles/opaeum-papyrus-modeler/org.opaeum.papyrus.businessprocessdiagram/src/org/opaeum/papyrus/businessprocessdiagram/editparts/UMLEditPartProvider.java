package org.opaeum.papyrus.businessprocessdiagram.editparts;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.tools.CellEditorLocator;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ITextAwareEditPart;
import org.eclipse.gmf.runtime.draw2d.ui.figures.FigureUtilities;
import org.eclipse.gmf.runtime.draw2d.ui.figures.WrappingLabel;
import org.eclipse.gmf.runtime.notation.GradientStyle;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.gmf.runtime.notation.datatype.GradientData;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.papyrus.uml.diagram.activity.edit.parts.OpaqueActionEditPart;
import org.eclipse.papyrus.uml.diagram.activity.part.UMLVisualIDRegistry;
import org.eclipse.papyrus.uml.diagram.activity.providers.CustomUMLEditPartProvider;
import org.eclipse.papyrus.uml.diagram.common.figure.node.ComponentFigure;
import org.eclipse.papyrus.uml.diagram.common.figure.node.IMultilineEditableFigure;
import org.eclipse.papyrus.uml.diagram.common.figure.node.IPapyrusNodeNamedElementFigure;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.uml2.uml.ActivityNode;
import org.eclipse.uml2.uml.Element;
import org.opaeum.eclipse.EmfActionUtil;
import org.opaeum.papyrus.common.ImageUtil;

public class UMLEditPartProvider extends CustomUMLEditPartProvider{
	public UMLEditPartProvider(){
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
									l.getTaggedLabel().setText("<<Embedded Task>>");
								}else{
									l.getTaggedLabel().setText("<<OCL Action>>");
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
				}
			}
			return super.createEditPart(context, model);
		}
		public static CellEditorLocator getTextCellEditorLocator(ITextAwareEditPart source){
			if(source.getFigure() instanceof IMultilineEditableFigure)
				return new MultilineCellEditorLocator((IMultilineEditableFigure) source.getFigure());
			else if(source.getFigure() instanceof WrappingLabel)
				return new TextCellEditorLocator((WrappingLabel) source.getFigure());
			else{
				return new LabelCellEditorLocator((Label) source.getFigure());
			}
		}
		/**
		 * @generated
		 */
		static private class MultilineCellEditorLocator implements CellEditorLocator{
			/**
			 * @generated
			 */
			private IMultilineEditableFigure multilineEditableFigure;
			/**
			 * @generated
			 */
			public MultilineCellEditorLocator(IMultilineEditableFigure figure){
				this.multilineEditableFigure = figure;
			}
			/**
			 * @generated
			 */
			public IMultilineEditableFigure getMultilineEditableFigure(){
				return multilineEditableFigure;
			}
			/**
			 * @generated
			 */
			public void relocate(CellEditor celleditor){
				Text text = (Text) celleditor.getControl();
				Rectangle rect = getMultilineEditableFigure().getBounds().getCopy();
				rect.x = getMultilineEditableFigure().getEditionLocation().x;
				rect.y = getMultilineEditableFigure().getEditionLocation().y;
				getMultilineEditableFigure().translateToAbsolute(rect);
				if(getMultilineEditableFigure().getText().length() > 0){
					rect.setSize(new Dimension(text.computeSize(rect.width, SWT.DEFAULT)));
				}
				if(!rect.equals(new Rectangle(text.getBounds()))){
					text.setBounds(rect.x, rect.y, rect.width, rect.height);
				}
			}
		}
		/**
		 * @generated
		 */
		static private class TextCellEditorLocator implements CellEditorLocator{
			/**
			 * @generated
			 */
			private WrappingLabel wrapLabel;
			/**
			 * @generated
			 */
			public TextCellEditorLocator(WrappingLabel wrapLabel){
				this.wrapLabel = wrapLabel;
			}
			/**
			 * @generated
			 */
			public WrappingLabel getWrapLabel(){
				return wrapLabel;
			}
			/**
			 * @generated
			 */
			public void relocate(CellEditor celleditor){
				Text text = (Text) celleditor.getControl();
				Rectangle rect = getWrapLabel().getTextBounds().getCopy();
				getWrapLabel().translateToAbsolute(rect);
				if(!text.getFont().isDisposed()){
					if(getWrapLabel().isTextWrapOn() && getWrapLabel().getText().length() > 0){
						rect.setSize(new Dimension(text.computeSize(rect.width, SWT.DEFAULT)));
					}else{
						int avr = FigureUtilities.getFontMetrics(text.getFont()).getAverageCharWidth();
						rect.setSize(new Dimension(text.computeSize(SWT.DEFAULT, SWT.DEFAULT)).expand(avr * 2, 0));
					}
				}
				if(!rect.equals(new Rectangle(text.getBounds()))){
					text.setBounds(rect.x, rect.y, rect.width, rect.height);
				}
			}
		}
		/**
		 * @generated
		 */
		private static class LabelCellEditorLocator implements CellEditorLocator{
			/**
			 * @generated
			 */
			private Label label;
			/**
			 * @generated
			 */
			public LabelCellEditorLocator(Label label){
				this.label = label;
			}
			/**
			 * @generated
			 */
			public Label getLabel(){
				return label;
			}
			/**
			 * @generated
			 */
			public void relocate(CellEditor celleditor){
				Text text = (Text) celleditor.getControl();
				Rectangle rect = getLabel().getTextBounds().getCopy();
				getLabel().translateToAbsolute(rect);
				if(!text.getFont().isDisposed()){
					int avr = FigureUtilities.getFontMetrics(text.getFont()).getAverageCharWidth();
					rect.setSize(new Dimension(text.computeSize(SWT.DEFAULT, SWT.DEFAULT)).expand(avr * 2, 0));
				}
				if(!rect.equals(new Rectangle(text.getBounds()))){
					text.setBounds(rect.x, rect.y, rect.width, rect.height);
				}
			}
		}
	}
}
