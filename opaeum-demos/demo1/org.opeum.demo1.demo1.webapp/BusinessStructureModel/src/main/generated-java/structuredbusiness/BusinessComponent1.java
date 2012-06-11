package structuredbusiness;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.hibernate.annotations.AccessType;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.Index;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.Where;
import org.opaeum.annotation.BusinessComponent;
import org.opaeum.annotation.NumlMetaInfo;
import org.opaeum.annotation.PropertyMetaInfo;
import org.opaeum.audit.AuditMe;
import org.opaeum.runtime.bpm.organization.IBusiness;
import org.opaeum.runtime.bpm.organization.IBusinessCollaboration;
import org.opaeum.runtime.bpm.organization.IBusinessComponent;
import org.opaeum.runtime.bpm.organization.OrganizationNode;
import org.opaeum.runtime.bpm.organization.Organization_iBusinessComponent_1;
import org.opaeum.runtime.bpm.request.AbstractRequest;
import org.opaeum.runtime.bpm.request.Participation;
import org.opaeum.runtime.bpm.request.ParticipationInRequest;
import org.opaeum.runtime.bpm.request.ParticipationInTask;
import org.opaeum.runtime.bpm.request.RequestParticipationKind;
import org.opaeum.runtime.bpm.request.TaskRequest;
import org.opaeum.runtime.domain.CancelledEvent;
import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.runtime.domain.HibernateEntity;
import org.opaeum.runtime.domain.IEventGenerator;
import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.runtime.domain.IntrospectionUtil;
import org.opaeum.runtime.domain.OutgoingEvent;
import org.opaeum.runtime.environment.Environment;
import org.opaeum.runtime.organization.IOrganizationNode;
import org.opaeum.runtime.persistence.AbstractPersistence;
import org.opaeum.runtime.persistence.CmtPersistence;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import structuredbusiness.util.Stdlib;
import structuredbusiness.util.StructuredbusinessFormatter;

@AuditMe
@NumlMetaInfo(uuid="914890@_ZXPEIKmhEeGU9ckjWaJA9g")
@BusinessComponent(businessRoles={},isRoot=true)
@Filter(name="noDeletedObjects")
@org.hibernate.annotations.Entity(dynamicUpdate=true)
@AccessType(	"field")
@Table(name="business_component1")
@Inheritance(strategy=javax.persistence.InheritanceType.JOINED)
@Entity(name="BusinessComponent1")
@DiscriminatorColumn(discriminatorType=javax.persistence.DiscriminatorType.STRING,name="type_descriminator")
public class BusinessComponent1 implements IPersistentObject, IEventGenerator, HibernateEntity, CompositionNode, IBusinessComponent, IBusiness, Serializable {
	@Transient
	private Set<CancelledEvent> cancelledEvents = new HashSet<CancelledEvent>();
		// Initialise to 1000 from 1970
	@Temporal(	javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="deleted_on")
	private Date deletedOn = Stdlib.FUTURE;
	@Id
	@GeneratedValue(strategy=javax.persistence.GenerationType.TABLE)
	private Long id;
	static private Set<BusinessComponent1> mockedAllInstances;
	@Version
	@Column(name="object_version")
	private int objectVersion;
	@OneToOne(cascade=javax.persistence.CascadeType.ALL,fetch=javax.persistence.FetchType.LAZY)
	@JoinColumn(name="organization_i_business_component_1_represented_organization_id",nullable=true)
	private Organization_iBusinessComponent_1 organization_iBusinessComponent_1_representedOrganization;
	@Transient
	private Set<OutgoingEvent> outgoingEvents = new HashSet<OutgoingEvent>();
	@Where(clause="participant_type='914890@_ZXPEIKmhEeGU9ckjWaJA9g'")
	@LazyCollection(	org.hibernate.annotations.LazyCollectionOption.TRUE)
	@Filter(condition="deleted_on > current_timestamp",name="noDeletedObjects")
	@OneToMany(fetch=javax.persistence.FetchType.LAZY,targetEntity=Participation.class)
	@JoinColumn(name="participation_id",nullable=true)
	private Set<Participation> participation = new HashSet<Participation>();
	@Transient
	private AbstractPersistence persistence;
	@Transient
	private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
	@Index(columnNames="root_id",name="idx_business_component1_root_id")
	@ManyToOne(fetch=javax.persistence.FetchType.LAZY)
	@JoinColumn(name="root_id",nullable=true)
	private Structuredbusiness root;
	static final private long serialVersionUID = 1718023009671694566l;
	private String uid;

	/** This constructor is intended for easy initialization in unit tests
	 * 
	 * @param owningObject 
	 */
	public BusinessComponent1(Structuredbusiness owningObject) {
		init(owningObject);
		addToOwningObject();
	}
	
	/** Default constructor for BusinessComponent1
	 */
	public BusinessComponent1() {
	}

	public void addAllToParticipation(Set<Participation> participation) {
		for ( Participation o : participation ) {
			addToParticipation(o);
		}
	}
	
	public void addPropertyChangeListener(String property, PropertyChangeListener listener) {
		propertyChangeSupport.addPropertyChangeListener(property,listener);
	}
	
	/** Call this method when you want to attach this object to the containment tree. Useful with transitive persistence
	 */
	public void addToOwningObject() {
		getRoot().z_internalAddToBusinessComponent1((BusinessComponent1)this);
	}
	
	public void addToParticipation(Participation participation) {
		if ( participation!=null ) {
			participation.z_internalRemoveFromParticipant(participation.getParticipant());
			participation.z_internalAddToParticipant(this);
			z_internalAddToParticipation(participation);
		}
	}
	
	static public Set<? extends BusinessComponent1> allInstances() {
		if ( mockedAllInstances==null ) {
			CmtPersistence session =org.opaeum.runtime.environment.Environment.getInstance().getComponent(CmtPersistence.class);
			return new HashSet(session.readAll(structuredbusiness.BusinessComponent1.class));
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
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("organization_iBusinessComponent_1_representedOrganization") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("5756915452752219728")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						Organization_iBusinessComponent_1 curVal;
						try {
							curVal=IntrospectionUtil.newInstance(((Element)currentPropertyValueNode).getAttribute("className"));
						} catch (Exception e) {
							curVal=Environment.getInstance().getMetaInfoMap().newInstance(((Element)currentPropertyValueNode).getAttribute("classUuid"));
						}
						curVal.buildTreeFromXml((Element)currentPropertyValueNode,map);
						this.setOrganization_iBusinessComponent_1_representedOrganization(curVal);
						map.put(curVal.getUid(), curVal);
					}
				}
			}
		}
	}
	
	public void clearParticipation() {
		removeAllFromParticipation(getParticipation());
	}
	
	public void copyShallowState(BusinessComponent1 from, BusinessComponent1 to) {
	}
	
	public void copyState(BusinessComponent1 from, BusinessComponent1 to) {
	}
	
	public void createComponents() {
	}
	
	public Organization_iBusinessComponent_1 createOrganization_iBusinessComponent_1_representedOrganization() {
		Organization_iBusinessComponent_1 newInstance= new Organization_iBusinessComponent_1();
		newInstance.init(this);
		return newInstance;
	}
	
	public boolean equals(Object other) {
		if ( other instanceof BusinessComponent1 ) {
			return other==this || ((BusinessComponent1)other).getUid().equals(this.getUid());
		}
		return false;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=2952021989536159761l,opposite="business",uuid="252060@_Rj0oE1YkEeGJUqEGX7bKSg")
	@NumlMetaInfo(uuid="252060@_Rj0oE1YkEeGJUqEGX7bKSg")
	public IBusinessCollaboration getBusinessCollaboration() {
		IBusinessCollaboration result = null;
		if ( this.getRoot()!=null ) {
			result=this.getRoot();
		}
		return result;
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
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=6185666218388591493l,uuid="252060@_rz7zsI6TEeCne5ArYLDbiA")
	@NumlMetaInfo(uuid="252060@_rz7zsI6TEeCne5ArYLDbiA")
	public Collection<AbstractRequest> getInitiatedRequests() {
		Collection<AbstractRequest> result = collect9();
		
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=5635486542671558270l,uuid="252060@_7MraII6lEeCFsPOcAnk69Q")
	@NumlMetaInfo(uuid="252060@_7MraII6lEeCFsPOcAnk69Q")
	public Collection<AbstractRequest> getInterestingRequests() {
		Collection<AbstractRequest> result = collect2();
		
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=5447021495172291044l,uuid="252060@_jSstQI6lEeCFsPOcAnk69Q")
	@NumlMetaInfo(uuid="252060@_jSstQI6lEeCFsPOcAnk69Q")
	public Collection<AbstractRequest> getManagedRequests() {
		Collection<AbstractRequest> result = collect4();
		
		return result;
	}
	
	public String getName() {
		return "BusinessComponent1["+getId()+"]";
	}
	
	public int getObjectVersion() {
		return this.objectVersion;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=true,opaeumId=5756915452752219728l,opposite="businessComponent",uuid="252060@_vf4noFYuEeGj5_I7bIwNoA")
	@NumlMetaInfo(uuid="252060@_vf4noVYuEeGj5_I7bIwNoA252060@_vf4noFYuEeGj5_I7bIwNoA")
	public Organization_iBusinessComponent_1 getOrganization_iBusinessComponent_1_representedOrganization() {
		Organization_iBusinessComponent_1 result = this.organization_iBusinessComponent_1_representedOrganization;
		
		return result;
	}
	
	public Organization_iBusinessComponent_1 getOrganization_iBusinessComponent_1_representedOrganizationFor(OrganizationNode match) {
		if ( organization_iBusinessComponent_1_representedOrganization.getRepresentedOrganization().equals(match) ) {
			return organization_iBusinessComponent_1_representedOrganization;
		} else {
			return null;
		}
	}
	
	public Set<OutgoingEvent> getOutgoingEvents() {
		return this.outgoingEvents;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=6404162095298970578l,uuid="252060@_NYHP0I6mEeCFsPOcAnk69Q")
	@NumlMetaInfo(uuid="252060@_NYHP0I6mEeCFsPOcAnk69Q")
	public Collection<TaskRequest> getOwnedTaskRequests() {
		Collection<TaskRequest> result = collect7();
		
		return result;
	}
	
	public CompositionNode getOwningObject() {
		return getRoot();
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=4480510548106225415l,opposite="participant",uuid="252060@_3YyGkYoXEeCPduia_-NbFw")
	@NumlMetaInfo(uuid="252060@_3YyGkYoXEeCPduia_-NbFw")
	public Set<Participation> getParticipation() {
		Set<Participation> result = this.participation;
		
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=2234431193389771664l,uuid="252060@_TfLFAJBkEeCWM9wKKqKWag")
	@NumlMetaInfo(uuid="252060@_TfLFAJBkEeCWM9wKKqKWag")
	public Collection<ParticipationInRequest> getParticipationsInRequests() {
		Collection<ParticipationInRequest> result = collect6();
		
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=6858863738991536174l,uuid="252060@_DIGv8JBkEeCWM9wKKqKWag")
	@NumlMetaInfo(uuid="252060@_DIGv8JBkEeCWM9wKKqKWag")
	public Collection<ParticipationInTask> getParticipationsInTasks() {
		Collection<ParticipationInTask> result = collect11();
		
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,lookupMethod="getSourcePopulationForRepresentedOrganization",opaeumId=8314504260854280851l,opposite="businessComponent",uuid="252060@_vf4noVYuEeGj5_I7bIwNoA")
	public OrganizationNode getRepresentedOrganization() {
		OrganizationNode result = null;
		if ( this.organization_iBusinessComponent_1_representedOrganization!=null ) {
			result = this.organization_iBusinessComponent_1_representedOrganization.getRepresentedOrganization();
		}
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=7737100568581358598l,opposite="businessComponent1",uuid="914890@_-VLbkE8VEeGA3PFuQY5w7QNakedBusinessCollaborationNakedBusinessCollaboration")
	@NumlMetaInfo(uuid="914890@_-VLbkE8VEeGA3PFuQY5w7QNakedBusinessCollaborationNakedBusinessCollaboration")
	public Structuredbusiness getRoot() {
		Structuredbusiness result = this.root;
		
		return result;
	}
	
	public List<OrganizationNode> getSourcePopulationForRepresentedOrganization() {
		return new ArrayList<OrganizationNode>(Stdlib.collectionAsSet(this.getRoot().getBusinessNetwork().getOrganization()));
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
		this.z_internalAddToRoot((Structuredbusiness)owner);
		createComponents();
	}
	
	public BusinessComponent1 makeCopy() {
		BusinessComponent1 result = new BusinessComponent1();
		copyState((BusinessComponent1)this,result);
		return result;
	}
	
	public BusinessComponent1 makeShallowCopy() {
		BusinessComponent1 result = new BusinessComponent1();
		copyShallowState((BusinessComponent1)this,result);
		result.setId(this.getId());
		return result;
	}
	
	public void markDeleted() {
		if ( getRepresentedOrganization()!=null ) {
			getRepresentedOrganization().z_internalRemoveFromBusinessComponent(this);
		}
		if ( getRoot()!=null ) {
			getRoot().z_internalRemoveFromBusinessComponent1(this);
		}
		setDeletedOn(new Date());
	}
	
	static public void mockAllInstances(Set<BusinessComponent1> newMocks) {
		mockedAllInstances=newMocks;
	}
	
	public void populateReferencesFromXml(Element xml, Map<String, Object> map) {
		NodeList propertyNodes = xml.getChildNodes();
		int i = 0;
		while ( i<propertyNodes.getLength() ) {
			Node currentPropertyNode = propertyNodes.item(i++);
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("organization_iBusinessComponent_1_representedOrganization") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("5756915452752219728")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						((Organization_iBusinessComponent_1)map.get(((Element)currentPropertyValueNode).getAttribute("uid"))).populateReferencesFromXml((Element)currentPropertyValueNode, map);
					}
				}
			}
		}
	}
	
	public void removeAllFromParticipation(Set<Participation> participation) {
		Set<Participation> tmp = new HashSet<Participation>(participation);
		for ( Participation o : tmp ) {
			removeFromParticipation(o);
		}
	}
	
	public void removeFromOwningObject() {
		this.markDeleted();
	}
	
	public void removeFromParticipation(Participation participation) {
		if ( participation!=null ) {
			participation.z_internalRemoveFromParticipant(this);
			z_internalRemoveFromParticipation(participation);
		}
	}
	
	public void removePropertyChangeListener(String property, PropertyChangeListener listener) {
		propertyChangeSupport.removePropertyChangeListener(property,listener);
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
	
	public void setOrganization_iBusinessComponent_1_representedOrganization(Organization_iBusinessComponent_1 organization_iBusinessComponent_1_representedOrganization) {
		Organization_iBusinessComponent_1 oldValue = this.getOrganization_iBusinessComponent_1_representedOrganization();
		propertyChangeSupport.firePropertyChange("organization_iBusinessComponent_1_representedOrganization",getOrganization_iBusinessComponent_1_representedOrganization(),organization_iBusinessComponent_1_representedOrganization);
		if ( oldValue==null ) {
			if ( organization_iBusinessComponent_1_representedOrganization!=null ) {
				BusinessComponent1 oldOther = (BusinessComponent1)organization_iBusinessComponent_1_representedOrganization.getBusinessComponent();
				organization_iBusinessComponent_1_representedOrganization.z_internalRemoveFromBusinessComponent(oldOther);
				if ( oldOther != null ) {
					oldOther.z_internalRemoveFromOrganization_iBusinessComponent_1_representedOrganization(organization_iBusinessComponent_1_representedOrganization);
				}
				organization_iBusinessComponent_1_representedOrganization.z_internalAddToBusinessComponent((BusinessComponent1)this);
			}
			this.z_internalAddToOrganization_iBusinessComponent_1_representedOrganization(organization_iBusinessComponent_1_representedOrganization);
		} else {
			if ( !oldValue.equals(organization_iBusinessComponent_1_representedOrganization) ) {
				oldValue.z_internalRemoveFromBusinessComponent(this);
				z_internalRemoveFromOrganization_iBusinessComponent_1_representedOrganization(oldValue);
				if ( organization_iBusinessComponent_1_representedOrganization!=null ) {
					BusinessComponent1 oldOther = (BusinessComponent1)organization_iBusinessComponent_1_representedOrganization.getBusinessComponent();
					organization_iBusinessComponent_1_representedOrganization.z_internalRemoveFromBusinessComponent(oldOther);
					if ( oldOther != null ) {
						oldOther.z_internalRemoveFromOrganization_iBusinessComponent_1_representedOrganization(organization_iBusinessComponent_1_representedOrganization);
					}
					organization_iBusinessComponent_1_representedOrganization.z_internalAddToBusinessComponent((BusinessComponent1)this);
				}
				this.z_internalAddToOrganization_iBusinessComponent_1_representedOrganization(organization_iBusinessComponent_1_representedOrganization);
			}
		}
	}
	
	public void setOutgoingEvents(Set<OutgoingEvent> outgoingEvents) {
		this.outgoingEvents=outgoingEvents;
	}
	
	public void setParticipation(Set<Participation> participation) {
		propertyChangeSupport.firePropertyChange("participation",getParticipation(),participation);
		this.clearParticipation();
		this.addAllToParticipation(participation);
	}
	
	public void setRepresentedOrganization(IOrganizationNode p) {
		setRepresentedOrganization((OrganizationNode)p);
	}
	
	public void setRepresentedOrganization(OrganizationNode representedOrganization) {
		propertyChangeSupport.firePropertyChange("representedOrganization",getRepresentedOrganization(),representedOrganization);
		if ( this.getRepresentedOrganization()!=null ) {
			this.getRepresentedOrganization().z_internalRemoveFromBusinessComponent(this);
		}
		if ( representedOrganization!=null ) {
			this.z_internalAddToRepresentedOrganization(representedOrganization);
		}
	}
	
	public void setRoot(Structuredbusiness root) {
		propertyChangeSupport.firePropertyChange("root",getRoot(),root);
		if ( this.getRoot()!=null ) {
			this.getRoot().z_internalRemoveFromBusinessComponent1(this);
		}
		if ( root!=null ) {
			root.z_internalAddToBusinessComponent1(this);
			this.z_internalAddToRoot(root);
			setDeletedOn(Stdlib.FUTURE);
		} else {
			markDeleted();
		}
	}
	
	public void setUid(String newUid) {
		this.uid=newUid;
	}
	
	public String toXmlReferenceString() {
		return "<BusinessComponent1 uid=\""+getUid() + "\"/>";
	}
	
	public String toXmlString() {
		StringBuilder sb = new StringBuilder();
		sb.append("<BusinessComponent1 ");
		sb.append("classUuid=\"914890@_ZXPEIKmhEeGU9ckjWaJA9g\" ");
		sb.append("className=\"structuredbusiness.BusinessComponent1\" ");
		sb.append("uid=\"" + this.getUid() + "\" ");
		sb.append(">");
		if ( getOrganization_iBusinessComponent_1_representedOrganization()==null ) {
			sb.append("\n<organization_iBusinessComponent_1_representedOrganization/>");
		} else {
			sb.append("\n<organization_iBusinessComponent_1_representedOrganization propertyId=\"5756915452752219728\">");
			sb.append("\n" + getOrganization_iBusinessComponent_1_representedOrganization().toXmlString());
			sb.append("\n</organization_iBusinessComponent_1_representedOrganization>");
		}
		sb.append("\n</BusinessComponent1>");
		return sb.toString();
	}
	
	public void z_internalAddToOrganization_iBusinessComponent_1_representedOrganization(Organization_iBusinessComponent_1 val) {
		this.organization_iBusinessComponent_1_representedOrganization=val;
	}
	
	public void z_internalAddToParticipation(Participation val) {
		this.participation.add(val);
	}
	
	public void z_internalAddToRepresentedOrganization(OrganizationNode representedOrganization) {
		Organization_iBusinessComponent_1 newOne = new Organization_iBusinessComponent_1(this,representedOrganization);
		this.z_internalAddToOrganization_iBusinessComponent_1_representedOrganization(newOne);
		newOne.getRepresentedOrganization().z_internalAddToOrganization_iBusinessComponent_1_businessComponent(newOne);
	}
	
	public void z_internalAddToRoot(Structuredbusiness val) {
		this.root=val;
	}
	
	public void z_internalRemoveFromOrganization_iBusinessComponent_1_representedOrganization(Organization_iBusinessComponent_1 val) {
		if ( getOrganization_iBusinessComponent_1_representedOrganization()!=null && val!=null && val.equals(getOrganization_iBusinessComponent_1_representedOrganization()) ) {
			this.organization_iBusinessComponent_1_representedOrganization=null;
			this.organization_iBusinessComponent_1_representedOrganization=null;
		}
	}
	
	public void z_internalRemoveFromParticipation(Participation val) {
		this.participation.remove(val);
	}
	
	public void z_internalRemoveFromRepresentedOrganization(OrganizationNode representedOrganization) {
		if ( this.organization_iBusinessComponent_1_representedOrganization!=null ) {
			this.organization_iBusinessComponent_1_representedOrganization.clear();
		}
	}
	
	public void z_internalRemoveFromRoot(Structuredbusiness val) {
		if ( getRoot()!=null && val!=null && val.equals(getRoot()) ) {
			this.root=null;
			this.root=null;
		}
	}
	
	/** Implements ->collect( i_Participation : Participation | i_Participation.oclAsType(ParticipationInTask) )
	 */
	private Collection<ParticipationInTask> collect11() {
		Collection<ParticipationInTask> result = new ArrayList<ParticipationInTask>();
		for ( Participation i_Participation : select10() ) {
			ParticipationInTask bodyExpResult = ((ParticipationInTask) i_Participation);
			if ( bodyExpResult != null ) result.add( bodyExpResult );
		}
		return result;
	}
	
	/** Implements ->collect( i_ParticipationInRequest : ParticipationInRequest | i_ParticipationInRequest.request )
	 */
	private Collection<AbstractRequest> collect2() {
		Collection<AbstractRequest> result = new ArrayList<AbstractRequest>();
		for ( ParticipationInRequest i_ParticipationInRequest : select1() ) {
			AbstractRequest bodyExpResult = i_ParticipationInRequest.getRequest();
			if ( bodyExpResult != null ) result.add( bodyExpResult );
		}
		return result;
	}
	
	/** Implements ->collect( i_ParticipationInRequest : ParticipationInRequest | i_ParticipationInRequest.request )
	 */
	private Collection<AbstractRequest> collect4() {
		Collection<AbstractRequest> result = new ArrayList<AbstractRequest>();
		for ( ParticipationInRequest i_ParticipationInRequest : select3() ) {
			AbstractRequest bodyExpResult = i_ParticipationInRequest.getRequest();
			if ( bodyExpResult != null ) result.add( bodyExpResult );
		}
		return result;
	}
	
	/** Implements ->collect( i_Participation : Participation | i_Participation.oclAsType(ParticipationInRequest) )
	 */
	private Collection<ParticipationInRequest> collect6() {
		Collection<ParticipationInRequest> result = new ArrayList<ParticipationInRequest>();
		for ( Participation i_Participation : select5() ) {
			ParticipationInRequest bodyExpResult = ((ParticipationInRequest) i_Participation);
			if ( bodyExpResult != null ) result.add( bodyExpResult );
		}
		return result;
	}
	
	/** Implements ->collect( i_ParticipationInTask : ParticipationInTask | i_ParticipationInTask.taskRequest )
	 */
	private Collection<TaskRequest> collect7() {
		Collection<TaskRequest> result = new ArrayList<TaskRequest>();
		for ( ParticipationInTask i_ParticipationInTask : this.getParticipationsInTasks() ) {
			TaskRequest bodyExpResult = i_ParticipationInTask.getTaskRequest();
			if ( bodyExpResult != null ) result.add( bodyExpResult );
		}
		return result;
	}
	
	/** Implements ->collect( i_ParticipationInRequest : ParticipationInRequest | i_ParticipationInRequest.request )
	 */
	private Collection<AbstractRequest> collect9() {
		Collection<AbstractRequest> result = new ArrayList<AbstractRequest>();
		for ( ParticipationInRequest i_ParticipationInRequest : select8() ) {
			AbstractRequest bodyExpResult = i_ParticipationInRequest.getRequest();
			if ( bodyExpResult != null ) result.add( bodyExpResult );
		}
		return result;
	}
	
	/** Implements ->select( i_ParticipationInRequest : ParticipationInRequest | i_ParticipationInRequest.kind = RequestParticipationKind::stakeholder )
	 */
	private Collection<ParticipationInRequest> select1() {
		Collection<ParticipationInRequest> result = new ArrayList<ParticipationInRequest>();
		for ( ParticipationInRequest i_ParticipationInRequest : this.getParticipationsInRequests() ) {
			if ( (i_ParticipationInRequest.getKind().equals( RequestParticipationKind.STAKEHOLDER)) ) {
				result.add( i_ParticipationInRequest );
			}
		}
		return result;
	}
	
	/** Implements ->select( i_Participation : Participation | i_Participation.oclIsKindOf(ParticipationInTask) )
	 */
	private Set<Participation> select10() {
		Set<Participation> result = new HashSet<Participation>();
		for ( Participation i_Participation : this.getParticipation() ) {
			if ( (i_Participation instanceof ParticipationInTask) ) {
				result.add( i_Participation );
			}
		}
		return result;
	}
	
	/** Implements ->select( i_ParticipationInRequest : ParticipationInRequest | i_ParticipationInRequest.kind = RequestParticipationKind::businessOwner )
	 */
	private Collection<ParticipationInRequest> select3() {
		Collection<ParticipationInRequest> result = new ArrayList<ParticipationInRequest>();
		for ( ParticipationInRequest i_ParticipationInRequest : this.getParticipationsInRequests() ) {
			if ( (i_ParticipationInRequest.getKind().equals( RequestParticipationKind.BUSINESSOWNER)) ) {
				result.add( i_ParticipationInRequest );
			}
		}
		return result;
	}
	
	/** Implements ->select( i_Participation : Participation | i_Participation.oclIsKindOf(ParticipationInRequest) )
	 */
	private Set<Participation> select5() {
		Set<Participation> result = new HashSet<Participation>();
		for ( Participation i_Participation : this.getParticipation() ) {
			if ( (i_Participation instanceof ParticipationInRequest) ) {
				result.add( i_Participation );
			}
		}
		return result;
	}
	
	/** Implements ->select( i_ParticipationInRequest : ParticipationInRequest | i_ParticipationInRequest.kind = RequestParticipationKind::initiator )
	 */
	private Collection<ParticipationInRequest> select8() {
		Collection<ParticipationInRequest> result = new ArrayList<ParticipationInRequest>();
		for ( ParticipationInRequest i_ParticipationInRequest : this.getParticipationsInRequests() ) {
			if ( (i_ParticipationInRequest.getKind().equals( RequestParticipationKind.INITIATOR)) ) {
				result.add( i_ParticipationInRequest );
			}
		}
		return result;
	}

}