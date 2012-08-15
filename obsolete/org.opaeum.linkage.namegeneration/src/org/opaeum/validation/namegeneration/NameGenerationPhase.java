package org.opaeum.validation.namegeneration;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.ElementOwner;
import org.eclipse.uml2.uml.RootObject;
import org.eclipse.uml2.uml.RootObjectStatus;
import org.opaeum.feature.InputModel;
import org.opaeum.feature.OpaeumConfig;
import org.opaeum.feature.PhaseDependency;
import org.opaeum.feature.TransformationContext;
import org.opaeum.feature.TransformationPhase;
import org.opaeum.linkage.LinkagePhase;
import org.opaeum.metamodel.workspace.ModelWorkspace;

@PhaseDependency(after = {
	LinkagePhase.class
})
public class NameGenerationPhase implements TransformationPhase<AbstractNameGenerator,Element>{
	@InputModel
	private ModelWorkspace modelWorkspace;
	private List<AbstractNameGenerator> nameGenerators;
	private OpaeumConfig config;
	public void initialize(OpaeumConfig config){
	}
	@Override
	public Collection<?> processElements(TransformationContext context,Collection<Element> elements){
		Set<Element> parents = LinkagePhase.filterChildrenOut(elements);
		for(Element element:parents){
			if(element instanceof RootObject){
				((RootObject) element).setStatus(RootObjectStatus.LINKED);
			}
			for(AbstractNameGenerator ng:nameGenerators){
				
				ng.visitRecursively((ElementOwner) element);
			}
		}
		return elements;
	}
	@Override
	public void execute(TransformationContext context){
		context.getLog().startTask("Generating platform-specific names", nameGenerators.size());
		for(AbstractNameGenerator ng:nameGenerators){
			context.getLog().startStep("Executing " + ng.getClass().getSimpleName());
			ng.startVisiting(modelWorkspace);
			context.getLog().endLastStep();
		}
		for(RootObject ro:modelWorkspace.getRootObjects()){
			if(!ro.getStatus().isNamed()){
				ro.setStatus(RootObjectStatus.NAMED);
			}
		}
		context.getLog().endLastTask();
	}
	@Override
	public void initialize(OpaeumConfig config,List<AbstractNameGenerator> features){
		this.nameGenerators = features;
		this.config = config;
	}
	@Override
	public Collection<AbstractNameGenerator> getSteps(){
		return this.nameGenerators;
	}
	@Override
	public void initializeSteps(){
		for(AbstractNameGenerator ng:nameGenerators){
			ng.initialize(config);
		}
	}
	@Override
	public void release(){
		this.modelWorkspace = null;
		for(AbstractNameGenerator n:this.nameGenerators){
			n.release();
		}
		// TODO Auto-generated method stub
	}
}
