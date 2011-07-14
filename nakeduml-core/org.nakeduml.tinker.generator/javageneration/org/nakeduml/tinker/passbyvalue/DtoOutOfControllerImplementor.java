package org.nakeduml.tinker.passbyvalue;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.javageneration.JavaTextSource;
import net.sf.nakeduml.javageneration.NakedStructuralFeatureMap;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedEntity;
import net.sf.nakeduml.metamodel.core.INakedEnumeration;
import net.sf.nakeduml.metamodel.core.INakedInterface;
import net.sf.nakeduml.metamodel.core.INakedProperty;

import org.nakeduml.java.metamodel.OJBlock;
import org.nakeduml.java.metamodel.OJField;
import org.nakeduml.java.metamodel.OJForStatement;
import org.nakeduml.java.metamodel.OJIfStatement;
import org.nakeduml.java.metamodel.OJOperation;
import org.nakeduml.java.metamodel.OJPackage;
import org.nakeduml.java.metamodel.OJParameter;
import org.nakeduml.java.metamodel.OJPathName;
import org.nakeduml.java.metamodel.OJSimpleStatement;
import org.nakeduml.java.metamodel.OJTryStatement;
import org.nakeduml.java.metamodel.OJVisibilityKind;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedClass;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedField;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedInterface;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedOperation;
import org.nakeduml.java.metamodel.annotation.OJAnnotationValue;
import org.nakeduml.name.NameConverter;
import org.nakeduml.tinker.basicjava.tinker.TinkerUtil;

public class DtoOutOfControllerImplementor extends AbstractPassByValueImplementor {

	@VisitBefore(matchSubclasses = true)
	public void visitFeature(INakedClassifier c) {
		if (OJUtil.hasOJClass(c)) {
			OJAnnotatedClass myClass = createControllerImpl(c);
			createControllerInterface(c, myClass);
		}
	}

	private void createControllerInterface(INakedClassifier c, OJAnnotatedClass ojClass) {
		OJAnnotatedInterface wsInf = new OJAnnotatedInterface();
		wsInf.addToImplementedInterfaces(new OJPathName(Serializable.class.getName()));
		wsInf.setName(c.getName() + DtoImplementationStep.WS);
		OJPathName path = OJUtil.packagePathname(c.getNameSpace());
		OJPackage pack = findOrCreatePackage(path);
		wsInf.setMyPackage(pack);
		addInterfaceGetters(wsInf, c);
		if (!(c instanceof INakedEnumeration) && !(c instanceof INakedInterface) && !c.getIsAbstract()) {
			addUpdateToInterface(wsInf, c);
			addCreateToInterface(wsInf, c);
		}
		if (!(c instanceof INakedInterface) && !(c instanceof INakedEnumeration)) {
			addFindToInterface(wsInf, c);
		}
		ojClass.addToImplementedInterfaces(wsInf.getPathName());
		super.createTextPath(wsInf, JavaTextSource.OutputRootId.ADAPTOR_GEN_SRC);
	}

	private void addFindToInterface(OJAnnotatedInterface wsInf, INakedClassifier c) {
		OJAnnotatedOperation find = new OJAnnotatedOperation("find");
		find.addParam(c.getMappingInfo().getJavaName().getDecapped() + "Id", new OJPathName("java.lang.Long"));
		find.setReturnType(PassByValueUtil.classifierDtoPathname(c));
		wsInf.addToOperations(find);
	}

	private void addCreateToInterface(OJAnnotatedInterface wsInf, INakedClassifier c) {
		OJAnnotatedOperation create = new OJAnnotatedOperation("create");
		create.addParam("dto", PassByValueUtil.classifierDtoPathname(c));
		wsInf.addToOperations(create);
	}

	private void addUpdateToInterface(OJAnnotatedInterface wsInf, INakedClassifier c) {
		OJAnnotatedOperation update = new OJAnnotatedOperation("update");
		update.addParam("dto", PassByValueUtil.classifierDtoPathname(c));
		wsInf.addToOperations(update);
	}

	private OJAnnotatedClass createControllerImpl(INakedClassifier c) {
		OJAnnotatedClass myClass = new OJAnnotatedClass();
		myClass.getDefaultConstructor();
		myClass.addToImplementedInterfaces(new OJPathName(Serializable.class.getName()));
		OJAnnotatedField seri = new OJAnnotatedField("serialVersionUID", new OJPathName("long"));
		seri.setStatic(true);
		seri.setFinal(true);
		seri.setVisibility(OJVisibilityKind.PRIVATE);
		seri.setInitExp(c.getMappingInfo().getNakedUmlId() + "");
		myClass.addToFields(seri);
		myClass.setName(c.getName() + DtoImplementationStep.CTRL);
		OJPathName path = OJUtil.packagePathname(c.getNameSpace());
		OJPackage pack = findOrCreatePackage(path);
		myClass.setMyPackage(pack);
		myClass.setSuperclass(new OJPathName("org.nakeduml.environment.PassByValueController"));
		addAssociations(myClass, c);
		if (c instanceof INakedEntity && !c.getIsAbstract()) {
			addUpdate(myClass, (INakedEntity) c);
			addCreate(myClass, (INakedEntity) c);
		}
		if (!(c instanceof INakedInterface) && !(c instanceof INakedEnumeration)) {
			addFind(myClass, c);
		}
		super.createTextPath(myClass, JavaTextSource.OutputRootId.ADAPTOR_GEN_SRC);
		return myClass;
	}

	private void addFind(OJAnnotatedClass myClass, INakedClassifier c) {
		OJAnnotatedOperation find = new OJAnnotatedOperation("find");
		find.addAnnotationIfNew(new OJAnnotationValue(new OJPathName("java.lang.Override")));
		find.addParam(c.getMappingInfo().getJavaName().getDecapped() + "Id", new OJPathName("java.lang.Long"));
		find.setReturnType(PassByValueUtil.classifierDtoPathname(c));
		myClass.addToOperations(find);
		find.getBody().addToStatements("before()");
		OJTryStatement ojTryStatement = new OJTryStatement();
		ojTryStatement.getTryPart().addToStatements("db.startTransaction()");
		ojTryStatement.getTryPart().addToStatements(
				OJUtil.classifierPathname(c).getLast() + " " + c.getMappingInfo().getJavaName().getDecapped() + " = db.instantiateClassifier("
						+ c.getMappingInfo().getJavaName().getDecapped() + "Id" + ")");
		ojTryStatement.getTryPart().addToStatements(
				PassByValueUtil.classifierDtoPathname(c).getLast() + " " + c.getMappingInfo().getJavaName().getDecapped() + "Dto = new "
						+ PassByValueUtil.classifierDtoPathname(c).getLast() + "()");
		ojTryStatement.getTryPart().addToStatements(
				PassByValueUtil.classifierAssemblerPathname(c).getLast() + ".assemble(" + c.getMappingInfo().getJavaName().getDecapped() + ", "
						+ c.getMappingInfo().getJavaName().getDecapped() + "Dto)");
		ojTryStatement.getTryPart().addToStatements("db.stopTransaction(Conclusion.SUCCESS)");
		ojTryStatement.getTryPart().addToStatements("return " + c.getMappingInfo().getJavaName().getDecapped() + "Dto");
		ojTryStatement.setCatchParam(new OJParameter("e", new OJPathName("java.lang.RuntimeException")));
		ojTryStatement.getCatchPart().addToStatements("db.stopTransaction(Conclusion.FAILURE)");
		ojTryStatement.getCatchPart().addToStatements("throw new RuntimeException(e)");
		ojTryStatement.getFinallyPart().addToStatements("after()");
		myClass.addToImports(new OJPathName("com.tinkerpop.blueprints.pgm.TransactionalGraph.Conclusion"));
		find.getBody().addToStatements(ojTryStatement);
	}

	private void addCreate(OJAnnotatedClass myClass, INakedEntity c) {
		OJAnnotatedOperation create = new OJAnnotatedOperation("create");
		create.addAnnotationIfNew(new OJAnnotationValue(new OJPathName("java.lang.Override")));
		create.addParam("dto", PassByValueUtil.classifierDtoPathname(c));
		myClass.addToOperations(create);
		create.getBody().addToStatements("before()");
		OJTryStatement ojTryStatement = new OJTryStatement();
		ojTryStatement.getTryPart().addToStatements("db.startTransaction()");
		ojTryStatement.getTryPart().addToStatements(
				OJUtil.classifierPathname(c).getLast() + " " + c.getMappingInfo().getJavaName().getDecapped() + " = new "
						+ OJUtil.classifierPathname(c).getLast() + "(true)");
		ojTryStatement.getTryPart().addToStatements(
				PassByValueUtil.classifierAssemblerPathname(c).getLast() + ".assemble(dto, " + c.getMappingInfo().getJavaName().getDecapped() + ")");
		ojTryStatement.getTryPart().addToStatements("db.stopTransaction(Conclusion.SUCCESS)");
		ojTryStatement.setCatchParam(new OJParameter("e", new OJPathName("java.lang.RuntimeException")));
		ojTryStatement.getCatchPart().addToStatements("db.stopTransaction(Conclusion.FAILURE)");
		ojTryStatement.getCatchPart().addToStatements("throw new RuntimeException(e)");
		ojTryStatement.getFinallyPart().addToStatements("after()");
		myClass.addToImports(new OJPathName("com.tinkerpop.blueprints.pgm.TransactionalGraph.Conclusion"));
		create.getBody().addToStatements(ojTryStatement);
	}

	private void addUpdate(OJAnnotatedClass myClass, INakedClassifier c) {
		OJAnnotatedOperation update = new OJAnnotatedOperation("update");
		update.addAnnotationIfNew(new OJAnnotationValue(new OJPathName("java.lang.Override")));
		update.addParam("dto", PassByValueUtil.classifierDtoPathname(c));
		myClass.addToOperations(update);
		update.getBody().addToStatements("before()");
		OJTryStatement ojTryStatement = new OJTryStatement();
		ojTryStatement.getTryPart().addToStatements("db.startTransaction()");
		ojTryStatement.getTryPart().addToStatements(
				OJUtil.classifierPathname(c).getLast() + " " + c.getMappingInfo().getJavaName().getDecapped() + " = db.instantiateClassifier(dto.getId())");
		ojTryStatement.getTryPart().addToStatements(
				PassByValueUtil.classifierAssemblerPathname(c).getLast() + ".assemble(dto, " + c.getMappingInfo().getJavaName().getDecapped() + ")");
		ojTryStatement.getTryPart().addToStatements("db.stopTransaction(Conclusion.SUCCESS)");
		ojTryStatement.setCatchParam(new OJParameter("e", new OJPathName("java.lang.RuntimeException")));
		ojTryStatement.getCatchPart().addToStatements("db.stopTransaction(Conclusion.FAILURE)");
		ojTryStatement.getCatchPart().addToStatements("throw new RuntimeException(e)");
		ojTryStatement.getFinallyPart().addToStatements("after()");
		myClass.addToImports(new OJPathName("com.tinkerpop.blueprints.pgm.TransactionalGraph.Conclusion"));
		update.getBody().addToStatements(ojTryStatement);
	}

	private void addInterfaceGetters(OJAnnotatedInterface ojClass, INakedClassifier c) {
		for (INakedProperty p : c.getEffectiveAttributes()) {
			NakedStructuralFeatureMap map = OJUtil.buildStructuralFeatureMap(p);
			if (!p.isDerived() && !p.isDerivedUnion() && !isRedefined(c, p)) {
				if (map.isOne() && p.getOtherEnd() != null) {
					NakedStructuralFeatureMap otherMap = OJUtil.buildStructuralFeatureMap(p.getOtherEnd());
					OJOperation get = new OJAnnotatedOperation();
					get.setName(otherMap.getter() + "For" + NameConverter.capitalize(map.umlName()));
					get.addParam(map.umlName() + "Id", new OJPathName("java.lang.Long"));
					ojClass.addToOperations(get);
					if (otherMap.isMany()) {
						OJPathName resultPath = new OJPathName("java.util.List");
						resultPath.addToElementTypes(PassByValueUtil.classifierDtoPathname(otherMap.getProperty().getNakedBaseType()));
						get.setReturnType(resultPath);
					} else {
						get.setReturnType(PassByValueUtil.classifierDtoPathname(otherMap.getProperty().getNakedBaseType()));
					}
				} else if (((p.getNakedBaseType() instanceof INakedEntity) || (p.getNakedBaseType() instanceof INakedInterface)) && map.isManyToMany()) {
				}
			}
		}
	}

	private void addAssociations(OJAnnotatedClass ojClass, INakedClassifier c) {
		for (INakedProperty p : c.getEffectiveAttributes()) {
			NakedStructuralFeatureMap map = OJUtil.buildStructuralFeatureMap(p);
			if (!p.isDerived() && !p.isDerivedUnion() && !isRedefined(c, p)) {

				if (map.isOne() && p.getOtherEnd() != null) {
					NakedStructuralFeatureMap otherMap = OJUtil.buildStructuralFeatureMap(p.getOtherEnd());
					OJAnnotatedOperation get = new OJAnnotatedOperation();
					get.addAnnotationIfNew(new OJAnnotationValue(new OJPathName("java.lang.Override")));
					get.setName(otherMap.getter() + "For" + NameConverter.capitalize(map.umlName()));
					get.addParam(map.umlName() + "Id", new OJPathName("java.lang.Long"));
					ojClass.addToOperations(get);
					get.getBody().addToStatements("before()");
					OJTryStatement ojTryStatement = new OJTryStatement();
					ojTryStatement.getTryPart().addToStatements("db.startTransaction()");
					OJField result = new OJField();
					result.setName("result");

					ojTryStatement.getTryPart().addToStatements(
							map.javaBaseTypePath().getLast() + " " + map.umlName() + " = db.instantiateClassifier(" + map.umlName() + "Id)");

					ojClass.addToImports(TinkerUtil.vertexPathName);
					ojClass.addToImports(TinkerUtil.tinkerUtil);
					ojClass.addToImports(map.javaBaseTypePath());

					OJField newDto = new OJField();
					newDto.setName("dto");
					newDto.setType(PassByValueUtil.classifierDtoPathname(otherMap.getProperty().getNakedBaseType()));
					newDto.setInitExp("null");

					if (otherMap.isMany()) {
						OJPathName resultPath = new OJPathName("java.util.List");
						resultPath.addToElementTypes(PassByValueUtil.classifierDtoPathname(otherMap.getProperty().getNakedBaseType()));
						get.setReturnType(resultPath);
						result.setType(resultPath);
						result.setInitExp("new ArrayList<" + PassByValueUtil.classifierDtoPathname(otherMap.getProperty().getNakedBaseType()).getLast() + ">()");
						ojClass.addToImports(new OJPathName("java.util.List"));
						ojClass.addToImports(new OJPathName("java.util.ArrayList"));
						ojTryStatement.getTryPart().addToLocals(result);

						OJForStatement forStatement = new OJForStatement(otherMap.umlName(), otherMap.javaBaseTypePath(), map.umlName() + "."
								+ otherMap.getter() + "()");
						ojClass.addToImports(otherMap.javaBaseTypePath());
						forStatement.getBody().addToLocals(newDto);

						Set<INakedClassifier> subclasses = new HashSet<INakedClassifier>();
						if (!c.getIsAbstract()) {
							subclasses.add(c);
						}
						findAllImplementingClassifiers(subclasses, c);
						for (INakedClassifier subclass : subclasses) {
							assembleClassifier(ojClass, otherMap, forStatement.getBody(), subclass);
						}

						forStatement.getBody().addToStatements("result.add(dto)");
						ojTryStatement.getTryPart().addToStatements(forStatement);
						ojTryStatement.getTryPart().addToStatements("db.stopTransaction(Conclusion.SUCCESS)");
						ojTryStatement.getTryPart().addToStatements("return result");

					} else {

						get.setReturnType(PassByValueUtil.classifierDtoPathname(otherMap.getProperty().getNakedBaseType()));
						ojTryStatement.getTryPart().addToLocals(newDto);

						Set<INakedClassifier> subclasses = new HashSet<INakedClassifier>();
						if (!c.getIsAbstract()) {
							subclasses.add(c);
						}
						findAllImplementingClassifiers(subclasses, c);
						OJSimpleStatement ojSimpleStatement = new OJSimpleStatement(otherMap.javaBaseTypePath().getLast() + " " + otherMap.umlName() + " = "
								+ map.umlName() + "." + otherMap.getter() + "()");
						ojTryStatement.getTryPart().addToStatements(ojSimpleStatement);
						for (INakedClassifier subclass : subclasses) {
							assembleClassifier(ojClass, otherMap, ojTryStatement.getTryPart(), subclass);
						}

						ojTryStatement.getTryPart().addToStatements("db.stopTransaction(Conclusion.SUCCESS)");
						ojTryStatement.getTryPart().addToStatements("return dto");
					}

					ojClass.addToImports(new OJPathName("com.tinkerpop.blueprints.pgm.TransactionalGraph.Conclusion"));
					ojTryStatement.setCatchParam(new OJParameter("e", new OJPathName("java.lang.Exception")));
					ojTryStatement.getCatchPart().addToStatements("db.stopTransaction(Conclusion.FAILURE)");
					ojTryStatement.getCatchPart().addToStatements("throw new RuntimeException(e)");
					ojTryStatement.getFinallyPart().addToStatements("after()");
					get.getBody().addToStatements(ojTryStatement);
				} else if (((p.getNakedBaseType() instanceof INakedEntity) || (p.getNakedBaseType() instanceof INakedInterface)) && map.isManyToMany()) {
				}
			}
		}
	}

	private void assembleClassifier(OJAnnotatedClass ojClass, NakedStructuralFeatureMap otherMap, OJBlock block, INakedClassifier implementing) {
		String condition = otherMap.umlName() + ".getClass().isAssignableFrom(" + OJUtil.classifierPathname(implementing).getLast() + ".class) && (" + otherMap.umlName() + " instanceof " + OJUtil.classifierPathname(implementing).getLast() + ")";
		OJIfStatement ifStatement = new OJIfStatement(condition);
		ojClass.addToImports(OJUtil.classifierPathname(implementing));
		ifStatement.addToThenPart("dto = new " + PassByValueUtil.classifierDtoPathname(implementing).getLast() + "()");
		ojClass.addToImports(PassByValueUtil.classifierDtoPathname(implementing));
		ifStatement.addToThenPart(PassByValueUtil.classifierAssemblerPathname(implementing).getLast() + ".assemble(("
				+ OJUtil.classifierPathname(implementing).getLast() + ")" + otherMap.umlName() + " ,(" + PassByValueUtil.classifierDtoPathname(implementing).getLast()
				+ ")dto)");
		block.addToStatements(ifStatement);
	}

	private boolean isRedefined(INakedClassifier c, INakedProperty p) {
		for (INakedProperty prop : c.getEffectiveAttributes()) {
			if (prop.getRedefinedProperties().contains(p)) {
				return true;
			}
		}
		return false;
	}
}
