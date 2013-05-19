package bpmmodel.custom;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.AccessType;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.Index;
import org.opaeum.annotation.NumlMetaInfo;
import org.opaeum.annotation.PropertyMetaInfo;
import org.opaeum.hibernate.domain.InternalHibernatePersistence;
import org.opaeum.runtime.bpm.businesscalendar.BusinessCalendar;
import org.opaeum.runtime.bpm.contact.OrganizationEMailAddress;
import org.opaeum.runtime.bpm.contact.OrganizationPhoneNumber;
import org.opaeum.runtime.bpm.organization.BusinessNetwork;
import org.opaeum.runtime.bpm.organization.OrganizationAsBusinessComponent;
import org.opaeum.runtime.bpm.organization.OrganizationFullfillsActorRole;
import org.opaeum.runtime.bpm.organization.OrganizationNode;
import org.opaeum.runtime.domain.CancelledEvent;
import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.runtime.domain.HibernateEntity;
import org.opaeum.runtime.domain.IEventGenerator;
import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.runtime.domain.IntrospectionUtil;
import org.opaeum.runtime.domain.OutgoingEvent;
import org.opaeum.runtime.persistence.AbstractPersistence;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import bpmmodel.util.BpmmodelFormatter;
import bpmmodel.util.Stdlib;

@NumlMetaInfo(applicationIdentifier="opaeum_hibernate_tests",uuid="bpm.uml@_2nyj8MB-EeKVzPWAF6TUwg")
@Filter(name="noDeletedObjects")
@org.hibernate.annotations.Entity(dynamicUpdate=true)
@AccessType(	"field")
@Table(name="custom_organization_node",uniqueConstraints=
	@UniqueConstraint(columnNames={"custom_business_network_id","name","deleted_on"}))
@NamedQueries(value=
	@NamedQuery(name="QueryCustomOrganizationNodeWithNameForCustomBusinessNetwork",query="from CustomOrganizationNode a where a.customBusinessNetwork = :customBusinessNetwork and a.name = :name"))
@Entity(name="CustomOrganizationNode")
@DiscriminatorValue(	"custom_organization_node")
public class CustomOrganizationNode extends OrganizationNode implements IPersistentObject, IEventGenerator, HibernateEntity, CompositionNode, Serializable {
	@Transient
	private Set<CancelledEvent> cancelledEvents = new HashSet<CancelledEvent>();
	@Index(columnNames="custom_business_network_id",name="idx_custom_organization_node_custom_business_network_id")
	@ManyToOne(fetch=javax.persistence.FetchType.LAZY)
	@JoinColumn(name="custom_business_network_id",nullable=true)
	protected CustomBusinessNetwork customBusinessNetwork;
		// Initialise to 1000 from 1970
	@Temporal(	javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="deleted_on")
	private Date deletedOn = Stdlib.FUTURE;
	static private Set<? extends CustomOrganizationNode> mockedAllInstances;
	@Column(name="name")
	@Basic
	protected String name;
	@Transient
	private Set<OutgoingEvent> outgoingEvents = new HashSet<OutgoingEvent>();
	@Transient
	private InternalHibernatePersistence persistence;
	static final private long serialVersionUID = 4554789667515797590l;
	@Column(name="key_in_c_o_n_on_c_b_n")
	private String z_keyOfCustomOrganizationNodeOnCustomBusinessNetwork;

	/** This constructor is intended for easy initialization in unit tests
	 * 
	 * @param owningObject 
	 * @param name 
	 */
	public CustomOrganizationNode(CustomBusinessNetwork owningObject, String name) {
		setName(name);
		init(owningObject);
		addToOwningObject();
	}
	
	/** Default constructor for CustomOrganizationNode
	 */
	public CustomOrganizationNode() {
	}

	/** Call this method when you want to attach this object to the containment tree. Useful with transitive persistence
	 */
	public void addToOwningObject() {
		if ( getName()==null ) {
			throw new IllegalStateException("The qualifying property 'name' must be set before adding a value to 'customBusinessNetwork'");
		}
		getCustomBusinessNetwork().z_internalAddToCustomOrganizationNode(this.getName(),(CustomOrganizationNode)this);
	}
	
	static public Set<? extends CustomOrganizationNode> allInstances(AbstractPersistence persistence) {
		if ( mockedAllInstances==null ) {
			return new HashSet(persistence.readAll(bpmmodel.custom.CustomOrganizationNode.class));
		} else {
			return mockedAllInstances;
		}
	}
	
	public void buildTreeFromXml(Element xml, Map<String, Object> map) {
		setUid(xml.getAttribute("uid"));
		if ( xml.getAttribute("name").length()>0 ) {
			setName(BpmmodelFormatter.getInstance().parseString(xml.getAttribute("name")));
		}
		NodeList propertyNodes = xml.getChildNodes();
		int i = 0;
		while ( i<propertyNodes.getLength() ) {
			Node currentPropertyNode = propertyNodes.item(i++);
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("phoneNumber") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("1861213202254517122")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						OrganizationPhoneNumber curVal;
						try {
							curVal=IntrospectionUtil.newInstance(((Element)currentPropertyValueNode).getAttribute("className"));
						} catch (Exception e) {
							curVal=org.opaeum.runtime.bpm.util.OpaeumLibraryForBPMJavaMetaInfoMap.INSTANCE.newInstance(((Element)currentPropertyValueNode).getAttribute("classUuid"));
						}
						curVal.buildTreeFromXml((Element)currentPropertyValueNode,map);
						this.z_internalAddToPhoneNumber(curVal.getType(),curVal);
						curVal.z_internalAddToOrganization(this);
						map.put(curVal.getUid(), curVal);
					}
				}
			}
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("eMailAddress") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("6276678134555712740")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						OrganizationEMailAddress curVal;
						try {
							curVal=IntrospectionUtil.newInstance(((Element)currentPropertyValueNode).getAttribute("className"));
						} catch (Exception e) {
							curVal=org.opaeum.runtime.bpm.util.OpaeumLibraryForBPMJavaMetaInfoMap.INSTANCE.newInstance(((Element)currentPropertyValueNode).getAttribute("classUuid"));
						}
						curVal.buildTreeFromXml((Element)currentPropertyValueNode,map);
						this.z_internalAddToEMailAddress(curVal.getType(),curVal);
						curVal.z_internalAddToOrganization(this);
						map.put(curVal.getUid(), curVal);
					}
				}
			}
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("businessCalendar") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("2759918346397932051")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						BusinessCalendar curVal;
						try {
							curVal=IntrospectionUtil.newInstance(((Element)currentPropertyValueNode).getAttribute("className"));
						} catch (Exception e) {
							curVal=org.opaeum.runtime.bpm.util.OpaeumLibraryForBPMJavaMetaInfoMap.INSTANCE.newInstance(((Element)currentPropertyValueNode).getAttribute("classUuid"));
						}
						curVal.buildTreeFromXml((Element)currentPropertyValueNode,map);
						this.z_internalAddToBusinessCalendar(curVal);
						curVal.z_internalAddToOrganization(this);
						map.put(curVal.getUid(), curVal);
					}
				}
			}
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("organizationFullfillsActorRole_businessActor") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("72028596279487881")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						OrganizationFullfillsActorRole curVal;
						try {
							curVal=IntrospectionUtil.newInstance(((Element)currentPropertyValueNode).getAttribute("className"));
						} catch (Exception e) {
							curVal=org.opaeum.runtime.bpm.util.OpaeumLibraryForBPMJavaMetaInfoMap.INSTANCE.newInstance(((Element)currentPropertyValueNode).getAttribute("classUuid"));
						}
						curVal.buildTreeFromXml((Element)currentPropertyValueNode,map);
						this.z_internalAddToOrganizationFullfillsActorRole_businessActor(curVal);
						curVal.z_internalAddToOrganization(this);
						map.put(curVal.getUid(), curVal);
					}
				}
			}
		}
	}
	
	public void copyShallowState(CustomOrganizationNode from, CustomOrganizationNode to) {
		if ( from.getBusinessCalendar()!=null ) {
			to.z_internalAddToBusinessCalendar(from.getBusinessCalendar().makeShallowCopy());
		}
		to.setName(from.getName());
	}
	
	public void copyState(CustomOrganizationNode from, CustomOrganizationNode to) {
		for ( OrganizationPhoneNumber child : from.getPhoneNumber() ) {
			to.addToPhoneNumber(child.getType(),child.makeCopy());
		}
		for ( OrganizationEMailAddress child : from.getEMailAddress() ) {
			to.addToEMailAddress(child.getType(),child.makeCopy());
		}
		if ( from.getBusinessCalendar()!=null ) {
			to.z_internalAddToBusinessCalendar(from.getBusinessCalendar().makeCopy());
		}
		to.setName(from.getName());
	}
	
	public void createComponents() {
		super.createComponents();
	}
	
	public boolean equals(Object other) {
		if ( other instanceof CustomOrganizationNode ) {
			return other==this || ((CustomOrganizationNode)other).getUid().equals(this.getUid());
		}
		return false;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=4954312663461917749l,opposite="organization",uuid="252060@_4uxKkUvREeGmqIr8YsFD4g")
	@NumlMetaInfo(uuid="252060@_4uxKkUvREeGmqIr8YsFD4g")
	public BusinessNetwork getBusinessNetwork() {
		BusinessNetwork result = getCustomBusinessNetwork();
		
		return result;
	}
	
	public Set<CancelledEvent> getCancelledEvents() {
		return this.cancelledEvents;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=6305775667469216488l,opposite="customOrganizationNode",uuid="bpm.uml@_9eFGUcB-EeKVzPWAF6TUwg")
	@NumlMetaInfo(uuid="bpm.uml@_9eFGUcB-EeKVzPWAF6TUwg")
	public CustomBusinessNetwork getCustomBusinessNetwork() {
		CustomBusinessNetwork result = this.customBusinessNetwork;
		
		return result;
	}
	
	public Date getDeletedOn() {
		return this.deletedOn;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=2348161042597649168l,uuid="bpm.uml@_OM83YMB_EeKVzPWAF6TUwg")
	@NumlMetaInfo(uuid="bpm.uml@_OM83YMB_EeKVzPWAF6TUwg")
	public String getName() {
		String result = this.name;
		
		return result;
	}
	
	public Set<OutgoingEvent> getOutgoingEvents() {
		return this.outgoingEvents;
	}
	
	public CompositionNode getOwningObject() {
		return getCustomBusinessNetwork();
	}
	
	public String getZ_keyOfCustomOrganizationNodeOnCustomBusinessNetwork() {
		return this.z_keyOfCustomOrganizationNodeOnCustomBusinessNetwork;
	}
	
	public int hashCode() {
		return getUid().hashCode();
	}
	
	public void init(CompositionNode owner) {
		super.init(owner);
		this.z_internalAddToCustomBusinessNetwork((CustomBusinessNetwork)owner);
	}
	
	public CustomOrganizationNode makeCopy() {
		CustomOrganizationNode result = new CustomOrganizationNode();
		copyState((CustomOrganizationNode)this,result);
		return result;
	}
	
	public CustomOrganizationNode makeShallowCopy() {
		CustomOrganizationNode result = new CustomOrganizationNode();
		copyShallowState((CustomOrganizationNode)this,result);
		result.setId(this.getId());
		return result;
	}
	
	public void markDeleted() {
		super.markDeleted();
		for ( OrganizationAsBusinessComponent link : new HashSet<OrganizationAsBusinessComponent>(getOrganizationAsBusinessComponent_businessComponent()) ) {
			link.getBusinessComponent().z_internalRemoveFromOrganizationAsBusinessComponent_representedOrganization(link);
		}
		for ( OrganizationFullfillsActorRole link : new HashSet<OrganizationFullfillsActorRole>(getOrganizationFullfillsActorRole_businessActor()) ) {
			link.getBusinessActor().z_internalRemoveFromOrganizationFullfillsActorRole_organization(link);
		}
		if ( getBusinessNetwork()!=null ) {
			getBusinessNetwork().z_internalRemoveFromOrganization(this.getName(),this);
		}
		if ( getCustomBusinessNetwork()!=null ) {
			getCustomBusinessNetwork().z_internalRemoveFromCustomOrganizationNode(this.getName(),this);
		}
		for ( OrganizationPhoneNumber child : new ArrayList<OrganizationPhoneNumber>(getPhoneNumber()) ) {
			child.markDeleted();
		}
		for ( OrganizationEMailAddress child : new ArrayList<OrganizationEMailAddress>(getEMailAddress()) ) {
			child.markDeleted();
		}
		if ( getBusinessCalendar()!=null ) {
			getBusinessCalendar().markDeleted();
		}
		for ( OrganizationFullfillsActorRole child : new ArrayList<OrganizationFullfillsActorRole>(getOrganizationFullfillsActorRole_businessActor()) ) {
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
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("phoneNumber") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("1861213202254517122")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						((OrganizationPhoneNumber)map.get(((Element)currentPropertyValueNode).getAttribute("uid"))).populateReferencesFromXml((Element)currentPropertyValueNode, map);
					}
				}
			}
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("eMailAddress") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("6276678134555712740")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						((OrganizationEMailAddress)map.get(((Element)currentPropertyValueNode).getAttribute("uid"))).populateReferencesFromXml((Element)currentPropertyValueNode, map);
					}
				}
			}
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("businessCalendar") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("2759918346397932051")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						((BusinessCalendar)map.get(((Element)currentPropertyValueNode).getAttribute("uid"))).populateReferencesFromXml((Element)currentPropertyValueNode, map);
					}
				}
			}
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("organizationFullfillsActorRole_businessActor") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("72028596279487881")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						((OrganizationFullfillsActorRole)map.get(((Element)currentPropertyValueNode).getAttribute("uid"))).populateReferencesFromXml((Element)currentPropertyValueNode, map);
					}
				}
			}
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("organizationAsBusinessComponent_businessComponent") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("2861752063599373180")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						OrganizationAsBusinessComponent organizationAsBusinessComponent_businessComponent = (OrganizationAsBusinessComponent)map.get(((Element)currentPropertyValueNode).getAttribute("uid"));
						if ( organizationAsBusinessComponent_businessComponent!=null ) {
							z_internalAddToOrganizationAsBusinessComponent_businessComponent(organizationAsBusinessComponent_businessComponent);
							organizationAsBusinessComponent_businessComponent.z_internalAddToRepresentedOrganization(this);
						}
					}
				}
			}
		}
	}
	
	public void removeFromOwningObject() {
		this.markDeleted();
	}
	
	public void setBusinessNetwork(BusinessNetwork businessNetwork) {
		if ( this.getBusinessNetwork()!=null ) {
			this.getBusinessNetwork().z_internalRemoveFromOrganization(this.getName(),this);
		}
		if ( businessNetwork == null ) {
			this.z_internalRemoveFromBusinessNetwork(this.getBusinessNetwork());
		} else {
			if ( getName()==null ) {
				throw new IllegalStateException("The qualifying property 'name' must be set before adding a value to 'businessNetwork'");
			}
			this.z_internalAddToBusinessNetwork(businessNetwork);
		}
		if ( businessNetwork!=null ) {
			businessNetwork.z_internalAddToOrganization(this.getName(),this);
		}
	}
	
	public void setCancelledEvents(Set<CancelledEvent> cancelledEvents) {
		this.cancelledEvents=cancelledEvents;
	}
	
	public void setCustomBusinessNetwork(CustomBusinessNetwork customBusinessNetwork) {
		if ( this.getCustomBusinessNetwork()!=null ) {
			this.getCustomBusinessNetwork().z_internalRemoveFromCustomOrganizationNode(this.getName(),this);
		}
		if ( customBusinessNetwork == null ) {
			this.z_internalRemoveFromCustomBusinessNetwork(this.getCustomBusinessNetwork());
		} else {
			if ( getName()==null ) {
				throw new IllegalStateException("The qualifying property 'name' must be set before adding a value to 'customBusinessNetwork'");
			}
			this.z_internalAddToCustomBusinessNetwork(customBusinessNetwork);
		}
		if ( customBusinessNetwork!=null ) {
			customBusinessNetwork.z_internalAddToCustomOrganizationNode(this.getName(),this);
			setDeletedOn(Stdlib.FUTURE);
		} else {
			markDeleted();
		}
	}
	
	public void setDeletedOn(Date deletedOn) {
		this.deletedOn=deletedOn;
		super.setDeletedOn(deletedOn);
	}
	
	public void setName(String name) {
		if ( getBusinessNetwork()!=null && getName()!=null ) {
			getBusinessNetwork().z_internalRemoveFromOrganization(this.getName(),this);
		}
		if ( getCustomBusinessNetwork()!=null && getName()!=null ) {
			getCustomBusinessNetwork().z_internalRemoveFromCustomOrganizationNode(this.getName(),this);
		}
		if ( name == null ) {
			this.z_internalRemoveFromName(getName());
		} else {
			this.z_internalAddToName(name);
		}
		if ( getBusinessNetwork()!=null && getName()!=null ) {
			getBusinessNetwork().z_internalAddToOrganization(this.getName(),this);
		}
		if ( getCustomBusinessNetwork()!=null && getName()!=null ) {
			getCustomBusinessNetwork().z_internalAddToCustomOrganizationNode(this.getName(),this);
		}
	}
	
	public void setOutgoingEvents(Set<OutgoingEvent> outgoingEvents) {
		this.outgoingEvents=outgoingEvents;
	}
	
	public void setZ_keyOfCustomOrganizationNodeOnCustomBusinessNetwork(String z_keyOfCustomOrganizationNodeOnCustomBusinessNetwork) {
		this.z_keyOfCustomOrganizationNodeOnCustomBusinessNetwork=z_keyOfCustomOrganizationNodeOnCustomBusinessNetwork;
	}
	
	public String toXmlReferenceString() {
		return "<CustomOrganizationNode uid=\""+getUid() + "\"/>";
	}
	
	public String toXmlString() {
		StringBuilder sb = new StringBuilder();
		sb.append("<CustomOrganizationNode ");
		sb.append("classUuid=\"bpm.uml@_2nyj8MB-EeKVzPWAF6TUwg\" ");
		sb.append("className=\"bpmmodel.custom.CustomOrganizationNode\" ");
		sb.append("uid=\"" + this.getUid() + "\" ");
		if ( getName()!=null ) {
			sb.append("name=\""+ BpmmodelFormatter.getInstance().formatString(getName())+"\" ");
		}
		sb.append(">");
		sb.append("\n<phoneNumber propertyId=\"1861213202254517122\">");
		for ( OrganizationPhoneNumber phoneNumber : getPhoneNumber() ) {
			sb.append("\n" + phoneNumber.toXmlString());
		}
		sb.append("\n</phoneNumber>");
		sb.append("\n<eMailAddress propertyId=\"6276678134555712740\">");
		for ( OrganizationEMailAddress eMailAddress : getEMailAddress() ) {
			sb.append("\n" + eMailAddress.toXmlString());
		}
		sb.append("\n</eMailAddress>");
		if ( getBusinessCalendar()==null ) {
			sb.append("\n<businessCalendar/>");
		} else {
			sb.append("\n<businessCalendar propertyId=\"2759918346397932051\">");
			sb.append("\n" + getBusinessCalendar().toXmlString());
			sb.append("\n</businessCalendar>");
		}
		sb.append("\n<organizationFullfillsActorRole_businessActor propertyId=\"72028596279487881\">");
		for ( OrganizationFullfillsActorRole organizationFullfillsActorRole_businessActor : getOrganizationFullfillsActorRole_businessActor() ) {
			sb.append("\n" + organizationFullfillsActorRole_businessActor.toXmlString());
		}
		sb.append("\n</organizationFullfillsActorRole_businessActor>");
		sb.append("\n<organizationAsBusinessComponent_businessComponent propertyId=\"2861752063599373180\">");
		for ( OrganizationAsBusinessComponent organizationAsBusinessComponent_businessComponent : getOrganizationAsBusinessComponent_businessComponent() ) {
			sb.append("\n" + organizationAsBusinessComponent_businessComponent.toXmlReferenceString());
		}
		sb.append("\n</organizationAsBusinessComponent_businessComponent>");
		sb.append("\n</CustomOrganizationNode>");
		return sb.toString();
	}
	
	public void z_internalAddToBusinessNetwork(BusinessNetwork businessNetwork) {
		CustomBusinessNetwork customBusinessNetwork = (CustomBusinessNetwork)businessNetwork;
		if ( businessNetwork.equals(this.businessNetwork) ) {
			return;
		}
		this.businessNetwork=businessNetwork;
		if ( customBusinessNetwork.equals(this.customBusinessNetwork) ) {
			return;
		}
		this.customBusinessNetwork=customBusinessNetwork;
	}
	
	public void z_internalAddToCustomBusinessNetwork(CustomBusinessNetwork customBusinessNetwork) {
		BusinessNetwork businessNetwork = customBusinessNetwork;
		if ( customBusinessNetwork.equals(this.customBusinessNetwork) ) {
			return;
		}
		this.customBusinessNetwork=customBusinessNetwork;
		if ( businessNetwork.equals(this.businessNetwork) ) {
			return;
		}
		this.businessNetwork=businessNetwork;
	}
	
	public void z_internalAddToName(String name) {
		if ( name.equals(this.name) ) {
			return;
		}
		this.name=name;
		super.z_internalAddToName(name);
	}
	
	public void z_internalRemoveFromBusinessNetwork(BusinessNetwork businessNetwork) {
		CustomBusinessNetwork customBusinessNetwork = (CustomBusinessNetwork)businessNetwork;
		if ( getBusinessNetwork()!=null && businessNetwork!=null && businessNetwork.equals(getBusinessNetwork()) ) {
			this.businessNetwork=null;
			this.businessNetwork=null;
		}
		if ( getCustomBusinessNetwork()!=null && customBusinessNetwork!=null && customBusinessNetwork.equals(getCustomBusinessNetwork()) ) {
			this.customBusinessNetwork=null;
			this.customBusinessNetwork=null;
		}
	}
	
	public void z_internalRemoveFromCustomBusinessNetwork(CustomBusinessNetwork customBusinessNetwork) {
		BusinessNetwork businessNetwork = customBusinessNetwork;
		if ( getCustomBusinessNetwork()!=null && customBusinessNetwork!=null && customBusinessNetwork.equals(getCustomBusinessNetwork()) ) {
			this.customBusinessNetwork=null;
			this.customBusinessNetwork=null;
		}
		if ( getBusinessNetwork()!=null && businessNetwork!=null && businessNetwork.equals(getBusinessNetwork()) ) {
			this.businessNetwork=null;
			this.businessNetwork=null;
		}
	}
	
	public void z_internalRemoveFromName(String name) {
		if ( getName()!=null && name!=null && name.equals(getName()) ) {
			this.name=null;
			this.name=null;
		}
		super.z_internalRemoveFromName(name);
	}

}