package org.nakeduml.topcased.propertysections;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.MultiplicityElement;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.UMLPackage;

public class MultiplicityElementFeaturesSection extends AbstractMultiFeaturePropertySection{
	private Text from;
	private Text to;
	private Button isUnique;
	private Button isOrdered;
	private Label label;
	private Label toLabel;
	protected void setSectionData(Composite composite){
		layout(null, label, 143);
		layout(label, from, 30);
		layout(from, toLabel, 10);
		layout(toLabel, to, 30);
		layout(to, isUnique, 140);
		layout(isUnique, isOrdered, 140);
	}
	@Override
	protected Element getFeatureOwner(){
		return this.getProperty();
	}
	protected MultiplicityElement getProperty(){
		return (MultiplicityElement) getEObject();
	}
	@Override
	protected void createWidgets(Composite composite){
		super.createWidgets(composite);
		label = getWidgetFactory().createLabel(composite, "Number of values:");
		toLabel = getWidgetFactory().createLabel(composite, "to");
		from = getWidgetFactory().createText(composite, "", SWT.SINGLE);
		to = getWidgetFactory().createText(composite, "", SWT.SINGLE);
		isUnique = getWidgetFactory().createButton(composite, "Every value is unique", SWT.CHECK);
		isOrdered = getWidgetFactory().createButton(composite, "Values are ordered", SWT.CHECK);
	}
	protected void hookListeners(){
		new LiteralIntegerTextChangeListener(UMLPackage.eINSTANCE.getMultiplicityElement_Lower()).startListeningTo(from);
		new LiteralIntegerTextChangeListener(UMLPackage.eINSTANCE.getMultiplicityElement_Upper()).startListeningTo(to);
		isUnique.addSelectionListener(new BooleanSelectionListener(UMLPackage.eINSTANCE.getMultiplicityElement_IsUnique()));
		isOrdered.addSelectionListener(new BooleanSelectionListener(UMLPackage.eINSTANCE.getMultiplicityElement_IsOrdered()));
	}
	@Override
	protected String getLabelText(){
		return "";
	}
	@Override
	public void refresh(){
		from.setText(getProperty().getLower() == -1 ? "*" : getProperty().getLower() + "");
		to.setText(getProperty().getUpper() == -1 ? "*" : getProperty().getUpper() + "");
		isUnique.setSelection(getProperty().isUnique());
		isOrdered.setSelection(getProperty().isOrdered());
	}
}
