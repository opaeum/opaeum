package net.sf.nakeduml.metamodel.commonbehaviors.internal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import net.sf.nakeduml.metamodel.commonbehaviors.INakedSignal;
import net.sf.nakeduml.metamodel.core.INakedProperty;
import net.sf.nakeduml.metamodel.core.internal.NakedClassifierImpl;
import nl.klasse.octopus.model.IDataType;

public class NakedSignalImpl extends NakedClassifierImpl implements INakedSignal,IDataType{
	private static final long serialVersionUID = 5492485182705048910L;
	public static final String META_CLASS = "signal";
	public NakedSignalImpl(){
	}
	@Override
	public String getMetaClass(){
		return META_CLASS;
	}
	public List<INakedProperty> getArgumentParameters(){
		List<INakedProperty> results = new ArrayList<INakedProperty>();
		if(hasSupertype()){
			results.addAll(((NakedSignalImpl) getSupertype()).getArgumentParameters());
		}
		results.addAll(getOwnedArgumentParamaters());
		return results;
	}
	@SuppressWarnings("unchecked")
	private List<INakedProperty> getOwnedArgumentParamaters(){
		List<INakedProperty> list = new ArrayList(getOwnedAttributes());
		Collections.<INakedProperty>sort(list, new Comparator<INakedProperty>(){
			public int compare(INakedProperty o1,INakedProperty o2){
				return o1.getOwnedAttributeIndex() - o2.getOwnedAttributeIndex();
			}
		});
		Iterator iter = list.iterator();
		while(iter.hasNext()){
			INakedProperty prop = (INakedProperty) iter.next();
			if(prop.isDerived() || prop.isReadOnly()){
				iter.remove();
			}
		}
		return list;
	}
	@Override
	public List getStates(){
		return Collections.EMPTY_LIST;
	}
	public boolean isPersistent(){
		return false;
	}
}