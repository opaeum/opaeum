package org.opaeum.uim.perspective;

import org.opaeum.ecore.EObject;

public class InboxConfigurationImpl implements InboxConfiguration {
	private Integer height;
	private String name;
	private PositionInPerspective position;
	private boolean underUserControl;
	private Integer width;


	public EObject eContainer() {
		EObject result = null;
		
		return result;
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