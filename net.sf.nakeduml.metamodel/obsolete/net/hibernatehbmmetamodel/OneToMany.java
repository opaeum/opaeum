package net.hibernatehbmmetamodel;

import net.sf.nakeduml.util.CompositionNode;

public class OneToMany extends HbmElement implements CompositionNode {
	private String className;
	private Collection collection;

	/** This constructor is intended for easy initialization in unit tests
	 * 
	 * @param owningObject 
	 */
	public OneToMany(Collection owningObject) {
		init(owningObject);
		addToOwningObject();
	}
	
	/** Default constructor for 
	 */
	public OneToMany() {
	}

	/** Call this method when you want to attach this object to the containment tree. Useful with transitive persistence
	 */
	public void addToOwningObject() {
		getCollection().setOneToMany((OneToMany)this);
	}
	
	public String getClassName() {
		return className;
	}
	
	public Collection getCollection() {
		return collection;
	}
	
	public String getHbmAttributes() {
		String hbmAttributes = ("class=\"".concat(this.getClassName()).concat(("\" ")));
		return hbmAttributes;
	}
	
	public String getHbmName() {
		String hbmName = "one-to-many";
		return hbmName;
	}
	
	public String getHbmString2() {
		String result = "";
		result= ("<".concat(this.getHbmName()).concat((" "))).concat(this.getHbmAttributes()).concat((" />\n"));
		return result;
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
			getCollection().setOneToMany(null);
		}
	}
	
	public void removeFromOwningObject() {
		this.markDeleted();
	}
	
	public void setClassName(String className) {
		this.className=className;
	}
	
	public void setCollection(Collection collection) {
		Collection oldValue = this.collection;
		if ( oldValue==null ) {
			this.collection=collection;
			if ( collection!=null ) {
				collection.z_internalAddToOneToMany((OneToMany)this);
			}
		} else {
			if ( !oldValue.equals(collection) ) {
				this.collection=collection;
				oldValue.z_internalRemoveFromOneToMany(this);
				if ( collection!=null ) {
					collection.z_internalAddToOneToMany((OneToMany)this);
				}
			}
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
		sb.append("className=");
		sb.append(getClassName());
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
		if ( getClassName()==null ) {
			sb.append("<className/>");
		} else {
			sb.append("<className>");
			sb.append(getClassName());
			sb.append("</className>");
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
	
	public void copyShallowState(OneToMany from, OneToMany to) {
		to.setQualifiedName(from.getQualifiedName());
		to.setClassName(from.getClassName());
	}
	
	public void copyState(OneToMany from, OneToMany to) {
		to.setQualifiedName(from.getQualifiedName());
		to.setClassName(from.getClassName());
	}
	
	public void createComponents() {
		super.createComponents();
	}
	
	public OneToMany makeCopy() {
		OneToMany result = new OneToMany();
		copyState((OneToMany)this,result);
		return result;
	}

}