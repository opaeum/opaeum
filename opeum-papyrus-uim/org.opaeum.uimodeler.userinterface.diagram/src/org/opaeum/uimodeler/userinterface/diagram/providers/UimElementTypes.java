package org.opaeum.uimodeler.userinterface.diagram.providers;

import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.gmf.runtime.emf.type.core.ElementTypeRegistry;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.swt.graphics.Image;
import org.opaeum.uim.UimPackage;
import org.opaeum.uim.action.ActionPackage;
import org.opaeum.uim.panel.PanelPackage;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.BuiltInAction2EditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.BuiltInAction3EditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.BuiltInActionEditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.GridPanel2EditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.GridPanelEditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.HorizontalPanel2EditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.HorizontalPanelEditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.LinkToEntity2EditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.LinkToEntityEditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.LinkToOperationEditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.OperationAction2EditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.OperationAction3EditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.OperationActionEditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.TransitionActionEditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.UimDataTableEditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.UimField2EditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.UimFieldEditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.UserInterfaceEditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.VerticalPanel2EditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.VerticalPanelEditPart;
import org.opaeum.uimodeler.userinterface.diagram.part.UimDiagramEditorPlugin;

/**
 * @generated
 */
public class UimElementTypes{
	/**
	 * @generated
	 */
	private UimElementTypes(){
	}
	/**
	 * @generated
	 */
	private static Map<IElementType,ENamedElement> elements;
	/**
	 * @generated
	 */
	private static ImageRegistry imageRegistry;
	/**
	 * @generated
	 */
	private static Set<IElementType> KNOWN_ELEMENT_TYPES;
	/**
	 * @generated
	 */
	public static final IElementType UserInterface_1000 = getElementType("org.opaeum.uimodeler.userinterface.diagram.UserInterface_1000"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType GridPanel_2001 = getElementType("org.opaeum.uimodeler.userinterface.diagram.GridPanel_2001"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType HorizontalPanel_2002 = getElementType("org.opaeum.uimodeler.userinterface.diagram.HorizontalPanel_2002"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType VerticalPanel_2003 = getElementType("org.opaeum.uimodeler.userinterface.diagram.VerticalPanel_2003"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType UimField_3001 = getElementType("org.opaeum.uimodeler.userinterface.diagram.UimField_3001"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType BuiltInAction_3015 = getElementType("org.opaeum.uimodeler.userinterface.diagram.BuiltInAction_3015"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType OperationAction_3016 = getElementType("org.opaeum.uimodeler.userinterface.diagram.OperationAction_3016"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType GridPanel_3017 = getElementType("org.opaeum.uimodeler.userinterface.diagram.GridPanel_3017"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType BuiltInAction_3002 = getElementType("org.opaeum.uimodeler.userinterface.diagram.BuiltInAction_3002"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType HorizontalPanel_3003 = getElementType("org.opaeum.uimodeler.userinterface.diagram.HorizontalPanel_3003"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType VerticalPanel_3004 = getElementType("org.opaeum.uimodeler.userinterface.diagram.VerticalPanel_3004"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType TransitionAction_3005 = getElementType("org.opaeum.uimodeler.userinterface.diagram.TransitionAction_3005"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType OperationAction_3006 = getElementType("org.opaeum.uimodeler.userinterface.diagram.OperationAction_3006"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType LinkToOperation_3007 = getElementType("org.opaeum.uimodeler.userinterface.diagram.LinkToOperation_3007"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType LinkToEntity_3008 = getElementType("org.opaeum.uimodeler.userinterface.diagram.LinkToEntity_3008"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType UimDataTable_3009 = getElementType("org.opaeum.uimodeler.userinterface.diagram.UimDataTable_3009"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType UimField_3010 = getElementType("org.opaeum.uimodeler.userinterface.diagram.UimField_3010"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType LinkToEntity_3011 = getElementType("org.opaeum.uimodeler.userinterface.diagram.LinkToEntity_3011"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType BuiltInAction_3012 = getElementType("org.opaeum.uimodeler.userinterface.diagram.BuiltInAction_3012"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType OperationAction_3014 = getElementType("org.opaeum.uimodeler.userinterface.diagram.OperationAction_3014"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	private static ImageRegistry getImageRegistry(){
		if(imageRegistry == null){
			imageRegistry = new ImageRegistry();
		}
		return imageRegistry;
	}
	/**
	 * @generated
	 */
	private static String getImageRegistryKey(ENamedElement element){
		return element.getName();
	}
	/**
	 * @generated
	 */
	private static ImageDescriptor getProvidedImageDescriptor(ENamedElement element){
		if(element instanceof EStructuralFeature){
			EStructuralFeature feature = ((EStructuralFeature) element);
			EClass eContainingClass = feature.getEContainingClass();
			EClassifier eType = feature.getEType();
			if(eContainingClass != null && !eContainingClass.isAbstract()){
				element = eContainingClass;
			}else if(eType instanceof EClass && !((EClass) eType).isAbstract()){
				element = eType;
			}
		}
		if(element instanceof EClass){
			EClass eClass = (EClass) element;
			if(!eClass.isAbstract()){
				return UimDiagramEditorPlugin.getInstance().getItemImageDescriptor(eClass.getEPackage().getEFactoryInstance().create(eClass));
			}
		}
		// TODO : support structural features
		return null;
	}
	/**
	 * @generated
	 */
	public static ImageDescriptor getImageDescriptor(ENamedElement element){
		String key = getImageRegistryKey(element);
		ImageDescriptor imageDescriptor = getImageRegistry().getDescriptor(key);
		if(imageDescriptor == null){
			imageDescriptor = getProvidedImageDescriptor(element);
			if(imageDescriptor == null){
				imageDescriptor = ImageDescriptor.getMissingImageDescriptor();
			}
			getImageRegistry().put(key, imageDescriptor);
		}
		return imageDescriptor;
	}
	/**
	 * @generated
	 */
	public static Image getImage(ENamedElement element){
		String key = getImageRegistryKey(element);
		Image image = getImageRegistry().get(key);
		if(image == null){
			ImageDescriptor imageDescriptor = getProvidedImageDescriptor(element);
			if(imageDescriptor == null){
				imageDescriptor = ImageDescriptor.getMissingImageDescriptor();
			}
			getImageRegistry().put(key, imageDescriptor);
			image = getImageRegistry().get(key);
		}
		return image;
	}
	/**
	 * @generated
	 */
	public static ImageDescriptor getImageDescriptor(IAdaptable hint){
		ENamedElement element = getElement(hint);
		if(element == null){
			return null;
		}
		return getImageDescriptor(element);
	}
	/**
	 * @generated
	 */
	public static Image getImage(IAdaptable hint){
		ENamedElement element = getElement(hint);
		if(element == null){
			return null;
		}
		return getImage(element);
	}
	/**
	 * Returns 'type' of the ecore object associated with the hint.
	 * 
	 * @generated
	 */
	public static ENamedElement getElement(IAdaptable hint){
		Object type = hint.getAdapter(IElementType.class);
		if(elements == null){
			elements = new IdentityHashMap<IElementType,ENamedElement>();
			elements.put(UserInterface_1000, UimPackage.eINSTANCE.getUserInterface());
			elements.put(GridPanel_2001, PanelPackage.eINSTANCE.getGridPanel());
			elements.put(HorizontalPanel_2002, PanelPackage.eINSTANCE.getHorizontalPanel());
			elements.put(VerticalPanel_2003, PanelPackage.eINSTANCE.getVerticalPanel());
			elements.put(UimField_3001, UimPackage.eINSTANCE.getUimField());
			elements.put(BuiltInAction_3002, ActionPackage.eINSTANCE.getBuiltInAction());
			elements.put(HorizontalPanel_3003, PanelPackage.eINSTANCE.getHorizontalPanel());
			elements.put(VerticalPanel_3004, PanelPackage.eINSTANCE.getVerticalPanel());
			elements.put(TransitionAction_3005, ActionPackage.eINSTANCE.getTransitionAction());
			elements.put(OperationAction_3006, ActionPackage.eINSTANCE.getOperationAction());
			elements.put(LinkToOperation_3007, ActionPackage.eINSTANCE.getLinkToOperation());
			elements.put(LinkToEntity_3008, ActionPackage.eINSTANCE.getLinkToEntity());
			elements.put(UimDataTable_3009, UimPackage.eINSTANCE.getUimDataTable());
			elements.put(UimField_3010, UimPackage.eINSTANCE.getUimField());
			elements.put(LinkToEntity_3011, ActionPackage.eINSTANCE.getLinkToEntity());
			elements.put(BuiltInAction_3012, ActionPackage.eINSTANCE.getBuiltInAction());
			elements.put(OperationAction_3014, ActionPackage.eINSTANCE.getOperationAction());
			elements.put(BuiltInAction_3015, ActionPackage.eINSTANCE.getBuiltInAction());
			elements.put(OperationAction_3016, ActionPackage.eINSTANCE.getOperationAction());
			elements.put(GridPanel_3017, PanelPackage.eINSTANCE.getGridPanel());
		}
		return (ENamedElement) elements.get(type);
	}
	/**
	 * @generated
	 */
	private static IElementType getElementType(String id){
		return ElementTypeRegistry.getInstance().getType(id);
	}
	/**
	 * @generated
	 */
	public static boolean isKnownElementType(IElementType elementType){
		if(KNOWN_ELEMENT_TYPES == null){
			KNOWN_ELEMENT_TYPES = new HashSet<IElementType>();
			KNOWN_ELEMENT_TYPES.add(UserInterface_1000);
			KNOWN_ELEMENT_TYPES.add(GridPanel_2001);
			KNOWN_ELEMENT_TYPES.add(HorizontalPanel_2002);
			KNOWN_ELEMENT_TYPES.add(VerticalPanel_2003);
			KNOWN_ELEMENT_TYPES.add(UimField_3001);
			KNOWN_ELEMENT_TYPES.add(BuiltInAction_3002);
			KNOWN_ELEMENT_TYPES.add(HorizontalPanel_3003);
			KNOWN_ELEMENT_TYPES.add(VerticalPanel_3004);
			KNOWN_ELEMENT_TYPES.add(TransitionAction_3005);
			KNOWN_ELEMENT_TYPES.add(OperationAction_3006);
			KNOWN_ELEMENT_TYPES.add(LinkToOperation_3007);
			KNOWN_ELEMENT_TYPES.add(LinkToEntity_3008);
			KNOWN_ELEMENT_TYPES.add(UimDataTable_3009);
			KNOWN_ELEMENT_TYPES.add(UimField_3010);
			KNOWN_ELEMENT_TYPES.add(LinkToEntity_3011);
			KNOWN_ELEMENT_TYPES.add(BuiltInAction_3012);
			KNOWN_ELEMENT_TYPES.add(OperationAction_3014);
			KNOWN_ELEMENT_TYPES.add(BuiltInAction_3015);
			KNOWN_ELEMENT_TYPES.add(OperationAction_3016);
			KNOWN_ELEMENT_TYPES.add(GridPanel_3017);
		}
		return KNOWN_ELEMENT_TYPES.contains(elementType);
	}
	/**
	 * @generated
	 */
	public static IElementType getElementType(int visualID){
		switch(visualID){
		case UserInterfaceEditPart.VISUAL_ID:
			return UserInterface_1000;
		case GridPanelEditPart.VISUAL_ID:
			return GridPanel_2001;
		case HorizontalPanelEditPart.VISUAL_ID:
			return HorizontalPanel_2002;
		case VerticalPanelEditPart.VISUAL_ID:
			return VerticalPanel_2003;
		case UimFieldEditPart.VISUAL_ID:
			return UimField_3001;
		case BuiltInActionEditPart.VISUAL_ID:
			return BuiltInAction_3002;
		case HorizontalPanel2EditPart.VISUAL_ID:
			return HorizontalPanel_3003;
		case VerticalPanel2EditPart.VISUAL_ID:
			return VerticalPanel_3004;
		case TransitionActionEditPart.VISUAL_ID:
			return TransitionAction_3005;
		case OperationActionEditPart.VISUAL_ID:
			return OperationAction_3006;
		case LinkToOperationEditPart.VISUAL_ID:
			return LinkToOperation_3007;
		case LinkToEntityEditPart.VISUAL_ID:
			return LinkToEntity_3008;
		case UimDataTableEditPart.VISUAL_ID:
			return UimDataTable_3009;
		case UimField2EditPart.VISUAL_ID:
			return UimField_3010;
		case LinkToEntity2EditPart.VISUAL_ID:
			return LinkToEntity_3011;
		case BuiltInAction2EditPart.VISUAL_ID:
			return BuiltInAction_3012;
		case OperationAction2EditPart.VISUAL_ID:
			return OperationAction_3014;
		case BuiltInAction3EditPart.VISUAL_ID:
			return BuiltInAction_3015;
		case OperationAction3EditPart.VISUAL_ID:
			return OperationAction_3016;
		case GridPanel2EditPart.VISUAL_ID:
			return GridPanel_3017;
		}
		return null;
	}
}
