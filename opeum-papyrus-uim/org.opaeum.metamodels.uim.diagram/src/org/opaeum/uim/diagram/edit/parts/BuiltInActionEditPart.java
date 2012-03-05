package org.opaeum.uim.diagram.edit.parts;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.FlowLayout;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.MouseEvent;
import org.eclipse.draw2d.MouseListener;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.Shape;
import org.eclipse.draw2d.StackLayout;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.LayoutEditPolicy;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.FlowLayoutEditPolicy;
import org.eclipse.gmf.runtime.draw2d.ui.figures.ConstrainedToolbarLayout;
import org.eclipse.gmf.runtime.draw2d.ui.figures.FigureUtilities;
import org.eclipse.gmf.runtime.draw2d.ui.figures.WrappingLabel;
import org.eclipse.gmf.runtime.gef.ui.figures.DefaultSizeNodeFigure;
import org.eclipse.gmf.runtime.gef.ui.figures.NodeFigure;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferenceConverter;
import org.eclipse.papyrus.infra.gmfdiag.preferences.utils.GradientPreferenceConverter;
import org.eclipse.papyrus.infra.gmfdiag.preferences.utils.PreferenceConstantHelper;
import org.eclipse.papyrus.uml.diagram.common.helper.PreferenceInitializerForElementHelper;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.opaeum.uim.diagram.edit.policies.BuiltInActionItemSemanticEditPolicy;
import org.opaeum.uim.diagram.part.UimDiagramEditorPlugin;
import org.opaeum.uim.diagram.part.UimVisualIDRegistry;
import org.opaeum.uimodeler.util.ControlPainter;

/**
 * @generated
 */
public class BuiltInActionEditPart extends ShapeNodeEditPart{
	/**
	 * @generated
	 */
	public static final int VISUAL_ID = 3002;
	/**
	 * @generated
	 */
	protected IFigure contentPane;
	/**
	 * @generated
	 */
	protected IFigure primaryShape;
	/**
	 * @generated
	 */
	public BuiltInActionEditPart(View view){
		super(view);
	}
	/**
	 * @generated
	 */
	protected void createDefaultEditPolicies(){
		super.createDefaultEditPolicies();
		installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE, new BuiltInActionItemSemanticEditPolicy());
		installEditPolicy(EditPolicy.LAYOUT_ROLE, createLayoutEditPolicy());
		// XXX need an SCR to runtime to have another abstract superclass that would let children add reasonable editpolicies
		// removeEditPolicy(org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles.CONNECTION_HANDLES_ROLE);
	}
	/**
	 * @generated
	 */
	protected LayoutEditPolicy createLayoutEditPolicy(){
		FlowLayoutEditPolicy lep = new FlowLayoutEditPolicy(){
			protected Command createAddCommand(EditPart child,EditPart after){
				return null;
			}
			protected Command createMoveChildCommand(EditPart child,EditPart after){
				return null;
			}
			protected Command getCreateCommand(CreateRequest request){
				return null;
			}
		};
		return lep;
	}
	/**
	 * @generated
	 */
	protected IFigure createNodeShape(){
		return primaryShape = new BuiltInActionFigure();
	}
	/**
	 * @generated
	 */
	public BuiltInActionFigure getPrimaryShape(){
		return (BuiltInActionFigure) primaryShape;
	}
	/**
	 * @generated
	 */
	protected boolean addFixedChild(EditPart childEditPart){
		if(childEditPart instanceof BuiltInActionNameKindEditPart){
			((BuiltInActionNameKindEditPart) childEditPart).setLabel(getPrimaryShape().getFigureBuiltInActionNameFigure());
			return true;
		}
		return false;
	}
	/**
	 * @generated
	 */
	protected boolean removeFixedChild(EditPart childEditPart){
		if(childEditPart instanceof BuiltInActionNameKindEditPart){
			return true;
		}
		return false;
	}
	/**
	 * @generated
	 */
	protected void addChildVisual(EditPart childEditPart,int index){
		if(addFixedChild(childEditPart)){
			return;
		}
		super.addChildVisual(childEditPart, -1);
	}
	/**
	 * @generated
	 */
	protected void removeChildVisual(EditPart childEditPart){
		if(removeFixedChild(childEditPart)){
			return;
		}
		super.removeChildVisual(childEditPart);
	}
	/**
	 * @generated
	 */
	protected IFigure getContentPaneFor(IGraphicalEditPart editPart){
		return getContentPane();
	}
	/**
	 * @generated
	 */
	protected NodeFigure createNodePlate(){
		String prefElementId = "BuiltInAction";
		IPreferenceStore store = UimDiagramEditorPlugin.getInstance().getPreferenceStore();
		String preferenceConstantWitdh = PreferenceInitializerForElementHelper.getpreferenceKey(getNotationView(), prefElementId,
				PreferenceConstantHelper.WIDTH);
		String preferenceConstantHeight = PreferenceInitializerForElementHelper.getpreferenceKey(getNotationView(), prefElementId,
				PreferenceConstantHelper.HEIGHT);
		DefaultSizeNodeFigure result = new DefaultSizeNodeFigure(store.getInt(preferenceConstantWitdh), store.getInt(preferenceConstantHeight));
		return result;
	}
	/**
	 * Creates figure for this edit part.
	 * 
	 * Body of this method does not depend on settings in generation model so you may safely remove <i>generated</i> tag and modify it.
	 * 
	 * @generated
	 */
	protected NodeFigure createNodeFigure(){
		NodeFigure figure = createNodePlate();
		figure.setLayoutManager(new StackLayout());
		IFigure shape = createNodeShape();
		figure.add(shape);
		contentPane = setupContentPane(shape);
		return figure;
	}
	/**
	 * Default implementation treats passed figure as content pane. Respects layout one may have set for generated figure.
	 * 
	 * @param nodeShape
	 *          instance of generated figure class
	 * @generated
	 */
	protected IFigure setupContentPane(IFigure nodeShape){
		if(nodeShape.getLayoutManager() == null){
			ConstrainedToolbarLayout layout = new ConstrainedToolbarLayout();
			layout.setSpacing(5);
			nodeShape.setLayoutManager(layout);
		}
		return nodeShape; // use nodeShape itself as contentPane
	}
	/**
	 * @generated
	 */
	public IFigure getContentPane(){
		if(contentPane != null){
			return contentPane;
		}
		return super.getContentPane();
	}
	/**
	 * @generated
	 */
	protected void setForegroundColor(Color color){
		if(primaryShape != null){
			primaryShape.setForegroundColor(color);
		}
	}
	/**
	 * @generated
	 */
	protected void setLineWidth(int width){
		if(primaryShape instanceof Shape){
			((Shape) primaryShape).setLineWidth(width);
		}
	}
	/**
	 * @generated
	 */
	protected void setLineType(int style){
		if(primaryShape instanceof Shape){
			((Shape) primaryShape).setLineStyle(style);
		}
	}
	/**
	 * @generated
	 */
	public EditPart getPrimaryChildEditPart(){
		return getChildBySemanticHint(UimVisualIDRegistry.getType(BuiltInActionNameKindEditPart.VISUAL_ID));
	}
	/**
	 * @generated not
	 */
	public static class BuiltInActionFigure extends RectangleFigure{
		/**
		 * @generated not
		 */
		private WrappingLabel fFigureBuiltInActionNameFigure;
		/**
		 * @generated not
		 */
		public BuiltInActionFigure(){
			FlowLayout layoutThis = new FlowLayout();
			layoutThis.setStretchMinorAxis(false);
			layoutThis.setMinorAlignment(FlowLayout.ALIGN_LEFTTOP);
			layoutThis.setMajorAlignment(FlowLayout.ALIGN_LEFTTOP);
			layoutThis.setMajorSpacing(5);
			layoutThis.setMinorSpacing(5);
			layoutThis.setHorizontal(true);
			this.setLayoutManager(layoutThis);
			createContents();
			fFigureBuiltInActionNameFigure.addMouseListener(new MouseListener(){
				@Override
				public void mousePressed(MouseEvent me){
					mouseDoubleClicked(me);
				}
				@Override
				public void mouseReleased(MouseEvent me){
					mouseDoubleClicked(me);
				}
				@Override
				public void mouseDoubleClicked(MouseEvent me){
				}
			});
		}
		/**
		 * @generated not
		 */
		private void createContents(){
			fFigureBuiltInActionNameFigure = new WrappingLabel();
			fFigureBuiltInActionNameFigure.setText("<...>");
			this.add(fFigureBuiltInActionNameFigure);
		}
		/**
		 * @generated not
		 */
		public WrappingLabel getFigureBuiltInActionNameFigure(){
			return fFigureBuiltInActionNameFigure;
		}
		@Override
		public void paint(Graphics graphics){
			final Image image = new Image(Display.getDefault(), getBounds().width, getBounds().height);
			Composite c = new Composite(ControlPainter.getFakeShell(), SWT.NONE);
			c.setSize(getBounds().width, getBounds().height);
			c.setLayout(new FillLayout());
			Button button = new Button(c, SWT.PUSH);
			button.setText(fFigureBuiltInActionNameFigure.getText());
			button.setBackground(ColorConstants.white);
			button.setForeground(ColorConstants.black);
			GC gc = new GC(image);
			c.layout();
			button.print(gc);
			c.dispose();
			// super.paint(graphics);
			Point copy = getLocation().getCopy();
			graphics.drawImage(image, copy.x + 1, copy.y + 1);
		}
	}
	/**
	 * @generated
	 */
	@Override
	public Object getPreferredValue(EStructuralFeature feature){
		IPreferenceStore preferenceStore = (IPreferenceStore) getDiagramPreferencesHint().getPreferenceStore();
		Object result = null;
		if(feature == NotationPackage.eINSTANCE.getLineStyle_LineColor() || feature == NotationPackage.eINSTANCE.getFontStyle_FontColor()
				|| feature == NotationPackage.eINSTANCE.getFillStyle_FillColor()){
			String prefColor = null;
			if(feature == NotationPackage.eINSTANCE.getLineStyle_LineColor()){
				prefColor = PreferenceConstantHelper.getElementConstant("BuiltInAction", PreferenceConstantHelper.COLOR_LINE);
			}else if(feature == NotationPackage.eINSTANCE.getFontStyle_FontColor()){
				prefColor = PreferenceConstantHelper.getElementConstant("BuiltInAction", PreferenceConstantHelper.COLOR_FONT);
			}else if(feature == NotationPackage.eINSTANCE.getFillStyle_FillColor()){
				prefColor = PreferenceConstantHelper.getElementConstant("BuiltInAction", PreferenceConstantHelper.COLOR_FILL);
			}
			result = FigureUtilities.RGBToInteger(PreferenceConverter.getColor((IPreferenceStore) preferenceStore, prefColor));
		}else if(feature == NotationPackage.eINSTANCE.getFillStyle_Transparency()
				|| feature == NotationPackage.eINSTANCE.getFillStyle_Gradient()){
			String prefGradient = PreferenceConstantHelper.getElementConstant("BuiltInAction", PreferenceConstantHelper.COLOR_GRADIENT);
			GradientPreferenceConverter gradientPreferenceConverter = new GradientPreferenceConverter(preferenceStore.getString(prefGradient));
			if(feature == NotationPackage.eINSTANCE.getFillStyle_Transparency()){
				result = new Integer(gradientPreferenceConverter.getTransparency());
			}else if(feature == NotationPackage.eINSTANCE.getFillStyle_Gradient()){
				result = gradientPreferenceConverter.getGradientData();
			}
		}
		if(result == null){
			result = getStructuralFeatureValue(feature);
		}
		return result;
	}
}
