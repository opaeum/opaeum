package org.opaeum.eclipse.uml.propertysections.base;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Property;
import org.opaeum.eclipse.EmfClassifierUtil;
import org.opaeum.eclipse.EmfElementFinder;

public abstract class AbstractPropertyLookupSection extends AbstractReferenceLookupSection{
	public AbstractPropertyLookupSection(){
		super();
	}
	protected boolean isMultiplicityCompatible(Property thisProperty,Property potentialProperty){
		return potentialProperty.getUpper() == 1 ? thisProperty.getUpper() == 1 : true;
	}
	@Override
	public boolean shouldUseExtraSpace(){
		return false;
	}
	protected List<EObject> getAvailableChoices(){
		Property p=(Property) getFeatureOwner(getEObject());
		List<EObject> choiceOfValues = new ArrayList<EObject>();
		if(p.eContainer() instanceof Classifier && p.eContainer() != p.getAssociation()){
			choiceOfValues.addAll(EmfElementFinder.getPropertiesInScope((Classifier) p.eContainer()));
		}else if(p.getAssociation() != null){
			choiceOfValues.addAll(EmfElementFinder.getPropertiesInScope((Classifier) p.getOtherEnd().getType()));
		}
		if(p.getType() != null){
			Iterator<EObject> iter = choiceOfValues.iterator();
			while(iter.hasNext()){
				Property rsp = (Property) iter.next();
				boolean typeConforms = rsp.getType() != null && EmfClassifierUtil.conformsTo((Classifier) p.getType(), (Classifier) rsp.getType());
				boolean multiplicityConforms = isMultiplicityCompatible(p, rsp);
				boolean staticConforms = rsp.isStatic() == p.isStatic();
				if(rsp == p || !(typeConforms && multiplicityConforms && staticConforms)){
					iter.remove();
				}
			}
		}
		return choiceOfValues;
	}
}