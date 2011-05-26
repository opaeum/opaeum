package org.nakeduml.uml2uim;

import java.util.Collection;

import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.TypedElement;
import org.nakeduml.name.NameConverter;
import org.nakeduml.uim.ActionKind;
import org.nakeduml.uim.BuiltInAction;
import org.nakeduml.uim.ControlKind;
import org.nakeduml.uim.FieldBinding;
import org.nakeduml.uim.FormPanel;
import org.nakeduml.uim.PropertyRef;
import org.nakeduml.uim.TableBinding;
import org.nakeduml.uim.UimDataColumn;
import org.nakeduml.uim.UimDataTable;
import org.nakeduml.uim.UimFactory;
import org.nakeduml.uim.UimField;
import org.nakeduml.uim.UimFullLayout;
import org.nakeduml.uim.UimGridLayout;
import org.nakeduml.uim.UimLayout;
import org.nakeduml.uim.UimPanel;
import org.nakeduml.uim.UimTab;
import org.nakeduml.uim.UimTabPanel;
import org.nakeduml.uim.util.ControlUtil;
import org.nakeduml.uim.util.SafeUmlUimLinks;
import org.nakeduml.uim.util.UimUtil;
import org.nakeduml.uim.util.UmlUimLinks;

public class FormCreator{
	private UimGridLayout mainTabLayout;
	private UimTabPanel tabPanel;
	private FormPanel formPanel;
	public FormCreator(FormPanel cf){
		this.formPanel = cf;
	}
	public void prepareFormPanel(String title,Collection<? extends TypedElement> typedElements){
		UimFullLayout formLayout = UimFactory.eINSTANCE.createUimFullLayout();
		formPanel.setLayout(formLayout);
		tabPanel = UimFactory.eINSTANCE.createUimTabPanel();
		formLayout.getChildren().add(tabPanel);
		UimTab tab = UimFactory.eINSTANCE.createUimTab();
		tab.setName("Edit Details");
		tabPanel.getChildren().add(tab);
		mainTabLayout = UimFactory.eINSTANCE.createUimGridLayout();
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
		UimGridLayout tabLayout = UimFactory.eINSTANCE.createUimGridLayout();
		tabLayout.setNumberOfColumns(1);
		tab.setLayout(tabLayout);
		for(Property property:SafeUmlUimLinks.getInstance(e).getOwnedAttributes(c)){
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
		UimFullLayout tabLayout = UimFactory.eINSTANCE.createUimFullLayout();
		tab.setLayout(tabLayout);
		// cfeate Data TAble
		UimDataTable table = UimFactory.eINSTANCE.createUimDataTable();
		TableBinding binding = UimFactory.eINSTANCE.createTableBinding();
		table.setBinding(binding);
		binding.setUmlElementUid(UmlUimLinks.getId(e));
		tabLayout.getChildren().add(table);
		Collection<Property> attrs = SafeUmlUimLinks.getInstance(e).getOwnedAttributes((Classifier) e.getType());
		for(Property property:attrs){
			if(property.getOtherEnd() == null || !property.getOtherEnd().isComposite()){
				UimDataColumn column = UimFactory.eINSTANCE.createUimDataColumn();
				column.setName(NameConverter.separateWords(property.getName()));
				table.getChildren().add(column);
				// CReate column xy layout
				UimGridLayout columnLayout = UimFactory.eINSTANCE.createUimGridLayout();
				columnLayout.setNumberOfColumns(1);
				column.setLayout(columnLayout);
				addUserField(columnLayout, 0, property);
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
		FieldBinding binding = UimFactory.eINSTANCE.createFieldBinding();
		uf.setBinding(binding);
		binding.setUmlElementUid(UmlUimLinks.getId(properties[0]));
		if(properties.length > 1){
			PropertyRef prev = UimFactory.eINSTANCE.createPropertyRef();
			prev.setUmlElementUid(UmlUimLinks.getId(properties[1]));
			binding.setNext(prev);
			for(int i = 2;i < properties.length;i++){
				PropertyRef next = UimFactory.eINSTANCE.createPropertyRef();
				next.setUmlElementUid(UmlUimLinks.getId(properties[i]));
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
		UimGridLayout gl = UimFactory.eINSTANCE.createUimGridLayout();
		panel.setLayout(gl);
		gl.setNumberOfColumns(updateCurrentEntity.length);
		for(ActionKind actionKind:updateCurrentEntity){
			BuiltInAction bia = UimFactory.eINSTANCE.createBuiltInAction();
			bia.setKind(actionKind);
			bia.setName(NameConverter.separateWords(NameConverter.capitalize(actionKind.getName())));
			gl.getChildren().add(bia);
		}
	}
}