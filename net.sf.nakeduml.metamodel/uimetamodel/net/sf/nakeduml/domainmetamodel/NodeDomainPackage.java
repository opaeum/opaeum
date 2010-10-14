package net.sf.nakeduml.domainmetamodel;

import net.sf.nakeduml.util.CompositionNode;

public class NodeDomainPackage extends DomainPackage implements CompositionNode {
	private DomainPackage parentPackage;

	/** Default constructor for 
	 */
	public NodeDomainPackage() {
	}
	
	/** This constructor is intended for easy initialization in unit tests
	 * 
	 * @param owningObject 
	 */
	public NodeDomainPackage(DomainPackage owningObject) {
		init(owningObject);
		addToOwningObject();
	}

	/** Call this method when you want to attach this object to the containment tree. Useful with transitive persistence
	 */
	public void addToOwningObject() {
		getParentPackage().getChildPackage().add((NodeDomainPackage)this);
	}
	
	public DomainElement getOwner() {
		DomainElement ownerSubsetting = null;
		ownerSubsetting=super.getOwner();
		if ( getParentPackage()!=null ) {
			ownerSubsetting=getParentPackage();
		}
		return ownerSubsetting;
	}
	
	public CompositionNode getOwningObject() {
		return getParentPackage();
	}
	
	public DomainPackage getParentPackage() {
		return parentPackage;
	}
	
	public void init(CompositionNode owner) {
		super.init(owner);
		internalSetOwner((DomainPackage)owner);
		createComponents();
	}
	
	public void markDeleted() {
		super.markDeleted();
		if ( getParentPackage()!=null ) {
			getParentPackage().getChildPackage().remove((NodeDomainPackage)this);
		}
	}
	
	public void removeFromOwningObject() {
		this.markDeleted();
	}
	
	public void setParentPackage(DomainPackage parentPackage) {
		if ( this.parentPackage!=null ) {
			this.parentPackage.getChildPackage().remove((NodeDomainPackage)this);
		}
		if ( parentPackage!=null ) {
			parentPackage.getChildPackage().add((NodeDomainPackage)this);
			this.parentPackage=parentPackage;
		} else {
			this.parentPackage=null;
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
		if ( getParentPackage()==null ) {
			sb.append("parentPackage=null;");
		} else {
			sb.append("parentPackage="+getParentPackage().getClass().getSimpleName()+"[");
			sb.append(getParentPackage().getName());
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
		if ( getParentPackage()==null ) {
			sb.append("<parentPackage/>");
		} else {
			sb.append("<parentPackage>");
			sb.append(getParentPackage().getClass().getSimpleName());
			sb.append("[");
			sb.append(getParentPackage().getName());
			sb.append("]");
			sb.append("</parentPackage>");
			sb.append("\n");
		}
		return sb.toString();
	}
	
	/** Used to set the owner internally in extended composition semantics
	 * 
	 * @param newOwner 
	 */
	protected void internalSetOwner(DomainPackage newOwner) {
		this.parentPackage=newOwner;
	}
	
	public void copyState(NodeDomainPackage from, NodeDomainPackage to) {
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
	
	public NodeDomainPackage makeCopy() {
		NodeDomainPackage result = new NodeDomainPackage();
		copyState((NodeDomainPackage)this,result);
		return result;
	}
	
	public void shallowCopyState(NodeDomainPackage from, NodeDomainPackage to) {
		to.setName(from.getName());
		to.setHumanName(from.getHumanName());
	}

}