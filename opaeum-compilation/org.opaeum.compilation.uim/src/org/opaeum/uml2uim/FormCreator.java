package org.opaeum.uml2uim;

import java.util.Collection;

import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.TypedElement;
import org.opaeum.eclipse.EmfElementFinder;
import org.opaeum.eclipse.EmfPropertyUtil;
import org.opaeum.emf.workspace.EmfWorkspace;
import org.opaeum.uim.UimDataTable;
import org.opaeum.uim.UimFactory;
import org.opaeum.uim.UimField;
import org.opaeum.uim.UimPanel;
import org.opaeum.uim.UimTab;
import org.opaeum.uim.UimTabPanel;
import org.opaeum.uim.action.ActionFactory;
import org.opaeum.uim.action.ActionKind;
import org.opaeum.uim.action.BuiltInAction;
import org.opaeum.uim.binding.BindingFactory;
import org.opaeum.uim.binding.FieldBinding;
import org.opaeum.uim.binding.PropertyRef;
import org.opaeum.uim.binding.TableBinding;
import org.opaeum.uim.control.ControlKind;
import org.opaeum.uim.form.FormPanel;
import org.opaeum.uim.layout.LayoutFactory;
import org.opaeum.uim.layout.UimFullLayout;
import org.opaeum.uim.layout.UimGridLayout;
import org.opaeum.uim.layout.UimLayout;
import org.opaeum.uim.util.ControlUtil;
import org.opeum.name.NameConverter;

public class FormCreator{
	private UimGridLayout mainTabLayout;
	private UimTabPanel tabPanel;
	private FormPanel formPanel;
	private EmfWorkspace workspace;
	public FormCreator(EmfWorkspace w, FormPanel cf){
		this.formPanel = cf;
		this.workspace=w;
	}
	public void prepareFormPanel(String title,Collection<? extends TypedElement> typedElements){
		UimFullLayout formLayout = LayoutFactory.eINSTANCE.createUimFullLayout();
		formPanel.setLayout(formLayout);
		tabPanel = UimFactory.eINSTANCE.createUimTabPanel();
		formLayout.getChildren().add(tabPanel);
		UimTab tab = UimFactory.eINSTANCE.createUimTab();
		tab.setName("Edit Details");
		tabPanel.getChildren().add(tab);
		mainTabLayout = LayoutFactory.eINSTANCE.createUimGridLayout();
		mainTabLayout.setNumberOfColumns(1);
		tab.setLayout(mainTabLayout);
		addUserFields(typedElements);
	}
	private void addUserFields(Collection<? extends TypedElement> typedElements){
		for(TypedElement property:typedElements){
			if(property instanceof Property && ((Property) property).getOtherEnd() != null && ((Property) property).getOtherEnd().isComposite()){
			}else if(requiresTableTab(property)){
				addTableTabForField(property);
			}else if(requiresDetailsTab(property)){
				addDetailsTabForField(property);
			}else{
				addUserField(mainTabLayout, 390, property);
			}
		}
	}
	@SuppressWarnings({
			"unchecked","rawtypes"
	})
	private void addDetailsTabForField(TypedElement e){
		Classifier c = (Classifier) e.getType();
		// // Create tab and add to panel
		UimTab tab = UimFactory.eINSTANCE.createUimTab();
		this.tabPanel.getChildren().add(tab);
		tab.setName(NameConverter.separateWords(e.getName()));
		UimGridLayout tabLayout = LayoutFactory.eINSTANCE.createUimGridLayout();
		tabLayout.setNumberOfColumns(1);
		tab.setLayout(tabLayout);
		for(Property property:(Collection<Property>) (Collection) EmfElementFinder.getPropertiesInScope(c)){
			if(requiresTableTab(property)){
				// No further details
			}else if(property.getOtherEnd() == null || !property.getOtherEnd().isComposite()){
				addUserField(tabLayout, 390, e, property);
			}
		}
	}
	@SuppressWarnings({
			"rawtypes","unchecked"
	})
	private void addTableTabForField(TypedElement e){
		// Create tab and add to panel
		UimTab tab = UimFactory.eINSTANCE.createUimTab();
		tab.setName(NameConverter.separateWords(e.getName()));
		tabPanel.getChildren().add(tab);
		// Create tab XYLayout
		UimFullLayout tabLayout = LayoutFactory.eINSTANCE.createUimFullLayout();
		tab.setLayout(tabLayout);
		// cfeate Data TAble
		UimDataTable table = UimFactory.eINSTANCE.createUimDataTable();
		TableBinding binding = BindingFactory.eINSTANCE.createTableBinding();
		table.setBinding(binding);
		binding.setUmlElementUid(workspace.getId(e));
		tabLayout.getChildren().add(table);
		table.setLayout(LayoutFactory.eINSTANCE.createUimColumnLayout());
		Collection<Property> attrs = (Collection<Property>) (Collection) EmfElementFinder.getPropertiesInScope((Classifier) e.getType());
		for(Property property:attrs){
			if(property.getOtherEnd() == null || !property.getOtherEnd().isComposite()){
				addUserField(table.getLayout(), 0, property);
			}
		}
		// create fields
	}
	private boolean requiresTableTab(TypedElement property){
		return property.getType() instanceof org.eclipse.uml2.uml.Class && EmfPropertyUtil.isMany(property) && !ControlUtil.requiresManySelection(formPanel, property);
	}
	private boolean requiresDetailsTab(TypedElement property){
		return property.getType() instanceof org.eclipse.uml2.uml.Class && !EmfPropertyUtil.isMany(property)
				&& (!ControlUtil.requiresUserInput(formPanel, property) || EmfPropertyUtil.isComposite(property));
	}
	private int addUserField(UimLayout layout,int labelWidth,TypedElement...properties){
		TypedElement property = properties[properties.length - 1];
		UimField uf = UimFactory.eINSTANCE.createUimField();
		uf.setLabelWidth(labelWidth < 100 ? 0 : labelWidth / 2);
		uf.setName(NameConverter.separateWords(property.getName()));
		ControlKind controlKind = ControlUtil.getPreferredControlKind(formPanel, property);
		uf.setControlKind(controlKind);
		uf.setControl(ControlUtil.instantiate(uf.getControlKind()));
		FieldBinding binding = BindingFactory.eINSTANCE.createFieldBinding();
		uf.setBinding(binding);
		binding.setUmlElementUid(workspace.getId(properties[0]));
		if(properties.length > 1){
			PropertyRef prev = BindingFactory.eINSTANCE.createPropertyRef();
			prev.setUmlElementUid(workspace.getId(properties[1]));
			binding.setNext(prev);
			for(int i = 2;i < properties.length;i++){
				PropertyRef next = BindingFactory.eINSTANCE.createPropertyRef();
				next.setUmlElementUid(workspace.getId(properties[i]));
				prev.setNext(next);
				prev = next;
			}
		}
		// TODO lookupBinding
		layout.getChildren().add(uf);
		int height = ControlUtil.isMultiRow(controlKind) ? 200 : 25;
		return height;
	}
	public void addButtonBar(ActionKind...updateCurrentEntity){
		UimPanel panel = UimFactory.eINSTANCE.createUimPanel();
		this.mainTabLayout.getChildren().add(panel);
		UimGridLayout gl = LayoutFactory.eINSTANCE.createUimGridLayout();
		panel.setLayout(gl);
		gl.setNumberOfColumns(updateCurrentEntity.length);
		for(ActionKind actionKind:updateCurrentEntity){
			BuiltInAction bia = ActionFactory.eINSTANCE.createBuiltInAction();
			bia.setKind(actionKind);
			bia.setName(NameConverter.separateWords(NameConverter.capitalize(actionKind.getName())));
			gl.getChildren().add(bia);
		}
	}
}