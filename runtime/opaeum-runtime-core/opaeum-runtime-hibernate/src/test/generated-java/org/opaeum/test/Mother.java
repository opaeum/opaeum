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
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

import org.hibernate.annotations.AccessType;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.Index;
import org.hibernate.annotations.LazyCollection;
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
import org.opaeum.test.util.ModelFormatter;
import org.opaeum.test.util.Stdlib;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

@NumlMetaInfo(applicationIdentifier="opaeum_hibernate_tests",uuid="Structures.uml@_YergsIhqEeK4s7QGypAJBA")
@Filter(name="noDeletedObjects")
@org.hibernate.annotations.Entity(dynamicUpdate=true)
@AccessType(	"field")
@Table(name="mother",uniqueConstraints={
	@UniqueConstraint(columnNames={"husband_id","deleted_on"}),
	@UniqueConstraint(columnNames={"family_id","deleted_on"})})
@Entity(name="Mother")
public class Mother implements SurnameProvider, Spouse, FamilyMember, IPersistentObject, IEventGenerator, HibernateEntity, CompositionNode, Serializable {
	@Transient
	private Set<CancelledEvent> cancelledEvents = new HashSet<CancelledEvent>();
	@LazyCollection(	org.hibernate.annotations.LazyCollectionOption.TRUE)
	@Filter(condition="deleted_on > current_timestamp",name="noDeletedObjects")
	@OneToMany(fetch=javax.persistence.FetchType.LAZY,mappedBy="mother",targetEntity=Child.class)
	@MapKey(name="z_keyOfChildOnMother")
	protected Map<String, Child> child = new HashMap<String,Child>();
		// Initialise to 1000 from 1970
	@Temporal(	javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="deleted_on")
	private Date deletedOn = Stdlib.FUTURE;
	@Index(columnNames="family_id",name="idx_mother_family_id")
	@ManyToOne(fetch=javax.persistence.FetchType.LAZY)
	@JoinColumn(name="family_id",nullable=true)
	protected Family family;
	@Where(clause="family_member_type='Structures.uml@_YergsIhqEeK4s7QGypAJBA'")
	@LazyCollection(	org.hibernate.annotations.LazyCollectionOption.TRUE)
	@Filter(condition="deleted_on > current_timestamp",name="noDeletedObjects")
	@OneToMany(cascade=javax.persistence.CascadeType.ALL,fetch=javax.persistence.FetchType.LAZY,targetEntity=FamilyMemberHasRelation.class)
	@MapKey(name="z_keyOfRelationOnFamilyMember")
	@JoinColumn(name="family_member",nullable=true)
	protected Map<String, FamilyMemberHasRelation> familyMemberHasRelation_relation = new HashMap<String,FamilyMemberHasRelation>();
	@Column(name="firstname")
	@Basic
	protected String firstname;
	@Index(columnNames="husband_id",name="idx_mother_husband_id")
	@ManyToOne(fetch=javax.persistence.FetchType.LAZY)
	@JoinColumn(name="husband_id",nullable=true)
	protected Father husband;
	@TableGenerator(allocationSize=20,name="id_generator",pkColumnName="type",pkColumnValue="mother",table="hi_value")
	@Id
	@GeneratedValue(generator="id_generator",strategy=javax.persistence.GenerationType.TABLE)
	private Long id;
	@OneToOne(cascade=javax.persistence.CascadeType.ALL,fetch=javax.persistence.FetchType.LAZY)
	@JoinColumn(name="marriage_spouse_id",nullable=true)
	protected Marriage marriage_spouse;
	@OneToOne(cascade=javax.persistence.CascadeType.ALL,fetch=javax.persistence.FetchType.LAZY)
	@JoinColumn(name="marriage_surname_provider_id",nullable=true)
	protected Marriage marriage_surnameProvider;
	static private Set<? extends Mother> mockedAllInstances;
	@LazyCollection(	org.hibernate.annotations.LazyCollectionOption.TRUE)
	@Filter(condition="deleted_on > current_timestamp",name="noDeletedObjects")
	@OneToMany(cascade=javax.persistence.CascadeType.ALL,fetch=javax.persistence.FetchType.LAZY,mappedBy="stepMother",targetEntity=MotherStepChildren.class)
	protected Set<MotherStepChildren> motherStepChildren_stepChild = new HashSet<MotherStepChildren>();
	@Version
	@Column(name="object_version")
	private int objectVersion;
	@Transient
	private Set<OutgoingEvent> outgoingEvents = new HashSet<OutgoingEvent>();
	@Transient
	private InternalHibernatePersistence persistence;
	static final private long serialVersionUID = 2128003045175769585l;
	@Column(name="surname")
	@Basic
	protected String surname;
	@Where(clause="surname_provider_type='Structures.uml@_YergsIhqEeK4s7QGypAJBA'")
	@LazyCollection(	org.hibernate.annotations.LazyCollectionOption.TRUE)
	@Filter(condition="deleted_on > current_timestamp",name="noDeletedObjects")
	@OneToMany(cascade=javax.persistence.CascadeType.ALL,fetch=javax.persistence.FetchType.LAZY,targetEntity=SurnameProviderHasDaughter.class)
	@MapKey(name="z_keyOfSurnameCarryingDaughterOnSurnameProvider")
	@JoinColumn(name="surname_provider",nullable=true)
	protected Map<String, SurnameProviderHasDaughter> surnameProviderHasDaughter_surnameCarryingDaughter = new HashMap<String,SurnameProviderHasDaughter>();
	@Where(clause="surname_provider_type='Structures.uml@_YergsIhqEeK4s7QGypAJBA'")
	@LazyCollection(	org.hibernate.annotations.LazyCollectionOption.TRUE)
	@Filter(condition="deleted_on > current_timestamp",name="noDeletedObjects")
	@OneToMany(cascade=javax.persistence.CascadeType.ALL,fetch=javax.persistence.FetchType.LAZY,targetEntity=SurnameProviderHasSon.class)
	@JoinColumn(name="surname_provider",nullable=true)
	protected Set<SurnameProviderHasSon> surnameProviderHasSon_surnameCarryingSon = new HashSet<SurnameProviderHasSon>();
	private String uid;

	/** This constructor is intended for easy initialization in unit tests
	 * 
	 * @param owningObject 
	 */
	public Mother(Family owningObject) {
		init(owningObject);
		addToOwningObject();
	}
	
	/** Default constructor for Mother
	 */
	public Mother() {
	}

	public void addAllToChild(Set<Child> child) {
		for ( Child o : child ) {
			addToChild(o.getName(),o);
		}
	}
	
	public void addAllToRelation(Set<Relation> relation) {
		for ( Relation o : relation ) {
			addToRelation(o.getFirstName(),o.getSurname(),o.getDateOfBirth(),o);
		}
	}
	
	public void addAllToStepChild(Set<StepChild> stepChild) {
		for ( StepChild o : stepChild ) {
			addToStepChild(o);
		}
	}
	
	public void addAllToSurnameCarryingDaughter(Set<Sister> surnameCarryingDaughter) {
		for ( Sister o : surnameCarryingDaughter ) {
			addToSurnameCarryingDaughter(o.getName(),o);
		}
	}
	
	public void addAllToSurnameCarryingSon(Set<Brother> surnameCarryingSon) {
		for ( Brother o : surnameCarryingSon ) {
			addToSurnameCarryingSon(o);
		}
	}
	
	public void addToChild(String name, Child child) {
		if ( child!=null ) {
			if ( child.getMother()!=null ) {
				child.getMother().removeFromChild(child.getName(),child);
			}
			child.z_internalAddToMother(this);
		}
		z_internalAddToChild(name,child);
	}
	
	/** Call this method when you want to attach this object to the containment tree. Useful with transitive persistence
	 */
	public void addToOwningObject() {
		getFamily().z_internalAddToMother((Mother)this);
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
	
	public void addToStepChild(StepChild stepChild) {
		if ( stepChild!=null && !this.getStepChild().contains(stepChild) ) {
			MotherStepChildren newLink = new MotherStepChildren((Mother)this,(StepChild)stepChild);
			if ( stepChild.getStepMother()!=null ) {
				stepChild.getStepMother().removeFromStepChild(stepChild);
			}
			this.z_internalAddToMotherStepChildren_stepChild(newLink);
			stepChild.z_internalAddToMotherStepChildren_stepMother(newLink);
		}
	}
	
	public void addToSurnameCarryingDaughter(String name, Sister surnameCarryingDaughter) {
		if ( surnameCarryingDaughter!=null && !this.getSurnameCarryingDaughter().contains(surnameCarryingDaughter) ) {
			SurnameProviderHasDaughter newLink = new SurnameProviderHasDaughter((SurnameProvider)this,(Sister)surnameCarryingDaughter);
			if ( surnameCarryingDaughter.getSurnameProvider()!=null ) {
				surnameCarryingDaughter.getSurnameProvider().removeFromSurnameCarryingDaughter(surnameCarryingDaughter.getName(),surnameCarryingDaughter);
			}
			this.z_internalAddToSurnameProviderHasDaughter_surnameCarryingDaughter(name,newLink);
			surnameCarryingDaughter.z_internalAddToSurnameProviderHasDaughter_surnameProvider(newLink);
		}
	}
	
	public void addToSurnameCarryingSon(Brother surnameCarryingSon) {
		if ( surnameCarryingSon!=null && !this.getSurnameCarryingSon().contains(surnameCarryingSon) ) {
			SurnameProviderHasSon newLink = new SurnameProviderHasSon((SurnameProvider)this,(Brother)surnameCarryingSon);
			if ( surnameCarryingSon.getSurnameProvider()!=null ) {
				surnameCarryingSon.getSurnameProvider().removeFromSurnameCarryingSon(surnameCarryingSon);
			}
			this.z_internalAddToSurnameProviderHasSon_surnameCarryingSon(newLink);
			surnameCarryingSon.z_internalAddToSurnameProviderHasSon_surnameProvider(newLink);
		}
	}
	
	static public Set<? extends Mother> allInstances(AbstractPersistence persistence) {
		if ( mockedAllInstances==null ) {
			return new HashSet(persistence.readAll(org.opaeum.test.Mother.class));
		} else {
			return mockedAllInstances;
		}
	}
	
	public void buildTreeFromXml(Element xml, Map<String, Object> map) {
		setUid(xml.getAttribute("uid"));
		if ( xml.getAttribute("firstname").length()>0 ) {
			setFirstname(ModelFormatter.getInstance().parseString(xml.getAttribute("firstname")));
		}
		if ( xml.getAttribute("surname").length()>0 ) {
			setSurname(ModelFormatter.getInstance().parseString(xml.getAttribute("surname")));
		}
		NodeList propertyNodes = xml.getChildNodes();
		int i = 0;
		while ( i<propertyNodes.getLength() ) {
			Node currentPropertyNode = propertyNodes.item(i++);
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("surnameProviderHasSon_surnameCarryingSon") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("9051680456867763967")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						SurnameProviderHasSon curVal;
						try {
							curVal=IntrospectionUtil.newInstance(((Element)currentPropertyValueNode).getAttribute("className"));
						} catch (Exception e) {
							curVal=org.opaeum.test.util.ModelJavaMetaInfoMap.INSTANCE.newInstance(((Element)currentPropertyValueNode).getAttribute("classUuid"));
						}
						curVal.buildTreeFromXml((Element)currentPropertyValueNode,map);
						this.z_internalAddToSurnameProviderHasSon_surnameCarryingSon(curVal);
						curVal.z_internalAddToSurnameProvider(this);
						map.put(curVal.getUid(), curVal);
					}
				}
			}
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("surnameProviderHasDaughter_surnameCarryingDaughter") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("3102093816030840167")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						SurnameProviderHasDaughter curVal;
						try {
							curVal=IntrospectionUtil.newInstance(((Element)currentPropertyValueNode).getAttribute("className"));
						} catch (Exception e) {
							curVal=org.opaeum.test.util.ModelJavaMetaInfoMap.INSTANCE.newInstance(((Element)currentPropertyValueNode).getAttribute("classUuid"));
						}
						curVal.buildTreeFromXml((Element)currentPropertyValueNode,map);
						this.z_internalAddToSurnameProviderHasDaughter_surnameCarryingDaughter(curVal.getSurnameCarryingDaughter().getName(),curVal);
						curVal.z_internalAddToSurnameProvider(this);
						map.put(curVal.getUid(), curVal);
					}
				}
			}
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("marriage_surnameProvider") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("6783615175887897848")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						Marriage curVal;
						try {
							curVal=IntrospectionUtil.newInstance(((Element)currentPropertyValueNode).getAttribute("className"));
						} catch (Exception e) {
							curVal=org.opaeum.test.util.ModelJavaMetaInfoMap.INSTANCE.newInstance(((Element)currentPropertyValueNode).getAttribute("classUuid"));
						}
						curVal.buildTreeFromXml((Element)currentPropertyValueNode,map);
						this.z_internalAddToMarriage_surnameProvider(curVal);
						curVal.z_internalAddToSpouse(this);
						map.put(curVal.getUid(), curVal);
					}
				}
			}
		}
	}
	
	public void clearChild() {
		Set<Child> tmp = new HashSet<Child>(getChild());
		for ( Child o : tmp ) {
			removeFromChild(o.getName(),o);
		}
	}
	
	public void clearRelation() {
		Set<Relation> tmp = new HashSet<Relation>(getRelation());
		for ( Relation o : tmp ) {
			removeFromRelation(o.getFirstName(),o.getSurname(),o.getDateOfBirth(),o);
		}
	}
	
	public void clearStepChild() {
		Set<StepChild> tmp = new HashSet<StepChild>(getStepChild());
		for ( StepChild o : tmp ) {
			removeFromStepChild(o);
		}
	}
	
	public void clearSurnameCarryingDaughter() {
		Set<Sister> tmp = new HashSet<Sister>(getSurnameCarryingDaughter());
		for ( Sister o : tmp ) {
			removeFromSurnameCarryingDaughter(o.getName(),o);
		}
	}
	
	public void clearSurnameCarryingSon() {
		Set<Brother> tmp = new HashSet<Brother>(getSurnameCarryingSon());
		for ( Brother o : tmp ) {
			removeFromSurnameCarryingSon(o);
		}
	}
	
	public void copyShallowState(Mother from, Mother to) {
		to.setFirstname(from.getFirstname());
		to.setSurname(from.getSurname());
		if ( from.getHusband()!=null ) {
			to.z_internalAddToHusband(from.getHusband().makeShallowCopy());
		}
		if ( from.getMarriage_spouse()!=null ) {
			to.z_internalAddToMarriage_spouse(from.getMarriage_spouse().makeShallowCopy());
		}
		if ( from.getMarriage_surnameProvider()!=null ) {
			to.z_internalAddToMarriage_surnameProvider(from.getMarriage_surnameProvider().makeShallowCopy());
		}
	}
	
	public void copyState(Mother from, Mother to) {
		to.setFirstname(from.getFirstname());
		to.setSurname(from.getSurname());
		if ( from.getHusband()!=null ) {
			to.z_internalAddToHusband(from.getHusband().makeCopy());
		}
		if ( from.getMarriage_spouse()!=null ) {
			to.z_internalAddToMarriage_spouse(from.getMarriage_spouse().makeCopy());
		}
		if ( from.getMarriage_surnameProvider()!=null ) {
			to.z_internalAddToMarriage_surnameProvider(from.getMarriage_surnameProvider().makeCopy());
		}
	}
	
	public void createComponents() {
	}
	
	public boolean equals(Object other) {
		if ( other instanceof Mother ) {
			return other==this || ((Mother)other).getUid().equals(this.getUid());
		}
		return false;
	}
	
	public Set<CancelledEvent> getCancelledEvents() {
		return this.cancelledEvents;
	}
	
	public Child getChild(String name) {
		Child result = null;
		String key = ModelFormatter.getInstance().formatStringQualifier(name);
		result=this.child.get(key.toString());
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=644519113065554701l,opposite="mother",uuid="Structures.uml@_EJPEMYsgEeKWvpYIRX9T4g")
	@NumlMetaInfo(uuid="Structures.uml@_EJPEMYsgEeKWvpYIRX9T4g")
	public Set<Child> getChild() {
		Set result = new HashSet<Child>(this.child.values());
		
		return result;
	}
	
	public Date getDeletedOn() {
		return this.deletedOn;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=4605372521822203769l,opposite="mother",uuid="Structures.uml@_liiYkYhqEeK4s7QGypAJBA")
	@NumlMetaInfo(uuid="Structures.uml@_liiYkYhqEeK4s7QGypAJBA")
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
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=4721126843880414953l,uuid="Structures.uml@_eepyMIiCEeK3L6u7KcC64Q")
	@NumlMetaInfo(uuid="Structures.uml@_eepyMIiCEeK3L6u7KcC64Q")
	public String getFirstname() {
		String result = this.firstname;
		
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=4806022725669416140l,opposite="wife",uuid="Structures.uml@_7cAt0Yr9EeKfXcW1LrVoAA")
	@NumlMetaInfo(uuid="Structures.uml@_7cAt0Yr9EeKfXcW1LrVoAA")
	public Father getHusband() {
		Father result = this.husband;
		
		return result;
	}
	
	public Long getId() {
		return this.id;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=599591342263275344l,opposite="surnameProvider",uuid="Structures.uml@_fz0rsIn-EeKv0PcdrJJtzg")
	@NumlMetaInfo(uuid="Structures.uml@_Zi2eAIhrEeK4s7QGypAJBA@Structures.uml@_fz0rsIn-EeKv0PcdrJJtzg")
	public Marriage getMarriage_spouse() {
		Marriage result = this.marriage_spouse;
		
		return result;
	}
	
	public Marriage getMarriage_spouseFor(Spouse match) {
		if ( marriage_spouse.getSpouse().equals(match) ) {
			return marriage_spouse;
		} else {
			return null;
		}
	}
	
	@PropertyMetaInfo(constraints={},isComposite=true,opaeumId=6783615175887897848l,opposite="spouse",uuid="Structures.uml@_fz0rsIn-EeKv0PcdrJJtzg")
	@NumlMetaInfo(uuid="Structures.uml@_wqZp8IjPEeKq68owPnlvHg@Structures.uml@_fz0rsIn-EeKv0PcdrJJtzg")
	public Marriage getMarriage_surnameProvider() {
		Marriage result = this.marriage_surnameProvider;
		
		return result;
	}
	
	public Marriage getMarriage_surnameProviderFor(SurnameProvider match) {
		if ( marriage_surnameProvider.getSurnameProvider().equals(match) ) {
			return marriage_surnameProvider;
		} else {
			return null;
		}
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=3786800531408988259l,opposite="stepMother",uuid="Structures.uml@_ncdcsI1OEeKgGLBcRSZFfw")
	@NumlMetaInfo(uuid="Structures.uml@_YergsIhqEeK4s7QGypAJBA@Structures.uml@_ncdcsI1OEeKgGLBcRSZFfw")
	public Set<MotherStepChildren> getMotherStepChildren_stepChild() {
		Set result = this.motherStepChildren_stepChild;
		
		return result;
	}
	
	public MotherStepChildren getMotherStepChildren_stepChildFor(StepChild match) {
		for ( MotherStepChildren var : getMotherStepChildren_stepChild() ) {
			if ( var.getStepChild().equals(match) ) {
				return var;
			}
		}
		return null;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=2192193425227496123l,uuid="Structures.uml@_mEtEwI08EeKHBNiW4NWnIg")
	@NumlMetaInfo(uuid="Structures.uml@_mEtEwI08EeKHBNiW4NWnIg")
	public String getName() {
		String result = "";
		
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
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=112985228842363744l,opposite="surnameProvider",uuid="Structures.uml@_fz0rsYn-EeKv0PcdrJJtzg")
	public Spouse getSpouse() {
		Spouse result = null;
		if ( this.marriage_spouse!=null ) {
			result = this.marriage_spouse.getSpouse();
		}
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=2501455895100216475l,opposite="stepMother",uuid="Structures.uml@_ncZyUI1OEeKgGLBcRSZFfw")
	public Set<StepChild> getStepChild() {
		Set result = new HashSet<StepChild>();
		for ( MotherStepChildren cur : this.getMotherStepChildren_stepChild() ) {
			result.add(cur.getStepChild());
		}
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=1128330048655112973l,uuid="Structures.uml@_CZjEgIiJEeKzQpCKar_mrQ")
	@NumlMetaInfo(uuid="Structures.uml@_CZjEgIiJEeKzQpCKar_mrQ")
	public String getSurname() {
		String result = this.surname;
		
		return result;
	}
	
	public Sister getSurnameCarryingDaughter(String name) {
		Sister result = null;
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=3588699929451751767l,opposite="surnameProvider",uuid="Structures.uml@_gtNy8YhrEeK4s7QGypAJBA")
	public Set<Sister> getSurnameCarryingDaughter() {
		Set result = new HashSet<Sister>();
		for ( SurnameProviderHasDaughter cur : this.getSurnameProviderHasDaughter_surnameCarryingDaughter() ) {
			result.add(cur.getSurnameCarryingDaughter());
		}
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=8908457503420876049l,opposite="surnameProvider",uuid="Structures.uml@_g-YbcYhrEeK4s7QGypAJBA")
	public Set<Brother> getSurnameCarryingSon() {
		Set result = new HashSet<Brother>();
		for ( SurnameProviderHasSon cur : this.getSurnameProviderHasSon_surnameCarryingSon() ) {
			result.add(cur.getSurnameCarryingSon());
		}
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=2792093672067248130l,opposite="spouse",uuid="Structures.uml@_fz0rtIn-EeKv0PcdrJJtzg")
	public SurnameProvider getSurnameProvider() {
		SurnameProvider result = null;
		if ( this.marriage_surnameProvider!=null ) {
			result = this.marriage_surnameProvider.getSurnameProvider();
		}
		return result;
	}
	
	public SurnameProviderHasDaughter getSurnameProviderHasDaughter_surnameCarryingDaughter(String name) {
		SurnameProviderHasDaughter result = null;
		String key = ModelFormatter.getInstance().formatStringQualifier(name);
		result=this.surnameProviderHasDaughter_surnameCarryingDaughter.get(key.toString());
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=true,opaeumId=3102093816030840167l,opposite="surnameProvider",uuid="Structures.uml@_gtNy8IhrEeK4s7QGypAJBA")
	@NumlMetaInfo(uuid="Structures.uml@_Zi2eAIhrEeK4s7QGypAJBA@Structures.uml@_gtNy8IhrEeK4s7QGypAJBA")
	public Set<SurnameProviderHasDaughter> getSurnameProviderHasDaughter_surnameCarryingDaughter() {
		Set result = new HashSet<SurnameProviderHasDaughter>(this.surnameProviderHasDaughter_surnameCarryingDaughter.values());
		
		return result;
	}
	
	public SurnameProviderHasDaughter getSurnameProviderHasDaughter_surnameCarryingDaughterFor(Sister match) {
		for ( SurnameProviderHasDaughter var : getSurnameProviderHasDaughter_surnameCarryingDaughter() ) {
			if ( var.getSurnameCarryingDaughter().equals(match) ) {
				return var;
			}
		}
		return null;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=true,opaeumId=9051680456867763967l,opposite="surnameProvider",uuid="Structures.uml@_g-YbcIhrEeK4s7QGypAJBA")
	@NumlMetaInfo(uuid="Structures.uml@_Zi2eAIhrEeK4s7QGypAJBA@Structures.uml@_g-YbcIhrEeK4s7QGypAJBA")
	public Set<SurnameProviderHasSon> getSurnameProviderHasSon_surnameCarryingSon() {
		Set result = this.surnameProviderHasSon_surnameCarryingSon;
		
		return result;
	}
	
	public SurnameProviderHasSon getSurnameProviderHasSon_surnameCarryingSonFor(Brother match) {
		for ( SurnameProviderHasSon var : getSurnameProviderHasSon_surnameCarryingSon() ) {
			if ( var.getSurnameCarryingSon().equals(match) ) {
				return var;
			}
		}
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
		this.z_internalAddToFamily((Family)owner);
	}
	
	public Mother makeCopy() {
		Mother result = new Mother();
		copyState((Mother)this,result);
		return result;
	}
	
	public Mother makeShallowCopy() {
		Mother result = new Mother();
		copyShallowState((Mother)this,result);
		result.setId(this.getId());
		return result;
	}
	
	public void markDeleted() {
		setDeletedOn(new Date(System.currentTimeMillis()));
		for ( MotherStepChildren link : new HashSet<MotherStepChildren>(getMotherStepChildren_stepChild()) ) {
			link.getStepChild().z_internalRemoveFromMotherStepChildren_stepMother(link);
		}
		for ( FamilyMemberHasRelation link : new HashSet<FamilyMemberHasRelation>(getFamilyMemberHasRelation_relation()) ) {
			link.getRelation().z_internalRemoveFromFamilyMemberHasRelation_familyMember(this.getFamily(),this.getName(),link);
		}
		for ( SurnameProviderHasSon link : new HashSet<SurnameProviderHasSon>(getSurnameProviderHasSon_surnameCarryingSon()) ) {
			link.getSurnameCarryingSon().z_internalRemoveFromSurnameProviderHasSon_surnameProvider(link);
		}
		for ( SurnameProviderHasDaughter link : new HashSet<SurnameProviderHasDaughter>(getSurnameProviderHasDaughter_surnameCarryingDaughter()) ) {
			link.getSurnameCarryingDaughter().z_internalRemoveFromSurnameProviderHasDaughter_surnameProvider(link);
		}
		if ( getSpouse()!=null ) {
			getSpouse().z_internalRemoveFromMarriage_surnameProvider(getMarriage_spouse());
		}
		if ( getSurnameProvider()!=null ) {
			getSurnameProvider().z_internalRemoveFromMarriage_spouse(getMarriage_surnameProvider());
		}
		if ( getFamily()!=null ) {
			getFamily().z_internalRemoveFromMother(this);
		}
		removeAllFromChild(getChild());
		if ( getHusband()!=null ) {
			getHusband().z_internalRemoveFromWife(this);
		}
		for ( SurnameProviderHasSon child : new ArrayList<SurnameProviderHasSon>(getSurnameProviderHasSon_surnameCarryingSon()) ) {
			child.markDeleted();
		}
		for ( SurnameProviderHasDaughter child : new ArrayList<SurnameProviderHasDaughter>(getSurnameProviderHasDaughter_surnameCarryingDaughter()) ) {
			child.markDeleted();
		}
		if ( getMarriage_surnameProvider()!=null ) {
			getMarriage_surnameProvider().markDeleted();
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
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("husband") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("4806022725669416140")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						Father husband = (Father)map.get(((Element)currentPropertyValueNode).getAttribute("uid"));
						if ( husband!=null ) {
							z_internalAddToHusband(husband);
							husband.z_internalAddToWife(this);
						}
					}
				}
			}
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("motherStepChildren_stepChild") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("3786800531408988259")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						MotherStepChildren motherStepChildren_stepChild = (MotherStepChildren)map.get(((Element)currentPropertyValueNode).getAttribute("uid"));
						if ( motherStepChildren_stepChild!=null ) {
							z_internalAddToMotherStepChildren_stepChild(motherStepChildren_stepChild);
							motherStepChildren_stepChild.z_internalAddToStepMother(this);
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
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("surnameProviderHasSon_surnameCarryingSon") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("9051680456867763967")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						((SurnameProviderHasSon)map.get(((Element)currentPropertyValueNode).getAttribute("uid"))).populateReferencesFromXml((Element)currentPropertyValueNode, map);
					}
				}
			}
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("surnameProviderHasDaughter_surnameCarryingDaughter") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("3102093816030840167")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						((SurnameProviderHasDaughter)map.get(((Element)currentPropertyValueNode).getAttribute("uid"))).populateReferencesFromXml((Element)currentPropertyValueNode, map);
					}
				}
			}
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("marriage_spouse") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("599591342263275344")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						Marriage marriage_spouse = (Marriage)map.get(((Element)currentPropertyValueNode).getAttribute("uid"));
						if ( marriage_spouse!=null ) {
							z_internalAddToMarriage_spouse(marriage_spouse);
							marriage_spouse.z_internalAddToSurnameProvider(this);
						}
					}
				}
			}
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("marriage_surnameProvider") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("6783615175887897848")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						((Marriage)map.get(((Element)currentPropertyValueNode).getAttribute("uid"))).populateReferencesFromXml((Element)currentPropertyValueNode, map);
					}
				}
			}
		}
	}
	
	public void removeAllFromChild(Set<? extends Child> child) {
		Set<Child> tmp = new HashSet<Child>(child);
		for ( Child o : tmp ) {
			removeFromChild(o.getName(),o);
		}
	}
	
	public void removeAllFromRelation(Set<? extends Relation> relation) {
		Set<Relation> tmp = new HashSet<Relation>(relation);
		for ( Relation o : tmp ) {
			removeFromRelation(o.getFirstName(),o.getSurname(),o.getDateOfBirth(),o);
		}
	}
	
	public void removeAllFromStepChild(Set<? extends StepChild> stepChild) {
		Set<StepChild> tmp = new HashSet<StepChild>(stepChild);
		for ( StepChild o : tmp ) {
			removeFromStepChild(o);
		}
	}
	
	public void removeAllFromSurnameCarryingDaughter(Set<? extends Sister> surnameCarryingDaughter) {
		Set<Sister> tmp = new HashSet<Sister>(surnameCarryingDaughter);
		for ( Sister o : tmp ) {
			removeFromSurnameCarryingDaughter(o.getName(),o);
		}
	}
	
	public void removeAllFromSurnameCarryingSon(Set<? extends Brother> surnameCarryingSon) {
		Set<Brother> tmp = new HashSet<Brother>(surnameCarryingSon);
		for ( Brother o : tmp ) {
			removeFromSurnameCarryingSon(o);
		}
	}
	
	public void removeFromChild(String name, Child child) {
		if ( child!=null ) {
			child.z_internalRemoveFromMother(this);
			z_internalRemoveFromChild(child.getName(),child);
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
	
	public void removeFromStepChild(StepChild stepChild) {
		if ( stepChild!=null ) {
			MotherStepChildren oldLink = getMotherStepChildren_stepChildFor(stepChild);
			if ( oldLink!=null ) {
				stepChild.z_internalRemoveFromMotherStepChildren_stepMother(oldLink);
				oldLink.clear();
				z_internalRemoveFromMotherStepChildren_stepChild(oldLink);
			}
		}
	}
	
	public void removeFromSurnameCarryingDaughter(String name, Sister surnameCarryingDaughter) {
		if ( surnameCarryingDaughter!=null ) {
			SurnameProviderHasDaughter oldLink = getSurnameProviderHasDaughter_surnameCarryingDaughterFor(surnameCarryingDaughter);
			if ( oldLink!=null ) {
				surnameCarryingDaughter.z_internalRemoveFromSurnameProviderHasDaughter_surnameProvider(oldLink);
				oldLink.clear();
				z_internalRemoveFromSurnameProviderHasDaughter_surnameCarryingDaughter(surnameCarryingDaughter.getName(),oldLink);
			}
		}
	}
	
	public void removeFromSurnameCarryingSon(Brother surnameCarryingSon) {
		if ( surnameCarryingSon!=null ) {
			SurnameProviderHasSon oldLink = getSurnameProviderHasSon_surnameCarryingSonFor(surnameCarryingSon);
			if ( oldLink!=null ) {
				surnameCarryingSon.z_internalRemoveFromSurnameProviderHasSon_surnameProvider(oldLink);
				oldLink.clear();
				z_internalRemoveFromSurnameProviderHasSon_surnameCarryingSon(oldLink);
			}
		}
	}
	
	public void setCancelledEvents(Set<CancelledEvent> cancelledEvents) {
		this.cancelledEvents=cancelledEvents;
	}
	
	public void setChild(Set<Child> child) {
		this.clearChild();
		this.addAllToChild(child);
	}
	
	public void setDeletedOn(Date deletedOn) {
		this.deletedOn=deletedOn;
	}
	
	public void setFamily(Family family) {
		Family oldValue = this.getFamily();
		for ( FamilyMemberHasRelation curVal : getFamilyMemberHasRelation_relation() ) {
			curVal.getRelation().z_internalRemoveFromFamilyMemberHasRelation_familyMember(this.getFamily(),this.getName(),curVal);
		}
		if ( oldValue==null ) {
			if ( family!=null ) {
				Mother oldOther = (Mother)family.getMother();
				family.z_internalRemoveFromMother(oldOther);
				if ( oldOther != null ) {
					oldOther.z_internalRemoveFromFamily(family);
				}
				family.z_internalAddToMother((Mother)this);
			}
			this.z_internalAddToFamily(family);
		} else {
			if ( !oldValue.equals(family) ) {
				oldValue.z_internalRemoveFromMother(this);
				z_internalRemoveFromFamily(oldValue);
				if ( family!=null ) {
					Mother oldOther = (Mother)family.getMother();
					family.z_internalRemoveFromMother(oldOther);
					if ( oldOther != null ) {
						oldOther.z_internalRemoveFromFamily(family);
					}
					family.z_internalAddToMother((Mother)this);
				}
				this.z_internalAddToFamily(family);
			}
		}
		for ( FamilyMemberHasRelation curVal : getFamilyMemberHasRelation_relation() ) {
			curVal.getRelation().z_internalAddToFamilyMemberHasRelation_familyMember(this.getFamily(),this.getName(),curVal);
		}
	}
	
	public void setFirstname(String firstname) {
		if ( firstname == null ) {
			this.z_internalRemoveFromFirstname(getFirstname());
		} else {
			this.z_internalAddToFirstname(firstname);
		}
	}
	
	public void setHusband(Father husband) {
		Father oldValue = this.getHusband();
		if ( oldValue==null ) {
			if ( husband!=null ) {
				Mother oldOther = (Mother)husband.getWife();
				husband.z_internalRemoveFromWife(oldOther);
				if ( oldOther != null ) {
					oldOther.z_internalRemoveFromHusband(husband);
				}
				husband.z_internalAddToWife((Mother)this);
			}
			this.z_internalAddToHusband(husband);
		} else {
			if ( !oldValue.equals(husband) ) {
				oldValue.z_internalRemoveFromWife(this);
				z_internalRemoveFromHusband(oldValue);
				if ( husband!=null ) {
					Mother oldOther = (Mother)husband.getWife();
					husband.z_internalRemoveFromWife(oldOther);
					if ( oldOther != null ) {
						oldOther.z_internalRemoveFromHusband(husband);
					}
					husband.z_internalAddToWife((Mother)this);
				}
				this.z_internalAddToHusband(husband);
			}
		}
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
	
	public void setRelation(Set<Relation> relation) {
		this.clearRelation();
		this.addAllToRelation(relation);
	}
	
	public void setSpouse(Spouse spouse) {
		Spouse oldValue = this.getSpouse();
		if ( oldValue !=null && !oldValue.equals(spouse) ) {
			getSpouse().z_internalRemoveFromMarriage_surnameProvider(getMarriage_spouse());
			getMarriage_spouse().clear();
			z_internalRemoveFromMarriage_spouse(getMarriage_spouse());
		}
		if ( spouse !=null && !spouse.equals(oldValue) ) {
			z_internalAddToMarriage_spouse(new Marriage((SurnameProvider)this,(Spouse)spouse));
			if ( spouse.getSurnameProvider()!=null ) {
				spouse.getMarriage_surnameProvider().clear();
				spouse.getSurnameProvider().z_internalRemoveFromMarriage_spouse(spouse.getMarriage_surnameProvider());
				spouse.z_internalRemoveFromMarriage_surnameProvider(spouse.getMarriage_surnameProvider());
			}
			spouse.z_internalAddToMarriage_surnameProvider(getMarriage_spouse());
		}
	}
	
	public void setStepChild(Set<StepChild> stepChild) {
		this.clearStepChild();
		this.addAllToStepChild(stepChild);
	}
	
	public void setSurname(String surname) {
		if ( surname == null ) {
			this.z_internalRemoveFromSurname(getSurname());
		} else {
			this.z_internalAddToSurname(surname);
		}
	}
	
	public void setSurnameCarryingDaughter(Set<Sister> surnameCarryingDaughter) {
		this.clearSurnameCarryingDaughter();
		this.addAllToSurnameCarryingDaughter(surnameCarryingDaughter);
	}
	
	public void setSurnameCarryingSon(Set<Brother> surnameCarryingSon) {
		this.clearSurnameCarryingSon();
		this.addAllToSurnameCarryingSon(surnameCarryingSon);
	}
	
	public void setSurnameProvider(SurnameProvider surnameProvider) {
		SurnameProvider oldValue = this.getSurnameProvider();
		if ( oldValue !=null && !oldValue.equals(surnameProvider) ) {
			getSurnameProvider().z_internalRemoveFromMarriage_spouse(getMarriage_surnameProvider());
			getMarriage_surnameProvider().clear();
			z_internalRemoveFromMarriage_surnameProvider(getMarriage_surnameProvider());
		}
		if ( surnameProvider !=null && !surnameProvider.equals(oldValue) ) {
			z_internalAddToMarriage_surnameProvider(new Marriage((Spouse)this,(SurnameProvider)surnameProvider));
			if ( surnameProvider.getSpouse()!=null ) {
				surnameProvider.getMarriage_spouse().clear();
				surnameProvider.getSpouse().z_internalRemoveFromMarriage_surnameProvider(surnameProvider.getMarriage_spouse());
				surnameProvider.z_internalRemoveFromMarriage_spouse(surnameProvider.getMarriage_spouse());
			}
			surnameProvider.z_internalAddToMarriage_spouse(getMarriage_surnameProvider());
		}
	}
	
	public void setUid(String newUid) {
		this.uid=newUid;
	}
	
	public String toXmlReferenceString() {
		return "<Mother uid=\""+getUid() + "\"/>";
	}
	
	public String toXmlString() {
		StringBuilder sb = new StringBuilder();
		sb.append("<Mother ");
		sb.append("classUuid=\"Structures.uml@_YergsIhqEeK4s7QGypAJBA\" ");
		sb.append("className=\"org.opaeum.test.Mother\" ");
		sb.append("uid=\"" + this.getUid() + "\" ");
		if ( getFirstname()!=null ) {
			sb.append("firstname=\""+ ModelFormatter.getInstance().formatString(getFirstname())+"\" ");
		}
		if ( getSurname()!=null ) {
			sb.append("surname=\""+ ModelFormatter.getInstance().formatString(getSurname())+"\" ");
		}
		sb.append(">");
		if ( getHusband()==null ) {
			sb.append("\n<husband/>");
		} else {
			sb.append("\n<husband propertyId=\"4806022725669416140\">");
			sb.append("\n" + getHusband().toXmlReferenceString());
			sb.append("\n</husband>");
		}
		sb.append("\n<motherStepChildren_stepChild propertyId=\"3786800531408988259\">");
		for ( MotherStepChildren motherStepChildren_stepChild : getMotherStepChildren_stepChild() ) {
			sb.append("\n" + motherStepChildren_stepChild.toXmlReferenceString());
		}
		sb.append("\n</motherStepChildren_stepChild>");
		sb.append("\n<familyMemberHasRelation_relation propertyId=\"5718737559910777343\">");
		for ( FamilyMemberHasRelation familyMemberHasRelation_relation : getFamilyMemberHasRelation_relation() ) {
			sb.append("\n" + familyMemberHasRelation_relation.toXmlReferenceString());
		}
		sb.append("\n</familyMemberHasRelation_relation>");
		sb.append("\n<surnameProviderHasSon_surnameCarryingSon propertyId=\"9051680456867763967\">");
		for ( SurnameProviderHasSon surnameProviderHasSon_surnameCarryingSon : getSurnameProviderHasSon_surnameCarryingSon() ) {
			sb.append("\n" + surnameProviderHasSon_surnameCarryingSon.toXmlString());
		}
		sb.append("\n</surnameProviderHasSon_surnameCarryingSon>");
		sb.append("\n<surnameProviderHasDaughter_surnameCarryingDaughter propertyId=\"3102093816030840167\">");
		for ( SurnameProviderHasDaughter surnameProviderHasDaughter_surnameCarryingDaughter : getSurnameProviderHasDaughter_surnameCarryingDaughter() ) {
			sb.append("\n" + surnameProviderHasDaughter_surnameCarryingDaughter.toXmlString());
		}
		sb.append("\n</surnameProviderHasDaughter_surnameCarryingDaughter>");
		if ( getMarriage_spouse()==null ) {
			sb.append("\n<marriage_spouse/>");
		} else {
			sb.append("\n<marriage_spouse propertyId=\"599591342263275344\">");
			sb.append("\n" + getMarriage_spouse().toXmlReferenceString());
			sb.append("\n</marriage_spouse>");
		}
		if ( getMarriage_surnameProvider()==null ) {
			sb.append("\n<marriage_surnameProvider/>");
		} else {
			sb.append("\n<marriage_surnameProvider propertyId=\"6783615175887897848\">");
			sb.append("\n" + getMarriage_surnameProvider().toXmlString());
			sb.append("\n</marriage_surnameProvider>");
		}
		sb.append("\n</Mother>");
		return sb.toString();
	}
	
	public void z_internalAddToChild(String name, Child child) {
		String key = ModelFormatter.getInstance().formatStringQualifier(name);
		if ( this.child.containsValue(child) ) {
			return;
		}
		child.z_internalAddToName(name);
		this.child.put(key.toString(),child);
		child.setZ_keyOfChildOnMother(key.toString());
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
	
	public void z_internalAddToFirstname(String firstname) {
		if ( firstname.equals(this.firstname) ) {
			return;
		}
		this.firstname=firstname;
	}
	
	public void z_internalAddToHusband(Father husband) {
		if ( husband.equals(this.husband) ) {
			return;
		}
		this.husband=husband;
	}
	
	public void z_internalAddToMarriage_spouse(Marriage marriage_spouse) {
		if ( marriage_spouse.equals(getMarriage_spouse()) ) {
			return;
		}
		this.marriage_spouse=marriage_spouse;
	}
	
	public void z_internalAddToMarriage_surnameProvider(Marriage marriage_surnameProvider) {
		if ( marriage_surnameProvider.equals(getMarriage_surnameProvider()) ) {
			return;
		}
		this.marriage_surnameProvider=marriage_surnameProvider;
	}
	
	public void z_internalAddToMotherStepChildren_stepChild(MotherStepChildren motherStepChildren_stepChild) {
		if ( getMotherStepChildren_stepChild().contains(motherStepChildren_stepChild) ) {
			return;
		}
		this.motherStepChildren_stepChild.add(motherStepChildren_stepChild);
	}
	
	public void z_internalAddToSurname(String surname) {
		if ( surname.equals(this.surname) ) {
			return;
		}
		this.surname=surname;
	}
	
	public void z_internalAddToSurnameProviderHasDaughter_surnameCarryingDaughter(String name, SurnameProviderHasDaughter surnameProviderHasDaughter_surnameCarryingDaughter) {
		String key = ModelFormatter.getInstance().formatStringQualifier(name);
		if ( getSurnameProviderHasDaughter_surnameCarryingDaughter().contains(surnameProviderHasDaughter_surnameCarryingDaughter) ) {
			return;
		}
		surnameProviderHasDaughter_surnameCarryingDaughter.getSurnameCarryingDaughter().z_internalAddToName(name);
		this.surnameProviderHasDaughter_surnameCarryingDaughter.put(key.toString(),surnameProviderHasDaughter_surnameCarryingDaughter);
		surnameProviderHasDaughter_surnameCarryingDaughter.setZ_keyOfSurnameCarryingDaughterOnSurnameProvider(key.toString());
	}
	
	public void z_internalAddToSurnameProviderHasSon_surnameCarryingSon(SurnameProviderHasSon surnameProviderHasSon_surnameCarryingSon) {
		if ( getSurnameProviderHasSon_surnameCarryingSon().contains(surnameProviderHasSon_surnameCarryingSon) ) {
			return;
		}
		this.surnameProviderHasSon_surnameCarryingSon.add(surnameProviderHasSon_surnameCarryingSon);
	}
	
	public void z_internalRemoveFromChild(String name, Child child) {
		String key = ModelFormatter.getInstance().formatStringQualifier(name);
		this.child.remove(key.toString());
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
	
	public void z_internalRemoveFromFirstname(String firstname) {
		if ( getFirstname()!=null && firstname!=null && firstname.equals(getFirstname()) ) {
			this.firstname=null;
			this.firstname=null;
		}
	}
	
	public void z_internalRemoveFromHusband(Father husband) {
		if ( getHusband()!=null && husband!=null && husband.equals(getHusband()) ) {
			this.husband=null;
			this.husband=null;
		}
	}
	
	public void z_internalRemoveFromMarriage_spouse(Marriage marriage_spouse) {
		if ( getMarriage_spouse()!=null && marriage_spouse!=null && marriage_spouse.equals(getMarriage_spouse()) ) {
			this.marriage_spouse=null;
			this.marriage_spouse=null;
		}
	}
	
	public void z_internalRemoveFromMarriage_surnameProvider(Marriage marriage_surnameProvider) {
		if ( getMarriage_surnameProvider()!=null && marriage_surnameProvider!=null && marriage_surnameProvider.equals(getMarriage_surnameProvider()) ) {
			this.marriage_surnameProvider=null;
			this.marriage_surnameProvider=null;
		}
	}
	
	public void z_internalRemoveFromMotherStepChildren_stepChild(MotherStepChildren motherStepChildren_stepChild) {
		this.motherStepChildren_stepChild.remove(motherStepChildren_stepChild);
	}
	
	public void z_internalRemoveFromSurname(String surname) {
		if ( getSurname()!=null && surname!=null && surname.equals(getSurname()) ) {
			this.surname=null;
			this.surname=null;
		}
	}
	
	public void z_internalRemoveFromSurnameProviderHasDaughter_surnameCarryingDaughter(String name, SurnameProviderHasDaughter surnameProviderHasDaughter_surnameCarryingDaughter) {
		String key = ModelFormatter.getInstance().formatStringQualifier(name);
		this.surnameProviderHasDaughter_surnameCarryingDaughter.remove(key.toString());
	}
	
	public void z_internalRemoveFromSurnameProviderHasSon_surnameCarryingSon(SurnameProviderHasSon surnameProviderHasSon_surnameCarryingSon) {
		this.surnameProviderHasSon_surnameCarryingSon.remove(surnameProviderHasSon_surnameCarryingSon);
	}

}