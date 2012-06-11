package org.opaeum.topcased.propertysections.property;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertyConstants;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Profile;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Stereotype;
import org.eclipse.uml2.uml.TypedElement;
import org.eclipse.uml2.uml.UMLPackage;
import org.opaeum.eclipse.EmfElementFinder;
import org.opaeum.metamodel.core.internal.StereotypeNames;
import org.opaeum.metamodel.core.internal.TagNames;
import org.opaeum.topcased.propertysections.property.roleincube.DerivationFormulaText;
import org.opaeum.topcased.propertysections.property.roleincube.TypedElementContentProposalProvider;
import org.topcased.tabbedproperties.utils.TextChangeListener;

public class PropertyRoleInCubeSection extends AbstractRoleInCubeSection{
	private DerivationFormulaText derivationFormula;
	private Label derivationFormulaLabel;
	protected Stereotype findStereotype(Profile applyNakedUmlProfile){
		Stereotype propertyStereotype = null;
		if(safeGetProperty().getAssociation() == null){
			propertyStereotype = applyNakedUmlProfile.getOwnedStereotype(StereotypeNames.ATTRIBUTE);
		}else{
			propertyStereotype = applyNakedUmlProfile.getOwnedStereotype(StereotypeNames.ASSOCIATION_END);
		}
		return propertyStereotype;
	}
	public Property safeGetProperty(){
		if(getEObject() instanceof Association){
			Association a = (Association) getEObject();
			if(a.getMemberEnds().size() < 2){
				return null;
			}
		}
		return getProperty();
	}
	protected final Property getProperty(){
		return (Property) getTypedElementFrom(getEObject());
	}
	@Override
	protected TypedElement getTypedElementFrom(EObject object){
		return (TypedElement)object;
	}
	protected boolean isMany(){
		return safeGetProperty().getUpper() > 1 || safeGetProperty().getUpper() == -1 || safeGetProperty().getQualifiers().size() > 0;
	}
	@Override
	protected void handleModelChanged(Notification msg){
		if(safeGetProperty() != null){
			super.handleModelChanged(msg);
			switch(msg.getFeatureID(Property.class)){
			case UMLPackage.PROPERTY__IS_DERIVED:
			case UMLPackage.PROPERTY__UPPER:
				refresh();
			}
		}
	}
	@Override
	protected void setRole(String name){
		super.setRole(name);
		if(name.equals("NONE")){
			if(derivationFormula != null){
				derivationFormula.getTextControl().setEnabled(false);
			}
		}
	}
	@Override
	protected void displayFormulas(){
		removeFormula();
		if(safeGetProperty().isDerived()){
			this.derivationFormulaLabel = getWidgetFactory().createLabel(check.getParent(), "Derivation Formula");
			FormData lfd = new FormData();
			this.derivationFormulaLabel.setLayoutData(lfd);
			lfd.left = new FormAttachment(check);
			lfd.width = 120;
			lfd.top = new FormAttachment(0, ITabbedPropertyConstants.VSPACE);
			this.derivationFormula = new DerivationFormulaText(check.getParent(), SWT.BORDER);
			String value = null;
			if(propertyStereotype != null && getTypedElementFrom(getEObject()).isStereotypeApplied(propertyStereotype)){
				value = (String) getTypedElementFrom(getEObject()).getValue(propertyStereotype, TagNames.DERIVATION_FORMULA);
			}
			value = value == null ? "" : value;
			this.derivationFormula.getTextControl().setText(value);
			new TextChangeListener(){
				@Override
				public void textChanged(Control control){
					EObject owner = safeGetProperty().getStereotypeApplication(propertyStereotype);
					EStructuralFeature feature = propertyStereotype.getDefinition().getEStructuralFeature(TagNames.DERIVATION_FORMULA);
					getEditingDomain().getCommandStack().execute(
							SetCommand.create(getEditingDomain(), owner, feature, derivationFormula.getTextControl().getText()));
				}
				@Override
				public void focusOut(Control control){
				}
				@Override
				public void focusIn(Control control){
				}
			}.startListeningTo(derivationFormula.getTextControl());
			FormData fd = new FormData();
			this.derivationFormula.setLayoutData(fd);
			fd.left = new FormAttachment(derivationFormulaLabel);
			fd.right = new FormAttachment(100, 0);
			fd.top = new FormAttachment(0, ITabbedPropertyConstants.VSPACE);
			check.getParent().layout();
			check.getParent();
			Classifier owner = (Classifier) EmfElementFinder.getContainer(safeGetProperty());
			derivationFormula.setContentProposalProvider(new TypedElementContentProposalProvider(super.propertyStereotype, owner
					.getAllAttributes()));
			boolean selection = check.getSelection();
			derivationFormula.getTextControl().setEnabled(selection);
			derivationFormula.setEnabled(selection);
		}else{
			super.displayFormulas();
		}
	}
	@Override
	public void refresh(){
		super.refresh();
	}
	protected void removeFormula(){
		if(this.derivationFormula != null){
			this.derivationFormula.dispose();
			this.derivationFormula.getTextControl().dispose();
			this.derivationFormulaLabel.dispose();
			derivationFormula = null;
			derivationFormulaLabel = null;
		}
	}
}