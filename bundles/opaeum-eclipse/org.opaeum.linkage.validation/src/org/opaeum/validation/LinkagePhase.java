package org.opaeum.validation;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.uml2.uml.Element;
import org.opaeum.emf.extraction.AbstractEmfPhase;
import org.opaeum.emf.workspace.EmfWorkspace;
import org.opaeum.feature.InputModel;
import org.opaeum.feature.OpaeumConfig;
import org.opaeum.feature.PhaseDependency;
import org.opaeum.feature.TransformationContext;
import org.opaeum.feature.TransformationPhase;
import org.opaeum.linkage.SourcePopulationResolver;

@PhaseDependency(after = {ValidationPhase.class})
public class LinkagePhase extends AbstractEmfPhase implements TransformationPhase<SourcePopulationResolver,Element>{
	private OpaeumConfig config;
	@InputModel
	private EmfWorkspace emfWorkspace;
	private List<SourcePopulationResolver> validators;
	@Override
	protected EmfWorkspace getModelWorkspace(){
		return emfWorkspace;
	}
	@Override
	public Collection<?> processElements(TransformationContext context,Collection<Element> elements){
		Set<Element> allAffectedElemens = calculateEffectiveChanges(elements);
		for(Element element:allAffectedElemens){
			for(AbstractValidator v:validators){
				v.visitOnly(element);
			}
		}
		return elements;
	}
	@Override
	public void execute(TransformationContext context){
		context.getLog().startTask("Linking Model", validators.size());
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
	public void initialize(OpaeumConfig config,List<SourcePopulationResolver> features){
		this.validators = features;
		this.config = config;
	}
	public void initializeSteps(){
		for(SourcePopulationResolver v:validators){
			v.initialize(emfWorkspace, this.config);
		}
	}
	@Override
	public Collection<SourcePopulationResolver> getSteps(){
		return validators;
	}
	@SuppressWarnings("unchecked")
	public static Set<Class<? extends SourcePopulationResolver>> getAllValidationSteps(){
		return new HashSet<Class<? extends SourcePopulationResolver>>(Arrays.asList(SourcePopulationResolver.class));
	}
	@Override
	public void release(){
		this.emfWorkspace = null;
		for(SourcePopulationResolver v:this.validators){
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