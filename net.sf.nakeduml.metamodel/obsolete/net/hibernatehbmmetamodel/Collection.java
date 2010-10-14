package net.hibernatehbmmetamodel;

import java.util.ArrayList;
import java.util.List;

import net.sf.nakeduml.util.CompositionNode;
import util.Stdlib;

public class Collection extends HbmElement implements CompositionNode {
	private FirstLevelClass abstractClass;
	private CollectionKey collectionKey;
	private String sort;
	private String hbmName;
	private String table;
	private Filter filter;
	private Access access;
	private Boolean lazy = false;
	private String name;
	private OneToMany oneToMany;

	/** Default constructor for 
	 */
	public Collection() {
	}
	
	/** This constructor is intended for easy initialization in unit tests
	 * 
	 * @param owningObject 
	 */
	public Collection(FirstLevelClass owningObject) {
		init(owningObject);
		addToOwningObject();
	}

	/** Call this method when you want to attach this object to the containment tree. Useful with transitive persistence
	 */
	public void addToOwningObject() {
		getAbstractClass().getCollection().add((Collection)this);
	}
	
	public void copyShallowState(Collection from, Collection to) {
		to.setQualifiedName(from.getQualifiedName());
		to.setCollectionKey(getCollectionKey());
		to.setLazy(from.getLazy());
		to.setName(from.getName());
		to.setSort(from.getSort());
		to.setTable(from.getTable());
		to.setHbmName(from.getHbmName());
		to.setOneToMany(getOneToMany());
		to.setFilter(getFilter());
		to.setAccess(from.getAccess());
	}
	
	public void copyState(Collection from, Collection to) {
		to.setQualifiedName(from.getQualifiedName());
		if ( from.getCollectionKey()!=null ) {
			to.setCollectionKey(from.getCollectionKey().makeCopy());
		}
		to.setLazy(from.getLazy());
		to.setName(from.getName());
		to.setSort(from.getSort());
		to.setTable(from.getTable());
		to.setHbmName(from.getHbmName());
		if ( from.getOneToMany()!=null ) {
			to.setOneToMany(from.getOneToMany().makeCopy());
		}
		if ( from.getFilter()!=null ) {
			to.setFilter(from.getFilter().makeCopy());
		}
		to.setAccess(from.getAccess());
	}
	
	public CollectionKey createCollectionKey() {
		CollectionKey newInstance= new CollectionKey();
		newInstance.init(this);
		return newInstance;
	}
	
	public Filter createFilter() {
		Filter newInstance= new Filter();
		newInstance.init(this);
		return newInstance;
	}
	
	public OneToMany createOneToMany() {
		OneToMany newInstance= new OneToMany();
		newInstance.init(this);
		return newInstance;
	}
	
	public FirstLevelClass getAbstractClass() {
		return abstractClass;
	}
	
	public Access getAccess() {
		return access;
	}
	
	public String getCascade() {
		String cascade = "all";
		return cascade;
	}
	
	public CollectionKey getCollectionKey() {
		return collectionKey;
	}
	
	public Filter getFilter() {
		return filter;
	}
	
	public String getHbmAttributes() {
		String hbmAttributes = ("name=\"".concat(this.getName()).concat(("\" "))).concat(("lazy=\"".concat((this.getLazy() ?
			"true" :
			"false")))).concat(("\" ")).concat(("table=\"`".concat(this.getTable()).concat(("`\" ")))).concat("sort=\"".concat(this.getSort())).concat(("\" ")).concat("access=\"").concat(this.getAccess().getAccessName()).concat(("\" ")).concat("cascade=\"".concat(this.getCascade())).concat(("\" "));
		return hbmAttributes;
	}
	
	public String getHbmName() {
		return hbmName;
	}
	
	public String getHbmString2() {
		String result = "";
		result= ("<".concat(this.getHbmName()).concat((" "))).concat(this.getHbmAttributes()).concat(">\n").concat(this.getCollectionKey().getHbmString2()).concat(iterate1()).concat(((this.getFilter() == null) ?
			"" :
			this.getFilter().getHbmString2())).concat("</").concat(this.getHbmName()).concat(">");
		return result;
	}
	
	public Boolean getLazy() {
		return lazy;
	}
	
	public String getName() {
		return name;
	}
	
	public OneToMany getOneToMany() {
		return oneToMany;
	}
	
	public List<HbmElement> getOwnedElement() {
		List<HbmElement> ownedElementSubsetting = new ArrayList<HbmElement>();
		ownedElementSubsetting.addAll(super.getOwnedElement());
		if ( getCollectionKey()!=null ) {
			ownedElementSubsetting.add(getCollectionKey());
		}
		if ( getOneToMany()!=null ) {
			ownedElementSubsetting.add(getOneToMany());
		}
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
	
	public String getSort() {
		return sort;
	}
	
	public String getTable() {
		return table;
	}
	
	public void init(CompositionNode owner) {
		super.init(owner);
		internalSetOwner((FirstLevelClass)owner);
		this.setSort( "unsorted" );
		this.setTable( "" );
		this.setLazy( true );
		this.setName( "" );
		createComponents();
		getOneToMany().init(this);
	}
	
	public Collection makeCopy() {
		Collection result = new Collection();
		copyState((Collection)this,result);
		return result;
	}
	
	public void markDeleted() {
		super.markDeleted();
		if ( getCollectionKey()!=null ) {
			getCollectionKey().setCollection(null);
		}
		if ( getOneToMany()!=null ) {
			getOneToMany().setCollection(null);
		}
		if ( getFilter()!=null ) {
			getFilter().setCollection(null);
		}
		if ( getAbstractClass()!=null ) {
			getAbstractClass().getCollection().remove((Collection)this);
		}
		getCollectionKey().markDeleted();
		getOneToMany().markDeleted();
		getFilter().markDeleted();
	}
	
	public void removeFromOwningObject() {
		this.markDeleted();
	}
	
	public void setAbstractClass(FirstLevelClass abstractClass) {
		if ( this.abstractClass!=null ) {
			this.abstractClass.getCollection().remove((Collection)this);
		}
		if ( abstractClass!=null ) {
			abstractClass.getCollection().add((Collection)this);
			this.abstractClass=abstractClass;
		} else {
			this.abstractClass=null;
		}
	}
	
	public void setAccess(Access access) {
		this.access=access;
	}
	
	public void setCollectionKey(CollectionKey collectionKey) {
		CollectionKey oldValue = this.collectionKey;
		if ( oldValue==null ) {
			this.collectionKey=collectionKey;
			if ( collectionKey!=null ) {
				collectionKey.z_internalAddToCollection((Collection)this);
			}
		} else {
			if ( !oldValue.equals(collectionKey) ) {
				this.collectionKey=collectionKey;
				oldValue.z_internalRemoveFromCollection(this);
				if ( collectionKey!=null ) {
					collectionKey.z_internalAddToCollection((Collection)this);
				}
			}
		}
	}
	
	public void setFilter(Filter filter) {
		Filter oldValue = this.filter;
		if ( oldValue==null ) {
			this.filter=filter;
			if ( filter!=null ) {
				filter.z_internalAddToCollection((Collection)this);
			}
		} else {
			if ( !oldValue.equals(filter) ) {
				this.filter=filter;
				oldValue.z_internalRemoveFromCollection(this);
				if ( filter!=null ) {
					filter.z_internalAddToCollection((Collection)this);
				}
			}
		}
	}
	
	public void setHbmName(String hbmName) {
		this.hbmName=hbmName;
	}
	
	public void setLazy(Boolean lazy) {
		this.lazy=lazy;
	}
	
	public void setName(String name) {
		this.name=name;
	}
	
	public void setOneToMany(OneToMany oneToMany) {
		OneToMany oldValue = this.oneToMany;
		if ( oldValue==null ) {
			this.oneToMany=oneToMany;
			if ( oneToMany!=null ) {
				oneToMany.z_internalAddToCollection((Collection)this);
			}
		} else {
			if ( !oldValue.equals(oneToMany) ) {
				this.oneToMany=oneToMany;
				oldValue.z_internalRemoveFromCollection(this);
				if ( oneToMany!=null ) {
					oneToMany.z_internalAddToCollection((Collection)this);
				}
			}
		}
	}
	
	public void setSort(String sort) {
		this.sort=sort;
	}
	
	public void setTable(String table) {
		this.table=table;
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
		if ( getCollectionKey()==null ) {
			sb.append("collectionKey=null;");
		} else {
			sb.append("collectionKey="+getCollectionKey().getClass().getSimpleName()+"[");
			sb.append(getCollectionKey().hashCode());
			sb.append("];");
		}
		sb.append("lazy=");
		sb.append(getLazy());
		sb.append(";");
		sb.append("name=");
		sb.append(getName());
		sb.append(";");
		sb.append("sort=");
		sb.append(getSort());
		sb.append(";");
		sb.append("table=");
		sb.append(getTable());
		sb.append(";");
		sb.append("hbmAttributes=");
		sb.append(getHbmAttributes());
		sb.append(";");
		sb.append("hbmName=");
		sb.append(getHbmName());
		sb.append(";");
		if ( getOneToMany()==null ) {
			sb.append("oneToMany=null;");
		} else {
			sb.append("oneToMany="+getOneToMany().getClass().getSimpleName()+"[");
			sb.append(getOneToMany().hashCode());
			sb.append("];");
		}
		sb.append("cascade=");
		sb.append(getCascade());
		sb.append(";");
		if ( getFilter()==null ) {
			sb.append("filter=null;");
		} else {
			sb.append("filter="+getFilter().getClass().getSimpleName()+"[");
			sb.append(getFilter().getName());
			sb.append("];");
		}
		if ( getAbstractClass()==null ) {
			sb.append("abstractClass=null;");
		} else {
			sb.append("abstractClass="+getAbstractClass().getClass().getSimpleName()+"[");
			sb.append(getAbstractClass().hashCode());
			sb.append("];");
		}
		sb.append("access=");
		sb.append(getAccess());
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
		if ( getCollectionKey()==null ) {
			sb.append("<collectionKey/>");
		} else {
			sb.append("<collectionKey>");
			sb.append(getCollectionKey().toXmlString());
			sb.append("</collectionKey>");
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
		if ( getSort()==null ) {
			sb.append("<sort/>");
		} else {
			sb.append("<sort>");
			sb.append(getSort());
			sb.append("</sort>");
			sb.append("\n");
		}
		if ( getTable()==null ) {
			sb.append("<table/>");
		} else {
			sb.append("<table>");
			sb.append(getTable());
			sb.append("</table>");
			sb.append("\n");
		}
		if ( getHbmName()==null ) {
			sb.append("<hbmName/>");
		} else {
			sb.append("<hbmName>");
			sb.append(getHbmName());
			sb.append("</hbmName>");
			sb.append("\n");
		}
		if ( getOneToMany()==null ) {
			sb.append("<oneToMany/>");
		} else {
			sb.append("<oneToMany>");
			sb.append(getOneToMany().toXmlString());
			sb.append("</oneToMany>");
			sb.append("\n");
		}
		if ( getFilter()==null ) {
			sb.append("<filter/>");
		} else {
			sb.append("<filter>");
			sb.append(getFilter().toXmlString());
			sb.append("</filter>");
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
		if ( getAccess()==null ) {
			sb.append("<access/>");
		} else {
			sb.append("<access>");
			sb.append(getAccess());
			sb.append("</access>");
			sb.append("\n");
		}
		return sb.toString();
	}
	
	public void z_internalAddToCollectionKey(CollectionKey collectionKey) {
		if ( getCollectionKey()==null || !getCollectionKey().equals(collectionKey) ) {
			this.collectionKey=collectionKey;
		}
	}
	
	public void z_internalAddToFilter(Filter filter) {
		if ( getFilter()==null || !getFilter().equals(filter) ) {
			this.filter=filter;
		}
	}
	
	public void z_internalAddToOneToMany(OneToMany oneToMany) {
		if ( getOneToMany()==null || !getOneToMany().equals(oneToMany) ) {
			this.oneToMany=oneToMany;
		}
	}
	
	public void z_internalRemoveFromCollectionKey(CollectionKey collectionKey) {
		if ( getCollectionKey()!=null && getCollectionKey().equals(collectionKey) ) {
			this.collectionKey=null;
		}
	}
	
	public void z_internalRemoveFromFilter(Filter filter) {
		if ( getFilter()!=null && getFilter().equals(filter) ) {
			this.filter=null;
		}
	}
	
	public void z_internalRemoveFromOneToMany(OneToMany oneToMany) {
		if ( getOneToMany()!=null && getOneToMany().equals(oneToMany) ) {
			this.oneToMany=null;
		}
	}
	
	/** Implements ->iterate( element : OneToMany; result : String = '' | ''.concat(result).concat(element.getHbmString2()) )
	 */
	private String iterate1() {
		String result = "";
		for ( OneToMany element : Stdlib.objectAsSet(this.getOneToMany()) ) {
			result = "".concat(result).concat(element.getHbmString2());
		}
		return result;
	}
	
	/** Used to set the owner internally in extended composition semantics
	 * 
	 * @param newOwner 
	 */
	protected void internalSetOwner(FirstLevelClass newOwner) {
		this.abstractClass=newOwner;
	}
	
	public void createComponents() {
		super.createComponents();
		if ( getOneToMany()==null ) {
			setOneToMany(new OneToMany());
		}
	}

}