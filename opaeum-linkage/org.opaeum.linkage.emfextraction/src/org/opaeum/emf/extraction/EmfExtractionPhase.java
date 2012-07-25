package org.opaeum.emf.extraction;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
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
import org.eclipse.uml2.uml.INakedElement;
import org.eclipse.uml2.uml.INakedPackage;
import org.eclipse.uml2.uml.INakedRootObject;
import org.eclipse.uml2.uml.InterfaceRealization;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Pseudostate;
import org.eclipse.uml2.uml.Reception;
import org.eclipse.uml2.uml.Region;
import org.eclipse.uml2.uml.RootObjectStatus;
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
import org.opaeum.linkage.LinkagePhase;
import org.opaeum.metamodel.core.internal.NakedElementOwnerImpl;
import org.opaeum.metamodel.workspace.ModelWorkspace;
import org.opaeum.metamodel.workspace.internal.NakedModelWorkspaceImpl;

@PhaseDependency(before = {
	LinkagePhase.class
})
public class EmfExtractionPhase implements TransformationPhase<AbstractExtractorFromEmf,EObject>{
	@InputModel(implementationClass = NakedModelWorkspaceImpl.class)
	private ModelWorkspace modelWorkspace;
	@InputModel
	private EmfWorkspace emfWorkspace;
	private OpaeumConfig config;
	private List<AbstractExtractorFromEmf> extractors;
	private INakedPackage getNakedPackage(Package emfModel){
		return (INakedPackage) modelWorkspace.getModelElement(EmfWorkspace.getId(emfModel));
	}
	@Override
	public Collection<?> processElements(TransformationContext context,Collection<EObject> elements){
		Collection<INakedElement> result = new HashSet<INakedElement>();
		final Set<Element> effectiveElementsToProcess = filterChildrenOut(calculateAffectedElements(elements));
		for(AbstractExtractorFromEmf v:extractors){
			for(Element element:effectiveElementsToProcess){
				v.visitRecursively((Element) element);
				result.add(modelWorkspace.getModelElement(EmfWorkspace.getId((Element) element)));
			}
			Set<INakedElement> affectedElements = v.getAffectedElements();
			result.addAll(affectedElements);
		}
		result.remove(null);
		return result;
	}
	@Override
	public void execute(TransformationContext context){
		context.getLog().registerInstanceCountMap(NakedElementOwnerImpl.counts);
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
				if(!gp.eIsProxy()){
					INakedRootObject nakedPackage = (INakedRootObject) getNakedPackage(gp);
					if(nakedPackage.getStatus() == null || !nakedPackage.getStatus().isExtracted()){
						nakedPackage.setStatus(RootObjectStatus.EXTRACTED);
					}
					modelWorkspace.addGeneratingRootObject(nakedPackage);
				}
			}
			emfWorkspace.calculatePrimaryModels();
			for(Package gp:emfWorkspace.getPotentialGeneratingModels()){
				if(!gp.eIsProxy()){
					INakedRootObject nakedPackage = (INakedRootObject) getNakedPackage(gp);
					if(nakedPackage.getStatus() == null || !nakedPackage.getStatus().isExtracted()){
						nakedPackage.setStatus(RootObjectStatus.EXTRACTED);
					}
					modelWorkspace.addPrimaryModel(nakedPackage);
				}
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
	public void initialize(OpaeumConfig config,List<AbstractExtractorFromEmf> features){
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
					//TODO establish a better design to do this
					//Influences validity of senSignalActions
					Trigger t = (Trigger) eObject;
					if(t.getEvent() instanceof SignalEvent){
						SignalEvent se = (SignalEvent) t.getEvent();
						if(se.getSignal() != null){
							addCrossReferences(result, se.getSignal());
						}
					}
				}else if(eObject instanceof Reception){
					//Influences validity of senSignalActions
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
		return e instanceof Action || e instanceof ControlNode || e instanceof State || e instanceof Pseudostate || e instanceof StructuredActivityNode
				|| e instanceof Region || e instanceof Operation || (e instanceof Property && ((Property) e).getAssociation() == null) || e instanceof Classifier
				|| e instanceof Transition || e instanceof ActivityEdge || e instanceof Package || e instanceof Association || e instanceof Generalization
				|| e instanceof InterfaceRealization || e instanceof Reception || e instanceof Constraint; 
	}
	@Override
	public void release(){
		this.emfWorkspace = null;
		this.modelWorkspace = null;
		for(AbstractExtractorFromEmf e:this.extractors){
			e.release();
		}
		// TODO Auto-generated method stub
	}
}
