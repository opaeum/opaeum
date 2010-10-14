package net.hibernatehbmmetamodel;

import java.util.ArrayList;
import java.util.List;

import net.sf.nakeduml.util.CompositionNode;

public class HbmClass extends FirstLevelClass implements CompositionNode {
	private String table;
	private String name;
	private Boolean _abstract = false;
	private Id id;
	private Boolean lazy = false;
	private Boolean selectBeforeUpdate = false;
	private HibernateConfiguration hibernateConfiguration;
	private String entityName;
	private String hbmName;
	private Discriminator discriminator;

	/** Default constructor for 
	 */
	public HbmClass() {
	}
	
	/** This constructor is intended for easy initialization in unit tests
	 * 
	 * @param owningObject 
	 */
	public HbmClass(HibernateConfiguration owningObject) {
		init(owningObject);
		addToOwningObject();
	}

	/** Call this method when you want to attach this object to the containment tree. Useful with transitive persistence
	 */
	public void addToOwningObject() {
		getHibernateConfiguration().getHbmClass().add((HbmClass)this);
	}
	
	public Discriminator getDiscriminator() {
		return discriminator;
	}
	
	public String getEntityName() {
		return entityName;
	}
	
	public String getHbmAttributes() {
		String hbmAttributes = ("name=\"".concat(this.getName()).concat(("\" "))).concat(("abstract=\"".concat((this.get_abstract() ?
			"true" :
			"false"))).concat(("\" ")).concat(("entity-name=\"".concat(this.getEntityName()).concat(("\" ")))).concat(("lazy=\"".concat((this.getLazy() ?
			"true" :
			"false"))).concat(("\" "))).concat(("select-before-update=\"".concat((this.getSelectBeforeUpdate() ?
			"true" :
			"false"))).concat(("\" ")))).concat(("table=\"`".concat(this.getTable()).concat(("`\" "))));
		return hbmAttributes;
	}
	
	public String getHbmName() {
		return hbmName;
	}
	
	public String getHbmString2() {
		String result = "";
		result= ("<".concat(this.getHbmName()).concat((" "))).concat(this.getHbmAttributes()).concat(">\n").concat(this.getId().getHbmString2()).concat(this.getDiscriminator().getHbmString2()).concat((this.getHibernateConfiguration().getExtendsBaseEntity() ?
			"&baseEntity;" :
			"")).concat(iterate1()).concat(iterate2()).concat(iterate3()).concat("</").concat(this.getHbmName()).concat(">");
		return result;
	}
	
	public HibernateConfiguration getHibernateConfiguration() {
		HibernateConfiguration hibernateConfigurationSubsetting = null;
		if ( this.hibernateConfiguration!=null ) {
			hibernateConfigurationSubsetting=this.hibernateConfiguration;
		}
		return hibernateConfigurationSubsetting;
	}
	
	public Id getId() {
		return id;
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
		if ( getId()!=null ) {
			ownedElementSubsetting.add(getId());
		}
		if ( getDiscriminator()!=null ) {
			ownedElementSubsetting.add(getDiscriminator());
		}
		return ownedElementSubsetting;
	}
	
	public HbmElement getOwner() {
		HbmElement ownerSubsetting = null;
		ownerSubsetting=super.getOwner();
		if ( getHibernateConfiguration()!=null ) {
			ownerSubsetting=getHibernateConfiguration();
		}
		return ownerSubsetting;
	}
	
	public CompositionNode getOwningObject() {
		return getHibernateConfiguration();
	}
	
	public Boolean getSelectBeforeUpdate() {
		return selectBeforeUpdate;
	}
	
	public String getTable() {
		return table;
	}
	
	public Boolean get_abstract() {
		return _abstract;
	}
	
	public void init(CompositionNode owner) {
		super.init(owner);
		internalSetOwner((HibernateConfiguration)owner);
		this.setTable( "" );
		this.set_abstract( true );
		this.setLazy( true );
		this.setSelectBeforeUpdate( false );
		this.setEntityName( "" );
		this.setHbmName( "class" );
		createComponents();
	}
	
	public void markDeleted() {
		super.markDeleted();
		if ( getHibernateConfiguration()!=null ) {
			getHibernateConfiguration().getAbstractClass().remove((HbmClass)this);
		}
		if ( getId()!=null ) {
			getId().setHbmClass(null);
		}
		if ( getDiscriminator()!=null ) {
			getDiscriminator().setHbmClass(null);
		}
		if ( getHibernateConfiguration()!=null ) {
			getHibernateConfiguration().getHbmClass().remove((HbmClass)this);
		}
		getId().markDeleted();
		getDiscriminator().markDeleted();
	}
	
	public void removeFromOwningObject() {
		this.markDeleted();
	}
	
	public void setDiscriminator(Discriminator discriminator) {
		Discriminator oldValue = this.discriminator;
		if ( oldValue==null ) {
			this.discriminator=discriminator;
			if ( discriminator!=null ) {
				discriminator.z_internalAddToHbmClass((HbmClass)this);
			}
		} else {
			if ( !oldValue.equals(discriminator) ) {
				this.discriminator=discriminator;
				oldValue.z_internalRemoveFromHbmClass(this);
				if ( discriminator!=null ) {
					discriminator.z_internalAddToHbmClass((HbmClass)this);
				}
			}
		}
	}
	
	public void setEntityName(String entityName) {
		this.entityName=entityName;
	}
	
	public void setHbmName(String hbmName) {
		this.hbmName=hbmName;
	}
	
	public void setHibernateConfiguration(HibernateConfiguration hibernateConfiguration) {
		if ( this.hibernateConfiguration!=null ) {
			this.hibernateConfiguration.getHbmClass().remove((HbmClass)this);
		}
		if ( hibernateConfiguration!=null ) {
			hibernateConfiguration.getHbmClass().add((HbmClass)this);
			this.hibernateConfiguration=hibernateConfiguration;
		} else {
			this.hibernateConfiguration=null;
		}
	}
	
	public void setId(Id id) {
		Id oldValue = this.id;
		if ( oldValue==null ) {
			this.id=id;
			if ( id!=null ) {
				id.z_internalAddToHbmClass((HbmClass)this);
			}
		} else {
			if ( !oldValue.equals(id) ) {
				this.id=id;
				oldValue.z_internalRemoveFromHbmClass(this);
				if ( id!=null ) {
					id.z_internalAddToHbmClass((HbmClass)this);
				}
			}
		}
	}
	
	public void setLazy(Boolean lazy) {
		this.lazy=lazy;
	}
	
	public void setName(String name) {
		this.name=name;
	}
	
	public void setSelectBeforeUpdate(Boolean selectBeforeUpdate) {
		this.selectBeforeUpdate=selectBeforeUpdate;
	}
	
	public void setTable(String table) {
		this.table=table;
	}
	
	public void set_abstract(Boolean _abstract) {
		this._abstract=_abstract;
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
		if ( getId()==null ) {
			sb.append("id=null;");
		} else {
			sb.append("id="+getId().getClass().getSimpleName()+"[");
			sb.append(getId().getName());
			sb.append("];");
		}
		if ( getDiscriminator()==null ) {
			sb.append("discriminator=null;");
		} else {
			sb.append("discriminator="+getDiscriminator().getClass().getSimpleName()+"[");
			sb.append(getDiscriminator().hashCode());
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
		sb.append("name=");
		sb.append(getName());
		sb.append(";");
		sb.append("entityName=");
		sb.append(getEntityName());
		sb.append(";");
		sb.append("_abstract=");
		sb.append(get_abstract());
		sb.append(";");
		sb.append("lazy=");
		sb.append(getLazy());
		sb.append(";");
		sb.append("selectBeforeUpdate=");
		sb.append(getSelectBeforeUpdate());
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
		if ( getId()==null ) {
			sb.append("<id/>");
		} else {
			sb.append("<id>");
			sb.append(getId().toXmlString());
			sb.append("</id>");
			sb.append("\n");
		}
		if ( getDiscriminator()==null ) {
			sb.append("<discriminator/>");
		} else {
			sb.append("<discriminator>");
			sb.append(getDiscriminator().toXmlString());
			sb.append("</discriminator>");
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
		if ( getName()==null ) {
			sb.append("<name/>");
		} else {
			sb.append("<name>");
			sb.append(getName());
			sb.append("</name>");
			sb.append("\n");
		}
		if ( getEntityName()==null ) {
			sb.append("<entityName/>");
		} else {
			sb.append("<entityName>");
			sb.append(getEntityName());
			sb.append("</entityName>");
			sb.append("\n");
		}
		if ( get_abstract()==null ) {
			sb.append("<_abstract/>");
		} else {
			sb.append("<_abstract>");
			sb.append(get_abstract());
			sb.append("</_abstract>");
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
		if ( getSelectBeforeUpdate()==null ) {
			sb.append("<selectBeforeUpdate/>");
		} else {
			sb.append("<selectBeforeUpdate>");
			sb.append(getSelectBeforeUpdate());
			sb.append("</selectBeforeUpdate>");
			sb.append("\n");
		}
		if ( getHibernateConfiguration()==null ) {
			sb.append("<hibernateConfiguration/>");
		} else {
			sb.append("<hibernateConfiguration>");
			sb.append(getHibernateConfiguration().getClass().getSimpleName());
			sb.append("[");
			sb.append(getHibernateConfiguration().hashCode());
			sb.append("]");
			sb.append("</hibernateConfiguration>");
			sb.append("\n");
		}
		return sb.toString();
	}
	
	public void z_internalAddToDiscriminator(Discriminator discriminator) {
		if ( getDiscriminator()==null || !getDiscriminator().equals(discriminator) ) {
			this.discriminator=discriminator;
		}
	}
	
	public void z_internalAddToId(Id id) {
		if ( getId()==null || !getId().equals(id) ) {
			this.id=id;
		}
	}
	
	public void z_internalRemoveFromDiscriminator(Discriminator discriminator) {
		if ( getDiscriminator()!=null && getDiscriminator().equals(discriminator) ) {
			this.discriminator=null;
		}
	}
	
	public void z_internalRemoveFromId(Id id) {
		if ( getId()!=null && getId().equals(id) ) {
			this.id=null;
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
	
	/** Implements ->iterate( element : Collection; result : String = '' | ''.concat(result).concat(element.getHbmString2()) )
	 */
	private String iterate3() {
		String result = "";
		for ( Collection element : this.getCollection() ) {
			result = "".concat(result).concat(element.getHbmString2());
		}
		return result;
	}
	
	/** Used to set the owner internally in extended composition semantics
	 * 
	 * @param newOwner 
	 */
	protected void internalSetOwner(HibernateConfiguration newOwner) {
		this.hibernateConfiguration=newOwner;
	}
	
	public void copyShallowState(HbmClass from, HbmClass to) {
		to.setQualifiedName(from.getQualifiedName());
		to.setId(getId());
		to.setDiscriminator(getDiscriminator());
		to.setHbmName(from.getHbmName());
		to.setTable(from.getTable());
		to.setName(from.getName());
		to.setEntityName(from.getEntityName());
		to.set_abstract(from.get_abstract());
		to.setLazy(from.getLazy());
		to.setSelectBeforeUpdate(from.getSelectBeforeUpdate());
	}
	
	public void copyState(HbmClass from, HbmClass to) {
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
		if ( from.getId()!=null ) {
			to.setId(from.getId().makeCopy());
		}
		if ( from.getDiscriminator()!=null ) {
			to.setDiscriminator(from.getDiscriminator().makeCopy());
		}
		to.setHbmName(from.getHbmName());
		to.setTable(from.getTable());
		to.setName(from.getName());
		to.setEntityName(from.getEntityName());
		to.set_abstract(from.get_abstract());
		to.setLazy(from.getLazy());
		to.setSelectBeforeUpdate(from.getSelectBeforeUpdate());
	}
	
	public void createComponents() {
		super.createComponents();
	}
	
	public Discriminator createDiscriminator() {
		Discriminator newInstance= new Discriminator();
		newInstance.init(this);
		return newInstance;
	}
	
	public Id createId() {
		Id newInstance= new Id();
		newInstance.init(this);
		return newInstance;
	}
	
	public HbmClass makeCopy() {
		HbmClass result = new HbmClass();
		copyState((HbmClass)this,result);
		return result;
	}

}