/*******************************************************************************
 * Copyright (c) 2012 EclipseSource and others. All rights reserved.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution, 
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   EclipseSource - initial API and implementation
 ******************************************************************************/
package org.opaeum.rap.runtime.internal.editors;

import java.util.Collection;

import org.eclipse.emf.ecore.EObject;
import org.opaeum.annotation.NumlMetaInfo;
import org.opaeum.rap.runtime.OpaeumRapSession;
import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.runtime.domain.IntrospectionUtil;
import org.opaeum.runtime.organization.IBusinessRoleBase;
import org.opaeum.uim.constraint.ConstrainedObject;
import org.opaeum.uim.constraint.EditableConstrainedObject;
import org.opaeum.uim.constraint.RequiredRole;
import org.opaeum.uim.constraint.UserInteractionConstraint;

public class SecurityUtil{
	IPersistentObject selectedObject;
	OpaeumRapSession session;
	public SecurityUtil(IPersistentObject selectedObject,OpaeumRapSession session){
		super();
		this.selectedObject = selectedObject;
		this.session = session;
	}
	public boolean calculateVisibility(ConstrainedObject uimField){
		return applyConstraint(uimField.getVisibility());
	}
	private boolean applyConstraint(UserInteractionConstraint uic){
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
	public boolean calculateEditability(EditableConstrainedObject uimField){
		UserInteractionConstraint uic = findApplicableEditabilityConstraint(uimField);
		return applyConstraint(uic);
	}
	public boolean isUserOwnershipOk(){
		CompositionNode cn = (CompositionNode) selectedObject;
		boolean userOwnershipOk = false;
		for(IBusinessRoleBase br:session.getPersonNode().getBusinessRole()){
			if(contains(br, cn)){
				userOwnershipOk = true;
				break;
			}
		}
		return userOwnershipOk;
	}
	public boolean isGroupOwnershipOk(){
		CompositionNode cn = (CompositionNode) selectedObject;
		boolean groupOwnershipOk = false;
		for(IBusinessRoleBase br:session.getPersonNode().getBusinessRole()){
			if(contains(br.getOwningObject(), cn)){
				groupOwnershipOk = true;
				break;
			}
		}
		return groupOwnershipOk;
	}
	private UserInteractionConstraint findApplicableEditabilityConstraint(EditableConstrainedObject uimField){
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
				Collection<? extends IBusinessRoleBase> brs = session.getPersonNode().getBusinessRole();
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