package org.nakeduml.topcased.propertysections;

import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;
import org.eclipse.uml2.common.edit.provider.IItemQualifiedTextProvider;
import org.eclipse.uml2.uml.AcceptEventAction;
import org.eclipse.uml2.uml.CallEvent;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.SignalEvent;
import org.eclipse.uml2.uml.Trigger;
import org.eclipse.uml2.uml.TypedElement;
import org.eclipse.uml2.uml.edit.providers.UMLItemProviderAdapterFactory;
import org.nakeduml.eclipse.EmfParameterUtil;
import org.nakeduml.topcased.uml.editor.NakedUmlElementLinker;
import org.topcased.tabbedproperties.sections.widgets.CSingleObjectChooser;

public abstract class ObjectChooserComposite extends Composite{
	protected Trigger trigger;
	protected CLabel label;
	protected CSingleObjectChooser cSingleObjectChooser;
	private Button synchronizeButton;
	public ObjectChooserComposite(Composite parent,String label,TabbedPropertySheetWidgetFactory toolkit,int labelWidth){
		super(parent, SWT.NONE);
		setBackground(parent.getBackground());
		super.setLayout(new FormLayout());
		FormData fd = new FormData();
		this.label = toolkit.createCLabel(this, "Choose " + label);
		this.label.setLayoutData(fd);
		cSingleObjectChooser = new CSingleObjectChooser(this, toolkit, SWT.NONE);
		cSingleObjectChooser.setLabelProvider(getLabelProvider());
		cSingleObjectChooser.setAdvancedLabelProvider(getAdvancedLabeProvider());
		fd = new FormData();
		fd.left = new FormAttachment(0, labelWidth);
		fd.right = new FormAttachment(100, 0);
		cSingleObjectChooser.setLayoutData(fd);
		synchronizeButton = toolkit.createButton(this, "Synchronize result pins with " + label, SWT.PUSH);
		FormData buttonFormData = new FormData();
		buttonFormData.top = new FormAttachment(cSingleObjectChooser);
		buttonFormData.left = new FormAttachment(0, labelWidth);
		synchronizeButton.setLayoutData(buttonFormData);
		synchronizeButton.addSelectionListener(new SelectionListener(){
			@Override
			public void widgetSelected(SelectionEvent e){
				AcceptEventAction action = (AcceptEventAction) trigger.getOwner();
				action.getResults().clear();
				for(TypedElement t:EmfParameterUtil.getArguments(getCause())){
					NakedUmlElementLinker.setOutputpin(t, action.getResults(),false);
				}
			}
			@Override
			public void widgetDefaultSelected(SelectionEvent e){
			}
		});
		cSingleObjectChooser.addSelectionListener(new SelectionAdapter(){
			@Override
			public void widgetSelected(SelectionEvent e){
				if(cSingleObjectChooser.getSelection()==null){
					cSingleObjectChooser.setSelection(getCause());
				}else{
					updateTrigger();
				}
				synchronizeButton.setEnabled(getCause() != null);
			}
		});
		toolkit.adapt(this);
	}
	protected abstract void updateTrigger();
	protected Element getCause(){
		Element org = null;
		if(trigger.getEvent() instanceof SignalEvent){
			org = ((SignalEvent) trigger.getEvent()).getSignal();
		}else if(trigger.getEvent() instanceof CallEvent){
			org = ((CallEvent) trigger.getEvent()).getOperation();
		}
		return org;
	}
	protected ILabelProvider getLabelProvider(){
		return new AdapterFactoryLabelProvider(new UMLItemProviderAdapterFactory());
	}
	protected ILabelProvider getAdvancedLabeProvider(){
		return new AdapterFactoryLabelProvider(new UMLItemProviderAdapterFactory()){
			public String getText(Object object){
				IItemQualifiedTextProvider itemQualifiedTextProvider = (IItemQualifiedTextProvider) adapterFactory.adapt(object, IItemQualifiedTextProvider.class);
				return itemQualifiedTextProvider != null ? itemQualifiedTextProvider.getQualifiedText(object) : super.getText(object);
			}
		};
	}
	public void setTrigger(Trigger t){
		this.trigger = t;
		synchronizeButton.setEnabled(getCause() != null);
	}
}