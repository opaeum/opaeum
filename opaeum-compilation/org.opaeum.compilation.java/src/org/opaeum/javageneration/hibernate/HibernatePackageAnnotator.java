package org.opaeum.javageneration.hibernate;

import org.eclipse.uml2.uml.Model;
import org.opaeum.emf.workspace.EmfWorkspace;
import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitBefore;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.javageneration.JavaTransformationPhase;

@StepDependency(phase = JavaTransformationPhase.class,requires = {},after = {},before = {})
public class HibernatePackageAnnotator extends AbstractHibernatePackageAnnotator{
	protected String getIdType(){
		return "long";
	}
	protected OJPathName getTargetClass(OJPathName javaTypePath){
		return javaTypePath;
	}
	@Override
	@VisitBefore
	public void visitWorkspace(EmfWorkspace root){
		doWorkspace(root);
	}
	@VisitBefore
	@Override
	public void visitModel(Model model){
		doModel(model);
	}
	@Override
	protected String getMetaDefNameSuffix(){
		return "";
	}
}
