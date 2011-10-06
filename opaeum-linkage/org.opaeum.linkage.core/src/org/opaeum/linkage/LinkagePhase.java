package org.opeum.linkage;

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
import org.opeum.feature.visit.VisitBefore;
import org.opeum.metamodel.core.INakedElement;
import org.opeum.metamodel.core.INakedElementOwner;
import org.opeum.metamodel.core.INakedRootObject;
import org.opeum.metamodel.core.RootObjectStatus;
import org.opeum.metamodel.visitor.NakedElementOwnerVisitor;
import org.opeum.metamodel.workspace.INakedModelWorkspace;

@PhaseDependency()
public class LinkagePhase implements TransformationPhase<AbstractModelElementLinker,INakedElement>{
	public final class ErrorRemover extends NakedElementOwnerVisitor{
		@VisitBefore(matchSubclasses = true)
		public void visitElement(INakedElement e){
			modelWorkspace.getErrorMap().getErrors().remove(e.getId());
		}
		@Override
		protected int getThreadPoolSize(){
			return 1;
		}
	}
	private OpeumConfig config;
	@InputModel
	private INakedModelWorkspace modelWorkspace;
	private List<AbstractModelElementLinker> linkers;
	@Override
	public Collection<?> processElements(TransformationContext context,Collection<INakedElement> elements){
		for(INakedElement element:elements){
			new ErrorRemover().visitRecursively(element);
		}
		Collection<INakedElement> affectedElements = new HashSet<INakedElement>(elements);
		for(AbstractModelElementLinker d:linkers){
			for(INakedElement element:filterChildrenOut(affectedElements)){
				d.setCurrentRootObject(element.getRootObject());
				d.visitRecursively((INakedElementOwner) element);
			}
			affectedElements.addAll(d.getAffectedElements());
		}
		return affectedElements;
	}
	@Override
	public void execute(TransformationContext context){
		context.getLog().startTask("Linking Opium Metadata", linkers.size());
		for(AbstractModelElementLinker d:linkers){
			if(!context.getLog().isCanceled()){
				context.getLog().startStep("Executing " + d.getClass().getSimpleName());
				d.startVisiting(modelWorkspace);
				context.getLog().endLastStep();
			}
		}
		for(INakedRootObject r:modelWorkspace.getRootObjects()){
			if(!r.getStatus().isLinked()){
				r.setStatus(RootObjectStatus.LINKED);
			}
		}
		context.getLog().endLastTask();
	}
	@Override
	public void initialize(OpeumConfig config,List<AbstractModelElementLinker> features){
		this.config = config;
		this.linkers = features;
	}
	public void initializeSteps(){
		for(AbstractModelElementLinker d:linkers){
			d.initialize(modelWorkspace, this.config);
		}
	}
	@Override
	public Collection<AbstractModelElementLinker> getSteps(){
		return this.linkers;
	}
	public static Set<INakedElement> filterChildrenOut(Collection<INakedElement> elements){
		elements.remove(null);
		Set<INakedElement> elementsToProcess = new HashSet<INakedElement>();
		outer:for(INakedElement element1:elements){
			for(INakedElement element2:elements){
				if(contains(element2, element1)){
					// skip - parent will be processed
					continue outer;
				}
			}
			elementsToProcess.add((INakedElement) element1);
		}
		return elementsToProcess;
	}
	static boolean contains(INakedElement arg0,INakedElement arg1){
		if(!(arg1.getOwnerElement() instanceof INakedElement)){
			return false;
		}else if(arg0.equals(arg1.getOwnerElement())){
			return true;
		}else{
			return contains(arg0, (INakedElement) arg1.getOwnerElement());
		}
	}
	@SuppressWarnings("unchecked")
	public static Set<Class<? extends AbstractModelElementLinker>> getAllSteps(){
		return new HashSet<Class<? extends AbstractModelElementLinker>>(Arrays.asList(NakedParsedOclStringResolver.class, ProcessIdentifier.class,
				MappedTypeLinker.class, DependencyCalculator.class, PinLinker.class, CompositionEmulator.class, ReferenceResolver.class, QualifierLogicCalculator.class,
				ParameterLinker.class, ObjectFlowLinker.class, InverseCalculator.class, EnumerationValuesAttributeAdder.class, TypeResolver.class,
				SourcePopulationResolver.class));
	}
}
