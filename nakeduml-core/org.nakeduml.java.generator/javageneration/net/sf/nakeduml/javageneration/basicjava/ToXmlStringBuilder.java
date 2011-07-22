package net.sf.nakeduml.javageneration.basicjava;

import net.sf.nakeduml.feature.visit.VisitAfter;
import net.sf.nakeduml.javageneration.NakedStructuralFeatureMap;
import net.sf.nakeduml.javageneration.StereotypeAnnotator;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.metamodel.core.INakedAssociationClass;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedComplexStructure;
import net.sf.nakeduml.metamodel.core.INakedEntity;
import net.sf.nakeduml.metamodel.core.INakedEnumeration;
import net.sf.nakeduml.metamodel.core.INakedInterface;
import net.sf.nakeduml.metamodel.core.INakedProperty;
import net.sf.nakeduml.metamodel.core.internal.StereotypeNames;

import org.nakeduml.java.metamodel.OJBlock;
import org.nakeduml.java.metamodel.OJForStatement;
import org.nakeduml.java.metamodel.OJIfStatement;
import org.nakeduml.java.metamodel.OJOperation;
import org.nakeduml.java.metamodel.OJPathName;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedClass;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedField;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedOperation;

public class ToXmlStringBuilder extends AbstractStructureVisitor {
	private void visitClass(INakedClassifier c) {
		if (OJUtil.hasOJClass(c) && !(c instanceof INakedEnumeration) && c.getStereotype(StereotypeNames.HELPER)==null) {
			OJAnnotatedClass ojClass = findJavaClass(c);
			this.buildToXmlString(ojClass, c);
		}
	}

	private void buildToXmlString(OJAnnotatedClass owner, INakedClassifier umlClass) {
		OJOperation toString = new OJAnnotatedOperation();
		toString.setReturnType(new OJPathName("String"));
		toString.setName("toXmlString");
		OJAnnotatedField sb = new OJAnnotatedField();
		sb.setName("sb");
		sb.setType(new OJPathName("StringBuilder"));
		sb.setInitExp("new StringBuilder()");
		toString.getBody().addToLocals(sb);
		for (INakedProperty f : umlClass.getEffectiveAttributes()) {
			if (!(OJUtil.isBuiltIn(f) || f.isDerived())) {
				NakedStructuralFeatureMap map = OJUtil.buildStructuralFeatureMap(f);
				owner.addToImports(map.javaBaseTypePath());
				if (map.isOne()) {
					OJIfStatement ifNull = new OJIfStatement(map.getter() + "()==null", "sb.append(\"<" + map.umlName() + "/>\")");
					ifNull.setElsePart(new OJBlock());
					ifNull.getElsePart().addToStatements("sb.append(\"<" + map.umlName() + ">\")");
					if (map.getProperty().getNakedBaseType() instanceof INakedEntity || map.getProperty().getNakedBaseType() instanceof INakedInterface) {
						if (f.isComposite()) {
							ifNull.getElsePart().addToStatements("sb.append(" + map.getter() + "().toXmlString())");
						} else {
							ifNull.getElsePart().addToStatements("sb.append(" + map.getter() + "().getClass().getSimpleName())");
							ifNull.getElsePart().addToStatements("sb.append(\"[\")");
							if (f.getNakedBaseType().findEffectiveAttribute("name") != null) {
								ifNull.getElsePart().addToStatements("sb.append(" + map.getter() + "().getName())");
							} else {
								ifNull.getElsePart().addToStatements("sb.append(" + map.getter() + "().hashCode())");
							}
							ifNull.getElsePart().addToStatements("sb.append(\"]\")");
						}
					} else {
						ifNull.getElsePart().addToStatements("sb.append(" + map.getter() + "())");
					}
					ifNull.getElsePart().addToStatements("sb.append(\"</" + map.umlName() + ">\")");
					ifNull.getElsePart().addToStatements("sb.append(\"\\n\")");
					toString.getBody().addToStatements(ifNull);
				} else if (map.isMany()) {
					if (!(f.getAssociation() instanceof INakedAssociationClass)) {
						OJForStatement forEach = new OJForStatement();
						forEach.setElemType(map.javaBaseTypePath());
						forEach.setElemName(map.umlName());
						forEach.setCollection(map.getter() + "()");
						forEach.setBody(new OJBlock());
						forEach.getBody().addToStatements("sb.append(\"<" + map.umlName() + ">\")");
						if (f.getNakedBaseType() instanceof INakedComplexStructure) {
							if (f.isComposite()) {
								forEach.getBody().addToStatements("sb.append(" + map.umlName() + ".toXmlString())");
							} else {
								forEach.getBody().addToStatements("sb.append(" + map.umlName() + ".getClass().getSimpleName())");
								forEach.getBody().addToStatements("sb.append(\"[\")");
								if (f.getNakedBaseType().findEffectiveAttribute("name") != null) {
									forEach.getBody().addToStatements("sb.append(" + map.umlName() + ".getName())");
								} else {
									forEach.getBody().addToStatements("sb.append(" + map.umlName() + ".hashCode())");
								}
								forEach.getBody().addToStatements("sb.append(\"]\")");
							}
						} else {
							forEach.getBody().addToStatements("sb.append(" + map.umlName() + ")");
						}
						forEach.getBody().addToStatements("sb.append(\"</" + map.umlName() + ">\")");
						forEach.getBody().addToStatements("sb.append(\"\\n\")");
						toString.getBody().addToStatements(forEach);
					}
				}
			}
		}
		toString.getBody().addToStatements("return sb.toString()");
		owner.addToOperations(toString);
	}

	@Override
	protected void visitProperty(INakedClassifier owner,NakedStructuralFeatureMap buildStructuralFeatureMap){
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void visitComplexStructure(INakedComplexStructure umlOwner){
		visitClass(umlOwner);
		
	}
}
