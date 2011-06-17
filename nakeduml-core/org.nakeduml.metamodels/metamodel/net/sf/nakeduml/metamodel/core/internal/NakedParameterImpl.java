package net.sf.nakeduml.metamodel.core.internal;

import net.sf.nakeduml.metamodel.commonbehaviors.INakedBehavior;
import net.sf.nakeduml.metamodel.core.INakedParameter;
import net.sf.nakeduml.metamodel.core.INakedTypedElement;
import nl.klasse.octopus.model.IOperation;
import nl.klasse.octopus.model.ParameterDirectionKind;

public class NakedParameterImpl extends NakedTypedElementImpl implements INakedTypedElement, INakedParameter {
	private static final long serialVersionUID = -1300669933351165122L;
	private int argumentIndex;
	private int resultIndex;
	private int exceptionIndex;
	private boolean isException = false;
	private boolean isReturn = false;
	private INakedParameter linkedParameter;
	public INakedParameter getLinkedParameter() {
		return linkedParameter;
	}

	public void setLinkedParameter(INakedParameter linkedParameter) {
		this.linkedParameter = linkedParameter;
	}

	private ParameterDirectionKind direction = ParameterDirectionKind.IN;

	public NakedParameterImpl() {
	}

	public boolean isRequired() {
		return getNakedMultiplicity().getLower() >= 1;
	}

	public boolean isOne() {
		return getNakedMultiplicity().getUpper() == 1;
	}

	public int getUpperLimit() {
		return getNakedMultiplicity().getLower();
	}

	public int getLowerLimit() {
		return getNakedMultiplicity().getUpper();
	}

	public boolean isMany() {
		return getNakedMultiplicity().getUpper() > 1;
	}

	@Override
	public String getMetaClass() {
		return "Parameter";
	}

	public void setReturn(boolean isReturn) {
		this.isReturn = isReturn;
	}

	public boolean isReturn() {
		return this.isReturn;
	}

	public IOperation getOwner() {
		if (getOwnerElement() instanceof IOperation) {
			return (IOperation) getOwnerElement();
		}
		return null;
	}

	public INakedBehavior getOwningBehaviour() {
		if (getOwnerElement() instanceof INakedBehavior) {
			return (INakedBehavior) getOwnerElement();
		}
		return null;
	}

	public int getArgumentIndex() {
		return this.argumentIndex;
	}

	public void setArgumentIndex(int index) {
		this.argumentIndex = index;
	}

	public void setDirection(ParameterDirectionKind in) {
		this.direction = in;
	}

	public ParameterDirectionKind getDirection() {
		return this.direction;
	}

	public boolean isException() {
		return this.isException;
	}

	public void setException(boolean isException) {
		this.isException = isException;
	}

	public int getExceptionIndex() {
		return this.exceptionIndex;
	}

	public void setExceptionIndex(int exceptionIndex) {
		this.exceptionIndex = exceptionIndex;
	}

	public int getResultIndex() {
		return this.resultIndex;
	}

	public void setResultIndex(int resultIndex) {
		this.resultIndex = resultIndex;
	}

	@Override
	public boolean isResult() {
		return isReturn() || ParameterDirectionKind.OUT.equals(getDirection()) || ParameterDirectionKind.INOUT.equals(getDirection());
	}

	@Override
	public boolean isArgument() {
		// TODO Auto-generated method stub
		return ParameterDirectionKind.IN.equals(getDirection()) || ParameterDirectionKind.INOUT.equals(getDirection());
	}

}