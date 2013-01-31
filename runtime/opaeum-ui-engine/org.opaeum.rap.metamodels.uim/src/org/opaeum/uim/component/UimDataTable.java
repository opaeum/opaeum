package org.opaeum.uim.component;

import java.util.List;

import org.opaeum.ecore.EObject;
import org.opaeum.uim.Labels;
import org.opaeum.uim.action.AbstractActionButton;
import org.opaeum.uim.binding.TableBinding;
import org.opaeum.uim.panel.Outlayable;

public interface UimDataTable extends EObject, UimContainer, Outlayable, MasterComponent {
	public List<AbstractActionButton> getActionsOnMultipleSelection();
	
	public TableBinding getBinding();
	
	public Labels getLabelOverride();
	
	public void setActionsOnMultipleSelection(List<AbstractActionButton> actionsOnMultipleSelection);
	
	public void setBinding(TableBinding binding);
	
	public void setLabelOverride(Labels labelOverride);

}