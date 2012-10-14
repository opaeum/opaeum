package org.opaeum.eclipse.uml.propertysections.base;

import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;
import org.eclipse.uml2.common.edit.provider.IItemQualifiedTextProvider;
import org.eclipse.uml2.uml.CallEvent;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.SignalEvent;
import org.eclipse.uml2.uml.Trigger;
import org.opaeum.eclipse.uml.propertysections.common.IChoiceProvider;
import org.opaeum.eclipse.uml.propertysections.common.OpaeumObjectChooser;
import org.opaeum.topcased.uml.editor.OpaeumItemProviderAdapterFactory;

public abstract class EventSourceChooserComposite extends Composite implements IChoiceProvider{
	protected Trigger trigger;
	protected CLabel label;
	protected OpaeumObjectChooser cSingleObjectChooser;
	public EventSourceChooserComposite(Composite parent,String label,TabbedPropertySheetWidgetFactory toolkit,int labelWidth){
		super(parent, SWT.NONE);
		setBackground(parent.getBackground());
		super.setLayout(new FormLayout());
		FormData fd = new FormData();
		this.label = toolkit.createCLabel(this, "Choose " + label);
		this.label.setLayoutData(fd);
		cSingleObjectChooser = new OpaeumObjectChooser(this, toolkit, SWT.NONE);
		cSingleObjectChooser.setChoiceProvider(this);
		fd = new FormData();
		fd.left = new FormAttachment(0, labelWidth);
		fd.right = new FormAttachment(100, 0);
		cSingleObjectChooser.getContentPane() .setLayoutData(fd);
		cSingleObjectChooser.addSelectionChangedListener(new ISelectionChangedListener(){
			@Override
			public void selectionChanged(SelectionChangedEvent event){
				if(cSingleObjectChooser.getSelection().isEmpty()){
					cSingleObjectChooser.setSelection(getCause());
				}else{
					updateElement();
				}
			}
		});
		toolkit.adapt(this);
	}
	protected abstract void updateElement();
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
		return new AdapterFactoryLabelProvider(new OpaeumItemProviderAdapterFactory());
	}
	protected ILabelProvider getAdvancedLabeProvider(){
		return new AdapterFactoryLabelProvider(new OpaeumItemProviderAdapterFactory()){
			public String getText(Object object){
				IItemQualifiedTextProvider itemQualifiedTextProvider = (IItemQualifiedTextProvider) adapterFactory.adapt(object, IItemQualifiedTextProvider.class);
				return itemQualifiedTextProvider != null ? itemQualifiedTextProvider.getQualifiedText(object) : super.getText(object);
			}
		};
	}
	public void setTrigger(Trigger t){
		this.trigger = t;
	}
}