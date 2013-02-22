package org.opaeum.demo1.structuredbusiness.appliance;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.hibernate.annotations.AccessType;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.Index;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.Type;
import org.opaeum.annotation.NumlMetaInfo;
import org.opaeum.annotation.PropertyMetaInfo;
import org.opaeum.demo1.structuredbusiness.ApplianceDoctor;
import org.opaeum.demo1.structuredbusiness.util.Stdlib;
import org.opaeum.demo1.structuredbusiness.util.StructuredbusinessFormatter;
import org.opaeum.hibernate.domain.InternalHibernatePersistence;
import org.opaeum.runtime.bpm.businesscalendar.TimeOfDay;
import org.opaeum.runtime.domain.CancelledEvent;
import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.runtime.domain.HibernateEntity;
import org.opaeum.runtime.domain.IEventGenerator;
import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.runtime.domain.IntrospectionUtil;
import org.opaeum.runtime.domain.OutgoingEvent;
import org.opaeum.runtime.persistence.AbstractPersistence;
import org.opaeum.runtime.strategy.DateTimeStrategyFactory;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

@NumlMetaInfo(applicationIdentifier="demo1",uuid="914890@_nhV7IGCfEeG6xvYqJACneg")
@Filter(name="noDeletedObjects")
@org.hibernate.annotations.Entity(dynamicUpdate=true)
@AccessType(	"field")
@Table(name="appliance_model",schema="structuredbusiness")
@Entity(name="ApplianceModel")
public class ApplianceModel implements IPersistentObject, IEventGenerator, HibernateEntity, CompositionNode, Serializable {
	@Index(columnNames="appliance_doctor_id",name="idx_appliance_model_appliance_doctor_id")
	@ManyToOne(fetch=javax.persistence.FetchType.LAZY)
	@JoinColumn(name="appliance_doctor_id",nullable=true)
	protected ApplianceDoctor applianceDoctor;
	@Type(type="org.opaeum.demo1.structuredbusiness.appliance.ApplianceTypeResolver")
	@Column(name="appliance_type",nullable=true)
	protected ApplianceType applianceType;
	@Transient
	private Set<CancelledEvent> cancelledEvents = new HashSet<CancelledEvent>();
	@LazyCollection(	org.hibernate.annotations.LazyCollectionOption.TRUE)
	@Filter(condition="deleted_on > current_timestamp",name="noDeletedObjects")
	@OneToMany(cascade=javax.persistence.CascadeType.ALL,fetch=javax.persistence.FetchType.LAZY,mappedBy="appliance",targetEntity=ApplianceComponent.class)
	protected Set<ApplianceComponent> component = new HashSet<ApplianceComponent>();
		// Initialise to 1000 from 1970
	@Temporal(	javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="deleted_on")
	private Date deletedOn = Stdlib.FUTURE;
	@TableGenerator(allocationSize=20,name="id_generator",pkColumnName="type",pkColumnValue="appliance_model",table="hi_value")
	@Id
	@GeneratedValue(generator="id_generator",strategy=javax.persistence.GenerationType.TABLE)
	private Long id;
	static private Set<? extends ApplianceModel> mockedAllInstances;
	@Temporal(	javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="namaell")
	protected Date namaell;
	@Column(name="name")
	@Basic
	protected String name;
	@Version
	@Column(name="object_version")
	private int objectVersion;
	@Transient
	private Set<OutgoingEvent> outgoingEvents = new HashSet<OutgoingEvent>();
	@ManyToOne(cascade=javax.persistence.CascadeType.ALL,fetch=javax.persistence.FetchType.LAZY)
	@JoinColumn(name="part_numberl_id",nullable=true)
	protected TimeOfDay partNumberl;
	@Transient
	private InternalHibernatePersistence persistence;
	@Transient
	private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
	static final private long serialVersionUID = 9167031835761131135l;
	private String uid;
	@Type(type="org.opaeum.demo1.structuredbusiness.appliance.VendorResolver")
	@Column(name="vendor",nullable=true)
	protected Vendor vendor;

	/** This constructor is intended for easy initialization in unit tests
	 * 
	 * @param owningObject 
	 */
	public ApplianceModel(ApplianceDoctor owningObject) {
		init(owningObject);
		addToOwningObject();
	}
	
	/** Default constructor for ApplianceModel
	 */
	public ApplianceModel() {
	}

	public void addAllToComponent(Set<ApplianceComponent> component) {
		for ( ApplianceComponent o : component ) {
			addToComponent(o);
		}
	}
	
	public void addPropertyChangeListener(String property, PropertyChangeListener listener) {
		propertyChangeSupport.addPropertyChangeListener(property,listener);
	}
	
	public void addToComponent(ApplianceComponent component) {
		if ( component!=null ) {
			component.z_internalRemoveFromAppliance(component.getAppliance());
			component.z_internalAddToAppliance(this);
			z_internalAddToComponent(component);
		}
	}
	
	/** Call this method when you want to attach this object to the containment tree. Useful with transitive persistence
	 */
	public void addToOwningObject() {
		getApplianceDoctor().z_internalAddToApplianceModel((ApplianceModel)this);
	}
	
	static public Set<? extends ApplianceModel> allInstances(AbstractPersistence persistence) {
		if ( mockedAllInstances==null ) {
			return new HashSet(persistence.readAll(org.opaeum.demo1.structuredbusiness.appliance.ApplianceModel.class));
		} else {
			return mockedAllInstances;
		}
	}
	
	public void buildTreeFromXml(Element xml, Map<String, Object> map) {
		setUid(xml.getAttribute("uid"));
		if ( xml.getAttribute("name").length()>0 ) {
			setName(StructuredbusinessFormatter.getInstance().parseString(xml.getAttribute("name")));
		}
		if ( xml.getAttribute("vendor").length()>0 ) {
			setVendor(Vendor.valueOf(xml.getAttribute("vendor")));
		}
		if ( xml.getAttribute("applianceType").length()>0 ) {
			setApplianceType(ApplianceType.valueOf(xml.getAttribute("applianceType")));
		}
		if ( xml.getAttribute("namaell").length()>0 ) {
			setNamaell(StructuredbusinessFormatter.getInstance().parseDateTime(xml.getAttribute("namaell")));
		}
		NodeList propertyNodes = xml.getChildNodes();
		int i = 0;
		while ( i<propertyNodes.getLength() ) {
			Node currentPropertyNode = propertyNodes.item(i++);
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("component") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("6689744676322243651")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						ApplianceComponent curVal;
						try {
							curVal=IntrospectionUtil.newInstance(((Element)currentPropertyValueNode).getAttribute("className"));
						} catch (Exception e) {
							curVal=org.opaeum.demo1.structuredbusiness.util.StructuredbusinessJavaMetaInfoMap.INSTANCE.newInstance(((Element)currentPropertyValueNode).getAttribute("classUuid"));
						}
						curVal.buildTreeFromXml((Element)currentPropertyValueNode,map);
						this.addToComponent(curVal);
						map.put(curVal.getUid(), curVal);
					}
				}
			}
		}
	}
	
	public void clearComponent() {
		Set<ApplianceComponent> tmp = new HashSet<ApplianceComponent>(getComponent());
		for ( ApplianceComponent o : tmp ) {
			removeFromComponent(o);
		}
	}
	
	public void copyShallowState(ApplianceModel from, ApplianceModel to) {
		to.setName(from.getName());
		if ( from.getPartNumberl()!=null ) {
			to.setPartNumberl(from.getPartNumberl().makeShallowCopy());
		}
		to.setVendor(from.getVendor());
		to.setApplianceType(from.getApplianceType());
		to.setNamaell(from.getNamaell());
	}
	
	public void copyState(ApplianceModel from, ApplianceModel to) {
		for ( ApplianceComponent child : from.getComponent() ) {
			to.addToComponent(child.makeCopy());
		}
		to.setName(from.getName());
		if ( from.getPartNumberl()!=null ) {
			to.setPartNumberl(from.getPartNumberl().makeCopy());
		}
		to.setVendor(from.getVendor());
		to.setApplianceType(from.getApplianceType());
		to.setNamaell(from.getNamaell());
	}
	
	public ApplianceComponent createComponent() {
		ApplianceComponent newInstance= new ApplianceComponent();
		newInstance.init(this);
		return newInstance;
	}
	
	public void createComponents() {
	}
	
	public boolean equals(Object other) {
		if ( other instanceof ApplianceModel ) {
			return other==this || ((ApplianceModel)other).getUid().equals(this.getUid());
		}
		return false;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=1562482550345978655l,opposite="applianceModel",uuid="914890@_wtIMYZK_EeGnpuq6_ber_Q")
	@NumlMetaInfo(uuid="914890@_wtIMYZK_EeGnpuq6_ber_Q")
	public ApplianceDoctor getApplianceDoctor() {
		ApplianceDoctor result = this.applianceDoctor;
		
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=3828222531147292597l,opposite="applianceModel",uuid="914890@_Ek7dQJLAEeGnpuq6_ber_Q")
	@NumlMetaInfo(uuid="914890@_Ek7dQJLAEeGnpuq6_ber_Q")
	public ApplianceType getApplianceType() {
		ApplianceType result = this.applianceType;
		
		return result;
	}
	
	public Set<CancelledEvent> getCancelledEvents() {
		return this.cancelledEvents;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=true,opaeumId=6689744676322243651l,opposite="appliance",uuid="914890@_4_SYoHJ6EeG5aYCQXxe9BQ")
	@NumlMetaInfo(uuid="914890@_4_SYoHJ6EeG5aYCQXxe9BQ")
	public Set<ApplianceComponent> getComponent() {
		Set result = this.component;
		
		return result;
	}
	
	public Date getDeletedOn() {
		return this.deletedOn;
	}
	
	public Long getId() {
		return this.id;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=1857961114105821151l,strategyFactory=DateTimeStrategyFactory.class,uuid="914890@_ku-8YBknEeKmaYAq-i-IvQ")
	@NumlMetaInfo(uuid="914890@_ku-8YBknEeKmaYAq-i-IvQ")
	public Date getNamaell() {
		Date result = this.namaell;
		
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=8027636478953627954l,uuid="914890@_ht9n8HphEeGlh5y8zQdYBA")
	@NumlMetaInfo(uuid="914890@_ht9n8HphEeGlh5y8zQdYBA")
	public String getName() {
		String result = this.name;
		
		return result;
	}
	
	public int getObjectVersion() {
		return this.objectVersion;
	}
	
	public Set<OutgoingEvent> getOutgoingEvents() {
		return this.outgoingEvents;
	}
	
	public CompositionNode getOwningObject() {
		return getApplianceDoctor();
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=6341938639454514848l,uuid="914890@_Kid8QHpiEeGlh5y8zQdYBA")
	@NumlMetaInfo(uuid="914890@_Kid8QHpiEeGlh5y8zQdYBA")
	public TimeOfDay getPartNumberl() {
		TimeOfDay result = this.partNumberl;
		
		return result;
	}
	
	public String getUid() {
		if ( this.uid==null || this.uid.trim().length()==0 ) {
			uid=UUID.randomUUID().toString();
		}
		return this.uid;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=1278716437618707842l,opposite="dishWasherModel",uuid="914890@_7RIUYHsKEeGBGZr9IpIa3A")
	@NumlMetaInfo(uuid="914890@_7RIUYHsKEeGBGZr9IpIa3A")
	public Vendor getVendor() {
		Vendor result = this.vendor;
		
		return result;
	}
	
	public int hashCode() {
		return getUid().hashCode();
	}
	
	public void init(CompositionNode owner) {
		this.z_internalAddToApplianceDoctor((ApplianceDoctor)owner);
		createComponents();
	}
	
	public ApplianceModel makeCopy() {
		ApplianceModel result = new ApplianceModel();
		copyState((ApplianceModel)this,result);
		return result;
	}
	
	public ApplianceModel makeShallowCopy() {
		ApplianceModel result = new ApplianceModel();
		copyShallowState((ApplianceModel)this,result);
		result.setId(this.getId());
		return result;
	}
	
	public void markDeleted() {
		setDeletedOn(new Date(System.currentTimeMillis()));
		if ( getApplianceDoctor()!=null ) {
			getApplianceDoctor().z_internalRemoveFromApplianceModel(this);
		}
		for ( ApplianceComponent child : new ArrayList<ApplianceComponent>(getComponent()) ) {
			child.markDeleted();
		}
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
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("component") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("6689744676322243651")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						((ApplianceComponent)map.get(((Element)currentPropertyValueNode).getAttribute("uid"))).populateReferencesFromXml((Element)currentPropertyValueNode, map);
					}
				}
			}
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("partNumberl") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("6341938639454514848")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						setPartNumberl((TimeOfDay)map.get(((Element)currentPropertyValueNode).getAttribute("uid")));
					}
				}
			}
		}
	}
	
	public void removeAllFromComponent(Set<ApplianceComponent> component) {
		Set<ApplianceComponent> tmp = new HashSet<ApplianceComponent>(component);
		for ( ApplianceComponent o : tmp ) {
			removeFromComponent(o);
		}
	}
	
	public void removeFromComponent(ApplianceComponent component) {
		if ( component!=null ) {
			component.z_internalRemoveFromAppliance(this);
			z_internalRemoveFromComponent(component);
			component.markDeleted();
		}
	}
	
	public void removeFromOwningObject() {
		this.markDeleted();
	}
	
	public void removePropertyChangeListener(String property, PropertyChangeListener listener) {
		propertyChangeSupport.removePropertyChangeListener(property,listener);
	}
	
	public void setApplianceDoctor(ApplianceDoctor applianceDoctor) {
		propertyChangeSupport.firePropertyChange("applianceDoctor",getApplianceDoctor(),applianceDoctor);
		if ( this.getApplianceDoctor()!=null ) {
			this.getApplianceDoctor().z_internalRemoveFromApplianceModel(this);
		}
		if ( applianceDoctor!=null ) {
			applianceDoctor.z_internalAddToApplianceModel(this);
			this.z_internalAddToApplianceDoctor(applianceDoctor);
			setDeletedOn(Stdlib.FUTURE);
		} else {
			markDeleted();
		}
	}
	
	public void setApplianceType(ApplianceType applianceType) {
		propertyChangeSupport.firePropertyChange("applianceType",getApplianceType(),applianceType);
		this.z_internalAddToApplianceType(applianceType);
	}
	
	public void setCancelledEvents(Set<CancelledEvent> cancelledEvents) {
		this.cancelledEvents=cancelledEvents;
	}
	
	public void setComponent(Set<ApplianceComponent> component) {
		propertyChangeSupport.firePropertyChange("component",getComponent(),component);
		this.clearComponent();
		this.addAllToComponent(component);
	}
	
	public void setDeletedOn(Date deletedOn) {
		this.deletedOn=deletedOn;
	}
	
	public void setId(Long id) {
		this.id=id;
	}
	
	public void setNamaell(Date namaell) {
		propertyChangeSupport.firePropertyChange("namaell",getNamaell(),namaell);
		this.z_internalAddToNamaell(namaell);
	}
	
	public void setName(String name) {
		propertyChangeSupport.firePropertyChange("name",getName(),name);
		this.z_internalAddToName(name);
	}
	
	public void setObjectVersion(int objectVersion) {
		this.objectVersion=objectVersion;
	}
	
	public void setOutgoingEvents(Set<OutgoingEvent> outgoingEvents) {
		this.outgoingEvents=outgoingEvents;
	}
	
	public void setPartNumberl(TimeOfDay partNumberl) {
		propertyChangeSupport.firePropertyChange("partNumberl",getPartNumberl(),partNumberl);
		this.z_internalAddToPartNumberl(partNumberl);
	}
	
	public void setUid(String newUid) {
		this.uid=newUid;
	}
	
	public void setVendor(Vendor vendor) {
		propertyChangeSupport.firePropertyChange("vendor",getVendor(),vendor);
		this.z_internalAddToVendor(vendor);
	}
	
	public String toXmlReferenceString() {
		return "<ApplianceModel uid=\""+getUid() + "\"/>";
	}
	
	public String toXmlString() {
		StringBuilder sb = new StringBuilder();
		sb.append("<ApplianceModel ");
		sb.append("classUuid=\"914890@_nhV7IGCfEeG6xvYqJACneg\" ");
		sb.append("className=\"org.opaeum.demo1.structuredbusiness.appliance.ApplianceModel\" ");
		sb.append("uid=\"" + this.getUid() + "\" ");
		if ( getName()!=null ) {
			sb.append("name=\""+ StructuredbusinessFormatter.getInstance().formatString(getName())+"\" ");
		}
		if ( getVendor()!=null ) {
			sb.append("vendor=\""+ getVendor().name() + "\" ");
		}
		if ( getApplianceType()!=null ) {
			sb.append("applianceType=\""+ getApplianceType().name() + "\" ");
		}
		if ( getNamaell()!=null ) {
			sb.append("namaell=\""+ StructuredbusinessFormatter.getInstance().formatDateTime(getNamaell())+"\" ");
		}
		sb.append(">");
		sb.append("\n<component propertyId=\"6689744676322243651\">");
		for ( ApplianceComponent component : getComponent() ) {
			sb.append("\n" + component.toXmlString());
		}
		sb.append("\n</component>");
		if ( getPartNumberl()==null ) {
			sb.append("\n<partNumberl/>");
		} else {
			sb.append("\n<partNumberl propertyId=\"6341938639454514848\">");
			sb.append("\n" + getPartNumberl().toXmlReferenceString());
			sb.append("\n</partNumberl>");
		}
		sb.append("\n</ApplianceModel>");
		return sb.toString();
	}
	
	public void z_internalAddToApplianceDoctor(ApplianceDoctor applianceDoctor) {
		this.applianceDoctor=applianceDoctor;
	}
	
	public void z_internalAddToApplianceType(ApplianceType applianceType) {
		this.applianceType=applianceType;
	}
	
	public void z_internalAddToComponent(ApplianceComponent component) {
		this.component.add(component);
	}
	
	public void z_internalAddToNamaell(Date namaell) {
		this.namaell=namaell;
	}
	
	public void z_internalAddToName(String name) {
		this.name=name;
	}
	
	public void z_internalAddToPartNumberl(TimeOfDay partNumberl) {
		this.partNumberl=partNumberl;
	}
	
	public void z_internalAddToVendor(Vendor vendor) {
		this.vendor=vendor;
	}
	
	public void z_internalRemoveFromApplianceDoctor(ApplianceDoctor applianceDoctor) {
		if ( getApplianceDoctor()!=null && applianceDoctor!=null && applianceDoctor.equals(getApplianceDoctor()) ) {
			this.applianceDoctor=null;
			this.applianceDoctor=null;
		}
	}
	
	public void z_internalRemoveFromApplianceType(ApplianceType applianceType) {
		if ( getApplianceType()!=null && applianceType!=null && applianceType.equals(getApplianceType()) ) {
			this.applianceType=null;
			this.applianceType=null;
		}
	}
	
	public void z_internalRemoveFromComponent(ApplianceComponent component) {
		this.component.remove(component);
	}
	
	public void z_internalRemoveFromNamaell(Date namaell) {
		if ( getNamaell()!=null && namaell!=null && namaell.equals(getNamaell()) ) {
			this.namaell=null;
			this.namaell=null;
		}
	}
	
	public void z_internalRemoveFromName(String name) {
		if ( getName()!=null && name!=null && name.equals(getName()) ) {
			this.name=null;
			this.name=null;
		}
	}
	
	public void z_internalRemoveFromPartNumberl(TimeOfDay partNumberl) {
		if ( getPartNumberl()!=null && partNumberl!=null && partNumberl.equals(getPartNumberl()) ) {
			this.partNumberl=null;
			this.partNumberl=null;
		}
	}
	
	public void z_internalRemoveFromVendor(Vendor vendor) {
		if ( getVendor()!=null && vendor!=null && vendor.equals(getVendor()) ) {
			this.vendor=null;
			this.vendor=null;
		}
	}

}