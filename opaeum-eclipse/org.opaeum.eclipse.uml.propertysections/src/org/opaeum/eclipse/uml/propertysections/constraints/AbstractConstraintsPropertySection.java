package org.opaeum.eclipse.uml.propertysections.constraints;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.uml2.uml.Constraint;
import org.opaeum.eclipse.uml.propertysections.base.AbstractMasterDetailSection;
import org.opaeum.eclipse.uml.propertysections.core.AbstractTableComposite;
import org.opaeum.eclipse.uml.propertysections.subsections.AbstractDetailsSubsection;

public abstract class AbstractConstraintsPropertySection extends AbstractMasterDetailSection<Constraint>{

	public AbstractConstraintsPropertySection(){
		super("Constraint Details");
	}
	@Override
	protected AbstractDetailsSubsection<Constraint> createDetails(Group elementDetailsGroup2){
		return new OclConstraintDetailsComposite(getWidgetFactory(),elementDetailsGroup2);
	}
	@Override
	protected AbstractTableComposite<Constraint> createTable(Composite composite){
		return new OclConstraintTable(getWidgetFactory(),composite,getFeature());
	}
}
