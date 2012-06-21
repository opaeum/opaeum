package org.opaeum.topcased.uml.editor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.command.CommandParameter;
import org.eclipse.emf.edit.ui.action.CreateChildAction;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.PlatformUI;
import org.eclipse.uml2.uml.Action;
import org.eclipse.uml2.uml.ActionInputPin;
import org.eclipse.uml2.uml.Activity;
import org.eclipse.uml2.uml.ActivityEdge;
import org.eclipse.uml2.uml.ActivityGroup;
import org.eclipse.uml2.uml.ActivityNode;
import org.eclipse.uml2.uml.AggregationKind;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.Component;
import org.eclipse.uml2.uml.Connector;
import org.eclipse.uml2.uml.Dependency;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.ElementImport;
import org.eclipse.uml2.uml.ExtensionEnd;
import org.eclipse.uml2.uml.Generalization;
import org.eclipse.uml2.uml.InputPin;
import org.eclipse.uml2.uml.InterfaceRealization;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.OutputPin;
import org.eclipse.uml2.uml.PackageImport;
import org.eclipse.uml2.uml.Port;
import org.eclipse.uml2.uml.Profile;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.StateMachine;
import org.eclipse.uml2.uml.Stereotype;
import org.eclipse.uml2.uml.StructuredActivityNode;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.ValuePin;
import org.opaeum.eclipse.OpaeumFilter;
import org.opaeum.emf.extraction.StereotypesHelper;
import org.opaeum.metamodel.core.internal.StereotypeNames;
import org.opaeum.name.NameConverter;
import org.topcased.modeler.editor.MixedEditDomain;
import org.topcased.modeler.uml.internal.customchildmenu.UMLEditorMenu;
@Deprecated
public class OpaeumEditorMenu extends UMLEditorMenu{
	private MixedEditDomain domain;
	private EObject selectedObject;
	private Collection<CommandParameter> descriptors;
	public void createMenuContents(){
		this.descriptors = new ArrayList<CommandParameter>();
		for(Object o:domain.getEMFEditingDomain().getNewChildDescriptors(selectedObject, null)){
			CommandParameter cp = (CommandParameter) o;
			if(OpaeumFilter.isAllowedElement((EObject) cp.getValue())){
				this.descriptors.add(cp);
			}
		}
		ISelection selection = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getSelectionService().getSelection();
		Collection<IAction> createChildActions = generateCreateChildActions(descriptors, selection);
		Map<String,Collection<IAction>> createChildSubmenuActions = extractSubmenuActions(createChildActions);
		if(selectedObject instanceof Element){
			for(Stereotype stereotype:((Element) selectedObject).getAppliedStereotypes()){
				for(EReference ref:stereotype.getDefinition().getEAllContainments()){
					if(!ref.getEReferenceType().isAbstract()){
						Collection<IAction> creates = new ArrayList<IAction>();
						createChildSubmenuActions.put(NameConverter.separateWords(NameConverter.capitalize(ref.getName())), creates);
						EObject childObject = EcoreUtil.create(ref.getEReferenceType());
						CommandParameter desc = new CommandParameter(((Element) selectedObject).getStereotypeApplication(stereotype), ref, childObject);
						CreateChildAction action = new CreateChildAction(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getPartService().getActivePart(),
								selection, desc);
						action.setText(NameConverter.separateWords(NameConverter.capitalize(ref.getEReferenceType().getName())));
						creates.add(action);
					}
				}
			}
		}
		populateManager(this, createChildSubmenuActions, null);
		populateManager(this, createChildActions, null);
		this.update();
	}
	public void setMixedEditDomain(MixedEditDomain editDomain){
		super.setMixedEditDomain(editDomain);
		this.domain = editDomain;
	}
	public void setSelectedEObject(EObject object){
		super.setSelectedEObject(object);
		selectedObject = object;
	}
	@SuppressWarnings("unchecked")
	protected Collection<IAction> generateCreateChildActionsGen(Collection<?> theDescriptors,ISelection selection){
		Collection<IAction> actions = new ArrayList<IAction>();
		if(theDescriptors != null){
			if(selectedObject instanceof Activity && !StereotypesHelper.hasStereotype((Element) selectedObject, StereotypeNames.BUSINES_PROCESS)){
				for(CommandParameter descriptor:(Collection<CommandParameter>) theDescriptors){
					if(!(descriptor.getValue() instanceof Property || descriptor.getValue() instanceof Operation || descriptor.getValue() instanceof Behavior)){
						CreateChildAction actio = new CreateChildAction(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getPartService().getActivePart(), selection,
								descriptor);
						actions.add(actio);
					}
				}
			}else{
				for(CommandParameter descriptor:(Collection<CommandParameter>) theDescriptors){
					if(!(descriptor.getValue() instanceof Model || descriptor.getValue() instanceof Profile)){
						CreateChildAction actio = new CreateChildAction(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getPartService().getActivePart(), selection,
								descriptor);
						if(descriptor.getValue() instanceof InterfaceRealization || descriptor.getValue() instanceof Generalization
								|| descriptor.getValue() instanceof Dependency || descriptor.getValue() instanceof ElementImport
								|| descriptor.getValue() instanceof PackageImport){
							actio.setText("Dependencies|" + descriptor.getEValue().eClass().getName());
						}
						if(descriptor.getValue() instanceof Association){
							// ignore
						}else if(descriptor.getValue() instanceof ValuePin){
							if(selectedObject instanceof Action && !(selectedObject instanceof StructuredActivityNode)){
								ValuePin oclPin = UMLFactory.eINSTANCE.createValuePin();
								CreateChildAction actio2 = new CreateChildAction(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getPartService().getActivePart(),
										selection, new CommandParameter(descriptor.getOwner(), descriptor.getFeature(), oclPin));
								String name = actio.getText().split("\\|")[0];
								actio2.setText(name + "|Ocl Input");
								createNumlAnnotation(oclPin).getDetails().put(StereotypeNames.OCL_INPUT, "");
								actions.add(actio2);
								ValuePin newObjectPin = UMLFactory.eINSTANCE.createValuePin();
								CreateChildAction actio3 = new CreateChildAction(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getPartService().getActivePart(),
										selection, new CommandParameter(descriptor.getOwner(), descriptor.getFeature(), newObjectPin));
								actio3.setText(name + "|New Object Input");
								createNumlAnnotation(newObjectPin).getDetails().put(StereotypeNames.NEW_OBJECT_INPUT, "");
								actions.add(actio3);
							}
						}else if(descriptor.getValue() instanceof InputPin && !(descriptor.getValue() instanceof ActionInputPin)){
							if(selectedObject instanceof Action && !(selectedObject instanceof StructuredActivityNode)){
								String name = actio.getText().split("\\|")[0];
								actio.setText(name + "|Object Flow Input");
								actions.add(actio);
							}
						}else if(descriptor.getValue() instanceof OutputPin){
							if(selectedObject instanceof Action && !(selectedObject instanceof StructuredActivityNode)){
								String name = actio.getText().split("\\|")[0];
								actio.setText(name + "|Object Flow Output");
								actions.add(actio);
							}
						}else if(descriptor.getValue() instanceof ActivityNode || descriptor.getValue() instanceof ActivityEdge
								|| descriptor.getValue() instanceof Connector || descriptor.getValue() instanceof ActivityGroup){
							// ignore
						}else if(descriptor.getValue() instanceof Behavior
								&& descriptor.getEStructuralFeature().equals(UMLPackage.eINSTANCE.getBehavioredClassifier_ClassifierBehavior())){
							if(!(selectedObject instanceof Behavior)){
								actions.add(actio);
							}
						}else if(descriptor.getValue() instanceof Property){
							if(!(descriptor.getValue() instanceof ExtensionEnd || descriptor.getValue() instanceof Port)){
								if((selectedObject instanceof Activity && StereotypesHelper.hasStereotype((Element) selectedObject, StereotypeNames.BUSINES_PROCESS))
										|| (selectedObject instanceof StateMachine && StereotypesHelper.hasStereotype((Element) selectedObject,
												StereotypeNames.BUSINES_STATE_MACHINE))){
									actions.add(actio);
									Property fact = UMLFactory.eINSTANCE.createProperty();
									CreateChildAction actio1 = new CreateChildAction(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getPartService()
											.getActivePart(), selection, new CommandParameter(descriptor.getOwner(), descriptor.getFeature(), fact));
									actio1.setText("Owned Attribute|Measure");
									createNumlAnnotation(fact).getDetails().put(StereotypeNames.MEASURE, "");
									actions.add(actio1);
									Property dimension = UMLFactory.eINSTANCE.createProperty();
									CreateChildAction actio2 = new CreateChildAction(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getPartService()
											.getActivePart(), selection, new CommandParameter(descriptor.getOwner(), descriptor.getFeature(), dimension));
									actio2.setText("Owned Attribute|Dimension");
									createNumlAnnotation(dimension).getDetails().put(StereotypeNames.DIMENSION, "");
									actions.add(actio2);
								}else if(selectedObject instanceof Component
										&& StereotypesHelper.hasStereotype((Element) selectedObject, StereotypeNames.BUSINESS_COMPONENT)){
									if(descriptor.getValue() instanceof Port){
										if(descriptor.getFeature().equals(UMLPackage.eINSTANCE.getEncapsulatedClassifier_OwnedPort())){
											Port port = (Port) descriptor.getValue();
											createNumlAnnotation(port).getDetails().put(StereotypeNames.BUSINESS_GATEWAY, "");
											actio.setText("Owned Attribute|Business Gateway");
											actions.add(actio);
										}
									}
								}else{
									actions.add(actio);
									Property participantReference = UMLFactory.eINSTANCE.createProperty();
									CreateChildAction actio1 = new CreateChildAction(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getPartService()
											.getActivePart(), selection, new CommandParameter(descriptor.getOwner(), descriptor.getFeature(), participantReference));
									actio1.setText("Owned Attribute|Participant Reference");
									participantReference.setAggregation(AggregationKind.NONE_LITERAL);
									createNumlAnnotation(participantReference).getDetails().put(StereotypeNames.PARTICIPANT_REFERENCE, "");
									actions.add(actio1);
									Property businessRoleContaiment = UMLFactory.eINSTANCE.createProperty();
									CreateChildAction actio2 = new CreateChildAction(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getPartService()
											.getActivePart(), selection, new CommandParameter(descriptor.getOwner(), descriptor.getFeature(), businessRoleContaiment));
									actio2.setText("Owned Attribute|Business Role Containment");
									createNumlAnnotation(businessRoleContaiment).getDetails().put(StereotypeNames.BUSINESS_ROLE_CONTAINMENT, "");
									actions.add(actio2);
								}
							}
						}else{
							actions.add(actio);
							if(descriptor.getValue() instanceof Operation){
								Operation responsibility = UMLFactory.eINSTANCE.createOperation();
								CreateChildAction actio2 = new CreateChildAction(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getPartService().getActivePart(),
										selection, new CommandParameter(descriptor.getOwner(), descriptor.getFeature(), responsibility));
								actio2.setText("Owned Operation|Responsibility");
								createNumlAnnotation(responsibility).getDetails().put(StereotypeNames.RESPONSIBILITY, "");
								actions.add(actio2);
							}else if(descriptor.getValue() instanceof Activity
									&& descriptor.getFeature().equals(UMLPackage.eINSTANCE.getBehavioredClassifier_OwnedBehavior())){
								Activity businessProcess = UMLFactory.eINSTANCE.createActivity();
								CreateChildAction actio2 = new CreateChildAction(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getPartService().getActivePart(),
										selection, new CommandParameter(descriptor.getOwner(), descriptor.getFeature(), businessProcess));
								actio2.setText("Owned Behavior|Business Process");
								createNumlAnnotation(businessProcess).getDetails().put(StereotypeNames.BUSINES_PROCESS, "");
								actions.add(actio2);
								Activity method = UMLFactory.eINSTANCE.createActivity();
								CreateChildAction actio3 = new CreateChildAction(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getPartService().getActivePart(),
										selection, new CommandParameter(descriptor.getOwner(), descriptor.getFeature(), method));
								actio3.setText("Owned Behavior|Method");
								createNumlAnnotation(method).getDetails().put(StereotypeNames.METHOD, "");
								actions.add(actio3);
							}
						}
					}
				}
			}
		}
		return actions;
	}
	protected EAnnotation createNumlAnnotation(Element fact){
		return StereotypesHelper.findOrCreateNumlAnnotation(fact);
	}
}
