package net.sf.nakeduml.javageneration.auditing;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import net.sf.nakeduml.feature.NakedUmlConfig;
import net.sf.nakeduml.javageneration.AbstractJavaProducingVisitor;
import net.sf.nakeduml.javametamodel.OJPackage;
import net.sf.nakeduml.javametamodel.OJPathName;
import net.sf.nakeduml.metamodel.workspace.INakedModelWorkspace;
import net.sf.nakeduml.textmetamodel.TextWorkspace;

public class AbstractJavaProducingVisitorForAudit extends AbstractJavaProducingVisitor {
	protected Map<String, OJPathName> classPathNames = new HashMap<String, OJPathName>();
	public void initialize(INakedModelWorkspace workspace, OJPackage javaModel, NakedUmlConfig config, TextWorkspace textWorkspace, Set<OJPathName> classes) {
		super.initialize(workspace, javaModel, config, textWorkspace);
		for (OJPathName clazz : classes) {
			this.classPathNames.put(clazz.toJavaString() ,clazz);
		}		
	}

}
