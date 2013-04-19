package org.opaeum.papyrus.uml;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.ListIterator;

import org.eclipse.papyrus.views.properties.xwt.XWTTabDescriptor;
import org.eclipse.papyrus.views.properties.xwt.XWTTabDescriptorProvider;
import org.eclipse.ui.views.properties.tabbed.ITabDescriptor;

public class OpaeumTabDescriptorProvider extends XWTTabDescriptorProvider{
	protected void orderTabDescriptors(final List<ITabDescriptor> descriptors){
		ListIterator<ITabDescriptor> listIterator = descriptors.listIterator();
		List<ITabDescriptor> append=new ArrayList<ITabDescriptor>();
		while(listIterator.hasNext()){
			ITabDescriptor td = (ITabDescriptor) listIterator.next();
			if(td.getCategory().equals("visual")){
				append.add(td);
				listIterator.remove();
			}
		}
		descriptors.addAll(append);
		Collections.sort(descriptors, new Comparator<ITabDescriptor>(){
			public int compare(ITabDescriptor tabDescriptor1,ITabDescriptor tabDescriptor2){

				int priority1 = getPriority(tabDescriptor1);
				int priority2 = getPriority(tabDescriptor2);
				if(priority1 < priority2){
					return -1;
				}
				if(priority1 > priority2){
					return 1;
				}
				// p1 == p2
				priority1 = getXWTTabPriority(tabDescriptor1);
				priority2 = getXWTTabPriority(tabDescriptor2);
				if(priority1 < priority2){
					return -1;
				}
				if(priority1 > priority2){
					return 1;
				}
				// p1 == p2
				String label1 = tabDescriptor1.getLabel();
				String label2 = tabDescriptor2.getLabel();
				return Collator.getInstance().compare(label1, label2);
			}
			private ITabDescriptor getPreviousTab(ITabDescriptor tab){
				String afterId = tab.getAfterTab();
				if(!(ITabDescriptor.TOP.equals(afterId))){
					for(ITabDescriptor descriptor:descriptors){
						String id = descriptor.getId();
						if(id != null && id.equals(afterId)){
							return descriptor;
						}
					}
				}
				// not found. Return null
				return null;
			}
			private int getPriority(ITabDescriptor tab){
				if(tab.getId().equals("advanced")){
					return 1001;// Advanced always last
				}
				ITabDescriptor previousTab = getPreviousTab(tab);
				if(previousTab != null && previousTab!=tab){
					return getPriority(previousTab)+1;
				}
				return getXWTTabPriority(tab);
			}
			private int getXWTTabPriority(ITabDescriptor tab){
				if(tab instanceof XWTTabDescriptor){
					XWTTabDescriptor xwtTab = (XWTTabDescriptor) tab;
					return xwtTab.getPriority();
				}else if(tab.getId().equals("advanced")){
					return 1000;// Advanced always last
				}else{
					return 0; // This is an Opaeum tab
				}
			}
		});
	}
}
