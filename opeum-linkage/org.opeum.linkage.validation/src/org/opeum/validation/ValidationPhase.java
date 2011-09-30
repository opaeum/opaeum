package org.opeum.validation;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.opeum.feature.InputModel;
import org.opeum.feature.OpeumConfig;
import org.opeum.feature.PhaseDependency;
import org.opeum.feature.TransformationContext;
import org.opeum.feature.TransformationPhase;
import org.opeum.linkage.LinkagePhase;
import org.opeum.metamodel.core.INakedElement;
import org.opeum.metamodel.core.INakedRootObject;
import org.opeum.metamodel.core.RootObjectStatus;
import org.opeum.metamodel.workspace.INakedModelWorkspace;
import org.opeum.validation.activities.ActionValidation;
import org.opeum.validation.activities.ActivityValidator;
import org.opeum.validation.commonbehavior.BehaviorValidator;
import org.opeum.validation.core.GeneralizationValidator;
import org.opeum.validation.core.PrimitiveValidator;
import org.opeum.validation.namegeneration.NameGenerationPhase;

@PhaseDependency(after = {
		NameGenerationPhase.class,LinkagePhase.class
})
public class ValidationPhase implements TransformationPhase<AbstractValidator,INakedElement>{
	private OpeumConfig config;
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
	public void initialize(OpeumConfig config,List<AbstractValidator> features){
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
}
