package org.opaeum.runtime.jface.entityeditor;


import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.runtime.rwt.OpaeumRapSession;
import org.opaeum.uim.constraint.ConstrainedObject;
import org.opaeum.uim.constraint.EditableConstrainedObject;
import org.opaeum.uim.constraint.UserInteractionConstraint;

public class SecurityUtil extends SecurityWrapper{
	IPersistentObject selectedObject;
	OpaeumRapSession session;
	public SecurityUtil(IPersistentObject selectedObject,OpaeumRapSession session){
		super();
		this.selectedObject = selectedObject;
		this.session = session;
	}
	@Override
	public IPersistentObject getSelectedObject(){
		return selectedObject;
	}
	@Override
	public OpaeumRapSession getSession(){
		return session;
	}
	public boolean calculateVisibility(ConstrainedObject uimField){
		return applyConstraint(uimField.getVisibility());
	}
	public boolean calculateEditability(EditableConstrainedObject uimField){
		UserInteractionConstraint uic = findApplicableEditabilityConstraint(uimField);
		return applyConstraint(uic);
	}
}