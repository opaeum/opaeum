package org.nakeduml.topcased.activitydiagram.propertysections;

import net.sf.nakeduml.metamodel.core.internal.StereotypeNames;
import net.sf.nakeduml.metamodel.name.SingularNameWrapper;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.OpaqueExpression;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.ValueSpecification;
import org.nakeduml.topcased.propertysections.AbstractOpaqueExpressionSection;
import org.nakeduml.topcased.propertysections.OclValueComposite;

public abstract class AbstractArtificialOpaqueExpressionSection extends AbstractOpaqueExpressionSection{
	public AbstractArtificialOpaqueExpressionSection(){
		super();
	}

	@Override
	protected OpaqueExpression getExpression(EObject eo){
		EAnnotation ann = findOrCreateAnnotation((Element) getEObject());
		return findOpaqueExpression(getExpressionName(), ann);
	}

	protected abstract String getExpressionName();

	protected void forceOpaqueExpression(){
		Element e = (Element) getEObject();
		EAnnotation ann = findOrCreateAnnotation(e);
		OpaqueExpression vs = findOpaqueExpression(getExpressionName(), ann);
		if(vs == null){
			vs = UMLFactory.eINSTANCE.createOpaqueExpression();
			vs.setName(getExpressionName());
			ann.getContents().add(vs);
		}
		if(vs.getBodies().isEmpty()){
			vs.getBodies().add(OclValueComposite.DEFAULT_TEXT);
			vs.getLanguages().add("OCL");
		}
	
	}

	protected OpaqueExpression findOpaqueExpression(String vsName,EAnnotation ann){
		EList<EObject> contents = ann.getContents();
		OpaqueExpression vs = null;
		for(EObject eObject:contents){
			if(eObject instanceof OpaqueExpression && vsName.equals(((OpaqueExpression) eObject).getName())){
				vs = (OpaqueExpression) eObject;
			}
		}
		return vs;
	}

	protected EAnnotation findOrCreateAnnotation(Element e){
		EAnnotation ann = e.getEAnnotation(StereotypeNames.NUML_ANNOTATION);
		if(ann == null){
			ann = e.createEAnnotation(StereotypeNames.NUML_ANNOTATION);
		}
		return ann;
	}

	@Override
	protected NamedElement getOwner(){
		return (NamedElement) getEObject();
	}

	@Override
	protected ValueSpecification getValueSpecification(){
		return getExpression(getEObject());
	}

	@Override
	protected EStructuralFeature getFeature(){
		return null;
	}

	@Override
	protected String getLabelText(){
		return new SingularNameWrapper(getExpressionName(),null).getCapped().getSeparateWords().getAsIs();
	}
}