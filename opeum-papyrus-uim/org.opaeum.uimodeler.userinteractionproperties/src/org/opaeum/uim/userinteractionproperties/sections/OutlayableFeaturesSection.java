package org.opaeum.uim.userinteractionproperties.sections;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.opaeum.topcased.propertysections.base.AbstractMultiFeaturePropertySection;
import org.opaeum.uim.panel.Outlayable;
import org.opaeum.uim.panel.PanelPackage;

public class OutlayableFeaturesSection extends AbstractMultiFeaturePropertySection{
	private Text preferredWidth;
	private Text preferredHeight;
	private Button fillHorizontally;
	private Button fillVertically;
	private Label label;
	private Label toLabel;
	protected void setSectionData(Composite composite){
		layout(null, label, 143);
		layout(label, preferredWidth, 30);
		layout(preferredWidth, toLabel, 120);
		layout(toLabel, preferredHeight, 30);
		layout(preferredHeight, fillHorizontally, 120);
		layout(fillHorizontally, fillVertically, 120);
	}
	@Override
	protected Outlayable getFeatureOwner(){
		return this.getProperty();
	}
	protected Outlayable getProperty(){
		return (Outlayable) getEObject();
	}
	@Override
	protected void createWidgets(Composite composite){
		super.createWidgets(composite);
		label = getWidgetFactory().createLabel(composite, "Preferred Width:");
		toLabel = getWidgetFactory().createLabel(composite, "Preferred Height");
		preferredWidth = getWidgetFactory().createText(composite, "", SWT.SINGLE);
		preferredHeight = getWidgetFactory().createText(composite, "", SWT.SINGLE);
		fillHorizontally = getWidgetFactory().createButton(composite, "Fill Horizontally", SWT.CHECK);
		fillVertically = getWidgetFactory().createButton(composite, "Fill Vertically", SWT.CHECK);
	}
	protected void hookListeners(){
		new LiteralIntegerTextChangeListener(PanelPackage.eINSTANCE.getOutlayable_PreferredWidth()).startListeningTo(preferredWidth);
		new LiteralIntegerTextChangeListener(PanelPackage.eINSTANCE.getOutlayable_PreferredHeight()).startListeningTo(preferredHeight);
		fillHorizontally.addSelectionListener(new BooleanSelectionListener(PanelPackage.eINSTANCE.getOutlayable_FillHorizontally()));
		fillVertically.addSelectionListener(new BooleanSelectionListener(PanelPackage.eINSTANCE.getOutlayable_FillVertically()));
	}
	@Override
	protected String getLabelText(){
		return "";
	}
	@Override
	public void refresh(){
		preferredWidth.setText(getProperty().getPreferredWidth()==null?"":getProperty().getPreferredWidth().toString());
		preferredHeight.setText(getProperty().getPreferredHeight()==null?"":getProperty().getPreferredHeight().toString());
		fillHorizontally.setSelection(Boolean.TRUE.equals(getProperty().getFillHorizontally()));
		fillVertically.setSelection(Boolean.TRUE.equals(getProperty().getFillVertically()));
	}
}
