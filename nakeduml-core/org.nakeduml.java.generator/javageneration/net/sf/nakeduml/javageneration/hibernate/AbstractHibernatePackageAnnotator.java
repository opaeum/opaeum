package net.sf.nakeduml.javageneration.hibernate;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import net.sf.nakeduml.javageneration.AbstractJavaProducingVisitor;
import net.sf.nakeduml.javageneration.IntegrationCodeGenerator;
import net.sf.nakeduml.javageneration.JavaSourceFolderIdentifier;
import net.sf.nakeduml.javageneration.jbpm5.ProcessStepResolverImplementor;
import net.sf.nakeduml.javageneration.maps.NakedClassifierMap;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.linkage.GeneralizationUtil;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedBehavior;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedBehavioredClassifier;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedEnumeration;
import net.sf.nakeduml.metamodel.core.INakedInterface;
import net.sf.nakeduml.metamodel.core.INakedRootObject;
import net.sf.nakeduml.metamodel.models.INakedModel;
import net.sf.nakeduml.metamodel.workspace.INakedModelWorkspace;

import org.nakeduml.java.metamodel.OJPathName;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedPackageInfo;
import org.nakeduml.java.metamodel.annotation.OJAnnotationAttributeValue;
import org.nakeduml.java.metamodel.annotation.OJAnnotationValue;

public abstract class AbstractHibernatePackageAnnotator extends AbstractJavaProducingVisitor implements IntegrationCodeGenerator{
	public static final class MetaDefElementCollector {
		Set<INakedInterface> interfaces = new HashSet<INakedInterface>();
		Set<INakedBehavior> allProcesses = new HashSet<INakedBehavior>();
		Set<INakedEnumeration> enumerations = new HashSet<INakedEnumeration>();
	}
	public abstract void visitWorkspace(INakedModelWorkspace root);
	public abstract void visitModel(INakedModel model);
	protected void doWorkspace(INakedModelWorkspace workspace){
		if(transformationContext.isIntegrationPhase()){
			OJAnnotatedPackageInfo pkg = findOrCreatePackageInfo(OJUtil.utilPackagePath(workspace), JavaSourceFolderIdentifier.INTEGRATED_ADAPTOR_GEN_SRC);
			applyFilter(pkg);
			MetaDefElementCollector collector = collectElements(workspace.getRootObjects());
			Set<INakedInterface> interfaces = collector.interfaces;
			for(INakedInterface i:interfaces){
				String metaDefName = HibernateUtil.metadefName((INakedInterface) i);
				doMetaDef(GeneralizationUtil.getConcreteEntityImplementationsOf(i, workspace.getRootObjects()), metaDefName, pkg);
			}
			if(transformationContext.isFeatureSelected(EnumResolverImplementor.class)){
				doTypeDefs(collector.enumerations, "Resolver", pkg);
			}
			if(transformationContext.isFeatureSelected(ProcessStepResolverImplementor.class)){
				doTypeDefs(collector.allProcesses, "StateResolver", pkg);
			}
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
	protected void doModel(INakedModel model){
		if(shouldProcessModel()){
			OJAnnotatedPackageInfo domainPkg = findOrCreatePackageInfo(OJUtil.utilPackagePath(model),JavaSourceFolderIdentifier.DOMAIN_GEN_SRC);
			applyFilter(domainPkg);
			Collection<INakedRootObject> selfAndDependencies = getModelInScope();
			MetaDefElementCollector collector = collectElements(selfAndDependencies);
			Set<INakedInterface> interfaces = collector.interfaces;
			for(INakedInterface i:interfaces){
				Collection<INakedBehavioredClassifier> generalizations = GeneralizationUtil.getConcreteEntityImplementationsOf(i, selfAndDependencies);
				doMetaDef(generalizations, HibernateUtil.metadefName((INakedInterface) i), domainPkg);
			}
			if(transformationContext.isFeatureSelected(EnumResolverImplementor.class)){
				doTypeDefs(collector.enumerations, "Resolver", domainPkg);
			}
			if(transformationContext.isFeatureSelected(ProcessStepResolverImplementor.class)){
				doTypeDefs(collector.allProcesses, "StateResolver", domainPkg);
			}
		}
	}
	private boolean shouldProcessModel(){
		//Will result in multiple packages defining the same filters and metadefs
		return !(transformationContext.isIntegrationPhase() || config.getSourceFolderStrategy().isSingleProjectStrategy());
	}
	private void doTypeDefs(Set<? extends INakedClassifier> processes,String string,OJAnnotatedPackageInfo p){
		OJAnnotationAttributeValue typeDefs = getTypeDefs(p);
		for(INakedClassifier a:processes){
			OJAnnotationValue typeDef = new OJAnnotationValue(new OJPathName("org.hibernate.annotations.TypeDef"));
			typeDefs.addAnnotationValue(typeDef);
			p.putAnnotation(typeDef);
			typeDef.putAttribute("name", a.getMappingInfo().getJavaName() + string);
			typeDef.putAttribute("typeClass", new OJPathName(a.getMappingInfo().getQualifiedJavaName() + string));
		}
	}
	private OJAnnotationAttributeValue getTypeDefs(OJAnnotatedPackageInfo p){
		OJAnnotationValue typeDefs = p.findAnnotation(new OJPathName("org.hibernate.annotations.TypeDefs"));
		if(typeDefs == null){
			typeDefs = new OJAnnotationValue(new OJPathName("org.hibernate.annotations.TypeDefs"));
			p.putAnnotation(typeDefs);
			typeDefs.putAttribute(new OJAnnotationAttributeValue("value"));
		}
		return typeDefs.findAttribute("value");
	}
	private MetaDefElementCollector collectElements(Collection<INakedRootObject> ownedElements){
		MetaDefElementCollector collector = new MetaDefElementCollector();
		final Set<INakedBehavior> elementsOfType = getElementsOfType(INakedBehavior.class, ownedElements);
		final Iterator<INakedBehavior> iter = elementsOfType.iterator();
		while(iter.hasNext()){
			INakedBehavior p = (INakedBehavior) iter.next();
			if(!p.isProcess()){
				iter.remove();
			}
		}
		collector.allProcesses=elementsOfType;
		collector.enumerations =getElementsOfType(INakedEnumeration.class, ownedElements);
		collector.interfaces=getElementsOfType(INakedInterface.class, ownedElements);
		return collector;
	}
	private void doMetaDef(Collection<? extends INakedClassifier> impls,String metaDefName,OJAnnotatedPackageInfo p){
		OJAnnotationValue metaDef = new OJAnnotationValue(new OJPathName("org.hibernate.annotations.AnyMetaDef"));
		OJAnnotationValue anyMetaDefs = getAnyMetaDefs(p);
		anyMetaDefs.addAnnotationValue(metaDef);
		metaDef.putAttribute("name", metaDefName + getMetaDefNameSuffix());
		metaDef.putAttribute("metaType", config.shouldBeCm1Compatible()?"string":"integer");
		metaDef.putAttribute("idType", getIdType());
		OJAnnotationAttributeValue metaValues = new OJAnnotationAttributeValue("metaValues");
		metaDef.putAttribute(metaValues);
		for(INakedClassifier bc:impls){
			OJAnnotationValue metaValue = new OJAnnotationValue(new OJPathName("org.hibernate.annotations.MetaValue"));
			NakedClassifierMap map = new NakedClassifierMap(bc);
			OJPathName javaTypePath = map.javaTypePath();
			metaValue.putAttribute("value", config.shouldBeCm1Compatible()?OJUtil.classifierPathname(bc).toJavaString(): bc.getMappingInfo().getNakedUmlId().toString());
			metaValue.putAttribute("targetEntity", javaTypePath);
			metaValues.addAnnotationValue(metaValue);
		}
	}
	protected abstract String getIdType();
	protected abstract String getMetaDefNameSuffix();
	private OJAnnotationValue getAnyMetaDefs(OJAnnotatedPackageInfo p){
		OJAnnotationValue anyMetaDefs = p.findAnnotation(new OJPathName("org.hibernate.annotations.AnyMetaDefs"));
		if(anyMetaDefs == null){
			anyMetaDefs = new OJAnnotationValue(new OJPathName("org.hibernate.annotations.AnyMetaDefs"));
			p.putAnnotation(anyMetaDefs);
		}
		return anyMetaDefs;
	}
}