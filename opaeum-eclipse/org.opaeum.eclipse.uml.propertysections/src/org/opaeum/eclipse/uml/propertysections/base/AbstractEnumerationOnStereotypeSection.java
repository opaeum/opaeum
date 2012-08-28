package org.opaeum.eclipse.uml.propertysections.base;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertyConstants;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Stereotype;
import org.opaeum.eclipse.ProfileApplier;
import org.opaeum.eclipse.commands.ApplyStereotypeCommand;
import org.topcased.tabbedproperties.sections.AbstractTabbedPropertySection;

public abstract class AbstractEnumerationOnStereotypeSection extends AbstractTabbedPropertySection{
	private Stereotype stereotype;
	private ComboViewer combo;
	private Label label;
	private EStructuralFeature feature;
	private EList<EEnumLiteral> literals;
	public AbstractEnumerationOnStereotypeSection(){
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
		if(stereotype != null){
			this.feature = this.stereotype.getDefinition().getEStructuralFeature(getAttributeName());
			if(this.feature != null && this.feature.getEType() instanceof EEnum){
				this.literals = ((EEnum) this.feature.getEType()).getELiterals();
			}
		}
	}
	@Override
	public void refresh(){
		super.refresh();
		if(literals != null){
			combo.setInput(literals.toArray());
			if(getElement().isStereotypeApplied(stereotype)){
				Object value = getElement().getValue(stereotype, getAttributeName());
				if(value != null){
					for(EEnumLiteral e:literals){
						if(e.getName().equals(((NamedElement) value).getName())){
							combo.getCCombo().select(literals.indexOf(e));
						}
					}
				}
			}
		}
	}
	@Override
	protected void setSectionData(Composite composite){
		FormData data = new FormData();
		data.left = new FormAttachment(0, getStandardLabelWidth(composite, new String[]{getLabelText()}));
		data.right = new FormAttachment(100, 0);
		data.top = new FormAttachment(0, ITabbedPropertyConstants.VSPACE);
		data.height = 18;
		combo.getCCombo().setLayoutData(data);
		data = new FormData();
		data.left = new FormAttachment(0, 0);
		data.right = new FormAttachment(combo.getCCombo(), -ITabbedPropertyConstants.HSPACE);
		data.top = new FormAttachment(combo.getCCombo(), 0, SWT.TOP);
		data.height = 15;
		label.setLayoutData(data);
	}
	@Override
	protected void createWidgets(Composite composite){
		super.createWidgets(composite);
		this.label = getWidgetFactory().createLabel(composite, getLabelText());
		this.combo = new ComboViewer(getWidgetFactory().createCCombo(composite, SWT.READ_ONLY));
		this.combo.setLabelProvider(new LabelProvider(){
			public String getText(Object element){
				return element == null ? "" : ((EEnumLiteral) element).getName();
			}
		});
		this.combo.setContentProvider(new ArrayContentProvider());
		this.combo.addSelectionChangedListener(new ISelectionChangedListener(){
			@Override
			public void selectionChanged(SelectionChangedEvent event){
				if(!getElement().isStereotypeApplied(stereotype)){
					Command cmd = new ApplyStereotypeCommand(getElement(), stereotype);
					getEditingDomain().getCommandStack().execute(cmd);
				}
				Command cmd = SetCommand.create(getEditingDomain(), getElement().getStereotypeApplication(stereotype), feature,
						((StructuredSelection) event.getSelection()).getFirstElement());
				getEditingDomain().getCommandStack().execute(cmd);
			}
		});
	}
}