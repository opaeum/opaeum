package model;

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
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;
import javax.persistence.Version;

import model.util.ModelFormatter;
import model.util.Stdlib;

import org.hibernate.annotations.AccessType;
import org.hibernate.annotations.Filter;
import org.opaeum.annotation.NumlMetaInfo;
import org.opaeum.annotation.PropertyMetaInfo;
import org.opaeum.audit.AuditMe;
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
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

@AuditMe
@NumlMetaInfo(uuid="model.uml@_z86RoLlMEeG4Bcw-uZ2BZg")
@Filter(name="noDeletedObjects")
@org.hibernate.annotations.Entity(dynamicUpdate=true)
@AccessType(	"field")
@Table(name="class5")
@Inheritance(strategy=javax.persistence.InheritanceType.JOINED)
@Entity(name="Class5")
@DiscriminatorColumn(discriminatorType=javax.persistence.DiscriminatorType.STRING,name="type_descriminator")
public class Class5 implements IPersistentObject, IEventGenerator, HibernateEntity, CompositionNode, Serializable {
	@ManyToOne(fetch=javax.persistence.FetchType.LAZY)
	@JoinColumn(name="attribute1_id",nullable=true)
	private Class3 attribute1;
	@ManyToOne(cascade=javax.persistence.CascadeType.ALL,fetch=javax.persistence.FetchType.LAZY)
	@JoinColumn(name="attribute2_id",nullable=true)
	private DataType1 attribute2;
	@Transient
	private Set<CancelledEvent> cancelledEvents = new HashSet<CancelledEvent>();
		// Initialise to 1000 from 1970
	@Temporal(	javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="deleted_on")
	private Date deletedOn = Stdlib.FUTURE;
	@Id
	@GeneratedValue(strategy=javax.persistence.GenerationType.TABLE)
	private Long id;
	static private Set<Class5> mockedAllInstances;
	@Version
	@Column(name="object_version")
	private int objectVersion;
	@Transient
	private Set<OutgoingEvent> outgoingEvents = new HashSet<OutgoingEvent>();
	@Transient
	private AbstractPersistence persistence;
	@Transient
	private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
	static final private long serialVersionUID = 3354487309258875279l;
	private String uid;

	/** Default constructor for Class5
	 */
	public Class5() {
	}

	public void addPropertyChangeListener(String property, PropertyChangeListener listener) {
		propertyChangeSupport.addPropertyChangeListener(property,listener);
	}
	
	/** Call this method when you want to attach this object to the containment tree. Useful with transitive persistence
	 */
	public void addToOwningObject() {
	}
	
	static public Set<? extends Class5> allInstances() {
		if ( mockedAllInstances==null ) {
			CmtPersistence session =org.opaeum.runtime.environment.Environment.getInstance().getComponent(CmtPersistence.class);
			return new HashSet(session.readAll(model.Class5.class));
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
		
		}
	}
	
	public void copyShallowState(Class5 from, Class5 to) {
		if ( from.getAttribute2()!=null ) {
			to.setAttribute2(from.getAttribute2().makeShallowCopy());
		}
	}
	
	public void copyState(Class5 from, Class5 to) {
		if ( from.getAttribute2()!=null ) {
			to.setAttribute2(from.getAttribute2().makeCopy());
		}
	}
	
	public void createComponents() {
	}
	
	public boolean equals(Object other) {
		if ( other instanceof Class5 ) {
			return other==this || ((Class5)other).getUid().equals(this.getUid());
		}
		return false;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=3993562276252762287l,uuid="model.uml@_1o6gcLlMEeG4Bcw-uZ2BZg")
	@NumlMetaInfo(uuid="model.uml@_1o6gcLlMEeG4Bcw-uZ2BZg")
	public Class3 getAttribute1() {
		Class3 result = this.attribute1;
		
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=1015917884199081229l,uuid="model.uml@_2cjj4LlMEeG4Bcw-uZ2BZg")
	@NumlMetaInfo(uuid="model.uml@_2cjj4LlMEeG4Bcw-uZ2BZg")
	public DataType1 getAttribute2() {
		DataType1 result = this.attribute2;
		
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
		return "Class5["+getId()+"]";
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
	
	public Class5 makeCopy() {
		Class5 result = new Class5();
		copyState((Class5)this,result);
		return result;
	}
	
	public Class5 makeShallowCopy() {
		Class5 result = new Class5();
		copyShallowState((Class5)this,result);
		result.setId(this.getId());
		return result;
	}
	
	public void markDeleted() {
		setDeletedOn(new Date());
	}
	
	static public void mockAllInstances(Set<Class5> newMocks) {
		mockedAllInstances=newMocks;
	}
	
	public void populateReferencesFromXml(Element xml, Map<String, Object> map) {
		NodeList propertyNodes = xml.getChildNodes();
		int i = 0;
		while ( i<propertyNodes.getLength() ) {
			Node currentPropertyNode = propertyNodes.item(i++);
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("attribute1") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("3993562276252762287")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						setAttribute1((Class3)map.get(((Element)currentPropertyValueNode).getAttribute("uid")));
					}
				}
			}
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("attribute2") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("1015917884199081229")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						setAttribute2((DataType1)map.get(((Element)currentPropertyValueNode).getAttribute("uid")));
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
	
	public void setAttribute1(Class3 attribute1) {
		propertyChangeSupport.firePropertyChange("Attribute1",getAttribute1(),attribute1);
		this.z_internalAddToAttribute1(attribute1);
	}
	
	public void setAttribute2(DataType1 attribute2) {
		propertyChangeSupport.firePropertyChange("Attribute2",getAttribute2(),attribute2);
		this.z_internalAddToAttribute2(attribute2);
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
		return "<Class5 uid=\""+getUid() + "\"/>";
	}
	
	public String toXmlString() {
		StringBuilder sb = new StringBuilder();
		sb.append("<Class5 ");
		sb.append("classUuid=\"model.uml@_z86RoLlMEeG4Bcw-uZ2BZg\" ");
		sb.append("className=\"model.Class5\" ");
		sb.append("uid=\"" + this.getUid() + "\" ");
		sb.append(">");
		if ( getAttribute1()==null ) {
			sb.append("\n<attribute1/>");
		} else {
			sb.append("\n<attribute1 propertyId=\"3993562276252762287\">");
			sb.append("\n" + getAttribute1().toXmlReferenceString());
			sb.append("\n</attribute1>");
		}
		if ( getAttribute2()==null ) {
			sb.append("\n<attribute2/>");
		} else {
			sb.append("\n<attribute2 propertyId=\"1015917884199081229\">");
			sb.append("\n" + getAttribute2().toXmlReferenceString());
			sb.append("\n</attribute2>");
		}
		sb.append("\n</Class5>");
		return sb.toString();
	}
	
	public void z_internalAddToAttribute1(Class3 val) {
		this.attribute1=val;
	}
	
	public void z_internalAddToAttribute2(DataType1 val) {
		this.attribute2=val;
	}
	
	public void z_internalRemoveFromAttribute1(Class3 val) {
		if ( getAttribute1()!=null && val!=null && val.equals(getAttribute1()) ) {
			this.attribute1=null;
			this.attribute1=null;
		}
	}
	
	public void z_internalRemoveFromAttribute2(DataType1 val) {
		if ( getAttribute2()!=null && val!=null && val.equals(getAttribute2()) ) {
			this.attribute2=null;
			this.attribute2=null;
		}
	}

}