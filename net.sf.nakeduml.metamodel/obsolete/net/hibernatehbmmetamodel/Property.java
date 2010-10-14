package net.hibernatehbmmetamodel;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.sf.nakeduml.util.CompositionNode;

public class Property extends HbmElement implements CompositionNode {
	private String uniqueKey;
	private String name;
	private Boolean lazy = false;
	private Generated generated;
	private String type;
	private AbstractClass abstractClass;
	private Set<PropertyColumn> propertyColumn = new HashSet<PropertyColumn>();

	/** This constructor is intended for easy initialization in unit tests
	 * 
	 * @param owningObject 
	 */
	public Property(AbstractClass owningObject) {
		init(owningObject);
		addToOwningObject();
	}
	
	/** Default constructor for 
	 */
	public Property() {
	}

	public void addAllToPropertyColumn(Set<PropertyColumn> propertyColumn) {
		for ( PropertyColumn o : propertyColumn ) {
			addToPropertyColumn(o);
		}
	}
	
	/** Call this method when you want to attach this object to the containment tree. Useful with transitive persistence
	 */
	public void addToOwningObject() {
		getAbstractClass().getProperty().add((Property)this);
	}
	
	public void addToPropertyColumn(PropertyColumn propertyColumn) {
		propertyColumn.setProperty(this);
	}
	
	public void clearPropertyColumn() {
		removeAllFromPropertyColumn(getPropertyColumn());
	}
	
	public AbstractClass getAbstractClass() {
		return abstractClass;
	}
	
	public Generated getGenerated() {
		return generated;
	}
	
	public String getHbmAttributes() {
		String hbmAttributes = ("generated=\"".concat(this.getGenerated().getGeneratedName()).concat(("\" "))).concat("type=\"").concat(this.getType()).concat(("\" ")).concat("name=\"").concat(this.getName()).concat(("\" ")).concat("lazy=\"").concat((this.getLazy() ?
			"true" :
			"false")).concat(("\" ")).concat((this.getUniqueKey().equals("") ?
			"" :
			("unique-key=\"".concat(this.getUniqueKey()).concat(("\" ")))));
		return hbmAttributes;
	}
	
	public String getHbmName() {
		String hbmName = "property";
		return hbmName;
	}
	
	public Boolean getLazy() {
		return lazy;
	}
	
	public String getName() {
		return name;
	}
	
	public List<HbmElement> getOwnedElement() {
		List<HbmElement> ownedElementSubsetting = new ArrayList<HbmElement>();
		ownedElementSubsetting.addAll(super.getOwnedElement());
		ownedElementSubsetting.addAll(getPropertyColumn());
		return ownedElementSubsetting;
	}
	
	public HbmElement getOwner() {
		HbmElement ownerSubsetting = null;
		ownerSubsetting=super.getOwner();
		if ( getAbstractClass()!=null ) {
			ownerSubsetting=getAbstractClass();
		}
		return ownerSubsetting;
	}
	
	public CompositionNode getOwningObject() {
		return getAbstractClass();
	}
	
	public Set<PropertyColumn> getPropertyColumn() {
		return propertyColumn;
	}
	
	public String getType() {
		return type;
	}
	
	public String getUniqueKey() {
		return uniqueKey;
	}
	
	public void init(CompositionNode owner) {
		super.init(owner);
		internalSetOwner((AbstractClass)owner);
		this.setUniqueKey( "" );
		this.setLazy( true );
		this.setGenerated( Generated.NEVER );
		createComponents();
	}
	
	public void markDeleted() {
		super.markDeleted();
		if ( getAbstractClass()!=null ) {
			getAbstractClass().getProperty().remove((Property)this);
		}
		for ( PropertyColumn child : new ArrayList<PropertyColumn>(getPropertyColumn()) ) {
			child.markDeleted();
		}
	}
	
	public void removeAllFromPropertyColumn(Set<PropertyColumn> propertyColumn) {
		for ( PropertyColumn o : propertyColumn ) {
			removeFromPropertyColumn(o);
		}
	}
	
	public void removeFromOwningObject() {
		this.markDeleted();
	}
	
	public void removeFromPropertyColumn(PropertyColumn propertyColumn) {
		propertyColumn.setProperty(null);
	}
	
	public void setAbstractClass(AbstractClass abstractClass) {
		if ( this.abstractClass!=null ) {
			this.abstractClass.getProperty().remove((Property)this);
		}
		if ( abstractClass!=null ) {
			abstractClass.getProperty().add((Property)this);
			this.abstractClass=abstractClass;
		} else {
			this.abstractClass=null;
		}
	}
	
	public void setGenerated(Generated generated) {
		this.generated=generated;
	}
	
	public void setLazy(Boolean lazy) {
		this.lazy=lazy;
	}
	
	public void setName(String name) {
		this.name=name;
	}
	
	public void setPropertyColumn(Set<PropertyColumn> propertyColumn) {
		for ( PropertyColumn o : new HashSet<PropertyColumn>(this.propertyColumn) ) {
			o.setProperty(null);
		}
		for ( PropertyColumn o : propertyColumn ) {
			o.setProperty((Property)this);
		}
	}
	
	public void setType(String type) {
		this.type=type;
	}
	
	public void setUniqueKey(String uniqueKey) {
		this.uniqueKey=uniqueKey;
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
		sb.append("lazy=");
		sb.append(getLazy());
		sb.append(";");
		sb.append("name=");
		sb.append(getName());
		sb.append(";");
		sb.append("type=");
		sb.append(getType());
		sb.append(";");
		sb.append("hbmAttributes=");
		sb.append(getHbmAttributes());
		sb.append(";");
		sb.append("hbmName=");
		sb.append(getHbmName());
		sb.append(";");
		sb.append("uniqueKey=");
		sb.append(getUniqueKey());
		sb.append(";");
		sb.append("generated=");
		sb.append(getGenerated());
		sb.append(";");
		if ( getAbstractClass()==null ) {
			sb.append("abstractClass=null;");
		} else {
			sb.append("abstractClass="+getAbstractClass().getClass().getSimpleName()+"[");
			sb.append(getAbstractClass().hashCode());
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
		if ( getLazy()==null ) {
			sb.append("<lazy/>");
		} else {
			sb.append("<lazy>");
			sb.append(getLazy());
			sb.append("</lazy>");
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
		if ( getType()==null ) {
			sb.append("<type/>");
		} else {
			sb.append("<type>");
			sb.append(getType());
			sb.append("</type>");
			sb.append("\n");
		}
		for ( PropertyColumn propertyColumn : getPropertyColumn() ) {
			sb.append("<propertyColumn>");
			sb.append(propertyColumn.toXmlString());
			sb.append("</propertyColumn>");
			sb.append("\n");
		}
		if ( getUniqueKey()==null ) {
			sb.append("<uniqueKey/>");
		} else {
			sb.append("<uniqueKey>");
			sb.append(getUniqueKey());
			sb.append("</uniqueKey>");
			sb.append("\n");
		}
		if ( getGenerated()==null ) {
			sb.append("<generated/>");
		} else {
			sb.append("<generated>");
			sb.append(getGenerated());
			sb.append("</generated>");
			sb.append("\n");
		}
		if ( getAbstractClass()==null ) {
			sb.append("<abstractClass/>");
		} else {
			sb.append("<abstractClass>");
			sb.append(getAbstractClass().getClass().getSimpleName());
			sb.append("[");
			sb.append(getAbstractClass().hashCode());
			sb.append("]");
			sb.append("</abstractClass>");
			sb.append("\n");
		}
		return sb.toString();
	}
	
	/** Used to set the owner internally in extended composition semantics
	 * 
	 * @param newOwner 
	 */
	protected void internalSetOwner(AbstractClass newOwner) {
		this.abstractClass=newOwner;
	}
	
	public void copyShallowState(Property from, Property to) {
		to.setQualifiedName(from.getQualifiedName());
		to.setLazy(from.getLazy());
		to.setName(from.getName());
		to.setType(from.getType());
		to.setUniqueKey(from.getUniqueKey());
		to.setGenerated(from.getGenerated());
	}
	
	public void copyState(Property from, Property to) {
		to.setQualifiedName(from.getQualifiedName());
		to.setLazy(from.getLazy());
		to.setName(from.getName());
		to.setType(from.getType());
		for ( PropertyColumn child : from.getPropertyColumn() ) {
			to.addToPropertyColumn(child.makeCopy());
		}
		to.setUniqueKey(from.getUniqueKey());
		to.setGenerated(from.getGenerated());
	}
	
	public void createComponents() {
		super.createComponents();
	}
	
	public PropertyColumn createPropertyColumn() {
		PropertyColumn newInstance= new PropertyColumn();
		newInstance.init(this);
		return newInstance;
	}
	
	public Property makeCopy() {
		Property result = new Property();
		copyState((Property)this,result);
		return result;
	}

}