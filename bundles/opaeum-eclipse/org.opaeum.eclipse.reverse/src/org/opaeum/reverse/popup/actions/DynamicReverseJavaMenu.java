package org.opaeum.reverse.popup.actions;

import org.eclipse.jdt.core.IClassFile;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.actions.CompoundContributionItem;
import org.opaeum.eclipse.menu.ICompoundContributionItem;

public class DynamicReverseJavaMenu extends CompoundContributionItem implements ICompoundContributionItem{
	IStructuredSelection selection;
	public DynamicReverseJavaMenu(IStructuredSelection selection){
		super();
		this.selection = selection;
	}
	@Override
	public IContributionItem[] getContributionItems(){
		Object[] array = selection.toArray();
		for(Object object:array){
			if(!canReverse(object)){
				return new IContributionItem[0];
			}
		}
		return new IContributionItem[]{new ActionContributionItem(new ReverseEngineerClassesAction(selection))};
	}
	private boolean canReverse(Object object){
		return object instanceof ICompilationUnit || object instanceof IPackageFragment || object instanceof IClassFile;
	}
}