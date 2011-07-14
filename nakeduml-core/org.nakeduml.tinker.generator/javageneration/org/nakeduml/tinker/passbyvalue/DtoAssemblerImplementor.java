package org.nakeduml.tinker.passbyvalue;

import java.io.Serializable;

import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.javageneration.JavaTextSource;
import net.sf.nakeduml.javageneration.NakedStructuralFeatureMap;
import net.sf.nakeduml.javageneration.StereotypeAnnotator;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.metamodel.core.INakedEntity;
import net.sf.nakeduml.metamodel.core.INakedInterface;
import net.sf.nakeduml.metamodel.core.INakedProperty;

import org.nakeduml.java.metamodel.OJField;
import org.nakeduml.java.metamodel.OJForStatement;
import org.nakeduml.java.metamodel.OJIfStatement;
import org.nakeduml.java.metamodel.OJOperation;
import org.nakeduml.java.metamodel.OJPackage;
import org.nakeduml.java.metamodel.OJParameter;
import org.nakeduml.java.metamodel.OJPathName;
import org.nakeduml.java.metamodel.OJTryStatement;
import org.nakeduml.java.metamodel.OJVisibilityKind;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedClass;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedOperation;
import org.nakeduml.tinker.basicjava.tinker.TinkerUtil;

public class DtoAssemblerImplementor extends StereotypeAnnotator {

	@VisitBefore(matchSubclasses = true)
	public void visitFeature(INakedEntity c) {
		if (OJUtil.hasOJClass(c)) {
			OJAnnotatedClass myClass = new OJAnnotatedClass();
			// Create default constructor for inits
			myClass.getDefaultConstructor();
			// In case it needs to be sent by jms or serialized as session
			// state
			myClass.addToImplementedInterfaces(new OJPathName(Serializable.class.getName()));
			myClass.setAbstract(c.getIsAbstract());
			myClass.setName(c.getName() + DtoImplementationStep.ASSEMBLER);
			OJPathName path = OJUtil.packagePathname(c.getNameSpace());
			OJPackage pack = findOrCreatePackage(path);
			myClass.setMyPackage(pack);
			OJOperation assemble = createAssemblyOper(c, myClass, "from", "to");
			addProperties(assemble, "from", "to", c);

			OJOperation reverseAssemble = createReverseAssemblyOper(c, myClass, "from", "to");
			addReverseProperties(reverseAssemble, "from", "to", c);
			
			myClass.addToImports(new OJPathName("java.util.List"));
			myClass.addToImports(new OJPathName("java.util.ArrayList"));
			myClass.addToImports(TinkerUtil.graphDbPathName);
			super.createTextPath(myClass, JavaTextSource.OutputRootId.ADAPTOR_GEN_SRC);
		}
	}

	private void addReverseProperties(OJOperation reverseAssemble, String param1, String param2, INakedEntity c) {
		for (INakedProperty p : c.getEffectiveAttributes()) {
			if (!p.isDerived() && !p.isDerivedUnion()) {
				NakedStructuralFeatureMap map = OJUtil.buildStructuralFeatureMap(p);
				if (!(p.getNakedBaseType() instanceof INakedEntity) && !(p.getNakedBaseType() instanceof INakedInterface)) {
					reverseAssemble.getBody().addToStatements(param2 + "." + map.setter() + "(" + param1 + "." + map.getter() + "())");
				} else if (((p.getNakedBaseType() instanceof INakedEntity) || (p.getNakedBaseType() instanceof INakedInterface)) && map.isOne()) {
					
					OJIfStatement ifThere = new OJIfStatement(param1 + "." + map.getter() + "Id() != null && " + param1 + "." + map.getter() + "Id() != -1");
					reverseAssemble.getOwner().addToImports(TinkerUtil.vertexPathName);

					ifThere.addToThenPart(map.javaBaseTypePath().getLast() + " " + map.umlName() +" = GraphDb.getDb().instantiateClassifier("+param1 + "." + map.getter() + "Id())");
					ifThere.addToThenPart(param2 + "." + map.setter() + "(" + map.umlName() + ")");

//					OJTryStatement tryInstantiateClass = new OJTryStatement();
//					tryInstantiateClass.getTryPart().addToStatements("Class<?> c = Class.forName((String) v.getProperty(\"className\"))");
//					tryInstantiateClass.getTryPart().addToStatements(map.javaBaseTypePath().getLast() + " " + map.umlName() +" = ("+map.javaBaseTypePath().getLast()+") c.getConstructor(Vertex.class).newInstance(v)");
//					tryInstantiateClass.getTryPart().addToStatements(param2 + "." + map.setter() + "(" + map.umlName() + ")");
//					tryInstantiateClass.setCatchParam(new OJParameter("e", new OJPathName("java.lang.Exception")));
//					tryInstantiateClass.getCatchPart().addToStatements("throw new RuntimeException(e)");
//					ifThere.addToThenPart(tryInstantiateClass);
					
					reverseAssemble.getOwner().addToImports(map.javaBaseTypePath());
					reverseAssemble.getBody().addToStatements(ifThere);
				
				} else if (((p.getNakedBaseType() instanceof INakedEntity) || (p.getNakedBaseType() instanceof INakedInterface)) && map.isManyToMany()) {
//					OJField manyField = new OJField();
//					OJPathName resultType = new OJPathName("java.lang.List");
//					resultType.addToElementTypes(new OJPathName("java.lang.Long"));
//					manyField.setType(resultType);
//					String resultManyIds = map.fieldname() + "Ids";
//					manyField.setName(resultManyIds);
//					manyField.setInitExp("new ArrayList<Long>()");
//					reverseAssemble.getBody().addToLocals(manyField);
//					OJForStatement forMany = new OJForStatement("many", map.javaBaseTypePath(), param1 + "." + map.getter() + "()");
//					reverseAssemble.getOwner().addToImports(map.javaBaseTypePath());
//					forMany.getBody().addToStatements(resultManyIds + ".add(many.getId());");
//					reverseAssemble.getBody().addToStatements(forMany);
//					reverseAssemble.getBody().addToStatements(param2 + "." + map.setter() + "Ids(" + resultManyIds + ")");
				}
			}
		}		
	}

	private OJOperation createAssemblyOper(INakedEntity c, OJAnnotatedClass myClass, String param1, String param2) {
		OJOperation assemble = new OJAnnotatedOperation();
		assemble.setName("assemble");
		assemble.setVisibility(OJVisibilityKind.PUBLIC);
		assemble.setStatic(true);
		assemble.addParam(param1, OJUtil.classifierPathname(c));
		assemble.addParam(param2, OJUtil.classifierDtoPathname(c));
		myClass.addToOperations(assemble);
		return assemble;
	}
	
	private OJOperation createReverseAssemblyOper(INakedEntity c, OJAnnotatedClass myClass, String param1, String param2) {
		OJOperation assemble = new OJAnnotatedOperation();
		assemble.setName("assemble");
		assemble.setVisibility(OJVisibilityKind.PUBLIC);
		assemble.setStatic(true);
		assemble.addParam(param1, OJUtil.classifierDtoPathname(c));
		assemble.addParam(param2, OJUtil.classifierPathname(c));
		myClass.addToOperations(assemble);
		return assemble;
	}	

	private void addProperties(OJOperation assemble, String param1, String param2, INakedEntity c) {
		for (INakedProperty p : c.getEffectiveAttributes()) {
			if (!p.isDerived() && !p.isDerivedUnion()) {
				NakedStructuralFeatureMap map = OJUtil.buildStructuralFeatureMap(p);
				if (!(p.getNakedBaseType() instanceof INakedEntity) && !(p.getNakedBaseType() instanceof INakedInterface)) {
					assemble.getBody().addToStatements(param2 + "." + map.setter() + "(" + param1 + "." + map.getter() + "())");
				} else if (((p.getNakedBaseType() instanceof INakedEntity) || (p.getNakedBaseType() instanceof INakedInterface)) && map.isOne()) {
					assemble.getBody().addToStatements(
							param2 + "." + map.setter() + "Id(" + param1 + "." + map.getter() + "()==null?-1L:" + param1 + "." + map.getter() + "().getId())");
				} else if (((p.getNakedBaseType() instanceof INakedEntity) || (p.getNakedBaseType() instanceof INakedInterface)) && map.isManyToMany()) {
					OJField manyField = new OJField();
					OJPathName resultType = new OJPathName("java.lang.List");
					resultType.addToElementTypes(new OJPathName("java.lang.Long"));
					manyField.setType(resultType);
					String resultManyIds = map.fieldname() + "Ids";
					manyField.setName(resultManyIds);
					manyField.setInitExp("new ArrayList<Long>()");
					assemble.getBody().addToLocals(manyField);
					OJForStatement forMany = new OJForStatement("many", map.javaBaseTypePath(), param1 + "." + map.getter() + "()");
					assemble.getOwner().addToImports(map.javaBaseTypePath());
					forMany.getBody().addToStatements(resultManyIds + ".add(many.getId());");
					assemble.getBody().addToStatements(forMany);
					assemble.getBody().addToStatements(param2 + "." + map.setter() + "Ids(" + resultManyIds + ")");
				}
			}
		}
		assemble.getBody().addToStatements(param2 + ".setId(" + param1 + ".getId())");
	}

}
