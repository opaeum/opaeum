package org.opaeum.eclipse.uml.propertysections.standardprofile;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.uml2.uml.DataType;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Enumeration;
import org.eclipse.uml2.uml.EnumerationLiteral;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.LiteralInteger;
import org.eclipse.uml2.uml.MultiplicityElement;
import org.eclipse.uml2.uml.PrimitiveType;
import org.eclipse.uml2.uml.Profile;
import org.eclipse.uml2.uml.Stereotype;
import org.eclipse.uml2.uml.TypedElement;
import org.eclipse.uml2.uml.UMLPackage;
import org.opaeum.eclipse.EmfClassifierUtil;
import org.opaeum.eclipse.commands.ApplyOpaeumStandardProfileCommand;
import org.opaeum.eclipse.commands.ApplyStereotypeCommand;
import org.opaeum.eclipse.uml.propertysections.property.AbstractTypedAndMultiplicityElementPropertySection;
import org.opaeum.metamodel.core.internal.TagNames;

public abstract class AbstractRoleInCubeSection extends AbstractTypedAndMultiplicityElementPropertySection{
	protected Button check;
	private Collection<Button> formulaChecks = new HashSet<Button>();
	Stereotype propertyStereotype;
	EEnum roleEnumeration;
	EEnum formulaEnumeration;
	@Deprecated
	
	private Label label;
	public AbstractRoleInCubeSection(){
		super();
	}
	@Override
	public Control getPrimaryInput(){
		return check;
	}
	@Override
	public void setInput(IWorkbenchPart part,ISelection selection){
		super.setInput(part, selection);
		if(((Element) getSelectedObject()).getModel() != null){
			ApplyOpaeumStandardProfileCommand cmd = new ApplyOpaeumStandardProfileCommand(getEditingDomain(), ((Element) getSelectedObject()).getModel());
			Profile profile = cmd.getProfile();
			Stereotype propertyStereotype = findStereotype(profile);
			this.propertyStereotype = propertyStereotype;
			roleEnumeration = (EEnum) profile.getDefinition().getEClassifier("RoleInCube");
			formulaEnumeration = (EEnum) profile.getDefinition().getEClassifier("AggregationFormula");
		}
	}
	@Override
	protected EStructuralFeature getFeature(){
		return null;
	}
	protected abstract Stereotype findStereotype(Profile applyNakedUmlProfile);
	@Override
	public String getLabelText(){
		return "Role in Cube";
	}
	@Override
	protected void createWidgets(Composite composite){
		super.createWidgets(composite);
		getWidgetFactory().setBorderStyle(SWT.NULL);
		this.check = getWidgetFactory().createButton(composite, "Is Measure", SWT.CHECK);
		check.addSelectionListener(new SelectionListener(){
			@Override
			public void widgetSelected(SelectionEvent e){
				if(!check.getSelection()){
					setRole("NONE");
				}else if(couldBeDimension()){
					setRole("DIMENSION");
				}else if(couldBeMeasure()){
					setRole("MEASURE");
					displayFormulas();
				}
			}
			@Override
			public void widgetDefaultSelected(SelectionEvent e){
			}
		});
		FormData fd = new FormData();
		fd.width = 120;
		fd.left = new FormAttachment(labelCombo);
		check.setLayoutData(fd);
	}
	@Override
	public void populateControls(){
		super.populateControls();

		for(Button button:this.formulaChecks){
			button.dispose();
		}
		formulaChecks.clear();
		if(isMany() || propertyStereotype == null || roleEnumeration == null || formulaEnumeration == null){
			check.setEnabled(false);
		}else{
			if(propertyStereotype != null && roleEnumeration != null){
				// NB!!! For Some reason EMF returns a UML.EnumerationLiteral and not an ECORE.EEnumLiteral, but only for single value
				// types
				if(couldBeDimension()){
					prepareForDimension();
				}else if(couldBeMeasure()){
					prepareForMeasure();
				}else{
					check.setEnabled(false);
				}
			}else{
				check.setEnabled(false);
			}
		}
		check.redraw();
		check.getParent().layout();
		check.getParent().getParent().layout();
		check.getParent().getParent().getParent().layout();
	}
	protected void prepareForDimension(){
		check.setText("Is Dimension");
		check.setEnabled(true);
		if(getTypedElementFrom(getSelectedObject()).isStereotypeApplied(propertyStereotype)){
			EEnumLiteral dimension = roleEnumeration.getEEnumLiteral("DIMENSION");
			EnumerationLiteral value = (EnumerationLiteral) getTypedElementFrom(getSelectedObject()).getValue(propertyStereotype, TagNames.ROLE_IN_CUBE);
			check.setSelection(dimension.getName().equals(value.getName()));
		}
	}
	protected void prepareForMeasure(){
		check.setText("Is Measure");
		if(getTypedElementFrom(getSelectedObject()).isStereotypeApplied(propertyStereotype)){
			EEnumLiteral dimension = roleEnumeration.getEEnumLiteral("MEASURE");
			EnumerationLiteral value = (EnumerationLiteral) getTypedElementFrom(getSelectedObject()).getValue(propertyStereotype, TagNames.ROLE_IN_CUBE);
			check.setSelection(dimension.getName().equals(value.getName()));
		}
		check.setEnabled(true);
		displayFormulas();
	}
	protected boolean isMany(){
		if(getTypedElementFrom(getSelectedObject()) instanceof MultiplicityElement){
			int upper = ((MultiplicityElement) getTypedElementFrom(getSelectedObject())).getUpper();
			return upper > 1 || upper == -1;
		}else{
			return false;
		}
	}
	@Override
	protected void handleModelChanged(Notification msg){
		if(!this.check.isDisposed()){
			super.handleModelChanged(msg);
			Object notifier = msg.getNotifier();
			if(notifier instanceof Element){
				switch(msg.getFeatureID(MultiplicityElement.class)){
				case UMLPackage.MULTIPLICITY_ELEMENT__UPPER:
				case UMLPackage.MULTIPLICITY_ELEMENT__UPPER_VALUE:
					checkRoleAndRefresh();
				}
				switch(msg.getFeatureID(LiteralInteger.class)){
				case UMLPackage.LITERAL_INTEGER__VALUE:
					checkRoleAndRefresh();
				}
				switch(msg.getFeatureID(TypedElement.class)){
				case UMLPackage.TYPED_ELEMENT__TYPE:
					checkRoleAndRefresh();
				}
				switch(msg.getFeatureID(TypedElement.class)){
				case UMLPackage.TYPED_ELEMENT__TYPE:
					checkRoleAndRefresh();
				}
			}
		}
	}
	private void checkRoleAndRefresh(){
		if(check.getText().equals("Is Measure") && !couldBeMeasure()){
			setRole("NONE");
		}
		if(check.getText().equals("Is Dimension") && !couldBeDimension()){
			setRole("NONE");
		}
		refresh();
	}
	private boolean couldBeDimension(){
		TypedElement p = getTypedElementFrom(getSelectedObject());
		if(p.getType() instanceof org.eclipse.uml2.uml.Class || p.getType() instanceof Enumeration || p.getType() instanceof Interface){
			return true;
		}else if(p.getType() instanceof PrimitiveType){
			return EmfClassifierUtil.comformsToLibraryType(p.getType(), "String")
					|| EmfClassifierUtil.comformsToLibraryType(p.getType(), "Boolean");
		}else if(p.getType() instanceof DataType){
			return EmfClassifierUtil.comformsToLibraryType(p.getType(), "DateTime")
					|| EmfClassifierUtil.comformsToLibraryType(p.getType(), "Date");
		}else{
			return false;
		}
	}
	private boolean couldBeMeasure(){
		TypedElement p = getTypedElementFrom(getSelectedObject());
		if(p.getType() instanceof org.eclipse.uml2.uml.Class || p.getType() instanceof Enumeration){
			return false;
		}else if(p.getType() instanceof PrimitiveType){
			return EmfClassifierUtil.comformsToLibraryType(p.getType(), "Real")
					|| EmfClassifierUtil.comformsToLibraryType(p.getType(), "Integer")
					|| EmfClassifierUtil.comformsToLibraryType(p.getType(), "Float") || EmfClassifierUtil.comformsToLibraryType(p.getType(), "Short")
					|| EmfClassifierUtil.comformsToLibraryType(p.getType(), "Double") || EmfClassifierUtil.comformsToLibraryType(p.getType(), "Long");
		}else{
			return false;
		}
	}
	protected void setRole(String name){
		if(propertyStereotype != null){
			for(TypedElement eObject:getTypedElementList()){
				EditingDomain ed = getEditingDomain();
				ApplyOpaeumStandardProfileCommand cmd = new ApplyOpaeumStandardProfileCommand(getEditingDomain(), eObject.getModel());
				if(!eObject.getModel().isProfileApplied(cmd.getProfile())){
					getEditingDomain().getCommandStack().execute(cmd);
				}
				if(!eObject.isStereotypeApplied(propertyStereotype)){
					ed.getCommandStack().execute(new ApplyStereotypeCommand(eObject, propertyStereotype));
				}
				EObject sa = eObject.getStereotypeApplication(propertyStereotype);
				EStructuralFeature feat = propertyStereotype.getDefinition().getEStructuralFeature("roleInCube");
				ed.getCommandStack().execute(SetCommand.create(ed, sa, feat, roleEnumeration.getEEnumLiteral(name)));
				// getEditingDomain().getCommandStack().execute(
				// SetCommand.create(getEditingDomain(), getTypedElement().getStereotypeApplication(propertyStereotype),
				// propertyStereotype.getDefinition()
				// .getEStructuralFeature("roleInCube"), roleEnumeration.getOwnedLiteral(name)));
				if(name.equals("NONE") || name.equals("MEASURE")){
					if(name.equals("NONE")){
						for(Button button:formulaChecks){
							button.setEnabled(false);
							button.setSelection(false);
						}
					}else if(name.equals("MEASURE")){
						for(Button button:formulaChecks){
							button.setEnabled(true);
						}
					}
					EStructuralFeature aggForm = propertyStereotype.getDefinition().getEStructuralFeature("aggregationFormulas");
					ed.getCommandStack().execute(RemoveCommand.create(ed, sa, aggForm, sa.eGet(aggForm)));
				}
			}
		}
	}
	@SuppressWarnings("unchecked")
	protected void displayFormulas(){
		Button prev = check;
		for(final EEnumLiteral l:formulaEnumeration.getELiterals()){
			final Button c = getWidgetFactory().createButton(check.getParent(), l.getName(), SWT.CHECK);
			this.formulaChecks.add(c);
			FormData fd = new FormData();
			fd.left = new FormAttachment(prev);
			prev = c;
			c.setEnabled(check.getSelection());
			c.setLayoutData(fd);
			final List<EObject> value;
			if(getTypedElementFrom(getSelectedObject()).isStereotypeApplied(propertyStereotype)){
				EObject sa = getTypedElementFrom(getSelectedObject()).getStereotypeApplication(propertyStereotype);
				EStructuralFeature feat = propertyStereotype.getDefinition().getEStructuralFeature("aggregationFormulas");
				value = (List<EObject>) sa.eGet(feat);
			}else{
				value = new ArrayList<EObject>();
			}
			c.addSelectionListener(new SelectionListener(){
				@Override
				public void widgetSelected(SelectionEvent e){
					EditingDomain ed = getEditingDomain();
					List<EObject> eObjectList = getEObjectList();
					for(EObject eObject:eObjectList){
						TypedElement te = (TypedElement) eObject;
						if(!te.isStereotypeApplied(propertyStereotype)){
							ed.getCommandStack().execute(new ApplyStereotypeCommand(te, propertyStereotype));
						}
						EObject sa = te.getStereotypeApplication(propertyStereotype);
						EStructuralFeature feat = propertyStereotype.getDefinition().getEStructuralFeature("aggregationFormulas");
						if(c.getSelection()){
							ed.getCommandStack().execute(AddCommand.create(ed, sa, feat, l));
						}else{
							ed.getCommandStack().execute(RemoveCommand.create(ed, sa, feat, l));
						}
					}
				}
				@Override
				public void widgetDefaultSelected(SelectionEvent e){
				}
			});
			c.setSelection(value.contains(l));
		}
		check.getParent().pack();
		check.getParent().getParent().pack();
		check.getParent().layout();
		check.getParent().getParent().layout();
	}
}