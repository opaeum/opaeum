package org.opaeum.modeler.compositestructures.propertysections;

import java.util.Collections;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.uml2.uml.Dependency;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.Usage;

public class PortRequiredInterfaces extends PortInterfacesSection{
	@Override
	protected Command getRemoveCommand(Interface element){
		Dependency usage = getDependency(element);
		CompoundCommand result = new CompoundCommand();
		result.append(RemoveCommand.create(getEditingDomain(), getType(), UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), usage));
		result.append(RemoveCommand.create(getEditingDomain(), usage.getNearestPackage(), UMLPackage.eINSTANCE.getPackage_PackagedElement(), usage));
		return result;
	}
	@Override
	protected Command getCreateCommand(Interface element){
		CompoundCommand result = new CompoundCommand();
		Dependency usage = createDependency(element);
		result.append(AddCommand.create(getEditingDomain(), getType().getNearestPackage(), UMLPackage.eINSTANCE.getPackage_PackagedElement(), usage));
		result.append(AddCommand.create(getEditingDomain(), getType(), UMLPackage.eINSTANCE.getNamedElement_ClientDependency(), usage));
		return result;
	}
	protected Dependency createDependency(Interface intf){
		Usage usage = UMLFactory.eINSTANCE.createUsage();
		usage.setName(intf.getName());
		usage.getSuppliers().add(intf);
		return usage;
	}
	protected Dependency getDependency(Interface intf){
		for(Dependency dependency:getType().getClientDependencies()){
			if(dependency instanceof Usage && dependency.getSuppliers().contains(intf)){
				return dependency;
			}
		}
		return null;
	}
	@Override
	protected EStructuralFeature getFeature(){
		return UMLPackage.eINSTANCE.getNamedElement_ClientDependency();
	}
	@Override
	protected String getLabelText(){
		return "Required Interfaces";
	}
	@Override
	protected Object getListValues(){
		if(((Property) getEObject()).getType() == null){
			return Collections.EMPTY_LIST;
		}else{
			return getType().getUsedInterfaces();
		}
	}
}
