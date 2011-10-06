package org.opeum.eclipse;

import org.eclipse.uml2.uml.BehavioredClassifier;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.InterfaceRealization;

public class EmfClassifierUtil{
	public static boolean conformsTo(Classifier from,Classifier to){
		if(from.equals(to)){
			return true;
		}else if(from.allParents().contains(to)){
			return true;
		}else if(from instanceof BehavioredClassifier){
			for(InterfaceRealization i:((BehavioredClassifier) from).getInterfaceRealizations()){
				if(i.getContract().equals(to)|| i.getContract().allParents().contains(to)){
					return true;
				}
			}
		}
		return false;
	}
}
