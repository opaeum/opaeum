package org.opaeum.eclipse.uml.propertysections.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.uml2.uml.InstanceSpecification;
import org.eclipse.uml2.uml.UMLPackage;
import org.opaeum.eclipse.context.OpaeumEclipseContext;
import org.opaeum.eclipse.uml.propertysections.base.AbstractChooserPropertySection;



public class InstanceSpecificationClassifierSection extends AbstractChooserPropertySection{
	protected EStructuralFeature getFeature(){
		return UMLPackage.eINSTANCE.getInstanceSpecification_Classifier();
	}
	public String getLabelText(){
		return "Classifier:";
	}
	protected Object[] getComboFeatureValues(){
		EObject element = getEObject();
		return getValidTypes(element);
	}
	public static Object[] getValidTypes(EObject element){
		List<Object> choices = new ArrayList<Object>();
		choices.add("");
		Collection<EObject> types = OpaeumEclipseContext.getReachableObjectsOfType(element, UMLPackage.eINSTANCE.getClass_());
		Iterator<EObject> iterator = types.iterator();
		while(iterator.hasNext()){
			EObject eObject = (EObject) iterator.next();
			if(!eObject.eClass().equals(UMLPackage.eINSTANCE.getClass_())){
				iterator.remove();
			}
		}
		choices.addAll(types);
		return choices.toArray();
	}
	protected Object getFeatureValue(){
		InstanceSpecification instanceSpecification = getInstanceSpecification();
		if(instanceSpecification.getClassifiers().size() == 1){
			return instanceSpecification.getClassifiers().get(0);
		}else{
			return null;
		}
	}
	public InstanceSpecification getInstanceSpecification(){
		InstanceSpecification instanceSpecification = (InstanceSpecification) getEObject();
		return instanceSpecification;
	}
	protected void createCommand(Object oldValue,Object newValue){
		boolean equals = oldValue == null ? false : oldValue.equals(newValue);
		if(!equals){
			EditingDomain editingDomain = getEditingDomain();
			Object value = newValue;
			CompoundCommand compoundCommand = new CompoundCommand(COMMAND_NAME);
			// apply the property change to all selected elements
			for(EObject nextObject:getEObjectList()){
				compoundCommand.append(RemoveCommand.create(editingDomain, nextObject, getFeature(), getInstanceSpecification().getClassifiers()));
				compoundCommand.append(AddCommand.create(editingDomain, nextObject, getFeature(), value));
			}
			editingDomain.getCommandStack().execute(compoundCommand);
		}
	}
}