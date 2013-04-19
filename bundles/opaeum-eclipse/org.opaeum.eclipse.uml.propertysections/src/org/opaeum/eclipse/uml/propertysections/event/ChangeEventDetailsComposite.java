package org.opaeum.eclipse.uml.propertysections.event;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;
import org.eclipse.uml2.uml.ChangeEvent;
import org.eclipse.uml2.uml.UMLPackage;
import org.opaeum.eclipse.uml.propertysections.base.AbstractMultiFeaturePropertySection;
import org.opaeum.eclipse.uml.propertysections.base.AbstractOpaeumPropertySection;
import org.opaeum.eclipse.uml.propertysections.base.AbstractTabbedPropertySubsection;
import org.opaeum.eclipse.uml.propertysections.subsections.AbstractDetailsSubsection;
import org.opaeum.eclipse.uml.propertysections.subsections.InstanceValueSubsection;

public class ChangeEventDetailsComposite extends AbstractDetailsSubsection<ChangeEvent>{
	private InstanceValueSubsection changeComposite;
	private int labelWidth=AbstractOpaeumPropertySection.STANDARD_LABEL_WIDTH;
	public ChangeEventDetailsComposite(TabbedPropertySheetWidgetFactory widgetFactory,Composite parent){
		super(parent, SWT.NONE, widgetFactory);
	}
	public void setLabelWidth(int labelWidth){
		this.labelWidth=labelWidth;
		changeComposite.setLabelWidth(labelWidth);
		
	}
	@Override
	protected int getNumberOfColumns(){
		return 1;
	}
	@Override
	protected void addSubsections(){
		changeComposite = new InstanceValueSubsection(this);
		AbstractMultiFeaturePropertySection.populateSubsection(changeComposite, UMLPackage.eINSTANCE.getChangeEvent_ChangeExpression(), "Change Expression",
				labelWidth, AbstractTabbedPropertySubsection.FILL);
		changeComposite.setRowSpan(3);
	}
}
