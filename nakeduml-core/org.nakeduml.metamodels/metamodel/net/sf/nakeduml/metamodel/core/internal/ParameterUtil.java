package net.sf.nakeduml.metamodel.core.internal;

import java.util.ArrayList;
import java.util.List;

import net.sf.nakeduml.metamodel.core.INakedMultiplicityElement;
import net.sf.nakeduml.metamodel.core.INakedOperation;
import net.sf.nakeduml.metamodel.core.INakedParameter;
import nl.klasse.octopus.model.IClassifier;
import nl.klasse.octopus.model.IParameter;
import nl.klasse.tools.common.Util;

public class ParameterUtil{

	public static void addParameterTolist(INakedMultiplicityElement p,int index,List list){
		if(index >= 0){
			while(list.size() <= index){
				list.add(null);
			}
			list.set(index, p);
		}
	}

	public static List<IClassifier> parameterTypes(List<INakedParameter> args){
		List<IClassifier> result = new ArrayList<IClassifier>();
		for(IParameter par:args){
			result.add(par.getType());
		}
		return result;
	}

	public static String signature(INakedOperation owner){
		String paramstr = Util.collectionToString(owner.getArgumentParameters(), ", ");
		String result = owner.getVisibility() + " " + owner.getName() + "( " + paramstr + " )";
		if(owner.getReturnParameter()== null){
			return result;
		}else{
			return result + " : " + owner.getReturnParameter().getType().getName();
		}
	}
}
