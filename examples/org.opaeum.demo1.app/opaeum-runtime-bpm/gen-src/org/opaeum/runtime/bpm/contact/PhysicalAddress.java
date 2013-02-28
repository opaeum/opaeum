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

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.AccessType;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.Index;
import org.opaeum.annotation.NumlMetaInfo;
import org.opaeum.annotation.PropertyMetaInfo;
import org.opaeum.hibernate.domain.InternalHibernatePersistence;
import org.opaeum.runtime.bpm.organization.PersonNode;
import org.opaeum.runtime.bpm.util.OpaeumLibraryForBPMFormatter;
import org.opaeum.runtime.bpm.util.Stdlib;
import org.opaeum.runtime.contact.IPersonPhysicalAddress;
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

@NumlMetaInfo(applicationIdentifier="demo1",uuid="252060@_cD7-MF-jEeGSPaWW9iQb9Q")
@Filter(name="noDeletedObjects")
@org.hibernate.annotations.Entity(dynamicUpdate=true)
@AccessType(	"field")
@Table(name="physical_address",schema="bpm",uniqueConstraints=
	@UniqueConstraint(columnNames={"person_id","deleted_on"}))
@Entity(name="PhysicalAddress")
@DiscriminatorValue(	"physical_address")
public class PhysicalAddress extends Address implements IPersistentObject, IEventGenerator, HibernateEntity, CompositionNode, IPersonPhysicalAddress, Serializable {
	@Transient
	private Set<CancelledEvent> cancelledEvents = new HashSet<CancelledEvent>();
		// Initialise to 1000 from 1970
	@Temporal(	javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="deleted_on")
	private Date deletedOn = Stdlib.FUTURE;
	static private Set<? extends PhysicalAddress> mockedAllInstances;
	@Transient
	private Set<OutgoingEvent> outgoingEvents = new HashSet<OutgoingEvent>();
	@Transient
	private InternalHibernatePersistence persistence;
	@Index(columnNames="person_id",name="idx_physical_address_person_id")
	@ManyToOne(fetch=javax.persistence.FetchType.LAZY)
	@JoinColumn(name="person_id",nullable=true)
	protected PersonNode person;
	@Transient
	private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
	static final private long serialVersionUID = 3794601824342079784l;

	/** This constructor is intended for easy initialization in unit tests
	 * 
	 * @param owningObject 
	 */
	public PhysicalAddress(PersonNode owningObject) {
		init(owningObject);
		addToOwningObject();
	}
	
	/** Default constructor for PhysicalAddress
	 */
	public PhysicalAddress() {
	}

	public void addPropertyChangeListener(String property, PropertyChangeListener listener) {
		propertyChangeSupport.addPropertyChangeListener(property,listener);
	}
	
	/** Call this method when you want to attach this object to the containment tree. Useful with transitive persistence
	 */
	public void addToOwningObject() {
		getPerson().z_internalAddToPhysicalAddress((PhysicalAddress)this);
	}
	
	static public Set<? extends PhysicalAddress> allInstances(AbstractPersistence persistence) {
		if ( mockedAllInstances==null ) {
			return new HashSet(persistence.readAll(org.opaeum.runtime.bpm.contact.PhysicalAddress.class));
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
		NodeList propertyNodes = xml.getChildNodes();
		int i = 0;
		while ( i<propertyNodes.getLength() ) {
			Node currentPropertyNode = propertyNodes.item(i++);
		
		}
	}
	
	public void copyShallowState(PhysicalAddress from, PhysicalAddress to) {
		to.setUnitNumber(from.getUnitNumber());
		to.setComplexName(from.getComplexName());
		to.setStreetName(from.getStreetName());
		to.setStreetNumber(from.getStreetNumber());
	}
	
	public void copyState(PhysicalAddress from, PhysicalAddress to) {
		to.setUnitNumber(from.getUnitNumber());
		to.setComplexName(from.getComplexName());
		to.setStreetName(from.getStreetName());
		to.setStreetNumber(from.getStreetNumber());
	}
	
	public void createComponents() {
		super.createComponents();
	}
	
	public boolean equals(Object other) {
		if ( other instanceof PhysicalAddress ) {
			return other==this || ((PhysicalAddress)other).getUid().equals(this.getUid());
		}
		return false;
	}
	
	public Set<CancelledEvent> getCancelledEvents() {
		return this.cancelledEvents;
	}
	
	public Date getDeletedOn() {
		return this.deletedOn;
	}
	
	public String getName() {
		return "PhysicalAddress["+getId()+"]";
	}
	
	public Set<OutgoingEvent> getOutgoingEvents() {
		return this.outgoingEvents;
	}
	
	public CompositionNode getOwningObject() {
		return getPerson();
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=3301610883493249210l,opposite="physicalAddress",uuid="252060@_U_inAF-mEeGSPaWW9iQb9Q")
	@NumlMetaInfo(uuid="252060@_U_inAF-mEeGSPaWW9iQb9Q")
	public PersonNode getPerson() {
		PersonNode result = this.person;
		
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
		this.z_internalAddToPerson((PersonNode)owner);
	}
	
	public PhysicalAddress makeCopy() {
		PhysicalAddress result = new PhysicalAddress();
		copyState((PhysicalAddress)this,result);
		return result;
	}
	
	public PhysicalAddress makeShallowCopy() {
		PhysicalAddress result = new PhysicalAddress();
		copyShallowState((PhysicalAddress)this,result);
		result.setId(this.getId());
		return result;
	}
	
	public void markDeleted() {
		super.markDeleted();
		if ( getPerson()!=null ) {
			getPerson().z_internalRemoveFromPhysicalAddress(this);
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
	
	public void setOutgoingEvents(Set<OutgoingEvent> outgoingEvents) {
		this.outgoingEvents=outgoingEvents;
	}
	
	public void setPerson(PersonNode person) {
		PersonNode oldValue = this.getPerson();
		propertyChangeSupport.firePropertyChange("person",getPerson(),person);
		if ( oldValue==null ) {
			if ( person!=null ) {
				PhysicalAddress oldOther = (PhysicalAddress)person.getPhysicalAddress();
				person.z_internalRemoveFromPhysicalAddress(oldOther);
				if ( oldOther != null ) {
					oldOther.z_internalRemoveFromPerson(person);
				}
				person.z_internalAddToPhysicalAddress((PhysicalAddress)this);
			}
			this.z_internalAddToPerson(person);
		} else {
			if ( !oldValue.equals(person) ) {
				oldValue.z_internalRemoveFromPhysicalAddress(this);
				z_internalRemoveFromPerson(oldValue);
				if ( person!=null ) {
					PhysicalAddress oldOther = (PhysicalAddress)person.getPhysicalAddress();
					person.z_internalRemoveFromPhysicalAddress(oldOther);
					if ( oldOther != null ) {
						oldOther.z_internalRemoveFromPerson(person);
					}
					person.z_internalAddToPhysicalAddress((PhysicalAddress)this);
				}
				this.z_internalAddToPerson(person);
			}
		}
	}
	
	public String toXmlReferenceString() {
		return "<PhysicalAddress uid=\""+getUid() + "\"/>";
	}
	
	public String toXmlString() {
		StringBuilder sb = new StringBuilder();
		sb.append("<PhysicalAddress ");
		sb.append("classUuid=\"252060@_cD7-MF-jEeGSPaWW9iQb9Q\" ");
		sb.append("className=\"org.opaeum.runtime.bpm.contact.PhysicalAddress\" ");
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
		sb.append(">");
		sb.append("\n</PhysicalAddress>");
		return sb.toString();
	}
	
	public void z_internalAddToPerson(PersonNode person) {
		if ( person.equals(getPerson()) ) {
			return;
		}
		this.person=person;
	}
	
	public void z_internalRemoveFromPerson(PersonNode person) {
		if ( getPerson()!=null && person!=null && person.equals(getPerson()) ) {
			this.person=null;
			this.person=null;
		}
	}

}