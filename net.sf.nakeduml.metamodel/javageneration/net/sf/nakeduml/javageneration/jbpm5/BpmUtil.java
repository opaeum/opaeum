package net.sf.nakeduml.javageneration.jbpm5;

import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.javametamodel.OJPathName;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedElement;
import nl.klasse.octopus.codegen.umlToJava.modelgenerators.visitors.UtilityCreator;

public class BpmUtil{
	public static String stepLiteralName(INakedElement s){
		return (s).getMappingInfo().getJavaName().getAsIs().toUpperCase();
	}
	public static OJPathName asyncInterfaceOf(INakedClassifier target){
		OJPathName result = OJUtil.classifierPathname(target);
		String name = "IAsync" + result.getLast();
		result=result.getHead();
		result.addToNames(name);
		return result;
	}
	public static OJPathName getJbpm5Environment() {
		return UtilityCreator.getUtilPathName().append("Jbpm5Environment");
	}
	public static OJPathName getNodeInstance() {
		return new OJPathName("org.jbpm.workflow.instance.NodeInstance");
	}
}
