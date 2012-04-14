package org.opaeum.uimodeler.perspective.diagram.providers;

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
import org.opaeum.uim.perspective.PerspectivePackage;
import org.opaeum.uimodeler.perspective.diagram.edit.parts.EditorConfigurationEditPart;
import org.opaeum.uimodeler.perspective.diagram.edit.parts.ExplorerConfigurationEditPart;
import org.opaeum.uimodeler.perspective.diagram.edit.parts.PropertiesConfigurationEditPart;
import org.opaeum.uimodeler.perspective.diagram.edit.parts.UimPerspectiveEditPart;
import org.opaeum.uimodeler.perspective.diagram.part.UimPerspectiveDiagramEditorPlugin;

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
	public static final IElementType UimPerspective_1000 = getElementType("org.opaeum.uimodeler.perspective.diagram.UimPerspective_1000"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType EditorConfiguration_2001 = getElementType("org.opaeum.uimodeler.perspective.diagram.EditorConfiguration_2001"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType PropertiesConfiguration_2002 = getElementType("org.opaeum.uimodeler.perspective.diagram.PropertiesConfiguration_2002"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType ExplorerConfiguration_2003 = getElementType("org.opaeum.uimodeler.perspective.diagram.ExplorerConfiguration_2003"); //$NON-NLS-1$
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
				return UimPerspectiveDiagramEditorPlugin.getInstance().getItemImageDescriptor(
						eClass.getEPackage().getEFactoryInstance().create(eClass));
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
			elements.put(UimPerspective_1000, PerspectivePackage.eINSTANCE.getUimPerspective());
			elements.put(EditorConfiguration_2001, PerspectivePackage.eINSTANCE.getEditorConfiguration());
			elements.put(PropertiesConfiguration_2002, PerspectivePackage.eINSTANCE.getPropertiesConfiguration());
			elements.put(ExplorerConfiguration_2003, PerspectivePackage.eINSTANCE.getExplorerConfiguration());
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
			KNOWN_ELEMENT_TYPES.add(UimPerspective_1000);
			KNOWN_ELEMENT_TYPES.add(EditorConfiguration_2001);
			KNOWN_ELEMENT_TYPES.add(PropertiesConfiguration_2002);
			KNOWN_ELEMENT_TYPES.add(ExplorerConfiguration_2003);
		}
		return KNOWN_ELEMENT_TYPES.contains(elementType);
	}
	/**
	 * @generated
	 */
	public static IElementType getElementType(int visualID){
		switch(visualID){
		case UimPerspectiveEditPart.VISUAL_ID:
			return UimPerspective_1000;
		case EditorConfigurationEditPart.VISUAL_ID:
			return EditorConfiguration_2001;
		case PropertiesConfigurationEditPart.VISUAL_ID:
			return PropertiesConfiguration_2002;
		case ExplorerConfigurationEditPart.VISUAL_ID:
			return ExplorerConfiguration_2003;
		}
		return null;
	}
}
