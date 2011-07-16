package org.nakeduml.topcased.propertysections.constraints;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.DeleteCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.ui.celleditor.FeatureEditorDialog;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.internal.views.properties.tabbed.view.TabbedPropertyComposite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;
import org.eclipse.uml2.uml.Constraint;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Namespace;
import org.eclipse.uml2.uml.OpaqueExpression;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.edit.providers.UMLItemProviderAdapterFactory;
import org.nakeduml.topcased.propertysections.OclValueComposite;
import org.nakeduml.topcased.uml.NakedUmlPlugin;
import org.topcased.tabbedproperties.utils.TextChangeListener;

public class OclOwnedRuleComposite extends Composite{
	public static final int STATES_INIT = 0;
	public static final int STATES_ONE_CONSTRAINT_SELECTED = 1;
	public static final int CONSTRAINTS_NAME = 0;
	public static final int CONSTRAINTS_COMMENT = 1;
	public static final int MIN_HEIGHT_FOR_OCL_AREA = 70;
	private static final int TABLE_HEIGHT = 60;
	private TabbedPropertySheetWidgetFactory theFactory;
	private TableViewer constraintList;
	private OclValueComposite oclComposite;
	private TextDisplayingElements textElementChoosen;
	private Group groupDetails;
	private Element context = null;
	private Composite compositeForConstraintList;
	private Text textForConstraintName;
	private OpaqueExpression selectedOpaqueExpression;
	private Constraint selectedConstraint;
	private EditingDomain currentEditDomain = null;
	private boolean reinit = false;
	private TextChangeListener listener;
	private EStructuralFeature feature;
	public OclOwnedRuleComposite(TabbedPropertySheetWidgetFactory factory,Composite parent,EStructuralFeature feature){
		super(parent, SWT.NONE);
		this.feature = feature;
		theFactory = factory;
		
		this.setLayout(new FillLayout(SWT.VERTICAL));
		setLayout(new GridLayout(1, false));
		createGroupConstraints(this);
		createDetailsZone(this);
		setState(STATES_INIT);
		Utils.layout(this);
	}
	private TabbedPropertySheetWidgetFactory getWidgetFactory(){
		return theFactory;
	}
	private void createDetailsZone(Composite composite){
		groupDetails = getWidgetFactory().createGroup(composite, "Details");
		groupDetails.setLayout(new GridLayout(3, false));
		groupDetails.setLayoutData(new GridData(GridData.FILL, GridData.FILL, true, true));
		createAreaForNameOfConstraintAndConstrainedElement(groupDetails);
		createGroupOCLRule(groupDetails);
	}
	private void createGroupConstraints(Composite composite){
		compositeForConstraintList = getWidgetFactory().createComposite(composite);
		compositeForConstraintList.setLayout(new GridLayout(2, false));
		compositeForConstraintList.setLayoutData(new GridData(GridData.FILL, GridData.FILL, true, false));
		// add the table for listing all the constraints
		constraintList = new TableViewer(compositeForConstraintList, SWT.SINGLE | SWT.BORDER | SWT.FILL);
		constraintList.addSelectionChangedListener(new ISelectionChangedListener(){
			public void selectionChanged(SelectionChangedEvent event){
				if(event.getSelection() instanceof IStructuredSelection){
					IStructuredSelection selec = (IStructuredSelection) event.getSelection();
					setSelectedConstraint((Constraint) selec.getFirstElement());
				}
			}
		});
		// create the columns
		applyTableInfo(constraintList, NakedUmlPlugin.getDefault().getImageRegistry().getDescriptor("Actor").createImage(), new int[]{
				200,200
		}, "Name", "Expression");
		GridData layoutDataConstraintsList = new GridData(SWT.FILL, SWT.BEGINNING, true, false, 1, 2);
		layoutDataConstraintsList.heightHint = TABLE_HEIGHT;
		constraintList.getTable().setLayoutData(layoutDataConstraintsList);
		constraintList.setContentProvider(new ConstraintContentProvider());
		constraintList.setLabelProvider(new ConstraintLabelProvider());
		Button plus = getWidgetFactory().createButton(compositeForConstraintList, "Add", SWT.PUSH);
		plus.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseUp(MouseEvent e){
				createConstraints();
			}
		});
		plus.setLayoutData(new GridData(GridData.BEGINNING, GridData.BEGINNING, false, false, 1, 1));
		Button minus = getWidgetFactory().createButton(compositeForConstraintList, "Delete", SWT.PUSH);
		minus.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseUp(MouseEvent e){
				deleteConstraints();
			}
		});
		minus.setLayoutData(new GridData(GridData.BEGINNING, GridData.BEGINNING, false, false, 1, 1));
	}
	private EStructuralFeature getFeature(EObject e,int id){
		return e.eClass().getEStructuralFeature(id);
	}
	private void deleteConstraints(){
		if(context != null && currentEditDomain != null){
			if(selectedConstraint != null){
				currentEditDomain.getCommandStack().execute(DeleteCommand.create(currentEditDomain, selectedConstraint));
				setSelectedConstraint(null);
				constraintList.setInput(context.eGet(feature));
				constraintList.refresh();
				if(constraintList.getTable().getItems().length > 0){
					setSelectedConstraint((Constraint) constraintList.getTable().getItems()[0].getData());
				}
			}
		}
	}
	private void createConstraints(){
		if(context != null && currentEditDomain != null){
			Constraint newConstraint = UMLFactory.eINSTANCE.createConstraint();
			newConstraint.setName("newConstraint");
			OpaqueExpression oclExpression = createExpression(newConstraint);
			newConstraint.setSpecification(oclExpression);
			currentEditDomain.getCommandStack().execute(AddCommand.create(currentEditDomain, context, this.feature, newConstraint));
			constraintList.setInput(context.eGet(feature));
			constraintList.refresh();
			setSelectedConstraint(newConstraint);
		}
	}
	private OpaqueExpression createExpression(Constraint newConstraint){
		OpaqueExpression oclExpression = UMLFactory.eINSTANCE.createOpaqueExpression();
		if(selectedConstraint != null){
			oclExpression.setName(selectedConstraint.getName() + "_body");
		}else{
			oclExpression.setName("newConstraint_body");
		}
		oclExpression.getLanguages().add("OCL");
		String body = "";
		oclExpression.getBodies().add(body);
		return oclExpression;
	}
	void applyTableInfo(TableViewer t,Image colImage,int[] sizes,String...columnNames){
		Table ta = t.getTable();
		int i = 0;
		for(String s:columnNames){
			TableColumn column = new TableColumn(ta, SWT.LEFT);
			if(i == 0){
				column.setImage(colImage);
			}
			column.setText(s);
			if(sizes != null && i < sizes.length){
				column.setWidth(sizes[i]);
			}else{
				column.setWidth(300);
			}
			i++;
		}
		ta.setHeaderVisible(true);
		ta.setLinesVisible(true);
	}
	private void createAreaForNameOfConstraintAndConstrainedElement(Group group){
		Label l = getWidgetFactory().createLabel(group, "Constraint Name : ", SWT.BOLD);
		l.setLayoutData(new GridData(GridData.BEGINNING, GridData.CENTER, false, false, 1, 1));
		textForConstraintName = getWidgetFactory().createText(group, "", SWT.BORDER);
		textForConstraintName.setLayoutData(new GridData(GridData.FILL, GridData.CENTER, true, false, 2, 1));
		textForConstraintName.addModifyListener(new ModifyListener(){
			public void modifyText(ModifyEvent e){
				if(Utils.notNull(context, currentEditDomain, selectedConstraint) && !textForConstraintName.getText().equals(selectedConstraint.getName()) && !reinit){
					currentEditDomain.getCommandStack().execute(
							SetCommand.create(currentEditDomain, selectedConstraint, getFeature(selectedConstraint, UMLPackage.CONSTRAINT__NAME),
									textForConstraintName.getText()));
					if(selectedOpaqueExpression == null){
						OpaqueExpression expression = createExpression(selectedConstraint);
						currentEditDomain.getCommandStack().execute(
								SetCommand
										.create(currentEditDomain, selectedConstraint, getFeature(selectedConstraint, UMLPackage.CONSTRAINT__SPECIFICATION), expression));
						selectedOpaqueExpression = expression;
					}
					currentEditDomain.getCommandStack().execute(
							SetCommand.create(currentEditDomain, selectedOpaqueExpression, getFeature(selectedOpaqueExpression, UMLPackage.OPAQUE_EXPRESSION__NAME),
									textForConstraintName.getText() + "_body"));
					constraintList.setInput(context.eGet(feature));
					constraintList.refresh();
				}
			}
		});
		// label for elements constraines
		Label labelConstrainedElement = getWidgetFactory().createLabel(group, "Elements constrained : ", SWT.BOLD);
		labelConstrainedElement.setLayoutData(new GridData(GridData.BEGINNING, GridData.CENTER, false, false, 1, 1));
		textElementChoosen = new TextDisplayingElements(group, SWT.BORDER, getWidgetFactory());
		Text t2 = textElementChoosen.getControl();
		t2.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		t2.setEditable(false);
		Button chooseElementChooButton = getWidgetFactory().createButton(group, "...", SWT.PUSH);
		chooseElementChooButton.setLayoutData(new GridData(GridData.FILL, GridData.CENTER, false, false, 1, 1));
		chooseElementChooButton.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseUp(MouseEvent e){
				askForConstrainedElements();
			}
		});
	}
	private List<?> getAllElements(){
		return context.eContents();
	}
	private void createGroupOCLRule(Group group){
		Label l = getWidgetFactory().createLabel(group, "OCL Rule Code : ", SWT.BOLD);
		l.setLayoutData(new GridData(SWT.BEGINNING, SWT.CENTER, true, false, 3, 1));
		oclComposite = new OclValueComposite(group, getWidgetFactory());
		listener = new TextChangeListener(){
			public void textChanged(Control control){
				updateOcl();
			}
			public void focusIn(Control control){
			}
			public void focusOut(Control control){
			}
		};
		listener.startListeningTo(oclComposite.getTextControl());
		GridData layoutDataComposite = new GridData(GridData.FILL, GridData.FILL, true, true, 3, 1);
		layoutDataComposite.minimumHeight = MIN_HEIGHT_FOR_OCL_AREA;
		layoutDataComposite.grabExcessVerticalSpace = true;
		oclComposite.setLayoutData(layoutDataComposite);
	}
	public static int getMinHeight(){
		return 150;
	}
	public void setContext(Element theContext){
		context = theContext;
		setState(STATES_INIT);
		if(theContext != null){
			constraintList.setInput(getConstraints());
			if(getConstraints().size() == 1){
				setSelectedConstraint(getConstraints().iterator().next());
				setState(STATES_ONE_CONSTRAINT_SELECTED);
			}
		}
	}
	private EList<Constraint> getConstraints(){
		return((EList<Constraint>) context.eGet(feature));
	}
	public void setState(int state){
		switch(state){
		case STATES_INIT:
			reinitForm();
			compositeForConstraintList.setEnabled(true);
			setEnablesChildren(groupDetails, false);
			break;
		case STATES_ONE_CONSTRAINT_SELECTED:
			compositeForConstraintList.setEnabled(true);
			setEnablesChildren(groupDetails, true);
			break;
		default:
			compositeForConstraintList.setEnabled(true);
			setEnablesChildren(groupDetails, false);
		}
	}
	private void reinitForm(){
		reinit = true;
		textForConstraintName.setText("");
		textElementChoosen.setCollectionElements(null);
		constraintList.setInput(null);
		selectedConstraint = null;
		selectedOpaqueExpression = null;
		reinit = false;
	}
	private void setEnablesChildren(Composite control,boolean enable){
		for(Control c:control.getChildren()){
			c.setEnabled(enable);
			if(c instanceof Composite){
				Composite com = (Composite) c;
				setEnablesChildren(com, enable);
			}
		}
	}
	public void setConstraints(Collection<Constraint> constraints){
		constraintList.setInput(constraints);
		constraintList.update(constraints, null);
	}
	public void setSelectedConstraint(Constraint theConstraint){
		if(theConstraint != null){
			oclComposite.setValueElement(theConstraint);

			setState(STATES_ONE_CONSTRAINT_SELECTED);
			selectedConstraint = theConstraint;
			int indexForSelection = getIndex(theConstraint, constraintList);
			constraintList.getTable().setSelection(indexForSelection);
			String constraintName = theConstraint.getName();
			if(constraintName == null){
				constraintName = "";
			}
			textForConstraintName.setText(constraintName);
			textElementChoosen.setCollectionElements(theConstraint.getConstrainedElements());
			if(theConstraint.getSpecification() instanceof OpaqueExpression){
				selectedOpaqueExpression = (OpaqueExpression) theConstraint.getSpecification();
				int index = 0;
				boolean found = false;
				for(String s:selectedOpaqueExpression.getLanguages()){
					if(s != null && "ocl".equals(s.toLowerCase().trim())){
						found = true;
						break;
					}
					index++;
				}
				if(found){
					if(selectedOpaqueExpression.getBodies().size() == 0){
						selectedOpaqueExpression.getBodies().add("");
					}
					oclComposite.setCompositeValue(selectedOpaqueExpression.getBodies().get(index));
				}
			}
		}else{
			setState(STATES_INIT);
		}
	}
	private int getIndex(Object object,TableViewer list){
		int i = 0;
		for(TableItem item:list.getTable().getItems()){
			if(item.getData().equals(object)){
				return i;
			}
			i++;
		}
		return 0;
	}
	public void setEditDomain(EditingDomain domain){
		currentEditDomain = domain;
	}
	private void askForConstrainedElements(){
		if(selectedConstraint != null){
			FeatureEditorDialog dialog = new FeatureEditorDialog(Display.getDefault().getActiveShell(), new AdapterFactoryLabelProvider(
					new UMLItemProviderAdapterFactory()), selectedConstraint, getFeature(selectedConstraint, UMLPackage.CONSTRAINT__CONSTRAINED_ELEMENT),
					"Choose Constrained Element", getAllElements());
			if(dialog.open() == FeatureEditorDialog.OK){
				List elements = dialog.getResult();
				if(currentEditDomain != null){
					if(!selectedConstraint.getConstrainedElements().isEmpty()){
						currentEditDomain.getCommandStack().execute(
								new RemoveCommand(currentEditDomain, selectedConstraint, getFeature(selectedConstraint, UMLPackage.CONSTRAINT__CONSTRAINED_ELEMENT),
										selectedConstraint.getConstrainedElements()));
					}
					currentEditDomain.getCommandStack().execute(
							new AddCommand(currentEditDomain, selectedConstraint, getFeature(selectedConstraint, UMLPackage.CONSTRAINT__CONSTRAINED_ELEMENT), elements));
					textElementChoosen.setCollectionElements(elements);
				}
			}
		}
	}
	private void updateOcl(){
		if(context != null && currentEditDomain != null && selectedConstraint != null && !reinit){
			if(selectedOpaqueExpression == null){
				OpaqueExpression expression = createExpression(selectedConstraint);
				currentEditDomain.getCommandStack().execute(
						SetCommand.create(currentEditDomain, selectedConstraint, getFeature(selectedConstraint, UMLPackage.CONSTRAINT__SPECIFICATION), expression));
				selectedOpaqueExpression = expression;
			}
			EList<String> bodies = selectedOpaqueExpression.getBodies();
			LinkedList<String> bodiesCopy = new LinkedList<String>(bodies);
			int index = 0;
			boolean found = false;
			for(String s:selectedOpaqueExpression.getLanguages()){
				if("ocl".equals(s.toLowerCase().trim())){
					found = true;
					break;
				}
				index++;
			}
			if(!found){
				currentEditDomain.getCommandStack().execute(
						AddCommand.create(currentEditDomain, selectedOpaqueExpression, getFeature(selectedOpaqueExpression, UMLPackage.OPAQUE_EXPRESSION__LANGUAGE),
								"OCL"));
				currentEditDomain.getCommandStack().execute(
						AddCommand.create(currentEditDomain, selectedOpaqueExpression, getFeature(selectedOpaqueExpression, UMLPackage.OPAQUE_EXPRESSION__BODY),
								oclComposite.getCompositeValue()));
			}else if(found && bodiesCopy.isEmpty()){
				currentEditDomain.getCommandStack().execute(
						AddCommand.create(currentEditDomain, selectedOpaqueExpression, getFeature(selectedOpaqueExpression, UMLPackage.OPAQUE_EXPRESSION__BODY),
								oclComposite.getCompositeValue()));
			}else if(found && !bodiesCopy.get(index).equals(oclComposite.getCompositeValue())){
				bodiesCopy.set(index, oclComposite.getCompositeValue());
				currentEditDomain.getCommandStack().execute(
						RemoveCommand.create(currentEditDomain, selectedOpaqueExpression, getFeature(selectedOpaqueExpression, UMLPackage.OPAQUE_EXPRESSION__BODY),
								bodies));
				currentEditDomain.getCommandStack().execute(
						AddCommand.create(currentEditDomain, selectedOpaqueExpression, getFeature(selectedOpaqueExpression, UMLPackage.OPAQUE_EXPRESSION__BODY),
								bodiesCopy));
			}
		}
	}
}
