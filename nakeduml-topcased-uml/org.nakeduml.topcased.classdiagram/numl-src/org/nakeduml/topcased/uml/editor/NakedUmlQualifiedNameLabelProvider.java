package org.nakeduml.topcased.uml.editor;

import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.uml2.common.edit.provider.IItemQualifiedTextProvider;

public class NakedUmlQualifiedNameLabelProvider extends AdapterFactoryLabelProvider{
	public NakedUmlQualifiedNameLabelProvider(NakedUmlItemProviderAdapterFactory adapterFactory){
		super(adapterFactory);
	}

	public String getText(Object object){
		IItemQualifiedTextProvider itemQualifiedTextProvider = (IItemQualifiedTextProvider) adapterFactory.adapt(object, IItemQualifiedTextProvider.class);
		return itemQualifiedTextProvider != null ? itemQualifiedTextProvider.getQualifiedText(object) : super.getText(object);
	}
}
