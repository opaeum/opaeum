package org.opaeum.validation;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature.Setting;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.SignalEvent;
import org.eclipse.uml2.uml.Trigger;
import org.opaeum.eclipse.EmfElementFinder;
import org.opaeum.emf.extraction.AbstractEmfPhase;
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
import org.opaeum.metamodel.validation.ErrorMap;
import org.opaeum.validation.activities.ActionValidation;
import org.opaeum.validation.activities.ActivityValidator;
import org.opaeum.validation.commonbehavior.BehaviorValidator;
import org.opaeum.validation.core.GeneralizationValidator;
import org.opaeum.validation.core.PrimitiveValidator;

@PhaseDependency(after = {})
public class ValidationPhase extends AbstractEmfPhase implements TransformationPhase<AbstractValidator,Element>{
	private OpaeumConfig config;
	@InputModel
	private EmfWorkspace emfWorkspace;
	private List<AbstractValidator> validators;
	@Override
	protected EmfWorkspace getModelWorkspace(){
		return emfWorkspace;
	}
	@Override
	public Collection<?> processElements(TransformationContext context,Collection<Element> elements){
		Collection<Element> affectedInvalidElements =new HashSet<Element>(elements);
		ErrorMap errorMap = emfWorkspace.getErrorMap();
		for(Element element:elements){
			String elementId = EmfWorkspace.getId(element);
			for(Entry<String,BrokenElement> entry:errorMap.getErrors().entrySet()){
				boolean match = entry.getKey().equals(elementId);
				for(BrokenRule br:entry.getValue().getBrokenRules().values()){
					Object[] parameters = br.getParameters();
					if(!match){
						for(Object object:parameters){
							if(object == element){
								match = true;
								Element broken = emfWorkspace.getModelElement(entry.getKey());
								if(broken != null){
									affectedInvalidElements.add(broken);
								}
								break;
							}
						}
					}
					if(match){
						for(Object object2:parameters){
							if(object2 instanceof Element){
								affectedInvalidElements.add((Element) object2);
							}
						}
					}
				}
			}
		}
		Set<Element> allAffectedElemens = calculateEffectiveChanges(elements);
		allAffectedElemens.addAll(affectedInvalidElements);
		for(Element element:affectedInvalidElements){
			errorMap.getErrors().remove(EmfWorkspace.getId(element));
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

	/**
	 * @deprecated Use {@link AbstractEmfPhase#canBeProcessedIndividually(EObject)} instead
	 */
	public static boolean canBeProcessedIndividually(EObject e){
		return AbstractEmfPhase.canBeProcessedIndividually(e);
	}
}