package org.opaeum.uim.uml2uim;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.uml2.uml.BehavioredClassifier;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.InterfaceRealization;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Namespace;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.TypedElement;
import org.opaeum.eclipse.EmfBehaviorUtil;
import org.opaeum.eclipse.EmfElementFinder;
import org.opaeum.eclipse.EmfOperationUtil;
import org.opaeum.eclipse.EmfPropertyUtil;
import org.opaeum.emf.workspace.EmfWorkspace;
import org.opaeum.name.NameConverter;
import org.opaeum.uim.Page;
import org.opaeum.uim.PageOrdering;
import org.opaeum.uim.UimFactory;
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
import org.opaeum.uim.component.UimComponent;
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
	protected abstract Page addPage(UserInterfaceRoot container,NamedElement represented);
	protected abstract void removePage(UserInterfaceRoot container,Page p);
	protected abstract UserInterfaceRoot getUserInterfaceRoot();
	public void populateUserInterface(Element owner,String title,Collection<? extends TypedElement> typedElements){
		Page page = UserInterfaceUtil.findRepresentingPage(owner, getUserInterfaceRoot());
		NamedElement ne = (NamedElement) owner;
		if(page == null){
			page = findOrCreatePageFor(getUserInterfaceRoot(), ne);
			page.setName(title);
		}
		if(!page.isUnderUserControl()){
			if(page.getPanel() == null){
				GridPanel layout = PanelFactory.eINSTANCE.createGridPanel();
				layout.setNumberOfColumns(1);
				page.setPanel(layout);
				page.setUmlElementUid(EmfWorkspace.getId(owner));
			}
			if(!page.getPanel().isUnderUserControl()){
				page.getPanel().setName(NameConverter.capitalize(ne.getName()));
				if(owner instanceof BehavioredClassifier){
					Map<InterfaceRealization,Collection<TypedElement>> interfaces = extractPropertiesByInterface((Classifier) owner, typedElements);
					addUserInterfaceElementsFor(getUserInterfaceRoot(), page.getPanel(), typedElements);
					addInterfaceProperties(getUserInterfaceRoot(), interfaces);
				}else{
					addUserInterfaceElementsFor(getUserInterfaceRoot(), page.getPanel(), typedElements);
				}
			}
		}
	}
	private void addInterfaceProperties(UserInterfaceRoot uiRoot,Map<InterfaceRealization,Collection<TypedElement>> interfaces){
		for(Entry<InterfaceRealization,Collection<TypedElement>> entry:interfaces.entrySet()){
			if(!UserInterfaceUtil.isIgnored(entry.getKey(), uiRoot)){
				Page page = findOrCreatePageFor(uiRoot, entry.getKey());
				if(!UserInterfaceUtil.isUnderUserControl(page)){
					page.setName(NameConverter.separateWords(entry.getKey().getName()));
					if(page.getPanel() == null){
						GridPanel rootGridPanel = PanelFactory.eINSTANCE.createGridPanel();
						rootGridPanel.setNumberOfColumns(1);
						page.setPanel(rootGridPanel);
					}
					addUserInterfaceElementsFor(uiRoot, page.getPanel(), entry.getValue());
				}
			}
		}
	}
	private Map<InterfaceRealization,Collection<TypedElement>> extractPropertiesByInterface(Classifier owner,
			Collection<? extends TypedElement> typedElements){
		Map<InterfaceRealization,Collection<TypedElement>> interfaces = buildInterfaceMap(owner, typedElements);
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
	private Map<InterfaceRealization,Collection<TypedElement>> buildInterfaceMap(Classifier owner,
			Collection<? extends TypedElement> typedElements){
		// NB!! this looks like a roundabout way, but remember we don't want to display overridden and redefined properties twice, so only look
		// for the properties
		// present contained by interfaces AFTER redefinition and overriding has been applied
		Map<InterfaceRealization,Collection<TypedElement>> interfaces = new HashMap<InterfaceRealization,Collection<TypedElement>>();
		for(TypedElement typedElement:new ArrayList<TypedElement>(typedElements)){
			EObject container = EmfElementFinder.getContainer(typedElement);
			if(container instanceof Interface && owner instanceof BehavioredClassifier){
				interfaces.put(EmfElementFinder.findNearestInterfaceRealization(owner, (Interface) container), new ArrayList<TypedElement>());
			}
		}
		Set<InterfaceRealization> keySet = new HashSet<InterfaceRealization>(interfaces.keySet());
		for(InterfaceRealization interface1:keySet){
			for(InterfaceRealization interface2:keySet){
				if(interface2.getContract() != interface1.getContract() && interface2.getContract().conformsTo(interface1.getContract())){
					interfaces.remove(interface1);
				}
			}
		}
		return interfaces;
	}
	private Collection<TypedElement> getMatchingCollection(Map<InterfaceRealization,Collection<TypedElement>> interfaces,Interface container){
		Set<Entry<InterfaceRealization,Collection<TypedElement>>> entrySet = interfaces.entrySet();
		for(Entry<InterfaceRealization,Collection<TypedElement>> entry:entrySet){
			if(entry.getKey().getContract().conformsTo(container)){
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
				}else if(property.getType() != null){
					maybeAddField(container, 390, property);
				}
			}
		}
	}
	@SuppressWarnings({"unchecked","rawtypes"})
	private void addDetailsPageForField(UserInterfaceRoot uiRoot,TypedElement te){
		Page page = findOrCreatePageFor(uiRoot, te);
		if(!UserInterfaceUtil.isUnderUserControl(page)){
			if(page.getPanel() == null){
				GridPanel layout = PanelFactory.eINSTANCE.createGridPanel();
				page.setPanel(layout);
				layout.setNumberOfColumns(1);
			}
			Set<UimComponent> controlledElements = new HashSet<UimComponent>();
			Classifier c = (Classifier) te.getType();
			// // Create tab and add to panel
			for(Property property:(Collection<Property>) (Collection) EmfPropertyUtil.getEffectiveProperties(c)){
				if(UserInterfaceUtil.requiresTablePage(uiRoot, property)){
					// No further details
				}else if(property.getOtherEnd() == null || !property.getOtherEnd().isComposite()){
					UimField f = maybeAddField(page.getPanel(), 390, te, property);
					if(f != null){
						controlledElements.add(f);
					}
				}
			}
			UserInterfaceUtil.removeObsoleteElements(controlledElements, page.getPanel().getChildren());
			if(page.getPanel().getChildren().isEmpty()){
				removePage(uiRoot, page);
			}
		}
	}
	private void addTablePageForField(UserInterfaceRoot pc,TypedElement e){
		UimDataTable table = null;
		Page page = findOrCreatePageFor(pc, e);
		if(UserInterfaceUtil.isUnderUserControl(page)){
			if(page.getPanel() != null){
				UserInteractionElement uie = UserInterfaceUtil.findRepresentingElement(e, page.getPanel());
				if(!(uie instanceof UimDataTable)){
					// Not a table, but a detailsTab. Replace with new Table
					// TODO convert existing detailsTab to a DataTable
					page.getPanel().getChildren().clear();
					table = findOrCreateTable(e, page.getPanel());
					populateDataTable(e, table);
				}
			}else{
				// No panel - assume user knows what he is doing
			}
		}else{
			if(page.getPanel() == null){
				GridPanel gridPanel = PanelFactory.eINSTANCE.createGridPanel();
				page.setPanel(gridPanel);
			}
			table = findOrCreateTable(e, page.getPanel());
			populateDataTable(e, table);
		}
	}
	private Page findOrCreatePageFor(UserInterfaceRoot pc,NamedElement e){
		Page page = UserInterfaceUtil.findRepresentingPage(e, pc);
		if(page == null){
			// Nothing found -create from scratch
			page = addPage(pc, e);
			page.setUmlElementUid(EmfWorkspace.getId(e));
			page.setName(NameConverter.separateWords(e.getName()));
		}
		registerPageOrdering(pc, page);
		return page;
	}
	private void registerPageOrdering(UserInterfaceRoot pc,Page page){
		PageOrdering found = null;
		for(PageOrdering po:pc.getPageOrdering()){
			if(po.getPage() == page){
				found = po;
				break;
			}
		}
		if(found == null){
			found = UimFactory.eINSTANCE.createPageOrdering();
			found.setPosition(pc.getPageOrdering().size());
			pc.getPageOrdering().add(found);
			found.setPage(page);
		}
	}
	private void populateDataTable(TypedElement e,UimDataTable table){
		if(!table.isUnderUserControl()){
			Set<UimComponent> controlledElements = new HashSet<UimComponent>();
			Classifier type = (Classifier) e.getType();
			BuiltInLink edit = findOrCreateBuiltInLink(table.getChildren(), BuiltInLinkKind.EDIT);
			controlledElements.add(edit);
			if(!edit.isUnderUserControl()){
				edit.setName("Edit");
				edit.setPreferredWidth(30);
			}
			addDataColumns(table, controlledElements, type);
			BuiltInActionButton deleteInRow = UserInterfaceUtil.findOrCreateBuiltInActionButton(ActionKind.DELETE, table.getChildren());
			controlledElements.add(deleteInRow);
			if(!deleteInRow.isUnderUserControl()){
				deleteInRow.setName("Delete");
			}
			BuiltInActionButton deleteOnActionBar = UserInterfaceUtil.findOrCreateBuiltInActionButton(ActionKind.ADD,
					table.getActionsOnMultipleSelection());
			controlledElements.add(deleteOnActionBar);
			if(!deleteOnActionBar.isUnderUserControl()){
				deleteOnActionBar.setName("Delete");
			}
			BuiltInActionButton addButton = UserInterfaceUtil.findOrCreateBuiltInActionButton(ActionKind.ADD,
					table.getActionsOnMultipleSelection());
			controlledElements.add(addButton);
			if(!addButton.isUnderUserControl()){
				addButton.setName("Add");
			}
			for(Operation operation:EmfOperationUtil.getEffectiveOperations(type)){
				if(!operation.isQuery()){
					findOrCreateInvocationButtonOnTable(table, controlledElements, operation);
				}
			}
			UserInterfaceUtil.removeObsoleteElements(controlledElements, table.getChildren());
			UserInterfaceUtil.removeObsoleteElements(controlledElements, table.getActionsOnMultipleSelection());
		}
	}
	private void addDataColumns(UimDataTable table,Set<UimComponent> controlledElements,Classifier type){
		Collection<Property> attrs = EmfPropertyUtil.getEffectiveProperties(type);
		for(Property property:attrs){
			boolean isNonComposite = property.getOtherEnd() == null || !property.getOtherEnd().isComposite();
			if(isNonComposite && !EmfPropertyUtil.isMany(property)){
				UimField f = maybeAddField(table, 0, property);
				if(f != null){
					controlledElements.add(f);
				}
			}
		}
	}
	private void findOrCreateInvocationButtonOnTable(UimDataTable table,Set<UimComponent> controlledElements,Operation operation){
		InvocationButton invocationButton = UserInterfaceUtil.findInvocationButton(operation, table.getChildren());
		if(invocationButton == null){
			invocationButton = UserInterfaceUtil.findInvocationButton(operation, table.getActionsOnMultipleSelection());
			if(invocationButton == null){
				invocationButton = ActionFactory.eINSTANCE.createInvocationButton();
				invocationButton.setUmlElementUid(EmfWorkspace.getId(operation));
			}
			table.getActionsOnMultipleSelection().add(invocationButton);
		}else{
			InvocationButton duplicate = UserInterfaceUtil.findInvocationButton(operation, table.getActionsOnMultipleSelection());
			if(duplicate != null){
				// Add to valid objects - to avoid removal - user is allowed to have the same invocation in both places
				controlledElements.add(duplicate);
			}
		}
		controlledElements.add(invocationButton);
		if(!invocationButton.isUnderUserControl()){
			invocationButton.setName(NameConverter.separateWords(NameConverter.capitalize(operation.getName())));
			invocationButton.setPopup(getInvocationWizard(operation));
			// TODO defaults for layout
		}
	}
	private BuiltInLink findOrCreateBuiltInLink(EList<UimComponent> children,BuiltInLinkKind edit2){
		BuiltInLink edit = ActionFactory.eINSTANCE.createBuiltInLink();
		children.add(edit);
		edit.setKind(edit2);
		return edit;
	}
	private UimDataTable findOrCreateTable(TypedElement e,AbstractPanel gridPanel){
		UimDataTable table = null;
		for(UimComponent uic:new ArrayList<UimComponent>(gridPanel.getChildren())){
			if(uic instanceof UimDataTable && EmfWorkspace.getId(e).equals(((UimDataTable) uic).getBinding().getLastPropertyUuid())){
				table = (UimDataTable) uic;
			}else if(!uic.isUnderUserControl()){
				gridPanel.getChildren().remove(uic);
			}
		}
		if(table == null){
			table = ComponentFactory.eINSTANCE.createUimDataTable();
			gridPanel.getChildren().add(table);
		}
		if(!table.isUnderUserControl()){
			table.setFillVertically(true);
			table.setFillHorizontally(true);
			TableBinding binding = BindingFactory.eINSTANCE.createTableBinding();
			table.setBinding(binding);
			binding.setUmlElementUid(EmfWorkspace.getId(e));
		}
		return table;
	}
	private UimField maybeAddField(UimContainer container,int labelWidth,TypedElement...properties){
		UimField uf = null;
		if(!(container == null || UserInterfaceUtil.isUnderUserControl(container))){
			// Only add fields to containers not under user control
			TypedElement property = properties[properties.length - 1];
			UserInteractionElement representingElement = UserInterfaceUtil.findRepresentingElement(property, container);
			if(representingElement == null){
				uf = ComponentFactory.eINSTANCE.createUimField();
				container.getChildren().add(uf);
			}else if(!representingElement.isUnderUserControl() && representingElement instanceof UimField){
				uf = (UimField) representingElement;
			}
			if(uf != null){
				uf.setMinimumLabelWidth(labelWidth < 100 ? 0 : labelWidth / 2);
				uf.setName(NameConverter.separateWords(property.getName()));
				ControlKind controlKind = ControlUtil.getPreferredControlKind(UmlUimLinks.getNearestUserInterfaceRoot(container), property,
						container instanceof UimDataTable);
				if(controlKind != uf.getControlKind()){
					uf.setControlKind(controlKind);
					uf.setControl(ControlUtil.instantiate(uf.getControlKind()));
				}
				FieldBinding binding = BindingFactory.eINSTANCE.createFieldBinding();
				uf.setFillHorizontally(true);
				binding.setUmlElementUid(EmfWorkspace.getId(properties[0]));
				// NB!! only set the binding now for UimContentAdapter
				uf.setBinding(binding);
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
		return uf;
	}
	protected InvocationWizard getInvocationWizard(Namespace element){
		Resource resource = resourceFactory.getResource(element);
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