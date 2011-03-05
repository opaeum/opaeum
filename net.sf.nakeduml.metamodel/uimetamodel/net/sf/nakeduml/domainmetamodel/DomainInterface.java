package net.sf.nakeduml.domainmetamodel;

import org.nakeduml.runtime.domain.CompositionNode;

public class DomainInterface extends DomainClassifier implements CompositionNode {


	/** This constructor is intended for easy initialization in unit tests
	 * 
	 * @param owningObject 
	 */
	public DomainInterface(DomainPackage owningObject) {
		init(owningObject);
		addToOwningObject();
	}
	
	/** Default constructor for 
	 */
	public DomainInterface() {
	}

	/** Call this method when you want to attach this object to the containment tree. Useful with transitive persistence
	 */
	public void addToOwningObject() {
		getDomainPackage().getOwnedClassifier().add((DomainInterface)this);
	}
	
	public void copyState(DomainInterface from, DomainInterface to) {
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
	}
	
	public void createComponents() {
		super.createComponents();
	}
	
	public CompositionNode getOwningObject() {
		return getDomainPackage();
	}
	
	public void init(CompositionNode owner) {
		super.init(owner);
		internalSetOwner((DomainPackage)owner);
		createComponents();
	}
	
	public DomainInterface makeCopy() {
		DomainInterface result = new DomainInterface();
		copyState((DomainInterface)this,result);
		return result;
	}
	
	public void markDeleted() {
		super.markDeleted();
		if ( getDomainPackage()!=null ) {
			getDomainPackage().getOwnedClassifier().remove((DomainInterface)this);
		}
	}
	
	public void removeFromOwningObject() {
		this.markDeleted();
	}
	
	public void shallowCopyState(DomainInterface from, DomainInterface to) {
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
		return sb.toString();
	}

}