package org.nakeduml.uim.userinteractionproperties.sections;

import java.util.Collection;
import java.util.StringTokenizer;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.swt.widgets.Event;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.TypedElement;
import org.nakeduml.uim.PropertyRef;
import org.nakeduml.uim.UimBinding;
import org.nakeduml.uim.UimComponent;
import org.nakeduml.uim.UimFactory;
import org.nakeduml.uim.util.SafeUmlUimLinks;
import org.nakeduml.uim.util.UimUtil;
import org.nakeduml.uim.util.UmlUimLinks;

public abstract class AbstractBindingSection extends TypedElementCodeCompletingSection{
	protected abstract Object getFeatureValue();
	protected abstract EClass getFeatureEClass();
	protected UimBinding getBinding(){
		return (UimBinding) getFeatureValue();
	}
	protected Collection<TypedElement> getTypedElements(){
		return UimUtil.getOwnedTypedElements(UimUtil.getNearestClass((UimComponent) getEObject()));
	}
	@Override
	protected String getFeatureAsString(){
		StringBuilder sb = new StringBuilder();
		UimBinding binding = (UimBinding) getBinding();
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
		Property typedElement = SafeUmlUimLinks.getInstance(getEObject()).getProperty(next);
		if(typedElement == null){
			return "NOTFOUND";
		}else{
			return typedElement.getName();
		}
	}
	private String getName(UimBinding binding){
		TypedElement typedElement = SafeUmlUimLinks.getInstance(getEObject()).getTypedElement(binding);
		if(typedElement == null){
			return "NOTFOUND";
		}else{
			return typedElement.getName();
		}
	}
	@Override
	protected Object getNewFeatureValue(String newText){
		StringTokenizer st = new StringTokenizer(newText, ".");
		if(st.hasMoreTokens()){
			String teName = st.nextToken();
			TypedElement te = getTypedElement(teName);
			if(te != null){
				UimBinding fb = (UimBinding) UimFactory.eINSTANCE.create(getFeatureEClass());
				fb.setUmlElementUid(UmlUimLinks.getId(te));
				PropertyRef prev = null;
				while(st.hasMoreTokens()){
					Classifier cl = (Classifier) te.getType();
					Property p = getProperty(cl, st.nextToken());
					if(p != null){
						PropertyRef pr2 = UimFactory.eINSTANCE.createPropertyRef();
						pr2.setUmlElementUid(UmlUimLinks.getId(p));
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
	@Override
	protected Object getOldFeatureValue(){
		return getFeatureValue();
	}
	@Override
	protected void verifyField(Event e){
	}
}
