package net.sf.nakeduml.linkage;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.metamodel.core.INakedEnumeration;
import net.sf.nakeduml.metamodel.core.internal.NakedMultiplicityImpl;
import net.sf.nakeduml.metamodel.core.internal.NakedPropertyImpl;
import net.sf.nakeduml.metamodel.mapping.internal.MappingInfoImpl;
import net.sf.nakeduml.metamodel.name.SingularNameWrapper;
import nl.klasse.octopus.model.CollectionMetaType;

@StepDependency(phase=LinkagePhase.class)
public class EnumerationValuesAttributeAdder extends AbstractModelElementLinker{
	@VisitBefore(matchSubclasses=true)
	public void enumeration(INakedEnumeration e){
		NakedPropertyImpl values = new NakedPropertyImpl();
		values.setBaseType(e);
		values.setMultiplicity(new NakedMultiplicityImpl("0", "*"));
		values.setType(workspace.getOclEngine().getOclLibrary().lookupCollectionType(CollectionMetaType.SEQUENCE, e));
		MappingInfoImpl mi = new MappingInfoImpl();
		mi.setJavaName(new SingularNameWrapper("values", "values"));
		values.initialize("asf", "values");
		values.setDerived(true);
		values.setReadOnly(true);
		values.setHasClassScope(true);
		values.setMappingInfo(mi);
		e.addOwnedElement(values);
		
	}
}
