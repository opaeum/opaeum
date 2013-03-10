package model;

import java.io.Serializable;
import java.util.ArrayList;
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

@NumlMetaInfo(applicationIdentifier="structuretests",uuid="Structures.uml@_tLdi4IiYEeKb2pFQKBBPKw")
public class Father implements IEventGenerator, CompositionNode, SurnameProvider, Serializable {
	transient private Set<CancelledEvent> cancelledEvents = new HashSet<CancelledEvent>();
	protected Family family;
	transient private Set<OutgoingEvent> outgoingEvents = new HashSet<OutgoingEvent>();
	static final private long serialVersionUID = 3913937104705735364l;
	protected Spouse spouse;
	protected Set<SurnameProviderHasDaughter> surnameProviderHasDaughter_surnameCarryingDaughter = new HashSet<SurnameProviderHasDaughter>();
	protected Set<SurnameProviderHasSon> surnameProviderHasSon_surnameCarryingSon = new HashSet<SurnameProviderHasSon>();
	private String uid;

	/** This constructor is intended for easy initialization in unit tests
	 * 
	 * @param owningObject 
	 */
	public Father(Family owningObject) {
		init(owningObject);
		addToOwningObject();
	}
	
	/** Default constructor for Father
	 */
	public Father() {
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
		getFamily().z_internalAddToFather((Father)this);
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
	
	public void copyShallowState(Father from, Father to) {
	}
	
	public void copyState(Father from, Father to) {
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
		if ( other instanceof Father ) {
			return other==this || ((Father)other).getUid().equals(this.getUid());
		}
		return false;
	}
	
	public Set<CancelledEvent> getCancelledEvents() {
		return this.cancelledEvents;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=7807032952468544404l,opposite="father",uuid="Structures.uml@_u4d3QYiYEeKb2pFQKBBPKw")
	@NumlMetaInfo(uuid="Structures.uml@_u4d3QYiYEeKb2pFQKBBPKw")
	public Family getFamily() {
		Family result = this.family;
		
		return result;
	}
	
	public Set<OutgoingEvent> getOutgoingEvents() {
		return this.outgoingEvents;
	}
	
	public CompositionNode getOwningObject() {
		return getFamily();
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=4841945505933354426l,opposite="surnameProvider",uuid="Structures.uml@_0-KlMYjPEeKq68owPnlvHg")
	@NumlMetaInfo(uuid="Structures.uml@_0-KlMYjPEeKq68owPnlvHg")
	public Spouse getSpouse() {
		Spouse result = this.spouse;
		
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
	
	public Father makeCopy() {
		Father result = new Father();
		copyState((Father)this,result);
		return result;
	}
	
	public Father makeShallowCopy() {
		Father result = new Father();
		copyShallowState((Father)this,result);
		return result;
	}
	
	public void markDeleted() {
		if ( getSpouse()!=null ) {
			getSpouse().z_internalRemoveFromSurnameProvider(this);
		}
		if ( getFamily()!=null ) {
			getFamily().z_internalRemoveFromFather(this);
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
				Father oldOther = (Father)family.getFather();
				family.z_internalRemoveFromFather(oldOther);
				if ( oldOther != null ) {
					oldOther.z_internalRemoveFromFamily(family);
				}
				family.z_internalAddToFather((Father)this);
			}
			this.z_internalAddToFamily(family);
		} else {
			if ( !oldValue.equals(family) ) {
				oldValue.z_internalRemoveFromFather(this);
				z_internalRemoveFromFamily(oldValue);
				if ( family!=null ) {
					Father oldOther = (Father)family.getFather();
					family.z_internalRemoveFromFather(oldOther);
					if ( oldOther != null ) {
						oldOther.z_internalRemoveFromFamily(family);
					}
					family.z_internalAddToFather((Father)this);
				}
				this.z_internalAddToFamily(family);
			}
		}
	}
	
	public void setOutgoingEvents(Set<OutgoingEvent> outgoingEvents) {
		this.outgoingEvents=outgoingEvents;
	}
	
	public void setSpouse(Spouse spouse) {
		Spouse oldValue = this.getSpouse();
		if ( oldValue==null ) {
			if ( spouse!=null ) {
				Father oldOther = (Father)spouse.getSurnameProvider();
				spouse.z_internalRemoveFromSurnameProvider(oldOther);
				if ( oldOther != null ) {
					oldOther.z_internalRemoveFromSpouse(spouse);
				}
				spouse.z_internalAddToSurnameProvider((Father)this);
			}
			this.z_internalAddToSpouse(spouse);
		} else {
			if ( !oldValue.equals(spouse) ) {
				oldValue.z_internalRemoveFromSurnameProvider(this);
				z_internalRemoveFromSpouse(oldValue);
				if ( spouse!=null ) {
					Father oldOther = (Father)spouse.getSurnameProvider();
					spouse.z_internalRemoveFromSurnameProvider(oldOther);
					if ( oldOther != null ) {
						oldOther.z_internalRemoveFromSpouse(spouse);
					}
					spouse.z_internalAddToSurnameProvider((Father)this);
				}
				this.z_internalAddToSpouse(spouse);
			}
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
	
	public void setUid(String newUid) {
		this.uid=newUid;
	}
	
	public String toXmlReferenceString() {
		return "<Father uid=\""+getUid() + "\"/>";
	}
	
	public String toXmlString() {
		StringBuilder sb = new StringBuilder();
		sb.append("<Father ");
		sb.append("classUuid=\"Structures.uml@_tLdi4IiYEeKb2pFQKBBPKw\" ");
		sb.append("className=\"model.Father\" ");
		sb.append("uid=\"" + this.getUid() + "\" ");
		sb.append(">");
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
		sb.append("\n</Father>");
		return sb.toString();
	}
	
	public void z_internalAddToFamily(Family family) {
		if ( family.equals(getFamily()) ) {
			return;
		}
		this.family=family;
	}
	
	public void z_internalAddToSpouse(Spouse spouse) {
		if ( spouse.equals(getSpouse()) ) {
			return;
		}
		this.spouse=spouse;
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
	
	public void z_internalRemoveFromSpouse(Spouse spouse) {
		if ( getSpouse()!=null && spouse!=null && spouse.equals(getSpouse()) ) {
			this.spouse=null;
			this.spouse=null;
		}
	}
	
	public void z_internalRemoveFromSurnameProviderHasDaughter_surnameCarryingDaughter(SurnameProviderHasDaughter surnameProviderHasDaughter_surnameCarryingDaughter) {
		this.surnameProviderHasDaughter_surnameCarryingDaughter.remove(surnameProviderHasDaughter_surnameCarryingDaughter);
	}
	
	public void z_internalRemoveFromSurnameProviderHasSon_surnameCarryingSon(SurnameProviderHasSon surnameProviderHasSon_surnameCarryingSon) {
		this.surnameProviderHasSon_surnameCarryingSon.remove(surnameProviderHasSon_surnameCarryingSon);
	}

}