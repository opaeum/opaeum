package org.opaeum.eclipse.commands;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

import org.eclipse.emf.common.command.AbstractCommand;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Profile;
import org.eclipse.uml2.uml.Stereotype;

public class ApplyStereotypeCommand extends AbstractCommand{
	private Element element;
	private Collection<Stereotype> stereotypes;
	Collection<Stereotype> applied = new HashSet<Stereotype>();
	Collection<Profile> appliedProfile = new HashSet<Profile>();
	public ApplyStereotypeCommand(Element element,Stereotype stereotype){
		this.element = element;
		this.stereotypes = Collections.singletonList(stereotype);
	}
	@Override
	public boolean canExecute(){
		return true;
	}
	public void execute(){
		for(Stereotype stereotype:stereotypes){
			if(!element.getAppliedStereotypes().contains(stereotype)){
				Element owner = getOwner();
				if(owner instanceof org.eclipse.uml2.uml.Package){
					org.eclipse.uml2.uml.Package pkg = (org.eclipse.uml2.uml.Package) owner;
					if(!pkg.getAllAppliedProfiles().contains(pkg)){
						pkg.applyProfile(stereotype.getProfile());
					}
					applied.add(stereotype);
					element.applyStereotype(stereotype);
				}
			}
		}
	}
	private Element getOwner(){
		Element owner = element;
		while(owner.getOwner() != null){
			owner = owner.getOwner();
		}
		return owner;
	}
	@Override
	public boolean canUndo(){
		return true;
	}
	@Override
	public void undo(){
		// TODO unapply profiles
		for(Stereotype stereotype:applied){
			element.unapplyStereotype(stereotype);
		}
		for(Profile profile:appliedProfile){
			Element owner = getOwner();
			if(owner instanceof org.eclipse.uml2.uml.Package){
				((org.eclipse.uml2.uml.Package) owner).unapplyProfile(profile);
			}
		}
	}
	public void redo(){
		execute();
	}
}
