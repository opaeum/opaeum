package org.opaeum.eclipse.uml.editingsupport;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.viewers.DialogCellEditor;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;
import org.eclipse.uml2.common.edit.provider.IItemQualifiedTextProvider;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.edit.providers.UMLItemProviderAdapterFactory;
import org.topcased.facilities.dialogs.ChooseDialog;

public abstract class ObjectChooserCompositeCellEditor extends DialogCellEditor{
	private TabbedPropertySheetWidgetFactory toolkit;
	public ObjectChooserCompositeCellEditor(Composite parent,TabbedPropertySheetWidgetFactory toolkit){
		super();
		this.toolkit = toolkit;
		create(parent);
	}
	protected abstract Object[] getChoices();
	protected ILabelProvider getLabelProvider(){
		return new AdapterFactoryLabelProvider(new UMLItemProviderAdapterFactory());
	}
	protected ILabelProvider getAdvancedLabeProvider(){
		return new AdapterFactoryLabelProvider(new UMLItemProviderAdapterFactory()){
			public String getText(Object object){
				IItemQualifiedTextProvider itemQualifiedTextProvider = (IItemQualifiedTextProvider) adapterFactory.adapt(object,
						IItemQualifiedTextProvider.class);
				return itemQualifiedTextProvider != null ? itemQualifiedTextProvider.getQualifiedText(object) : super.getText(object);
			}
		};
	}
	@Override
	protected void updateContents(Object value){
		super.updateContents(value==null?null:((NamedElement)value).getName());
	}
	@Override
	protected Object openDialogBox(Control cellEditorWindow){
		ChooseDialog dialog = new ChooseDialog(cellEditorWindow.getShell(), getChoices());
		dialog.setLabelProvider(getLabelProvider());
		dialog.setAdvancedLabelProvider(getAdvancedLabeProvider());
		List<Object> selectedObjects = new ArrayList<Object>();
		selectedObjects.add(getValue());
		dialog.setInitialElementSelections(selectedObjects);
		if(dialog.open() == Window.OK){
			Object[] selection = dialog.getResult();
			if(selection != null && selection.length > 0){
				return selection[0];
			}
		}
		return null;
	}
}
