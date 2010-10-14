package net.sf.nakeduml.domainmetamodel;

import net.sf.nakeduml.userinteractionmetamodel.UserInteractionWorkspace;
import net.sf.nakeduml.util.CompositionNode;

public class RootDomainPackage extends DomainPackage implements CompositionNode {
	private UserInteractionWorkspace userInteractionWorkspace;

	/** This constructor is intended for easy initialization in unit tests
	 * 
	 * @param owningObject 
	 */
	public RootDomainPackage(UserInteractionWorkspace owningObject) {
		init(owningObject);
		addToOwningObject();
	}
	
	/** Default constructor for 
	 */
	public RootDomainPackage() {
	}

	/** Call this method when you want to attach this object to the containment tree. Useful with transitive persistence
	 */
	public void addToOwningObject() {
		getUserInteractionWorkspace().getDomainPackage().add((RootDomainPackage)this);
	}
	
	public CompositionNode getOwningObject() {
		return getUserInteractionWorkspace();
	}
	
	public UserInteractionWorkspace getUserInteractionWorkspace() {
		return userInteractionWorkspace;
	}
	
	public void init(CompositionNode owner) {
		super.init(owner);
		internalSetOwner((UserInteractionWorkspace)owner);
		createComponents();
	}
	
	public void markDeleted() {
		super.markDeleted();
		if ( getUserInteractionWorkspace()!=null ) {
			getUserInteractionWorkspace().getDomainPackage().remove((RootDomainPackage)this);
		}
	}
	
	public void removeFromOwningObject() {
		this.markDeleted();
	}
	
	public void setUserInteractionWorkspace(UserInteractionWorkspace userInteractionWorkspace) {
		if ( this.userInteractionWorkspace!=null ) {
			this.userInteractionWorkspace.getDomainPackage().remove((RootDomainPackage)this);
		}
		if ( userInteractionWorkspace!=null ) {
			userInteractionWorkspace.getDomainPackage().add((RootDomainPackage)this);
			this.userInteractionWorkspace=userInteractionWorkspace;
		} else {
			this.userInteractionWorkspace=null;
		}
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		if ( getOwner()==null ) {
			sb.append("owner=null;");
		} else {
			sb.append("owner="+getOwner().getClass().getSimpleName()+"[");
			sb.append(getOwner().getName());
			sb.append("];");
		}
		sb.append("name=");
		sb.append(getName());
		sb.append(";");
		sb.append("humanName=");
		sb.append(getHumanName());
		sb.append(";");
		sb.append("qualifiedName=");
		sb.append(getQualifiedName());
		sb.append(";");
		if ( getUserInteractionWorkspace()==null ) {
			sb.append("userInteractionWorkspace=null;");
		} else {
			sb.append("userInteractionWorkspace="+getUserInteractionWorkspace().getClass().getSimpleName()+"[");
			sb.append(getUserInteractionWorkspace().getName());
			sb.append("];");
		}
		return sb.toString();
	}
	
	public String toXmlString() {
		StringBuilder sb = new StringBuilder();
		if ( getName()==null ) {
			sb.append("<name/>");
		} else {
			sb.append("<name>");
			sb.append(getName());
			sb.append("</name>");
			sb.append("\n");
		}
		if ( getHumanName()==null ) {
			sb.append("<humanName/>");
		} else {
			sb.append("<humanName>");
			sb.append(getHumanName());
			sb.append("</humanName>");
			sb.append("\n");
		}
		for ( DomainClassifier ownedClassifier : getOwnedClassifier() ) {
			sb.append("<ownedClassifier>");
			sb.append(ownedClassifier.toXmlString());
			sb.append("</ownedClassifier>");
			sb.append("\n");
		}
		for ( NodeDomainPackage childPackage : getChildPackage() ) {
			sb.append("<childPackage>");
			sb.append(childPackage.toXmlString());
			sb.append("</childPackage>");
			sb.append("\n");
		}
		if ( getUserInteractionWorkspace()==null ) {
			sb.append("<userInteractionWorkspace/>");
		} else {
			sb.append("<userInteractionWorkspace>");
			sb.append(getUserInteractionWorkspace().getClass().getSimpleName());
			sb.append("[");
			sb.append(getUserInteractionWorkspace().getName());
			sb.append("]");
			sb.append("</userInteractionWorkspace>");
			sb.append("\n");
		}
		return sb.toString();
	}
	
	/** Used to set the owner internally in extended composition semantics
	 * 
	 * @param newOwner 
	 */
	protected void internalSetOwner(UserInteractionWorkspace newOwner) {
		this.userInteractionWorkspace=newOwner;
	}
	
	public void copyState(RootDomainPackage from, RootDomainPackage to) {
		to.setName(from.getName());
		to.setHumanName(from.getHumanName());
		for ( DomainClassifier child : getOwnedClassifier() ) {
			to.addToOwnedClassifier(child.makeCopy());
		}
		for ( NodeDomainPackage child : getChildPackage() ) {
			to.addToChildPackage(child.makeCopy());
		}
	}
	
	public void createComponents() {
		super.createComponents();
	}
	
	public RootDomainPackage makeCopy() {
		RootDomainPackage result = new RootDomainPackage();
		copyState((RootDomainPackage)this,result);
		return result;
	}
	
	public void shallowCopyState(RootDomainPackage from, RootDomainPackage to) {
		to.setName(from.getName());
		to.setHumanName(from.getHumanName());
	}

}