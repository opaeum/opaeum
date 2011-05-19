package org.nakeduml.uim.userinteractionproperties.sections;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.TypedElement;
import org.nakeduml.uim.ActionTaskForm;
import org.nakeduml.uim.OperationInvocationForm;
import org.nakeduml.uim.OperationTaskForm;
import org.nakeduml.uim.StateForm;
import org.nakeduml.uim.UIMBinding;
import org.nakeduml.uim.UIMComponent;
import org.nakeduml.uim.UIMDataTable;
import org.nakeduml.uim.UIMForm;
import org.nakeduml.uim.UIMPackage;
import org.nakeduml.uim.provider.UIMItemProviderAdapterFactory;
import org.nakeduml.uim.util.UimUtil;
import org.topcased.tabbedproperties.AbstractTabbedPropertySheetPage;
import org.topcased.tabbedproperties.providers.TabbedPropertiesLabelProvider;
import org.topcased.tabbedproperties.sections.AbstractChooserPropertySection;

/**
 * A section featuring a combo box with a seach button. This section<br>
 * displays single references.
 *
 * <!-- begin-user-doc --> <!-- end-user-doc -->
 *
 * @generated NOT
 */
public class UIMBindingElementSection extends AbstractChooserPropertySection{
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 *
	 * @see org.topcased.tabbedproperties.sections.AbstractTabbedPropertySection#getLabelText()
	 * @generated
	 */
	protected String getLabelText(){
		return "Element:";
	}
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 *
	 * @see org.topcased.tabbedproperties.sections.AbstractTabbedPropertySection#getFeature()
	 * @generated
	 */
	protected EStructuralFeature getFeature(){
		return UIMPackage.eINSTANCE.getUIMBinding_Element();
	}
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 *
	 * @see org.topcased.tabbedproperties.sections.AbstractChooserPropertySection#getFeatureValue()
	 * @generated
	 */
	protected Object getFeatureValue(){
		return ((UIMBinding) getEObject()).getElement();
	}
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.topcased.tabbedproperties.sections.AbstractChooserPropertySection#getComboFeatureValues()
	 * @generated NOT
	 */
	protected Object[] getComboFeatureValues(){
		List<TypedElement> attrs = new ArrayList<TypedElement>();
		UIMBinding uIMBinding = (UIMBinding) getEObject();
		UIMComponent component = UimUtil.getComponent(uIMBinding);
		UIMForm uf = UimUtil.getNearestForm(component);
		Classifier representedClass = UimUtil.getNearestClass(component);
		UIMDataTable nearestTable = UimUtil.getNearestTable(component);
		if(representedClass != null){
			attrs.addAll(representedClass.getAllAttributes());
		}
		if(nearestTable == null){
			if(uf instanceof OperationInvocationForm){
				OperationInvocationForm oif = (OperationInvocationForm) uf;
				if(oif.getOperation() != null){
					attrs.addAll(oif.getOperation().getOwnedParameters());
				}
			}else if(uf instanceof OperationTaskForm){
				OperationTaskForm oif = (OperationTaskForm) uf;
				if(oif.getOperation() != null){
					attrs.addAll(oif.getOperation().getOwnedParameters());
				}
			}else if(uf instanceof ActionTaskForm){
				ActionTaskForm oif = (ActionTaskForm) uf;
				if(oif.getAction() != null){
					attrs.addAll(oif.getAction().getInputs());
					attrs.addAll(oif.getAction().getOutputs());
				}
			}else if(uf instanceof StateForm){
				StateForm sf = (StateForm) uf;
				if(sf.getFolder().getStateMachine() != null){
					attrs.addAll(sf.getFolder().getStateMachine().getOwnedParameters());
				}
			}
		}
		return attrs.toArray();
	}
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 *
	 * @see org.topcased.tabbedproperties.sections.AbstractChooserPropertySection#getLabelProvider()
	 * @generated
	 */
	protected ILabelProvider getLabelProvider(){
		List f = new ArrayList();
		f.add(new UIMItemProviderAdapterFactory());
		f.addAll(AbstractTabbedPropertySheetPage.getPrincipalAdapterFactories());
		return new TabbedPropertiesLabelProvider(new ComposedAdapterFactory(f));
	}
}
