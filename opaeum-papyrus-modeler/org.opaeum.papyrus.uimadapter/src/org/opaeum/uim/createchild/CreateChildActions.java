package org.opaeum.uim.createchild;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EReference;
import org.opaeum.eclipse.newchild.DefaultCreateChildAction;
import org.opaeum.eclipse.newchild.ICreateChildAction;
import org.opaeum.eclipse.newchild.ICreateChildActionProvider;
import org.opaeum.uim.UimPackage;
import org.opaeum.uim.editor.EditorPackage;
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
		controlledFeatures.remove(EditorPackage.eINSTANCE.getAbstractEditor_ActionBar());
		controlledFeatures.remove(EditorPackage.eINSTANCE.getAbstractEditor_Pages());
		controlledFeatures.remove(WizardPackage.eINSTANCE.getAbstractWizard_Pages());
		controlledFeatures.remove(EditorPackage.eINSTANCE.getMenuConfiguration_Operations());
		add(EditorPackage.eINSTANCE.getAbstractEditor_Pages());
		add(EditorPackage.eINSTANCE.getAbstractEditor_ActionBar());
		add(WizardPackage.eINSTANCE.getAbstractWizard_Pages());
	}
	private void add(EReference feature){
		actions.add(new DefaultCreateChildAction(feature));
	}
	@Override
	public Set<EReference> getControlledFeatures(){
		return controlledFeatures;
	}
	@Override
	public Set<? extends ICreateChildAction> getActions(){
		return actions;
	}
}
