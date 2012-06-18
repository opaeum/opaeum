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
import org.opaeum.runtime.environment.SimpleTypeRuntimeStrategyFactory;
import org.opaeum.runtime.persistence.AbstractPersistence;
import org.opaeum.runtime.persistence.CmtPersistence;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

@AuditMe
@NumlMetaInfo(uuid="model.uml@_LUyeoLPJEeGjH_kSoa4Y3A")
@Filter(name="noDeletedObjects")
@org.hibernate.annotations.Entity(dynamicUpdate=true)
@AccessType(	"field")
@Table(name="class2")
@Inheritance(strategy=javax.persistence.InheritanceType.JOINED)
@Entity(name="Class2")
@DiscriminatorColumn(discriminatorType=javax.persistence.DiscriminatorType.STRING,name="type_descriminator")
public class Class2 implements IPersistentObject, IEventGenerator, HibernateEntity, CompositionNode, Serializable {
	@Column(name="attribute1")
	private Integer attribute1;
	@Column(name="attribute2")
	private String attribute2;
	@Transient
	private Set<CancelledEvent> cancelledEvents = new HashSet<CancelledEvent>();
	@Index(columnNames="class3_id",name="idx_class2_class3_id")
	@ManyToOne(fetch=javax.persistence.FetchType.LAZY)
	@JoinColumn(name="class3_id",nullable=true)
	private Class3 class3;
		// Initialise to 1000 from 1970
	@Temporal(	javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="deleted_on")
	private Date deletedOn = Stdlib.FUTURE;
	@Id
	@GeneratedValue(strategy=javax.persistence.GenerationType.TABLE)
	private Long id;
	static private Set<Class2> mockedAllInstances;
	@Version
	@Column(name="object_version")
	private int objectVersion;
	@Transient
	private Set<OutgoingEvent> outgoingEvents = new HashSet<OutgoingEvent>();
	@Transient
	private AbstractPersistence persistence;
	@ManyToOne(fetch=javax.persistence.FetchType.LAZY)
	@JoinColumn(name="property1_id",nullable=true)
	private Class2 property1;
	@Transient
	private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
	static final private long serialVersionUID = 8298346873488858840l;
	private String uid;

	/** This constructor is intended for easy initialization in unit tests
	 * 
	 * @param owningObject 
	 */
	public Class2(Class3 owningObject) {
		init(owningObject);
		addToOwningObject();
	}
	
	/** Default constructor for Class2
	 */
	public Class2() {
	}

	@NumlMetaInfo(uuid="model.uml@_FJO3sLawEeGoaOQgUyDAWw")
	public void Operation1() {
		generateOperation1Event();
	}
	
	@NumlMetaInfo(uuid="model.uml@_H4RWYLboEeGZM4YYtBhImQ")
	public void Operation2() {
		generateOperation2Event();
	}
	
	public void addPropertyChangeListener(String property, PropertyChangeListener listener) {
		propertyChangeSupport.addPropertyChangeListener(property,listener);
	}
	
	/** Call this method when you want to attach this object to the containment tree. Useful with transitive persistence
	 */
	public void addToOwningObject() {
		getClass3().z_internalAddToClass2((Class2)this);
	}
	
	static public Set<? extends Class2> allInstances() {
		if ( mockedAllInstances==null ) {
			CmtPersistence session =org.opaeum.runtime.environment.Environment.getInstance().getComponent(CmtPersistence.class);
			return new HashSet(session.readAll(model.Class2.class));
		} else {
			return mockedAllInstances;
		}
	}
	
	public void buildTreeFromXml(Element xml, Map<String, Object> map) {
		setUid(xml.getAttribute("uid"));
		if ( xml.getAttribute("attribute1").length()>0 ) {
			setAttribute1(ModelFormatter.getInstance().parseInteger(xml.getAttribute("attribute1")));
		}
		if ( xml.getAttribute("attribute2").length()>0 ) {
			setAttribute2(ModelFormatter.getInstance().parseString(xml.getAttribute("attribute2")));
		}
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
	
	public boolean consumeOperation2Occurrence() {
		boolean consumed = false;
		return consumed;
	}
	
	public void copyShallowState(Class2 from, Class2 to) {
		to.setAttribute1(from.getAttribute1());
		to.setAttribute2(from.getAttribute2());
	}
	
	public void copyState(Class2 from, Class2 to) {
		to.setAttribute1(from.getAttribute1());
		to.setAttribute2(from.getAttribute2());
	}
	
	public void createComponents() {
	}
	
	public boolean equals(Object other) {
		if ( other instanceof Class2 ) {
			return other==this || ((Class2)other).getUid().equals(this.getUid());
		}
		return false;
	}
	
	public void generateOperation1Event() {
	}
	
	public void generateOperation2Event() {
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=1858630361567303188l,strategyFactory=SimpleTypeRuntimeStrategyFactory.class,uuid="model.uml@_lMkGwLavEeGoaOQgUyDAWw")
	@NumlMetaInfo(uuid="model.uml@_lMkGwLavEeGoaOQgUyDAWw")
	public Integer getAttribute1() {
		Integer result = this.attribute1;
		
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=1326775365047311356l,strategyFactory=SimpleTypeRuntimeStrategyFactory.class,uuid="model.uml@_B0EgcLa1EeGKjIJH2lzvoQ")
	@NumlMetaInfo(uuid="model.uml@_B0EgcLa1EeGKjIJH2lzvoQ")
	public String getAttribute2() {
		String result = this.attribute2;
		
		return result;
	}
	
	public Set<CancelledEvent> getCancelledEvents() {
		return this.cancelledEvents;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=6051916989977066566l,opposite="class2",uuid="model.uml@_7xigAbblEeGZM4YYtBhImQ")
	@NumlMetaInfo(uuid="model.uml@_7xigAbblEeGZM4YYtBhImQ")
	public Class3 getClass3() {
		Class3 result = this.class3;
		
		return result;
	}
	
	public Date getDeletedOn() {
		return this.deletedOn;
	}
	
	public Long getId() {
		return this.id;
	}
	
	public String getName() {
		return "Class2["+getId()+"]";
	}
	
	public int getObjectVersion() {
		return this.objectVersion;
	}
	
	public Set<OutgoingEvent> getOutgoingEvents() {
		return this.outgoingEvents;
	}
	
	public CompositionNode getOwningObject() {
		return getClass3();
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,lookupMethod="getSourcePopulationForProperty1",opaeumId=943253800090336894l,uuid="model.uml@_BZjAcLbdEeG62dzgiRb2WA")
	@NumlMetaInfo(uuid="model.uml@_BZjAcLbdEeG62dzgiRb2WA")
	public Class2 getProperty1() {
		Class2 result = this.property1;
		
		return result;
	}
	
	public List<Class2> getSourcePopulationForProperty1() {
		return new ArrayList<Class2>(Stdlib.collectionAsSet(this.getClass3().getClass2()));
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
		this.z_internalAddToClass3((Class3)owner);
		createComponents();
	}
	
	public Class2 makeCopy() {
		Class2 result = new Class2();
		copyState((Class2)this,result);
		return result;
	}
	
	public Class2 makeShallowCopy() {
		Class2 result = new Class2();
		copyShallowState((Class2)this,result);
		result.setId(this.getId());
		return result;
	}
	
	public void markDeleted() {
		if ( getClass3()!=null ) {
			getClass3().z_internalRemoveFromClass2(this);
		}
		setDeletedOn(new Date());
	}
	
	static public void mockAllInstances(Set<Class2> newMocks) {
		mockedAllInstances=newMocks;
	}
	
	public void populateReferencesFromXml(Element xml, Map<String, Object> map) {
		NodeList propertyNodes = xml.getChildNodes();
		int i = 0;
		while ( i<propertyNodes.getLength() ) {
			Node currentPropertyNode = propertyNodes.item(i++);
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("property1") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("943253800090336894")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						setProperty1((Class2)map.get(((Element)currentPropertyValueNode).getAttribute("uid")));
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
	
	public void setAttribute1(Integer attribute1) {
		propertyChangeSupport.firePropertyChange("Attribute1",getAttribute1(),attribute1);
		this.z_internalAddToAttribute1(attribute1);
	}
	
	public void setAttribute2(String attribute2) {
		propertyChangeSupport.firePropertyChange("Attribute2",getAttribute2(),attribute2);
		this.z_internalAddToAttribute2(attribute2);
	}
	
	public void setCancelledEvents(Set<CancelledEvent> cancelledEvents) {
		this.cancelledEvents=cancelledEvents;
	}
	
	public void setClass3(Class3 class3) {
		propertyChangeSupport.firePropertyChange("class3",getClass3(),class3);
		if ( this.getClass3()!=null ) {
			this.getClass3().z_internalRemoveFromClass2(this);
		}
		if ( class3!=null ) {
			class3.z_internalAddToClass2(this);
			this.z_internalAddToClass3(class3);
			setDeletedOn(Stdlib.FUTURE);
		} else {
			markDeleted();
		}
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
	
	public void setProperty1(Class2 property1) {
		propertyChangeSupport.firePropertyChange("property1",getProperty1(),property1);
		this.z_internalAddToProperty1(property1);
	}
	
	public void setUid(String newUid) {
		this.uid=newUid;
	}
	
	public String toXmlReferenceString() {
		return "<Class2 uid=\""+getUid() + "\"/>";
	}
	
	public String toXmlString() {
		StringBuilder sb = new StringBuilder();
		sb.append("<Class2 ");
		sb.append("classUuid=\"model.uml@_LUyeoLPJEeGjH_kSoa4Y3A\" ");
		sb.append("className=\"model.Class2\" ");
		sb.append("uid=\"" + this.getUid() + "\" ");
		if ( getAttribute1()!=null ) {
			sb.append("attribute1=\""+ ModelFormatter.getInstance().formatInteger(getAttribute1())+"\" ");
		}
		if ( getAttribute2()!=null ) {
			sb.append("attribute2=\""+ ModelFormatter.getInstance().formatString(getAttribute2())+"\" ");
		}
		sb.append(">");
		if ( getProperty1()==null ) {
			sb.append("\n<property1/>");
		} else {
			sb.append("\n<property1 propertyId=\"943253800090336894\">");
			sb.append("\n" + getProperty1().toXmlReferenceString());
			sb.append("\n</property1>");
		}
		sb.append("\n</Class2>");
		return sb.toString();
	}
	
	public void z_internalAddToAttribute1(Integer val) {
		this.attribute1=val;
	}
	
	public void z_internalAddToAttribute2(String val) {
		this.attribute2=val;
	}
	
	public void z_internalAddToClass3(Class3 val) {
		this.class3=val;
	}
	
	public void z_internalAddToProperty1(Class2 val) {
		this.property1=val;
	}
	
	public void z_internalRemoveFromAttribute1(Integer val) {
		if ( getAttribute1()!=null && val!=null && val.equals(getAttribute1()) ) {
			this.attribute1=null;
			this.attribute1=null;
		}
	}
	
	public void z_internalRemoveFromAttribute2(String val) {
		if ( getAttribute2()!=null && val!=null && val.equals(getAttribute2()) ) {
			this.attribute2=null;
			this.attribute2=null;
		}
	}
	
	public void z_internalRemoveFromClass3(Class3 val) {
		if ( getClass3()!=null && val!=null && val.equals(getClass3()) ) {
			this.class3=null;
			this.class3=null;
		}
	}
	
	public void z_internalRemoveFromProperty1(Class2 val) {
		if ( getProperty1()!=null && val!=null && val.equals(getProperty1()) ) {
			this.property1=null;
			this.property1=null;
		}
	}

}