package net.sf.nakeduml.javageneration.seam;

import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.javageneration.AbstractJavaProducingVisitor;
import net.sf.nakeduml.javageneration.NakedStructuralFeatureMap;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.javametamodel.OJBlock;
import net.sf.nakeduml.javametamodel.OJForStatement;
import net.sf.nakeduml.javametamodel.OJIfStatement;
import net.sf.nakeduml.javametamodel.OJOperation;
import net.sf.nakeduml.javametamodel.OJPathName;
import net.sf.nakeduml.javametamodel.OJSimpleStatement;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedClass;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedOperation;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedEntity;
import net.sf.nakeduml.metamodel.core.INakedProperty;
import net.sf.nakeduml.name.NameConverter;

public class SeamSecuritySupport extends AbstractJavaProducingVisitor {

	@VisitBefore(matchSubclasses=true)
	public void visitPropertyBefore(INakedProperty property) {
		NakedStructuralFeatureMap map = OJUtil.buildStructuralFeatureMap(property);
		if(!property.isDerived() && property.isNavigable() && map.isMany() && hasOJClass(property.getOwner()) && property.getOwner() instanceof INakedEntity && property.getNakedBaseType() instanceof INakedEntity){
			INakedClassifier c = property.getOwner();
			if (hasOJClass(c)&& isPersistent(c) && c instanceof INakedClassifier && !c.getIsAbstract()) {
				OJAnnotatedClass ojClass = findJavaClass(c);
				buildColumnRenderer(ojClass, property, "group");
				buildColumnRenderer(ojClass, property, "user");
			}
		}
	}
	
	private void buildColumnRenderer(OJAnnotatedClass ojClass, INakedProperty property, String functionName) {
		NakedStructuralFeatureMap map = OJUtil.buildStructuralFeatureMap(property);
		ojClass.addToImports(new OJPathName("java.util.ArrayList"));
		ojClass.addToImports(new OJPathName("java.util.HashSet"));
		OJOperation rendered = new OJAnnotatedOperation();
		rendered.setName("get"+NameConverter.capitalize(property.getName())+"For"+NameConverter.capitalize(functionName)+"Security");
		rendered.addParam("nakedUser", new OJPathName("com.rorotika.cm.user.User"));
		rendered.setReturnType(map.javaTypePath());
		
		String collectionInterface=property.isOrdered()?"List":"Set";
		String collectionName=property.isOrdered()?"ArrayList":"HashSet";
		
		OJSimpleStatement simpleStatement = new OJSimpleStatement(collectionInterface + "<" + NameConverter.capitalize(property.getNakedBaseType().getName()) + "> result = new " + collectionName + "<" + NameConverter.capitalize(property.getNakedBaseType().getName()) + ">()");
		rendered.getBody().addToStatements(simpleStatement);
		
		OJForStatement forAll = new OJForStatement();
		forAll.setCollection("this."+map.umlName());
		forAll.setElemName(map.umlName());
		forAll.setElemType(map.javaBaseTypePath());
		forAll.setBody(new OJBlock());
		
		OJIfStatement ifStatement = new OJIfStatement(map.umlName() + ".is"+NameConverter.capitalize(functionName)+"OwnershipValid(nakedUser)", "result.add("+map.umlName()+")");
		forAll.getBody().addToStatements(ifStatement);
		
		rendered.getBody().addToStatements(forAll);
		
		collectionName=property.isOrdered()?"ArrayListWithModel":"HashSetWithModel";
		OJSimpleStatement returnStatement = new OJSimpleStatement("return new "+collectionName+"<"+NameConverter.capitalize(property.getNakedBaseType().getName())+">(result)");
		rendered.getBody().addToStatements(returnStatement);
		ojClass.addToOperations(rendered);
		
	}
	
}
