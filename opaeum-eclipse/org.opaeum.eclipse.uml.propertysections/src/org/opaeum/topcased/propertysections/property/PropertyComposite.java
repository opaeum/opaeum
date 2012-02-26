package org.opaeum.topcased.propertysections.property;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;
import org.eclipse.uml2.uml.DataType;
import org.eclipse.uml2.uml.Enumeration;
import org.eclipse.uml2.uml.PrimitiveType;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.UMLPackage;
import org.opaeum.eclipse.uml.propertysections.common.OpaeumQualifiedNameLabelProvider;
import org.opaeum.eclipse.uml.propertysections.common.TextChangeHelper;
import org.opaeum.topcased.propertysections.UmlMetaTypeRemover;
import org.opaeum.topcased.uml.editor.OpaeumItemProviderAdapterFactory;
import org.topcased.tabbedproperties.sections.widgets.CSingleObjectChooser;
import org.topcased.tabbedproperties.utils.TypeCacheAdapter;

public class PropertyComposite extends Composite{
	private Property parameter;
	private EditingDomain mixedEditDomain;
	private TabbedPropertySheetWidgetFactory widgetFactory;
	private Text propertyNameTxt;
	private CSingleObjectChooser propertyType;
	public PropertyComposite(Composite parent,int style,TabbedPropertySheetWidgetFactory widgetFactory){
		super(parent, style);
		this.widgetFactory = widgetFactory;
		setLayout(new GridLayout(4, true));
		setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		widgetFactory.adapt(this);
		createContents(this);
		hookListeners();
		setEnabled(this, false);
	}
	private void setEnabled(Composite control,boolean enable){
		for(Control c:control.getChildren()){
			c.setEnabled(enable);
			if(c instanceof Composite){
				Composite com = (Composite) c;
				setEnabled(com, enable);
			}
		}
	}
	public void setProperty(Property parameter){
		setEnabled(this, parameter != null);
		this.parameter = parameter;
		loadData();
	}
	public void setEditingDomain(EditingDomain mixedEditDomain){
		this.mixedEditDomain = mixedEditDomain;
	}
	protected void createContents(Composite parent){
		widgetFactory.createLabel(parent, "Name : ");
		propertyNameTxt = widgetFactory.createText(parent, "", SWT.BORDER);
		propertyNameTxt.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		widgetFactory.createLabel(parent, "Type : ");
		propertyType = new CSingleObjectChooser(parent, widgetFactory, SWT.NONE);
		propertyType.setAdvancedLabelProvider(new OpaeumQualifiedNameLabelProvider(new OpaeumItemProviderAdapterFactory()));
		propertyType.setLabelProvider(new AdapterFactoryLabelProvider(new OpaeumItemProviderAdapterFactory()));
		propertyType.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
	}
	private void hookListeners(){
		TextChangeHelper parameterNameListener = new TextChangeHelper(){
			public void textChanged(Control control){
				String newText = propertyNameTxt.getText();
				if(parameter != null && !newText.equals(parameter.getName())){
					mixedEditDomain.getCommandStack().execute(
							SetCommand.create(mixedEditDomain, parameter, UMLPackage.eINSTANCE.getNamedElement_Name(), newText));
				}
			}
		};
		parameterNameListener.startListeningTo(propertyNameTxt);
		parameterNameListener.startListeningForEnter(propertyNameTxt);
		propertyType.addSelectionListener(new SelectionAdapter(){
			public void widgetSelected(SelectionEvent event){
				Object selectedElement = propertyType.getSelection();
				if(selectedElement != null && !selectedElement.equals(parameter.getType())){
					mixedEditDomain.getCommandStack().execute(
							SetCommand.create(mixedEditDomain, parameter, UMLPackage.eINSTANCE.getTypedElement_Type(), selectedElement));
				}
			}
		});
	}
	private void loadData(){
		if(parameter != null){
			String nameToDisplay = parameter.getName() != null ? parameter.getName() : "";
			propertyNameTxt.setText(nameToDisplay);
			propertyType.setChoices(getChoices());
			if(parameter.getType() != null){
				propertyType.setSelection(parameter.getType());
			}
		}else{
			propertyNameTxt.setText("");
			propertyType.setSelection(null);
		}
	}
	private Object[] getChoices(){
		List<Object> choices = new ArrayList<Object>();
		choices.add("");
		Collection<EObject> types = TypeCacheAdapter.getExistingTypeCacheAdapter(parameter).getReachableObjectsOfType(parameter,
				UMLPackage.eINSTANCE.getType());
		Collection<? extends Object> removeAll = UmlMetaTypeRemover.removeAll(types);
		Iterator<? extends Object> iterator = removeAll.iterator();
		while(iterator.hasNext()){
			Object object = (Object) iterator.next();
			boolean isApplicableToQualifiers = isQualifierType(object);
			if(!isApplicableToQualifiers){
				iterator.remove();
			}
		}
		choices.addAll(removeAll);
		return choices.toArray();
	}
	private boolean isQualifierType(Object object){
		boolean isApplicableToQualifiers = true;
		if(object instanceof Class || object instanceof Enumeration || object instanceof PrimitiveType){
			// TODO filter out methods and screenflows
			isApplicableToQualifiers = true;
		}else{
			if(object instanceof DataType){
				for(Property property:((DataType) object).getAllAttributes()){
					if(!property.isDerived()){
						isApplicableToQualifiers = false;
						break;
					}
				}
			}
		}
		return isApplicableToQualifiers;
	}
}