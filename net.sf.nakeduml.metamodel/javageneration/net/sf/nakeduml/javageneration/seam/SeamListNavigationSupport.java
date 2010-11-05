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
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedClass;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedOperation;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedEntity;
import net.sf.nakeduml.metamodel.core.INakedInterface;
import net.sf.nakeduml.metamodel.core.INakedProperty;
import net.sf.nakeduml.name.NameConverter;

public class SeamListNavigationSupport extends AbstractJavaProducingVisitor {

	@VisitBefore(matchSubclasses=true)
	public void visitPropertyBefore(INakedProperty property) {
		if (!property.isComposite() && !(property.getNakedBaseType() instanceof INakedInterface) && 
				property.getOtherEnd() != null && !property.getOtherEnd().isComposite() && property.getOwner() instanceof INakedEntity) {

			INakedProperty endToComposite = ((INakedEntity)property.getOwner()).getEndToComposite();
			INakedClassifier c = endToComposite.getNakedBaseType();
			if (OJUtil.hasOJClass(c)&& isPersistent(c) && c instanceof INakedClassifier && !c.getIsAbstract()) {
				OJAnnotatedClass ojClass = findJavaClass(c);
				NakedStructuralFeatureMap map = OJUtil.buildStructuralFeatureMap(endToComposite.getOtherEnd());
				if (map.isOneToMany()) {
					buildColumnRenderer(ojClass, endToComposite, property, map, "isGroupOwnershipValid");
					buildColumnRenderer(ojClass, endToComposite, property, map, "isUserOwnershipValid");
				}
			}
			
		}
	}
	
	private void buildColumnRenderer(OJAnnotatedClass ojClass, INakedProperty endToComposite, INakedProperty property, NakedStructuralFeatureMap map, String functionName) {
		OJOperation rendered = new OJAnnotatedOperation();
		rendered.setName(functionName + "For" + NameConverter.capitalize(property.getOwner().getName()) + NameConverter.capitalize(property.getName()));
		rendered.addParam("nakedUser", new OJPathName("com.rorotika.cm.user.User"));
		rendered.setReturnType(new OJPathName("java.lang.Boolean"));
		
		OJIfStatement ifStatement = new OJIfStatement(map.getter() + "() == null || " + map.getter() + "().isEmpty()", "return false");
		rendered.getBody().addToStatements(ifStatement);
		OJForStatement forAll = new OJForStatement();
		forAll.setCollection(map.getter()+"()");
		forAll.setElemName(map.umlName());
		forAll.setElemType(map.javaBaseTypePath());
		forAll.setBody(new OJBlock());
		
		NakedStructuralFeatureMap propertyMap = OJUtil.buildStructuralFeatureMap(property);
		if (propertyMap.isOneToMany()) {
			ojClass.addToImports(propertyMap.javaTypePath());
			OJForStatement propertyForAll = new OJForStatement();
			propertyForAll.setCollection(map.umlName() + "." + propertyMap.getter() + "()");
			propertyForAll.setElemName(propertyMap.umlName());
			propertyForAll.setElemType(propertyMap.javaBaseTypePath());
			propertyForAll.setBody(new OJBlock());
			ifStatement = new OJIfStatement(propertyMap.umlName()+"."+functionName+"(nakedUser)","return true");			
			propertyForAll.getBody().addToStatements(ifStatement);
			forAll.getBody().addToStatements(propertyForAll);
		} else {
			ifStatement = new OJIfStatement(map.umlName()+"."+propertyMap.getter()+"()." + functionName + "(nakedUser)","return true");			
			forAll.getBody().addToStatements(ifStatement);
		}
		rendered.getBody().addToStatements(forAll);
		rendered.getBody().addToStatements("return false");
		ojClass.addToOperations(rendered);
	}
	
}
