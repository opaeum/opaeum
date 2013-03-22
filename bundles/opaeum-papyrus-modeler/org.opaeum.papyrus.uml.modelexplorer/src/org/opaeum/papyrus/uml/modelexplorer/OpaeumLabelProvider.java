package org.opaeum.papyrus.uml.modelexplorer;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.opaeum.eclipse.EmfElementFinder;
import org.opaeum.topcased.uml.editor.OpaeumItemProviderAdapterFactory;

public class OpaeumLabelProvider extends LabelProvider {
	OpaeumItemProviderAdapterFactory factory = new OpaeumItemProviderAdapterFactory();

	@Override
	public String getText(Object element) {
		EObject eObject = EmfElementFinder.adaptObject(element);
		if (eObject == null) {
			return super.getText(element);
		} else {
			Adapter ad = factory.createAdapter(eObject);
			return ((IItemLabelProvider) ad).getText(eObject);
		}
	}
}
