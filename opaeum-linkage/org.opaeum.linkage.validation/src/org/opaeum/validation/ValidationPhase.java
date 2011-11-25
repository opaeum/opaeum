package org.opaeum.validation;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.opaeum.feature.InputModel;
import org.opaeum.feature.OpaeumConfig;
import org.opaeum.feature.PhaseDependency;
import org.opaeum.feature.TransformationContext;
import org.opaeum.feature.TransformationPhase;
import org.opaeum.linkage.LinkagePhase;
import org.opaeum.metamodel.core.INakedElement;
import org.opaeum.metamodel.core.INakedRootObject;
import org.opaeum.metamodel.core.RootObjectStatus;
import org.opaeum.metamodel.workspace.INakedModelWorkspace;
import org.opaeum.validation.activities.ActionValidation;
import org.opaeum.validation.activities.ActivityValidator;
import org.opaeum.validation.commonbehavior.BehaviorValidator;
import org.opaeum.validation.core.GeneralizationValidator;
import org.opaeum.validation.core.PrimitiveValidator;
import org.opaeum.validation.namegeneration.NameGenerationPhase;

@PhaseDependency(after = {
		NameGenerationPhase.class,LinkagePhase.class
})
public class ValidationPhase implements TransformationPhase<AbstractValidator,INakedElement>{
	private OpaeumConfig config;
	@InputModel
	private INakedModelWorkspace modelWorkspace;
	private List<AbstractValidator> validators;
	@Override
	public Collection<?> processElements(TransformationContext context,Collection<INakedElement> elements){
		for(INakedElement element:elements){
			for(AbstractValidator v:validators){
				v.visitRecursively((INakedElement) element);
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
				v.startVisiting(modelWorkspace);
				context.getLog().endLastStep();
			}
		}
		for(INakedRootObject ro:modelWorkspace.getGeneratingModelsOrProfiles()){
			ro.setStatus(RootObjectStatus.VALIDATED);
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
			v.initialize(modelWorkspace, this.config);
		}
	}
	@Override
	public Collection<AbstractValidator> getSteps(){
		return validators;
	}
	@SuppressWarnings("unchecked")
	public static Set<Class<? extends AbstractValidator>> getAllValidationSteps(){
		return new HashSet<Class<? extends AbstractValidator>>(Arrays.asList(OperationValidation.class, ActionValidation.class, ReservedWordValidator.class,
				NameUniquenessValidation.class, PropertyValidation.class, GeneralizationValidator.class, PrimitiveValidator.class, BehaviorValidator.class,
				ActivityValidator.class));
	}
	@Override
	public void release(){
		this.modelWorkspace=null;
		for(AbstractValidator v:this.validators){
			v.release();
		}
		
	}
}
