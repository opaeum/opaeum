package org.opaeum.uim.component;

import java.util.List;

import org.opaeum.ecore.EObject;
import org.opaeum.runtime.domain.EcoreDataTypeParser;
import org.opaeum.runtime.environment.Environment;
import org.opaeum.uim.action.AbstractActionButton;
import org.opaeum.uim.binding.TableBinding;
import org.opaeum.uim.panel.Outlayable;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public interface UimDataTable extends EObject, UimContainer, Outlayable, MasterComponent {
	public void buildTreeFromXml(Element xml);
	
	public List<AbstractActionButton> getActionsOnMultipleSelection();
	
	public TableBinding getBinding();
	
	public String getUid();
	
	public void populateReferencesFromXml(Element xml);
	
	public void setActionsOnMultipleSelection(List<AbstractActionButton> actionsOnMultipleSelection);
	
	public void setBinding(TableBinding binding);
	
	public void setUid(String uid);

}