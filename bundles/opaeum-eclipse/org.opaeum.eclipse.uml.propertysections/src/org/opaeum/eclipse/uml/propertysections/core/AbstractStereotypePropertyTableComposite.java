package org.opaeum.eclipse.uml.propertysections.core;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Stereotype;

public abstract class AbstractStereotypePropertyTableComposite<T extends EObject> extends AbstractTableComposite<T>{
	Stereotype stereotype;
	String featureName;
	public AbstractStereotypePropertyTableComposite(Composite parent,int style,TabbedPropertySheetWidgetFactory widgetFactory,
			String featureName){
		super(parent, style, widgetFactory, null);
		this.featureName = featureName;
	}
	@Override
	public void setOwner(EObject owner){
		feature = owner.eClass().getEStructuralFeature(featureName);
		super.setOwner(owner);
	}
	protected void addNew(){
		Command addCommand = AddCommand.create(editingDomain, owner, feature, getNewChild());
		editingDomain.getCommandStack().execute(addCommand);
	}
	protected void removeOld(Object object){
		editingDomain.getCommandStack().execute(RemoveCommand.create(editingDomain, object));
	}
}
