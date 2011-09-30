package org.opeum.javageneration.oclexpressions;

import org.opeum.feature.StepDependency;
import org.opeum.feature.visit.VisitBefore;
import org.opeum.javageneration.AbstractJavaProducingVisitor;
import org.opeum.javageneration.JavaSourceFolderIdentifier;
import org.opeum.javageneration.JavaTransformationPhase;
import org.opeum.javageneration.basicjava.Java6ModelGenerator;
import org.opeum.linkage.NakedParsedOclStringResolver;
import org.opeum.metamodel.models.INakedModel;
import nl.klasse.octopus.codegen.umlToJava.expgenerators.visitors.OclUtilityCreator;
import nl.klasse.octopus.codegen.umlToJava.modelgenerators.visitors.UtilityCreator;

import org.opeum.java.metamodel.OJClassifier;
import org.opeum.java.metamodel.annotation.OJAnnotatedClass;

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
			if(!(c instanceof OJAnnotatedClass)){
				createTextPath(c, JavaSourceFolderIdentifier.DOMAIN_GEN_SRC);
			}
		}
	}
}