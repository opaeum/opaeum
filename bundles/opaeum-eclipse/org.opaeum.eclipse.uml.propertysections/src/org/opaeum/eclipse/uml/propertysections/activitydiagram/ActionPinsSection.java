package org.opaeum.eclipse.uml.propertysections.activitydiagram;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.uml2.uml.Pin;
import org.eclipse.uml2.uml.UMLPackage;
import org.opaeum.eclipse.uml.propertysections.base.AbstractMasterDetailSection;
import org.opaeum.eclipse.uml.propertysections.core.AbstractTableComposite;
import org.opaeum.eclipse.uml.propertysections.subsections.AbstractDetailsSubsection;

public class ActionPinsSection extends AbstractMasterDetailSection<Pin>{
	public ActionPinsSection(){
		super("Input");
	}
	private PinTable table;
	private PinDetailsComposite details;
	@Override
	protected AbstractDetailsSubsection<Pin> createDetails(Group group){
		return details = new PinDetailsComposite(group, SWT.NONE, getWidgetFactory());
	}
	@Override
	protected AbstractTableComposite<Pin> createTable(Composite composite){
		return table=new PinTable(composite, SWT.NONE, getWidgetFactory(), getFeature());
	}
	@Override
	protected EStructuralFeature getFeature(){
		return UMLPackage.eINSTANCE.getAction_Input();
	}
}
