package net.sf.nakeduml.validation;

import java.util.Collection;

import net.sf.nakeduml.feature.NakedUmlConfig;
import net.sf.nakeduml.feature.TransformationStep;
import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.feature.visit.VisitorAdapter;
import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.metamodel.core.INakedPackage;
import net.sf.nakeduml.metamodel.models.INakedModel;
import net.sf.nakeduml.metamodel.validation.ErrorMap;
import net.sf.nakeduml.metamodel.workspace.INakedModelWorkspace;

public abstract class AbstractValidator extends VisitorAdapter<INakedElement, INakedPackage> implements TransformationStep {
	protected INakedModelWorkspace workspace;
	protected NakedUmlConfig config;

	@Override
	public Collection<? extends INakedElement> getChildren(INakedElement root) {
		return root.getOwnedElements();
	}

	public void initialize(INakedModelWorkspace workspace, NakedUmlConfig config) {
		this.workspace = workspace;
		this.config = config;
	}

	protected ErrorMap getErrorMap() {
		return workspace.getErrorMap();
	}

	@VisitBefore(matchSubclasses = true)
	public void remove(INakedModel e) {
		workspace.removeOwnedElement(e);
	}
	
}
