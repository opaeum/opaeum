package org.opaeum.linkage;

import org.opaeum.feature.MappingInfo;
import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitBefore;
import org.opaeum.metamodel.core.INakedEnumeration;
import org.opaeum.metamodel.core.INakedProperty;
import org.opaeum.metamodel.core.internal.NakedMultiplicityImpl;
import org.opaeum.metamodel.core.internal.NakedPropertyImpl;
import org.opaeum.metamodel.name.SingularNameWrapper;

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
			mi.setOpaeumId(e.getMappingInfo().getOpaeumId());
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
