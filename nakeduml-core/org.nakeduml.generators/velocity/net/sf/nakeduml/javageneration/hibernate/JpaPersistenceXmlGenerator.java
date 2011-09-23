package net.sf.nakeduml.javageneration.hibernate;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.javageneration.JavaTransformationPhase;
import net.sf.nakeduml.metamodel.core.INakedElementOwner;
import net.sf.nakeduml.metamodel.core.INakedRootObject;
import net.sf.nakeduml.metamodel.models.INakedModel;
import net.sf.nakeduml.metamodel.workspace.INakedModelWorkspace;

@StepDependency(phase = JavaTransformationPhase.class,requires = {},after = {})
public class JpaPersistenceXmlGenerator extends AbstractPersistenceConfigGenerator{
	public JpaPersistenceXmlGenerator(){
		super();
	}
	protected String getOutputPath(INakedElementOwner model){
		return "META-INF/persistence.xml";
	}
	protected String getConfigName(INakedElementOwner model){
		if(model instanceof INakedModel){
			return ((INakedRootObject) model).getIdentifier();
		}else{
			return ((INakedModelWorkspace) model).getIdentifier();
		}
	}
	protected String getTemplateName(){
		return "templates/Model/PersistenceXml.vsl";
	}
	@Override
	protected String getDomainEnvironmentImplementation(){
		return "org.nakeduml.runtime.jpa.JpaEnvironment";
	}
	@Override
	protected String getAdaptorEnvironmentImplementation(){
		return "org.nakeduml.environment.seam2.Seam2Environment";
	}
}
