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

@NumlMetaInfo(applicationIdentifier="structuretests",uuid="Structures.uml@_dS3zAIhrEeK4s7QGypAJBA")
public class Sister extends Child implements IEventGenerator, CompositionNode, Serializable {
	protected Map<String, Brother> brother = new HashMap<String,Brother>();
	transient private Set<CancelledEvent> cancelledEvents = new HashSet<CancelledEvent>();
	transient private Set<OutgoingEvent> outgoingEvents = new HashSet<OutgoingEvent>();
	static final private long serialVersionUID = 8714499026471153695l;
	protected SurnameProviderHasDaughter surnameProviderHasDaughter_surnameProvider;
	private String z_keyOfSisterOnBrother;

	/** This constructor is intended for easy initialization in unit tests
	 * 
	 * @param owningObject 
	 * @param name 
	 */
	public Sister(Family owningObject, String name) {
		setName(name);
		init(owningObject);
		addToOwningObject();
	}
	
	/** Default constructor for Sister
	 */
	public Sister() {
	}

	public void addAllToBrother(Set<Brother> brother) {
		for ( Brother o : brother ) {
			addToBrother(o.getName(),o);
		}
	}
	
	public void addToBrother(String name, Brother brother) {
		if ( brother!=null ) {
			if ( getName()==null ) {
				throw new IllegalStateException("The qualifying property 'name' must be set before adding a value to 'brother'");
			}
			brother.z_internalAddToSister(this.getName(),this);
		}
		z_internalAddToBrother(name,brother);
	}
	
	/** Call this method when you want to attach this object to the containment tree. Useful with transitive persistence
	 */
	public void addToOwningObject() {
		getFamily().z_internalAddToChild(this.getName(),(Sister)this);
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
	
	public void clearBrother() {
		Set<Brother> tmp = new HashSet<Brother>(getBrother());
		for ( Brother o : tmp ) {
			removeFromBrother(o.getName(),o);
		}
	}
	
	public void copyShallowState(Sister from, Sister to) {
		to.setName(from.getName());
		to.setDateOfBirth(from.getDateOfBirth());
	}
	
	public void copyState(Sister from, Sister to) {
		to.setName(from.getName());
		to.setDateOfBirth(from.getDateOfBirth());
	}
	
	public void createComponents() {
		super.createComponents();
	}
	
	public boolean equals(Object other) {
		if ( other instanceof Sister ) {
			return other==this || ((Sister)other).getUid().equals(this.getUid());
		}
		return false;
	}
	
	public Brother getBrother(String name) {
		Brother result = null;
		String key = ModelFormatter.getInstance().formatStringQualifier(name);
		result=this.brother.get(key.toString());
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=3115366053943004335l,opposite="sister",uuid="Structures.uml@_FtqVAYlXEeK8Z-Y1T93HUw")
	@NumlMetaInfo(uuid="Structures.uml@_FtqVAYlXEeK8Z-Y1T93HUw")
	public Set<Brother> getBrother() {
		Set result = new HashSet<Brother>(this.brother.values());
		
		return result;
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
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=5018664865751679831l,opposite="surnameCarryingDaughter",uuid="Structures.uml@_gtKIkIhrEeK4s7QGypAJBA")
	public SurnameProvider getSurnameProvider() {
		SurnameProvider result = null;
		if ( this.surnameProviderHasDaughter_surnameProvider!=null ) {
			result = this.surnameProviderHasDaughter_surnameProvider.getSurnameProvider();
		}
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=6312646117027228013l,opposite="surnameCarryingDaughter",uuid="Structures.uml@_gtNy8IhrEeK4s7QGypAJBA")
	@NumlMetaInfo(uuid="Structures.uml@_dS3zAIhrEeK4s7QGypAJBA@Structures.uml@_gtNy8IhrEeK4s7QGypAJBA")
	public SurnameProviderHasDaughter getSurnameProviderHasDaughter_surnameProvider() {
		SurnameProviderHasDaughter result = this.surnameProviderHasDaughter_surnameProvider;
		
		return result;
	}
	
	public SurnameProviderHasDaughter getSurnameProviderHasDaughter_surnameProviderFor(SurnameProvider match) {
		if ( surnameProviderHasDaughter_surnameProvider.getSurnameProvider().equals(match) ) {
			return surnameProviderHasDaughter_surnameProvider;
		} else {
			return null;
		}
	}
	
	public String getZ_keyOfSisterOnBrother() {
		return this.z_keyOfSisterOnBrother;
	}
	
	public int hashCode() {
		return getUid().hashCode();
	}
	
	public void init(CompositionNode owner) {
		super.init(owner);
		this.z_internalAddToFamily((Family)owner);
	}
	
	public Sister makeCopy() {
		Sister result = new Sister();
		copyState((Sister)this,result);
		return result;
	}
	
	public Sister makeShallowCopy() {
		Sister result = new Sister();
		copyShallowState((Sister)this,result);
		return result;
	}
	
	public void markDeleted() {
		super.markDeleted();
		removeAllFromGodParent(getGodParent());
		if ( getSurnameProvider()!=null ) {
			SurnameProviderHasDaughter link = getSurnameProviderHasDaughter_surnameProvider();
			link.getSurnameProvider().z_internalRemoveFromSurnameProviderHasDaughter_surnameCarryingDaughter(link);
			link.markDeleted();
		}
		removeAllFromRelation(getRelation());
		if ( getFamily()!=null ) {
			getFamily().z_internalRemoveFromChild(this.getName(),this);
		}
		removeAllFromBrother(getBrother());
		if ( getSurnameProviderHasDaughter_surnameProvider()!=null ) {
			getSurnameProviderHasDaughter_surnameProvider().z_internalRemoveFromSurnameCarryingDaughter(this);
		}
	}
	
	public void populateReferencesFromXml(Element xml, Map<String, Object> map) {
		NodeList propertyNodes = xml.getChildNodes();
		int i = 0;
		while ( i<propertyNodes.getLength() ) {
			Node currentPropertyNode = propertyNodes.item(i++);
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("brother") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("3115366053943004335")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						Brother brother = (Brother)map.get(((Element)currentPropertyValueNode).getAttribute("uid"));
						if ( brother!=null ) {
							z_internalAddToBrother(brother.getName(),brother);
							brother.z_internalAddToSister(this.getName(),this);
						}
					}
				}
			}
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("surnameProviderHasDaughter_surnameProvider") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("6312646117027228013")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						SurnameProviderHasDaughter surnameProviderHasDaughter_surnameProvider = (SurnameProviderHasDaughter)map.get(((Element)currentPropertyValueNode).getAttribute("uid"));
						if ( surnameProviderHasDaughter_surnameProvider!=null ) {
							z_internalAddToSurnameProviderHasDaughter_surnameProvider(surnameProviderHasDaughter_surnameProvider);
							surnameProviderHasDaughter_surnameProvider.z_internalAddToSurnameCarryingDaughter(this);
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
	
	public void removeAllFromBrother(Set<Brother> brother) {
		Set<Brother> tmp = new HashSet<Brother>(brother);
		for ( Brother o : tmp ) {
			removeFromBrother(o.getName(),o);
		}
	}
	
	public void removeFromBrother(String name, Brother brother) {
		if ( brother!=null ) {
			brother.z_internalRemoveFromSister(this.getName(),this);
			z_internalRemoveFromBrother(brother.getName(),brother);
		}
	}
	
	public void removeFromOwningObject() {
		this.markDeleted();
	}
	
	public void setBrother(Set<Brother> brother) {
		this.clearBrother();
		this.addAllToBrother(brother);
	}
	
	public void setCancelledEvents(Set<CancelledEvent> cancelledEvents) {
		this.cancelledEvents=cancelledEvents;
	}
	
	public void setOutgoingEvents(Set<OutgoingEvent> outgoingEvents) {
		this.outgoingEvents=outgoingEvents;
	}
	
	public void setSurnameProvider(SurnameProvider surnameProvider) {
		if ( this.getSurnameProvider()!=null ) {
			this.getSurnameProvider().z_internalRemoveFromSurnameProviderHasDaughter_surnameCarryingDaughter(getSurnameProviderHasDaughter_surnameProvider());
		}
		if ( surnameProvider == null ) {
			this.z_internalRemoveFromSurnameProviderHasDaughter_surnameProvider(this.getSurnameProviderHasDaughter_surnameProvider());
		} else {
			SurnameProviderHasDaughter surnameProviderHasDaughter_surnameProvider = new SurnameProviderHasDaughter((Sister)this,(SurnameProvider)surnameProvider);
			this.z_internalAddToSurnameProviderHasDaughter_surnameProvider(surnameProviderHasDaughter_surnameProvider);
			surnameProvider.z_internalAddToSurnameProviderHasDaughter_surnameCarryingDaughter(surnameProviderHasDaughter_surnameProvider);
		}
	}
	
	public void setZ_keyOfSisterOnBrother(String z_keyOfSisterOnBrother) {
		this.z_keyOfSisterOnBrother=z_keyOfSisterOnBrother;
	}
	
	public String toXmlReferenceString() {
		return "<Sister uid=\""+getUid() + "\"/>";
	}
	
	public String toXmlString() {
		StringBuilder sb = new StringBuilder();
		sb.append("<Sister ");
		sb.append("classUuid=\"Structures.uml@_dS3zAIhrEeK4s7QGypAJBA\" ");
		sb.append("className=\"org.opaeum.test.Sister\" ");
		sb.append("uid=\"" + this.getUid() + "\" ");
		if ( getName()!=null ) {
			sb.append("name=\""+ ModelFormatter.getInstance().formatString(getName())+"\" ");
		}
		if ( getDateOfBirth()!=null ) {
			sb.append("dateOfBirth=\""+ ModelFormatter.getInstance().formatDate(getDateOfBirth())+"\" ");
		}
		sb.append(">");
		sb.append("\n<brother propertyId=\"3115366053943004335\">");
		for ( Brother brother : getBrother() ) {
			sb.append("\n" + brother.toXmlReferenceString());
		}
		sb.append("\n</brother>");
		if ( getSurnameProviderHasDaughter_surnameProvider()==null ) {
			sb.append("\n<surnameProviderHasDaughter_surnameProvider/>");
		} else {
			sb.append("\n<surnameProviderHasDaughter_surnameProvider propertyId=\"6312646117027228013\">");
			sb.append("\n" + getSurnameProviderHasDaughter_surnameProvider().toXmlReferenceString());
			sb.append("\n</surnameProviderHasDaughter_surnameProvider>");
		}
		sb.append("\n<childHasRelation_godParent propertyId=\"8434948450232903107\">");
		for ( ChildHasRelation childHasRelation_godParent : getChildHasRelation_godParent() ) {
			sb.append("\n" + childHasRelation_godParent.toXmlReferenceString());
		}
		sb.append("\n</childHasRelation_godParent>");
		sb.append("\n<familyMemberHasRelation_relation propertyId=\"5718737559910777343\">");
		for ( FamilyMemberHasRelation familyMemberHasRelation_relation : getFamilyMemberHasRelation_relation() ) {
			sb.append("\n" + familyMemberHasRelation_relation.toXmlReferenceString());
		}
		sb.append("\n</familyMemberHasRelation_relation>");
		sb.append("\n</Sister>");
		return sb.toString();
	}
	
	public void z_internalAddToBrother(String name, Brother brother) {
		String key = ModelFormatter.getInstance().formatStringQualifier(name);
		if ( getBrother().contains(brother) ) {
			return;
		}
		brother.z_internalAddToName(name);
		this.brother.put(key.toString(),brother);
		brother.setZ_keyOfBrotherOnSister(key.toString());
	}
	
	public void z_internalAddToSurnameProviderHasDaughter_surnameProvider(SurnameProviderHasDaughter surnameProviderHasDaughter_surnameProvider) {
		if ( surnameProviderHasDaughter_surnameProvider.equals(getSurnameProviderHasDaughter_surnameProvider()) ) {
			return;
		}
		this.surnameProviderHasDaughter_surnameProvider=surnameProviderHasDaughter_surnameProvider;
	}
	
	public void z_internalRemoveFromBrother(String name, Brother brother) {
		String key = ModelFormatter.getInstance().formatStringQualifier(name);
		this.brother.remove(key.toString());
	}
	
	public void z_internalRemoveFromSurnameProviderHasDaughter_surnameProvider(SurnameProviderHasDaughter surnameProviderHasDaughter_surnameProvider) {
		if ( getSurnameProviderHasDaughter_surnameProvider()!=null && surnameProviderHasDaughter_surnameProvider!=null && surnameProviderHasDaughter_surnameProvider.equals(getSurnameProviderHasDaughter_surnameProvider()) ) {
			this.surnameProviderHasDaughter_surnameProvider=null;
			this.surnameProviderHasDaughter_surnameProvider=null;
		}
	}

}