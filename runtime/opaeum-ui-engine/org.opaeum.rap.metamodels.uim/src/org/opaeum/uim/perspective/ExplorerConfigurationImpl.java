package org.opaeum.uim.perspective;

import java.util.List;

import org.opaeum.ecore.EObject;

public class ExplorerConfigurationImpl implements ExplorerConfiguration {
	private List<ExplorerClassConstraint> classes;
	private Integer height;
	private String name;
	private PositionInPerspective position;
	private boolean underUserControl;
	private Integer width;


	public EObject eContainer() {
		EObject result = null;
		
		return result;
	}
	
	public List<ExplorerClassConstraint> getClasses() {
		return this.classes;
	}
	
	public Integer getHeight() {
		return this.height;
	}
	
	public String getName() {
		return this.name;
	}
	
	public PositionInPerspective getPosition() {
		return this.position;
	}
	
	public boolean getUnderUserControl() {
		return this.underUserControl;
	}
	
	public Integer getWidth() {
		return this.width;
	}
	
	public void isUnderUserControl(boolean underUserControl) {
		this.underUserControl=underUserControl;
	}
	
	public void setClasses(List<ExplorerClassConstraint> classes) {
		this.classes=classes;
	}
	
	public void setHeight(Integer height) {
		this.height=height;
	}
	
	public void setName(String name) {
		this.name=name;
	}
	
	public void setPosition(PositionInPerspective position) {
		this.position=position;
	}
	
	public void setWidth(Integer width) {
		this.width=width;
	}

}