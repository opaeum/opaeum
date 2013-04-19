package org.opaeum.papyrus.uml.modelexplorer.newdiagram;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.CompoundContributionItem;
import org.eclipse.ui.menus.CommandContributionItem;
import org.eclipse.ui.menus.CommandContributionItemParameter;
import org.eclipse.uml2.uml.Activity;
import org.eclipse.uml2.uml.Collaboration;
import org.eclipse.uml2.uml.Component;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.StateMachine;
import org.opaeum.eclipse.EmfBehaviorUtil;
import org.opaeum.eclipse.menu.ICompoundContributionItem;
import org.opaeum.papyrus.uml.modelexplorer.Activator;

public class DynamicNewDiagramMenu extends CompoundContributionItem implements ICompoundContributionItem{
	private IStructuredSelection selection;
	private List<IContributionItem> actions = null;
	public DynamicNewDiagramMenu(IStructuredSelection selection){
		this.selection = selection;
	}
	protected Object getElementFrom(){
		Object firstElement = selection.getFirstElement();
		if(!(firstElement instanceof Element) && firstElement instanceof IAdaptable){
			return ((IAdaptable) firstElement).getAdapter(EObject.class);
		}
		return firstElement;
	}
	@Override
	public IContributionItem[] getContributionItems(){
		if(actions == null){
			actions = new ArrayList<IContributionItem>();
			Object firstElement = selection.getFirstElement();
			EObject selectedObject = null;
			if(selection.getFirstElement() instanceof EObject){
				selectedObject = (EObject) selection.getFirstElement();
			}else if(selection.getFirstElement() instanceof IAdaptable){
				selectedObject = (EObject) ((IAdaptable) selection.getFirstElement()).getAdapter(EObject.class);
			}
			if(selectedObject instanceof org.eclipse.uml2.uml.Package){
				String cdid = "org.eclipse.papyrus.uml.diagram.clazz.CreateClassDiagramCommand";
				ImageDescriptor cdimg = Activator.imageDescriptorFromPlugin("org.opaeum.papyrus.classdiagram", "icons/obj16/Diagram_Class.gif");
				actions.add(new CommandContributionItem(new CommandContributionItemParameter(PlatformUI.getWorkbench().getActiveWorkbenchWindow(), cdid,
						cdid, null, cdimg, null, null, null, null, null, CommandContributionItem.STYLE_PUSH, null, false)));
				String ucdid = "org.eclipse.papyrus.uml.diagram.usecase.CreateUseCaseDiagramCommand";
				ImageDescriptor ucdimg = Activator.imageDescriptorFromPlugin("org.opaeum.papyrus.usecasediagram", "icons/obj16/Diagram_UseCase.gif");
				actions.add(new CommandContributionItem(new CommandContributionItemParameter(PlatformUI.getWorkbench().getActiveWorkbenchWindow(), ucdid,
						ucdid, null, ucdimg, null, null, null, null, null, CommandContributionItem.STYLE_PUSH, null, false)));
			}else if(selectedObject instanceof Activity){
				Activity a = (Activity) selectedObject;
				String COMMAND_ID=null;
				ImageDescriptor desc=null;;
				if(EmfBehaviorUtil.isProcess(a)){
					COMMAND_ID = "org.opaeum.papyrus.businessprocessdiagram.CreateBusinessProcessDiagramCommand";
					desc = Activator.imageDescriptorFromPlugin("org.opaeum.papyrus.businessprocessdiagram", "icons/obj16/Diagram_Activity.gif");
				}else{
					COMMAND_ID = "org.opaeum.papyrus.methoddiagram.CreateMethodDiagramCommand";
					desc = Activator.imageDescriptorFromPlugin("org.opaeum.papyrus.businessprocessdiagram", "icons/obj16/Diagram_Activity.gif");
				}
				actions.add(new CommandContributionItem(new CommandContributionItemParameter(PlatformUI.getWorkbench().getActiveWorkbenchWindow(), COMMAND_ID,
						COMMAND_ID, null, desc, null, null, null, null, null, CommandContributionItem.STYLE_PUSH, null, true)));
			}else if(selectedObject instanceof Component || selectedObject instanceof Collaboration){
				String cdid = "org.eclipse.papyrus.uml.diagram.composite.createCompositeDiagram";
				ImageDescriptor cdimg = Activator.imageDescriptorFromPlugin("org.eclipse.papyrus.uml.diagram.composite", "icons/obj16/Diagram_CompositeStructure.gif");
				actions.add(new CommandContributionItem(new CommandContributionItemParameter(PlatformUI.getWorkbench().getActiveWorkbenchWindow(), cdid,
						cdid, null, cdimg, null, null, null, null, null, CommandContributionItem.STYLE_PUSH, null, false)));

			}else if(selectedObject instanceof StateMachine){
				String cdid = "org.eclipse.papyrus.uml.diagram.statemachine.CreationCommand";
				ImageDescriptor cdimg = Activator.imageDescriptorFromPlugin("org.eclipse.papyrus.uml.diagram.statemachine", "icons/obj16/Diagram_StateMachine.gif");
				actions.add(new CommandContributionItem(new CommandContributionItemParameter(PlatformUI.getWorkbench().getActiveWorkbenchWindow(), cdid,
						cdid, null, cdimg, null, null, null, null, null, CommandContributionItem.STYLE_PUSH, null, false)));

			}
		}
		return (IContributionItem[]) actions.toArray(new IContributionItem[actions.size()]);
	}
}
