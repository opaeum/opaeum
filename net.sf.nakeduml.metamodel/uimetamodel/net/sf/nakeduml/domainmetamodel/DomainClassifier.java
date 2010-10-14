package net.sf.nakeduml.domainmetamodel;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import net.sf.nakeduml.util.CompositionNode;

public class DomainClassifier extends DomainElement implements CompositionNode {
	private Set<DomainProperty> property = new HashSet<DomainProperty>();
	private SecurityOnUserAction securityOnView;
	private SecurityOnUserAction securityOnDelete;
	private SecurityOnUserAction securityOnEdit;
	private SecurityOnUserAction securityOnAdd;
	private String qualifiedImplementationType;
	private ClassifierKind classifierKind;
	private DomainPackage domainPackage;

	/** Default constructor for 
	 */
	public DomainClassifier() {
	}
	
	/** This constructor is intended for easy initialization in unit tests
	 * 
	 * @param owningObject 
	 */
	public DomainClassifier(DomainPackage owningObject) {
		init(owningObject);
		addToOwningObject();
	}

	public void addAllToProperty(Set<DomainProperty> property) {
		for ( DomainProperty o : property ) {
			addToProperty(o);
		}
	}
	
	/** Call this method when you want to attach this object to the containment tree. Useful with transitive persistence
	 */
	public void addToOwningObject() {
		getDomainPackage().getOwnedClassifier().add((DomainClassifier)this);
	}
	
	public void addToProperty(DomainProperty property) {
		property.setClassifier(this);
	}
	
	public void clearProperty() {
		removeAllFromProperty(getProperty());
	}
	
	public ClassifierKind getClassifierKind() {
		return classifierKind;
	}
	
	public DomainPackage getDomainPackage() {
		return domainPackage;
	}
	
	public Set<DomainElement> getOwnedElement() {
		Set<DomainElement> ownedElementSubsetting = new HashSet<DomainElement>();
		ownedElementSubsetting.addAll(super.getOwnedElement());
		ownedElementSubsetting.addAll(getProperty());
		return ownedElementSubsetting;
	}
	
	public DomainElement getOwner() {
		DomainElement ownerSubsetting = null;
		ownerSubsetting=super.getOwner();
		if ( getDomainPackage()!=null ) {
			ownerSubsetting=getDomainPackage();
		}
		return ownerSubsetting;
	}
	
	public CompositionNode getOwningObject() {
		return getDomainPackage();
	}
	
	public Set<DomainProperty> getProperty() {
		return property;
	}
	
	public String getQualifiedImplementationType() {
		return qualifiedImplementationType;
	}
	
	public SecurityOnUserAction getSecurityOnAdd() {
		return securityOnAdd;
	}
	
	public SecurityOnUserAction getSecurityOnDelete() {
		return securityOnDelete;
	}
	
	public SecurityOnUserAction getSecurityOnEdit() {
		return securityOnEdit;
	}
	
	public SecurityOnUserAction getSecurityOnView() {
		return securityOnView;
	}
	
	public void init(CompositionNode owner) {
		super.init(owner);
		internalSetOwner((DomainPackage)owner);
		createComponents();
	}
	
	public void markDeleted() {
		super.markDeleted();
		if ( getDomainPackage()!=null ) {
			getDomainPackage().getOwnedClassifier().remove((DomainClassifier)this);
		}
		for ( DomainProperty child : new ArrayList<DomainProperty>(getProperty()) ) {
			child.markDeleted();
		}
	}
	
	public void removeAllFromProperty(Set<DomainProperty> property) {
		for ( DomainProperty o : property ) {
			removeFromProperty(o);
		}
	}
	
	public void removeFromOwningObject() {
		this.markDeleted();
	}
	
	public void removeFromProperty(DomainProperty property) {
		property.setClassifier(null);
	}
	
	public void setClassifierKind(ClassifierKind classifierKind) {
		this.classifierKind=classifierKind;
	}
	
	public void setDomainPackage(DomainPackage domainPackage) {
		if ( this.domainPackage!=null ) {
			this.domainPackage.getOwnedClassifier().remove((DomainClassifier)this);
		}
		if ( domainPackage!=null ) {
			domainPackage.getOwnedClassifier().add((DomainClassifier)this);
			this.domainPackage=domainPackage;
		} else {
			this.domainPackage=null;
		}
	}
	
	public void setProperty(Set<DomainProperty> property) {
		for ( DomainProperty o : new HashSet<DomainProperty>(this.property) ) {
			o.setClassifier(null);
		}
		for ( DomainProperty o : property ) {
			o.setClassifier((DomainClassifier)this);
		}
	}
	
	public void setQualifiedImplementationType(String qualifiedImplementationType) {
		this.qualifiedImplementationType=qualifiedImplementationType;
	}
	
	public void setSecurityOnAdd(SecurityOnUserAction securityOnAdd) {
		this.securityOnAdd=securityOnAdd;
	}
	
	public void setSecurityOnDelete(SecurityOnUserAction securityOnDelete) {
		this.securityOnDelete=securityOnDelete;
	}
	
	public void setSecurityOnEdit(SecurityOnUserAction securityOnEdit) {
		this.securityOnEdit=securityOnEdit;
	}
	
	public void setSecurityOnView(SecurityOnUserAction securityOnView) {
		this.securityOnView=securityOnView;
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
	
	/** Used to set the owner internally in extended composition semantics
	 * 
	 * @param newOwner 
	 */
	protected void internalSetOwner(DomainPackage newOwner) {
		this.domainPackage=newOwner;
	}
	
	public void copyState(DomainClassifier from, DomainClassifier to) {
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
		if ( getSecurityOnEdit()==null ) {
			setSecurityOnEdit(new SecurityOnUserAction());
		}
		if ( getSecurityOnView()==null ) {
			setSecurityOnView(new SecurityOnUserAction());
		}
		if ( getSecurityOnDelete()==null ) {
			setSecurityOnDelete(new SecurityOnUserAction());
		}
		if ( getSecurityOnAdd()==null ) {
			setSecurityOnAdd(new SecurityOnUserAction());
		}
	}
	
	public DomainProperty createProperty() {
		DomainProperty newInstance= new DomainProperty();
		newInstance.init(this);
		return newInstance;
	}
	
	public SecurityOnUserAction createSecurityOnAdd() {
		SecurityOnUserAction newInstance= new SecurityOnUserAction();
		return newInstance;
	}
	
	public SecurityOnUserAction createSecurityOnDelete() {
		SecurityOnUserAction newInstance= new SecurityOnUserAction();
		return newInstance;
	}
	
	public SecurityOnUserAction createSecurityOnEdit() {
		SecurityOnUserAction newInstance= new SecurityOnUserAction();
		return newInstance;
	}
	
	public SecurityOnUserAction createSecurityOnView() {
		SecurityOnUserAction newInstance= new SecurityOnUserAction();
		return newInstance;
	}
	
	public DomainClassifier makeCopy() {
		DomainClassifier result = new DomainClassifier();
		copyState((DomainClassifier)this,result);
		return result;
	}
	
	public void shallowCopyState(DomainClassifier from, DomainClassifier to) {
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

}