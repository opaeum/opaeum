package org.opaeum.topcased.propertysections.base;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.jface.text.TextViewer;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertyConstants;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Stereotype;
import org.opaeum.eclipse.ProfileApplier;
import org.opaeum.eclipse.commands.ApplyStereotypeCommand;
import org.topcased.tabbedproperties.sections.AbstractTabbedPropertySection;
import org.topcased.tabbedproperties.utils.TextChangeListener;

public abstract class AbstractStringOnStereotypeSection extends AbstractTabbedPropertySection{
	private Stereotype stereotype;
	private StyledText text;
	private Label label;
	private EStructuralFeature feature;
	public AbstractStringOnStereotypeSection(){
		super();
	}
	protected abstract Element getElement();
	protected abstract String getAttributeName();
	protected abstract String getProfileName();
	protected abstract String getStereotypeName();
	@Override
	protected EStructuralFeature getFeature(){
		return null;
	}
	@Override
	public void setInput(IWorkbenchPart part,ISelection selection){
		super.setInput(part, selection);
		this.stereotype = ProfileApplier.getProfile(getElement(), getProfileName()).getOwnedStereotype(getStereotypeName());
		this.feature = this.stereotype.getDefinition().getEStructuralFeature(getAttributeName());
	}
	@Override
	public void refresh(){
		super.refresh();
		if(getElement().isStereotypeApplied(stereotype)){
			String value = (String) getElement().getValue(stereotype, getAttributeName());
			text.setText(value == null ? "" : value);
		}else{
			text.setText("");
		}
	}
	@Override
	protected void setSectionData(Composite composite){
		FormData data = new FormData();
		data.left = new FormAttachment(0, getStandardLabelWidth(composite, new String[]{getLabelText()}));
		data.right = new FormAttachment(100, 0);
		data.top = new FormAttachment(0, ITabbedPropertyConstants.VSPACE);
		data.height = 18;
		text.setLayoutData(data);
		data = new FormData();
		data.left = new FormAttachment(0, 0);
		data.right = new FormAttachment(text, -ITabbedPropertyConstants.HSPACE);
		data.top = new FormAttachment(text, 0, SWT.TOP);
		data.height = 15;
		label.setLayoutData(data);
	}
	@Override
	protected void createWidgets(Composite composite){
		super.createWidgets(composite);
		this.label = getWidgetFactory().createLabel(composite, getLabelText());
		this.text = new TextViewer(composite, SWT.BORDER | SWT.BORDER | SWT.SINGLE).getTextWidget();
		new TextChangeListener(){
			@Override
			public void textChanged(Control control){
				if(!getElement().isStereotypeApplied(stereotype)){
					Command cmd = new ApplyStereotypeCommand(getElement(), stereotype);
					getEditingDomain().getCommandStack().execute(cmd);
				}
				Command cmd = SetCommand.create(getEditingDomain(), getElement().getStereotypeApplication(stereotype), feature, text.getText());
				getEditingDomain().getCommandStack().execute(cmd);
			}
			@Override
			public void focusOut(Control control){
				textChanged(control);
			}
			@Override
			public void focusIn(Control control){
			}
		}.startListeningForEnter(text);
	}
}