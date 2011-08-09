package net.sf.nakeduml.javageneration.hibernate;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.javageneration.JavaTransformationPhase;
import net.sf.nakeduml.metamodel.models.INakedModel;
import net.sf.nakeduml.metamodel.workspace.INakedModelWorkspace;

import org.nakeduml.java.metamodel.OJPathName;

@StepDependency(phase = JavaTransformationPhase.class,requires = {},after = {},before = {})
public class HibernatePackageAnnotator extends AbstractHibernatePackageAnnotator{
	protected String getIdType(){
		return "long";
	}
	protected OJPathName getTargetEntity(OJPathName javaTypePath){
		return javaTypePath;
	}
	@Override
	@VisitBefore
	public void visitWorkspace(INakedModelWorkspace root){
		doWorkspace(root);
	}
	@VisitBefore
	@Override
	public void visitModel(INakedModel model){
		doModel(model);
	}
	@Override
	protected String getMetaDefNameSuffix(){
		return "";
	}
}
