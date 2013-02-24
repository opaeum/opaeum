package org.opaeum.uim.component;

import java.util.List;
import java.util.Map;

import org.opaeum.ecore.EObject;
import org.opaeum.uim.action.AbstractActionButton;
import org.opaeum.uim.binding.TableBinding;
import org.opaeum.uim.panel.Outlayable;
import org.w3c.dom.Element;

public interface UimDataTable extends EObject, UimContainer, Outlayable, MasterComponent {
	public void buildTreeFromXml(Element xml, Map<String, Object> map);
	
	public List<AbstractActionButton> getActionsOnMultipleSelection();
	
	public TableBinding getBinding();
	
	public String getUid();
	
	public void populateReferencesFromXml(Element xml, Map<String, Object> map);
	
	public void setActionsOnMultipleSelection(List<AbstractActionButton> actionsOnMultipleSelection);
	
	public void setBinding(TableBinding binding);
	
	public void setUid(String uid);

}