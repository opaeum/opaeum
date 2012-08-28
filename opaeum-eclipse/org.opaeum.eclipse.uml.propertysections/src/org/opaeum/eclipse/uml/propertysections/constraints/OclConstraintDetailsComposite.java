package org.opaeum.eclipse.uml.propertysections.constraints;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.ui.celleditor.FeatureEditorDialog;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;
import org.eclipse.uml2.uml.Constraint;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.OpaqueExpression;
import org.eclipse.uml2.uml.TypedElement;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.edit.providers.UMLItemProviderAdapterFactory;
import org.opaeum.eclipse.uml.propertysections.ocl.OpaqueExpressionComposite;

public class OclConstraintDetailsComposite extends Composite{
	private TabbedPropertySheetWidgetFactory theFactory;
	private OpaqueExpressionComposite oclComposite;
	private TextDisplayingElements textElementChoosen;
	private Element context = null;
	private Text textForConstraintName;
	private OpaqueExpression selectedOpaqueExpression;
	private Constraint selectedConstraint;
	private EditingDomain currentEditDomain = null;
	private boolean reinit = false;
	private EStructuralFeature feature;
	public OclConstraintDetailsComposite(TabbedPropertySheetWidgetFactory factory,Composite parent,EStructuralFeature feature){
		super(parent, SWT.NONE);
		this.feature = feature;
		theFactory = factory;
		setBackground(parent.getBackground());
		this.setLayout(new FillLayout(SWT.VERTICAL));
		setLayout(new GridLayout(5, false));
		createDetailsZone(this);
		setEnabled(this, false);
	}
	private TabbedPropertySheetWidgetFactory getWidgetFactory(){
		return theFactory;
	}
	private void createDetailsZone(Composite composite){
		createAreaForNameOfConstraintAndConstrainedElement(composite);
		createGroupOCLRule(composite);
	}
	private EStructuralFeature getFeature(EObject e,int id){
		return e.eClass().getEStructuralFeature(id);
	}
	private OpaqueExpression createExpression(Constraint newConstraint){
		OpaqueExpression oclExpression = UMLFactory.eINSTANCE.createOpaqueExpression();
		if(selectedConstraint != null){
			oclExpression.setName(selectedConstraint.getName() + "_body");
		}else{
			oclExpression.setName("newConstraintBody");
		}
		oclExpression.getLanguages().add("OCL");
		oclExpression.getBodies().add("");
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
	public void constraintUpdated(Constraint a){
	}
	private void createAreaForNameOfConstraintAndConstrainedElement(Composite group){
		Label l = getWidgetFactory().createLabel(group, "Constraint Name : ", SWT.BOLD);
		l.setLayoutData(new GridData(GridData.BEGINNING, GridData.CENTER, false, false, 1, 1));
		textForConstraintName = getWidgetFactory().createText(group, "", SWT.BORDER);
		textForConstraintName.setLayoutData(new GridData(GridData.FILL, GridData.CENTER, true, false, 1, 1));
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
					constraintUpdated(selectedConstraint);
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
		List<EObject> eContents = new ArrayList<EObject>();
		for(EObject eObject:context.eContents()){
			if(eObject instanceof TypedElement){
				eContents.add(eObject);
			}
		}
		return eContents;
		
	}
	
	private void createGroupOCLRule(Composite group){
		Label l = getWidgetFactory().createLabel(group, "OCL Rule Code : ", SWT.BOLD);
		l.setLayoutData(new GridData(SWT.BEGINNING, SWT.CENTER, true, false, 3, 1));
		oclComposite = new OpaqueExpressionComposite(group, getWidgetFactory()){
			@Override
			public void fireOclChanged(String value){
				if(context != null && currentEditDomain != null && selectedConstraint != null && !reinit){
					super.fireOclChanged(value);
					constraintUpdated(selectedConstraint);
				}
			}

			@Override
			protected EditingDomain getEditingDomain(){
				return currentEditDomain;
			}
		};
		oclComposite.setBackground(getBackground());
		GridData layoutDataComposite = new GridData(GridData.FILL, GridData.FILL, true, true, 5, 1);
		layoutDataComposite.minimumHeight = 50;
		layoutDataComposite.grabExcessVerticalSpace = true;
		oclComposite.setLayoutData(layoutDataComposite);
	}
	public static int getMinHeight(){
		return 150;
	}
	public void setContext(Element theContext){
		context = theContext;
	}
	private EList<Constraint> getConstraints(){
		return((EList<Constraint>) context.eGet(feature));
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
	public void setSelectedConstraint(Constraint theConstraint){
		if(theConstraint != null){
			oclComposite.setOclContext(theConstraint,(OpaqueExpression) theConstraint.getSpecification());
			selectedConstraint = theConstraint;
			String constraintName = theConstraint.getName();
			if(constraintName == null){
				constraintName = "";
			}
			textForConstraintName.setText(constraintName);
			textElementChoosen.setCollectionElements(theConstraint.getConstrainedElements());
			setEnabled(this, true);
		}else{
			reinit = true;
			textForConstraintName.setText("");
			textElementChoosen.setCollectionElements(null);
			selectedConstraint = null;
			selectedOpaqueExpression = null;
			setEnabled(this, false);
			reinit = false;
		}
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
	
	public void setEditDomain(EditingDomain editingDomain){
		currentEditDomain=editingDomain;
		
	}
}
