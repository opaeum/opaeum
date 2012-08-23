package org.opaeum.eclipse.newchild;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.common.CommonPlugin;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.command.CommandParameter;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.ui.action.CreateChildAction;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IContributionManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.ISelectionService;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.uml2.uml.Action;
import org.eclipse.uml2.uml.ActionInputPin;
import org.eclipse.uml2.uml.Activity;
import org.eclipse.uml2.uml.ActivityEdge;
import org.eclipse.uml2.uml.ActivityGroup;
import org.eclipse.uml2.uml.ActivityNode;
import org.eclipse.uml2.uml.AggregationKind;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.Component;
import org.eclipse.uml2.uml.Connector;
import org.eclipse.uml2.uml.Dependency;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.ElementImport;
import org.eclipse.uml2.uml.ExtensionEnd;
import org.eclipse.uml2.uml.Generalization;
import org.eclipse.uml2.uml.InputPin;
import org.eclipse.uml2.uml.InterfaceRealization;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.OutputPin;
import org.eclipse.uml2.uml.PackageImport;
import org.eclipse.uml2.uml.Port;
import org.eclipse.uml2.uml.Profile;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.StateMachine;
import org.eclipse.uml2.uml.Stereotype;
import org.eclipse.uml2.uml.StructuredActivityNode;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.ValuePin;
import org.opaeum.eclipse.CreateStereotypedChildAction;
import org.opaeum.eclipse.OpaeumEclipsePlugin;
import org.opaeum.eclipse.OpaeumFilter;
import org.opaeum.eclipse.context.EObjectSelectorUI;
import org.opaeum.eclipse.context.OpaeumEclipseContext;
import org.opaeum.emf.extraction.StereotypesHelper;
import org.opaeum.metamodel.core.internal.StereotypeNames;
import org.opaeum.name.NameConverter;

public class OpaeumEditorMenu extends UMLEditorMenu{
	private EditingDomain domain;
	private EObject selectedObject;
	private Collection<CommandParameter> descriptors;
	private EObjectSelectorUI selector;
	public OpaeumEditorMenu(){
		createMenuContents();
	}
	public void createMenuContents(){
		IWorkbenchWindow activeWorkbenchWindow = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
		ISelectionService s = activeWorkbenchWindow.getSelectionService();
		IStructuredSelection ss = (IStructuredSelection) s.getSelection();
		if(ss != null){
			if(ss.getFirstElement() instanceof EObject){
				this.selectedObject = (EObject) ss.getFirstElement();
			}else if(ss.getFirstElement() instanceof IAdaptable){
				this.selectedObject = (EObject) ((IAdaptable) ss.getFirstElement()).getAdapter(EObject.class);
			}
		}
		if(selectedObject != null && activeWorkbenchWindow.getActivePage() != null){
			ss = new StructuredSelection(selectedObject);
			domain = (EditingDomain) activeWorkbenchWindow.getActivePage().getActiveEditor().getAdapter(EditingDomain.class);
			this.descriptors = new ArrayList<CommandParameter>();
			for(Object o:domain.getNewChildDescriptors(selectedObject, null)){
				CommandParameter cp = (CommandParameter) o;
				cp.setOwner(selectedObject);
				if(OpaeumFilter.isAllowedElement((EObject) cp.getValue()) && cp.getEStructuralFeature().getContainerClass() != null){
					// filter out stereotype features
					this.descriptors.add(cp);
				}
			}
			Collection<IAction> createChildActions = generateCreateChildActions(descriptors, ss);
			for(ICreateChildAction cca:OpaeumEclipsePlugin.getDefault().getCreateChildActions()){
				if(cca.isPotentialParent(selectedObject)){
					createChildActions.add(cca.createAction(activeWorkbenchWindow.getActivePage().getActivePart(), ss));
				}
			}
			Map<String,Collection<IAction>> createChildSubmenuActions = extractSubmenuActions(createChildActions);
			if(selectedObject instanceof Element){
				OpaeumEclipseContext ctx = OpaeumEclipseContext.getContextFor((Element) selectedObject);
				if(ctx != null){
					this.selector = ctx.geteObjectSelectorUI();
				}
				for(Stereotype stereotype:((Element) selectedObject).getAppliedStereotypes()){
					for(EReference ref:stereotype.getDefinition().getEAllContainments()){
						if(!ref.getEReferenceType().isAbstract()){
							Collection<IAction> creates = new ArrayList<IAction>();
							createChildSubmenuActions.put(NameConverter.separateWords(NameConverter.capitalize(ref.getName())), creates);
							EObject childObject = EcoreUtil.create(ref.getEReferenceType());
							CommandParameter desc = new CommandParameter(((Element) selectedObject).getStereotypeApplication(stereotype), ref,
									childObject);
							CreateChildAction action = null;
							if(ref.getName().equals("deadlines")){
								action = new CreateStereotypedChildAction(activeWorkbenchWindow.getPartService().getActivePart(), ss, desc, StereotypeNames.DEADLINE);
								action.setText("Deadline");
							}else{
								action = new CreateStereotypedChildAction(activeWorkbenchWindow.getPartService().getActivePart(), ss, desc);
								action.setText(NameConverter.separateWords(NameConverter.capitalize(ref.getEReferenceType().getName())));
							}
							creates.add(action);
						}
					}
				}
			}
			populateManager(this, createChildSubmenuActions, null);
			populateManager(this, createChildActions, null);
			this.update();
		}
	}
	@SuppressWarnings("unchecked")
	protected Collection<IAction> generateCreateChildActionsGen(Collection<?> theDescriptors,ISelection selection){
		Collection<IAction> actions = new ArrayList<IAction>();
		if(theDescriptors != null){
			if(selectedObject instanceof Activity && !StereotypesHelper.hasStereotype((Element) selectedObject, StereotypeNames.BUSINES_PROCESS)){
				for(CommandParameter descriptor:(Collection<CommandParameter>) theDescriptors){
					if(!(descriptor.getValue() instanceof Property || descriptor.getValue() instanceof Operation || descriptor.getValue() instanceof Behavior)){
						CreateChildAction actio = new CreateChildAndSelectAction(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getPartService()
								.getActivePart(), selection, descriptor);
						actions.add(actio);
					}
				}
			}else{
				for(CommandParameter descriptor:(Collection<CommandParameter>) theDescriptors){
					if(!(descriptor.getValue() instanceof Model || descriptor.getValue() instanceof Profile)){
						CreateChildAction actio = new CreateChildAndSelectAction(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getPartService()
								.getActivePart(), selection, descriptor);
						if(descriptor.getValue() instanceof InterfaceRealization || descriptor.getValue() instanceof Generalization
								|| descriptor.getValue() instanceof Dependency || descriptor.getValue() instanceof ElementImport
								|| descriptor.getValue() instanceof PackageImport){
							actio.setText("Dependencies|" + descriptor.getEValue().eClass().getName());
						}
						if(descriptor.getValue() instanceof Association){
							// ignore
						}else if(descriptor.getValue() instanceof ValuePin){
							if(selectedObject instanceof Action && !(selectedObject instanceof StructuredActivityNode)){
								ValuePin oclPin = UMLFactory.eINSTANCE.createValuePin();
								CreateChildAction actio2 = new CreateStereotypedChildAction(PlatformUI.getWorkbench().getActiveWorkbenchWindow()
										.getPartService().getActivePart(), selection, new CommandParameter(descriptor.getOwner(), descriptor.getFeature(),
										oclPin),StereotypeNames.OCL_INPUT);
								String name = actio.getText().split("\\|")[0];
								actio2.setText(name + "|Ocl Input");
								actions.add(actio2);
								ValuePin newObjectPin = UMLFactory.eINSTANCE.createValuePin();
								CreateChildAction actio3 = new CreateStereotypedChildAction(PlatformUI.getWorkbench().getActiveWorkbenchWindow()
										.getPartService().getActivePart(), selection, new CommandParameter(descriptor.getOwner(), descriptor.getFeature(),
										newObjectPin),StereotypeNames.NEW_OBJECT_INPUT);
								actio3.setText(name + "|New Object Input");
								actions.add(actio3);
							}
						}else if(descriptor.getValue() instanceof InputPin && !(descriptor.getValue() instanceof ActionInputPin)){
							if(selectedObject instanceof Action && !(selectedObject instanceof StructuredActivityNode)){
								String name = actio.getText().split("\\|")[0];
								actio.setText(name + "|Object Flow Input");
								actions.add(actio);
							}
						}else if(descriptor.getValue() instanceof OutputPin){
							if(selectedObject instanceof Action && !(selectedObject instanceof StructuredActivityNode)){
								String name = actio.getText().split("\\|")[0];
								actio.setText(name + "|Object Flow Output");
								actions.add(actio);
							}
						}else if(descriptor.getValue() instanceof ActivityNode || descriptor.getValue() instanceof ActivityEdge
								|| descriptor.getValue() instanceof Connector || descriptor.getValue() instanceof ActivityGroup){
							// ignore
						}else if(descriptor.getValue() instanceof Behavior
								&& descriptor.getEStructuralFeature().equals(UMLPackage.eINSTANCE.getBehavioredClassifier_ClassifierBehavior())){
							if(!(selectedObject instanceof Behavior)){
								actions.add(actio);
							}
						}else if(descriptor.getValue() instanceof Property){
							if(!(descriptor.getValue() instanceof ExtensionEnd || descriptor.getValue() instanceof Port)){
								if((selectedObject instanceof Activity && StereotypesHelper.hasStereotype((Element) selectedObject,
										StereotypeNames.BUSINES_PROCESS))
										|| (selectedObject instanceof StateMachine && StereotypesHelper.hasStereotype((Element) selectedObject,
												StereotypeNames.BUSINES_STATE_MACHINE))){
									actions.add(actio);
								}else if(selectedObject instanceof Component
										&& StereotypesHelper.hasStereotype((Element) selectedObject, StereotypeNames.BUSINESS_COMPONENT)){
									if(descriptor.getValue() instanceof Port){
										if(descriptor.getFeature().equals(UMLPackage.eINSTANCE.getEncapsulatedClassifier_OwnedPort())){
											Port port = (Port) descriptor.getValue();
											CreateChildAction actio1 = new CreateStereotypedChildAction(PlatformUI.getWorkbench().getActiveWorkbenchWindow()
													.getPartService().getActivePart(), selection, new CommandParameter(descriptor.getOwner(), descriptor.getFeature(),
													port),StereotypeNames.BUSINESS_GATEWAY);
											actio1.setText("Owned attribute|Business Gateway");
											actions.add(actio1);
										}
									}
								}else{
									actions.add(actio);
									Property participantReference = UMLFactory.eINSTANCE.createProperty();
									CreateChildAction actio1 = new CreateStereotypedChildAction(PlatformUI.getWorkbench().getActiveWorkbenchWindow()
											.getPartService().getActivePart(), selection, new CommandParameter(descriptor.getOwner(), descriptor.getFeature(),
											participantReference),StereotypeNames.PARTICIPANT_REFERENCE);
									actio1.setText("Owned attribute|Participant Reference");
									participantReference.setAggregation(AggregationKind.NONE_LITERAL);
									actions.add(actio1);
									Property businessRoleContaiment = UMLFactory.eINSTANCE.createProperty();
									CreateChildAction actio2 = new CreateStereotypedChildAction(PlatformUI.getWorkbench().getActiveWorkbenchWindow()
											.getPartService().getActivePart(), selection, new CommandParameter(descriptor.getOwner(), descriptor.getFeature(),
											businessRoleContaiment),StereotypeNames.BUSINESS_ROLE_CONTAINMENT);
									actio2.setText("Owned attribute|Business Role Containment");
									actions.add(actio2);
								}
							}
						}else{
							actions.add(actio);
							if(descriptor.getValue() instanceof Operation){
								Operation responsibility = UMLFactory.eINSTANCE.createOperation();
								CreateChildAction actio2 = new CreateStereotypedChildAction(PlatformUI.getWorkbench().getActiveWorkbenchWindow()
										.getPartService().getActivePart(), selection, new CommandParameter(descriptor.getOwner(), descriptor.getFeature(),
										responsibility), StereotypeNames.RESPONSIBILITY);
								actio2.setText("Owned operation|Responsibility");
								actions.add(actio2);
							}else if(descriptor.getValue() instanceof Activity
									&& descriptor.getFeature().equals(UMLPackage.eINSTANCE.getBehavioredClassifier_OwnedBehavior())){
								Activity businessProcess = UMLFactory.eINSTANCE.createActivity();
								CreateChildAction actio2 = new CreateStereotypedChildAction(PlatformUI.getWorkbench().getActiveWorkbenchWindow()
										.getPartService().getActivePart(), selection, new CommandParameter(descriptor.getOwner(), descriptor.getFeature(),
										businessProcess),StereotypeNames.BUSINES_PROCESS);
								actio2.setText("Owned behavior|Business Process");
								actions.add(actio2);
								Activity method = UMLFactory.eINSTANCE.createActivity();
								CreateChildAction actio3 = new CreateStereotypedChildAction(PlatformUI.getWorkbench().getActiveWorkbenchWindow()
										.getPartService().getActivePart(), selection, new CommandParameter(descriptor.getOwner(), descriptor.getFeature(),
										method), StereotypeNames.METHOD);
								actio3.setText("Owned behavior|Method");
								actions.add(actio3);
							}
						}
					}
				}
			}
		}
		return actions;
	}
	protected void populateManager(IContributionManager manager,Map<String,Collection<IAction>> submenuActions,String contributionID){
		if(submenuActions != null){
			for(Map.Entry<String,Collection<IAction>> entry:submenuActions.entrySet()){
				MenuManager submenuManager = new MenuManager(entry.getKey());
				if(contributionID != null){
					manager.insertBefore(contributionID, submenuManager);
				}else{
					manager.add(submenuManager);
				}
				populateManager(submenuManager, entry.getValue(), null);
			}
		}
	}
	protected Collection<IAction> generateCreateChildActions(Collection<?> theDescriptors,ISelection selection){
		List<IAction> createChildActions = (List<IAction>) generateCreateChildActionsGen(theDescriptors, selection);
		Collections.<IAction>sort(createChildActions, new Comparator<IAction>(){
			public int compare(IAction a1,IAction a2){
				return CommonPlugin.INSTANCE.getComparator().compare("" + a1.getText(), "" + a2.getText());
			}
		});
		return createChildActions;
	}
	protected void populateManager(IContributionManager manager,Collection<? extends IAction> actions,String contributionID){
		if(actions != null){
			for(IAction action:actions){
				if(action instanceof CreateChildAndSelectAction){
					((CreateChildAndSelectAction) action).setSelector(selector);
				}
				if(contributionID != null){
					manager.insertBefore(contributionID, action);
				}else{
					manager.add(action);
				}
			}
		}
	}
	protected Map<String,Collection<IAction>> extractSubmenuActions(Collection<IAction> createActions){
		Map<String,Collection<IAction>> createSubmenuActions = new LinkedHashMap<String,Collection<IAction>>();
		if(createActions != null){
			for(Iterator<IAction> actions = createActions.iterator();actions.hasNext();){
				IAction action = actions.next();
				StringTokenizer st = new StringTokenizer(action.getText() + "", "|"); //$NON-NLS-1$
				if(st.countTokens() == 2){
					String text = st.nextToken().trim();
					Collection<IAction> submenuActions = createSubmenuActions.get(text);
					if(submenuActions == null){
						submenuActions = new ArrayList<IAction>();
						createSubmenuActions.put(text, submenuActions);
					}
					action.setText(st.nextToken().trim());
					submenuActions.add(action);
					actions.remove();
				}
			}
		}
		return createSubmenuActions;
	}
}
