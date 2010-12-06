package net.sf.nakeduml.javageneration.hibernate;

import net.sf.nakeduml.feature.NakedUmlConfig;
import net.sf.nakeduml.feature.TransformationStep;
import net.sf.nakeduml.javametamodel.OJPackage;
import net.sf.nakeduml.metamodel.workspace.INakedModelWorkspace;
import net.sf.nakeduml.textmetamodel.TextWorkspace;

public interface StandaloneHibernateStep extends TransformationStep {

	void initialize(INakedModelWorkspace workspace, NakedUmlConfig config, TextWorkspace textWorkspace, OJPackage javaModel);

	void generate();

}
