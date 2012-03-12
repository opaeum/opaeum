package org.opaeum.uim.userinteractionproperties.sections;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.opaeum.topcased.propertysections.base.AbstractMultiFeaturePropertySection;
import org.opaeum.uim.constraint.ConstrainedObject;
import org.opaeum.uim.constraint.ConstraintFactory;
import org.opaeum.uim.constraint.ConstraintPackage;
import org.opaeum.uim.constraint.UserInteractionConstraint;

public abstract class AbstractUserInteractionConstraintBooleanFeaturesSection extends AbstractMultiFeaturePropertySection{
	public class ConstraintBooleanPropertySelectionListener extends BooleanSelectionListener{
		private final EReference constraintFeature;
		private final boolean defaultValue;
		private ConstraintBooleanPropertySelectionListener(EAttribute booleanProperty,EReference constraintFeature,boolean defaultValue){
			super(booleanProperty);
			this.constraintFeature = constraintFeature;
			this.defaultValue = defaultValue;
		}
		public void widgetSelected(SelectionEvent e){
			if(((Button) e.widget).getSelection() != defaultValue){
				if(getFeatureOwner() == null){
					UserInteractionConstraint constr = ConstraintFactory.eINSTANCE.createUserInteractionConstraint();
					Command cmd = SetCommand.create(getEditingDomain(), getEObject(), constraintFeature, constr);
					getEditingDomain().getCommandStack().execute(cmd);
				}
			}
			super.widgetSelected(e);
		}
	}
	private Button inheritFromParent;
	protected abstract EReference getConstraintContainingFeature();
	private Button requiresGroupOwnership;
	private Button requiresOwnership;
	public AbstractUserInteractionConstraintBooleanFeaturesSection(){
		super();
	}
	protected void setSectionData(Composite composite){
		layout(null, inheritFromParent, 170);
		layout(inheritFromParent, requiresGroupOwnership, 170);
		layout(requiresGroupOwnership, requiresOwnership, 170);
	}
	@Override
	protected UserInteractionConstraint getFeatureOwner(){
		return (UserInteractionConstraint) ((ConstrainedObject) this.getEObject()).eGet(getConstraintContainingFeature());
	}
	@Override
	protected void createWidgets(Composite composite){
		super.createWidgets(composite);
		inheritFromParent = getWidgetFactory().createButton(composite, "Inherits from Parent", SWT.CHECK);
		requiresGroupOwnership = getWidgetFactory().createButton(composite, "Requires Group Ownership", SWT.CHECK);
		requiresOwnership = getWidgetFactory().createButton(composite, "Requires User Ownership", SWT.CHECK);
	}
	protected void hookListeners(){
		EReference v = getConstraintContainingFeature();
		inheritFromParent.addSelectionListener(new ConstraintBooleanPropertySelectionListener(ConstraintPackage.eINSTANCE
				.getUserInteractionConstraint_InheritFromParent(), v, false){
			@Override
			public void widgetSelected(SelectionEvent e){
				super.widgetSelected(e);

				updateBoxes();
			}

		});
		requiresGroupOwnership.addSelectionListener(new ConstraintBooleanPropertySelectionListener(ConstraintPackage.eINSTANCE
				.getRootUserInteractionConstraint_RequiresGroupOwnership(), v, true));
		requiresOwnership.addSelectionListener(new ConstraintBooleanPropertySelectionListener(ConstraintPackage.eINSTANCE
				.getRootUserInteractionConstraint_RequiresOwnership(), v, false));
	}
	protected void updateBoxes(){
		requiresOwnership.setEnabled(!inheritFromParent.getSelection());
		requiresGroupOwnership.setEnabled(!inheritFromParent.getSelection());
	}
	@Override
	protected String getLabelText(){
		return "";
	}
	@Override
	public void refresh(){
		if(getFeatureOwner() == null){
			inheritFromParent.setSelection(true);
			requiresGroupOwnership.setSelection(true);
			requiresOwnership.setSelection(false);
		}else{
			inheritFromParent.setSelection(getFeatureOwner().isInheritFromParent());
			requiresGroupOwnership.setSelection(getFeatureOwner().isRequiresGroupOwnership());
			requiresOwnership.setSelection(getFeatureOwner().isRequiresOwnership());
		}
		updateBoxes();
	}
}