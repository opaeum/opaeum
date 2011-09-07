package net.sf.nakeduml.metamodel.commonbehaviors.internal;

import java.util.Collection;
import java.util.Collections;

import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.metamodel.core.INakedEnumerationLiteral;
import net.sf.nakeduml.metamodel.core.INakedInstanceSpecification;
import net.sf.nakeduml.metamodel.core.INakedValueSpecification;
import net.sf.nakeduml.metamodel.name.NameWrapper;
import net.sf.nakeduml.metamodel.name.SingularNameWrapper;

import org.nakeduml.runtime.domain.TimeUnit;

public abstract class AbstractTimeEventImpl extends NakedEventImpl{
	static public final String TIME_UNIT = "timeUnit";
	private static final long serialVersionUID = -4132717082708308377L;
	private boolean isRelative;
	private INakedEnumerationLiteral timeUnit;
	private INakedValueSpecification when;
	public NameWrapper getTimeUnitName(){
		return new SingularNameWrapper(getTimeUnit().getName(), null);
	}
	public boolean isRelative(){
		return this.isRelative;
	}
	public void setRelative(boolean relative){
		this.isRelative = relative;
	}
	public INakedEnumerationLiteral getTimeUnit(){
		return this.timeUnit;
	}
	public void setWhen(INakedValueSpecification when){
		removeOwnedElement(this.when);
		this.when = when;
		addOwnedElement(when);
		when.setOwnerElement(this);
	}
	public INakedValueSpecification getWhen(){
		return this.when;
	}
	@Override
	public void addStereotype(INakedInstanceSpecification stereotype){
		super.addStereotype(stereotype);
		if(stereotype.hasValueForFeature(TIME_UNIT)){
			this.timeUnit=(INakedEnumerationLiteral) stereotype.getFirstValueFor(TIME_UNIT).getValue();
		}
	}
	@Override
	public Collection<INakedElement> getOwnedElements(){
		if(when == null){
			return Collections.<INakedElement>emptySet();
		}else{
			return Collections.<INakedElement>singleton(when);
		}
	}
}
