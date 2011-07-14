package org.nakeduml.tinker.passbyvalue;

import net.sf.nakeduml.javageneration.passbyvalue.DtoImplementationStep;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.metamodel.core.INakedClassifier;

import org.nakeduml.java.metamodel.OJPathName;

public class PassByValueUtil {

	public static OJPathName classifierDtoPathname(INakedClassifier classifier) {
		if (classifier instanceof INakedClassifier && (classifier).getMappedImplementationType() != null) {
			return new OJPathName(classifier.getMappedImplementationType()+DtoImplementationStep.DTO);
		} else {
			OJPathName path = OJUtil.packagePathname(classifier.getNameSpace());
			path.addToNames(classifier.getName());
			return path;
		}
	}

	public static OJPathName classifierAssemblerPathname(INakedClassifier classifier) {
		if (classifier instanceof INakedClassifier && (classifier).getMappedImplementationType() != null) {
			return new OJPathName(classifier.getMappedImplementationType()+DtoImplementationStep.ASSEMBLER);
		} else {
			OJPathName path = OJUtil.packagePathname(classifier.getNameSpace());
			path.addToNames(classifier.getName());
			return path;
		}
	}

	public static OJPathName classifierControllerPathname(INakedClassifier classifier) {
		if (classifier instanceof INakedClassifier && (classifier).getMappedImplementationType() != null) {
			return new OJPathName(classifier.getMappedImplementationType()+DtoImplementationStep.CTRL);
		} else {
			OJPathName path = OJUtil.packagePathname(classifier.getNameSpace());
			path.addToNames(classifier.getName());
			return path;
		}
	}

	public static OJPathName classifierWsInterfacePathname(INakedClassifier classifier) {
		if (classifier instanceof INakedClassifier && (classifier).getMappedImplementationType() != null) {
			return new OJPathName(classifier.getMappedImplementationType()+DtoImplementationStep.WS);
		} else {
			OJPathName path = OJUtil.packagePathname(classifier.getNameSpace());
			path.addToNames(classifier.getName());
			return path;
		}
	}
	
}
