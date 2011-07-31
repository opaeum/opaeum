package net.sf.nakeduml.javageneration.oclexpressions;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.javageneration.AbstractJavaProducingVisitor;
import net.sf.nakeduml.javageneration.JavaTextSource;
import net.sf.nakeduml.javageneration.JavaTransformationPhase;
import net.sf.nakeduml.javageneration.basicjava.Java6ModelGenerator;
import net.sf.nakeduml.linkage.NakedParsedOclStringResolver;
import net.sf.nakeduml.metamodel.models.INakedModel;
import nl.klasse.octopus.codegen.umlToJava.expgenerators.visitors.OclUtilityCreator;
import nl.klasse.octopus.codegen.umlToJava.modelgenerators.visitors.UtilityCreator;

import org.nakeduml.java.metamodel.OJClassifier;

@StepDependency(phase = JavaTransformationPhase.class,requires = {
		Java6ModelGenerator.class,NakedParsedOclStringResolver.class
},after = {
	Java6ModelGenerator.class
})
public class UtilCreator extends AbstractJavaProducingVisitor{
	@VisitBefore
	public void visitModel(INakedModel pkg){
		OclUtilityCreator ouc = new OclUtilityCreator(javaModel);
		ouc.makeOclUtilities(null, workspace.getOclEngine().getOclLibrary());
		for(OJClassifier c:UtilityCreator.getUtilPack().getClasses()){
			createTextPath(c, JavaTextSource.OutputRootId.DOMAIN_GEN_SRC);
		}
	}
}