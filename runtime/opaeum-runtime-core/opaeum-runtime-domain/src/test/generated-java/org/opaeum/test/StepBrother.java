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

@NumlMetaInfo(applicationIdentifier="structuretests",uuid="Structures.uml@_qIwuMIlZEeKhILqZBrW9Hg")
public class StepBrother implements StepChild, IEventGenerator, CompositionNode, Serializable {
	transient private Set<CancelledEvent> cancelledEvents = new HashSet<CancelledEvent>();
	protected FamilyStepChild familyStepChild_family;
	protected String name;
	transient private Set<OutgoingEvent> outgoingEvents = new HashSet<OutgoingEvent>();
	static final private long serialVersionUID = 3737954068926204439l;
	private String uid;

	/** This constructor is intended for easy initialization in unit tests
	 * 
	 * @param owningObject 
	 * @param name 
	 */
	public StepBrother(Family owningObject, String name) {
		setName(name);
		init(owningObject);
		addToOwningObject();
	}
	
	/** Default constructor for StepBrother
	 */
	public StepBrother() {
	}

	/** Call this method when you want to attach this object to the containment tree. Useful with transitive persistence
	 */
	public void addToOwningObject() {
		getFamily().z_internalAddToFamilyStepChild_stepChild(this.getName(),getFamilyStepChild_family());
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
	
	public void copyShallowState(StepBrother from, StepBrother to) {
		to.setName(from.getName());
	}
	
	public void copyState(StepBrother from, StepBrother to) {
		to.setName(from.getName());
	}
	
	public void createComponents() {
	}
	
	public boolean equals(Object other) {
		if ( other instanceof StepBrother ) {
			return other==this || ((StepBrother)other).getUid().equals(this.getUid());
		}
		return false;
	}
	
	public Set<CancelledEvent> getCancelledEvents() {
		return this.cancelledEvents;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=5616902939022633687l,opposite="stepChild",uuid="Structures.uml@_0vhRgYlZEeKhILqZBrW9Hg")
	public Family getFamily() {
		Family result = null;
		if ( this.familyStepChild_family!=null ) {
			result = this.familyStepChild_family.getFamily();
		}
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=true,opaeumId=7517388397186460345l,opposite="stepChild",uuid="Structures.uml@_0vhRgIlZEeKhILqZBrW9Hg")
	@NumlMetaInfo(uuid="Structures.uml@_o7BvwIlZEeKhILqZBrW9Hg@Structures.uml@_0vhRgIlZEeKhILqZBrW9Hg")
	public FamilyStepChild getFamilyStepChild_family() {
		FamilyStepChild result = this.familyStepChild_family;
		
		return result;
	}
	
	public FamilyStepChild getFamilyStepChild_familyFor(Family match) {
		if ( familyStepChild_family.getFamily().equals(match) ) {
			return familyStepChild_family;
		} else {
			return null;
		}
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=6393952343144368733l,uuid="Structures.uml@_vm81MIlZEeKhILqZBrW9Hg")
	@NumlMetaInfo(uuid="Structures.uml@_vm81MIlZEeKhILqZBrW9Hg")
	public String getName() {
		String result = this.name;
		
		return result;
	}
	
	public Set<OutgoingEvent> getOutgoingEvents() {
		return this.outgoingEvents;
	}
	
	public CompositionNode getOwningObject() {
		return getFamily();
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
		this.z_internalAddToFamilyStepChild_family(new FamilyStepChild((StepChild)this,(Family)owner));
	}
	
	public StepBrother makeCopy() {
		StepBrother result = new StepBrother();
		copyState((StepBrother)this,result);
		return result;
	}
	
	public StepBrother makeShallowCopy() {
		StepBrother result = new StepBrother();
		copyShallowState((StepBrother)this,result);
		return result;
	}
	
	public void markDeleted() {
		if ( getFamily()!=null ) {
			FamilyStepChild link = getFamilyStepChild_family();
			link.getFamily().z_internalRemoveFromFamilyStepChild_stepChild(this.getName(),link);
			link.markDeleted();
		}
		if ( getFamilyStepChild_family()!=null ) {
			getFamilyStepChild_family().markDeleted();
		}
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
	
	public void setFamily(Family family) {
		if ( this.getFamily()!=null ) {
			this.getFamily().z_internalRemoveFromFamilyStepChild_stepChild(this.getName(),getFamilyStepChild_family());
		}
		if ( family == null ) {
			this.z_internalRemoveFromFamilyStepChild_family(this.getFamilyStepChild_family());
		} else {
			FamilyStepChild familyStepChild_family = new FamilyStepChild((StepChild)this,(Family)family);
			if ( getName()==null ) {
				throw new IllegalStateException("The qualifying property 'name' must be set before adding a value to 'family'");
			}
			this.z_internalAddToFamilyStepChild_family(familyStepChild_family);
			family.z_internalAddToFamilyStepChild_stepChild(this.getName(),familyStepChild_family);
		}
	}
	
	public void setName(String name) {
		if ( getFamily()!=null && getName()!=null ) {
			getFamily().z_internalRemoveFromFamilyStepChild_stepChild(this.getName(),getFamilyStepChild_family());
		}
		if ( name == null ) {
			this.z_internalRemoveFromName(getName());
		} else {
			this.z_internalAddToName(name);
		}
		if ( getFamily()!=null && getName()!=null ) {
			getFamily().z_internalAddToFamilyStepChild_stepChild(this.getName(),getFamilyStepChild_family());
		}
	}
	
	public void setOutgoingEvents(Set<OutgoingEvent> outgoingEvents) {
		this.outgoingEvents=outgoingEvents;
	}
	
	public void setUid(String newUid) {
		this.uid=newUid;
	}
	
	public String toXmlReferenceString() {
		return "<StepBrother uid=\""+getUid() + "\"/>";
	}
	
	public String toXmlString() {
		StringBuilder sb = new StringBuilder();
		sb.append("<StepBrother ");
		sb.append("classUuid=\"Structures.uml@_qIwuMIlZEeKhILqZBrW9Hg\" ");
		sb.append("className=\"org.opaeum.test.StepBrother\" ");
		sb.append("uid=\"" + this.getUid() + "\" ");
		if ( getName()!=null ) {
			sb.append("name=\""+ ModelFormatter.getInstance().formatString(getName())+"\" ");
		}
		sb.append(">");
		sb.append("\n</StepBrother>");
		return sb.toString();
	}
	
	public void z_internalAddToFamilyStepChild_family(FamilyStepChild familyStepChild_family) {
		if ( familyStepChild_family.equals(getFamilyStepChild_family()) ) {
			return;
		}
		this.familyStepChild_family=familyStepChild_family;
	}
	
	public void z_internalAddToName(String name) {
		if ( name.equals(getName()) ) {
			return;
		}
		this.name=name;
	}
	
	public void z_internalRemoveFromFamilyStepChild_family(FamilyStepChild familyStepChild_family) {
		if ( getFamilyStepChild_family()!=null && familyStepChild_family!=null && familyStepChild_family.equals(getFamilyStepChild_family()) ) {
			this.familyStepChild_family=null;
			this.familyStepChild_family=null;
		}
	}
	
	public void z_internalRemoveFromName(String name) {
		if ( getName()!=null && name!=null && name.equals(getName()) ) {
			this.name=null;
			this.name=null;
		}
	}

}