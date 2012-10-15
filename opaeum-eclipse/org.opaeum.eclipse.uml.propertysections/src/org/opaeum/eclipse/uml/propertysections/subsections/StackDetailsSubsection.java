package org.opaeum.eclipse.uml.propertysections.subsections;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

public abstract class StackDetailsSubsection<T extends EObject> implements IDetailsSubsection<T>{
	List<AbstractDetailsSubsection<? extends T>> delegates = new ArrayList<AbstractDetailsSubsection<? extends T>>();
	StackLayout stackLayout = new StackLayout();
	Composite contentPane;
	Map<String,AbstractDetailsSubsection<? extends T>> layers = new HashMap<String,AbstractDetailsSubsection<? extends T>>();
	public StackDetailsSubsection(TabbedPropertySheetWidgetFactory factory,Composite parent,String eventDetailsLabel){
		if(parent instanceof Group){
			this.contentPane = parent;
		}else{
			this.contentPane = factory.createGroup(parent, eventDetailsLabel);
		}
		contentPane.setLayout(stackLayout);
		addLayers(contentPane, factory);
		stackLayout.topControl=null;
	}
	protected abstract void addLayers(Composite composite,TabbedPropertySheetWidgetFactory factory);
	protected String getKeyFor(List<T> eObjectList){
		if(eObjectList.size() > 0){
			return eObjectList.get(0).eClass().getName();
		}else{
			return null;
		}
	}
	protected void addLayer(String key,AbstractDetailsSubsection<? extends T> ss){
		layers.put(key, ss);
		delegates.add(ss);
	}
	@Override
	public void setEditingDomain(EditingDomain mixedEditDomain){
		for(AbstractDetailsSubsection<? extends T> d:delegates){
			d.setEditingDomain(mixedEditDomain);
		}
	}
	@Override
	public void setEnabled(boolean enabled){
		for(AbstractDetailsSubsection<? extends T> d:delegates){
			d.setEnabled(enabled);
		}
	}
	@Override
	@SuppressWarnings("unchecked")
	public void selectionChanged(SelectionChangedEvent event){
		ArrayList<T> eObjectList = new ArrayList<T>();
		if(event != null){
			Iterator<EObject> iterator = ((IStructuredSelection) event.getSelection()).iterator();
			while(iterator.hasNext()){
				EObject eObject = iterator.next();
				eObjectList.add((T) eObject);
			}
		}
		String key = getKeyFor(eObjectList);
		if(key == null){
			stackLayout.topControl = null;
		}else{
			AbstractDetailsSubsection<? extends T> ss = layers.get(key);
			ss.selectionChanged(event);
			stackLayout.topControl = ss.getContentPane();
		}
		contentPane.layout();
	}
	@Override
	public void setLayoutData(Object data){
		contentPane.setLayoutData(data);
		for(AbstractDetailsSubsection<? extends T> d:delegates){
			d.setLayoutData(null);
			d.getContentPane().layout();
		}
	}
	@Override
	public void dispose(){
		contentPane.dispose();
		for(AbstractDetailsSubsection<? extends T> d:delegates){
			d.dispose();
		}
	}
	public Composite getContentPane(){
		return contentPane;
	}
}
