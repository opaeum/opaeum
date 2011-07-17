package org.nakeduml.topcased.commands;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.AbstractOverrideableCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.OpaqueExpression;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.ValueSpecification;

public class SetOclExpressionCommand extends AbstractOverrideableCommand{
	protected static final String DEFAULT_TEXT = "Type expression here";
	public static SetOclExpressionCommand create(EditingDomain d,NamedElement owner, EStructuralFeature feature, String expression){
		return new SetOclExpressionCommand(d, owner,feature,expression);
	}
	private NamedElement owner;
	EStructuralFeature feature;
	private String expression;
	public ValueSpecification getValueSpecification(){
		return (ValueSpecification) owner.eGet(feature);
	}

	public SetOclExpressionCommand(EditingDomain domain, NamedElement owner,EStructuralFeature feature, String expression){
		super(domain,"Set Ocl Expression");
		this.owner = owner;
		this.feature=feature;
		this.expression=expression;
	}
	@Override
	public boolean doCanExecute(){
		super.doCanExecute();
		return true;
	}
	@Override
	public void doExecute(){
		if(!(getValueSpecification() instanceof OpaqueExpression)){
			OpaqueExpression oe = UMLFactory.eINSTANCE.createOpaqueExpression();
			oe.setName(owner.getName() + "Expression");
			if(oe.getBodies().isEmpty()){
				oe.getBodies().add(expression);
				oe.getLanguages().add("OCL");
			}
			owner.eSet(feature, oe);
		}else {
			//TODO look for ocl language
			OpaqueExpression oe = (OpaqueExpression) getValueSpecification();
			if(oe.getBodies().isEmpty()){
				oe.getBodies().add(expression);
				oe.getLanguages().add("OCL");
			}else{
				oe.getBodies().set(0,expression);
				oe.getLanguages().set(0,"OCL");
			}
		}
	}
	@Override
	public void doUndo(){
		// TODO Auto-generated method stub
	}
	@Override
	public void doRedo(){
		doExecute();
	}
}
