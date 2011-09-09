package net.sf.nakeduml.emf.extraction;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.sf.nakeduml.emf.workspace.EmfWorkspace;
import net.sf.nakeduml.feature.InputModel;
import net.sf.nakeduml.feature.NakedUmlConfig;
import net.sf.nakeduml.feature.PhaseDependency;
import net.sf.nakeduml.feature.TransformationContext;
import net.sf.nakeduml.feature.TransformationPhase;
import net.sf.nakeduml.linkage.LinkagePhase;
import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.metamodel.core.INakedPackage;
import net.sf.nakeduml.metamodel.core.INakedRootObject;
import net.sf.nakeduml.metamodel.core.RootObjectStatus;
import net.sf.nakeduml.metamodel.workspace.INakedModelWorkspace;
import net.sf.nakeduml.metamodel.workspace.internal.NakedModelWorkspaceImpl;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.uml2.uml.Action;
import org.eclipse.uml2.uml.ActivityEdge;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Event;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.State;
import org.eclipse.uml2.uml.Transition;
import org.eclipse.uml2.uml.Trigger;

@PhaseDependency(before = {
	LinkagePhase.class
})
public class EmfExtractionPhase implements TransformationPhase<AbstractExtractorFromEmf,EObject>{
	@InputModel(implementationClass = NakedModelWorkspaceImpl.class)
	private INakedModelWorkspace modelWorkspace;
	@InputModel
	private EmfWorkspace emfWorkspace;
	private NakedUmlConfig config;
	private List<AbstractExtractorFromEmf> extractors;
	private INakedPackage getNakedPackage(Package emfModel){
		return (INakedPackage) modelWorkspace.getModelElement(emfWorkspace.getId(emfModel));
	}
	@Override
	public Collection<?> processElements(TransformationContext context,Collection<EObject> elements){
		Collection<INakedElement> result = new HashSet<INakedElement>();
		for(AbstractExtractorFromEmf v:extractors){
			for(Element element:filterChildrenOut(elements)){
				v.visitRecursively((Element) element);
				result.add(modelWorkspace.getModelElement(emfWorkspace.getId((Element) element)));
			}
			Set<INakedElement> affectedElements = v.getAffectedElements();
			result.addAll(affectedElements);
		}
		result.remove(null);
		return result;
	}
	@Override
	public void execute(TransformationContext context){
		context.getLog().startTask("Extracting Uml Elements from EMF", extractors.size());
		modelWorkspace.clearGeneratingModelOrProfiles();
		for(AbstractExtractorFromEmf v:extractors){
			context.getLog().startStep("Executing " + v.getClass().getSimpleName());
			if(!context.getLog().isCanceled()){
				v.startVisiting(emfWorkspace);
			}
			context.getLog().endLastStep();
		}
		for(Package gp:emfWorkspace.getGeneratingModelsOrProfiles()){
			INakedRootObject nakedPackage = (INakedRootObject) getNakedPackage(gp);
			nakedPackage.setStatus(RootObjectStatus.EXTRACTED);
			modelWorkspace.addGeneratingRootObject(nakedPackage);
		}
		for(INakedRootObject ro:modelWorkspace.getRootObjects()){
			if(!ro.getStatus().isExtracted()){
				ro.setStatus(RootObjectStatus.EXTRACTED);
			}
		}
		context.getLog().endLastTask();
	}
	@Override
	public void initialize(NakedUmlConfig config,List<AbstractExtractorFromEmf> features){
		this.config = config;
		this.extractors = features;
	}
	public void initializeSteps(){
		emfWorkspace.setMappingInfo(config.getWorkspaceMappingInfo());
		modelWorkspace.setWorkspaceMappingInfo(config.getWorkspaceMappingInfo());
		modelWorkspace.setIdentifier(emfWorkspace.getIdentifier());
		modelWorkspace.setName(emfWorkspace.getName());// Currently same as Identifier
		for(AbstractExtractorFromEmf v:extractors){
			v.initialize(emfWorkspace, modelWorkspace);
		}
	}
	@Override
	public Collection<AbstractExtractorFromEmf> getSteps(){
		return this.extractors;
	}
	private Set<Element> filterChildrenOut(Collection<EObject> elements){
		Set<Element> elementsToProcess = new HashSet<Element>();
		outer:for(EObject element1:elements){
			EObject o = element1;
			while(!(canBeProcessedIndividually(o) || o == null)){
				o = getContainer(o);
			}
			if(o != null){
				for(EObject element2:elements){
					if(contains(element2, o)){
						// skip - parent will be processed
						continue outer;
					}
				}
				elementsToProcess.add((Element) o);
			}
		}
		return elementsToProcess;
	}
	private boolean contains(EObject arg0,EObject arg1){
		if(arg1.eContainer() == null){
			return false;
		}else if(arg0.equals(arg1.eContainer())){
			return true;
		}else{
			return contains(arg0, arg1.eContainer());
		}
	}
	protected EObject getContainer(EObject o){
		if(o instanceof Event && o.eContainer() instanceof org.eclipse.uml2.uml.Package){
			for(EObject eObject:StereotypesHelper.getNumlAnnotation((Element) o).getReferences()){
				if(eObject instanceof Trigger){
					return eObject;
				}
			}
		}
		return o.eContainer();
	}
	private boolean canBeProcessedIndividually(EObject e){
		return e instanceof Action || e instanceof Property || e instanceof Operation || e instanceof Classifier || e instanceof State || e instanceof Transition
				|| e instanceof ActivityEdge || e instanceof Package;
	}
}
