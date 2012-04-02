package org.opaeum.runtime.bpm.contact;

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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.hibernate.annotations.AccessType;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.Proxy;
import org.opaeum.annotation.NumlMetaInfo;
import org.opaeum.annotation.PropertyMetaInfo;
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
import org.opaeum.runtime.persistence.CmtPersistence;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

@NumlMetaInfo(uuid="252060@_eIdasF-jEeGSPaWW9iQb9Q")
@Proxy(lazy=false)
@Filter(name="noDeletedObjects")
@org.hibernate.annotations.Entity(dynamicUpdate=true)
@AccessType(	"field")
@Table(name="address")
@Inheritance(strategy=javax.persistence.InheritanceType.JOINED)
@Entity(name="Address")
@DiscriminatorColumn(discriminatorType=javax.persistence.DiscriminatorType.STRING,name="type_descriminator")
public class Address implements IPersistentObject, IEventGenerator, HibernateEntity, CompositionNode, Serializable {
	@Transient
	private Set<CancelledEvent> cancelledEvents = new HashSet<CancelledEvent>();
	@Column(name="complex_name")
	private String complexName;
		// Initialise to 1000 from 1970
	@Temporal(	javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="deleted_on")
	private Date deletedOn = Stdlib.FUTURE;
	@Id
	@GeneratedValue(strategy=javax.persistence.GenerationType.TABLE)
	private Long id;
	static private Set<Address> mockedAllInstances;
	@Version
	@Column(name="object_version")
	private int objectVersion;
	@Transient
	private Set<OutgoingEvent> outgoingEvents = new HashSet<OutgoingEvent>();
	@Transient
	private AbstractPersistence persistence;
	@Column(name="property1")
	private String property1;
	@Transient
	private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
	static final private long serialVersionUID = 8212401318043182240l;
	@Column(name="street_name")
	private String streetName;
	@Column(name="street_number")
	private String streetNumber;
	private String uid;
	@Column(name="unit_number")
	private String unitNumber;

	/** Default constructor for Address
	 */
	public Address() {
	}

	public void addPropertyChangeListener(String property, PropertyChangeListener listener) {
		propertyChangeSupport.addPropertyChangeListener(property,listener);
	}
	
	/** Call this method when you want to attach this object to the containment tree. Useful with transitive persistence
	 */
	public void addToOwningObject() {
	}
	
	static public Set<? extends Address> allInstances() {
		if ( mockedAllInstances==null ) {
			CmtPersistence session =org.opaeum.runtime.environment.Environment.getInstance().getComponent(CmtPersistence.class);
			return new HashSet(session.readAll(org.opaeum.runtime.bpm.contact.Address.class));
		} else {
			return mockedAllInstances;
		}
	}
	
	public void buildTreeFromXml(Element xml, Map<String, Object> map) {
		setUid(xml.getAttribute("uid"));
		if ( xml.getAttribute("unitNumber").length()>0 ) {
			setUnitNumber(OpaeumLibraryForBPMFormatter.getInstance().parseString(xml.getAttribute("unitNumber")));
		}
		if ( xml.getAttribute("complexName").length()>0 ) {
			setComplexName(OpaeumLibraryForBPMFormatter.getInstance().parseString(xml.getAttribute("complexName")));
		}
		if ( xml.getAttribute("streetName").length()>0 ) {
			setStreetName(OpaeumLibraryForBPMFormatter.getInstance().parseString(xml.getAttribute("streetName")));
		}
		if ( xml.getAttribute("streetNumber").length()>0 ) {
			setStreetNumber(OpaeumLibraryForBPMFormatter.getInstance().parseString(xml.getAttribute("streetNumber")));
		}
		if ( xml.getAttribute("property1").length()>0 ) {
			setProperty1(OpaeumLibraryForBPMFormatter.getInstance().parseString(xml.getAttribute("property1")));
		}
		NodeList propertyNodes = xml.getChildNodes();
		int i = 0;
		while ( i<propertyNodes.getLength() ) {
			Node currentPropertyNode = propertyNodes.item(i++);
		
		}
	}
	
	public void copyShallowState(Address from, Address to) {
		to.setUnitNumber(from.getUnitNumber());
		to.setComplexName(from.getComplexName());
		to.setStreetName(from.getStreetName());
		to.setStreetNumber(from.getStreetNumber());
		to.setProperty1(from.getProperty1());
	}
	
	public void copyState(Address from, Address to) {
		to.setUnitNumber(from.getUnitNumber());
		to.setComplexName(from.getComplexName());
		to.setStreetName(from.getStreetName());
		to.setStreetNumber(from.getStreetNumber());
		to.setProperty1(from.getProperty1());
	}
	
	public void createComponents() {
	}
	
	public boolean equals(Object other) {
		if ( other instanceof Address ) {
			return other==this || ((Address)other).getUid().equals(this.getUid());
		}
		return false;
	}
	
	public Set<CancelledEvent> getCancelledEvents() {
		return this.cancelledEvents;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=803417683337410610l,uuid="252060@_l4XFoF-jEeGSPaWW9iQb9Q")
	@NumlMetaInfo(uuid="252060@_l4XFoF-jEeGSPaWW9iQb9Q")
	public String getComplexName() {
		String result = this.complexName;
		
		return result;
	}
	
	public Date getDeletedOn() {
		return this.deletedOn;
	}
	
	public Long getId() {
		return this.id;
	}
	
	public String getName() {
		return "Address["+getId()+"]";
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
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=9050182878831642158l,uuid="252060@_tLfVAF-jEeGSPaWW9iQb9Q")
	@NumlMetaInfo(uuid="252060@_tLfVAF-jEeGSPaWW9iQb9Q")
	public String getProperty1() {
		String result = this.property1;
		
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=6661262784939673274l,uuid="252060@_nqLokF-jEeGSPaWW9iQb9Q")
	@NumlMetaInfo(uuid="252060@_nqLokF-jEeGSPaWW9iQb9Q")
	public String getStreetName() {
		String result = this.streetName;
		
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=7701164517031304452l,uuid="252060@_rXXsAF-jEeGSPaWW9iQb9Q")
	@NumlMetaInfo(uuid="252060@_rXXsAF-jEeGSPaWW9iQb9Q")
	public String getStreetNumber() {
		String result = this.streetNumber;
		
		return result;
	}
	
	public String getUid() {
		if ( this.uid==null || this.uid.trim().length()==0 ) {
			uid=UUID.randomUUID().toString();
		}
		return this.uid;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=922468094468620152l,uuid="252060@_gaGtMF-jEeGSPaWW9iQb9Q")
	@NumlMetaInfo(uuid="252060@_gaGtMF-jEeGSPaWW9iQb9Q")
	public String getUnitNumber() {
		String result = this.unitNumber;
		
		return result;
	}
	
	public int hashCode() {
		return getUid().hashCode();
	}
	
	public void init(CompositionNode owner) {
		createComponents();
	}
	
	public Address makeCopy() {
		Address result = new Address();
		copyState((Address)this,result);
		return result;
	}
	
	public Address makeShallowCopy() {
		Address result = new Address();
		copyShallowState((Address)this,result);
		result.setId(this.getId());
		return result;
	}
	
	public void markDeleted() {
		setDeletedOn(new Date());
	}
	
	static public void mockAllInstances(Set<Address> newMocks) {
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
	
	public void setComplexName(String complexName) {
		propertyChangeSupport.firePropertyChange("complexName",getComplexName(),complexName);
		this.z_internalAddToComplexName(complexName);
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
	
	public void setProperty1(String property1) {
		propertyChangeSupport.firePropertyChange("Property1",getProperty1(),property1);
		this.z_internalAddToProperty1(property1);
	}
	
	public void setStreetName(String streetName) {
		propertyChangeSupport.firePropertyChange("streetName",getStreetName(),streetName);
		this.z_internalAddToStreetName(streetName);
	}
	
	public void setStreetNumber(String streetNumber) {
		propertyChangeSupport.firePropertyChange("streetNumber",getStreetNumber(),streetNumber);
		this.z_internalAddToStreetNumber(streetNumber);
	}
	
	public void setUid(String newUid) {
		this.uid=newUid;
	}
	
	public void setUnitNumber(String unitNumber) {
		propertyChangeSupport.firePropertyChange("unitNumber",getUnitNumber(),unitNumber);
		this.z_internalAddToUnitNumber(unitNumber);
	}
	
	public String toXmlReferenceString() {
		return "<Address uid=\""+getUid() + "\"/>";
	}
	
	public String toXmlString() {
		StringBuilder sb = new StringBuilder();
		sb.append("<Address ");
		sb.append("classUuid=\"252060@_eIdasF-jEeGSPaWW9iQb9Q\" ");
		sb.append("className=\"org.opaeum.runtime.bpm.contact.Address\" ");
		sb.append("uid=\"" + this.getUid() + "\" ");
		if ( getUnitNumber()!=null ) {
			sb.append("unitNumber=\""+ OpaeumLibraryForBPMFormatter.getInstance().formatString(getUnitNumber())+"\" ");
		}
		if ( getComplexName()!=null ) {
			sb.append("complexName=\""+ OpaeumLibraryForBPMFormatter.getInstance().formatString(getComplexName())+"\" ");
		}
		if ( getStreetName()!=null ) {
			sb.append("streetName=\""+ OpaeumLibraryForBPMFormatter.getInstance().formatString(getStreetName())+"\" ");
		}
		if ( getStreetNumber()!=null ) {
			sb.append("streetNumber=\""+ OpaeumLibraryForBPMFormatter.getInstance().formatString(getStreetNumber())+"\" ");
		}
		if ( getProperty1()!=null ) {
			sb.append("property1=\""+ OpaeumLibraryForBPMFormatter.getInstance().formatString(getProperty1())+"\" ");
		}
		sb.append(">");
		sb.append("\n</Address>");
		return sb.toString();
	}
	
	public void z_internalAddToComplexName(String val) {
		this.complexName=val;
	}
	
	public void z_internalAddToProperty1(String val) {
		this.property1=val;
	}
	
	public void z_internalAddToStreetName(String val) {
		this.streetName=val;
	}
	
	public void z_internalAddToStreetNumber(String val) {
		this.streetNumber=val;
	}
	
	public void z_internalAddToUnitNumber(String val) {
		this.unitNumber=val;
	}
	
	public void z_internalRemoveFromComplexName(String val) {
		if ( getComplexName()!=null && val!=null && val.equals(getComplexName()) ) {
			this.complexName=null;
			this.complexName=null;
		}
	}
	
	public void z_internalRemoveFromProperty1(String val) {
		if ( getProperty1()!=null && val!=null && val.equals(getProperty1()) ) {
			this.property1=null;
			this.property1=null;
		}
	}
	
	public void z_internalRemoveFromStreetName(String val) {
		if ( getStreetName()!=null && val!=null && val.equals(getStreetName()) ) {
			this.streetName=null;
			this.streetName=null;
		}
	}
	
	public void z_internalRemoveFromStreetNumber(String val) {
		if ( getStreetNumber()!=null && val!=null && val.equals(getStreetNumber()) ) {
			this.streetNumber=null;
			this.streetNumber=null;
		}
	}
	
	public void z_internalRemoveFromUnitNumber(String val) {
		if ( getUnitNumber()!=null && val!=null && val.equals(getUnitNumber()) ) {
			this.unitNumber=null;
			this.unitNumber=null;
		}
	}

}