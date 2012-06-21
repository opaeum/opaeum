package model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
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
import org.opaeum.runtime.domain.HibernateEntity;
import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.runtime.domain.IntrospectionUtil;
import org.opaeum.runtime.environment.Environment;
import org.opaeum.runtime.environment.SimpleTypeRuntimeStrategyFactory;
import org.opaeum.runtime.persistence.AbstractPersistence;
import org.opaeum.runtime.persistence.CmtPersistence;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

@AuditMe
@NumlMetaInfo(uuid="model.uml@_ejhnILkpEeGPU4u_bLx6fA")
@Filter(name="noDeletedObjects")
@org.hibernate.annotations.Entity(dynamicUpdate=true)
@AccessType(	"field")
@Table(name="data_type1")
@Inheritance(strategy=javax.persistence.InheritanceType.JOINED)
@Entity(name="DataType1")
@DiscriminatorColumn(discriminatorType=javax.persistence.DiscriminatorType.STRING,name="type_descriminator")
public class DataType1 implements IPersistentObject, HibernateEntity, Serializable {
	@Column(name="attribute1")
	private String attribute1;
		// Initialise to 1000 from 1970
	@Temporal(	javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="deleted_on")
	private Date deletedOn = Stdlib.FUTURE;
	@Id
	@GeneratedValue(strategy=javax.persistence.GenerationType.TABLE)
	private Long id;
	static private Set<DataType1> mockedAllInstances;
	@Version
	@Column(name="object_version")
	private int objectVersion;
	@Transient
	private AbstractPersistence persistence;
	@Column(name="property1332342342")
	private String property1332342342;
	@Transient
	private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
	static final private long serialVersionUID = 8392248192324562412l;
	private String uid;

	/** Default constructor for DataType1
	 */
	public DataType1() {
	}

	@NumlMetaInfo(uuid="model.uml@_KNkFsLlMEeG-Ou4fV0X62w")
	public void Operation1() {
		generateOperation1Event();
	}
	
	public void addPropertyChangeListener(String property, PropertyChangeListener listener) {
		propertyChangeSupport.addPropertyChangeListener(property,listener);
	}
	
	static public Set<? extends DataType1> allInstances() {
		if ( mockedAllInstances==null ) {
			CmtPersistence session =org.opaeum.runtime.environment.Environment.getInstance().getComponent(CmtPersistence.class);
			return new HashSet(session.readAll(model.DataType1.class));
		} else {
			return mockedAllInstances;
		}
	}
	
	public void buildTreeFromXml(Element xml, Map<String, Object> map) {
		setUid(xml.getAttribute("uid"));
		if ( xml.getAttribute("property1332342342").length()>0 ) {
			setProperty1332342342(ModelFormatter.getInstance().parseString(xml.getAttribute("property1332342342")));
		}
		if ( xml.getAttribute("attribute1").length()>0 ) {
			setAttribute1(ModelFormatter.getInstance().parseString(xml.getAttribute("attribute1")));
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
	
	public void copyShallowState(DataType1 from, DataType1 to) {
		to.setProperty1332342342(from.getProperty1332342342());
		to.setAttribute1(from.getAttribute1());
	}
	
	public void copyState(DataType1 from, DataType1 to) {
		to.setProperty1332342342(from.getProperty1332342342());
		to.setAttribute1(from.getAttribute1());
	}
	
	public void generateOperation1Event() {
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=5459481385216975272l,strategyFactory=SimpleTypeRuntimeStrategyFactory.class,uuid="model.uml@_5SGDELlLEeG-Ou4fV0X62w")
	@NumlMetaInfo(uuid="model.uml@_5SGDELlLEeG-Ou4fV0X62w")
	public String getAttribute1() {
		String result = this.attribute1;
		
		return result;
	}
	
	public Date getDeletedOn() {
		return this.deletedOn;
	}
	
	public Long getId() {
		return this.id;
	}
	
	public String getName() {
		return "DataType1["+getId()+"]";
	}
	
	public int getObjectVersion() {
		return this.objectVersion;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=7589661985272868808l,strategyFactory=SimpleTypeRuntimeStrategyFactory.class,uuid="model.uml@_k-aHcLlLEeG-Ou4fV0X62w")
	@NumlMetaInfo(uuid="model.uml@_k-aHcLlLEeG-Ou4fV0X62w")
	public String getProperty1332342342() {
		String result = this.property1332342342;
		
		return result;
	}
	
	public String getUid() {
		if ( this.uid==null || this.uid.trim().length()==0 ) {
			uid=UUID.randomUUID().toString();
		}
		return this.uid;
	}
	
	public DataType1 makeCopy() {
		DataType1 result = new DataType1();
		copyState((DataType1)this,result);
		return result;
	}
	
	public DataType1 makeShallowCopy() {
		DataType1 result = new DataType1();
		copyShallowState((DataType1)this,result);
		result.setId(this.getId());
		return result;
	}
	
	public void markDeleted() {
		setDeletedOn(new Date());
	}
	
	static public void mockAllInstances(Set<DataType1> newMocks) {
		mockedAllInstances=newMocks;
	}
	
	public void populateReferencesFromXml(Element xml, Map<String, Object> map) {
		NodeList propertyNodes = xml.getChildNodes();
		int i = 0;
		while ( i<propertyNodes.getLength() ) {
			Node currentPropertyNode = propertyNodes.item(i++);
		
		}
	}
	
	public void removePropertyChangeListener(String property, PropertyChangeListener listener) {
		propertyChangeSupport.removePropertyChangeListener(property,listener);
	}
	
	public void setAttribute1(String attribute1) {
		propertyChangeSupport.firePropertyChange("Attribute1",getAttribute1(),attribute1);
		this.z_internalAddToAttribute1(attribute1);
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
	
	public void setProperty1332342342(String property1332342342) {
		propertyChangeSupport.firePropertyChange("Property1332342342",getProperty1332342342(),property1332342342);
		this.z_internalAddToProperty1332342342(property1332342342);
	}
	
	public void setUid(String newUid) {
		this.uid=newUid;
	}
	
	public String toXmlReferenceString() {
		return "<DataType1 uid=\""+getUid() + "\"/>";
	}
	
	public String toXmlString() {
		StringBuilder sb = new StringBuilder();
		sb.append("<DataType1 ");
		sb.append("classUuid=\"model.uml@_ejhnILkpEeGPU4u_bLx6fA\" ");
		sb.append("className=\"model.DataType1\" ");
		sb.append("uid=\"" + this.getUid() + "\" ");
		if ( getProperty1332342342()!=null ) {
			sb.append("property1332342342=\""+ ModelFormatter.getInstance().formatString(getProperty1332342342())+"\" ");
		}
		if ( getAttribute1()!=null ) {
			sb.append("attribute1=\""+ ModelFormatter.getInstance().formatString(getAttribute1())+"\" ");
		}
		sb.append(">");
		sb.append("\n</DataType1>");
		return sb.toString();
	}
	
	public void z_internalAddToAttribute1(String val) {
		this.attribute1=val;
	}
	
	public void z_internalAddToProperty1332342342(String val) {
		this.property1332342342=val;
	}
	
	public void z_internalRemoveFromAttribute1(String val) {
		if ( getAttribute1()!=null && val!=null && val.equals(getAttribute1()) ) {
			this.attribute1=null;
			this.attribute1=null;
		}
	}
	
	public void z_internalRemoveFromProperty1332342342(String val) {
		if ( getProperty1332342342()!=null && val!=null && val.equals(getProperty1332342342()) ) {
			this.property1332342342=null;
			this.property1332342342=null;
		}
	}

}