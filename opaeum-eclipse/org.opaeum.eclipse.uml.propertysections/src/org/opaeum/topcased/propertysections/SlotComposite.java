package org.opaeum.topcased.propertysections;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.swt.SWT;
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
import org.eclipse.uml2.uml.StructuralFeature;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.ValueSpecification;
import org.opaeum.eclipse.EmfPropertyUtil;
import org.topcased.tabbedproperties.utils.TextChangeListener;

public class SlotComposite extends Composite{
	public static interface SlotValueListener{
		public void slotsUpdated(Collection<Slot> slots);
	}
	public static int MAX_ROWS = 5;
	public static int DEFAULT_ROW_HEIGHT = 25;
	private FormToolkit toolkit;
	private EditingDomain editingDomain;
	private SlotOclComposite currentOclText;
	private Control control;
	private SlotValueListener listener;
	public SlotComposite(Composite parent,int style,FormToolkit toolkit,EditingDomain editingDomain, SlotValueListener l){
		super(parent, SWT.BORDER);
		this.listener=l;
		GridLayout layout = new GridLayout(1, false);
		layout.marginHeight = 0;
		layout.marginWidth = 0;
		setBackground(parent.getBackground());
		setLayout(layout);
		this.toolkit = toolkit;
		this.editingDomain = editingDomain;
	}
	public void slotsUpdated(Collection<Slot> slots){
		if(this.listener!=null){
			this.listener.slotsUpdated(slots);
		}
		
	}
	public void setInput(List<Slot> slots,final StructuralFeature feature){
		for(Control control:getChildren()){
			control.dispose();
		}
		if(slots.size() >= 1){
			if(feature.getType() instanceof Enumeration){
				createEnumLiteralSelector(feature, slots);
			}else{
				if(onlyContainsValuesOfType(slots, OpaqueExpression.class)){
					createOclComposite(slots);
				}else if(onlyContainsValuesOfType(slots, LiteralString.class)){
					createStringTextField(slots);
				}else if(onlyContainsValuesOfType(slots, LiteralInteger.class)){
					createIntegerTextField(slots);
				}else if(onlyContainsValuesOfType(slots, LiteralBoolean.class)){
					createBooleanCheckbox(slots);
				}
			}
		}
		GridLayout layout = new GridLayout((int) Math.round(Math.ceil(getChildren().length / (double) MAX_ROWS)), false);
		layout.marginHeight = 0;
		layout.verticalSpacing = -2;
		layout.horizontalSpacing = 8;
		setLayout(layout);
	}
	protected void createEnumLiteralSelector(final StructuralFeature feature,final List<Slot> slots){
		final EList<EnumerationLiteral> ownedLiterals = ((Enumeration) feature.getType()).getOwnedLiterals();
		for(final EnumerationLiteral enumerationLiteral:ownedLiterals){
			final Button btn = toolkit.createButton(this, enumerationLiteral.getName(), EmfPropertyUtil.isMany(feature) ? SWT.CHECK : SWT.RADIO);
			setSelectionStatus(slots, enumerationLiteral, btn);
			btn.addSelectionListener(new SelectionListener(){
				@Override
				public void widgetSelected(SelectionEvent e){
					btn.setGrayed(false);
					CompoundCommand command = new CompoundCommand("Multiple Slot Updates");
					if(btn.getSelection()){
						for(Slot slot:slots){
							final InstanceValue iv = UMLFactory.eINSTANCE.createInstanceValue();
							iv.setInstance(enumerationLiteral);
							if(!EmfPropertyUtil.isMany(feature)){
								// Only one allowed
								command.append(RemoveCommand.create(editingDomain, slot, UMLPackage.eINSTANCE.getSlot_Value(), slot.getValues()));
								command.append(AddCommand.create(editingDomain, slot, UMLPackage.eINSTANCE.getSlot_Value(), iv));
							}else{
								boolean alreadyPresent = false;
								for(ValueSpecification vs:slot.getValues()){
									if(vs instanceof InstanceValue && ((InstanceValue) vs).getInstance() instanceof EnumerationLiteral
											&& enumerationLiteral.equals(((InstanceValue) vs).getInstance())){
										alreadyPresent = true;
										break;
									}
								}
								if(!alreadyPresent){
									command.append(AddCommand.create(editingDomain, slot, UMLPackage.eINSTANCE.getSlot_Value(), iv));
								}
							}
						}
					}else{
						for(Slot slot:slots){
							for(ValueSpecification vs:slot.getValues()){
								if(vs instanceof InstanceValue && ((InstanceValue) vs).getInstance() instanceof EnumerationLiteral
										&& enumerationLiteral.equals(((InstanceValue) vs).getInstance())){
									command.append(RemoveCommand.create(editingDomain, slot, UMLPackage.eINSTANCE.getSlot_Value(), vs));
								}
							}
						}
					}
					editingDomain.getCommandStack().execute(command);
					slotsUpdated(slots);
				}
				@Override
				public void widgetDefaultSelected(SelectionEvent e){
				}
			});
		}
	}
	protected void setSelectionStatus(final List<Slot> slots,final EnumerationLiteral enumerationLiteral,final Button btn){
		int selected = -2;
		outer:for(Slot slot:slots){
			for(ValueSpecification vs:slot.getValues()){
				if(vs instanceof InstanceValue && ((InstanceValue) vs).getInstance() instanceof EnumerationLiteral
						&& enumerationLiteral.equals(((InstanceValue) vs).getInstance())){
					if(selected == -2 || selected == 1){
						selected = 1;
					}else{
						selected = -1;
					}
					continue outer;
				}
			}
			if(selected == -2 || selected == 0){
				selected = 0;
			}else{
				selected = -1;
			}

		}
		switch(selected){
		case -1:
			btn.setGrayed(true);
			btn.setSelection(true);
			break;
		case 1:
			btn.setSelection(true);
			break;
		default:
			btn.setSelection(false);
		}
	}
	public int getBorderWidth(){
		return getChildren().length > 1 ? 1 : 0;
	}
	private boolean onlyContainsValuesOfType(final List<Slot> slots,Class<? extends ValueSpecification> c){
		int count =0;
		for(Slot slot:slots){
			for(ValueSpecification vs:slot.getValues()){
				count++;
				if(!c.isInstance(vs)){
					return false;
				}
			}
		}
		return count>0;
	}
	protected void createBooleanCheckbox(final List<Slot> slots){
		Boolean booleanValue = slots.get(0).getValues().get(0).booleanValue();
		outer:for(Slot slot:slots){
			for(ValueSpecification vs:slot.getValues()){
				if(vs.booleanValue() != booleanValue){
					booleanValue = null;
					break outer;
				}
			}
		}
		final Button chk = toolkit.createButton(this, "", SWT.CHECK | SWT.BORDER);
		if(booleanValue == null){
			chk.setGrayed(true);
			chk.setSelection(true);
		}else{
			chk.setSelection(booleanValue);
		}
		chk.addSelectionListener(new SelectionListener(){
			@Override
			public void widgetSelected(SelectionEvent e){
				chk.setGrayed(false);
				CompoundCommand command = new CompoundCommand();
				for(Slot slot:slots){
					for(ValueSpecification vs:slot.getValues()){
						command.append(SetCommand.create(editingDomain, vs, UMLPackage.eINSTANCE.getLiteralBoolean_Value(), chk.getSelection()));
					}
				}
				editingDomain.getCommandStack().execute(command);
				slotsUpdated(slots);
			}
			@Override
			public void widgetDefaultSelected(SelectionEvent e){
			}
		});
		control = chk;
		GridData ld = new GridData(SWT.FILL, SWT.FILL, true, true);
		control.setLayoutData(ld);
	}
	protected void createIntegerTextField(final List<Slot> slots){
		int integerValue = slots.get(0).getValues().get(0).integerValue();
		String string = integerValue + "";
		outer:for(Slot slot:slots){
			for(ValueSpecification vs:slot.getValues()){
				if(vs.integerValue() != integerValue){
					string = "";
					break outer;
				}
			}
		}
		final Text txt = toolkit.createText(this, string, SWT.BORDER);
		TextChangeListener l = new TextChangeListener(){
			@Override
			public void textChanged(Control control){
				parseint(txt);
			}
			private void parseint(final Text txt){
				try{
					int integerValue = Integer.parseInt(txt.getText());
					CompoundCommand command = new CompoundCommand();
					for(Slot slot:slots){
						for(ValueSpecification vs:slot.getValues()){
							command.append(SetCommand.create(editingDomain, vs, UMLPackage.eINSTANCE.getLiteralInteger_Value(), integerValue));
						}
					}
					editingDomain.getCommandStack().execute(command);
					slotsUpdated(slots);
				}catch(Exception e){
				}
			}
			@Override
			public void focusOut(Control control){
				parseint(txt);
			}
			@Override
			public void focusIn(Control control){
			}
		};
		l.startListeningTo(txt);
		GridData ld = new GridData(SWT.FILL, SWT.FILL, true, true);
		control = txt;
		control.setLayoutData(ld);
	}
	protected void createStringTextField(final List<Slot> slots){
		String string = slots.get(0).getValues().get(0).stringValue();
		outer:for(Slot slot:slots){
			for(ValueSpecification vs:slot.getValues()){
				if(vs.stringValue() == null || !vs.stringValue().equals(string)){
					string = "";
					break outer;
				}
			}
		}
		final Text txt = toolkit.createText(this, string, SWT.BORDER);
		TextChangeListener l = new TextChangeListener(){
			@Override
			public void textChanged(Control control){
				CompoundCommand command = new CompoundCommand();
				for(Slot slot:slots){
					for(ValueSpecification vs:slot.getValues()){
						command.append(SetCommand.create(editingDomain, vs, UMLPackage.eINSTANCE.getLiteralString_Value(), txt.getText()));
					}
				}
				editingDomain.getCommandStack().execute(command);
				slotsUpdated(slots);
			}
			@Override
			public void focusOut(Control control){
				textChanged(control);
			}
			@Override
			public void focusIn(Control control){
			}
		};
		l.startListeningTo(txt);
		GridData ld = new GridData(SWT.FILL, SWT.FILL, true, true);
		control = txt;
		control.setLayoutData(ld);
	}
	protected void createOclComposite(final List<Slot> slots){
		final SlotOclComposite oclText = new SlotOclComposite(this, this.toolkit);
		oclText.setOpaqueExpression(slots, editingDomain);
		GridData layoutData = new GridData(SWT.FILL, SWT.FILL, true, true);
		layoutData.minimumHeight = DEFAULT_ROW_HEIGHT;
		oclText.setLayoutData(layoutData);
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
