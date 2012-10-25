package org.opaeum.javageneration.hibernate;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.Enumeration;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.Model;
import org.opaeum.emf.workspace.EmfWorkspace;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.annotation.OJAnnotatedPackageInfo;
import org.opaeum.java.metamodel.annotation.OJAnnotationAttributeValue;
import org.opaeum.java.metamodel.annotation.OJAnnotationValue;
import org.opaeum.javageneration.AbstractJavaProducingVisitor;
import org.opaeum.javageneration.IntegrationCodeGenerator;
import org.opaeum.textmetamodel.JavaSourceFolderIdentifier;

public abstract class AbstractHibernatePackageAnnotator extends AbstractJavaProducingVisitor implements IntegrationCodeGenerator{
	public static final class MetaDefElementCollector{
		Set<Interface> interfaces = new HashSet<Interface>();
		Set<Behavior> allProcesses = new HashSet<Behavior>();
		Set<Enumeration> enumerations = new HashSet<Enumeration>();
	}
	public abstract void visitWorkspace(EmfWorkspace root);
	public abstract void visitModel(Model model);
	protected void doWorkspace(EmfWorkspace workspace){
		if(transformationContext.isIntegrationPhase()){
			OJAnnotatedPackageInfo pkg = findOrCreatePackageInfo(ojUtil.utilPackagePath(workspace),
					JavaSourceFolderIdentifier.INTEGRATED_ADAPTOR_GEN_SRC);
			applyFilter(pkg);
		}
	}
	private void applyFilter(OJAnnotatedPackageInfo ap){
		OJAnnotationValue filterDef = new OJAnnotationValue(new OJPathName("org.hibernate.annotations.FilterDef"));
		filterDef.putAttribute(new OJAnnotationAttributeValue("name", "noDeletedObjects"));
		filterDef.putAttribute(new OJAnnotationAttributeValue("defaultCondition", "deleted_on > " + getCurrentTimestampSQLFunction()));
		ap.putAnnotation(filterDef);
	}
	private String getCurrentTimestampSQLFunction(){
		return config.getDbDialect().getCurrentTimeStampString();
	}
	protected void doModel(Model model){
		if(shouldProcessModel()){
			OJAnnotatedPackageInfo domainPkg = findOrCreatePackageInfo(ojUtil.utilPackagePath(model), JavaSourceFolderIdentifier.DOMAIN_GEN_SRC);
			applyFilter(domainPkg);
		}
	}
	private boolean shouldProcessModel(){
		// Will result in multiple packages defining the same filters and metadefs
		return !(transformationContext.isIntegrationPhase() || config.getSourceFolderStrategy().isSingleProjectStrategy());
	}
	protected abstract String getIdType();
	protected abstract String getMetaDefNameSuffix();
}