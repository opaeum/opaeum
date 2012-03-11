package org.opaeum.uim.userinteractionproperties.sections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.uml2.uml.Operation;
import org.opaeum.topcased.propertysections.OpaeumChooserPropertySection;
import org.opaeum.uim.UserInterface;
import org.opaeum.uim.action.ActionPackage;
import org.opaeum.uim.action.LinkToOperation;
import org.opaeum.uim.action.provider.ActionItemProviderAdapterFactory;
import org.opaeum.uim.editor.AbstractEditor;
import org.opaeum.uim.editor.QueryInvocationEditor;
import org.opaeum.uim.util.UmlUimLinks;
import org.topcased.tabbedproperties.AbstractTabbedPropertySheetPage;
import org.topcased.tabbedproperties.providers.TabbedPropertiesLabelProvider;
import org.topcased.tabbedproperties.utils.ITypeCacheAdapter;
import org.topcased.tabbedproperties.utils.TypeCacheAdapter;

public class LinkToOperationToFormSection extends OpaeumChooserPropertySection{
	protected String getLabelText(){
		return "ToForm:";
	}
	protected EStructuralFeature getFeature(){
		return ActionPackage.eINSTANCE.getLinkToOperation_ToForm();
	}
	protected Object getFeatureValue(){
		return getLinkToQuery().getToForm();
	}
	protected Object[] getComboFeatureValues(){
		LinkToOperation on = getLinkToQuery();
		UserInterface ui = UmlUimLinks.getCurrentUmlLinks(on).getNearestForm(on);
		//TODO check if parent is table first
		ITypeCacheAdapter typeCacheAdapter = TypeCacheAdapter.getExistingTypeCacheAdapter(getEObject());
		Collection<EObject> population = typeCacheAdapter.getReachableObjectsOfType(getEObject(), ActionPackage.eINSTANCE.getLinkToOperation_ToForm().getEType());
		List<Operation> validOperations = UmlUimLinks.getCurrentUmlLinks(getLinkToQuery()).getValidOperationsFor(ui);
		List<QueryInvocationEditor> results = new ArrayList<QueryInvocationEditor>();
		for(EObject eObject:population){
			QueryInvocationEditor form = (QueryInvocationEditor) eObject;
			if(validOperations.contains(UmlUimLinks.getCurrentUmlLinks(getLinkToQuery()).getOperation(form))){
				results.add(form);
			}
		}
		return (QueryInvocationEditor[]) results.toArray(new QueryInvocationEditor[results.size()]);
	}
	private LinkToOperation getLinkToQuery(){
		return (LinkToOperation) getEObject();
	}
	protected ILabelProvider getLabelProvider(){
		List f = new ArrayList();
		f.add(new ActionItemProviderAdapterFactory());
		f.addAll(AbstractTabbedPropertySheetPage.getPrincipalAdapterFactories());
		return new TabbedPropertiesLabelProvider(new ComposedAdapterFactory(f));
	}
}
