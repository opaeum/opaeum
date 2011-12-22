package org.nakeduml.tinker.audit;

import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.metamodel.core.INakedClassifier;

public class OJAuditUtil {

	public static OJPathName classifierAuditPathname(INakedClassifier classifier) {
		if (classifier instanceof INakedClassifier && (classifier).getMappedImplementationType() != null) {
			return new OJPathName(classifier.getMappedImplementationType()+TinkerAuditGenerationUtil.AUDIT);
		} else {
			OJPathName path = OJUtil.packagePathname(classifier.getNameSpace()).getDeepCopy();
			path.addToNames(classifier.getName()+TinkerAuditGenerationUtil.AUDIT);
			return path;
		}
	}
	
}
