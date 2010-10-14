package net.sf.nakeduml.obsolete.uimetamodel;

public class UIMParameterParticipation extends UIMTypedElementParticipation{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7890522671996785427L;
	UIMOperation operation;
	@Override
	public UIMElement getOwnerElement(){
		return this.operation;
	}
	public int getArgumentIndex(){
		return getParameter().getArgumentIndex();
	}
	@Override
	public boolean canReceiveInput(){
		return !(getParameter().isReturn() || getParameter().isOut());
	}
	public UIMParameter getParameter(){
		return (UIMParameter) getTypedElement();
	}
	@Override
	public boolean isRequired(){
		return getParameter().isRequired();
	}
	@Override
	public boolean isReadOnly(){
		return getParameter().isReturn();
	}
}
