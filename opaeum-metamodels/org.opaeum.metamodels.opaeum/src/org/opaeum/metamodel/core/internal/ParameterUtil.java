package org.opaeum.metamodel.core.internal;

import java.util.ArrayList;
import java.util.List;

import nl.klasse.octopus.model.IClassifier;
import nl.klasse.octopus.model.IParameter;
import nl.klasse.tools.common.Util;

import org.opaeum.metamodel.commonbehaviors.INakedReception;
import org.opaeum.metamodel.core.INakedMultiplicityElement;
import org.opaeum.metamodel.core.INakedOperation;
import org.opaeum.metamodel.core.INakedParameter;

public class ParameterUtil{
	@SuppressWarnings({
			"rawtypes","unchecked"
	})
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
	public static String toIdentifyingString(INakedOperation o){
		return o.getName() + "(" + Util.collectionToString(o.getArgumentParameters(), ", ") + ")";
	}
	public static String fullSignature(INakedOperation owner){
		String paramstr = Util.collectionToString(owner.getArgumentParameters(), ", ");
		String result = owner.getVisibility() + " " + owner.getName() + "( " + paramstr + " )";
		if(owner.getReturnParameter() == null){
			return result;
		}else{
			return result + " : " + owner.getReturnParameter().getType().getName();
		}
	}
	public static String toIdentifyingString(INakedReception o){
		// TODO Auto-generated method stub
		return o.getName() + "(" + o.getSignal().toString() + ")";
	}
}
