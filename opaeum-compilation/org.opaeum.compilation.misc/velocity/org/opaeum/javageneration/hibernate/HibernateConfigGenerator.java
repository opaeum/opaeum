package org.opaeum.javageneration.hibernate;

import org.opaeum.feature.StepDependency;
import org.opaeum.javageneration.JavaTransformationPhase;
import org.opaeum.metamodel.core.INakedElementOwner;
import org.opaeum.metamodel.core.INakedRootObject;
import org.opaeum.metamodel.models.INakedModel;
import org.opaeum.metamodel.workspace.INakedModelWorkspace;

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
		return "org.opaeum.hibernate.domain.HibernateEnvironment";
	}
	@Override
	protected String getAdaptorEnvironmentImplementation(){
		return "org.opaeum.environment.adaptor.CdiEnvironment";
	}
}
