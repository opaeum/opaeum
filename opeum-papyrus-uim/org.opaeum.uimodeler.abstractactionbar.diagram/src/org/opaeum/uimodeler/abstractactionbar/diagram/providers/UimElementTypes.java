package org.opaeum.uimodeler.abstractactionbar.diagram.providers;

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
import org.opaeum.uimodeler.abstractactionbar.diagram.edit.parts.AbstractActionBarEditPart;
import org.opaeum.uimodeler.abstractactionbar.diagram.edit.parts.BuiltInActionEditPart;
import org.opaeum.uimodeler.abstractactionbar.diagram.edit.parts.OperationActionEditPart;
import org.opaeum.uimodeler.abstractactionbar.diagram.edit.parts.TransitionActionEditPart;
import org.opaeum.uimodeler.abstractactionbar.diagram.part.UimDiagramEditorPlugin;

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
	public static final IElementType AbstractActionBar_1000 = getElementType("org.opaeum.uimodeler.abstractactionbar.diagram.AbstractActionBar_1000"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType BuiltInAction_2001 = getElementType("org.opaeum.uimodeler.abstractactionbar.diagram.BuiltInAction_2001"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType TransitionAction_2002 = getElementType("org.opaeum.uimodeler.abstractactionbar.diagram.TransitionAction_2002"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType OperationAction_2003 = getElementType("org.opaeum.uimodeler.abstractactionbar.diagram.OperationAction_2003"); //$NON-NLS-1$
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
			elements.put(AbstractActionBar_1000, UimPackage.eINSTANCE.getAbstractActionBar());
			elements.put(BuiltInAction_2001, ActionPackage.eINSTANCE.getBuiltInAction());
			elements.put(TransitionAction_2002, ActionPackage.eINSTANCE.getTransitionAction());
			elements.put(OperationAction_2003, ActionPackage.eINSTANCE.getOperationAction());
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
			KNOWN_ELEMENT_TYPES.add(AbstractActionBar_1000);
			KNOWN_ELEMENT_TYPES.add(BuiltInAction_2001);
			KNOWN_ELEMENT_TYPES.add(TransitionAction_2002);
			KNOWN_ELEMENT_TYPES.add(OperationAction_2003);
		}
		return KNOWN_ELEMENT_TYPES.contains(elementType);
	}
	/**
	 * @generated
	 */
	public static IElementType getElementType(int visualID){
		switch(visualID){
		case AbstractActionBarEditPart.VISUAL_ID:
			return AbstractActionBar_1000;
		case BuiltInActionEditPart.VISUAL_ID:
			return BuiltInAction_2001;
		case TransitionActionEditPart.VISUAL_ID:
			return TransitionAction_2002;
		case OperationActionEditPart.VISUAL_ID:
			return OperationAction_2003;
		}
		return null;
	}
}
