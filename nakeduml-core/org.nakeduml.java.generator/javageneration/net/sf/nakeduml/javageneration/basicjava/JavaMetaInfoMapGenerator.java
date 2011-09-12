package net.sf.nakeduml.javageneration.basicjava;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import net.sf.nakeduml.feature.MappingInfo;
import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.javageneration.AbstractJavaProducingVisitor;
import net.sf.nakeduml.javageneration.IntegrationCodeGenerator;
import net.sf.nakeduml.javageneration.JavaSourceFolderIdentifier;
import net.sf.nakeduml.javageneration.JavaTransformationPhase;
import net.sf.nakeduml.javageneration.jbpm5.EventUtil;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedChangeEvent;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedEvent;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedSignal;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedTimer;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedElementOwner;
import net.sf.nakeduml.metamodel.core.INakedEnumeration;
import net.sf.nakeduml.metamodel.core.INakedInterface;
import net.sf.nakeduml.metamodel.core.INakedOperation;
import net.sf.nakeduml.metamodel.core.INakedRootObject;
import net.sf.nakeduml.metamodel.core.INakedSimpleType;
import net.sf.nakeduml.metamodel.models.INakedModel;
import net.sf.nakeduml.metamodel.workspace.INakedModelWorkspace;
import net.sf.nakeduml.validation.namegeneration.JavaNameRegenerator;
import nl.klasse.octopus.model.IOperation;

import org.nakeduml.java.metamodel.OJBlock;
import org.nakeduml.java.metamodel.OJClass;
import org.nakeduml.java.metamodel.OJConstructor;
import org.nakeduml.java.metamodel.OJPathName;
import org.nakeduml.java.metamodel.OJVisibilityKind;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedClass;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedField;
import org.nakeduml.runtime.domain.IPersistentObject;
import org.nakeduml.runtime.environment.JavaMetaInfoMap;

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
		if(isIntegrationRequired()){
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
							initBlock.addToStatements("putMethod(" + ci.getQualifiedJavaName() + ".class,\"" + mi.getIdInModel() + "\"," + mi.getNakedUmlId() + ")");
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
		ignore.add("NakedUMLSimpleTypes".toLowerCase());
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
