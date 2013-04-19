package org.opaeum.runtime.jface.entityeditor;

import java.util.Collection;

import org.opaeum.annotation.NumlMetaInfo;
import org.opaeum.ecore.EObject;
import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.runtime.domain.IntrospectionUtil;
import org.opaeum.runtime.organization.IBusinessRoleBase;
import org.opaeum.runtime.rwt.OpaeumRapSession;
import org.opaeum.uim.constraint.EditableConstrainedObject;
import org.opaeum.uim.constraint.RequiredRole;
import org.opaeum.uim.constraint.UserInteractionConstraint;

public abstract class SecurityWrapper{
	public SecurityWrapper(){
		super();
	}

	public abstract OpaeumRapSession getSession();

	public abstract IPersistentObject getSelectedObject();

	protected boolean applyConstraint(UserInteractionConstraint uic){
		boolean visibility = true;
		if(uic != null){
			if(uic.isRequiresGroupOwnership()){
				visibility = isGroupOwnershipOk();
			}
			if(uic.isRequiresOwnership()){
				visibility &= isUserOwnershipOk();
			}
			visibility &= hasAllowedRoles(uic);
		}
		return visibility;
	}

	public boolean isUserOwnershipOk(){
		CompositionNode cn = (CompositionNode) getSelectedObject();
		boolean userOwnershipOk = false;
		for(IBusinessRoleBase br:getSession().getPersonNode().getBusinessRole()){
			if(contains(br, cn)){
				userOwnershipOk = true;
				break;
			}
		}
		return userOwnershipOk;
	}

	public boolean isGroupOwnershipOk(){
		CompositionNode cn = (CompositionNode) getSelectedObject();
		boolean groupOwnershipOk = false;
		for(IBusinessRoleBase br:getSession().getPersonNode().getBusinessRole()){
			if(contains(br.getOwningObject(), cn)){
				groupOwnershipOk = true;
				break;
			}
		}
		return groupOwnershipOk;
	}

	protected UserInteractionConstraint findApplicableEditabilityConstraint(EditableConstrainedObject uimField){
		UserInteractionConstraint uic = null;
		EObject container = uimField;
		while(container != null){
			if(container instanceof EditableConstrainedObject){
				EditableConstrainedObject eco = (EditableConstrainedObject) container;
				if(eco.getEditability() == null || eco.getEditability().isInheritFromParent()){
					container = eco.eContainer();
				}else{
					uic = eco.getEditability();
					container = null;
				}
			}else{
				container = container.eContainer();
			}
		}
		return uic;
	}

	private boolean contains(CompositionNode businessComponent,CompositionNode selectedObject){
		if(selectedObject.equals(businessComponent)){
			return true;
		}else if(selectedObject.getOwningObject() != null && contains(businessComponent, selectedObject.getOwningObject())){
			return true;
		}else{
			return false;
		}
	}

	private boolean hasAllowedRoles(UserInteractionConstraint uic){
		if(uic.getRequiredRoles().isEmpty()){
			return true;
		}else{
			for(RequiredRole requiredRole:uic.getRequiredRoles()){
				Collection<? extends IBusinessRoleBase> brs = getSession().getPersonNode().getBusinessRole();
				for(IBusinessRoleBase br:brs){
					NumlMetaInfo annotation = IntrospectionUtil.getOriginalClass(br).getAnnotation(NumlMetaInfo.class);
					if(annotation != null && annotation.uuid().equals(requiredRole.getUmlElementUid())){
						return true;
					}
				}
			}
			return false;
		}
	}
}