package org.opaeum.uim.userinteractionproperties.sections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.BehavioredClassifier;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Namespace;
import org.eclipse.uml2.uml.Operation;
import org.opaeum.eclipse.EmfBehaviorUtil;
import org.opaeum.eclipse.EmfOperationUtil;
import org.opaeum.eclipse.uml.propertysections.base.AbstractChooserPropertySection;
import org.opaeum.emf.workspace.EmfWorkspace;
import org.opaeum.uim.UimPackage;
import org.opaeum.uim.UmlReference;
import org.opaeum.uim.action.InvocationButton;
import org.opaeum.uim.provider.UimItemProviderAdapterFactory;
import org.opaeum.uim.util.UmlUimLinks;

public class InvocationButtonOperationSection extends AbstractChooserPropertySection{
	public String getLabelText(){
		return "Invoked element:";
	}
	protected EStructuralFeature getFeature(){
		return UimPackage.eINSTANCE.getUmlReference_UmlElementUid();
	}
	protected Object getFeatureValue(){
		return UmlUimLinks.getCurrentUmlLinks(getOperationAction()).getOperation(getOperationAction());
	}
	private InvocationButton getOperationAction(){
		return (InvocationButton) getEObject();
	}
	protected Object[] getComboFeatureValues(){
		Collection<Namespace> results = new ArrayList<Namespace>();
		if(getEObject() instanceof InvocationButton){
			InvocationButton oa = getOperationAction();
			Classifier nearestClass = UmlUimLinks.getCurrentUmlLinks(oa).getNearestClass(oa);
			results.addAll(EmfOperationUtil.getEffectiveOperations(nearestClass));
			if(nearestClass instanceof BehavioredClassifier){
				results.addAll(EmfBehaviorUtil.getEffectiveBehaviors((BehavioredClassifier) nearestClass));
			}
		}
		return getActionOperations(results);
	}
	private Object[] getActionOperations(Collection<Namespace> opers){
		List<Object> results = new ArrayList<Object>();
		for(Namespace element:opers){
			if(element instanceof Behavior){
				Behavior b=(Behavior) element;
				if(EmfBehaviorUtil.isStandaloneTask(b) || EmfBehaviorUtil.isProcess(b)){
					results.add(b);
				}
			}else if(!((Operation)element).isQuery()){
				results.add(element);
			}
		}
		return (NamedElement[]) results.toArray(new NamedElement[results.size()]);
	}
	protected ILabelProvider getLabelProvider(){
		List f = new ArrayList();
		f.add(new UimItemProviderAdapterFactory());
		f.addAll(getPrincipalAdapterFactories());
		return new AdapterFactoryLabelProvider(new ComposedAdapterFactory(f));
	}
	protected void createCommand(Object oldValue,Object newValue){
		boolean equals = oldValue == null ? false : oldValue.equals(newValue);
		if(!equals){
			EditingDomain editingDomain = getEditingDomain();
			Element value = (Element) newValue;
			String uuid = EmfWorkspace.getId(value);
			CompoundCommand compoundCommand = new CompoundCommand(COMMAND_NAME);
			// apply the property change to all selected elements
			for(EObject nextObject:getEObjectList()){
				compoundCommand.append(SetCommand.create(editingDomain, nextObject, getFeature(), uuid));
			}
			editingDomain.getCommandStack().execute(compoundCommand);
		}
	}
	UmlReference getReference(){
		return getOperationAction();
	}
}
