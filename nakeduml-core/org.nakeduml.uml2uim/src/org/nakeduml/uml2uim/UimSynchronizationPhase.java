package org.nakeduml.uml2uim;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import net.sf.nakeduml.emf.workspace.EmfWorkspace;
import net.sf.nakeduml.emf.workspace.UmlElementCache;
import net.sf.nakeduml.feature.InputModel;
import net.sf.nakeduml.feature.NakedUmlConfig;
import net.sf.nakeduml.feature.PhaseDependency;
import net.sf.nakeduml.feature.TransformationContext;
import net.sf.nakeduml.feature.TransformationPhase;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.OpaqueAction;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.Pin;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.State;
import org.eclipse.uml2.uml.Transition;
import org.nakeduml.uim.util.UmlUimLinks;

@PhaseDependency()
public class UimSynchronizationPhase implements TransformationPhase<AbstractUimSynchronizer,Element>{
	@InputModel
	EmfWorkspace workspace;
	private NakedUmlConfig config;
	private List<AbstractUimSynchronizer> features;
	protected UmlElementCache buildUmlMap(){
		UmlElementCache map = new UmlElementCache(workspace);
		return map;
	}
	public static void save(URI rootDir,ResourceSet r) throws IOException{
		String rootString = toString(rootDir);
		for(Resource resource:r.getResources()){
			String string = toString(resource.getURI());
			if(string != null && string.startsWith(rootString)){
				resource.save(new HashMap());
			}
		}
	}
	private static String toString(URI rootDir){
		return rootDir.isFile() ? rootDir.toFileString() : rootDir.toPlatformString(true);
	}
	@Override
	public Collection<?> processElements(TransformationContext context,Collection<Element> elements){
		UmlElementCache map = buildUmlMap();
		for(Element element:elements){
			ResourceSet resourceSet = new ResourceSetImpl();
			//TODO when to load contents?

			UmlUimLinks.associate(resourceSet, map);
			for(AbstractUimSynchronizer s:features){
				s.init(workspace, resourceSet, false,map);
				s.visitUpThenDown((Element) element);
			}
			try{
				save(workspace.getDirectoryUri(), resourceSet);
			}catch(IOException e){
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return elements;
	}
	@Override
	public void execute(TransformationContext context){
		ResourceSet resourceSet = new ResourceSetImpl();
		for(AbstractUimSynchronizer s:features){
			s.startVisiting(workspace);
		}
		try{
			save(workspace.getDirectoryUri(), resourceSet);
		}catch(IOException e){
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void initialize(NakedUmlConfig config,List<AbstractUimSynchronizer> features){
		this.config=config;
		ResourceSet resourceSet = new ResourceSetImpl();
		UmlElementCache map = buildUmlMap();
		UmlUimLinks.associate(resourceSet, map);
		for(AbstractUimSynchronizer s:features){
			s.init(workspace, resourceSet, false, map);
		}
	}
	@Override
	public Collection<AbstractUimSynchronizer> getSteps(){
		return features;
	}
}
