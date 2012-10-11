package org.opaeum.eclipse.uml.propertysections.subsections;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Composite;
import org.opaeum.eclipse.uml.propertysections.base.AbstractTabbedPropertySubsection;
import org.opaeum.eclipse.uml.propertysections.base.IMultiPropertySection;
import org.opaeum.eclipse.uml.propertysections.common.IChoiceProvider;
import org.opaeum.eclipse.uml.propertysections.common.OpaeumQualifiedNameLabelProvider;
import org.opaeum.eclipse.uml.propertysections.common.OpaeumSingleObjectChooser;
import org.opaeum.topcased.uml.editor.OpaeumItemProviderAdapterFactory;

public class ChooserSubsection extends AbstractTabbedPropertySubsection<Composite,EObject> implements SelectionListener{
	private IChoiceProvider choiceProvider;
	OpaeumSingleObjectChooser chooser;
	public ChooserSubsection(IMultiPropertySection section){
		super(section);
	}

	@Override
	public void setEnabled(boolean enabled){
		chooser.setEnabled(enabled);
	}
	public void widgetSelected(SelectionEvent e){
		super.updateModel();
	}
	public void widgetDefaultSelected(SelectionEvent e){
	}

	@Override
	protected EObject getNewValue(){
		return (EObject) chooser.getSelection();
	}

	@Override
	protected void populateControls(){
			chooser.setSelection(getCurrentValue());
	}
	@Override
	protected Composite createControl(Composite parent){
		chooser=new OpaeumSingleObjectChooser(parent, getWidgetFactory(), SWT.NONE);
		chooser.setChoiceProvider(choiceProvider);
		return chooser.getContentPane();
	}
	@Override
	public void hookControlListener(){
		chooser.addSelectionListener(this);
	}


	public void setVisible(boolean b){
		super.label.setVisible(b);
		getControl().setVisible(b);
	}


	public IChoiceProvider getChoiceProvider(){
		return choiceProvider;
	}


	public void setChoiceProvider(IChoiceProvider choiceProvider){
		this.choiceProvider = choiceProvider;
	}
}