package org.opaeum.eclipse.commands;

import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.Profile;
import org.opaeum.eclipse.ProfileApplier;
import org.opaeum.metamodel.core.internal.StereotypeNames;

public class ApplyOpaeumStandardProfileCommand extends ApplyProfileCommand{
	private Profile profile;

	public ApplyOpaeumStandardProfileCommand(EditingDomain ed,Package umlPackage){
		super(umlPackage, getProfile(ed, umlPackage));
		this.setProfile(getProfile(ed, umlPackage));
	}

	private static Profile getProfile(EditingDomain ed,Package umlPackage){
		return ProfileApplier.getProfile(umlPackage,
				ed instanceof TransactionalEditingDomain ? StereotypeNames.OPAEUM_STANDARD_PROFILE_PAPYRUS
						: StereotypeNames.OPAEUM_STANDARD_PROFILE_TOPCASED);
	}

	public Profile getProfile(){
		return profile;
	}

	public void setProfile(Profile profile){
		this.profile = profile;
	}
}
