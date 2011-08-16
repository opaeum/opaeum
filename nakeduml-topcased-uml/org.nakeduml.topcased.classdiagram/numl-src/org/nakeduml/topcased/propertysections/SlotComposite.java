package org.nakeduml.topcased.propertysections;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.uml2.uml.OpaqueExpression;
import org.eclipse.uml2.uml.Slot;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.ValueSpecification;
import org.nakeduml.topcased.propertysections.ocl.OclBodyComposite;
import org.nakeduml.topcased.propertysections.ocl.OpaqueExpressionBodyComposite;

public class SlotComposite extends Composite{
	public final class SlotOclComposite extends OpaqueExpressionBodyComposite{
		private OpaqueExpression opaqueExpression;
		private SlotOclComposite(Composite parent,FormToolkit toolkit){
			super(parent, toolkit);
		}
		@Override
		public EReference getValueSpecificationFeature(){
			return UMLPackage.eINSTANCE.getSlot_Value();
		}
		@Override
		protected EditingDomain getEditingDomain(){
			return editingDomain;
		}
	}
	private FormToolkit toolkit;
	private EditingDomain editingDomain;
	private Composite values;
	private Composite buttons;
	private SlotOclComposite currentOclText;
	public SlotComposite(Composite parent,int style,FormToolkit toolkit,EditingDomain editingDomain){
		super(parent, SWT.NONE);
		GridLayout layout = new GridLayout(2, false);
		layout.marginHeight = 0;
		layout.marginWidth = 0;
		setLayout(layout);
		this.toolkit = toolkit;
		this.editingDomain = editingDomain;
	}
	public void setInput(final Slot slot){
		for(Control control:getChildren()){
			control.dispose();
		}
		values = toolkit.createComposite(this);
		values.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false));
		GridLayout layout = new GridLayout(1, false);
		values.setLayout(layout);
		layout.marginHeight = 0;
		layout.marginWidth = 0;
		if(slot.getDefiningFeature().getUpper() > 1 && false){
			buttons = toolkit.createComposite(this);
			Button create = toolkit.createButton(buttons, "Add Value", SWT.PUSH);
			create.addSelectionListener(new SelectionListener(){
				@Override
				public void widgetSelected(SelectionEvent e){
					OpaqueExpression oe = UMLFactory.eINSTANCE.createOpaqueExpression();
					Command add = AddCommand.create(editingDomain, slot, UMLPackage.eINSTANCE.getSlot_Value(), oe);
					editingDomain.getCommandStack().execute(add);
					createRow(slot, oe);
					values.layout();
				}
				@Override
				public void widgetDefaultSelected(SelectionEvent e){
				}
			});
			Button remove = toolkit.createButton(buttons, "Remove Value", SWT.PUSH);
			remove.addSelectionListener(new SelectionListener(){
				@Override
				public void widgetSelected(SelectionEvent e){
					if(getCurrentOclText() != null){
						Command remove = RemoveCommand.create(editingDomain, slot, UMLPackage.eINSTANCE.getSlot_Value(), getCurrentOclText().opaqueExpression);
						editingDomain.getCommandStack().execute(remove);
						getCurrentOclText().dispose();
						values.layout();
					}
				}
				@Override
				public void widgetDefaultSelected(SelectionEvent e){
				}
			});
			buttons.setLayout(new RowLayout(SWT.HORIZONTAL));
		}
		for(ValueSpecification valueSpecification:slot.getValues()){
			if(valueSpecification instanceof OpaqueExpression){
				final OpaqueExpression oe = (OpaqueExpression) valueSpecification;
				createRow(slot, oe);
			}
		}
		layout();
	}
	protected void createRow(final Slot slot,final OpaqueExpression oe){
		final SlotOclComposite oclText = new SlotOclComposite(values, this.toolkit);
		oclText.opaqueExpression = oe;
		oclText.setOclContext(slot.getOwningInstance(), slot, oe);
		oclText.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false));
		String t = OclBodyComposite.getOclText(oe.getBodies(), oe.getLanguages());
		if(t.endsWith(OclBodyComposite.DEFAULT_TEXT)){
			oclText.getTextControl().setText(OclBodyComposite.REQUIRED_TEXT);
		}else{
			oclText.getTextControl().setText(t);
		}
		oclText.getTextControl().addFocusListener(new FocusListener(){
			@Override
			public void focusLost(FocusEvent e){
			}
			@Override
			public void focusGained(FocusEvent e){
				setCurrentOclText(oclText);
			}
		});
		if(getCurrentOclText() != null){
			getCurrentOclText().setTabTo(oclText.getTextControl());
		}
		setCurrentOclText(oclText);
	}
	public SlotOclComposite getCurrentOclText(){
		return currentOclText;
	}
	public void setCurrentOclText(SlotOclComposite currentOclText){
		this.currentOclText = currentOclText;
	}
}
