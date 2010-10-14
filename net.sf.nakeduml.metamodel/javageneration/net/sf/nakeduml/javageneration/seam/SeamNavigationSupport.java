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

public class SeamNavigationSupport extends AbstractJavaProducingVisitor {

	@VisitBefore(matchSubclasses=true)
	public void visitPropertyBefore(INakedProperty property) {
		if (!(property.getNakedBaseType() instanceof INakedInterface) &&	property.getOtherEnd() != null && property.getOwner() instanceof INakedEntity) {

			INakedClassifier c = property.getOwner();
			if (hasOJClass(c)&& isPersistent(c) && c instanceof INakedClassifier) {
				OJAnnotatedClass ojClass = findJavaClass(c);
				buildColumnRenderer(ojClass, property, "isGroupOwnershipValid");
				buildColumnRenderer(ojClass, property, "isUserOwnershipValid");
			}
			
		}
	}
	
	private void buildColumnRenderer(OJAnnotatedClass ojClass, INakedProperty property, String functionName) {
		NakedStructuralFeatureMap map = OJUtil.buildStructuralFeatureMap(property);
		OJOperation rendered = new OJAnnotatedOperation();
		rendered.setName(functionName + "For" + NameConverter.capitalize(property.getName()));
		rendered.addParam("nakedUser", new OJPathName("com.rorotika.cm.user.User"));
		rendered.setReturnType(new OJPathName("java.lang.Boolean"));
		
		if (map.isOneToMany() || (map.isOneToOne() && property.isInverse())) {
			
			OJIfStatement ifStatement = null;
			if (map.getFeature().isDerived()) {
				if (map.isOneToOne()) {
					ifStatement = new OJIfStatement(map.getter() + "() == null", "return false");
				} else {
					ifStatement = new OJIfStatement(map.getter() + "() == null || " + map.getter() + "().isEmpty()", "return false");
				}
			} else {
				if (map.isMany()) {
					ifStatement = new OJIfStatement(map.getter() + "() == null || " + map.getter() + "().isEmpty()", "return false");
				} else {
					ifStatement = new OJIfStatement(map.getter() + "() == null", "return false");
				}
			}
			
			rendered.getBody().addToStatements(ifStatement);
			
			if (map.isOneToOne()) {
				rendered.getBody().addToStatements("return " + map.getter()+"()." + functionName + "(nakedUser)");
			} else {
				OJForStatement forAll = new OJForStatement();
				if (map.getFeature().isDerived()) {
					forAll.setCollection(map.getter() + "()");
				} else {
					forAll.setCollection("this."+map.umlName());
				}
				forAll.setElemName(map.umlName());
				forAll.setElemType(map.javaBaseTypePath());
				forAll.setBody(new OJBlock());
				
				ifStatement = new OJIfStatement(map.umlName()+"." + functionName + "(nakedUser)","return true");			
				forAll.getBody().addToStatements(ifStatement);
				
				rendered.getBody().addToStatements(forAll);
				rendered.getBody().addToStatements("return false");
				ojClass.addToOperations(rendered);
			}
			
		} else {
			OJIfStatement ifStatement = new OJIfStatement(map.getter() + "() == null", "return false");
			rendered.getBody().addToStatements(ifStatement);
			
			rendered.getBody().addToStatements("return " + map.getter()+"()." + functionName + "(nakedUser)");
			ojClass.addToOperations(rendered);
		}
		
	}
	
}
