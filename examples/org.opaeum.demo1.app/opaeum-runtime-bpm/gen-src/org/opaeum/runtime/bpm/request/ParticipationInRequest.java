package org.opaeum.runtime.bpm.request;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;

import org.hibernate.annotations.AccessType;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.Index;
import org.hibernate.annotations.Type;
import org.opaeum.annotation.NumlMetaInfo;
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
import org.opaeum.runtime.persistence.AbstractPersistence;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

@NumlMetaInfo(applicationIdentifier="demo1",uuid="252060@_GVhgMI6NEeCrtavWRHwoHg")
@Filter(name="noDeletedObjects")
@org.hibernate.annotations.Entity(dynamicUpdate=true)
@AccessType(	"field")
@Table(name="participation_in_request",schema="bpm")
@Entity(name="ParticipationInRequest")
@DiscriminatorValue(	"participation_in_request")
public class ParticipationInRequest extends Participation implements IPersistentObject, IEventGenerator, HibernateEntity, CompositionNode, Serializable {
	@Transient
	private Set<CancelledEvent> cancelledEvents = new HashSet<CancelledEvent>();
		// Initialise to 1000 from 1970
	@Temporal(	javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="deleted_on")
	private Date deletedOn = Stdlib.FUTURE;
	@Type(type="org.opaeum.runtime.bpm.request.RequestParticipationKindResolver")
	@Column(name="kind",nullable=true)
	protected RequestParticipationKind kind;
	static private Set<? extends ParticipationInRequest> mockedAllInstances;
	@Transient
	private Set<OutgoingEvent> outgoingEvents = new HashSet<OutgoingEvent>();
	@Transient
	private InternalHibernatePersistence persistence;
	@Transient
	private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
	@Index(columnNames="request_id",name="idx_participation_in_request_request_id")
	@ManyToOne(fetch=javax.persistence.FetchType.LAZY)
	@JoinColumn(name="request_id",nullable=true)
	protected AbstractRequest request;
	static final private long serialVersionUID = 7415808214485911862l;

	/** This constructor is intended for easy initialization in unit tests
	 * 
	 * @param owningObject 
	 */
	public ParticipationInRequest(AbstractRequest owningObject) {
		init(owningObject);
		addToOwningObject();
	}
	
	/** Default constructor for ParticipationInRequest
	 */
	public ParticipationInRequest() {
	}

	public void addPropertyChangeListener(String property, PropertyChangeListener listener) {
		propertyChangeSupport.addPropertyChangeListener(property,listener);
	}
	
	/** Call this method when you want to attach this object to the containment tree. Useful with transitive persistence
	 */
	public void addToOwningObject() {
		getRequest().z_internalAddToParticipationInRequest((ParticipationInRequest)this);
	}
	
	static public Set<? extends ParticipationInRequest> allInstances(AbstractPersistence persistence) {
		if ( mockedAllInstances==null ) {
			return new HashSet(persistence.readAll(org.opaeum.runtime.bpm.request.ParticipationInRequest.class));
		} else {
			return mockedAllInstances;
		}
	}
	
	public void buildTreeFromXml(Element xml, Map<String, Object> map) {
		setUid(xml.getAttribute("uid"));
		if ( xml.getAttribute("kind").length()>0 ) {
			setKind(RequestParticipationKind.valueOf(xml.getAttribute("kind")));
		}
		NodeList propertyNodes = xml.getChildNodes();
		int i = 0;
		while ( i<propertyNodes.getLength() ) {
			Node currentPropertyNode = propertyNodes.item(i++);
		
		}
	}
	
	public void copyShallowState(ParticipationInRequest from, ParticipationInRequest to) {
		to.setKind(from.getKind());
	}
	
	public void copyState(ParticipationInRequest from, ParticipationInRequest to) {
		to.setKind(from.getKind());
	}
	
	public void createComponents() {
		super.createComponents();
	}
	
	public boolean equals(Object other) {
		if ( other instanceof ParticipationInRequest ) {
			return other==this || ((ParticipationInRequest)other).getUid().equals(this.getUid());
		}
		return false;
	}
	
	public Set<CancelledEvent> getCancelledEvents() {
		return this.cancelledEvents;
	}
	
	public Date getDeletedOn() {
		return this.deletedOn;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=3310250841268333114l,opposite="participationInRequest",uuid="252060@_cATKlI6NEeCrtavWRHwoHg")
	@NumlMetaInfo(uuid="252060@_cATKlI6NEeCrtavWRHwoHg")
	public RequestParticipationKind getKind() {
		RequestParticipationKind result = this.kind;
		
		return result;
	}
	
	public String getName() {
		return "ParticipationInRequest["+getId()+"]";
	}
	
	public Set<OutgoingEvent> getOutgoingEvents() {
		return this.outgoingEvents;
	}
	
	public CompositionNode getOwningObject() {
		return getRequest();
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=440094146247750780l,opposite="participationInRequest",uuid="252060@_XLVmwY6NEeCrtavWRHwoHg")
	@NumlMetaInfo(uuid="252060@_XLVmwY6NEeCrtavWRHwoHg")
	public AbstractRequest getRequest() {
		AbstractRequest result = this.request;
		
		return result;
	}
	
	public int hashCode() {
		return getUid().hashCode();
	}
	
	public void init(CompositionNode owner) {
		if ( getOwningObject()!=null && !getOwningObject().equals(owner) ) {
			System.out.println("Reparenting "+getClass().getSimpleName() +getId());
		}
		super.init(owner);
		this.z_internalAddToRequest((AbstractRequest)owner);
	}
	
	public ParticipationInRequest makeCopy() {
		ParticipationInRequest result = new ParticipationInRequest();
		copyState((ParticipationInRequest)this,result);
		return result;
	}
	
	public ParticipationInRequest makeShallowCopy() {
		ParticipationInRequest result = new ParticipationInRequest();
		copyShallowState((ParticipationInRequest)this,result);
		result.setId(this.getId());
		return result;
	}
	
	public void markDeleted() {
		super.markDeleted();
		if ( getParticipant()!=null ) {
			getParticipant().z_internalRemoveFromParticipation(this);
		}
		if ( getRequest()!=null ) {
			getRequest().z_internalRemoveFromParticipationInRequest(this);
		}
		if ( getParticipationParticipant_participant()!=null ) {
			getParticipationParticipant_participant().z_internalRemoveFromParticipation(this);
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
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("participationParticipant_participant") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("7938655185881125361")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						setParticipationParticipant_participant((ParticipationParticipant)map.get(((Element)currentPropertyValueNode).getAttribute("uid")));
					}
				}
			}
		}
	}
	
	public void removeFromOwningObject() {
		this.markDeleted();
	}
	
	public void removePropertyChangeListener(String property, PropertyChangeListener listener) {
		propertyChangeSupport.removePropertyChangeListener(property,listener);
	}
	
	public void setCancelledEvents(Set<CancelledEvent> cancelledEvents) {
		this.cancelledEvents=cancelledEvents;
	}
	
	public void setDeletedOn(Date deletedOn) {
		this.deletedOn=deletedOn;
		super.setDeletedOn(deletedOn);
	}
	
	public void setKind(RequestParticipationKind kind) {
		propertyChangeSupport.firePropertyChange("kind",getKind(),kind);
		this.z_internalAddToKind(kind);
	}
	
	public void setOutgoingEvents(Set<OutgoingEvent> outgoingEvents) {
		this.outgoingEvents=outgoingEvents;
	}
	
	public void setRequest(AbstractRequest request) {
		propertyChangeSupport.firePropertyChange("request",getRequest(),request);
		if ( this.getRequest()!=null ) {
			this.getRequest().z_internalRemoveFromParticipationInRequest(this);
		}
		this.z_internalAddToRequest(request);
		if ( request!=null ) {
			request.z_internalAddToParticipationInRequest(this);
			setDeletedOn(Stdlib.FUTURE);
		} else {
			markDeleted();
		}
	}
	
	public String toXmlReferenceString() {
		return "<ParticipationInRequest uid=\""+getUid() + "\"/>";
	}
	
	public String toXmlString() {
		StringBuilder sb = new StringBuilder();
		sb.append("<ParticipationInRequest ");
		sb.append("classUuid=\"252060@_GVhgMI6NEeCrtavWRHwoHg\" ");
		sb.append("className=\"org.opaeum.runtime.bpm.request.ParticipationInRequest\" ");
		sb.append("uid=\"" + this.getUid() + "\" ");
		if ( getKind()!=null ) {
			sb.append("kind=\""+ getKind().name() + "\" ");
		}
		sb.append(">");
		if ( getParticipationParticipant_participant()==null ) {
			sb.append("\n<participationParticipant_participant/>");
		} else {
			sb.append("\n<participationParticipant_participant propertyId=\"7938655185881125361\">");
			sb.append("\n" + getParticipationParticipant_participant().toXmlReferenceString());
			sb.append("\n</participationParticipant_participant>");
		}
		sb.append("\n</ParticipationInRequest>");
		return sb.toString();
	}
	
	public void z_internalAddToKind(RequestParticipationKind kind) {
		if ( kind.equals(getKind()) ) {
			return;
		}
		this.kind=kind;
	}
	
	public void z_internalAddToRequest(AbstractRequest request) {
		if ( request.equals(getRequest()) ) {
			return;
		}
		this.request=request;
	}
	
	public void z_internalRemoveFromKind(RequestParticipationKind kind) {
		if ( getKind()!=null && kind!=null && kind.equals(getKind()) ) {
			this.kind=null;
			this.kind=null;
		}
	}
	
	public void z_internalRemoveFromRequest(AbstractRequest request) {
		if ( getRequest()!=null && request!=null && request.equals(getRequest()) ) {
			this.request=null;
			this.request=null;
		}
	}

}