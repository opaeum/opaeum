package org.opaeum.uim.userinteractionproperties.sections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.uml2.uml.Operation;
import org.opaeum.topcased.propertysections.OpaeumChooserPropertySection;
import org.opaeum.uim.action.OperationAction;
import org.opaeum.uim.provider.UimItemProviderAdapterFactory;
import org.opaeum.uim.util.UmlUimLinks;
import org.topcased.tabbedproperties.AbstractTabbedPropertySheetPage;
import org.topcased.tabbedproperties.providers.TabbedPropertiesLabelProvider;

/**
 * A section featuring a combo box with a seach button. This section<br>
 * displays single references.
 * 
 * <!-- begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 */
public class OperationActionOperationSection extends OpaeumChooserPropertySection{
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.topcased.tabbedproperties.sections.AbstractTabbedPropertySection#getLabelText()
	 * @generated
	 */
	protected String getLabelText(){
		return "Operation:";
	}
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.topcased.tabbedproperties.sections.AbstractTabbedPropertySection#getFeature()
	 * @generated
	 */
	protected EStructuralFeature getFeature(){
		return null;
	}
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.topcased.tabbedproperties.sections.OpaeumChooserPropertySection#getFeatureValue()
	 * @generated
	 */
	protected Object getFeatureValue(){
		return UmlUimLinks.getCurrentUmlLinks(getOperationAction()).getOperation(getOperationAction());
	}
	private OperationAction getOperationAction(){
		return (OperationAction) getEObject();
	}
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.topcased.tabbedproperties.sections.OpaeumChooserPropertySection#getComboFeatureValues()
	 * @generated NOT
	 */
	protected Object[] getComboFeatureValues(){
		Collection<Operation> results = new ArrayList<Operation>();
		if(getEObject() instanceof OperationAction){
			OperationAction oa = getOperationAction();
			results.addAll(UmlUimLinks.getCurrentUmlLinks(oa).getNearestClass(oa).getAllOperations());
		}
		return getParameterlessOperations(results);
	}
	private Object[] getParameterlessOperations(Collection<Operation> opers){
		List<Operation> results = new ArrayList<Operation>();
		for(Operation operation:opers){
			if(operation.getOwnedParameters().isEmpty()){
				results.add(operation);
			}
		}
		return (Operation[]) results.toArray(new Operation[results.size()]);
	}
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.topcased.tabbedproperties.sections.OpaeumChooserPropertySection#getLabelProvider()
	 * @generated
	 */
	protected ILabelProvider getLabelProvider(){
		List f = new ArrayList();
		f.add(new UimItemProviderAdapterFactory());
		f.addAll(AbstractTabbedPropertySheetPage.getPrincipalAdapterFactories());
		return new TabbedPropertiesLabelProvider(new ComposedAdapterFactory(f));
	}
}
