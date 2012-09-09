package org.opaeum.eclipse.uml.propertysections.base;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EObject;
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
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.Profile;
import org.eclipse.uml2.uml.Stereotype;
import org.opaeum.eclipse.ProfileApplier;
import org.opaeum.eclipse.commands.ApplyProfileCommand;
import org.opaeum.eclipse.commands.ApplyStereotypeCommand;
import org.opaeum.emf.extraction.StereotypesHelper;
import org.topcased.tabbedproperties.sections.AbstractTabbedPropertySection;
import org.topcased.tabbedproperties.utils.TextChangeListener;

public abstract class AbstractStringOnStereotypeSection extends AbstractTabbedPropertySection{
	private StyledText text;
	private Label label;
	public AbstractStringOnStereotypeSection(){
		super();
	}
	protected abstract Element getElement(EObject o);
	protected abstract String getAttributeName();
	protected abstract String getProfileName();
	protected abstract String getStereotypeName(Element e);
	@Override
	protected EStructuralFeature getFeature(){
		return null;
	}
	@Override
	public void setInput(IWorkbenchPart part,ISelection selection){
		super.setInput(part, selection);
	}
	@Override
	public void refresh(){
		super.refresh();
		Set<String> values = new HashSet<String>();
		List<EObject> eObjectList = getEObjectList();
		for(EObject eObject:eObjectList){
			Element element = getElement(eObject);
			Stereotype st = StereotypesHelper.getStereotype(element, getStereotypeName(element));
			if(st != null){
				String value = (String) getElement(eObject).getValue(st, getAttributeName());
				values.add(value == null ? "" : value);
			}else{
				values.add("");
			}
		}
		if(values.size() == 1){
			text.setText(values.iterator().next());
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
		this.text = new TextViewer(composite, SWT.BORDER | SWT.FLAT | SWT.SINGLE).getTextWidget();
		TextChangeListener textChangeListener = new TextChangeListener(){
			@Override
			public void textChanged(Control control){
				CompoundCommand cc = new CompoundCommand();
				for(EObject eObject:getEObjectList()){
					Element element = getElement(eObject);
					Profile p = ProfileApplier.getAppliedProfile(element.getModel(), getProfileName());
					if(p == null){
						Package pkg = element.getModel() == null ? element.getNearestPackage() : element.getModel();
						getEditingDomain().getCommandStack().execute(
								new ApplyProfileCommand(pkg, p = ProfileApplier.getProfile(element, getProfileName()), false));
					}
					Stereotype stereotype = p.getOwnedStereotype(getStereotypeName(element));
					EStructuralFeature feature = stereotype.getDefinition().getEStructuralFeature(getAttributeName());
					if(!element.isStereotypeApplied(stereotype)){
						getEditingDomain().getCommandStack().execute(new ApplyStereotypeCommand(element, stereotype));
					}
					cc.append(SetCommand.create(getEditingDomain(), element.getStereotypeApplication(stereotype), feature, text.getText()));
				}
				getEditingDomain().getCommandStack().execute(cc);
			}
			@Override
			public void focusOut(Control control){
				textChanged(control);
			}
			@Override
			public void focusIn(Control control){
			}
		};
		textChangeListener.startListeningForEnter(text);
		textChangeListener.startListeningTo(text);
	}
}