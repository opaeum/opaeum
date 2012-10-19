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
import javax.persistence.UniqueConstraint;
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
import org.opaeum.runtime.environment.SimpleTypeRuntimeStrategyFactory;
import org.opaeum.runtime.persistence.AbstractPersistence;
import org.opaeum.runtime.persistence.CmtPersistence;
import org.opaeum.runtime.strategy.DateTimeStrategyFactory;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

@AuditMe
@NumlMetaInfo(uuid="model.uml@_Gqq44LPJEeGjH_kSoa4Y3A")
@Filter(name="noDeletedObjects")
@org.hibernate.annotations.Entity(dynamicUpdate=true)
@AccessType(	"field")
@Table(name="class1lk",uniqueConstraints=
	@UniqueConstraint(columnNames={"class2_id","deleted_on"}))
@Inheritance(strategy=javax.persistence.InheritanceType.JOINED)
@Entity(name="Class1lk")
@DiscriminatorColumn(discriminatorType=javax.persistence.DiscriminatorType.STRING,name="type_descriminator")
public class Class1lk implements IPersistentObject, IEventGenerator, HibernateEntity, CompositionNode, Serializable {
	@Column(name="attribute3")
	private Double attribute3;
	@Column(name="attribute4")
	private String attribute4;
	@Temporal(	javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="attribute5")
	private Date attribute5;
	@Column(name="attribute6")
	private Integer attribute6;
	@Column(name="attribute7")
	private Boolean attribute7;
	@ManyToOne(fetch=javax.persistence.FetchType.LAZY)
	@JoinColumn(name="attribute8_id",nullable=true)
	private MyBusiness attribute8;
	@Transient
	private Set<CancelledEvent> cancelledEvents = new HashSet<CancelledEvent>();
	@ManyToOne(fetch=javax.persistence.FetchType.LAZY)
	@JoinColumn(name="class2_id",nullable=true)
	private Class2 class2;
		// Initialise to 1000 from 1970
	@Temporal(	javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="deleted_on")
	private Date deletedOn = Stdlib.FUTURE;
	@Id
	@GeneratedValue(strategy=javax.persistence.GenerationType.TABLE)
	private Long id;
	static private Set<Class1lk> mockedAllInstances;
	@Version
	@Column(name="object_version")
	private int objectVersion;
	@Transient
	private Set<OutgoingEvent> outgoingEvents = new HashSet<OutgoingEvent>();
	@Transient
	private AbstractPersistence persistence;
	@Transient
	private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
	static final private long serialVersionUID = 454396204962880190l;
	private String uid;

	/** Default constructor for Class1lk
	 */
	public Class1lk() {
	}

	public void addPropertyChangeListener(String property, PropertyChangeListener listener) {
		propertyChangeSupport.addPropertyChangeListener(property,listener);
	}
	
	/** Call this method when you want to attach this object to the containment tree. Useful with transitive persistence
	 */
	public void addToOwningObject() {
	}
	
	static public Set<? extends Class1lk> allInstances() {
		if ( mockedAllInstances==null ) {
			CmtPersistence session =org.opaeum.runtime.environment.Environment.getInstance().getComponent(CmtPersistence.class);
			return new HashSet(session.readAll(model.Class1lk.class));
		} else {
			return mockedAllInstances;
		}
	}
	
	public void buildTreeFromXml(Element xml, Map<String, Object> map) {
		setUid(xml.getAttribute("uid"));
		if ( xml.getAttribute("attribute3").length()>0 ) {
			setAttribute3(ModelFormatter.getInstance().parseReal(xml.getAttribute("attribute3")));
		}
		if ( xml.getAttribute("attribute4").length()>0 ) {
			setAttribute4(ModelFormatter.getInstance().parseString(xml.getAttribute("attribute4")));
		}
		if ( xml.getAttribute("attribute5").length()>0 ) {
			setAttribute5(ModelFormatter.getInstance().parseDateTime(xml.getAttribute("attribute5")));
		}
		if ( xml.getAttribute("attribute6").length()>0 ) {
			setAttribute6(ModelFormatter.getInstance().parseInteger(xml.getAttribute("attribute6")));
		}
		if ( xml.getAttribute("attribute7").length()>0 ) {
			setAttribute7(ModelFormatter.getInstance().parseBoolean(xml.getAttribute("attribute7")));
		}
		NodeList propertyNodes = xml.getChildNodes();
		int i = 0;
		while ( i<propertyNodes.getLength() ) {
			Node currentPropertyNode = propertyNodes.item(i++);
		
		}
	}
	
	public void copyShallowState(Class1lk from, Class1lk to) {
		to.setAttribute3(from.getAttribute3());
		to.setAttribute4(from.getAttribute4());
		to.setAttribute5(from.getAttribute5());
		to.setAttribute6(from.getAttribute6());
		to.setAttribute7(from.getAttribute7());
	}
	
	public void copyState(Class1lk from, Class1lk to) {
		to.setAttribute3(from.getAttribute3());
		to.setAttribute4(from.getAttribute4());
		to.setAttribute5(from.getAttribute5());
		to.setAttribute6(from.getAttribute6());
		to.setAttribute7(from.getAttribute7());
	}
	
	public void createComponents() {
	}
	
	public boolean equals(Object other) {
		if ( other instanceof Class1lk ) {
			return other==this || ((Class1lk)other).getUid().equals(this.getUid());
		}
		return false;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=2652969965937734490l,strategyFactory=SimpleTypeRuntimeStrategyFactory.class,uuid="model.uml@_DqQ3ILbjEeGZM4YYtBhImQ")
	@NumlMetaInfo(uuid="model.uml@_DqQ3ILbjEeGZM4YYtBhImQ")
	public Double getAttribute3() {
		Double result = this.attribute3;
		
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=886638585899141376l,strategyFactory=SimpleTypeRuntimeStrategyFactory.class,uuid="model.uml@_A4NJwLbkEeGZM4YYtBhImQ")
	@NumlMetaInfo(uuid="model.uml@_A4NJwLbkEeGZM4YYtBhImQ")
	public String getAttribute4() {
		String result = this.attribute4;
		
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=3689603458571237496l,strategyFactory=DateTimeStrategyFactory.class,uuid="model.uml@_Itz0ELbkEeGZM4YYtBhImQ")
	@NumlMetaInfo(uuid="model.uml@_Itz0ELbkEeGZM4YYtBhImQ")
	public Date getAttribute5() {
		Date result = this.attribute5;
		
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=6860754956643438168l,strategyFactory=SimpleTypeRuntimeStrategyFactory.class,uuid="model.uml@_USms8LbkEeGZM4YYtBhImQ")
	@NumlMetaInfo(uuid="model.uml@_USms8LbkEeGZM4YYtBhImQ")
	public Integer getAttribute6() {
		Integer result = this.attribute6;
		
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=6627454316548902682l,strategyFactory=SimpleTypeRuntimeStrategyFactory.class,uuid="model.uml@_usvpcLbkEeGZM4YYtBhImQ")
	@NumlMetaInfo(uuid="model.uml@_usvpcLbkEeGZM4YYtBhImQ")
	public Boolean getAttribute7() {
		Boolean result = this.attribute7;
		
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=8663720164270918206l,uuid="model.uml@_RDlWwLblEeGZM4YYtBhImQ")
	@NumlMetaInfo(uuid="model.uml@_RDlWwLblEeGZM4YYtBhImQ")
	public MyBusiness getAttribute8() {
		MyBusiness result = this.attribute8;
		
		return result;
	}
	
	public Set<CancelledEvent> getCancelledEvents() {
		return this.cancelledEvents;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=2436848451548097274l,opposite="class1",uuid="model.uml@_L_204LPJEeGjH_kSoa4Y3A")
	@NumlMetaInfo(uuid="model.uml@_L_204LPJEeGjH_kSoa4Y3A")
	public Class2 getClass2() {
		Class2 result = this.class2;
		
		return result;
	}
	
	public Date getDeletedOn() {
		return this.deletedOn;
	}
	
	public Long getId() {
		return this.id;
	}
	
	public String getName() {
		return "Class1lk["+getId()+"]";
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
	
	public Class1lk makeCopy() {
		Class1lk result = new Class1lk();
		copyState((Class1lk)this,result);
		return result;
	}
	
	public Class1lk makeShallowCopy() {
		Class1lk result = new Class1lk();
		copyShallowState((Class1lk)this,result);
		result.setId(this.getId());
		return result;
	}
	
	public void markDeleted() {
		setDeletedOn(new Date());
	}
	
	static public void mockAllInstances(Set<Class1lk> newMocks) {
		mockedAllInstances=newMocks;
	}
	
	public void populateReferencesFromXml(Element xml, Map<String, Object> map) {
		NodeList propertyNodes = xml.getChildNodes();
		int i = 0;
		while ( i<propertyNodes.getLength() ) {
			Node currentPropertyNode = propertyNodes.item(i++);
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("class2") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("2436848451548097274")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						setClass2((Class2)map.get(((Element)currentPropertyValueNode).getAttribute("uid")));
					}
				}
			}
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("attribute8") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("8663720164270918206")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						setAttribute8((MyBusiness)map.get(((Element)currentPropertyValueNode).getAttribute("uid")));
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
	
	public void setAttribute3(Double attribute3) {
		propertyChangeSupport.firePropertyChange("Attribute3",getAttribute3(),attribute3);
		this.z_internalAddToAttribute3(attribute3);
	}
	
	public void setAttribute4(String attribute4) {
		propertyChangeSupport.firePropertyChange("Attribute4",getAttribute4(),attribute4);
		this.z_internalAddToAttribute4(attribute4);
	}
	
	public void setAttribute5(Date attribute5) {
		propertyChangeSupport.firePropertyChange("Attribute5",getAttribute5(),attribute5);
		this.z_internalAddToAttribute5(attribute5);
	}
	
	public void setAttribute6(Integer attribute6) {
		propertyChangeSupport.firePropertyChange("Attribute6",getAttribute6(),attribute6);
		this.z_internalAddToAttribute6(attribute6);
	}
	
	public void setAttribute7(Boolean attribute7) {
		propertyChangeSupport.firePropertyChange("Attribute7",getAttribute7(),attribute7);
		this.z_internalAddToAttribute7(attribute7);
	}
	
	public void setAttribute8(MyBusiness attribute8) {
		propertyChangeSupport.firePropertyChange("Attribute8",getAttribute8(),attribute8);
		this.z_internalAddToAttribute8(attribute8);
	}
	
	public void setCancelledEvents(Set<CancelledEvent> cancelledEvents) {
		this.cancelledEvents=cancelledEvents;
	}
	
	public void setClass2(Class2 class2) {
		propertyChangeSupport.firePropertyChange("class2",getClass2(),class2);
		this.z_internalAddToClass2(class2);
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
		return "<Class1lk uid=\""+getUid() + "\"/>";
	}
	
	public String toXmlString() {
		StringBuilder sb = new StringBuilder();
		sb.append("<Class1lk ");
		sb.append("classUuid=\"model.uml@_Gqq44LPJEeGjH_kSoa4Y3A\" ");
		sb.append("className=\"model.Class1lk\" ");
		sb.append("uid=\"" + this.getUid() + "\" ");
		if ( getAttribute3()!=null ) {
			sb.append("attribute3=\""+ ModelFormatter.getInstance().formatReal(getAttribute3())+"\" ");
		}
		if ( getAttribute4()!=null ) {
			sb.append("attribute4=\""+ ModelFormatter.getInstance().formatString(getAttribute4())+"\" ");
		}
		if ( getAttribute5()!=null ) {
			sb.append("attribute5=\""+ ModelFormatter.getInstance().formatDateTime(getAttribute5())+"\" ");
		}
		if ( getAttribute6()!=null ) {
			sb.append("attribute6=\""+ ModelFormatter.getInstance().formatInteger(getAttribute6())+"\" ");
		}
		if ( getAttribute7()!=null ) {
			sb.append("attribute7=\""+ ModelFormatter.getInstance().formatBoolean(getAttribute7())+"\" ");
		}
		sb.append(">");
		if ( getClass2()==null ) {
			sb.append("\n<class2/>");
		} else {
			sb.append("\n<class2 propertyId=\"2436848451548097274\">");
			sb.append("\n" + getClass2().toXmlReferenceString());
			sb.append("\n</class2>");
		}
		if ( getAttribute8()==null ) {
			sb.append("\n<attribute8/>");
		} else {
			sb.append("\n<attribute8 propertyId=\"8663720164270918206\">");
			sb.append("\n" + getAttribute8().toXmlReferenceString());
			sb.append("\n</attribute8>");
		}
		sb.append("\n</Class1lk>");
		return sb.toString();
	}
	
	public void z_internalAddToAttribute3(Double val) {
		this.attribute3=val;
	}
	
	public void z_internalAddToAttribute4(String val) {
		this.attribute4=val;
	}
	
	public void z_internalAddToAttribute5(Date val) {
		this.attribute5=val;
	}
	
	public void z_internalAddToAttribute6(Integer val) {
		this.attribute6=val;
	}
	
	public void z_internalAddToAttribute7(Boolean val) {
		this.attribute7=val;
	}
	
	public void z_internalAddToAttribute8(MyBusiness val) {
		this.attribute8=val;
	}
	
	public void z_internalAddToClass2(Class2 val) {
		this.class2=val;
	}
	
	public void z_internalRemoveFromAttribute3(Double val) {
		if ( getAttribute3()!=null && val!=null && val.equals(getAttribute3()) ) {
			this.attribute3=null;
			this.attribute3=null;
		}
	}
	
	public void z_internalRemoveFromAttribute4(String val) {
		if ( getAttribute4()!=null && val!=null && val.equals(getAttribute4()) ) {
			this.attribute4=null;
			this.attribute4=null;
		}
	}
	
	public void z_internalRemoveFromAttribute5(Date val) {
		if ( getAttribute5()!=null && val!=null && val.equals(getAttribute5()) ) {
			this.attribute5=null;
			this.attribute5=null;
		}
	}
	
	public void z_internalRemoveFromAttribute6(Integer val) {
		if ( getAttribute6()!=null && val!=null && val.equals(getAttribute6()) ) {
			this.attribute6=null;
			this.attribute6=null;
		}
	}
	
	public void z_internalRemoveFromAttribute7(Boolean val) {
		if ( getAttribute7()!=null && val!=null && val.equals(getAttribute7()) ) {
			this.attribute7=null;
			this.attribute7=null;
		}
	}
	
	public void z_internalRemoveFromAttribute8(MyBusiness val) {
		if ( getAttribute8()!=null && val!=null && val.equals(getAttribute8()) ) {
			this.attribute8=null;
			this.attribute8=null;
		}
	}
	
	public void z_internalRemoveFromClass2(Class2 val) {
		if ( getClass2()!=null && val!=null && val.equals(getClass2()) ) {
			this.class2=null;
			this.class2=null;
		}
	}

}