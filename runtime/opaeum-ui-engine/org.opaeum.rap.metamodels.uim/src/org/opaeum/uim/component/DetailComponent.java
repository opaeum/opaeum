package org.opaeum.uim.component;

import java.util.List;

import org.opaeum.ecore.EObject;

public interface DetailComponent extends EObject, UimComponent {
	public MasterComponent getMasterComponent();
	
	public List<PanelForClass> getPanelsForClasses();
	
	public void setMasterComponent(MasterComponent masterComponent);
	
	public void setPanelsForClasses(List<PanelForClass> panelsForClasses);

}