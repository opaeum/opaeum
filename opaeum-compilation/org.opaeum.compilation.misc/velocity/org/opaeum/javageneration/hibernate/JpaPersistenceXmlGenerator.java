package org.opaeum.javageneration.hibernate;

import org.opaeum.feature.StepDependency;
import org.opaeum.javageneration.JavaTransformationPhase;
import org.opaeum.metamodel.core.INakedElementOwner;
import org.opaeum.metamodel.core.INakedRootObject;
import org.opaeum.metamodel.models.INakedModel;
import org.opaeum.metamodel.workspace.INakedModelWorkspace;

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
		return "org.opaeum.environment.seam2.Seam2Environment";
	}
}
