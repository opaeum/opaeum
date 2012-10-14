package org.opaeum.eclipse.uml.propertysections.compositestructures;

import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.uml2.uml.BehavioredClassifier;
import org.eclipse.uml2.uml.Dependency;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.InterfaceRealization;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.UMLPackage;

public class PortProvidedInterfaces extends PortInterfacesSection{
	@Override
	protected Command getRemoveCommand(BehavioredClassifier bc,Interface element){
		Dependency usage = getDependency(bc,element);
		return RemoveCommand.create(getEditingDomain(),bc, UMLPackage.eINSTANCE.getBehavioredClassifier_InterfaceRealization(), usage);
	}
	@Override
	protected Command getCreateCommand(BehavioredClassifier bc,Interface element){
		Dependency usage = createDependency(element);
		return AddCommand.create(getEditingDomain(), bc, UMLPackage.eINSTANCE.getBehavioredClassifier_InterfaceRealization(), usage);
	}
	protected Dependency createDependency(Interface intf){
		InterfaceRealization usage = UMLFactory.eINSTANCE.createInterfaceRealization();
		usage.setName(intf.getName());
		usage.setContract(intf);
		return usage;
	}
	protected InterfaceRealization getDependency(BehavioredClassifier bc, Interface intf){
		for(InterfaceRealization dependency:bc.getInterfaceRealizations()){
			if(dependency.getContract() == intf){
				return dependency;
			}
		}
		return null;
	}
	@Override
	protected EStructuralFeature getFeature(){
		return UMLPackage.eINSTANCE.getBehavioredClassifier_InterfaceRealization();
	}
	@Override
	public String getLabelText(){
		return "Provided Interfaces";
	}
	@Override
	protected List<?> getListValues(){
		if(((Property) getSelectedObject()).getType() == null){
			return Collections.EMPTY_LIST;
		}else{
			return getType(getSelectedObject()).getImplementedInterfaces();
		}
	}
}
