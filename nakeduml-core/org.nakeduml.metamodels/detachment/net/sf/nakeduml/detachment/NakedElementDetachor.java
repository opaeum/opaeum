package net.sf.nakeduml.detachment;

import java.util.Collection;

import net.sf.nakeduml.feature.TransformationStep;
import net.sf.nakeduml.feature.visit.VisitorAdapter;
import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.metamodel.core.INakedPackage;
import net.sf.nakeduml.metamodel.workspace.INakedModelWorkspace;

/**
 * A visitor the is invoked when (incremental) model updates require certain
 * elements that were linked before to be detached from each other
 */
// TODO go through all the model originalElement linkers and make sure that the linked
// elements are detached
public abstract class NakedElementDetachor extends VisitorAdapter<INakedElement, INakedPackage> implements TransformationStep {
	protected INakedModelWorkspace workspace;

	@Override
	public Collection<? extends INakedElement> getChildren(INakedElement root) {
		return root.getOwnedElements();
	}

	public void initialize(INakedModelWorkspace workspace) {
		this.workspace = workspace;
	}
}
