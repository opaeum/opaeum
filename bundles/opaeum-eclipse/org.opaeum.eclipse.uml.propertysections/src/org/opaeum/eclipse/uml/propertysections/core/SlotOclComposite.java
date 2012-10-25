package org.opaeum.eclipse.uml.propertysections.core;

import java.util.List;

import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.uml2.uml.OpaqueExpression;
import org.eclipse.uml2.uml.Slot;
import org.eclipse.uml2.uml.ValueSpecification;
import org.opaeum.eclipse.commands.SetOclBodyCommand;
import org.opaeum.eclipse.uml.propertysections.ocl.OpaqueExpressionComposite;

public final class SlotOclComposite extends OpaqueExpressionComposite{
	private EditingDomain editingDomain;
	private List<Slot> slots;
	public SlotOclComposite(Composite parent,FormToolkit toolkit){
		super(parent, toolkit, SWT.NONE);
		GridLayout layout = new GridLayout(1, false);
		layout.marginHeight = 0;
		layout.marginWidth = 0;
		layout.verticalSpacing = 1;
		setLayout(layout);
	}
	protected void fireOclChanged(String text){
		if(containsExpression(text)){
			CompoundCommand command = new CompoundCommand();
			for(Slot slot:slots){
				EList<ValueSpecification> values = slot.getValues();
				for(ValueSpecification valueSpecification:values){
					command.append(SetOclBodyCommand.create(getEditingDomain(), valueSpecification, getBodiesFeature(), getLanguagesFeature(), text));
				}
			}
			getEditingDomain().getCommandStack().execute(command);
		}
		Display.getDefault().timerExec(1000, highlighter);
	}
	@Override
	protected EditingDomain getEditingDomain(){
		return editingDomain;
	}
	public void setOpaqueExpression(List<Slot> slots,EditingDomain ed){
		this.slots = slots;
		OpaqueExpression oe = (OpaqueExpression) slots.get(0).getValues().get(0);
		setOclContext(slots.get(0).getOwningInstance(), oe);
		String ocl = getOclText(oe.getBodies(), oe.getLanguages());
		outer:for(Slot slot:slots){
			for(ValueSpecification vs:slot.getValues()){
				OpaqueExpression oe2 = (OpaqueExpression) vs;
				String ocl2 = getOclText(oe2.getBodies(), oe2.getLanguages());
				if(ocl2 == null || !ocl2.equals(ocl)){
					ocl = null;
					break outer;
				}
			}
		}
		if(ocl != null){
			getTextControl().setText(ocl);
		}else{
			getTextControl().setText("");
		}
		this.oclBodyOwner = oe;
		this.editingDomain = ed;
	}
}