package org.opaeum.uim.constraint;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.opaeum.ecore.EObject;
import org.opaeum.ecore.EObjectImpl;
import org.opaeum.org.opaeum.runtime.uim.metamodel.UimInstantiator;
import org.opaeum.runtime.domain.EcoreDataTypeParser;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class UserInteractionConstraintImpl extends EObjectImpl implements UserInteractionConstraint {
	private boolean inheritFromParent;
	private Boolean openToPublic;
	private List<RequiredRole> requiredRoles = new ArrayList<RequiredRole>();
	private List<RequiredState> requiredStates = new ArrayList<RequiredState>();
	private boolean requiresGroupOwnership;
	private boolean requiresOwnership;
	private String uid;


	public void buildTreeFromXml(Element xml, Map<String, Object> map) {
		setUid(xml.getAttribute("xmi:id"));
		if ( xml.getAttribute("requiresGroupOwnership").length()>0 ) {
			setRequiresGroupOwnership(EcoreDataTypeParser.getInstance().parseEBoolean(xml.getAttribute("requiresGroupOwnership")));
		}
		if ( xml.getAttribute("requiresOwnership").length()>0 ) {
			setRequiresOwnership(EcoreDataTypeParser.getInstance().parseEBoolean(xml.getAttribute("requiresOwnership")));
		}
		if ( xml.getAttribute("openToPublic").length()>0 ) {
			setOpenToPublic(EcoreDataTypeParser.getInstance().parseEBooleanObject(xml.getAttribute("openToPublic")));
		}
		if ( xml.getAttribute("inheritFromParent").length()>0 ) {
			setInheritFromParent(EcoreDataTypeParser.getInstance().parseEBoolean(xml.getAttribute("inheritFromParent")));
		}
		NodeList propertyNodes = xml.getChildNodes();
		int i = 0;
		while ( i<propertyNodes.getLength() ) {
			Node currentPropertyNode = propertyNodes.item(i++);
			if ( currentPropertyNode instanceof Element && currentPropertyNode.getNodeName().equals("requiredRoles") ) {
				String typeString = ((Element)currentPropertyNode).getAttribute("xsi:type");
				RequiredRole curVal;
				if ( typeString==null ||typeString.trim().length()==0 ) {
					typeString="constr:RequiredRole";
				}
				curVal=UimInstantiator.INSTANCE.newInstance(typeString);
				this.getRequiredRoles().add(curVal);
				curVal.buildTreeFromXml((Element)currentPropertyNode,map);
				map.put(curVal.getUid(), curVal);
				curVal.eContainer(this);
				curVal.setConstraint(this);
			}
			if ( currentPropertyNode instanceof Element && currentPropertyNode.getNodeName().equals("requiredStates") ) {
				String typeString = ((Element)currentPropertyNode).getAttribute("xsi:type");
				RequiredState curVal;
				if ( typeString==null ||typeString.trim().length()==0 ) {
					typeString="constr:RequiredState";
				}
				curVal=UimInstantiator.INSTANCE.newInstance(typeString);
				this.getRequiredStates().add(curVal);
				curVal.buildTreeFromXml((Element)currentPropertyNode,map);
				map.put(curVal.getUid(), curVal);
				curVal.eContainer(this);
				curVal.setConstraint(this);
			}
		}
	}
	
	public EObject eContainer() {
		EObject result = null;
		
		return result;
	}
	
	public Boolean getOpenToPublic() {
		return this.openToPublic;
	}
	
	public List<RequiredRole> getRequiredRoles() {
		return this.requiredRoles;
	}
	
	public List<RequiredState> getRequiredStates() {
		return this.requiredStates;
	}
	
	public String getUid() {
		return this.uid;
	}
	
	public boolean isInheritFromParent() {
		return this.inheritFromParent;
	}
	
	public boolean isRequiresGroupOwnership() {
		return this.requiresGroupOwnership;
	}
	
	public boolean isRequiresOwnership() {
		return this.requiresOwnership;
	}
	
	public void populateReferencesFromXml(Element xml, Map<String, Object> map) {
		NodeList propertyNodes = xml.getChildNodes();
		int i = 0;
		while ( i<propertyNodes.getLength() ) {
			Node currentPropertyNode = propertyNodes.item(i++);
			if ( currentPropertyNode instanceof Element && currentPropertyNode.getNodeName().equals("requiredRoles") ) {
				((org.opaeum.uim.constraint.RequiredRole)map.get(((Element)currentPropertyNode).getAttribute("xmi:id"))).populateReferencesFromXml((Element)currentPropertyNode, map);
			}
			if ( currentPropertyNode instanceof Element && currentPropertyNode.getNodeName().equals("requiredStates") ) {
				((org.opaeum.uim.constraint.RequiredState)map.get(((Element)currentPropertyNode).getAttribute("xmi:id"))).populateReferencesFromXml((Element)currentPropertyNode, map);
			}
		}
	}
	
	public void setInheritFromParent(boolean inheritFromParent) {
		this.inheritFromParent=inheritFromParent;
	}
	
	public void setOpenToPublic(Boolean openToPublic) {
		this.openToPublic=openToPublic;
	}
	
	public void setRequiredRoles(List<RequiredRole> requiredRoles) {
		this.requiredRoles=requiredRoles;
	}
	
	public void setRequiredStates(List<RequiredState> requiredStates) {
		this.requiredStates=requiredStates;
	}
	
	public void setRequiresGroupOwnership(boolean requiresGroupOwnership) {
		this.requiresGroupOwnership=requiresGroupOwnership;
	}
	
	public void setRequiresOwnership(boolean requiresOwnership) {
		this.requiresOwnership=requiresOwnership;
	}
	
	public void setUid(String uid) {
		this.uid=uid;
	}

}