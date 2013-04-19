package org.opaeum.eclipse.uml.propertysections.common;

import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.uml2.common.edit.provider.IItemQualifiedTextProvider;
import org.opaeum.topcased.uml.editor.OpaeumItemProviderAdapterFactory;

public class OpaeumQualifiedNameLabelProvider extends AdapterFactoryLabelProvider{
	public OpaeumQualifiedNameLabelProvider(OpaeumItemProviderAdapterFactory adapterFactory){
		super(adapterFactory);
	}

	public String getText(Object object){
		IItemQualifiedTextProvider itemQualifiedTextProvider = (IItemQualifiedTextProvider) adapterFactory.adapt(object, IItemQualifiedTextProvider.class);
		return itemQualifiedTextProvider != null ? itemQualifiedTextProvider.getQualifiedText(object) : super.getText(object);
	}
}
