package org.opaeum.eclipse.uml.propertysections.subsections;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.custom.TextChangeListener;
import org.eclipse.swt.custom.TextChangedEvent;
import org.eclipse.swt.custom.TextChangingEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.OpaqueExpression;
import org.opaeum.eclipse.EmfValueSpecificationUtil;
import org.opaeum.eclipse.uml.propertysections.base.AbstractTabbedPropertySubsection;
import org.opaeum.eclipse.uml.propertysections.base.IMultiPropertySection;
import org.opaeum.eclipse.uml.propertysections.ocl.OpaqueExpressionComposite;

/**
 * NB!! this class considers the opaqueExpression to be the value. As text changes, new OpaqueExpression will be created
 * @author ampie
 *
 */
public class OpaqueExpressionSubsection extends AbstractTabbedPropertySubsection<OpaqueExpressionComposite,OpaqueExpression> implements TextChangeListener{
	public OpaqueExpressionSubsection(IMultiPropertySection section){
		super(section);
	}
	
	private OpaqueExpressionComposite oclComposite;
	@Override
	protected OpaqueExpression getNewValue(){
		NamedElement selectedObject = (NamedElement)section.getSelectedObject();
		String name = getFeature().getName();
		StyledText textControl = getControl().getTextControl();
		return EmfValueSpecificationUtil.buildOpaqueExpression(selectedObject,name, textControl.getText());
	}
	@Override
	protected int getModelSubscriptionLevel(){
		return 2;
	}
	@Override
	protected void populateControls(){
		getControl().setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		getControl().setOclContext((NamedElement) getFeatureOwner(section.getSelectedObject()), getCurrentValue());
	}
	@Override
	public void hookControlListener(){
		getControl().addTextChangeListener(this);
	}
	@Override
	public void textChanged(TextChangedEvent event){
		updateModel();
	}
	@Override
	protected OpaqueExpressionComposite createControl(Composite parent){
		return new OpaqueExpressionComposite(parent, getWidgetFactory());
	}
	@Override
	public void textChanging(TextChangingEvent event){
	}
	@Override
	public void textSet(TextChangedEvent event){
		
	}
}
