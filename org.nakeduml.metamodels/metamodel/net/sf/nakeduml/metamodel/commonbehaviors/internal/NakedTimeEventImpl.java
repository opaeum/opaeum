package net.sf.nakeduml.metamodel.commonbehaviors.internal;
import java.util.Collection;
import java.util.Collections;

import net.sf.nakeduml.metamodel.commonbehaviors.INakedTimeEvent;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.metamodel.core.INakedInstanceSpecification;
import net.sf.nakeduml.metamodel.core.INakedValueSpecification;
import net.sf.nakeduml.metamodel.core.internal.NakedModelElementImpl;
import net.sf.nakeduml.metamodel.name.NameWrapper;
import net.sf.nakeduml.metamodel.name.SingularNameWrapper;

import org.nakeduml.runtime.domain.TimeUnit;
public class NakedTimeEventImpl extends NakedModelElementImpl implements INakedTimeEvent {
	static public final String TIME_UNIT = "timeUnit";
	private static final long serialVersionUID = -4132717082708308377L;
	private boolean isRelative;
	private TimeUnit timeUnit;
	private INakedValueSpecification when;
	public NakedTimeEventImpl() {
		super();
	}
	@Override
	public String getMetaClass() {
		return "TimeTrigger";
	}
	public NameWrapper getTimeUnitName() {
		return new SingularNameWrapper(getTimeUnit().getName(), null);
	}
	public boolean isRelative() {
		return this.isRelative;
	}
	public void setRelative(boolean relative) {
		this.isRelative = relative;
	}
	public void setTimeUnit(TimeUnit timeUnit) {
		this.timeUnit = timeUnit;
	}
	public TimeUnit getTimeUnit() {
		if (this.timeUnit == null) {
			return TimeUnit.BUSINESS_DAY;
		} else {
			return this.timeUnit;
		}
	}
	public void setWhen(INakedValueSpecification when) {
		this.when = when;
	}
	public INakedValueSpecification getWhen() {
		return this.when;
	}
	@Override
	public void addStereotype(INakedInstanceSpecification stereotype) {
		super.addStereotype(stereotype);
		if (stereotype.hasValueForFeature(TIME_UNIT)) {
			this.timeUnit = TimeUnit.lookup(stereotype.getFirstValueFor(TIME_UNIT).stringValue());
		}
	}
	@Override
	public Collection<INakedElement> getOwnedElements() {
		if (when == null) {
			return Collections.<INakedElement> emptySet();
		} else {
			return Collections.<INakedElement> singleton(when);
		}
	}
	@Override
	public void addOwnedElement(INakedElement element) {
	}
	public INakedClassifier getContext() {
		return (INakedClassifier) getOwnerElement();
	}
}
