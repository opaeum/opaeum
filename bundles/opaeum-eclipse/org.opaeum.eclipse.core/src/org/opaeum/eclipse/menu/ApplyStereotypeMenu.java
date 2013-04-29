package org.opaeum.eclipse.menu;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.CompoundContributionItem;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Stereotype;
import org.opaeum.eclipse.EmfElementFinder;

public class ApplyStereotypeMenu extends CompoundContributionItem{
	private IStructuredSelection selection;
	@Override
	protected IContributionItem[] getContributionItems(){
		this.selection = (IStructuredSelection) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getSelectionService().getSelection();
		List<IContributionItem> actions = new ArrayList<IContributionItem>();
		EObject e = EmfElementFinder.adaptObject(selection.getFirstElement());
		if(e instanceof Element){
			Element el = (Element) e;
			EList<Stereotype> applicableStereotypes = el.getApplicableStereotypes();
			for(Stereotype uri:applicableStereotypes){
				ApplyStereotypeAction a = new ApplyStereotypeAction(uri);
				a.configureAction(selection);
				actions.add(new ActionContributionItem(a));
			}
		}
		return (IContributionItem[]) actions.toArray(new IContributionItem[actions.size()]);
	}
}
