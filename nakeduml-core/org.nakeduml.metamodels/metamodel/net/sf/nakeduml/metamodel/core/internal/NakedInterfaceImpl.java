package net.sf.nakeduml.metamodel.core.internal;

import java.util.ArrayList;
import java.util.Collection;

import net.sf.nakeduml.metamodel.commonbehaviors.INakedBehavioredClassifier;
import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.metamodel.core.INakedInterface;

public class NakedInterfaceImpl extends NakedClassifierImpl implements INakedInterface{
	private Collection<INakedBehavioredClassifier> implementingClassifiers = new ArrayList<INakedBehavioredClassifier>();
	private static final long serialVersionUID = 1406494153933781228L;
	public static final String META_CLASS = "interface";
	@Override
	public void addOwnedElement(INakedElement element){
		super.addOwnedElement(element);
	}
	@Override
	public String getMetaClass(){
		return META_CLASS;
	}
	@Override
	public boolean getIsAbstract(){
		return true;
	}
	public void addImplementingClassifier(INakedBehavioredClassifier c){
		implementingClassifiers.add(c);
	}
	public Collection<INakedBehavioredClassifier> getImplementingClassifiers(){
		return implementingClassifiers;
	}
	public void removeImplementingClassifier(INakedBehavioredClassifier implementingClassifier){
		this.implementingClassifiers.remove(implementingClassifier);
	}
}
