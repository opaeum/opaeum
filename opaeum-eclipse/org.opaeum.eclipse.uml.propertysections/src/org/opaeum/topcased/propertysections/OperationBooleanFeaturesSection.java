package org.opaeum.topcased.propertysections;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.UMLPackage;
import org.opaeum.topcased.propertysections.base.AbstractMultiFeaturePropertySection;

public class OperationBooleanFeaturesSection extends AbstractMultiFeaturePropertySection{
	private Button isQuery;
	private Button isStatic;
	private Button isAbstract;
	protected void setSectionData(Composite composite){
		layout(null, isQuery,140);
		layout(isQuery, isStatic,140);
		layout(isStatic, isAbstract,140);
	}
	private Operation getProperty(){
		return (Operation) getEObject();
	}
	@Override
	protected void createWidgets(Composite composite){
		super.createWidgets(composite);
		isQuery = getWidgetFactory().createButton(composite, "Is Query", SWT.CHECK);
		isStatic = getWidgetFactory().createButton(composite, "Is Static", SWT.CHECK);
		isAbstract = getWidgetFactory().createButton(composite, "Is Abstract", SWT.CHECK);
	}
	protected void hookListeners(){
		isQuery.addSelectionListener(new BooleanSelectionListener(UMLPackage.eINSTANCE.getOperation_IsQuery()));
		isStatic.addSelectionListener(new BooleanSelectionListener(UMLPackage.eINSTANCE.getFeature_IsStatic()));
		isAbstract.addSelectionListener(new BooleanSelectionListener(UMLPackage.eINSTANCE.getBehavioralFeature_IsAbstract()));
	}
	@Override
	protected String getLabelText(){
		return "";
	}
	@Override
	public void refresh(){
		isQuery.setSelection(getProperty().isQuery());
		isStatic.setSelection(getProperty().isStatic());
		isAbstract.setSelection(getProperty().isAbstract());
	}
}
