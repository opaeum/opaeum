package net.sf.nakeduml.javageneration.basicjava;

import java.util.Collection;
import java.util.HashSet;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.javageneration.AbstractJavaProducingVisitor;
import net.sf.nakeduml.javageneration.IntegrationCodeGenerator;
import net.sf.nakeduml.javageneration.JavaSourceFolderIdentifier;
import net.sf.nakeduml.javageneration.JavaTransformationPhase;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedSignal;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.metamodel.core.INakedEnumeration;
import net.sf.nakeduml.metamodel.core.INakedInterface;
import net.sf.nakeduml.metamodel.core.INakedOperation;
import net.sf.nakeduml.metamodel.mapping.IMappingInfo;
import net.sf.nakeduml.metamodel.models.INakedModel;
import net.sf.nakeduml.metamodel.workspace.INakedModelWorkspace;
import net.sf.nakeduml.validation.namegeneration.JavaNameRegenerator;
import net.sf.nakeduml.validation.namegeneration.PersistentNameGenerator;
import nl.klasse.octopus.codegen.umlToJava.modelgenerators.visitors.UtilityCreator;
import nl.klasse.octopus.model.IOperation;

import org.nakeduml.environment.JavaMetaInfoMap;
import org.nakeduml.java.metamodel.OJBlock;
import org.nakeduml.java.metamodel.OJClass;
import org.nakeduml.java.metamodel.OJConstructor;
import org.nakeduml.java.metamodel.OJPathName;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedClass;
import org.nakeduml.runtime.domain.IPersistentObject;

@StepDependency(phase = JavaTransformationPhase.class,requires = {
	JavaNameRegenerator.class
},after = {})
public class JavaMetaInfoMapGenerator extends AbstractJavaProducingVisitor implements IntegrationCodeGenerator{
	public class ClassCollector extends AbstractJavaProducingVisitor{
		Collection<INakedClassifier> classes = new HashSet<INakedClassifier>();
		@VisitBefore(matchSubclasses = true)
		public void visitClassifier(INakedClassifier c){
			if(isPersistent(c) || c instanceof INakedEnumeration || c instanceof INakedSignal || c instanceof INakedInterface){
				classes.add(c);
			}
		}
	}
	@VisitBefore
	public void visitWorkspace(INakedModelWorkspace ws){
		if(isIntegrationPhase()){
			ClassCollector classCollector = new ClassCollector();
			classCollector.startVisiting(ws);
			createMapClass(UtilityCreator.getUtilPathName().append("metainfo"), JavaSourceFolderIdentifier.INTEGRATED_ADAPTOR_GEN_SRC, classCollector.classes);
		}
	}
	@VisitBefore
	public void visitModel(INakedModel m){
		if(!isIntegrationPhase()){
			ClassCollector collector = new ClassCollector();
			for(INakedElement e:getModelInScope()){
				if(e instanceof INakedModel){
					INakedModel model = (INakedModel) e;
					collector.visitRecursively(model);
				}
			}
			createMapClass(UtilityCreator.getUtilPathName().append("metainfo").append("domain"), JavaSourceFolderIdentifier.DOMAIN_GEN_TEST_SRC, collector.classes);
			createMapClass(UtilityCreator.getUtilPathName().append("metainfo").append("adaptor"), JavaSourceFolderIdentifier.ADAPTOR_GEN_TEST_SRC, collector.classes);
		}
	}
	protected void createMapClass(OJPathName metaInfoPackage,JavaSourceFolderIdentifier output,Collection<INakedClassifier> cls){
		String javaMetaInfoMapName = javaMetaInfoMapName();
		OJClass mapClass = new OJAnnotatedClass(javaMetaInfoMapName);
		mapClass.setSuperclass(new OJPathName(JavaMetaInfoMap.class.getName()));
		findOrCreatePackage(metaInfoPackage).addToClasses(mapClass);
		super.createTextPath(mapClass, output);
		mapClass.addToImports(IPersistentObject.class.getName());
		OJConstructor constr = new OJConstructor();
		mapClass.addToConstructors(constr);
		OJBlock initBlock = constr.getBody();
		for(INakedClassifier c:cls){
			IMappingInfo ci = c.getMappingInfo();
			initBlock.addToStatements("putClass(" + ci.getQualifiedJavaName() + ".class,\"" + ci.getIdInModel() + "\"," + ci.getNakedUmlId() + ")");
			for(IOperation o:c.getOperations()){
				IMappingInfo mi = ((INakedOperation) o).getMappingInfo();
				initBlock.addToStatements("putMethod(" + ci.getQualifiedJavaName() + ".class,\"" + mi.getIdInModel() + "\"," + mi.getNakedUmlId() + ")");
			}
		}
	}
	public static String javaMetaInfoMapName(){
		return "JavaMetaInfoMapImpl";
	}
}
