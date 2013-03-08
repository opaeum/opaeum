package org.opaeum.eclipse.uml.propertysections.activitydiagram;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.jface.viewers.CellLabelProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;
import org.eclipse.uml2.uml.InputPin;
import org.eclipse.uml2.uml.InstanceSpecification;
import org.eclipse.uml2.uml.InstanceValue;
import org.eclipse.uml2.uml.OpaqueAction;
import org.eclipse.uml2.uml.Pin;
import org.eclipse.uml2.uml.TimeEvent;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.ValuePin;
import org.eclipse.uml2.uml.util.UMLUtil;
import org.opaeum.eclipse.EmfActionUtil;
import org.opaeum.eclipse.EmfValidationUtil;
import org.opaeum.eclipse.EmfValueSpecificationUtil;
import org.opaeum.eclipse.ImageManager;
import org.opaeum.eclipse.commands.AddStereotypeValueCommand;
import org.opaeum.eclipse.commands.ApplyStereotypeCommand;
import org.opaeum.eclipse.uml.editingsupport.EditingDomainEditingSupport;
import org.opaeum.eclipse.uml.editingsupport.MultiplicityElementUpperValueEditingSupport;
import org.opaeum.eclipse.uml.editingsupport.NamedElementNameEditingSupport;
import org.opaeum.eclipse.uml.editingsupport.OpaqueExpressionPropertyEditingSupport;
import org.opaeum.eclipse.uml.editingsupport.TypedElementTypeEditingSupport;
import org.opaeum.eclipse.uml.propertysections.core.AbstractTableComposite;
import org.opaeum.eclipse.uml.propertysections.ocl.OclBodyComposite;
import org.opaeum.metamodel.core.internal.StereotypeNames;
import org.opaeum.metamodel.core.internal.TagNames;
import org.opaeum.validation.OclValidator;

public class PinTable extends AbstractTableComposite<InputPin>{
	public PinTable(Composite parent,int style,TabbedPropertySheetWidgetFactory widgetFactory,EStructuralFeature feature){
		super(parent, style, widgetFactory, feature);
	}
	public void setOwner(EObject owner){
		super.setOwner(owner);
		if(owner instanceof OpaqueAction){
			feature = UMLPackage.eINSTANCE.getOpaqueAction_InputValue();
			addButton.setEnabled(true);
			removeButton.setEnabled(true);
			moveDownButton.setEnabled(true);
			moveDownButton.setEnabled(true);
		}else{
			feature = UMLPackage.eINSTANCE.getAction_Input();
			addButton.setEnabled(false);
			removeButton.setEnabled(false);
			moveDownButton.setEnabled(true);
			moveDownButton.setEnabled(true);
		}
		// TODO invocation actions
	}
	protected void addNew(){
		// TODO for invocation actions, prompt the user then create the linkedTypeElement - remember the work done by opaeumElementLinker
		Command addCommand = AddCommand.create(editingDomain, owner, feature, getNewChild());
		editingDomain.getCommandStack().execute(addCommand);
	}
	protected void removeOld(Object object){
		// TODO for invocation actions, prompt the user then remove the linkedTypeElement - remember the work done by opaeumElementLinker
		editingDomain.getCommandStack().execute(RemoveCommand.create(editingDomain, object));
	}
	protected class ParameterNameLabelProvider extends ColumnLabelProvider{
		@Override
		public String getText(Object element){
			Pin pin = (Pin) element;
			if(!pin.eContainingFeature().isMany()){
				return pin.eContainingFeature().getName();
			}
			return pin.getName();
		}
	}
	protected class ParameterTypeLabelProvider extends ColumnLabelProvider{
		@Override
		public String getText(Object element){
			Pin param = (Pin) element;
			if(param.getType() != null){
				return param.getType().getName();
			}else{
				return "null";
			}
		}
	}
	@Override
	protected void createColumns(){
		EditingDomainEditingSupport e = new NamedElementNameEditingSupport(tableViewer);
		TableViewerColumn name = createTableViewerColumn(e);
		EditingDomainEditingSupport te = new TypedElementTypeEditingSupport(tableViewer, widgetFactory){
			public CellLabelProvider getLabelProvider(){
				return new CellLabelProvider(){
					@Override
					public void update(ViewerCell cell){
						Pin pin = (Pin) cell.getElement();
						Type type = pin.getType();
						if(type == null){
							type = EmfActionUtil.calculateType(pin);
						}
						if(type == null){
							cell.setText(null);
						}else{
							cell.setText(labelProvider.getText(type));
							cell.setImage(labelProvider.getImage(type));
						}
					}
				};
			}
		};
		te.setEditable(false);
		TableViewerColumn type = createTableViewerColumn(te);
		createTableViewerColumn(new MultiplicityElementUpperValueEditingSupport(tableViewer));
		createTableViewerColumn(new OpaqueExpressionPropertyEditingSupport(tableViewer, widgetFactory, UMLPackage.eINSTANCE.getValuePin_Value()));
	}
	@Override
	protected void createAddButton(Composite parent){
		addButton = widgetFactory.createButton(parent, "Add", SWT.NONE);
		addButton.setImage(ImageManager.IMG_ADD);
		addButton.setLayoutData(new GridData(SWT.FILL, SWT.TOP, false, false));
		Menu m = new Menu(addButton.getShell());
		MenuItem newObjectInput = new MenuItem(m, SWT.NONE);
		newObjectInput.addSelectionListener(new SelectionListener(){
			@Override
			public void widgetSelected(SelectionEvent e){
				addNew(getNewNewObjectPin());
			}
			@Override
			public void widgetDefaultSelected(SelectionEvent e){
			}
		});
		newObjectInput.setText("Object Creation Input");
		MenuItem oclInput = new MenuItem(m, SWT.NONE);
		oclInput.addSelectionListener(new SelectionListener(){
			@Override
			public void widgetSelected(SelectionEvent e){
				addNew(getNewOclPin());
			}
			@Override
			public void widgetDefaultSelected(SelectionEvent e){
			}
		});
		oclInput.setText("Ocl Input");
		MenuItem flowInput = new MenuItem(m, SWT.NONE);
		flowInput.addSelectionListener(new SelectionListener(){
			@Override
			public void widgetSelected(SelectionEvent e){
				addNew(getNewInputPin());
			}
			@Override
			public void widgetDefaultSelected(SelectionEvent e){
			}
		});
		flowInput.setText("Object Flow Input");
		addButton.setMenu(m);
		addButton.addSelectionListener(new SelectionListener(){
			@Override
			public void widgetSelected(SelectionEvent e){
				addButton.getMenu().setVisible(true);
			}
			@Override
			public void widgetDefaultSelected(SelectionEvent e){
				// TODO Auto-generated method stub
			}
		});
	}
	protected ValuePin getNewOclPin(){
		ValuePin result = UMLFactory.eINSTANCE.createValuePin();
		result.setName("NewOclInput");
		result.setValue(EmfValueSpecificationUtil.buildOpaqueExpression(result, "value", EmfValidationUtil.OCL_EXPRESSION_REQUIRED));
		return result;
	}
	protected ValuePin getNewNewObjectPin(){
		ValuePin result = UMLFactory.eINSTANCE.createValuePin();
		result.setName("NewObjectInput");
		InstanceValue iv = UMLFactory.eINSTANCE.createInstanceValue();
		result.setValue(iv);
		result.getValue().setName("NewObjectInstance");
		EAnnotation ann = EcoreFactory.eINSTANCE.createEAnnotation();
		ann.setSource(StereotypeNames.NUML_ANNOTATION);
		InstanceSpecification createInstanceSpecification = UMLFactory.eINSTANCE.createInstanceSpecification();
		ann.getContents().add(createInstanceSpecification);
		createInstanceSpecification.setName("NewObjectInstanceSpecification");
		iv.setInstance(createInstanceSpecification);
		result.getEAnnotations().add(ann);
		
		return result;
	}
	protected InputPin getNewInputPin(){
		InputPin result = UMLFactory.eINSTANCE.createInputPin();
		result.setName("NewObjectFlowInput");
		return result;
	}
	protected void addNew(InputPin newChild){
		// Assume stereotypes has already been set
		CompoundCommand cc = new CompoundCommand();
		cc.append(AddCommand.create(editingDomain, owner, UMLPackage.eINSTANCE.getOpaqueAction_InputValue(), newChild));
//		if(stereotypeName == null){
//			cc.append(new ApplyStereotypeCommand(newChild, stereotypeName));
//		}
		editingDomain.getCommandStack().execute(cc);
		refresh();
		EObject newObject = getObjectList().get(getObjectList().size() - 1);
		newObject.eAdapters().add(adaptor);
		tableViewer.setSelection(new StructuredSelection(newObject));
	}
}
