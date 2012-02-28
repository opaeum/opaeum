package org.opaeum.runtime.bpm.organization;

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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.hibernate.annotations.AccessType;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.LazyCollection;
import org.opaeum.annotation.NumlMetaInfo;
import org.opaeum.runtime.bpm.util.OpaeumLibraryForBPMFormatter;
import org.opaeum.runtime.bpm.util.Stdlib;
import org.opaeum.runtime.domain.CancelledEvent;
import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.runtime.domain.HibernateEntity;
import org.opaeum.runtime.domain.IEventGenerator;
import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.runtime.domain.IntrospectionUtil;
import org.opaeum.runtime.domain.OutgoingEvent;
import org.opaeum.runtime.environment.Environment;
import org.opaeum.runtime.organization.IBusinessNetwork;
import org.opaeum.runtime.persistence.AbstractPersistence;
import org.opaeum.runtime.persistence.CmtPersistence;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

@NumlMetaInfo(uuid="252060@_NRu9QFYjEeGJUqEGX7bKSg")
@Filter(name="noDeletedObjects")
@org.hibernate.annotations.Entity(dynamicUpdate=true)
@AccessType(	"field")
@Table(name="business_network")
@Inheritance(strategy=javax.persistence.InheritanceType.JOINED)
@Entity(name="BusinessNetwork")
@DiscriminatorColumn(discriminatorType=javax.persistence.DiscriminatorType.STRING,name="type_descriminator")
public class BusinessNetwork implements IBusinessNetwork, IPersistentObject, IEventGenerator, HibernateEntity, CompositionNode, Serializable {
	@LazyCollection(	org.hibernate.annotations.LazyCollectionOption.TRUE)
	@Filter(condition="deleted_on > current_timestamp",name="noDeletedObjects")
	@OneToMany(cascade=javax.persistence.CascadeType.ALL,fetch=javax.persistence.FetchType.LAZY,mappedBy="businessNetwork",targetEntity=BusinessNetworkFacilatatesCollaboration.class)
	private Set<BusinessNetworkFacilatatesCollaboration> businessNetworkFacilatatesCollaboration_businessCollaboration = new HashSet<BusinessNetworkFacilatatesCollaboration>();
	@Transient
	private Set<CancelledEvent> cancelledEvents = new HashSet<CancelledEvent>();
		// Initialise to 1000 from 1970
	@Temporal(	javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="deleted_on")
	private Date deletedOn = Stdlib.FUTURE;
	@Id
	@GeneratedValue(strategy=javax.persistence.GenerationType.TABLE)
	private Long id;
	static private Set<BusinessNetwork> mockedAllInstances;
	@Version
	@Column(name="object_version")
	private int objectVersion;
	@LazyCollection(	org.hibernate.annotations.LazyCollectionOption.TRUE)
	@Filter(condition="deleted_on > current_timestamp",name="noDeletedObjects")
	@OneToMany(cascade=javax.persistence.CascadeType.ALL,fetch=javax.persistence.FetchType.LAZY,mappedBy="businessNetwork",targetEntity=OrganizationNode.class)
	private Set<OrganizationNode> organization = new HashSet<OrganizationNode>();
	@Transient
	private Set<OutgoingEvent> outgoingEvents = new HashSet<OutgoingEvent>();
	@Transient
	private AbstractPersistence persistence;
	@LazyCollection(	org.hibernate.annotations.LazyCollectionOption.TRUE)
	@Filter(condition="deleted_on > current_timestamp",name="noDeletedObjects")
	@OneToMany(cascade=javax.persistence.CascadeType.ALL,fetch=javax.persistence.FetchType.LAZY,mappedBy="collaboration",targetEntity=PersonNode.class)
	private Map<String, PersonNode> person = new HashMap<String,PersonNode>();
	static final private long serialVersionUID = 2395627898464121473l;
	private String uid;

	/** Default constructor for BusinessNetwork
	 */
	public BusinessNetwork() {
	}

	public void addAllToBusinessCollaboration(Set<IBusinessCollaboration> businessCollaboration) {
		for ( IBusinessCollaboration o : businessCollaboration ) {
			addToBusinessCollaboration(o);
		}
	}
	
	public void addAllToBusinessNetworkFacilatatesCollaboration_businessCollaboration(Set<BusinessNetworkFacilatatesCollaboration> businessNetworkFacilatatesCollaboration_businessCollaboration) {
		for ( BusinessNetworkFacilatatesCollaboration o : businessNetworkFacilatatesCollaboration_businessCollaboration ) {
			addToBusinessNetworkFacilatatesCollaboration_businessCollaboration(o);
		}
	}
	
	public void addAllToOrganization(Set<OrganizationNode> organization) {
		for ( OrganizationNode o : organization ) {
			addToOrganization(o);
		}
	}
	
	public void addToBusinessCollaboration(IBusinessCollaboration businessCollaboration) {
		if ( businessCollaboration!=null ) {
			businessCollaboration.z_internalRemoveFromBusinessNetwork(businessCollaboration.getBusinessNetwork());
			businessCollaboration.z_internalAddToBusinessNetwork(this);
			z_internalAddToBusinessCollaboration(businessCollaboration);
		}
	}
	
	public void addToBusinessNetworkFacilatatesCollaboration_businessCollaboration(BusinessNetworkFacilatatesCollaboration businessNetworkFacilatatesCollaboration_businessCollaboration) {
		if ( businessNetworkFacilatatesCollaboration_businessCollaboration!=null ) {
			businessNetworkFacilatatesCollaboration_businessCollaboration.z_internalRemoveFromBusinessNetwork(businessNetworkFacilatatesCollaboration_businessCollaboration.getBusinessNetwork());
			businessNetworkFacilatatesCollaboration_businessCollaboration.z_internalAddToBusinessNetwork(this);
			z_internalAddToBusinessNetworkFacilatatesCollaboration_businessCollaboration(businessNetworkFacilatatesCollaboration_businessCollaboration);
		}
	}
	
	public void addToOrganization(OrganizationNode organization) {
		if ( organization!=null ) {
			organization.z_internalRemoveFromBusinessNetwork(organization.getBusinessNetwork());
			organization.z_internalAddToBusinessNetwork(this);
			z_internalAddToOrganization(organization);
		}
	}
	
	/** Call this method when you want to attach this object to the containment tree. Useful with transitive persistence
	 */
	public void addToOwningObject() {
	}
	
	public void addToPerson(String username, PersonNode person) {
		if ( person!=null ) {
			person.z_internalRemoveFromCollaboration(person.getCollaboration());
			person.z_internalAddToCollaboration(this);
			z_internalAddToPerson(username,person);
		}
	}
	
	static public Set<? extends BusinessNetwork> allInstances() {
		if ( mockedAllInstances==null ) {
			CmtPersistence session =org.opaeum.runtime.environment.Environment.getInstance().getComponent(CmtPersistence.class);
			return new HashSet(session.readAll(org.opaeum.runtime.bpm.organization.BusinessNetwork.class));
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
							curVal=Environment.getMetaInfoMap().newInstance(((Element)currentPropertyValueNode).getAttribute("classUuid"));
						}
						curVal.buildTreeFromXml((Element)currentPropertyValueNode,map);
						this.addToPerson(curVal.getUsername(),curVal);
						map.put(curVal.getUid(), curVal);
					}
				}
			}
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
							curVal=Environment.getMetaInfoMap().newInstance(((Element)currentPropertyValueNode).getAttribute("classUuid"));
						}
						curVal.buildTreeFromXml((Element)currentPropertyValueNode,map);
						this.addToOrganization(curVal);
						map.put(curVal.getUid(), curVal);
					}
				}
			}
		}
	}
	
	public void clearBusinessCollaboration() {
		removeAllFromBusinessCollaboration(getBusinessCollaboration());
	}
	
	public void clearBusinessNetworkFacilatatesCollaboration_businessCollaboration() {
		removeAllFromBusinessNetworkFacilatatesCollaboration_businessCollaboration(getBusinessNetworkFacilatatesCollaboration_businessCollaboration());
	}
	
	public void clearOrganization() {
		removeAllFromOrganization(getOrganization());
	}
	
	public void clearPerson() {
		Set<PersonNode> tmp = new HashSet<PersonNode>(getPerson());
		for ( PersonNode o : tmp ) {
			removeFromPerson(o.getUsername(),o);
		}
		person.clear();
	}
	
	public void copyShallowState(BusinessNetwork from, BusinessNetwork to) {
	}
	
	public void copyState(BusinessNetwork from, BusinessNetwork to) {
		for ( PersonNode child : from.getPerson() ) {
			to.addToPerson(child.getUsername(),child.makeCopy());
		}
		for ( OrganizationNode child : from.getOrganization() ) {
			to.addToOrganization(child.makeCopy());
		}
	}
	
	public BusinessNetworkFacilatatesCollaboration createBusinessNetworkFacilatatesCollaboration_businessCollaboration() {
		BusinessNetworkFacilatatesCollaboration newInstance= new BusinessNetworkFacilatatesCollaboration();
		newInstance.init(this);
		return newInstance;
	}
	
	public void createComponents() {
	}
	
	public OrganizationNode createOrganization() {
		OrganizationNode newInstance= new OrganizationNode();
		newInstance.init(this);
		return newInstance;
	}
	
	public PersonNode createPerson() {
		PersonNode newInstance= new PersonNode();
		newInstance.init(this);
		return newInstance;
	}
	
	public boolean equals(Object other) {
		if ( other instanceof BusinessNetwork ) {
			return other==this || ((BusinessNetwork)other).getUid().equals(this.getUid());
		}
		return false;
	}
	
	public Set<IBusinessCollaboration> getBusinessCollaboration() {
		Set<IBusinessCollaboration> result = new HashSet<IBusinessCollaboration>();
		for ( BusinessNetworkFacilatatesCollaboration cur : this.getBusinessNetworkFacilatatesCollaboration_businessCollaboration() ) {
			result.add(cur.getBusinessCollaboration());
		}
		return result;
	}
	
	@NumlMetaInfo(uuid="252060@_YJETMFYjEeGJUqEGX7bKSg252060@_YJGvcFYjEeGJUqEGX7bKSg")
	public Set<BusinessNetworkFacilatatesCollaboration> getBusinessNetworkFacilatatesCollaboration_businessCollaboration() {
		Set<BusinessNetworkFacilatatesCollaboration> result = this.businessNetworkFacilatatesCollaboration_businessCollaboration;
		
		return result;
	}
	
	public BusinessNetworkFacilatatesCollaboration getBusinessNetworkFacilatatesCollaboration_businessCollaborationFor(IBusinessCollaboration match) {
		for ( BusinessNetworkFacilatatesCollaboration var : getBusinessNetworkFacilatatesCollaboration_businessCollaboration() ) {
			if ( var.getBusinessCollaboration().equals(match) ) {
				return var;
			}
		}
		return null;
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
	
	public String getName() {
		return "BusinessNetwork["+getId()+"]";
	}
	
	public int getObjectVersion() {
		return this.objectVersion;
	}
	
	@NumlMetaInfo(uuid="252060@_4uZ-MEvREeGmqIr8YsFD4g")
	public Set<OrganizationNode> getOrganization() {
		Set<OrganizationNode> result = this.organization;
		
		return result;
	}
	
	public Set<OutgoingEvent> getOutgoingEvents() {
		return this.outgoingEvents;
	}
	
	public CompositionNode getOwningObject() {
		return null;
	}
	
	public PersonNode getPerson(String username) {
		PersonNode result = null;
		StringBuilder key = new StringBuilder();
		key.append(username.toString());
		result=this.person.get(key.toString());
		return result;
	}
	
	@NumlMetaInfo(uuid="252060@_3lOvoEvREeGmqIr8YsFD4g")
	public Set<PersonNode> getPerson() {
		Set<PersonNode> result = new HashSet<PersonNode>(this.person.values());
		
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
		createComponents();
	}
	
	public BusinessNetwork makeCopy() {
		BusinessNetwork result = new BusinessNetwork();
		copyState((BusinessNetwork)this,result);
		return result;
	}
	
	public BusinessNetwork makeShallowCopy() {
		BusinessNetwork result = new BusinessNetwork();
		copyShallowState((BusinessNetwork)this,result);
		result.setId(this.getId());
		return result;
	}
	
	public void markDeleted() {
		for ( IBusinessCollaboration child : new ArrayList<IBusinessCollaboration>(getBusinessCollaboration()) ) {
			child.markDeleted();
		}
		for ( PersonNode child : new ArrayList<PersonNode>(getPerson()) ) {
			child.markDeleted();
		}
		for ( OrganizationNode child : new ArrayList<OrganizationNode>(getOrganization()) ) {
			child.markDeleted();
		}
		for ( BusinessNetworkFacilatatesCollaboration child : new ArrayList<BusinessNetworkFacilatatesCollaboration>(getBusinessNetworkFacilatatesCollaboration_businessCollaboration()) ) {
			child.markDeleted();
		}
		setDeletedOn(new Date());
	}
	
	static public void mockAllInstances(Set<BusinessNetwork> newMocks) {
		mockedAllInstances=newMocks;
	}
	
	public void populateReferencesFromXml(Element xml, Map<String, Object> map) {
		NodeList propertyNodes = xml.getChildNodes();
		int i = 0;
		while ( i<propertyNodes.getLength() ) {
			Node currentPropertyNode = propertyNodes.item(i++);
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
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("businessNetworkFacilatatesCollaboration_businessCollaboration") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("2189163074731335617")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						addToBusinessNetworkFacilatatesCollaboration_businessCollaboration((BusinessNetworkFacilatatesCollaboration)map.get(((Element)currentPropertyValueNode).getAttribute("uid")));
					}
				}
			}
		}
	}
	
	public void removeAllFromBusinessCollaboration(Set<IBusinessCollaboration> businessCollaboration) {
		Set<IBusinessCollaboration> tmp = new HashSet<IBusinessCollaboration>(businessCollaboration);
		for ( IBusinessCollaboration o : tmp ) {
			removeFromBusinessCollaboration(o);
		}
	}
	
	public void removeAllFromBusinessNetworkFacilatatesCollaboration_businessCollaboration(Set<BusinessNetworkFacilatatesCollaboration> businessNetworkFacilatatesCollaboration_businessCollaboration) {
		Set<BusinessNetworkFacilatatesCollaboration> tmp = new HashSet<BusinessNetworkFacilatatesCollaboration>(businessNetworkFacilatatesCollaboration_businessCollaboration);
		for ( BusinessNetworkFacilatatesCollaboration o : tmp ) {
			removeFromBusinessNetworkFacilatatesCollaboration_businessCollaboration(o);
		}
	}
	
	public void removeAllFromOrganization(Set<OrganizationNode> organization) {
		Set<OrganizationNode> tmp = new HashSet<OrganizationNode>(organization);
		for ( OrganizationNode o : tmp ) {
			removeFromOrganization(o);
		}
	}
	
	public void removeFromBusinessCollaboration(IBusinessCollaboration businessCollaboration) {
		if ( businessCollaboration!=null ) {
			z_internalRemoveFromBusinessCollaboration(businessCollaboration);
		}
	}
	
	public void removeFromBusinessNetworkFacilatatesCollaboration_businessCollaboration(BusinessNetworkFacilatatesCollaboration businessNetworkFacilatatesCollaboration_businessCollaboration) {
		if ( businessNetworkFacilatatesCollaboration_businessCollaboration!=null ) {
			businessNetworkFacilatatesCollaboration_businessCollaboration.z_internalRemoveFromBusinessNetwork(this);
			z_internalRemoveFromBusinessNetworkFacilatatesCollaboration_businessCollaboration(businessNetworkFacilatatesCollaboration_businessCollaboration);
		}
	}
	
	public void removeFromOrganization(OrganizationNode organization) {
		if ( organization!=null ) {
			organization.z_internalRemoveFromBusinessNetwork(this);
			z_internalRemoveFromOrganization(organization);
		}
	}
	
	public void removeFromOwningObject() {
		this.markDeleted();
	}
	
	public void removeFromPerson(String username, PersonNode person) {
		if ( person!=null ) {
			person.z_internalRemoveFromCollaboration(this);
			z_internalRemoveFromPerson(username,person);
		}
	}
	
	public void setBusinessCollaboration(Set<IBusinessCollaboration> businessCollaboration) {
		this.clearBusinessCollaboration();
		this.addAllToBusinessCollaboration(businessCollaboration);
	}
	
	public void setBusinessNetworkFacilatatesCollaboration_businessCollaboration(Set<BusinessNetworkFacilatatesCollaboration> businessNetworkFacilatatesCollaboration_businessCollaboration) {
		this.clearBusinessNetworkFacilatatesCollaboration_businessCollaboration();
		this.addAllToBusinessNetworkFacilatatesCollaboration_businessCollaboration(businessNetworkFacilatatesCollaboration_businessCollaboration);
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
	
	public void setObjectVersion(int objectVersion) {
		this.objectVersion=objectVersion;
	}
	
	public void setOrganization(Set<OrganizationNode> organization) {
		this.clearOrganization();
		this.addAllToOrganization(organization);
	}
	
	public void setOutgoingEvents(Set<OutgoingEvent> outgoingEvents) {
		this.outgoingEvents=outgoingEvents;
	}
	
	public void setUid(String newUid) {
		this.uid=newUid;
	}
	
	public String toXmlReferenceString() {
		return "<BusinessNetwork uid=\""+getUid() + "\"/>";
	}
	
	public String toXmlString() {
		StringBuilder sb = new StringBuilder();
		sb.append("<BusinessNetwork ");
		sb.append("classUuid=\"252060@_NRu9QFYjEeGJUqEGX7bKSg\" ");
		sb.append("className=\"org.opaeum.runtime.bpm.organization.BusinessNetwork\" ");
		sb.append("uid=\"" + this.getUid() + "\" ");
		sb.append(">");
		sb.append("\n<person propertyId=\"2470938974911877691\">");
		for ( PersonNode person : getPerson() ) {
			sb.append("\n" + person.toXmlString());
		}
		sb.append("\n</person>");
		sb.append("\n<organization propertyId=\"5972556763473316153\">");
		for ( OrganizationNode organization : getOrganization() ) {
			sb.append("\n" + organization.toXmlString());
		}
		sb.append("\n</organization>");
		sb.append("\n<businessNetworkFacilatatesCollaboration_businessCollaboration propertyId=\"2189163074731335617\">");
		for ( BusinessNetworkFacilatatesCollaboration businessNetworkFacilatatesCollaboration_businessCollaboration : getBusinessNetworkFacilatatesCollaboration_businessCollaboration() ) {
			sb.append("\n" + businessNetworkFacilatatesCollaboration_businessCollaboration.toXmlReferenceString());
		}
		sb.append("\n</businessNetworkFacilatatesCollaboration_businessCollaboration>");
		sb.append("\n</BusinessNetwork>");
		return sb.toString();
	}
	
	public void z_internalAddToBusinessCollaboration(IBusinessCollaboration businessCollaboration) {
		BusinessNetworkFacilatatesCollaboration newOne = new BusinessNetworkFacilatatesCollaboration(this,businessCollaboration);
		this.z_internalAddToBusinessNetworkFacilatatesCollaboration_businessCollaboration(newOne);
		newOne.getBusinessCollaboration().z_internalAddToBusinessNetworkFacilatatesCollaboration_businessNetwork(newOne);
	}
	
	public void z_internalAddToBusinessNetworkFacilatatesCollaboration_businessCollaboration(BusinessNetworkFacilatatesCollaboration val) {
		this.businessNetworkFacilatatesCollaboration_businessCollaboration.add(val);
	}
	
	public void z_internalAddToOrganization(OrganizationNode val) {
		this.organization.add(val);
	}
	
	public void z_internalAddToPerson(String username, PersonNode val) {
		StringBuilder key = new StringBuilder();
		key.append(username.toString());
		val.z_internalAddToUsername(username);
		this.person.put(key.toString(),val);
		val.setZ_keyOfPersonOnBusinessNetwork(key.toString());
	}
	
	public void z_internalRemoveFromBusinessCollaboration(IBusinessCollaboration businessCollaboration) {
		for ( BusinessNetworkFacilatatesCollaboration cur : new HashSet<BusinessNetworkFacilatatesCollaboration>(this.businessNetworkFacilatatesCollaboration_businessCollaboration) ) {
			if ( cur.getBusinessCollaboration().equals(businessCollaboration) ) {
				cur.clear();
				break;
			}
		}
	}
	
	public void z_internalRemoveFromBusinessNetworkFacilatatesCollaboration_businessCollaboration(BusinessNetworkFacilatatesCollaboration val) {
		this.businessNetworkFacilatatesCollaboration_businessCollaboration.remove(val);
	}
	
	public void z_internalRemoveFromOrganization(OrganizationNode val) {
		this.organization.remove(val);
	}
	
	public void z_internalRemoveFromPerson(String username, PersonNode val) {
		StringBuilder key = new StringBuilder();
		key.append(username.toString());
		this.person.remove(key.toString());
	}

}