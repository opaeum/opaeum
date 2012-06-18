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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;
import javax.persistence.Version;

import model.util.ModelFormatter;
import model.util.Stdlib;

import org.hibernate.annotations.AccessType;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.LazyCollection;
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
@NumlMetaInfo(uuid="model.uml@_tWXP4LblEeGZM4YYtBhImQ")
@Filter(name="noDeletedObjects")
@org.hibernate.annotations.Entity(dynamicUpdate=true)
@AccessType(	"field")
@Table(name="class3")
@Inheritance(strategy=javax.persistence.InheritanceType.JOINED)
@Entity(name="Class3")
@DiscriminatorColumn(discriminatorType=javax.persistence.DiscriminatorType.STRING,name="type_descriminator")
public class Class3 implements IPersistentObject, IEventGenerator, HibernateEntity, CompositionNode, Serializable {
	@Transient
	private Set<CancelledEvent> cancelledEvents = new HashSet<CancelledEvent>();
	@LazyCollection(	org.hibernate.annotations.LazyCollectionOption.TRUE)
	@Filter(condition="deleted_on > current_timestamp",name="noDeletedObjects")
	@OneToMany(cascade=javax.persistence.CascadeType.ALL,fetch=javax.persistence.FetchType.LAZY,mappedBy="class3",targetEntity=Class2.class)
	private Set<Class2> class2 = new HashSet<Class2>();
		// Initialise to 1000 from 1970
	@Temporal(	javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="deleted_on")
	private Date deletedOn = Stdlib.FUTURE;
	@Id
	@GeneratedValue(strategy=javax.persistence.GenerationType.TABLE)
	private Long id;
	static private Set<Class3> mockedAllInstances;
	@Version
	@Column(name="object_version")
	private int objectVersion;
	@Transient
	private Set<OutgoingEvent> outgoingEvents = new HashSet<OutgoingEvent>();
	@Transient
	private AbstractPersistence persistence;
	@Column(name="property1")
	private Integer property1;
	@Column(name="property2")
	private Double property2;
	@Transient
	private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
	static final private long serialVersionUID = 1651245019065455264l;
	private String uid;

	/** Default constructor for Class3
	 */
	public Class3() {
	}

	public void addAllToClass2(Set<Class2> class2) {
		for ( Class2 o : class2 ) {
			addToClass2(o);
		}
	}
	
	public void addPropertyChangeListener(String property, PropertyChangeListener listener) {
		propertyChangeSupport.addPropertyChangeListener(property,listener);
	}
	
	public void addToClass2(Class2 class2) {
		if ( class2!=null ) {
			class2.z_internalRemoveFromClass3(class2.getClass3());
			class2.z_internalAddToClass3(this);
			z_internalAddToClass2(class2);
		}
	}
	
	/** Call this method when you want to attach this object to the containment tree. Useful with transitive persistence
	 */
	public void addToOwningObject() {
	}
	
	static public Set<? extends Class3> allInstances() {
		if ( mockedAllInstances==null ) {
			CmtPersistence session =org.opaeum.runtime.environment.Environment.getInstance().getComponent(CmtPersistence.class);
			return new HashSet(session.readAll(model.Class3.class));
		} else {
			return mockedAllInstances;
		}
	}
	
	public void buildTreeFromXml(Element xml, Map<String, Object> map) {
		setUid(xml.getAttribute("uid"));
		if ( xml.getAttribute("property1").length()>0 ) {
			setProperty1(ModelFormatter.getInstance().parseInteger(xml.getAttribute("property1")));
		}
		if ( xml.getAttribute("property2").length()>0 ) {
			setProperty2(ModelFormatter.getInstance().parseReal(xml.getAttribute("property2")));
		}
		NodeList propertyNodes = xml.getChildNodes();
		int i = 0;
		while ( i<propertyNodes.getLength() ) {
			Node currentPropertyNode = propertyNodes.item(i++);
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("class2") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("3105128598471571232")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						Class2 curVal;
						try {
							curVal=IntrospectionUtil.newInstance(((Element)currentPropertyValueNode).getAttribute("className"));
						} catch (Exception e) {
							curVal=Environment.getInstance().getMetaInfoMap().newInstance(((Element)currentPropertyValueNode).getAttribute("classUuid"));
						}
						curVal.buildTreeFromXml((Element)currentPropertyValueNode,map);
						this.addToClass2(curVal);
						map.put(curVal.getUid(), curVal);
					}
				}
			}
		}
	}
	
	public void clearClass2() {
		removeAllFromClass2(getClass2());
	}
	
	public void copyShallowState(Class3 from, Class3 to) {
		to.setProperty1(from.getProperty1());
		to.setProperty2(from.getProperty2());
	}
	
	public void copyState(Class3 from, Class3 to) {
		to.setProperty1(from.getProperty1());
		to.setProperty2(from.getProperty2());
		for ( Class2 child : from.getClass2() ) {
			to.addToClass2(child.makeCopy());
		}
	}
	
	public Class2 createClass2() {
		Class2 newInstance= new Class2();
		newInstance.init(this);
		return newInstance;
	}
	
	public void createComponents() {
	}
	
	public boolean equals(Object other) {
		if ( other instanceof Class3 ) {
			return other==this || ((Class3)other).getUid().equals(this.getUid());
		}
		return false;
	}
	
	public Set<CancelledEvent> getCancelledEvents() {
		return this.cancelledEvents;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=true,opaeumId=3105128598471571232l,opposite="class3",uuid="model.uml@_7xhR4LblEeGZM4YYtBhImQ")
	@NumlMetaInfo(uuid="model.uml@_7xhR4LblEeGZM4YYtBhImQ")
	public Set<Class2> getClass2() {
		Set<Class2> result = this.class2;
		
		return result;
	}
	
	public Date getDeletedOn() {
		return this.deletedOn;
	}
	
	public Long getId() {
		return this.id;
	}
	
	public String getName() {
		return "Class3["+getId()+"]";
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
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=9166833987744201730l,strategyFactory=SimpleTypeRuntimeStrategyFactory.class,uuid="model.uml@_t_OmsLblEeGZM4YYtBhImQ")
	@NumlMetaInfo(uuid="model.uml@_t_OmsLblEeGZM4YYtBhImQ")
	public Integer getProperty1() {
		Integer result = this.property1;
		
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=5649822052760654746l,strategyFactory=SimpleTypeRuntimeStrategyFactory.class,uuid="model.uml@_zQS3sLblEeGZM4YYtBhImQ")
	@NumlMetaInfo(uuid="model.uml@_zQS3sLblEeGZM4YYtBhImQ")
	public Double getProperty2() {
		Double result = this.property2;
		
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
	
	public Class3 makeCopy() {
		Class3 result = new Class3();
		copyState((Class3)this,result);
		return result;
	}
	
	public Class3 makeShallowCopy() {
		Class3 result = new Class3();
		copyShallowState((Class3)this,result);
		result.setId(this.getId());
		return result;
	}
	
	public void markDeleted() {
		for ( Class2 child : new ArrayList<Class2>(getClass2()) ) {
			child.markDeleted();
		}
		setDeletedOn(new Date());
	}
	
	static public void mockAllInstances(Set<Class3> newMocks) {
		mockedAllInstances=newMocks;
	}
	
	public void populateReferencesFromXml(Element xml, Map<String, Object> map) {
		NodeList propertyNodes = xml.getChildNodes();
		int i = 0;
		while ( i<propertyNodes.getLength() ) {
			Node currentPropertyNode = propertyNodes.item(i++);
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("class2") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("3105128598471571232")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						((Class2)map.get(((Element)currentPropertyValueNode).getAttribute("uid"))).populateReferencesFromXml((Element)currentPropertyValueNode, map);
					}
				}
			}
		}
	}
	
	public void removeAllFromClass2(Set<Class2> class2) {
		Set<Class2> tmp = new HashSet<Class2>(class2);
		for ( Class2 o : tmp ) {
			removeFromClass2(o);
		}
	}
	
	public void removeFromClass2(Class2 class2) {
		if ( class2!=null ) {
			class2.z_internalRemoveFromClass3(this);
			z_internalRemoveFromClass2(class2);
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
	
	public void setClass2(Set<Class2> class2) {
		propertyChangeSupport.firePropertyChange("class2",getClass2(),class2);
		this.clearClass2();
		this.addAllToClass2(class2);
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
	
	public void setProperty1(Integer property1) {
		propertyChangeSupport.firePropertyChange("Property1",getProperty1(),property1);
		this.z_internalAddToProperty1(property1);
	}
	
	public void setProperty2(Double property2) {
		propertyChangeSupport.firePropertyChange("Property2",getProperty2(),property2);
		this.z_internalAddToProperty2(property2);
	}
	
	public void setUid(String newUid) {
		this.uid=newUid;
	}
	
	public String toXmlReferenceString() {
		return "<Class3 uid=\""+getUid() + "\"/>";
	}
	
	public String toXmlString() {
		StringBuilder sb = new StringBuilder();
		sb.append("<Class3 ");
		sb.append("classUuid=\"model.uml@_tWXP4LblEeGZM4YYtBhImQ\" ");
		sb.append("className=\"model.Class3\" ");
		sb.append("uid=\"" + this.getUid() + "\" ");
		if ( getProperty1()!=null ) {
			sb.append("property1=\""+ ModelFormatter.getInstance().formatInteger(getProperty1())+"\" ");
		}
		if ( getProperty2()!=null ) {
			sb.append("property2=\""+ ModelFormatter.getInstance().formatReal(getProperty2())+"\" ");
		}
		sb.append(">");
		sb.append("\n<class2 propertyId=\"3105128598471571232\">");
		for ( Class2 class2 : getClass2() ) {
			sb.append("\n" + class2.toXmlString());
		}
		sb.append("\n</class2>");
		sb.append("\n</Class3>");
		return sb.toString();
	}
	
	public void z_internalAddToClass2(Class2 val) {
		this.class2.add(val);
	}
	
	public void z_internalAddToProperty1(Integer val) {
		this.property1=val;
	}
	
	public void z_internalAddToProperty2(Double val) {
		this.property2=val;
	}
	
	public void z_internalRemoveFromClass2(Class2 val) {
		this.class2.remove(val);
	}
	
	public void z_internalRemoveFromProperty1(Integer val) {
		if ( getProperty1()!=null && val!=null && val.equals(getProperty1()) ) {
			this.property1=null;
			this.property1=null;
		}
	}
	
	public void z_internalRemoveFromProperty2(Double val) {
		if ( getProperty2()!=null && val!=null && val.equals(getProperty2()) ) {
			this.property2=null;
			this.property2=null;
		}
	}

}