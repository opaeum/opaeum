package org.opaeum.javageneration.hibernate;

import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.Package;
import org.opaeum.eclipse.EmfPackageUtil;
import org.opaeum.feature.StepDependency;
import org.opaeum.javageneration.JavaTransformationPhase;
import org.opaeum.metamodel.workspace.ModelWorkspace;
import org.opaeum.rap.RapCapabilities;

@StepDependency(phase = JavaTransformationPhase.class,requires = {},after = {})
public class JpaPersistenceXmlGenerator extends AbstractPersistenceConfigGenerator{
	public JpaPersistenceXmlGenerator(){
		super();
	}
	protected String getOutputPath(Element model){
		return "META-INF/persistence.xml";
	}
	protected String getConfigName(Element model){
		if(model instanceof Model){
			return EmfPackageUtil.getIdentifier((Package) model);
		}else{
			return ((ModelWorkspace) model).getIdentifier();
		}
	}
	protected boolean shouldProcessModel(){
		// Might overwrite other models persistence.xml
		return !(config.getSourceFolderStrategy().isSingleProjectStrategy() || transformationContext.isIntegrationPhase());
	}
	protected String getTemplateName(){
		//TODO temp hack - make fully configurable in config
		if(transformationContext.isFeatureSelected(RapCapabilities.class)){
			return "templates/Model/PersistenceXmlRap.vsl";
		}else{
			return "templates/Model/PersistenceXml.vsl";
		}
	}
	@Override
	protected String getDomainEnvironmentImplementation(){
		return "org.opaeum.runtime.jpa.StandaloneJpaEnvironment";
	}
	@Override
	protected String getAdaptorEnvironmentImplementation(){
		return "org.opaeum.runtime.jpa.StandaloneJpaEnvironment";
	}
}
