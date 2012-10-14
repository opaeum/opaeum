package org.opaeum.uim.userinteractionproperties.sections;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.uml2.uml.Behavior;
import org.opaeum.eclipse.EmfBehaviorUtil;
import org.opaeum.eclipse.uml.propertysections.base.AbstractEnumerationPropertySection;
import org.opaeum.uim.UmlReference;
import org.opaeum.uim.action.ActionKind;
import org.opaeum.uim.action.ActionPackage;
import org.opaeum.uim.action.BuiltInActionButton;
import org.opaeum.uim.component.ComponentPackage;
import org.opaeum.uim.component.UimDataTable;
import org.opaeum.uim.editor.BehaviorExecutionEditor;
import org.opaeum.uim.editor.InstanceEditor;
import org.opaeum.uim.model.EmbeddedTaskEditor;
import org.opaeum.uim.model.OperationInvocationWizard;
import org.opaeum.uim.model.QueryInvoker;
import org.opaeum.uim.util.UmlUimLinks;

public class BuiltInActionButtonKindSection extends AbstractEnumerationPropertySection{
	public String getLabelText(){
		return "Kind:";
	}
	protected EStructuralFeature getFeature(){
		return ActionPackage.eINSTANCE.getBuiltInActionButton_Kind();
	}
	protected String[] getEnumerationFeatureValues(){
		BuiltInActionButton action = (BuiltInActionButton) getEObject();
		if(action.eContainer() instanceof UimDataTable){
			if(action.eContainmentFeature().equals(ComponentPackage.eINSTANCE.getUimContainer_Children())){
				return new String[]{ActionKind.DELETE.getName()};
			}else{
				return new String[]{ActionKind.ADD.getName(),ActionKind.DELETE.getName()};
			}
		}else{
			UmlReference uf = UmlUimLinks.getNearestForm(action);
			if(uf.eContainer() instanceof BehaviorExecutionEditor){
				Behavior b = UmlUimLinks.getCurrentUmlLinks(uf.eContainer()).getBehavior((BehaviorExecutionEditor)uf.eContainer());
				if(EmfBehaviorUtil.isStandaloneTask(b)){
					return new String[]{ActionKind.SUSPEND.getName(),ActionKind.COMPLETE_TASK.getName(),ActionKind.DELEGATE_TASK.getName(),ActionKind.SKIP.getName() ,ActionKind.ABORT.getName()};
				}else if(EmfBehaviorUtil.isProcess(b)){
					return new String[]{ActionKind.SUSPEND.getName(),ActionKind.ABORT.getName()};
				}else{
					return new String[0];
				}
			}else if(uf.eContainer() instanceof EmbeddedTaskEditor){ 
				return new String[]{ActionKind.SUSPEND.getName(),ActionKind.COMPLETE_TASK.getName(),ActionKind.DELEGATE_TASK.getName(),ActionKind.SKIP.getName() ,ActionKind.ABORT.getName()};
			}else if(uf.eContainer() instanceof InstanceEditor || uf instanceof InstanceEditor){
				return new String[]{ActionKind.UPDATE.getName(),ActionKind.DELETE.getName(),ActionKind.REFRESH.getName()};
			}else if(uf.eContainer() instanceof OperationInvocationWizard || uf.eContainer() instanceof QueryInvoker){
				return new String[]{ActionKind.EXECUTE.getName()};
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
	@Override
	protected String getFeatureAsText(EObject featureOwner){
		return getFeatureAsText();
	}
}