package org.opaeum.eclipse.uml.propertysections.standardprofile;

import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Property;
import org.opaeum.eclipse.EmfClassifierUtil;
import org.opaeum.eclipse.EmfPropertyUtil;
import org.opaeum.eclipse.uml.propertysections.base.AbstractComboOnStereotypeSection;
import org.opaeum.metamodel.core.internal.StereotypeNames;
import org.opaeum.metamodel.core.internal.TagNames;

public class ClassifierNamePropertySection extends AbstractComboOnStereotypeSection{
	private List<Property> props;
	public String getStereotypeName(Element e){
		if(e instanceof org.eclipse.uml2.uml.Class){
			return StereotypeNames.ENTITY;
		}else{
			return e.eClass().getName();
		}
	}
	@Override
	protected List<? extends EObject> getComboValues(){
		this.props = EmfPropertyUtil.getEffectiveProperties(getClassifier());
		Iterator<Property> iterator = props.iterator();
		while(iterator.hasNext()){
			Property property = (Property) iterator.next();
			if(property.getType() == null || !EmfClassifierUtil.comformsToLibraryType(property.getType(), "String")){
				iterator.remove();
			}
		}
		return props;
	}
	@Override
	public String getLabelText(){
		return "Name Property";
	}
	@Override
	public void setInput(IWorkbenchPart part,ISelection selection){
		super.setInput(part, selection);
	}
	private Classifier getClassifier(){
		return (Classifier) getSelectedObject();
	}
	@Override
	protected String getAttributeName(){
		return TagNames.NAME_PROPERTY;
	}
	@Override
	protected String getProfileName(){
		return StereotypeNames.OPAEUM_STANDARD_PROFILE;
	}
}
