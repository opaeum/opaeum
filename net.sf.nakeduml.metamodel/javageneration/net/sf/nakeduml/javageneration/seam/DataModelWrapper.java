package net.sf.nakeduml.javageneration.seam;

import java.util.Collections;
import java.util.List;

import net.sf.nakeduml.feature.visit.VisitAfter;
import net.sf.nakeduml.javageneration.AbstractJavaProducingVisitor;
import net.sf.nakeduml.javageneration.NakedStructuralFeatureMap;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.javametamodel.OJOperation;
import net.sf.nakeduml.javametamodel.OJSimpleStatement;
import net.sf.nakeduml.javametamodel.OJStatement;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedClass;
import net.sf.nakeduml.metamodel.core.INakedEntity;
import net.sf.nakeduml.metamodel.core.INakedOperation;
import net.sf.nakeduml.metamodel.core.INakedProperty;
import nl.klasse.octopus.codegen.umlToJava.modelgenerators.visitors.UtilityCreator;

public class DataModelWrapper extends AbstractJavaProducingVisitor{
	@VisitAfter(matchSubclasses=true)
	public void visitAttribute(INakedProperty p){
		NakedStructuralFeatureMap map = OJUtil.buildStructuralFeatureMap(p);
		if(p.isNavigable() && map.isMany() && hasOJClass(p.getOwner()) && p.getOwner() instanceof INakedEntity){
			OJAnnotatedClass ojClass = findJavaClass(p.getOwner());
			OJOperation oper = ojClass.findOperation(map.getter(), Collections.emptyList());
			List<OJStatement> statements = oper.getBody().getStatements();
			String collectionName=p.isOrdered()?"ArrayListWithModel":"HashSetWithModel";
			ojClass.addToImports(UtilityCreator.getUtilPathName().toJavaString() +"." + collectionName);
			
			String variableName = map.umlName();
			if (p.isDerivedUnion()) {
				variableName += "Subsetting";
			}
			
			statements.set(statements.size()-1, new OJSimpleStatement("return new " + collectionName +"<"+ map.javaBaseType() +">("+ variableName+")"));
			
		}
	}
	@VisitAfter
	public void visitOperation(INakedOperation o){
		//TODO
	}
}
