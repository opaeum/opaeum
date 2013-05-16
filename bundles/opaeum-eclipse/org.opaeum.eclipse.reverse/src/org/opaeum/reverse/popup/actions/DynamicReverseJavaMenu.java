package org.opaeum.reverse.popup.actions;

import org.eclipse.core.resources.IContainer;
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
		boolean canReverseJava = false;
		for(Object object:array){
			if(!canReverse(object)){
				canReverseJava = false;
			}
		}
		if(canReverseJava){
			return new IContributionItem[]{new ActionContributionItem(new ReverseEngineerAnnotationsToProfileAction(selection)),
					new ActionContributionItem(new ReverseEngineerJpaClassesAction(selection)),
					new ActionContributionItem(new ReverseEngineerSimpleClassesAction(selection))};
		}else{
			for(Object object:array){
				if(!canReverseMaven(object)){
					return new IContributionItem[]{new ActionContributionItem(new ReverseEngineerMavenProjectsAction(selection))};
				}
			}
		}
		return new IContributionItem[0];
	}
	private boolean canReverseMaven(Object object){
		return(object instanceof IContainer && ((IContainer) object).findMember("pom.xml") != null);
	}
	private boolean canReverse(Object object){
		return object instanceof ICompilationUnit || object instanceof IPackageFragment || object instanceof IClassFile;
	}
}
