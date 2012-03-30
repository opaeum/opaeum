package org.opaeum.uim.userinteractionproperties.sections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ListIterator;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.jface.viewers.ILabelProvider;
import org.opaeum.topcased.propertysections.OpaeumChooserPropertySection;
import org.opaeum.uim.DetailComponent;
import org.opaeum.uim.MasterComponent;
import org.opaeum.uim.UimComponent;
import org.opaeum.uim.UimPackage;
import org.opaeum.uim.provider.UimItemProviderAdapterFactory;
import org.opaeum.uim.util.UmlUimLinks;
import org.topcased.tabbedproperties.AbstractTabbedPropertySheetPage;
import org.topcased.tabbedproperties.providers.TabbedPropertiesLabelProvider;
import org.topcased.tabbedproperties.utils.ITypeCacheAdapter;
import org.topcased.tabbedproperties.utils.TypeCacheAdapter;

public class DetailComponentMasterComponentSection extends OpaeumChooserPropertySection{
	protected String getLabelText(){
		return "MasterTable:";
	}
	protected EStructuralFeature getFeature(){
		return UimPackage.eINSTANCE.getDetailComponent_MasterComponent();
	}
	protected Object getFeatureValue(){
		return getDetailComponent().getMasterComponent();
	}
	private DetailComponent getDetailComponent(){
		return (DetailComponent) getEObject();
	}
	protected Object[] getComboFeatureValues(){
		List<MasterComponent> choices = new ArrayList<MasterComponent>();
		ITypeCacheAdapter tca = TypeCacheAdapter.getExistingTypeCacheAdapter(getEObject());
		choices.addAll((Collection) tca.getReachableObjectsOfType(getEObject(), UimPackage.eINSTANCE.getDetailComponent_MasterComponent()
				.getEType()));
		ListIterator<MasterComponent> li = choices.listIterator();
		while(li.hasNext()){
			if(UmlUimLinks.getCurrentUmlLinks(getDetailComponent()).getNearestForm((UimComponent) li.next()) != UmlUimLinks.getCurrentUmlLinks(getDetailComponent()).getNearestForm(
					(UimComponent) getEObject())){
				li.remove();
			}
		}
		return choices.toArray();
	}
	protected ILabelProvider getLabelProvider(){
		List f = new ArrayList();
		f.add(new UimItemProviderAdapterFactory());
		f.addAll(AbstractTabbedPropertySheetPage.getPrincipalAdapterFactories());
		return new TabbedPropertiesLabelProvider(new ComposedAdapterFactory(f));
	}
}