package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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

@NumlMetaInfo(applicationIdentifier="structuretests",uuid="Structures.uml@_dS3zAIhrEeK4s7QGypAJBA")
public class Sister extends Child implements IEventGenerator, CompositionNode, Serializable {
	transient private Set<CancelledEvent> cancelledEvents = new HashSet<CancelledEvent>();
	transient private Set<OutgoingEvent> outgoingEvents = new HashSet<OutgoingEvent>();
	static final private long serialVersionUID = 8714499026471153695l;
	protected SurnameProviderHasDaughter surnameProviderHasDaughter_surnameProvider;

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
		NodeList propertyNodes = xml.getChildNodes();
		int i = 0;
		while ( i<propertyNodes.getLength() ) {
			Node currentPropertyNode = propertyNodes.item(i++);
		
		}
	}
	
	public void copyShallowState(Sister from, Sister to) {
		to.setName(from.getName());
	}
	
	public void copyState(Sister from, Sister to) {
		to.setName(from.getName());
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
		if ( getGodParent()!=null ) {
			getGodParent().z_internalRemoveFromChild(this);
		}
		if ( getSurnameProvider()!=null ) {
			getSurnameProvider().z_internalRemoveFromSurnameCarryingDaughter(this);
		}
		removeAllFromRelation(getRelation());
		if ( getFamily()!=null ) {
			getFamily().z_internalRemoveFromChild(this.getName(),this);
		}
		if ( getSurnameProviderHasDaughter_surnameProvider()!=null ) {
			getSurnameProviderHasDaughter_surnameProvider().z_internalRemoveFromSurnameCarryingDaughter(this);
		}
	}
	
	public void populateReferencesFromXml(Element xml, Map<String, Object> map) {
		NodeList propertyNodes = xml.getChildNodes();
		int i = 0;
		while ( i<propertyNodes.getLength() ) {
			Node currentPropertyNode = propertyNodes.item(i++);
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
		}
	}
	
	public void removeFromOwningObject() {
		this.markDeleted();
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
	
	public String toXmlReferenceString() {
		return "<Sister uid=\""+getUid() + "\"/>";
	}
	
	public String toXmlString() {
		StringBuilder sb = new StringBuilder();
		sb.append("<Sister ");
		sb.append("classUuid=\"Structures.uml@_dS3zAIhrEeK4s7QGypAJBA\" ");
		sb.append("className=\"model.Sister\" ");
		sb.append("uid=\"" + this.getUid() + "\" ");
		if ( getName()!=null ) {
			sb.append("name=\""+ ModelFormatter.getInstance().formatString(getName())+"\" ");
		}
		sb.append(">");
		if ( getSurnameProviderHasDaughter_surnameProvider()==null ) {
			sb.append("\n<surnameProviderHasDaughter_surnameProvider/>");
		} else {
			sb.append("\n<surnameProviderHasDaughter_surnameProvider propertyId=\"6312646117027228013\">");
			sb.append("\n" + getSurnameProviderHasDaughter_surnameProvider().toXmlReferenceString());
			sb.append("\n</surnameProviderHasDaughter_surnameProvider>");
		}
		sb.append("\n<familyMemberHasRelation_relation propertyId=\"5718737559910777343\">");
		for ( FamilyMemberHasRelation familyMemberHasRelation_relation : getFamilyMemberHasRelation_relation() ) {
			sb.append("\n" + familyMemberHasRelation_relation.toXmlReferenceString());
		}
		sb.append("\n</familyMemberHasRelation_relation>");
		sb.append("\n</Sister>");
		return sb.toString();
	}
	
	public void z_internalAddToSurnameProviderHasDaughter_surnameProvider(SurnameProviderHasDaughter surnameProviderHasDaughter_surnameProvider) {
		if ( surnameProviderHasDaughter_surnameProvider.equals(getSurnameProviderHasDaughter_surnameProvider()) ) {
			return;
		}
		this.surnameProviderHasDaughter_surnameProvider=surnameProviderHasDaughter_surnameProvider;
	}
	
	public void z_internalRemoveFromSurnameProviderHasDaughter_surnameProvider(SurnameProviderHasDaughter surnameProviderHasDaughter_surnameProvider) {
		if ( getSurnameProviderHasDaughter_surnameProvider()!=null && surnameProviderHasDaughter_surnameProvider!=null && surnameProviderHasDaughter_surnameProvider.equals(getSurnameProviderHasDaughter_surnameProvider()) ) {
			this.surnameProviderHasDaughter_surnameProvider=null;
			this.surnameProviderHasDaughter_surnameProvider=null;
		}
	}

}