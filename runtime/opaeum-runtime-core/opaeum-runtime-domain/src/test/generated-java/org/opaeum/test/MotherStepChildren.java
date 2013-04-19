package org.opaeum.test;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;
import java.util.UUID;

import org.opaeum.annotation.NumlMetaInfo;
import org.opaeum.annotation.PropertyMetaInfo;
import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.runtime.domain.IntrospectionUtil;
import org.opaeum.runtime.environment.Environment;
import org.opaeum.test.util.ModelFormatter;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

@NumlMetaInfo(applicationIdentifier="structuretests",uuid="Structures.uml@_ncdcsI1OEeKgGLBcRSZFfw")
public class MotherStepChildren implements CompositionNode, Serializable {
	static final private long serialVersionUID = 7831556271646916577l;
	protected StepChild stepChild;
	protected Mother stepMother;
	private String uid;

	/** Constructor for MotherStepChildren
	 * 
	 * @param end1 
	 * @param end2 
	 */
	public MotherStepChildren(Mother end1, StepChild end2) {
		this.z_internalAddToStepMother(end1);
		this.z_internalAddToStepChild(end2);
	}
	
	/** Constructor for MotherStepChildren
	 * 
	 * @param end2 
	 * @param end1 
	 */
	public MotherStepChildren(StepChild end2, Mother end1) {
		this.z_internalAddToStepMother(end1);
		this.z_internalAddToStepChild(end2);
	}
	
	/** Default constructor for MotherStepChildren
	 */
	public MotherStepChildren() {
	}

	/** Call this method when you want to attach this object to the containment tree. Useful with transitive persistence
	 */
	public void addToOwningObject() {
	}
	
	public void buildTreeFromXml(Element xml, Map<String, Object> map) {
		setUid(xml.getAttribute("uid"));
		NodeList propertyNodes = xml.getChildNodes();
		int i = 0;
		while ( i<propertyNodes.getLength() ) {
			Node currentPropertyNode = propertyNodes.item(i++);
		
		}
	}
	
	public void clear() {
		this.z_internalRemoveFromStepChild(getStepChild());
		this.z_internalRemoveFromStepMother(getStepMother());
	}
	
	public void createComponents() {
	}
	
	public boolean equals(Object other) {
		if ( other instanceof MotherStepChildren ) {
			return other==this || ((MotherStepChildren)other).getUid().equals(this.getUid());
		}
		return false;
	}
	
	public CompositionNode getOwningObject() {
		return getStepChild();
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=6473770983472549223l,opposite="motherStepChildren_stepMother",uuid="Structures.uml@_ncZyUI1OEeKgGLBcRSZFfw")
	@NumlMetaInfo(uuid="Structures.uml@_ncdcsI1OEeKgGLBcRSZFfw@Structures.uml@_ncZyUI1OEeKgGLBcRSZFfw")
	public StepChild getStepChild() {
		StepChild result = this.stepChild;
		
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=6136886502272554435l,opposite="motherStepChildren_stepChild",uuid="Structures.uml@_ncdcsY1OEeKgGLBcRSZFfw")
	@NumlMetaInfo(uuid="Structures.uml@_ncdcsI1OEeKgGLBcRSZFfw@Structures.uml@_ncdcsY1OEeKgGLBcRSZFfw")
	public Mother getStepMother() {
		Mother result = this.stepMother;
		
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
		this.z_internalAddToStepChild((StepChild)owner);
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
	
	public void setUid(String newUid) {
		this.uid=newUid;
	}
	
	public String toXmlReferenceString() {
		return "<MotherStepChildren uid=\""+getUid() + "\"/>";
	}
	
	public String toXmlString() {
		StringBuilder sb = new StringBuilder();
		sb.append("<MotherStepChildren ");
		sb.append("classUuid=\"Structures.uml@_ncdcsI1OEeKgGLBcRSZFfw\" ");
		sb.append("className=\"org.opaeum.test.MotherStepChildren\" ");
		sb.append("uid=\"" + this.getUid() + "\" ");
		sb.append(">");
		sb.append("\n</MotherStepChildren>");
		return sb.toString();
	}
	
	public void z_internalAddToStepChild(StepChild stepChild) {
		if ( stepChild.equals(getStepChild()) ) {
			return;
		}
		this.stepChild=stepChild;
	}
	
	public void z_internalAddToStepMother(Mother stepMother) {
		if ( stepMother.equals(getStepMother()) ) {
			return;
		}
		this.stepMother=stepMother;
	}
	
	public void z_internalRemoveFromStepChild(StepChild stepChild) {
		if ( getStepChild()!=null && stepChild!=null && stepChild.equals(getStepChild()) ) {
			this.stepChild=null;
			this.stepChild=null;
		}
	}
	
	public void z_internalRemoveFromStepMother(Mother stepMother) {
		if ( getStepMother()!=null && stepMother!=null && stepMother.equals(getStepMother()) ) {
			this.stepMother=null;
			this.stepMother=null;
		}
	}

}