package net.sf.nakeduml.metamodel.core.internal;
import java.util.Collection;
import java.util.List;

import net.sf.nakeduml.metamodel.core.INakedComplexStructure;
import net.sf.nakeduml.metamodel.core.INakedProperty;
public class ClassifierDependencyComparator {
	// public int compare(Object o1, Object o2) {
	// int result = 0;
	// try {
	// INakedStructuredType c1 = (INakedStructuredType) o1;
	// INakedStructuredType c2 = (INakedStructuredType) o2;
	// if (c1.equals(c2)) {
	// result = 0;
	// } else if (c1 instanceof INakedEntity && ((INakedEntity)
	// c1).isRootEntity()) {
	// result = -1;
	// } else if (c2 instanceof INakedEntity && ((INakedEntity)
	// c2).isRootEntity()) {
	// result = 1;
	// } else if (Conformance.conformsTo(c2, c1)) {
	// result = -1;
	// } else if (Conformance.conformsTo(c1, c2)) {
	// result = 1;
	// } else if (dependsOn(c2, c1, 20)) {
	// result = -1;
	// } else if (dependsOn(c1, c2, 20)) {
	// result = 1;
	// } else if (!c1.hasComposite() && !c1.hasSupertype() && (c2.hasComposite()
	// || c2.hasSupertype())) {
	// result = -1;
	// } else if (!c2.hasComposite() && !c2.hasSupertype() && (c1.hasComposite()
	// || c1.hasSupertype())) {
	// result = 1;
	// } else {
	// result =
	// c2.getMappingInfo().getIdInModel().compareTo(c1.getMappingInfo().getIdInModel());
	// }
	// } catch (ClassCastException e) {
	// e.printStackTrace();
	// result = 0;
	// }
	// return result;
	// }
	public static <E extends INakedComplexStructure> void addTo(Class<E> type, E classElement, List<E> list, int level) {
		if (level > 0) {
			level--;
			if (!list.contains(classElement)) {
				for (E c : (Collection<E>)(Collection) classElement.getGeneralizations()) {
					addTo(type, c, list, level);
				}
				for (INakedProperty a : classElement.getOwnedAttributes()) {
					if (!a.isDerived() && !a.isInverse()) {
						if (type.isInstance(a.getNakedBaseType())) {
							addTo(type, type.cast(a.getNakedBaseType()), list, level);
						}
					}
				}
				list.add(classElement);
			}
		}
	}
	// private static boolean dependsOn(INakedStructuredType c1,
	// INakedStructuredType c2, int level) {
	// Iterator attributes = c1.getAllNakedAttributes().iterator();
	// while (attributes.hasNext() && level > 0) {
	// INakedProperty a = (INakedProperty) attributes.next();
	// if (!a.isDerived()) {
	// if (a.isManyToOne() || ((a.isManyToMany() || a.isOneToOne()) &&
	// !a.isInverse())
	// || (a.isMany() && a.getBaseType() instanceof INakedStructuredDataType)) {
	// if (a.getBaseType().conformsTo(c2)) {
	// return true;
	// } else if (a.getBaseType() instanceof INakedStructuredType) {
	// if (dependsOn((INakedStructuredType) a.getBaseType(), c2, --level)) {
	// return true;
	// }
	// }
	// }
	// }
	// }
	// return false;
	// }
}
