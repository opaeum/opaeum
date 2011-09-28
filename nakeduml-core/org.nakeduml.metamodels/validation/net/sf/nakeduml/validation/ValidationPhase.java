package net.sf.nakeduml.validation;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.sf.nakeduml.feature.InputModel;
import net.sf.nakeduml.feature.NakedUmlConfig;
import net.sf.nakeduml.feature.PhaseDependency;
import net.sf.nakeduml.feature.TransformationContext;
import net.sf.nakeduml.feature.TransformationPhase;
import net.sf.nakeduml.linkage.LinkagePhase;
import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.metamodel.core.INakedRootObject;
import net.sf.nakeduml.metamodel.core.RootObjectStatus;
import net.sf.nakeduml.metamodel.workspace.INakedModelWorkspace;
import net.sf.nakeduml.validation.activities.ActionValidation;
import net.sf.nakeduml.validation.activities.ActivityValidator;
import net.sf.nakeduml.validation.commonbehavior.BehaviorValidator;
import net.sf.nakeduml.validation.core.GeneralizationValidator;
import net.sf.nakeduml.validation.core.PrimitiveValidator;
import net.sf.nakeduml.validation.namegeneration.NameGenerationPhase;

@PhaseDependency(after = {
		NameGenerationPhase.class,LinkagePhase.class
})
public class ValidationPhase implements TransformationPhase<AbstractValidator,INakedElement>{
	private NakedUmlConfig config;
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
	public void initialize(NakedUmlConfig config,List<AbstractValidator> features){
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
	public static Set<Class<? extends AbstractValidator>> getAllValidationSteps(){
		return new HashSet<Class<? extends AbstractValidator>>(Arrays.asList(OperationValidation.class, ActionValidation.class, ReservedWordValidator.class,
				NameUniquenessValidation.class, PropertyValidation.class, GeneralizationValidator.class, PrimitiveValidator.class, BehaviorValidator.class,
				ActivityValidator.class));
	}
}
