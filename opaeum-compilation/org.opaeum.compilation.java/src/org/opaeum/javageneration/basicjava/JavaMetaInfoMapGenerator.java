package org.opaeum.javageneration.basicjava;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import nl.klasse.octopus.model.IOperation;

import org.opaeum.feature.MappingInfo;
import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitBefore;
import org.opaeum.java.metamodel.OJBlock;
import org.opaeum.java.metamodel.OJClass;
import org.opaeum.java.metamodel.OJConstructor;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.OJVisibilityKind;
import org.opaeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opaeum.java.metamodel.annotation.OJAnnotatedField;
import org.opaeum.javageneration.AbstractJavaProducingVisitor;
import org.opaeum.javageneration.IntegrationCodeGenerator;
import org.opaeum.javageneration.JavaTransformationPhase;
import org.opaeum.javageneration.jbpm5.EventUtil;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.metamodel.commonbehaviors.INakedChangeEvent;
import org.opaeum.metamodel.commonbehaviors.INakedEvent;
import org.opaeum.metamodel.commonbehaviors.INakedSignal;
import org.opaeum.metamodel.commonbehaviors.INakedTimer;
import org.opaeum.metamodel.core.DefaultOpaeumComparator;
import org.opaeum.metamodel.core.INakedClassifier;
import org.opaeum.metamodel.core.INakedElementOwner;
import org.opaeum.metamodel.core.INakedEnumeration;
import org.opaeum.metamodel.core.INakedInterface;
import org.opaeum.metamodel.core.INakedOperation;
import org.opaeum.metamodel.core.INakedRootObject;
import org.opaeum.metamodel.core.INakedSimpleType;
import org.opaeum.metamodel.models.INakedModel;
import org.opaeum.metamodel.workspace.INakedModelWorkspace;
import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.runtime.environment.JavaMetaInfoMap;
import org.opaeum.textmetamodel.JavaSourceFolderIdentifier;
import org.opaeum.validation.namegeneration.JavaNameRegenerator;

@StepDependency(phase = JavaTransformationPhase.class,requires = {
	JavaNameRegenerator.class
},after = {})
public class JavaMetaInfoMapGenerator extends AbstractJavaProducingVisitor implements IntegrationCodeGenerator{
	@Override
	protected int getThreadPoolSize(){
		return 12;
	}
	@VisitBefore
	public void visitWorkspace(INakedModelWorkspace ws){
		if(transformationContext.isIntegrationPhase()){
			createBasicMetaInfo(ws, ws.getPrimaryRootObjects(), JavaSourceFolderIdentifier.INTEGRATED_ADAPTOR_GEN_SRC);
		}
	}
	@VisitBefore
	public void visitModel(INakedModel m){
		if(!transformationContext.isIntegrationPhase()){
			Collection<INakedRootObject> rootObjectsToImport = new TreeSet<INakedRootObject>(new DefaultOpaeumComparator());
			rootObjectsToImport.addAll(m.getAllDependencies());
			rootObjectsToImport.remove(m);
			OJBlock initBlock = createBasicMetaInfo(m, rootObjectsToImport, JavaSourceFolderIdentifier.DOMAIN_GEN_SRC);
			for(INakedClassifier c:getElementsOfType(INakedClassifier.class, Arrays.asList(m))){
				if(isPersistent(c) || c instanceof INakedEnumeration || c instanceof INakedSignal || c instanceof INakedInterface || c instanceof INakedSimpleType){
					MappingInfo ci = c.getMappingInfo();
					initBlock.addToStatements("putClass(" + ci.getQualifiedJavaName() + ".class,\"" + ci.getIdInModel() + "\")");
					if(c.getRootObject() == m){
						for(IOperation o:c.getOperations()){
							MappingInfo mi = ((INakedOperation) o).getMappingInfo();
							initBlock.addToStatements("putMethod(" + ci.getQualifiedJavaName() + ".class,\"" + mi.getIdInModel() + "\"," + mi.getOpaeumId() + "l)");
						}
					}
				}
			}
			for(INakedEvent e:getElementsOfType(INakedEvent.class, Collections.singletonList((INakedRootObject) m))){
				if(e instanceof INakedTimer || e instanceof INakedChangeEvent){
					initBlock.addToStatements("putEventHandler(" + EventUtil.handlerPathName(e) + ".class,\"" + e.getId() + "\")");
				}
			}
		}
	}
	private OJBlock createBasicMetaInfo(INakedElementOwner m,Collection<INakedRootObject> allDependencies,JavaSourceFolderIdentifier sourceid){
		TreeSet<INakedRootObject> treeSet = new TreeSet<INakedRootObject>(new DefaultOpaeumComparator());
		treeSet.addAll(allDependencies);
		OJPathName pathName = javaMetaInfoMapPath(m);
		OJClass mapClass = new OJAnnotatedClass(pathName.getLast());
		mapClass.setSuperclass(new OJPathName(JavaMetaInfoMap.class.getName()));
		findOrCreatePackage(pathName.getHead()).addToClasses(mapClass);
		super.createTextPath(mapClass, sourceid);
		mapClass.addToImports(IPersistentObject.class.getName());
		OJAnnotatedField instance = new OJAnnotatedField("INSTANCE", pathName);
		instance.setStatic(true);
		instance.setVisibility(OJVisibilityKind.PUBLIC);
		instance.setFinal(true);
		instance.setInitExp("new " + pathName.getLast() + "()");
		mapClass.addToFields(instance);
		OJConstructor constr = new OJConstructor();
		mapClass.addToConstructors(constr);
		OJBlock initBlock = constr.getBody();
		Set<String> ignore = new HashSet<String>();
		ignore.add("OpaeumSimpleTypes".toLowerCase());
		ignore.add("UMLPrimitiveTypes".toLowerCase());
		ignore.add("PrimitiveTypes".toLowerCase());
		ignore.add("JavaPrimitiveTypes".toLowerCase());
		ignore.add("OpaeumSimpleTypes".toLowerCase());
		for(INakedRootObject ro:treeSet){
			if(ro instanceof INakedModel && !ignore.contains(ro.getName().toLowerCase())){
				initBlock.addToStatements("this.importMetaInfo(" + javaMetaInfoMapPath(ro) + ".INSTANCE)");
			}
		}
		return initBlock;
	}
	public static OJPathName javaMetaInfoMapPath(INakedElementOwner owner){
		return OJUtil.utilPackagePath(owner).append(owner.getMappingInfo().getJavaName().getCapped() + "JavaMetaInfoMap");
	}
}
