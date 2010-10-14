package net.sf.nakeduml.obsolete.uimetamodel;

import java.io.Serializable;

public class UIMParameter extends UIMTypedElement implements Serializable{
	private static final long serialVersionUID = -2346843065274962264L;
	private UIMEntity owner;
	private boolean isReturn;
	private boolean isOut;
	private int argumentIndex;
	private UIMOperation operation;
	@Override
	public final UIMEntity getOwner(){
		return this.owner;
	}
	public final boolean isReturn(){
		return this.isReturn;
	}
	public final int getArgumentIndex(){
		return this.argumentIndex;
	}
	public final UIMOperation getOperation(){
		return this.operation;
	}
	public final void setArgumentIndex(int argumentIndex){
		this.argumentIndex = argumentIndex;
	}
	public final void setReturn(boolean isReturn){
		this.isReturn = isReturn;
	}
	public final void setOwner(UIMEntity owner){
		this.owner = owner;
	}
	@Override
	public String getMetaClass() {
		return "paramater";
	}
	@Override
	public UIMElement getOwnerElement() {
		return this.operation;
	}
	public boolean isOut(){
		return this.isOut;
	}
}
