package structuredbusiness;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.hibernate.annotations.AccessType;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.LazyCollection;
import org.opaeum.annotation.NumlMetaInfo;
import org.opaeum.runtime.bpm.organization.BusinessNetwork;
import org.opaeum.runtime.bpm.organization.BusinessNetworkFacilatatesCollaboration;
import org.opaeum.runtime.bpm.organization.IBusiness;
import org.opaeum.runtime.bpm.organization.IBusinessActor;
import org.opaeum.runtime.bpm.organization.IBusinessCollaboration;
import org.opaeum.runtime.domain.CancelledEvent;
import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.runtime.domain.HibernateEntity;
import org.opaeum.runtime.domain.IEventGenerator;
import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.runtime.domain.IntrospectionUtil;
import org.opaeum.runtime.domain.OutgoingEvent;
import org.opaeum.runtime.environment.Environment;
import org.opaeum.runtime.persistence.AbstractPersistence;
import org.opaeum.runtime.persistence.CmtPersistence;
import org.opeum.demo1.testmodel.Component1;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import structuredbusiness.util.Stdlib;
import structuredbusiness.util.StructuredbusinessFormatter;

@NumlMetaInfo(uuid="914890@_-VLbkE8VEeGA3PFuQY5w7QNakedBusinessCollaborationNakedBusinessCollaboration")
@Filter(name="noDeletedObjects")
@org.hibernate.annotations.Entity(dynamicUpdate=true)
@AccessType(	"field")
@Table(name="structuredbusiness")
@Inheritance(strategy=javax.persistence.InheritanceType.JOINED)
@Entity(name="Structuredbusiness")
@DiscriminatorColumn(discriminatorType=javax.persistence.DiscriminatorType.STRING,name="type_descriminator")
public class Structuredbusiness implements IPersistentObject, IEventGenerator, HibernateEntity, CompositionNode, IBusinessCollaboration, Serializable {
	@OneToOne(cascade=javax.persistence.CascadeType.ALL,fetch=javax.persistence.FetchType.LAZY)
	@JoinColumn(name="business_network_facilatates_collaboration_business_network_id",nullable=true)
	private BusinessNetworkFacilatatesCollaboration businessNetworkFacilatatesCollaboration_businessNetwork;
	@Transient
	private Set<CancelledEvent> cancelledEvents = new HashSet<CancelledEvent>();
	@LazyCollection(	org.hibernate.annotations.LazyCollectionOption.TRUE)
	@Filter(condition="deleted_on > current_timestamp",name="noDeletedObjects")
	@OneToMany(cascade=javax.persistence.CascadeType.ALL,fetch=javax.persistence.FetchType.LAZY,mappedBy="root",targetEntity=Component1.class)
	private Set<Component1> component1 = new HashSet<Component1>();
		// Initialise to 1000 from 1970
	@Temporal(	javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="deleted_on")
	private Date deletedOn = Stdlib.FUTURE;
	@Id
	@GeneratedValue(strategy=javax.persistence.GenerationType.TABLE)
	private Long id;
	static private Set<Structuredbusiness> mockedAllInstances;
	@Version
	@Column(name="object_version")
	private int objectVersion;
	@LazyCollection(	org.hibernate.annotations.LazyCollectionOption.TRUE)
	@Filter(condition="deleted_on > current_timestamp",name="noDeletedObjects")
	@OneToMany(cascade=javax.persistence.CascadeType.ALL,fetch=javax.persistence.FetchType.LAZY,mappedBy="root",targetEntity=Online_Customer.class)
	private Set<Online_Customer> online_Customer = new HashSet<Online_Customer>();
	@Transient
	private Set<OutgoingEvent> outgoingEvents = new HashSet<OutgoingEvent>();
	@Transient
	private AbstractPersistence persistence;
	static final private long serialVersionUID = 7737100568581358598l;
	private String uid;

	/** Default constructor for Structuredbusiness
	 */
	public Structuredbusiness() {
	}

	public void addAllToComponent1(Set<Component1> component1) {
		for ( Component1 o : component1 ) {
			addToComponent1(o);
		}
	}
	
	public void addAllToOnline_Customer(Set<Online_Customer> online_Customer) {
		for ( Online_Customer o : online_Customer ) {
			addToOnline_Customer(o);
		}
	}
	
	public void addToComponent1(Component1 component1) {
		if ( component1!=null ) {
			component1.z_internalRemoveFromRoot(component1.getRoot());
			component1.z_internalAddToRoot(this);
			z_internalAddToComponent1(component1);
		}
	}
	
	public void addToOnline_Customer(Online_Customer online_Customer) {
		if ( online_Customer!=null ) {
			online_Customer.z_internalRemoveFromRoot(online_Customer.getRoot());
			online_Customer.z_internalAddToRoot(this);
			z_internalAddToOnline_Customer(online_Customer);
		}
	}
	
	/** Call this method when you want to attach this object to the containment tree. Useful with transitive persistence
	 */
	public void addToOwningObject() {
	}
	
	static public Set<? extends Structuredbusiness> allInstances() {
		if ( mockedAllInstances==null ) {
			CmtPersistence session =org.opaeum.runtime.environment.Environment.getInstance().getComponent(CmtPersistence.class);
			return new HashSet(session.readAll(structuredbusiness.Structuredbusiness.class));
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
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("businessNetworkFacilatatesCollaboration_businessNetwork") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("4246171799000216537")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						BusinessNetworkFacilatatesCollaboration curVal;
						try {
							curVal=IntrospectionUtil.newInstance(((Element)currentPropertyValueNode).getAttribute("className"));
						} catch (Exception e) {
							curVal=Environment.getMetaInfoMap().newInstance(((Element)currentPropertyValueNode).getAttribute("classUuid"));
						}
						curVal.buildTreeFromXml((Element)currentPropertyValueNode,map);
						this.setBusinessNetworkFacilatatesCollaboration_businessNetwork(curVal);
						map.put(curVal.getUid(), curVal);
					}
				}
			}
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("online_Customer") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("6808203985768568000")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						Online_Customer curVal;
						try {
							curVal=IntrospectionUtil.newInstance(((Element)currentPropertyValueNode).getAttribute("className"));
						} catch (Exception e) {
							curVal=Environment.getMetaInfoMap().newInstance(((Element)currentPropertyValueNode).getAttribute("classUuid"));
						}
						curVal.buildTreeFromXml((Element)currentPropertyValueNode,map);
						this.addToOnline_Customer(curVal);
						map.put(curVal.getUid(), curVal);
					}
				}
			}
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("component1") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("2424007516471786755")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						Component1 curVal;
						try {
							curVal=IntrospectionUtil.newInstance(((Element)currentPropertyValueNode).getAttribute("className"));
						} catch (Exception e) {
							curVal=Environment.getMetaInfoMap().newInstance(((Element)currentPropertyValueNode).getAttribute("classUuid"));
						}
						curVal.buildTreeFromXml((Element)currentPropertyValueNode,map);
						this.addToComponent1(curVal);
						map.put(curVal.getUid(), curVal);
					}
				}
			}
		}
	}
	
	public void clearComponent1() {
		removeAllFromComponent1(getComponent1());
	}
	
	public void clearOnline_Customer() {
		removeAllFromOnline_Customer(getOnline_Customer());
	}
	
	public BusinessNetworkFacilatatesCollaboration createBusinessNetworkFacilatatesCollaboration_businessNetwork() {
		BusinessNetworkFacilatatesCollaboration newInstance= new BusinessNetworkFacilatatesCollaboration();
		newInstance.init(this);
		return newInstance;
	}
	
	public Component1 createComponent1() {
		Component1 newInstance= new Component1();
		newInstance.init(this);
		return newInstance;
	}
	
	public Online_Customer createOnline_Customer() {
		Online_Customer newInstance= new Online_Customer();
		newInstance.init(this);
		return newInstance;
	}
	
	public boolean equals(Object other) {
		if ( other instanceof Structuredbusiness ) {
			return other==this || ((Structuredbusiness)other).getUid().equals(this.getUid());
		}
		return false;
	}
	
	@NumlMetaInfo(uuid="252060@_Rj0oEFYkEeGJUqEGX7bKSg")
	public Set<IBusiness> getBusiness() {
		Set<IBusiness> result = new HashSet<IBusiness>();
		result.addAll(this.getComponent1());
		return result;
	}
	
	@NumlMetaInfo(uuid="252060@_pP5QQVYuEeGj5_I7bIwNoA")
	public Set<IBusinessActor> getBusinessActor() {
		Set<IBusinessActor> result = new HashSet<IBusinessActor>();
		result.addAll(this.getOnline_Customer());
		return result;
	}
	
	public BusinessNetwork getBusinessNetwork() {
		BusinessNetwork result = this.businessNetworkFacilatatesCollaboration_businessNetwork.getBusinessNetwork();
		return result;
	}
	
	@NumlMetaInfo(uuid="252060@_YJGvcVYjEeGJUqEGX7bKSg252060@_YJGvcFYjEeGJUqEGX7bKSg")
	public BusinessNetworkFacilatatesCollaboration getBusinessNetworkFacilatatesCollaboration_businessNetwork() {
		BusinessNetworkFacilatatesCollaboration result = this.businessNetworkFacilatatesCollaboration_businessNetwork;
		
		return result;
	}
	
	public BusinessNetworkFacilatatesCollaboration getBusinessNetworkFacilatatesCollaboration_businessNetworkFor(BusinessNetwork match) {
		if ( businessNetworkFacilatatesCollaboration_businessNetwork.getBusinessNetwork().equals(match) ) {
			return businessNetworkFacilatatesCollaboration_businessNetwork;
		} else {
			return null;
		}
	}
	
	public Set<CancelledEvent> getCancelledEvents() {
		return this.cancelledEvents;
	}
	
	public Set<Component1> getComponent1() {
		Set<Component1> result = this.component1;
		
		return result;
	}
	
	public Date getDeletedOn() {
		return this.deletedOn;
	}
	
	public Long getId() {
		return this.id;
	}
	
	public String getName() {
		return "Structuredbusiness["+getId()+"]";
	}
	
	public int getObjectVersion() {
		return this.objectVersion;
	}
	
	public Set<Online_Customer> getOnline_Customer() {
		Set<Online_Customer> result = this.online_Customer;
		
		return result;
	}
	
	public Set<OutgoingEvent> getOutgoingEvents() {
		return this.outgoingEvents;
	}
	
	public CompositionNode getOwningObject() {
		return null;
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
	}
	
	public void markDeleted() {
		if ( getBusinessNetwork()!=null ) {
			getBusinessNetwork().z_internalRemoveFromBusinessCollaboration(this);
		}
		for ( Online_Customer child : new ArrayList<Online_Customer>(getOnline_Customer()) ) {
			child.markDeleted();
		}
		for ( Component1 child : new ArrayList<Component1>(getComponent1()) ) {
			child.markDeleted();
		}
		setDeletedOn(new Date());
	}
	
	static public void mockAllInstances(Set<Structuredbusiness> newMocks) {
		mockedAllInstances=newMocks;
	}
	
	public void populateReferencesFromXml(Element xml, Map<String, Object> map) {
		NodeList propertyNodes = xml.getChildNodes();
		int i = 0;
		while ( i<propertyNodes.getLength() ) {
			Node currentPropertyNode = propertyNodes.item(i++);
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("businessNetworkFacilatatesCollaboration_businessNetwork") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("4246171799000216537")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						((BusinessNetworkFacilatatesCollaboration)map.get(((Element)currentPropertyValueNode).getAttribute("uid"))).populateReferencesFromXml((Element)currentPropertyValueNode, map);
					}
				}
			}
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("online_Customer") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("6808203985768568000")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						((Online_Customer)map.get(((Element)currentPropertyValueNode).getAttribute("uid"))).populateReferencesFromXml((Element)currentPropertyValueNode, map);
					}
				}
			}
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("component1") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("2424007516471786755")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						((Component1)map.get(((Element)currentPropertyValueNode).getAttribute("uid"))).populateReferencesFromXml((Element)currentPropertyValueNode, map);
					}
				}
			}
		}
	}
	
	public void removeAllFromComponent1(Set<Component1> component1) {
		Set<Component1> tmp = new HashSet<Component1>(component1);
		for ( Component1 o : tmp ) {
			removeFromComponent1(o);
		}
	}
	
	public void removeAllFromOnline_Customer(Set<Online_Customer> online_Customer) {
		Set<Online_Customer> tmp = new HashSet<Online_Customer>(online_Customer);
		for ( Online_Customer o : tmp ) {
			removeFromOnline_Customer(o);
		}
	}
	
	public void removeFromComponent1(Component1 component1) {
		if ( component1!=null ) {
			component1.z_internalRemoveFromRoot(this);
			z_internalRemoveFromComponent1(component1);
		}
	}
	
	public void removeFromOnline_Customer(Online_Customer online_Customer) {
		if ( online_Customer!=null ) {
			online_Customer.z_internalRemoveFromRoot(this);
			z_internalRemoveFromOnline_Customer(online_Customer);
		}
	}
	
	public void removeFromOwningObject() {
		this.markDeleted();
	}
	
	public void setBusinessNetwork(BusinessNetwork businessNetwork) {
		if ( this.getBusinessNetwork()!=null ) {
			this.getBusinessNetwork().z_internalRemoveFromBusinessCollaboration(this);
		}
		if ( businessNetwork!=null ) {
			businessNetwork.z_internalAddToBusinessCollaboration(this);
			this.z_internalAddToBusinessNetwork(businessNetwork);
		}
	}
	
	public void setBusinessNetworkFacilatatesCollaboration_businessNetwork(BusinessNetworkFacilatatesCollaboration businessNetworkFacilatatesCollaboration_businessNetwork) {
		BusinessNetworkFacilatatesCollaboration oldValue = this.getBusinessNetworkFacilatatesCollaboration_businessNetwork();
		if ( oldValue==null ) {
			if ( businessNetworkFacilatatesCollaboration_businessNetwork!=null ) {
				Structuredbusiness oldOther = (Structuredbusiness)businessNetworkFacilatatesCollaboration_businessNetwork.getBusinessCollaboration();
				businessNetworkFacilatatesCollaboration_businessNetwork.z_internalRemoveFromBusinessCollaboration(oldOther);
				if ( oldOther != null ) {
					oldOther.z_internalRemoveFromBusinessNetworkFacilatatesCollaboration_businessNetwork(businessNetworkFacilatatesCollaboration_businessNetwork);
				}
				businessNetworkFacilatatesCollaboration_businessNetwork.z_internalAddToBusinessCollaboration((Structuredbusiness)this);
			}
			this.z_internalAddToBusinessNetworkFacilatatesCollaboration_businessNetwork(businessNetworkFacilatatesCollaboration_businessNetwork);
		} else {
			if ( !oldValue.equals(businessNetworkFacilatatesCollaboration_businessNetwork) ) {
				oldValue.z_internalRemoveFromBusinessCollaboration(this);
				z_internalRemoveFromBusinessNetworkFacilatatesCollaboration_businessNetwork(oldValue);
				if ( businessNetworkFacilatatesCollaboration_businessNetwork!=null ) {
					Structuredbusiness oldOther = (Structuredbusiness)businessNetworkFacilatatesCollaboration_businessNetwork.getBusinessCollaboration();
					businessNetworkFacilatatesCollaboration_businessNetwork.z_internalRemoveFromBusinessCollaboration(oldOther);
					if ( oldOther != null ) {
						oldOther.z_internalRemoveFromBusinessNetworkFacilatatesCollaboration_businessNetwork(businessNetworkFacilatatesCollaboration_businessNetwork);
					}
					businessNetworkFacilatatesCollaboration_businessNetwork.z_internalAddToBusinessCollaboration((Structuredbusiness)this);
				}
				this.z_internalAddToBusinessNetworkFacilatatesCollaboration_businessNetwork(businessNetworkFacilatatesCollaboration_businessNetwork);
			}
		}
	}
	
	public void setCancelledEvents(Set<CancelledEvent> cancelledEvents) {
		this.cancelledEvents=cancelledEvents;
	}
	
	public void setComponent1(Set<Component1> component1) {
		this.clearComponent1();
		this.addAllToComponent1(component1);
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
	
	public void setOnline_Customer(Set<Online_Customer> online_Customer) {
		this.clearOnline_Customer();
		this.addAllToOnline_Customer(online_Customer);
	}
	
	public void setOutgoingEvents(Set<OutgoingEvent> outgoingEvents) {
		this.outgoingEvents=outgoingEvents;
	}
	
	public void setUid(String newUid) {
		this.uid=newUid;
	}
	
	public String toXmlReferenceString() {
		return "<Structuredbusiness uid=\""+getUid() + "\"/>";
	}
	
	public String toXmlString() {
		StringBuilder sb = new StringBuilder();
		sb.append("<Structuredbusiness ");
		sb.append("classUuid=\"914890@_-VLbkE8VEeGA3PFuQY5w7QNakedBusinessCollaborationNakedBusinessCollaboration\" ");
		sb.append("className=\"structuredbusiness.Structuredbusiness\" ");
		sb.append("uid=\"" + this.getUid() + "\" ");
		sb.append(">");
		if ( getBusinessNetworkFacilatatesCollaboration_businessNetwork()==null ) {
			sb.append("\n<businessNetworkFacilatatesCollaboration_businessNetwork/>");
		} else {
			sb.append("\n<businessNetworkFacilatatesCollaboration_businessNetwork propertyId=\"4246171799000216537\">");
			sb.append("\n" + getBusinessNetworkFacilatatesCollaboration_businessNetwork().toXmlString());
			sb.append("\n</businessNetworkFacilatatesCollaboration_businessNetwork>");
		}
		sb.append("\n<online_Customer propertyId=\"6808203985768568000\">");
		for ( Online_Customer online_Customer : getOnline_Customer() ) {
			sb.append("\n" + online_Customer.toXmlString());
		}
		sb.append("\n</online_Customer>");
		sb.append("\n<component1 propertyId=\"2424007516471786755\">");
		for ( Component1 component1 : getComponent1() ) {
			sb.append("\n" + component1.toXmlString());
		}
		sb.append("\n</component1>");
		sb.append("\n</Structuredbusiness>");
		return sb.toString();
	}
	
	public void z_internalAddToBusinessNetwork(BusinessNetwork businessNetwork) {
		BusinessNetworkFacilatatesCollaboration newOne = new BusinessNetworkFacilatatesCollaboration(this,businessNetwork);
		this.z_internalAddToBusinessNetworkFacilatatesCollaboration_businessNetwork(newOne);
		newOne.getBusinessNetwork().z_internalAddToBusinessNetworkFacilatatesCollaboration_businessCollaboration(newOne);
	}
	
	public void z_internalAddToBusinessNetworkFacilatatesCollaboration_businessNetwork(BusinessNetworkFacilatatesCollaboration val) {
		this.businessNetworkFacilatatesCollaboration_businessNetwork=val;
	}
	
	public void z_internalAddToComponent1(Component1 val) {
		this.component1.add(val);
	}
	
	public void z_internalAddToOnline_Customer(Online_Customer val) {
		this.online_Customer.add(val);
	}
	
	public void z_internalRemoveFromBusinessNetwork(BusinessNetwork businessNetwork) {
		this.businessNetworkFacilatatesCollaboration_businessNetwork.clear();
	}
	
	public void z_internalRemoveFromBusinessNetworkFacilatatesCollaboration_businessNetwork(BusinessNetworkFacilatatesCollaboration val) {
		if ( getBusinessNetworkFacilatatesCollaboration_businessNetwork()!=null && val!=null && val.equals(getBusinessNetworkFacilatatesCollaboration_businessNetwork()) ) {
			this.businessNetworkFacilatatesCollaboration_businessNetwork=null;
			this.businessNetworkFacilatatesCollaboration_businessNetwork=null;
		}
	}
	
	public void z_internalRemoveFromComponent1(Component1 val) {
		this.component1.remove(val);
	}
	
	public void z_internalRemoveFromOnline_Customer(Online_Customer val) {
		this.online_Customer.remove(val);
	}

}