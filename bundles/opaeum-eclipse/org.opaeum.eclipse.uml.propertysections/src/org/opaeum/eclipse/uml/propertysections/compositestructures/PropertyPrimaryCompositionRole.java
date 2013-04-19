package org.opaeum.eclipse.uml.propertysections.compositestructures;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.UMLPackage;
import org.opaeum.eclipse.uml.propertysections.base.AbstractBooleanPropertySection;
import org.opaeum.name.NameConverter;

public class PropertyPrimaryCompositionRole extends AbstractBooleanPropertySection{
	Property getProperty(){
		return (Property) getSelectedObject();
	}
	@Override
	protected Boolean getDefaultValue(){
		return getProperty().isComposite() && getProperty().getAssociation() != null;
	}
	@Override
	protected EStructuralFeature getFeature(){
		return UMLPackage.eINSTANCE.getProperty_IsComposite();
	}
	@Override
	public String getLabelText(){
		return "Is Primary Role";
	}
	@Override
	protected Command createSingleCommand(EditingDomain editingDomain,Object value,EStructuralFeature f,EObject owner){
		Property p = (Property) owner;
		Boolean isComposite = (Boolean) value;
		CompoundCommand result = new CompoundCommand();
		if(isComposite){
			if(p.getAssociation() == null && p.getType()!=null){
				Package nearestPackage = p.getNearestPackage();
				if(nearestPackage!=null){
					Association ass = UMLFactory.eINSTANCE.createAssociation();
					ass.setName(p.getNamespace().getName()+p.getType().getName());
					Property otherEnd=UMLFactory.eINSTANCE.createProperty();
					otherEnd.setName(NameConverter.decapitalize(p.getNamespace().getName()));
					otherEnd.setUpper(1);
					otherEnd.setType((Type)p.getOwner());
					ass.getNavigableOwnedEnds().add(otherEnd);
					result.append(AddCommand.create(editingDomain, nearestPackage, UMLPackage.eINSTANCE.getPackage_OwnedType(), ass));
					result.append(AddCommand.create(editingDomain, ass, UMLPackage.eINSTANCE.getAssociation_MemberEnd(), p));
//					result.append(SetCommand.create(editingDomain, otherEnd, UMLPackage.eINSTANCE.getProperty__IsNavigable(),true));
				}
			}
		}else{
			if(p.getAssociation() != null){
				result.append(RemoveCommand.create(editingDomain, p.getAssociation().getNearestPackage(), UMLPackage.eINSTANCE.getPackage_OwnedType(), p.getAssociation()));
				result.append(RemoveCommand.create(editingDomain, p.getAssociation(), UMLPackage.eINSTANCE.getAssociation_MemberEnd(), p));
			}
		}
		result.append(SetCommand.create(editingDomain, owner, f, value));
		return result;
	}
}
