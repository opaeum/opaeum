package net.sf.nakeduml.javageneration.auditing;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import net.sf.nakeduml.javageneration.AbstractJavaProducingVisitor;

import org.nakeduml.java.metamodel.OJPathName;

public class AbstractJavaProducingVisitorForAudit extends AbstractJavaProducingVisitor {

	protected Map<String, OJPathName> classPathNames = new HashMap<String, OJPathName>();
	public void setAuditableClasses(Set<OJPathName> classes) {
		for (OJPathName clazz : classes) {
			this.classPathNames.put(clazz.toJavaString() ,clazz);
		}
	}

}
