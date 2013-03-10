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
import org.opaeum.runtime.strategy.DateStrategyFactory;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

@NumlMetaInfo(applicationIdentifier="structuretests",uuid="Structures.uml@_dH6VoIhqEeK4s7QGypAJBA")
public class Uncle implements IEventGenerator, CompositionNode, Relation, Serializable {
	transient private Set<CancelledEvent> cancelledEvents = new HashSet<CancelledEvent>();
	protected Child child;
	protected Date dateOfBirth;
	protected Set<FamilyMemberHasRelation> familyMemberHasRelation_familyMember = new HashSet<FamilyMemberHasRelation>();
	protected String firstName;
	protected String name;
	transient private Set<OutgoingEvent> outgoingEvents = new HashSet<OutgoingEvent>();
	static final private long serialVersionUID = 4431394108956167905l;
	protected String surname;
	private String uid;

	/** Default constructor for Uncle
	 */
	public Uncle() {
	}

	public void addAllToFamilyMember(Set<FamilyMember> familyMember) {
		for ( FamilyMember o : familyMember ) {
			addToFamilyMember(o);
		}
	}
	
	public void addToFamilyMember(FamilyMember familyMember) {
		if ( familyMember!=null && !this.getFamilyMember().contains(familyMember) ) {
			FamilyMemberHasRelation newLink = new FamilyMemberHasRelation((Relation)this,(FamilyMember)familyMember);
			this.z_internalAddToFamilyMemberHasRelation_familyMember(newLink);
			familyMember.z_internalAddToFamilyMemberHasRelation_relation(this.getFirstName(),this.getSurname(),this.getDateOfBirth(),newLink);
		}
	}
	
	/** Call this method when you want to attach this object to the containment tree. Useful with transitive persistence
	 */
	public void addToOwningObject() {
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
							curVal=model.util.ModelJavaMetaInfoMap.INSTANCE.newInstance(((Element)currentPropertyValueNode).getAttribute("classUuid"));
						}
						curVal.buildTreeFromXml((Element)currentPropertyValueNode,map);
						this.z_internalAddToFamilyMemberHasRelation_familyMember(curVal);
						curVal.z_internalAddToRelation(this);
						map.put(curVal.getUid(), curVal);
					}
				}
			}
		}
	}
	
	public void clearFamilyMember() {
		Set<FamilyMember> tmp = new HashSet<FamilyMember>(getFamilyMember());
		for ( FamilyMember o : tmp ) {
			removeFromFamilyMember(o);
		}
	}
	
	public void copyShallowState(Uncle from, Uncle to) {
		to.setFirstName(from.getFirstName());
		to.setSurname(from.getSurname());
		to.setDateOfBirth(from.getDateOfBirth());
		to.setName(from.getName());
	}
	
	public void copyState(Uncle from, Uncle to) {
		to.setFirstName(from.getFirstName());
		to.setSurname(from.getSurname());
		to.setDateOfBirth(from.getDateOfBirth());
		to.setName(from.getName());
	}
	
	public void createComponents() {
	}
	
	public FamilyMemberHasRelation createFamilyMemberHasRelation_familyMember() {
		FamilyMemberHasRelation newInstance= new FamilyMemberHasRelation();
		newInstance.init(this);
		return newInstance;
	}
	
	public boolean equals(Object other) {
		if ( other instanceof Uncle ) {
			return other==this || ((Uncle)other).getUid().equals(this.getUid());
		}
		return false;
	}
	
	public Set<CancelledEvent> getCancelledEvents() {
		return this.cancelledEvents;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=984124978325014811l,opposite="godParent",uuid="Structures.uml@_I7GooYhrEeK4s7QGypAJBA")
	@NumlMetaInfo(uuid="Structures.uml@_I7GooYhrEeK4s7QGypAJBA")
	public Child getChild() {
		Child result = this.child;
		
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=4576801132852053498l,strategyFactory=DateStrategyFactory.class,uuid="Structures.uml@_wFrE4IjSEeKq68owPnlvHg")
	@NumlMetaInfo(uuid="Structures.uml@_wFrE4IjSEeKq68owPnlvHg")
	public Date getDateOfBirth() {
		Date result = this.dateOfBirth;
		
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
	
	@PropertyMetaInfo(constraints={},isComposite=true,opaeumId=3903606770219243395l,opposite="relation",uuid="Structures.uml@_wPOkwIhqEeK4s7QGypAJBA")
	@NumlMetaInfo(uuid="Structures.uml@_bVPeIIhqEeK4s7QGypAJBA@Structures.uml@_wPOkwIhqEeK4s7QGypAJBA")
	public Set<FamilyMemberHasRelation> getFamilyMemberHasRelation_familyMember() {
		Set result = this.familyMemberHasRelation_familyMember;
		
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
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=3420291012060486540l,uuid="Structures.uml@_veSRMIiEEeKgR4NjNPh_CA")
	@NumlMetaInfo(uuid="Structures.uml@_veSRMIiEEeKgR4NjNPh_CA")
	public String getName() {
		String result = this.name;
		
		return result;
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
	
	public Uncle makeCopy() {
		Uncle result = new Uncle();
		copyState((Uncle)this,result);
		return result;
	}
	
	public Uncle makeShallowCopy() {
		Uncle result = new Uncle();
		copyShallowState((Uncle)this,result);
		return result;
	}
	
	public void markDeleted() {
		if ( getChild()!=null ) {
			getChild().z_internalRemoveFromGodParent(this);
		}
		removeAllFromFamilyMember(getFamilyMember());
		for ( FamilyMemberHasRelation child : new ArrayList<FamilyMemberHasRelation>(getFamilyMemberHasRelation_familyMember()) ) {
			child.markDeleted();
		}
	}
	
	public void populateReferencesFromXml(Element xml, Map<String, Object> map) {
		NodeList propertyNodes = xml.getChildNodes();
		int i = 0;
		while ( i<propertyNodes.getLength() ) {
			Node currentPropertyNode = propertyNodes.item(i++);
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("child") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("984124978325014811")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						Child child = (Child)map.get(((Element)currentPropertyValueNode).getAttribute("uid"));
						if ( child!=null ) {
							z_internalAddToChild(child);
							child.z_internalAddToGodParent(this);
						}
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
	
	public void removeAllFromFamilyMember(Set<FamilyMember> familyMember) {
		Set<FamilyMember> tmp = new HashSet<FamilyMember>(familyMember);
		for ( FamilyMember o : tmp ) {
			removeFromFamilyMember(o);
		}
	}
	
	public void removeFromFamilyMember(FamilyMember familyMember) {
		if ( familyMember!=null ) {
			FamilyMemberHasRelation oldLink = getFamilyMemberHasRelation_familyMemberFor(familyMember);
			if ( oldLink!=null ) {
				familyMember.z_internalRemoveFromFamilyMemberHasRelation_relation(this.getFirstName(),this.getSurname(),this.getDateOfBirth(),oldLink);
				oldLink.clear();
				z_internalRemoveFromFamilyMemberHasRelation_familyMember(oldLink);
			}
		}
	}
	
	public void removeFromOwningObject() {
		this.markDeleted();
	}
	
	public void setCancelledEvents(Set<CancelledEvent> cancelledEvents) {
		this.cancelledEvents=cancelledEvents;
	}
	
	public void setChild(Child child) {
		Child oldValue = this.getChild();
		if ( oldValue==null ) {
			if ( child!=null ) {
				Uncle oldOther = (Uncle)child.getGodParent();
				child.z_internalRemoveFromGodParent(oldOther);
				if ( oldOther != null ) {
					oldOther.z_internalRemoveFromChild(child);
				}
				child.z_internalAddToGodParent((Uncle)this);
			}
			this.z_internalAddToChild(child);
		} else {
			if ( !oldValue.equals(child) ) {
				oldValue.z_internalRemoveFromGodParent(this);
				z_internalRemoveFromChild(oldValue);
				if ( child!=null ) {
					Uncle oldOther = (Uncle)child.getGodParent();
					child.z_internalRemoveFromGodParent(oldOther);
					if ( oldOther != null ) {
						oldOther.z_internalRemoveFromChild(child);
					}
					child.z_internalAddToGodParent((Uncle)this);
				}
				this.z_internalAddToChild(child);
			}
		}
	}
	
	public void setDateOfBirth(Date dateOfBirth) {
		for ( FamilyMemberHasRelation curVal : getFamilyMemberHasRelation_familyMember() ) {
			curVal.getFamilyMember().z_internalRemoveFromFamilyMemberHasRelation_relation(this.getFirstName(),this.getSurname(),this.getDateOfBirth(),curVal);
		}
		if ( dateOfBirth == null ) {
			this.z_internalRemoveFromDateOfBirth(getDateOfBirth());
		} else {
			this.z_internalAddToDateOfBirth(dateOfBirth);
		}
		for ( FamilyMemberHasRelation curVal : getFamilyMemberHasRelation_familyMember() ) {
			curVal.getFamilyMember().z_internalAddToFamilyMemberHasRelation_relation(this.getFirstName(),this.getSurname(),this.getDateOfBirth(),curVal);
		}
	}
	
	public void setFamilyMember(Set<FamilyMember> familyMember) {
		this.clearFamilyMember();
		this.addAllToFamilyMember(familyMember);
	}
	
	public void setFirstName(String firstName) {
		for ( FamilyMemberHasRelation curVal : getFamilyMemberHasRelation_familyMember() ) {
			curVal.getFamilyMember().z_internalRemoveFromFamilyMemberHasRelation_relation(this.getFirstName(),this.getSurname(),this.getDateOfBirth(),curVal);
		}
		if ( firstName == null ) {
			this.z_internalRemoveFromFirstName(getFirstName());
		} else {
			this.z_internalAddToFirstName(firstName);
		}
		for ( FamilyMemberHasRelation curVal : getFamilyMemberHasRelation_familyMember() ) {
			curVal.getFamilyMember().z_internalAddToFamilyMemberHasRelation_relation(this.getFirstName(),this.getSurname(),this.getDateOfBirth(),curVal);
		}
	}
	
	public void setName(String name) {
		if ( name == null ) {
			this.z_internalRemoveFromName(getName());
		} else {
			this.z_internalAddToName(name);
		}
	}
	
	public void setOutgoingEvents(Set<OutgoingEvent> outgoingEvents) {
		this.outgoingEvents=outgoingEvents;
	}
	
	public void setSurname(String surname) {
		for ( FamilyMemberHasRelation curVal : getFamilyMemberHasRelation_familyMember() ) {
			curVal.getFamilyMember().z_internalRemoveFromFamilyMemberHasRelation_relation(this.getFirstName(),this.getSurname(),this.getDateOfBirth(),curVal);
		}
		if ( surname == null ) {
			this.z_internalRemoveFromSurname(getSurname());
		} else {
			this.z_internalAddToSurname(surname);
		}
		for ( FamilyMemberHasRelation curVal : getFamilyMemberHasRelation_familyMember() ) {
			curVal.getFamilyMember().z_internalAddToFamilyMemberHasRelation_relation(this.getFirstName(),this.getSurname(),this.getDateOfBirth(),curVal);
		}
	}
	
	public void setUid(String newUid) {
		this.uid=newUid;
	}
	
	public String toXmlReferenceString() {
		return "<Uncle uid=\""+getUid() + "\"/>";
	}
	
	public String toXmlString() {
		StringBuilder sb = new StringBuilder();
		sb.append("<Uncle ");
		sb.append("classUuid=\"Structures.uml@_dH6VoIhqEeK4s7QGypAJBA\" ");
		sb.append("className=\"model.Uncle\" ");
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
		if ( getChild()==null ) {
			sb.append("\n<child/>");
		} else {
			sb.append("\n<child propertyId=\"984124978325014811\">");
			sb.append("\n" + getChild().toXmlReferenceString());
			sb.append("\n</child>");
		}
		sb.append("\n<familyMemberHasRelation_familyMember propertyId=\"3903606770219243395\">");
		for ( FamilyMemberHasRelation familyMemberHasRelation_familyMember : getFamilyMemberHasRelation_familyMember() ) {
			sb.append("\n" + familyMemberHasRelation_familyMember.toXmlString());
		}
		sb.append("\n</familyMemberHasRelation_familyMember>");
		sb.append("\n</Uncle>");
		return sb.toString();
	}
	
	public void z_internalAddToChild(Child child) {
		if ( child.equals(getChild()) ) {
			return;
		}
		this.child=child;
	}
	
	public void z_internalAddToDateOfBirth(Date dateOfBirth) {
		if ( dateOfBirth.equals(getDateOfBirth()) ) {
			return;
		}
		this.dateOfBirth=dateOfBirth;
	}
	
	public void z_internalAddToFamilyMemberHasRelation_familyMember(FamilyMemberHasRelation familyMemberHasRelation_familyMember) {
		if ( getFamilyMemberHasRelation_familyMember().contains(familyMemberHasRelation_familyMember) ) {
			return;
		}
		this.familyMemberHasRelation_familyMember.add(familyMemberHasRelation_familyMember);
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
	
	public void z_internalRemoveFromChild(Child child) {
		if ( getChild()!=null && child!=null && child.equals(getChild()) ) {
			this.child=null;
			this.child=null;
		}
	}
	
	public void z_internalRemoveFromDateOfBirth(Date dateOfBirth) {
		if ( getDateOfBirth()!=null && dateOfBirth!=null && dateOfBirth.equals(getDateOfBirth()) ) {
			this.dateOfBirth=null;
			this.dateOfBirth=null;
		}
	}
	
	public void z_internalRemoveFromFamilyMemberHasRelation_familyMember(FamilyMemberHasRelation familyMemberHasRelation_familyMember) {
		this.familyMemberHasRelation_familyMember.remove(familyMemberHasRelation_familyMember);
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