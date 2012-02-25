package org.opaeum.topcased.propertysections.classifier;

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
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Stereotype;
import org.opaeum.eclipse.ProfileApplier;
import org.opaeum.metamodel.core.internal.StereotypeNames;
import org.topcased.tabbedproperties.sections.AbstractTabbedPropertySection;

public class BusinessDocumentDocumentTypeSection extends AbstractTabbedPropertySection{
	private Stereotype stereotype;
	private ComboViewer combo;
	private Label label;
	private EStructuralFeature feature;
	private EList<EEnumLiteral> literals;
	@Override
	protected EStructuralFeature getFeature(){
		return null;
	}
	@Override
	protected String getLabelText(){
		return "Document Type";
	}
	@Override
	public void setInput(IWorkbenchPart part,ISelection selection){
		super.setInput(part, selection);
		this.stereotype = ProfileApplier.getProfile(getClassifier(), StereotypeNames.OPAEUM_BPM_PROFILE)
				.getOwnedStereotype(StereotypeNames.BUSINESS_DOCUMENT);
		this.feature = this.stereotype.getDefinition().getEStructuralFeature("documentType");
		this.literals = ((EEnum) this.feature.getEType()).getELiterals();
	}
	private Classifier getClassifier(){
		return (Classifier) getEObject();
	}
	@Override
	public void refresh(){
		super.refresh();
		combo.setInput(literals.toArray());
		combo.setSelection(new StructuredSelection(getClassifier().getValue(stereotype, "documentType")));
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
				Command cmd = SetCommand.create(getEditingDomain(), getClassifier().getStereotypeApplication(stereotype), stereotype
						.getDefinition().getEStructuralFeature("nameProperty"), ((StructuredSelection) event.getSelection()).getFirstElement());
				getEditingDomain().getCommandStack().execute(cmd);
			}
		});
	}
}
