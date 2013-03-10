package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import model.util.ModelFormatter;

import org.opaeum.annotation.NumlMetaInfo;
import org.opaeum.annotation.PropertyMetaInfo;
import org.opaeum.runtime.domain.CancelledEvent;
import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.runtime.domain.IEventGenerator;
import org.opaeum.runtime.domain.IntrospectionUtil;
import org.opaeum.runtime.domain.OutgoingEvent;
import org.opaeum.runtime.environment.Environment;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

@NumlMetaInfo(applicationIdentifier="structuretests",uuid="Structures.uml@_YergsIhqEeK4s7QGypAJBA")
public class Mother implements IEventGenerator, CompositionNode, SurnameProvider, Spouse, FamilyMember, Serializable {
	transient private Set<CancelledEvent> cancelledEvents = new HashSet<CancelledEvent>();
	protected Family family;
	protected Map<String, FamilyMemberHasRelation> familyMemberHasRelation_relation = new HashMap<String,FamilyMemberHasRelation>();
	protected String firstname;
	transient private Set<OutgoingEvent> outgoingEvents = new HashSet<OutgoingEvent>();
	static final private long serialVersionUID = 2128003045175769585l;
	protected Spouse spouse;
	protected String surname;
	protected SurnameProvider surnameProvider;
	protected Set<SurnameProviderHasDaughter> surnameProviderHasDaughter_surnameCarryingDaughter = new HashSet<SurnameProviderHasDaughter>();
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

	public void addAllToRelation(Set<Relation> relation) {
		for ( Relation o : relation ) {
			addToRelation(o.getFirstName(),o.getSurname(),o.getDateOfBirth(),o);
		}
	}
	
	public void addAllToSurnameCarryingDaughter(Set<Sister> surnameCarryingDaughter) {
		for ( Sister o : surnameCarryingDaughter ) {
			addToSurnameCarryingDaughter(o);
		}
	}
	
	public void addAllToSurnameCarryingSon(Set<Brother> surnameCarryingSon) {
		for ( Brother o : surnameCarryingSon ) {
			addToSurnameCarryingSon(o);
		}
	}
	
	/** Call this method when you want to attach this object to the containment tree. Useful with transitive persistence
	 */
	public void addToOwningObject() {
		getFamily().z_internalAddToMother((Mother)this);
	}
	
	public void addToRelation(String firstName, String surname, Date dateOfBirth, Relation relation) {
		if ( relation!=null && !this.getRelation().contains(relation) ) {
			FamilyMemberHasRelation newLink = new FamilyMemberHasRelation((FamilyMember)this,(Relation)relation);
			this.z_internalAddToFamilyMemberHasRelation_relation(relation.getFirstName(),relation.getSurname(),relation.getDateOfBirth(),newLink);
			relation.z_internalAddToFamilyMemberHasRelation_familyMember(newLink);
		}
	}
	
	public void addToSurnameCarryingDaughter(Sister surnameCarryingDaughter) {
		if ( surnameCarryingDaughter!=null && !this.getSurnameCarryingDaughter().contains(surnameCarryingDaughter) ) {
			SurnameProviderHasDaughter newLink = new SurnameProviderHasDaughter((SurnameProvider)this,(Sister)surnameCarryingDaughter);
			SurnameProviderHasDaughter oldLink = surnameCarryingDaughter.getSurnameProviderHasDaughter_surnameProvider();
			if ( oldLink!=null ) {
				oldLink.getSurnameCarryingDaughter().z_internalRemoveFromSurnameProviderHasDaughter_surnameProvider(oldLink);
			}
			this.z_internalAddToSurnameProviderHasDaughter_surnameCarryingDaughter(newLink);
			surnameCarryingDaughter.z_internalAddToSurnameProviderHasDaughter_surnameProvider(newLink);
		}
	}
	
	public void addToSurnameCarryingSon(Brother surnameCarryingSon) {
		if ( surnameCarryingSon!=null && !this.getSurnameCarryingSon().contains(surnameCarryingSon) ) {
			SurnameProviderHasSon newLink = new SurnameProviderHasSon((SurnameProvider)this,(Brother)surnameCarryingSon);
			SurnameProviderHasSon oldLink = surnameCarryingSon.getSurnameProviderHasSon_surnameProvider();
			if ( oldLink!=null ) {
				oldLink.getSurnameCarryingSon().z_internalRemoveFromSurnameProviderHasSon_surnameProvider(oldLink);
			}
			this.z_internalAddToSurnameProviderHasSon_surnameCarryingSon(newLink);
			surnameCarryingSon.z_internalAddToSurnameProviderHasSon_surnameProvider(newLink);
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
							curVal=model.util.ModelJavaMetaInfoMap.INSTANCE.newInstance(((Element)currentPropertyValueNode).getAttribute("classUuid"));
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
							curVal=model.util.ModelJavaMetaInfoMap.INSTANCE.newInstance(((Element)currentPropertyValueNode).getAttribute("classUuid"));
						}
						curVal.buildTreeFromXml((Element)currentPropertyValueNode,map);
						this.z_internalAddToSurnameProviderHasDaughter_surnameCarryingDaughter(curVal);
						curVal.z_internalAddToSurnameProvider(this);
						map.put(curVal.getUid(), curVal);
					}
				}
			}
		}
	}
	
	public void clearRelation() {
		Set<Relation> tmp = new HashSet<Relation>(getRelation());
		for ( Relation o : tmp ) {
			removeFromRelation(o.getFirstName(),o.getSurname(),o.getDateOfBirth(),o);
		}
	}
	
	public void clearSurnameCarryingDaughter() {
		Set<Sister> tmp = new HashSet<Sister>(getSurnameCarryingDaughter());
		for ( Sister o : tmp ) {
			removeFromSurnameCarryingDaughter(o);
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
	}
	
	public void copyState(Mother from, Mother to) {
		to.setFirstname(from.getFirstname());
		to.setSurname(from.getSurname());
	}
	
	public void createComponents() {
	}
	
	public SurnameProviderHasDaughter createSurnameProviderHasDaughter_surnameCarryingDaughter() {
		SurnameProviderHasDaughter newInstance= new SurnameProviderHasDaughter();
		newInstance.init(this);
		return newInstance;
	}
	
	public SurnameProviderHasSon createSurnameProviderHasSon_surnameCarryingSon() {
		SurnameProviderHasSon newInstance= new SurnameProviderHasSon();
		newInstance.init(this);
		return newInstance;
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
	
	public Set<OutgoingEvent> getOutgoingEvents() {
		return this.outgoingEvents;
	}
	
	public CompositionNode getOwningObject() {
		return getFamily();
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=4408335886692542833l,opposite="familyMember",uuid="Structures.uml@_wPOkwYhqEeK4s7QGypAJBA")
	public Set<Relation> getRelation() {
		Set result = new HashSet<Relation>();
		for ( FamilyMemberHasRelation cur : this.getFamilyMemberHasRelation_relation() ) {
			result.add(cur.getRelation());
		}
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=4841945505933354426l,opposite="surnameProvider",uuid="Structures.uml@_0-KlMYjPEeKq68owPnlvHg")
	@NumlMetaInfo(uuid="Structures.uml@_0-KlMYjPEeKq68owPnlvHg")
	public Spouse getSpouse() {
		Spouse result = this.spouse;
		
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=1128330048655112973l,uuid="Structures.uml@_CZjEgIiJEeKzQpCKar_mrQ")
	@NumlMetaInfo(uuid="Structures.uml@_CZjEgIiJEeKzQpCKar_mrQ")
	public String getSurname() {
		String result = this.surname;
		
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
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=7521053949158238812l,opposite="spouse",uuid="Structures.uml@_0-KlNIjPEeKq68owPnlvHg")
	@NumlMetaInfo(uuid="Structures.uml@_0-KlNIjPEeKq68owPnlvHg")
	public SurnameProvider getSurnameProvider() {
		SurnameProvider result = this.surnameProvider;
		
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=true,opaeumId=3102093816030840167l,opposite="surnameProvider",uuid="Structures.uml@_gtNy8IhrEeK4s7QGypAJBA")
	@NumlMetaInfo(uuid="Structures.uml@_Zi2eAIhrEeK4s7QGypAJBA@Structures.uml@_gtNy8IhrEeK4s7QGypAJBA")
	public Set<SurnameProviderHasDaughter> getSurnameProviderHasDaughter_surnameCarryingDaughter() {
		Set result = this.surnameProviderHasDaughter_surnameCarryingDaughter;
		
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
		return result;
	}
	
	public void markDeleted() {
		removeAllFromRelation(getRelation());
		if ( getSpouse()!=null ) {
			getSpouse().z_internalRemoveFromSurnameProvider(this);
		}
		if ( getSurnameProvider()!=null ) {
			getSurnameProvider().z_internalRemoveFromSpouse(this);
		}
		if ( getFamily()!=null ) {
			getFamily().z_internalRemoveFromMother(this);
		}
		for ( SurnameProviderHasSon child : new ArrayList<SurnameProviderHasSon>(getSurnameProviderHasSon_surnameCarryingSon()) ) {
			child.markDeleted();
		}
		for ( SurnameProviderHasDaughter child : new ArrayList<SurnameProviderHasDaughter>(getSurnameProviderHasDaughter_surnameCarryingDaughter()) ) {
			child.markDeleted();
		}
	}
	
	public void populateReferencesFromXml(Element xml, Map<String, Object> map) {
		NodeList propertyNodes = xml.getChildNodes();
		int i = 0;
		while ( i<propertyNodes.getLength() ) {
			Node currentPropertyNode = propertyNodes.item(i++);
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("surnameProvider") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("7521053949158238812")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						SurnameProvider surnameProvider = (SurnameProvider)map.get(((Element)currentPropertyValueNode).getAttribute("uid"));
						if ( surnameProvider!=null ) {
							z_internalAddToSurnameProvider(surnameProvider);
							surnameProvider.z_internalAddToSpouse(this);
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
							z_internalAddToFamilyMemberHasRelation_relation(familyMemberHasRelation_relation);
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
		}
	}
	
	public void removeAllFromRelation(Set<Relation> relation) {
		Set<Relation> tmp = new HashSet<Relation>(relation);
		for ( Relation o : tmp ) {
			removeFromRelation(o.getFirstName(),o.getSurname(),o.getDateOfBirth(),o);
		}
	}
	
	public void removeAllFromSurnameCarryingDaughter(Set<Sister> surnameCarryingDaughter) {
		Set<Sister> tmp = new HashSet<Sister>(surnameCarryingDaughter);
		for ( Sister o : tmp ) {
			removeFromSurnameCarryingDaughter(o);
		}
	}
	
	public void removeAllFromSurnameCarryingSon(Set<Brother> surnameCarryingSon) {
		Set<Brother> tmp = new HashSet<Brother>(surnameCarryingSon);
		for ( Brother o : tmp ) {
			removeFromSurnameCarryingSon(o);
		}
	}
	
	public void removeFromOwningObject() {
		this.markDeleted();
	}
	
	public void removeFromRelation(String firstName, String surname, Date dateOfBirth, Relation relation) {
		if ( relation!=null ) {
			FamilyMemberHasRelation oldLink = getFamilyMemberHasRelation_relationFor(relation);
			if ( oldLink!=null ) {
				relation.z_internalRemoveFromFamilyMemberHasRelation_familyMember(oldLink);
				oldLink.clear();
				z_internalRemoveFromFamilyMemberHasRelation_relation(relation.getFirstName(),relation.getSurname(),relation.getDateOfBirth(),oldLink);
			}
		}
	}
	
	public void removeFromSurnameCarryingDaughter(Sister surnameCarryingDaughter) {
		if ( surnameCarryingDaughter!=null ) {
			SurnameProviderHasDaughter oldLink = getSurnameProviderHasDaughter_surnameCarryingDaughterFor(surnameCarryingDaughter);
			if ( oldLink!=null ) {
				surnameCarryingDaughter.z_internalRemoveFromSurnameProviderHasDaughter_surnameProvider(oldLink);
				oldLink.clear();
				z_internalRemoveFromSurnameProviderHasDaughter_surnameCarryingDaughter(oldLink);
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
	
	public void setFamily(Family family) {
		Family oldValue = this.getFamily();
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
	}
	
	public void setFirstname(String firstname) {
		if ( firstname == null ) {
			this.z_internalRemoveFromFirstname(getFirstname());
		} else {
			this.z_internalAddToFirstname(firstname);
		}
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
		if ( oldValue==null ) {
			if ( spouse!=null ) {
				Mother oldOther = (Mother)spouse.getSurnameProvider();
				spouse.z_internalRemoveFromSurnameProvider(oldOther);
				if ( oldOther != null ) {
					oldOther.z_internalRemoveFromSpouse(spouse);
				}
				spouse.z_internalAddToSurnameProvider((Mother)this);
			}
			this.z_internalAddToSpouse(spouse);
		} else {
			if ( !oldValue.equals(spouse) ) {
				oldValue.z_internalRemoveFromSurnameProvider(this);
				z_internalRemoveFromSpouse(oldValue);
				if ( spouse!=null ) {
					Mother oldOther = (Mother)spouse.getSurnameProvider();
					spouse.z_internalRemoveFromSurnameProvider(oldOther);
					if ( oldOther != null ) {
						oldOther.z_internalRemoveFromSpouse(spouse);
					}
					spouse.z_internalAddToSurnameProvider((Mother)this);
				}
				this.z_internalAddToSpouse(spouse);
			}
		}
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
		if ( oldValue==null ) {
			if ( surnameProvider!=null ) {
				Mother oldOther = (Mother)surnameProvider.getSpouse();
				surnameProvider.z_internalRemoveFromSpouse(oldOther);
				if ( oldOther != null ) {
					oldOther.z_internalRemoveFromSurnameProvider(surnameProvider);
				}
				surnameProvider.z_internalAddToSpouse((Mother)this);
			}
			this.z_internalAddToSurnameProvider(surnameProvider);
		} else {
			if ( !oldValue.equals(surnameProvider) ) {
				oldValue.z_internalRemoveFromSpouse(this);
				z_internalRemoveFromSurnameProvider(oldValue);
				if ( surnameProvider!=null ) {
					Mother oldOther = (Mother)surnameProvider.getSpouse();
					surnameProvider.z_internalRemoveFromSpouse(oldOther);
					if ( oldOther != null ) {
						oldOther.z_internalRemoveFromSurnameProvider(surnameProvider);
					}
					surnameProvider.z_internalAddToSpouse((Mother)this);
				}
				this.z_internalAddToSurnameProvider(surnameProvider);
			}
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
		sb.append("className=\"model.Mother\" ");
		sb.append("uid=\"" + this.getUid() + "\" ");
		if ( getFirstname()!=null ) {
			sb.append("firstname=\""+ ModelFormatter.getInstance().formatString(getFirstname())+"\" ");
		}
		if ( getSurname()!=null ) {
			sb.append("surname=\""+ ModelFormatter.getInstance().formatString(getSurname())+"\" ");
		}
		sb.append(">");
		if ( getSurnameProvider()==null ) {
			sb.append("\n<surnameProvider/>");
		} else {
			sb.append("\n<surnameProvider propertyId=\"7521053949158238812\">");
			sb.append("\n" + getSurnameProvider().toXmlReferenceString());
			sb.append("\n</surnameProvider>");
		}
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
		sb.append("\n</Mother>");
		return sb.toString();
	}
	
	public void z_internalAddToFamily(Family family) {
		if ( family.equals(getFamily()) ) {
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
		if ( firstname.equals(getFirstname()) ) {
			return;
		}
		this.firstname=firstname;
	}
	
	public void z_internalAddToSpouse(Spouse spouse) {
		if ( spouse.equals(getSpouse()) ) {
			return;
		}
		this.spouse=spouse;
	}
	
	public void z_internalAddToSurname(String surname) {
		if ( surname.equals(getSurname()) ) {
			return;
		}
		this.surname=surname;
	}
	
	public void z_internalAddToSurnameProvider(SurnameProvider surnameProvider) {
		if ( surnameProvider.equals(getSurnameProvider()) ) {
			return;
		}
		this.surnameProvider=surnameProvider;
	}
	
	public void z_internalAddToSurnameProviderHasDaughter_surnameCarryingDaughter(SurnameProviderHasDaughter surnameProviderHasDaughter_surnameCarryingDaughter) {
		if ( getSurnameProviderHasDaughter_surnameCarryingDaughter().contains(surnameProviderHasDaughter_surnameCarryingDaughter) ) {
			return;
		}
		this.surnameProviderHasDaughter_surnameCarryingDaughter.add(surnameProviderHasDaughter_surnameCarryingDaughter);
	}
	
	public void z_internalAddToSurnameProviderHasSon_surnameCarryingSon(SurnameProviderHasSon surnameProviderHasSon_surnameCarryingSon) {
		if ( getSurnameProviderHasSon_surnameCarryingSon().contains(surnameProviderHasSon_surnameCarryingSon) ) {
			return;
		}
		this.surnameProviderHasSon_surnameCarryingSon.add(surnameProviderHasSon_surnameCarryingSon);
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
	
	public void z_internalRemoveFromSpouse(Spouse spouse) {
		if ( getSpouse()!=null && spouse!=null && spouse.equals(getSpouse()) ) {
			this.spouse=null;
			this.spouse=null;
		}
	}
	
	public void z_internalRemoveFromSurname(String surname) {
		if ( getSurname()!=null && surname!=null && surname.equals(getSurname()) ) {
			this.surname=null;
			this.surname=null;
		}
	}
	
	public void z_internalRemoveFromSurnameProvider(SurnameProvider surnameProvider) {
		if ( getSurnameProvider()!=null && surnameProvider!=null && surnameProvider.equals(getSurnameProvider()) ) {
			this.surnameProvider=null;
			this.surnameProvider=null;
		}
	}
	
	public void z_internalRemoveFromSurnameProviderHasDaughter_surnameCarryingDaughter(SurnameProviderHasDaughter surnameProviderHasDaughter_surnameCarryingDaughter) {
		this.surnameProviderHasDaughter_surnameCarryingDaughter.remove(surnameProviderHasDaughter_surnameCarryingDaughter);
	}
	
	public void z_internalRemoveFromSurnameProviderHasSon_surnameCarryingSon(SurnameProviderHasSon surnameProviderHasSon_surnameCarryingSon) {
		this.surnameProviderHasSon_surnameCarryingSon.remove(surnameProviderHasSon_surnameCarryingSon);
	}

}