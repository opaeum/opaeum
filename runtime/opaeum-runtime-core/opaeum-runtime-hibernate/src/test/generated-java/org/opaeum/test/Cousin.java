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
import javax.persistence.MapKey;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.hibernate.annotations.AccessType;
import org.hibernate.annotations.Filter;
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
import org.opaeum.runtime.strategy.DateStrategyFactory;
import org.opaeum.test.util.ModelFormatter;
import org.opaeum.test.util.Stdlib;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

@NumlMetaInfo(applicationIdentifier="opaeum_hibernate_tests",uuid="Structures.uml@_htfZoIhqEeK4s7QGypAJBA")
@Filter(name="noDeletedObjects")
@org.hibernate.annotations.Entity(dynamicUpdate=true)
@AccessType(	"field")
@Table(name="cousin")
@Entity(name="Cousin")
public class Cousin implements Relation, IPersistentObject, IEventGenerator, HibernateEntity, CompositionNode, Serializable {
	@Transient
	transient private Set<CancelledEvent> cancelledEvents = new HashSet<CancelledEvent>();
	@Where(clause="god_parent_type='Structures.uml@_htfZoIhqEeK4s7QGypAJBA'")
	@LazyCollection(	org.hibernate.annotations.LazyCollectionOption.TRUE)
	@Filter(condition="deleted_on > current_timestamp",name="noDeletedObjects")
	@OneToMany(cascade=javax.persistence.CascadeType.ALL,fetch=javax.persistence.FetchType.LAZY,targetEntity=ChildHasRelation.class)
	@MapKey(name="z_keyOfChildOnRelation")
	@JoinColumn(name="god_parent",nullable=true)
	protected Map<String, ChildHasRelation> childHasRelation_child = new HashMap<String,ChildHasRelation>();
	@Temporal(	javax.persistence.TemporalType.DATE)
	@Column(name="date_of_birth")
	protected Date dateOfBirth;
		// Initialise to 1000 from 1970
	@Temporal(	javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="deleted_on")
	private Date deletedOn = Stdlib.FUTURE;
	@Where(clause="relation_type='Structures.uml@_htfZoIhqEeK4s7QGypAJBA'")
	@LazyCollection(	org.hibernate.annotations.LazyCollectionOption.TRUE)
	@Filter(condition="deleted_on > current_timestamp",name="noDeletedObjects")
	@OneToMany(cascade=javax.persistence.CascadeType.ALL,fetch=javax.persistence.FetchType.LAZY,targetEntity=FamilyMemberHasRelation.class)
	@MapKey(name="z_keyOfFamilyMemberOnRelation")
	@JoinColumn(name="relation",nullable=true)
	protected Map<String, FamilyMemberHasRelation> familyMemberHasRelation_familyMember = new HashMap<String,FamilyMemberHasRelation>();
	@Column(name="first_name")
	@Basic
	protected String firstName;
	@TableGenerator(allocationSize=20,name="id_generator",pkColumnName="type",pkColumnValue="cousin",table="hi_value")
	@Id
	@GeneratedValue(generator="id_generator",strategy=javax.persistence.GenerationType.TABLE)
	private Long id;
	static private Set<? extends Cousin> mockedAllInstances;
	@Column(name="name")
	@Basic
	protected String name;
	@Version
	@Column(name="object_version")
	private int objectVersion;
	@Transient
	transient private Set<OutgoingEvent> outgoingEvents = new HashSet<OutgoingEvent>();
	@Transient
	private InternalHibernatePersistence persistence;
	static final private long serialVersionUID = 2663085624618796279l;
	@Column(name="surname")
	@Basic
	protected String surname;
	private String uid;

	/** Default constructor for Cousin
	 */
	public Cousin() {
	}

	public void addAllToChild(Set<Child> child) {
		for ( Child o : child ) {
			addToChild(o.getName(),o.getDateOfBirth(),o);
		}
	}
	
	public void addAllToFamilyMember(Set<FamilyMember> familyMember) {
		for ( FamilyMember o : familyMember ) {
			addToFamilyMember(o.getFamily(),o.getName(),o);
		}
	}
	
	public void addToChild(String name, Date dateOfBirth, Child child) {
		if ( child!=null && !this.getChild().contains(child) ) {
			ChildHasRelation newLink = new ChildHasRelation((Relation)this,(Child)child);
			if ( getFirstName()==null ) {
				throw new IllegalStateException("The qualifying property 'firstName' must be set before adding a value to 'child'");
			}
			if ( getSurname()==null ) {
				throw new IllegalStateException("The qualifying property 'surname' must be set before adding a value to 'child'");
			}
			if ( getDateOfBirth()==null ) {
				throw new IllegalStateException("The qualifying property 'dateOfBirth' must be set before adding a value to 'child'");
			}
			this.z_internalAddToChildHasRelation_child(name,dateOfBirth,newLink);
			child.z_internalAddToChildHasRelation_godParent(this.getFirstName(),this.getSurname(),this.getDateOfBirth(),newLink);
		}
	}
	
	public void addToFamilyMember(Family family, String name, FamilyMember familyMember) {
		if ( familyMember!=null && !this.getFamilyMember().contains(familyMember) ) {
			FamilyMemberHasRelation newLink = new FamilyMemberHasRelation((Relation)this,(FamilyMember)familyMember);
			if ( getFirstName()==null ) {
				throw new IllegalStateException("The qualifying property 'firstName' must be set before adding a value to 'familyMember'");
			}
			if ( getSurname()==null ) {
				throw new IllegalStateException("The qualifying property 'surname' must be set before adding a value to 'familyMember'");
			}
			if ( getDateOfBirth()==null ) {
				throw new IllegalStateException("The qualifying property 'dateOfBirth' must be set before adding a value to 'familyMember'");
			}
			this.z_internalAddToFamilyMemberHasRelation_familyMember(family,name,newLink);
			familyMember.z_internalAddToFamilyMemberHasRelation_relation(this.getFirstName(),this.getSurname(),this.getDateOfBirth(),newLink);
		}
	}
	
	/** Call this method when you want to attach this object to the containment tree. Useful with transitive persistence
	 */
	public void addToOwningObject() {
	}
	
	static public Set<? extends Cousin> allInstances(AbstractPersistence persistence) {
		if ( mockedAllInstances==null ) {
			return new HashSet(persistence.readAll(org.opaeum.test.Cousin.class));
		} else {
			return mockedAllInstances;
		}
	}
	
	public void buildTreeFromXml(Element xml, Map<String, Object> map) {
		setUid(xml.getAttribute("uid"));
		if ( xml.getAttribute("firstName").length()>0 ) {
			setFirstName(ModelFormatter.getInstance().parseString(xml.getAttribute("firstName")));
		}
		if ( xml.getAttribute("surname").length()>0 ) {
			setSurname(ModelFormatter.getInstance().parseString(xml.getAttribute("surname")));
		}
		if ( xml.getAttribute("dateOfBirth").length()>0 ) {
			setDateOfBirth(ModelFormatter.getInstance().parseDate(xml.getAttribute("dateOfBirth")));
		}
		if ( xml.getAttribute("name").length()>0 ) {
			setName(ModelFormatter.getInstance().parseString(xml.getAttribute("name")));
		}
		NodeList propertyNodes = xml.getChildNodes();
		int i = 0;
		while ( i<propertyNodes.getLength() ) {
			Node currentPropertyNode = propertyNodes.item(i++);
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("childHasRelation_child") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("1488854094798314249")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						ChildHasRelation curVal;
						try {
							curVal=IntrospectionUtil.newInstance(((Element)currentPropertyValueNode).getAttribute("className"));
						} catch (Exception e) {
							curVal=org.opaeum.test.util.ModelJavaMetaInfoMap.INSTANCE.newInstance(((Element)currentPropertyValueNode).getAttribute("classUuid"));
						}
						curVal.buildTreeFromXml((Element)currentPropertyValueNode,map);
						this.z_internalAddToChildHasRelation_child(curVal.getChild().getName(),curVal.getChild().getDateOfBirth(),curVal);
						curVal.z_internalAddToGodParent(this);
						map.put(curVal.getUid(), curVal);
					}
				}
			}
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("familyMemberHasRelation_familyMember") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("3903606770219243395")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						FamilyMemberHasRelation curVal;
						try {
							curVal=IntrospectionUtil.newInstance(((Element)currentPropertyValueNode).getAttribute("className"));
						} catch (Exception e) {
							curVal=org.opaeum.test.util.ModelJavaMetaInfoMap.INSTANCE.newInstance(((Element)currentPropertyValueNode).getAttribute("classUuid"));
						}
						curVal.buildTreeFromXml((Element)currentPropertyValueNode,map);
						this.z_internalAddToFamilyMemberHasRelation_familyMember(curVal.getFamilyMember().getFamily(),curVal.getFamilyMember().getName(),curVal);
						curVal.z_internalAddToRelation(this);
						map.put(curVal.getUid(), curVal);
					}
				}
			}
		}
	}
	
	public void clearChild() {
		Set<Child> tmp = new HashSet<Child>(getChild());
		for ( Child o : tmp ) {
			removeFromChild(o.getName(),o.getDateOfBirth(),o);
		}
	}
	
	public void clearFamilyMember() {
		Set<FamilyMember> tmp = new HashSet<FamilyMember>(getFamilyMember());
		for ( FamilyMember o : tmp ) {
			removeFromFamilyMember(o.getFamily(),o.getName(),o);
		}
	}
	
	public void copyShallowState(Cousin from, Cousin to) {
		to.setFirstName(from.getFirstName());
		to.setSurname(from.getSurname());
		to.setDateOfBirth(from.getDateOfBirth());
		to.setName(from.getName());
	}
	
	public void copyState(Cousin from, Cousin to) {
		to.setFirstName(from.getFirstName());
		to.setSurname(from.getSurname());
		to.setDateOfBirth(from.getDateOfBirth());
		to.setName(from.getName());
	}
	
	public void createComponents() {
	}
	
	public boolean equals(Object other) {
		if ( other instanceof Cousin ) {
			return other==this || ((Cousin)other).getUid().equals(this.getUid());
		}
		return false;
	}
	
	public Set<CancelledEvent> getCancelledEvents() {
		return this.cancelledEvents;
	}
	
	public Child getChild(String name, Date dateOfBirth) {
		Child result = null;
		String key = ModelFormatter.getInstance().formatStringQualifier(name)+ModelFormatter.getInstance().formatDateQualifier(dateOfBirth);
		ChildHasRelation link = this.childHasRelation_child.get(key.toString());
		result= link==null || link.getChild()==null?null:link.getChild();
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=984124978325014811l,opposite="godParent",uuid="Structures.uml@_I7GooYhrEeK4s7QGypAJBA")
	public Set<Child> getChild() {
		Set result = new HashSet<Child>();
		for ( ChildHasRelation cur : this.getChildHasRelation_child() ) {
			result.add(cur.getChild());
		}
		return result;
	}
	
	public ChildHasRelation getChildHasRelation_child(String name, Date dateOfBirth) {
		ChildHasRelation result = null;
		String key = ModelFormatter.getInstance().formatStringQualifier(name)+ModelFormatter.getInstance().formatDateQualifier(dateOfBirth);
		result=this.childHasRelation_child.get(key.toString());
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=true,opaeumId=1488854094798314249l,opposite="godParent",uuid="Structures.uml@_I7GooIhrEeK4s7QGypAJBA")
	@NumlMetaInfo(uuid="Structures.uml@_bVPeIIhqEeK4s7QGypAJBA@Structures.uml@_I7GooIhrEeK4s7QGypAJBA")
	public Set<ChildHasRelation> getChildHasRelation_child() {
		Set result = new HashSet<ChildHasRelation>(this.childHasRelation_child.values());
		
		return result;
	}
	
	public ChildHasRelation getChildHasRelation_childFor(Child match) {
		for ( ChildHasRelation var : getChildHasRelation_child() ) {
			if ( var.getChild().equals(match) ) {
				return var;
			}
		}
		return null;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=4576801132852053498l,strategyFactory=DateStrategyFactory.class,uuid="Structures.uml@_wFrE4IjSEeKq68owPnlvHg")
	@NumlMetaInfo(uuid="Structures.uml@_wFrE4IjSEeKq68owPnlvHg")
	public Date getDateOfBirth() {
		Date result = this.dateOfBirth;
		
		return result;
	}
	
	public Date getDeletedOn() {
		return this.deletedOn;
	}
	
	public FamilyMember getFamilyMember(Family family, String name) {
		FamilyMember result = null;
		String key = family.getUid()+ModelFormatter.getInstance().formatStringQualifier(name);
		FamilyMemberHasRelation link = this.familyMemberHasRelation_familyMember.get(key.toString());
		result= link==null || link.getFamilyMember()==null?null:link.getFamilyMember();
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=1729227443467658447l,opposite="relation",uuid="Structures.uml@_wPOkxIhqEeK4s7QGypAJBA")
	public Set<FamilyMember> getFamilyMember() {
		Set result = new HashSet<FamilyMember>();
		for ( FamilyMemberHasRelation cur : this.getFamilyMemberHasRelation_familyMember() ) {
			result.add(cur.getFamilyMember());
		}
		return result;
	}
	
	public FamilyMemberHasRelation getFamilyMemberHasRelation_familyMember(Family family, String name) {
		FamilyMemberHasRelation result = null;
		String key = family.getUid()+ModelFormatter.getInstance().formatStringQualifier(name);
		result=this.familyMemberHasRelation_familyMember.get(key.toString());
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=true,opaeumId=3903606770219243395l,opposite="relation",uuid="Structures.uml@_wPOkwIhqEeK4s7QGypAJBA")
	@NumlMetaInfo(uuid="Structures.uml@_bVPeIIhqEeK4s7QGypAJBA@Structures.uml@_wPOkwIhqEeK4s7QGypAJBA")
	public Set<FamilyMemberHasRelation> getFamilyMemberHasRelation_familyMember() {
		Set result = new HashSet<FamilyMemberHasRelation>(this.familyMemberHasRelation_familyMember.values());
		
		return result;
	}
	
	public FamilyMemberHasRelation getFamilyMemberHasRelation_familyMemberFor(FamilyMember match) {
		for ( FamilyMemberHasRelation var : getFamilyMemberHasRelation_familyMember() ) {
			if ( var.getFamilyMember().equals(match) ) {
				return var;
			}
		}
		return null;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=4472055897408457748l,uuid="Structures.uml@_ojWi8IjSEeKq68owPnlvHg")
	@NumlMetaInfo(uuid="Structures.uml@_ojWi8IjSEeKq68owPnlvHg")
	public String getFirstName() {
		String result = this.firstName;
		
		return result;
	}
	
	public Long getId() {
		return this.id;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=5295029831018107038l,uuid="Structures.uml@_jHAA4IiDEeKe_ZlMOEGy8Q")
	@NumlMetaInfo(uuid="Structures.uml@_jHAA4IiDEeKe_ZlMOEGy8Q")
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
		return null;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=7182092940400916328l,uuid="Structures.uml@_rPn78IjSEeKq68owPnlvHg")
	@NumlMetaInfo(uuid="Structures.uml@_rPn78IjSEeKq68owPnlvHg")
	public String getSurname() {
		String result = this.surname;
		
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
	}
	
	public Cousin makeCopy() {
		Cousin result = new Cousin();
		copyState((Cousin)this,result);
		return result;
	}
	
	public Cousin makeShallowCopy() {
		Cousin result = new Cousin();
		copyShallowState((Cousin)this,result);
		result.setId(this.getId());
		return result;
	}
	
	public void markDeleted() {
		setDeletedOn(new Date(System.currentTimeMillis()));
		for ( ChildHasRelation link : new HashSet<ChildHasRelation>(getChildHasRelation_child()) ) {
			link.getChild().z_internalRemoveFromChildHasRelation_godParent(this.getFirstName(),this.getSurname(),this.getDateOfBirth(),link);
		}
		for ( FamilyMemberHasRelation link : new HashSet<FamilyMemberHasRelation>(getFamilyMemberHasRelation_familyMember()) ) {
			link.getFamilyMember().z_internalRemoveFromFamilyMemberHasRelation_relation(this.getFirstName(),this.getSurname(),this.getDateOfBirth(),link);
		}
		for ( ChildHasRelation child : new ArrayList<ChildHasRelation>(getChildHasRelation_child()) ) {
			child.markDeleted();
		}
		for ( FamilyMemberHasRelation child : new ArrayList<FamilyMemberHasRelation>(getFamilyMemberHasRelation_familyMember()) ) {
			child.markDeleted();
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
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("childHasRelation_child") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("1488854094798314249")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						((ChildHasRelation)map.get(((Element)currentPropertyValueNode).getAttribute("uid"))).populateReferencesFromXml((Element)currentPropertyValueNode, map);
					}
				}
			}
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("familyMemberHasRelation_familyMember") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("3903606770219243395")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						((FamilyMemberHasRelation)map.get(((Element)currentPropertyValueNode).getAttribute("uid"))).populateReferencesFromXml((Element)currentPropertyValueNode, map);
					}
				}
			}
		}
	}
	
	public void removeAllFromChild(Set<Child> child) {
		Set<Child> tmp = new HashSet<Child>(child);
		for ( Child o : tmp ) {
			removeFromChild(o.getName(),o.getDateOfBirth(),o);
		}
	}
	
	public void removeAllFromFamilyMember(Set<FamilyMember> familyMember) {
		Set<FamilyMember> tmp = new HashSet<FamilyMember>(familyMember);
		for ( FamilyMember o : tmp ) {
			removeFromFamilyMember(o.getFamily(),o.getName(),o);
		}
	}
	
	public void removeFromChild(String name, Date dateOfBirth, Child child) {
		if ( child!=null ) {
			ChildHasRelation oldLink = getChildHasRelation_childFor(child);
			if ( oldLink!=null ) {
				child.z_internalRemoveFromChildHasRelation_godParent(this.getFirstName(),this.getSurname(),this.getDateOfBirth(),oldLink);
				oldLink.clear();
				z_internalRemoveFromChildHasRelation_child(child.getName(),child.getDateOfBirth(),oldLink);
			}
		}
	}
	
	public void removeFromFamilyMember(Family family, String name, FamilyMember familyMember) {
		if ( familyMember!=null ) {
			FamilyMemberHasRelation oldLink = getFamilyMemberHasRelation_familyMemberFor(familyMember);
			if ( oldLink!=null ) {
				familyMember.z_internalRemoveFromFamilyMemberHasRelation_relation(this.getFirstName(),this.getSurname(),this.getDateOfBirth(),oldLink);
				oldLink.clear();
				z_internalRemoveFromFamilyMemberHasRelation_familyMember(familyMember.getFamily(),familyMember.getName(),oldLink);
			}
		}
	}
	
	public void removeFromOwningObject() {
		this.markDeleted();
	}
	
	public void setCancelledEvents(Set<CancelledEvent> cancelledEvents) {
		this.cancelledEvents=cancelledEvents;
	}
	
	public void setChild(Set<Child> child) {
		this.clearChild();
		this.addAllToChild(child);
	}
	
	public void setDateOfBirth(Date dateOfBirth) {
		for ( ChildHasRelation curVal : getChildHasRelation_child() ) {
			curVal.getChild().z_internalRemoveFromChildHasRelation_godParent(this.getFirstName(),this.getSurname(),this.getDateOfBirth(),curVal);
		}
		for ( FamilyMemberHasRelation curVal : getFamilyMemberHasRelation_familyMember() ) {
			curVal.getFamilyMember().z_internalRemoveFromFamilyMemberHasRelation_relation(this.getFirstName(),this.getSurname(),this.getDateOfBirth(),curVal);
		}
		if ( dateOfBirth == null ) {
			this.z_internalRemoveFromDateOfBirth(getDateOfBirth());
		} else {
			this.z_internalAddToDateOfBirth(dateOfBirth);
		}
		for ( ChildHasRelation curVal : getChildHasRelation_child() ) {
			curVal.getChild().z_internalAddToChildHasRelation_godParent(this.getFirstName(),this.getSurname(),this.getDateOfBirth(),curVal);
		}
		for ( FamilyMemberHasRelation curVal : getFamilyMemberHasRelation_familyMember() ) {
			curVal.getFamilyMember().z_internalAddToFamilyMemberHasRelation_relation(this.getFirstName(),this.getSurname(),this.getDateOfBirth(),curVal);
		}
	}
	
	public void setDeletedOn(Date deletedOn) {
		this.deletedOn=deletedOn;
	}
	
	public void setFamilyMember(Set<FamilyMember> familyMember) {
		this.clearFamilyMember();
		this.addAllToFamilyMember(familyMember);
	}
	
	public void setFirstName(String firstName) {
		for ( ChildHasRelation curVal : getChildHasRelation_child() ) {
			curVal.getChild().z_internalRemoveFromChildHasRelation_godParent(this.getFirstName(),this.getSurname(),this.getDateOfBirth(),curVal);
		}
		for ( FamilyMemberHasRelation curVal : getFamilyMemberHasRelation_familyMember() ) {
			curVal.getFamilyMember().z_internalRemoveFromFamilyMemberHasRelation_relation(this.getFirstName(),this.getSurname(),this.getDateOfBirth(),curVal);
		}
		if ( firstName == null ) {
			this.z_internalRemoveFromFirstName(getFirstName());
		} else {
			this.z_internalAddToFirstName(firstName);
		}
		for ( ChildHasRelation curVal : getChildHasRelation_child() ) {
			curVal.getChild().z_internalAddToChildHasRelation_godParent(this.getFirstName(),this.getSurname(),this.getDateOfBirth(),curVal);
		}
		for ( FamilyMemberHasRelation curVal : getFamilyMemberHasRelation_familyMember() ) {
			curVal.getFamilyMember().z_internalAddToFamilyMemberHasRelation_relation(this.getFirstName(),this.getSurname(),this.getDateOfBirth(),curVal);
		}
	}
	
	public void setId(Long id) {
		this.id=id;
	}
	
	public void setName(String name) {
		if ( name == null ) {
			this.z_internalRemoveFromName(getName());
		} else {
			this.z_internalAddToName(name);
		}
	}
	
	public void setObjectVersion(int objectVersion) {
		this.objectVersion=objectVersion;
	}
	
	public void setOutgoingEvents(Set<OutgoingEvent> outgoingEvents) {
		this.outgoingEvents=outgoingEvents;
	}
	
	public void setSurname(String surname) {
		for ( ChildHasRelation curVal : getChildHasRelation_child() ) {
			curVal.getChild().z_internalRemoveFromChildHasRelation_godParent(this.getFirstName(),this.getSurname(),this.getDateOfBirth(),curVal);
		}
		for ( FamilyMemberHasRelation curVal : getFamilyMemberHasRelation_familyMember() ) {
			curVal.getFamilyMember().z_internalRemoveFromFamilyMemberHasRelation_relation(this.getFirstName(),this.getSurname(),this.getDateOfBirth(),curVal);
		}
		if ( surname == null ) {
			this.z_internalRemoveFromSurname(getSurname());
		} else {
			this.z_internalAddToSurname(surname);
		}
		for ( ChildHasRelation curVal : getChildHasRelation_child() ) {
			curVal.getChild().z_internalAddToChildHasRelation_godParent(this.getFirstName(),this.getSurname(),this.getDateOfBirth(),curVal);
		}
		for ( FamilyMemberHasRelation curVal : getFamilyMemberHasRelation_familyMember() ) {
			curVal.getFamilyMember().z_internalAddToFamilyMemberHasRelation_relation(this.getFirstName(),this.getSurname(),this.getDateOfBirth(),curVal);
		}
	}
	
	public void setUid(String newUid) {
		this.uid=newUid;
	}
	
	public String toXmlReferenceString() {
		return "<Cousin uid=\""+getUid() + "\"/>";
	}
	
	public String toXmlString() {
		StringBuilder sb = new StringBuilder();
		sb.append("<Cousin ");
		sb.append("classUuid=\"Structures.uml@_htfZoIhqEeK4s7QGypAJBA\" ");
		sb.append("className=\"org.opaeum.test.Cousin\" ");
		sb.append("uid=\"" + this.getUid() + "\" ");
		if ( getFirstName()!=null ) {
			sb.append("firstName=\""+ ModelFormatter.getInstance().formatString(getFirstName())+"\" ");
		}
		if ( getSurname()!=null ) {
			sb.append("surname=\""+ ModelFormatter.getInstance().formatString(getSurname())+"\" ");
		}
		if ( getDateOfBirth()!=null ) {
			sb.append("dateOfBirth=\""+ ModelFormatter.getInstance().formatDate(getDateOfBirth())+"\" ");
		}
		if ( getName()!=null ) {
			sb.append("name=\""+ ModelFormatter.getInstance().formatString(getName())+"\" ");
		}
		sb.append(">");
		sb.append("\n<childHasRelation_child propertyId=\"1488854094798314249\">");
		for ( ChildHasRelation childHasRelation_child : getChildHasRelation_child() ) {
			sb.append("\n" + childHasRelation_child.toXmlString());
		}
		sb.append("\n</childHasRelation_child>");
		sb.append("\n<familyMemberHasRelation_familyMember propertyId=\"3903606770219243395\">");
		for ( FamilyMemberHasRelation familyMemberHasRelation_familyMember : getFamilyMemberHasRelation_familyMember() ) {
			sb.append("\n" + familyMemberHasRelation_familyMember.toXmlString());
		}
		sb.append("\n</familyMemberHasRelation_familyMember>");
		sb.append("\n</Cousin>");
		return sb.toString();
	}
	
	public void z_internalAddToChildHasRelation_child(String name, Date dateOfBirth, ChildHasRelation childHasRelation_child) {
		String key = ModelFormatter.getInstance().formatStringQualifier(name)+ModelFormatter.getInstance().formatDateQualifier(dateOfBirth);
		if ( getChildHasRelation_child().contains(childHasRelation_child) ) {
			return;
		}
		childHasRelation_child.getChild().z_internalAddToName(name);
		childHasRelation_child.getChild().z_internalAddToDateOfBirth(dateOfBirth);
		this.childHasRelation_child.put(key.toString(),childHasRelation_child);
		childHasRelation_child.setZ_keyOfChildOnRelation(key.toString());
	}
	
	public void z_internalAddToDateOfBirth(Date dateOfBirth) {
		if ( dateOfBirth.equals(getDateOfBirth()) ) {
			return;
		}
		this.dateOfBirth=dateOfBirth;
	}
	
	public void z_internalAddToFamilyMemberHasRelation_familyMember(Family family, String name, FamilyMemberHasRelation familyMemberHasRelation_familyMember) {
		String key = family.getUid()+ModelFormatter.getInstance().formatStringQualifier(name);
		if ( getFamilyMemberHasRelation_familyMember().contains(familyMemberHasRelation_familyMember) ) {
			return;
		}
		if ( familyMemberHasRelation_familyMember.getFamilyMember().getFamily()==null ) {
			throw new IllegalStateException("The qualifying property 'family' must be set before adding a value to 'familyMemberHasRelation_familyMember'");
		}
		if ( familyMemberHasRelation_familyMember.getFamilyMember().getName()==null ) {
			throw new IllegalStateException("The qualifying property 'name' must be set before adding a value to 'familyMemberHasRelation_familyMember'");
		}
		this.familyMemberHasRelation_familyMember.put(key.toString(),familyMemberHasRelation_familyMember);
		familyMemberHasRelation_familyMember.setZ_keyOfFamilyMemberOnRelation(key.toString());
	}
	
	public void z_internalAddToFirstName(String firstName) {
		if ( firstName.equals(getFirstName()) ) {
			return;
		}
		this.firstName=firstName;
	}
	
	public void z_internalAddToName(String name) {
		if ( name.equals(getName()) ) {
			return;
		}
		this.name=name;
	}
	
	public void z_internalAddToSurname(String surname) {
		if ( surname.equals(getSurname()) ) {
			return;
		}
		this.surname=surname;
	}
	
	public void z_internalRemoveFromChildHasRelation_child(String name, Date dateOfBirth, ChildHasRelation childHasRelation_child) {
		String key = ModelFormatter.getInstance().formatStringQualifier(name)+ModelFormatter.getInstance().formatDateQualifier(dateOfBirth);
		this.childHasRelation_child.remove(key.toString());
	}
	
	public void z_internalRemoveFromDateOfBirth(Date dateOfBirth) {
		if ( getDateOfBirth()!=null && dateOfBirth!=null && dateOfBirth.equals(getDateOfBirth()) ) {
			this.dateOfBirth=null;
			this.dateOfBirth=null;
		}
	}
	
	public void z_internalRemoveFromFamilyMemberHasRelation_familyMember(Family family, String name, FamilyMemberHasRelation familyMemberHasRelation_familyMember) {
		String key = family.getUid()+ModelFormatter.getInstance().formatStringQualifier(name);
		this.familyMemberHasRelation_familyMember.remove(key.toString());
	}
	
	public void z_internalRemoveFromFirstName(String firstName) {
		if ( getFirstName()!=null && firstName!=null && firstName.equals(getFirstName()) ) {
			this.firstName=null;
			this.firstName=null;
		}
	}
	
	public void z_internalRemoveFromName(String name) {
		if ( getName()!=null && name!=null && name.equals(getName()) ) {
			this.name=null;
			this.name=null;
		}
	}
	
	public void z_internalRemoveFromSurname(String surname) {
		if ( getSurname()!=null && surname!=null && surname.equals(getSurname()) ) {
			this.surname=null;
			this.surname=null;
		}
	}

}