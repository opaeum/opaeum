package org.opeum.javageneration.hibernate;

import org.opeum.feature.StepDependency;
import org.opeum.javageneration.JavaTransformationPhase;
import org.opeum.metamodel.core.INakedElementOwner;
import org.opeum.metamodel.core.INakedRootObject;
import org.opeum.metamodel.models.INakedModel;
import org.opeum.metamodel.workspace.INakedModelWorkspace;

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
	protected boolean shouldProcessModel(){
		//Might overwrite other models persistence.xml
		return !(config.getSourceFolderStrategy().isSingleProjectStrategy() || transformationContext.isIntegrationPhase());
	}

	protected String getTemplateName(){
		return "templates/Model/PersistenceXml.vsl";
	}
	@Override
	protected String getDomainEnvironmentImplementation(){
		return "org.opeum.runtime.jpa.JpaEnvironment";
	}
	@Override
	protected String getAdaptorEnvironmentImplementation(){
		return "org.opeum.environment.seam2.Seam2Environment";
	}
}
