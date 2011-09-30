package org.opeum.javageneration.basicjava;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.opeum.feature.MappingInfo;
import org.opeum.feature.StepDependency;
import org.opeum.feature.visit.VisitBefore;
import org.opeum.javageneration.AbstractJavaProducingVisitor;
import org.opeum.javageneration.IntegrationCodeGenerator;
import org.opeum.javageneration.JavaSourceFolderIdentifier;
import org.opeum.javageneration.JavaTransformationPhase;
import org.opeum.javageneration.jbpm5.EventUtil;
import org.opeum.javageneration.util.OJUtil;
import org.opeum.metamodel.commonbehaviors.INakedChangeEvent;
import org.opeum.metamodel.commonbehaviors.INakedEvent;
import org.opeum.metamodel.commonbehaviors.INakedSignal;
import org.opeum.metamodel.commonbehaviors.INakedTimer;
import org.opeum.metamodel.core.INakedClassifier;
import org.opeum.metamodel.core.INakedElementOwner;
import org.opeum.metamodel.core.INakedEnumeration;
import org.opeum.metamodel.core.INakedInterface;
import org.opeum.metamodel.core.INakedOperation;
import org.opeum.metamodel.core.INakedRootObject;
import org.opeum.metamodel.core.INakedSimpleType;
import org.opeum.metamodel.models.INakedModel;
import org.opeum.metamodel.workspace.INakedModelWorkspace;
import org.opeum.validation.namegeneration.JavaNameRegenerator;
import nl.klasse.octopus.model.IOperation;

import org.opeum.java.metamodel.OJBlock;
import org.opeum.java.metamodel.OJClass;
import org.opeum.java.metamodel.OJConstructor;
import org.opeum.java.metamodel.OJPathName;
import org.opeum.java.metamodel.OJVisibilityKind;
import org.opeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opeum.java.metamodel.annotation.OJAnnotatedField;
import org.opeum.runtime.domain.IPersistentObject;
import org.opeum.runtime.environment.JavaMetaInfoMap;

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
			Collection<INakedRootObject> rootObjectsToImport = m.getAllDependencies();
			rootObjectsToImport.remove(m);
			OJBlock initBlock = createBasicMetaInfo(m, rootObjectsToImport, JavaSourceFolderIdentifier.DOMAIN_GEN_SRC);
			for(INakedClassifier c:getElementsOfType(INakedClassifier.class, Arrays.asList(m))){
				if(isPersistent(c) || c instanceof INakedEnumeration || c instanceof INakedSignal || c instanceof INakedInterface || c instanceof INakedSimpleType){
					MappingInfo ci = c.getMappingInfo();
					initBlock.addToStatements("putClass(" + ci.getQualifiedJavaName() + ".class,\"" + ci.getIdInModel() + "\")");
					if(c.getRootObject() == m){
						for(IOperation o:c.getOperations()){
							MappingInfo mi = ((INakedOperation) o).getMappingInfo();
							initBlock.addToStatements("putMethod(" + ci.getQualifiedJavaName() + ".class,\"" + mi.getIdInModel() + "\"," + mi.getOpeumId() + ")");
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
		ignore.add("OpiumSimpleTypes".toLowerCase());
		ignore.add("UMLPrimitiveTypes".toLowerCase());
		ignore.add("JavaPrimitiveTypes".toLowerCase());
		ignore.add("OpeumSimpleTypes".toLowerCase());
		for(INakedRootObject ro:allDependencies){
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
