package org.opaeum.uim.diagram.providers;

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
import org.opaeum.uim.diagram.edit.parts.BuiltInActionEditPart;
import org.opaeum.uim.diagram.edit.parts.EditorPageEditPart;
import org.opaeum.uim.diagram.edit.parts.GridPanelEditPart;
import org.opaeum.uim.diagram.edit.parts.HorizontalPanelEditPart;
import org.opaeum.uim.diagram.edit.parts.UimFieldEditPart;
import org.opaeum.uim.diagram.edit.parts.VerticalPanelEditPart;
import org.opaeum.uim.diagram.part.UimDiagramEditorPlugin;
import org.opaeum.uim.editor.EditorPackage;
import org.opaeum.uim.panel.PanelPackage;

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
	public static final IElementType EditorPage_1000 = getElementType("org.opaeum.metamodels.uim.diagram.EditorPage_1000"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType GridPanel_2001 = getElementType("org.opaeum.metamodels.uim.diagram.GridPanel_2001"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType HorizontalPanel_2002 = getElementType("org.opaeum.metamodels.uim.diagram.HorizontalPanel_2002"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType VerticalPanel_2003 = getElementType("org.opaeum.metamodels.uim.diagram.VerticalPanel_2003"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType UimField_3001 = getElementType("org.opaeum.metamodels.uim.diagram.UimField_3001"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType BuiltInAction_3002 = getElementType("org.opaeum.metamodels.uim.diagram.BuiltInAction_3002"); //$NON-NLS-1$
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
			elements.put(EditorPage_1000, EditorPackage.eINSTANCE.getEditorPage());
			elements.put(GridPanel_2001, PanelPackage.eINSTANCE.getGridPanel());
			elements.put(HorizontalPanel_2002, PanelPackage.eINSTANCE.getHorizontalPanel());
			elements.put(VerticalPanel_2003, PanelPackage.eINSTANCE.getVerticalPanel());
			elements.put(UimField_3001, UimPackage.eINSTANCE.getUimField());
			elements.put(BuiltInAction_3002, ActionPackage.eINSTANCE.getBuiltInAction());
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
			KNOWN_ELEMENT_TYPES.add(EditorPage_1000);
			KNOWN_ELEMENT_TYPES.add(GridPanel_2001);
			KNOWN_ELEMENT_TYPES.add(HorizontalPanel_2002);
			KNOWN_ELEMENT_TYPES.add(VerticalPanel_2003);
			KNOWN_ELEMENT_TYPES.add(UimField_3001);
			KNOWN_ELEMENT_TYPES.add(BuiltInAction_3002);
		}
		return KNOWN_ELEMENT_TYPES.contains(elementType);
	}
	/**
	 * @generated
	 */
	public static IElementType getElementType(int visualID){
		switch(visualID){
		case EditorPageEditPart.VISUAL_ID:
			return EditorPage_1000;
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
		}
		return null;
	}
}
