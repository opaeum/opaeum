package org.opaeum.eclipse.uml.propertysections.bpmprofile;

import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.TimeEvent;
import org.eclipse.uml2.uml.UMLFactory;
import org.opaeum.eclipse.EmfValueSpecificationUtil;
import org.opaeum.eclipse.ImageManager;
import org.opaeum.eclipse.commands.AddStereotypeValueCommand;
import org.opaeum.eclipse.commands.ApplyStereotypeCommand;
import org.opaeum.eclipse.commands.RemoveStereotypeValueCommand;
import org.opaeum.eclipse.uml.editingsupport.NamedElementNameEditingSupport;
import org.opaeum.eclipse.uml.editingsupport.TimeEventIsRelativeEditingSupport;
import org.opaeum.eclipse.uml.editingsupport.TimeEventWhenExprEditingSupport;
import org.opaeum.eclipse.uml.editingsupport.bpmprofile.DeadlineKindEditingSupport;
import org.opaeum.eclipse.uml.propertysections.core.AbstractTableComposite;
import org.opaeum.eclipse.uml.propertysections.ocl.OclBodyComposite;
import org.opaeum.metamodel.core.internal.TagNames;

public class DeadlinesTableComposite extends AbstractTableComposite<TimeEvent>{
	private Button addAbsoluteButton;
	public DeadlinesTableComposite(Composite parent,int style,TabbedPropertySheetWidgetFactory widgetFactory){
		super(parent, style, widgetFactory, null);
	}
	protected void addNew(boolean relative){
		// Assume stereotypes has already been set
		CompoundCommand cc = new CompoundCommand();
		TimeEvent newChild = (TimeEvent) getNewChild(relative);
		cc.append(new AddStereotypeValueCommand(editingDomain, (Element) owner, TagNames.DEADLINES, newChild));
		cc.append(new ApplyStereotypeCommand(newChild, TagNames.DEADLINE));
		editingDomain.getCommandStack().execute(cc);
	}
	protected void removeOld(Object object){
		editingDomain.getCommandStack().execute(new RemoveStereotypeValueCommand(editingDomain,(Element)owner, TagNames.DEADLINES, object));
	}
	@Override
	protected void createAddButton(Composite parent){
		addButton = widgetFactory.createButton(parent, "Add Relative", SWT.NONE);
		addButton.setImage(ImageManager.IMG_ADD);
		addButton.setLayoutData(new GridData(SWT.FILL, SWT.TOP, false, false));
		addButton.addSelectionListener(new SelectionListener(){
			@Override
			public void widgetSelected(SelectionEvent e){
				addNew(true);
			}
			@Override
			public void widgetDefaultSelected(SelectionEvent e){
			}
		});
		addAbsoluteButton = widgetFactory.createButton(parent, "Add Absolute", SWT.NONE);
		addAbsoluteButton.setLayoutData(new GridData(SWT.FILL, SWT.TOP, false, false));
		addAbsoluteButton.setImage(ImageManager.IMG_ADD);
		addAbsoluteButton.addSelectionListener(new SelectionListener(){
			@Override
			public void widgetSelected(SelectionEvent e){
				addNew(false);
			}
			@Override
			public void widgetDefaultSelected(SelectionEvent e){
			}
		});
	}
	protected TimeEvent getNewChild(boolean b){
		TimeEvent newDeadline = UMLFactory.eINSTANCE.createTimeEvent();
		newDeadline.setName("NewDeadline");
		newDeadline.setWhen(EmfValueSpecificationUtil.buildTimeExpression(newDeadline, "when", OclBodyComposite.DEFAULT_TEXT));
		newDeadline.setIsRelative(b);
		return newDeadline;
	}
	@Override
	protected void createColumns(){
		NamedElementNameEditingSupport nees = new NamedElementNameEditingSupport(super.tableViewer);
		createTableViewerColumn(nees);
		DeadlineKindEditingSupport dkes = new DeadlineKindEditingSupport(super.tableViewer);
		createTableViewerColumn(dkes);
		TimeEventIsRelativeEditingSupport teires = new TimeEventIsRelativeEditingSupport(super.tableViewer);
		createTableViewerColumn(teires);
		TimeEventWhenExprEditingSupport tewees = new TimeEventWhenExprEditingSupport(super.tableViewer, widgetFactory);
		createTableViewerColumn(tewees);
	}
}
