package net.sf.nakeduml.emf.extraction;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import net.sf.nakeduml.detachment.DetachmentPhase;
import net.sf.nakeduml.emf.workspace.EmfWorkspace;
import net.sf.nakeduml.feature.InputModel;
import net.sf.nakeduml.feature.NakedUmlConfig;
import net.sf.nakeduml.feature.PhaseDependency;
import net.sf.nakeduml.feature.TransformationContext;
import net.sf.nakeduml.feature.TransformationPhase;
import net.sf.nakeduml.linkage.LinkagePhase;
import net.sf.nakeduml.metamodel.core.INakedPackage;
import net.sf.nakeduml.metamodel.core.INakedRootObject;
import net.sf.nakeduml.metamodel.workspace.INakedModelWorkspace;
import net.sf.nakeduml.metamodel.workspace.MappedType;
import net.sf.nakeduml.metamodel.workspace.internal.NakedModelWorkspaceImpl;

import org.eclipse.emf.common.util.URI;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Package;

@PhaseDependency(after = DetachmentPhase.class,before = {
	LinkagePhase.class
})
public class EmfExtractionPhase implements TransformationPhase<AbstractExtractorFromEmf>{
	public static final String MAPPINGS_EXTENSION = "mappings";
	@InputModel(implementationClass = NakedModelWorkspaceImpl.class)
	private INakedModelWorkspace modelWorkspace;
	@InputModel
	private EmfWorkspace emfWorkspace;
	private NakedUmlConfig config;
	public void initialize(NakedUmlConfig config){
		this.config = config;
	}
	public Object[] execute(List<AbstractExtractorFromEmf> features,TransformationContext context){
		modelWorkspace.setWorkspaceMappingInfo(emfWorkspace.getMappingInfo());
		modelWorkspace.clearGeneratingModelOrProfiles();
		modelWorkspace.setName(emfWorkspace.getName());
		modelWorkspace.setIdentifier(emfWorkspace.getIdentifier());
		for(Element e:emfWorkspace.getOwnedElements()){
			URI mappedTypesUri = e.eResource().getURI().trimFileExtension().appendFileExtension(MAPPINGS_EXTENSION);
			try{
				InputStream inStream = e.eResource().getResourceSet().getURIConverter().createInputStream(mappedTypesUri);
				Properties props = new Properties();
				props.load(inStream);
				Set<Entry<Object,Object>> entrySet = props.entrySet();
				for(Entry<Object,Object> entry:entrySet){
					modelWorkspace.getNakedUmlLibrary().getTypeMap().put((String) entry.getKey(), new MappedType((String) entry.getValue()));
				}
				System.out.println("Loaded mappings: " + mappedTypesUri);
			}catch(IOException e1){
				// System.out.println("Could not load mappedTypes in " + mappedTypesUri);
				// System.out.println(e);
			}
		}
		// FIrst initialize to allow extractors to determine previously extracted models
		for(AbstractExtractorFromEmf v:features){
			v.initialize(modelWorkspace);
		}
		for(AbstractExtractorFromEmf v:features){
			v.startVisiting(emfWorkspace);
		}
		for(Package gp:emfWorkspace.getGeneratingModelsOrProfiles()){
			modelWorkspace.addGeneratingRootObject((INakedRootObject) getNakedPackage(gp));
		}
		for(Package gp:emfWorkspace.getPrimaryModels()){
			modelWorkspace.addPrimaryModel((INakedRootObject) getNakedPackage(gp));
		}
		return new Object[]{};
	}
	private INakedPackage getNakedPackage(Package emfModel){
		return (INakedPackage) modelWorkspace.getModelElement(getIdFor(emfModel));
	}
	private static String getIdFor(Package model){
		return AbstractExtractorFromEmf.getId(model);
	}
}
