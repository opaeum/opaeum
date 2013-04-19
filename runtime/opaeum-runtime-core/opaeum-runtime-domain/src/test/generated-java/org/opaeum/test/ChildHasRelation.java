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

@NumlMetaInfo(applicationIdentifier="structuretests",uuid="Structures.uml@_I7GooIhrEeK4s7QGypAJBA")
public class ChildHasRelation implements CompositionNode, Serializable {
	protected Child child;
	protected Relation godParent;
	static final private long serialVersionUID = 6792722447437408965l;
	private String uid;
	private String z_keyOfChildOnRelation;
	private String z_keyOfGodParentOnChild;

	/** Constructor for ChildHasRelation
	 * 
	 * @param end1 
	 * @param end2 
	 */
	public ChildHasRelation(Child end1, Relation end2) {
		this.z_internalAddToChild(end1);
		this.z_internalAddToGodParent(end2);
	}
	
	/** Constructor for ChildHasRelation
	 * 
	 * @param end2 
	 * @param end1 
	 */
	public ChildHasRelation(Relation end2, Child end1) {
		this.z_internalAddToChild(end1);
		this.z_internalAddToGodParent(end2);
	}
	
	/** Default constructor for ChildHasRelation
	 */
	public ChildHasRelation() {
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
		this.z_internalRemoveFromGodParent(getGodParent());
		this.z_internalRemoveFromChild(getChild());
	}
	
	public void createComponents() {
	}
	
	public boolean equals(Object other) {
		if ( other instanceof ChildHasRelation ) {
			return other==this || ((ChildHasRelation)other).getUid().equals(this.getUid());
		}
		return false;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=8317084472673424687l,opposite="childHasRelation_godParent",uuid="Structures.uml@_I7GooYhrEeK4s7QGypAJBA")
	@NumlMetaInfo(uuid="Structures.uml@_I7GooIhrEeK4s7QGypAJBA@Structures.uml@_I7GooYhrEeK4s7QGypAJBA")
	public Child getChild() {
		Child result = this.child;
		
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=7442837881736799797l,opposite="childHasRelation_child",uuid="Structures.uml@_I7BwIIhrEeK4s7QGypAJBA")
	@NumlMetaInfo(uuid="Structures.uml@_I7GooIhrEeK4s7QGypAJBA@Structures.uml@_I7BwIIhrEeK4s7QGypAJBA")
	public Relation getGodParent() {
		Relation result = this.godParent;
		
		return result;
	}
	
	public CompositionNode getOwningObject() {
		return getGodParent();
	}
	
	public String getUid() {
		if ( this.uid==null || this.uid.trim().length()==0 ) {
			uid=UUID.randomUUID().toString();
		}
		return this.uid;
	}
	
	public String getZ_keyOfChildOnRelation() {
		return this.z_keyOfChildOnRelation;
	}
	
	public String getZ_keyOfGodParentOnChild() {
		return this.z_keyOfGodParentOnChild;
	}
	
	public int hashCode() {
		return getUid().hashCode();
	}
	
	public void init(CompositionNode owner) {
		this.z_internalAddToGodParent((Relation)owner);
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
	
	public void setZ_keyOfChildOnRelation(String z_keyOfChildOnRelation) {
		this.z_keyOfChildOnRelation=z_keyOfChildOnRelation;
	}
	
	public void setZ_keyOfGodParentOnChild(String z_keyOfGodParentOnChild) {
		this.z_keyOfGodParentOnChild=z_keyOfGodParentOnChild;
	}
	
	public String toXmlReferenceString() {
		return "<ChildHasRelation uid=\""+getUid() + "\"/>";
	}
	
	public String toXmlString() {
		StringBuilder sb = new StringBuilder();
		sb.append("<ChildHasRelation ");
		sb.append("classUuid=\"Structures.uml@_I7GooIhrEeK4s7QGypAJBA\" ");
		sb.append("className=\"org.opaeum.test.ChildHasRelation\" ");
		sb.append("uid=\"" + this.getUid() + "\" ");
		sb.append(">");
		sb.append("\n</ChildHasRelation>");
		return sb.toString();
	}
	
	public void z_internalAddToChild(Child child) {
		if ( child.equals(getChild()) ) {
			return;
		}
		this.child=child;
	}
	
	public void z_internalAddToGodParent(Relation godParent) {
		if ( godParent.equals(getGodParent()) ) {
			return;
		}
		this.godParent=godParent;
	}
	
	public void z_internalRemoveFromChild(Child child) {
		if ( getChild()!=null && child!=null && child.equals(getChild()) ) {
			this.child=null;
			this.child=null;
		}
	}
	
	public void z_internalRemoveFromGodParent(Relation godParent) {
		if ( getGodParent()!=null && godParent!=null && godParent.equals(getGodParent()) ) {
			this.godParent=null;
			this.godParent=null;
		}
	}

}