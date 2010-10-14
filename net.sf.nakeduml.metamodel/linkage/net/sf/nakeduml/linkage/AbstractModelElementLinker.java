package net.sf.nakeduml.linkage;
import net.sf.nakeduml.feature.NakedUmlConfig;
import net.sf.nakeduml.feature.TransformationStep;
import net.sf.nakeduml.metamodel.validation.ErrorMap;
import net.sf.nakeduml.metamodel.visitor.NakedElementOwnerVisitor;
import net.sf.nakeduml.metamodel.workspace.INakedModelWorkspace;
import net.sf.nakeduml.metamodel.workspace.MappedTypes;
public abstract class AbstractModelElementLinker extends NakedElementOwnerVisitor implements TransformationStep {
	protected INakedModelWorkspace workspace;
	protected NakedUmlConfig config;
	public void initialize(INakedModelWorkspace workspace, NakedUmlConfig config) {
		this.workspace = workspace;
		this.config = config;
		
	}
	protected MappedTypes getBuiltInTypes(){
		return workspace.getMappedTypes();
	}
	protected ErrorMap getErrorMap() {
		return workspace.getErrorMap();
	}
}
