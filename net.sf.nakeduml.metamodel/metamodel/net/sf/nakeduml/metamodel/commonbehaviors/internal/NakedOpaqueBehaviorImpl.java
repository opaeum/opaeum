package net.sf.nakeduml.metamodel.commonbehaviors.internal;

import java.util.List;

import net.sf.nakeduml.metamodel.commonbehaviors.INakedOpaqueBehavior;
import net.sf.nakeduml.metamodel.core.INakedParameter;
import net.sf.nakeduml.metamodel.core.INakedValueSpecification;
import net.sf.nakeduml.metamodel.core.internal.NakedValueSpecificationImpl;
import net.sf.nakeduml.metamodel.core.internal.emulated.TypedElementPropertyBridge;
import nl.klasse.octopus.model.IAttribute;
import nl.klasse.octopus.oclengine.IOclContext;

public class NakedOpaqueBehaviorImpl extends NakedBehaviorImpl implements INakedOpaqueBehavior {
	private static final long serialVersionUID = 4959233999272640273L;
	private IOclContext bodyExpression;

	public IOclContext getBodyExpression() {
		return bodyExpression;
	}

	public void setBodyExpression(IOclContext bodyExpression) {
		this.bodyExpression = bodyExpression;
	}

	public NakedOpaqueBehaviorImpl() {
	}

	@Override
	protected List<IAttribute> getAllAttributesForOcl(boolean classScope) {
		List<IAttribute> results = super.getAllAttributesForOcl(classScope);
		if (!classScope) {
			for (INakedParameter p : getArgumentParameters()) {
				results.add(new TypedElementPropertyBridge(this, p));
			}
		}
		return results;
	}


	@Override
	public String getMetaClass() {
		return "opaqueBehavior";
	}


	public boolean isProcess() {
		return false;
	}

	@Override
	public INakedValueSpecification getBody() {
		if (getBodyExpression() == null) {
			return null;
		} else {
			return new NakedValueSpecificationImpl(getBodyExpression());
		}
	}
}
