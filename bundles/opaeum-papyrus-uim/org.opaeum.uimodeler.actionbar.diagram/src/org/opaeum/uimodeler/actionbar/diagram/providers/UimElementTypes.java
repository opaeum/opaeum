package org.opaeum.uimodeler.actionbar.diagram.providers;

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
import org.opaeum.uim.action.ActionPackage;
import org.opaeum.uim.editor.EditorPackage;
import org.opaeum.uimodeler.actionbar.diagram.edit.parts.AbstractEditorEditPart;
import org.opaeum.uimodeler.actionbar.diagram.edit.parts.ActionBarEditPart;
import org.opaeum.uimodeler.actionbar.diagram.edit.parts.BuiltInActionButtonEditPart;
import org.opaeum.uimodeler.actionbar.diagram.edit.parts.BuiltInLinkEditPart;
import org.opaeum.uimodeler.actionbar.diagram.edit.parts.InvocationButtonEditPart;
import org.opaeum.uimodeler.actionbar.diagram.edit.parts.LinkToQueryEditPart;
import org.opaeum.uimodeler.actionbar.diagram.edit.parts.TransitionButtonEditPart;
import org.opaeum.uimodeler.actionbar.diagram.part.UimDiagramEditorPlugin;

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
	public static final IElementType AbstractEditor_1000 = getElementType("org.opaeum.uimodeler.actionbar.diagram.AbstractEditor_1000"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType ActionBar_2011 = getElementType("org.opaeum.uimodeler.actionbar.diagram.ActionBar_2011"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType BuiltInLink_3001 = getElementType("org.opaeum.uimodeler.actionbar.diagram.BuiltInLink_3001"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType LinkToQuery_3002 = getElementType("org.opaeum.uimodeler.actionbar.diagram.LinkToQuery_3002"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType InvocationButton_3003 = getElementType("org.opaeum.uimodeler.actionbar.diagram.InvocationButton_3003"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType BuiltInActionButton_3004 = getElementType("org.opaeum.uimodeler.actionbar.diagram.BuiltInActionButton_3004"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType TransitionButton_3005 = getElementType("org.opaeum.uimodeler.actionbar.diagram.TransitionButton_3005"); //$NON-NLS-1$
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
			elements.put(AbstractEditor_1000, EditorPackage.eINSTANCE.getAbstractEditor());
			elements.put(ActionBar_2011, EditorPackage.eINSTANCE.getActionBar());
			elements.put(BuiltInLink_3001, ActionPackage.eINSTANCE.getBuiltInLink());
			elements.put(LinkToQuery_3002, ActionPackage.eINSTANCE.getLinkToQuery());
			elements.put(InvocationButton_3003, ActionPackage.eINSTANCE.getInvocationButton());
			elements.put(BuiltInActionButton_3004, ActionPackage.eINSTANCE.getBuiltInActionButton());
			elements.put(TransitionButton_3005, ActionPackage.eINSTANCE.getTransitionButton());
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
			KNOWN_ELEMENT_TYPES.add(AbstractEditor_1000);
			KNOWN_ELEMENT_TYPES.add(ActionBar_2011);
			KNOWN_ELEMENT_TYPES.add(BuiltInLink_3001);
			KNOWN_ELEMENT_TYPES.add(LinkToQuery_3002);
			KNOWN_ELEMENT_TYPES.add(InvocationButton_3003);
			KNOWN_ELEMENT_TYPES.add(BuiltInActionButton_3004);
			KNOWN_ELEMENT_TYPES.add(TransitionButton_3005);
		}
		return KNOWN_ELEMENT_TYPES.contains(elementType);
	}
	/**
	 * @generated
	 */
	public static IElementType getElementType(int visualID){
		switch(visualID){
		case AbstractEditorEditPart.VISUAL_ID:
			return AbstractEditor_1000;
		case ActionBarEditPart.VISUAL_ID:
			return ActionBar_2011;
		case BuiltInLinkEditPart.VISUAL_ID:
			return BuiltInLink_3001;
		case LinkToQueryEditPart.VISUAL_ID:
			return LinkToQuery_3002;
		case InvocationButtonEditPart.VISUAL_ID:
			return InvocationButton_3003;
		case BuiltInActionButtonEditPart.VISUAL_ID:
			return BuiltInActionButton_3004;
		case TransitionButtonEditPart.VISUAL_ID:
			return TransitionButton_3005;
		}
		return null;
	}
}
