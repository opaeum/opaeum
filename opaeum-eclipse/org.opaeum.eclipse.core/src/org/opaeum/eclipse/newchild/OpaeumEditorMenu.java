package org.opaeum.eclipse.newchild;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.common.CommonPlugin;
import org.eclipse.emf.ecore.EObject;
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
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.uml2.uml.Action;
import org.eclipse.uml2.uml.ActionInputPin;
import org.eclipse.uml2.uml.ActivityEdge;
import org.eclipse.uml2.uml.ActivityGroup;
import org.eclipse.uml2.uml.ActivityNode;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Connector;
import org.eclipse.uml2.uml.Constraint;
import org.eclipse.uml2.uml.Dependency;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.ElementImport;
import org.eclipse.uml2.uml.Generalization;
import org.eclipse.uml2.uml.InputPin;
import org.eclipse.uml2.uml.InterfaceRealization;
import org.eclipse.uml2.uml.LiteralBoolean;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.OutputPin;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.PackageImport;
import org.eclipse.uml2.uml.Profile;
import org.eclipse.uml2.uml.StructuredActivityNode;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.ValuePin;
import org.opaeum.eclipse.CreateStereotypedChildAction;
import org.opaeum.eclipse.EmfBehaviorUtil;
import org.opaeum.eclipse.OpaeumEclipsePlugin;
import org.opaeum.eclipse.OpaeumFilter;
import org.opaeum.eclipse.context.EObjectSelectorUI;
import org.opaeum.eclipse.context.OpaeumEclipseContext;
import org.opaeum.emf.extraction.StereotypesHelper;
import org.opaeum.metamodel.core.internal.StereotypeNames;
import org.opaeum.metamodel.workspace.OpaeumLibrary;

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
			Set<ICreateChildAction> customCreateChildActions = OpaeumEclipsePlugin.getDefault().getCreateChildActions();
			OpaeumLibrary lib = null;
			if(selectedObject instanceof Element){
				lib = OpaeumEclipseContext.getContextFor((Element) selectedObject).getEditingContextFor(selectedObject).getEmfWorkspace()
						.getOpaeumLibrary();
			}
			for(ICreateChildAction cca:customCreateChildActions){
				if(cca.isPotentialParent(selectedObject)){
					CreateChildAction createAction = cca.createAction(activeWorkbenchWindow.getActivePage().getActivePart(), ss, lib);
					if(createAction == null){
						OpaeumEclipsePlugin.logError("Null CreateChildAction produced by :" + cca.getClass(), new IllegalStateException());
					}else{
						createChildActions.add(createAction);
					}
				}
			}
			Map<String,Collection<IAction>> createChildSubmenuActions = extractSubmenuActions(createChildActions);
			if(selectedObject instanceof Element){
				OpaeumEclipseContext ctx = OpaeumEclipseContext.getContextFor((Element) selectedObject);
				if(ctx != null){
					this.selector = ctx.geteObjectSelectorUI();
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
			IWorkbenchPart activePart = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getPartService().getActivePart();
			for(CommandParameter descriptor:(Collection<CommandParameter>) theDescriptors){
				if(!(descriptor.getValue() instanceof Model || descriptor.getValue() instanceof Profile || CreateChildActions.CONTROLLED_FEATURES
						.contains(descriptor.getFeature()))){
					CreateChildAction actio = new CreateChildAndSelectAction(activePart, selection, descriptor);
					if(descriptor.getValue() instanceof InterfaceRealization || descriptor.getValue() instanceof Generalization
							|| descriptor.getValue() instanceof Dependency || descriptor.getValue() instanceof ElementImport
							|| descriptor.getValue() instanceof PackageImport){
						if((selectedObject instanceof Package || selectedObject instanceof Classifier)
								&& !(selectedObject instanceof Behavior && !EmfBehaviorUtil.hasExecutionInstance((Behavior) selectedObject))){
							actio.setText("Dependencies|" + descriptor.getEValue().eClass().getName());
							actions.add(actio);
						}
					}else if(descriptor.getValue() instanceof Constraint
							&& StereotypesHelper.hasStereotype((Element) selectedObject, StereotypeNames.RESPONSIBILITY,
									StereotypeNames.STANDALONE_SCREENFLOW_TASK, StereotypeNames.STANDALONE_SINGLE_SCREEN_TASK,
									StereotypeNames.EMBEDDED_SCREEN_FLOW_TASK, StereotypeNames.EMBEDDED_SINGLE_SCREEN_TASK)){
						actions.add(actio);
						Constraint value = (Constraint) descriptor.getValue();
						if(value.eClass().equals(UMLPackage.eINSTANCE.getConstraint())
								&& descriptor.getFeature().equals(UMLPackage.eINSTANCE.getNamespace_OwnedRule())){
							Constraint c = UMLFactory.eINSTANCE.createConstraint();
							LiteralBoolean lb = UMLFactory.eINSTANCE.createLiteralBoolean();
							c.setSpecification(lb);
							lb.setValue(true);
							CreateChildAction actio2 = new CreateStereotypedChildAction(activePart, selection, new CommandParameter(
									descriptor.getOwner(), descriptor.getFeature(), c), StereotypeNames.ESCALATION);
							actio2.setText("Escalations|Escalation");
							actions.add(actio2);
						}
					}else if(descriptor.getValue() instanceof Association){
						// ignore
					}else if(descriptor.getValue() instanceof ValuePin){
						if(selectedObject instanceof Action && !(selectedObject instanceof StructuredActivityNode)){
							ValuePin oclPin = UMLFactory.eINSTANCE.createValuePin();
							CreateChildAction actio2 = new CreateStereotypedChildAction(activePart, selection, new CommandParameter(
									descriptor.getOwner(), descriptor.getFeature(), oclPin), StereotypeNames.OCL_INPUT);
							String name = actio.getText().split("\\|")[0];
							actio2.setText(name + "|Ocl Input");
							actions.add(actio2);
							ValuePin newObjectPin = UMLFactory.eINSTANCE.createValuePin();
							CreateChildAction actio3 = new CreateStereotypedChildAction(activePart, selection, new CommandParameter(
									descriptor.getOwner(), descriptor.getFeature(), newObjectPin), StereotypeNames.NEW_OBJECT_INPUT);
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
					}else{
						actions.add(actio);
					}
					for(IAction iAction:actions){
						if(iAction == null){
							System.out.println(descriptor);
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
			Set<IAction> set = new TreeSet<IAction>(new Comparator<IAction>(){

				@Override
				public int compare(IAction o1,IAction o2){
					return o1.getText().compareTo(o2.getText());
				}
			});
			set.addAll(actions);
			for(IAction action:set){
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
		Map<String,Collection<IAction>> createSubmenuActions = new TreeMap<String,Collection<IAction>>();
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
