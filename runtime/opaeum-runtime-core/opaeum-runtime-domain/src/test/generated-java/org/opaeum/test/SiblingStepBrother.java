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

@NumlMetaInfo(applicationIdentifier="structuretests",uuid="Structures.uml@_1X1ycI1OEeKgGLBcRSZFfw")
public class SiblingStepBrother implements CompositionNode, Serializable {
	static final private long serialVersionUID = 6202180249626236171l;
	protected Child stepSibling;
	protected StepChild stepSibling1;
	private String uid;

	/** Constructor for SiblingStepBrother
	 * 
	 * @param end1 
	 * @param end2 
	 */
	public SiblingStepBrother(Child end1, StepChild end2) {
		this.z_internalAddToStepSibling(end1);
		this.z_internalAddToStepSibling1(end2);
	}
	
	/** Constructor for SiblingStepBrother
	 * 
	 * @param end2 
	 * @param end1 
	 */
	public SiblingStepBrother(StepChild end2, Child end1) {
		this.z_internalAddToStepSibling(end1);
		this.z_internalAddToStepSibling1(end2);
	}
	
	/** Default constructor for SiblingStepBrother
	 */
	public SiblingStepBrother() {
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
		this.z_internalRemoveFromStepSibling1(getStepSibling1());
		this.z_internalRemoveFromStepSibling(getStepSibling());
	}
	
	public void createComponents() {
	}
	
	public boolean equals(Object other) {
		if ( other instanceof SiblingStepBrother ) {
			return other==this || ((SiblingStepBrother)other).getUid().equals(this.getUid());
		}
		return false;
	}
	
	public CompositionNode getOwningObject() {
		return getStepSibling1();
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=1462536871377234328l,opposite="siblingStepBrother_stepSibling",uuid="Structures.uml@_1X1ycY1OEeKgGLBcRSZFfw")
	@NumlMetaInfo(uuid="Structures.uml@_1X1ycI1OEeKgGLBcRSZFfw@Structures.uml@_1X1ycY1OEeKgGLBcRSZFfw")
	public Child getStepSibling() {
		Child result = this.stepSibling;
		
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=192384936427444854l,opposite="siblingStepBrother_stepSibling",uuid="Structures.uml@_1X0kUI1OEeKgGLBcRSZFfw")
	@NumlMetaInfo(uuid="Structures.uml@_1X1ycI1OEeKgGLBcRSZFfw@Structures.uml@_1X0kUI1OEeKgGLBcRSZFfw")
	public StepChild getStepSibling1() {
		StepChild result = this.stepSibling1;
		
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
		this.z_internalAddToStepSibling1((StepChild)owner);
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
		return "<SiblingStepBrother uid=\""+getUid() + "\"/>";
	}
	
	public String toXmlString() {
		StringBuilder sb = new StringBuilder();
		sb.append("<SiblingStepBrother ");
		sb.append("classUuid=\"Structures.uml@_1X1ycI1OEeKgGLBcRSZFfw\" ");
		sb.append("className=\"org.opaeum.test.SiblingStepBrother\" ");
		sb.append("uid=\"" + this.getUid() + "\" ");
		sb.append(">");
		sb.append("\n</SiblingStepBrother>");
		return sb.toString();
	}
	
	public void z_internalAddToStepSibling(Child stepSibling) {
		if ( stepSibling.equals(getStepSibling()) ) {
			return;
		}
		this.stepSibling=stepSibling;
	}
	
	public void z_internalAddToStepSibling1(StepChild stepSibling1) {
		if ( stepSibling1.equals(getStepSibling1()) ) {
			return;
		}
		this.stepSibling1=stepSibling1;
	}
	
	public void z_internalRemoveFromStepSibling(Child stepSibling) {
		if ( getStepSibling()!=null && stepSibling!=null && stepSibling.equals(getStepSibling()) ) {
			this.stepSibling=null;
			this.stepSibling=null;
		}
	}
	
	public void z_internalRemoveFromStepSibling1(StepChild stepSibling1) {
		if ( getStepSibling1()!=null && stepSibling1!=null && stepSibling1.equals(getStepSibling1()) ) {
			this.stepSibling1=null;
			this.stepSibling1=null;
		}
	}

}