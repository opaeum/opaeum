package org.opaeum.javageneration.hibernate;

import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.Package;
import org.opaeum.eclipse.EmfPackageUtil;
import org.opaeum.emf.workspace.EmfWorkspace;
import org.opaeum.feature.StepDependency;
import org.opaeum.javageneration.JavaTransformationPhase;

@StepDependency(phase = JavaTransformationPhase.class,requires = {},after = {})
public class HibernateConfigGenerator extends AbstractPersistenceConfigGenerator{
	public HibernateConfigGenerator(){
		super();
	}
	protected String getOutputPath(Element model){
		if(model instanceof Model){
			return EmfPackageUtil.getIdentifier((Package) model) + "-hibernate.cfg.xml";
		}else{
			return ((EmfWorkspace) model).getIdentifier() + "-hibernate.cfg.xml";
		}
	}
	protected String getConfigName(Element model){
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
