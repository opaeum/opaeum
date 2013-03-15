package org.opaeum.eclipse.emulated;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.TypedElement;
import org.opaeum.metamodel.workspace.IPropertyEmulation;

public class EmulatedPropertyHolderForAssociation extends EmulatedPropertyHolder{
	Association owner;
	private List<EndToAssociationClass> endsToAssociationClass = new ArrayList<EndToAssociationClass>();
	@SuppressWarnings("unchecked")
	public EmulatedPropertyHolderForAssociation(Association owner,IPropertyEmulation e){
		super(owner, e, new BasicEList<TypedElement>());
		this.owner = owner;
		for(Property p:owner.getMemberEnds()){
			AssociationClassToEnd otherEnd = new AssociationClassToEnd(p);
			super.addEmulatedAttribute(otherEnd);
		}
		for(Property p:owner.getMemberEnds()){
			EndToAssociationClass thisEnd = new EndToAssociationClass(p);
			endsToAssociationClass.add(thisEnd);
			Property emulatedAttribute = getEmulatedAttribute(p.getOtherEnd());
			if(!(emulatedAttribute instanceof AssociationClassToEnd)){
				System.out.println();
			}
			AssociationClassToEnd associationToEnd = (AssociationClassToEnd) emulatedAttribute;
			thisEnd.setOtherEnd(associationToEnd);
			associationToEnd.setOtherEnd(thisEnd);
		}
	}
	public EndToAssociationClass getEndToAssociation(Property property){
		for(EndToAssociationClass p:this.endsToAssociationClass){
			if(p.getOriginalProperty()== property){
				return p;
			}
		}
		return null;
	}
}
