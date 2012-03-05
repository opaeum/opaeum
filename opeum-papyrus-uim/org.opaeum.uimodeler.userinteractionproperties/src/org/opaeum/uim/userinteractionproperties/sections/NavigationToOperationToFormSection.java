package org.opaeum.uim.userinteractionproperties.sections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.uml2.uml.Operation;
import org.opaeum.uim.action.ActionPackage;
import org.opaeum.uim.action.NavigationToOperation;
import org.opaeum.uim.action.provider.ActionItemProviderAdapterFactory;
import org.opaeum.uim.editor.AbstractEditor;
import org.opaeum.uim.editor.QueryInvocationEditor;
import org.opaeum.uim.util.UmlUimLinks;
import org.topcased.tabbedproperties.AbstractTabbedPropertySheetPage;
import org.topcased.tabbedproperties.providers.TabbedPropertiesLabelProvider;
import org.topcased.tabbedproperties.utils.ITypeCacheAdapter;
import org.topcased.tabbedproperties.utils.TypeCacheAdapter;

/**
 * A section featuring a combo box with a seach button. This section<br>
 * displays single references.
 *
 * <!-- begin-user-doc --> <!-- end-user-doc -->
 *
 * @generated
 */
public class NavigationToOperationToFormSection extends OpaeumChooserPropertySection{
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 *
	 * @see org.topcased.tabbedproperties.sections.AbstractTabbedPropertySection#getLabelText()
	 * @generated
	 */
	protected String getLabelText(){
		return "ToForm:";
	}
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 *
	 * @see org.topcased.tabbedproperties.sections.AbstractTabbedPropertySection#getFeature()
	 * @generated
	 */
	protected EStructuralFeature getFeature(){
		return ActionPackage.eINSTANCE.getLinkToOperation_ToForm();
	}
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 *
	 * @see org.topcased.tabbedproperties.sections.OpaeumChooserPropertySection#getFeatureValue()
	 * @generated
	 */
	protected Object getFeatureValue(){
		return ((NavigationToOperation) getEObject()).getToForm();
	}
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.topcased.tabbedproperties.sections.OpaeumChooserPropertySection#getComboFeatureValues()
	 * @generated NOT
	 */
	protected Object[] getComboFeatureValues(){
		NavigationToOperation on = (NavigationToOperation) getEObject();
		AbstractEditor ui = UmlUimLinks.getCurrentUmlLinks().getNearestForm(on);
		//TODO check if parent is table first
		ITypeCacheAdapter typeCacheAdapter = TypeCacheAdapter.getExistingTypeCacheAdapter(getEObject());
		Collection<EObject> population = typeCacheAdapter.getReachableObjectsOfType(getEObject(), ActionPackage.eINSTANCE.getLinkToOperation_ToForm().getEType());
		List<Operation> validOperations = UmlUimLinks.getCurrentUmlLinks().getValidOperationsFor(ui);
		List<QueryInvocationEditor> results = new ArrayList<QueryInvocationEditor>();
		for(EObject eObject:population){
			QueryInvocationEditor form = (QueryInvocationEditor) eObject;
			if(validOperations.contains(UmlUimLinks.getCurrentUmlLinks().getOperation(form))){
				results.add(form);
			}
		}
		return (QueryInvocationEditor[]) results.toArray(new QueryInvocationEditor[results.size()]);
	}
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 *
	 * @see org.topcased.tabbedproperties.sections.OpaeumChooserPropertySection#getLabelProvider()
	 * @generated
	 */
	protected ILabelProvider getLabelProvider(){
		List f = new ArrayList();
		f.add(new ActionItemProviderAdapterFactory());
		f.addAll(AbstractTabbedPropertySheetPage.getPrincipalAdapterFactories());
		return new TabbedPropertiesLabelProvider(new ComposedAdapterFactory(f));
	}
}
