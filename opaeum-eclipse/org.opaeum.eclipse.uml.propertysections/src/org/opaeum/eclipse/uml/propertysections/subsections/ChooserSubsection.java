package org.opaeum.eclipse.uml.propertysections.subsections;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.opaeum.eclipse.uml.propertysections.base.AbstractTabbedPropertySubsection;
import org.opaeum.eclipse.uml.propertysections.base.IMultiPropertySection;
import org.opaeum.eclipse.uml.propertysections.common.IChoiceProvider;
import org.opaeum.eclipse.uml.propertysections.common.OpaeumObjectChooser;

public class ChooserSubsection extends AbstractTabbedPropertySubsection<Composite,Object> implements ISelectionChangedListener{
	private IChoiceProvider choiceProvider;
	OpaeumObjectChooser chooser;
	private boolean isSingle;
	public ChooserSubsection(IMultiPropertySection section){
		super(section);
	}
	@Override
	public void setEnabled(boolean enabled){
		chooser.setEnabled(enabled);
	}
	@Override
	protected Object getNewValue(){
		if(isSingle){
			return (EObject) chooser.getSelectedObject();
		}else{
			return chooser.getSelection().toList();
		}
	}
	@Override
	protected void populateControls(){
		Object c = getCurrentValue();
		if(c instanceof EObject){
			chooser.setSelection((EObject) c);
		}else if(c instanceof List){
			chooser.setSelection(new StructuredSelection((List<?>) c));
		}
	}
	@Override
	protected Composite createControl(Composite parent){
		chooser = new OpaeumObjectChooser(parent, getWidgetFactory(), SWT.NONE);
		chooser.setChoiceProvider(choiceProvider);
		chooser.setSingle(isSingle);
		return chooser.getContentPane();
	}
	@Override
	public void hookControlListener(){
		chooser.addSelectionChangedListener(this);
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
	protected Command buildCommand(EObject selection,EObject featureOwner){
		if(isSingle){
			Command cmd = SetCommand.create(section.getEditingDomain(), featureOwner, getFeature(), getNewValue());
			return cmd;
		}else{
			CompoundCommand cmd = new CompoundCommand();
			if(((Collection<?>) getCurrentValue()).size() > 0){
				cmd.append(RemoveCommand.create(section.getEditingDomain(), featureOwner, getFeature(), getCurrentValue()));
			}
			cmd.append(AddCommand.create(section.getEditingDomain(), featureOwner, getFeature(), getNewValue()));
			return cmd;
		}
	}
	@Override
	public void selectionChanged(SelectionChangedEvent event){
		super.updateModel();
	}
	public void setSingle(boolean b){
		this.isSingle = b;
		if(chooser != null){
			chooser.setSingle(b);
		}
	}
}