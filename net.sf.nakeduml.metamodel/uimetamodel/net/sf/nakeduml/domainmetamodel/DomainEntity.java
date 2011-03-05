package net.sf.nakeduml.domainmetamodel;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.nakeduml.runtime.domain.CompositionNode;


public class DomainEntity extends DomainClassifier implements CompositionNode {
	private Set<DomainOperation> operation = new HashSet<DomainOperation>();

	/** This constructor is intended for easy initialization in unit tests
	 * 
	 * @param owningObject 
	 */
	public DomainEntity(DomainPackage owningObject) {
		init(owningObject);
		addToOwningObject();
	}
	
	/** Default constructor for 
	 */
	public DomainEntity() {
	}

	public void addAllToOperation(Set<DomainOperation> operation) {
		for ( DomainOperation o : operation ) {
			addToOperation(o);
		}
	}
	
	public void addToOperation(DomainOperation operation) {
		operation.setEntity(this);
	}
	
	/** Call this method when you want to attach this object to the containment tree. Useful with transitive persistence
	 */
	public void addToOwningObject() {
		getDomainPackage().getOwnedClassifier().add((DomainEntity)this);
	}
	
	public void clearOperation() {
		removeAllFromOperation(getOperation());
	}
	
	public void copyState(DomainEntity from, DomainEntity to) {
		to.setName(from.getName());
		to.setHumanName(from.getHumanName());
		for ( DomainProperty child : getProperty() ) {
			to.addToProperty(child.makeCopy());
		}
		to.setQualifiedImplementationType(from.getQualifiedImplementationType());
		to.setClassifierKind(from.getClassifierKind());
		if ( getSecurityOnEdit()!=null ) {
			to.setSecurityOnEdit(getSecurityOnEdit().makeCopy());
		}
		if ( getSecurityOnView()!=null ) {
			to.setSecurityOnView(getSecurityOnView().makeCopy());
		}
		if ( getSecurityOnDelete()!=null ) {
			to.setSecurityOnDelete(getSecurityOnDelete().makeCopy());
		}
		if ( getSecurityOnAdd()!=null ) {
			to.setSecurityOnAdd(getSecurityOnAdd().makeCopy());
		}
		for ( DomainOperation child : getOperation() ) {
			to.addToOperation(child.makeCopy());
		}
	}
	
	public void createComponents() {
		super.createComponents();
	}
	
	public DomainOperation createOperation() {
		DomainOperation newInstance= new DomainOperation();
		newInstance.init(this);
		return newInstance;
	}
	
	public Set<DomainOperation> getOperation() {
		return operation;
	}
	
	public Set<DomainElement> getOwnedElement() {
		Set<DomainElement> ownedElementSubsetting = new HashSet<DomainElement>();
		ownedElementSubsetting.addAll(super.getOwnedElement());
		ownedElementSubsetting.addAll(getOperation());
		return ownedElementSubsetting;
	}
	
	public CompositionNode getOwningObject() {
		return getDomainPackage();
	}
	
	public void init(CompositionNode owner) {
		super.init(owner);
		internalSetOwner((DomainPackage)owner);
		createComponents();
	}
	
	public DomainEntity makeCopy() {
		DomainEntity result = new DomainEntity();
		copyState((DomainEntity)this,result);
		return result;
	}
	
	public void markDeleted() {
		super.markDeleted();
		if ( getDomainPackage()!=null ) {
			getDomainPackage().getOwnedClassifier().remove((DomainEntity)this);
		}
		for ( DomainOperation child : new ArrayList<DomainOperation>(getOperation()) ) {
			child.markDeleted();
		}
	}
	
	public void removeAllFromOperation(Set<DomainOperation> operation) {
		for ( DomainOperation o : operation ) {
			removeFromOperation(o);
		}
	}
	
	public void removeFromOperation(DomainOperation operation) {
		operation.setEntity(null);
	}
	
	public void removeFromOwningObject() {
		this.markDeleted();
	}
	
	public void setOperation(Set<DomainOperation> operation) {
		for ( DomainOperation o : new HashSet<DomainOperation>(this.operation) ) {
			o.setEntity(null);
		}
		for ( DomainOperation o : operation ) {
			o.setEntity((DomainEntity)this);
		}
	}
	
	public void shallowCopyState(DomainEntity from, DomainEntity to) {
		to.setName(from.getName());
		to.setHumanName(from.getHumanName());
		to.setQualifiedImplementationType(from.getQualifiedImplementationType());
		to.setClassifierKind(from.getClassifierKind());
		if ( getSecurityOnEdit()!=null ) {
			to.setSecurityOnEdit(getSecurityOnEdit().makeCopy());
		}
		if ( getSecurityOnView()!=null ) {
			to.setSecurityOnView(getSecurityOnView().makeCopy());
		}
		if ( getSecurityOnDelete()!=null ) {
			to.setSecurityOnDelete(getSecurityOnDelete().makeCopy());
		}
		if ( getSecurityOnAdd()!=null ) {
			to.setSecurityOnAdd(getSecurityOnAdd().makeCopy());
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
		if ( getDomainPackage()==null ) {
			sb.append("domainPackage=null;");
		} else {
			sb.append("domainPackage="+getDomainPackage().getClass().getSimpleName()+"[");
			sb.append(getDomainPackage().getName());
			sb.append("];");
		}
		sb.append("qualifiedImplementationType=");
		sb.append(getQualifiedImplementationType());
		sb.append(";");
		sb.append("classifierKind=");
		sb.append(getClassifierKind());
		sb.append(";");
		if ( getSecurityOnEdit()==null ) {
			sb.append("securityOnEdit=null;");
		} else {
			sb.append("securityOnEdit="+getSecurityOnEdit().getClass().getSimpleName()+"[");
			sb.append(getSecurityOnEdit().hashCode());
			sb.append("];");
		}
		if ( getSecurityOnView()==null ) {
			sb.append("securityOnView=null;");
		} else {
			sb.append("securityOnView="+getSecurityOnView().getClass().getSimpleName()+"[");
			sb.append(getSecurityOnView().hashCode());
			sb.append("];");
		}
		if ( getSecurityOnDelete()==null ) {
			sb.append("securityOnDelete=null;");
		} else {
			sb.append("securityOnDelete="+getSecurityOnDelete().getClass().getSimpleName()+"[");
			sb.append(getSecurityOnDelete().hashCode());
			sb.append("];");
		}
		if ( getSecurityOnAdd()==null ) {
			sb.append("securityOnAdd=null;");
		} else {
			sb.append("securityOnAdd="+getSecurityOnAdd().getClass().getSimpleName()+"[");
			sb.append(getSecurityOnAdd().hashCode());
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
		for ( DomainProperty property : getProperty() ) {
			sb.append("<property>");
			sb.append(property.toXmlString());
			sb.append("</property>");
			sb.append("\n");
		}
		if ( getDomainPackage()==null ) {
			sb.append("<domainPackage/>");
		} else {
			sb.append("<domainPackage>");
			sb.append(getDomainPackage().getClass().getSimpleName());
			sb.append("[");
			sb.append(getDomainPackage().getName());
			sb.append("]");
			sb.append("</domainPackage>");
			sb.append("\n");
		}
		if ( getQualifiedImplementationType()==null ) {
			sb.append("<qualifiedImplementationType/>");
		} else {
			sb.append("<qualifiedImplementationType>");
			sb.append(getQualifiedImplementationType());
			sb.append("</qualifiedImplementationType>");
			sb.append("\n");
		}
		if ( getClassifierKind()==null ) {
			sb.append("<classifierKind/>");
		} else {
			sb.append("<classifierKind>");
			sb.append(getClassifierKind());
			sb.append("</classifierKind>");
			sb.append("\n");
		}
		if ( getSecurityOnEdit()==null ) {
			sb.append("<securityOnEdit/>");
		} else {
			sb.append("<securityOnEdit>");
			sb.append(getSecurityOnEdit().toXmlString());
			sb.append("</securityOnEdit>");
			sb.append("\n");
		}
		if ( getSecurityOnView()==null ) {
			sb.append("<securityOnView/>");
		} else {
			sb.append("<securityOnView>");
			sb.append(getSecurityOnView().toXmlString());
			sb.append("</securityOnView>");
			sb.append("\n");
		}
		if ( getSecurityOnDelete()==null ) {
			sb.append("<securityOnDelete/>");
		} else {
			sb.append("<securityOnDelete>");
			sb.append(getSecurityOnDelete().toXmlString());
			sb.append("</securityOnDelete>");
			sb.append("\n");
		}
		if ( getSecurityOnAdd()==null ) {
			sb.append("<securityOnAdd/>");
		} else {
			sb.append("<securityOnAdd>");
			sb.append(getSecurityOnAdd().toXmlString());
			sb.append("</securityOnAdd>");
			sb.append("\n");
		}
		for ( DomainOperation operation : getOperation() ) {
			sb.append("<operation>");
			sb.append(operation.toXmlString());
			sb.append("</operation>");
			sb.append("\n");
		}
		return sb.toString();
	}

}