package org.nakeduml.topcased.activitydiagram.propertysections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
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
import org.eclipse.uml2.uml.OpaqueExpression;
import org.eclipse.uml2.uml.Pin;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.ValuePin;
import org.eclipse.uml2.uml.edit.providers.UMLItemProviderAdapterFactory;
import org.nakeduml.topcased.propertysections.UmlMetaTypeRemover;
import org.nakeduml.topcased.propertysections.ocl.OpaqueExpressionBodyComposite;
import org.topcased.modeler.editor.properties.TextChangeHelper;
import org.topcased.tabbedproperties.sections.widgets.CSingleObjectChooser;
import org.topcased.tabbedproperties.utils.TypeCacheAdapter;

public class PinDetailsComposite extends Composite{
	private Pin pin;
	private EditingDomain editingDomain;
	private TabbedPropertySheetWidgetFactory widgetFactory;
	private Text parameterNameTxt;
	private CSingleObjectChooser parameterType;
	private OpaqueExpressionBodyComposite oclValue;
	public PinDetailsComposite(Composite parent,int style,TabbedPropertySheetWidgetFactory widgetFactory){
		super(parent, style);
		setBackground(parent.getBackground());
		this.widgetFactory = widgetFactory;
		setLayout(new GridLayout(4, true));
		setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		widgetFactory.adapt(this);
		createContents(this);
		hookListeners();
	}
	public void setPin(Pin parameter){
		this.pin = parameter;
		loadData();
		if(pin instanceof ValuePin){
			oclValue.setEnabled(true);
			oclValue.setOclContext(pin,pin,(OpaqueExpression) ((ValuePin) pin).getValue());
		}else{
			oclValue.setEnabled(false);
		}
	}
	protected void createContents(Composite parent){
		widgetFactory.createLabel(parent, "Name : ");
		parameterNameTxt = widgetFactory.createText(parent, "", SWT.BORDER);
		parameterNameTxt.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		widgetFactory.createLabel(parent, "Type : ");
		parameterType = new CSingleObjectChooser(parent, widgetFactory, SWT.NONE);
		parameterType.setLabelProvider(new AdapterFactoryLabelProvider(new UMLItemProviderAdapterFactory()));
		parameterType.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		widgetFactory.createLabel(parent, "Value : ");
		this.oclValue = new OpaqueExpressionBodyComposite(parent, widgetFactory){
			@Override
			public void fireOclChanged(String value){
				super.fireOclChanged(value);
				pinUpdated(pin);
			}

			@Override
			public EReference getValueSpecificationFeature(){
				return UMLPackage.eINSTANCE.getValuePin_Value();
			}

			@Override
			protected EditingDomain getEditingDomain(){
				return editingDomain;
			}
		};
		oclValue.setLayoutData(new GridData(GridData.FILL, GridData.FILL, true, true, 1, 3));
		GridData layoutDataComposite = new GridData(GridData.FILL, GridData.FILL, true, true, 4, 1);
		layoutDataComposite.minimumHeight = 50;
		layoutDataComposite.grabExcessVerticalSpace = true;
		oclValue.setLayoutData(layoutDataComposite);
	}
	private void hookListeners(){
		TextChangeHelper parameterNameListener = new TextChangeHelper(){
			public void textChanged(Control control){
				String newText = parameterNameTxt.getText();
				if(pin != null && !newText.equals(pin.getName())){
					editingDomain.getCommandStack().execute(SetCommand.create(editingDomain, pin, UMLPackage.eINSTANCE.getNamedElement_Name(), newText));
					pinUpdated(pin);
				}
			}
		};
		parameterNameListener.startListeningTo(parameterNameTxt);
		parameterNameListener.startListeningForEnter(parameterNameTxt);
		parameterType.addSelectionListener(new SelectionAdapter(){
			public void widgetSelected(SelectionEvent event){
				Object selectedElement = parameterType.getSelection();
				if(selectedElement != null && !selectedElement.equals(pin.getType())){
					editingDomain.getCommandStack().execute(SetCommand.create(editingDomain, pin, UMLPackage.eINSTANCE.getTypedElement_Type(), selectedElement));
					pinUpdated(pin);
				}
			}
		});
	}
	public void pinUpdated(Pin pin){
	}
	private void loadData(){
		oclValue.getTextControl().setText("");
		if(pin != null){
			String nameToDisplay = pin.getName() != null ? pin.getName() : "";
			parameterNameTxt.setText(nameToDisplay);
			parameterType.setChoices(getChoices());
			if(pin.getType() != null){
				parameterType.setSelection(pin.getType());
			}
			if(pin instanceof ValuePin){
				ValuePin vp = (ValuePin) pin;
				oclValue.setOclContext(pin, pin, (OpaqueExpression) vp.getValue());
			}
		}else{
			parameterNameTxt.setText("");
			parameterType.setSelection(null);
		}
	}
	private Object[] getChoices(){
		List<Object> choices = new ArrayList<Object>();
		choices.add("");
		Collection<EObject> types = TypeCacheAdapter.getExistingTypeCacheAdapter(pin).getReachableObjectsOfType(pin, UMLPackage.eINSTANCE.getType());
		choices.addAll(UmlMetaTypeRemover.removeAll(types));
		return choices.toArray();
	}
	public void setEditingDomain(EditingDomain editingDomain){
		this.editingDomain = editingDomain;
	}
}