package org.opaeum.eclipse.emulated;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.Property;
import org.opaeum.metamodel.workspace.IPropertyEmulation;

public class EmulatedPropertyHolderForAssociation extends EmulatedPropertyHolder{
	Association owner;
	private List<EndToAssociationClass> endsToAssociationClass = new ArrayList<EndToAssociationClass>();
	@SuppressWarnings("unchecked")
	public EmulatedPropertyHolderForAssociation(Association owner,IPropertyEmulation e){
		super(owner, e, owner.getAttributes());
		this.owner = owner;
		for(Property p:owner.getMemberEnds()){
			EndToAssociationClass thisEnd = new EndToAssociationClass(p);
			endsToAssociationClass.add(thisEnd);
			AssociationClassToEnd otherEnd = new AssociationClassToEnd(p);
			thisEnd.setOtherEnd(otherEnd);
			super.addEmulatedAttribute(otherEnd);
		}
	}
	public EndToAssociationClass getEndToAssociation(Property property){
		for(EndToAssociationClass p:this.endsToAssociationClass){
			if(p.getOriginalElement()==property){
				return p;
			}
		}
		return null;
	}
}
