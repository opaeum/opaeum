package org.opaeum.eclipse.uml.propertysections.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EContentAdapter;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.ParameterDirectionKind;
import org.eclipse.uml2.uml.UMLPackage;
import org.opaeum.eclipse.uml.propertysections.common.OpaeumQualifiedNameLabelProvider;
import org.opaeum.eclipse.uml.propertysections.common.TextChangeHelper;
import org.opaeum.topcased.uml.editor.OpaeumItemProviderAdapterFactory;
import org.topcased.tabbedproperties.sections.widgets.CSingleObjectChooser;
import org.topcased.tabbedproperties.utils.TypeCacheAdapter;

public class ParameterComposite extends Composite{
	private Parameter parameter;
	private EditingDomain mixedEditDomain;
	private TabbedPropertySheetWidgetFactory widgetFactory;
	private Text parameterNameTxt;
	private CSingleObjectChooser parameterType;
	private CCombo parameterDirectionCb;
	private Button isExceptionBtn;
	private EContentAdapter adaptor = new EContentAdapter(){
		public void notifyChanged(Notification notification){
			if(notification.getNotifier().equals(parameter)){
				switch(notification.getFeatureID(Parameter.class)){
				case UMLPackage.PARAMETER__NAME:
				case UMLPackage.PARAMETER__DIRECTION:
				case UMLPackage.PARAMETER__IS_EXCEPTION:
				case UMLPackage.PARAMETER__TYPE:
					loadData();
				}
			}
		}
	};
	public ParameterComposite(Composite parent,int style,TabbedPropertySheetWidgetFactory widgetFactory){
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
	public void dispose(){
		super.dispose();
		removeAdaptor();
	}
	private void removeAdaptor(){
		if(parameter != null){
			parameter.eAdapters().remove(adaptor);
		}
	}
	public void setParameter(Parameter parameter){
		removeAdaptor();
		setEnabled(this, parameter != null);
		this.parameter = parameter;
		addAdaptor();
		loadData();
	}
	private void addAdaptor(){
		if(this.parameter != null && !this.parameter.eAdapters().contains(adaptor)){
			this.parameter.eAdapters().add(adaptor);
		}
	}
	public void setEditingDomain(EditingDomain mixedEditDomain){
		this.mixedEditDomain = mixedEditDomain;
	}
	protected void createContents(Composite parent){
		widgetFactory.createLabel(parent, "Name : ");
		parameterNameTxt = widgetFactory.createText(parent, "", SWT.BORDER);
		parameterNameTxt.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		widgetFactory.createLabel(parent, "Type : ");
		parameterType = new CSingleObjectChooser(parent, widgetFactory, SWT.NONE);
		parameterType.setAdvancedLabelProvider(new OpaeumQualifiedNameLabelProvider(new OpaeumItemProviderAdapterFactory()));
		parameterType.setLabelProvider(new AdapterFactoryLabelProvider(new OpaeumItemProviderAdapterFactory()));
		parameterType.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		widgetFactory.createLabel(parent, "Direction : ");
		parameterDirectionCb = widgetFactory.createCCombo(parent, SWT.BORDER | SWT.FLAT);
		parameterDirectionCb.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		isExceptionBtn = widgetFactory.createButton(parent, "Is Exception", SWT.CHECK);
		isExceptionBtn.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
	}
	private void hookListeners(){
		TextChangeHelper parameterNameListener = new TextChangeHelper(){
			public void textChanged(Control control){
				String newText = parameterNameTxt.getText();
				if(parameter != null && !newText.equals(parameter.getName())){
					mixedEditDomain.getCommandStack().execute(
							SetCommand.create(mixedEditDomain, parameter, UMLPackage.eINSTANCE.getNamedElement_Name(), newText));
				}
			}
		};
		parameterNameListener.startListeningTo(parameterNameTxt);
		parameterNameListener.startListeningForEnter(parameterNameTxt);
		parameterType.addSelectionListener(new SelectionAdapter(){
			public void widgetSelected(SelectionEvent event){
				Object selectedElement = parameterType.getSelection();
				if(selectedElement != null && !selectedElement.equals(parameter.getType())){
					mixedEditDomain.getCommandStack().execute(
							SetCommand.create(mixedEditDomain, parameter, UMLPackage.eINSTANCE.getTypedElement_Type(), selectedElement));
				}
			}
		});
		parameterDirectionCb.addSelectionListener(new SelectionListener(){
			@Override
			public void widgetSelected(SelectionEvent e){
				ParameterDirectionKind selectedElement = ParameterDirectionKind.get(parameterDirectionCb.getSelectionIndex());
				if(!selectedElement.equals(parameter.getDirection())){
					mixedEditDomain.getCommandStack().execute(
							SetCommand.create(mixedEditDomain, parameter, UMLPackage.eINSTANCE.getParameter_Direction(), selectedElement));
				}
			}
			@Override
			public void widgetDefaultSelected(SelectionEvent e){
				// TODO Auto-generated method stub
			}
		});
		isExceptionBtn.addSelectionListener(new SelectionListener(){
			@Override
			public void widgetSelected(SelectionEvent e){
				boolean v = isExceptionBtn.getSelection();
				if(v != parameter.isException()){
					mixedEditDomain.getCommandStack().execute(
							SetCommand.create(mixedEditDomain, parameter, UMLPackage.eINSTANCE.getParameter_IsException(), v));
					if(v && parameter.getDirection() != ParameterDirectionKind.OUT_LITERAL){
						mixedEditDomain.getCommandStack().execute(
								SetCommand.create(mixedEditDomain, parameter, UMLPackage.eINSTANCE.getParameter_Direction(),
										ParameterDirectionKind.OUT_LITERAL));
						parameterDirectionCb.select(parameter.getDirection().ordinal());
					}
					parameterDirectionCb.setEnabled(!parameter.isException());
				}
			}
			@Override
			public void widgetDefaultSelected(SelectionEvent e){
				// TODO Auto-generated method stub
			}
		});
	}
	private void loadData(){
		parameterDirectionCb.removeAll();
		if(parameter != null){
			String nameToDisplay = parameter.getName() != null ? parameter.getName() : "";
			parameterNameTxt.setText(nameToDisplay);
			parameterType.setChoices(getChoices());
			if(parameter.getType() != null){
				parameterType.setSelection(parameter.getType());
			}
			List<ParameterDirectionKind> values = ParameterDirectionKind.VALUES;
			for(ParameterDirectionKind p:values){
				parameterDirectionCb.add(p.getName());
			}
			if(parameter.getDirection() != null){
				parameterDirectionCb.select(parameter.getDirection().ordinal());
			}
			parameterDirectionCb.setEnabled(!parameter.isException());
			isExceptionBtn.setSelection(parameter.isException());
		}else{
			parameterNameTxt.setText("");
			parameterType.setSelection(null);
			isExceptionBtn.setSelection(false);
		}
	}
	private Object[] getChoices(){
		List<Object> choices = new ArrayList<Object>();
		choices.add("");
		Collection<EObject> types = TypeCacheAdapter.getExistingTypeCacheAdapter(parameter).getReachableObjectsOfType(parameter,
				UMLPackage.eINSTANCE.getClassifier());
		choices.addAll(UmlMetaTypeRemover.removeAll(types));
		return choices.toArray();
	}
}