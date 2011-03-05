package net.sf.nakeduml.domainmetamodel;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.nakeduml.runtime.domain.CompositionNode;

import util.Stdlib;

abstract public class DomainPackage extends DomainElement implements CompositionNode {
	private Set<DomainClassifier> ownedClassifier = new HashSet<DomainClassifier>();
	private Set<NodeDomainPackage> childPackage = new HashSet<NodeDomainPackage>();

	/** Default constructor for 
	 */
	public DomainPackage() {
	}

	public void addAllToChildPackage(Set<NodeDomainPackage> childPackage) {
		for ( NodeDomainPackage o : childPackage ) {
			addToChildPackage(o);
		}
	}
	
	public void addAllToOwnedClassifier(Set<DomainClassifier> ownedClassifier) {
		for ( DomainClassifier o : ownedClassifier ) {
			addToOwnedClassifier(o);
		}
	}
	
	public void addToChildPackage(NodeDomainPackage childPackage) {
		childPackage.setParentPackage(this);
	}
	
	public void addToOwnedClassifier(DomainClassifier ownedClassifier) {
		ownedClassifier.setDomainPackage(this);
	}
	
	/** Call this method when you want to attach this object to the containment tree. Useful with transitive persistence
	 */
	public void addToOwningObject() {
	}
	
	public void clearChildPackage() {
		removeAllFromChildPackage(getChildPackage());
	}
	
	public void clearOwnedClassifier() {
		removeAllFromOwnedClassifier(getOwnedClassifier());
	}
	
	public void copyState(DomainPackage from, DomainPackage to) {
		to.setName(from.getName());
		to.setHumanName(from.getHumanName());
		for ( DomainClassifier child : getOwnedClassifier() ) {
			to.addToOwnedClassifier(child.makeCopy());
		}
		for ( NodeDomainPackage child : getChildPackage() ) {
			to.addToChildPackage(child.makeCopy());
		}
	}
	
	public NodeDomainPackage createChildPackage() {
		NodeDomainPackage newInstance= new NodeDomainPackage();
		newInstance.init(this);
		return newInstance;
	}
	
	public void createComponents() {
		super.createComponents();
	}
	
	public DomainClassifier createOwnedClassifier() {
		DomainClassifier newInstance= new DomainClassifier();
		newInstance.init(this);
		return newInstance;
	}
	
	public Set<NodeDomainPackage> getChildPackage() {
		return childPackage;
	}
	
	public Set<DomainEntity> getEntity() {
		Set<DomainEntity> entity = (Set)Stdlib.collectionAsSet(collect2());
		return entity;
	}
	
	public Set<DomainClassifier> getOwnedClassifier() {
		return ownedClassifier;
	}
	
	public Set<DomainElement> getOwnedElement() {
		Set<DomainElement> ownedElementSubsetting = new HashSet<DomainElement>();
		ownedElementSubsetting.addAll(super.getOwnedElement());
		ownedElementSubsetting.addAll(getOwnedClassifier());
		ownedElementSubsetting.addAll(getChildPackage());
		return ownedElementSubsetting;
	}
	
	public CompositionNode getOwningObject() {
		return null;
	}
	
	public void init(CompositionNode owner) {
		super.init(owner);
		createComponents();
	}
	
	abstract public DomainPackage makeCopy();
	
	public void markDeleted() {
		super.markDeleted();
		for ( DomainClassifier child : new ArrayList<DomainClassifier>(getOwnedClassifier()) ) {
			child.markDeleted();
		}
		for ( NodeDomainPackage child : new ArrayList<NodeDomainPackage>(getChildPackage()) ) {
			child.markDeleted();
		}
	}
	
	public void removeAllFromChildPackage(Set<NodeDomainPackage> childPackage) {
		for ( NodeDomainPackage o : childPackage ) {
			removeFromChildPackage(o);
		}
	}
	
	public void removeAllFromOwnedClassifier(Set<DomainClassifier> ownedClassifier) {
		for ( DomainClassifier o : ownedClassifier ) {
			removeFromOwnedClassifier(o);
		}
	}
	
	public void removeFromChildPackage(NodeDomainPackage childPackage) {
		childPackage.setParentPackage(null);
	}
	
	public void removeFromOwnedClassifier(DomainClassifier ownedClassifier) {
		ownedClassifier.setDomainPackage(null);
	}
	
	public void removeFromOwningObject() {
		this.markDeleted();
	}
	
	public void setChildPackage(Set<NodeDomainPackage> childPackage) {
		for ( NodeDomainPackage o : new HashSet<NodeDomainPackage>(this.childPackage) ) {
			o.setParentPackage(null);
		}
		for ( NodeDomainPackage o : childPackage ) {
			o.setParentPackage((DomainPackage)this);
		}
	}
	
	public void setOwnedClassifier(Set<DomainClassifier> ownedClassifier) {
		for ( DomainClassifier o : new HashSet<DomainClassifier>(this.ownedClassifier) ) {
			o.setDomainPackage(null);
		}
		for ( DomainClassifier o : ownedClassifier ) {
			o.setDomainPackage((DomainPackage)this);
		}
	}
	
	public void shallowCopyState(DomainPackage from, DomainPackage to) {
		to.setName(from.getName());
		to.setHumanName(from.getHumanName());
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
		return sb.toString();
	}
	
	/** Implements ->collect( c : DomainClassifier | c.oclAsType(DomainEntity) )
	 */
	private List<DomainEntity> collect2() {
		List<DomainEntity> result = new ArrayList<DomainEntity>();
		for ( DomainClassifier c : select1() ) {
			DomainEntity bodyExpResult = ((DomainEntity) c);
			if ( bodyExpResult != null ) result.add( bodyExpResult );
		}
		return result;
	}
	
	/** Implements ->select( c : DomainClassifier | c.oclIsKindOf(DomainEntity) )
	 */
	private Set<DomainClassifier> select1() {
		Set<DomainClassifier> result = new HashSet<DomainClassifier>();
		for ( DomainClassifier c : this.getOwnedClassifier() ) {
			if ( (false /* types of oclIsKindOf do not conform */) ) {
				result.add( c );
			}
		}
		return result;
	}

}