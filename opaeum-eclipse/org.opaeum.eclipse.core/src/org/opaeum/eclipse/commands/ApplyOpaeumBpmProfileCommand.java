package org.opaeum.eclipse.commands;

import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.Profile;
import org.opaeum.eclipse.ProfileApplier;
import org.opaeum.metamodel.core.internal.StereotypeNames;

public class ApplyOpaeumBpmProfileCommand extends ApplyProfileCommand{
	private Profile profile;

	public ApplyOpaeumBpmProfileCommand(EditingDomain ed,Package umlPackage){
		super(umlPackage, getProfile(ed, umlPackage),false);
		this.setProfile(getProfile(ed, umlPackage));
	}

	private static Profile getProfile(EditingDomain ed,Package umlPackage){
		return ProfileApplier.getProfile(umlPackage,StereotypeNames.OPAEUM_BPM_PROFILE);
	}

	public Profile getProfile(){
		return profile;
	}

	public void setProfile(Profile profile){
		this.profile = profile;
	}
}
