package net.hibernatehbmmetamodel;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.sf.nakeduml.util.CompositionNode;

public class SubClass extends FirstLevelClass implements CompositionNode {
	private String discriminatorValue;
	private String _extends;
	private HibernateConfiguration hibernateConfiguration;
	private Boolean _abstract = false;
	private String name;
	private String hbmName;
	private Set<Join> join = new HashSet<Join>();
	private Boolean lazy = false;
	private Boolean selectBeforeUpdate = false;
	private String entityName;

	/** This constructor is intended for easy initialization in unit tests
	 * 
	 * @param owningObject 
	 */
	public SubClass(HibernateConfiguration owningObject) {
		init(owningObject);
		addToOwningObject();
	}
	
	/** Default constructor for 
	 */
	public SubClass() {
	}

	public void addAllToJoin(Set<Join> join) {
		for ( Join o : join ) {
			addToJoin(o);
		}
	}
	
	public void addToJoin(Join join) {
		join.setSubClass(this);
	}
	
	/** Call this method when you want to attach this object to the containment tree. Useful with transitive persistence
	 */
	public void addToOwningObject() {
		getHibernateConfiguration().getSubClass().add((SubClass)this);
	}
	
	public void clearJoin() {
		removeAllFromJoin(getJoin());
	}
	
	public String getDiscriminatorValue() {
		return discriminatorValue;
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
			"false"))).concat(("\" ")))).concat(("extends=\"".concat(this.get_extends()).concat(("\" ")))).concat(("discriminator-value=\"".concat(this.getDiscriminatorValue()).concat(("\" "))));
		return hbmAttributes;
	}
	
	public String getHbmName() {
		return hbmName;
	}
	
	public HibernateConfiguration getHibernateConfiguration() {
		HibernateConfiguration hibernateConfigurationSubsetting = null;
		if ( this.hibernateConfiguration!=null ) {
			hibernateConfigurationSubsetting=this.hibernateConfiguration;
		}
		return hibernateConfigurationSubsetting;
	}
	
	public Set<Join> getJoin() {
		return join;
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
		ownedElementSubsetting.addAll(getJoin());
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
	
	public Boolean get_abstract() {
		return _abstract;
	}
	
	public String get_extends() {
		return _extends;
	}
	
	public void init(CompositionNode owner) {
		super.init(owner);
		internalSetOwner((HibernateConfiguration)owner);
		this.setDiscriminatorValue( "" );
		this.set_extends( "" );
		this.set_abstract( true );
		this.setName( "" );
		this.setHbmName( "subclass" );
		this.setLazy( true );
		this.setSelectBeforeUpdate( false );
		createComponents();
	}
	
	public void markDeleted() {
		super.markDeleted();
		if ( getHibernateConfiguration()!=null ) {
			getHibernateConfiguration().getAbstractClass().remove((SubClass)this);
		}
		if ( getHibernateConfiguration()!=null ) {
			getHibernateConfiguration().getSubClass().remove((SubClass)this);
		}
		for ( Join child : new ArrayList<Join>(getJoin()) ) {
			child.markDeleted();
		}
	}
	
	public void removeAllFromJoin(Set<Join> join) {
		for ( Join o : join ) {
			removeFromJoin(o);
		}
	}
	
	public void removeFromJoin(Join join) {
		join.setSubClass(null);
	}
	
	public void removeFromOwningObject() {
		this.markDeleted();
	}
	
	public void setDiscriminatorValue(String discriminatorValue) {
		this.discriminatorValue=discriminatorValue;
	}
	
	public void setEntityName(String entityName) {
		this.entityName=entityName;
	}
	
	public void setHbmName(String hbmName) {
		this.hbmName=hbmName;
	}
	
	public void setHibernateConfiguration(HibernateConfiguration hibernateConfiguration) {
		if ( this.hibernateConfiguration!=null ) {
			this.hibernateConfiguration.getSubClass().remove((SubClass)this);
		}
		if ( hibernateConfiguration!=null ) {
			hibernateConfiguration.getSubClass().add((SubClass)this);
			this.hibernateConfiguration=hibernateConfiguration;
		} else {
			this.hibernateConfiguration=null;
		}
	}
	
	public void setJoin(Set<Join> join) {
		for ( Join o : new HashSet<Join>(this.join) ) {
			o.setSubClass(null);
		}
		for ( Join o : join ) {
			o.setSubClass((SubClass)this);
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
	
	public void set_abstract(Boolean _abstract) {
		this._abstract=_abstract;
	}
	
	public void set_extends(String _extends) {
		this._extends=_extends;
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
		sb.append("hbmName=");
		sb.append(getHbmName());
		sb.append(";");
		sb.append("_extends=");
		sb.append(get_extends());
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
		sb.append("discriminatorValue=");
		sb.append(getDiscriminatorValue());
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
		for ( Join join : getJoin() ) {
			sb.append("<join>");
			sb.append(join.toXmlString());
			sb.append("</join>");
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
		if ( get_extends()==null ) {
			sb.append("<_extends/>");
		} else {
			sb.append("<_extends>");
			sb.append(get_extends());
			sb.append("</_extends>");
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
		if ( getDiscriminatorValue()==null ) {
			sb.append("<discriminatorValue/>");
		} else {
			sb.append("<discriminatorValue>");
			sb.append(getDiscriminatorValue());
			sb.append("</discriminatorValue>");
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
	
	/** Used to set the owner internally in extended composition semantics
	 * 
	 * @param newOwner 
	 */
	protected void internalSetOwner(HibernateConfiguration newOwner) {
		this.hibernateConfiguration=newOwner;
	}
	
	public void copyShallowState(SubClass from, SubClass to) {
		to.setQualifiedName(from.getQualifiedName());
		to.setHbmName(from.getHbmName());
		to.set_extends(from.get_extends());
		to.setName(from.getName());
		to.setEntityName(from.getEntityName());
		to.set_abstract(from.get_abstract());
		to.setLazy(from.getLazy());
		to.setSelectBeforeUpdate(from.getSelectBeforeUpdate());
		to.setDiscriminatorValue(from.getDiscriminatorValue());
	}
	
	public void copyState(SubClass from, SubClass to) {
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
		for ( Join child : from.getJoin() ) {
			to.addToJoin(child.makeCopy());
		}
		to.setHbmName(from.getHbmName());
		to.set_extends(from.get_extends());
		to.setName(from.getName());
		to.setEntityName(from.getEntityName());
		to.set_abstract(from.get_abstract());
		to.setLazy(from.getLazy());
		to.setSelectBeforeUpdate(from.getSelectBeforeUpdate());
		to.setDiscriminatorValue(from.getDiscriminatorValue());
	}
	
	public void createComponents() {
		super.createComponents();
	}
	
	public Join createJoin() {
		Join newInstance= new Join();
		newInstance.init(this);
		return newInstance;
	}
	
	public SubClass makeCopy() {
		SubClass result = new SubClass();
		copyState((SubClass)this,result);
		return result;
	}

}