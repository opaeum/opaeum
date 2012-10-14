package org.opaeum.uim.userinteractionproperties.providers;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.opaeum.topcased.uml.editor.OpaeumItemProviderAdapterFactory;
import org.opaeum.uim.action.provider.ActionItemProviderAdapterFactory;
import org.opaeum.uim.constraint.provider.ConstraintItemProviderAdapterFactory;
import org.opaeum.uim.control.provider.ControlItemProviderAdapterFactory;
import org.opaeum.uim.editor.provider.EditorItemProviderAdapterFactory;
import org.opaeum.uim.model.provider.ModelItemProviderAdapterFactory;
import org.opaeum.uim.panel.provider.PanelItemProviderAdapterFactory;
import org.opaeum.uim.provider.UimItemProviderAdapterFactory;
import org.opaeum.uim.wizard.provider.WizardItemProviderAdapterFactory;

public class SectionsLabelProvider {
	static private ILabelProvider labelProvider;

	public static  ILabelProvider getAdapterFactoryLabelProvider(){
		if(labelProvider == null){
			List<AdapterFactory> f = new ArrayList<AdapterFactory>();
			f.add(new UimItemProviderAdapterFactory());
			f.add(new PanelItemProviderAdapterFactory());
			f.add(new EditorItemProviderAdapterFactory());
			f.add(new ActionItemProviderAdapterFactory());
			f.add(new WizardItemProviderAdapterFactory());
			f.add(new ControlItemProviderAdapterFactory());
			f.add(new ConstraintItemProviderAdapterFactory());
			f.add(new ModelItemProviderAdapterFactory());
			f.add(new OpaeumItemProviderAdapterFactory());
			labelProvider = new AdapterFactoryLabelProvider(new ComposedAdapterFactory(f));
		}
		return labelProvider;
	}
}
