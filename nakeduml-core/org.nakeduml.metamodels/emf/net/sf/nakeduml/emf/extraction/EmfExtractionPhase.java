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
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.ControlNode;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Generalization;
import org.eclipse.uml2.uml.InterfaceRealization;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Pseudostate;
import org.eclipse.uml2.uml.Region;
import org.eclipse.uml2.uml.State;
import org.eclipse.uml2.uml.StructuredActivityNode;
import org.eclipse.uml2.uml.Transition;
import org.nakeduml.eclipse.EmfElementFinder;

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
		final Set<Element> effectiveElementsToProcess = filterChildrenOut(calculateAffectedElements(elements));
		for(AbstractExtractorFromEmf v:extractors){
			for(Element element:effectiveElementsToProcess){
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
		if(!context.getLog().isCanceled()){
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
				while(!(canBeProcessedIndividually(o) || o == null)){
					o = EmfElementFinder.getContainer(o);
				}
				if(o != null){
					result.add((Element) o);
				}
				if(eObject instanceof Association){
					// TODO NakedUML Metamodel still follows UML2.0 where navigable ends are contained by the class
					result.addAll(((Association) eObject).getMemberEnds());
				}
			}
		}
		return result;
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
	private boolean canBeProcessedIndividually(EObject e){
		return e instanceof Action || e instanceof ControlNode || e instanceof State || e instanceof Pseudostate || e instanceof StructuredActivityNode
				|| e instanceof Region || e instanceof Operation || (e instanceof Property && ((Property) e).getAssociation() == null) || e instanceof Classifier
				|| e instanceof Transition || e instanceof ActivityEdge || e instanceof Package || e instanceof Association || e instanceof Generalization
				|| e instanceof InterfaceRealization;
	}
}
