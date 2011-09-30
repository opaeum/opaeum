package org.opeum.uim.userinteractionproperties.sections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ListIterator;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.jface.viewers.ILabelProvider;
import org.opeum.uim.MasterComponent;
import org.opeum.uim.UimComponent;
import org.opeum.uim.form.DetailPanel;
import org.opeum.uim.form.FormPackage;
import org.opeum.uim.modeleditor.editor.UimEditor;
import org.opeum.uim.provider.UimItemProviderAdapterFactory;
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
public class DetailPanelMasterTableSection extends
		AbstractChooserPropertySection {
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 *
	 * @see org.topcased.tabbedproperties.sections.AbstractTabbedPropertySection#getLabelText()
	 * @generated
	 */
	protected String getLabelText() {
		return "MasterTable:";
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 *
	 * @see org.topcased.tabbedproperties.sections.AbstractTabbedPropertySection#getFeature()
	 * @generated
	 */
	protected EStructuralFeature getFeature() {
		return FormPackage.eINSTANCE.getDetailPanel_MasterComponent();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 *
	 * @see org.topcased.tabbedproperties.sections.AbstractChooserPropertySection#getFeatureValue()
	 * @generated
	 */
	protected Object getFeatureValue() {
		return ((DetailPanel) getEObject()).getMasterComponent();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.topcased.tabbedproperties.sections.AbstractChooserPropertySection#getComboFeatureValues()
	 * @generated NOT
	 */
	protected Object[] getComboFeatureValues() {
		List<MasterComponent> choices = new ArrayList<MasterComponent>();
		ITypeCacheAdapter tca = TypeCacheAdapter
				.getExistingTypeCacheAdapter(getEObject());
		choices.addAll((Collection) tca.getReachableObjectsOfType(getEObject(),
				FormPackage.eINSTANCE.getDetailPanel_MasterComponent().getEType()));
		ListIterator<MasterComponent> li = choices.listIterator();
		while (li.hasNext()) {
			if (UimEditor.getCurrentUmlLinks().getNearestForm((UimComponent) li.next()) != UimEditor.getCurrentUmlLinks()
					.getNearestForm((UimComponent) getEObject())) {
				li.remove();
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
	protected ILabelProvider getLabelProvider() {
		List f = new ArrayList();
		f.add(new UimItemProviderAdapterFactory());
		f
				.addAll(AbstractTabbedPropertySheetPage
						.getPrincipalAdapterFactories());
		return new TabbedPropertiesLabelProvider(new ComposedAdapterFactory(f));
	}

}
