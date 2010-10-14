package net.sf.nakeduml.metamodel.workspace.internal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.feature.visit.VisitorAdapter;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedComplexStructure;
import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.metamodel.core.INakedPackage;
import net.sf.nakeduml.metamodel.core.internal.ClassifierDependencyComparator;
public class ClassElementCollector<E extends INakedComplexStructure> extends VisitorAdapter<INakedElement,INakedPackage> {
	private Set<E> classElements = new HashSet<E>();
	private Class<E> type;
	public ClassElementCollector(Class<E> type) {
		this.type = type;
	}
	@VisitBefore(matchSubclasses=true)
	public void collectClass(INakedClassifier c) {
		if (type.isInstance(c)) {
			classElements.add(type.cast(c));
		}
	}
	protected Set<E> getClassElements() {
		return classElements;
	}
	public List<E> getExternalClasses(Class<E> c) {
		List<E> result = new ArrayList<E>();
		for (E type : getClassElements()) {
			ClassifierDependencyComparator.addTo(c,type, result, 10);
		}
		result.removeAll(getClassElements());
		return result;
	}
	@Override
	public Collection<? extends INakedElement> getChildren(INakedElement root){
		return root.getOwnedElements();
	}
}
