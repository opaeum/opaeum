package org.opaeum.eclipse.commands;

import java.util.Collection;
import java.util.Collections;

import org.eclipse.emf.common.command.AbstractCommand;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.Profile;
import org.eclipse.uml2.uml.ProfileApplication;

public class ApplyProfileCommand extends AbstractCommand{
	private Package umlPackage;
	private Collection<Profile> profiles;
	public ApplyProfileCommand(Package umlPackage,Profile profile){
		this.umlPackage = umlPackage;
		this.profiles = Collections.singletonList(profile);
	}
	@Override
	public boolean canExecute(){
		return true;
	}
	public void execute(){
		for(Profile profile:profiles){
			if(!umlPackage.getAllAppliedProfiles().contains(profile)){
				umlPackage.applyProfile(profile);
			}else{
				for(ProfileApplication pa:umlPackage.getProfileApplications()){
					if(pa.getAppliedProfile().equals(profile) && !pa.getAppliedDefinition().equals(profile.getDefinition())){
						umlPackage.applyProfile(profile);
						break;
					}
				}
			}
		}
	}
	@Override
	public boolean canUndo(){
		return true;
	}
	@Override
	public void undo(){
		for(Profile profile:profiles){
			umlPackage.unapplyProfile(profile);
		}
	}
	public void redo(){
		execute();
	}
}
