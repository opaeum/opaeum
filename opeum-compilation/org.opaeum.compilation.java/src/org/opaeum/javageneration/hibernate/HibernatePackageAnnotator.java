package org.opeum.javageneration.hibernate;

import org.opeum.feature.StepDependency;
import org.opeum.feature.visit.VisitBefore;
import org.opeum.java.metamodel.OJPathName;
import org.opeum.javageneration.JavaTransformationPhase;
import org.opeum.metamodel.models.INakedModel;
import org.opeum.metamodel.workspace.INakedModelWorkspace;

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
