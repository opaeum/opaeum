package org.opaeum.topcased.uml.editor;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.swt.graphics.Image;
import org.eclipse.uml2.uml.Element;
import org.topcased.modeler.editor.outline.VirtualContainer;

public class StereotypeVirtualContainer implements VirtualContainer{
	OpaeumItemProviderAdapterFactory itemProviderAdapterFactory=new OpaeumItemProviderAdapterFactory();
	@Override
	public EClass getEClassToMatch(){
		return EcorePackage.eINSTANCE.getEObject();
	}
	@SuppressWarnings("unchecked")
	@Override
	public Collection<EObject> getChildren(EObject eobject){
		if(eobject instanceof Element){
			Element element = (Element) eobject;
			return element.getStereotypeApplications();
		}else{
			Collection<EObject> children=new ArrayList<EObject>();
			for(EReference ref:eobject.eClass().getEAllContainments()){
				Object result=eobject.eGet(ref);
				if(result instanceof Collection){
					children.addAll((Collection)result);
				}else{
					children.add((EObject) result);
				}
			}
			return children;
		}
	}
	@Override
	public String getLabel(EObject eobject){
		return eobject.eClass().getName();
	}
	@Override
	public Image getIcon(EObject eobject){
		return (Image) itemProviderAdapterFactory.getResourceLocator().getImage("full/obj16/Stereotype");
	}
}
