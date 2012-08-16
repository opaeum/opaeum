package org.opaeum.validation;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature.Setting;
import org.eclipse.uml2.uml.Action;
import org.eclipse.uml2.uml.ActivityEdge;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Constraint;
import org.eclipse.uml2.uml.ControlNode;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Generalization;
import org.eclipse.uml2.uml.InterfaceRealization;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Pseudostate;
import org.eclipse.uml2.uml.Reception;
import org.eclipse.uml2.uml.Region;
import org.eclipse.uml2.uml.SignalEvent;
import org.eclipse.uml2.uml.State;
import org.eclipse.uml2.uml.StructuredActivityNode;
import org.eclipse.uml2.uml.Transition;
import org.eclipse.uml2.uml.Trigger;
import org.opaeum.eclipse.EmfElementFinder;
import org.opaeum.emf.workspace.EmfWorkspace;
import org.opaeum.feature.InputModel;
import org.opaeum.feature.OpaeumConfig;
import org.opaeum.feature.PhaseDependency;
import org.opaeum.feature.TransformationContext;
import org.opaeum.feature.TransformationPhase;
import org.opaeum.linkage.MappedTypeLoader;
import org.opaeum.linkage.SourcePopulationResolver;
import org.opaeum.metamodel.validation.BrokenElement;
import org.opaeum.metamodel.validation.BrokenRule;
import org.opaeum.validation.activities.ActionValidation;
import org.opaeum.validation.activities.ActivityValidator;
import org.opaeum.validation.commonbehavior.BehaviorValidator;
import org.opaeum.validation.core.GeneralizationValidator;
import org.opaeum.validation.core.PrimitiveValidator;

@PhaseDependency(after = {})
public class ValidationPhase implements TransformationPhase<AbstractValidator,Element>{
	private OpaeumConfig config;
	@InputModel
	private EmfWorkspace emfWorkspace;
	private List<AbstractValidator> validators;
	@Override
	public Collection<?> processElements(TransformationContext context,Collection<Element> elements){
		Collection<Element> actualElements = new HashSet<Element>(elements);
		for(Element element:elements){
			String elementId = EmfWorkspace.getId(element);
			for(Entry<String,BrokenElement> entry:emfWorkspace.getErrorMap().getErrors().entrySet()){
				boolean match = entry.getKey().equals(elementId);
				for(BrokenRule br:entry.getValue().getBrokenRules().values()){
					Object[] parameters = br.getParameters();
					if(!match){
						for(Object object:parameters){
							if(object == element){
								match = true;
								actualElements.add(emfWorkspace.getModelElement(entry.getKey()));
								break;
							}
						}
					}
					if(match){
						for(Object object2:parameters){
							if(object2 instanceof Element){
								actualElements.add((Element) object2);
							}
						}
					}
				}
			}
		}
		for(Element element:actualElements){
			emfWorkspace.getErrorMap().getErrors().remove(EmfWorkspace.getId(element));
			for(AbstractValidator v:validators){
				v.visitOnly(element);
			}
		}
		return elements;
	}
	@Override
	public void execute(TransformationContext context){
		context.getLog().startTask("Validating Model", validators.size());
		for(AbstractValidator v:validators){
			if(!context.getLog().isCanceled()){
				context.getLog().startStep("Executing " + v.getClass().getSimpleName());
				v.startVisiting(emfWorkspace);
				context.getLog().endLastStep();
			}
		}
		context.getLog().endLastTask();
	}
	@Override
	public void initialize(OpaeumConfig config,List<AbstractValidator> features){
		this.validators = features;
		this.config = config;
	}
	public void initializeSteps(){
		for(AbstractValidator v:validators){
			v.initialize(emfWorkspace, this.config);
		}
	}
	@Override
	public Collection<AbstractValidator> getSteps(){
		return validators;
	}
	@SuppressWarnings("unchecked")
	public static Set<Class<? extends AbstractValidator>> getAllValidationSteps(){
		return new HashSet<Class<? extends AbstractValidator>>(Arrays.asList(OperationValidation.class, ActionValidation.class,
				ReservedWordValidator.class, NameUniquenessValidation.class, PropertyValidation.class, GeneralizationValidator.class,
				PrimitiveValidator.class, BehaviorValidator.class, ActivityValidator.class, OclValidator.class, SourcePopulationResolver.class,
				MappedTypeLoader.class));
	}
	@Override
	public void release(){
		this.emfWorkspace = null;
		for(AbstractValidator v:this.validators){
			v.release();
		}
	}
	private Set<Element> calculateAffectedElements(Collection<EObject> elements){
		Set<Element> result = new HashSet<Element>();
		if(elements.size() > 300){
			for(EObject eObject:elements){
				if(eObject instanceof Element){
					result.add(((Element) eObject).getNearestPackage());
				}
			}
		}else{
			for(EObject eObject:elements){
				EObject o = eObject;
				o = getProcessibleElement(o);
				if(o != null){
					result.add((Element) o);
					if(emfWorkspace.getCrossReferenceAdapter() != null){
						addCrossReferences(result, o);
					}
				}
				if(eObject instanceof Association){
					// TODO Opaeum Metamodel still follows UML2.0 where navigable ends are contained by the class
					result.addAll(((Association) eObject).getMemberEnds());
				}else if(eObject instanceof Trigger){
					// TODO establish a better design to do this
					// Influences validity of senSignalActions
					Trigger t = (Trigger) eObject;
					if(t.getEvent() instanceof SignalEvent){
						SignalEvent se = (SignalEvent) t.getEvent();
						if(se.getSignal() != null){
							addCrossReferences(result, se.getSignal());
						}
					}
				}else if(eObject instanceof Reception){
					// Influences validity of senSignalActions
					Reception r = (Reception) eObject;
					if(r.getSignal() != null){
						addCrossReferences(result, r.getSignal());
					}
				}
			}
		}
		return result;
	}
	protected void addCrossReferences(Set<Element> result,EObject o){
		Collection<Setting> non = emfWorkspace.getCrossReferenceAdapter().getNonNavigableInverseReferences(o, true);
		for(Setting setting:non){
			Element processibleElement = getProcessibleElement(setting.getEObject());
			if(processibleElement != null){
				result.add(processibleElement);
			}
		}
	}
	protected Element getProcessibleElement(EObject o){
		while(!(canBeProcessedIndividually(o) || o == null)){
			o = EmfElementFinder.getContainer(o);
		}
		return (Element) o;
	}
	private Set<Element> filterChildrenOut(Collection<Element> elements){
		Set<Element> elementsToProcess = new HashSet<Element>();
		outer:for(Element element:elements){
			for(EObject element2:elements){
				if(contains(element2, element)){
					// skip - parent will be processed
					continue outer;
				}
			}
			elementsToProcess.add((Element) element);
		}
		return elementsToProcess;
	}
	private boolean contains(EObject parent,EObject child){
		if(child == parent){
			return false;
		}else{
			final EObject childsContainer = EmfElementFinder.getContainer(child);
			if(childsContainer == null){
				return false;
			}else if(parent.equals(childsContainer)){
				return true;
			}else{
				return contains(parent, childsContainer);
			}
		}
	}
	public static boolean canBeProcessedIndividually(EObject e){
		return e instanceof Action || e instanceof ControlNode || e instanceof State || e instanceof Pseudostate
				|| e instanceof StructuredActivityNode || e instanceof Region || e instanceof Operation
				|| (e instanceof Property && ((Property) e).getAssociation() == null) || e instanceof Classifier || e instanceof Transition
				|| e instanceof ActivityEdge || e instanceof Package || e instanceof Association || e instanceof Generalization
				|| e instanceof InterfaceRealization || e instanceof Reception || e instanceof Constraint;
	}
}