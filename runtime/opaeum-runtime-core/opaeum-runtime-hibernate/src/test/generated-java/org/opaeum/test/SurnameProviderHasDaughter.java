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

@NumlMetaInfo(applicationIdentifier="opaeum_hibernate_tests",uuid="Structures.uml@_gtNy8IhrEeK4s7QGypAJBA")
@Filter(name="noDeletedObjects")
@org.hibernate.annotations.Entity(dynamicUpdate=true)
@AccessType(	"field")
@Table(name="surname_provider_has_daughter",uniqueConstraints=
	@UniqueConstraint(columnNames={"surname_carrying_daughter_id","deleted_on"}))
@Entity(name="SurnameProviderHasDaughter")
public class SurnameProviderHasDaughter implements IPersistentObject, HibernateEntity, CompositionNode, Serializable {
		// Initialise to 1000 from 1970
	@Temporal(	javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="deleted_on")
	private Date deletedOn = Stdlib.FUTURE;
	@TableGenerator(allocationSize=20,name="id_generator",pkColumnName="type",pkColumnValue="surname_provider_has_daughter",table="hi_value")
	@Id
	@GeneratedValue(generator="id_generator",strategy=javax.persistence.GenerationType.TABLE)
	private Long id;
	static private Set<? extends SurnameProviderHasDaughter> mockedAllInstances;
	@Version
	@Column(name="object_version")
	private int objectVersion;
	@Transient
	private InternalHibernatePersistence persistence;
	static final private long serialVersionUID = 7081196718495376073l;
	@Index(columnNames="surname_carrying_daughter_id",name="idx_surname_provider_has_daughter_surname_carrying_daughter_id")
	@ManyToOne(fetch=javax.persistence.FetchType.LAZY)
	@JoinColumn(name="surname_carrying_daughter_id",nullable=true)
	protected Sister surnameCarryingDaughter;
	@Embedded
	@AttributeOverrides(	{
		@AttributeOverride(column=
			@Column(name="surname_provider"),name="identifier"),
		@AttributeOverride(column=
			@Column(name="surname_provider_type"),name="classIdentifier")})
	protected UiidBasedInterfaceValue surnameProvider;
	private String uid;
	private String z_keyOfSurnameCarryingDaughterOnSurnameProvider;

	/** Constructor for SurnameProviderHasDaughter
	 * 
	 * @param end1 
	 * @param end2 
	 */
	public SurnameProviderHasDaughter(Sister end1, SurnameProvider end2) {
		this.z_internalAddToSurnameCarryingDaughter(end1);
		this.z_internalAddToSurnameProvider(end2);
	}
	
	/** Constructor for SurnameProviderHasDaughter
	 * 
	 * @param end2 
	 * @param end1 
	 */
	public SurnameProviderHasDaughter(SurnameProvider end2, Sister end1) {
		this.z_internalAddToSurnameCarryingDaughter(end1);
		this.z_internalAddToSurnameProvider(end2);
	}
	
	/** Default constructor for SurnameProviderHasDaughter
	 */
	public SurnameProviderHasDaughter() {
	}

	/** Call this method when you want to attach this object to the containment tree. Useful with transitive persistence
	 */
	public void addToOwningObject() {
	}
	
	static public Set<? extends SurnameProviderHasDaughter> allInstances(AbstractPersistence persistence) {
		if ( mockedAllInstances==null ) {
			return new HashSet(persistence.readAll(org.opaeum.test.SurnameProviderHasDaughter.class));
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
		this.z_internalRemoveFromSurnameCarryingDaughter(getSurnameCarryingDaughter());
		markDeleted();
	}
	
	public void createComponents() {
	}
	
	public boolean equals(Object other) {
		if ( other instanceof SurnameProviderHasDaughter ) {
			return other==this || ((SurnameProviderHasDaughter)other).getUid().equals(this.getUid());
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
		return "SurnameProviderHasDaughter["+getId()+"]";
	}
	
	public int getObjectVersion() {
		return this.objectVersion;
	}
	
	public CompositionNode getOwningObject() {
		return getSurnameProvider();
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=7054433913514256040l,opposite="surnameProviderHasDaughter_surnameProvider",uuid="Structures.uml@_gtNy8YhrEeK4s7QGypAJBA")
	@NumlMetaInfo(uuid="Structures.uml@_gtNy8IhrEeK4s7QGypAJBA@Structures.uml@_gtNy8YhrEeK4s7QGypAJBA")
	public Sister getSurnameCarryingDaughter() {
		Sister result = this.surnameCarryingDaughter;
		
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=8484398849814184104l,opposite="surnameProviderHasDaughter_surnameCarryingDaughter",uuid="Structures.uml@_gtKIkIhrEeK4s7QGypAJBA")
	@NumlMetaInfo(uuid="Structures.uml@_gtNy8IhrEeK4s7QGypAJBA@Structures.uml@_gtKIkIhrEeK4s7QGypAJBA")
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
	
	public String getZ_keyOfSurnameCarryingDaughterOnSurnameProvider() {
		return this.z_keyOfSurnameCarryingDaughterOnSurnameProvider;
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
	
	public void setZ_keyOfSurnameCarryingDaughterOnSurnameProvider(String z_keyOfSurnameCarryingDaughterOnSurnameProvider) {
		this.z_keyOfSurnameCarryingDaughterOnSurnameProvider=z_keyOfSurnameCarryingDaughterOnSurnameProvider;
	}
	
	public String toXmlReferenceString() {
		return "<SurnameProviderHasDaughter uid=\""+getUid() + "\"/>";
	}
	
	public String toXmlString() {
		StringBuilder sb = new StringBuilder();
		sb.append("<SurnameProviderHasDaughter ");
		sb.append("classUuid=\"Structures.uml@_gtNy8IhrEeK4s7QGypAJBA\" ");
		sb.append("className=\"org.opaeum.test.SurnameProviderHasDaughter\" ");
		sb.append("uid=\"" + this.getUid() + "\" ");
		sb.append(">");
		sb.append("\n</SurnameProviderHasDaughter>");
		return sb.toString();
	}
	
	public void z_internalAddToSurnameCarryingDaughter(Sister surnameCarryingDaughter) {
		if ( surnameCarryingDaughter.equals(getSurnameCarryingDaughter()) ) {
			return;
		}
		this.surnameCarryingDaughter=surnameCarryingDaughter;
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
	
	public void z_internalRemoveFromSurnameCarryingDaughter(Sister surnameCarryingDaughter) {
		if ( getSurnameCarryingDaughter()!=null && surnameCarryingDaughter!=null && surnameCarryingDaughter.equals(getSurnameCarryingDaughter()) ) {
			this.surnameCarryingDaughter=null;
			this.surnameCarryingDaughter=null;
		}
	}
	
	public void z_internalRemoveFromSurnameProvider(SurnameProvider surnameProvider) {
		if ( getSurnameProvider()!=null && surnameProvider!=null && surnameProvider.equals(getSurnameProvider()) ) {
			this.surnameProvider.setValue(null);
		}
	}

}