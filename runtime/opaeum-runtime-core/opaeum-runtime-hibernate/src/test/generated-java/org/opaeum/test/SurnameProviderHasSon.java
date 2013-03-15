package org.opaeum.test;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

import org.hibernate.annotations.AccessType;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.Index;
import org.opaeum.annotation.NumlMetaInfo;
import org.opaeum.annotation.PropertyMetaInfo;
import org.opaeum.hibernate.domain.InternalHibernatePersistence;
import org.opaeum.hibernate.domain.UiidBasedInterfaceValue;
import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.runtime.domain.HibernateEntity;
import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.runtime.domain.IntrospectionUtil;
import org.opaeum.runtime.environment.Environment;
import org.opaeum.runtime.persistence.AbstractPersistence;
import org.opaeum.test.util.ModelFormatter;
import org.opaeum.test.util.Stdlib;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

@NumlMetaInfo(applicationIdentifier="opaeum_hibernate_tests",uuid="Structures.uml@_g-YbcIhrEeK4s7QGypAJBA")
@Filter(name="noDeletedObjects")
@org.hibernate.annotations.Entity(dynamicUpdate=true)
@AccessType(	"field")
@Table(name="surname_provider_has_son",uniqueConstraints=
	@UniqueConstraint(columnNames={"surname_carrying_son_id","deleted_on"}))
@Entity(name="SurnameProviderHasSon")
public class SurnameProviderHasSon implements IPersistentObject, HibernateEntity, CompositionNode, Serializable {
		// Initialise to 1000 from 1970
	@Temporal(	javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="deleted_on")
	private Date deletedOn = Stdlib.FUTURE;
	@TableGenerator(allocationSize=20,name="id_generator",pkColumnName="type",pkColumnValue="surname_provider_has_son",table="hi_value")
	@Id
	@GeneratedValue(generator="id_generator",strategy=javax.persistence.GenerationType.TABLE)
	private Long id;
	static private Set<? extends SurnameProviderHasSon> mockedAllInstances;
	@Version
	@Column(name="object_version")
	private int objectVersion;
	@Transient
	private InternalHibernatePersistence persistence;
	static final private long serialVersionUID = 1131610077658452273l;
	@Index(columnNames="surname_carrying_son_id",name="idx_surname_provider_has_son_surname_carrying_son_id")
	@ManyToOne(fetch=javax.persistence.FetchType.LAZY)
	@JoinColumn(name="surname_carrying_son_id",nullable=true)
	protected Brother surnameCarryingSon;
	@Embedded
	@AttributeOverrides(	{
		@AttributeOverride(column=
			@Column(name="surname_provider"),name="identifier"),
		@AttributeOverride(column=
			@Column(name="surname_provider_type"),name="classIdentifier")})
	protected UiidBasedInterfaceValue surnameProvider;
	private String uid;

	/** Constructor for SurnameProviderHasSon
	 * 
	 * @param end1 
	 * @param end2 
	 */
	public SurnameProviderHasSon(Brother end1, SurnameProvider end2) {
		this.z_internalAddToSurnameCarryingSon(end1);
		this.z_internalAddToSurnameProvider(end2);
	}
	
	/** Constructor for SurnameProviderHasSon
	 * 
	 * @param end2 
	 * @param end1 
	 */
	public SurnameProviderHasSon(SurnameProvider end2, Brother end1) {
		this.z_internalAddToSurnameCarryingSon(end1);
		this.z_internalAddToSurnameProvider(end2);
	}
	
	/** Default constructor for SurnameProviderHasSon
	 */
	public SurnameProviderHasSon() {
	}

	/** Call this method when you want to attach this object to the containment tree. Useful with transitive persistence
	 */
	public void addToOwningObject() {
	}
	
	static public Set<? extends SurnameProviderHasSon> allInstances(AbstractPersistence persistence) {
		if ( mockedAllInstances==null ) {
			return new HashSet(persistence.readAll(org.opaeum.test.SurnameProviderHasSon.class));
		} else {
			return mockedAllInstances;
		}
	}
	
	public void buildTreeFromXml(Element xml, Map<String, Object> map) {
		setUid(xml.getAttribute("uid"));
		NodeList propertyNodes = xml.getChildNodes();
		int i = 0;
		while ( i<propertyNodes.getLength() ) {
			Node currentPropertyNode = propertyNodes.item(i++);
		
		}
	}
	
	public void clear() {
		this.z_internalRemoveFromSurnameProvider(getSurnameProvider());
		this.z_internalRemoveFromSurnameCarryingSon(getSurnameCarryingSon());
		markDeleted();
	}
	
	public void createComponents() {
	}
	
	public boolean equals(Object other) {
		if ( other instanceof SurnameProviderHasSon ) {
			return other==this || ((SurnameProviderHasSon)other).getUid().equals(this.getUid());
		}
		return false;
	}
	
	public Date getDeletedOn() {
		return this.deletedOn;
	}
	
	public Long getId() {
		return this.id;
	}
	
	public String getName() {
		return "SurnameProviderHasSon["+getId()+"]";
	}
	
	public int getObjectVersion() {
		return this.objectVersion;
	}
	
	public CompositionNode getOwningObject() {
		return getSurnameProvider();
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=984688315664840852l,opposite="surnameProviderHasSon_surnameProvider",uuid="Structures.uml@_g-YbcYhrEeK4s7QGypAJBA")
	@NumlMetaInfo(uuid="Structures.uml@_g-YbcIhrEeK4s7QGypAJBA@Structures.uml@_g-YbcYhrEeK4s7QGypAJBA")
	public Brother getSurnameCarryingSon() {
		Brother result = this.surnameCarryingSon;
		
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=4434103865371600604l,opposite="surnameProviderHasSon_surnameCarryingSon",uuid="Structures.uml@_g-UxEIhrEeK4s7QGypAJBA")
	@NumlMetaInfo(uuid="Structures.uml@_g-YbcIhrEeK4s7QGypAJBA@Structures.uml@_g-UxEIhrEeK4s7QGypAJBA")
	public SurnameProvider getSurnameProvider() {
		SurnameProvider result = null;
		if ( this.surnameProvider==null ) {
			this.surnameProvider=new UiidBasedInterfaceValue();
		}
		result=(SurnameProvider)this.surnameProvider.getValue(persistence);
		return result;
	}
	
	public String getUid() {
		if ( this.uid==null || this.uid.trim().length()==0 ) {
			uid=UUID.randomUUID().toString();
		}
		return this.uid;
	}
	
	public int hashCode() {
		return getUid().hashCode();
	}
	
	public void init(CompositionNode owner) {
		this.z_internalAddToSurnameProvider((SurnameProvider)owner);
	}
	
	public void markDeleted() {
		setDeletedOn(new Date(System.currentTimeMillis()));
		setDeletedOn(new Date());
	}
	
	static public void mockAllInstances(Set newMocks) {
		mockedAllInstances=newMocks;
	}
	
	public void populateReferencesFromXml(Element xml, Map<String, Object> map) {
		NodeList propertyNodes = xml.getChildNodes();
		int i = 0;
		while ( i<propertyNodes.getLength() ) {
			Node currentPropertyNode = propertyNodes.item(i++);
		
		}
	}
	
	public void removeFromOwningObject() {
		this.markDeleted();
	}
	
	public void setDeletedOn(Date deletedOn) {
		this.deletedOn=deletedOn;
	}
	
	public void setId(Long id) {
		this.id=id;
	}
	
	public void setObjectVersion(int objectVersion) {
		this.objectVersion=objectVersion;
	}
	
	public void setUid(String newUid) {
		this.uid=newUid;
	}
	
	public String toXmlReferenceString() {
		return "<SurnameProviderHasSon uid=\""+getUid() + "\"/>";
	}
	
	public String toXmlString() {
		StringBuilder sb = new StringBuilder();
		sb.append("<SurnameProviderHasSon ");
		sb.append("classUuid=\"Structures.uml@_g-YbcIhrEeK4s7QGypAJBA\" ");
		sb.append("className=\"org.opaeum.test.SurnameProviderHasSon\" ");
		sb.append("uid=\"" + this.getUid() + "\" ");
		sb.append(">");
		sb.append("\n</SurnameProviderHasSon>");
		return sb.toString();
	}
	
	public void z_internalAddToSurnameCarryingSon(Brother surnameCarryingSon) {
		if ( surnameCarryingSon.equals(getSurnameCarryingSon()) ) {
			return;
		}
		this.surnameCarryingSon=surnameCarryingSon;
	}
	
	public void z_internalAddToSurnameProvider(SurnameProvider surnameProvider) {
		if ( surnameProvider.equals(getSurnameProvider()) ) {
			return;
		}
		if ( this.surnameProvider==null ) {
			this.surnameProvider=new UiidBasedInterfaceValue();
		}
		this.surnameProvider.setValue(surnameProvider);
	}
	
	public void z_internalRemoveFromSurnameCarryingSon(Brother surnameCarryingSon) {
		if ( getSurnameCarryingSon()!=null && surnameCarryingSon!=null && surnameCarryingSon.equals(getSurnameCarryingSon()) ) {
			this.surnameCarryingSon=null;
			this.surnameCarryingSon=null;
		}
	}
	
	public void z_internalRemoveFromSurnameProvider(SurnameProvider surnameProvider) {
		if ( getSurnameProvider()!=null && surnameProvider!=null && surnameProvider.equals(getSurnameProvider()) ) {
			this.surnameProvider.setValue(null);
		}
	}

}