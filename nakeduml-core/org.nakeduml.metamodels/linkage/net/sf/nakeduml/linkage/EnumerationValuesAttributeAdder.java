package net.sf.nakeduml.linkage;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.metamodel.core.INakedEnumeration;
import net.sf.nakeduml.metamodel.core.internal.NakedMultiplicityImpl;
import net.sf.nakeduml.metamodel.core.internal.NakedPropertyImpl;
import net.sf.nakeduml.metamodel.core.internal.emulated.EmulatingElement;
import net.sf.nakeduml.metamodel.mapping.internal.MappingInfoImpl;
import net.sf.nakeduml.metamodel.name.SingularNameWrapper;

@StepDependency(phase = LinkagePhase.class, before = { NakedParsedOclStringResolver.class, TypeResolver.class })
public class EnumerationValuesAttributeAdder extends AbstractModelElementLinker {
	@VisitBefore(matchSubclasses = true)
	public void enumeration(INakedEnumeration e) {
		NakedPropertyImpl values = new NakedPropertyImpl();
		values.setName("values");
		values.setBaseType(e);
		values.setMultiplicity(new NakedMultiplicityImpl("0", "*"));
		MappingInfoImpl mi = (MappingInfoImpl) e.getMappingInfo().getCopy();
		mi.setJavaName(new SingularNameWrapper("values", "values"));
		mi.setPersistentName(new SingularNameWrapper("values", "values"));
		mi.setNakedUmlId(e.getMappingInfo().getNakedUmlId());
		mi.setQualifiedPersistentName(e.getMappingInfo().getPersistentName() +".values");
		values.initialize("asf", "values",false);
		values.setIsOrdered(false);
		values.setIsUnique(true);
		values.setDerived(true);
		values.setReadOnly(true);
		values.setHasClassScope(true);
		values.setMappingInfo(mi);
		e.addOwnedElement(values);
	}
}
