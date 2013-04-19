package org.opaeum.papyrus.uml.modelexplorer;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.facet.infra.browser.uicore.internal.AppearanceConfiguration;
import org.eclipse.emf.facet.infra.browser.uicore.internal.model.ITreeElement;
import org.eclipse.emf.facet.infra.browser.uicore.internal.model.ModelElementItem;

public class OpaeumModelElementItem extends ModelElementItem{

	public OpaeumModelElementItem(EObject eObject,ITreeElement treeParent,AppearanceConfiguration appearanceConfiguration){
		super(eObject, treeParent, appearanceConfiguration);
	}
	@Override
	public List<Object> getChildren(){
		// TODO Auto-generated method stub
		return super.getChildren();
	}
}
