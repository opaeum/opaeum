package net.sf.nakeduml.javageneration.bpm;

import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.javametamodel.OJPathName;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedElement;

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
}
