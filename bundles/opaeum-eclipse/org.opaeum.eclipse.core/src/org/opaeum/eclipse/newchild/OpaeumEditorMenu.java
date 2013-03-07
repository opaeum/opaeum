package org.opaeum.eclipse.newchild;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.common.CommonPlugin;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.edit.command.CommandParameter;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.ui.action.CreateChildAction;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IContributionManager;
import org.eclipse.jface.action.MenuManager;
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
import org.opaeum.eclipse.context.OpenUmlFile;
import org.opaeum.emf.extraction.StereotypesHelper;
import org.opaeum.metamodel.core.internal.StereotypeNames;
import org.opaeum.metamodel.workspace.OpaeumLibrary;

public class OpaeumEditorMenu extends UMLEditorMenu{
	private Collection<CommandParameter> descriptors;
	private StructuredSelection ss;
	private OpaeumLibrary lib;
	private EObjectSelectorUI selector;
	private EditingDomain domain;
	private EObject selectedObject;
	private Set<EReference> controlledFeatures;
	public Set<EReference> getControlledFeatures(){
		if(controlledFeatures == null){
			controlledFeatures = new HashSet<EReference>();
			for(ICreateChildActionProvider ccap:OpaeumEclipsePlugin.getDefault().getCreateChildActionProviders()){
				controlledFeatures.addAll(ccap.getControlledFeatures());
			}
		}
		return controlledFeatures;
	}
	private EObject getSelectedObject(){
		if(selectedObject == null){
			IWorkbenchWindow activeWorkbenchWindow = getActiveWorkbenchWindow();
			ISelectionService s = activeWorkbenchWindow.getSelectionService();
			IStructuredSelection ss = (IStructuredSelection) s.getSelection();
			if(ss != null){
				if(ss.getFirstElement() instanceof EObject){
					this.selectedObject = (EObject) ss.getFirstElement();
				}else if(ss.getFirstElement() instanceof IAdaptable){
					this.selectedObject = (EObject) ((IAdaptable) ss.getFirstElement()).getAdapter(EObject.class);
				}
			}
		}
		return selectedObject;
	}
	private IWorkbenchWindow getActiveWorkbenchWindow(){
		IWorkbenchWindow activeWorkbenchWindow = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
		return activeWorkbenchWindow;
	}
	public OpaeumEditorMenu(){
		createMenuContents();
	}
	public EObjectSelectorUI getSelector(){
		if(selector == null && getSelectedObject() != null){
			OpenUmlFile ouf = OpaeumEclipseContext.findOpenUmlFileFor(getSelectedObject());
			if(ouf != null){
				selector = ouf.geteObjectSelectorUI();
			}
		}
		return selector;
	}
	public void createMenuContents(){
		if(getSelectedObject() != null && getActiveWorkbenchWindow().getActivePage() != null){
			domain = (EditingDomain) getActiveWorkbenchWindow().getActivePage().getActiveEditor().getAdapter(EditingDomain.class);
			this.descriptors = new ArrayList<CommandParameter>();
			if(domain != null){
				for(Object o:domain.getNewChildDescriptors(getSelectedObject(), null)){
					CommandParameter cp = (CommandParameter) o;
					cp.setOwner(getSelectedObject());
					if(OpaeumFilter.isAllowedElement((EObject) cp.getValue()) && cp.getEStructuralFeature().getContainerClass() != null){
						// filter out stereotype features
						this.descriptors.add(cp);
					}
				}
				Collection<IAction> createChildActions = generateCreateChildActions(descriptors);
				addCustomerCreateChildActions(createChildActions, OpaeumEclipsePlugin.getDefault().getCreateChildActions());
				for(ICreateChildActionProvider p:OpaeumEclipsePlugin.getDefault().getCreateChildActionProviders()){
					addCustomerCreateChildActions(createChildActions, p.getActions());
				}
				Map<String,Collection<IAction>> createChildSubmenuActions = extractSubmenuActions(createChildActions);
				populateManager(this, createChildSubmenuActions, null);
				populateManager(this, createChildActions, null);
			}
			this.update();
		}
	}
	private void addCustomerCreateChildActions(Collection<IAction> createChildActions,
			Set<? extends ICreateChildAction> customCreateChildActions){
		for(ICreateChildAction cca:customCreateChildActions){
			if(cca.isPotentialParent(getSelectedObject())){
				CreateChildAction createAction = cca.createAction(getActiveWorkbenchWindow().getActivePage().getActivePart(), getSs(),
						getOpaeumLibrary());
				if(createAction == null){
//					OpaeumEclipsePlugin.logError("Null CreateChildAction produced by :" + cca.getClass(), new IllegalStateException());
				}else{
					createChildActions.add(createAction);
				}
			}
		}
	}
	private OpaeumLibrary getOpaeumLibrary(){
		if(this.lib == null){
			OpenUmlFile ouf = getOpenUmlFile();
			if(ouf != null){
				lib = ouf.getEmfWorkspace().getOpaeumLibrary();
			}
		}
		return lib;
	}
	private OpenUmlFile getOpenUmlFile(){
		OpenUmlFile ouf = null;
		if(getSelectedObject() instanceof Element){
			ouf = OpaeumEclipseContext.findOpenUmlFileFor((Element) getSelectedObject());
		}else if(getSelectedObject().eResource().getResourceSet() instanceof IOpaeumResourceSet){
			IOpaeumResourceSet orst = (IOpaeumResourceSet) getSelectedObject().eResource().getResourceSet();
			ouf = OpaeumEclipseContext.findOpenUmlFileFor((orst.getPrimaryFile()));
		}
		return ouf;
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
	protected Collection<IAction> generateCreateChildActions(Collection<CommandParameter> theDescriptors){
		ArrayList<IAction> actions = new ArrayList<IAction>();
		if(theDescriptors != null){
			IWorkbenchPart activePart = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getPartService().getActivePart();
			for(CommandParameter descriptor:theDescriptors){
				if(!(descriptor.getValue() instanceof Model || descriptor.getValue() instanceof Profile || getControlledFeatures().contains(
						descriptor.getFeature()))){
					CreateChildAction actio = new CreateChildAndSelectAction(activePart, getSs(), descriptor);
					if(descriptor.getValue() instanceof InterfaceRealization || descriptor.getValue() instanceof Generalization
							|| descriptor.getValue() instanceof Dependency || descriptor.getValue() instanceof ElementImport
							|| descriptor.getValue() instanceof PackageImport){
						if((getSelectedObject() instanceof Package || getSelectedObject() instanceof Classifier)
								&& !(getSelectedObject() instanceof Behavior && !EmfBehaviorUtil.hasExecutionInstance((Behavior) getSelectedObject()))){
							actio.setText("Dependencies|" + descriptor.getEValue().eClass().getName());
							actions.add(actio);
						}
					}else if(descriptor.getValue() instanceof Constraint
							&& StereotypesHelper.hasStereotype((Element) getSelectedObject(), StereotypeNames.RESPONSIBILITY,
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
							CreateChildAction actio2 = new CreateStereotypedChildAction(activePart, getSs(), new CommandParameter(descriptor.getOwner(),
									descriptor.getFeature(), c), StereotypeNames.ESCALATION);
							actio2.setText("Escalations|Escalation");
							actions.add(actio2);
						}
					}else if(descriptor.getValue() instanceof Association){
						// ignore
					}else if(descriptor.getValue() instanceof ValuePin){
						if(getSelectedObject() instanceof Action && !(getSelectedObject() instanceof StructuredActivityNode)){
							ValuePin oclPin = UMLFactory.eINSTANCE.createValuePin();
							CreateChildAction actio2 = new CreateStereotypedChildAction(activePart, getSs(), new CommandParameter(descriptor.getOwner(),
									descriptor.getFeature(), oclPin), StereotypeNames.OCL_INPUT);
							String name = actio.getText().split("\\|")[0];
							actio2.setText(name + "|Ocl Input");
							actions.add(actio2);
							ValuePin newObjectPin = UMLFactory.eINSTANCE.createValuePin();
							CreateChildAction actio3 = new CreateStereotypedChildAction(activePart, getSs(), new CommandParameter(descriptor.getOwner(),
									descriptor.getFeature(), newObjectPin), StereotypeNames.NEW_OBJECT_INPUT);
							actio3.setText(name + "|New Object Input");
							actions.add(actio3);
						}
					}else if(descriptor.getValue() instanceof InputPin && !(descriptor.getValue() instanceof ActionInputPin)){
						if(getSelectedObject() instanceof Action && !(getSelectedObject() instanceof StructuredActivityNode)){
							String name = actio.getText().split("\\|")[0];
							actio.setText(name + "|Object Flow Input");
							actions.add(actio);
						}
					}else if(descriptor.getValue() instanceof OutputPin){
						if(getSelectedObject() instanceof Action && !(getSelectedObject() instanceof StructuredActivityNode)){
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
				}
			}
		}
		Collections.<IAction>sort(actions, new Comparator<IAction>(){
			public int compare(IAction a1,IAction a2){
				return CommonPlugin.INSTANCE.getComparator().compare("" + a1.getText(), "" + a2.getText());
			}
		});
		return actions;
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
					((CreateChildAndSelectAction) action).setSelector(getSelector());
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
	private StructuredSelection getSs(){
		if(ss == null){
			ss = new StructuredSelection(getSelectedObject());
		}
		return ss;
	}
}
