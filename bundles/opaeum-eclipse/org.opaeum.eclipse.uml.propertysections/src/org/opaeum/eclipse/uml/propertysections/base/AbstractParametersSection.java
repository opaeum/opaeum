package org.opaeum.eclipse.uml.propertysections.base;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.uml2.uml.Parameter;
import org.opaeum.eclipse.uml.propertysections.core.ParameterComposite;
import org.opaeum.eclipse.uml.propertysections.core.ParametersTableComposite;

public abstract class AbstractParametersSection extends AbstractMasterDetailSection<Parameter>{
	public AbstractParametersSection(){
		super("Parameter Details");
	}
	@Override
	protected ParameterComposite createDetails(Group elementDetailsGroup2){
		return new ParameterComposite(elementDetailsGroup2, SWT.NONE, getWidgetFactory());
	}
	@Override
	protected ParametersTableComposite createTable(Composite composite){
		return new ParametersTableComposite(composite, SWT.NONE, getWidgetFactory(), getFeature());
	}
}