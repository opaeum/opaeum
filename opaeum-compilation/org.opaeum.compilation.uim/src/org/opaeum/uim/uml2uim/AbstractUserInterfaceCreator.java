package org.opaeum.uim.uml2uim;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.Namespace;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.ParameterEffectKind;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.TypedElement;
import org.opaeum.eclipse.EmfBehaviorUtil;
import org.opaeum.eclipse.EmfElementFinder;
import org.opaeum.eclipse.EmfOperationUtil;
import org.opaeum.eclipse.EmfPropertyUtil;
import org.opaeum.emf.workspace.EmfWorkspace;
import org.opaeum.name.NameConverter;
import org.opaeum.uim.Page;
import org.opaeum.uim.UserInteractionElement;
import org.opaeum.uim.UserInterfaceRoot;
import org.opaeum.uim.action.ActionFactory;
import org.opaeum.uim.action.ActionKind;
import org.opaeum.uim.action.BuiltInActionButton;
import org.opaeum.uim.action.BuiltInLink;
import org.opaeum.uim.action.BuiltInLinkKind;
import org.opaeum.uim.action.InvocationButton;
import org.opaeum.uim.binding.BindingFactory;
import org.opaeum.uim.binding.FieldBinding;
import org.opaeum.uim.binding.PropertyRef;
import org.opaeum.uim.binding.TableBinding;
import org.opaeum.uim.component.ComponentFactory;
import org.opaeum.uim.component.UimContainer;
import org.opaeum.uim.component.UimDataTable;
import org.opaeum.uim.component.UimField;
import org.opaeum.uim.control.ControlKind;
import org.opaeum.uim.model.BehaviorUserInteractionModel;
import org.opaeum.uim.model.ModelFactory;
import org.opaeum.uim.model.OperationInvocationWizard;
import org.opaeum.uim.model.ResponsibilityUserInteractionModel;
import org.opaeum.uim.panel.AbstractPanel;
import org.opaeum.uim.panel.GridPanel;
import org.opaeum.uim.panel.Orientation;
import org.opaeum.uim.panel.PanelFactory;
import org.opaeum.uim.util.ControlUtil;
import org.opaeum.uim.util.UmlUimLinks;
import org.opaeum.uim.wizard.InvocationWizard;
import org.opaeum.uim.wizard.WizardFactory;

public abstract class AbstractUserInterfaceCreator{
	protected UserInterfaceResourceFactory resourceFactory;
	public AbstractUserInterfaceCreator(UserInterfaceResourceFactory resourceFactory){
		this.resourceFactory = resourceFactory;
	}
	protected abstract Page addPage(UserInterfaceRoot container);
	protected abstract UserInterfaceRoot getUserInterfaceRoot();
	public void populateUserInterface(Element owner,UserInterfaceRoot uiRoot,String title,Collection<? extends TypedElement> typedElements){
		Page page = UserInterfaceUtil.findRepresentingPage(owner, uiRoot);
		if(page == null){
			page = addPage(uiRoot);
			page.setName(title);
		}
		if(!UserInterfaceUtil.isUnderUserControl(page)){
			GridPanel layout = PanelFactory.eINSTANCE.createGridPanel();
			layout.setNumberOfColumns(1);
			page.setPanel(layout);
			page.setUmlElementUid(EmfWorkspace.getId(owner));
		}
		Map<Interface,Collection<TypedElement>> interfaces = extractPropertiesByInterface(typedElements);
		addUserInterfaceElementsFor(uiRoot, page.getPanel(), typedElements);
		addInterfaceProperties(uiRoot, interfaces);
	}
	private void addInterfaceProperties(UserInterfaceRoot container,Map<Interface,Collection<TypedElement>> interfaces){
		for(Entry<Interface,Collection<TypedElement>> entry:interfaces.entrySet()){
			Page page = UserInterfaceUtil.findRepresentingPage(entry.getKey(), container);
			if(page == null){
				page = addPage(container);
				page.setName(NameConverter.separateWords(entry.getKey().getName()));
			}
			if(!page.isUnderUserControl()){
				// recreate page
				GridPanel rootGridPanel = PanelFactory.eINSTANCE.createGridPanel();
				rootGridPanel.setNumberOfColumns(1);
				page.setPanel(rootGridPanel);
			}
			addUserInterfaceElementsFor(container, page.getPanel(), entry.getValue());
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
				if(interface2 != interface1 && interface2.conformsTo(interface1)){
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
	private void addUserInterfaceElementsFor(UserInterfaceRoot uiRoot,UimContainer container,Collection<? extends TypedElement> typedElements){
		for(TypedElement property:typedElements){
			if(!UserInterfaceUtil.isIgnored(property, uiRoot)){
				if(EmfPropertyUtil.isEndToComposite(property)){
					// Cannot change the owner
				}else if(UserInterfaceUtil.requiresTablePage(uiRoot, property)){
					addTablePageForField(uiRoot, property);
				}else if(UserInterfaceUtil.requiresDetailsPage(uiRoot, property)){
					addDetailsPageForField(uiRoot, property);
				}else{
					maybeAddField(container, 390, property);
				}
			}
		}
	}
	@SuppressWarnings({"unchecked","rawtypes"})
	private void addDetailsPageForField(UserInterfaceRoot uiRoot,TypedElement te){
		Page page = UserInterfaceUtil.findRepresentingPage(te, uiRoot);
		if(page == null){
			page = addPage(uiRoot);
			page.setName(NameConverter.separateWords(te.getName()));
		}
		if(!UserInterfaceUtil.isUnderUserControl(page)){
			GridPanel layout = PanelFactory.eINSTANCE.createGridPanel();
			layout.setNumberOfColumns(1);
			page.setPanel(layout);
			page.setUmlElementUid(EmfWorkspace.getId(te));
			GridPanel rootGridPanel = PanelFactory.eINSTANCE.createGridPanel();
			rootGridPanel.setNumberOfColumns(1);
			page.setPanel(rootGridPanel);
			Classifier c = (Classifier) te.getType();
			// // Create tab and add to panel
			for(Property property:(Collection<Property>) (Collection) EmfElementFinder.getPropertiesInScope(c)){
				if(UserInterfaceUtil.requiresTablePage(uiRoot, property)){
					// No further details
				}else if(property.getOtherEnd() == null || !property.getOtherEnd().isComposite()){
					maybeAddField(page.getPanel(), 390, te, property);
				}
			}
		}
	}
	void addTablePageForField(UserInterfaceRoot pc,TypedElement e){
		Page page = UserInterfaceUtil.findRepresentingPage(e, pc);
		UimDataTable table = null;
		if(page == null){
			// Nothing found -create from scratch
			page = addPage(pc);
			page.setUmlElementUid(EmfWorkspace.getId(e));
			page.setName(NameConverter.separateWords(e.getName()));
		}
		if(UserInterfaceUtil.isUnderUserControl(page)){
			if(page.getPanel() != null){
				UserInteractionElement uie = UserInterfaceUtil.findRepresentingElement(e, page.getPanel());
				if(!(uie instanceof UimDataTable)){
					// Not a table, but a detailsTab. Replace with new Table
					// TODO convert existing detailsTab to a DataTable
					page.getPanel().getChildren().clear();
					table = addTableTo(e, page.getPanel());
				}
			}else{
				// No panel - assume user knows what he is doing
			}
		}else{
			GridPanel gridPanel = PanelFactory.eINSTANCE.createGridPanel();
			page.setPanel(gridPanel);
			table = addTableTo(e, gridPanel);
		}
		if(table != null){
			populateDataTable(e, table);
		}
	}
	private void populateDataTable(TypedElement e,UimDataTable table){
		Classifier type = (Classifier) e.getType();
		BuiltInLink edit = ActionFactory.eINSTANCE.createBuiltInLink();
		table.getChildren().add(edit);
		edit.setKind(BuiltInLinkKind.EDIT);
		edit.setName("Edit");
		edit.setPreferredWidth(30);
		Collection<Property> attrs = EmfElementFinder.getPropertiesInScope(type);
		for(Property property:attrs){
			boolean isNonComposite = property.getOtherEnd() == null || !property.getOtherEnd().isComposite();
			if(isNonComposite && !EmfPropertyUtil.isMany(property)){
				maybeAddField(table, 0, property);
			}
		}
		addActionColumnsTo(table, type);
		addActionBarTo(table, type);
	}
	private void addActionColumnsTo(UimDataTable table,Classifier type){
		BuiltInActionButton deleteInRow = ActionFactory.eINSTANCE.createBuiltInActionButton();
		table.getChildren().add(deleteInRow);
		deleteInRow.setKind(ActionKind.DELETE);
		deleteInRow.setName("Delete");
		addInvocationColumns(table, type);
		BuiltInActionButton deleteOnActionBar = ActionFactory.eINSTANCE.createBuiltInActionButton();
		table.getActionsOnMultipleSelection().add(deleteOnActionBar);
		deleteOnActionBar.setKind(ActionKind.DELETE);
		deleteOnActionBar.setName("Delete");
		BuiltInActionButton action2 = ActionFactory.eINSTANCE.createBuiltInActionButton();
		table.getActionsOnMultipleSelection().add(action2);
		action2.setKind(ActionKind.ADD);
		action2.setName("Add");
	}
	private void addInvocationColumns(UimDataTable table,Classifier type){
		for(Operation operation:EmfOperationUtil.getEffectiveOperations(type)){
			if(!operation.isQuery()){
				InvocationButton action = ActionFactory.eINSTANCE.createInvocationButton();
				table.getChildren().add(action);
				action.setUmlElementUid(EmfWorkspace.getId(operation));
				action.setName(NameConverter.separateWords(NameConverter.capitalize(operation.getName())));
				action.setPopup(getInvocationWizard(operation));
			}
		}
	}
	private UimDataTable addTableTo(TypedElement e,AbstractPanel gridPanel){
		UimDataTable table;
		table = ComponentFactory.eINSTANCE.createUimDataTable();
		gridPanel.getChildren().add(table);
		table.setFillVertically(true);
		table.setFillHorizontally(true);
		TableBinding binding = BindingFactory.eINSTANCE.createTableBinding();
		table.setBinding(binding);
		binding.setUmlElementUid(EmfWorkspace.getId(e));
		return table;
	}
	protected void addActionBarTo(UimDataTable table,Classifier type){
		for(Operation operation:EmfOperationUtil.getEffectiveOperations(type)){
			if(!operation.isQuery()){
				InvocationButton action = ActionFactory.eINSTANCE.createInvocationButton();
				table.getActionsOnMultipleSelection().add(action);
				action.setUmlElementUid(EmfWorkspace.getId(operation));
				action.setName(NameConverter.separateWords(NameConverter.capitalize(operation.getName())));
				action.setPopup(getInvocationWizard(operation));
			}
		}
	}
	private void maybeAddField(UimContainer container,int labelWidth,TypedElement...properties){
		if(container != null && UserInterfaceUtil.isUnderUserControl(container)){
			// Only add fields to containers not under user control
			TypedElement property = properties[properties.length - 1];
			UserInteractionElement representingElement = UserInterfaceUtil.findRepresentingElement(property, container);
			UimField uf = null;
			if(representingElement == null){
				uf = ComponentFactory.eINSTANCE.createUimField();
				container.getChildren().add(uf);
			}else if(!representingElement.isUnderUserControl() && representingElement instanceof UimField){
				uf = (UimField) representingElement;
			}
			if(uf != null){
				uf.setMinimumLabelWidth(labelWidth < 100 ? 0 : labelWidth / 2);
				uf.setName(NameConverter.separateWords(property.getName()));
				ControlKind controlKind = ControlUtil.getPreferredControlKind(UmlUimLinks.getNearestForm(container), property, container instanceof UimDataTable);
				uf.setControlKind(controlKind);
				uf.setControl(ControlUtil.instantiate(uf.getControlKind()));
				FieldBinding binding = BindingFactory.eINSTANCE.createFieldBinding();
				uf.setBinding(binding);
				uf.setFillHorizontally(true);
				binding.setUmlElementUid(EmfWorkspace.getId(properties[0]));
				if(properties.length > 1){
					// When we're delegating to an object referenced by the root, e.g. contained objects
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
				if(ControlUtil.isMultiRow(controlKind)){
					uf.setPreferredHeight(150);
					uf.setOrientation(Orientation.VERTICAL);
				}else{
					uf.setPreferredHeight(25);
					uf.setOrientation(Orientation.HORIZONTAL);
				}
				if(container.getChildren().size() > 6 && container instanceof GridPanel){
					((GridPanel) container).setNumberOfColumns(2);
				}
			}
		}
	}
	public InvocationWizard getInvocationWizard(Namespace element){
		Resource resource = resourceFactory.getResource(EmfWorkspace.getId(element), "uml");
		if(resource.getContents().isEmpty()){
			if(element instanceof Operation){
				Operation operation = (Operation) element;
				if(EmfBehaviorUtil.isResponsibility(operation)){
					resource.getContents().add(ModelFactory.eINSTANCE.createResponsibilityUserInteractionModel());
				}else{
					resource.getContents().add(ModelFactory.eINSTANCE.createOperationInvocationWizard());
				}
			}else{
				resource.getContents().add(ModelFactory.eINSTANCE.createBehaviorUserInteractionModel());
			}
		}
		if(resource.getContents().get(0) instanceof ResponsibilityUserInteractionModel){
			ResponsibilityUserInteractionModel model = (ResponsibilityUserInteractionModel) resource.getContents().get(0);
			if(model.getInvocationWizard() == null){
				model.setInvocationWizard(WizardFactory.eINSTANCE.createResponsibilityInvocationWizard());
			}
			return model.getInvocationWizard();
		}else if(resource.getContents().get(0) instanceof BehaviorUserInteractionModel){
			BehaviorUserInteractionModel model = (BehaviorUserInteractionModel) resource.getContents().get(0);
			if(model.getInvocationWizard() == null){
				model.setInvocationWizard(WizardFactory.eINSTANCE.createBehaviorInvocationWizard());
			}
			return model.getInvocationWizard();
		}else{
			return (OperationInvocationWizard) resource.getContents().get(0);
		}
	}
}