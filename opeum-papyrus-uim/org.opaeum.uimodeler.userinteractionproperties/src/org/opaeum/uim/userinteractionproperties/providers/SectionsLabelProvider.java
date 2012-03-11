package org.opaeum.uim.userinteractionproperties.providers;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.jface.viewers.ILabelProvider;
import org.opaeum.uim.action.provider.ActionItemProviderAdapterFactory;
import org.opaeum.uim.constraint.provider.ConstraintItemProviderAdapterFactory;
import org.opaeum.uim.control.provider.ControlItemProviderAdapterFactory;
import org.opaeum.uim.editor.provider.EditorItemProviderAdapterFactory;
import org.opaeum.uim.panel.provider.PanelItemProviderAdapterFactory;
import org.opaeum.uim.provider.UimItemProviderAdapterFactory;
import org.opaeum.uim.wizard.provider.WizardItemProviderAdapterFactory;
import org.topcased.tabbedproperties.AbstractTabbedPropertySheetPage;
import org.topcased.tabbedproperties.providers.AbstractSectionLabelProvider;
import org.topcased.tabbedproperties.providers.TabbedPropertiesLabelProvider;

public class SectionsLabelProvider extends AbstractSectionLabelProvider{
	private ILabelProvider labelProvider;

	protected ILabelProvider getAdapterFactoryLabelProvider(){
		if(labelProvider == null){
			List<AdapterFactory> f = new ArrayList<AdapterFactory>();
			f.add(new UimItemProviderAdapterFactory());
			f.add(new PanelItemProviderAdapterFactory());
			f.add(new EditorItemProviderAdapterFactory());
			f.add(new ActionItemProviderAdapterFactory());
			f.add(new WizardItemProviderAdapterFactory());
			f.add(new ControlItemProviderAdapterFactory());
			f.add(new ConstraintItemProviderAdapterFactory());
			f.addAll(AbstractTabbedPropertySheetPage.getPrincipalAdapterFactories());
			labelProvider = new TabbedPropertiesLabelProvider(new ComposedAdapterFactory(f));
		}
		return labelProvider;
	}
}
