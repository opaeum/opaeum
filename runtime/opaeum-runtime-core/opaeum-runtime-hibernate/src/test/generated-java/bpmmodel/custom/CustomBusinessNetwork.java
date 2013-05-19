package bpmmodel.custom;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;

import org.hibernate.annotations.AccessType;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.LazyCollection;
import org.opaeum.annotation.NumlMetaInfo;
import org.opaeum.annotation.PropertyMetaInfo;
import org.opaeum.hibernate.domain.InternalHibernatePersistence;
import org.opaeum.runtime.bpm.organization.BusinessNetwork;
import org.opaeum.runtime.bpm.organization.BusinessNetworkFacilatatesCollaboration;
import org.opaeum.runtime.bpm.organization.IBusinessCollaboration;
import org.opaeum.runtime.bpm.organization.OrganizationNode;
import org.opaeum.runtime.bpm.organization.PersonNode;
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

@NumlMetaInfo(applicationIdentifier="opaeum_hibernate_tests",uuid="bpm.uml@_qYfVgMB-EeKVzPWAF6TUwg")
@Filter(name="noDeletedObjects")
@org.hibernate.annotations.Entity(dynamicUpdate=true)
@AccessType(	"field")
@Table(name="custom_business_network")
@Entity(name="CustomBusinessNetwork")
@DiscriminatorValue(	"custom_business_network")
public class CustomBusinessNetwork extends BusinessNetwork implements IPersistentObject, IEventGenerator, HibernateEntity, CompositionNode, Serializable {
	@Transient
	private Set<CancelledEvent> cancelledEvents = new HashSet<CancelledEvent>();
	@LazyCollection(	org.hibernate.annotations.LazyCollectionOption.TRUE)
	@Filter(condition="deleted_on > current_timestamp",name="noDeletedObjects")
	@OneToMany(cascade=javax.persistence.CascadeType.ALL,fetch=javax.persistence.FetchType.LAZY,mappedBy="customBusinessNetwork",targetEntity=CustomOrganizationNode.class)
	@MapKey(name="z_keyOfCustomOrganizationNodeOnCustomBusinessNetwork")
	protected Map<String, CustomOrganizationNode> customOrganizationNode = new HashMap<String,CustomOrganizationNode>();
		// Initialise to 1000 from 1970
	@Temporal(	javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="deleted_on")
	private Date deletedOn = Stdlib.FUTURE;
	static private Set<? extends CustomBusinessNetwork> mockedAllInstances;
	@Transient
	private Set<OutgoingEvent> outgoingEvents = new HashSet<OutgoingEvent>();
	@Transient
	private InternalHibernatePersistence persistence;
	static final private long serialVersionUID = 6933712012973498290l;

	/** Default constructor for CustomBusinessNetwork
	 */
	public CustomBusinessNetwork() {
	}

	public void addAllToCustomOrganizationNode(Set<CustomOrganizationNode> customOrganizationNode) {
		for ( CustomOrganizationNode o : customOrganizationNode ) {
			addToCustomOrganizationNode(o.getName(),o);
		}
	}
	
	public void addAllToOrganization(Set<OrganizationNode> organization) {
		for ( OrganizationNode o : organization ) {
			addToOrganization(o.getName(),o);
		}
	}
	
	public void addToCustomOrganizationNode(String name, CustomOrganizationNode customOrganizationNode) {
		if ( customOrganizationNode!=null ) {
			if ( customOrganizationNode.getCustomBusinessNetwork()!=null ) {
				customOrganizationNode.getCustomBusinessNetwork().removeFromCustomOrganizationNode(customOrganizationNode.getName(),customOrganizationNode);
			}
			customOrganizationNode.z_internalAddToCustomBusinessNetwork(this);
		}
		z_internalAddToCustomOrganizationNode(name,customOrganizationNode);
	}
	
	public void addToOrganization(String name, OrganizationNode organization) {
		if ( organization!=null ) {
			if ( organization.getBusinessNetwork()!=null ) {
				organization.getBusinessNetwork().removeFromOrganization(organization.getName(),organization);
			}
			organization.z_internalAddToBusinessNetwork(this);
		}
		z_internalAddToOrganization(name,organization);
	}
	
	/** Call this method when you want to attach this object to the containment tree. Useful with transitive persistence
	 */
	public void addToOwningObject() {
	}
	
	static public Set<? extends CustomBusinessNetwork> allInstances(AbstractPersistence persistence) {
		if ( mockedAllInstances==null ) {
			return new HashSet(persistence.readAll(bpmmodel.custom.CustomBusinessNetwork.class));
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
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("organization") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("5972556763473316153")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						OrganizationNode curVal;
						try {
							curVal=IntrospectionUtil.newInstance(((Element)currentPropertyValueNode).getAttribute("className"));
						} catch (Exception e) {
							curVal=org.opaeum.runtime.bpm.util.OpaeumLibraryForBPMJavaMetaInfoMap.INSTANCE.newInstance(((Element)currentPropertyValueNode).getAttribute("classUuid"));
						}
						curVal.buildTreeFromXml((Element)currentPropertyValueNode,map);
						this.z_internalAddToOrganization(curVal.getName(),curVal);
						curVal.z_internalAddToBusinessNetwork(this);
						map.put(curVal.getUid(), curVal);
					}
				}
			}
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("person") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("2470938974911877691")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						PersonNode curVal;
						try {
							curVal=IntrospectionUtil.newInstance(((Element)currentPropertyValueNode).getAttribute("className"));
						} catch (Exception e) {
							curVal=org.opaeum.runtime.bpm.util.OpaeumLibraryForBPMJavaMetaInfoMap.INSTANCE.newInstance(((Element)currentPropertyValueNode).getAttribute("classUuid"));
						}
						curVal.buildTreeFromXml((Element)currentPropertyValueNode,map);
						this.z_internalAddToPerson(curVal.getUsername(),curVal);
						curVal.z_internalAddToBusinessNetwork(this);
						map.put(curVal.getUid(), curVal);
					}
				}
			}
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("customOrganizationNode") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("5677900741572114538")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						CustomOrganizationNode curVal;
						try {
							curVal=IntrospectionUtil.newInstance(((Element)currentPropertyValueNode).getAttribute("className"));
						} catch (Exception e) {
							curVal=bpmmodel.util.BpmmodelJavaMetaInfoMap.INSTANCE.newInstance(((Element)currentPropertyValueNode).getAttribute("classUuid"));
						}
						curVal.buildTreeFromXml((Element)currentPropertyValueNode,map);
						this.z_internalAddToCustomOrganizationNode(curVal.getName(),curVal);
						curVal.z_internalAddToCustomBusinessNetwork(this);
						map.put(curVal.getUid(), curVal);
					}
				}
			}
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("businessNetworkFacilatatesCollaboration_businessCollaboration") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("8697190624988594199")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						BusinessNetworkFacilatatesCollaboration curVal;
						try {
							curVal=IntrospectionUtil.newInstance(((Element)currentPropertyValueNode).getAttribute("className"));
						} catch (Exception e) {
							curVal=org.opaeum.runtime.bpm.util.OpaeumLibraryForBPMJavaMetaInfoMap.INSTANCE.newInstance(((Element)currentPropertyValueNode).getAttribute("classUuid"));
						}
						curVal.buildTreeFromXml((Element)currentPropertyValueNode,map);
						this.z_internalAddToBusinessNetworkFacilatatesCollaboration_businessCollaboration(curVal);
						curVal.z_internalAddToBusinessNetwork(this);
						map.put(curVal.getUid(), curVal);
					}
				}
			}
		}
	}
	
	public void clearCustomOrganizationNode() {
		Set<CustomOrganizationNode> tmp = new HashSet<CustomOrganizationNode>(getCustomOrganizationNode());
		for ( CustomOrganizationNode o : tmp ) {
			removeFromCustomOrganizationNode(o.getName(),o);
		}
	}
	
	public void clearOrganization() {
		Set<OrganizationNode> tmp = new HashSet<OrganizationNode>(getOrganization());
		for ( OrganizationNode o : tmp ) {
			removeFromOrganization(o.getName(),o);
		}
	}
	
	public void copyShallowState(CustomBusinessNetwork from, CustomBusinessNetwork to) {
	}
	
	public void copyState(CustomBusinessNetwork from, CustomBusinessNetwork to) {
		for ( OrganizationNode child : from.getOrganization() ) {
			to.addToOrganization(child.getName(),child.makeCopy());
		}
		for ( PersonNode child : from.getPerson() ) {
			to.addToPerson(child.getUsername(),child.makeCopy());
		}
		for ( CustomOrganizationNode child : from.getCustomOrganizationNode() ) {
			to.addToCustomOrganizationNode(child.getName(),child.makeCopy());
		}
	}
	
	public void createComponents() {
		super.createComponents();
	}
	
	public CustomOrganizationNode createCustomOrganizationNode(String name) {
		CustomOrganizationNode newInstance= new CustomOrganizationNode();
		newInstance.setName(name);
		newInstance.init(this);
		return newInstance;
	}
	
	public OrganizationNode createOrganization(String name) {
		OrganizationNode newInstance= new OrganizationNode();
		newInstance.setName(name);
		newInstance.init(this);
		return newInstance;
	}
	
	public boolean equals(Object other) {
		if ( other instanceof CustomBusinessNetwork ) {
			return other==this || ((CustomBusinessNetwork)other).getUid().equals(this.getUid());
		}
		return false;
	}
	
	public Set<CancelledEvent> getCancelledEvents() {
		return this.cancelledEvents;
	}
	
	public CustomOrganizationNode getCustomOrganizationNode(String name) {
		CustomOrganizationNode result = null;
		String key = BpmmodelFormatter.getInstance().formatStringQualifier(name);
		result=this.customOrganizationNode.get(key.toString());
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=true,opaeumId=5677900741572114538l,opposite="customBusinessNetwork",uuid="bpm.uml@_9d9xkMB-EeKVzPWAF6TUwg")
	@NumlMetaInfo(uuid="bpm.uml@_9d9xkMB-EeKVzPWAF6TUwg")
	public Set<CustomOrganizationNode> getCustomOrganizationNode() {
		Set result = new HashSet<CustomOrganizationNode>(this.customOrganizationNode.values());
		
		return result;
	}
	
	public Date getDeletedOn() {
		return this.deletedOn;
	}
	
	public String getName() {
		return "CustomBusinessNetwork["+getId()+"]";
	}
	
	public OrganizationNode getOrganization(String name) {
		OrganizationNode result = getCustomOrganizationNode(name);
		
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=true,opaeumId=5972556763473316153l,opposite="businessNetwork",uuid="252060@_4uZ-MEvREeGmqIr8YsFD4g")
	@NumlMetaInfo(uuid="252060@_4uZ-MEvREeGmqIr8YsFD4g")
	@SuppressWarnings(	{"unchecked","rawtypes"})
	public Set<OrganizationNode> getOrganization() {
		Set result = (java.util.Set)getCustomOrganizationNode();
		
		return result;
	}
	
	public Set<OutgoingEvent> getOutgoingEvents() {
		return this.outgoingEvents;
	}
	
	public CompositionNode getOwningObject() {
		return null;
	}
	
	public int hashCode() {
		return getUid().hashCode();
	}
	
	public void init(CompositionNode owner) {
		super.init(owner);
	}
	
	public CustomBusinessNetwork makeCopy() {
		CustomBusinessNetwork result = new CustomBusinessNetwork();
		copyState((CustomBusinessNetwork)this,result);
		return result;
	}
	
	public CustomBusinessNetwork makeShallowCopy() {
		CustomBusinessNetwork result = new CustomBusinessNetwork();
		copyShallowState((CustomBusinessNetwork)this,result);
		result.setId(this.getId());
		return result;
	}
	
	public void markDeleted() {
		super.markDeleted();
		for ( OrganizationNode child : new ArrayList<OrganizationNode>(getOrganization()) ) {
			child.markDeleted();
		}
		for ( PersonNode child : new ArrayList<PersonNode>(getPerson()) ) {
			child.markDeleted();
		}
		for ( IBusinessCollaboration child : new ArrayList<IBusinessCollaboration>(getBusinessCollaboration()) ) {
			child.markDeleted();
		}
		for ( CustomOrganizationNode child : new ArrayList<CustomOrganizationNode>(getCustomOrganizationNode()) ) {
			child.markDeleted();
		}
		for ( BusinessNetworkFacilatatesCollaboration child : new ArrayList<BusinessNetworkFacilatatesCollaboration>(getBusinessNetworkFacilatatesCollaboration_businessCollaboration()) ) {
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
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("organization") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("5972556763473316153")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						((OrganizationNode)map.get(((Element)currentPropertyValueNode).getAttribute("uid"))).populateReferencesFromXml((Element)currentPropertyValueNode, map);
					}
				}
			}
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("person") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("2470938974911877691")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						((PersonNode)map.get(((Element)currentPropertyValueNode).getAttribute("uid"))).populateReferencesFromXml((Element)currentPropertyValueNode, map);
					}
				}
			}
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("customOrganizationNode") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("5677900741572114538")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						((CustomOrganizationNode)map.get(((Element)currentPropertyValueNode).getAttribute("uid"))).populateReferencesFromXml((Element)currentPropertyValueNode, map);
					}
				}
			}
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("businessNetworkFacilatatesCollaboration_businessCollaboration") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("8697190624988594199")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						BusinessNetworkFacilatatesCollaboration businessNetworkFacilatatesCollaboration_businessCollaboration = (BusinessNetworkFacilatatesCollaboration)map.get(((Element)currentPropertyValueNode).getAttribute("uid"));
						if ( businessNetworkFacilatatesCollaboration_businessCollaboration!=null ) {
							z_internalAddToBusinessNetworkFacilatatesCollaboration_businessCollaboration(businessNetworkFacilatatesCollaboration_businessCollaboration);
							businessNetworkFacilatatesCollaboration_businessCollaboration.z_internalAddToBusinessNetwork(this);
						}
					}
				}
			}
		}
	}
	
	public void removeAllFromCustomOrganizationNode(Set<? extends CustomOrganizationNode> customOrganizationNode) {
		Set<CustomOrganizationNode> tmp = new HashSet<CustomOrganizationNode>(customOrganizationNode);
		for ( CustomOrganizationNode o : tmp ) {
			removeFromCustomOrganizationNode(o.getName(),o);
		}
	}
	
	public void removeAllFromOrganization(Set<? extends OrganizationNode> organization) {
		Set<OrganizationNode> tmp = new HashSet<OrganizationNode>(organization);
		for ( OrganizationNode o : tmp ) {
			removeFromOrganization(o.getName(),o);
		}
	}
	
	public void removeFromCustomOrganizationNode(String name, CustomOrganizationNode customOrganizationNode) {
		if ( customOrganizationNode!=null ) {
			customOrganizationNode.z_internalRemoveFromCustomBusinessNetwork(this);
			z_internalRemoveFromCustomOrganizationNode(customOrganizationNode.getName(),customOrganizationNode);
		}
	}
	
	public void removeFromOrganization(String name, OrganizationNode organization) {
		if ( organization!=null ) {
			organization.z_internalRemoveFromBusinessNetwork(this);
			z_internalRemoveFromOrganization(organization.getName(),organization);
		}
	}
	
	public void removeFromOwningObject() {
		this.markDeleted();
	}
	
	public void setCancelledEvents(Set<CancelledEvent> cancelledEvents) {
		this.cancelledEvents=cancelledEvents;
	}
	
	public void setCustomOrganizationNode(Set<CustomOrganizationNode> customOrganizationNode) {
		this.clearCustomOrganizationNode();
		this.addAllToCustomOrganizationNode(customOrganizationNode);
	}
	
	public void setDeletedOn(Date deletedOn) {
		this.deletedOn=deletedOn;
		super.setDeletedOn(deletedOn);
	}
	
	public void setOrganization(Set<OrganizationNode> organization) {
		this.clearOrganization();
		this.addAllToOrganization(organization);
	}
	
	public void setOutgoingEvents(Set<OutgoingEvent> outgoingEvents) {
		this.outgoingEvents=outgoingEvents;
	}
	
	public String toXmlReferenceString() {
		return "<CustomBusinessNetwork uid=\""+getUid() + "\"/>";
	}
	
	public String toXmlString() {
		StringBuilder sb = new StringBuilder();
		sb.append("<CustomBusinessNetwork ");
		sb.append("classUuid=\"bpm.uml@_qYfVgMB-EeKVzPWAF6TUwg\" ");
		sb.append("className=\"bpmmodel.custom.CustomBusinessNetwork\" ");
		sb.append("uid=\"" + this.getUid() + "\" ");
		sb.append(">");
		sb.append("\n<organization propertyId=\"5972556763473316153\">");
		for ( OrganizationNode organization : getOrganization() ) {
			sb.append("\n" + organization.toXmlString());
		}
		sb.append("\n</organization>");
		sb.append("\n<person propertyId=\"2470938974911877691\">");
		for ( PersonNode person : getPerson() ) {
			sb.append("\n" + person.toXmlString());
		}
		sb.append("\n</person>");
		sb.append("\n<customOrganizationNode propertyId=\"5677900741572114538\">");
		for ( CustomOrganizationNode customOrganizationNode : getCustomOrganizationNode() ) {
			sb.append("\n" + customOrganizationNode.toXmlString());
		}
		sb.append("\n</customOrganizationNode>");
		sb.append("\n<businessNetworkFacilatatesCollaboration_businessCollaboration propertyId=\"8697190624988594199\">");
		for ( BusinessNetworkFacilatatesCollaboration businessNetworkFacilatatesCollaboration_businessCollaboration : getBusinessNetworkFacilatatesCollaboration_businessCollaboration() ) {
			sb.append("\n" + businessNetworkFacilatatesCollaboration_businessCollaboration.toXmlString());
		}
		sb.append("\n</businessNetworkFacilatatesCollaboration_businessCollaboration>");
		sb.append("\n</CustomBusinessNetwork>");
		return sb.toString();
	}
	
	public void z_internalAddToCustomOrganizationNode(String name, CustomOrganizationNode customOrganizationNode) {
		String key = BpmmodelFormatter.getInstance().formatStringQualifier(name);
		OrganizationNode organization = customOrganizationNode;
		if ( this.customOrganizationNode.containsValue(customOrganizationNode) ) {
			return;
		}
		customOrganizationNode.z_internalAddToName(name);
		this.customOrganizationNode.put(key.toString(),customOrganizationNode);
		customOrganizationNode.setZ_keyOfCustomOrganizationNodeOnCustomBusinessNetwork(key.toString());
		if ( this.organization.containsValue(organization) ) {
			return;
		}
		organization.z_internalAddToName(name);
		this.organization.put(key.toString(),organization);
		organization.setZ_keyOfOrganizationOnBusinessNetwork(key.toString());
	}
	
	public void z_internalAddToOrganization(String name, OrganizationNode organization) {
		String key = BpmmodelFormatter.getInstance().formatStringQualifier(name);
		CustomOrganizationNode customOrganizationNode = (CustomOrganizationNode)organization;
		if ( this.organization.containsValue(organization) ) {
			return;
		}
		organization.z_internalAddToName(name);
		this.organization.put(key.toString(),organization);
		organization.setZ_keyOfOrganizationOnBusinessNetwork(key.toString());
		if ( this.customOrganizationNode.containsValue(customOrganizationNode) ) {
			return;
		}
		customOrganizationNode.z_internalAddToName(name);
		this.customOrganizationNode.put(key.toString(),customOrganizationNode);
		customOrganizationNode.setZ_keyOfCustomOrganizationNodeOnCustomBusinessNetwork(key.toString());
	}
	
	public void z_internalRemoveFromCustomOrganizationNode(String name, CustomOrganizationNode customOrganizationNode) {
		String key = BpmmodelFormatter.getInstance().formatStringQualifier(name);
		OrganizationNode organization = customOrganizationNode;
		this.customOrganizationNode.remove(key.toString());
		this.organization.remove(key.toString());
	}
	
	public void z_internalRemoveFromOrganization(String name, OrganizationNode organization) {
		String key = BpmmodelFormatter.getInstance().formatStringQualifier(name);
		CustomOrganizationNode customOrganizationNode = (CustomOrganizationNode)organization;
		this.organization.remove(key.toString());
		this.customOrganizationNode.remove(key.toString());
	}

}