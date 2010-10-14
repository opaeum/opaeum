package net.hibernatehbmmetamodel;

import net.sf.nakeduml.util.CompositionNode;

public class PropertyColumn extends AbstractColumn implements CompositionNode {
	private Property property;

	/** Default constructor for 
	 */
	public PropertyColumn() {
	}
	
	/** This constructor is intended for easy initialization in unit tests
	 * 
	 * @param owningObject 
	 */
	public PropertyColumn(Property owningObject) {
		init(owningObject);
		addToOwningObject();
	}

	/** Call this method when you want to attach this object to the containment tree. Useful with transitive persistence
	 */
	public void addToOwningObject() {
		getProperty().getPropertyColumn().add((PropertyColumn)this);
	}
	
	public HbmElement getOwner() {
		HbmElement ownerSubsetting = null;
		ownerSubsetting=super.getOwner();
		if ( getProperty()!=null ) {
			ownerSubsetting=getProperty();
		}
		return ownerSubsetting;
	}
	
	public CompositionNode getOwningObject() {
		return getProperty();
	}
	
	public Property getProperty() {
		return property;
	}
	
	public void init(CompositionNode owner) {
		super.init(owner);
		internalSetOwner((Property)owner);
		createComponents();
	}
	
	public void markDeleted() {
		super.markDeleted();
		if ( getProperty()!=null ) {
			getProperty().getPropertyColumn().remove((PropertyColumn)this);
		}
	}
	
	public void removeFromOwningObject() {
		this.markDeleted();
	}
	
	public void setProperty(Property property) {
		if ( this.property!=null ) {
			this.property.getPropertyColumn().remove((PropertyColumn)this);
		}
		if ( property!=null ) {
			property.getPropertyColumn().add((PropertyColumn)this);
			this.property=property;
		} else {
			this.property=null;
		}
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("hbmName=");
		sb.append(getHbmName());
		sb.append(";");
		if ( getOwner()==null ) {
			sb.append("owner=null;");
		} else {
			sb.append("owner="+getOwner().getClass().getSimpleName()+"[");
			sb.append(getOwner().hashCode());
			sb.append("];");
		}
		sb.append("hbmAttributes=");
		sb.append(getHbmAttributes());
		sb.append(";");
		sb.append("qualifiedName=");
		sb.append(getQualifiedName());
		sb.append(";");
		sb.append("name=");
		sb.append(getName());
		sb.append(";");
		sb.append("hbmAttributes=");
		sb.append(getHbmAttributes());
		sb.append(";");
		sb.append("hbmName=");
		sb.append(getHbmName());
		sb.append(";");
		if ( getProperty()==null ) {
			sb.append("property=null;");
		} else {
			sb.append("property="+getProperty().getClass().getSimpleName()+"[");
			sb.append(getProperty().getName());
			sb.append("];");
		}
		return sb.toString();
	}
	
	public String toXmlString() {
		StringBuilder sb = new StringBuilder();
		if ( getQualifiedName()==null ) {
			sb.append("<qualifiedName/>");
		} else {
			sb.append("<qualifiedName>");
			sb.append(getQualifiedName());
			sb.append("</qualifiedName>");
			sb.append("\n");
		}
		if ( getName()==null ) {
			sb.append("<name/>");
		} else {
			sb.append("<name>");
			sb.append(getName());
			sb.append("</name>");
			sb.append("\n");
		}
		if ( getProperty()==null ) {
			sb.append("<property/>");
		} else {
			sb.append("<property>");
			sb.append(getProperty().getClass().getSimpleName());
			sb.append("[");
			sb.append(getProperty().getName());
			sb.append("]");
			sb.append("</property>");
			sb.append("\n");
		}
		return sb.toString();
	}
	
	/** Used to set the owner internally in extended composition semantics
	 * 
	 * @param newOwner 
	 */
	protected void internalSetOwner(Property newOwner) {
		this.property=newOwner;
	}
	
	public void copyShallowState(PropertyColumn from, PropertyColumn to) {
		to.setQualifiedName(from.getQualifiedName());
		to.setName(from.getName());
	}
	
	public void copyState(PropertyColumn from, PropertyColumn to) {
		to.setQualifiedName(from.getQualifiedName());
		to.setName(from.getName());
	}
	
	public void createComponents() {
		super.createComponents();
	}
	
	public PropertyColumn makeCopy() {
		PropertyColumn result = new PropertyColumn();
		copyState((PropertyColumn)this,result);
		return result;
	}

}