package org.opaeum.uim.userinteractionproperties.sections;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.opaeum.eclipse.uml.propertysections.base.AbstractMultiFeaturePropertySection;
import org.opaeum.uim.panel.Outlayable;
import org.opaeum.uim.panel.PanelPackage;

public class OutlayableColumnFeaturesSection extends AbstractMultiFeaturePropertySection{
	private Text preferredWidth;
	private Button fillHorizontally;
	private Label label;
	private Label toLabel;
	boolean isRefreshing = false;
	protected void setSectionData(Composite composite){
		layout(null, label, 143);
		layout(label, preferredWidth, 30);
		layout(preferredWidth, fillHorizontally, 140);
	}
	@Override
	protected Outlayable getFeatureOwner(){
		return this.getProperty();
	}
	protected Outlayable getProperty(){
		return (Outlayable) getEObject();
	}
	@Override
	protected void handleModelChanged(Notification msg){
		super.handleModelChanged(msg);
		if(msg.getNotifier() instanceof Outlayable){
			refresh();
		}
	}
	@Override
	protected void createWidgets(Composite composite){
		super.createWidgets(composite);
		label = getWidgetFactory().createLabel(composite, "Preferred Width:");
		preferredWidth = getWidgetFactory().createText(composite, "", SWT.SINGLE);
		fillHorizontally = getWidgetFactory().createButton(composite, "Fill Horizontally", SWT.CHECK);
	}
	protected void hookListeners(){
		new LiteralIntegerTextChangeListener(PanelPackage.eINSTANCE.getOutlayable_PreferredWidth()).startListeningTo(preferredWidth);
		fillHorizontally.addSelectionListener(new BooleanSelectionListener(PanelPackage.eINSTANCE.getOutlayable_FillHorizontally()));
	}
	@Override
	protected String getLabelText(){
		return "";
	}
	@Override
	public void refresh(){
		if(!isRefreshing){
			isRefreshing = true;
			preferredWidth.setText(getProperty().getPreferredWidth() == null ? "" : getProperty().getPreferredWidth().toString());
			fillHorizontally.setSelection(Boolean.TRUE.equals(getProperty().getFillHorizontally()));
			isRefreshing=false;
		}
	}
}
