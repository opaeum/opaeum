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

@NumlMetaInfo(applicationIdentifier="structuretests",uuid="Structures.uml@_bzlKgIhrEeK4s7QGypAJBA")
public class Brother extends Child implements IEventGenerator, CompositionNode, Serializable {
	transient private Set<CancelledEvent> cancelledEvents = new HashSet<CancelledEvent>();
	transient private Set<OutgoingEvent> outgoingEvents = new HashSet<OutgoingEvent>();
	static final private long serialVersionUID = 5473144541454217719l;
	protected SurnameProviderHasSon surnameProviderHasSon_surnameProvider;

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

	/** Call this method when you want to attach this object to the containment tree. Useful with transitive persistence
	 */
	public void addToOwningObject() {
		getFamily().z_internalAddToChild(this.getName(),(Brother)this);
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
	
	public void copyShallowState(Brother from, Brother to) {
		to.setName(from.getName());
	}
	
	public void copyState(Brother from, Brother to) {
		to.setName(from.getName());
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
		if ( getGodParent()!=null ) {
			getGodParent().z_internalRemoveFromChild(this);
		}
		if ( getSurnameProvider()!=null ) {
			getSurnameProvider().z_internalRemoveFromSurnameCarryingSon(this);
		}
		removeAllFromRelation(getRelation());
		if ( getFamily()!=null ) {
			getFamily().z_internalRemoveFromChild(this.getName(),this);
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
	
	public String toXmlReferenceString() {
		return "<Brother uid=\""+getUid() + "\"/>";
	}
	
	public String toXmlString() {
		StringBuilder sb = new StringBuilder();
		sb.append("<Brother ");
		sb.append("classUuid=\"Structures.uml@_bzlKgIhrEeK4s7QGypAJBA\" ");
		sb.append("className=\"model.Brother\" ");
		sb.append("uid=\"" + this.getUid() + "\" ");
		if ( getName()!=null ) {
			sb.append("name=\""+ ModelFormatter.getInstance().formatString(getName())+"\" ");
		}
		sb.append(">");
		if ( getSurnameProviderHasSon_surnameProvider()==null ) {
			sb.append("\n<surnameProviderHasSon_surnameProvider/>");
		} else {
			sb.append("\n<surnameProviderHasSon_surnameProvider propertyId=\"4285853828966048752\">");
			sb.append("\n" + getSurnameProviderHasSon_surnameProvider().toXmlReferenceString());
			sb.append("\n</surnameProviderHasSon_surnameProvider>");
		}
		sb.append("\n<familyMemberHasRelation_relation propertyId=\"5718737559910777343\">");
		for ( FamilyMemberHasRelation familyMemberHasRelation_relation : getFamilyMemberHasRelation_relation() ) {
			sb.append("\n" + familyMemberHasRelation_relation.toXmlReferenceString());
		}
		sb.append("\n</familyMemberHasRelation_relation>");
		sb.append("\n</Brother>");
		return sb.toString();
	}
	
	public void z_internalAddToSurnameProviderHasSon_surnameProvider(SurnameProviderHasSon surnameProviderHasSon_surnameProvider) {
		if ( surnameProviderHasSon_surnameProvider.equals(getSurnameProviderHasSon_surnameProvider()) ) {
			return;
		}
		this.surnameProviderHasSon_surnameProvider=surnameProviderHasSon_surnameProvider;
	}
	
	public void z_internalRemoveFromSurnameProviderHasSon_surnameProvider(SurnameProviderHasSon surnameProviderHasSon_surnameProvider) {
		if ( getSurnameProviderHasSon_surnameProvider()!=null && surnameProviderHasSon_surnameProvider!=null && surnameProviderHasSon_surnameProvider.equals(getSurnameProviderHasSon_surnameProvider()) ) {
			this.surnameProviderHasSon_surnameProvider=null;
			this.surnameProviderHasSon_surnameProvider=null;
		}
	}

}