package org.opaeum.uim.userinteractionproperties.sections;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.opaeum.uim.UimDataTable;
import org.opaeum.uim.UimPackage;
import org.opaeum.uim.UmlReference;
import org.opaeum.uim.UserInterface;
import org.opaeum.uim.action.ActionKind;
import org.opaeum.uim.action.ActionPackage;
import org.opaeum.uim.action.BuiltInActionButton;
import org.opaeum.uim.action.OperationPopup;
import org.opaeum.uim.editor.ActionTaskEditor;
import org.opaeum.uim.editor.ClassEditor;
import org.opaeum.uim.editor.QueryInvocationEditor;
import org.opaeum.uim.editor.ResponsibilityTaskEditor;
import org.opaeum.uim.userinteractionproperties.core.AbstractEnumerationPropertySection;
import org.opaeum.uim.util.UmlUimLinks;

public class BuiltInActionButtonKindSection extends AbstractEnumerationPropertySection{
	protected String getLabelText(){
		return "Kind:";
	}
	protected EStructuralFeature getFeature(){
		return ActionPackage.eINSTANCE.getBuiltInActionButton_Kind();
	}
	protected String[] getEnumerationFeatureValues(){
		BuiltInActionButton action = (BuiltInActionButton) getEObject();
		if(action.eContainer() instanceof UimDataTable){
			if(action.eContainmentFeature().equals(UimPackage.eINSTANCE.getUimContainer_Children())){
				return new String[]{ActionKind.DELETE.getName()};
			}else{
				return new String[]{ActionKind.ADD.getName(),ActionKind.DELETE.getName()};
			}
		}else{
			UmlReference uf = UmlUimLinks.getNearestForm(action);
			if(uf.eContainer() instanceof ResponsibilityTaskEditor || uf.eContainer() instanceof ActionTaskEditor){
				return new String[]{ActionKind.SUSPEND_TASK.getName(),ActionKind.COMPLETE_TASK.getName(),ActionKind.DELEGATE_TASK.getName()};
			}else if(uf.eContainer() instanceof ClassEditor || uf instanceof ClassEditor){
				return new String[]{ActionKind.UPDATE.getName(),ActionKind.DELETE.getName(),ActionKind.REFRESH.getName()};
			}else if(uf.eContainer() instanceof OperationPopup || uf.eContainer() instanceof QueryInvocationEditor){
				return new String[]{ActionKind.EXECUTE_OPERATION.getName()};
			}else{
				return new String[0];
			}
		}
	}
	protected String getFeatureAsText(){
		return getAction().getKind().getName();
	}
	protected Object getOldFeatureValue(){
		return getAction().getKind();
	}
	private BuiltInActionButton getAction(){
		return (BuiltInActionButton) getEObject();
	}
	@Override
	protected Object getFeatureValue(String name){
		return ActionKind.getByName(name);
	}
}