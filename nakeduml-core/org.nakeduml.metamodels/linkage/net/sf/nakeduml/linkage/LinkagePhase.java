package net.sf.nakeduml.linkage;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import net.sf.nakeduml.feature.InputModel;
import net.sf.nakeduml.feature.NakedUmlConfig;
import net.sf.nakeduml.feature.PhaseDependency;
import net.sf.nakeduml.feature.TransformationContext;
import net.sf.nakeduml.feature.TransformationPhase;
import net.sf.nakeduml.feature.ITransformationStep;
import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.metamodel.core.INakedElementOwner;
import net.sf.nakeduml.metamodel.mapping.internal.WorkspaceMappingInfoImpl;
import net.sf.nakeduml.metamodel.visitor.NakedElementOwnerVisitor;
import net.sf.nakeduml.metamodel.workspace.INakedModelWorkspace;
import nl.klasse.octopus.stdlib.internal.types.OclLibraryImpl;

@PhaseDependency()
public class LinkagePhase implements TransformationPhase<AbstractModelElementLinker,INakedElement>{
	public final class ErrorRemover extends NakedElementOwnerVisitor{
		@VisitBefore(matchSubclasses = true)
		public void visitElement(INakedElement e){
			modelWorkspace.getErrorMap().getErrors().remove(e.getId());
		}
	}
	private NakedUmlConfig config;
	@InputModel
	private INakedModelWorkspace modelWorkspace;
	private List<AbstractModelElementLinker> linkers;
	@Override
	public Collection<?> processElements(TransformationContext context,Collection<INakedElement> elements){
		Collection<INakedElement> affectedElements = new HashSet<INakedElement>();
		for(INakedElement element:elements){
			new ErrorRemover().visitRecursively(element);
			modelWorkspace.getOclEngine().setOclLibrary(new OclLibraryImpl());
			for(AbstractModelElementLinker d:linkers){
				d.initialize(modelWorkspace, config);
				d.visitRecursively((INakedElementOwner) element);
				if(!modelWorkspace.getErrorMap().getErrors().containsKey(element.getId())){
					affectedElements.addAll(d.getAffectedElements());
				}
			}
		}
		return affectedElements;
	}
	@Override
	public void execute(TransformationContext context){
		for(AbstractModelElementLinker d:linkers){
			d.startVisiting(modelWorkspace);
		}
	}
	@Override
	public void initialize(NakedUmlConfig config,List<AbstractModelElementLinker> features){
		this.config=config;
		this.linkers = features;
		modelWorkspace.getOclEngine().setOclLibrary(new OclLibraryImpl());
		for(AbstractModelElementLinker d:linkers){
			d.initialize(modelWorkspace, config);
		}
	}
	@Override
	public Collection<AbstractModelElementLinker> getSteps(){
		return this.linkers;
	}
}
