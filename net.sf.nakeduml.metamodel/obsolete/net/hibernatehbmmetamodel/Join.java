package net.hibernatehbmmetamodel;

import java.util.ArrayList;
import java.util.List;

import net.sf.nakeduml.util.CompositionNode;

public class Join extends AbstractClass implements CompositionNode {
	private Fetch fetch;
	private String hbmName;
	private Key key;
	private String schema;
	private String table;
	private SubClass subClass;

	/** Default constructor for 
	 */
	public Join() {
	}
	
	/** This constructor is intended for easy initialization in unit tests
	 * 
	 * @param owningObject 
	 */
	public Join(SubClass owningObject) {
		init(owningObject);
		addToOwningObject();
	}

	/** Call this method when you want to attach this object to the containment tree. Useful with transitive persistence
	 */
	public void addToOwningObject() {
		getSubClass().getJoin().add((Join)this);
	}
	
	public Fetch getFetch() {
		return fetch;
	}
	
	public String getHbmAttributes() {
		String hbmAttributes = ("table=\"`".concat(this.getTable()).concat(("`\" "))).concat("fetch=\"").concat(this.getFetch().getFetchName()).concat(("\" ")).concat(("schema=\"`".concat(this.getSchema()).concat(("`\" "))));
		return hbmAttributes;
	}
	
	public String getHbmName() {
		return hbmName;
	}
	
	public String getHbmString2() {
		String result = "";
		result= ("<".concat(this.getHbmName()).concat((" "))).concat(this.getHbmAttributes()).concat(">\n").concat(this.getKey().getHbmString2()).concat(iterate1()).concat(iterate2()).concat("</").concat(this.getHbmName()).concat(">");
		return result;
	}
	
	public Key getKey() {
		return key;
	}
	
	public List<HbmElement> getOwnedElement() {
		List<HbmElement> ownedElementSubsetting = new ArrayList<HbmElement>();
		ownedElementSubsetting.addAll(super.getOwnedElement());
		if ( getKey()!=null ) {
			ownedElementSubsetting.add(getKey());
		}
		return ownedElementSubsetting;
	}
	
	public HbmElement getOwner() {
		HbmElement ownerSubsetting = null;
		ownerSubsetting=super.getOwner();
		if ( getSubClass()!=null ) {
			ownerSubsetting=getSubClass();
		}
		return ownerSubsetting;
	}
	
	public CompositionNode getOwningObject() {
		return getSubClass();
	}
	
	public String getSchema() {
		return schema;
	}
	
	public SubClass getSubClass() {
		return subClass;
	}
	
	public String getTable() {
		return table;
	}
	
	public void init(CompositionNode owner) {
		super.init(owner);
		internalSetOwner((SubClass)owner);
		this.setHbmName( "join" );
		this.setSchema( this.getSubClass().getHibernateConfiguration().getSchema() );
		createComponents();
	}
	
	public void markDeleted() {
		super.markDeleted();
		if ( getKey()!=null ) {
			getKey().setJoin(null);
		}
		if ( getSubClass()!=null ) {
			getSubClass().getJoin().remove((Join)this);
		}
		getKey().markDeleted();
	}
	
	public void removeFromOwningObject() {
		this.markDeleted();
	}
	
	public void setFetch(Fetch fetch) {
		this.fetch=fetch;
	}
	
	public void setHbmName(String hbmName) {
		this.hbmName=hbmName;
	}
	
	public void setKey(Key key) {
		Key oldValue = this.key;
		if ( oldValue==null ) {
			this.key=key;
			if ( key!=null ) {
				key.z_internalAddToJoin((Join)this);
			}
		} else {
			if ( !oldValue.equals(key) ) {
				this.key=key;
				oldValue.z_internalRemoveFromJoin(this);
				if ( key!=null ) {
					key.z_internalAddToJoin((Join)this);
				}
			}
		}
	}
	
	public void setSchema(String schema) {
		this.schema=schema;
	}
	
	public void setSubClass(SubClass subClass) {
		if ( this.subClass!=null ) {
			this.subClass.getJoin().remove((Join)this);
		}
		if ( subClass!=null ) {
			subClass.getJoin().add((Join)this);
			this.subClass=subClass;
		} else {
			this.subClass=null;
		}
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
		if ( getKey()==null ) {
			sb.append("key=null;");
		} else {
			sb.append("key="+getKey().getClass().getSimpleName()+"[");
			sb.append(getKey().hashCode());
			sb.append("];");
		}
		sb.append("hbmName=");
		sb.append(getHbmName());
		sb.append(";");
		sb.append("table=");
		sb.append(getTable());
		sb.append(";");
		sb.append("hbmAttributes=");
		sb.append(getHbmAttributes());
		sb.append(";");
		sb.append("schema=");
		sb.append(getSchema());
		sb.append(";");
		if ( getSubClass()==null ) {
			sb.append("subClass=null;");
		} else {
			sb.append("subClass="+getSubClass().getClass().getSimpleName()+"[");
			sb.append(getSubClass().getName());
			sb.append("];");
		}
		sb.append("fetch=");
		sb.append(getFetch());
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
		if ( getKey()==null ) {
			sb.append("<key/>");
		} else {
			sb.append("<key>");
			sb.append(getKey().toXmlString());
			sb.append("</key>");
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
		if ( getTable()==null ) {
			sb.append("<table/>");
		} else {
			sb.append("<table>");
			sb.append(getTable());
			sb.append("</table>");
			sb.append("\n");
		}
		if ( getSchema()==null ) {
			sb.append("<schema/>");
		} else {
			sb.append("<schema>");
			sb.append(getSchema());
			sb.append("</schema>");
			sb.append("\n");
		}
		if ( getSubClass()==null ) {
			sb.append("<subClass/>");
		} else {
			sb.append("<subClass>");
			sb.append(getSubClass().getClass().getSimpleName());
			sb.append("[");
			sb.append(getSubClass().getName());
			sb.append("]");
			sb.append("</subClass>");
			sb.append("\n");
		}
		if ( getFetch()==null ) {
			sb.append("<fetch/>");
		} else {
			sb.append("<fetch>");
			sb.append(getFetch());
			sb.append("</fetch>");
			sb.append("\n");
		}
		return sb.toString();
	}
	
	public void z_internalAddToKey(Key key) {
		if ( getKey()==null || !getKey().equals(key) ) {
			this.key=key;
		}
	}
	
	public void z_internalRemoveFromKey(Key key) {
		if ( getKey()!=null && getKey().equals(key) ) {
			this.key=null;
		}
	}
	
	/** Implements ->iterate( element : Property; result : String = '' | ''.concat(result).concat(element.getHbmString2()) )
	 */
	private String iterate1() {
		String result = "";
		for ( Property element : this.getProperty() ) {
			result = "".concat(result).concat(element.getHbmString2());
		}
		return result;
	}
	
	/** Implements ->iterate( element : ManyToOne; result : String = '' | ''.concat(result).concat(element.getHbmString2()) )
	 */
	private String iterate2() {
		String result = "";
		for ( ManyToOne element : this.getManyToOne() ) {
			result = "".concat(result).concat(element.getHbmString2());
		}
		return result;
	}
	
	/** Used to set the owner internally in extended composition semantics
	 * 
	 * @param newOwner 
	 */
	protected void internalSetOwner(SubClass newOwner) {
		this.subClass=newOwner;
	}
	
	public void copyShallowState(Join from, Join to) {
		to.setQualifiedName(from.getQualifiedName());
		to.setKey(getKey());
		to.setHbmName(from.getHbmName());
		to.setTable(from.getTable());
		to.setSchema(from.getSchema());
		to.setFetch(from.getFetch());
	}
	
	public void copyState(Join from, Join to) {
		to.setQualifiedName(from.getQualifiedName());
		for ( ManyToOne child : from.getManyToOne() ) {
			to.addToManyToOne(child.makeCopy());
		}
		for ( Property child : from.getProperty() ) {
			to.addToProperty(child.makeCopy());
		}
		if ( from.getKey()!=null ) {
			to.setKey(from.getKey().makeCopy());
		}
		to.setHbmName(from.getHbmName());
		to.setTable(from.getTable());
		to.setSchema(from.getSchema());
		to.setFetch(from.getFetch());
	}
	
	public void createComponents() {
		super.createComponents();
	}
	
	public Key createKey() {
		Key newInstance= new Key();
		newInstance.init(this);
		return newInstance;
	}
	
	public Join makeCopy() {
		Join result = new Join();
		copyState((Join)this,result);
		return result;
	}

}