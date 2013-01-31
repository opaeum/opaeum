package org.opaeum.uim.cube;

import java.util.List;

import org.opaeum.ecore.EObject;
import org.opaeum.uim.Labels;
import org.opaeum.uim.constraint.UserInteractionConstraint;
import org.opaeum.uim.panel.AbstractPanel;

public class CubeQueryImpl implements CubeQuery {
	private List<ColumnAxisEntry> columnAxis;
	private UserInteractionConstraint editability;
	private Labels labelOverride;
	private List<MeasureProperty> measures;
	private String name;
	private AbstractPanel panel;
	private List<RowAxisEntry> rowAxis;
	private String umlElementUid;
	private boolean underUserControl;
	private UserInteractionConstraint visibility;


	public EObject eContainer() {
		EObject result = null;
		
		return result;
	}
	
	public List<ColumnAxisEntry> getColumnAxis() {
		return this.columnAxis;
	}
	
	public UserInteractionConstraint getEditability() {
		return this.editability;
	}
	
	public Labels getLabelOverride() {
		return this.labelOverride;
	}
	
	public List<MeasureProperty> getMeasures() {
		return this.measures;
	}
	
	public String getName() {
		return this.name;
	}
	
	public AbstractPanel getPanel() {
		return this.panel;
	}
	
	public List<RowAxisEntry> getRowAxis() {
		return this.rowAxis;
	}
	
	public String getUmlElementUid() {
		return this.umlElementUid;
	}
	
	public boolean getUnderUserControl() {
		return this.underUserControl;
	}
	
	public UserInteractionConstraint getVisibility() {
		return this.visibility;
	}
	
	public void isUnderUserControl(boolean underUserControl) {
		this.underUserControl=underUserControl;
	}
	
	public void setColumnAxis(List<ColumnAxisEntry> columnAxis) {
		this.columnAxis=columnAxis;
	}
	
	public void setEditability(UserInteractionConstraint editability) {
		this.editability=editability;
	}
	
	public void setLabelOverride(Labels labelOverride) {
		this.labelOverride=labelOverride;
	}
	
	public void setMeasures(List<MeasureProperty> measures) {
		this.measures=measures;
	}
	
	public void setName(String name) {
		this.name=name;
	}
	
	public void setPanel(AbstractPanel panel) {
		this.panel=panel;
	}
	
	public void setRowAxis(List<RowAxisEntry> rowAxis) {
		this.rowAxis=rowAxis;
	}
	
	public void setUmlElementUid(String umlElementUid) {
		this.umlElementUid=umlElementUid;
	}
	
	public void setVisibility(UserInteractionConstraint visibility) {
		this.visibility=visibility;
	}

}