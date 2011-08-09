package org.nakeduml.topcased.commands;

import org.eclipse.emf.common.util.EList;
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
		Object eGet = owner.eGet(feature);
		return (ValueSpecification) eGet;
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
			OpaqueExpression oe = (OpaqueExpression) getValueSpecification();
			EList<String> languages = oe.getLanguages();
			if(languages.isEmpty() || !(languages.contains("OCL") || languages.contains("ocl"))){
				languages.add("OCL");
			}
			for(int i=0; i<languages.size(); i ++){
				if(languages.get(i).equalsIgnoreCase("OCL")){
					while(oe.getBodies().size()<=i){
						oe.getBodies().add("dummy");
					}
					oe.getBodies().set(i,expression);
				}
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
