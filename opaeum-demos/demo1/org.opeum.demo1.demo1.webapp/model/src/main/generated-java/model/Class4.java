package model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.ArrayList;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;
import javax.persistence.Version;

import model.util.ModelFormatter;
import model.util.Stdlib;

import org.hibernate.annotations.AccessType;
import org.hibernate.annotations.Filter;
import org.opaeum.annotation.NumlMetaInfo;
import org.opaeum.annotation.ParameterMetaInfo;
import org.opaeum.annotation.PropertyMetaInfo;
import org.opaeum.audit.AuditMe;
import org.opaeum.runtime.domain.CancelledEvent;
import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.runtime.domain.FailedConstraintsException;
import org.opaeum.runtime.domain.HibernateEntity;
import org.opaeum.runtime.domain.IEventGenerator;
import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.runtime.domain.IntrospectionUtil;
import org.opaeum.runtime.domain.OutgoingEvent;
import org.opaeum.runtime.environment.Environment;
import org.opaeum.runtime.environment.SimpleTypeRuntimeStrategyFactory;
import org.opaeum.runtime.persistence.AbstractPersistence;
import org.opaeum.runtime.persistence.CmtPersistence;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

@AuditMe
@NumlMetaInfo(uuid="model.uml@_iEtO4LkpEeGPU4u_bLx6fA")
@Filter(name="noDeletedObjects")
@org.hibernate.annotations.Entity(dynamicUpdate=true)
@AccessType(	"field")
@Table(name="class4")
@Inheritance(strategy=javax.persistence.InheritanceType.JOINED)
@Entity(name="Class4")
@DiscriminatorColumn(discriminatorType=javax.persistence.DiscriminatorType.STRING,name="type_descriminator")
public class Class4 implements IPersistentObject, IEventGenerator, HibernateEntity, CompositionNode, Signal1Receiver, Interface12, Serializable {
	@Column(name="attribute1")
	private Boolean attribute1;
	@Transient
	private Set<CancelledEvent> cancelledEvents = new HashSet<CancelledEvent>();
		// Initialise to 1000 from 1970
	@Temporal(	javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="deleted_on")
	private Date deletedOn = Stdlib.FUTURE;
	@Id
	@GeneratedValue(strategy=javax.persistence.GenerationType.TABLE)
	private Long id;
	static private Set<Class4> mockedAllInstances;
	@Version
	@Column(name="object_version")
	private int objectVersion;
	@Transient
	private Set<OutgoingEvent> outgoingEvents = new HashSet<OutgoingEvent>();
	@Transient
	private AbstractPersistence persistence;
	@Transient
	private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
	static final private long serialVersionUID = 4118350281343581130l;
	private String uid;

	/** Default constructor for Class4
	 */
	public Class4() {
	}

	@NumlMetaInfo(uuid="model.uml@_pl7T4LlFEeG-Ou4fV0X62w")
	public Integer Operation1(@ParameterMetaInfo(name="param1",opaeumId=2312122819401591160l,uuid="model.uml@_A2bl8LlGEeG-Ou4fV0X62w") Integer param1) {
		Integer result = ((1 + 2) + param1);
		
		return result;
	}
	
	@NumlMetaInfo(uuid="model.uml@_ke1H8LkpEeGPU4u_bLx6fA")
	public Integer Operation12() throws FailedConstraintsException {
		Integer result = (123 + this.Operation12());
		List<String> failedConstraints = new ArrayList<String>();
		if ( !((this.getAttribute1() || this.getAttribute1()) && false) ) {
			failedConstraints.add("model::class4::Operation12::newConstraint");
		}
		if ( failedConstraints.size()>0 ) {
			throw new FailedConstraintsException(true,failedConstraints);
		}
		return result;
	}
	
	public void addPropertyChangeListener(String property, PropertyChangeListener listener) {
		propertyChangeSupport.addPropertyChangeListener(property,listener);
	}
	
	/** Call this method when you want to attach this object to the containment tree. Useful with transitive persistence
	 */
	public void addToOwningObject() {
	}
	
	static public Set<? extends Class4> allInstances() {
		if ( mockedAllInstances==null ) {
			CmtPersistence session =org.opaeum.runtime.environment.Environment.getInstance().getComponent(CmtPersistence.class);
			return new HashSet(session.readAll(model.Class4.class));
		} else {
			return mockedAllInstances;
		}
	}
	
	public void buildTreeFromXml(Element xml, Map<String, Object> map) {
		setUid(xml.getAttribute("uid"));
		if ( xml.getAttribute("attribute1").length()>0 ) {
			setAttribute1(ModelFormatter.getInstance().parseBoolean(xml.getAttribute("attribute1")));
		}
		NodeList propertyNodes = xml.getChildNodes();
		int i = 0;
		while ( i<propertyNodes.getLength() ) {
			Node currentPropertyNode = propertyNodes.item(i++);
		
		}
	}
	
	public boolean consumeSignal1Event(Signal1 signal) {
		boolean consumed = false;
		return consumed;
	}
	
	public void copyShallowState(Class4 from, Class4 to) {
		to.setAttribute1(from.getAttribute1());
	}
	
	public void copyState(Class4 from, Class4 to) {
		to.setAttribute1(from.getAttribute1());
	}
	
	public void createComponents() {
	}
	
	public boolean equals(Object other) {
		if ( other instanceof Class4 ) {
			return other==this || ((Class4)other).getUid().equals(this.getUid());
		}
		return false;
	}
	
	public void generateSignal1Event(Signal1 signal) {
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=6358566459059977368l,strategyFactory=SimpleTypeRuntimeStrategyFactory.class,uuid="model.uml@_heaygLlLEeG-Ou4fV0X62w")
	@NumlMetaInfo(uuid="model.uml@_heaygLlLEeG-Ou4fV0X62w")
	public Boolean getAttribute1() {
		Boolean result = this.attribute1;
		
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
	
	public String getName() {
		return "Class4["+getId()+"]";
	}
	
	public int getObjectVersion() {
		return this.objectVersion;
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
		createComponents();
	}
	
	public Class4 makeCopy() {
		Class4 result = new Class4();
		copyState((Class4)this,result);
		return result;
	}
	
	public Class4 makeShallowCopy() {
		Class4 result = new Class4();
		copyShallowState((Class4)this,result);
		result.setId(this.getId());
		return result;
	}
	
	public void markDeleted() {
		setDeletedOn(new Date());
	}
	
	static public void mockAllInstances(Set<Class4> newMocks) {
		mockedAllInstances=newMocks;
	}
	
	public void populateReferencesFromXml(Element xml, Map<String, Object> map) {
		NodeList propertyNodes = xml.getChildNodes();
		int i = 0;
		while ( i<propertyNodes.getLength() ) {
			Node currentPropertyNode = propertyNodes.item(i++);
		
		}
	}
	
	public void receiveSignal1(Signal1 signal) {
		generateSignal1Event(signal);
	}
	
	public void removeFromOwningObject() {
		this.markDeleted();
	}
	
	public void removePropertyChangeListener(String property, PropertyChangeListener listener) {
		propertyChangeSupport.removePropertyChangeListener(property,listener);
	}
	
	public void setAttribute1(Boolean attribute1) {
		propertyChangeSupport.firePropertyChange("Attribute1",getAttribute1(),attribute1);
		this.z_internalAddToAttribute1(attribute1);
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
		return "<Class4 uid=\""+getUid() + "\"/>";
	}
	
	public String toXmlString() {
		StringBuilder sb = new StringBuilder();
		sb.append("<Class4 ");
		sb.append("classUuid=\"model.uml@_iEtO4LkpEeGPU4u_bLx6fA\" ");
		sb.append("className=\"model.Class4\" ");
		sb.append("uid=\"" + this.getUid() + "\" ");
		if ( getAttribute1()!=null ) {
			sb.append("attribute1=\""+ ModelFormatter.getInstance().formatBoolean(getAttribute1())+"\" ");
		}
		sb.append(">");
		sb.append("\n</Class4>");
		return sb.toString();
	}
	
	public void z_internalAddToAttribute1(Boolean val) {
		this.attribute1=val;
	}
	
	public void z_internalRemoveFromAttribute1(Boolean val) {
		if ( getAttribute1()!=null && val!=null && val.equals(getAttribute1()) ) {
			this.attribute1=null;
			this.attribute1=null;
		}
	}

}