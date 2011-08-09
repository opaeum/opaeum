package org.nakeduml.uim.userinteractionproperties.sections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.uml2.uml.Classifier;
import org.nakeduml.uim.action.ActionPackage;
import org.nakeduml.uim.action.NavigationToEntity;
import org.nakeduml.uim.action.provider.ActionItemProviderAdapterFactory;
import org.nakeduml.uim.form.ClassForm;
import org.nakeduml.uim.modeleditor.editor.UimEditor;
import org.topcased.tabbedproperties.AbstractTabbedPropertySheetPage;
import org.topcased.tabbedproperties.providers.TabbedPropertiesLabelProvider;
import org.topcased.tabbedproperties.sections.AbstractChooserPropertySection;
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
public class NavigationToEntityToFormSection extends AbstractChooserPropertySection{
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
		return ActionPackage.eINSTANCE.getNavigationToEntity_ToForm();
	}
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 *
	 * @see org.topcased.tabbedproperties.sections.AbstractChooserPropertySection#getFeatureValue()
	 * @generated
	 */
	protected Object getFeatureValue(){
		return ((NavigationToEntity) getEObject()).getToForm();
	}
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 *
	 * @see org.topcased.tabbedproperties.sections.AbstractChooserPropertySection#getComboFeatureValues()
	 * @generated NOT
	 */
	protected Object[] getComboFeatureValues(){
		NavigationToEntity nte = (NavigationToEntity) getEObject();
		Classifier toClass = UimEditor.getCurrentUmlLinks().getType(nte.getBinding());
		if(toClass == null){
			toClass = UimEditor.getCurrentUmlLinks().getNearestClass(nte);
		}
		List<ClassForm> choices = new ArrayList<ClassForm>();
		ITypeCacheAdapter tca = TypeCacheAdapter.getExistingTypeCacheAdapter(getEObject());
		Collection<? extends ClassForm> source = (Collection<? extends ClassForm>) tca.getReachableObjectsOfType(getEObject(), ActionPackage.eINSTANCE
				.getNavigationToEntity_ToForm().getEType());
		for(ClassForm classForm:source){
			if(UimEditor.getCurrentUmlLinks().getRepresentedClass(classForm.getFolder()) == toClass){
				choices.add(classForm);
			}
		}
		return choices.toArray();
	}
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 *
	 * @see org.topcased.tabbedproperties.sections.AbstractChooserPropertySection#getLabelProvider()
	 * @generated
	 */
	protected ILabelProvider getLabelProvider(){
		List f = new ArrayList();
		f.add(new ActionItemProviderAdapterFactory());
		f.addAll(AbstractTabbedPropertySheetPage.getPrincipalAdapterFactories());
		return new TabbedPropertiesLabelProvider(new ComposedAdapterFactory(f));
	}
}
