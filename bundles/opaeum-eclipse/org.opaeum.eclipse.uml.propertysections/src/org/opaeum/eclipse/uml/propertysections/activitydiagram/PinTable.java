package org.opaeum.eclipse.uml.propertysections.activitydiagram;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.jface.viewers.CellLabelProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;
import org.eclipse.uml2.uml.OpaqueAction;
import org.eclipse.uml2.uml.Pin;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.UMLPackage;
import org.opaeum.eclipse.EmfActionUtil;
import org.opaeum.eclipse.uml.editingsupport.EditingDomainEditingSupport;
import org.opaeum.eclipse.uml.editingsupport.MultiplicityElementUpperValueEditingSupport;
import org.opaeum.eclipse.uml.editingsupport.NamedElementNameEditingSupport;
import org.opaeum.eclipse.uml.editingsupport.OpaqueExpressionPropertyEditingSupport;
import org.opaeum.eclipse.uml.editingsupport.TypedElementTypeEditingSupport;
import org.opaeum.eclipse.uml.propertysections.core.AbstractTableComposite;

public class PinTable extends AbstractTableComposite<Pin>{
	public PinTable(Composite parent,int style,TabbedPropertySheetWidgetFactory widgetFactory,EStructuralFeature feature){
		super(parent, style, widgetFactory, feature);
	}
	public void setOwner(EObject owner){
		super.setOwner(owner);
		if(owner instanceof OpaqueAction){
			feature=UMLPackage.eINSTANCE.getOpaqueAction_InputValue();
			addButton.setEnabled(true);
			removeButton.setEnabled(true);
			moveDownButton.setEnabled(true);
			moveDownButton.setEnabled(true);
		}else{
			feature=UMLPackage.eINSTANCE.getAction_Input();
			addButton.setEnabled(false);
			removeButton.setEnabled(false);
			moveDownButton.setEnabled(true);
			moveDownButton.setEnabled(true);
		}
		//TODO invocation actions
	}
	protected void addNew(){
		//TODO for invocation actions, prompt the user then create the linkedTypeElement - remember the work done by opaeumElementLinker
		Command addCommand = AddCommand.create(editingDomain, owner, feature, getNewChild());
		editingDomain.getCommandStack().execute(addCommand);
	}
	protected void removeOld(Object object){
		//TODO for invocation actions, prompt the user then remove the linkedTypeElement - remember the work done by opaeumElementLinker
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
							type=EmfActionUtil.calculateType(pin);
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
}
