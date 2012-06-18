package org.opaeum.papyrus.classdiagram.editparts;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.diagram.core.preferences.PreferencesHint;
import org.eclipse.gmf.runtime.diagram.core.providers.IViewProvider;
import org.eclipse.gmf.runtime.diagram.core.util.ViewUtil;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.NotationFactory;
import org.eclipse.gmf.runtime.notation.Shape;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.papyrus.uml.diagram.clazz.edit.parts.DataTypeAttributeCompartmentEditPart;
import org.eclipse.papyrus.uml.diagram.clazz.edit.parts.EnumerationEditPart;
import org.eclipse.papyrus.uml.diagram.clazz.edit.parts.EnumerationEnumerationLiteralCompartmentEditPart;
import org.eclipse.papyrus.uml.diagram.clazz.edit.parts.EnumerationNameEditPart;
import org.eclipse.papyrus.uml.diagram.clazz.part.UMLVisualIDRegistry;
import org.eclipse.papyrus.uml.diagram.common.helper.PreferenceInitializerForElementHelper;

public class UMLViewProvider extends org.eclipse.papyrus.uml.diagram.clazz.providers.UMLViewProvider implements IViewProvider{
	@Override
	public Node createEnumeration_2006(EObject domainElement,View containerView,int index,boolean persisted,PreferencesHint preferencesHint){
		Shape node = NotationFactory.eINSTANCE.createShape();
		node.setLayoutConstraint(NotationFactory.eINSTANCE.createBounds());
		node.setType(UMLVisualIDRegistry.getType(EnumerationEditPart.VISUAL_ID));
		ViewUtil.insertChildView(containerView, node, index, persisted);
		node.setElement(domainElement);
		stampShortcut(containerView, node);
		// initializeFromPreferences
		final IPreferenceStore prefStore = (IPreferenceStore) preferencesHint.getPreferenceStore();
		PreferenceInitializerForElementHelper.initForegroundFromPrefs(node, prefStore, "Enumeration");
		PreferenceInitializerForElementHelper.initFontStyleFromPrefs(node, prefStore, "Enumeration");
		PreferenceInitializerForElementHelper.initBackgroundFromPrefs(node, prefStore, "Enumeration");
		Node label5023 = createLabel(node, UMLVisualIDRegistry.getType(EnumerationNameEditPart.VISUAL_ID));
		createCompartment(node, UMLVisualIDRegistry.getType(EnumerationEnumerationLiteralCompartmentEditPart.VISUAL_ID), true, true, true, true);
		createCompartment(node, UMLVisualIDRegistry.getType(DataTypeAttributeCompartmentEditPart.VISUAL_ID), true, true, true, true);
		PreferenceInitializerForElementHelper.initCompartmentsStatusFromPrefs(node, prefStore, "Enumeration");
		EList<View> visibleChildren = node.getChildren();
		for(View object:visibleChildren){
			if(object.getType().equals(""+DataTypeAttributeCompartmentEditPart.VISUAL_ID)){
				object.setVisible(true);
				object.getChildren();
			}
		}
		return node;
	}
}
