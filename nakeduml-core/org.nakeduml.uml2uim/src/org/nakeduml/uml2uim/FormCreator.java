package org.nakeduml.uml2uim;

import java.util.Collection;

import net.sf.nakeduml.emf.workspace.UmlElementCache;

import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.TypedElement;
import org.nakeduml.name.NameConverter;
import org.nakeduml.uim.UimDataTable;
import org.nakeduml.uim.UimFactory;
import org.nakeduml.uim.UimField;
import org.nakeduml.uim.UimPanel;
import org.nakeduml.uim.UimTab;
import org.nakeduml.uim.UimTabPanel;
import org.nakeduml.uim.action.ActionFactory;
import org.nakeduml.uim.action.ActionKind;
import org.nakeduml.uim.action.BuiltInAction;
import org.nakeduml.uim.binding.BindingFactory;
import org.nakeduml.uim.binding.FieldBinding;
import org.nakeduml.uim.binding.PropertyRef;
import org.nakeduml.uim.binding.TableBinding;
import org.nakeduml.uim.control.ControlKind;
import org.nakeduml.uim.form.FormPanel;
import org.nakeduml.uim.layout.LayoutFactory;
import org.nakeduml.uim.layout.UimFullLayout;
import org.nakeduml.uim.layout.UimGridLayout;
import org.nakeduml.uim.layout.UimLayout;
import org.nakeduml.uim.util.ControlUtil;
import org.nakeduml.uim.util.UimUtil;
import org.nakeduml.uim.util.UmlUimLinks;

public class FormCreator{
	private UimGridLayout mainTabLayout;
	private UimTabPanel tabPanel;
	private FormPanel formPanel;
	private UmlElementCache umlCache;
	public FormCreator(UmlElementCache links, FormPanel cf){
		this.formPanel = cf;
		this.umlCache=links;
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
	private void addDetailsTabForField(TypedElement e){
		Classifier c = (Classifier) e.getType();
		// // Create tab and add to panel
		UimTab tab = UimFactory.eINSTANCE.createUimTab();
		this.tabPanel.getChildren().add(tab);
		tab.setName(NameConverter.separateWords(e.getName()));
		UimGridLayout tabLayout = LayoutFactory.eINSTANCE.createUimGridLayout();
		tabLayout.setNumberOfColumns(1);
		tab.setLayout(tabLayout);
		for(Property property:UmlUimLinks.getInstance(this.tabPanel).getOwnedAttributes(c)){
			if(requiresTableTab(property)){
				// No further details
			}else if(property.getOtherEnd() == null || !property.getOtherEnd().isComposite()){
				addUserField(tabLayout, 390, e, property);
			}
		}
	}
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
		binding.setUmlElementUid(umlCache.getId(e));
		tabLayout.getChildren().add(table);
		table.setLayout(LayoutFactory.eINSTANCE.createUimColumnLayout());
		Collection<Property> attrs = UmlUimLinks.getInstance(this.tabPanel).getOwnedAttributes((Classifier) e.getType());
		for(Property property:attrs){
			if(property.getOtherEnd() == null || !property.getOtherEnd().isComposite()){
				addUserField(table.getLayout(), 0, property);
			}
		}
		// create fields
	}
	private boolean requiresTableTab(TypedElement property){
		return property.getType() instanceof org.eclipse.uml2.uml.Class && UimUtil.isMany(property) && !ControlUtil.requiresManySelection(formPanel, property);
	}
	private boolean requiresDetailsTab(TypedElement property){
		return property.getType() instanceof org.eclipse.uml2.uml.Class && !UimUtil.isMany(property)
				&& (!ControlUtil.requiresUserInput(formPanel, property) || UimUtil.isComposite(property));
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
		binding.setUmlElementUid(umlCache.getId(properties[0]));
		if(properties.length > 1){
			PropertyRef prev = BindingFactory.eINSTANCE.createPropertyRef();
			prev.setUmlElementUid(umlCache.getId(properties[1]));
			binding.setNext(prev);
			for(int i = 2;i < properties.length;i++){
				PropertyRef next = BindingFactory.eINSTANCE.createPropertyRef();
				next.setUmlElementUid(umlCache.getId(properties[i]));
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