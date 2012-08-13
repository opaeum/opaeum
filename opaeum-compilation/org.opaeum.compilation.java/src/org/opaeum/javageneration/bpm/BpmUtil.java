package org.opaeum.javageneration.bpm;


import org.eclipse.uml2.uml.ActivityNode;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Vertex;
import org.opaeum.eclipse.EmfActivityUtil;
import org.opaeum.eclipse.EmfElementFinder;
import org.opaeum.eclipse.EmfStateMachineUtil;
import org.opaeum.eclipse.PersistentNameUtil;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.javageneration.util.JavaNameGenerator;
import org.opaeum.name.NameConverter;
import org.opaeum.runtime.domain.ExceptionHolder;

public class BpmUtil{
	public static final OJPathName ABSTRACT_TOKEN = new OJPathName("org.opaeum.hibernate.domain.AbstractToken");
	public static final OJPathName RETURN_INFO = new OJPathName("org.opaeum.hibernate.domain.ReturnInfo");
	public static String stepLiteralName(NamedElement s){
		String result="";
		if(s instanceof Vertex){
			result=EmfStateMachineUtil.getStatePath((Vertex) s);
		}else if(s instanceof ActivityNode){
			result=EmfActivityUtil.getNodePath((ActivityNode) s);
		}
		return  JavaNameGenerator.toJavaName(result.replace("::", "_").toUpperCase());
	}
	public static String generateProcessName(Element parameterOwner){
		return PersistentNameUtil.getPersistentName((Element) EmfElementFinder.getContainer( parameterOwner)) + "_" + PersistentNameUtil.getPersistentName( parameterOwner);
	}
	public static String getArtificialJoinName(Element target){
		return "join_for_" + PersistentNameUtil.getPersistentName(target);
	}
	public static String getGuardMethod(NamedElement source, NamedElement flow){
		return JavaNameGenerator.toJavaName("is" + NameConverter.capitalize(source.getName()) + NameConverter.capitalize(flow.getName()));
	}

	public static String getArtificialForkName(Element owner){
		return "fork_for_" + PersistentNameUtil.getPersistentName(owner);
	}
	public static OJPathName getExceptionHolder(){
		return new OJPathName(ExceptionHolder.class.getName());
	}
	public static String endNodeFieldNameFor(NamedElement flow){
		return "endNodeIn" + NameConverter.capitalize(flow.getName());
	}
	public static String getArtificialChoiceName(ActivityNode node){
		return PersistentNameUtil.getPersistentName( node).getAsIs() + "_choice";
	}
}
