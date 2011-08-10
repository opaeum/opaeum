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
import net.sf.nakeduml.metamodel.workspace.INakedModelWorkspace;
import net.sf.nakeduml.metamodel.workspace.internal.NakedModelWorkspaceImpl;

import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Package;

@PhaseDependency(before = {
	LinkagePhase.class
})
public class EmfExtractionPhase implements TransformationPhase<AbstractExtractorFromEmf,Element>{
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
	public Collection<?> processElements(TransformationContext context,Collection<Element> elements){
		Collection<INakedElement> result = new HashSet<INakedElement>();
		Set<Element> elementsToProcess = filterChildrenOut(elements);
		for(Element element:elementsToProcess){
			for(AbstractExtractorFromEmf v:extractors){
				v.visitRecursively((Element) element);
			}
			result.add(modelWorkspace.getModelElement(emfWorkspace.getId((Element) element)));
		}
		return result;
	}
	private Set<Element> filterChildrenOut(Collection<Element> elements){
		Set<Element> elementsToProcess = new HashSet<Element>();
		outer:for(Element element1:elements){
			for(Element element2:elements){
				if(element2.eContents().contains(element1)){
					continue outer;
				}
			}
			elementsToProcess.add(element1);
		}
		return elementsToProcess;
	}
	@Override
	public void execute(TransformationContext context){
		for(AbstractExtractorFromEmf v:extractors){
			v.startVisiting(emfWorkspace);
		}
		for(Package gp:emfWorkspace.getGeneratingModelsOrProfiles()){
			modelWorkspace.addGeneratingRootObject((INakedRootObject) getNakedPackage(gp));
		}
		for(Package gp:emfWorkspace.getPrimaryModels()){
			modelWorkspace.addPrimaryModel((INakedRootObject) getNakedPackage(gp));
		}
	}
	@Override
	public void initialize(NakedUmlConfig config,List<AbstractExtractorFromEmf> features){
		this.config=config;
		this.extractors = features;
		
	}
	public void initializeSteps(){
		emfWorkspace.setMappingInfo(config.getWorkspaceMappingInfo());
		modelWorkspace.setWorkspaceMappingInfo(config.getWorkspaceMappingInfo());
		modelWorkspace.clearGeneratingModelOrProfiles();
		modelWorkspace.setName(emfWorkspace.getName());
		modelWorkspace.setIdentifier(emfWorkspace.getIdentifier());
		for(AbstractExtractorFromEmf v:extractors){
			v.initialize(emfWorkspace, modelWorkspace);
		}
	}
	@Override
	public Collection<AbstractExtractorFromEmf> getSteps(){
		return this.extractors;
	}
}
