package org.opeum.javageneration.hibernate;

import org.opeum.feature.StepDependency;
import org.opeum.javageneration.JavaTransformationPhase;
import org.opeum.metamodel.core.INakedElementOwner;
import org.opeum.metamodel.core.INakedRootObject;
import org.opeum.metamodel.models.INakedModel;
import org.opeum.metamodel.workspace.INakedModelWorkspace;

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
		return "org.opeum.hibernate.domain.HibernateEnvironment";
	}
	@Override
	protected String getAdaptorEnvironmentImplementation(){
		return "org.opeum.environment.adaptor.CdiEnvironment";
	}
}
