package structuredbusiness;

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

import org.hibernate.annotations.AccessType;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.Index;
import org.hibernate.annotations.Type;
import org.opaeum.annotation.BusinessDocument;
import org.opaeum.annotation.NumlMetaInfo;
import org.opaeum.annotation.PropertyMetaInfo;
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

import structuredbusiness.util.Stdlib;
import structuredbusiness.util.StructuredbusinessFormatter;

@NumlMetaInfo(uuid="914890@_oiVeEGCfEeG6xvYqJACneg")
@BusinessDocument(documentType=org.opaeum.runtime.domain.DocumentType.SPREADSHEET)
@Filter(name="noDeletedObjects")
@org.hibernate.annotations.Entity(dynamicUpdate=true)
@AccessType(	"field")
@Table(name="id_book")
@Inheritance(strategy=javax.persistence.InheritanceType.JOINED)
@Entity(name="IdBook")
@DiscriminatorColumn(discriminatorType=javax.persistence.DiscriminatorType.STRING,name="type_descriminator")
public class IdBook implements IPersistentObject, IEventGenerator, HibernateEntity, CompositionNode, IBusinessDocument, Serializable {
	@Transient
	private Set<CancelledEvent> cancelledEvents = new HashSet<CancelledEvent>();
	@Column(name="date_of_birth")
	private Date dateOfBirth;
		// Initialise to 1000 from 1970
	@Temporal(	javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="deleted_on")
	private Date deletedOn = Stdlib.FUTURE;
	@Index(columnNames="dishwashers_inc_id",name="idx_id_book_dishwashers_inc_id")
	@ManyToOne(fetch=javax.persistence.FetchType.LAZY)
	@JoinColumn(name="dishwashers_inc_id",nullable=true)
	private DishwashersInc dishwashersInc;
	@Type(type="org.opaeum.runtime.domain.DocumentTypeResolver")
	@Column(name="document_type",nullable=true)
	private DocumentType documentType;
	@Column(name="full_names")
	private String fullNames;
	@Id
	@GeneratedValue(strategy=javax.persistence.GenerationType.TABLE)
	private Long id;
	@Column(name="id_number")
	private String idNumber;
	static private Set<IdBook> mockedAllInstances;
	@Version
	@Column(name="object_version")
	private int objectVersion;
	@Transient
	private Set<OutgoingEvent> outgoingEvents = new HashSet<OutgoingEvent>();
	@Transient
	private AbstractPersistence persistence;
	@Transient
	private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
	static final private long serialVersionUID = 7267980829799356539l;
	private String uid;

	/** This constructor is intended for easy initialization in unit tests
	 * 
	 * @param owningObject 
	 */
	public IdBook(DishwashersInc owningObject) {
		init(owningObject);
		addToOwningObject();
	}
	
	/** Default constructor for IdBook
	 */
	public IdBook() {
	}

	public void addPropertyChangeListener(String property, PropertyChangeListener listener) {
		propertyChangeSupport.addPropertyChangeListener(property,listener);
	}
	
	/** Call this method when you want to attach this object to the containment tree. Useful with transitive persistence
	 */
	public void addToOwningObject() {
		getDishwashersInc().z_internalAddToIdBook((IdBook)this);
	}
	
	static public Set<? extends IdBook> allInstances() {
		if ( mockedAllInstances==null ) {
			CmtPersistence session =org.opaeum.runtime.environment.Environment.getInstance().getComponent(CmtPersistence.class);
			return new HashSet(session.readAll(structuredbusiness.IdBook.class));
		} else {
			return mockedAllInstances;
		}
	}
	
	public void buildTreeFromXml(Element xml, Map<String, Object> map) {
		setUid(xml.getAttribute("uid"));
		if ( xml.getAttribute("documentType").length()>0 ) {
			setDocumentType(DocumentType.valueOf(xml.getAttribute("documentType")));
		}
		if ( xml.getAttribute("idNumber").length()>0 ) {
			setIdNumber(StructuredbusinessFormatter.getInstance().parseString(xml.getAttribute("idNumber")));
		}
		if ( xml.getAttribute("fullNames").length()>0 ) {
			setFullNames(StructuredbusinessFormatter.getInstance().parseString(xml.getAttribute("fullNames")));
		}
		if ( xml.getAttribute("dateOfBirth").length()>0 ) {
			setDateOfBirth(StructuredbusinessFormatter.getInstance().parseDate(xml.getAttribute("dateOfBirth")));
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
	
	public void copyShallowState(IdBook from, IdBook to) {
		to.setDocumentType(from.getDocumentType());
		to.setIdNumber(from.getIdNumber());
		to.setFullNames(from.getFullNames());
		to.setDateOfBirth(from.getDateOfBirth());
	}
	
	public void copyState(IdBook from, IdBook to) {
		to.setDocumentType(from.getDocumentType());
		to.setIdNumber(from.getIdNumber());
		to.setFullNames(from.getFullNames());
		to.setDateOfBirth(from.getDateOfBirth());
	}
	
	public void createComponents() {
	}
	
	public boolean equals(Object other) {
		if ( other instanceof IdBook ) {
			return other==this || ((IdBook)other).getUid().equals(this.getUid());
		}
		return false;
	}
	
	public void generateMakeCopyEvent() {
	}
	
	public Set<CancelledEvent> getCancelledEvents() {
		return this.cancelledEvents;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=1406233976933179724l,uuid="914890@_aFhacHpiEeGlh5y8zQdYBA")
	@NumlMetaInfo(uuid="914890@_aFhacHpiEeGlh5y8zQdYBA")
	public Date getDateOfBirth() {
		Date result = this.dateOfBirth;
		
		return result;
	}
	
	public Date getDeletedOn() {
		return this.deletedOn;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=3496196660294374553l,opposite="idBook",uuid="914890@_0EgusXHgEeGus4aKic9sIg")
	@NumlMetaInfo(uuid="914890@_0EgusXHgEeGus4aKic9sIg")
	public DishwashersInc getDishwashersInc() {
		DishwashersInc result = this.dishwashersInc;
		
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=759998593327277107l,uuid="252060@_3FqBQF9lEeG3X_yvufTVmw")
	@NumlMetaInfo(uuid="252060@_3FqBQF9lEeG3X_yvufTVmw")
	public DocumentType getDocumentType() {
		DocumentType result = this.documentType;
		
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=8379936838922333184l,uuid="914890@_U5_wwHpiEeGlh5y8zQdYBA")
	@NumlMetaInfo(uuid="914890@_U5_wwHpiEeGlh5y8zQdYBA")
	public String getFullNames() {
		String result = this.fullNames;
		
		return result;
	}
	
	public Long getId() {
		return this.id;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=3895930087602592634l,uuid="914890@_SoyjIHpiEeGlh5y8zQdYBA")
	@NumlMetaInfo(uuid="914890@_SoyjIHpiEeGlh5y8zQdYBA")
	public String getIdNumber() {
		String result = this.idNumber;
		
		return result;
	}
	
	public String getName() {
		return "IdBook["+getId()+"]";
	}
	
	public int getObjectVersion() {
		return this.objectVersion;
	}
	
	public Set<OutgoingEvent> getOutgoingEvents() {
		return this.outgoingEvents;
	}
	
	public CompositionNode getOwningObject() {
		return getDishwashersInc();
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
		this.z_internalAddToDishwashersInc((DishwashersInc)owner);
		createComponents();
	}
	
	@NumlMetaInfo(uuid="252060@_nx6xcF9lEeG3X_yvufTVmw")
	public IdBook makeCopy() {
		IdBook result = new IdBook();
		copyState((IdBook)this,result);
		return result;
	}
	
	public IdBook makeShallowCopy() {
		IdBook result = new IdBook();
		copyShallowState((IdBook)this,result);
		result.setId(this.getId());
		return result;
	}
	
	public void markDeleted() {
		if ( getDishwashersInc()!=null ) {
			getDishwashersInc().z_internalRemoveFromIdBook(this);
		}
		setDeletedOn(new Date());
	}
	
	static public void mockAllInstances(Set<IdBook> newMocks) {
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
	
	public void setDateOfBirth(Date dateOfBirth) {
		propertyChangeSupport.firePropertyChange("dateOfBirth",getDateOfBirth(),dateOfBirth);
		this.z_internalAddToDateOfBirth(dateOfBirth);
	}
	
	public void setDeletedOn(Date deletedOn) {
		this.deletedOn=deletedOn;
	}
	
	public void setDishwashersInc(DishwashersInc dishwashersInc) {
		propertyChangeSupport.firePropertyChange("dishwashersInc",getDishwashersInc(),dishwashersInc);
		if ( this.getDishwashersInc()!=null ) {
			this.getDishwashersInc().z_internalRemoveFromIdBook(this);
		}
		if ( dishwashersInc!=null ) {
			dishwashersInc.z_internalAddToIdBook(this);
			this.z_internalAddToDishwashersInc(dishwashersInc);
			setDeletedOn(Stdlib.FUTURE);
		} else {
			markDeleted();
		}
	}
	
	public void setDocumentType(DocumentType documentType) {
		propertyChangeSupport.firePropertyChange("documentType",getDocumentType(),documentType);
		this.z_internalAddToDocumentType(documentType);
	}
	
	public void setFullNames(String fullNames) {
		propertyChangeSupport.firePropertyChange("fullNames",getFullNames(),fullNames);
		this.z_internalAddToFullNames(fullNames);
	}
	
	public void setId(Long id) {
		this.id=id;
	}
	
	public void setIdNumber(String idNumber) {
		propertyChangeSupport.firePropertyChange("idNumber",getIdNumber(),idNumber);
		this.z_internalAddToIdNumber(idNumber);
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
		return "<IdBook uid=\""+getUid() + "\"/>";
	}
	
	public String toXmlString() {
		StringBuilder sb = new StringBuilder();
		sb.append("<IdBook ");
		sb.append("classUuid=\"914890@_oiVeEGCfEeG6xvYqJACneg\" ");
		sb.append("className=\"structuredbusiness.IdBook\" ");
		sb.append("uid=\"" + this.getUid() + "\" ");
		if ( getDocumentType()!=null ) {
			sb.append("documentType=\""+ getDocumentType().name() + "\" ");
		}
		if ( getIdNumber()!=null ) {
			sb.append("idNumber=\""+ StructuredbusinessFormatter.getInstance().formatString(getIdNumber())+"\" ");
		}
		if ( getFullNames()!=null ) {
			sb.append("fullNames=\""+ StructuredbusinessFormatter.getInstance().formatString(getFullNames())+"\" ");
		}
		if ( getDateOfBirth()!=null ) {
			sb.append("dateOfBirth=\""+ StructuredbusinessFormatter.getInstance().formatDate(getDateOfBirth())+"\" ");
		}
		sb.append(">");
		sb.append("\n</IdBook>");
		return sb.toString();
	}
	
	public void z_internalAddToDateOfBirth(Date val) {
		this.dateOfBirth=val;
	}
	
	public void z_internalAddToDishwashersInc(DishwashersInc val) {
		this.dishwashersInc=val;
	}
	
	public void z_internalAddToDocumentType(DocumentType val) {
		this.documentType=val;
	}
	
	public void z_internalAddToFullNames(String val) {
		this.fullNames=val;
	}
	
	public void z_internalAddToIdNumber(String val) {
		this.idNumber=val;
	}
	
	public void z_internalRemoveFromDateOfBirth(Date val) {
		if ( getDateOfBirth()!=null && val!=null && val.equals(getDateOfBirth()) ) {
			this.dateOfBirth=null;
			this.dateOfBirth=null;
		}
	}
	
	public void z_internalRemoveFromDishwashersInc(DishwashersInc val) {
		if ( getDishwashersInc()!=null && val!=null && val.equals(getDishwashersInc()) ) {
			this.dishwashersInc=null;
			this.dishwashersInc=null;
		}
	}
	
	public void z_internalRemoveFromDocumentType(DocumentType val) {
		if ( getDocumentType()!=null && val!=null && val.equals(getDocumentType()) ) {
			this.documentType=null;
			this.documentType=null;
		}
	}
	
	public void z_internalRemoveFromFullNames(String val) {
		if ( getFullNames()!=null && val!=null && val.equals(getFullNames()) ) {
			this.fullNames=null;
			this.fullNames=null;
		}
	}
	
	public void z_internalRemoveFromIdNumber(String val) {
		if ( getIdNumber()!=null && val!=null && val.equals(getIdNumber()) ) {
			this.idNumber=null;
			this.idNumber=null;
		}
	}

}