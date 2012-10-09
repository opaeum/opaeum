package org.opaeum.uim.createchild;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.opaeum.eclipse.newchild.DefaultCreateChildAction;
import org.opaeum.eclipse.newchild.ICreateChildAction;
import org.opaeum.eclipse.newchild.ICreateChildActionProvider;
import org.opaeum.eclipse.newchild.MatchingOwner;
import org.opaeum.uim.UimPackage;
import org.opaeum.uim.constraint.ConstraintPackage;
import org.opaeum.uim.editor.EditorPackage;
import org.opaeum.uim.model.ModelPackage;
import org.opaeum.uim.wizard.WizardPackage;

public class CreateChildActions implements ICreateChildActionProvider{
	private Set<EReference> controlledFeatures = new HashSet<EReference>();
	private Set<ICreateChildAction> actions = new HashSet<ICreateChildAction>();
	public CreateChildActions(){
		for(EClassifier ec:UimPackage.eINSTANCE.getEClassifiers()){
			if(ec instanceof EClass){
				controlledFeatures.addAll(((EClass) ec).getEReferences());
			}
		}
		for(EPackage ePackage:UimPackage.eINSTANCE.getESubpackages()){
			for(EClassifier ec:ePackage.getEClassifiers()){
				if(ec instanceof EClass){
					controlledFeatures.addAll(((EClass) ec).getEReferences());
				}
			}
		}
		controlledFeatures.remove(EditorPackage.eINSTANCE.getAbstractEditor_ActionBar());
		controlledFeatures.remove(EditorPackage.eINSTANCE.getAbstractEditor_Pages());
		controlledFeatures.remove(WizardPackage.eINSTANCE.getAbstractWizard_Pages());
		controlledFeatures.remove(EditorPackage.eINSTANCE.getMenuConfiguration_Operations());
		controlledFeatures.remove(ConstraintPackage.eINSTANCE.getConstrainedObject_Visibility());
		controlledFeatures.remove(ConstraintPackage.eINSTANCE.getEditableConstrainedObject_Editability());
	}
	private void add(EReference feature){
		actions.add(new DefaultCreateChildAction(feature));
	}
	@Override
	public Set<EReference> getControlledFeatures(){
		controlledFeatures.clear();
		for(EClassifier ec:UimPackage.eINSTANCE.getEClassifiers()){
			if(ec instanceof EClass){
				controlledFeatures.addAll(((EClass) ec).getEReferences());
			}
		}
		for(EPackage ePackage:UimPackage.eINSTANCE.getESubpackages()){
			for(EClassifier ec:ePackage.getEClassifiers()){
				if(ec instanceof EClass){
					controlledFeatures.addAll(((EClass) ec).getEReferences());
				}
			}
		}
		controlledFeatures.remove(EditorPackage.eINSTANCE.getAbstractEditor_ActionBar());
		controlledFeatures.remove(EditorPackage.eINSTANCE.getAbstractEditor_Pages());
		controlledFeatures.remove(WizardPackage.eINSTANCE.getAbstractWizard_Pages());
		controlledFeatures.remove(EditorPackage.eINSTANCE.getMenuConfiguration_Operations());
		controlledFeatures.remove(ModelPackage.eINSTANCE.getQueryInvoker_ResultPage());
		controlledFeatures.remove(ModelPackage.eINSTANCE.getOperationInvocationWizard_ResultPage());
		controlledFeatures.remove(ConstraintPackage.eINSTANCE.getConstrainedObject_Visibility());
		controlledFeatures.remove(ConstraintPackage.eINSTANCE.getEditableConstrainedObject_Editability());
		return controlledFeatures;
	}
	@Override
	public Set<? extends ICreateChildAction> getActions(){
		actions.clear();
//		MatchingOwner[] editors =
//		 add(EditorPackage.eINSTANCE.getAbstractEditor_Pages());
//		 add(EditorPackage.eINSTANCE.getAbstractEditor_ActionBar());
//		 add(WizardPackage.eINSTANCE.getAbstractWizard_Pages());

		return actions;
	}
}
