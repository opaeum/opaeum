package net.hibernatehbmmetamodel;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.sf.nakeduml.util.CompositionNode;

public class FirstLevelClass extends AbstractClass implements CompositionNode {
	private Set<Collection> collection = new HashSet<Collection>();

	/** Default constructor for 
	 */
	public FirstLevelClass() {
	}
	
	/** This constructor is intended for easy initialization in unit tests
	 * 
	 * @param owningObject 
	 */
	public FirstLevelClass(HibernateConfiguration owningObject) {
		init(owningObject);
		addToOwningObject();
	}

	public void addAllToCollection(Set<Collection> collection) {
		for ( Collection o : collection ) {
			addToCollection(o);
		}
	}
	
	public void addToCollection(Collection collection) {
		collection.setAbstractClass(this);
	}
	
	/** Call this method when you want to attach this object to the containment tree. Useful with transitive persistence
	 */
	public void addToOwningObject() {
		getHibernateConfiguration().getAbstractClass().add((FirstLevelClass)this);
	}
	
	public void clearCollection() {
		removeAllFromCollection(getCollection());
	}
	
	public Set<Collection> getCollection() {
		return collection;
	}
	
	public HibernateConfiguration getHibernateConfiguration() {
		return null;
	}
	
	public List<HbmElement> getOwnedElement() {
		List<HbmElement> ownedElementSubsetting = new ArrayList<HbmElement>();
		ownedElementSubsetting.addAll(super.getOwnedElement());
		ownedElementSubsetting.addAll(getCollection());
		return ownedElementSubsetting;
	}
	
	public CompositionNode getOwningObject() {
		return getHibernateConfiguration();
	}
	
	public void init(CompositionNode owner) {
		super.init(owner);
		internalSetOwner((HibernateConfiguration)owner);
		createComponents();
	}
	
	public void markDeleted() {
		super.markDeleted();
		if ( getHibernateConfiguration()!=null ) {
			getHibernateConfiguration().getAbstractClass().remove((FirstLevelClass)this);
		}
		for ( Collection child : new ArrayList<Collection>(getCollection()) ) {
			child.markDeleted();
		}
	}
	
	public void removeAllFromCollection(Set<Collection> collection) {
		for ( Collection o : collection ) {
			removeFromCollection(o);
		}
	}
	
	public void removeFromCollection(Collection collection) {
		collection.setAbstractClass(null);
	}
	
	public void removeFromOwningObject() {
		this.markDeleted();
	}
	
	public void setCollection(Set<Collection> collection) {
		for ( Collection o : new HashSet<Collection>(this.collection) ) {
			o.setAbstractClass(null);
		}
		for ( Collection o : collection ) {
			o.setAbstractClass((FirstLevelClass)this);
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
		if ( getHibernateConfiguration()==null ) {
			sb.append("hibernateConfiguration=null;");
		} else {
			sb.append("hibernateConfiguration="+getHibernateConfiguration().getClass().getSimpleName()+"[");
			sb.append(getHibernateConfiguration().hashCode());
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
		for ( Collection collection : getCollection() ) {
			sb.append("<collection>");
			sb.append(collection.toXmlString());
			sb.append("</collection>");
			sb.append("\n");
		}
		return sb.toString();
	}
	
	/** Used to set the owner internally in extended composition semantics
	 * 
	 * @param newOwner 
	 */
	protected void internalSetOwner(HibernateConfiguration newOwner) {
	}
	
	public void copyShallowState(FirstLevelClass from, FirstLevelClass to) {
		to.setQualifiedName(from.getQualifiedName());
	}
	
	public void copyState(FirstLevelClass from, FirstLevelClass to) {
		to.setQualifiedName(from.getQualifiedName());
		for ( ManyToOne child : from.getManyToOne() ) {
			to.addToManyToOne(child.makeCopy());
		}
		for ( Property child : from.getProperty() ) {
			to.addToProperty(child.makeCopy());
		}
		for ( Collection child : from.getCollection() ) {
			to.addToCollection(child.makeCopy());
		}
	}
	
	public Collection createCollection() {
		Collection newInstance= new Collection();
		newInstance.init(this);
		return newInstance;
	}
	
	public void createComponents() {
		super.createComponents();
	}
	
	public FirstLevelClass makeCopy() {
		FirstLevelClass result = new FirstLevelClass();
		copyState((FirstLevelClass)this,result);
		return result;
	}

}