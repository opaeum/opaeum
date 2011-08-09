package net.sf.nakeduml.validation.namegeneration;
import java.util.Iterator;

import net.sf.nakeduml.feature.ITransformationStep;
import net.sf.nakeduml.feature.visit.VisitSpec;
import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.metamodel.core.INakedElementOwner;
import net.sf.nakeduml.metamodel.core.INakedInstanceSpecification;
import net.sf.nakeduml.metamodel.core.INakedValueSpecification;
import net.sf.nakeduml.metamodel.visitor.NakedElementOwnerVisitor;
public abstract class AbstractNameGenerator extends NakedElementOwnerVisitor implements ITransformationStep{
	protected static INakedValueSpecification getTaggedValue(INakedElement element, String... tags) {
		try {
			 Iterator<? extends INakedInstanceSpecification> iter = element.getStereotypes().iterator();
			while (iter.hasNext()) {
				INakedInstanceSpecification is = iter.next();
				for (String tag : tags) {
					if (is.hasValueForFeature(tag)) {
						return is.getFirstValueFor(tag);
					}
				}
			}
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	protected void maybeVisit(INakedElementOwner o,VisitSpec v){
		super.maybeVisit(o, v);
	}
	
}