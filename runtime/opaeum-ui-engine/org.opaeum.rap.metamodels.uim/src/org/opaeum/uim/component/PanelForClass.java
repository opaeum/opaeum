package org.opaeum.uim.component;

import org.opaeum.ecore.EObject;
import org.opaeum.uim.UmlReference;
import org.opaeum.uim.panel.AbstractPanel;

public interface PanelForClass extends EObject, UmlReference {
	public DetailComponent getDetailComponent();
	
	public AbstractPanel getPanel();
	
	public void setDetailComponent(DetailComponent detailComponent);
	
	public void setPanel(AbstractPanel panel);

}