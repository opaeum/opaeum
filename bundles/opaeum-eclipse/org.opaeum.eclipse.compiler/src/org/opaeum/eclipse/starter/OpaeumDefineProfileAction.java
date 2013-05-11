package org.opaeum.eclipse.starter;

import java.util.Arrays;

import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.uml2.uml.Profile;
import org.eclipse.uml2.uml.editor.actions.DefineProfileAction;
import org.opaeum.eclipse.EmfElementFinder;
import org.opaeum.eclipse.context.OpaeumEclipseContext;
import org.opaeum.eclipse.menu.AbstractOpaeumAction;
import org.opaeum.topcased.uml.editor.OpaeumItemProviderAdapterFactory;

public class OpaeumDefineProfileAction extends AbstractOpaeumAction{
	Profile profile;
	public OpaeumDefineProfileAction(IStructuredSelection selection){
		super(selection, "Define profile");
		profile=(Profile) EmfElementFinder.adaptObject(selection.getFirstElement());
	}
	@Override
	public void run(){
		DefineProfileAction delegate = new DefineProfileAction(){
			ILabelProvider lp=new AdapterFactoryLabelProvider(new OpaeumItemProviderAdapterFactory());
			@Override
			public void run(IAction action){
				super.collection=Arrays.asList((Object)profile);
				super.editingDomain=OpaeumEclipseContext.findOpenUmlFileFor(profile).getEditingDomain();
				super.command=createActionCommand(editingDomain, Arrays.asList(profile));
				super.run(action);
			}
			@Override
			protected ILabelProvider getLabelProvider(){
				return lp;
			}
		};
		delegate.run(this);
	}
}
