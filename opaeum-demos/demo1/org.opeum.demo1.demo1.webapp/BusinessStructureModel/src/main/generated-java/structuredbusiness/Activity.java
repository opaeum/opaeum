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
import org.opaeum.runtime.strategy.DateTimeStrategyFactory;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import structuredbusiness.util.Stdlib;
import structuredbusiness.util.StructuredbusinessFormatter;

@AuditMe
@NumlMetaInfo(uuid="914890@_DlV9oJLCEeGnpuq6_ber_Q")
@Filter(name="noDeletedObjects")
@org.hibernate.annotations.Entity(dynamicUpdate=true)
@AccessType(	"field")
@Table(name="activity")
@Inheritance(strategy=javax.persistence.InheritanceType.JOINED)
@Entity(name="Activity")
@DiscriminatorColumn(discriminatorType=javax.persistence.DiscriminatorType.STRING,name="type_descriminator")
public class Activity implements IPersistentObject, IEventGenerator, HibernateEntity, CompositionNode, Serializable {
	@Transient
	private Set<CancelledEvent> cancelledEvents = new HashSet<CancelledEvent>();
	@Column(name="cost_to_company")
	private Double costToCompany;
	@Column(name="cost_to_customer")
	private Double costToCustomer;
	@Temporal(	javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="date_of_work")
	private Date dateOfWork;
		// Initialise to 1000 from 1970
	@Temporal(	javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="deleted_on")
	private Date deletedOn = Stdlib.FUTURE;
	@Column(name="duration_in_hours")
	private Double durationInHours;
	@Id
	@GeneratedValue(strategy=javax.persistence.GenerationType.TABLE)
	private Long id;
	@Index(columnNames="job_id",name="idx_activity_job_id")
	@ManyToOne(fetch=javax.persistence.FetchType.LAZY)
	@JoinColumn(name="job_id",nullable=true)
	private Job job;
	static private Set<Activity> mockedAllInstances;
	@Version
	@Column(name="object_version")
	private int objectVersion;
	@Transient
	private Set<OutgoingEvent> outgoingEvents = new HashSet<OutgoingEvent>();
	@Transient
	private AbstractPersistence persistence;
	@Transient
	private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
	static final private long serialVersionUID = 7091164841557458131l;
	@ManyToOne(fetch=javax.persistence.FetchType.LAZY)
	@JoinColumn(name="technician_id",nullable=true)
	private Technician technician;
	private String uid;

	/** This constructor is intended for easy initialization in unit tests
	 * 
	 * @param owningObject 
	 */
	public Activity(Job owningObject) {
		init(owningObject);
		addToOwningObject();
	}
	
	/** Default constructor for Activity
	 */
	public Activity() {
	}

	public void addPropertyChangeListener(String property, PropertyChangeListener listener) {
		propertyChangeSupport.addPropertyChangeListener(property,listener);
	}
	
	/** Call this method when you want to attach this object to the containment tree. Useful with transitive persistence
	 */
	public void addToOwningObject() {
		getJob().z_internalAddToActivity((Activity)this);
	}
	
	static public Set<? extends Activity> allInstances(AbstractPersistence persistence) {
		if ( mockedAllInstances==null ) {
			return new HashSet(persistence.readAll(structuredbusiness.Activity.class));
		} else {
			return mockedAllInstances;
		}
	}
	
	public void buildTreeFromXml(Element xml, Map<String, Object> map) {
		setUid(xml.getAttribute("uid"));
		if ( xml.getAttribute("durationInHours").length()>0 ) {
			setDurationInHours(StructuredbusinessFormatter.getInstance().parseReal(xml.getAttribute("durationInHours")));
		}
		if ( xml.getAttribute("costToCustomer").length()>0 ) {
			setCostToCustomer(StructuredbusinessFormatter.getInstance().parseReal(xml.getAttribute("costToCustomer")));
		}
		if ( xml.getAttribute("costToCompany").length()>0 ) {
			setCostToCompany(StructuredbusinessFormatter.getInstance().parseReal(xml.getAttribute("costToCompany")));
		}
		if ( xml.getAttribute("dateOfWork").length()>0 ) {
			setDateOfWork(StructuredbusinessFormatter.getInstance().parseDateTime(xml.getAttribute("dateOfWork")));
		}
		NodeList propertyNodes = xml.getChildNodes();
		int i = 0;
		while ( i<propertyNodes.getLength() ) {
			Node currentPropertyNode = propertyNodes.item(i++);
		
		}
	}
	
	public void copyShallowState(Activity from, Activity to) {
		to.setDurationInHours(from.getDurationInHours());
		to.setCostToCustomer(from.getCostToCustomer());
		to.setCostToCompany(from.getCostToCompany());
		to.setDateOfWork(from.getDateOfWork());
	}
	
	public void copyState(Activity from, Activity to) {
		to.setDurationInHours(from.getDurationInHours());
		to.setCostToCustomer(from.getCostToCustomer());
		to.setCostToCompany(from.getCostToCompany());
		to.setDateOfWork(from.getDateOfWork());
	}
	
	public void createComponents() {
	}
	
	public boolean equals(Object other) {
		if ( other instanceof Activity ) {
			return other==this || ((Activity)other).getUid().equals(this.getUid());
		}
		return false;
	}
	
	public Set<CancelledEvent> getCancelledEvents() {
		return this.cancelledEvents;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=3707035665302306774l,uuid="914890@_d_KVgJN8EeGzUIOTq-3iUg")
	@NumlMetaInfo(uuid="914890@_d_KVgJN8EeGzUIOTq-3iUg")
	public Double getCostToCompany() {
		Double result = this.costToCompany;
		
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=2172027145591522686l,uuid="914890@_cHpaQJN8EeGzUIOTq-3iUg")
	@NumlMetaInfo(uuid="914890@_cHpaQJN8EeGzUIOTq-3iUg")
	public Double getCostToCustomer() {
		Double result = this.costToCustomer;
		
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=4250448505769073606l,strategyFactory=DateTimeStrategyFactory.class,uuid="914890@_u863IJN8EeGzUIOTq-3iUg")
	@NumlMetaInfo(uuid="914890@_u863IJN8EeGzUIOTq-3iUg")
	public Date getDateOfWork() {
		Date result = this.dateOfWork;
		
		return result;
	}
	
	public Date getDeletedOn() {
		return this.deletedOn;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=3022114162335846648l,uuid="914890@_XPTyUJN8EeGzUIOTq-3iUg")
	@NumlMetaInfo(uuid="914890@_XPTyUJN8EeGzUIOTq-3iUg")
	public Double getDurationInHours() {
		Double result = this.durationInHours;
		
		return result;
	}
	
	public Long getId() {
		return this.id;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=3673373424147704107l,opposite="activity",uuid="914890@_F6R0sZLCEeGnpuq6_ber_Q")
	@NumlMetaInfo(uuid="914890@_F6R0sZLCEeGnpuq6_ber_Q")
	public Job getJob() {
		Job result = this.job;
		
		return result;
	}
	
	public String getName() {
		return "Activity["+getId()+"]";
	}
	
	public int getObjectVersion() {
		return this.objectVersion;
	}
	
	public Set<OutgoingEvent> getOutgoingEvents() {
		return this.outgoingEvents;
	}
	
	public CompositionNode getOwningObject() {
		return getJob();
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=8435746886564594669l,opposite="activity",uuid="914890@_ehuEgJLCEeGnpuq6_ber_Q")
	@NumlMetaInfo(uuid="914890@_ehuEgJLCEeGnpuq6_ber_Q")
	public Technician getTechnician() {
		Technician result = this.technician;
		
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
		this.z_internalAddToJob((Job)owner);
		createComponents();
	}
	
	public Activity makeCopy() {
		Activity result = new Activity();
		copyState((Activity)this,result);
		return result;
	}
	
	public Activity makeShallowCopy() {
		Activity result = new Activity();
		copyShallowState((Activity)this,result);
		result.setId(this.getId());
		return result;
	}
	
	public void markDeleted() {
		if ( getJob()!=null ) {
			getJob().z_internalRemoveFromActivity(this);
		}
		setDeletedOn(new Date());
	}
	
	static public void mockAllInstances(Set<Activity> newMocks) {
		mockedAllInstances=newMocks;
	}
	
	public void populateReferencesFromXml(Element xml, Map<String, Object> map) {
		NodeList propertyNodes = xml.getChildNodes();
		int i = 0;
		while ( i<propertyNodes.getLength() ) {
			Node currentPropertyNode = propertyNodes.item(i++);
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("technician") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("8435746886564594669")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						setTechnician((Technician)map.get(((Element)currentPropertyValueNode).getAttribute("uid")));
					}
				}
			}
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("technician") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("8435746886564594669")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						setTechnician((Technician)map.get(((Element)currentPropertyValueNode).getAttribute("uid")));
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
	
	public void setCancelledEvents(Set<CancelledEvent> cancelledEvents) {
		this.cancelledEvents=cancelledEvents;
	}
	
	public void setCostToCompany(Double costToCompany) {
		propertyChangeSupport.firePropertyChange("costToCompany",getCostToCompany(),costToCompany);
		this.z_internalAddToCostToCompany(costToCompany);
	}
	
	public void setCostToCustomer(Double costToCustomer) {
		propertyChangeSupport.firePropertyChange("costToCustomer",getCostToCustomer(),costToCustomer);
		this.z_internalAddToCostToCustomer(costToCustomer);
	}
	
	public void setDateOfWork(Date dateOfWork) {
		propertyChangeSupport.firePropertyChange("dateOfWork",getDateOfWork(),dateOfWork);
		this.z_internalAddToDateOfWork(dateOfWork);
	}
	
	public void setDeletedOn(Date deletedOn) {
		this.deletedOn=deletedOn;
	}
	
	public void setDurationInHours(Double durationInHours) {
		propertyChangeSupport.firePropertyChange("durationInHours",getDurationInHours(),durationInHours);
		this.z_internalAddToDurationInHours(durationInHours);
	}
	
	public void setId(Long id) {
		this.id=id;
	}
	
	public void setJob(Job job) {
		propertyChangeSupport.firePropertyChange("job",getJob(),job);
		if ( this.getJob()!=null ) {
			this.getJob().z_internalRemoveFromActivity(this);
		}
		if ( job!=null ) {
			job.z_internalAddToActivity(this);
			this.z_internalAddToJob(job);
		}
	}
	
	public void setObjectVersion(int objectVersion) {
		this.objectVersion=objectVersion;
	}
	
	public void setOutgoingEvents(Set<OutgoingEvent> outgoingEvents) {
		this.outgoingEvents=outgoingEvents;
	}
	
	public void setTechnician(Technician technician) {
		propertyChangeSupport.firePropertyChange("technician",getTechnician(),technician);
		this.z_internalAddToTechnician(technician);
	}
	
	public void setUid(String newUid) {
		this.uid=newUid;
	}
	
	public String toXmlReferenceString() {
		return "<Activity uid=\""+getUid() + "\"/>";
	}
	
	public String toXmlString() {
		StringBuilder sb = new StringBuilder();
		sb.append("<Activity ");
		sb.append("classUuid=\"914890@_DlV9oJLCEeGnpuq6_ber_Q\" ");
		sb.append("className=\"structuredbusiness.Activity\" ");
		sb.append("uid=\"" + this.getUid() + "\" ");
		if ( getDurationInHours()!=null ) {
			sb.append("durationInHours=\""+ StructuredbusinessFormatter.getInstance().formatReal(getDurationInHours())+"\" ");
		}
		if ( getCostToCustomer()!=null ) {
			sb.append("costToCustomer=\""+ StructuredbusinessFormatter.getInstance().formatReal(getCostToCustomer())+"\" ");
		}
		if ( getCostToCompany()!=null ) {
			sb.append("costToCompany=\""+ StructuredbusinessFormatter.getInstance().formatReal(getCostToCompany())+"\" ");
		}
		if ( getDateOfWork()!=null ) {
			sb.append("dateOfWork=\""+ StructuredbusinessFormatter.getInstance().formatDateTime(getDateOfWork())+"\" ");
		}
		sb.append(">");
		if ( getTechnician()==null ) {
			sb.append("\n<technician/>");
		} else {
			sb.append("\n<technician propertyId=\"8435746886564594669\">");
			sb.append("\n" + getTechnician().toXmlReferenceString());
			sb.append("\n</technician>");
		}
		if ( getTechnician()==null ) {
			sb.append("\n<technician/>");
		} else {
			sb.append("\n<technician propertyId=\"8435746886564594669\">");
			sb.append("\n" + getTechnician().toXmlReferenceString());
			sb.append("\n</technician>");
		}
		sb.append("\n</Activity>");
		return sb.toString();
	}
	
	public void z_internalAddToCostToCompany(Double val) {
		this.costToCompany=val;
	}
	
	public void z_internalAddToCostToCustomer(Double val) {
		this.costToCustomer=val;
	}
	
	public void z_internalAddToDateOfWork(Date val) {
		this.dateOfWork=val;
	}
	
	public void z_internalAddToDurationInHours(Double val) {
		this.durationInHours=val;
	}
	
	public void z_internalAddToJob(Job val) {
		this.job=val;
	}
	
	public void z_internalAddToTechnician(Technician val) {
		this.technician=val;
	}
	
	public void z_internalRemoveFromCostToCompany(Double val) {
		if ( getCostToCompany()!=null && val!=null && val.equals(getCostToCompany()) ) {
			this.costToCompany=null;
			this.costToCompany=null;
		}
	}
	
	public void z_internalRemoveFromCostToCustomer(Double val) {
		if ( getCostToCustomer()!=null && val!=null && val.equals(getCostToCustomer()) ) {
			this.costToCustomer=null;
			this.costToCustomer=null;
		}
	}
	
	public void z_internalRemoveFromDateOfWork(Date val) {
		if ( getDateOfWork()!=null && val!=null && val.equals(getDateOfWork()) ) {
			this.dateOfWork=null;
			this.dateOfWork=null;
		}
	}
	
	public void z_internalRemoveFromDurationInHours(Double val) {
		if ( getDurationInHours()!=null && val!=null && val.equals(getDurationInHours()) ) {
			this.durationInHours=null;
			this.durationInHours=null;
		}
	}
	
	public void z_internalRemoveFromJob(Job val) {
		if ( getJob()!=null && val!=null && val.equals(getJob()) ) {
			this.job=null;
			this.job=null;
		}
	}
	
	public void z_internalRemoveFromTechnician(Technician val) {
		if ( getTechnician()!=null && val!=null && val.equals(getTechnician()) ) {
			this.technician=null;
			this.technician=null;
		}
	}

}