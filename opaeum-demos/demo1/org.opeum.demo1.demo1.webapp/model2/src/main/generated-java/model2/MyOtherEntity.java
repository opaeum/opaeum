package model2;

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

import model2.util.Model2Formatter;
import model2.util.Stdlib;

import org.hibernate.annotations.AccessType;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.Index;
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
@NumlMetaInfo(uuid="model2.uml@_zveNMLbIEeG62dzgiRb2WA")
@Filter(name="noDeletedObjects")
@org.hibernate.annotations.Entity(dynamicUpdate=true)
@AccessType(	"field")
@Table(name="my_other_entity")
@Inheritance(strategy=javax.persistence.InheritanceType.JOINED)
@Entity(name="MyOtherEntity")
@DiscriminatorColumn(discriminatorType=javax.persistence.DiscriminatorType.STRING,name="type_descriminator")
public class MyOtherEntity implements IPersistentObject, IEventGenerator, HibernateEntity, CompositionNode, Serializable {
	@Transient
	private Set<CancelledEvent> cancelledEvents = new HashSet<CancelledEvent>();
		// Initialise to 1000 from 1970
	@Temporal(	javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="deleted_on")
	private Date deletedOn = Stdlib.FUTURE;
	@Id
	@GeneratedValue(strategy=javax.persistence.GenerationType.TABLE)
	private Long id;
	static private Set<MyOtherEntity> mockedAllInstances;
	@Index(columnNames="my_entity_id",name="idx_my_other_entity_my_entity_id")
	@ManyToOne(fetch=javax.persistence.FetchType.LAZY)
	@JoinColumn(name="my_entity_id",nullable=true)
	private MyEntity myEntity;
	@Version
	@Column(name="object_version")
	private int objectVersion;
	@Transient
	private Set<OutgoingEvent> outgoingEvents = new HashSet<OutgoingEvent>();
	@Transient
	private AbstractPersistence persistence;
	@Transient
	private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
	static final private long serialVersionUID = 6817333241806181631l;
	private String uid;

	/** This constructor is intended for easy initialization in unit tests
	 * 
	 * @param owningObject 
	 */
	public MyOtherEntity(MyEntity owningObject) {
		init(owningObject);
		addToOwningObject();
	}
	
	/** Default constructor for MyOtherEntity
	 */
	public MyOtherEntity() {
	}

	@NumlMetaInfo(uuid="model2.uml@_DcYK8LbJEeG62dzgiRb2WA")
	public void Operation1() {
		generateOperation1Event();
	}
	
	public void addPropertyChangeListener(String property, PropertyChangeListener listener) {
		propertyChangeSupport.addPropertyChangeListener(property,listener);
	}
	
	/** Call this method when you want to attach this object to the containment tree. Useful with transitive persistence
	 */
	public void addToOwningObject() {
		getMyEntity().z_internalAddToMyOtherEntity((MyOtherEntity)this);
	}
	
	static public Set<? extends MyOtherEntity> allInstances() {
		if ( mockedAllInstances==null ) {
			CmtPersistence session =org.opaeum.runtime.environment.Environment.getInstance().getComponent(CmtPersistence.class);
			return new HashSet(session.readAll(model2.MyOtherEntity.class));
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
	
	public boolean consumeOperation1Occurrence() {
		boolean consumed = false;
		return consumed;
	}
	
	public void copyShallowState(MyOtherEntity from, MyOtherEntity to) {
	}
	
	public void copyState(MyOtherEntity from, MyOtherEntity to) {
	}
	
	public void createComponents() {
	}
	
	public boolean equals(Object other) {
		if ( other instanceof MyOtherEntity ) {
			return other==this || ((MyOtherEntity)other).getUid().equals(this.getUid());
		}
		return false;
	}
	
	public void generateOperation1Event() {
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
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=4872142832268096401l,opposite="myOtherEntity",uuid="model2.uml@_1aVzkbbIEeG62dzgiRb2WA")
	@NumlMetaInfo(uuid="model2.uml@_1aVzkbbIEeG62dzgiRb2WA")
	public MyEntity getMyEntity() {
		MyEntity result = this.myEntity;
		
		return result;
	}
	
	public String getName() {
		return "MyOtherEntity["+getId()+"]";
	}
	
	public int getObjectVersion() {
		return this.objectVersion;
	}
	
	public Set<OutgoingEvent> getOutgoingEvents() {
		return this.outgoingEvents;
	}
	
	public CompositionNode getOwningObject() {
		return getMyEntity();
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
		this.z_internalAddToMyEntity((MyEntity)owner);
		createComponents();
	}
	
	public MyOtherEntity makeCopy() {
		MyOtherEntity result = new MyOtherEntity();
		copyState((MyOtherEntity)this,result);
		return result;
	}
	
	public MyOtherEntity makeShallowCopy() {
		MyOtherEntity result = new MyOtherEntity();
		copyShallowState((MyOtherEntity)this,result);
		result.setId(this.getId());
		return result;
	}
	
	public void markDeleted() {
		if ( getMyEntity()!=null ) {
			getMyEntity().z_internalRemoveFromMyOtherEntity(this);
		}
		setDeletedOn(new Date());
	}
	
	static public void mockAllInstances(Set<MyOtherEntity> newMocks) {
		mockedAllInstances=newMocks;
	}
	
	public void populateReferencesFromXml(Element xml, Map<String, Object> map) {
		NodeList propertyNodes = xml.getChildNodes();
		int i = 0;
		while ( i<propertyNodes.getLength() ) {
			Node currentPropertyNode = propertyNodes.item(i++);
		
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
	}
	
	public void setId(Long id) {
		this.id=id;
	}
	
	public void setMyEntity(MyEntity myEntity) {
		propertyChangeSupport.firePropertyChange("myEntity",getMyEntity(),myEntity);
		if ( this.getMyEntity()!=null ) {
			this.getMyEntity().z_internalRemoveFromMyOtherEntity(this);
		}
		if ( myEntity!=null ) {
			myEntity.z_internalAddToMyOtherEntity(this);
			this.z_internalAddToMyEntity(myEntity);
			setDeletedOn(Stdlib.FUTURE);
		} else {
			markDeleted();
		}
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
		return "<MyOtherEntity uid=\""+getUid() + "\"/>";
	}
	
	public String toXmlString() {
		StringBuilder sb = new StringBuilder();
		sb.append("<MyOtherEntity ");
		sb.append("classUuid=\"model2.uml@_zveNMLbIEeG62dzgiRb2WA\" ");
		sb.append("className=\"model2.MyOtherEntity\" ");
		sb.append("uid=\"" + this.getUid() + "\" ");
		sb.append(">");
		sb.append("\n</MyOtherEntity>");
		return sb.toString();
	}
	
	public void z_internalAddToMyEntity(MyEntity val) {
		this.myEntity=val;
	}
	
	public void z_internalRemoveFromMyEntity(MyEntity val) {
		if ( getMyEntity()!=null && val!=null && val.equals(getMyEntity()) ) {
			this.myEntity=null;
			this.myEntity=null;
		}
	}

}