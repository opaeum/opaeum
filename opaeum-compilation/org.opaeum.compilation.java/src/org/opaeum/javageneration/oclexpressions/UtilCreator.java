package org.opaeum.javageneration.oclexpressions;

import nl.klasse.octopus.codegen.umlToJava.expgenerators.visitors.OclUtilityCreator;
import nl.klasse.octopus.codegen.umlToJava.modelgenerators.visitors.UtilityCreator;

import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitBefore;
import org.opaeum.java.metamodel.OJClassifier;
import org.opaeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opaeum.javageneration.AbstractJavaProducingVisitor;
import org.opaeum.javageneration.JavaTransformationPhase;
import org.opaeum.javageneration.basicjava.Java6ModelGenerator;
import org.opaeum.linkage.NakedParsedOclStringResolver;
import org.opaeum.metamodel.models.INakedModel;
import org.opaeum.textmetamodel.JavaSourceFolderIdentifier;

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