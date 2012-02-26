package org.opaeum.modeler.compositestructures.propertysections;

import java.util.Collections;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.uml2.uml.Dependency;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.InterfaceRealization;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.UMLPackage;

public class PortProvidedInterfaces extends PortInterfacesSection{
	@Override
	protected Command getRemoveCommand(Interface element){
		Dependency usage = getDependency(element);
		return RemoveCommand.create(getEditingDomain(), getType(), UMLPackage.eINSTANCE.getBehavioredClassifier_InterfaceRealization(), usage);
	}
	@Override
	protected Command getCreateCommand(Interface element){
		Dependency usage = createDependency(element);
		return AddCommand.create(getEditingDomain(), getType(), UMLPackage.eINSTANCE.getBehavioredClassifier_InterfaceRealization(), usage);
	}
	protected Dependency createDependency(Interface intf){
		InterfaceRealization usage = UMLFactory.eINSTANCE.createInterfaceRealization();
		usage.setName(intf.getName());
		usage.setContract(intf);
		return usage;
	}
	protected InterfaceRealization getDependency(Interface intf){
		for(InterfaceRealization dependency:getType().getInterfaceRealizations()){
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
	protected String getLabelText(){
		return "Provided Interfaces";
	}
	@Override
	protected Object getListValues(){
		if(((Property) getEObject()).getType() == null){
			return Collections.EMPTY_LIST;
		}else{
			return getType().getImplementedInterfaces();
		}
	}
}
