package org.opaeum.linkage;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.uml2.uml.INakedElement;
import org.eclipse.uml2.uml.INakedElementOwner;
import org.eclipse.uml2.uml.INakedRootObject;
import org.eclipse.uml2.uml.RootObjectStatus;
import org.opaeum.feature.InputModel;
import org.opaeum.feature.OpaeumConfig;
import org.opaeum.feature.PhaseDependency;
import org.opaeum.feature.TransformationContext;
import org.opaeum.feature.TransformationPhase;
import org.opaeum.feature.visit.VisitBefore;
import org.opaeum.metamodel.validation.BrokenElement;
import org.opaeum.metamodel.validation.BrokenRule;
import org.opaeum.metamodel.workspace.ModelWorkspace;
import org.opaeum.visitor.NakedElementOwnerVisitor;

@PhaseDependency()
public class LinkagePhase implements TransformationPhase<AbstractModelElementLinker,INakedElement>{
	public final class ErrorRemover extends NakedElementOwnerVisitor{
		@VisitBefore(matchSubclasses = true)
		public void visitElement(INakedElement e){
			BrokenElement remove = modelWorkspace.getErrorMap().getErrors().remove(e.getId());
		}
		@Override
		protected int getThreadPoolSize(){
			return 1;
		}
	}
	private OpaeumConfig config;
	@InputModel
	private ModelWorkspace modelWorkspace;
	private List<AbstractModelElementLinker> linkers;
	@Override
	public Collection<?> processElements(TransformationContext context,Collection<INakedElement> elements){
		Collection<INakedElement> affectedElements = new HashSet<INakedElement>(elements);
		for(BrokenElement brokenElement:modelWorkspace.getErrorMap().getErrors().values()){
			for(BrokenRule b:brokenElement.getBrokenRules().values()){
				Object[] parameters = b.getParameters();
				for(Object object:parameters){
					for(INakedElement iNakedElement:elements){
						if(object == iNakedElement){
							for(Object object2:parameters){
								if(object2 instanceof INakedElement){
									affectedElements.add((INakedElement) object2);
								}
							}
							INakedElement source = modelWorkspace.getModelElement(b.getElementId());
							if(source != null){
								affectedElements.add(source);
							}
						}
					}
				}
			}
		}
		ErrorRemover er = new ErrorRemover();
		for(INakedElement element:affectedElements){
			er.visitRecursively(element);
		}
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
		context.getLog().startTask("Linking Opaeum Metadata", linkers.size());
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
	public void initialize(OpaeumConfig config,List<AbstractModelElementLinker> features){
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
		return new HashSet<Class<? extends AbstractModelElementLinker>>(Arrays.asList(NakedParsedOclStringResolver.class,
				ProcessIdentifier.class, MappedTypeLinker.class, DependencyCalculator.class, PinLinker.class, CompositionEmulator.class,
				ReferenceResolver.class, QualifierLogicCalculator.class, ParameterLinker.class, ObjectFlowLinker.class, InverseCalculator.class,
				EnumerationValuesAttributeAdder.class, TypeResolver.class, SourcePopulationResolver.class, RootClassifierIdentifier.class));
	}
	@Override
	public void release(){
		this.modelWorkspace = null;
		for(AbstractModelElementLinker l:this.linkers){
			l.release();
		}
	}
}