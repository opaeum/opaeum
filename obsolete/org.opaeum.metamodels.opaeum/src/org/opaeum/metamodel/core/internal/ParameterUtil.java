package org.opaeum.metamodel.core.internal;

import java.util.ArrayList;
import java.util.List;

import nl.klasse.octopus.model.IClassifier;
import nl.klasse.octopus.model.IParameter;
import nl.klasse.tools.common.Util;

import org.eclipse.uml2.uml.INakedMultiplicityElement;
import org.eclipse.uml2.uml.INakedOperation;
import org.eclipse.uml2.uml.INakedParameter;
import org.eclipse.uml2.uml.INakedReception;

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
		return o.getName() + "(" + (o.getSignal() == null ? "nosignal" : o.getSignal().toString()) + ")";
	}
}
