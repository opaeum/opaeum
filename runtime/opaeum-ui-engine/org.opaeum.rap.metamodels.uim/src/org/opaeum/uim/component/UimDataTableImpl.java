package org.opaeum.uim.component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.opaeum.ecore.EObject;
import org.opaeum.ecore.EObjectImpl;
import org.opaeum.org.opaeum.rap.metamodels.uim.UimInstantiator;
import org.opaeum.runtime.domain.EcoreDataTypeParser;
import org.opaeum.runtime.environment.Environment;
import org.opaeum.uim.Labels;
import org.opaeum.uim.action.AbstractActionButton;
import org.opaeum.uim.binding.TableBinding;
import org.opaeum.uim.constraint.UserInteractionConstraint;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class UimDataTableImpl extends EObjectImpl implements UimDataTable {
	private List<AbstractActionButton> actionsOnMultipleSelection = new ArrayList<AbstractActionButton>();
	private TableBinding binding;
	private List<UimComponent> children = new ArrayList<UimComponent>();
	private List<DetailComponent> detailComponents = new ArrayList<DetailComponent>();
	private UserInteractionConstraint editability;
	private Boolean fillHorizontally;
	private Boolean fillVertically;
	private Labels labelOverride;
	private String name;
	private Integer preferredHeight;
	private Integer preferredWidth;
	private String uid;
	private boolean underUserControl;
	private UserInteractionConstraint visibility;


	public void buildTreeFromXml(Element xml, Map<String, Object> map) {
		setUid(xml.getAttribute("xmi:id"));
		if ( xml.getAttribute("name").length()>0 ) {
			setName(EcoreDataTypeParser.getInstance().parseEString(xml.getAttribute("name")));
		}
		if ( xml.getAttribute("underUserControl").length()>0 ) {
			setUnderUserControl(EcoreDataTypeParser.getInstance().parseEBoolean(xml.getAttribute("underUserControl")));
		}
		if ( xml.getAttribute("preferredWidth").length()>0 ) {
			setPreferredWidth(EcoreDataTypeParser.getInstance().parseEIntegerObject(xml.getAttribute("preferredWidth")));
		}
		if ( xml.getAttribute("preferredHeight").length()>0 ) {
			setPreferredHeight(EcoreDataTypeParser.getInstance().parseEIntegerObject(xml.getAttribute("preferredHeight")));
		}
		if ( xml.getAttribute("fillHorizontally").length()>0 ) {
			setFillHorizontally(EcoreDataTypeParser.getInstance().parseEBooleanObject(xml.getAttribute("fillHorizontally")));
		}
		if ( xml.getAttribute("fillVertically").length()>0 ) {
			setFillVertically(EcoreDataTypeParser.getInstance().parseEBooleanObject(xml.getAttribute("fillVertically")));
		}
		NodeList propertyNodes = xml.getChildNodes();
		int i = 0;
		while ( i<propertyNodes.getLength() ) {
			Node currentPropertyNode = propertyNodes.item(i++);
			if ( currentPropertyNode instanceof Element && currentPropertyNode.getNodeName().equals("visibility") ) {
				String typeString = ((Element)currentPropertyNode).getAttribute("xsi:type");
				UserInteractionConstraint curVal;
				if ( typeString==null ||typeString.trim().length()==0 ) {
					typeString="constr:UserInteractionConstraint";
				}
				curVal=UimInstantiator.INSTANCE.newInstance(typeString);
				this.setVisibility(curVal);
				curVal.buildTreeFromXml((Element)currentPropertyNode,map);
				map.put(curVal.getUid(), curVal);
				curVal.eContainer(this);
			}
			if ( currentPropertyNode instanceof Element && currentPropertyNode.getNodeName().equals("editability") ) {
				String typeString = ((Element)currentPropertyNode).getAttribute("xsi:type");
				UserInteractionConstraint curVal;
				if ( typeString==null ||typeString.trim().length()==0 ) {
					typeString="constr:UserInteractionConstraint";
				}
				curVal=UimInstantiator.INSTANCE.newInstance(typeString);
				this.setEditability(curVal);
				curVal.buildTreeFromXml((Element)currentPropertyNode,map);
				map.put(curVal.getUid(), curVal);
				curVal.eContainer(this);
			}
			if ( currentPropertyNode instanceof Element && currentPropertyNode.getNodeName().equals("children") ) {
				String typeString = ((Element)currentPropertyNode).getAttribute("xsi:type");
				UimComponent curVal;
				if ( typeString==null ||typeString.trim().length()==0 ) {
					typeString="comp:UimComponent";
				}
				curVal=UimInstantiator.INSTANCE.newInstance(typeString);
				this.getChildren().add(curVal);
				curVal.buildTreeFromXml((Element)currentPropertyNode,map);
				map.put(curVal.getUid(), curVal);
				curVal.eContainer(this);
			}
			if ( currentPropertyNode instanceof Element && currentPropertyNode.getNodeName().equals("binding") ) {
				String typeString = ((Element)currentPropertyNode).getAttribute("xsi:type");
				TableBinding curVal;
				if ( typeString==null ||typeString.trim().length()==0 ) {
					typeString="bind:TableBinding";
				}
				curVal=UimInstantiator.INSTANCE.newInstance(typeString);
				this.setBinding(curVal);
				curVal.buildTreeFromXml((Element)currentPropertyNode,map);
				map.put(curVal.getUid(), curVal);
				curVal.eContainer(this);
				curVal.setTable(this);
			}
			if ( currentPropertyNode instanceof Element && currentPropertyNode.getNodeName().equals("actionsOnMultipleSelection") ) {
				String typeString = ((Element)currentPropertyNode).getAttribute("xsi:type");
				AbstractActionButton curVal;
				if ( typeString==null ||typeString.trim().length()==0 ) {
					typeString="action:AbstractActionButton";
				}
				curVal=UimInstantiator.INSTANCE.newInstance(typeString);
				this.getActionsOnMultipleSelection().add(curVal);
				curVal.buildTreeFromXml((Element)currentPropertyNode,map);
				map.put(curVal.getUid(), curVal);
				curVal.eContainer(this);
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
		}
	}
	
	public EObject eContainer() {
		EObject result = null;
		
		return result;
	}
	
	public List<AbstractActionButton> getActionsOnMultipleSelection() {
		return this.actionsOnMultipleSelection;
	}
	
	public TableBinding getBinding() {
		return this.binding;
	}
	
	public List<UimComponent> getChildren() {
		return this.children;
	}
	
	public List<DetailComponent> getDetailComponents() {
		return this.detailComponents;
	}
	
	public UserInteractionConstraint getEditability() {
		return this.editability;
	}
	
	public Boolean getFillHorizontally() {
		return this.fillHorizontally;
	}
	
	public Boolean getFillVertically() {
		return this.fillVertically;
	}
	
	public Labels getLabelOverride() {
		return this.labelOverride;
	}
	
	public String getName() {
		return this.name;
	}
	
	public UimContainer getParent() {
		UimContainer result = null;
		
		return result;
	}
	
	public Integer getPreferredHeight() {
		return this.preferredHeight;
	}
	
	public Integer getPreferredWidth() {
		return this.preferredWidth;
	}
	
	public String getUid() {
		return this.uid;
	}
	
	public UserInteractionConstraint getVisibility() {
		return this.visibility;
	}
	
	public boolean isUnderUserControl() {
		return this.underUserControl;
	}
	
	public void populateReferencesFromXml(Element xml, Map<String, Object> map) {
		NodeList propertyNodes = xml.getChildNodes();
		int i = 0;
		while ( i<propertyNodes.getLength() ) {
			Node currentPropertyNode = propertyNodes.item(i++);
			if ( currentPropertyNode instanceof Element && currentPropertyNode.getNodeName().equals("detailComponents") ) {
				getDetailComponents().add((org.opaeum.uim.component.DetailComponent)map.get(((Element)currentPropertyNode).getAttribute("xmi:id")));
			}
			if ( currentPropertyNode instanceof Element && currentPropertyNode.getNodeName().equals("visibility") ) {
				((org.opaeum.uim.constraint.UserInteractionConstraint)map.get(((Element)currentPropertyNode).getAttribute("xmi:id"))).populateReferencesFromXml((Element)currentPropertyNode, map);
			}
			if ( currentPropertyNode instanceof Element && currentPropertyNode.getNodeName().equals("editability") ) {
				((org.opaeum.uim.constraint.UserInteractionConstraint)map.get(((Element)currentPropertyNode).getAttribute("xmi:id"))).populateReferencesFromXml((Element)currentPropertyNode, map);
			}
			if ( currentPropertyNode instanceof Element && currentPropertyNode.getNodeName().equals("children") ) {
				((org.opaeum.uim.component.UimComponent)map.get(((Element)currentPropertyNode).getAttribute("xmi:id"))).populateReferencesFromXml((Element)currentPropertyNode, map);
			}
			if ( currentPropertyNode instanceof Element && currentPropertyNode.getNodeName().equals("binding") ) {
				((org.opaeum.uim.binding.TableBinding)map.get(((Element)currentPropertyNode).getAttribute("xmi:id"))).populateReferencesFromXml((Element)currentPropertyNode, map);
			}
			if ( currentPropertyNode instanceof Element && currentPropertyNode.getNodeName().equals("actionsOnMultipleSelection") ) {
				((org.opaeum.uim.action.AbstractActionButton)map.get(((Element)currentPropertyNode).getAttribute("xmi:id"))).populateReferencesFromXml((Element)currentPropertyNode, map);
			}
			if ( currentPropertyNode instanceof Element && currentPropertyNode.getNodeName().equals("labelOverride") ) {
				((org.opaeum.uim.Labels)map.get(((Element)currentPropertyNode).getAttribute("xmi:id"))).populateReferencesFromXml((Element)currentPropertyNode, map);
			}
		}
	}
	
	public void setActionsOnMultipleSelection(List<AbstractActionButton> actionsOnMultipleSelection) {
		this.actionsOnMultipleSelection=actionsOnMultipleSelection;
	}
	
	public void setBinding(TableBinding binding) {
		this.binding=binding;
	}
	
	public void setChildren(List<UimComponent> children) {
		this.children=children;
	}
	
	public void setDetailComponents(List<DetailComponent> detailComponents) {
		this.detailComponents=detailComponents;
	}
	
	public void setEditability(UserInteractionConstraint editability) {
		this.editability=editability;
	}
	
	public void setFillHorizontally(Boolean fillHorizontally) {
		this.fillHorizontally=fillHorizontally;
	}
	
	public void setFillVertically(Boolean fillVertically) {
		this.fillVertically=fillVertically;
	}
	
	public void setLabelOverride(Labels labelOverride) {
		this.labelOverride=labelOverride;
	}
	
	public void setName(String name) {
		this.name=name;
	}
	
	public void setPreferredHeight(Integer preferredHeight) {
		this.preferredHeight=preferredHeight;
	}
	
	public void setPreferredWidth(Integer preferredWidth) {
		this.preferredWidth=preferredWidth;
	}
	
	public void setUid(String uid) {
		this.uid=uid;
	}
	
	public void setUnderUserControl(boolean underUserControl) {
		this.underUserControl=underUserControl;
	}
	
	public void setVisibility(UserInteractionConstraint visibility) {
		this.visibility=visibility;
	}

}