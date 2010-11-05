package net.sf.nakeduml.metamodel.commonbehaviors.internal;

import java.util.List;

import net.sf.nakeduml.metamodel.commonbehaviors.INakedOpaqueBehavior;
import net.sf.nakeduml.metamodel.core.INakedConstraint;
import net.sf.nakeduml.metamodel.core.INakedParameter;
import net.sf.nakeduml.metamodel.core.INakedValueSpecification;
import net.sf.nakeduml.metamodel.core.internal.NakedConstraintImpl;
import net.sf.nakeduml.metamodel.core.internal.emulated.TypedElementPropertyBridge;
import nl.klasse.octopus.model.IAttribute;
import nl.klasse.octopus.oclengine.IOclContext;

public class NakedOpaqueBehaviorImpl extends NakedBehaviorImpl implements INakedOpaqueBehavior {
	private static final long serialVersionUID = 4959233999272640273L;
	private INakedValueSpecification body;

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

	public INakedValueSpecification getBody() {
		return body;
	}

	@Override
	public INakedConstraint getBodyCondition() {
		NakedConstraintImpl bodyConstraint = new NakedConstraintImpl();
		if (body != null) {
			bodyConstraint.setSpecification(body);
		}
		return bodyConstraint;
	}

	public void setBody(INakedValueSpecification body) {
		this.body = body;
		super.addOwnedElement(body);
	}

	@Override
	public String getMetaClass() {
		return "opaqueBehavior";
	}

	public IOclContext getBodyExpression() {
		if (this.body != null) {
			return this.body.getOclValue();
		} else {
			return null;
		}
	}

	public boolean isProcess() {
		return false;
	}
}
