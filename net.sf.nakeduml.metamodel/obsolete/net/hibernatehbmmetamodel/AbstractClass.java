package net.hibernatehbmmetamodel;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.sf.nakeduml.util.CompositionNode;

public class AbstractClass extends HbmElement implements CompositionNode {
	private Set<ManyToOne> manyToOne = new HashSet<ManyToOne>();
	private Set<Property> property = new HashSet<Property>();

	/** Default constructor for 
	 */
	public AbstractClass() {
	}

	public void addAllToManyToOne(Set<ManyToOne> manyToOne) {
		for ( ManyToOne o : manyToOne ) {
			addToManyToOne(o);
		}
	}
	
	public void addAllToProperty(Set<Property> property) {
		for ( Property o : property ) {
			addToProperty(o);
		}
	}
	
	public void addToManyToOne(ManyToOne manyToOne) {
		manyToOne.setAbstractClass(this);
	}
	
	/** Call this method when you want to attach this object to the containment tree. Useful with transitive persistence
	 */
	public void addToOwningObject() {
	}
	
	public void addToProperty(Property property) {
		property.setAbstractClass(this);
	}
	
	public void clearManyToOne() {
		removeAllFromManyToOne(getManyToOne());
	}
	
	public void clearProperty() {
		removeAllFromProperty(getProperty());
	}
	
	public void copyShallowState(AbstractClass from, AbstractClass to) {
		to.setQualifiedName(from.getQualifiedName());
	}
	
	public void copyState(AbstractClass from, AbstractClass to) {
		to.setQualifiedName(from.getQualifiedName());
		for ( ManyToOne child : from.getManyToOne() ) {
			to.addToManyToOne(child.makeCopy());
		}
		for ( Property child : from.getProperty() ) {
			to.addToProperty(child.makeCopy());
		}
	}
	
	public void createComponents() {
		super.createComponents();
	}
	
	public ManyToOne createManyToOne() {
		ManyToOne newInstance= new ManyToOne();
		newInstance.init(this);
		return newInstance;
	}
	
	public Property createProperty() {
		Property newInstance= new Property();
		newInstance.init(this);
		return newInstance;
	}
	
	public Set<ManyToOne> getManyToOne() {
		return manyToOne;
	}
	
	public List<HbmElement> getOwnedElement() {
		List<HbmElement> ownedElementSubsetting = new ArrayList<HbmElement>();
		ownedElementSubsetting.addAll(super.getOwnedElement());
		ownedElementSubsetting.addAll(getManyToOne());
		ownedElementSubsetting.addAll(getProperty());
		return ownedElementSubsetting;
	}
	
	public CompositionNode getOwningObject() {
		return null;
	}
	
	public Set<Property> getProperty() {
		return property;
	}
	
	public void init(CompositionNode owner) {
		super.init(owner);
		createComponents();
	}
	
	public AbstractClass makeCopy() {
		AbstractClass result = new AbstractClass();
		copyState((AbstractClass)this,result);
		return result;
	}
	
	public void markDeleted() {
		super.markDeleted();
		for ( ManyToOne child : new ArrayList<ManyToOne>(getManyToOne()) ) {
			child.markDeleted();
		}
		for ( Property child : new ArrayList<Property>(getProperty()) ) {
			child.markDeleted();
		}
	}
	
	public void removeAllFromManyToOne(Set<ManyToOne> manyToOne) {
		for ( ManyToOne o : manyToOne ) {
			removeFromManyToOne(o);
		}
	}
	
	public void removeAllFromProperty(Set<Property> property) {
		for ( Property o : property ) {
			removeFromProperty(o);
		}
	}
	
	public void removeFromManyToOne(ManyToOne manyToOne) {
		manyToOne.setAbstractClass(null);
	}
	
	public void removeFromOwningObject() {
		this.markDeleted();
	}
	
	public void removeFromProperty(Property property) {
		property.setAbstractClass(null);
	}
	
	public void setManyToOne(Set<ManyToOne> manyToOne) {
		for ( ManyToOne o : new HashSet<ManyToOne>(this.manyToOne) ) {
			o.setAbstractClass(null);
		}
		for ( ManyToOne o : manyToOne ) {
			o.setAbstractClass((AbstractClass)this);
		}
	}
	
	public void setProperty(Set<Property> property) {
		for ( Property o : new HashSet<Property>(this.property) ) {
			o.setAbstractClass(null);
		}
		for ( Property o : property ) {
			o.setAbstractClass((AbstractClass)this);
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
		for ( ManyToOne manyToOne : getManyToOne() ) {
			sb.append("<manyToOne>");
			sb.append(manyToOne.toXmlString());
			sb.append("</manyToOne>");
			sb.append("\n");
		}
		for ( Property property : getProperty() ) {
			sb.append("<property>");
			sb.append(property.toXmlString());
			sb.append("</property>");
			sb.append("\n");
		}
		return sb.toString();
	}

}