package org.opaeum.eclipse.uml.propertysections.activitydiagram;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.UMLPackage;
import org.opaeum.eclipse.EmfBehaviorUtil;
import org.opaeum.eclipse.uml.propertysections.base.OpaeumChooserPropertySection;
import org.opaeum.emf.extraction.StereotypesHelper;
import org.opaeum.metamodel.core.internal.StereotypeNames;

public class BehaviorSpecificationSection extends OpaeumChooserPropertySection{
	@Override
	protected Object[] getComboFeatureValues(){
		List<Object> choices = new ArrayList<Object>();
		choices.add("");
		Collection<Operation> specs = EmfBehaviorUtil.findSpecificationsInScope(getBehavior());
		if(couldImplementResponsibility()){
			for(Operation operation:specs){
				if(EmfBehaviorUtil.isResponsibility(operation)){
					choices.add(operation);
				}
			}
		}else{
			for(Operation operation:specs){
				if(!EmfBehaviorUtil.isResponsibility(operation)){
					choices.add(operation);
				}
			}
			
		}
		return choices.toArray();
	}
	private Behavior getBehavior(){
		return (Behavior) getEObject();
	}
	@Override
	protected Object getFeatureValue(){
		return getBehavior().getSpecification();
	}
	@Override
	public void setInput(IWorkbenchPart part,ISelection selection){
		super.setInput(part, selection);
	}
	protected boolean couldImplementResponsibility(){
		return StereotypesHelper.hasStereotype(getBehavior(), StereotypeNames.STANDALONE_SCREENFLOW_TASK,StereotypeNames.STANDALONE_SINGLE_SCREEN_TASK,StereotypeNames.BUSINES_PROCESS );
	}
	@Override
	protected EStructuralFeature getFeature(){
		return UMLPackage.eINSTANCE.getBehavior_Specification();
	}
	@Override
	protected String getLabelText(){
		return "Implements:";
	}
}
