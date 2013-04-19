package org.opaeum.uim.userinteractionproperties;

import java.util.ArrayList;
import java.util.Arrays;

import org.eclipse.ui.views.properties.tabbed.ISectionDescriptor;
import org.eclipse.ui.views.properties.tabbed.ISectionDescriptorProvider;
import org.opaeum.propertysections.AbstractSectionDescriptorProvider;

public class UimPropertySectionProvider extends AbstractSectionDescriptorProvider implements ISectionDescriptorProvider{
	public UimPropertySectionProvider(String id){
		super(id);
	}

	@Override
	public ISectionDescriptor[] getSectionDescriptors(){
		this.result = new ArrayList<ISectionDescriptor>();
		result.addAll(Arrays.asList(readSectionDescriptors()));
		return result.toArray(new ISectionDescriptor[result.size()]);
	}
}
