package org.opaeum.test;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

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

@NumlMetaInfo(applicationIdentifier="structuretests",uuid="Structures.uml@_tLdi4IiYEeKb2pFQKBBPKw")
public class Father implements SurnameProvider, IEventGenerator, CompositionNode, Serializable {
	transient private Set<CancelledEvent> cancelledEvents = new HashSet<CancelledEvent>();
	protected Map<String, Child> child = new HashMap<String,Child>();
	protected Family family;
	protected Marriage marriage_spouse;
	transient private Set<OutgoingEvent> outgoingEvents = new HashSet<OutgoingEvent>();
	static final private long serialVersionUID = 3913937104705735364l;
	protected Map<String, SurnameProviderHasDaughter> surnameProviderHasDaughter_surnameCarryingDaughter = new HashMap<String,SurnameProviderHasDaughter>();
	protected Set<SurnameProviderHasSon> surnameProviderHasSon_surnameCarryingSon = new HashSet<SurnameProviderHasSon>();
	private String uid;
	protected Mother wife;

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

	public void addAllToChild(Set<Child> child) {
		for ( Child o : child ) {
			addToChild(o.getName(),o);
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
			if ( child.getFather()!=null ) {
				child.getFather().removeFromChild(child.getName(),child);
			}
			child.z_internalAddToFather(this);
		}
		z_internalAddToChild(name,child);
	}
	
	/** Call this method when you want to attach this object to the containment tree. Useful with transitive persistence
	 */
	public void addToOwningObject() {
		getFamily().z_internalAddToFather((Father)this);
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
		}
	}
	
	public void clearChild() {
		Set<Child> tmp = new HashSet<Child>(getChild());
		for ( Child o : tmp ) {
			removeFromChild(o.getName(),o);
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
	
	public void copyShallowState(Father from, Father to) {
		if ( from.getWife()!=null ) {
			to.z_internalAddToWife(from.getWife().makeShallowCopy());
		}
		if ( from.getMarriage_spouse()!=null ) {
			to.z_internalAddToMarriage_spouse(from.getMarriage_spouse().makeShallowCopy());
		}
	}
	
	public void copyState(Father from, Father to) {
		if ( from.getWife()!=null ) {
			to.z_internalAddToWife(from.getWife().makeCopy());
		}
		if ( from.getMarriage_spouse()!=null ) {
			to.z_internalAddToMarriage_spouse(from.getMarriage_spouse().makeCopy());
		}
	}
	
	public void createComponents() {
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
	
	public Child getChild(String name) {
		Child result = null;
		String key = ModelFormatter.getInstance().formatStringQualifier(name);
		result=this.child.get(key.toString());
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=3140556702662193093l,opposite="father",uuid="Structures.uml@_KNfFoIsgEeKWvpYIRX9T4g")
	@NumlMetaInfo(uuid="Structures.uml@_KNfFoIsgEeKWvpYIRX9T4g")
	public Set<Child> getChild() {
		Set result = new HashSet<Child>(this.child.values());
		
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=7807032952468544404l,opposite="father",uuid="Structures.uml@_u4d3QYiYEeKb2pFQKBBPKw")
	@NumlMetaInfo(uuid="Structures.uml@_u4d3QYiYEeKb2pFQKBBPKw")
	public Family getFamily() {
		Family result = this.family;
		
		return result;
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
	
	public Set<OutgoingEvent> getOutgoingEvents() {
		return this.outgoingEvents;
	}
	
	public CompositionNode getOwningObject() {
		return getFamily();
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=112985228842363744l,opposite="surnameProvider",uuid="Structures.uml@_fz0rsYn-EeKv0PcdrJJtzg")
	public Spouse getSpouse() {
		Spouse result = null;
		if ( this.marriage_spouse!=null ) {
			result = this.marriage_spouse.getSpouse();
		}
		return result;
	}
	
	public Sister getSurnameCarryingDaughter(String name) {
		Sister result = null;
		String key = ModelFormatter.getInstance().formatStringQualifier(name);
		SurnameProviderHasDaughter link = this.surnameProviderHasDaughter_surnameCarryingDaughter.get(key.toString());
		result= link==null || link.getSurnameCarryingDaughter()==null?null:link.getSurnameCarryingDaughter();
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
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=2763641334812734820l,opposite="husband",uuid="Structures.uml@_7cAGwIr9EeKfXcW1LrVoAA")
	@NumlMetaInfo(uuid="Structures.uml@_7cAGwIr9EeKfXcW1LrVoAA")
	public Mother getWife() {
		Mother result = this.wife;
		
		return result;
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
		if ( getWife()!=null ) {
			getWife().z_internalRemoveFromHusband(this);
		}
		removeAllFromChild(getChild());
		for ( SurnameProviderHasSon link : new HashSet<SurnameProviderHasSon>(getSurnameProviderHasSon_surnameCarryingSon()) ) {
			link.getSurnameCarryingSon().z_internalRemoveFromSurnameProviderHasSon_surnameProvider(link);
		}
		for ( SurnameProviderHasDaughter link : new HashSet<SurnameProviderHasDaughter>(getSurnameProviderHasDaughter_surnameCarryingDaughter()) ) {
			link.getSurnameCarryingDaughter().z_internalRemoveFromSurnameProviderHasDaughter_surnameProvider(link);
		}
		if ( getSpouse()!=null ) {
			getSpouse().z_internalRemoveFromMarriage_surnameProvider(getMarriage_spouse());
		}
		if ( getFamily()!=null ) {
			getFamily().z_internalRemoveFromFather(this);
		}
		if ( getMarriage_spouse()!=null ) {
			getMarriage_spouse().z_internalRemoveFromSurnameProvider(this);
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
		}
	}
	
	public void removeAllFromChild(Set<Child> child) {
		Set<Child> tmp = new HashSet<Child>(child);
		for ( Child o : tmp ) {
			removeFromChild(o.getName(),o);
		}
	}
	
	public void removeAllFromSurnameCarryingDaughter(Set<Sister> surnameCarryingDaughter) {
		Set<Sister> tmp = new HashSet<Sister>(surnameCarryingDaughter);
		for ( Sister o : tmp ) {
			removeFromSurnameCarryingDaughter(o.getName(),o);
		}
	}
	
	public void removeAllFromSurnameCarryingSon(Set<Brother> surnameCarryingSon) {
		Set<Brother> tmp = new HashSet<Brother>(surnameCarryingSon);
		for ( Brother o : tmp ) {
			removeFromSurnameCarryingSon(o);
		}
	}
	
	public void removeFromChild(String name, Child child) {
		if ( child!=null ) {
			child.z_internalRemoveFromFather(this);
			z_internalRemoveFromChild(child.getName(),child);
		}
	}
	
	public void removeFromOwningObject() {
		this.markDeleted();
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
		if ( oldValue !=null && !oldValue.equals(spouse) ) {
			getSpouse().z_internalRemoveFromMarriage_surnameProvider(getMarriage_spouse());
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
	
	public void setWife(Mother wife) {
		Mother oldValue = this.getWife();
		if ( oldValue==null ) {
			if ( wife!=null ) {
				Father oldOther = (Father)wife.getHusband();
				wife.z_internalRemoveFromHusband(oldOther);
				if ( oldOther != null ) {
					oldOther.z_internalRemoveFromWife(wife);
				}
				wife.z_internalAddToHusband((Father)this);
			}
			this.z_internalAddToWife(wife);
		} else {
			if ( !oldValue.equals(wife) ) {
				oldValue.z_internalRemoveFromHusband(this);
				z_internalRemoveFromWife(oldValue);
				if ( wife!=null ) {
					Father oldOther = (Father)wife.getHusband();
					wife.z_internalRemoveFromHusband(oldOther);
					if ( oldOther != null ) {
						oldOther.z_internalRemoveFromWife(wife);
					}
					wife.z_internalAddToHusband((Father)this);
				}
				this.z_internalAddToWife(wife);
			}
		}
	}
	
	public String toXmlReferenceString() {
		return "<Father uid=\""+getUid() + "\"/>";
	}
	
	public String toXmlString() {
		StringBuilder sb = new StringBuilder();
		sb.append("<Father ");
		sb.append("classUuid=\"Structures.uml@_tLdi4IiYEeKb2pFQKBBPKw\" ");
		sb.append("className=\"org.opaeum.test.Father\" ");
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
		if ( getMarriage_spouse()==null ) {
			sb.append("\n<marriage_spouse/>");
		} else {
			sb.append("\n<marriage_spouse propertyId=\"599591342263275344\">");
			sb.append("\n" + getMarriage_spouse().toXmlReferenceString());
			sb.append("\n</marriage_spouse>");
		}
		sb.append("\n</Father>");
		return sb.toString();
	}
	
	public void z_internalAddToChild(String name, Child child) {
		String key = ModelFormatter.getInstance().formatStringQualifier(name);
		if ( getChild().contains(child) ) {
			return;
		}
		child.z_internalAddToName(name);
		this.child.put(key.toString(),child);
		child.setZ_keyOfChildOnFather(key.toString());
	}
	
	public void z_internalAddToFamily(Family family) {
		if ( family.equals(getFamily()) ) {
			return;
		}
		this.family=family;
	}
	
	public void z_internalAddToMarriage_spouse(Marriage marriage_spouse) {
		if ( marriage_spouse.equals(getMarriage_spouse()) ) {
			return;
		}
		this.marriage_spouse=marriage_spouse;
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
	
	public void z_internalAddToWife(Mother wife) {
		if ( wife.equals(getWife()) ) {
			return;
		}
		this.wife=wife;
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
	
	public void z_internalRemoveFromMarriage_spouse(Marriage marriage_spouse) {
		if ( getMarriage_spouse()!=null && marriage_spouse!=null && marriage_spouse.equals(getMarriage_spouse()) ) {
			this.marriage_spouse=null;
			this.marriage_spouse=null;
		}
	}
	
	public void z_internalRemoveFromSurnameProviderHasDaughter_surnameCarryingDaughter(String name, SurnameProviderHasDaughter surnameProviderHasDaughter_surnameCarryingDaughter) {
		String key = ModelFormatter.getInstance().formatStringQualifier(name);
		this.surnameProviderHasDaughter_surnameCarryingDaughter.remove(key.toString());
	}
	
	public void z_internalRemoveFromSurnameProviderHasSon_surnameCarryingSon(SurnameProviderHasSon surnameProviderHasSon_surnameCarryingSon) {
		this.surnameProviderHasSon_surnameCarryingSon.remove(surnameProviderHasSon_surnameCarryingSon);
	}
	
	public void z_internalRemoveFromWife(Mother wife) {
		if ( getWife()!=null && wife!=null && wife.equals(getWife()) ) {
			this.wife=null;
			this.wife=null;
		}
	}

}