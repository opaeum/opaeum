package org.opaeum.propertysections;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.IFilter;
import org.eclipse.ui.internal.views.properties.tabbed.TabbedPropertyViewPlugin;
import org.eclipse.ui.internal.views.properties.tabbed.view.TabbedPropertyRegistry;
import org.eclipse.ui.views.properties.tabbed.AbstractSectionDescriptor;
import org.eclipse.ui.views.properties.tabbed.ISection;
import org.eclipse.ui.views.properties.tabbed.ISectionDescriptor;
import org.opaeum.eclipse.uml.filters.core.AbstractEObjectFilter;

public class AbstractSectionDescriptorProvider extends TabbedPropertyRegistry{
	public static final class FilterBasedDescriptor extends AbstractSectionDescriptor{
		private final ISection s;
		private final IFilter f;
		private String tabId;
		private String afterSection;
		private FilterBasedDescriptor(ISection s,IFilter f){
			this.s = s;
			this.f = f;
		}
		@Override
		public IFilter getFilter(){
			return f;
		}
		@Override
		public String getTargetTab(){
			return getTabId();
		}
		@Override
		public ISection getSectionClass(){
			return s;
		}
		@Override
		public String getId(){
			return s.getClass().getName();
		}
		public String getTabId(){
			return tabId;
		}
		public void setTabId(String tabId){
			this.tabId = tabId;
		}
		public String getAfterSection(){
			if(afterSection == null){
				return super.getAfterSection();
			}
			return afterSection;
		}
		public void setAfterSection(String afterSection){
			this.afterSection = afterSection;
		}
	}

	protected ArrayList<ISectionDescriptor> result;
	ISection previousSection;

	public AbstractSectionDescriptorProvider(String id){
		super(id);
	}

	protected FilterBasedDescriptor add(IFilter filter,ISection section){
		FilterBasedDescriptor result = new FilterBasedDescriptor(section, filter);
		this.result.add(result);
		if(previousSection != null){
			result.setAfterSection(previousSection.getClass().getName());
		}
		previousSection = section;
		return result;
	}

	protected <T extends EObject> FilterBasedDescriptor add(final Class<T> c,final ISection s){
		return add(new AbstractEObjectFilter<T>(){
			@Override
			public Class<? extends T> getObjectClass(){
				return c;
			}
			@Override
			public boolean select(T e){
				return c.isInstance(e);
			}
			@Override
			public boolean select(Object e){
				return c.isInstance(e) || super.select(e);
			}
		}, s);
	}
	@SuppressWarnings({"restriction","unchecked","rawtypes"})
	protected IConfigurationElement[] getConfigurationElements(String extensionPointId){
		if(contributorId == null){
			return new IConfigurationElement[0];
		}
		IExtensionPoint point = Platform.getExtensionRegistry().getExtensionPoint(
				TabbedPropertyViewPlugin.getPlugin().getBundle().getSymbolicName(), extensionPointId);
		IConfigurationElement[] extensions = point.getConfigurationElements();
		List unordered = new ArrayList(extensions.length);
		for(int i = 0;i < extensions.length;i++){
			IConfigurationElement extension = extensions[i];
			if(!extension.getName().equals(extensionPointId)){
				continue;
			}
			String contributor = extension.getAttribute("contributorId");
			if(!contributorId.equals(contributor)){
				continue;
			}
			unordered.add(extension);
		}
		return (IConfigurationElement[]) unordered.toArray(new IConfigurationElement[unordered.size()]);
	}
}