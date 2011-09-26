package org.nakeduml.topcased.propertysections;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.ui.celleditor.FeatureEditorDialog;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Property;
import org.nakeduml.eclipse.EmfClassifierUtil;
import org.nakeduml.eclipse.EmfElementFinder;
import org.topcased.tabbedproperties.internal.sections.TableObjectManager;

public abstract class AbstractPropertyLookupSection extends AbstractReferenceLookupSection{
	public AbstractPropertyLookupSection(){
		super();
	}
	protected EObject getFeatureOwner(){
		return getProperty();
	}
	protected boolean isMultiplicityCompatible(Property thisProperty, Property potentialProperty){
		return potentialProperty.getUpper()==1?thisProperty.getUpper()==1:true;
	}
	protected Property getProperty(){
		return (Property) getEObject();
	}
	protected List<EObject> getAvailableChoices(){
		Property p = (Property) getProperty();
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
				boolean typeConforms = rsp.getType() != null && EmfClassifierUtil.conformsTo((Classifier) p.getType(), (Classifier)rsp.getType());
				boolean multiplicityConforms=isMultiplicityCompatible(p, rsp);
				boolean staticConforms=rsp.isStatic()==p.isStatic();
				if(rsp == p || !(typeConforms && multiplicityConforms && staticConforms)){
					iter.remove();
				}
			}
		}
		return choiceOfValues;
	}

}