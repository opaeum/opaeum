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
import org.opaeum.uim.component.ComponentPackage;
import org.opaeum.uim.panel.PanelPackage;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.BuiltInActionButton2EditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.BuiltInActionButton3EditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.BuiltInActionButtonEditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.BuiltInLink2EditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.BuiltInLinkEditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.GridPanel2EditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.GridPanelEditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.HorizontalPanel2EditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.HorizontalPanelEditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.LinkToQueryEditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.InvocationButton2EditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.InvocationButton3EditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.InvocationButtonEditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.TransitionButtonEditPart;
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
	public static final IElementType BuiltInActionButton_3018 = getElementType("org.opaeum.uimodeler.userinterface.diagram.BuiltInActionButton_3018"); //$NON-NLS-1$
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
	public static final IElementType TransitionButton_3019 = getElementType("org.opaeum.uimodeler.userinterface.diagram.TransitionButton_3019"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType InvocationButton_3020 = getElementType("org.opaeum.uimodeler.userinterface.diagram.InvocationButton_3020"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType LinkToQuery_3021 = getElementType("org.opaeum.uimodeler.userinterface.diagram.LinkToQuery_3021"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType BuiltInLink_3022 = getElementType("org.opaeum.uimodeler.userinterface.diagram.BuiltInLink_3022"); //$NON-NLS-1$
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
	public static final IElementType BuiltInActionButton_3023 = getElementType("org.opaeum.uimodeler.userinterface.diagram.BuiltInActionButton_3023"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType InvocationButton_3024 = getElementType("org.opaeum.uimodeler.userinterface.diagram.InvocationButton_3024"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType BuiltInActionButton_3025 = getElementType("org.opaeum.uimodeler.userinterface.diagram.BuiltInActionButton_3025"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType InvocationButton_3026 = getElementType("org.opaeum.uimodeler.userinterface.diagram.InvocationButton_3026"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType BuiltInLink_3027 = getElementType("org.opaeum.uimodeler.userinterface.diagram.BuiltInLink_3027"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType GridPanel_3017 = getElementType("org.opaeum.uimodeler.userinterface.diagram.GridPanel_3017"); //$NON-NLS-1$
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
			elements.put(UserInterface_1000, UimPackage.eINSTANCE.getPage());
			elements.put(GridPanel_2001, PanelPackage.eINSTANCE.getGridPanel());
			elements.put(HorizontalPanel_2002, PanelPackage.eINSTANCE.getHorizontalPanel());
			elements.put(VerticalPanel_2003, PanelPackage.eINSTANCE.getVerticalPanel());
			elements.put(UimField_3001, ComponentPackage.eINSTANCE.getUimField());
			elements.put(BuiltInActionButton_3018, ActionPackage.eINSTANCE.getBuiltInActionButton());
			elements.put(HorizontalPanel_3003, PanelPackage.eINSTANCE.getHorizontalPanel());
			elements.put(VerticalPanel_3004, PanelPackage.eINSTANCE.getVerticalPanel());
			elements.put(TransitionButton_3019, ActionPackage.eINSTANCE.getTransitionButton());
			elements.put(InvocationButton_3020, ActionPackage.eINSTANCE.getInvocationButton());
			elements.put(LinkToQuery_3021, ActionPackage.eINSTANCE.getLinkToQuery());
			elements.put(BuiltInLink_3022, ActionPackage.eINSTANCE.getBuiltInLink());
			elements.put(UimDataTable_3009, ComponentPackage.eINSTANCE.getUimDataTable());
			elements.put(UimField_3010, ComponentPackage.eINSTANCE.getUimField());
			elements.put(BuiltInActionButton_3023, ActionPackage.eINSTANCE.getBuiltInActionButton());
			elements.put(InvocationButton_3024, ActionPackage.eINSTANCE.getInvocationButton());
			elements.put(BuiltInActionButton_3025, ActionPackage.eINSTANCE.getBuiltInActionButton());
			elements.put(InvocationButton_3026, ActionPackage.eINSTANCE.getInvocationButton());
			elements.put(BuiltInLink_3027, ActionPackage.eINSTANCE.getBuiltInLink());
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
			KNOWN_ELEMENT_TYPES.add(BuiltInActionButton_3018);
			KNOWN_ELEMENT_TYPES.add(HorizontalPanel_3003);
			KNOWN_ELEMENT_TYPES.add(VerticalPanel_3004);
			KNOWN_ELEMENT_TYPES.add(TransitionButton_3019);
			KNOWN_ELEMENT_TYPES.add(InvocationButton_3020);
			KNOWN_ELEMENT_TYPES.add(LinkToQuery_3021);
			KNOWN_ELEMENT_TYPES.add(BuiltInLink_3022);
			KNOWN_ELEMENT_TYPES.add(UimDataTable_3009);
			KNOWN_ELEMENT_TYPES.add(UimField_3010);
			KNOWN_ELEMENT_TYPES.add(BuiltInActionButton_3023);
			KNOWN_ELEMENT_TYPES.add(InvocationButton_3024);
			KNOWN_ELEMENT_TYPES.add(BuiltInActionButton_3025);
			KNOWN_ELEMENT_TYPES.add(InvocationButton_3026);
			KNOWN_ELEMENT_TYPES.add(BuiltInLink_3027);
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
		case BuiltInActionButtonEditPart.VISUAL_ID:
			return BuiltInActionButton_3018;
		case HorizontalPanel2EditPart.VISUAL_ID:
			return HorizontalPanel_3003;
		case VerticalPanel2EditPart.VISUAL_ID:
			return VerticalPanel_3004;
		case TransitionButtonEditPart.VISUAL_ID:
			return TransitionButton_3019;
		case InvocationButtonEditPart.VISUAL_ID:
			return InvocationButton_3020;
		case LinkToQueryEditPart.VISUAL_ID:
			return LinkToQuery_3021;
		case BuiltInLinkEditPart.VISUAL_ID:
			return BuiltInLink_3022;
		case UimDataTableEditPart.VISUAL_ID:
			return UimDataTable_3009;
		case UimField2EditPart.VISUAL_ID:
			return UimField_3010;
		case BuiltInActionButton2EditPart.VISUAL_ID:
			return BuiltInActionButton_3023;
		case InvocationButton2EditPart.VISUAL_ID:
			return InvocationButton_3024;
		case BuiltInActionButton3EditPart.VISUAL_ID:
			return BuiltInActionButton_3025;
		case InvocationButton3EditPart.VISUAL_ID:
			return InvocationButton_3026;
		case BuiltInLink2EditPart.VISUAL_ID:
			return BuiltInLink_3027;
		case GridPanel2EditPart.VISUAL_ID:
			return GridPanel_3017;
		}
		return null;
	}
}
