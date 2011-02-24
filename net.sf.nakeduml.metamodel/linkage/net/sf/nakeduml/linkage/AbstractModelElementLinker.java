package net.sf.nakeduml.linkage;

import java.util.Collection;
import java.util.HashSet;

import net.sf.nakeduml.feature.NakedUmlConfig;
import net.sf.nakeduml.feature.TransformationStep;
import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.metamodel.core.INakedElementOwner;
import net.sf.nakeduml.metamodel.core.INakedPackage;
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

	protected MappedTypes getBuiltInTypes() {
		return workspace.getMappedTypes();
	}

	protected ErrorMap getErrorMap() {
		return workspace.getErrorMap();
	}

	@Override
	public final Collection<? extends INakedElementOwner> getChildren(INakedElementOwner root) {
		if (root instanceof INakedModelWorkspace) {
			Collection<INakedElement> children = new HashSet<INakedElement>();
			for (INakedElement element : root.getOwnedElements()) {
				if (!(isLinkedModel(element))) {
					children.add(element);
				}
			}
			for (INakedElement e : children) {
				if(e instanceof INakedPackage && ((INakedPackage) e).isLinked()){
					System.out.println();
				}
			}
			return children;
		} else {
			return root.getOwnedElements();
		}
	}

	private boolean isLinkedModel(INakedElement iNakedElement) {
		return iNakedElement instanceof INakedPackage && ((INakedPackage) iNakedElement).isLinked();
	}
}
