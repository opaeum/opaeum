package org.opaeum.uim.component;

import org.opaeum.ecore.EObject;
import org.opaeum.uim.panel.AbstractPanel;

public class PanelForClassImpl implements PanelForClass {
	private DetailComponent detailComponent;
	private AbstractPanel panel;
	private String umlElementUid;


	public EObject eContainer() {
		EObject result = null;
		
		return result;
	}
	
	public DetailComponent getDetailComponent() {
		return this.detailComponent;
	}
	
	public AbstractPanel getPanel() {
		return this.panel;
	}
	
	public String getUmlElementUid() {
		return this.umlElementUid;
	}
	
	public void setDetailComponent(DetailComponent detailComponent) {
		this.detailComponent=detailComponent;
	}
	
	public void setPanel(AbstractPanel panel) {
		this.panel=panel;
	}
	
	public void setUmlElementUid(String umlElementUid) {
		this.umlElementUid=umlElementUid;
	}

}