package org.opaeum.test;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
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
import org.opaeum.runtime.strategy.DateStrategyFactory;
import org.opaeum.test.util.ModelFormatter;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

@NumlMetaInfo(applicationIdentifier="structuretests",uuid="Structures.uml@_fz0rsIn-EeKv0PcdrJJtzg")
public class Marriage implements IEventGenerator, CompositionNode, Serializable {
	transient private Set<CancelledEvent> cancelledEvents = new HashSet<CancelledEvent>();
	protected Date initiationDate;
	transient private Set<OutgoingEvent> outgoingEvents = new HashSet<OutgoingEvent>();
	static final private long serialVersionUID = 7663862196920060032l;
	protected Spouse spouse;
	protected SurnameProvider surnameProvider;
	private String uid;

	/** Constructor for Marriage
	 * 
	 * @param end2 
	 * @param end1 
	 */
	public Marriage(Spouse end2, SurnameProvider end1) {
		this.z_internalAddToSurnameProvider(end1);
		this.z_internalAddToSpouse(end2);
	}
	
	/** Constructor for Marriage
	 * 
	 * @param end1 
	 * @param end2 
	 */
	public Marriage(SurnameProvider end1, Spouse end2) {
		this.z_internalAddToSurnameProvider(end1);
		this.z_internalAddToSpouse(end2);
	}
	
	/** Default constructor for Marriage
	 */
	public Marriage() {
	}

	/** Call this method when you want to attach this object to the containment tree. Useful with transitive persistence
	 */
	public void addToOwningObject() {
	}
	
	public void buildTreeFromXml(Element xml, Map<String, Object> map) {
		setUid(xml.getAttribute("uid"));
		if ( xml.getAttribute("initiationDate").length()>0 ) {
			setInitiationDate(ModelFormatter.getInstance().parseDate(xml.getAttribute("initiationDate")));
		}
		NodeList propertyNodes = xml.getChildNodes();
		int i = 0;
		while ( i<propertyNodes.getLength() ) {
			Node currentPropertyNode = propertyNodes.item(i++);
		
		}
	}
	
	public void clear() {
		this.z_internalRemoveFromSpouse(getSpouse());
		this.z_internalRemoveFromSurnameProvider(getSurnameProvider());
	}
	
	public void copyShallowState(Marriage from, Marriage to) {
		to.setInitiationDate(from.getInitiationDate());
	}
	
	public void copyState(Marriage from, Marriage to) {
		to.setInitiationDate(from.getInitiationDate());
	}
	
	public void createComponents() {
	}
	
	public boolean equals(Object other) {
		if ( other instanceof Marriage ) {
			return other==this || ((Marriage)other).getUid().equals(this.getUid());
		}
		return false;
	}
	
	public Set<CancelledEvent> getCancelledEvents() {
		return this.cancelledEvents;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=3770870485039737366l,strategyFactory=DateStrategyFactory.class,uuid="Structures.uml@_k3fZ4In-EeKv0PcdrJJtzg")
	@NumlMetaInfo(uuid="Structures.uml@_k3fZ4In-EeKv0PcdrJJtzg")
	public Date getInitiationDate() {
		Date result = this.initiationDate;
		
		return result;
	}
	
	public Set<OutgoingEvent> getOutgoingEvents() {
		return this.outgoingEvents;
	}
	
	public CompositionNode getOwningObject() {
		return getSpouse();
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=7470639403134265280l,opposite="marriage_surnameProvider",uuid="Structures.uml@_fz0rsYn-EeKv0PcdrJJtzg")
	@NumlMetaInfo(uuid="Structures.uml@_fz0rsIn-EeKv0PcdrJJtzg@Structures.uml@_fz0rsYn-EeKv0PcdrJJtzg")
	public Spouse getSpouse() {
		Spouse result = this.spouse;
		
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=4791530959909380894l,opposite="marriage_spouse",uuid="Structures.uml@_fz0rtIn-EeKv0PcdrJJtzg")
	@NumlMetaInfo(uuid="Structures.uml@_fz0rsIn-EeKv0PcdrJJtzg@Structures.uml@_fz0rtIn-EeKv0PcdrJJtzg")
	public SurnameProvider getSurnameProvider() {
		SurnameProvider result = this.surnameProvider;
		
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
		this.z_internalAddToSpouse((Spouse)owner);
	}
	
	public Marriage makeCopy() {
		Marriage result = new Marriage();
		copyState((Marriage)this,result);
		return result;
	}
	
	public Marriage makeShallowCopy() {
		Marriage result = new Marriage();
		copyShallowState((Marriage)this,result);
		return result;
	}
	
	public void markDeleted() {
	}
	
	public void populateReferencesFromXml(Element xml, Map<String, Object> map) {
		NodeList propertyNodes = xml.getChildNodes();
		int i = 0;
		while ( i<propertyNodes.getLength() ) {
			Node currentPropertyNode = propertyNodes.item(i++);
		
		}
	}
	
	public void removeFromOwningObject() {
		this.markDeleted();
	}
	
	public void setCancelledEvents(Set<CancelledEvent> cancelledEvents) {
		this.cancelledEvents=cancelledEvents;
	}
	
	public void setInitiationDate(Date initiationDate) {
		if ( initiationDate == null ) {
			this.z_internalRemoveFromInitiationDate(getInitiationDate());
		} else {
			this.z_internalAddToInitiationDate(initiationDate);
		}
	}
	
	public void setOutgoingEvents(Set<OutgoingEvent> outgoingEvents) {
		this.outgoingEvents=outgoingEvents;
	}
	
	public void setUid(String newUid) {
		this.uid=newUid;
	}
	
	public String toXmlReferenceString() {
		return "<Marriage uid=\""+getUid() + "\"/>";
	}
	
	public String toXmlString() {
		StringBuilder sb = new StringBuilder();
		sb.append("<Marriage ");
		sb.append("classUuid=\"Structures.uml@_fz0rsIn-EeKv0PcdrJJtzg\" ");
		sb.append("className=\"org.opaeum.test.Marriage\" ");
		sb.append("uid=\"" + this.getUid() + "\" ");
		if ( getInitiationDate()!=null ) {
			sb.append("initiationDate=\""+ ModelFormatter.getInstance().formatDate(getInitiationDate())+"\" ");
		}
		sb.append(">");
		sb.append("\n</Marriage>");
		return sb.toString();
	}
	
	public void z_internalAddToInitiationDate(Date initiationDate) {
		if ( initiationDate.equals(getInitiationDate()) ) {
			return;
		}
		this.initiationDate=initiationDate;
	}
	
	public void z_internalAddToSpouse(Spouse spouse) {
		if ( spouse.equals(getSpouse()) ) {
			return;
		}
		this.spouse=spouse;
	}
	
	public void z_internalAddToSurnameProvider(SurnameProvider surnameProvider) {
		if ( surnameProvider.equals(getSurnameProvider()) ) {
			return;
		}
		this.surnameProvider=surnameProvider;
	}
	
	public void z_internalRemoveFromInitiationDate(Date initiationDate) {
		if ( getInitiationDate()!=null && initiationDate!=null && initiationDate.equals(getInitiationDate()) ) {
			this.initiationDate=null;
			this.initiationDate=null;
		}
	}
	
	public void z_internalRemoveFromSpouse(Spouse spouse) {
		if ( getSpouse()!=null && spouse!=null && spouse.equals(getSpouse()) ) {
			this.spouse=null;
			this.spouse=null;
		}
	}
	
	public void z_internalRemoveFromSurnameProvider(SurnameProvider surnameProvider) {
		if ( getSurnameProvider()!=null && surnameProvider!=null && surnameProvider.equals(getSurnameProvider()) ) {
			this.surnameProvider=null;
			this.surnameProvider=null;
		}
	}

}