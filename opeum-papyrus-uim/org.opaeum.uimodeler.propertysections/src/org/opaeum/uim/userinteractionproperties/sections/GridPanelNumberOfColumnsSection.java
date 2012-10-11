package org.opaeum.uim.userinteractionproperties.sections;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.swt.widgets.Event;
import org.opaeum.uim.panel.GridPanel;
import org.opaeum.uim.panel.PanelPackage;
import org.topcased.tabbedproperties.sections.AbstractTextPropertySection;

public class GridPanelNumberOfColumnsSection extends AbstractTextPropertySection{
	@Override
	protected EStructuralFeature getFeature(){
		return PanelPackage.eINSTANCE.getGridPanel_NumberOfColumns();
	}
	@Override
	public String getLabelText(){
		return "Nubmer of Columns";
	}
	@Override
	protected String getFeatureAsString(){
		return getGridPanel().getNumberOfColumns()==null?"2":getGridPanel().getNumberOfColumns().toString();
	}
	private GridPanel getGridPanel(){
		return (GridPanel) getEObject();
	}
	@Override
	protected Object getNewFeatureValue(String newText){
		try{
			return Integer.valueOf(newText);
		}catch(Exception e){
			return 2;
		}
	}
	@Override
	protected Object getOldFeatureValue(){
		return getGridPanel().getNumberOfColumns();
	}
	@Override
	protected void verifyField(Event e){
	
	}
}
