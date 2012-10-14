package org.opaeum.uim.userinteractionproperties.sections;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.swt.widgets.Event;
import org.opaeum.eclipse.uml.propertysections.base.AbstractStringPropertySection;
import org.opaeum.uim.panel.GridPanel;
import org.opaeum.uim.panel.PanelPackage;

public class GridPanelNumberOfColumnsSection extends AbstractStringPropertySection{
	@Override
	protected EStructuralFeature getFeature(){
		return PanelPackage.eINSTANCE.getGridPanel_NumberOfColumns();
	}
	@Override
	public String getLabelText(){
		return "Nubmer of Columns";
	}
	@Override
	protected String getFeatureAsString(EObject e){
		// TODO correct
		return getGridPanel().getNumberOfColumns() == null ? "2" : getGridPanel().getNumberOfColumns().toString();
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
}
