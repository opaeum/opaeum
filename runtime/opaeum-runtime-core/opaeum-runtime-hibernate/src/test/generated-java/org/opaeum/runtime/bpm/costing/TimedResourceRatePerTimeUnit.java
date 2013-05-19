package org.opaeum.runtime.bpm.costing;

import java.io.Serializable;
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
import org.opaeum.runtime.bpm.util.Stdlib;
import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.runtime.domain.HibernateEntity;
import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.runtime.domain.InterfaceValueOwner;
import org.opaeum.runtime.domain.IntrospectionUtil;
import org.opaeum.runtime.persistence.AbstractPersistence;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

@NumlMetaInfo(applicationIdentifier="opaeum_hibernate_tests",uuid="252060@_dIQKEPjyEeGEN6Fz86uBYA")
@Filter(name="noDeletedObjects")
@org.hibernate.annotations.Entity(dynamicUpdate=true)
@AccessType(	"field")
@Table(name="timed_resource_rate_per_time_unit",schema="bpm",uniqueConstraints=
	@UniqueConstraint(columnNames={"rate_per_time_unit_id","deleted_on"}))
@Entity(name="TimedResourceRatePerTimeUnit")
public class TimedResourceRatePerTimeUnit implements InterfaceValueOwner, IPersistentObject, HibernateEntity, CompositionNode, Serializable {
	static private Map<String, Class> INTERFACE_FIELDS = new HashMap<String,Class>();
	static{
	INTERFACE_FIELDS.put("timedResource",ITimedResource.class);
	}
	
		// Initialise to 1000 from 1970
	@Temporal(	javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="deleted_on")
	private Date deletedOn = Stdlib.FUTURE;
	@TableGenerator(allocationSize=20,name="id_generator",pkColumnName="type",pkColumnValue="timed_resource_rate_per_time_unit",table="hi_value")
	@Id
	@GeneratedValue(generator="id_generator",strategy=javax.persistence.GenerationType.TABLE)
	private Long id;
	static private Set<? extends TimedResourceRatePerTimeUnit> mockedAllInstances;
	@Version
	@Column(name="object_version")
	private int objectVersion;
	@Transient
	private InternalHibernatePersistence persistence;
	@Index(columnNames="rate_per_time_unit_id",name="idx_timed_resource_rate_per_time_unit_rate_per_time_unit_id")
	@ManyToOne(cascade=javax.persistence.CascadeType.ALL,fetch=javax.persistence.FetchType.LAZY)
	@JoinColumn(name="rate_per_time_unit_id",nullable=true)
	protected RatePerTimeUnit ratePerTimeUnit;
	static final private long serialVersionUID = 8525454765666674009l;
	@Embedded
	@AttributeOverrides(	{
		@AttributeOverride(column=
			@Column(name="timed_resource"),name="identifier"),
		@AttributeOverride(column=
			@Column(name="timed_resource_type"),name="classIdentifier")})
	protected UiidBasedInterfaceValue timedResource;
	private String uid;

	/** Constructor for TimedResourceRatePerTimeUnit
	 * 
	 * @param end1 
	 * @param end2 
	 */
	public TimedResourceRatePerTimeUnit(ITimedResource end1, RatePerTimeUnit end2) {
		this.z_internalAddToTimedResource(end1);
		this.z_internalAddToRatePerTimeUnit(end2);
	}
	
	/** Constructor for TimedResourceRatePerTimeUnit
	 * 
	 * @param end2 
	 * @param end1 
	 */
	public TimedResourceRatePerTimeUnit(RatePerTimeUnit end2, ITimedResource end1) {
		this.z_internalAddToTimedResource(end1);
		this.z_internalAddToRatePerTimeUnit(end2);
	}
	
	/** Default constructor for TimedResourceRatePerTimeUnit
	 */
	public TimedResourceRatePerTimeUnit() {
	}

	/** Call this method when you want to attach this object to the containment tree. Useful with transitive persistence
	 */
	public void addToOwningObject() {
	}
	
	static public Set<? extends TimedResourceRatePerTimeUnit> allInstances(AbstractPersistence persistence) {
		if ( mockedAllInstances==null ) {
			return new HashSet(persistence.readAll(org.opaeum.runtime.bpm.costing.TimedResourceRatePerTimeUnit.class));
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
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("ratePerTimeUnit") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("2947938488506450892")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						RatePerTimeUnit curVal;
						try {
							curVal=IntrospectionUtil.newInstance(((Element)currentPropertyValueNode).getAttribute("className"));
						} catch (Exception e) {
							curVal=org.opaeum.runtime.bpm.util.OpaeumLibraryForBPMJavaMetaInfoMap.INSTANCE.newInstance(((Element)currentPropertyValueNode).getAttribute("classUuid"));
						}
						curVal.buildTreeFromXml((Element)currentPropertyValueNode,map);
						this.z_internalAddToRatePerTimeUnit(curVal);
						curVal.z_internalAddToTimedResourceRatePerTimeUnit_timedResource(this);
						map.put(curVal.getUid(), curVal);
					}
				}
			}
		}
	}
	
	public void clear() {
		this.z_internalRemoveFromRatePerTimeUnit(getRatePerTimeUnit());
		this.z_internalRemoveFromTimedResource(getTimedResource());
		markDeleted();
	}
	
	public void createComponents() {
	}
	
	public RatePerTimeUnit createRatePerTimeUnit() {
		RatePerTimeUnit newInstance= new RatePerTimeUnit();
		newInstance.init(this);
		return newInstance;
	}
	
	public boolean equals(Object other) {
		if ( other instanceof TimedResourceRatePerTimeUnit ) {
			return other==this || ((TimedResourceRatePerTimeUnit)other).getUid().equals(this.getUid());
		}
		return false;
	}
	
	public Date getDeletedOn() {
		return this.deletedOn;
	}
	
	public Class getFieldType(String fieldName) {
		Class result = INTERFACE_FIELDS.get(fieldName);
		
		return result;
	}
	
	public Long getId() {
		return this.id;
	}
	
	public String getName() {
		return "TimedResourceRatePerTimeUnit["+getId()+"]";
	}
	
	public int getObjectVersion() {
		return this.objectVersion;
	}
	
	public CompositionNode getOwningObject() {
		return getTimedResource();
	}
	
	@PropertyMetaInfo(constraints={},isComposite=true,opaeumId=2947938488506450892l,opposite="timedResourceRatePerTimeUnit_timedResource",uuid="252060@_dIQKFPjyEeGEN6Fz86uBYA")
	@NumlMetaInfo(uuid="252060@_dIQKEPjyEeGEN6Fz86uBYA@252060@_dIQKFPjyEeGEN6Fz86uBYA")
	public RatePerTimeUnit getRatePerTimeUnit() {
		RatePerTimeUnit result = this.ratePerTimeUnit;
		
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=6038224206912238882l,opposite="timedResourceRatePerTimeUnit_ratePerTimeUnit",uuid="252060@_dIQKEfjyEeGEN6Fz86uBYA")
	@NumlMetaInfo(uuid="252060@_dIQKEPjyEeGEN6Fz86uBYA@252060@_dIQKEfjyEeGEN6Fz86uBYA")
	public ITimedResource getTimedResource() {
		ITimedResource result = null;
		if ( this.timedResource==null ) {
			this.timedResource=new UiidBasedInterfaceValue();
		}
		result=(ITimedResource)this.timedResource.getValue(persistence);
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
		this.z_internalAddToTimedResource((ITimedResource)owner);
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
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("ratePerTimeUnit") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("2947938488506450892")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						((RatePerTimeUnit)map.get(((Element)currentPropertyValueNode).getAttribute("uid"))).populateReferencesFromXml((Element)currentPropertyValueNode, map);
					}
				}
			}
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
		return "<TimedResourceRatePerTimeUnit uid=\""+getUid() + "\"/>";
	}
	
	public String toXmlString() {
		StringBuilder sb = new StringBuilder();
		sb.append("<TimedResourceRatePerTimeUnit ");
		sb.append("classUuid=\"252060@_dIQKEPjyEeGEN6Fz86uBYA\" ");
		sb.append("className=\"org.opaeum.runtime.bpm.costing.TimedResourceRatePerTimeUnit\" ");
		sb.append("uid=\"" + this.getUid() + "\" ");
		sb.append(">");
		if ( getRatePerTimeUnit()==null ) {
			sb.append("\n<ratePerTimeUnit/>");
		} else {
			sb.append("\n<ratePerTimeUnit propertyId=\"2947938488506450892\">");
			sb.append("\n" + getRatePerTimeUnit().toXmlString());
			sb.append("\n</ratePerTimeUnit>");
		}
		sb.append("\n</TimedResourceRatePerTimeUnit>");
		return sb.toString();
	}
	
	public void z_internalAddToRatePerTimeUnit(RatePerTimeUnit ratePerTimeUnit) {
		if ( ratePerTimeUnit.equals(getRatePerTimeUnit()) ) {
			return;
		}
		this.ratePerTimeUnit=ratePerTimeUnit;
	}
	
	public void z_internalAddToTimedResource(ITimedResource timedResource) {
		if ( timedResource.equals(getTimedResource()) ) {
			return;
		}
		if ( this.timedResource==null ) {
			this.timedResource=new UiidBasedInterfaceValue();
		}
		this.timedResource.setValue(timedResource);
	}
	
	public void z_internalRemoveFromRatePerTimeUnit(RatePerTimeUnit ratePerTimeUnit) {
		if ( getRatePerTimeUnit()!=null && ratePerTimeUnit!=null && ratePerTimeUnit.equals(getRatePerTimeUnit()) ) {
			this.ratePerTimeUnit=null;
			this.ratePerTimeUnit=null;
		}
	}
	
	public void z_internalRemoveFromTimedResource(ITimedResource timedResource) {
		if ( getTimedResource()!=null && timedResource!=null && timedResource.equals(getTimedResource()) ) {
			this.timedResource.setValue(null);
		}
	}

}