package org.nakeduml.runtime.adaptor;

import org.nakeduml.runtime.domain.AbstractEntity;
import org.nakeduml.runtime.domain.AbstractUser;

public abstract class EntityHelper {

	public void setSelectedObject(AbstractEntity auditedObject){
		// TODO Auto-generated method stub
		
	}
//	private AbstractUser user;
//	private EntityHelper[] selectionPath;
//	public boolean getIsLandingObject() {
//		if(this.getUser()==null || getUser().getOwningObject()==null){
//			if(this.getSelectedObject() instanceof CompositionNode){
//				return ((CompositionNode)this.getSelectedObject()).getOwningObject()==null;
//			}else{
//				return true; // No composition tree every object is landing object
//			}
//		}else{
//			return this.getSelectedObject().equals(getUser().getOwningObject());
//		}
//	}
//	public boolean getIsOwnedByLandingObject() {
//		if(getUser() ==null){
//			//just let the selectionpath point all the way to this objects' root
//			return true;
//		}else if (getSelectedObject() instanceof CompositionNode) {
//			CompositionNode object = (CompositionNode) getSelectedObject();
//			while (object != null) {
//				if (getUser().getOwningObject().equals(object)) {
//					return true;
//				} else {
//					object = object.getOwningObject();
//				}
//			}
//			return false;
//		} else {
//			//no composition tree, no selectionpath
//			return false;
//		}
//	}
//	public EntityHelper[] getSelectionPath() {
//		if (this.selectionPath == null) {
//			if (getIsOwnedByLandingObject()) {
//				CompositionNode object = (CompositionNode) getSelectedObject();
//				List<EntityHelper> results = new ArrayList<EntityHelper>();
//				while (object != null) {
//					EntityHelper helper = IntrospectionUtil.createHelper((MetaIdentifiable) object);
//					helper.setSelectedObject((AbstractEntity) object);
//					helper.setUser(getUser());
//					results.add(0, helper);
//					if (getUser()!=null && getUser().getOwningObject().equals(object)) {
//						object = null;
//					} else {
//						object = object.getOwningObject();
//					}
//				}
//				this.selectionPath = results.toArray(new EntityHelper[results.size()]);
//			} else {
//				this.selectionPath = new EntityHelper[] { this };
//			}
//		}
//		return this.selectionPath;
//	}
//	public boolean isUserOwnershipValid() {
//		if (getSelectedObject() instanceof SecureObject && getUser() !=null) {
//			SecureObject objet = (SecureObject) getSelectedObject();
//			if (objet.canBeOwnedByUser(getUser())) {
//				// user ownership rules apply - selected object must be owned by
//				// user
//				return objet.isOwnedByUser(getUser());
//			} else {
//				// no user ownership rules apply
//				return true;
//			}
//		} else {
//			return true;
//		}
//	}
//	public boolean isGroupOwnershipValid() {
//		if (getSelectedObject() instanceof SecureObject && getUser() !=null) {
//			SecureObject objet = (SecureObject) getSelectedObject();
//			if (objet.canBeOwnedByGroup(getUser())) {
//				// user ownership rules apply - selected object must be owned by
//				// user
//				return objet.isOwnedByGroup(getUser());
//			} else {
//				// no user ownership rules apply
//				return true;
//			}
//		} else {
//			return true;
//		}
//	}
//	public abstract void setSelectedObject(AbstractEntity selectedObject);
//	public abstract AbstractEntity getSelectedObject();
//	public void setUser(AbstractUserRole user) {
//		this.user = user;
//	}
//	public AbstractUser getUser() {
//		return this.user;
//	}

	public void setUser(AbstractUser currentUser){
		// TODO Auto-generated method stub
		
	}
}
