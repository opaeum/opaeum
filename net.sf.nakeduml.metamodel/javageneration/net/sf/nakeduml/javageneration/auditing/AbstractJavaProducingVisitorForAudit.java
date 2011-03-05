package net.sf.nakeduml.javageneration.auditing;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import net.sf.nakeduml.feature.NakedUmlConfig;
import net.sf.nakeduml.feature.TransformationContext;
import net.sf.nakeduml.javageneration.AbstractJavaProducingVisitor;
import net.sf.nakeduml.javametamodel.OJPackage;
import net.sf.nakeduml.javametamodel.OJPathName;
import net.sf.nakeduml.metamodel.workspace.INakedModelWorkspace;
import net.sf.nakeduml.textmetamodel.TextWorkspace;

public class AbstractJavaProducingVisitorForAudit extends AbstractJavaProducingVisitor {

	protected Map<String, OJPathName> classPathNames = new HashMap<String, OJPathName>();
	public void setAuditableClasses(Set<OJPathName> classes) {
		for (OJPathName clazz : classes) {
			this.classPathNames.put(clazz.toJavaString() ,clazz);
		}
	}

}
