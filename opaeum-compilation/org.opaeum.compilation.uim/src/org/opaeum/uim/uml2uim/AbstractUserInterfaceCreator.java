package org.opaeum.uim.uml2uim;

import java.util.Collection;

import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.DataType;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.ParameterEffectKind;
import org.eclipse.uml2.uml.PrimitiveType;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.TypedElement;
import org.opaeum.eclipse.EmfElementFinder;
import org.opaeum.eclipse.EmfPropertyUtil;
import org.opaeum.emf.workspace.EmfWorkspace;
import org.opaeum.name.NameConverter;
import org.opaeum.uim.Page;
import org.opaeum.uim.PageContainer;
import org.opaeum.uim.UimContainer;
import org.opaeum.uim.UimDataTable;
import org.opaeum.uim.UimFactory;
import org.opaeum.uim.UimField;
import org.opaeum.uim.UserInterfaceEntryPoint;
import org.opaeum.uim.action.ActionFactory;
import org.opaeum.uim.action.ActionKind;
import org.opaeum.uim.action.BuiltInAction;
import org.opaeum.uim.action.OperationAction;
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
	private EmfWorkspace workspace;
	public AbstractUserInterfaceCreator(EmfWorkspace w){
		this.workspace = w;
	}
	public void prepareFormPanel(PageContainer contaier, String title,Collection<? extends TypedElement> typedElements){
		Page page = addPage(contaier);
		page.setName(title);
		GridPanel layout = PanelFactory.eINSTANCE.createGridPanel();
		layout.setNumberOfColumns(1);
		page.setPanel(layout);
		addUserFields(contaier, layout, typedElements);
	}
	protected abstract Page addPage(PageContainer contaier);
	protected abstract UserInterfaceEntryPoint getUserInterfaceEntryPoint();
	private void addUserFields(PageContainer pc, UimContainer layout, Collection<? extends TypedElement> typedElements){
		for(TypedElement property:typedElements){
			if(property instanceof Property && ((Property) property).getOtherEnd() != null && ((Property) property).getOtherEnd().isComposite()){
			}else if(requiresTableTab(layout, property)){
				addTableTabForField(pc,property);
			}else if(requiresDetailsTab(layout, property)){
				addDetailsTabForField(pc,property);
			}else{
				addUserField(layout, 390, property);
			}
		}
	}
	@SuppressWarnings({"unchecked","rawtypes"})
	void addDetailsTabForField(PageContainer pc,TypedElement e){
		Classifier c = (Classifier) e.getType();
		// // Create tab and add to panel
		Page tab = addPage(pc);
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
	UimDataTable addTableTabForField(PageContainer pc,TypedElement e){
		// Create tab and add to panel
		Page page = addPage(pc);
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
		Classifier type = (Classifier) e.getType();
		Collection<Property> attrs = (Collection<Property>) (Collection) EmfElementFinder.getPropertiesInScope(type);
		for(Property property:attrs){
			boolean isCreateParameter = e instanceof Parameter && ((Parameter) e).getEffect()==ParameterEffectKind.CREATE_LITERAL;
			boolean isNonComposite = property.getOtherEnd() == null || !property.getOtherEnd().isComposite();
			if((isNonComposite||isCreateParameter) && !EmfPropertyUtil.isMany(property)){
				addUserField(table, 0, property);
			}
		}
		BuiltInAction action = ActionFactory.eINSTANCE.createBuiltInAction();
		table.getActionsOnMultipleSelection().add(action);
		action.setKind(ActionKind.DELETE);
		action.setName("Delete");
		BuiltInAction action2 = ActionFactory.eINSTANCE.createBuiltInAction();
		table.getActionsOnMultipleSelection().add(action2);
		action2.setKind(ActionKind.ADD);
		action2.setName("Add");
		addActionBarTo(table, type);
		return table;
	}
	protected void addActionBarTo(UimDataTable table,Classifier type){
		for(Operation operation:type.getAllOperations()){
			if(operation.getReturnResult() == null && !operation.isQuery()){
				OperationAction action = ActionFactory.eINSTANCE.createOperationAction();
				table.getActionsOnMultipleSelection().add(action);
				action.setUmlElementUid(workspace.getId(operation));
				action.setName(NameConverter.separateWords(NameConverter.capitalize(operation.getName())));
				action.setPopup(ActionFactory.eINSTANCE.createOperationActionPopup());
				prepareFormPanel(action.getPopup(),action.getName(), operation.getOwnedParameters());
			}
		}
	}
	boolean requiresTableTab(UimContainer layout,TypedElement property){
		if(EmfPropertyUtil.isMany(property)){
			boolean isStructuredDataType = property.getType() instanceof DataType && !(property.getType() instanceof PrimitiveType);
			if(property.getType() instanceof org.eclipse.uml2.uml.Class || isStructuredDataType){
				if(property instanceof Property && ((Property) property).isComposite()){
					return true;
				}else if(property instanceof Parameter && ((Parameter) property).getEffect() == ParameterEffectKind.CREATE_LITERAL){
					return true;
				}else{
					return !ControlUtil.requiresManySelection(UmlUimLinks.getNearestForm(layout), property);
				}
			}
		}
		return false;
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