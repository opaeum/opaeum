package org.opaeum.uim.userinteractionproperties.binding;

import java.util.ArrayList;
import java.util.Collection;
import java.util.StringTokenizer;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.TypedElement;
import org.opaeum.eclipse.EmfElementFinder;
import org.opaeum.emf.workspace.EmfWorkspace;
import org.opaeum.uim.UmlReference;
import org.opaeum.uim.UserInteractionElement;
import org.opaeum.uim.binding.BindingFactory;
import org.opaeum.uim.binding.PropertyRef;
import org.opaeum.uim.binding.UimBinding;
import org.opaeum.uim.userinteractionproperties.sections.ITypedElementProvider;
import org.opaeum.uim.util.UmlUimLinks;

public class BindingHelper implements ITypedElementProvider{
	private EObject owner;
	private EReference feature;
	public BindingHelper(EReference feature){
		super();
		this.feature = feature;
	}
	public Collection<TypedElement> getTypedElements(){
		EObject owner = getOwner();
		Element form = UmlUimLinks.getCurrentUmlLinks(owner).getNearestUmlElement((UserInteractionElement) owner);
		if(form instanceof Operation){
			return new ArrayList<TypedElement>(((Operation) form).getOwnedParameters());
		}else{
			form = UmlUimLinks.getCurrentUmlLinks(owner).getNearestClass(owner);
		}
		return EmfElementFinder.getTypedElementsInScope(form);
	}
	public String getFeatureAsString(){
		StringBuilder sb = new StringBuilder();
		UimBinding binding = (UimBinding) getOwner().eGet(feature);
		if(binding != null){
			sb.append(getName(binding));
			PropertyRef next = binding.getNext();
			while(next != null){
				sb.append(".");
				sb.append(getName(next));
				next = next.getNext();
			}
		}
		return sb.toString();
	}
	private String getName(PropertyRef next){
		Property typedElement = UmlUimLinks.getCurrentUmlLinks(getOwner()).getProperty(next);
		if(typedElement == null){
			return "NOTFOUND";
		}else{
			return typedElement.getName();
		}
	}
	private String getName(UimBinding binding){
		TypedElement typedElement = UmlUimLinks.getCurrentUmlLinks(getOwner()).getTypedElement(binding);
		if(typedElement == null){
			return "NOTFOUND";
		}else{
			return typedElement.getName();
		}
	}
	public Object getNewFeatureValue(String newText){
		StringTokenizer st = new StringTokenizer(newText, ".");
		if(st.hasMoreTokens()){
			String teName = st.nextToken();
			TypedElement te = getTypedElement(teName);
			if(te != null){
				UimBinding fb = (UimBinding) BindingFactory.eINSTANCE.create((EClass) feature.getEType());
				fb.setUmlElementUid(EmfWorkspace.getId(te));
				PropertyRef prev = null;
				while(st.hasMoreTokens()){
					Classifier cl = (Classifier) te.getType();
					Property p = getProperty(cl, st.nextToken());
					if(p != null){
						PropertyRef pr2 = BindingFactory.eINSTANCE.createPropertyRef();
						pr2.setUmlElementUid(EmfWorkspace.getId(p));
						if(fb.getNext() == null){
							fb.setNext(pr2);
						}else{
							prev.setNext(pr2);
						}
						te = p;
						prev = pr2;
					}else{
						break;
					}
				}
				return fb;
			}
		}
		return null;
	}
	public EObject getOwner(){
		return owner;
	}
	public void setOwner(EObject owner){
		this.owner = owner;
	}
	public TypedElement getTypedElement(String name){
		Collection<? extends TypedElement> typedElements = getTypedElements();
		for(TypedElement te:typedElements){
			if(te.getName().equals(name)){
				return te;
			}
		}
		return null;
	}
	public Property getProperty(Classifier c,String name){
		Collection<TypedElement> typedElements = EmfElementFinder.getTypedElementsInScope(c);
		for(TypedElement te:typedElements){
			if(te.getName().equals(name)){
				return (Property) te;
			}
		}
		return null;
	}
}
