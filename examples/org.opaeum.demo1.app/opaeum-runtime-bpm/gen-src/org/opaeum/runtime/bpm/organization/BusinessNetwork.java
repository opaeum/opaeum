package org.opaeum.runtime.bpm.organization;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.hibernate.annotations.AccessType;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.LazyCollection;
import org.opaeum.annotation.NumlMetaInfo;
import org.opaeum.annotation.ParameterMetaInfo;
import org.opaeum.annotation.PropertyMetaInfo;
import org.opaeum.hibernate.domain.InternalHibernatePersistence;
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
import org.opaeum.runtime.organization.IBusinessCollaborationBase;
import org.opaeum.runtime.organization.IBusinessNetwork;
import org.opaeum.runtime.persistence.AbstractPersistence;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

@NumlMetaInfo(applicationIdentifier="demo1",uuid="252060@_NRu9QFYjEeGJUqEGX7bKSg")
@Filter(name="noDeletedObjects")
@org.hibernate.annotations.Entity(dynamicUpdate=true)
@AccessType(	"field")
@Table(name="business_network",schema="bpm")
@Entity(name="BusinessNetwork")
public class BusinessNetwork implements IBusinessNetwork, IPersistentObject, IEventGenerator, HibernateEntity, CompositionNode, Serializable {
	@LazyCollection(	org.hibernate.annotations.LazyCollectionOption.TRUE)
	@Filter(condition="deleted_on > current_timestamp",name="noDeletedObjects")
	@OneToMany(cascade=javax.persistence.CascadeType.ALL,fetch=javax.persistence.FetchType.LAZY,mappedBy="businessNetwork",targetEntity=BusinessNetworkFacilatatesCollaboration.class)
	protected Set<BusinessNetworkFacilatatesCollaboration> businessNetworkFacilatatesCollaboration_businessCollaboration = new HashSet<BusinessNetworkFacilatatesCollaboration>();
	@Transient
	private Set<CancelledEvent> cancelledEvents = new HashSet<CancelledEvent>();
		// Initialise to 1000 from 1970
	@Temporal(	javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="deleted_on")
	private Date deletedOn = Stdlib.FUTURE;
	@TableGenerator(allocationSize=20,name="id_generator",pkColumnName="type",pkColumnValue="business_network",table="hi_value")
	@Id
	@GeneratedValue(generator="id_generator",strategy=javax.persistence.GenerationType.TABLE)
	private Long id;
	static private Set<? extends BusinessNetwork> mockedAllInstances;
	@Version
	@Column(name="object_version")
	private int objectVersion;
	@LazyCollection(	org.hibernate.annotations.LazyCollectionOption.TRUE)
	@Filter(condition="deleted_on > current_timestamp",name="noDeletedObjects")
	@OneToMany(cascade=javax.persistence.CascadeType.ALL,fetch=javax.persistence.FetchType.LAZY,mappedBy="businessNetwork",targetEntity=OrganizationNode.class)
	@MapKey(name="z_keyOfOrganizationOnBusinessNetwork")
	protected Map<String, OrganizationNode> organization = new HashMap<String,OrganizationNode>();
	@Transient
	private Set<OutgoingEvent> outgoingEvents = new HashSet<OutgoingEvent>();
	@Transient
	private InternalHibernatePersistence persistence;
	@LazyCollection(	org.hibernate.annotations.LazyCollectionOption.TRUE)
	@Filter(condition="deleted_on > current_timestamp",name="noDeletedObjects")
	@OneToMany(cascade=javax.persistence.CascadeType.ALL,fetch=javax.persistence.FetchType.LAZY,mappedBy="businessNetwork",targetEntity=PersonNode.class)
	@MapKey(name="z_keyOfPersonOnBusinessNetwork")
	protected Map<String, PersonNode> person = new HashMap<String,PersonNode>();
	@Transient
	private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
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
	
	public void addPropertyChangeListener(String property, PropertyChangeListener listener) {
		propertyChangeSupport.addPropertyChangeListener(property,listener);
	}
	
	public void addToBusinessCollaboration(IBusinessCollaboration businessCollaboration) {
		if ( businessCollaboration!=null ) {
			businessCollaboration.z_internalRemoveFromBusinessNetwork(businessCollaboration.getBusinessNetwork());
			z_internalAddToBusinessCollaboration(businessCollaboration);
		}
	}
	
	@NumlMetaInfo(uuid="252060@_Nn1XcGIIEeGzlJ4_CwngCw")
	public void addToBusinessCollaboration(@ParameterMetaInfo(name="collaboration",opaeumId=8024673565944051013l,uuid="252060@_dy7GAGIIEeGzlJ4_CwngCw") IBusinessCollaborationBase collaboration) {
		BusinessNetwork tgtAddStructuralFeatureValueAction1=this;
		tgtAddStructuralFeatureValueAction1.addToBusinessCollaboration(((IBusinessCollaboration) collaboration));
	}
	
	public void addToBusinessNetworkFacilatatesCollaboration_businessCollaboration(BusinessNetworkFacilatatesCollaboration businessNetworkFacilatatesCollaboration_businessCollaboration) {
		if ( businessNetworkFacilatatesCollaboration_businessCollaboration!=null ) {
			businessNetworkFacilatatesCollaboration_businessCollaboration.z_internalRemoveFromBusinessNetwork(businessNetworkFacilatatesCollaboration_businessCollaboration.getBusinessNetwork());
			businessNetworkFacilatatesCollaboration_businessCollaboration.z_internalAddToBusinessNetwork(this);
			z_internalAddToBusinessNetworkFacilatatesCollaboration_businessCollaboration(businessNetworkFacilatatesCollaboration_businessCollaboration);
		}
	}
	
	public void addToOrganization(String name, OrganizationNode organization) {
		if ( organization!=null ) {
			organization.z_internalRemoveFromBusinessNetwork(organization.getBusinessNetwork());
			organization.z_internalAddToBusinessNetwork(this);
			z_internalAddToOrganization(name,organization);
		}
	}
	
	/** Call this method when you want to attach this object to the containment tree. Useful with transitive persistence
	 */
	public void addToOwningObject() {
	}
	
	public void addToPerson(String username, PersonNode person) {
		if ( person!=null ) {
			person.z_internalRemoveFromBusinessNetwork(person.getBusinessNetwork());
			person.z_internalAddToBusinessNetwork(this);
			z_internalAddToPerson(username,person);
		}
	}
	
	static public Set<? extends BusinessNetwork> allInstances(AbstractPersistence persistence) {
		if ( mockedAllInstances==null ) {
			return new HashSet(persistence.readAll(org.opaeum.runtime.bpm.organization.BusinessNetwork.class));
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
						this.addToOrganization(curVal.getName(),curVal);
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
						this.addToPerson(curVal.getUsername(),curVal);
						map.put(curVal.getUid(), curVal);
					}
				}
			}
		}
	}
	
	public void clearBusinessCollaboration() {
		Set<IBusinessCollaboration> tmp = new HashSet<IBusinessCollaboration>(getBusinessCollaboration());
		for ( IBusinessCollaboration o : tmp ) {
			removeFromBusinessCollaboration(o);
		}
	}
	
	public void clearBusinessNetworkFacilatatesCollaboration_businessCollaboration() {
		Set<BusinessNetworkFacilatatesCollaboration> tmp = new HashSet<BusinessNetworkFacilatatesCollaboration>(getBusinessNetworkFacilatatesCollaboration_businessCollaboration());
		for ( BusinessNetworkFacilatatesCollaboration o : tmp ) {
			removeFromBusinessNetworkFacilatatesCollaboration_businessCollaboration(o);
		}
	}
	
	public void clearOrganization() {
		Set<OrganizationNode> tmp = new HashSet<OrganizationNode>(getOrganization());
		for ( OrganizationNode o : tmp ) {
			removeFromOrganization(o.getName(),o);
		}
		organization.clear();
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
		for ( OrganizationNode child : from.getOrganization() ) {
			to.addToOrganization(child.getName(),child.makeCopy());
		}
		for ( PersonNode child : from.getPerson() ) {
			to.addToPerson(child.getUsername(),child.makeCopy());
		}
	}
	
	public void createComponents() {
	}
	
	public OrganizationNode createOrganization(String name) {
		OrganizationNode newInstance= new OrganizationNode();
		newInstance.setName(name);
		newInstance.init(this);
		return newInstance;
	}
	
	public PersonNode createPerson(String username) {
		PersonNode newInstance= new PersonNode();
		newInstance.setUsername(username);
		newInstance.init(this);
		return newInstance;
	}
	
	public boolean equals(Object other) {
		if ( other instanceof BusinessNetwork ) {
			return other==this || ((BusinessNetwork)other).getUid().equals(this.getUid());
		}
		return false;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=true,opaeumId=3543861783554997017l,opposite="businessNetwork",uuid="252060@_YJETMFYjEeGJUqEGX7bKSg")
	public Set<IBusinessCollaboration> getBusinessCollaboration() {
		Set result = new HashSet<IBusinessCollaboration>();
		for ( BusinessNetworkFacilatatesCollaboration cur : this.getBusinessNetworkFacilatatesCollaboration_businessCollaboration() ) {
			result.add(cur.getBusinessCollaboration());
		}
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=8697190624988594199l,opposite="businessNetwork",uuid="252060@_YJGvcFYjEeGJUqEGX7bKSg")
	@NumlMetaInfo(uuid="252060@_NRu9QFYjEeGJUqEGX7bKSg@252060@_YJGvcFYjEeGJUqEGX7bKSg")
	public Set<BusinessNetworkFacilatatesCollaboration> getBusinessNetworkFacilatatesCollaboration_businessCollaboration() {
		Set result = this.businessNetworkFacilatatesCollaboration_businessCollaboration;
		
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
	
	public OrganizationNode getOrganization(String name) {
		OrganizationNode result = null;
		String key = OpaeumLibraryForBPMFormatter.getInstance().formatStringQualifier(name);
		result=this.organization.get(key.toString());
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=true,opaeumId=5972556763473316153l,opposite="businessNetwork",uuid="252060@_4uZ-MEvREeGmqIr8YsFD4g")
	@NumlMetaInfo(uuid="252060@_4uZ-MEvREeGmqIr8YsFD4g")
	public Set<OrganizationNode> getOrganization() {
		Set result = new HashSet<OrganizationNode>(this.organization.values());
		
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
		String key = OpaeumLibraryForBPMFormatter.getInstance().formatStringQualifier(username);
		result=this.person.get(key.toString());
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=true,opaeumId=2470938974911877691l,opposite="businessNetwork",uuid="252060@_3lOvoEvREeGmqIr8YsFD4g")
	@NumlMetaInfo(uuid="252060@_3lOvoEvREeGmqIr8YsFD4g")
	public Set<PersonNode> getPerson() {
		Set result = new HashSet<PersonNode>(this.person.values());
		
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
		if ( getOwningObject()!=null && !getOwningObject().equals(owner) ) {
			System.out.println("Reparenting "+getClass().getSimpleName() +getId());
		}
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
		setDeletedOn(new Date(System.currentTimeMillis()));
		for ( OrganizationNode child : new ArrayList<OrganizationNode>(getOrganization()) ) {
			child.markDeleted();
		}
		for ( PersonNode child : new ArrayList<PersonNode>(getPerson()) ) {
			child.markDeleted();
		}
		for ( IBusinessCollaboration child : new ArrayList<IBusinessCollaboration>(getBusinessCollaboration()) ) {
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
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("businessNetworkFacilatatesCollaboration_businessCollaboration") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("8697190624988594199")) ) {
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
	
	public void removeFromBusinessCollaboration(IBusinessCollaboration businessCollaboration) {
		if ( businessCollaboration!=null ) {
			z_internalRemoveFromBusinessCollaboration(businessCollaboration);
			businessCollaboration.markDeleted();
		}
	}
	
	public void removeFromBusinessNetworkFacilatatesCollaboration_businessCollaboration(BusinessNetworkFacilatatesCollaboration businessNetworkFacilatatesCollaboration_businessCollaboration) {
		if ( businessNetworkFacilatatesCollaboration_businessCollaboration!=null ) {
			businessNetworkFacilatatesCollaboration_businessCollaboration.z_internalRemoveFromBusinessNetwork(this);
			z_internalRemoveFromBusinessNetworkFacilatatesCollaboration_businessCollaboration(businessNetworkFacilatatesCollaboration_businessCollaboration);
		}
	}
	
	public void removeFromOrganization(String name, OrganizationNode organization) {
		if ( organization!=null ) {
			organization.z_internalRemoveFromBusinessNetwork(this);
			z_internalRemoveFromOrganization(name,organization);
		}
	}
	
	public void removeFromOwningObject() {
		this.markDeleted();
	}
	
	public void removeFromPerson(String username, PersonNode person) {
		if ( person!=null ) {
			person.z_internalRemoveFromBusinessNetwork(this);
			z_internalRemoveFromPerson(username,person);
		}
	}
	
	public void removePropertyChangeListener(String property, PropertyChangeListener listener) {
		propertyChangeSupport.removePropertyChangeListener(property,listener);
	}
	
	public void setBusinessCollaboration(Set<IBusinessCollaboration> businessCollaboration) {
		propertyChangeSupport.firePropertyChange("businessCollaboration",getBusinessCollaboration(),businessCollaboration);
		this.clearBusinessCollaboration();
		this.addAllToBusinessCollaboration(businessCollaboration);
	}
	
	public void setBusinessNetworkFacilatatesCollaboration_businessCollaboration(Set<BusinessNetworkFacilatatesCollaboration> businessNetworkFacilatatesCollaboration_businessCollaboration) {
		propertyChangeSupport.firePropertyChange("businessNetworkFacilatatesCollaboration_businessCollaboration",getBusinessNetworkFacilatatesCollaboration_businessCollaboration(),businessNetworkFacilatatesCollaboration_businessCollaboration);
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
		sb.append("\n<businessNetworkFacilatatesCollaboration_businessCollaboration propertyId=\"8697190624988594199\">");
		for ( BusinessNetworkFacilatatesCollaboration businessNetworkFacilatatesCollaboration_businessCollaboration : getBusinessNetworkFacilatatesCollaboration_businessCollaboration() ) {
			sb.append("\n" + businessNetworkFacilatatesCollaboration_businessCollaboration.toXmlReferenceString());
		}
		sb.append("\n</businessNetworkFacilatatesCollaboration_businessCollaboration>");
		sb.append("\n</BusinessNetwork>");
		return sb.toString();
	}
	
	public void z_internalAddToBusinessCollaboration(IBusinessCollaboration businessCollaboration) {
		BusinessNetworkFacilatatesCollaboration newOne;
		if ( getBusinessCollaboration().contains(businessCollaboration) ) {
			return;
		}
		newOne = new BusinessNetworkFacilatatesCollaboration(this,businessCollaboration);
		this.z_internalAddToBusinessNetworkFacilatatesCollaboration_businessCollaboration(newOne);
		newOne.getBusinessCollaboration().z_internalAddToBusinessNetworkFacilatatesCollaboration_businessNetwork(newOne);
	}
	
	public void z_internalAddToBusinessNetworkFacilatatesCollaboration_businessCollaboration(BusinessNetworkFacilatatesCollaboration businessNetworkFacilatatesCollaboration_businessCollaboration) {
		if ( getBusinessNetworkFacilatatesCollaboration_businessCollaboration().contains(businessNetworkFacilatatesCollaboration_businessCollaboration) ) {
			return;
		}
		this.businessNetworkFacilatatesCollaboration_businessCollaboration.add(businessNetworkFacilatatesCollaboration_businessCollaboration);
	}
	
	public void z_internalAddToOrganization(String name, OrganizationNode organization) {
		String key = OpaeumLibraryForBPMFormatter.getInstance().formatStringQualifier(name);
		if ( getOrganization().contains(organization) ) {
			return;
		}
		organization.z_internalAddToName(name);
		this.organization.put(key.toString(),organization);
		organization.setZ_keyOfOrganizationOnBusinessNetwork(key.toString());
	}
	
	public void z_internalAddToPerson(String username, PersonNode person) {
		String key = OpaeumLibraryForBPMFormatter.getInstance().formatStringQualifier(username);
		if ( getPerson().contains(person) ) {
			return;
		}
		person.z_internalAddToUsername(username);
		this.person.put(key.toString(),person);
		person.setZ_keyOfPersonOnBusinessNetwork(key.toString());
	}
	
	public void z_internalRemoveFromBusinessCollaboration(IBusinessCollaboration businessCollaboration) {
		for ( BusinessNetworkFacilatatesCollaboration cur : new HashSet<BusinessNetworkFacilatatesCollaboration>(this.businessNetworkFacilatatesCollaboration_businessCollaboration) ) {
			if ( cur.getBusinessCollaboration().equals(businessCollaboration) ) {
				cur.clear();
				break;
			}
		}
	}
	
	public void z_internalRemoveFromBusinessNetworkFacilatatesCollaboration_businessCollaboration(BusinessNetworkFacilatatesCollaboration businessNetworkFacilatatesCollaboration_businessCollaboration) {
		this.businessNetworkFacilatatesCollaboration_businessCollaboration.remove(businessNetworkFacilatatesCollaboration_businessCollaboration);
	}
	
	public void z_internalRemoveFromOrganization(String name, OrganizationNode organization) {
		String key = OpaeumLibraryForBPMFormatter.getInstance().formatStringQualifier(name);
		this.organization.remove(key.toString());
	}
	
	public void z_internalRemoveFromPerson(String username, PersonNode person) {
		String key = OpaeumLibraryForBPMFormatter.getInstance().formatStringQualifier(username);
		this.person.remove(key.toString());
	}

}