package org.opeum.topcased.propertysections;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.uml2.uml.Enumeration;
import org.eclipse.uml2.uml.EnumerationLiteral;
import org.eclipse.uml2.uml.InstanceValue;
import org.eclipse.uml2.uml.LiteralBoolean;
import org.eclipse.uml2.uml.LiteralInteger;
import org.eclipse.uml2.uml.LiteralString;
import org.eclipse.uml2.uml.OpaqueExpression;
import org.eclipse.uml2.uml.Slot;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.ValueSpecification;
import org.opeum.eclipse.EmfPropertyUtil;
import org.opeum.topcased.propertysections.ocl.OclBodyComposite;
import org.opeum.topcased.propertysections.ocl.OpaqueExpressionComposite;
import org.topcased.tabbedproperties.utils.TextChangeListener;

public class SlotComposite extends Composite{
	public static int MAX_ROWS=5;
	public static int DEFAULT_ROW_HEIGHT=25;
	public final class SlotOclComposite extends OpaqueExpressionComposite{
		private SlotOclComposite(Composite parent,FormToolkit toolkit){
			super(parent, toolkit,SWT.NONE);
			GridLayout layout = new GridLayout(1, false);
			layout.marginHeight = 0;
			layout.marginWidth = 0;
			layout.verticalSpacing=1;
			setLayout(layout);
		}
		@Override
		protected EditingDomain getEditingDomain(){
			return editingDomain;
		}
		public void setOpaqueExpression(OpaqueExpression oe){
			this.oclBodyOwner = oe;
		}
	}
	private FormToolkit toolkit;
	private EditingDomain editingDomain;
	private SlotOclComposite currentOclText;
	private Control control;
	public SlotComposite(Composite parent,int style,FormToolkit toolkit,EditingDomain editingDomain){
		super(parent, SWT.BORDER);
		GridLayout layout = new GridLayout(1, false);
		layout.marginHeight = 0;
		layout.marginWidth = 0;
		setBackground(parent.getBackground());
		setLayout(layout);
		this.toolkit = toolkit;
		this.editingDomain = editingDomain;
	}
	public void setInput(final Slot slot){
		for(Control control:getChildren()){
			control.dispose();
		}
		if(EmfPropertyUtil.isMany(slot.getDefiningFeature()) && slot.getOwner() instanceof EnumerationLiteral && slot.getDefiningFeature().getType() instanceof Enumeration){
			final EList<EnumerationLiteral> ownedLiterals = ((Enumeration) slot.getDefiningFeature().getType()).getOwnedLiterals();
			for(final EnumerationLiteral enumerationLiteral:ownedLiterals){
				final Button btn = toolkit.createButton(this, enumerationLiteral.getName(), SWT.CHECK);
				for(ValueSpecification vs:slot.getValues()){
					if(vs instanceof InstanceValue && ((InstanceValue) vs).getInstance() instanceof EnumerationLiteral
							&& enumerationLiteral.equals(((InstanceValue) vs).getInstance())){
						btn.setSelection(true);
					}
				}
				btn.addSelectionListener(new SelectionListener(){
					@Override
					public void widgetSelected(SelectionEvent e){
						if(btn.getSelection()){
							final InstanceValue iv = UMLFactory.eINSTANCE.createInstanceValue();
							iv.setInstance(enumerationLiteral);
							editingDomain.getCommandStack().execute(AddCommand.create(editingDomain, slot, UMLPackage.eINSTANCE.getSlot_Value(), iv));
						}else{
							for(ValueSpecification vs:slot.getValues()){
								if(vs instanceof InstanceValue && ((InstanceValue) vs).getInstance() instanceof EnumerationLiteral
										&& enumerationLiteral.equals(((InstanceValue) vs).getInstance())){
									editingDomain.getCommandStack().execute(RemoveCommand.create(editingDomain, slot, UMLPackage.eINSTANCE.getSlot_Value(), vs));
								}
							}
						}
					}
					@Override
					public void widgetDefaultSelected(SelectionEvent e){
					}
				});
			}
		}else{
			for(final ValueSpecification valueSpecification:slot.getValues()){
				createRow(slot, valueSpecification);
			}
		}
		GridLayout layout = new GridLayout((int)Math.round(Math.ceil(getChildren().length/(double)MAX_ROWS)), false);
		layout.marginHeight = 0;
		layout.marginWidth = 0;
		layout.verticalSpacing=-2;
		layout.horizontalSpacing=8;
		setLayout(layout);
	}
	public int getBorderWidth(){
		return getChildren().length>1?1:0;
	}
	private void createRow(final Slot slot,final ValueSpecification valueSpecification){
		if(valueSpecification instanceof OpaqueExpression){
			final OpaqueExpression oe = (OpaqueExpression) valueSpecification;
			createRow(slot, oe);
		}else if(valueSpecification instanceof LiteralString){
			final LiteralString literalString = (LiteralString) valueSpecification;
			final Text txt = toolkit.createText(this, literalString.getValue() + "", SWT.BORDER);
			TextChangeListener l = new TextChangeListener(){
				@Override
				public void textChanged(Control control){
					literalString.setValue(txt.getText());
				}
				@Override
				public void focusOut(Control control){
					literalString.setValue(txt.getText());
				}
				@Override
				public void focusIn(Control control){
				}
			};
			l.startListeningTo(txt);
			GridData ld = new GridData(SWT.FILL, SWT.FILL, true, true);
			control = txt;
			control.setLayoutData(ld);
		}else if(valueSpecification instanceof LiteralInteger){
			final LiteralInteger literalString = (LiteralInteger) valueSpecification;
			final Text txt = toolkit.createText(this, literalString.getValue() + "", SWT.BORDER);
			TextChangeListener l = new TextChangeListener(){
				@Override
				public void textChanged(Control control){
					parseint(literalString, txt);
				}
				private void parseint(final LiteralInteger literalString,final Text txt){
					try{
						literalString.setValue(Integer.parseInt(txt.getText()));
					}catch(Exception e){
					}
				}
				@Override
				public void focusOut(Control control){
					parseint(literalString, txt);
				}
				@Override
				public void focusIn(Control control){
				}
			};
			l.startListeningTo(txt);
			GridData ld = new GridData(SWT.FILL, SWT.FILL, true, true);
			control = txt;
			control.setLayoutData(ld);
		}else if(valueSpecification instanceof LiteralBoolean){
			final LiteralBoolean literalString = (LiteralBoolean) valueSpecification;
			final Button chk = toolkit.createButton(this, "", SWT.CHECK | SWT.BORDER);
			chk.setSelection(literalString.booleanValue());
			chk.addSelectionListener(new SelectionListener(){
				@Override
				public void widgetSelected(SelectionEvent e){
					literalString.setValue(chk.getSelection());
				}
				@Override
				public void widgetDefaultSelected(SelectionEvent e){
				}
			});
			control = chk;
			GridData ld = new GridData(SWT.FILL, SWT.FILL, true, true);
			control.setLayoutData(ld);
		}else if(valueSpecification instanceof InstanceValue){
			final InstanceValue literalString = (InstanceValue) valueSpecification;
			if(literalString.getInstance() instanceof EnumerationLiteral){
				final EnumerationLiteral lit = (EnumerationLiteral) literalString.getInstance();
				final CCombo cbo = new CCombo(this, SWT.READ_ONLY);
				for(EnumerationLiteral enumerationLiteral:lit.getEnumeration().getOwnedLiterals()){
					cbo.add(enumerationLiteral.getName());
				}
				cbo.setText(lit.getName());
				cbo.addSelectionListener(new SelectionListener(){
					@Override
					public void widgetSelected(SelectionEvent e){
						literalString.setInstance(lit.getEnumeration().getOwnedLiteral(cbo.getText()));
					}
					@Override
					public void widgetDefaultSelected(SelectionEvent e){
					}
				});
				control = cbo;
				GridData ld = new GridData(SWT.FILL, SWT.FILL, true, true);
				control.setLayoutData(ld);
			}
		}
	}
	protected void createRow(final Slot slot,final OpaqueExpression oe){
		final SlotOclComposite oclText = new SlotOclComposite(this, this.toolkit);
		oclText.setOpaqueExpression(oe);
		oclText.setOclContext(slot.getOwningInstance(), oe);
		GridData layoutData = new GridData(SWT.FILL, SWT.FILL, true, true);
		layoutData.minimumHeight = DEFAULT_ROW_HEIGHT;
		oclText.setLayoutData(layoutData);
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
		this.control = currentOclText.getTextControl();
	}
	public Control getControl(){
		return control;
	}
}
