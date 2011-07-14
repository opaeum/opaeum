package org.nakeduml.tinker.basicjava.tinker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import net.sf.nakeduml.feature.visit.VisitAfter;
import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.javageneration.AbstractJavaProducingVisitor;
import net.sf.nakeduml.javageneration.NakedStructuralFeatureMap;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedBehavioredClassifier;
import net.sf.nakeduml.metamodel.core.INakedAssociationClass;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedEntity;
import net.sf.nakeduml.metamodel.core.INakedInterface;
import net.sf.nakeduml.metamodel.core.INakedProperty;
import net.sf.nakeduml.metamodel.core.INakedSimpleType;
import net.sf.nakeduml.metamodel.core.INakedStructuredDataType;
import net.sf.nakeduml.metamodel.core.internal.StereotypeNames;

import org.nakeduml.java.metamodel.OJClass;
import org.nakeduml.java.metamodel.OJConstructor;
import org.nakeduml.java.metamodel.OJField;
import org.nakeduml.java.metamodel.OJForStatement;
import org.nakeduml.java.metamodel.OJIfStatement;
import org.nakeduml.java.metamodel.OJOperation;
import org.nakeduml.java.metamodel.OJParameter;
import org.nakeduml.java.metamodel.OJPathName;
import org.nakeduml.java.metamodel.OJSimpleStatement;
import org.nakeduml.java.metamodel.OJStatement;
import org.nakeduml.java.metamodel.OJTryStatement;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedClass;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedInterface;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedOperation;
import org.nakeduml.tinker.composition.tinker.TinkerCompositionNodeStrategy;

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

	@VisitBefore(matchSubclasses = true, match = { INakedEntity.class, INakedStructuredDataType.class, INakedAssociationClass.class })
	public void visitEntity(INakedEntity entity) {
		if (OJUtil.hasOJClass(entity) && !(entity instanceof INakedSimpleType)) {
			OJAnnotatedClass owner = findJavaClass(entity);
			
			if (entity.getEndToComposite()!=null && !entity.getEndToComposite().isDerived()) {
				NakedStructuralFeatureMap endToCompositeMap = new NakedStructuralFeatureMap(entity.getEndToComposite());
				NakedStructuralFeatureMap otherMap = new NakedStructuralFeatureMap(entity.getEndToComposite().getOtherEnd());
				addToParentCache(owner, entity, endToCompositeMap, otherMap);
			}
			
			OJAnnotatedOperation markDeleted = (OJAnnotatedOperation) owner.findOperation("markDeleted", Collections.EMPTY_LIST);
			removeFromCache(entity, owner, markDeleted);
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
	
	private void removeFromCache(INakedBehavioredClassifier ew, OJClass ojClass, OJAnnotatedOperation markDeleted) {
		List<INakedProperty> properties = new ArrayList<INakedProperty>();
		if (ew.getGeneralizations().isEmpty()) {
			properties.addAll(ew.getEffectiveAttributes());
		} else {
			properties.addAll(ew.getOwnedAttributes());
		}
		//This is to ensure that the vertex removal happens lasts, after being removed from the cache
		int indexOfRemoveVertex = 0;
		OJStatement removeVertex = markDeleted.getBody().findStatement(TinkerCompositionNodeStrategy.REMOVE_VERTEX);
		if (removeVertex!=null) {
			indexOfRemoveVertex = markDeleted.getBody().getStatements().indexOf(removeVertex);
			if (indexOfRemoveVertex == -1) {
				indexOfRemoveVertex = 0;
			}
		}
		for (INakedProperty np : properties) {
			if (np.getOtherEnd() != null && !np.isDerived()) {
				NakedStructuralFeatureMap map = new NakedStructuralFeatureMap(np);
				NakedStructuralFeatureMap otherMap = new NakedStructuralFeatureMap(np.getOtherEnd());
				if (map.isManyToMany()) {
					implementInternalRemoveMany(ojClass, markDeleted, map, indexOfRemoveVertex);
				} else if (map.isManyToOne() && np.getOtherEnd().isNavigable()) {
					OJIfStatement ifNotNull = new OJIfStatement(map.umlName() + "!=null", map.umlName() + "." + otherMap.internalRemover()
							+ "(this)");
					markDeleted.getBody().addToStatements(indexOfRemoveVertex, ifNotNull);
				} else if (!np.isComposite() && map.isOneToMany() && np.getOtherEnd().isNavigable()) {
					implementInternalRemoveMany(ojClass, markDeleted, map, indexOfRemoveVertex);
				} else if (map.isOneToOne() && !np.isInverse() && np.getOtherEnd().isNavigable() && !np.isDerived() && !np.isDerivedUnion()) {
					OJIfStatement ifNotNull = new OJIfStatement(map.umlName() + "!=null", map.umlName() + "." + otherMap.internalRemover()
							+ "(this)");
					markDeleted.getBody().addToStatements(indexOfRemoveVertex,  ifNotNull);
				}
			}
		}
	}	

	private void implementInternalRemoveMany(OJClass ojClass, OJAnnotatedOperation markDeleted, NakedStructuralFeatureMap map, int indexOfRemoveVertex) {
		markDeleted.getBody().addToStatements(indexOfRemoveVertex, TinkerUtil.constructNameForInternalManiesRemoval(map) + "()");
		OJAnnotatedOperation internalRemoveManies = new OJAnnotatedOperation(TinkerUtil.constructNameForInternalManiesRemoval(map));
		ojClass.addToOperations(internalRemoveManies);
		OJField tmp = new OJField();
		tmp.setName("tmp");
		OJPathName tmpPath = map.javaDefaultTypePath();
		tmp.setType(tmpPath);
		tmp.setInitExp("new "+map.javaDefaultTypePath()+"<"+map.javaBaseTypePath().getLast()+">(this." + map.umlName() + ")");
		internalRemoveManies.getBody().addToLocals(tmp);
		OJForStatement forStatement = new OJForStatement("o", map.javaBaseTypePath(), "tmp");
		forStatement.getBody().addToStatements(map.internalRemover() + "(o)");
		if (map.getProperty().getOtherEnd()!=null && map.getProperty().getOtherEnd().isNavigable()) {
			NakedStructuralFeatureMap otherMap = new NakedStructuralFeatureMap(map.getProperty().getOtherEnd());
			forStatement.getBody().addToStatements("o." + otherMap.internalRemover() + "(this)");
		}
		internalRemoveManies.getBody().addToStatements(forStatement);
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
	}

	private OJOperation buildRemover(OJAnnotatedClass owner, NakedStructuralFeatureMap map) {
		OJOperation remover = owner.findOperation(map.remover(), Arrays.asList(map.javaBaseTypePath()));
		INakedProperty p = map.getProperty();
		if (p.getOtherEnd() != null && p.getOtherEnd().isNavigable() && !(p.getOtherEnd().isDerived() || p.getOtherEnd().isReadOnly())) {
			NakedStructuralFeatureMap otherMap = new NakedStructuralFeatureMap((p).getOtherEnd());
			if (otherMap.isMany()) {
				remover.getBody().addToStatements(map.umlName() + "." + otherMap.internalRemover() + "(this)");
				remover.getBody().addToStatements(map.internalRemover() + "(" + map.umlName() + ")");
			} else {
			}
		} else {
			if (map.getProperty().getBaseType() instanceof INakedEntity) {
				remover.getBody().addToStatements(map.internalRemover() + "(" + map.umlName() + ")");
			} else {
				OJField tmp = remover.getBody().findLocal("tmp");
				String defaultValue = map.javaDefaultValue();
				tmp.setInitExp(defaultValue.substring(0, defaultValue.length()-1) + tmp.getInitExp() + ")");
			}			
		}
		owner.addToOperations(remover);
		return remover;
	}

	private OJOperation buildAdder(OJAnnotatedClass owner, NakedStructuralFeatureMap map) {
		OJOperation adder = owner.findOperation(map.adder(), Arrays.asList(map.javaBaseTypePath()));
		INakedProperty p = map.getProperty();
		if (p.getOtherEnd() != null && p.getOtherEnd().isNavigable() && !(p.getOtherEnd().isDerived() || p.getOtherEnd().isReadOnly())) {
			NakedStructuralFeatureMap otherMap = new NakedStructuralFeatureMap((p).getOtherEnd());
			if (otherMap.isMany()) {
				adder.getBody().addToStatements(map.umlName() + "." + otherMap.internalAdder() + "(this)");
				adder.getBody().addToStatements(map.internalAdder() + "(" + map.umlName() + ")");
			} else {
			}
		} else {
			if (map.getProperty().getBaseType() instanceof INakedEntity) {
				adder.getBody().addToStatements(map.internalAdder() + "(" + map.umlName() + ")");
			} else {
				OJField tmp = adder.getBody().findLocal("tmp");
				String defaultValue = map.javaDefaultValue();
				tmp.setInitExp(defaultValue.substring(0, defaultValue.length()-1) + tmp.getInitExp() + ")");
			}
		}
		return adder;
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
			ifCurrentNotNull.addToThenPart("this." + map.umlName() + "." + otherMap.internalRemover()
					+ "((" + otherMap.javaBaseTypePath().getLast() + ")this)");
		} else {
			ifCurrentNotNull.addToThenPart("this." + map.umlName() + "." + otherMap.internalRemover()
					+ "((" + otherMap.javaBaseTypePath().getLast() + ")this)");
		}
		
		setter.getBody().addToStatements(ifCurrentNotNull);
		OJIfStatement ifVarNotNull = new OJIfStatement(map.umlName() + "!=null");
		if (map.getProperty().isOrdered()) {
			ifVarNotNull.addToThenPart(map.umlName() + "." + otherMap.internalAdder()
					+ "((" + otherMap.javaBaseTypePath().getLast() + ")this)");
		} else {
			ifVarNotNull.addToThenPart(map.umlName() + "." + otherMap.internalAdder()
					+ "((" + otherMap.javaBaseTypePath().getLast() + ")this)");
		}
		ifVarNotNull.addToThenPart("this." + map.umlName() + "=" + map.umlName());
		ifVarNotNull.addToElsePart("this." + map.umlName() + "=null");
		setter.getBody().addToStatements(ifVarNotNull);
	}

	private void addToParentCache(OJAnnotatedClass originalClass, INakedEntity c, NakedStructuralFeatureMap endToCompositeMap,
			NakedStructuralFeatureMap map) {
		OJOperation initVertex = originalClass.findOperation(TinkerTransformation.INIT_VERTEX, Arrays.asList(endToCompositeMap.javaBaseTypePath()));
		initVertex.getBody().addToStatements("owningObject." + map.internalAdder() + "(this)");		
		initVertex.getBody().addToStatements("this." + endToCompositeMap.umlName() + "=owningObject");		
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
		OJField property = getter.getBody().findLocal(TinkerAttributeImplementorStrategy.EMBEDDED_MANY_PROPERTY_RESULT);
		ifEmpty.getThenPart().addToLocals(result);
		if (property!=null) {
			ifEmpty.getThenPart().addToLocals(property);
		}
		OJIfStatement ifNotNullEmbbedded = (OJIfStatement) getter.getBody().findStatementRecursive(TinkerAttributeImplementorStrategy.EMBEDDED_MANY_RESULT_IFNOTNULL);
		ifEmpty.addToThenPart(ifNotNullEmbbedded);
		getter.getBody().removeAllFromStatements();
		getter.getBody().removeAllFromLocals();
		ifEmpty.addToThenPart(map.umlName() + ".tinkerAddAll(" + TinkerAttributeImplementorStrategy.EMBEDDED_MANY_RESULT + ")");
		getter.getBody().addToStatements(ifEmpty);
		getter.getBody().addToStatements("return this." + map.umlName());
	}

	private void implementCacheGetterForOne(NakedStructuralFeatureMap map, OJOperation getter) {
		OJIfStatement ifOneNull = new OJIfStatement("this." + map.umlName() + "==null");
		ifOneNull.addToThenPart(getter.getBody().findStatement(TinkerAttributeImplementorStrategy.POLYMORPHIC_GETTER_FOR_TO_ONE_ITER));
		OJIfStatement ifIter = (OJIfStatement) getter.getBody().findStatement(TinkerAttributeImplementorStrategy.POLYMORPHIC_GETTER_FOR_TO_ONE_IF);
		getter.getBody().removeAllFromStatements();
		ifOneNull.addToThenPart(ifIter);
		OJTryStatement tryConstructor = (OJTryStatement) ifIter.getThenPart().findStatementRecursive(TinkerAttributeImplementorStrategy.POLYMORPHIC_GETTER_FOR_TO_ONE_TRY);
		
		OJSimpleStatement construction = (OJSimpleStatement) tryConstructor.getTryPart().getStatements().get(1);
		construction.setExpression(construction.getExpression().replace("return", "this." + map.umlName() + " = "));
		getter.getBody().addToStatements(ifOneNull);
		getter.getBody().addToStatements("return this." + map.umlName());
	}

	private void implementCacheGetterForMany(NakedStructuralFeatureMap map, OJOperation getter) {
		getter.getBody().removeAllFromLocals();
		OJIfStatement ifEmpty = new OJIfStatement("this." + map.umlName() + ".isEmpty()");
		OJSimpleStatement simple = (OJSimpleStatement) getter.getBody().findStatementRecursive(TinkerAttributeImplementorStrategy.POLYMORPHIC_GETTER_FOR_TO_MANY_ITER);
		ifEmpty.addToThenPart(simple);
		OJForStatement forS = (OJForStatement) getter.getBody().findStatementRecursive(TinkerAttributeImplementorStrategy.POLYMORPHIC_GETTER_FOR_TO_MANY_FOR);
		ifEmpty.addToThenPart(forS);
		OJTryStatement tryPart = (OJTryStatement) forS.getBody().findStatementRecursive(TinkerAttributeImplementorStrategy.POLYMORPHIC_GETTER_FOR_TO_MANY_TRY);
		OJSimpleStatement construction = (OJSimpleStatement) tryPart.getTryPart().findStatementRecursive(
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
				OJSimpleStatement collectionInit = new OJSimpleStatement(TinkerUtil.constructTinkerCollectionInit(owner, map, false));
				collectionInit.setName(map.fieldname());
				tryForCollectionWithOwner.getTryPart().addToStatements(collectionInit);
			}

			OJConstructor defaultConstructor = owner.findConstructor(new OJPathName("java.lang.Boolean"));
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
			OJSimpleStatement collectionInit = new OJSimpleStatement(TinkerUtil.constructTinkerCollectionInit(owner, map, false));
			collectionInit.setName(map.fieldname());
			tryForCollectionWithOwner.getTryPart().addToStatements(collectionInit);
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
