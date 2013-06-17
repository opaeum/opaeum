package org.opaeum.test;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.hibernate.annotations.AccessType;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.Index;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.Proxy;
import org.hibernate.annotations.Where;
import org.opaeum.annotation.NumlMetaInfo;
import org.opaeum.annotation.PropertyMetaInfo;
import org.opaeum.hibernate.domain.InternalHibernatePersistence;
import org.opaeum.runtime.domain.CancelledEvent;
import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.runtime.domain.HibernateEntity;
import org.opaeum.runtime.domain.IEventGenerator;
import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.runtime.domain.IntrospectionUtil;
import org.opaeum.runtime.domain.OutgoingEvent;
import org.opaeum.runtime.environment.Environment;
import org.opaeum.runtime.persistence.AbstractPersistence;
import org.opaeum.runtime.strategy.DateStrategyFactory;
import org.opaeum.test.util.ModelFormatter;
import org.opaeum.test.util.Stdlib;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

@NumlMetaInfo(applicationIdentifier="opaeum_hibernate_tests",uuid="Structures.uml@_V2hysIhqEeK4s7QGypAJBA")
@Proxy(lazy=false)
@Filter(name="noDeletedObjects")
@org.hibernate.annotations.Entity(dynamicUpdate=true)
@AccessType(	"field")
@Table(name="child")
@Inheritance(strategy=javax.persistence.InheritanceType.JOINED)
@Entity(name="Child")
@DiscriminatorColumn(discriminatorType=javax.persistence.DiscriminatorType.STRING,name="type_descriminator")
public class Child implements FamilyMember, IPersistentObject, IEventGenerator, HibernateEntity, CompositionNode, Serializable {
	@Transient
	private Set<CancelledEvent> cancelledEvents = new HashSet<CancelledEvent>();
	@LazyCollection(	org.hibernate.annotations.LazyCollectionOption.TRUE)
	@Filter(condition="deleted_on > current_timestamp",name="noDeletedObjects")
	@OneToMany(cascade=javax.persistence.CascadeType.ALL,fetch=javax.persistence.FetchType.LAZY,mappedBy="child",targetEntity=ChildHasRelation.class)
	@MapKey(name="z_keyOfGodParentOnChild")
	protected Map<String, ChildHasRelation> childHasRelation_godParent = new HashMap<String,ChildHasRelation>();
	@Temporal(	javax.persistence.TemporalType.DATE)
	@Column(name="date_of_birth")
	protected Date dateOfBirth;
		// Initialise to 1000 from 1970
	@Temporal(	javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="deleted_on")
	private Date deletedOn = Stdlib.FUTURE;
	@Index(columnNames="family_id",name="idx_child_family_id")
	@ManyToOne(fetch=javax.persistence.FetchType.LAZY)
	@JoinColumn(name="family_id",nullable=true)
	protected Family family;
	@Where(clause="family_member_type='Structures.uml@_V2hysIhqEeK4s7QGypAJBA'")
	@LazyCollection(	org.hibernate.annotations.LazyCollectionOption.TRUE)
	@Filter(condition="deleted_on > current_timestamp",name="noDeletedObjects")
	@OneToMany(cascade=javax.persistence.CascadeType.ALL,fetch=javax.persistence.FetchType.LAZY,targetEntity=FamilyMemberHasRelation.class)
	@MapKey(name="z_keyOfRelationOnFamilyMember")
	@JoinColumn(name="family_member",nullable=true)
	protected Map<String, FamilyMemberHasRelation> familyMemberHasRelation_relation = new HashMap<String,FamilyMemberHasRelation>();
	@Index(columnNames="father_id",name="idx_child_father_id")
	@ManyToOne(fetch=javax.persistence.FetchType.LAZY)
	@JoinColumn(name="father_id",nullable=true)
	protected Father father;
	@Index(columnNames="first_born_sibling_id",name="idx_child_first_born_sibling_id")
	@ManyToOne(fetch=javax.persistence.FetchType.LAZY)
	@JoinColumn(name="first_born_sibling_id",nullable=true)
	protected Child firstBornSibling;
	@TableGenerator(allocationSize=20,name="id_generator",pkColumnName="type",pkColumnValue="child",table="hi_value")
	@Id
	@GeneratedValue(generator="id_generator",strategy=javax.persistence.GenerationType.TABLE)
	private Long id;
	static private Set<? extends Child> mockedAllInstances;
	@Index(columnNames="mother_id",name="idx_child_mother_id")
	@ManyToOne(fetch=javax.persistence.FetchType.LAZY)
	@JoinColumn(name="mother_id",nullable=true)
	protected Mother mother;
	@Column(name="name")
	@Basic
	protected String name;
	@Version
	@Column(name="object_version")
	private int objectVersion;
	@Transient
	private Set<OutgoingEvent> outgoingEvents = new HashSet<OutgoingEvent>();
	@Transient
	private InternalHibernatePersistence persistence;
	static final private long serialVersionUID = 1446520852367386199l;
	@LazyCollection(	org.hibernate.annotations.LazyCollectionOption.TRUE)
	@Filter(condition="deleted_on > current_timestamp",name="noDeletedObjects")
	@OneToMany(cascade=javax.persistence.CascadeType.ALL,fetch=javax.persistence.FetchType.LAZY,mappedBy="stepSibling",targetEntity=SiblingStepSibling.class)
	protected Set<SiblingStepSibling> siblingStepSibling_stepSibling = new HashSet<SiblingStepSibling>();
	private String uid;
	@LazyCollection(	org.hibernate.annotations.LazyCollectionOption.TRUE)
	@Filter(condition="deleted_on > current_timestamp",name="noDeletedObjects")
	@OneToMany(fetch=javax.persistence.FetchType.LAZY,mappedBy="firstBornSibling",targetEntity=Child.class)
	protected Set<Child> younberSiblings = new HashSet<Child>();
	@Column(name="key_in_child_on_family")
	private String z_keyOfChildOnFamily;
	@Column(name="key_in_child_on_father")
	private String z_keyOfChildOnFather;
	@Column(name="key_in_child_on_mother")
	private String z_keyOfChildOnMother;

	/** This constructor is intended for easy initialization in unit tests
	 * 
	 * @param owningObject 
	 * @param name 
	 */
	public Child(Family owningObject, String name) {
		setName(name);
		init(owningObject);
		addToOwningObject();
	}
	
	/** Default constructor for Child
	 */
	public Child() {
	}

	public void addAllToGodParent(Set<Relation> godParent) {
		for ( Relation o : godParent ) {
			addToGodParent(o.getFirstName(),o.getSurname(),o.getDateOfBirth(),o);
		}
	}
	
	public void addAllToRelation(Set<Relation> relation) {
		for ( Relation o : relation ) {
			addToRelation(o.getFirstName(),o.getSurname(),o.getDateOfBirth(),o);
		}
	}
	
	public void addAllToStepSibling(Set<StepChild> stepSibling) {
		for ( StepChild o : stepSibling ) {
			addToStepSibling(o);
		}
	}
	
	public void addAllToYounberSiblings(Set<Child> younberSiblings) {
		for ( Child o : younberSiblings ) {
			addToYounberSiblings(o);
		}
	}
	
	public void addToGodParent(String firstName, String surname, Date dateOfBirth, Relation godParent) {
		if ( godParent!=null && !this.getGodParent().contains(godParent) ) {
			ChildHasRelation newLink = new ChildHasRelation((Child)this,(Relation)godParent);
			if ( getName()==null ) {
				throw new IllegalStateException("The qualifying property 'name' must be set before adding a value to 'godParent'");
			}
			if ( getDateOfBirth()==null ) {
				throw new IllegalStateException("The qualifying property 'dateOfBirth' must be set before adding a value to 'godParent'");
			}
			this.z_internalAddToChildHasRelation_godParent(firstName,surname,dateOfBirth,newLink);
			godParent.z_internalAddToChildHasRelation_child(this.getName(),this.getDateOfBirth(),newLink);
		}
	}
	
	/** Call this method when you want to attach this object to the containment tree. Useful with transitive persistence
	 */
	public void addToOwningObject() {
		if ( getName()==null ) {
			throw new IllegalStateException("The qualifying property 'name' must be set before adding a value to 'family'");
		}
		getFamily().z_internalAddToChild(this.getName(),(Child)this);
	}
	
	public void addToRelation(String firstName, String surname, Date dateOfBirth, Relation relation) {
		if ( relation!=null && !this.getRelation().contains(relation) ) {
			FamilyMemberHasRelation newLink = new FamilyMemberHasRelation((FamilyMember)this,(Relation)relation);
			if ( getFamily()==null ) {
				throw new IllegalStateException("The qualifying property 'family' must be set before adding a value to 'relation'");
			}
			if ( getName()==null ) {
				throw new IllegalStateException("The qualifying property 'name' must be set before adding a value to 'relation'");
			}
			this.z_internalAddToFamilyMemberHasRelation_relation(firstName,surname,dateOfBirth,newLink);
			relation.z_internalAddToFamilyMemberHasRelation_familyMember(this.getFamily(),this.getName(),newLink);
		}
	}
	
	public void addToStepSibling(StepChild stepSibling) {
		if ( stepSibling!=null && !this.getStepSibling().contains(stepSibling) ) {
			SiblingStepSibling newLink = new SiblingStepSibling((Child)this,(StepChild)stepSibling);
			this.z_internalAddToSiblingStepSibling_stepSibling(newLink);
			stepSibling.z_internalAddToSiblingStepSibling_stepSibling(newLink);
		}
	}
	
	public void addToYounberSiblings(Child younberSiblings) {
		if ( younberSiblings!=null ) {
			if ( younberSiblings.getFirstBornSibling()!=null ) {
				younberSiblings.getFirstBornSibling().removeFromYounberSiblings(younberSiblings);
			}
			younberSiblings.z_internalAddToFirstBornSibling(this);
		}
		z_internalAddToYounberSiblings(younberSiblings);
	}
	
	static public Set<? extends Child> allInstances(AbstractPersistence persistence) {
		if ( mockedAllInstances==null ) {
			return new HashSet(persistence.readAll(org.opaeum.test.Child.class));
		} else {
			return mockedAllInstances;
		}
	}
	
	public void buildTreeFromXml(Element xml, Map<String, Object> map) {
		setUid(xml.getAttribute("uid"));
		if ( xml.getAttribute("name").length()>0 ) {
			setName(ModelFormatter.getInstance().parseString(xml.getAttribute("name")));
		}
		if ( xml.getAttribute("dateOfBirth").length()>0 ) {
			setDateOfBirth(ModelFormatter.getInstance().parseDate(xml.getAttribute("dateOfBirth")));
		}
		NodeList propertyNodes = xml.getChildNodes();
		int i = 0;
		while ( i<propertyNodes.getLength() ) {
			Node currentPropertyNode = propertyNodes.item(i++);
		
		}
	}
	
	public void clearGodParent() {
		Set<Relation> tmp = new HashSet<Relation>(getGodParent());
		for ( Relation o : tmp ) {
			removeFromGodParent(o.getFirstName(),o.getSurname(),o.getDateOfBirth(),o);
		}
	}
	
	public void clearRelation() {
		Set<Relation> tmp = new HashSet<Relation>(getRelation());
		for ( Relation o : tmp ) {
			removeFromRelation(o.getFirstName(),o.getSurname(),o.getDateOfBirth(),o);
		}
	}
	
	public void clearStepSibling() {
		Set<StepChild> tmp = new HashSet<StepChild>(getStepSibling());
		for ( StepChild o : tmp ) {
			removeFromStepSibling(o);
		}
	}
	
	public void clearYounberSiblings() {
		Set<Child> tmp = new HashSet<Child>(getYounberSiblings());
		for ( Child o : tmp ) {
			removeFromYounberSiblings(o);
		}
	}
	
	public void copyShallowState(Child from, Child to) {
		to.setName(from.getName());
		to.setDateOfBirth(from.getDateOfBirth());
	}
	
	public void copyState(Child from, Child to) {
		to.setName(from.getName());
		to.setDateOfBirth(from.getDateOfBirth());
	}
	
	public void createComponents() {
	}
	
	public boolean equals(Object other) {
		if ( other instanceof Child ) {
			return other==this || ((Child)other).getUid().equals(this.getUid());
		}
		return false;
	}
	
	public Set<CancelledEvent> getCancelledEvents() {
		return this.cancelledEvents;
	}
	
	public ChildHasRelation getChildHasRelation_godParent(String firstName, String surname, Date dateOfBirth) {
		ChildHasRelation result = null;
		String key = ModelFormatter.getInstance().formatStringQualifier(firstName)+ModelFormatter.getInstance().formatStringQualifier(surname)+ModelFormatter.getInstance().formatDateQualifier(dateOfBirth);
		result=this.childHasRelation_godParent.get(key.toString());
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=8434948450232903107l,opposite="child",uuid="Structures.uml@_I7GooIhrEeK4s7QGypAJBA")
	@NumlMetaInfo(uuid="Structures.uml@_V2hysIhqEeK4s7QGypAJBA@Structures.uml@_I7GooIhrEeK4s7QGypAJBA")
	public Set<ChildHasRelation> getChildHasRelation_godParent() {
		Set result = new HashSet<ChildHasRelation>(this.childHasRelation_godParent.values());
		
		return result;
	}
	
	public ChildHasRelation getChildHasRelation_godParentFor(Relation match) {
		for ( ChildHasRelation var : getChildHasRelation_godParent() ) {
			if ( var.getGodParent().equals(match) ) {
				return var;
			}
		}
		return null;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=3866952438253764885l,strategyFactory=DateStrategyFactory.class,uuid="Structures.uml@_9QxBMIlWEeK8Z-Y1T93HUw")
	@NumlMetaInfo(uuid="Structures.uml@_9QxBMIlWEeK8Z-Y1T93HUw")
	public Date getDateOfBirth() {
		Date result = this.dateOfBirth;
		
		return result;
	}
	
	public Date getDeletedOn() {
		return this.deletedOn;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=6981150938619650761l,opposite="child",uuid="Structures.uml@_lw6LwIhqEeK4s7QGypAJBA")
	@NumlMetaInfo(uuid="Structures.uml@_lw6LwIhqEeK4s7QGypAJBA")
	public Family getFamily() {
		Family result = this.family;
		
		return result;
	}
	
	public FamilyMemberHasRelation getFamilyMemberHasRelation_relation(String firstName, String surname, Date dateOfBirth) {
		FamilyMemberHasRelation result = null;
		String key = ModelFormatter.getInstance().formatStringQualifier(firstName)+ModelFormatter.getInstance().formatStringQualifier(surname)+ModelFormatter.getInstance().formatDateQualifier(dateOfBirth);
		result=this.familyMemberHasRelation_relation.get(key.toString());
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=5718737559910777343l,opposite="familyMember",uuid="Structures.uml@_wPOkwIhqEeK4s7QGypAJBA")
	@NumlMetaInfo(uuid="Structures.uml@_uAFMoIhqEeK4s7QGypAJBA@Structures.uml@_wPOkwIhqEeK4s7QGypAJBA")
	public Set<FamilyMemberHasRelation> getFamilyMemberHasRelation_relation() {
		Set result = new HashSet<FamilyMemberHasRelation>(this.familyMemberHasRelation_relation.values());
		
		return result;
	}
	
	public FamilyMemberHasRelation getFamilyMemberHasRelation_relationFor(Relation match) {
		for ( FamilyMemberHasRelation var : getFamilyMemberHasRelation_relation() ) {
			if ( var.getRelation().equals(match) ) {
				return var;
			}
		}
		return null;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=7915129683656142253l,opposite="child",uuid="Structures.uml@_KNfssYsgEeKWvpYIRX9T4g")
	@NumlMetaInfo(uuid="Structures.uml@_KNfssYsgEeKWvpYIRX9T4g")
	public Father getFather() {
		Father result = this.father;
		
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=2122232498866777869l,opposite="younberSiblings",uuid="Structures.uml@_lSLoYY1MEeKkdfSznNDwqg")
	@NumlMetaInfo(uuid="Structures.uml@_lSLoYY1MEeKkdfSznNDwqg")
	public Child getFirstBornSibling() {
		Child result = this.firstBornSibling;
		
		return result;
	}
	
	public Relation getGodParent(String firstName, String surname, Date dateOfBirth) {
		Relation result = null;
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=1858371569261639701l,opposite="child",uuid="Structures.uml@_I7BwIIhrEeK4s7QGypAJBA")
	public Set<Relation> getGodParent() {
		Set result = new HashSet<Relation>();
		for ( ChildHasRelation cur : this.getChildHasRelation_godParent() ) {
			result.add(cur.getGodParent());
		}
		return result;
	}
	
	public Long getId() {
		return this.id;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=3843307682732995451l,opposite="child",uuid="Structures.uml@_EI7iMIsgEeKWvpYIRX9T4g")
	@NumlMetaInfo(uuid="Structures.uml@_EI7iMIsgEeKWvpYIRX9T4g")
	public Mother getMother() {
		Mother result = this.mother;
		
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=6583079073938083054l,uuid="Structures.uml@_3GSEsIiBEeKSGOXl2qggew")
	@NumlMetaInfo(uuid="Structures.uml@_3GSEsIiBEeKSGOXl2qggew")
	public String getName() {
		String result = this.name;
		
		return result;
	}
	
	public int getObjectVersion() {
		return this.objectVersion;
	}
	
	public Set<OutgoingEvent> getOutgoingEvents() {
		return this.outgoingEvents;
	}
	
	public CompositionNode getOwningObject() {
		return getFamily();
	}
	
	public Relation getRelation(String firstName, String surname, Date dateOfBirth) {
		Relation result = null;
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=4408335886692542833l,opposite="familyMember",uuid="Structures.uml@_wPOkwYhqEeK4s7QGypAJBA")
	public Set<Relation> getRelation() {
		Set result = new HashSet<Relation>();
		for ( FamilyMemberHasRelation cur : this.getFamilyMemberHasRelation_relation() ) {
			result.add(cur.getRelation());
		}
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=2983107073586996627l,opposite="stepSibling",uuid="Structures.uml@_1X1ycI1OEeKgGLBcRSZFfw")
	@NumlMetaInfo(uuid="Structures.uml@_V2hysIhqEeK4s7QGypAJBA@Structures.uml@_1X1ycI1OEeKgGLBcRSZFfw")
	public Set<SiblingStepSibling> getSiblingStepSibling_stepSibling() {
		Set result = this.siblingStepSibling_stepSibling;
		
		return result;
	}
	
	public SiblingStepSibling getSiblingStepSibling_stepSiblingFor(StepChild match) {
		for ( SiblingStepSibling var : getSiblingStepSibling_stepSibling() ) {
			if ( var.getStepSibling1().equals(match) ) {
				return var;
			}
		}
		return null;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=5737868333270681143l,opposite="stepSibling",uuid="Structures.uml@_1X0kUI1OEeKgGLBcRSZFfw")
	public Set<StepChild> getStepSibling() {
		Set result = new HashSet<StepChild>();
		for ( SiblingStepSibling cur : this.getSiblingStepSibling_stepSibling() ) {
			result.add(cur.getStepSibling1());
		}
		return result;
	}
	
	public String getUid() {
		if ( this.uid==null || this.uid.trim().length()==0 ) {
			uid=UUID.randomUUID().toString();
		}
		return this.uid;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=3598322603986863641l,opposite="firstBornSibling",uuid="Structures.uml@_lSHW8I1MEeKkdfSznNDwqg")
	@NumlMetaInfo(uuid="Structures.uml@_lSHW8I1MEeKkdfSznNDwqg")
	public Set<Child> getYounberSiblings() {
		Set result = this.younberSiblings;
		
		return result;
	}
	
	public String getZ_keyOfChildOnFamily() {
		return this.z_keyOfChildOnFamily;
	}
	
	public String getZ_keyOfChildOnFather() {
		return this.z_keyOfChildOnFather;
	}
	
	public String getZ_keyOfChildOnMother() {
		return this.z_keyOfChildOnMother;
	}
	
	public int hashCode() {
		return getUid().hashCode();
	}
	
	public void init(CompositionNode owner) {
		this.z_internalAddToFamily((Family)owner);
	}
	
	public Child makeCopy() {
		Child result = new Child();
		copyState((Child)this,result);
		return result;
	}
	
	public Child makeShallowCopy() {
		Child result = new Child();
		copyShallowState((Child)this,result);
		result.setId(this.getId());
		return result;
	}
	
	public void markDeleted() {
		setDeletedOn(new Date(System.currentTimeMillis()));
		for ( ChildHasRelation link : new HashSet<ChildHasRelation>(getChildHasRelation_godParent()) ) {
			link.getGodParent().z_internalRemoveFromChildHasRelation_child(this.getName(),this.getDateOfBirth(),link);
		}
		if ( getMother()!=null ) {
			getMother().z_internalRemoveFromChild(this.getName(),this);
		}
		removeAllFromYounberSiblings(getYounberSiblings());
		for ( SiblingStepSibling link : new HashSet<SiblingStepSibling>(getSiblingStepSibling_stepSibling()) ) {
			link.getStepSibling1().z_internalRemoveFromSiblingStepSibling_stepSibling(link);
		}
		for ( FamilyMemberHasRelation link : new HashSet<FamilyMemberHasRelation>(getFamilyMemberHasRelation_relation()) ) {
			link.getRelation().z_internalRemoveFromFamilyMemberHasRelation_familyMember(this.getFamily(),this.getName(),link);
		}
		if ( getFamily()!=null ) {
			getFamily().z_internalRemoveFromChild(this.getName(),this);
		}
		if ( getFirstBornSibling()!=null ) {
			getFirstBornSibling().z_internalRemoveFromYounberSiblings(this);
		}
		if ( getFather()!=null ) {
			getFather().z_internalRemoveFromChild(this.getName(),this);
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
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("mother") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("3843307682732995451")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						Mother mother = (Mother)map.get(((Element)currentPropertyValueNode).getAttribute("uid"));
						if ( mother!=null ) {
							z_internalAddToMother(mother);
							mother.z_internalAddToChild(this.getName(),this);
						}
					}
				}
			}
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("firstBornSibling") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("2122232498866777869")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						Child firstBornSibling = (Child)map.get(((Element)currentPropertyValueNode).getAttribute("uid"));
						if ( firstBornSibling!=null ) {
							z_internalAddToFirstBornSibling(firstBornSibling);
							firstBornSibling.z_internalAddToYounberSiblings(this);
						}
					}
				}
			}
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("father") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("7915129683656142253")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						Father father = (Father)map.get(((Element)currentPropertyValueNode).getAttribute("uid"));
						if ( father!=null ) {
							z_internalAddToFather(father);
							father.z_internalAddToChild(this.getName(),this);
						}
					}
				}
			}
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("childHasRelation_godParent") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("8434948450232903107")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						ChildHasRelation childHasRelation_godParent = (ChildHasRelation)map.get(((Element)currentPropertyValueNode).getAttribute("uid"));
						if ( childHasRelation_godParent!=null ) {
							z_internalAddToChildHasRelation_godParent(childHasRelation_godParent.getGodParent().getFirstName(),childHasRelation_godParent.getGodParent().getSurname(),childHasRelation_godParent.getGodParent().getDateOfBirth(),childHasRelation_godParent);
							childHasRelation_godParent.z_internalAddToChild(this);
						}
					}
				}
			}
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("siblingStepSibling_stepSibling") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("2983107073586996627")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						SiblingStepSibling siblingStepSibling_stepSibling = (SiblingStepSibling)map.get(((Element)currentPropertyValueNode).getAttribute("uid"));
						if ( siblingStepSibling_stepSibling!=null ) {
							z_internalAddToSiblingStepSibling_stepSibling(siblingStepSibling_stepSibling);
							siblingStepSibling_stepSibling.z_internalAddToStepSibling(this);
						}
					}
				}
			}
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("familyMemberHasRelation_relation") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("5718737559910777343")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						FamilyMemberHasRelation familyMemberHasRelation_relation = (FamilyMemberHasRelation)map.get(((Element)currentPropertyValueNode).getAttribute("uid"));
						if ( familyMemberHasRelation_relation!=null ) {
							z_internalAddToFamilyMemberHasRelation_relation(familyMemberHasRelation_relation.getRelation().getFirstName(),familyMemberHasRelation_relation.getRelation().getSurname(),familyMemberHasRelation_relation.getRelation().getDateOfBirth(),familyMemberHasRelation_relation);
							familyMemberHasRelation_relation.z_internalAddToFamilyMember(this);
						}
					}
				}
			}
		}
	}
	
	public void removeAllFromGodParent(Set<? extends Relation> godParent) {
		Set<Relation> tmp = new HashSet<Relation>(godParent);
		for ( Relation o : tmp ) {
			removeFromGodParent(o.getFirstName(),o.getSurname(),o.getDateOfBirth(),o);
		}
	}
	
	public void removeAllFromRelation(Set<? extends Relation> relation) {
		Set<Relation> tmp = new HashSet<Relation>(relation);
		for ( Relation o : tmp ) {
			removeFromRelation(o.getFirstName(),o.getSurname(),o.getDateOfBirth(),o);
		}
	}
	
	public void removeAllFromStepSibling(Set<? extends StepChild> stepSibling) {
		Set<StepChild> tmp = new HashSet<StepChild>(stepSibling);
		for ( StepChild o : tmp ) {
			removeFromStepSibling(o);
		}
	}
	
	public void removeAllFromYounberSiblings(Set<? extends Child> younberSiblings) {
		Set<Child> tmp = new HashSet<Child>(younberSiblings);
		for ( Child o : tmp ) {
			removeFromYounberSiblings(o);
		}
	}
	
	public void removeFromGodParent(String firstName, String surname, Date dateOfBirth, Relation godParent) {
		if ( godParent!=null ) {
			ChildHasRelation oldLink = getChildHasRelation_godParentFor(godParent);
			if ( oldLink!=null ) {
				godParent.z_internalRemoveFromChildHasRelation_child(this.getName(),this.getDateOfBirth(),oldLink);
				oldLink.clear();
				z_internalRemoveFromChildHasRelation_godParent(godParent.getFirstName(),godParent.getSurname(),godParent.getDateOfBirth(),oldLink);
			}
		}
	}
	
	public void removeFromOwningObject() {
		this.markDeleted();
	}
	
	public void removeFromRelation(String firstName, String surname, Date dateOfBirth, Relation relation) {
		if ( relation!=null ) {
			FamilyMemberHasRelation oldLink = getFamilyMemberHasRelation_relationFor(relation);
			if ( oldLink!=null ) {
				relation.z_internalRemoveFromFamilyMemberHasRelation_familyMember(this.getFamily(),this.getName(),oldLink);
				oldLink.clear();
				z_internalRemoveFromFamilyMemberHasRelation_relation(relation.getFirstName(),relation.getSurname(),relation.getDateOfBirth(),oldLink);
			}
		}
	}
	
	public void removeFromStepSibling(StepChild stepSibling) {
		if ( stepSibling!=null ) {
			SiblingStepSibling oldLink = getSiblingStepSibling_stepSiblingFor(stepSibling);
			if ( oldLink!=null ) {
				stepSibling.z_internalRemoveFromSiblingStepSibling_stepSibling(oldLink);
				oldLink.clear();
				z_internalRemoveFromSiblingStepSibling_stepSibling(oldLink);
			}
		}
	}
	
	public void removeFromYounberSiblings(Child younberSiblings) {
		if ( younberSiblings!=null ) {
			younberSiblings.z_internalRemoveFromFirstBornSibling(this);
			z_internalRemoveFromYounberSiblings(younberSiblings);
		}
	}
	
	public void setCancelledEvents(Set<CancelledEvent> cancelledEvents) {
		this.cancelledEvents=cancelledEvents;
	}
	
	public void setDateOfBirth(Date dateOfBirth) {
		for ( ChildHasRelation curVal : getChildHasRelation_godParent() ) {
			curVal.getGodParent().z_internalRemoveFromChildHasRelation_child(this.getName(),this.getDateOfBirth(),curVal);
		}
		if ( dateOfBirth == null ) {
			this.z_internalRemoveFromDateOfBirth(getDateOfBirth());
		} else {
			this.z_internalAddToDateOfBirth(dateOfBirth);
		}
		for ( ChildHasRelation curVal : getChildHasRelation_godParent() ) {
			curVal.getGodParent().z_internalAddToChildHasRelation_child(this.getName(),this.getDateOfBirth(),curVal);
		}
	}
	
	public void setDeletedOn(Date deletedOn) {
		this.deletedOn=deletedOn;
	}
	
	public void setFamily(Family family) {
		for ( FamilyMemberHasRelation curVal : getFamilyMemberHasRelation_relation() ) {
			curVal.getRelation().z_internalRemoveFromFamilyMemberHasRelation_familyMember(this.getFamily(),this.getName(),curVal);
		}
		if ( this.getFamily()!=null ) {
			this.getFamily().z_internalRemoveFromChild(this.getName(),this);
		}
		if ( family == null ) {
			this.z_internalRemoveFromFamily(this.getFamily());
		} else {
			if ( getName()==null ) {
				throw new IllegalStateException("The qualifying property 'name' must be set before adding a value to 'family'");
			}
			this.z_internalAddToFamily(family);
		}
		if ( family!=null ) {
			family.z_internalAddToChild(this.getName(),this);
			setDeletedOn(Stdlib.FUTURE);
		} else {
			markDeleted();
		}
		for ( FamilyMemberHasRelation curVal : getFamilyMemberHasRelation_relation() ) {
			curVal.getRelation().z_internalAddToFamilyMemberHasRelation_familyMember(this.getFamily(),this.getName(),curVal);
		}
	}
	
	public void setFather(Father father) {
		if ( this.getFather()!=null ) {
			this.getFather().z_internalRemoveFromChild(this.getName(),this);
		}
		if ( father == null ) {
			this.z_internalRemoveFromFather(this.getFather());
		} else {
			if ( getName()==null ) {
				throw new IllegalStateException("The qualifying property 'name' must be set before adding a value to 'father'");
			}
			this.z_internalAddToFather(father);
		}
		if ( father!=null ) {
			father.z_internalAddToChild(this.getName(),this);
		}
	}
	
	public void setFirstBornSibling(Child firstBornSibling) {
		if ( this.getFirstBornSibling()!=null ) {
			this.getFirstBornSibling().z_internalRemoveFromYounberSiblings(this);
		}
		if ( firstBornSibling == null ) {
			this.z_internalRemoveFromFirstBornSibling(this.getFirstBornSibling());
		} else {
			this.z_internalAddToFirstBornSibling(firstBornSibling);
		}
		if ( firstBornSibling!=null ) {
			firstBornSibling.z_internalAddToYounberSiblings(this);
		}
	}
	
	public void setGodParent(Set<Relation> godParent) {
		this.clearGodParent();
		this.addAllToGodParent(godParent);
	}
	
	public void setId(Long id) {
		this.id=id;
	}
	
	public void setMother(Mother mother) {
		if ( this.getMother()!=null ) {
			this.getMother().z_internalRemoveFromChild(this.getName(),this);
		}
		if ( mother == null ) {
			this.z_internalRemoveFromMother(this.getMother());
		} else {
			if ( getName()==null ) {
				throw new IllegalStateException("The qualifying property 'name' must be set before adding a value to 'mother'");
			}
			this.z_internalAddToMother(mother);
		}
		if ( mother!=null ) {
			mother.z_internalAddToChild(this.getName(),this);
		}
	}
	
	public void setName(String name) {
		if ( getMother()!=null && getName()!=null ) {
			getMother().z_internalRemoveFromChild(this.getName(),this);
		}
		for ( ChildHasRelation curVal : getChildHasRelation_godParent() ) {
			curVal.getGodParent().z_internalRemoveFromChildHasRelation_child(this.getName(),this.getDateOfBirth(),curVal);
		}
		if ( getFather()!=null && getName()!=null ) {
			getFather().z_internalRemoveFromChild(this.getName(),this);
		}
		if ( getFamily()!=null && getName()!=null ) {
			getFamily().z_internalRemoveFromChild(this.getName(),this);
		}
		for ( FamilyMemberHasRelation curVal : getFamilyMemberHasRelation_relation() ) {
			curVal.getRelation().z_internalRemoveFromFamilyMemberHasRelation_familyMember(this.getFamily(),this.getName(),curVal);
		}
		if ( name == null ) {
			this.z_internalRemoveFromName(getName());
		} else {
			this.z_internalAddToName(name);
		}
		if ( getMother()!=null && getName()!=null ) {
			getMother().z_internalAddToChild(this.getName(),this);
		}
		for ( ChildHasRelation curVal : getChildHasRelation_godParent() ) {
			curVal.getGodParent().z_internalAddToChildHasRelation_child(this.getName(),this.getDateOfBirth(),curVal);
		}
		if ( getFather()!=null && getName()!=null ) {
			getFather().z_internalAddToChild(this.getName(),this);
		}
		if ( getFamily()!=null && getName()!=null ) {
			getFamily().z_internalAddToChild(this.getName(),this);
		}
		for ( FamilyMemberHasRelation curVal : getFamilyMemberHasRelation_relation() ) {
			curVal.getRelation().z_internalAddToFamilyMemberHasRelation_familyMember(this.getFamily(),this.getName(),curVal);
		}
	}
	
	public void setObjectVersion(int objectVersion) {
		this.objectVersion=objectVersion;
	}
	
	public void setOutgoingEvents(Set<OutgoingEvent> outgoingEvents) {
		this.outgoingEvents=outgoingEvents;
	}
	
	public void setRelation(Set<Relation> relation) {
		this.clearRelation();
		this.addAllToRelation(relation);
	}
	
	public void setStepSibling(Set<StepChild> stepSibling) {
		this.clearStepSibling();
		this.addAllToStepSibling(stepSibling);
	}
	
	public void setUid(String newUid) {
		this.uid=newUid;
	}
	
	public void setYounberSiblings(Set<Child> younberSiblings) {
		this.clearYounberSiblings();
		this.addAllToYounberSiblings(younberSiblings);
	}
	
	public void setZ_keyOfChildOnFamily(String z_keyOfChildOnFamily) {
		this.z_keyOfChildOnFamily=z_keyOfChildOnFamily;
	}
	
	public void setZ_keyOfChildOnFather(String z_keyOfChildOnFather) {
		this.z_keyOfChildOnFather=z_keyOfChildOnFather;
	}
	
	public void setZ_keyOfChildOnMother(String z_keyOfChildOnMother) {
		this.z_keyOfChildOnMother=z_keyOfChildOnMother;
	}
	
	public String toXmlReferenceString() {
		return "<Child uid=\""+getUid() + "\"/>";
	}
	
	public String toXmlString() {
		StringBuilder sb = new StringBuilder();
		sb.append("<Child ");
		sb.append("classUuid=\"Structures.uml@_V2hysIhqEeK4s7QGypAJBA\" ");
		sb.append("className=\"org.opaeum.test.Child\" ");
		sb.append("uid=\"" + this.getUid() + "\" ");
		if ( getName()!=null ) {
			sb.append("name=\""+ ModelFormatter.getInstance().formatString(getName())+"\" ");
		}
		if ( getDateOfBirth()!=null ) {
			sb.append("dateOfBirth=\""+ ModelFormatter.getInstance().formatDate(getDateOfBirth())+"\" ");
		}
		sb.append(">");
		if ( getMother()==null ) {
			sb.append("\n<mother/>");
		} else {
			sb.append("\n<mother propertyId=\"3843307682732995451\">");
			sb.append("\n" + getMother().toXmlReferenceString());
			sb.append("\n</mother>");
		}
		if ( getFirstBornSibling()==null ) {
			sb.append("\n<firstBornSibling/>");
		} else {
			sb.append("\n<firstBornSibling propertyId=\"2122232498866777869\">");
			sb.append("\n" + getFirstBornSibling().toXmlReferenceString());
			sb.append("\n</firstBornSibling>");
		}
		if ( getFather()==null ) {
			sb.append("\n<father/>");
		} else {
			sb.append("\n<father propertyId=\"7915129683656142253\">");
			sb.append("\n" + getFather().toXmlReferenceString());
			sb.append("\n</father>");
		}
		sb.append("\n<childHasRelation_godParent propertyId=\"8434948450232903107\">");
		for ( ChildHasRelation childHasRelation_godParent : getChildHasRelation_godParent() ) {
			sb.append("\n" + childHasRelation_godParent.toXmlReferenceString());
		}
		sb.append("\n</childHasRelation_godParent>");
		sb.append("\n<siblingStepSibling_stepSibling propertyId=\"2983107073586996627\">");
		for ( SiblingStepSibling siblingStepSibling_stepSibling : getSiblingStepSibling_stepSibling() ) {
			sb.append("\n" + siblingStepSibling_stepSibling.toXmlReferenceString());
		}
		sb.append("\n</siblingStepSibling_stepSibling>");
		sb.append("\n<familyMemberHasRelation_relation propertyId=\"5718737559910777343\">");
		for ( FamilyMemberHasRelation familyMemberHasRelation_relation : getFamilyMemberHasRelation_relation() ) {
			sb.append("\n" + familyMemberHasRelation_relation.toXmlReferenceString());
		}
		sb.append("\n</familyMemberHasRelation_relation>");
		sb.append("\n</Child>");
		return sb.toString();
	}
	
	public void z_internalAddToChildHasRelation_godParent(String firstName, String surname, Date dateOfBirth, ChildHasRelation childHasRelation_godParent) {
		String key = ModelFormatter.getInstance().formatStringQualifier(firstName)+ModelFormatter.getInstance().formatStringQualifier(surname)+ModelFormatter.getInstance().formatDateQualifier(dateOfBirth);
		if ( getChildHasRelation_godParent().contains(childHasRelation_godParent) ) {
			return;
		}
		childHasRelation_godParent.getGodParent().z_internalAddToFirstName(firstName);
		childHasRelation_godParent.getGodParent().z_internalAddToSurname(surname);
		childHasRelation_godParent.getGodParent().z_internalAddToDateOfBirth(dateOfBirth);
		this.childHasRelation_godParent.put(key.toString(),childHasRelation_godParent);
		childHasRelation_godParent.setZ_keyOfGodParentOnChild(key.toString());
	}
	
	public void z_internalAddToDateOfBirth(Date dateOfBirth) {
		if ( dateOfBirth.equals(this.dateOfBirth) ) {
			return;
		}
		this.dateOfBirth=dateOfBirth;
	}
	
	public void z_internalAddToFamily(Family family) {
		if ( family.equals(this.family) ) {
			return;
		}
		this.family=family;
	}
	
	public void z_internalAddToFamilyMemberHasRelation_relation(String firstName, String surname, Date dateOfBirth, FamilyMemberHasRelation familyMemberHasRelation_relation) {
		String key = ModelFormatter.getInstance().formatStringQualifier(firstName)+ModelFormatter.getInstance().formatStringQualifier(surname)+ModelFormatter.getInstance().formatDateQualifier(dateOfBirth);
		if ( getFamilyMemberHasRelation_relation().contains(familyMemberHasRelation_relation) ) {
			return;
		}
		familyMemberHasRelation_relation.getRelation().z_internalAddToFirstName(firstName);
		familyMemberHasRelation_relation.getRelation().z_internalAddToSurname(surname);
		familyMemberHasRelation_relation.getRelation().z_internalAddToDateOfBirth(dateOfBirth);
		this.familyMemberHasRelation_relation.put(key.toString(),familyMemberHasRelation_relation);
		familyMemberHasRelation_relation.setZ_keyOfRelationOnFamilyMember(key.toString());
	}
	
	public void z_internalAddToFather(Father father) {
		if ( father.equals(this.father) ) {
			return;
		}
		this.father=father;
	}
	
	public void z_internalAddToFirstBornSibling(Child firstBornSibling) {
		if ( firstBornSibling.equals(this.firstBornSibling) ) {
			return;
		}
		this.firstBornSibling=firstBornSibling;
	}
	
	public void z_internalAddToMother(Mother mother) {
		if ( mother.equals(this.mother) ) {
			return;
		}
		this.mother=mother;
	}
	
	public void z_internalAddToName(String name) {
		if ( name.equals(this.name) ) {
			return;
		}
		this.name=name;
	}
	
	public void z_internalAddToSiblingStepSibling_stepSibling(SiblingStepSibling siblingStepSibling_stepSibling) {
		if ( getSiblingStepSibling_stepSibling().contains(siblingStepSibling_stepSibling) ) {
			return;
		}
		this.siblingStepSibling_stepSibling.add(siblingStepSibling_stepSibling);
	}
	
	public void z_internalAddToYounberSiblings(Child younberSiblings) {
		if ( this.younberSiblings.contains(younberSiblings) ) {
			return;
		}
		this.younberSiblings.add(younberSiblings);
	}
	
	public void z_internalRemoveFromChildHasRelation_godParent(String firstName, String surname, Date dateOfBirth, ChildHasRelation childHasRelation_godParent) {
		String key = ModelFormatter.getInstance().formatStringQualifier(firstName)+ModelFormatter.getInstance().formatStringQualifier(surname)+ModelFormatter.getInstance().formatDateQualifier(dateOfBirth);
		this.childHasRelation_godParent.remove(key.toString());
	}
	
	public void z_internalRemoveFromDateOfBirth(Date dateOfBirth) {
		if ( getDateOfBirth()!=null && dateOfBirth!=null && dateOfBirth.equals(getDateOfBirth()) ) {
			this.dateOfBirth=null;
			this.dateOfBirth=null;
		}
	}
	
	public void z_internalRemoveFromFamily(Family family) {
		if ( getFamily()!=null && family!=null && family.equals(getFamily()) ) {
			this.family=null;
			this.family=null;
		}
	}
	
	public void z_internalRemoveFromFamilyMemberHasRelation_relation(String firstName, String surname, Date dateOfBirth, FamilyMemberHasRelation familyMemberHasRelation_relation) {
		String key = ModelFormatter.getInstance().formatStringQualifier(firstName)+ModelFormatter.getInstance().formatStringQualifier(surname)+ModelFormatter.getInstance().formatDateQualifier(dateOfBirth);
		this.familyMemberHasRelation_relation.remove(key.toString());
	}
	
	public void z_internalRemoveFromFather(Father father) {
		if ( getFather()!=null && father!=null && father.equals(getFather()) ) {
			this.father=null;
			this.father=null;
		}
	}
	
	public void z_internalRemoveFromFirstBornSibling(Child firstBornSibling) {
		if ( getFirstBornSibling()!=null && firstBornSibling!=null && firstBornSibling.equals(getFirstBornSibling()) ) {
			this.firstBornSibling=null;
			this.firstBornSibling=null;
		}
	}
	
	public void z_internalRemoveFromMother(Mother mother) {
		if ( getMother()!=null && mother!=null && mother.equals(getMother()) ) {
			this.mother=null;
			this.mother=null;
		}
	}
	
	public void z_internalRemoveFromName(String name) {
		if ( getName()!=null && name!=null && name.equals(getName()) ) {
			this.name=null;
			this.name=null;
		}
	}
	
	public void z_internalRemoveFromSiblingStepSibling_stepSibling(SiblingStepSibling siblingStepSibling_stepSibling) {
		this.siblingStepSibling_stepSibling.remove(siblingStepSibling_stepSibling);
	}
	
	public void z_internalRemoveFromYounberSiblings(Child younberSiblings) {
		this.younberSiblings.remove(younberSiblings);
	}

}