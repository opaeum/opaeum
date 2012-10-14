package org.opaeum.eclipse.uml.propertysections.constraints;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ICellEditorListener;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;
import org.eclipse.uml2.uml.Constraint;
import org.eclipse.uml2.uml.OpaqueExpression;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.UMLPackage;
import org.opaeum.eclipse.EmfElementFinder;
import org.opaeum.eclipse.uml.editingsupport.AbstractCellEditorListener;
import org.opaeum.eclipse.uml.editingsupport.NamedElementNameEditingSupport;
import org.opaeum.eclipse.uml.editingsupport.OpaqueExpressionPropertyEditingSupport;
import org.opaeum.eclipse.uml.propertysections.RecursiveAdapter;
import org.opaeum.eclipse.uml.propertysections.core.AbstractTableComposite;

public class OclConstraintTable extends AbstractTableComposite<Constraint>{
	public OclConstraintTable(TabbedPropertySheetWidgetFactory factory,Composite parent,EStructuralFeature feature){
		super(parent, SWT.NONE, factory, feature);
		super.adaptor = new RecursiveAdapter(){
			public void safeNotifyChanged(Notification msg){
				if(tableViewer.getTable().isDisposed() && msg.getNotifier() instanceof Notifier){
					((Notifier) msg.getNotifier()).eAdapters().remove(adaptor);
				}else{
					Constraint c = EmfElementFinder.findNearestElementOfType(Constraint.class, (EObject) msg.getNotifier());
					if(c == null && msg.getNewValue() instanceof Constraint){
						c = (Constraint) msg.getNewValue();
					}
					if(msg.getNotifier() instanceof EObject && msg.getFeature() != null){
						boolean inScope = false;
						EObject notifier = (EObject) msg.getNotifier();
						while(notifier != null){
							if(getObjectList().contains(notifier) || notifier == owner){
								inScope = true;
								notifier = null;
							}else{
								notifier = notifier.eContainer();
							}
						}
						if(inScope){
							Control focusControl = Display.getCurrent().getFocusControl();
							if(msg.getFeature().equals(UMLPackage.eINSTANCE.getOpaqueExpression_Body()) && focusControl instanceof StyledText
									&& focusControl.getParent().getParent() == tableViewer.getTable()){
								// nothing-this control caused it
							}else{
								tableViewer.refresh(c);
							}
						}
					}
				}
			}
		};
	}
	@Override
	protected Constraint getNewChild(){
		Constraint newConstraint = UMLFactory.eINSTANCE.createConstraint();
		newConstraint.setName("newConstraint");
		OpaqueExpression oclExpression = createExpression(newConstraint);
		newConstraint.setSpecification(oclExpression);
		return newConstraint;
	}
	private OpaqueExpression createExpression(Constraint newConstraint){
		OpaqueExpression oclExpression = UMLFactory.eINSTANCE.createOpaqueExpression();
		if(newConstraint != null){
			oclExpression.setName(newConstraint.getName() + "_body");
		}else{
			oclExpression.setName("newConstraint_body");
		}
		oclExpression.getLanguages().add("OCL");
		String body = "";
		oclExpression.getBodies().add(body);
		return oclExpression;
	}
	@Override
	protected void createColumns(){
		createTableViewerColumn(new NamedElementNameEditingSupport(tableViewer));
		OpaqueExpressionPropertyEditingSupport oe = new OpaqueExpressionPropertyEditingSupport(tableViewer, widgetFactory,
				UMLPackage.eINSTANCE.getConstraint_Specification());
		createTableViewerColumn(oe).getColumn();
		oe.addCellEditorListener(new AbstractCellEditorListener(){
			@Override
			public void deactivated(){
				tableViewer.refresh();
			}
		});
	}
}
