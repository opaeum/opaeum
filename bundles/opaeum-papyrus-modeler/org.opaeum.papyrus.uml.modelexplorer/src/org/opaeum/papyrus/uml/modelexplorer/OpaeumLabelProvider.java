package org.opaeum.papyrus.uml.modelexplorer;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.facet.infra.browser.uicore.internal.model.ModelElementItem;
import org.eclipse.papyrus.views.modelexplorer.MoDiscoLabelProvider;
import org.opaeum.topcased.uml.editor.OpaeumItemProviderAdapterFactory;

public class OpaeumLabelProvider extends MoDiscoLabelProvider{
	OpaeumItemProviderAdapterFactory factory = new OpaeumItemProviderAdapterFactory();
	@Override
	public String getText(Object element){
		if(element instanceof ModelElementItem){
			EObject eObject = ((ModelElementItem) element).getEObject();
			Adapter ad = factory.createAdapter(eObject);
			return ((IItemLabelProvider)ad).getText(eObject);
		}
		return super.getText(element);
	}
}
