package net.sf.nakeduml.metamodel.core;


public interface INakedMultiplicityElement extends INakedElement{
	public abstract boolean isOrdered();
	public abstract boolean isUnique();
	public abstract INakedMultiplicity getNakedMultiplicity();
	public abstract void setMultiplicity(INakedMultiplicity nakedMultiplicity);
	public abstract void setIsOrdered(boolean ordered);
	public abstract void setIsUnique(boolean unique);
}