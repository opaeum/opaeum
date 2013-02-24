package org.opaeum.uim.perspective;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.opaeum.ecore.EObject;
import org.opaeum.ecore.EObjectImpl;
import org.opaeum.org.opaeum.runtime.uim.metamodel.UimInstantiator;
import org.opaeum.runtime.domain.EcoreDataTypeParser;
import org.opaeum.uim.Labels;
import org.opaeum.uim.constraint.RequiredRole;
import org.opaeum.uim.constraint.RequiredState;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class OperationNavigationConstraintImpl extends EObjectImpl implements OperationNavigationConstraint {
	private boolean hidden;
	private boolean inheritFromParent;
	private NavigationConstraint invocationConstraint;
	private Labels labelOverride;
	private String name;
	private Boolean openToPublic;
	private ClassNavigationConstraint owner;
	private List<ParameterNavigationConstraint> parameters = new ArrayList<ParameterNavigationConstraint>();
	private List<RequiredRole> requiredRoles = new ArrayList<RequiredRole>();
	private List<RequiredState> requiredStates = new ArrayList<RequiredState>();
	private boolean requiresGroupOwnership;
	private boolean requiresOwnership;
	private String uid;
	private String umlElementUid;
	private boolean underUserControl;


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
			if ( currentPropertyNode instanceof Element && currentPropertyNode.getNodeName().equals("labelOverride") ) {
				String typeString = ((Element)currentPropertyNode).getAttribute("xsi:type");
				Labels curVal;
				if ( typeString==null ||typeString.trim().length()==0 ) {
					typeString="Labels";
				}
				curVal=UimInstantiator.INSTANCE.newInstance(typeString);
				this.setLabelOverride(curVal);
				curVal.buildTreeFromXml((Element)currentPropertyNode,map);
				map.put(curVal.getUid(), curVal);
				curVal.eContainer(this);
			}
			if ( currentPropertyNode instanceof Element && currentPropertyNode.getNodeName().equals("invocationConstraint") ) {
				String typeString = ((Element)currentPropertyNode).getAttribute("xsi:type");
				NavigationConstraint curVal;
				if ( typeString==null ||typeString.trim().length()==0 ) {
					typeString="persp:NavigationConstraint";
				}
				curVal=UimInstantiator.INSTANCE.newInstance(typeString);
				this.setInvocationConstraint(curVal);
				curVal.buildTreeFromXml((Element)currentPropertyNode,map);
				map.put(curVal.getUid(), curVal);
				curVal.eContainer(this);
			}
			if ( currentPropertyNode instanceof Element && currentPropertyNode.getNodeName().equals("parameters") ) {
				String typeString = ((Element)currentPropertyNode).getAttribute("xsi:type");
				ParameterNavigationConstraint curVal;
				if ( typeString==null ||typeString.trim().length()==0 ) {
					typeString="persp:ParameterNavigationConstraint";
				}
				curVal=UimInstantiator.INSTANCE.newInstance(typeString);
				this.getParameters().add(curVal);
				curVal.buildTreeFromXml((Element)currentPropertyNode,map);
				map.put(curVal.getUid(), curVal);
				curVal.eContainer(this);
			}
		}
	}
	
	public EObject eContainer() {
		EObject result = null;
		
		return result;
	}
	
	public NavigationConstraint getInvocationConstraint() {
		return this.invocationConstraint;
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
	
	public ClassNavigationConstraint getOwner() {
		return this.owner;
	}
	
	public List<ParameterNavigationConstraint> getParameters() {
		return this.parameters;
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
			if ( currentPropertyNode instanceof Element && currentPropertyNode.getNodeName().equals("labelOverride") ) {
				((org.opaeum.uim.Labels)map.get(((Element)currentPropertyNode).getAttribute("xmi:id"))).populateReferencesFromXml((Element)currentPropertyNode, map);
			}
			if ( currentPropertyNode instanceof Element && currentPropertyNode.getNodeName().equals("invocationConstraint") ) {
				((org.opaeum.uim.perspective.NavigationConstraint)map.get(((Element)currentPropertyNode).getAttribute("xmi:id"))).populateReferencesFromXml((Element)currentPropertyNode, map);
			}
			if ( currentPropertyNode instanceof Element && currentPropertyNode.getNodeName().equals("parameters") ) {
				((org.opaeum.uim.perspective.ParameterNavigationConstraint)map.get(((Element)currentPropertyNode).getAttribute("xmi:id"))).populateReferencesFromXml((Element)currentPropertyNode, map);
			}
		}
	}
	
	public void setHidden(boolean hidden) {
		this.hidden=hidden;
	}
	
	public void setInheritFromParent(boolean inheritFromParent) {
		this.inheritFromParent=inheritFromParent;
	}
	
	public void setInvocationConstraint(NavigationConstraint invocationConstraint) {
		this.invocationConstraint=invocationConstraint;
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
	
	public void setOwner(ClassNavigationConstraint owner) {
		this.owner=owner;
	}
	
	public void setParameters(List<ParameterNavigationConstraint> parameters) {
		this.parameters=parameters;
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
	
	public void setUmlElementUid(String umlElementUid) {
		this.umlElementUid=umlElementUid;
	}
	
	public void setUnderUserControl(boolean underUserControl) {
		this.underUserControl=underUserControl;
	}

}