package net.hibernatehbmmetamodel;

import net.sf.nakeduml.util.CompositionNode;

public class Filter extends HbmElement implements CompositionNode {
	private Collection collection;
	private String condition;
	private String name;

	/** This constructor is intended for easy initialization in unit tests
	 * 
	 * @param owningObject 
	 */
	public Filter(Collection owningObject) {
		init(owningObject);
		addToOwningObject();
	}
	
	/** Default constructor for 
	 */
	public Filter() {
	}

	/** Call this method when you want to attach this object to the containment tree. Useful with transitive persistence
	 */
	public void addToOwningObject() {
		getCollection().setFilter((Filter)this);
	}
	
	public Collection getCollection() {
		return collection;
	}
	
	public String getCondition() {
		return condition;
	}
	
	public String getHbmAttributes() {
		String hbmAttributes = ("name=\"".concat(this.getName()).concat(("\" "))).concat("condition=\"").concat(this.getCondition()).concat(("\" "));
		return hbmAttributes;
	}
	
	public String getHbmName() {
		String hbmName = "filter";
		return hbmName;
	}
	
	public String getName() {
		return name;
	}
	
	public CompositionNode getOwningObject() {
		return getCollection();
	}
	
	public void init(CompositionNode owner) {
		super.init(owner);
		internalSetOwner((Collection)owner);
		createComponents();
	}
	
	public void markDeleted() {
		super.markDeleted();
		if ( getCollection()!=null ) {
			getCollection().setFilter(null);
		}
	}
	
	public void removeFromOwningObject() {
		this.markDeleted();
	}
	
	public void setCollection(Collection collection) {
		Collection oldValue = this.collection;
		if ( oldValue==null ) {
			this.collection=collection;
			if ( collection!=null ) {
				collection.z_internalAddToFilter((Filter)this);
			}
		} else {
			if ( !oldValue.equals(collection) ) {
				this.collection=collection;
				oldValue.z_internalRemoveFromFilter(this);
				if ( collection!=null ) {
					collection.z_internalAddToFilter((Filter)this);
				}
			}
		}
	}
	
	public void setCondition(String condition) {
		this.condition=condition;
	}
	
	public void setName(String name) {
		this.name=name;
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
		sb.append("hbmName=");
		sb.append(getHbmName());
		sb.append(";");
		sb.append("hbmAttributes=");
		sb.append(getHbmAttributes());
		sb.append(";");
		sb.append("name=");
		sb.append(getName());
		sb.append(";");
		sb.append("condition=");
		sb.append(getCondition());
		sb.append(";");
		if ( getCollection()==null ) {
			sb.append("collection=null;");
		} else {
			sb.append("collection="+getCollection().getClass().getSimpleName()+"[");
			sb.append(getCollection().getName());
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
		if ( getCondition()==null ) {
			sb.append("<condition/>");
		} else {
			sb.append("<condition>");
			sb.append(getCondition());
			sb.append("</condition>");
			sb.append("\n");
		}
		if ( getCollection()==null ) {
			sb.append("<collection/>");
		} else {
			sb.append("<collection>");
			sb.append(getCollection().getClass().getSimpleName());
			sb.append("[");
			sb.append(getCollection().getName());
			sb.append("]");
			sb.append("</collection>");
			sb.append("\n");
		}
		return sb.toString();
	}
	
	public void z_internalAddToCollection(Collection collection) {
		if ( getCollection()==null || !getCollection().equals(collection) ) {
			this.collection=collection;
		}
	}
	
	public void z_internalRemoveFromCollection(Collection collection) {
		if ( getCollection()!=null && getCollection().equals(collection) ) {
			this.collection=null;
		}
	}
	
	/** Used to set the owner internally in extended composition semantics
	 * 
	 * @param newOwner 
	 */
	protected void internalSetOwner(Collection newOwner) {
		this.collection=newOwner;
	}
	
	public void copyShallowState(Filter from, Filter to) {
		to.setQualifiedName(from.getQualifiedName());
		to.setName(from.getName());
		to.setCondition(from.getCondition());
	}
	
	public void copyState(Filter from, Filter to) {
		to.setQualifiedName(from.getQualifiedName());
		to.setName(from.getName());
		to.setCondition(from.getCondition());
	}
	
	public void createComponents() {
		super.createComponents();
	}
	
	public Filter makeCopy() {
		Filter result = new Filter();
		copyState((Filter)this,result);
		return result;
	}

}