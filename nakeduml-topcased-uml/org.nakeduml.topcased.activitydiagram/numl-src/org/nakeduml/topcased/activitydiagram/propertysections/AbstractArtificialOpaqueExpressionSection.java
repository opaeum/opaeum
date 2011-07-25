package org.nakeduml.topcased.activitydiagram.propertysections;

import net.sf.nakeduml.emf.extraction.StereotypesHelper;
import net.sf.nakeduml.metamodel.name.SingularNameWrapper;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.OpaqueExpression;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.ValueSpecification;
import org.nakeduml.topcased.propertysections.OpaqueExpressionBodySection;

public abstract class AbstractArtificialOpaqueExpressionSection extends OpaqueExpressionBodySection{
	public AbstractArtificialOpaqueExpressionSection(){
		super();
	}

	@Override
	protected OpaqueExpression getExpression(EObject eo){
		return findOpaqueExpression(getExpressionName(), getAnnotation());
	}
	@Override
	protected Element getOclContext(){
		return (Element) getEObject();
	}

	protected EAnnotation getAnnotation(){
		return StereotypesHelper.getNumlAnnotation((Element) getEObject());
	}
	protected void handleOclChanged(String oclText){
		if(oclText.trim().length() > 0){
			EAnnotation ann = getAnnotation();
			OpaqueExpression vs = findOpaqueExpression(getExpressionName(), ann);
			if(vs == null){
				vs = UMLFactory.eINSTANCE.createOpaqueExpression();
				vs.setName(getExpressionName());
				Command cmd = AddCommand.create(getEditingDomain(), ann, EcorePackage.eINSTANCE.getEAnnotation_Contents(), vs);
				getEditingDomain().getCommandStack().execute(cmd);
			}
			super.handleOclChanged(oclText);
		}
	}

	protected abstract String getExpressionName();


	protected OpaqueExpression findOpaqueExpression(String vsName,EAnnotation ann){
		EList<EObject> contents = ann.getContents();
		OpaqueExpression vs = null;
		for(EObject eObject:contents){
			if(eObject instanceof OpaqueExpression && vsName.equals(((OpaqueExpression) eObject).getName())){
				vs = (OpaqueExpression) eObject;
				break;
			}
		}
		return vs;
	}


	@Override
	protected NamedElement getOwner(){
		throw new IllegalStateException("EAnnotation not a namedElement");
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