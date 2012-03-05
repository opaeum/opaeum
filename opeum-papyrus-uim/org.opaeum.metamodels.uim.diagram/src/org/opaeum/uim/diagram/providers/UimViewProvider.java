package org.opaeum.uim.diagram.providers;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.gmf.runtime.common.core.service.AbstractProvider;
import org.eclipse.gmf.runtime.common.core.service.IOperation;
import org.eclipse.gmf.runtime.diagram.core.preferences.PreferencesHint;
import org.eclipse.gmf.runtime.diagram.core.providers.IViewProvider;
import org.eclipse.gmf.runtime.diagram.core.services.view.CreateDiagramViewOperation;
import org.eclipse.gmf.runtime.diagram.core.services.view.CreateEdgeViewOperation;
import org.eclipse.gmf.runtime.diagram.core.services.view.CreateNodeViewOperation;
import org.eclipse.gmf.runtime.diagram.core.services.view.CreateViewForKindOperation;
import org.eclipse.gmf.runtime.diagram.core.services.view.CreateViewOperation;
import org.eclipse.gmf.runtime.diagram.core.util.ViewUtil;
import org.eclipse.gmf.runtime.draw2d.ui.figures.FigureUtilities;
import org.eclipse.gmf.runtime.emf.core.util.EMFCoreUtil;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.emf.type.core.IHintedType;
import org.eclipse.gmf.runtime.notation.DecorationNode;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.FillStyle;
import org.eclipse.gmf.runtime.notation.FontStyle;
import org.eclipse.gmf.runtime.notation.MeasurementUnit;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.NotationFactory;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.Shape;
import org.eclipse.gmf.runtime.notation.TitleStyle;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferenceConverter;
import org.eclipse.papyrus.infra.gmfdiag.preferences.utils.GradientPreferenceConverter;
import org.eclipse.papyrus.infra.gmfdiag.preferences.utils.PreferenceConstantHelper;
import org.eclipse.papyrus.uml.diagram.common.helper.PreferenceInitializerForElementHelper;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.FontData;
import org.opaeum.uim.diagram.edit.parts.BuiltInActionEditPart;
import org.opaeum.uim.diagram.edit.parts.BuiltInActionNameKindEditPart;
import org.opaeum.uim.diagram.edit.parts.GridPanelEditPart;
import org.opaeum.uim.diagram.edit.parts.GridPanelGridPanelChildrenCompartmentEditPart;
import org.opaeum.uim.diagram.edit.parts.GridPanelNameEditPart;
import org.opaeum.uim.diagram.edit.parts.HorizontalPanel2EditPart;
import org.opaeum.uim.diagram.edit.parts.HorizontalPanelEditPart;
import org.opaeum.uim.diagram.edit.parts.LinkToEntityEditPart;
import org.opaeum.uim.diagram.edit.parts.LinkToOperationEditPart;
import org.opaeum.uim.diagram.edit.parts.OperationActionEditPart;
import org.opaeum.uim.diagram.edit.parts.TransitionActionEditPart;
import org.opaeum.uim.diagram.edit.parts.UimDataTableDataTableColumnCompartmentEditPart;
import org.opaeum.uim.diagram.edit.parts.UimDataTableEditPart;
import org.opaeum.uim.diagram.edit.parts.UimField2EditPart;
import org.opaeum.uim.diagram.edit.parts.UimFieldEditPart;
import org.opaeum.uim.diagram.edit.parts.UimFieldNameEditPart;
import org.opaeum.uim.diagram.edit.parts.UserInterfaceEditPart;
import org.opaeum.uim.diagram.edit.parts.VerticalPanel2EditPart;
import org.opaeum.uim.diagram.edit.parts.VerticalPanelEditPart;
import org.opaeum.uim.diagram.part.UimVisualIDRegistry;

/**
 * @generated
 */
public class UimViewProvider extends AbstractProvider implements IViewProvider{
	/**
	 * @generated
	 */
	public final boolean provides(IOperation operation){
		if(operation instanceof CreateViewForKindOperation){
			return provides((CreateViewForKindOperation) operation);
		}
		assert operation instanceof CreateViewOperation;
		if(operation instanceof CreateDiagramViewOperation){
			return provides((CreateDiagramViewOperation) operation);
		}else if(operation instanceof CreateEdgeViewOperation){
			return provides((CreateEdgeViewOperation) operation);
		}else if(operation instanceof CreateNodeViewOperation){
			return provides((CreateNodeViewOperation) operation);
		}
		return false;
	}
	/**
	 * @generated
	 */
	protected boolean provides(CreateViewForKindOperation op){
		/*
		 if (op.getViewKind() == Node.class)
		 return getNodeViewClass(op.getSemanticAdapter(), op.getContainerView(), op.getSemanticHint()) != null;
		 if (op.getViewKind() == Edge.class)
		 return getEdgeViewClass(op.getSemanticAdapter(), op.getContainerView(), op.getSemanticHint()) != null;
		 */
		// check Diagram Type should be the class diagram
		String modelID = UimVisualIDRegistry.getModelID(op.getContainerView());
		if(!getDiagramProvidedId().equals(modelID)){
			return false;
		}
		int visualID = UimVisualIDRegistry.getVisualID(op.getSemanticHint());
		if(Node.class.isAssignableFrom(op.getViewKind())){
			return UimVisualIDRegistry.canCreateNode(op.getContainerView(), visualID);
		}
		return true;
	}
	/**
	 * @generated
	 */
	protected String getDiagramProvidedId(){
		/*
		 * Indicates for which diagram this provider works for.
		 * <p>
		 * This method can be overloaded when diagram editor inherits from another one, but should never be <code>null</code>
		 * </p>
		 * 
		 * @return the unique identifier of the diagram for which views are provided.
		 */
		return UserInterfaceEditPart.MODEL_ID;
	}
	/**
	 * @generated
	 */
	protected boolean provides(CreateDiagramViewOperation op){
		return UserInterfaceEditPart.MODEL_ID.equals(op.getSemanticHint())
				&& UimVisualIDRegistry.getDiagramVisualID(getSemanticElement(op.getSemanticAdapter())) != -1;
	}
	/**
	 * @generated
	 */
	protected boolean provides(CreateNodeViewOperation op){
		if(op.getContainerView() == null){
			return false;
		}
		IElementType elementType = getSemanticElementType(op.getSemanticAdapter());
		EObject domainElement = getSemanticElement(op.getSemanticAdapter());
		int visualID;
		if(op.getSemanticHint() == null){
			// Semantic hint is not specified. Can be a result of call from CanonicalEditPolicy.
			// In this situation there should be NO elementType, visualID will be determined
			// by VisualIDRegistry.getNodeVisualID() for domainElement.
			if(elementType != null || domainElement == null){
				return false;
			}
			visualID = UimVisualIDRegistry.getNodeVisualID(op.getContainerView(), domainElement);
		}else{
			visualID = UimVisualIDRegistry.getVisualID(op.getSemanticHint());
			if(elementType != null){
				if(!UimElementTypes.isKnownElementType(elementType) || (!(elementType instanceof IHintedType))){
					return false; // foreign element type
				}
				String elementTypeHint = ((IHintedType) elementType).getSemanticHint();
				if(!op.getSemanticHint().equals(elementTypeHint)){
					return false; // if semantic hint is specified it should be the same as in element type
				}
				if(domainElement != null && visualID != UimVisualIDRegistry.getNodeVisualID(op.getContainerView(), domainElement)){
					return false; // visual id for node EClass should match visual id from element type
				}
			}else{
				if(!UserInterfaceEditPart.MODEL_ID.equals(UimVisualIDRegistry.getModelID(op.getContainerView()))){
					return false; // foreign diagram
				}
				switch(visualID){
				case GridPanelEditPart.VISUAL_ID:
				case UimFieldEditPart.VISUAL_ID:
				case BuiltInActionEditPart.VISUAL_ID:
				case HorizontalPanel2EditPart.VISUAL_ID:
				case VerticalPanel2EditPart.VISUAL_ID:
				case TransitionActionEditPart.VISUAL_ID:
				case OperationActionEditPart.VISUAL_ID:
				case LinkToOperationEditPart.VISUAL_ID:
				case LinkToEntityEditPart.VISUAL_ID:
				case UimDataTableEditPart.VISUAL_ID:
				case HorizontalPanelEditPart.VISUAL_ID:
				case VerticalPanelEditPart.VISUAL_ID:
				case UimField2EditPart.VISUAL_ID:
					if(domainElement == null || visualID != UimVisualIDRegistry.getNodeVisualID(op.getContainerView(), domainElement)){
						return false; // visual id in semantic hint should match visual id for domain element
					}
					break;
				default:
					return false;
				}
			}
		}
		return GridPanelEditPart.VISUAL_ID == visualID || HorizontalPanelEditPart.VISUAL_ID == visualID
				|| VerticalPanelEditPart.VISUAL_ID == visualID || UimFieldEditPart.VISUAL_ID == visualID
				|| BuiltInActionEditPart.VISUAL_ID == visualID || HorizontalPanel2EditPart.VISUAL_ID == visualID
				|| VerticalPanel2EditPart.VISUAL_ID == visualID || TransitionActionEditPart.VISUAL_ID == visualID
				|| OperationActionEditPart.VISUAL_ID == visualID || LinkToOperationEditPart.VISUAL_ID == visualID
				|| LinkToEntityEditPart.VISUAL_ID == visualID || UimDataTableEditPart.VISUAL_ID == visualID
				|| UimField2EditPart.VISUAL_ID == visualID;
	}
	/**
	 * @generated
	 */
	protected boolean provides(CreateEdgeViewOperation op){
		IElementType elementType = getSemanticElementType(op.getSemanticAdapter());
		if(!UimElementTypes.isKnownElementType(elementType) || (!(elementType instanceof IHintedType))){
			return false; // foreign element type
		}
		String elementTypeHint = ((IHintedType) elementType).getSemanticHint();
		if(elementTypeHint == null || (op.getSemanticHint() != null && !elementTypeHint.equals(op.getSemanticHint()))){
			return false; // our hint is visual id and must be specified, and it should be the same as in element type
		}
		int visualID = UimVisualIDRegistry.getVisualID(elementTypeHint);
		EObject domainElement = getSemanticElement(op.getSemanticAdapter());
		if(domainElement != null && visualID != UimVisualIDRegistry.getLinkWithClassVisualID(domainElement)){
			return false; // visual id for link EClass should match visual id from element type
		}
		return true;
	}
	/**
	 * @generated
	 */
	public Diagram createDiagram(IAdaptable semanticAdapter,String diagramKind,PreferencesHint preferencesHint){
		Diagram diagram = NotationFactory.eINSTANCE.createDiagram();
		diagram.getStyles().add(NotationFactory.eINSTANCE.createDiagramStyle());
		diagram.setType(UserInterfaceEditPart.MODEL_ID);
		diagram.setElement(getSemanticElement(semanticAdapter));
		diagram.setMeasurementUnit(MeasurementUnit.PIXEL_LITERAL);
		return diagram;
	}
	/**
	 * @generated
	 */
	public Node createNode(IAdaptable semanticAdapter,View containerView,String semanticHint,int index,boolean persisted,
			PreferencesHint preferencesHint){
		final EObject domainElement = getSemanticElement(semanticAdapter);
		final int visualID;
		if(semanticHint == null){
			visualID = UimVisualIDRegistry.getNodeVisualID(containerView, domainElement);
		}else{
			visualID = UimVisualIDRegistry.getVisualID(semanticHint);
		}
		switch(visualID){
		case GridPanelEditPart.VISUAL_ID:
			return createGridPanel_2004(domainElement, containerView, index, persisted, preferencesHint);
		case HorizontalPanelEditPart.VISUAL_ID:
			return createHorizontalPanel_2005(domainElement, containerView, index, persisted, preferencesHint);
		case VerticalPanelEditPart.VISUAL_ID:
			return createVerticalPanel_2006(domainElement, containerView, index, persisted, preferencesHint);
		case UimFieldEditPart.VISUAL_ID:
			return createUimField_3001(domainElement, containerView, index, persisted, preferencesHint);
		case BuiltInActionEditPart.VISUAL_ID:
			return createBuiltInAction_3002(domainElement, containerView, index, persisted, preferencesHint);
		case HorizontalPanel2EditPart.VISUAL_ID:
			return createHorizontalPanel_3003(domainElement, containerView, index, persisted, preferencesHint);
		case VerticalPanel2EditPart.VISUAL_ID:
			return createVerticalPanel_3004(domainElement, containerView, index, persisted, preferencesHint);
		case TransitionActionEditPart.VISUAL_ID:
			return createTransitionAction_3005(domainElement, containerView, index, persisted, preferencesHint);
		case OperationActionEditPart.VISUAL_ID:
			return createOperationAction_3006(domainElement, containerView, index, persisted, preferencesHint);
		case LinkToOperationEditPart.VISUAL_ID:
			return createLinkToOperation_3007(domainElement, containerView, index, persisted, preferencesHint);
		case LinkToEntityEditPart.VISUAL_ID:
			return createLinkToEntity_3008(domainElement, containerView, index, persisted, preferencesHint);
		case UimDataTableEditPart.VISUAL_ID:
			return createUimDataTable_3009(domainElement, containerView, index, persisted, preferencesHint);
		case UimField2EditPart.VISUAL_ID:
			return createUimField_3010(domainElement, containerView, index, persisted, preferencesHint);
		}
		// can't happen, provided #provides(CreateNodeViewOperation) is correct
		return null;
	}
	/**
	 * @generated
	 */
	public Edge createEdge(IAdaptable semanticAdapter,View containerView,String semanticHint,int index,boolean persisted,
			PreferencesHint preferencesHint){
		IElementType elementType = getSemanticElementType(semanticAdapter);
		String elementTypeHint = ((IHintedType) elementType).getSemanticHint();
		switch(UimVisualIDRegistry.getVisualID(elementTypeHint)){
		}
		// can never happen, provided #provides(CreateEdgeViewOperation) is correct
		return null;
	}
	/**
	 * @generated
	 */
	public Node createGridPanel_2004(EObject domainElement,View containerView,int index,boolean persisted,PreferencesHint preferencesHint){
		Shape node = NotationFactory.eINSTANCE.createShape();
		node.setLayoutConstraint(NotationFactory.eINSTANCE.createBounds());
		node.setType(UimVisualIDRegistry.getType(GridPanelEditPart.VISUAL_ID));
		ViewUtil.insertChildView(containerView, node, index, persisted);
		node.setElement(domainElement);
		stampShortcut(containerView, node);
		// initializeFromPreferences 
		final IPreferenceStore prefStore = (IPreferenceStore) preferencesHint.getPreferenceStore();
		PreferenceInitializerForElementHelper.initForegroundFromPrefs(node, prefStore, "GridPanel");
		PreferenceInitializerForElementHelper.initFontStyleFromPrefs(node, prefStore, "GridPanel");
		PreferenceInitializerForElementHelper.initBackgroundFromPrefs(node, prefStore, "GridPanel");
		Node label5004 = createLabel(node, UimVisualIDRegistry.getType(GridPanelNameEditPart.VISUAL_ID));
		createCompartment(node, UimVisualIDRegistry.getType(GridPanelGridPanelChildrenCompartmentEditPart.VISUAL_ID), false, false, false,
				false);
		PreferenceInitializerForElementHelper.initCompartmentsStatusFromPrefs(node, prefStore, "GridPanel");
		return node;
	}
	/**
	 * @generated
	 */
	public Node createHorizontalPanel_2005(EObject domainElement,View containerView,int index,boolean persisted,
			PreferencesHint preferencesHint){
		Shape node = NotationFactory.eINSTANCE.createShape();
		node.setLayoutConstraint(NotationFactory.eINSTANCE.createBounds());
		node.setType(UimVisualIDRegistry.getType(HorizontalPanelEditPart.VISUAL_ID));
		ViewUtil.insertChildView(containerView, node, index, persisted);
		node.setElement(domainElement);
		stampShortcut(containerView, node);
		// initializeFromPreferences 
		final IPreferenceStore prefStore = (IPreferenceStore) preferencesHint.getPreferenceStore();
		PreferenceInitializerForElementHelper.initForegroundFromPrefs(node, prefStore, "HorizontalPanel");
		PreferenceInitializerForElementHelper.initFontStyleFromPrefs(node, prefStore, "HorizontalPanel");
		PreferenceInitializerForElementHelper.initBackgroundFromPrefs(node, prefStore, "HorizontalPanel");
		return node;
	}
	/**
	 * @generated
	 */
	public Node createVerticalPanel_2006(EObject domainElement,View containerView,int index,boolean persisted,PreferencesHint preferencesHint){
		Shape node = NotationFactory.eINSTANCE.createShape();
		node.setLayoutConstraint(NotationFactory.eINSTANCE.createBounds());
		node.setType(UimVisualIDRegistry.getType(VerticalPanelEditPart.VISUAL_ID));
		ViewUtil.insertChildView(containerView, node, index, persisted);
		node.setElement(domainElement);
		stampShortcut(containerView, node);
		// initializeFromPreferences 
		final IPreferenceStore prefStore = (IPreferenceStore) preferencesHint.getPreferenceStore();
		PreferenceInitializerForElementHelper.initForegroundFromPrefs(node, prefStore, "VerticalPanel");
		PreferenceInitializerForElementHelper.initFontStyleFromPrefs(node, prefStore, "VerticalPanel");
		PreferenceInitializerForElementHelper.initBackgroundFromPrefs(node, prefStore, "VerticalPanel");
		return node;
	}
	/**
	 * @generated
	 */
	public Node createUimField_3001(EObject domainElement,View containerView,int index,boolean persisted,PreferencesHint preferencesHint){
		Shape node = NotationFactory.eINSTANCE.createShape();
		node.setLayoutConstraint(NotationFactory.eINSTANCE.createBounds());
		node.setType(UimVisualIDRegistry.getType(UimFieldEditPart.VISUAL_ID));
		ViewUtil.insertChildView(containerView, node, index, persisted);
		node.setElement(domainElement);
		// initializeFromPreferences 
		final IPreferenceStore prefStore = (IPreferenceStore) preferencesHint.getPreferenceStore();
		PreferenceInitializerForElementHelper.initForegroundFromPrefs(node, prefStore, "UimField");
		PreferenceInitializerForElementHelper.initFontStyleFromPrefs(node, prefStore, "UimField");
		PreferenceInitializerForElementHelper.initBackgroundFromPrefs(node, prefStore, "UimField");
		Node label5001 = createLabel(node, UimVisualIDRegistry.getType(UimFieldNameEditPart.VISUAL_ID));
		return node;
	}
	/**
	 * @generated
	 */
	public Node createBuiltInAction_3002(EObject domainElement,View containerView,int index,boolean persisted,PreferencesHint preferencesHint){
		Shape node = NotationFactory.eINSTANCE.createShape();
		node.setLayoutConstraint(NotationFactory.eINSTANCE.createBounds());
		node.setType(UimVisualIDRegistry.getType(BuiltInActionEditPart.VISUAL_ID));
		ViewUtil.insertChildView(containerView, node, index, persisted);
		node.setElement(domainElement);
		// initializeFromPreferences 
		final IPreferenceStore prefStore = (IPreferenceStore) preferencesHint.getPreferenceStore();
		PreferenceInitializerForElementHelper.initForegroundFromPrefs(node, prefStore, "BuiltInAction");
		PreferenceInitializerForElementHelper.initFontStyleFromPrefs(node, prefStore, "BuiltInAction");
		PreferenceInitializerForElementHelper.initBackgroundFromPrefs(node, prefStore, "BuiltInAction");
		Node label5003 = createLabel(node, UimVisualIDRegistry.getType(BuiltInActionNameKindEditPart.VISUAL_ID));
		return node;
	}
	/**
	 * @generated
	 */
	public Node createHorizontalPanel_3003(EObject domainElement,View containerView,int index,boolean persisted,
			PreferencesHint preferencesHint){
		Shape node = NotationFactory.eINSTANCE.createShape();
		node.setLayoutConstraint(NotationFactory.eINSTANCE.createBounds());
		node.setType(UimVisualIDRegistry.getType(HorizontalPanel2EditPart.VISUAL_ID));
		ViewUtil.insertChildView(containerView, node, index, persisted);
		node.setElement(domainElement);
		// initializeFromPreferences 
		final IPreferenceStore prefStore = (IPreferenceStore) preferencesHint.getPreferenceStore();
		PreferenceInitializerForElementHelper.initForegroundFromPrefs(node, prefStore, "HorizontalPanel");
		PreferenceInitializerForElementHelper.initFontStyleFromPrefs(node, prefStore, "HorizontalPanel");
		PreferenceInitializerForElementHelper.initBackgroundFromPrefs(node, prefStore, "HorizontalPanel");
		return node;
	}
	/**
	 * @generated
	 */
	public Node createVerticalPanel_3004(EObject domainElement,View containerView,int index,boolean persisted,PreferencesHint preferencesHint){
		Shape node = NotationFactory.eINSTANCE.createShape();
		node.setLayoutConstraint(NotationFactory.eINSTANCE.createBounds());
		node.setType(UimVisualIDRegistry.getType(VerticalPanel2EditPart.VISUAL_ID));
		ViewUtil.insertChildView(containerView, node, index, persisted);
		node.setElement(domainElement);
		// initializeFromPreferences 
		final IPreferenceStore prefStore = (IPreferenceStore) preferencesHint.getPreferenceStore();
		PreferenceInitializerForElementHelper.initForegroundFromPrefs(node, prefStore, "VerticalPanel");
		PreferenceInitializerForElementHelper.initFontStyleFromPrefs(node, prefStore, "VerticalPanel");
		PreferenceInitializerForElementHelper.initBackgroundFromPrefs(node, prefStore, "VerticalPanel");
		return node;
	}
	/**
	 * @generated
	 */
	public Node createTransitionAction_3005(EObject domainElement,View containerView,int index,boolean persisted,
			PreferencesHint preferencesHint){
		Shape node = NotationFactory.eINSTANCE.createShape();
		node.setLayoutConstraint(NotationFactory.eINSTANCE.createBounds());
		node.setType(UimVisualIDRegistry.getType(TransitionActionEditPart.VISUAL_ID));
		ViewUtil.insertChildView(containerView, node, index, persisted);
		node.setElement(domainElement);
		// initializeFromPreferences 
		final IPreferenceStore prefStore = (IPreferenceStore) preferencesHint.getPreferenceStore();
		PreferenceInitializerForElementHelper.initForegroundFromPrefs(node, prefStore, "TransitionAction");
		PreferenceInitializerForElementHelper.initFontStyleFromPrefs(node, prefStore, "TransitionAction");
		PreferenceInitializerForElementHelper.initBackgroundFromPrefs(node, prefStore, "TransitionAction");
		return node;
	}
	/**
	 * @generated
	 */
	public Node createOperationAction_3006(EObject domainElement,View containerView,int index,boolean persisted,
			PreferencesHint preferencesHint){
		Shape node = NotationFactory.eINSTANCE.createShape();
		node.setLayoutConstraint(NotationFactory.eINSTANCE.createBounds());
		node.setType(UimVisualIDRegistry.getType(OperationActionEditPart.VISUAL_ID));
		ViewUtil.insertChildView(containerView, node, index, persisted);
		node.setElement(domainElement);
		// initializeFromPreferences 
		final IPreferenceStore prefStore = (IPreferenceStore) preferencesHint.getPreferenceStore();
		PreferenceInitializerForElementHelper.initForegroundFromPrefs(node, prefStore, "OperationAction");
		PreferenceInitializerForElementHelper.initFontStyleFromPrefs(node, prefStore, "OperationAction");
		PreferenceInitializerForElementHelper.initBackgroundFromPrefs(node, prefStore, "OperationAction");
		return node;
	}
	/**
	 * @generated
	 */
	public Node createLinkToOperation_3007(EObject domainElement,View containerView,int index,boolean persisted,
			PreferencesHint preferencesHint){
		Shape node = NotationFactory.eINSTANCE.createShape();
		node.setLayoutConstraint(NotationFactory.eINSTANCE.createBounds());
		node.setType(UimVisualIDRegistry.getType(LinkToOperationEditPart.VISUAL_ID));
		ViewUtil.insertChildView(containerView, node, index, persisted);
		node.setElement(domainElement);
		// initializeFromPreferences 
		final IPreferenceStore prefStore = (IPreferenceStore) preferencesHint.getPreferenceStore();
		PreferenceInitializerForElementHelper.initForegroundFromPrefs(node, prefStore, "LinkToOperation");
		PreferenceInitializerForElementHelper.initFontStyleFromPrefs(node, prefStore, "LinkToOperation");
		PreferenceInitializerForElementHelper.initBackgroundFromPrefs(node, prefStore, "LinkToOperation");
		return node;
	}
	/**
	 * @generated
	 */
	public Node createLinkToEntity_3008(EObject domainElement,View containerView,int index,boolean persisted,PreferencesHint preferencesHint){
		Shape node = NotationFactory.eINSTANCE.createShape();
		node.setLayoutConstraint(NotationFactory.eINSTANCE.createBounds());
		node.setType(UimVisualIDRegistry.getType(LinkToEntityEditPart.VISUAL_ID));
		ViewUtil.insertChildView(containerView, node, index, persisted);
		node.setElement(domainElement);
		// initializeFromPreferences 
		final IPreferenceStore prefStore = (IPreferenceStore) preferencesHint.getPreferenceStore();
		PreferenceInitializerForElementHelper.initForegroundFromPrefs(node, prefStore, "LinkToEntity");
		PreferenceInitializerForElementHelper.initFontStyleFromPrefs(node, prefStore, "LinkToEntity");
		PreferenceInitializerForElementHelper.initBackgroundFromPrefs(node, prefStore, "LinkToEntity");
		return node;
	}
	/**
	 * @generated
	 */
	public Node createUimDataTable_3009(EObject domainElement,View containerView,int index,boolean persisted,PreferencesHint preferencesHint){
		Shape node = NotationFactory.eINSTANCE.createShape();
		node.setLayoutConstraint(NotationFactory.eINSTANCE.createBounds());
		node.setType(UimVisualIDRegistry.getType(UimDataTableEditPart.VISUAL_ID));
		ViewUtil.insertChildView(containerView, node, index, persisted);
		node.setElement(domainElement);
		// initializeFromPreferences 
		final IPreferenceStore prefStore = (IPreferenceStore) preferencesHint.getPreferenceStore();
		PreferenceInitializerForElementHelper.initForegroundFromPrefs(node, prefStore, "UimDataTable");
		PreferenceInitializerForElementHelper.initFontStyleFromPrefs(node, prefStore, "UimDataTable");
		PreferenceInitializerForElementHelper.initBackgroundFromPrefs(node, prefStore, "UimDataTable");
		createCompartment(node, UimVisualIDRegistry.getType(UimDataTableDataTableColumnCompartmentEditPart.VISUAL_ID), false, false, false,
				false);
		PreferenceInitializerForElementHelper.initCompartmentsStatusFromPrefs(node, prefStore, "UimDataTable");
		return node;
	}
	/**
	 * @generated
	 */
	public Node createUimField_3010(EObject domainElement,View containerView,int index,boolean persisted,PreferencesHint preferencesHint){
		Shape node = NotationFactory.eINSTANCE.createShape();
		node.setLayoutConstraint(NotationFactory.eINSTANCE.createBounds());
		node.setType(UimVisualIDRegistry.getType(UimField2EditPart.VISUAL_ID));
		ViewUtil.insertChildView(containerView, node, index, persisted);
		node.setElement(domainElement);
		// initializeFromPreferences 
		final IPreferenceStore prefStore = (IPreferenceStore) preferencesHint.getPreferenceStore();
		PreferenceInitializerForElementHelper.initForegroundFromPrefs(node, prefStore, "UimField");
		PreferenceInitializerForElementHelper.initFontStyleFromPrefs(node, prefStore, "UimField");
		PreferenceInitializerForElementHelper.initBackgroundFromPrefs(node, prefStore, "UimField");
		return node;
	}
	/**
	 * @generated
	 */
	protected void stampShortcut(View containerView,Node target){
		if(!UserInterfaceEditPart.MODEL_ID.equals(UimVisualIDRegistry.getModelID(containerView))){
			EAnnotation shortcutAnnotation = EcoreFactory.eINSTANCE.createEAnnotation();
			shortcutAnnotation.setSource("Shortcut"); //$NON-NLS-1$
			shortcutAnnotation.getDetails().put("modelID", UserInterfaceEditPart.MODEL_ID); //$NON-NLS-1$
			target.getEAnnotations().add(shortcutAnnotation);
		}
	}
	/**
	 * @generated
	 */
	protected Node createLabel(View owner,String hint){
		DecorationNode rv = NotationFactory.eINSTANCE.createDecorationNode();
		rv.setType(hint);
		ViewUtil.insertChildView(owner, rv, ViewUtil.APPEND, true);
		return rv;
	}
	/**
	 * @generated
	 */
	protected Node createCompartment(View owner,String hint,boolean canCollapse,boolean hasTitle,boolean canSort,boolean canFilter){
		//SemanticListCompartment rv = NotationFactory.eINSTANCE.createSemanticListCompartment();
		//rv.setShowTitle(showTitle);
		//rv.setCollapsed(isCollapsed);
		Node rv;
		if(canCollapse){
			rv = NotationFactory.eINSTANCE.createBasicCompartment();
		}else{
			rv = NotationFactory.eINSTANCE.createDecorationNode();
		}
		rv.setLayoutConstraint(NotationFactory.eINSTANCE.createBounds());
		if(hasTitle){
			TitleStyle ts = NotationFactory.eINSTANCE.createTitleStyle();
			ts.setShowTitle(true);
			rv.getStyles().add(ts);
		}
		if(canSort){
			rv.getStyles().add(NotationFactory.eINSTANCE.createSortingStyle());
		}
		if(canFilter){
			rv.getStyles().add(NotationFactory.eINSTANCE.createFilteringStyle());
		}
		rv.setType(hint);
		ViewUtil.insertChildView(owner, rv, ViewUtil.APPEND, true);
		return rv;
	}
	/**
	 * @generated
	 */
	protected EObject getSemanticElement(IAdaptable semanticAdapter){
		if(semanticAdapter == null){
			return null;
		}
		EObject eObject = (EObject) semanticAdapter.getAdapter(EObject.class);
		if(eObject != null){
			return EMFCoreUtil.resolve(TransactionUtil.getEditingDomain(eObject), eObject);
		}
		return null;
	}
	/**
	 * @generated
	 */
	protected IElementType getSemanticElementType(IAdaptable semanticAdapter){
		if(semanticAdapter == null){
			return null;
		}
		return (IElementType) semanticAdapter.getAdapter(IElementType.class);
	}
	/**
	 * @generated
	 */
	private void initFontStyleFromPrefs(View view,final IPreferenceStore store,String elementName){
		String fontConstant = PreferenceConstantHelper.getElementConstant(elementName, PreferenceConstantHelper.FONT);
		String fontColorConstant = PreferenceConstantHelper.getElementConstant(elementName, PreferenceConstantHelper.COLOR_FONT);
		FontStyle viewFontStyle = (FontStyle) view.getStyle(NotationPackage.Literals.FONT_STYLE);
		if(viewFontStyle != null){
			FontData fontData = PreferenceConverter.getFontData(store, fontConstant);
			viewFontStyle.setFontName(fontData.getName());
			viewFontStyle.setFontHeight(fontData.getHeight());
			viewFontStyle.setBold((fontData.getStyle() & SWT.BOLD) != 0);
			viewFontStyle.setItalic((fontData.getStyle() & SWT.ITALIC) != 0);
			org.eclipse.swt.graphics.RGB fontRGB = PreferenceConverter.getColor(store, fontColorConstant);
			viewFontStyle.setFontColor(FigureUtilities.RGBToInteger(fontRGB).intValue());
		}
	}
	/**
	 * @generated
	 */
	private void initForegroundFromPrefs(View view,final IPreferenceStore store,String elementName){
		String lineColorConstant = PreferenceConstantHelper.getElementConstant(elementName, PreferenceConstantHelper.COLOR_LINE);
		org.eclipse.swt.graphics.RGB lineRGB = PreferenceConverter.getColor(store, lineColorConstant);
		ViewUtil.setStructuralFeatureValue(view, NotationPackage.eINSTANCE.getLineStyle_LineColor(), FigureUtilities.RGBToInteger(lineRGB));
	}
	/**
	 * @generated
	 */
	private void initBackgroundFromPrefs(View view,final IPreferenceStore store,String elementName){
		String fillColorConstant = PreferenceConstantHelper.getElementConstant(elementName, PreferenceConstantHelper.COLOR_FILL);
		String gradientColorConstant = PreferenceConstantHelper.getElementConstant(elementName, PreferenceConstantHelper.COLOR_GRADIENT);
		String gradientPolicyConstant = PreferenceConstantHelper.getElementConstant(elementName, PreferenceConstantHelper.GRADIENT_POLICY);
		org.eclipse.swt.graphics.RGB fillRGB = PreferenceConverter.getColor(store, fillColorConstant);
		ViewUtil.setStructuralFeatureValue(view, NotationPackage.eINSTANCE.getFillStyle_FillColor(), FigureUtilities.RGBToInteger(fillRGB));
		FillStyle fillStyle = (FillStyle) view.getStyle(NotationPackage.Literals.FILL_STYLE);
		fillStyle.setFillColor(FigureUtilities.RGBToInteger(fillRGB).intValue());
		;
		if(store.getBoolean(gradientPolicyConstant)){
			GradientPreferenceConverter gradientPreferenceConverter = new GradientPreferenceConverter(store.getString(gradientColorConstant));
			fillStyle.setGradient(gradientPreferenceConverter.getGradientData());
			fillStyle.setTransparency(gradientPreferenceConverter.getTransparency());
		}
	}
}
