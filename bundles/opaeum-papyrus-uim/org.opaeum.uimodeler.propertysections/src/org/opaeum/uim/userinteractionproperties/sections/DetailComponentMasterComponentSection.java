package org.opaeum.uim.userinteractionproperties.sections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ListIterator;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.opaeum.eclipse.context.OpaeumEclipseContext;
import org.opaeum.eclipse.uml.propertysections.base.AbstractChooserPropertySection;
import org.opaeum.uim.component.ComponentPackage;
import org.opaeum.uim.component.DetailComponent;
import org.opaeum.uim.component.MasterComponent;
import org.opaeum.uim.component.UimComponent;
import org.opaeum.uim.provider.UimItemProviderAdapterFactory;
import org.opaeum.uim.util.UmlUimLinks;

public class DetailComponentMasterComponentSection extends AbstractChooserPropertySection{
	public String getLabelText(){
		return "MasterTable:";
	}
	protected EStructuralFeature getFeature(){
		return ComponentPackage.eINSTANCE.getDetailComponent_MasterComponent();
	}
	protected Object getFeatureValue(){
		return getDetailComponent().getMasterComponent();
	}
	private DetailComponent getDetailComponent(){
		return (DetailComponent) getSelectedObject();
	}
	@SuppressWarnings({"unchecked","rawtypes"})
	protected Object[] getComboFeatureValues(){
		List<MasterComponent> choices = new ArrayList<MasterComponent>();
		choices.addAll((Collection) OpaeumEclipseContext.getReachableObjectsOfType(getSelectedObject(), (EClass) ComponentPackage.eINSTANCE.getDetailComponent_MasterComponent()
				.getEType()));
		ListIterator<MasterComponent> li = choices.listIterator();
		while(li.hasNext()){
			if(UmlUimLinks.getNearestUserInterfaceRoot((UimComponent) li.next()) != UmlUimLinks.getNearestUserInterfaceRoot(
					(UimComponent) getSelectedObject())){
				li.remove();
			}
		}
		return choices.toArray();
	}
	protected ILabelProvider getLabelProvider(){
		List<AdapterFactory> f = new ArrayList<AdapterFactory>();
		f.add(new UimItemProviderAdapterFactory());
		f.addAll(getPrincipalAdapterFactories());
		return new AdapterFactoryLabelProvider(new ComposedAdapterFactory(f));
	}
}
