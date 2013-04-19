package org.opaeum.test;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.opaeum.annotation.NumlMetaInfo;
import org.opaeum.annotation.PropertyMetaInfo;
import org.opaeum.runtime.domain.CancelledEvent;
import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.runtime.domain.IEventGenerator;
import org.opaeum.runtime.domain.IntrospectionUtil;
import org.opaeum.runtime.domain.OutgoingEvent;
import org.opaeum.runtime.environment.Environment;
import org.opaeum.test.util.ModelFormatter;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

@NumlMetaInfo(applicationIdentifier="structuretests",uuid="Structures.uml@_bzlKgIhrEeK4s7QGypAJBA")
public class Brother extends Child implements IEventGenerator, CompositionNode, Serializable {
	transient private Set<CancelledEvent> cancelledEvents = new HashSet<CancelledEvent>();
	transient private Set<OutgoingEvent> outgoingEvents = new HashSet<OutgoingEvent>();
	static final private long serialVersionUID = 5473144541454217719l;
	protected Map<String, Sister> sister = new HashMap<String,Sister>();
	protected SurnameProviderHasSon surnameProviderHasSon_surnameProvider;
	private String z_keyOfBrotherOnSister;

	/** This constructor is intended for easy initialization in unit tests
	 * 
	 * @param owningObject 
	 * @param name 
	 */
	public Brother(Family owningObject, String name) {
		setName(name);
		init(owningObject);
		addToOwningObject();
	}
	
	/** Default constructor for Brother
	 */
	public Brother() {
	}

	public void addAllToSister(Set<Sister> sister) {
		for ( Sister o : sister ) {
			addToSister(o.getName(),o);
		}
	}
	
	/** Call this method when you want to attach this object to the containment tree. Useful with transitive persistence
	 */
	public void addToOwningObject() {
		if ( getName()==null ) {
			throw new IllegalStateException("The qualifying property 'name' must be set before adding a value to 'family'");
		}
		getFamily().z_internalAddToChild(this.getName(),(Brother)this);
	}
	
	public void addToSister(String name, Sister sister) {
		if ( sister!=null ) {
			if ( getName()==null ) {
				throw new IllegalStateException("The qualifying property 'name' must be set before adding a value to 'sister'");
			}
			sister.z_internalAddToBrother(this.getName(),this);
		}
		z_internalAddToSister(name,sister);
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
	
	public void clearSister() {
		Set<Sister> tmp = new HashSet<Sister>(getSister());
		for ( Sister o : tmp ) {
			removeFromSister(o.getName(),o);
		}
	}
	
	public void copyShallowState(Brother from, Brother to) {
		to.setName(from.getName());
		to.setDateOfBirth(from.getDateOfBirth());
		to.addAllToSister(copyShallowStateSister(from.getSister()));
	}
	
	public Set<Sister> copyShallowStateSister(Set<Sister> from) {
		Set result = new HashSet<Sister>();
		for ( Sister entity : from ) {
			result.add(entity.makeShallowCopy());
		}
		return result;
	}
	
	public void copyState(Brother from, Brother to) {
		to.setName(from.getName());
		to.setDateOfBirth(from.getDateOfBirth());
		to.addAllToSister(copyStateSister(from.getSister()));
	}
	
	public Set<Sister> copyStateSister(Set<Sister> from) {
		Set result = new HashSet<Sister>();
		for ( Sister entity : from ) {
			result.add(entity.makeCopy());
		}
		return result;
	}
	
	public void createComponents() {
		super.createComponents();
	}
	
	public boolean equals(Object other) {
		if ( other instanceof Brother ) {
			return other==this || ((Brother)other).getUid().equals(this.getUid());
		}
		return false;
	}
	
	public Set<CancelledEvent> getCancelledEvents() {
		return this.cancelledEvents;
	}
	
	public Set<OutgoingEvent> getOutgoingEvents() {
		return this.outgoingEvents;
	}
	
	public CompositionNode getOwningObject() {
		return getFamily();
	}
	
	public Sister getSister(String name) {
		Sister result = null;
		String key = ModelFormatter.getInstance().formatStringQualifier(name);
		result=this.sister.get(key.toString());
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=1375397564067999903l,opposite="brother",uuid="Structures.uml@_Ftpt8IlXEeK8Z-Y1T93HUw")
	@NumlMetaInfo(uuid="Structures.uml@_Ftpt8IlXEeK8Z-Y1T93HUw")
	public Set<Sister> getSister() {
		Set result = new HashSet<Sister>(this.sister.values());
		
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=4119494389252234111l,opposite="surnameCarryingSon",uuid="Structures.uml@_g-UxEIhrEeK4s7QGypAJBA")
	public SurnameProvider getSurnameProvider() {
		SurnameProvider result = null;
		if ( this.surnameProviderHasSon_surnameProvider!=null ) {
			result = this.surnameProviderHasSon_surnameProvider.getSurnameProvider();
		}
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=4285853828966048752l,opposite="surnameCarryingSon",uuid="Structures.uml@_g-YbcIhrEeK4s7QGypAJBA")
	@NumlMetaInfo(uuid="Structures.uml@_bzlKgIhrEeK4s7QGypAJBA@Structures.uml@_g-YbcIhrEeK4s7QGypAJBA")
	public SurnameProviderHasSon getSurnameProviderHasSon_surnameProvider() {
		SurnameProviderHasSon result = this.surnameProviderHasSon_surnameProvider;
		
		return result;
	}
	
	public SurnameProviderHasSon getSurnameProviderHasSon_surnameProviderFor(SurnameProvider match) {
		if ( surnameProviderHasSon_surnameProvider.getSurnameProvider().equals(match) ) {
			return surnameProviderHasSon_surnameProvider;
		} else {
			return null;
		}
	}
	
	public String getZ_keyOfBrotherOnSister() {
		return this.z_keyOfBrotherOnSister;
	}
	
	public int hashCode() {
		return getUid().hashCode();
	}
	
	public void init(CompositionNode owner) {
		super.init(owner);
		this.z_internalAddToFamily((Family)owner);
	}
	
	public Brother makeCopy() {
		Brother result = new Brother();
		copyState((Brother)this,result);
		return result;
	}
	
	public Brother makeShallowCopy() {
		Brother result = new Brother();
		copyShallowState((Brother)this,result);
		return result;
	}
	
	public void markDeleted() {
		super.markDeleted();
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
		if ( getSurnameProvider()!=null ) {
			SurnameProviderHasSon link = getSurnameProviderHasSon_surnameProvider();
			link.getSurnameProvider().z_internalRemoveFromSurnameProviderHasSon_surnameCarryingSon(link);
			link.markDeleted();
		}
		removeAllFromSister(getSister());
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
		if ( getSurnameProviderHasSon_surnameProvider()!=null ) {
			getSurnameProviderHasSon_surnameProvider().z_internalRemoveFromSurnameCarryingSon(this);
		}
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
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("surnameProviderHasSon_surnameProvider") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("4285853828966048752")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						SurnameProviderHasSon surnameProviderHasSon_surnameProvider = (SurnameProviderHasSon)map.get(((Element)currentPropertyValueNode).getAttribute("uid"));
						if ( surnameProviderHasSon_surnameProvider!=null ) {
							z_internalAddToSurnameProviderHasSon_surnameProvider(surnameProviderHasSon_surnameProvider);
							surnameProviderHasSon_surnameProvider.z_internalAddToSurnameCarryingSon(this);
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
	
	public void removeAllFromSister(Set<Sister> sister) {
		Set<Sister> tmp = new HashSet<Sister>(sister);
		for ( Sister o : tmp ) {
			removeFromSister(o.getName(),o);
		}
	}
	
	public void removeFromOwningObject() {
		this.markDeleted();
	}
	
	public void removeFromSister(String name, Sister sister) {
		if ( sister!=null ) {
			sister.z_internalRemoveFromBrother(this.getName(),this);
			z_internalRemoveFromSister(sister.getName(),sister);
		}
	}
	
	public void setCancelledEvents(Set<CancelledEvent> cancelledEvents) {
		this.cancelledEvents=cancelledEvents;
	}
	
	public void setOutgoingEvents(Set<OutgoingEvent> outgoingEvents) {
		this.outgoingEvents=outgoingEvents;
	}
	
	public void setSister(Set<Sister> sister) {
		this.clearSister();
		this.addAllToSister(sister);
	}
	
	public void setSurnameProvider(SurnameProvider surnameProvider) {
		if ( this.getSurnameProvider()!=null ) {
			this.getSurnameProvider().z_internalRemoveFromSurnameProviderHasSon_surnameCarryingSon(getSurnameProviderHasSon_surnameProvider());
		}
		if ( surnameProvider == null ) {
			this.z_internalRemoveFromSurnameProviderHasSon_surnameProvider(this.getSurnameProviderHasSon_surnameProvider());
		} else {
			SurnameProviderHasSon surnameProviderHasSon_surnameProvider = new SurnameProviderHasSon((Brother)this,(SurnameProvider)surnameProvider);
			this.z_internalAddToSurnameProviderHasSon_surnameProvider(surnameProviderHasSon_surnameProvider);
			surnameProvider.z_internalAddToSurnameProviderHasSon_surnameCarryingSon(surnameProviderHasSon_surnameProvider);
		}
	}
	
	public void setZ_keyOfBrotherOnSister(String z_keyOfBrotherOnSister) {
		this.z_keyOfBrotherOnSister=z_keyOfBrotherOnSister;
	}
	
	public String toXmlReferenceString() {
		return "<Brother uid=\""+getUid() + "\"/>";
	}
	
	public String toXmlString() {
		StringBuilder sb = new StringBuilder();
		sb.append("<Brother ");
		sb.append("classUuid=\"Structures.uml@_bzlKgIhrEeK4s7QGypAJBA\" ");
		sb.append("className=\"org.opaeum.test.Brother\" ");
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
		if ( getSurnameProviderHasSon_surnameProvider()==null ) {
			sb.append("\n<surnameProviderHasSon_surnameProvider/>");
		} else {
			sb.append("\n<surnameProviderHasSon_surnameProvider propertyId=\"4285853828966048752\">");
			sb.append("\n" + getSurnameProviderHasSon_surnameProvider().toXmlReferenceString());
			sb.append("\n</surnameProviderHasSon_surnameProvider>");
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
		sb.append("\n</Brother>");
		return sb.toString();
	}
	
	public void z_internalAddToSister(String name, Sister sister) {
		String key = ModelFormatter.getInstance().formatStringQualifier(name);
		if ( getSister().contains(sister) ) {
			return;
		}
		sister.z_internalAddToName(name);
		this.sister.put(key.toString(),sister);
		sister.setZ_keyOfSisterOnBrother(key.toString());
	}
	
	public void z_internalAddToSurnameProviderHasSon_surnameProvider(SurnameProviderHasSon surnameProviderHasSon_surnameProvider) {
		if ( surnameProviderHasSon_surnameProvider.equals(getSurnameProviderHasSon_surnameProvider()) ) {
			return;
		}
		this.surnameProviderHasSon_surnameProvider=surnameProviderHasSon_surnameProvider;
	}
	
	public void z_internalRemoveFromSister(String name, Sister sister) {
		String key = ModelFormatter.getInstance().formatStringQualifier(name);
		this.sister.remove(key.toString());
	}
	
	public void z_internalRemoveFromSurnameProviderHasSon_surnameProvider(SurnameProviderHasSon surnameProviderHasSon_surnameProvider) {
		if ( getSurnameProviderHasSon_surnameProvider()!=null && surnameProviderHasSon_surnameProvider!=null && surnameProviderHasSon_surnameProvider.equals(getSurnameProviderHasSon_surnameProvider()) ) {
			this.surnameProviderHasSon_surnameProvider=null;
			this.surnameProviderHasSon_surnameProvider=null;
		}
	}

}