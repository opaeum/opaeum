package org.opaeum.uimodeler.page.diagram.providers;

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
import org.opaeum.uimodeler.page.diagram.edit.parts.BuiltInActionButton2EditPart;
import org.opaeum.uimodeler.page.diagram.edit.parts.BuiltInActionButton3EditPart;
import org.opaeum.uimodeler.page.diagram.edit.parts.BuiltInActionButtonEditPart;
import org.opaeum.uimodeler.page.diagram.edit.parts.BuiltInLink2EditPart;
import org.opaeum.uimodeler.page.diagram.edit.parts.BuiltInLinkEditPart;
import org.opaeum.uimodeler.page.diagram.edit.parts.GridPanel2EditPart;
import org.opaeum.uimodeler.page.diagram.edit.parts.GridPanelEditPart;
import org.opaeum.uimodeler.page.diagram.edit.parts.HorizontalPanel2EditPart;
import org.opaeum.uimodeler.page.diagram.edit.parts.HorizontalPanelEditPart;
import org.opaeum.uimodeler.page.diagram.edit.parts.InvocationButton2EditPart;
import org.opaeum.uimodeler.page.diagram.edit.parts.InvocationButton3EditPart;
import org.opaeum.uimodeler.page.diagram.edit.parts.InvocationButtonEditPart;
import org.opaeum.uimodeler.page.diagram.edit.parts.LinkToQueryEditPart;
import org.opaeum.uimodeler.page.diagram.edit.parts.PageEditPart;
import org.opaeum.uimodeler.page.diagram.edit.parts.TransitionButtonEditPart;
import org.opaeum.uimodeler.page.diagram.edit.parts.UimDataTableEditPart;
import org.opaeum.uimodeler.page.diagram.edit.parts.UimField2EditPart;
import org.opaeum.uimodeler.page.diagram.edit.parts.UimFieldEditPart;
import org.opaeum.uimodeler.page.diagram.edit.parts.VerticalPanel2EditPart;
import org.opaeum.uimodeler.page.diagram.edit.parts.VerticalPanelEditPart;
import org.opaeum.uimodeler.page.diagram.part.UimDiagramEditorPlugin;

/**
 * @generated
 */
public class UimElementTypes {
	/**
	 * @generated
	 */
	private UimElementTypes() {
	}

	/**
	 * @generated
	 */
	private static Map<IElementType, ENamedElement> elements;
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
	public static final IElementType Page_1000 = getElementType("org.opaeum.uimodeler.page.diagram.Page_1000"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType GridPanel_2001 = getElementType("org.opaeum.uimodeler.page.diagram.GridPanel_2001"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType HorizontalPanel_2002 = getElementType("org.opaeum.uimodeler.page.diagram.HorizontalPanel_2002"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType VerticalPanel_2003 = getElementType("org.opaeum.uimodeler.page.diagram.VerticalPanel_2003"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType UimField_3028 = getElementType("org.opaeum.uimodeler.page.diagram.UimField_3028"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType BuiltInActionButton_3029 = getElementType("org.opaeum.uimodeler.page.diagram.BuiltInActionButton_3029"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType HorizontalPanel_3030 = getElementType("org.opaeum.uimodeler.page.diagram.HorizontalPanel_3030"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType VerticalPanel_3031 = getElementType("org.opaeum.uimodeler.page.diagram.VerticalPanel_3031"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType TransitionButton_3032 = getElementType("org.opaeum.uimodeler.page.diagram.TransitionButton_3032"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType InvocationButton_3033 = getElementType("org.opaeum.uimodeler.page.diagram.InvocationButton_3033"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType LinkToQuery_3034 = getElementType("org.opaeum.uimodeler.page.diagram.LinkToQuery_3034"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType BuiltInLink_3035 = getElementType("org.opaeum.uimodeler.page.diagram.BuiltInLink_3035"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType UimDataTable_3036 = getElementType("org.opaeum.uimodeler.page.diagram.UimDataTable_3036"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType UimField_3037 = getElementType("org.opaeum.uimodeler.page.diagram.UimField_3037"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType BuiltInActionButton_3038 = getElementType("org.opaeum.uimodeler.page.diagram.BuiltInActionButton_3038"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType InvocationButton_3039 = getElementType("org.opaeum.uimodeler.page.diagram.InvocationButton_3039"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType BuiltInActionButton_3040 = getElementType("org.opaeum.uimodeler.page.diagram.BuiltInActionButton_3040"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType InvocationButton_3041 = getElementType("org.opaeum.uimodeler.page.diagram.InvocationButton_3041"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType BuiltInLink_3042 = getElementType("org.opaeum.uimodeler.page.diagram.BuiltInLink_3042"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType GridPanel_3043 = getElementType("org.opaeum.uimodeler.page.diagram.GridPanel_3043"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	private static ImageRegistry getImageRegistry() {
		if (imageRegistry == null) {
			imageRegistry = new ImageRegistry();
		}
		return imageRegistry;
	}

	/**
	 * @generated
	 */
	private static String getImageRegistryKey(ENamedElement element) {
		return element.getName();
	}

	/**
	 * @generated
	 */
	private static ImageDescriptor getProvidedImageDescriptor(
			ENamedElement element) {
		if (element instanceof EStructuralFeature) {
			EStructuralFeature feature = ((EStructuralFeature) element);
			EClass eContainingClass = feature.getEContainingClass();
			EClassifier eType = feature.getEType();
			if (eContainingClass != null && !eContainingClass.isAbstract()) {
				element = eContainingClass;
			} else if (eType instanceof EClass
					&& !((EClass) eType).isAbstract()) {
				element = eType;
			}
		}
		if (element instanceof EClass) {
			EClass eClass = (EClass) element;
			if (!eClass.isAbstract()) {
				return UimDiagramEditorPlugin.getInstance()
						.getItemImageDescriptor(
								eClass.getEPackage().getEFactoryInstance()
										.create(eClass));
			}
		}
		// TODO : support structural features
		return null;
	}

	/**
	 * @generated
	 */
	public static ImageDescriptor getImageDescriptor(ENamedElement element) {
		String key = getImageRegistryKey(element);
		ImageDescriptor imageDescriptor = getImageRegistry().getDescriptor(key);
		if (imageDescriptor == null) {
			imageDescriptor = getProvidedImageDescriptor(element);
			if (imageDescriptor == null) {
				imageDescriptor = ImageDescriptor.getMissingImageDescriptor();
			}
			getImageRegistry().put(key, imageDescriptor);
		}
		return imageDescriptor;
	}

	/**
	 * @generated
	 */
	public static Image getImage(ENamedElement element) {
		String key = getImageRegistryKey(element);
		Image image = getImageRegistry().get(key);
		if (image == null) {
			ImageDescriptor imageDescriptor = getProvidedImageDescriptor(element);
			if (imageDescriptor == null) {
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
	public static ImageDescriptor getImageDescriptor(IAdaptable hint) {
		ENamedElement element = getElement(hint);
		if (element == null) {
			return null;
		}
		return getImageDescriptor(element);
	}

	/**
	 * @generated
	 */
	public static Image getImage(IAdaptable hint) {
		ENamedElement element = getElement(hint);
		if (element == null) {
			return null;
		}
		return getImage(element);
	}

	/**
	 * Returns 'type' of the ecore object associated with the hint.
	 * 
	 * @generated
	 */
	public static ENamedElement getElement(IAdaptable hint) {
		Object type = hint.getAdapter(IElementType.class);
		if (elements == null) {
			elements = new IdentityHashMap<IElementType, ENamedElement>();

			elements.put(Page_1000, UimPackage.eINSTANCE.getPage());

			elements.put(GridPanel_2001, PanelPackage.eINSTANCE.getGridPanel());

			elements.put(HorizontalPanel_2002,
					PanelPackage.eINSTANCE.getHorizontalPanel());

			elements.put(VerticalPanel_2003,
					PanelPackage.eINSTANCE.getVerticalPanel());

			elements.put(UimField_3028,
					ComponentPackage.eINSTANCE.getUimField());

			elements.put(BuiltInActionButton_3029,
					ActionPackage.eINSTANCE.getBuiltInActionButton());

			elements.put(HorizontalPanel_3030,
					PanelPackage.eINSTANCE.getHorizontalPanel());

			elements.put(VerticalPanel_3031,
					PanelPackage.eINSTANCE.getVerticalPanel());

			elements.put(TransitionButton_3032,
					ActionPackage.eINSTANCE.getTransitionButton());

			elements.put(InvocationButton_3033,
					ActionPackage.eINSTANCE.getInvocationButton());

			elements.put(LinkToQuery_3034,
					ActionPackage.eINSTANCE.getLinkToQuery());

			elements.put(BuiltInLink_3035,
					ActionPackage.eINSTANCE.getBuiltInLink());

			elements.put(UimDataTable_3036,
					ComponentPackage.eINSTANCE.getUimDataTable());

			elements.put(UimField_3037,
					ComponentPackage.eINSTANCE.getUimField());

			elements.put(BuiltInActionButton_3038,
					ActionPackage.eINSTANCE.getBuiltInActionButton());

			elements.put(InvocationButton_3039,
					ActionPackage.eINSTANCE.getInvocationButton());

			elements.put(BuiltInActionButton_3040,
					ActionPackage.eINSTANCE.getBuiltInActionButton());

			elements.put(InvocationButton_3041,
					ActionPackage.eINSTANCE.getInvocationButton());

			elements.put(BuiltInLink_3042,
					ActionPackage.eINSTANCE.getBuiltInLink());

			elements.put(GridPanel_3043, PanelPackage.eINSTANCE.getGridPanel());
		}
		return (ENamedElement) elements.get(type);
	}

	/**
	 * @generated
	 */
	private static IElementType getElementType(String id) {
		return ElementTypeRegistry.getInstance().getType(id);
	}

	/**
	 * @generated
	 */
	public static boolean isKnownElementType(IElementType elementType) {
		if (KNOWN_ELEMENT_TYPES == null) {
			KNOWN_ELEMENT_TYPES = new HashSet<IElementType>();
			KNOWN_ELEMENT_TYPES.add(Page_1000);
			KNOWN_ELEMENT_TYPES.add(GridPanel_2001);
			KNOWN_ELEMENT_TYPES.add(HorizontalPanel_2002);
			KNOWN_ELEMENT_TYPES.add(VerticalPanel_2003);
			KNOWN_ELEMENT_TYPES.add(UimField_3028);
			KNOWN_ELEMENT_TYPES.add(BuiltInActionButton_3029);
			KNOWN_ELEMENT_TYPES.add(HorizontalPanel_3030);
			KNOWN_ELEMENT_TYPES.add(VerticalPanel_3031);
			KNOWN_ELEMENT_TYPES.add(TransitionButton_3032);
			KNOWN_ELEMENT_TYPES.add(InvocationButton_3033);
			KNOWN_ELEMENT_TYPES.add(LinkToQuery_3034);
			KNOWN_ELEMENT_TYPES.add(BuiltInLink_3035);
			KNOWN_ELEMENT_TYPES.add(UimDataTable_3036);
			KNOWN_ELEMENT_TYPES.add(UimField_3037);
			KNOWN_ELEMENT_TYPES.add(BuiltInActionButton_3038);
			KNOWN_ELEMENT_TYPES.add(InvocationButton_3039);
			KNOWN_ELEMENT_TYPES.add(BuiltInActionButton_3040);
			KNOWN_ELEMENT_TYPES.add(InvocationButton_3041);
			KNOWN_ELEMENT_TYPES.add(BuiltInLink_3042);
			KNOWN_ELEMENT_TYPES.add(GridPanel_3043);
		}
		return KNOWN_ELEMENT_TYPES.contains(elementType);
	}

	/**
	 * @generated
	 */
	public static IElementType getElementType(int visualID) {
		switch (visualID) {
		case PageEditPart.VISUAL_ID:
			return Page_1000;
		case GridPanelEditPart.VISUAL_ID:
			return GridPanel_2001;
		case HorizontalPanelEditPart.VISUAL_ID:
			return HorizontalPanel_2002;
		case VerticalPanelEditPart.VISUAL_ID:
			return VerticalPanel_2003;
		case UimFieldEditPart.VISUAL_ID:
			return UimField_3028;
		case BuiltInActionButtonEditPart.VISUAL_ID:
			return BuiltInActionButton_3029;
		case HorizontalPanel2EditPart.VISUAL_ID:
			return HorizontalPanel_3030;
		case VerticalPanel2EditPart.VISUAL_ID:
			return VerticalPanel_3031;
		case TransitionButtonEditPart.VISUAL_ID:
			return TransitionButton_3032;
		case InvocationButtonEditPart.VISUAL_ID:
			return InvocationButton_3033;
		case LinkToQueryEditPart.VISUAL_ID:
			return LinkToQuery_3034;
		case BuiltInLinkEditPart.VISUAL_ID:
			return BuiltInLink_3035;
		case UimDataTableEditPart.VISUAL_ID:
			return UimDataTable_3036;
		case UimField2EditPart.VISUAL_ID:
			return UimField_3037;
		case BuiltInActionButton2EditPart.VISUAL_ID:
			return BuiltInActionButton_3038;
		case InvocationButton2EditPart.VISUAL_ID:
			return InvocationButton_3039;
		case BuiltInActionButton3EditPart.VISUAL_ID:
			return BuiltInActionButton_3040;
		case InvocationButton3EditPart.VISUAL_ID:
			return InvocationButton_3041;
		case BuiltInLink2EditPart.VISUAL_ID:
			return BuiltInLink_3042;
		case GridPanel2EditPart.VISUAL_ID:
			return GridPanel_3043;
		}
		return null;
	}
}
