package net.hibernatehbmmetamodel;

import net.sf.nakeduml.util.CompositionNode;

public class CollectionKey extends HbmElement implements CompositionNode {
	private Collection collection;
	private String column;

	/** Default constructor for 
	 */
	public CollectionKey() {
	}
	
	/** This constructor is intended for easy initialization in unit tests
	 * 
	 * @param owningObject 
	 */
	public CollectionKey(Collection owningObject) {
		init(owningObject);
		addToOwningObject();
	}

	/** Call this method when you want to attach this object to the containment tree. Useful with transitive persistence
	 */
	public void addToOwningObject() {
		getCollection().setCollectionKey((CollectionKey)this);
	}
	
	public Collection getCollection() {
		return collection;
	}
	
	public String getColumn() {
		return column;
	}
	
	public String getHbmAttributes() {
		String hbmAttributes = ("column=\"".concat(this.getColumn()).concat(("\" ")));
		return hbmAttributes;
	}
	
	public String getHbmName() {
		String hbmName = "key";
		return hbmName;
	}
	
	public HbmElement getOwner() {
		HbmElement ownerSubsetting = null;
		ownerSubsetting=super.getOwner();
		if ( getCollection()!=null ) {
			ownerSubsetting=getCollection();
		}
		return ownerSubsetting;
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
			getCollection().setCollectionKey(null);
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
				collection.z_internalAddToCollectionKey((CollectionKey)this);
			}
		} else {
			if ( !oldValue.equals(collection) ) {
				this.collection=collection;
				oldValue.z_internalRemoveFromCollectionKey(this);
				if ( collection!=null ) {
					collection.z_internalAddToCollectionKey((CollectionKey)this);
				}
			}
		}
	}
	
	public void setColumn(String column) {
		this.column=column;
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
		sb.append("column=");
		sb.append(getColumn());
		sb.append(";");
		sb.append("hbmName=");
		sb.append(getHbmName());
		sb.append(";");
		sb.append("hbmAttributes=");
		sb.append(getHbmAttributes());
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
		if ( getColumn()==null ) {
			sb.append("<column/>");
		} else {
			sb.append("<column>");
			sb.append(getColumn());
			sb.append("</column>");
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
	
	public void copyShallowState(CollectionKey from, CollectionKey to) {
		to.setQualifiedName(from.getQualifiedName());
		to.setColumn(from.getColumn());
	}
	
	public void copyState(CollectionKey from, CollectionKey to) {
		to.setQualifiedName(from.getQualifiedName());
		to.setColumn(from.getColumn());
	}
	
	public void createComponents() {
		super.createComponents();
	}
	
	public CollectionKey makeCopy() {
		CollectionKey result = new CollectionKey();
		copyState((CollectionKey)this,result);
		return result;
	}

}