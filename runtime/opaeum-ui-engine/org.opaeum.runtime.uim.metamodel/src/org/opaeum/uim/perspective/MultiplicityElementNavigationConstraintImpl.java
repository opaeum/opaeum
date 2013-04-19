package org.opaeum.uim.perspective;

import java.util.ArrayList;
import java.util.List;

import org.opaeum.ecore.EObjectImpl;
import org.opaeum.org.opaeum.runtime.uim.metamodel.UimInstantiator;
import org.opaeum.runtime.domain.EcoreDataTypeParser;
import org.opaeum.runtime.environment.Environment;
import org.opaeum.uim.Labels;
import org.opaeum.uim.constraint.RequiredRole;
import org.opaeum.uim.constraint.RequiredState;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class MultiplicityElementNavigationConstraintImpl extends EObjectImpl implements MultiplicityElementNavigationConstraint {
	private NavigationConstraint addConstraint;
	private boolean hidden;
	private boolean inheritFromParent;
	private Labels labelOverride;
	private String name;
	private Boolean openToPublic;
	private NavigationConstraint removeConstraint;
	private List<RequiredRole> requiredRoles = new ArrayList<RequiredRole>();
	private List<RequiredState> requiredStates = new ArrayList<RequiredState>();
	private boolean requiresGroupOwnership;
	private boolean requiresOwnership;
	private String umlElementUid;
	private boolean underUserControl;


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
		if ( xml.getAttribute("name").length()>0 ) {
			setName(EcoreDataTypeParser.getInstance().parseEString(xml.getAttribute("name")));
		}
		if ( xml.getAttribute("underUserControl").length()>0 ) {
			setUnderUserControl(EcoreDataTypeParser.getInstance().parseEBoolean(xml.getAttribute("underUserControl")));
		}
		if ( xml.getAttribute("umlElementUid").length()>0 ) {
			setUmlElementUid(EcoreDataTypeParser.getInstance().parseEString(xml.getAttribute("umlElementUid")));
		}
		if ( xml.getAttribute("hidden").length()>0 ) {
			setHidden(EcoreDataTypeParser.getInstance().parseEBoolean(xml.getAttribute("hidden")));
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
			if ( currentPropertyNode instanceof Element && currentPropertyNode.getNodeName().equals("labelOverride") ) {
				String typeString = ((Element)currentPropertyNode).getAttribute("xsi:type");
				Labels curVal;
				if ( typeString==null ||typeString.trim().length()==0 ) {
					typeString="Labels";
				}
				curVal=UimInstantiator.INSTANCE.newInstance(typeString);
				this.setLabelOverride(curVal);
				curVal.init(this,eResource(),(Element)currentPropertyNode);
				curVal.buildTreeFromXml((Element)currentPropertyNode);
			}
			if ( currentPropertyNode instanceof Element && currentPropertyNode.getNodeName().equals("removeConstraint") ) {
				String typeString = ((Element)currentPropertyNode).getAttribute("xsi:type");
				NavigationConstraint curVal;
				if ( typeString==null ||typeString.trim().length()==0 ) {
					typeString="persp:NavigationConstraint";
				}
				curVal=UimInstantiator.INSTANCE.newInstance(typeString);
				this.setRemoveConstraint(curVal);
				curVal.init(this,eResource(),(Element)currentPropertyNode);
				curVal.buildTreeFromXml((Element)currentPropertyNode);
			}
			if ( currentPropertyNode instanceof Element && currentPropertyNode.getNodeName().equals("addConstraint") ) {
				String typeString = ((Element)currentPropertyNode).getAttribute("xsi:type");
				NavigationConstraint curVal;
				if ( typeString==null ||typeString.trim().length()==0 ) {
					typeString="persp:NavigationConstraint";
				}
				curVal=UimInstantiator.INSTANCE.newInstance(typeString);
				this.setAddConstraint(curVal);
				curVal.init(this,eResource(),(Element)currentPropertyNode);
				curVal.buildTreeFromXml((Element)currentPropertyNode);
			}
		}
	}
	
	public NavigationConstraint getAddConstraint() {
		return this.addConstraint;
	}
	
	public Labels getLabelOverride() {
		return this.labelOverride;
	}
	
	public String getName() {
		return this.name;
	}
	
	public Boolean getOpenToPublic() {
		return this.openToPublic;
	}
	
	public NavigationConstraint getRemoveConstraint() {
		return this.removeConstraint;
	}
	
	public List<RequiredRole> getRequiredRoles() {
		return this.requiredRoles;
	}
	
	public List<RequiredState> getRequiredStates() {
		return this.requiredStates;
	}
	
	public String getUmlElementUid() {
		return this.umlElementUid;
	}
	
	public boolean isHidden() {
		return this.hidden;
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
	
	public boolean isUnderUserControl() {
		return this.underUserControl;
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
			if ( currentPropertyNode instanceof Element && currentPropertyNode.getNodeName().equals("labelOverride") ) {
				((org.opaeum.uim.Labels)this.eResource().getElement((Element)currentPropertyNode)).populateReferencesFromXml((Element)currentPropertyNode);
			}
			if ( currentPropertyNode instanceof Element && currentPropertyNode.getNodeName().equals("removeConstraint") ) {
				((org.opaeum.uim.perspective.NavigationConstraint)this.eResource().getElement((Element)currentPropertyNode)).populateReferencesFromXml((Element)currentPropertyNode);
			}
			if ( currentPropertyNode instanceof Element && currentPropertyNode.getNodeName().equals("addConstraint") ) {
				((org.opaeum.uim.perspective.NavigationConstraint)this.eResource().getElement((Element)currentPropertyNode)).populateReferencesFromXml((Element)currentPropertyNode);
			}
		}
	}
	
	public void setAddConstraint(NavigationConstraint addConstraint) {
		this.addConstraint=addConstraint;
	}
	
	public void setHidden(boolean hidden) {
		this.hidden=hidden;
	}
	
	public void setInheritFromParent(boolean inheritFromParent) {
		this.inheritFromParent=inheritFromParent;
	}
	
	public void setLabelOverride(Labels labelOverride) {
		this.labelOverride=labelOverride;
	}
	
	public void setName(String name) {
		this.name=name;
	}
	
	public void setOpenToPublic(Boolean openToPublic) {
		this.openToPublic=openToPublic;
	}
	
	public void setRemoveConstraint(NavigationConstraint removeConstraint) {
		this.removeConstraint=removeConstraint;
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
	
	public void setUmlElementUid(String umlElementUid) {
		this.umlElementUid=umlElementUid;
	}
	
	public void setUnderUserControl(boolean underUserControl) {
		this.underUserControl=underUserControl;
	}

}