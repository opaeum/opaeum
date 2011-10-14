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
import org.opaeum.uim.form.FormPanel;
import org.opaeum.uim.form.OperationInvocationForm;
import org.opaeum.uim.modeleditor.editor.UimEditor;
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
		return ActionPackage.eINSTANCE.getNavigationToOperation_ToForm();
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
		FormPanel ui = UimEditor.getCurrentUmlLinks().getNearestForm(on);
		//TODO check if parent is table first
		ITypeCacheAdapter typeCacheAdapter = TypeCacheAdapter.getExistingTypeCacheAdapter(getEObject());
		Collection<EObject> population = typeCacheAdapter.getReachableObjectsOfType(getEObject(), ActionPackage.eINSTANCE.getNavigationToOperation_ToForm().getEType());
		List<Operation> validOperations = UimEditor.getCurrentUmlLinks().getValidOperationsFor(ui);
		List<OperationInvocationForm> results = new ArrayList<OperationInvocationForm>();
		for(EObject eObject:population){
			OperationInvocationForm form = (OperationInvocationForm) eObject;
			if(validOperations.contains(UimEditor.getCurrentUmlLinks().getOperation(form))){
				results.add(form);
			}
		}
		return (OperationInvocationForm[]) results.toArray(new OperationInvocationForm[results.size()]);
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
