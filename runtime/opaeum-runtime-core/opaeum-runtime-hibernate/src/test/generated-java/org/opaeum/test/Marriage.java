package org.opaeum.test;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

import org.hibernate.annotations.AccessType;
import org.hibernate.annotations.Filter;
import org.opaeum.annotation.NumlMetaInfo;
import org.opaeum.annotation.PropertyMetaInfo;
import org.opaeum.hibernate.domain.InternalHibernatePersistence;
import org.opaeum.hibernate.domain.UiidBasedInterfaceValue;
import org.opaeum.runtime.domain.CancelledEvent;
import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.runtime.domain.HibernateEntity;
import org.opaeum.runtime.domain.IEventGenerator;
import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.runtime.domain.IntrospectionUtil;
import org.opaeum.runtime.domain.OutgoingEvent;
import org.opaeum.runtime.environment.Environment;
import org.opaeum.runtime.persistence.AbstractPersistence;
import org.opaeum.runtime.strategy.DateStrategyFactory;
import org.opaeum.test.util.ModelFormatter;
import org.opaeum.test.util.Stdlib;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

@NumlMetaInfo(applicationIdentifier="opaeum_hibernate_tests",uuid="Structures.uml@_fz0rsIn-EeKv0PcdrJJtzg")
@Filter(name="noDeletedObjects")
@org.hibernate.annotations.Entity(dynamicUpdate=true)
@AccessType(	"field")
@Table(name="marriage",uniqueConstraints={
	@UniqueConstraint(columnNames={"surname_provider","deleted_on"}),
	@UniqueConstraint(columnNames={"spouse","deleted_on"})})
@Entity(name="Marriage")
public class Marriage implements IPersistentObject, IEventGenerator, HibernateEntity, CompositionNode, Serializable {
	@Transient
	transient private Set<CancelledEvent> cancelledEvents = new HashSet<CancelledEvent>();
		// Initialise to 1000 from 1970
	@Temporal(	javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="deleted_on")
	private Date deletedOn = Stdlib.FUTURE;
	@TableGenerator(allocationSize=20,name="id_generator",pkColumnName="type",pkColumnValue="marriage",table="hi_value")
	@Id
	@GeneratedValue(generator="id_generator",strategy=javax.persistence.GenerationType.TABLE)
	private Long id;
	@Temporal(	javax.persistence.TemporalType.DATE)
	@Column(name="initiation_date")
	protected Date initiationDate;
	static private Set<? extends Marriage> mockedAllInstances;
	@Version
	@Column(name="object_version")
	private int objectVersion;
	@Transient
	transient private Set<OutgoingEvent> outgoingEvents = new HashSet<OutgoingEvent>();
	@Transient
	private InternalHibernatePersistence persistence;
	static final private long serialVersionUID = 7663862196920060032l;
	@Embedded
	@AttributeOverrides(	{
		@AttributeOverride(column=
			@Column(name="spouse"),name="identifier"),
		@AttributeOverride(column=
			@Column(name="spouse_type"),name="classIdentifier")})
	protected UiidBasedInterfaceValue spouse;
	@Embedded
	@AttributeOverrides(	{
		@AttributeOverride(column=
			@Column(name="surname_provider"),name="identifier"),
		@AttributeOverride(column=
			@Column(name="surname_provider_type"),name="classIdentifier")})
	protected UiidBasedInterfaceValue surnameProvider;
	private String uid;

	/** Constructor for Marriage
	 * 
	 * @param end2 
	 * @param end1 
	 */
	public Marriage(Spouse end2, SurnameProvider end1) {
		this.z_internalAddToSurnameProvider(end1);
		this.z_internalAddToSpouse(end2);
	}
	
	/** Constructor for Marriage
	 * 
	 * @param end1 
	 * @param end2 
	 */
	public Marriage(SurnameProvider end1, Spouse end2) {
		this.z_internalAddToSurnameProvider(end1);
		this.z_internalAddToSpouse(end2);
	}
	
	/** Default constructor for Marriage
	 */
	public Marriage() {
	}

	/** Call this method when you want to attach this object to the containment tree. Useful with transitive persistence
	 */
	public void addToOwningObject() {
	}
	
	static public Set<? extends Marriage> allInstances(AbstractPersistence persistence) {
		if ( mockedAllInstances==null ) {
			return new HashSet(persistence.readAll(org.opaeum.test.Marriage.class));
		} else {
			return mockedAllInstances;
		}
	}
	
	public void buildTreeFromXml(Element xml, Map<String, Object> map) {
		setUid(xml.getAttribute("uid"));
		if ( xml.getAttribute("initiationDate").length()>0 ) {
			setInitiationDate(ModelFormatter.getInstance().parseDate(xml.getAttribute("initiationDate")));
		}
		NodeList propertyNodes = xml.getChildNodes();
		int i = 0;
		while ( i<propertyNodes.getLength() ) {
			Node currentPropertyNode = propertyNodes.item(i++);
		
		}
	}
	
	public void clear() {
		this.z_internalRemoveFromSpouse(getSpouse());
		this.z_internalRemoveFromSurnameProvider(getSurnameProvider());
		markDeleted();
	}
	
	public void copyShallowState(Marriage from, Marriage to) {
		to.setInitiationDate(from.getInitiationDate());
	}
	
	public void copyState(Marriage from, Marriage to) {
		to.setInitiationDate(from.getInitiationDate());
	}
	
	public void createComponents() {
	}
	
	public boolean equals(Object other) {
		if ( other instanceof Marriage ) {
			return other==this || ((Marriage)other).getUid().equals(this.getUid());
		}
		return false;
	}
	
	public Set<CancelledEvent> getCancelledEvents() {
		return this.cancelledEvents;
	}
	
	public Date getDeletedOn() {
		return this.deletedOn;
	}
	
	public Long getId() {
		return this.id;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=3770870485039737366l,strategyFactory=DateStrategyFactory.class,uuid="Structures.uml@_k3fZ4In-EeKv0PcdrJJtzg")
	@NumlMetaInfo(uuid="Structures.uml@_k3fZ4In-EeKv0PcdrJJtzg")
	public Date getInitiationDate() {
		Date result = this.initiationDate;
		
		return result;
	}
	
	public String getName() {
		return "Marriage["+getId()+"]";
	}
	
	public int getObjectVersion() {
		return this.objectVersion;
	}
	
	public Set<OutgoingEvent> getOutgoingEvents() {
		return this.outgoingEvents;
	}
	
	public CompositionNode getOwningObject() {
		return getSpouse();
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=7470639403134265280l,opposite="marriage_surnameProvider",uuid="Structures.uml@_fz0rsYn-EeKv0PcdrJJtzg")
	@NumlMetaInfo(uuid="Structures.uml@_fz0rsIn-EeKv0PcdrJJtzg@Structures.uml@_fz0rsYn-EeKv0PcdrJJtzg")
	public Spouse getSpouse() {
		Spouse result = null;
		if ( this.spouse==null ) {
			this.spouse=new UiidBasedInterfaceValue();
		}
		result=(Spouse)this.spouse.getValue(persistence);
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=4791530959909380894l,opposite="marriage_spouse",uuid="Structures.uml@_fz0rtIn-EeKv0PcdrJJtzg")
	@NumlMetaInfo(uuid="Structures.uml@_fz0rsIn-EeKv0PcdrJJtzg@Structures.uml@_fz0rtIn-EeKv0PcdrJJtzg")
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
		this.z_internalAddToSpouse((Spouse)owner);
	}
	
	public Marriage makeCopy() {
		Marriage result = new Marriage();
		copyState((Marriage)this,result);
		return result;
	}
	
	public Marriage makeShallowCopy() {
		Marriage result = new Marriage();
		copyShallowState((Marriage)this,result);
		result.setId(this.getId());
		return result;
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
	
	public void setCancelledEvents(Set<CancelledEvent> cancelledEvents) {
		this.cancelledEvents=cancelledEvents;
	}
	
	public void setDeletedOn(Date deletedOn) {
		this.deletedOn=deletedOn;
	}
	
	public void setId(Long id) {
		this.id=id;
	}
	
	public void setInitiationDate(Date initiationDate) {
		if ( initiationDate == null ) {
			this.z_internalRemoveFromInitiationDate(getInitiationDate());
		} else {
			this.z_internalAddToInitiationDate(initiationDate);
		}
	}
	
	public void setObjectVersion(int objectVersion) {
		this.objectVersion=objectVersion;
	}
	
	public void setOutgoingEvents(Set<OutgoingEvent> outgoingEvents) {
		this.outgoingEvents=outgoingEvents;
	}
	
	public void setUid(String newUid) {
		this.uid=newUid;
	}
	
	public String toXmlReferenceString() {
		return "<Marriage uid=\""+getUid() + "\"/>";
	}
	
	public String toXmlString() {
		StringBuilder sb = new StringBuilder();
		sb.append("<Marriage ");
		sb.append("classUuid=\"Structures.uml@_fz0rsIn-EeKv0PcdrJJtzg\" ");
		sb.append("className=\"org.opaeum.test.Marriage\" ");
		sb.append("uid=\"" + this.getUid() + "\" ");
		if ( getInitiationDate()!=null ) {
			sb.append("initiationDate=\""+ ModelFormatter.getInstance().formatDate(getInitiationDate())+"\" ");
		}
		sb.append(">");
		sb.append("\n</Marriage>");
		return sb.toString();
	}
	
	public void z_internalAddToInitiationDate(Date initiationDate) {
		if ( initiationDate.equals(getInitiationDate()) ) {
			return;
		}
		this.initiationDate=initiationDate;
	}
	
	public void z_internalAddToSpouse(Spouse spouse) {
		if ( spouse.equals(getSpouse()) ) {
			return;
		}
		if ( this.spouse==null ) {
			this.spouse=new UiidBasedInterfaceValue();
		}
		this.spouse.setValue(spouse);
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
	
	public void z_internalRemoveFromInitiationDate(Date initiationDate) {
		if ( getInitiationDate()!=null && initiationDate!=null && initiationDate.equals(getInitiationDate()) ) {
			this.initiationDate=null;
			this.initiationDate=null;
		}
	}
	
	public void z_internalRemoveFromSpouse(Spouse spouse) {
		if ( getSpouse()!=null && spouse!=null && spouse.equals(getSpouse()) ) {
			this.spouse.setValue(null);
		}
	}
	
	public void z_internalRemoveFromSurnameProvider(SurnameProvider surnameProvider) {
		if ( getSurnameProvider()!=null && surnameProvider!=null && surnameProvider.equals(getSurnameProvider()) ) {
			this.surnameProvider.setValue(null);
		}
	}

}