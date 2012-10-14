package org.opaeum.uim.userinteractionproperties.uimcontrol;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;
import org.opaeum.eclipse.uml.propertysections.common.TextChangeListener;
import org.opaeum.uim.control.ControlPackage;
import org.opaeum.uim.control.UimLookup;
import org.opaeum.uim.userinteractionproperties.binding.BindingHelper;
import org.opaeum.uim.userinteractionproperties.sections.TextControlAdapter;

public class UimLookupFeaturesSection<T extends UimLookup> extends ControlFeaturesComposite<T>{
	BindingHelper bindingHelper = new BindingHelper(ControlPackage.eINSTANCE.getUimLookup_LookupSource());
	Text text;
	CLabel label;
	private TextControlAdapter adapter;
	public UimLookupFeaturesSection(Composite parent,int style){
		super(parent, style);
	}
	@Override
	public void createContent(){
		super.createContent();
		setLayout(new GridLayout(2, false));
		label = new CLabel(this, SWT.NONE);
		label.setText("Lookup Source");
		GridData gd = new GridData(GridData.BEGINNING, GridData.CENTER, false, false);
		gd.widthHint = 143;
		label.setLayoutData(gd);
		//TODO get widgetfactorty
		text=new Text(this,SWT.BORDER);
		adapter = new TextControlAdapter(text, bindingHelper);
		new TextChangeListener(){
			@Override
			public void textChanged(Control control){
				Command cmd = SetCommand.create(editingDomain, UimLookupFeaturesSection.this.control, ControlPackage.eINSTANCE.getUimLookup_LookupSource(),
						bindingHelper.getNewFeatureValue(text.getText()));
				editingDomain.getCommandStack().execute(cmd);
			}
			@Override
			public void focusOut(Control control){
			}
			@Override
			public void focusIn(Control control){
			}
		}.startListeningForEnter(text);
	}
	@Override
	public void refresh(){
		super.refresh();
		bindingHelper.setOwner(super.control);
		text.setText(bindingHelper.getFeatureAsString());
	}
}
