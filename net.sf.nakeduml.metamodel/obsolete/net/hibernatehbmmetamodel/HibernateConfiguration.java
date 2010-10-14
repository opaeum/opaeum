package net.hibernatehbmmetamodel;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.sf.nakeduml.util.CompositionNode;

public class HibernateConfiguration extends HbmElement implements CompositionNode {
	private Boolean extendsBaseEntity = false;
	private Set<HbmClass> hbmClass = new HashSet<HbmClass>();
	private List<String> path = new ArrayList<String>();
	private Set<SubClass> subClass = new HashSet<SubClass>();
	private HbmWorkspace hbmWorkspace;
	private String hbmName;
	private String schema;

	/** Default constructor for 
	 */
	public HibernateConfiguration() {
	}
	
	/** This constructor is intended for easy initialization in unit tests
	 * 
	 * @param owningObject 
	 */
	public HibernateConfiguration(HbmWorkspace owningObject) {
		init(owningObject);
		addToOwningObject();
	}

	public void addAllToHbmClass(Set<HbmClass> hbmClass) {
		for ( HbmClass o : hbmClass ) {
			addToHbmClass(o);
		}
	}
	
	public void addAllToPath(List<String> path) {
		for ( String o : path ) {
			addToPath(o);
		}
	}
	
	public void addAllToSubClass(Set<SubClass> subClass) {
		for ( SubClass o : subClass ) {
			addToSubClass(o);
		}
	}
	
	public void addToHbmClass(HbmClass hbmClass) {
		hbmClass.setHibernateConfiguration(this);
	}
	
	/** Call this method when you want to attach this object to the containment tree. Useful with transitive persistence
	 */
	public void addToOwningObject() {
		getHbmWorkspace().getHibernateConfiguration().add((HibernateConfiguration)this);
	}
	
	public void addToPath(String path) {
		getPath().add(path);
	}
	
	public void addToSubClass(SubClass subClass) {
		subClass.setHibernateConfiguration(this);
	}
	
	public void clearHbmClass() {
		removeAllFromHbmClass(getHbmClass());
	}
	
	public void clearPath() {
		removeAllFromPath(getPath());
	}
	
	public void clearSubClass() {
		removeAllFromSubClass(getSubClass());
	}
	
	public Set<FirstLevelClass> getAbstractClass() {
		Set<FirstLevelClass> abstractClassSubsetting = new HashSet<FirstLevelClass>();
		abstractClassSubsetting.addAll(getHbmClass());
		abstractClassSubsetting.addAll(getSubClass());
		return abstractClassSubsetting;
	}
	
	public Boolean getExtendsBaseEntity() {
		return extendsBaseEntity;
	}
	
	public String getHbmAttributes() {
		String hbmAttributes = "schema=\"".concat(this.getSchema()).concat("\"");
		return hbmAttributes;
	}
	
	public Set<HbmClass> getHbmClass() {
		return hbmClass;
	}
	
	public String getHbmName() {
		String hbmNameSubsetting = "";
		if ( this.hbmName!=null ) {
			hbmNameSubsetting=this.hbmName;
		}
		return hbmNameSubsetting;
	}
	
	public String getHbmString2() {
		String result = "";
		result= ("<?xml version=\"1.0\"?>\n").concat(("<!DOCTYPE hibernate-mapping PUBLIC \"-//Hibernate/Hibernate Mapping DTD 3.0//EN\"\n")).concat(("                                   \"http://hibernate.sourceforge.net/hibernate-mapping-3.0.dtd\"\n")).concat((this.getExtendsBaseEntity() ?
			("[<!ENTITY baseEntity SYSTEM \"classpath://util/BaseEntity.xml\">]>") :
			">")).concat("<").concat(this.getHbmName()).concat((" ")).concat(this.getHbmAttributes()).concat(">\n").concat(iterate1()).concat(iterate2()).concat("</").concat(this.getHbmName()).concat(">");
		return result;
	}
	
	public HbmWorkspace getHbmWorkspace() {
		return hbmWorkspace;
	}
	
	public List<HbmElement> getOwnedElement() {
		List<HbmElement> ownedElementSubsetting = new ArrayList<HbmElement>();
		ownedElementSubsetting.addAll(super.getOwnedElement());
		ownedElementSubsetting.addAll(getHbmClass());
		ownedElementSubsetting.addAll(getSubClass());
		return ownedElementSubsetting;
	}
	
	public HbmElement getOwner() {
		HbmElement ownerSubsetting = null;
		ownerSubsetting=super.getOwner();
		if ( getHbmWorkspace()!=null ) {
			ownerSubsetting=getHbmWorkspace();
		}
		return ownerSubsetting;
	}
	
	public CompositionNode getOwningObject() {
		return getHbmWorkspace();
	}
	
	public List<String> getPath() {
		return path;
	}
	
	public String getSchema() {
		return schema;
	}
	
	public Set<SubClass> getSubClass() {
		return subClass;
	}
	
	public void init(CompositionNode owner) {
		super.init(owner);
		internalSetOwner((HbmWorkspace)owner);
		this.setHbmName( "hibernate-mapping" );
		createComponents();
	}
	
	public void markDeleted() {
		super.markDeleted();
		if ( getHbmWorkspace()!=null ) {
			getHbmWorkspace().getHibernateConfiguration().remove((HibernateConfiguration)this);
		}
		for ( HbmClass child : new ArrayList<HbmClass>(getHbmClass()) ) {
			child.markDeleted();
		}
		for ( FirstLevelClass child : new ArrayList<FirstLevelClass>(getAbstractClass()) ) {
			child.markDeleted();
		}
		for ( SubClass child : new ArrayList<SubClass>(getSubClass()) ) {
			child.markDeleted();
		}
	}
	
	public void removeAllFromHbmClass(Set<HbmClass> hbmClass) {
		for ( HbmClass o : hbmClass ) {
			removeFromHbmClass(o);
		}
	}
	
	public void removeAllFromPath(List<String> path) {
		for ( String o : path ) {
			removeFromPath(o);
		}
	}
	
	public void removeAllFromSubClass(Set<SubClass> subClass) {
		for ( SubClass o : subClass ) {
			removeFromSubClass(o);
		}
	}
	
	public void removeFromHbmClass(HbmClass hbmClass) {
		hbmClass.setHibernateConfiguration(null);
	}
	
	public void removeFromOwningObject() {
		this.markDeleted();
	}
	
	public void removeFromPath(String path) {
		getPath().remove(path);
	}
	
	public void removeFromSubClass(SubClass subClass) {
		subClass.setHibernateConfiguration(null);
	}
	
	public void setExtendsBaseEntity(Boolean extendsBaseEntity) {
		this.extendsBaseEntity=extendsBaseEntity;
	}
	
	public void setHbmClass(Set<HbmClass> hbmClass) {
		for ( HbmClass o : new HashSet<HbmClass>(this.hbmClass) ) {
			o.setHibernateConfiguration(null);
		}
		for ( HbmClass o : hbmClass ) {
			o.setHibernateConfiguration((HibernateConfiguration)this);
		}
	}
	
	public void setHbmName(String hbmName) {
		this.hbmName=hbmName;
	}
	
	public void setHbmWorkspace(HbmWorkspace hbmWorkspace) {
		if ( this.hbmWorkspace!=null ) {
			this.hbmWorkspace.getHibernateConfiguration().remove((HibernateConfiguration)this);
		}
		if ( hbmWorkspace!=null ) {
			hbmWorkspace.getHibernateConfiguration().add((HibernateConfiguration)this);
			this.hbmWorkspace=hbmWorkspace;
		} else {
			this.hbmWorkspace=null;
		}
	}
	
	public void setPath(List<String> path) {
		this.path=path;
	}
	
	public void setSchema(String schema) {
		this.schema=schema;
	}
	
	public void setSubClass(Set<SubClass> subClass) {
		for ( SubClass o : new HashSet<SubClass>(this.subClass) ) {
			o.setHibernateConfiguration(null);
		}
		for ( SubClass o : subClass ) {
			o.setHibernateConfiguration((HibernateConfiguration)this);
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
		sb.append("schema=");
		sb.append(getSchema());
		sb.append(";");
		sb.append("hbmName=");
		sb.append(getHbmName());
		sb.append(";");
		sb.append("hbmAttributes=");
		sb.append(getHbmAttributes());
		sb.append(";");
		sb.append("extendsBaseEntity=");
		sb.append(getExtendsBaseEntity());
		sb.append(";");
		if ( getHbmWorkspace()==null ) {
			sb.append("hbmWorkspace=null;");
		} else {
			sb.append("hbmWorkspace="+getHbmWorkspace().getClass().getSimpleName()+"[");
			sb.append(getHbmWorkspace().hashCode());
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
		for ( HbmClass hbmClass : getHbmClass() ) {
			sb.append("<hbmClass>");
			sb.append(hbmClass.toXmlString());
			sb.append("</hbmClass>");
			sb.append("\n");
		}
		for ( SubClass subClass : getSubClass() ) {
			sb.append("<subClass>");
			sb.append(subClass.toXmlString());
			sb.append("</subClass>");
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
		if ( getHbmName()==null ) {
			sb.append("<hbmName/>");
		} else {
			sb.append("<hbmName>");
			sb.append(getHbmName());
			sb.append("</hbmName>");
			sb.append("\n");
		}
		for ( String path : getPath() ) {
			sb.append("<path>");
			sb.append(path);
			sb.append("</path>");
			sb.append("\n");
		}
		if ( getExtendsBaseEntity()==null ) {
			sb.append("<extendsBaseEntity/>");
		} else {
			sb.append("<extendsBaseEntity>");
			sb.append(getExtendsBaseEntity());
			sb.append("</extendsBaseEntity>");
			sb.append("\n");
		}
		if ( getHbmWorkspace()==null ) {
			sb.append("<hbmWorkspace/>");
		} else {
			sb.append("<hbmWorkspace>");
			sb.append(getHbmWorkspace().getClass().getSimpleName());
			sb.append("[");
			sb.append(getHbmWorkspace().hashCode());
			sb.append("]");
			sb.append("</hbmWorkspace>");
			sb.append("\n");
		}
		return sb.toString();
	}
	
	/** Implements ->iterate( element : HbmClass; result : String = '' | ''.concat(result).concat(element.getHbmString2()) )
	 */
	private String iterate1() {
		String result = "";
		for ( HbmClass element : this.getHbmClass() ) {
			result = "".concat(result).concat(element.getHbmString2());
		}
		return result;
	}
	
	/** Implements ->iterate( element : SubClass; result : String = '' | ''.concat(result).concat(element.getHbmString2()) )
	 */
	private String iterate2() {
		String result = "";
		for ( SubClass element : this.getSubClass() ) {
			result = "".concat(result).concat(element.getHbmString2());
		}
		return result;
	}
	
	/** Used to set the owner internally in extended composition semantics
	 * 
	 * @param newOwner 
	 */
	protected void internalSetOwner(HbmWorkspace newOwner) {
		this.hbmWorkspace=newOwner;
	}
	
	public void copyShallowState(HibernateConfiguration from, HibernateConfiguration to) {
		to.setQualifiedName(from.getQualifiedName());
		to.setSchema(from.getSchema());
		to.setHbmName(from.getHbmName());
		to.getPath().addAll(from.getPath());
		to.setExtendsBaseEntity(from.getExtendsBaseEntity());
	}
	
	public void copyState(HibernateConfiguration from, HibernateConfiguration to) {
		to.setQualifiedName(from.getQualifiedName());
		for ( HbmClass child : from.getHbmClass() ) {
			to.addToHbmClass(child.makeCopy());
		}
		for ( SubClass child : from.getSubClass() ) {
			to.addToSubClass(child.makeCopy());
		}
		to.setSchema(from.getSchema());
		to.setHbmName(from.getHbmName());
		to.getPath().addAll(from.getPath());
		to.setExtendsBaseEntity(from.getExtendsBaseEntity());
	}
	
	public void createComponents() {
		super.createComponents();
	}
	
	public HbmClass createHbmClass() {
		HbmClass newInstance= new HbmClass();
		newInstance.init(this);
		return newInstance;
	}
	
	public SubClass createSubClass() {
		SubClass newInstance= new SubClass();
		newInstance.init(this);
		return newInstance;
	}
	
	public HibernateConfiguration makeCopy() {
		HibernateConfiguration result = new HibernateConfiguration();
		copyState((HibernateConfiguration)this,result);
		return result;
	}

}