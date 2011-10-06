package org.opaeum.javageneration.hibernate;

import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitBefore;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.javageneration.JavaTransformationPhase;
import org.opaeum.metamodel.models.INakedModel;
import org.opaeum.metamodel.workspace.INakedModelWorkspace;

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
