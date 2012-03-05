package org.opaeum.uim.userinteractionproperties.sections;

import java.util.Collection;
import java.util.StringTokenizer;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.swt.widgets.Event;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.TypedElement;
import org.opaeum.eclipse.EmfElementFinder;
import org.opaeum.eclipse.context.OpaeumEclipseContext;
import org.opaeum.uim.UimComponent;
import org.opaeum.uim.binding.BindingFactory;
import org.opaeum.uim.binding.PropertyRef;
import org.opaeum.uim.binding.UimBinding;
import org.opaeum.uim.util.UmlUimLinks;

public abstract class AbstractBindingSection extends TypedElementCodeCompletingSection{
	protected abstract Object getFeatureValue();
	protected abstract EClass getFeatureEClass();
	protected UimBinding getBinding(){
		return (UimBinding) getFeatureValue();
	}
	protected Collection<TypedElement> getTypedElements(){
		return EmfElementFinder.getTypedElementsInScope(UmlUimLinks.getCurrentUmlLinks().getNearestClass((UimComponent) getEObject()));
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
		Property typedElement = UmlUimLinks.getCurrentUmlLinks().getProperty(next);
		if(typedElement == null){
			return "NOTFOUND";
		}else{
			return typedElement.getName();
		}
	}
	private String getName(UimBinding binding){
		TypedElement typedElement = UmlUimLinks.getCurrentUmlLinks().getTypedElement(binding);
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
				UimBinding fb = (UimBinding) BindingFactory.eINSTANCE.create(getFeatureEClass());
				fb.setUmlElementUid(OpaeumEclipseContext.getCurrentContext().getId(te));
				PropertyRef prev = null;
				while(st.hasMoreTokens()){
					Classifier cl = (Classifier) te.getType();
					Property p = getProperty(cl, st.nextToken());
					if(p != null){
						PropertyRef pr2 = BindingFactory.eINSTANCE.createPropertyRef();
						pr2.setUmlElementUid(OpaeumEclipseContext.getCurrentContext().getId(p));
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
