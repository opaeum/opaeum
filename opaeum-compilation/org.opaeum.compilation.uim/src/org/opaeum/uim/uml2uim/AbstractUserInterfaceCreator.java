package org.opaeum.uim.uml2uim;

import java.util.Collection;

import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.TypedElement;
import org.opaeum.eclipse.EmfElementFinder;
import org.opaeum.eclipse.EmfPropertyUtil;
import org.opaeum.emf.workspace.EmfWorkspace;
import org.opaeum.name.NameConverter;
import org.opaeum.uim.Page;
import org.opaeum.uim.UimContainer;
import org.opaeum.uim.UimDataTable;
import org.opaeum.uim.UimFactory;
import org.opaeum.uim.UimField;
import org.opaeum.uim.UserInterfaceEntryPoint;
import org.opaeum.uim.binding.BindingFactory;
import org.opaeum.uim.binding.FieldBinding;
import org.opaeum.uim.binding.PropertyRef;
import org.opaeum.uim.binding.TableBinding;
import org.opaeum.uim.control.ControlKind;
import org.opaeum.uim.panel.GridPanel;
import org.opaeum.uim.panel.PanelFactory;
import org.opaeum.uim.util.ControlUtil;
import org.opaeum.uim.util.UmlUimLinks;

public abstract class AbstractUserInterfaceCreator{
	GridPanel mainTabLayout;
	private EmfWorkspace workspace;
	public AbstractUserInterfaceCreator(EmfWorkspace w){
		this.workspace = w;
	}
	public void prepareFormPanel(String title,Collection<? extends TypedElement> typedElements){
		Page page = addPage();
		page.setName("Edit Details");
		mainTabLayout = PanelFactory.eINSTANCE.createGridPanel();
		mainTabLayout.setNumberOfColumns(1);
		page.setPanel(mainTabLayout);
		addUserFields(mainTabLayout,typedElements);
	}
	protected abstract Page addPage();
	protected abstract UserInterfaceEntryPoint getUserInterfaceEntryPoint();
	private void addUserFields(UimContainer layout,Collection<? extends TypedElement> typedElements){
		for(TypedElement property:typedElements){
			if(property instanceof Property && ((Property) property).getOtherEnd() != null && ((Property) property).getOtherEnd().isComposite()){
			}else if(requiresTableTab(layout, property)){
				addTableTabForField(property);
			}else if(requiresDetailsTab(layout, property)){
				addDetailsTabForField(property);
			}else{
				addUserField(mainTabLayout, 390, property);
			}
		}
	}
	@SuppressWarnings({"unchecked","rawtypes"})
	void addDetailsTabForField(TypedElement e){
		Classifier c = (Classifier) e.getType();
		// // Create tab and add to panel
		Page tab = addPage();
		tab.setName(NameConverter.separateWords(e.getName()));
		GridPanel rootGridPanel = PanelFactory.eINSTANCE.createGridPanel();
		rootGridPanel.setNumberOfColumns(1);
		tab.setPanel(rootGridPanel);
		for(Property property:(Collection<Property>) (Collection) EmfElementFinder.getPropertiesInScope(c)){
			if(requiresTableTab(rootGridPanel, property)){
				// No further details
			}else if(property.getOtherEnd() == null || !property.getOtherEnd().isComposite()){
				addUserField(rootGridPanel, 390, e, property);
			}
		}
	}
	@SuppressWarnings({"rawtypes","unchecked"})
	void addTableTabForField(TypedElement e){
		// Create tab and add to panel
		Page page = addPage();
		page.setName(NameConverter.separateWords(e.getName()));
		// Create tab XYLayout
		GridPanel tabLayout = PanelFactory.eINSTANCE.createGridPanel();
		page.setPanel(tabLayout);
		// cfeate Data TAble
		UimDataTable table = UimFactory.eINSTANCE.createUimDataTable();
		tabLayout.getChildren().add(table);
		table.setFillVertically(true);
		table.setFillHorizontally(true);
		TableBinding binding = BindingFactory.eINSTANCE.createTableBinding();
		table.setBinding(binding);
		binding.setUmlElementUid(workspace.getId(e));
		Collection<Property> attrs = (Collection<Property>) (Collection) EmfElementFinder.getPropertiesInScope((Classifier) e.getType());
		for(Property property:attrs){
			if(property.getOtherEnd() == null || !property.getOtherEnd().isComposite()){
				addUserField(table, 0, property);
			}
		}
		// create fields
	}
	boolean requiresTableTab(UimContainer layout,TypedElement property){
		return property.getType() instanceof org.eclipse.uml2.uml.Class && EmfPropertyUtil.isMany(property)
				&& !ControlUtil.requiresManySelection(UmlUimLinks.getNearestForm(layout), property);
	}
	boolean requiresDetailsTab(UimContainer layout,TypedElement property){
		return property.getType() instanceof org.eclipse.uml2.uml.Class && !EmfPropertyUtil.isMany(property)
				&& (!ControlUtil.requiresUserInput(UmlUimLinks.getNearestForm(layout), property) || EmfPropertyUtil.isComposite(property));
	}
	int addUserField(UimContainer layout,int labelWidth,TypedElement...properties){
		TypedElement property = properties[properties.length - 1];
		UimField uf = UimFactory.eINSTANCE.createUimField();
		uf.setMinimumLabelWidth(labelWidth < 100 ? 0 : labelWidth / 2);
		uf.setName(NameConverter.separateWords(property.getName()));
		ControlKind controlKind = ControlUtil.getPreferredControlKind(UmlUimLinks.getNearestForm(layout), property);
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
}