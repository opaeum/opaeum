package org.opaeum.uim.uml2uim;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.DataType;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Interface;
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
import org.opaeum.uim.Orientation;
import org.opaeum.uim.Page;
import org.opaeum.uim.PageContainer;
import org.opaeum.uim.UimContainer;
import org.opaeum.uim.UimDataTable;
import org.opaeum.uim.UimFactory;
import org.opaeum.uim.UimField;
import org.opaeum.uim.UmlReference;
import org.opaeum.uim.UserInteractionElement;
import org.opaeum.uim.UserInterfaceEntryPoint;
import org.opaeum.uim.action.ActionFactory;
import org.opaeum.uim.action.ActionKind;
import org.opaeum.uim.action.BuiltInActionButton;
import org.opaeum.uim.action.BuiltInLink;
import org.opaeum.uim.action.BuiltInLinkKind;
import org.opaeum.uim.action.OperationButton;
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
	protected EmfWorkspace workspace;
	public AbstractUserInterfaceCreator(EmfWorkspace w){
		this.workspace = w;
	}
	protected abstract Page addPage(PageContainer container);
	protected abstract UserInterfaceEntryPoint getUserInterfaceEntryPoint();
	protected boolean isUnderUserControl(Element e,UimContainer container){
		String id = EmfWorkspace.getId(e);
		TreeIterator<EObject> eAllContents = container.eAllContents();
		while(eAllContents.hasNext()){
			EObject eObject = (EObject) eAllContents.next();
			if(eObject instanceof UmlReference && id.equals(((UmlReference) eObject).getUmlElementUid())){
				if(eObject instanceof UserInteractionElement && ((UserInteractionElement) eObject).isUnderUserControl()){
					return true;
				}
			}
		}
		return false;
	}
	public void addFormPanel(PageContainer container,String title,Collection<? extends TypedElement> typedElements){
		Map<Interface,Collection<TypedElement>> interfaces = extractPropertiesByInterface(typedElements);
		Page page = addPage(container);
		page.setName(title);
		GridPanel layout = PanelFactory.eINSTANCE.createGridPanel();
		layout.setNumberOfColumns(1);
		page.setPanel(layout);
		addUserFields(container, layout, typedElements);
		addInterfaceProperties(container, interfaces);
	}
	private void addInterfaceProperties(PageContainer container,Map<Interface,Collection<TypedElement>> interfaces){
		for(Entry<Interface,Collection<TypedElement>> entry:interfaces.entrySet()){
			Page page2 = addPage(container);
			page2.setName(NameConverter.separateWords(entry.getKey().getName()));
			GridPanel rootGridPanel = PanelFactory.eINSTANCE.createGridPanel();
			rootGridPanel.setNumberOfColumns(1);
			page2.setPanel(rootGridPanel);
			addUserFields(container, rootGridPanel, entry.getValue());
		}
	}
	private Map<Interface,Collection<TypedElement>> extractPropertiesByInterface(Collection<? extends TypedElement> typedElements){
		Map<Interface,Collection<TypedElement>> interfaces = buildInterfaceMap(typedElements);
		for(TypedElement typedElement:new ArrayList<TypedElement>(typedElements)){
			EObject container = EmfElementFinder.getContainer(typedElement);
			if(container instanceof Interface){
				typedElements.remove(typedElement);
				Collection<TypedElement> collection = getMatchingCollection(interfaces, (Interface) container);
				collection.add(typedElement);
			}
		}
		return interfaces;
	}
	private Map<Interface,Collection<TypedElement>> buildInterfaceMap(Collection<? extends TypedElement> typedElements){
		// NB!! this looks like a roundabout way, but remember we don't want to display overridden and redefined properties twice, so only look
		// for the properties
		// present contained by interfaces AFTER redefinition and overriding has been applied
		Map<Interface,Collection<TypedElement>> interfaces = new HashMap<Interface,Collection<TypedElement>>();
		for(TypedElement typedElement:new ArrayList<TypedElement>(typedElements)){
			EObject container = EmfElementFinder.getContainer(typedElement);
			if(container instanceof Interface){
				interfaces.put((Interface) container, new ArrayList<TypedElement>());
			}
		}
		Set<Interface> keySet = new HashSet<Interface>(interfaces.keySet());
		for(Interface interface1:keySet){
			for(Interface interface2:keySet){
				if(interface2!=interface1 && interface2.conformsTo(interface1)){
					interfaces.remove(interface1);
				}
			}
		}
		return interfaces;
	}
	private Collection<TypedElement> getMatchingCollection(Map<Interface,Collection<TypedElement>> interfaces,Interface container){
		Set<Entry<Interface,Collection<TypedElement>>> entrySet = interfaces.entrySet();
		for(Entry<Interface,Collection<TypedElement>> entry:entrySet){
			if(entry.getKey().conformsTo(container)){
				return entry.getValue();
			}
		}
		throw new IllegalStateException("All collections should have been created");
	}
	private void addUserFields(PageContainer pc,UimContainer layout,Collection<? extends TypedElement> typedElements){
		for(TypedElement property:typedElements){
			if(property instanceof Property && ((Property) property).getOtherEnd() != null && ((Property) property).getOtherEnd().isComposite()){
			}else if(requiresTableTab(layout, property)){
				addTableTabForField(pc, property);
			}else if(requiresDetailsTab(layout, property)){
				addDetailsTabForField(pc, property);
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
		binding.setUmlElementUid(EmfWorkspace.getId(e));
		Classifier type = (Classifier) e.getType();
		BuiltInLink edit = ActionFactory.eINSTANCE.createBuiltInLink();
		table.getChildren().add(edit);
		edit.setKind(BuiltInLinkKind.EDIT);
		edit.setName("Edit");
		edit.setPreferredWidth(20);
		Collection<Property> attrs = (Collection<Property>) (Collection) EmfElementFinder.getPropertiesInScope(type);
		for(Property property:attrs){
			boolean isCreateParameter = e instanceof Parameter && ((Parameter) e).getEffect() == ParameterEffectKind.CREATE_LITERAL;
			boolean isNonComposite = property.getOtherEnd() == null || !property.getOtherEnd().isComposite();
			if((isNonComposite || isCreateParameter) && !EmfPropertyUtil.isMany(property)){
				addUserField(table, 0, property);
			}
		}
		BuiltInActionButton deleteInRow = ActionFactory.eINSTANCE.createBuiltInActionButton();
		table.getChildren().add(deleteInRow);
		deleteInRow.setKind(ActionKind.DELETE);
		deleteInRow.setName("Delete");
		for(Operation operation:type.getOperations()){
			if(!operation.isQuery() && operation.getReturnResult()==null){
				OperationButton action = ActionFactory.eINSTANCE.createOperationButton();
				table.getChildren().add(action);
				action.setUmlElementUid(EmfWorkspace.getId(operation));
				action.setName(NameConverter.separateWords(NameConverter.capitalize(operation.getName())));
				action.setPopup(ActionFactory.eINSTANCE.createOperationPopup());
				addFormPanel(action.getPopup(), action.getName(), operation.getOwnedParameters());
			}
		}
		BuiltInActionButton deleteOnActionBar = ActionFactory.eINSTANCE.createBuiltInActionButton();
		table.getActionsOnMultipleSelection().add(deleteOnActionBar);
		deleteOnActionBar.setKind(ActionKind.DELETE);
		deleteOnActionBar.setName("Delete");
		BuiltInActionButton action2 = ActionFactory.eINSTANCE.createBuiltInActionButton();
		table.getActionsOnMultipleSelection().add(action2);
		action2.setKind(ActionKind.ADD);
		action2.setName("Add");
		addActionBarTo(table, type);
		return table;
	}
	protected void addActionBarTo(UimDataTable table,Classifier type){
		for(Operation operation:type.getAllOperations()){
			if(operation.getReturnResult() == null && !operation.isQuery()){
				OperationButton action = ActionFactory.eINSTANCE.createOperationButton();
				table.getActionsOnMultipleSelection().add(action);
				action.setUmlElementUid(EmfWorkspace.getId(operation));
				action.setName(NameConverter.separateWords(NameConverter.capitalize(operation.getName())));
				action.setPopup(ActionFactory.eINSTANCE.createOperationPopup());
				addFormPanel(action.getPopup(), action.getName(), operation.getOwnedParameters());
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
	void addUserField(UimContainer container,int labelWidth,TypedElement...properties){
		TypedElement property = properties[properties.length - 1];
		UimField uf = UimFactory.eINSTANCE.createUimField();
		uf.setMinimumLabelWidth(labelWidth < 100 ? 0 : labelWidth / 2);
		uf.setName(NameConverter.separateWords(property.getName()));
		ControlKind controlKind = ControlUtil.getPreferredControlKind(UmlUimLinks.getNearestForm(container), property,
				container instanceof UimDataTable);
		uf.setControlKind(controlKind);
		uf.setControl(ControlUtil.instantiate(uf.getControlKind()));
		FieldBinding binding = BindingFactory.eINSTANCE.createFieldBinding();
		uf.setBinding(binding);
		uf.setFillHorizontally(true);
		binding.setUmlElementUid(EmfWorkspace.getId(properties[0]));
		if(properties.length > 1){
			PropertyRef prev = BindingFactory.eINSTANCE.createPropertyRef();
			prev.setUmlElementUid(EmfWorkspace.getId(properties[1]));
			binding.setNext(prev);
			for(int i = 2;i < properties.length;i++){
				PropertyRef next = BindingFactory.eINSTANCE.createPropertyRef();
				next.setUmlElementUid(EmfWorkspace.getId(properties[i]));
				prev.setNext(next);
				prev = next;
			}
		}
		container.getChildren().add(uf);
		if(ControlUtil.isMultiRow(controlKind)){
			uf.setPreferredHeight(150);
			uf.setOrientation(Orientation.VERTICAL);
		}else{
			uf.setPreferredHeight(25);
			uf.setOrientation(Orientation.HORIZONTAL);
		}
		if(container.getChildren().size() > 6){
			((GridPanel) container).setNumberOfColumns(2);
		}
	}
}