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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;
import javax.persistence.Version;

import model.util.ModelFormatter;
import model.util.Stdlib;

import org.hibernate.annotations.AccessType;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.Type;
import org.opaeum.annotation.BusinessDocument;
import org.opaeum.annotation.NumlMetaInfo;
import org.opaeum.annotation.PropertyMetaInfo;
import org.opaeum.audit.AuditMe;
import org.opaeum.runtime.bpm.document.IBusinessDocument;
import org.opaeum.runtime.domain.CancelledEvent;
import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.runtime.domain.DocumentType;
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
@NumlMetaInfo(uuid="model.uml@_F8xYMLlHEeG-Ou4fV0X62w")
@BusinessDocument(documentType=org.opaeum.runtime.domain.DocumentType.PRESENTATION)
@Filter(name="noDeletedObjects")
@org.hibernate.annotations.Entity(dynamicUpdate=true)
@AccessType(	"field")
@Table(name="business_document1")
@Inheritance(strategy=javax.persistence.InheritanceType.JOINED)
@Entity(name="BusinessDocument1")
@DiscriminatorColumn(discriminatorType=javax.persistence.DiscriminatorType.STRING,name="type_descriminator")
public class BusinessDocument1 implements IPersistentObject, IEventGenerator, HibernateEntity, CompositionNode, IBusinessDocument, Serializable {
	@Transient
	private Set<CancelledEvent> cancelledEvents = new HashSet<CancelledEvent>();
		// Initialise to 1000 from 1970
	@Temporal(	javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="deleted_on")
	private Date deletedOn = Stdlib.FUTURE;
	@Type(type="org.opaeum.runtime.domain.DocumentTypeResolver")
	@Column(name="document_type",nullable=true)
	private DocumentType documentType;
	@Id
	@GeneratedValue(strategy=javax.persistence.GenerationType.TABLE)
	private Long id;
	static private Set<BusinessDocument1> mockedAllInstances;
	@Version
	@Column(name="object_version")
	private int objectVersion;
	@Transient
	private Set<OutgoingEvent> outgoingEvents = new HashSet<OutgoingEvent>();
	@Transient
	private AbstractPersistence persistence;
	@Transient
	private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
	static final private long serialVersionUID = 4218949634562919120l;
	private String uid;

	/** Default constructor for BusinessDocument1
	 */
	public BusinessDocument1() {
	}

	public void addPropertyChangeListener(String property, PropertyChangeListener listener) {
		propertyChangeSupport.addPropertyChangeListener(property,listener);
	}
	
	/** Call this method when you want to attach this object to the containment tree. Useful with transitive persistence
	 */
	public void addToOwningObject() {
	}
	
	static public Set<? extends BusinessDocument1> allInstances() {
		if ( mockedAllInstances==null ) {
			CmtPersistence session =org.opaeum.runtime.environment.Environment.getInstance().getComponent(CmtPersistence.class);
			return new HashSet(session.readAll(model.BusinessDocument1.class));
		} else {
			return mockedAllInstances;
		}
	}
	
	public void buildTreeFromXml(Element xml, Map<String, Object> map) {
		setUid(xml.getAttribute("uid"));
		if ( xml.getAttribute("documentType").length()>0 ) {
			setDocumentType(DocumentType.valueOf(xml.getAttribute("documentType")));
		}
		NodeList propertyNodes = xml.getChildNodes();
		int i = 0;
		while ( i<propertyNodes.getLength() ) {
			Node currentPropertyNode = propertyNodes.item(i++);
		
		}
	}
	
	public boolean consumeMakeCopyOccurrence() {
		boolean consumed = false;
		return consumed;
	}
	
	public void copyShallowState(BusinessDocument1 from, BusinessDocument1 to) {
		to.setDocumentType(from.getDocumentType());
	}
	
	public void copyState(BusinessDocument1 from, BusinessDocument1 to) {
		to.setDocumentType(from.getDocumentType());
	}
	
	public void createComponents() {
	}
	
	public boolean equals(Object other) {
		if ( other instanceof BusinessDocument1 ) {
			return other==this || ((BusinessDocument1)other).getUid().equals(this.getUid());
		}
		return false;
	}
	
	public void generateMakeCopyEvent() {
	}
	
	public Set<CancelledEvent> getCancelledEvents() {
		return this.cancelledEvents;
	}
	
	public Date getDeletedOn() {
		return this.deletedOn;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=759998593327277107l,uuid="252060@_3FqBQF9lEeG3X_yvufTVmw")
	@NumlMetaInfo(uuid="252060@_3FqBQF9lEeG3X_yvufTVmw")
	public DocumentType getDocumentType() {
		DocumentType result = this.documentType;
		
		return result;
	}
	
	public Long getId() {
		return this.id;
	}
	
	public String getName() {
		return "BusinessDocument1["+getId()+"]";
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
	
	@NumlMetaInfo(uuid="252060@_nx6xcF9lEeG3X_yvufTVmw")
	public BusinessDocument1 makeCopy() {
		BusinessDocument1 result = new BusinessDocument1();
		copyState((BusinessDocument1)this,result);
		return result;
	}
	
	public BusinessDocument1 makeShallowCopy() {
		BusinessDocument1 result = new BusinessDocument1();
		copyShallowState((BusinessDocument1)this,result);
		result.setId(this.getId());
		return result;
	}
	
	public void markDeleted() {
		setDeletedOn(new Date());
	}
	
	static public void mockAllInstances(Set<BusinessDocument1> newMocks) {
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
	
	public void setDocumentType(DocumentType documentType) {
		propertyChangeSupport.firePropertyChange("documentType",getDocumentType(),documentType);
		this.z_internalAddToDocumentType(documentType);
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
		return "<BusinessDocument1 uid=\""+getUid() + "\"/>";
	}
	
	public String toXmlString() {
		StringBuilder sb = new StringBuilder();
		sb.append("<BusinessDocument1 ");
		sb.append("classUuid=\"model.uml@_F8xYMLlHEeG-Ou4fV0X62w\" ");
		sb.append("className=\"model.BusinessDocument1\" ");
		sb.append("uid=\"" + this.getUid() + "\" ");
		if ( getDocumentType()!=null ) {
			sb.append("documentType=\""+ getDocumentType().name() + "\" ");
		}
		sb.append(">");
		sb.append("\n</BusinessDocument1>");
		return sb.toString();
	}
	
	public void z_internalAddToDocumentType(DocumentType val) {
		this.documentType=val;
	}
	
	public void z_internalRemoveFromDocumentType(DocumentType val) {
		if ( getDocumentType()!=null && val!=null && val.equals(getDocumentType()) ) {
			this.documentType=null;
			this.documentType=null;
		}
	}

}