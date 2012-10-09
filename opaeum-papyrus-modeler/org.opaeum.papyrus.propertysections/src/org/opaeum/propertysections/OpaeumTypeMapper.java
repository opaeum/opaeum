package org.opaeum.propertysections;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.facet.infra.browser.uicore.internal.model.ModelElementItem;
import org.eclipse.gef.EditPart;
import org.eclipse.ui.views.properties.tabbed.AbstractTypeMapper;

public class OpaeumTypeMapper extends AbstractTypeMapper{
	@Override
	public Class mapType(Object input){
		Class type = super.mapType(input);
		if(input instanceof EditPart){
			Object tmpObj = ((EditPart) input).getModel();
			if(tmpObj instanceof EObject){
				type = getEObjectType((EObject) tmpObj);
			}else{
				type = tmpObj.getClass();
			}
		}else if(input instanceof ModelElementItem){
			type = getEObjectType(((ModelElementItem) input).getEObject());
		}else if(input instanceof EObject){
			type =getEObjectType((EObject) input);
		}
		return type;
	}
	protected Class getEObjectType(EObject eObj){
		return eObj.getClass();
	}
}
