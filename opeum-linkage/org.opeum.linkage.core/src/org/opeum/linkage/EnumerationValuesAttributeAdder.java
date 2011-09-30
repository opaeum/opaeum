package org.opeum.linkage;

import org.opeum.feature.MappingInfo;
import org.opeum.feature.StepDependency;
import org.opeum.feature.visit.VisitBefore;
import org.opeum.metamodel.core.INakedEnumeration;
import org.opeum.metamodel.core.INakedProperty;
import org.opeum.metamodel.core.internal.NakedMultiplicityImpl;
import org.opeum.metamodel.core.internal.NakedPropertyImpl;
import org.opeum.metamodel.name.SingularNameWrapper;

@StepDependency(phase = LinkagePhase.class,before = {
		NakedParsedOclStringResolver.class,TypeResolver.class
})
public class EnumerationValuesAttributeAdder extends AbstractModelElementLinker{
	@VisitBefore(matchSubclasses = true)
	public void enumeration(INakedEnumeration e){
		if(e.findClassAttribute("values") == null){
			NakedPropertyImpl values = new NakedPropertyImpl();
			values.setName("values");
			values.setBaseType(e);
			values.setMultiplicity(new NakedMultiplicityImpl("0", "*"));
			MappingInfo mi = (MappingInfo) e.getMappingInfo().getCopy();
			mi.setJavaName(new SingularNameWrapper("values", "values"));
			mi.setPersistentName(new SingularNameWrapper("values", "values"));
			mi.setOpeumId(e.getMappingInfo().getOpeumId());
			values.initialize("asf", "values", false);
			values.setIsOrdered(false);
			values.setIsUnique(true);
			values.setDerived(true);
			values.setReadOnly(true);
			values.setHasClassScope(true);
			values.setMappingInfo(mi);
			e.addOwnedElement(values);
		}
		for(INakedProperty p:e.getOwnedAttributes()){
			p.setReadOnly(true);
		}
	}
}
