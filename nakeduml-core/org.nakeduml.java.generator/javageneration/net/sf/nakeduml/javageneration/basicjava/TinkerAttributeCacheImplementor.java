package net.sf.nakeduml.javageneration.basicjava;

import java.util.Arrays;
import java.util.Collections;

import net.sf.nakeduml.feature.visit.VisitAfter;
import net.sf.nakeduml.javageneration.AbstractJavaProducingVisitor;
import net.sf.nakeduml.javageneration.NakedStructuralFeatureMap;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.metamodel.core.INakedAssociationClass;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedEntity;
import net.sf.nakeduml.metamodel.core.INakedInterface;
import net.sf.nakeduml.metamodel.core.INakedProperty;
import net.sf.nakeduml.metamodel.core.INakedStructuredDataType;
import net.sf.nakeduml.metamodel.core.internal.StereotypeNames;

import org.nakeduml.java.metamodel.OJConstructor;
import org.nakeduml.java.metamodel.OJField;
import org.nakeduml.java.metamodel.OJForStatement;
import org.nakeduml.java.metamodel.OJIfStatement;
import org.nakeduml.java.metamodel.OJOperation;
import org.nakeduml.java.metamodel.OJParameter;
import org.nakeduml.java.metamodel.OJPathName;
import org.nakeduml.java.metamodel.OJSimpleStatement;
import org.nakeduml.java.metamodel.OJTryStatement;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedClass;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedInterface;

public class TinkerAttributeCacheImplementor extends AbstractJavaProducingVisitor {

	@VisitAfter(matchSubclasses = true)
	public void visitFeature(INakedProperty p) {
		if (OJUtil.hasOJClass(p.getOwner())) {
			if (p.getAssociation() instanceof INakedAssociationClass) {
			} else {
				visitProperty(p.getOwner(), OJUtil.buildStructuralFeatureMap(p));
			}
		}
	}

	@VisitAfter(matchSubclasses = true, match = { INakedEntity.class, INakedStructuredDataType.class, INakedAssociationClass.class })
	public void visitFeature(INakedClassifier entity) {
		for (INakedProperty p : entity.getEffectiveAttributes()) {
			if (p.getOwner() instanceof INakedInterface && OJUtil.hasOJClass(entity)) {
				if (p.getAssociation() instanceof INakedAssociationClass) {
				} else {
					visitProperty(entity, OJUtil.buildStructuralFeatureMap(p));
				}
			}
		}
	}

	private void visitProperty(INakedClassifier umlOwner, NakedStructuralFeatureMap map) {
		INakedProperty p = map.getProperty();
		if (!OJUtil.isBuiltIn(p)) {
			if (p.getNakedBaseType().hasStereotype(StereotypeNames.HELPER)) {
			} else if (p.isDerived() || p.isReadOnly()) {
			} else {
				implementAttributeFully(umlOwner, map);
			}
		}
	}

	private void implementAttributeFully(INakedClassifier umlOwner, NakedStructuralFeatureMap map) {
		INakedProperty p = map.getProperty();
		OJAnnotatedClass owner = findJavaClass(umlOwner);
		if (map.isMany()) {
			initializeCollection(umlOwner, owner, map);
			buildAdder(owner, map);
			buildRemover(owner, map);
		} else if (map.isOne() && isPersistent(p.getNakedBaseType()) || p.getBaseType() instanceof INakedInterface) {
		}
		buildGetter(owner, map, false);
		buildSetter(umlOwner, owner, map);
		if (umlOwner instanceof INakedEntity) {
			INakedEntity entity = (INakedEntity) umlOwner;
			if (entity.getEndToComposite() != null && p == entity.getEndToComposite()) {
				addToParentCollection(owner, entity, map, new NakedStructuralFeatureMap(p.getOtherEnd()));
			}
		}
	}

	private OJOperation buildRemover(OJAnnotatedClass owner, NakedStructuralFeatureMap map) {
		OJOperation adder = owner.findOperation(map.remover(), Arrays.asList(map.javaBaseTypePath()));
		INakedProperty p = map.getProperty();
		if (p.getOtherEnd() != null && p.getOtherEnd().isNavigable() && !(p.getOtherEnd().isDerived() || p.getOtherEnd().isReadOnly())) {
			NakedStructuralFeatureMap otherMap = new NakedStructuralFeatureMap((p).getOtherEnd());
			if (otherMap.isMany()) {
				buildManyRemover(map, otherMap, adder);
			} else {
			}
		} else {
		}
		owner.addToOperations(adder);
		return adder;
	}

	private void buildManyRemover(NakedStructuralFeatureMap map, NakedStructuralFeatureMap otherMap, OJOperation adder) {
		adder.getBody().addToStatements(
				(map.getProperty().isOrdered() ? "((TinkerList<" : "((TinkerSet<") + map.javaBaseTypePath().getLast() + ">)" + map.getter()
						+ "()).tinkerRemove(" + map.umlName() + ")");
	}

	private OJOperation buildAdder(OJAnnotatedClass owner, NakedStructuralFeatureMap map) {
		OJOperation adder = owner.findOperation(map.adder(), Arrays.asList(map.javaBaseTypePath()));
		INakedProperty p = map.getProperty();
		if (p.getOtherEnd() != null && p.getOtherEnd().isNavigable() && !(p.getOtherEnd().isDerived() || p.getOtherEnd().isReadOnly())) {
			NakedStructuralFeatureMap otherMap = new NakedStructuralFeatureMap((p).getOtherEnd());
			if (otherMap.isMany()) {
				buildManyAdder(map, otherMap, adder);
			} else {
			}
		} else {
			adder.getBody().addToStatements(map.getter() + "().add(" + map.umlName() + ")");
		}
		return adder;
	}

	private void buildManyAdder(NakedStructuralFeatureMap map, NakedStructuralFeatureMap otherMap, OJOperation adder) {
		if (map.getProperty().isOrdered()) {
			adder.getBody().addToStatements("((TinkerList)" + map.umlName() + "." + otherMap.getter() + "()).tinkerAdd(this)");
		} else {
			adder.getBody().addToStatements("((TinkerSet)" + map.umlName() + "." + otherMap.getter() + "()).tinkerAdd(this)");
		}
		if (otherMap.getProperty().isOrdered()) {
			adder.getBody().addToStatements("((TinkerList)" + map.getter() + "()).tinkerAdd(" + map.umlName() + ")");
		} else {
			adder.getBody().addToStatements("((TinkerSet)" + map.getter() + "()).tinkerAdd(" + map.umlName() + ")");
		}
	}

	protected OJOperation buildSetter(INakedClassifier umlOwner, OJAnnotatedClass owner, NakedStructuralFeatureMap map) {
		OJOperation setter = owner.findOperation(map.setter(), Arrays.asList(map.javaTypePath()));
		if (owner instanceof OJAnnotatedInterface) {
		} else {
			INakedProperty prop = map.getProperty();
			if (prop.getOtherEnd() != null && prop.getOtherEnd().isNavigable() && !(prop.getOtherEnd().isDerived() || prop.getOtherEnd().isReadOnly())) {
				NakedStructuralFeatureMap otherMap = new NakedStructuralFeatureMap(prop.getOtherEnd());
				if (map.isManyToOne()) {
					implementCacheForManyToOne(umlOwner, map, otherMap, owner, setter);
				} else if (map.isOneToMany()) {
				} else if (map.isManyToMany()) {
				} else if (map.isOneToOne()) {
					implementCacheForOneToOne(umlOwner, map, otherMap, owner, setter);
				}
			} else {
				if (!prop.isDerived()) {
					if (prop.getBaseType() instanceof INakedEntity) {
						if (map.isOne()) {
							implementCacheForOneToOne(umlOwner, map, owner, setter);
						} else if (map.isMany()) {
							implementCacheForEmbeddedMany(umlOwner, map, owner, setter);
						} else {
						}
					} else {
						if (map.isMany()) {
							implementCacheForEmbeddedMany(umlOwner, map, owner, setter);
						}
					}
				}
			}
		}
		return setter;
	}

	private void implementCacheForOneToOne(INakedClassifier umlOwner, NakedStructuralFeatureMap map, NakedStructuralFeatureMap otherMap,
			OJAnnotatedClass owner, OJOperation setter) {
		OJIfStatement ifCurrentNotNull = new OJIfStatement("this." + map.umlName() + "!=null");
		ifCurrentNotNull.addToThenPart("this." + map.umlName() + "." + otherMap.internalRemover() + "((" + otherMap.javaBaseTypePath().getLast() + ")this)");
		setter.getBody().addToStatements(ifCurrentNotNull);
		OJIfStatement ifVarNotNull = new OJIfStatement(map.umlName() + "!=null");
		ifVarNotNull.addToThenPart(map.umlName() + "." + otherMap.internalAdder() + "((" + otherMap.javaBaseTypePath().getLast() + ")this)");
		ifVarNotNull.addToThenPart("this." + map.umlName() + "=" + map.umlName());
		ifVarNotNull.addToElsePart("this." + map.umlName() + "=null");
		setter.getBody().addToStatements(ifVarNotNull);
	}

	private void implementCacheForOneToOne(INakedClassifier umlOwner, NakedStructuralFeatureMap map, OJAnnotatedClass owner, OJOperation setter) {
		OJIfStatement ifVarNotNull = new OJIfStatement(map.umlName() + "!=null");
		ifVarNotNull.addToThenPart("this." + map.umlName() + "=" + map.umlName());
		ifVarNotNull.addToElsePart("this." + map.umlName() + "=null");
		setter.getBody().addToStatements(ifVarNotNull);
	}

	private void implementCacheForEmbeddedMany(INakedClassifier umlOwner, NakedStructuralFeatureMap map, OJAnnotatedClass owner, OJOperation setter) {
		OJIfStatement ifVarNotNull = new OJIfStatement(map.umlName() + "!=null");
		ifVarNotNull.addToThenPart("this." + map.umlName() + ".clear()");
		ifVarNotNull.addToThenPart("this." + map.umlName() + ".tinkerAddAll(" + map.umlName() + ")");
		ifVarNotNull.addToElsePart("this." + map.umlName() + ".clear()");
		setter.getBody().addToStatements(ifVarNotNull);
	}

	private void implementCacheForManyToOne(INakedClassifier umlOwner, NakedStructuralFeatureMap map, NakedStructuralFeatureMap otherMap,
			OJAnnotatedClass owner, OJOperation setter) {
		OJIfStatement ifCurrentNotNull = new OJIfStatement("this." + map.umlName() + "!=null");
		if (map.getProperty().isOrdered()) {
			ifCurrentNotNull.addToThenPart("((TinkerList<" + otherMap.javaBaseTypePath().getLast() + ">)this." + map.umlName() + "." + otherMap.getter()
					+ "()).tinkerRemove((" + otherMap.javaBaseTypePath().getLast() + ")this)");
		} else {
			ifCurrentNotNull.addToThenPart("((TinkerSet<" + otherMap.javaBaseTypePath().getLast() + ">)this." + map.umlName() + "." + otherMap.getter()
					+ "()).tinkerRemove((" + otherMap.javaBaseTypePath().getLast() + ")this)");
		}
		setter.getBody().addToStatements(ifCurrentNotNull);
		OJIfStatement ifVarNotNull = new OJIfStatement(map.umlName() + "!=null");
		if (map.getProperty().isOrdered()) {
			ifVarNotNull.addToThenPart("((TinkerList<" + otherMap.javaBaseTypePath().getLast() + ">)" + map.umlName() + "." + otherMap.getter()
					+ "()).tinkerAdd((" + otherMap.javaBaseTypePath().getLast() + ")this)");
		} else {
			ifVarNotNull.addToThenPart("((TinkerSet<" + otherMap.javaBaseTypePath().getLast() + ">)" + map.umlName() + "." + otherMap.getter()
					+ "()).tinkerAdd((" + otherMap.javaBaseTypePath().getLast() + ")this)");
		}
		ifVarNotNull.addToThenPart("this." + map.umlName() + "=" + map.umlName());
		ifVarNotNull.addToElsePart("this." + map.umlName() + "=null");
		setter.getBody().addToStatements(ifVarNotNull);
	}

	private void addToParentCollection(OJAnnotatedClass originalClass, INakedEntity c, NakedStructuralFeatureMap endToCompositeMap,
			NakedStructuralFeatureMap map) {
		OJOperation initVertex = originalClass.findOperation(TinkerTransformation.INIT_VERTEX, Arrays.asList(endToCompositeMap.javaBaseTypePath()));
		OJSimpleStatement addToParentCollection;
		if (map.getProperty().isOrdered()) {
			if (map.isMany()) {
				addToParentCollection = new OJSimpleStatement("((TinkerList<" + map.javaBaseTypePath().getLast() + ">)owningObject." + map.getter()
						+ "()).tinkerAdd(this)");
				initVertex.getBody().getStatements().add(0, addToParentCollection);
				originalClass.addToImports(TinkerUtil.tinkerList);
			}
		} else {
			if (map.isMany()) {
				addToParentCollection = new OJSimpleStatement("((TinkerSet<" + map.javaBaseTypePath().getLast() + ">)owningObject." + map.getter()
						+ "()).tinkerAdd(this)");
				initVertex.getBody().getStatements().add(0, addToParentCollection);
				originalClass.addToImports(TinkerUtil.tinkerSet);
			}
		}
	}

	public OJOperation buildGetter(OJAnnotatedClass owner, NakedStructuralFeatureMap map, boolean returnDefault) {
		OJOperation getter = owner.findOperation(map.getter(), Collections.EMPTY_LIST);
		if (owner instanceof OJAnnotatedInterface) {
		} else if (returnDefault) {
		} else {
			INakedProperty prop = map.getProperty();
			if (prop.getOtherEnd() != null && prop.getOtherEnd().isNavigable() && !(prop.getOtherEnd().isDerived() || prop.getOtherEnd().isReadOnly())) {
				if (map.isManyToOne()) {
					implementCacheGetterForOne(map, getter);
				} else if (map.isOneToMany()) {
					implementCacheGetterForMany(map, getter);
				} else if (map.isManyToMany()) {
					implementCacheGetterForMany(map, getter);
				} else if (map.isOneToOne()) {
					implementCacheGetterForOne(map, getter);
				}
			} else {
				if (!prop.isDerived()) {
					if (prop.getBaseType() instanceof INakedEntity) {
						if (map.isOne()) {
							implementCacheGetterForOne(map, getter);
						} else if (map.isMany()) {
							implementCacheGetterForMany(map, getter);
						} else {
						}
					} else {
						if (map.isMany()) {
							implementCacheGetterForEmbeddedMany(map, getter);
						}
					}
				}
			}

		}
		getter.setStatic(map.isStatic());
		return getter;
	}

	private void implementCacheGetterForEmbeddedMany(NakedStructuralFeatureMap map, OJOperation getter) {
		OJIfStatement ifEmpty = new OJIfStatement("this." + map.umlName() + ".isEmpty()");
		OJField result = getter.getBody().findLocal(TinkerAttributeImplementorStrategy.EMBEDDED_MANY_RESULT);
		ifEmpty.getThenPart().addToLocals(result);
		OJIfStatement ifNotNull = (OJIfStatement) getter.getBody().findStatementRecursive(TinkerAttributeImplementorStrategy.IFNOTNULL_EMBEDDED_MANY);
		ifNotNull.getThenPart().getStatements().clear();
		ifNotNull.addToThenPart(map.umlName() + ".tinkerAddAll(" + TinkerAttributeImplementorStrategy.EMBEDDED_MANY_RESULT + ")");
		ifNotNull.setElsePart(null);
		getter.getBody().removeAllFromStatements();
		getter.getBody().removeAllFromLocals();
		ifEmpty.addToThenPart(ifNotNull);
		getter.getBody().addToStatements(ifEmpty);
		getter.getBody().addToStatements("return this." + map.umlName());
	}

	private void implementCacheGetterForOne(NakedStructuralFeatureMap map, OJOperation getter) {
		OJIfStatement ifOneNull = new OJIfStatement("this." + map.umlName() + "==null");
		ifOneNull.addToThenPart(getter.getBody().findStatement(TinkerAttributeImplementorStrategy.POLYMORPHIC_GETTER_FOR_TO_ONE_ITER));
		OJIfStatement ifIter = (OJIfStatement) getter.getBody().findStatement(TinkerAttributeImplementorStrategy.POLYMORPHIC_GETTER_FOR_TO_ONE_IF);
		getter.getBody().removeAllFromStatements();
		ifOneNull.addToThenPart(ifIter);
		OJIfStatement ifEdge = (OJIfStatement) ifIter.getThenPart().findStatement(TinkerSoftDeleteTransformation.IF_EDGE_NOT_DELETED);
		OJTryStatement tryConstructor = (OJTryStatement) ifEdge.getThenPart().findStatement(
				TinkerAttributeImplementorStrategy.POLYMORPHIC_GETTER_FOR_TO_ONE_TRY);
		OJSimpleStatement construction = (OJSimpleStatement) tryConstructor.getTryPart().getStatements().get(1);
		construction.setExpression(construction.getExpression().replace("return", "this." + map.umlName() + " = "));
		getter.getBody().addToStatements(ifOneNull);
		getter.getBody().addToStatements("return this." + map.umlName());
	}

	private void implementCacheGetterForMany(NakedStructuralFeatureMap map, OJOperation getter) {
		getter.getBody().removeAllFromLocals();
		OJIfStatement ifEmpty = new OJIfStatement("this." + map.umlName() + ".isEmpty()");
		OJSimpleStatement simple = (OJSimpleStatement) getter.getBody().findStatement(TinkerAttributeImplementorStrategy.POLYMORPHIC_GETTER_FOR_TO_MANY_ITER);
		ifEmpty.addToThenPart(simple);
		OJForStatement forS = (OJForStatement) getter.getBody().findStatement(TinkerAttributeImplementorStrategy.POLYMORPHIC_GETTER_FOR_TO_MANY_FOR);
		ifEmpty.addToThenPart(forS);
		OJIfStatement ss = (OJIfStatement) forS.getBody().findStatement(TinkerSoftDeleteTransformation.FOR_MANY_IF_NOT_DELETED);
		OJTryStatement tryPart = (OJTryStatement) ss.getThenPart().findStatement(TinkerAttributeImplementorStrategy.POLYMORPHIC_GETTER_FOR_TO_MANY_TRY);
		OJSimpleStatement construction = (OJSimpleStatement) tryPart.getTryPart().findStatement(
				TinkerAttributeImplementorStrategy.POLYMORPHIC_GETTER_FORMANY_CONSTRUCTION);
		construction.setExpression(construction.getExpression().replace("result.add", "this." + map.umlName() + ".tinkerAdd"));
		getter.getBody().removeAllFromStatements();
		getter.getBody().addToStatements(ifEmpty);
		getter.getBody().addToStatements("return this." + map.umlName());
	}

	private void initializeCollection(INakedClassifier umlOwner, OJAnnotatedClass owner, NakedStructuralFeatureMap map) {
		OJField field = owner.findField(map.umlName());
		if (map.getProperty().isOrdered()) {
			OJPathName tinkerList = new OJPathName(TinkerUtil.tinkerList.toJavaString());
			tinkerList.addToElementTypes(map.javaBaseTypePath());
			field.setType(tinkerList);
		} else {
			OJPathName tinkerSet = new OJPathName(TinkerUtil.tinkerSet.toJavaString());
			tinkerSet.addToElementTypes(map.javaBaseTypePath());
			field.setType(tinkerSet);
		}
		field.setInitExp(null);
		if (umlOwner instanceof INakedEntity) {
			INakedEntity entity = (INakedEntity) umlOwner;
			if (entity.getEndToComposite() != null) {
				NakedStructuralFeatureMap compositeEndMap = new NakedStructuralFeatureMap(entity.getEndToComposite());
				OJConstructor constructorWithOwner = owner.findConstructor(compositeEndMap.javaBaseTypePath());
				OJTryStatement tryForCollectionWithOwner = (OJTryStatement) constructorWithOwner.getBody().findStatement("collectionInitializer");
				if (tryForCollectionWithOwner == null) {
					tryForCollectionWithOwner = new OJTryStatement();
					tryForCollectionWithOwner.setName("collectionInitializer");
					constructorWithOwner.getBody().getStatements().add(1, tryForCollectionWithOwner);
					tryForCollectionWithOwner.setCatchParam(new OJParameter("e", new OJPathName("java.lang.Exception")));
					tryForCollectionWithOwner.getCatchPart().addToStatements("new RuntimeException(e)");
				}
				tryForCollectionWithOwner.getTryPart().addToStatements(TinkerUtil.constructTinkerCollectionInit(owner, map));
			}

			OJConstructor defaultConstructor = owner.getDefaultConstructor();
			OJConstructor vertexConstructor = owner.findConstructor(TinkerUtil.vertexPathName);
			OJTryStatement tryForCollectionWithOwner = (OJTryStatement) defaultConstructor.getBody().findStatement("collectionInitializer");
			if (tryForCollectionWithOwner == null) {
				tryForCollectionWithOwner = new OJTryStatement();
				tryForCollectionWithOwner.setName("collectionInitializer");
				defaultConstructor.getBody().getStatements().add(1, tryForCollectionWithOwner);
				vertexConstructor.getBody().getStatements().add(1, tryForCollectionWithOwner);
				tryForCollectionWithOwner.setCatchParam(new OJParameter("e", new OJPathName("java.lang.Exception")));
				tryForCollectionWithOwner.getCatchPart().addToStatements("new RuntimeException(e)");
			}
			tryForCollectionWithOwner.getTryPart().addToStatements(TinkerUtil.constructTinkerCollectionInit(owner, map));
			if (map.getProperty().getOtherEnd() != null) {
				owner.addToImports(TinkerUtil.tinkerHashSetImpl);
				owner.addToImports(TinkerUtil.tinkerArrayListImpl);
			} else {
				owner.addToImports(TinkerUtil.tinkerEmbeddedHashSetImpl);
				owner.addToImports(TinkerUtil.tinkerEmbeddedArrayListImpl);
			}
		}
	}
}
