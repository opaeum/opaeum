package org.opaeum.eclipse.uml.propertysections.standardprofile;

import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
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
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Stereotype;
import org.opaeum.eclipse.EmfClassifierUtil;
import org.opaeum.eclipse.EmfElementFinder;
import org.opaeum.eclipse.commands.ApplyOpaeumStandardProfileCommand;
import org.opaeum.eclipse.commands.ApplyStereotypeCommand;
import org.opaeum.emf.extraction.StereotypesHelper;
import org.opaeum.metamodel.core.internal.StereotypeNames;
import org.opaeum.metamodel.core.internal.TagNames;
import org.opaeum.topcased.uml.editor.OpaeumItemProviderAdapterFactory;
import org.topcased.tabbedproperties.sections.AbstractTabbedPropertySection;

public class ClassifierNamePropertySection extends AbstractTabbedPropertySection{
	private ComboViewer combo;
	private Label label;
	public String getStereotypeName(org.eclipse.uml2.uml.Element e){
		if(e instanceof org.eclipse.uml2.uml.Class){
			return StereotypeNames.ENTITY;
		}else{
			return e.eClass().getName();
		}
	}
	@Override
	protected EStructuralFeature getFeature(){
		return null;
	}
	@Override
	protected String getLabelText(){
		return "Name Property";
	}
	@Override
	public void setInput(IWorkbenchPart part,ISelection selection){
		super.setInput(part, selection);
	}
	private Classifier getClassifier(){
		return (Classifier) getEObject();
	}
	@Override
	public void refresh(){
		super.refresh();
		List<Property> props = EmfElementFinder.getPropertiesInScope(getClassifier());
		Iterator<Property> iterator = props.iterator();
		while(iterator.hasNext()){
			Property property = (Property) iterator.next();
			if(property.getType()==null || !EmfClassifierUtil.comformsToLibraryType(property.getType(), "String")){
				iterator.remove();
			}
		}
		combo.setInput(props);
		Stereotype stereotype = StereotypesHelper.getStereotype(getClassifier(), getStereotypeName(getClassifier()));
		StructuredSelection ss= new StructuredSelection();
		if(stereotype != null){
			Property nameProperty = (Property) getClassifier().getValue(stereotype, TagNames.NAME_PROPERTY);
			if(nameProperty!=null){
				ss=new StructuredSelection(nameProperty);
			}
		}
		combo.setSelection(ss);
	}
	@Override
	protected void setSectionData(Composite composite){
		FormData data = new FormData();
		data.left = new FormAttachment(0, getStandardLabelWidth(composite, new String[]{getLabelText()}));
		data.right = new FormAttachment(100, 0);
		data.top = new FormAttachment(0, ITabbedPropertyConstants.VSPACE);
		data.height = 18;
		combo.getControl(). setLayoutData(data);
		data = new FormData();
		data.left = new FormAttachment(0, 0);
		data.right = new FormAttachment(combo.getControl(), -ITabbedPropertyConstants.HSPACE);
		data.top = new FormAttachment(combo.getControl(), 0, SWT.TOP);
		data.height = 15;
		label.setLayoutData(data);
	}
	@Override
	protected void createWidgets(Composite composite){
		super.createWidgets(composite);
		this.label = getWidgetFactory().createLabel(composite, getLabelText());
		this.combo = new ComboViewer(getWidgetFactory().createCCombo(composite, SWT.READ_ONLY));
		this.combo.setContentProvider(new ArrayContentProvider());
		this.combo.setLabelProvider(new AdapterFactoryLabelProvider(new OpaeumItemProviderAdapterFactory()));
		this.combo.addSelectionChangedListener(new ISelectionChangedListener(){
			@Override
			public void selectionChanged(SelectionChangedEvent event){
				ApplyOpaeumStandardProfileCommand command = new ApplyOpaeumStandardProfileCommand(getEditingDomain(), getClassifier().getModel());
				getEditingDomain().getCommandStack().execute(command);
				Stereotype stereotype = command.getProfile().getOwnedStereotype(getStereotypeName((org.eclipse.uml2.uml.Element) getEObject()));
				if(!getClassifier().isStereotypeApplied(stereotype)){
					getEditingDomain().getCommandStack().execute(new ApplyStereotypeCommand(getClassifier(), stereotype));
				}
				Command cmd = SetCommand.create(getEditingDomain(), getClassifier().getStereotypeApplication(stereotype), stereotype
						.getDefinition().getEStructuralFeature(TagNames.NAME_PROPERTY), ((IStructuredSelection) combo.getSelection()).getFirstElement());
				getEditingDomain().getCommandStack().execute(cmd);
			}
		});
	}
}
