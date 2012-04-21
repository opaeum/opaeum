package org.opaeum.uim.userinteractionproperties.sections;

import java.awt.Desktop.Action;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.uml2.uml.ActivityEdge;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.OpaqueAction;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.TimeEvent;
import org.eclipse.uml2.uml.Trigger;
import org.eclipse.uml2.uml.UMLPackage;
import org.opaeum.emf.extraction.StereotypesHelper;
import org.opaeum.emf.workspace.EmfWorkspace;
import org.opaeum.uim.binding.PropertyRef;
import org.opaeum.uim.cube.AxisEntry;
import org.opaeum.uim.cube.CubeFactory;
import org.opaeum.uim.cube.CubePackage;
import org.opaeum.uim.cube.DimensionBinding;
import org.opaeum.uim.cube.LevelProperty;
import org.opaeum.uim.util.UmlUimLinks;
import org.topcased.tabbedproperties.sections.AbstractTabbedPropertySection;

public class AxisEntryLevelPropertiesSection extends AbstractTabbedPropertySection{
	Composite checkBoxComposite;
	private CLabel label;
	private Composite parent;
	@Override
	protected EStructuralFeature getFeature(){
		return UMLPackage.eINSTANCE.getAcceptEventAction_Trigger();
	}
	@Override
	protected String getLabelText(){
		return "Select Levels";
	}
	protected void createWidgets(Composite composite){
		this.parent = composite;
		label = getWidgetFactory().createCLabel(composite, getLabelText());
		checkBoxComposite = getWidgetFactory().createGroup(composite, "Available Levels");
		FormData fd = new FormData();
		fd.left = new FormAttachment(0, getStandardLabelWidth(composite, new String[0]));
		fd.right = new FormAttachment(100);
		fd.bottom = new FormAttachment(100);
		this.checkBoxComposite.setLayoutData(fd);
		checkBoxComposite.setLayoutData(fd);
		checkBoxComposite.setLayout(new GridLayout(1, true));
	}
	@Override
	public void setInput(IWorkbenchPart part,ISelection selection){
		super.setInput(part, selection);
		refreshCheckBoxes();
	}
	private void refreshCheckBoxes(){
		Control[] children = checkBoxComposite.getChildren();
		for(Control control:children){
			control.dispose();
		}
		DimensionBinding dimensionBinding = getAxisEntry().getDimensionBinding();
		if(dimensionBinding != null){
			final List<Property> availableProperties = new ArrayList<Property>();
			if(dimensionBinding.getUmlElementUid() != null){
				availableProperties.add((Property) UmlUimLinks.getCurrentUmlLinks(dimensionBinding).getUmlElement(dimensionBinding));
			}
			PropertyRef next = dimensionBinding.getNext();
			while(next != null){
				availableProperties.add((Property) UmlUimLinks.getCurrentUmlLinks(dimensionBinding).getUmlElement(next));
				next = next.getNext();
			}
			for(final Property property:availableProperties){
				final Button btn = getWidgetFactory().createButton(checkBoxComposite, property.getName() + ":" + property.getType().getName(),
						SWT.CHECK);
				for(LevelProperty levelProperty2:getAxisEntry().getLevelProperty()){
					if(EmfWorkspace.getId(property).equals(levelProperty2.getUmlElementUid())){
						btn.setSelection(true);
					}
				}
				btn.addSelectionListener(new SelectionListener(){
					public void widgetSelected(SelectionEvent e){
						if(btn.getSelection()){
							LevelProperty lp = CubeFactory.eINSTANCE.createLevelProperty();
							lp.setUmlElementUid(EmfWorkspace.getId(property));
							int i = calculateInsertIndex(availableProperties, property);
							getEditingDomain().getCommandStack().execute(
									AddCommand.create(getEditingDomain(), getAxisEntry(), CubePackage.eINSTANCE.getAxisEntry_LevelProperty(), lp, i));
						}else{
							for(LevelProperty lp:getAxisEntry().getLevelProperty()){
								if(EmfWorkspace.getId(property).equals(lp.getUmlElementUid())){
									Command cmd = RemoveCommand.create(getEditingDomain(), getAxisEntry(),
											CubePackage.eINSTANCE.getAxisEntry_LevelProperty(), lp);
									getEditingDomain().getCommandStack().execute(cmd);
								}
							}
						}
					}
					private int calculateInsertIndex(final List<Property> availableProperties,final Property property){
						int indexInAvailableProperties = availableProperties.indexOf(property);
						List<LevelProperty> lps = getAxisEntry().getLevelProperty();
						int i = 0;
						for(LevelProperty levelProperty:lps){
							Element availableProperty = UmlUimLinks.getCurrentUmlLinks(levelProperty).getUmlElement(levelProperty);
							if(availableProperties.indexOf(availableProperty) == indexInAvailableProperties+1){
								break;
							}
							i++;
						}
						return i;
					}
					public void widgetDefaultSelected(SelectionEvent e){
					}
				});
			}
		}
		checkBoxComposite.layout();
		parent.getParent().getParent().layout();
	}
	@Override
	protected void handleModelChanged(Notification msg){
		super.handleModelChanged(msg);
		if(msg.getNotifier() == getAxisEntry()){
			switch(msg.getFeatureID(Action.class)){
			case CubePackage.AXIS_ENTRY__DIMENSION_BINDING:
				refreshCheckBoxes();
				break;
			}
		}
	}
	public AxisEntry getAxisEntry(){
		return (AxisEntry) getEObject();
	}
}
