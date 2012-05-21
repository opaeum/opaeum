package org.nakeduml.tinker.activity.maps;

import nl.klasse.octopus.expressions.internal.types.PathName;

import org.nakeduml.tinker.generator.TinkerBehaviorUtil;
import org.opaeum.metamodel.activities.INakedPin;
import org.opaeum.metamodel.core.INakedNameSpace;
import org.opaeum.name.NameConverter;

public class ConcretePinEmulatedClassifier extends ConcreteEmulatedClassifier {
	private static final long serialVersionUID = -7051187260422402462L;

	private INakedPin pin;
	
	public ConcretePinEmulatedClassifier(INakedNameSpace owner,INakedPin pin) {
		super(owner, pin);
		this.pin = pin;
	}
	
	public INakedPin getPin() {
		return pin;
	}

	@Override
	public String getName() {
		return TinkerBehaviorUtil.pinPathName(this.pin);
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public PathName getPathName(){
		PathName p = owner.getPathName();
		p.addString(NameConverter.capitalize(this.pin.getAction().getName()) + NameConverter.capitalize(getName()));
		return p;
	}

}
