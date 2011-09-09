package net.sf.nakeduml.javageneration.hibernate;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.javageneration.JavaTransformationPhase;
import net.sf.nakeduml.metamodel.core.INakedElementOwner;
import net.sf.nakeduml.metamodel.core.INakedRootObject;
import net.sf.nakeduml.metamodel.models.INakedModel;
import net.sf.nakeduml.metamodel.workspace.INakedModelWorkspace;

@StepDependency(phase = JavaTransformationPhase.class,requires = {},after = {})
public class HibernateConfigGenerator extends AbstractPersistenceConfigGenerator{
	public HibernateConfigGenerator(){
		super();
	}
	protected String getOutputPath(INakedElementOwner model){
		if(model instanceof INakedModel){
			return ((INakedRootObject) model).getIdentifier() + "-hibernate.cfg.xml";
		}else{
			return ((INakedModelWorkspace) model).getIdentifier() + "-hibernate.cfg.xml";
		}
	}
	protected String getConfigName(INakedElementOwner model){
		return getOutputPath(model);
	}
	protected String getTemplateName(){
		return "templates/Model/Jbpm5HibernateConfig.vsl";
	}
	@Override
	protected String getDomainEnvironmentImplementation(){
		return "org.nakeduml.hibernate.domain.HibernateEnvironment";
	}
	@Override
	protected String getAdaptorEnvironmentImplementation(){
		return "org.nakeduml.environment.adaptor.CdiEnvironment";
	}
}
