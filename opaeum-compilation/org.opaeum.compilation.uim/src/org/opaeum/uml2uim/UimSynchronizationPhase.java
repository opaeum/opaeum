package org.opaeum.uml2uim;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.uml2.uml.Element;
import org.opaeum.emf.workspace.EmfWorkspace;
import org.opaeum.feature.InputModel;
import org.opaeum.feature.OpaeumConfig;
import org.opaeum.feature.PhaseDependency;
import org.opaeum.feature.TransformationContext;
import org.opaeum.feature.TransformationPhase;

@PhaseDependency(before = {
	ModelCopyPhase.class
})
public class UimSynchronizationPhase implements TransformationPhase<AbstractUimSynchronizer,Element>{
	@InputModel
	EmfWorkspace workspace;
	@SuppressWarnings("unused")
	private OpaeumConfig config;
	private List<AbstractUimSynchronizer> features;
	private ResourceSetImpl uimResourceSet;
	@SuppressWarnings("rawtypes")
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
		for(Element element:elements){
			ResourceSet resourceSet = new ResourceSetImpl();
			// TODO when to load contents?
			for(AbstractUimSynchronizer s:features){
				s.init(workspace, resourceSet, false);
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
			if(!context.getLog().isCanceled()){
				s.startVisiting(workspace);
			}
		}
		try{
			save(workspace.getDirectoryUri(), resourceSet);
		}catch(IOException e){
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void initialize(OpaeumConfig config,List<AbstractUimSynchronizer> features){
		this.config = config;
		this.uimResourceSet = new ResourceSetImpl();
		this.features = features;
	}
	public void initializeSteps(){
		for(AbstractUimSynchronizer s:this.features){
			s.init(workspace, uimResourceSet, false);
		}
	}
	@Override
	public Collection<AbstractUimSynchronizer> getSteps(){
		return features;
	}
	@Override
	public void release(){
		this.workspace = null;
		this.uimResourceSet = null;
		for(AbstractUimSynchronizer u:this.features){
			u.release();
		}
	}
}
