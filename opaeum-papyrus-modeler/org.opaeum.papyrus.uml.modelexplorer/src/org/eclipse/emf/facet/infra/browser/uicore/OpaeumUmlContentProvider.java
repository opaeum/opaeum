package org.eclipse.emf.facet.infra.browser.uicore;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.facet.infra.browser.custom.MetamodelView;
import org.eclipse.emf.facet.infra.browser.custom.TypeView;
import org.eclipse.emf.facet.infra.browser.uicore.internal.AppearanceConfiguration;
import org.eclipse.emf.facet.infra.browser.uicore.internal.model.ITreeElement;
import org.eclipse.emf.facet.infra.browser.uicore.internal.model.ItemsFactory;
import org.eclipse.emf.facet.infra.browser.uicore.internal.model.ModelElementItem;
import org.eclipse.emf.facet.infra.facet.Facet;
import org.eclipse.emf.facet.infra.facet.FacetSet;
import org.eclipse.emf.facet.infra.facet.core.FacetSetCatalog;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.papyrus.infra.core.resource.ModelSet;
import org.eclipse.papyrus.infra.emf.Activator;
import org.eclipse.papyrus.uml.modelexplorer.OpaeumModelElementItem;

public class OpaeumUmlContentProvider extends org.eclipse.papyrus.uml.modelexplorer.UMLContentProvider implements ITreeContentProvider{
	private AppearanceConfiguration appearanceConfiguration;
	public OpaeumUmlContentProvider(){
		super();
		appearanceConfiguration = new AppearanceConfiguration(new ItemsFactory(){
			@Override
			public ModelElementItem createModelElementItem(EObject eObject,ITreeElement treeParent,AppearanceConfiguration appearanceConfiguration){
				// TODO Auto-generated method stub
				return new OpaeumModelElementItem(eObject, treeParent, appearanceConfiguration);
			}
		});
		appearanceConfiguration.createCustomizationEngine();
		CustomizationManager cm = org.eclipse.papyrus.infra.emf.Activator.getDefault().getCustomizationManager();
		List<MetamodelView> registeredCustomizations = cm.getRegisteredCustomizations();
		for(MetamodelView metamodelView:registeredCustomizations){
			appearanceConfiguration.getCustomizationEngine().registerCustomization(metamodelView);
		}
		final Set<Facet> referencedFacets = new HashSet<Facet>();
		final Collection<FacetSet> facetSets = FacetSetCatalog.getSingleton().getAllFacetSets();
		for(MetamodelView customization:registeredCustomizations){
			String metamodelURI = customization.getMetamodelURI();
			// find customized FacetSet
			FacetSet customizedFacetSet = null;
			if(metamodelURI != null){
				for(FacetSet facetSet:facetSets){
					if(metamodelURI.equals(facetSet.getNsURI())){
						customizedFacetSet = facetSet;
						break;
					}
				}
			}
			if(customizedFacetSet == null){
				continue;
			}
			// find customized Facets
			EList<TypeView> types = customization.getTypes();
			for(TypeView typeView:types){
				String metaclassName = typeView.getMetaclassName();
				Facet facet = findFacetWithFullyQualifiedName(metaclassName, customizedFacetSet);
				if(facet != null){
					referencedFacets.add(facet);
				}else{
					Activator.log.warn(String.format("Missing required facet \"%s\" in FacetSet \"%s\" for customization \"%s\"", metaclassName,
							customizedFacetSet.getName(), customization.getName()));
				}
			}
			for(Facet referencedFacet:referencedFacets){
				appearanceConfiguration.loadFacet(referencedFacet);
			}
		}
		appearanceConfiguration.getCustomizationEngine().loadCustomizations();
	}
	@Override
	protected EObject[] getRootElements(ModelSet modelSet){
		return super.getRootElements(modelSet);
	}
	public Object[] getElements(final Object inputElement){
		Object[] rootElements = getRootElements(inputElement);
		if(rootElements == null){
			return null;
		}
		List<Object> result = new ArrayList<Object>();
		for(Object element:rootElements){
			if(element instanceof EObject){
				EObject eObject = (EObject) element;
				result.add(new ModelElementItem(eObject, null, this.appearanceConfiguration));
			}else{
				result.add(element);
			}
		}
		return result.toArray();
	}
	private Facet findFacetWithFullyQualifiedName(final String metaclassName,final FacetSet customizedFacetSet){
		EList<Facet> facets = customizedFacetSet.getFacets();
		for(Facet facet:facets){
			String facetName = getMetaclassQualifiedName(facet);
			if(metaclassName.equals(facetName)){
				return facet;
			}
		}
		return null;
	}
	public static String getMetaclassQualifiedName(final EClassifier eClass){
		final ArrayList<String> qualifiedNameParts = new ArrayList<String>();
		final StringBuilder builder = new StringBuilder();
		EPackage ePackage = eClass.getEPackage();
		while(ePackage != null){
			qualifiedNameParts.add(ePackage.getName());
			ePackage = ePackage.getESuperPackage();
		}
		for(int i = qualifiedNameParts.size() - 1;i >= 0;i--){
			builder.append(qualifiedNameParts.get(i) + "."); //$NON-NLS-1$
		}
		builder.append(eClass.getName());
		return builder.toString();
	}
}
