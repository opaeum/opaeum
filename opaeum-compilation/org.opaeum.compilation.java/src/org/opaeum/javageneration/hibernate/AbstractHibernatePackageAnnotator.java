package org.opaeum.javageneration.hibernate;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import nl.klasse.octopus.codegen.umlToJava.maps.ClassifierMap;

import org.eclipse.ocl.expressions.CollectionKind;
import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.BehavioredClassifier;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Enumeration;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.Profile;
import org.opaeum.eclipse.EmfBehaviorUtil;
import org.opaeum.eclipse.EmfClassifierUtil;
import org.opaeum.eclipse.EmfElementFinder;
import org.opaeum.emf.workspace.EmfWorkspace;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.annotation.OJAnnotatedPackageInfo;
import org.opaeum.java.metamodel.annotation.OJAnnotationAttributeValue;
import org.opaeum.java.metamodel.annotation.OJAnnotationValue;
import org.opaeum.javageneration.AbstractJavaProducingVisitor;
import org.opaeum.javageneration.IntegrationCodeGenerator;
import org.opaeum.javageneration.jbpm5.ProcessStepResolverImplementor;
import org.opaeum.javageneration.util.OJUtil;
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
			MetaDefElementCollector collector = collectElements(workspace.getRootObjects());
			Set<Interface> interfaces = collector.interfaces;
			for(Interface i:interfaces){
				String metaDefName = HibernateUtil.metadefName((Interface) i);
				doMetaDef(EmfClassifierUtil.getConcreteEntityImplementationsOf(i, workspace.getRootObjects()), metaDefName, pkg);
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
	protected void doModel(Model model){
		if(shouldProcessModel()){
			OJAnnotatedPackageInfo domainPkg = findOrCreatePackageInfo(ojUtil.utilPackagePath(model), JavaSourceFolderIdentifier.DOMAIN_GEN_SRC);
			applyFilter(domainPkg);
			Collection<Package> selfAndDependencies = getModelInScope();
			MetaDefElementCollector collector = collectElements(selfAndDependencies);
			Set<Interface> interfaces = collector.interfaces;
			for(Interface i:interfaces){
				Collection<BehavioredClassifier> generalizations = EmfClassifierUtil.getConcreteEntityImplementationsOf(i,
						selfAndDependencies);
				doMetaDef(generalizations, HibernateUtil.metadefName((Interface) i), domainPkg);
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
		// Will result in multiple packages defining the same filters and metadefs
		return !(transformationContext.isIntegrationPhase() || config.getSourceFolderStrategy().isSingleProjectStrategy());
	}
	private void doTypeDefs(Set<? extends Classifier> processes,String string,OJAnnotatedPackageInfo p){
		OJAnnotationAttributeValue typeDefs = getTypeDefs(p);
		for(Classifier a:processes){
			if(!(EmfElementFinder.getRootObject(a) instanceof Profile)){
				OJAnnotationValue typeDef = new OJAnnotationValue(new OJPathName("org.hibernate.annotations.TypeDef"));
				typeDefs.addAnnotationValue(typeDef);
				p.putAnnotation(typeDef);
				typeDef.putAttribute("name", a.getName() + string);
				typeDef.putAttribute("typeClass", new OJPathName(ojUtil.classifierPathname(a) + string));
			}
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
	private MetaDefElementCollector collectElements(Collection<Package> ownedElements){
		MetaDefElementCollector collector = new MetaDefElementCollector();
		final Set<Behavior> elementsOfType = getElementsOfType(Behavior.class, ownedElements);
		final Iterator<Behavior> iter = elementsOfType.iterator();
		while(iter.hasNext()){
			Behavior p = (Behavior) iter.next();
			if(!EmfBehaviorUtil.isProcess( p)){
				iter.remove();
			}
		}
		collector.allProcesses = elementsOfType;
		collector.enumerations = getElementsOfType(Enumeration.class, ownedElements);
		collector.interfaces = getElementsOfType(Interface.class, ownedElements);
		return collector;
	}
	private void doMetaDef(Collection<? extends Classifier> impls,String metaDefName,OJAnnotatedPackageInfo p){
		OJAnnotationValue metaDef = new OJAnnotationValue(new OJPathName("org.hibernate.annotations.AnyMetaDef"));
		OJAnnotationValue anyMetaDefs = getAnyMetaDefs(p);
		anyMetaDefs.addAnnotationValue(metaDef);
		metaDef.putAttribute("name", metaDefName + getMetaDefNameSuffix());
		metaDef.putAttribute("metaType", "string");
		metaDef.putAttribute("idType", getIdType());
		OJAnnotationAttributeValue metaValues = new OJAnnotationAttributeValue("metaValues");
		metaDef.putAttribute(metaValues);
		for(Classifier bc:impls){
			OJAnnotationValue metaValue = new OJAnnotationValue(new OJPathName("org.hibernate.annotations.MetaValue"));
			ClassifierMap map = ojUtil.buildClassifierMap(bc,(CollectionKind)null);
			OJPathName javaTypePath = map.javaTypePath();
			metaValue.putAttribute("value", config.shouldBeCm1Compatible() ? ojUtil.classifierPathname(bc).toJavaString() : EmfWorkspace.getOpaeumId(bc)+"");
			metaValue.putAttribute("targetClass", javaTypePath);
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