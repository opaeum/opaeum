package org.opaeum.uim.constraint;

import java.util.ArrayList;
import java.util.List;

import org.opaeum.ecore.EObjectImpl;
import org.opaeum.org.opaeum.runtime.uim.metamodel.UimInstantiator;
import org.opaeum.runtime.domain.EcoreDataTypeParser;
import org.opaeum.runtime.environment.Environment;
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


	public void buildTreeFromXml(Element xml) {
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
				curVal.init(this,eResource(),(Element)currentPropertyNode);
				curVal.buildTreeFromXml((Element)currentPropertyNode);
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
				curVal.init(this,eResource(),(Element)currentPropertyNode);
				curVal.buildTreeFromXml((Element)currentPropertyNode);
				curVal.setConstraint(this);
			}
		}
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
	
	public boolean isInheritFromParent() {
		return this.inheritFromParent;
	}
	
	public boolean isRequiresGroupOwnership() {
		return this.requiresGroupOwnership;
	}
	
	public boolean isRequiresOwnership() {
		return this.requiresOwnership;
	}
	
	public void populateReferencesFromXml(Element xml) {
		NodeList propertyNodes = xml.getChildNodes();
		int i = 0;
		while ( i<propertyNodes.getLength() ) {
			Node currentPropertyNode = propertyNodes.item(i++);
			if ( currentPropertyNode instanceof Element && currentPropertyNode.getNodeName().equals("requiredRoles") ) {
				((org.opaeum.uim.constraint.RequiredRole)this.eResource().getElement((Element)currentPropertyNode)).populateReferencesFromXml((Element)currentPropertyNode);
			}
			if ( currentPropertyNode instanceof Element && currentPropertyNode.getNodeName().equals("requiredStates") ) {
				((org.opaeum.uim.constraint.RequiredState)this.eResource().getElement((Element)currentPropertyNode)).populateReferencesFromXml((Element)currentPropertyNode);
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

}