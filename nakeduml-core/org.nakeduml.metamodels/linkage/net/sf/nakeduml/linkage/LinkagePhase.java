package net.sf.nakeduml.linkage;

import java.util.List;

import net.sf.nakeduml.feature.InputModel;
import net.sf.nakeduml.feature.NakedUmlConfig;
import net.sf.nakeduml.feature.PhaseDependency;
import net.sf.nakeduml.feature.TransformationContext;
import net.sf.nakeduml.feature.TransformationPhase;
import net.sf.nakeduml.feature.visit.VisitAfter;
import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.metamodel.core.INakedElementOwner;
import net.sf.nakeduml.metamodel.visitor.NakedElementOwnerVisitor;
import net.sf.nakeduml.metamodel.workspace.INakedModelWorkspace;
import nl.klasse.octopus.stdlib.internal.types.OclLibraryImpl;

@PhaseDependency()
public class LinkagePhase implements TransformationPhase<AbstractModelElementLinker,INakedElement>{
	public final class ErrorRemover extends NakedElementOwnerVisitor{
		@VisitBefore(matchSubclasses=true)
		public void visitElement(INakedElement e){
			modelWorkspace.getErrorMap().getErrors().remove(e.getId());
		}
	}
	private NakedUmlConfig config;
	@InputModel
	private INakedModelWorkspace modelWorkspace;
	public Object[] execute(List<AbstractModelElementLinker> linkers,TransformationContext context){
		modelWorkspace.getOclEngine().setOclLibrary(new OclLibraryImpl());
		for(AbstractModelElementLinker d:linkers){
			d.initialize(modelWorkspace, config);
			d.startVisiting(modelWorkspace);
		}
		return new Object[]{};
	}
	public void initialize(NakedUmlConfig config){
		this.config = config;
	}
	@Override
	public Object processSingleElement(List<AbstractModelElementLinker> featuresFor,TransformationContext context,INakedElement element){
		new ErrorRemover().visitRecursively(element);
		modelWorkspace.getOclEngine().setOclLibrary(new OclLibraryImpl());
		for(AbstractModelElementLinker d:featuresFor){
			d.initialize(modelWorkspace, config);
			d.visitRecursively((INakedElementOwner) element);
		}
		return element;
	}
}
