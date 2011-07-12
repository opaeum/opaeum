package net.sf.nakeduml.javageneration.composition;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import net.sf.nakeduml.feature.visit.VisitAfter;
import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.javageneration.AbstractTestDataGenerator;
import net.sf.nakeduml.javageneration.JavaTextSource;
import net.sf.nakeduml.javageneration.NakedClassifierMap;
import net.sf.nakeduml.javageneration.NakedStructuralFeatureMap;
import net.sf.nakeduml.javageneration.persistence.JpaStrategy;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.linkage.GeneralizationUtil;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedBehavioredClassifier;
import net.sf.nakeduml.metamodel.core.ICompositionParticipant;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedEntity;
import net.sf.nakeduml.metamodel.core.INakedEnumeration;
import net.sf.nakeduml.metamodel.core.INakedHelper;
import net.sf.nakeduml.metamodel.core.INakedInterface;
import net.sf.nakeduml.metamodel.core.INakedPrimitiveType;
import net.sf.nakeduml.metamodel.core.INakedProperty;
import net.sf.nakeduml.metamodel.core.INakedSimpleType;
import net.sf.nakeduml.metamodel.models.INakedModel;
import nl.klasse.octopus.model.IClassifier;
import nl.klasse.octopus.model.IEnumerationType;

import org.nakeduml.java.metamodel.OJBlock;
import org.nakeduml.java.metamodel.OJField;
import org.nakeduml.java.metamodel.OJForStatement;
import org.nakeduml.java.metamodel.OJIfStatement;
import org.nakeduml.java.metamodel.OJParameter;
import org.nakeduml.java.metamodel.OJPathName;
import org.nakeduml.java.metamodel.OJSimpleStatement;
import org.nakeduml.java.metamodel.OJVisibilityKind;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedClass;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedField;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedOperation;
import org.nakeduml.java.metamodel.annotation.OJAnnotationValue;
import org.nakeduml.name.NameConverter;


public class ConfigurableCompositionDataGenerator extends AbstractTestDataGenerator{
	public Map<String,DataPopulatorPropertyEntry> modelInstanceMap = new HashMap<String,DataPopulatorPropertyEntry>();
	@VisitAfter(matchSubclasses = true)
	public void placeInTree(INakedModel model){
		for(String key:modelInstanceMap.keySet()){
			DataPopulatorPropertyEntry current = modelInstanceMap.get(key);
			DataPopulatorPropertyEntry parent = null;
			int indexOf = key.indexOf(".");
			if(indexOf != -1){
				parent = modelInstanceMap.get(key.substring(indexOf + 1, key.length()));
			}
			current.setParent(parent);
		}
	}

	protected List<ICompositionParticipant> getHierarchicalRoots(ICompositionParticipant entity) {
		List<ICompositionParticipant> result = new ArrayList<ICompositionParticipant>();
		List<IClassifier> generalizations = entity.getGeneralizations();
		for (IClassifier generalization : generalizations) {
			ICompositionParticipant gen = (ICompositionParticipant) generalization;
			Collection<INakedClassifier> subs = getConcreteSubclassifiersOf(gen);
			for (IClassifier sub : subs) {
				if (!sub.equals(entity)) {
					result.add((ICompositionParticipant) sub);
				}
			}
		}
		return result;
	}


	@VisitBefore(matchSubclasses = true)
	public void visit(INakedEntity entity){
		if(OJUtil.hasOJClass(entity) && !entity.getIsAbstract()){
			OJAnnotatedClass testDataClass = new OJAnnotatedClass();
			OJAnnotatedClass theClass = findJavaClass(entity);
			testDataClass.setName(getTestDataName(entity));
			theClass.getMyPackage().addToClasses(testDataClass);
			addChildrenFields(entity, testDataClass);
			addPropertyUtil(testDataClass);
			addCreate(entity, testDataClass);
			populateModelInstanceMap(entity);
			addPopulateData(entity, testDataClass, false);
			addPopulateData(entity, testDataClass, true);
			// TODO put in integrated adapator?
			super.createTextPath(testDataClass, JavaTextSource.OutputRootId.ADAPTOR_GEN_SRC);
		}
	}
	private void populateSelf(ICompositionParticipant entity,DataPopulatorPropertyEntry currentEntry){
		for(INakedProperty f:entity.getEffectiveAttributes()){
			NakedStructuralFeatureMap map = new NakedStructuralFeatureMap(f);
			boolean isReadOnly = (f instanceof INakedProperty && (f).isReadOnly());
			if(f instanceof INakedProperty){
				INakedProperty p = f;
				boolean isEndToComposite = p.getOtherEnd() != null && p.getOtherEnd().isComposite();
				if(p.getInitialValue() == null && !isEndToComposite){
					if(map.isOne() && !(p.isDerived() || isReadOnly || p.isInverse())){
						if(!(couldBePersistedInModel(map))){
							// if (!p.getName().equals("name")) {
							currentEntry.addToOtherProperties(p);
							// }
						}
					}
				}
			}
		}
	}
	private boolean couldBePersistedInModel(NakedStructuralFeatureMap map){
		Collection<INakedClassifier> classes = GeneralizationUtil.getAllSubClassifiers(map.getProperty().getNakedBaseType(), getModelInScope());
		for(INakedClassifier c:classes){
			if(isPersistent(c)){
				return true;
			}
		}
		return isPersistent(map.getProperty().getNakedBaseType());
	}
	private void addPropertyUtil(OJAnnotatedClass testDataClass){
		OJAnnotatedField propertyUtil = new OJAnnotatedField();
		propertyUtil.setName("dataGeneratorProperty");
		propertyUtil.setType(new OJPathName("org.nakeduml.runtime.adaptor.DataGeneratorProperty"));
		OJAnnotationValue in = new OJAnnotationValue(new OJPathName("javax.inject.Inject"));
		propertyUtil.putAnnotation(in);
		testDataClass.addToFields(propertyUtil);
	}
	private void addPopulateData(ICompositionParticipant entity,OJAnnotatedClass testDataClass,boolean forExport){
		final String FUNCTION = forExport ? "export" : "populate";
		OJAnnotatedOperation populate = new OJAnnotatedOperation();
		populate.setName(FUNCTION + entity.getMappingInfo().getJavaName());
		populate.setBody(new OJBlock());
		OJField count = new OJField();
		count.setName("i");
		count.setType(new OJPathName("java.util.Integer"));
		count.setInitExp("0");
		populate.getBody().addToLocals(count);
		OJBlock block;
		INakedProperty parent = entity.getEndToComposite();
		boolean hasOneToOneToOwner = false;
		if(parent != null){
			OJParameter owner = new OJParameter();
			NakedStructuralFeatureMap map = new NakedStructuralFeatureMap(parent);
			hasOneToOneToOwner = map.isOneToOne();
			owner.setName(parent.getMappingInfo().getJavaName().toString());
			OJPathName javaTypePath = map.javaTypePath();
			owner.setType(javaTypePath);
			populate.addToParameters(owner);
			OJPathName instancePath = new OJPathName(entity.getMappingInfo().getQualifiedJavaName());
			NakedStructuralFeatureMap otherMap = new NakedStructuralFeatureMap(parent.getOtherEnd());
			if(otherMap.isMany()){
				if(forExport){
					populate.getBody().addToStatements(
							"dataGeneratorProperty.putExportProperty(" + parent.getMappingInfo().getJavaName().toString() + ".getUid() + " + "\"."
									+ entity.getMappingInfo().getJavaName().getDecapped() + ".size\"," + parent.getMappingInfo().getJavaName().toString() + "."
									+ otherMap.getter() + "().size())");
				}
				testDataClass.addToImports(otherMap.javaBaseDefaultTypePath());
				OJForStatement forX = new OJForStatement();
				forX.setElemType(otherMap.javaBaseTypePath());
				forX.setElemName("iter");
				forX.setCollection(parent.getMappingInfo().getJavaName().toString() + "." + otherMap.getter() + "()");
				OJIfStatement ifStatement = new OJIfStatement();
				ifStatement.setCondition("iter instanceof " + instancePath.getLast());
				OJBlock ifBlock = new OJBlock();
				ifStatement.setThenPart(ifBlock);
				if(forExport){
					OJField manyCount = new OJField();
					manyCount.setName("manyCount");
					manyCount.setType(new OJPathName("java.util.Integer"));
					manyCount.setInitExp("0");
					ifBlock.addToLocals(manyCount);
				}
				OJField instance = new OJField();
				instance.setName(entity.getMappingInfo().getJavaName().getDecapped().toString());
				instance.setType(instancePath);
				instance.setInitExp("(" + instancePath.getLast() + ")iter");
				ifBlock.addToLocals(instance);
				OJBlock forBlock = new OJBlock();
				forX.setBody(forBlock);
				forBlock.addToStatements(ifStatement);
				block = ifBlock;
				populate.getBody().addToStatements(forX);
			}else{
				OJField field = new OJField();
				field.setName(NameConverter.decapitalize(entity.getName()));
				field.setType(instancePath);
				field.setInitExp("(" + instancePath.getLast() + ")" + parent.getMappingInfo().getJavaName().toString() + "." + otherMap.getter() + "()");
				populate.getBody().addToLocals(field);
				OJIfStatement checkNull = new OJIfStatement(field.getName() + "==null", "return");
				populate.getBody().addToStatements(checkNull);
				block = populate.getBody();
			}
		}else{
			OJParameter owner = new OJParameter();
			owner.setName(entity.getMappingInfo().getJavaName().getDecapped().getPlural().toString());
			OJPathName ownerType = new OJPathName("java.util.List");
			OJPathName javaTypePath = new OJPathName(entity.getMappingInfo().getQualifiedJavaName());
			ownerType.addToElementTypes(javaTypePath);
			owner.setType(ownerType);
			OJForStatement rootExportFor = new OJForStatement(entity.getMappingInfo().getJavaName().getDecapped().toString(), javaTypePath, owner.getName());
			populate.addToParameters(owner);
			block = populate.getBody();
			block.addToStatements(rootExportFor);
			block = rootExportFor.getBody();
			if(forExport){
				populate.getBody().addToStatements(
						"dataGeneratorProperty.putExportProperty(\"" + entity.getMappingInfo().getJavaName().getDecapped() + ".size\", " + owner.getName() + ".size() )");
			}
		}
		testDataClass.addToOperations(populate);
		if(forExport){
			populateSelf(testDataClass, entity, block, true);
			populateToOneOrManyToMany(testDataClass, hasOneToOneToOwner, entity, block);
		}else{
			populateSourcePopulationSelf(testDataClass, entity, block);
		}
		block.addToStatements(new OJSimpleStatement("i++"));
		addPopulateChildren(entity, testDataClass, block, forExport);
	}
	private void populateToOneOrManyToMany(OJAnnotatedClass test,boolean hasOneToOneToOwner,ICompositionParticipant entity,OJBlock populate){
		for(INakedProperty f:entity.getEffectiveAttributes()){
			NakedStructuralFeatureMap map = new NakedStructuralFeatureMap(f);
			if(f instanceof INakedProperty){
				boolean isEndToComposite = f.getOtherEnd() != null && f.getOtherEnd().isComposite();
				if(f.getInitialValue() == null && !isEndToComposite){
					if((map.isManyToMany() || map.isOne()) && !(f.isDerived() || f.isReadOnly() || f.isInverse())){
						if(couldBePersistedInModel(map)){
							if(map.isManyToMany()){
								test.addToImports(map.javaBaseDefaultTypePath());
								OJForStatement forMany = new OJForStatement(f.getMappingInfo().getJavaName().getDecapped().toString(), map.javaBaseTypePath(), entity
										.getMappingInfo().getJavaName().getDecapped().toString()
										+ "." + map.getter() + "()");
								INakedProperty parent = entity.getEndToComposite();
								NakedStructuralFeatureMap otherMap = null;
								if(parent != null){
									otherMap = new NakedStructuralFeatureMap(parent.getOtherEnd());
								}
								String varName = otherMap != null && otherMap.isMany() ? "iter" : entity.getMappingInfo().getJavaName().getDecapped().toString();
								if(hasOneToOneToOwner){
									forMany.getBody().addToStatements(
											"dataGeneratorProperty.putExportProperty(" + varName + ".getName() + \"." + map.setter() + "\", "
													+ f.getMappingInfo().getJavaName().getDecapped().toString() + ".getUid())");
								}else{
									forMany.getBody().addToStatements(
											"dataGeneratorProperty.putExportProperty(" + varName + ".getName() + \"." + map.setter() + "_\" + manyCount++, "
													+ f.getMappingInfo().getJavaName().getDecapped().toString() + ".getUid())");
								}
								populate.addToStatements(forMany);
							}else{
								String javaType = map.javaType();
								if(map.isManyToMany()){
									javaType = javaType.replace("<", "");
									javaType = javaType.replace(">", "");
								}
								String property = entity.getMappingInfo().getJavaName().getDecapped().toString() + ".getUid()" + "+\"."
										+ NameConverter.decapitalize(javaType) + "\"";
								populate.addToStatements(new OJIfStatement(entity.getMappingInfo().getJavaName().getDecapped().toString() + "." + map.getter()
										+ "()!=null", "dataGeneratorProperty.putExportProperty(" + property + ", "
										+ entity.getMappingInfo().getJavaName().getDecapped().toString() + "." + map.getter() + "().getUid())"));
							}
						}else if(f.getNakedBaseType() instanceof INakedInterface){
							// populate.addToStatements("!!!" +
							// f.getNakedBaseType().getName()
							// +
							// " does not have any persistent implementations. Source population is not resolvable");
						}
					}
				}
			}
		}
	}
	protected boolean isHierarchical(ICompositionParticipant c, INakedProperty p) {
		if (p.isComposite() && p.getNakedBaseType().equals(c)) {
			return true;
		}
		return false;
	}
	protected boolean isHierarchical(ICompositionParticipant c) {
		for (INakedProperty a : c.getEffectiveAttributes()) {
			if (isHierarchical(c, a)) {
				return true;
			}
		}
		return false;
	}

	protected boolean isInHierarchical(ICompositionParticipant c) {
		for (INakedProperty a : c.getEffectiveAttributes()) {
			if (isHierarchical(c, a)) {
				return true;
			}
		}
		if (c.getEndToComposite() != null && isInHierarchical((ICompositionParticipant) c.getEndToComposite().getBaseType())) {
			return true;
		} else {
			return false;
		}
	}

	protected void createHierarchicalEntries(ICompositionParticipant entity, List<StringBuilder> theList, StringBuilder currentPath) {
		if (currentPath.toString().isEmpty()) {
			currentPath.append(entity.getName());
		} else {
			currentPath.append("." + entity.getName());
		}
		if (isHierarchical(entity)) {
			theList.remove(currentPath);
			List<ICompositionParticipant> hierarchicalRoots = getHierarchicalRoots(entity);
			for (ICompositionParticipant hierarchicalEntityRoot : hierarchicalRoots) {
				StringBuilder alternativePath = new StringBuilder(currentPath.toString());
				theList.add(alternativePath);
				createHierarchicalEntries(hierarchicalEntityRoot, theList, alternativePath);
			}
		} else if (isCompositeParentAbstract(entity)) {
			theList.remove(currentPath);
			Collection<INakedClassifier> concreteImpls = GeneralizationUtil.getAllConcreteSubClassifiers(entity.getEndToComposite().getNakedBaseType(),getModelInScope());
			for (INakedClassifier concreteImpl : concreteImpls) {
				StringBuilder alternativePath = new StringBuilder(currentPath.toString());
				theList.add(alternativePath);
				//Will be an entity
				createHierarchicalEntries((ICompositionParticipant) concreteImpl, theList, alternativePath);
			}
		} else {
			if (entity.getEndToComposite() != null) {
				createHierarchicalEntries((ICompositionParticipant) entity.getEndToComposite().getBaseType(), theList, currentPath);
			}
		}

	}

	private boolean isCompositeParentAbstract(ICompositionParticipant entity) {
		if (entity.getEndToComposite() == null) {
			return false;
		} else {
			return entity.getEndToComposite().getBaseType().getIsAbstract();
		}
	}

	private void populateModelInstanceMap(ICompositionParticipant entity){
		List<StringBuilder> theList = new ArrayList<StringBuilder>();
		StringBuilder currentPath = new StringBuilder();
		theList.add(currentPath);
		createHierarchicalEntries(entity, theList, currentPath);
		for(StringBuilder key:theList){
			int level = key.toString().split("\\.").length - 1;
			DataPopulatorPropertyEntry entry = new DataPopulatorPropertyEntry(level, entity.getMappingInfo().getQualifiedJavaName(), entity.getMappingInfo()
					.getJavaName().getDecapped().toString()
					+ ".uid_0");
			modelInstanceMap.put(key.toString(), entry);
			populateSelf(entity, entry);
		}
	}
	private void populateSelf(OJAnnotatedClass testClass,ICompositionParticipant entity,OJBlock populate,boolean forExport){
		String configuredValue = "dataGeneratorProperty.getProperty(" + calcMapKeyForUid(entity) + "+i, \"" + UUID.randomUUID().toString() + "\")";
		populate.addToStatements(entity.getMappingInfo().getJavaName().getDecapped().toString() + ".setUid(" + configuredValue + ")");
		for(INakedProperty f:entity.getEffectiveAttributes()){
			NakedStructuralFeatureMap map = new NakedStructuralFeatureMap(f);
			boolean isReadOnly = (f instanceof INakedProperty && (f).isReadOnly());
			if(f instanceof INakedProperty){
				INakedProperty p = f;
				boolean isEndToComposite = p.getOtherEnd() != null && p.getOtherEnd().isComposite();
				if(p.getInitialValue() == null && !isEndToComposite){
					if(map.isOne() && !(f.isDerived() || isReadOnly || f.isInverse())){
						if(!(couldBePersistedInModel(map) || map.getProperty().getNakedBaseType() instanceof INakedInterface)){
							if(!forExport){
								String defaultValue = "";
								defaultValue = calculateDefaultStringValue(testClass, populate, p);
								String str = calcConfiguredValue(testClass, populate, entity, p, defaultValue);
								populate.addToStatements(entity.getMappingInfo().getJavaName().getDecapped().toString() + "." + map.setter() + "(" + str + ")");
							}else{
								String result = "";
								if(entity.getEndToComposite() != null){
									result = entity.getEndToComposite().getName() + ".getUid() + \"." + NameConverter.decapitalize(entity.getName()) + "." + p.getName()
											+ "_\" + String.valueOf(i)";
									populate.addToStatements("dataGeneratorProperty.putExportProperty(" + result + ", "
											+ entity.getMappingInfo().getJavaName().getDecapped().toString() + "." + map.getter() + "())");
								}else{
									result = NameConverter.decapitalize(entity.getName()) + "." + p.getName() + "_\" + String.valueOf(i)";
									populate.addToStatements("dataGeneratorProperty.putExportProperty(\"" + result + ", "
											+ entity.getMappingInfo().getJavaName().getDecapped().toString() + "." + map.getter() + "())");
								}
							}
						}
					}
				}
			}
		}
	}
	private void populateSourcePopulationSelf(OJAnnotatedClass test,ICompositionParticipant c,OJBlock populate){
		for(INakedProperty f:c.getEffectiveAttributes()){
			NakedStructuralFeatureMap map = new NakedStructuralFeatureMap(f);
			if(f instanceof INakedProperty){
				boolean isEndToComposite = f.getOtherEnd() != null && f.getOtherEnd().isComposite();
				if(f.getInitialValue() == null && !isEndToComposite){
					if((map.isManyToMany() || map.isOne()) && !(f.isDerived() || f.isReadOnly() || f.isInverse())){
						if(couldBePersistedInModel(map)){
							String defaultValue = super.lookup(test, f);
							OJIfStatement ifSourcePopulationNotEmpty = new OJIfStatement();
							ifSourcePopulationNotEmpty.setCondition("!" + c.getMappingInfo().getJavaName().getDecapped().toString() + "." + map.getter()
									+ "SourcePopulation().isEmpty()");
							OJBlock ifBlock = new OJBlock();
							OJForStatement forSourcePopulation = new OJForStatement();
							forSourcePopulation.setElemType(OJUtil.classifierPathname(f.getNakedBaseType()));
							test.addToImports(OJUtil.classifierPathname(f.getNakedBaseType()));
							// TODO use map.javaBaseType()
							String javaType = map.javaType();
							if(map.isManyToMany()){
								javaType = javaType.replace("<", "");
								javaType = javaType.replace(">", "");
							}
							forSourcePopulation.setElemName(NameConverter.decapitalize(javaType) + "Internal");
							forSourcePopulation.setCollection(c.getMappingInfo().getJavaName().getDecapped().toString() + "." + map.getter() + "SourcePopulation()");
							ifBlock.addToStatements(forSourcePopulation);
							ifSourcePopulationNotEmpty.setThenPart(ifBlock);
							OJBlock forSourcePopulationBlock = new OJBlock();
							forSourcePopulation.setBody(forSourcePopulationBlock);
							if(map.isManyToMany()){
								OJBlock forInternalPropertyBlock = createRetrievalOfMany(c, map, defaultValue, forSourcePopulationBlock);
								OJIfStatement ifMatch = createIfMatch(c, map, defaultValue, javaType);
								forInternalPropertyBlock.addToStatements(ifMatch);
							}else{
								OJIfStatement ifStatement2 = new OJIfStatement();
								ifStatement2.setCondition(NameConverter.decapitalize(javaType + "Internal") + ".getUid().equals(dataGeneratorProperty.getProperty("
										+ constructSourcePopulationProperty(c, map, defaultValue) + "\"))");
								OJBlock forBlock2 = new OJBlock();
								forBlock2.addToStatements(c.getMappingInfo().getJavaName().getDecapped().toString() + "." + map.setter() + "("
										+ NameConverter.decapitalize(javaType + "Internal") + ")");
								ifStatement2.setThenPart(forBlock2);
								forSourcePopulationBlock.addToStatements(ifStatement2);
							}
							populate.addToStatements(ifSourcePopulationNotEmpty);
						}else if(f.getNakedBaseType() instanceof INakedInterface){
							// populate.addToStatements("!!!" +
							// f.getNakedBaseType().getName()
							// +
							// " does not have any persistent implementations. Source population is not resolvable");
						}
					}
				}
			}
		}
	}
	public OJIfStatement createIfMatch(ICompositionParticipant c,NakedStructuralFeatureMap map,String defaultValue,String javaType){
		OJIfStatement ifMatch = new OJIfStatement();
		ifMatch.setCondition("propertyKey.equals(" + constructSourcePopulationProperty(c, map, defaultValue) + "_\"+count ) && dataGeneratorProperty.getProperty("
				+ constructSourcePopulationProperty(c, map, defaultValue) + "_\"+count ).equals(" + NameConverter.decapitalize(javaType + "Internal") + ".getUid())");
		OJBlock ifMatchThenPart = new OJBlock();
		ifMatchThenPart.addToStatements(c.getMappingInfo().getJavaName().getDecapped().toString() + "." + map.adder() + "("
				+ NameConverter.decapitalize(javaType + "Internal") + ")");
		ifMatchThenPart.addToStatements("break");
		ifMatch.setThenPart(ifMatchThenPart);
		return ifMatch;
	}
	public OJBlock createRetrievalOfMany(ICompositionParticipant c,NakedStructuralFeatureMap map,String defaultValue,OJBlock forBlock){
		OJField propertyKeys = new OJField();
		propertyKeys.setName("propertyKeys");
		OJPathName stringSet = new OJPathName("java.util.List");
		List<OJPathName> elementTypes = new ArrayList<OJPathName>();
		elementTypes.add(new OJPathName("java.lang.String"));
		stringSet.setElementTypes(elementTypes);
		propertyKeys.setType(stringSet);
		propertyKeys.setInitExp("dataGeneratorProperty.getPropertiesThatStartsWith(" + constructSourcePopulationProperty(c, map, defaultValue) + "\")");
		forBlock.addToLocals(propertyKeys);
		OJForStatement propertyKeysFor = new OJForStatement("", "", "propertyKey", "propertyKeys");
		propertyKeysFor.setElemType(new OJPathName("java.lang.String"));
		propertyKeysFor.setBody(new OJBlock());
		forBlock.addToStatements(propertyKeysFor);
		OJForStatement internalPropertyLoop = new OJForStatement("", "", "count", "dataGeneratorProperty.getSizeListOfPropertiesThatStartsWith("
				+ constructSourcePopulationProperty(c, map, defaultValue) + "\")");
		internalPropertyLoop.setElemType(new OJPathName("java.lang.Integer"));
		internalPropertyLoop.setBody(new OJBlock());
		propertyKeysFor.getBody().addToStatements(internalPropertyLoop);
		return internalPropertyLoop.getBody();
	}
	private String calculateDefaultStringValue(OJAnnotatedClass test,OJBlock block,INakedProperty f){
		if(f.getNakedBaseType() instanceof IEnumerationType){
			INakedEnumeration en = (INakedEnumeration) f.getNakedBaseType();
			NakedClassifierMap map = new NakedClassifierMap(en);
			if(en.getLiterals().size() > 0){
				return en.getLiterals().get(0).getName().toUpperCase();
			}else{
				return map.javaType() + ".has no literals!!!!";
			}
		}else if(f.getNakedBaseType() instanceof INakedSimpleType){
			INakedSimpleType baseType = (INakedSimpleType) f.getNakedBaseType();
			if(baseType.hasStrategy(ConfigurableDataStrategy.class)){
				return baseType.getStrategy(ConfigurableDataStrategy.class).getDefaultStringValue(test, block, f);
			}else if(f.getNakedBaseType() instanceof INakedPrimitiveType){
				String calculateDefaultValue = calculateDefaultValue(test, block, f);
				if(calculateDefaultValue.startsWith("\"") && calculateDefaultValue.endsWith("\"")){
					calculateDefaultValue = calculateDefaultValue.substring(1, calculateDefaultValue.length() - 1);
				}
				return calculateDefaultValue;
			}else{
				return "no ConfigurableDataStrategy strategy!!!";
			}
		}
		return "BLASDFASDFadsf";
	}
	private String calcConfiguredValue(OJAnnotatedClass clazz,OJBlock block,ICompositionParticipant c,INakedProperty f,String defaultStringValue){
		String configuredValue = "dataGeneratorProperty.getProperty(" + calcMapKey(c, f) + "+i, \"" + defaultStringValue + "\")";
		if(f.getNakedBaseType() instanceof INakedSimpleType){
			INakedSimpleType baseType = (INakedSimpleType) f.getNakedBaseType();
			if(baseType.hasStrategy(JpaStrategy.class)){
				return baseType.getStrategy(ConfigurableDataStrategy.class).parseConfiguredValue(clazz, block, f, configuredValue);
			}else if(f.getNakedBaseType() instanceof INakedPrimitiveType){
				INakedPrimitiveType t = (INakedPrimitiveType) f.getNakedBaseType();
				if(t.getOclType().getName().equals("Integer")){
					return "Integer.valueOf(" + configuredValue + ")";
				}else if(t.getOclType().getName().equals("Real")){
					return "new Double(" + configuredValue + ")";
				}else if(t.getOclType().getName().equals("Boolean")){
					return "Boolean.valueOf(" + configuredValue + ")";
				}else if(t.getOclType().getName().equals("String")){
					return configuredValue;
				}else if(f.getName().equals("name")){
					return configuredValue;
				}
			}
			return "no configurable data generation strategy!!!";
		}else if(f.getNakedBaseType() instanceof IEnumerationType){
			INakedEnumeration en = (INakedEnumeration) f.getNakedBaseType();
			NakedClassifierMap map = new NakedClassifierMap(en);
			clazz.addToImports(map.javaDefaultTypePath());
			if(en.getLiterals().size() > 0){
				return map.javaType() + ".valueOf(" + configuredValue + ")";
			}else{
				return map.javaType() + ".has no literals!!!!";
			}
		}else if(f.getBaseType() instanceof INakedHelper){
			return "new " + f.getBaseType().getName() + "()";
		}else if(f.getBaseType() instanceof INakedInterface){
			return "new " + f.getBaseType().getName() + "()";
		}else{
			throw new RuntimeException("Not implemented");
		}
	}
	private void addPopulateChildren(ICompositionParticipant c,OJAnnotatedClass testDataClass,OJBlock block,boolean forExport){
		final String FUNCTION = forExport ? "export" : "populate";
		for(INakedProperty a:c.getEffectiveAttributes()){
			INakedProperty p = (INakedProperty) a;
			if(p.isComposite() && !isHierarchical(c, p)){
				NakedStructuralFeatureMap map = new NakedStructuralFeatureMap(p);
				if(p.getNakedBaseType() instanceof INakedInterface && map.isOne()){
					// TODO sourcePopulation needs to handle redefinition and
					// looks like WorkspaceElement.name has duplicates
					INakedInterface inf = (INakedInterface) p.getNakedBaseType();
					Collection<INakedBehavioredClassifier> classifiers = getConcreteImplementations(inf);
					for(INakedClassifier iNakedClassifier:classifiers){
						OJIfStatement ifInstanceOf = new OJIfStatement(c.getMappingInfo().getJavaName().getDecapped().toString() + "." + map.getter() + "()!=null && "
								+ c.getMappingInfo().getJavaName().getDecapped().toString() + "." + map.getter() + "() instanceof "
								+ iNakedClassifier.getMappingInfo().getJavaName().toString());
						if(forExport){
							String s = "dataGeneratorProperty.putExportProperty(" + c.getMappingInfo().getJavaName().getDecapped().toString() + ".getUid() + \"."
									+ p.getMappingInfo().getJavaName().getDecapped().toString() + "\", \"" + iNakedClassifier.getMappingInfo().getQualifiedJavaName()
									+ "\")";
							ifInstanceOf.addToThenPart(new OJSimpleStatement(s));
						}
						String s = NameConverter.decapitalize(getTestDataName(iNakedClassifier)) + "." + FUNCTION + iNakedClassifier.getMappingInfo().getJavaName() + "("
								+ c.getMappingInfo().getJavaName().getDecapped().toString() + ")";
						ifInstanceOf.addToThenPart(new OJSimpleStatement(s));
						block.addToStatements(ifInstanceOf);
						testDataClass.addToImports(OJUtil.classifierPathname(iNakedClassifier));
					}
				}else{
					if(!p.getNakedBaseType().getIsAbstract()){
						block.addToStatements(new OJSimpleStatement(NameConverter.decapitalize(getTestDataName(p.getNakedBaseType())) + "." + FUNCTION
								+ p.getNakedBaseType().getMappingInfo().getJavaName() + "(" + c.getMappingInfo().getJavaName().getDecapped().toString() + ")"));
					}
				}
			}else if(p.isComposite() && !p.getNakedBaseType().getIsAbstract() && isHierarchical(c, p)){
				block.addToStatements(new OJSimpleStatement(FUNCTION + p.getNakedBaseType().getMappingInfo().getJavaName() + "("
						+ c.getMappingInfo().getJavaName().getDecapped().toString() + ")"));
			}
		}
	}
	private OJAnnotatedOperation addCreate(ICompositionParticipant entity,OJAnnotatedClass testDataClass){
		OJPathName element = new OJPathName(entity.getMappingInfo().getQualifiedJavaName());
		if(isHierarchical(entity)){
			OJAnnotatedOperation create = addCreateHierarchical(entity, testDataClass, element);
			return create;
		}else{
			OJAnnotatedOperation create = addCreateNonHierarchical(entity, testDataClass, element);
			return create;
		}
	}
	private OJAnnotatedOperation addCreateNonHierarchical(ICompositionParticipant entity,OJAnnotatedClass testDataClass,OJPathName element){
		OJAnnotatedOperation createOperation = new OJAnnotatedOperation();
		createOperation.setName("create" + entity.getMappingInfo().getJavaName());
		testDataClass.addToOperations(createOperation);
		OJBlock block = new OJBlock();
		createOperation.setBody(block);
		createOperation.addToThrows(new OJPathName("java.text.ParseException"));
		testDataClass.addToImports(new OJPathName("java.text.ParseException"));
		INakedProperty parent = entity.getEndToComposite();
		OJField local = new OJField();
		local.setName(entity.getMappingInfo().getJavaName().getDecapped().toString());
		local.setType(element);
		if(parent != null){
			local.setInitExp("new " + entity.getMappingInfo().getJavaName() + "(" + parent.getMappingInfo().getJavaName().toString() + ")");
			block = createNonHierarchicalForNonRoot(entity, createOperation, block, parent);
		}else{
			local.setInitExp("new " + entity.getMappingInfo().getJavaName() + "()");
			block = createNonHierarchicalForRoot(entity, block);
			// Always have for loop
		}
		OJPathName returnList = new OJPathName("java.util.List");
		returnList.addToElementTypes(element);
		createOperation.setReturnType(returnList);
		OJField listReturn = new OJField();
		listReturn.setType(returnList);
		listReturn.setName("result");
		listReturn.setInitExp("new ArrayList<" + element.getLast() + ">()");
		testDataClass.addToImports(new OJPathName("java.util.ArrayList"));
		createOperation.getBody().addToLocals(listReturn);
		block.addToLocals(local);
		populateSelf(testDataClass, entity, block, false);
		block.addToStatements("result.add(" + entity.getMappingInfo().getJavaName().getDecapped() + ")");
		addCompositeChildren(entity, block);
		createOperation.getBody().addToStatements("return " + listReturn.getName());
		return createOperation;
	}
	private OJBlock createNonHierarchicalForRoot(ICompositionParticipant entity,OJBlock block){
		OJForStatement forX = new OJForStatement();
		forX.setElemType(new OJPathName("java.util.Integer"));
		forX.setElemName("i");
		forX.setCollection("dataGeneratorProperty.getIterationListForSizeProperty(\"" + constructSizePropertyName(entity) + "\",\"0\")");
		OJBlock forXBLock = new OJBlock();
		forX.setBody(forXBLock);
		block.addToStatements(forX);
		block = forXBLock;
		return block;
	}
	private OJBlock createNonHierarchicalForNonRoot(ICompositionParticipant entity,OJAnnotatedOperation create,OJBlock block,INakedProperty parent){
		OJParameter owner = new OJParameter();
		owner.setName(parent.getMappingInfo().getJavaName().toString());
		NakedStructuralFeatureMap map = new NakedStructuralFeatureMap(parent);
		OJPathName javaTypePath = map.javaTypePath();
		owner.setType(javaTypePath);
		create.addToParameters(owner);
		NakedStructuralFeatureMap otherMap = new NakedStructuralFeatureMap(parent.getOtherEnd());
		if(otherMap.isMany()){
			// Have for loop
			OJForStatement forX = new OJForStatement();
			forX.setElemType(new OJPathName("java.util.Integer"));
			forX.setElemName("i");
			forX.setCollection("dataGeneratorProperty.getIterationListForSizeProperty(" + parent.getMappingInfo().getJavaName().toString() + ".getName()+\"."
					+ entity.getMappingInfo().getJavaName().getDecapped().toString() + ".size\",\"0\")");
			OJBlock forXBLock = new OJBlock();
			forX.setBody(forXBLock);
			block.addToStatements(forX);
			block = forXBLock;
		}else{
			// Add variable i
			OJField variableI = new OJField();
			variableI.setType(new OJPathName("java.util.Integer"));
			variableI.setName("i");
			variableI.setInitExp("0");
			block.addToLocals(variableI);
		}
		return block;
	}
	private OJAnnotatedOperation addCreateHierarchical(ICompositionParticipant entity,OJAnnotatedClass testDataClass,OJPathName element){
		OJAnnotatedOperation createHierarchical = new OJAnnotatedOperation();
		createHierarchical.addToThrows(new OJPathName("java.text.ParseException"));
		createHierarchical.setName("createHierarchical" + entity.getMappingInfo().getJavaName());
		OJPathName returnType = element;
		createHierarchical.setReturnType(returnType);
		createHierarchical.setVisibility(OJVisibilityKind.PRIVATE);
		testDataClass.addToOperations(createHierarchical);
		INakedProperty parent = entity.getEndToComposite();
		OJBlock block = new OJBlock();
		createHierarchical.setBody(block);
		OJField local = new OJField();
		local.setName(entity.getMappingInfo().getJavaName().getDecapped().toString());
		local.setType(returnType);
		if(parent != null){
			OJParameter owner = new OJParameter();
			owner.setName(parent.getMappingInfo().getJavaName().toString());
			NakedStructuralFeatureMap map = new NakedStructuralFeatureMap(parent);
			OJPathName javaTypePath = map.javaTypePath();
			owner.setType(javaTypePath);
			createHierarchical.addToParameters(owner);
			OJParameter localI = new OJParameter();
			localI.setType(new OJPathName("java.lang.Integer"));
			localI.setName("i");
			createHierarchical.addToParameters(localI);
			local.setInitExp("new " + entity.getMappingInfo().getJavaName() + "(" + parent.getMappingInfo().getJavaName().toString() + ")");
		}else{
			local.setInitExp("new " + entity.getMappingInfo().getJavaName() + "()");
		}
		block.addToLocals(local);
		populateSelf(testDataClass, entity, block, false);
		addCompositeChildren(entity, block);
		OJSimpleStatement returnStatement = new OJSimpleStatement("return " + local.getName());
		block.addToStatements(returnStatement);
		// Create method
		OJAnnotatedOperation create = new OJAnnotatedOperation();
		create.addToThrows(new OJPathName("java.text.ParseException"));
		testDataClass.addToImports(new OJPathName("java.text.ParseException"));
		create.setName("create" + entity.getMappingInfo().getJavaName());
		testDataClass.addToOperations(create);
		parent = entity.getEndToComposite();
		block = new OJBlock();
		create.setBody(block);
		OJPathName integerPath = new OJPathName("java.util.Integer");
		OJForStatement forX = new OJForStatement();
		forX.setElemType(integerPath);
		forX.setElemName("i");
		forX.setCollection("dataGeneratorProperty.getIterationListForSizeProperty(" + parent.getMappingInfo().getJavaName().toString() + ".getName()+\"."
				+ entity.getMappingInfo().getJavaName().getDecapped().toString() + ".size\",\"0\")");
		OJBlock forXBLock = new OJBlock();
		block.addToStatements(forX);
		forX.setBody(forXBLock);
		local = new OJField();
		local.setName(entity.getMappingInfo().getJavaName().getDecapped().toString());
		local.setType(returnType);
		if(parent != null){
			OJParameter owner = new OJParameter();
			owner.setName(parent.getMappingInfo().getJavaName().toString());
			NakedStructuralFeatureMap map = new NakedStructuralFeatureMap(parent);
			OJPathName javaTypePath = map.javaTypePath();
			owner.setType(javaTypePath);
			create.addToParameters(owner);
			local.setInitExp("createHierarchical" + entity.getMappingInfo().getJavaName() + "(" + parent.getMappingInfo().getJavaName().toString() + ",i)");
		}
		forXBLock.addToLocals(local);
		forXBLock.addToStatements(new OJSimpleStatement("create" + entity.getMappingInfo().getJavaName() + "(" + local.getName() + ")"));
		forXBLock.addToStatements("result.add(" + entity.getMappingInfo().getJavaName().getDecapped() + ")");
		OJPathName returnList = new OJPathName("java.util.List");
		returnList.addToElementTypes(element);
		create.setReturnType(returnList);
		OJField listReturn = new OJField();
		listReturn.setType(returnList);
		listReturn.setName("result");
		listReturn.setInitExp("new ArrayList<" + element.getLast() + ">()");
		testDataClass.addToImports(new OJPathName("java.util.ArrayList"));
		create.getBody().addToLocals(listReturn);
		create.getBody().addToStatements("return " + listReturn.getName());
		return create;
	}
	private String constructSourcePopulationProperty(ICompositionParticipant entity,NakedStructuralFeatureMap map,String defaultValue){
		String javaType = map.javaType();
		if(map.isManyToMany()){
			javaType = javaType.replace("<", "");
			javaType = javaType.replace(">", "");
		}
		String result = entity.getMappingInfo().getJavaName().getDecapped().toString() + ".getUid()" + "+\"." + NameConverter.decapitalize(javaType);
		return result;
	}
	private String constructSizePropertyName(ICompositionParticipant entity){
		String property;
		if(entity.getEndToComposite() != null){
			NakedStructuralFeatureMap map = new NakedStructuralFeatureMap(entity.getEndToComposite().getOtherEnd());
			if(map.isMany()){
				property = entity.getEndToComposite().getName() + "." + NameConverter.decapitalize(entity.getName()) + ".size";
			}else{
				throw new RuntimeException("Code should not come here");
			}
		}else{
			property = NameConverter.decapitalize(entity.getName()) + ".size";
		}
		return property;
	}
	private void addCompositeChildren(ICompositionParticipant c,OJBlock block){
		for(INakedProperty a:c.getEffectiveAttributes()){
			INakedProperty p = (INakedProperty) a;
			NakedStructuralFeatureMap map = new NakedStructuralFeatureMap(p);
			if(p.isComposite() && !isHierarchical(c, p)){
				if(p.getNakedBaseType() instanceof INakedInterface && map.isOne()){
					INakedInterface inf = (INakedInterface) p.getNakedBaseType();
					Collection<INakedBehavioredClassifier> classifiers = getConcreteImplementations(inf);
					for(INakedClassifier iNakedClassifier:classifiers){
						String result = c.getMappingInfo().getJavaName().getDecapped().toString() + ".getUid() + \"."
								+ p.getMappingInfo().getJavaName().getDecapped().toString() + "\"";
						OJIfStatement ifInstanceOf = new OJIfStatement("dataGeneratorProperty.getProperty(" + result + ",\"\").equals(\""
								+ iNakedClassifier.getMappingInfo().getQualifiedJavaName().toString() + "\")", iNakedClassifier.getMappingInfo().getJavaName()
								.getDecapped().toString()
								+ "DataGenerator.create"
								+ iNakedClassifier.getMappingInfo().getJavaName().toString()
								+ "("
								+ c.getMappingInfo().getJavaName().getDecapped().toString() + ")");
						block.addToStatements(ifInstanceOf);
					}
				}else if(p.getNakedBaseType() instanceof INakedInterface && map.isMany()){
					INakedInterface inf = (INakedInterface) p.getNakedBaseType();
					Collection<INakedBehavioredClassifier> classifiers = getConcreteImplementations(inf);
					for(INakedClassifier iNakedClassifier:classifiers){
						OJPathName baseTypePath = getTestDataPath(iNakedClassifier);
						OJSimpleStatement ojSimpleStatement = new OJSimpleStatement();
						ojSimpleStatement.setExpression(NameConverter.decapitalize(baseTypePath.getLast()) + ".create" + iNakedClassifier.getMappingInfo().getJavaName()
								+ "(" + c.getMappingInfo().getJavaName().getDecapped() + ")");
						block.addToStatements(ojSimpleStatement);
					}
				}else{
					if(!p.getNakedBaseType().getIsAbstract()){
						OJPathName baseTypePath = getTestDataPath(p.getNakedBaseType());
						OJSimpleStatement ojSimpleStatement = new OJSimpleStatement();
						ojSimpleStatement.setExpression(NameConverter.decapitalize(baseTypePath.getLast()) + ".create"
								+ p.getNakedBaseType().getMappingInfo().getJavaName() + "(" + c.getMappingInfo().getJavaName().getDecapped() + ")");
						block.addToStatements(ojSimpleStatement);
					}
				}
			}
		}
	}
	private void addChildrenFields(ICompositionParticipant c,OJAnnotatedClass testDataClass){
		for(INakedProperty f:c.getEffectiveAttributes()){
			INakedProperty p = f;
			if(f.isComposite() && !isHierarchical(c, p)){
				// TODO sourcePopulation needs to handle redefinition and looks
				// like WorkspaceElement.name has duplicates
				if(f.getNakedBaseType() instanceof INakedInterface){
					INakedInterface inf = (INakedInterface) p.getNakedBaseType();
					Collection<INakedBehavioredClassifier> classifiers = getConcreteImplementations(inf);
					for(INakedClassifier iNakedClassifier:classifiers){
						if(!iNakedClassifier.getIsAbstract()){
							addChildField(testDataClass, iNakedClassifier);
						}
					}
				}else{
					if(!f.getNakedBaseType().getIsAbstract()){
						addChildField(testDataClass, p.getNakedBaseType());
					}
				}
			}
		}
	}
	private void addChildField(OJAnnotatedClass testDataClass,INakedClassifier type){
		OJPathName baseTypePath = getTestDataPath(type);
		OJAnnotatedField childController = new OJAnnotatedField();
		childController.setName(NameConverter.decapitalize(baseTypePath.getLast()));
		childController.setType(baseTypePath);
		OJAnnotationValue in = new OJAnnotationValue(new OJPathName("javax.inject.Inject"));
		childController.putAnnotation(in);
		testDataClass.addToFields(childController);
	}
	@Override
	protected String getTestDataName(INakedClassifier child){
		return OJUtil.classifierPathname(child).getLast() + "DataGenerator";
	}
	protected String calcMapKey(ICompositionParticipant entity,INakedProperty f){
		String result = entity.getMappingInfo().getJavaName().getDecapped() + "." + f.getName() + "_";
		// TODO endToComposite needs to work for interfaces
		if(entity.getEndToComposite() != null){
			NakedStructuralFeatureMap other = new NakedStructuralFeatureMap(entity.getEndToComposite());
			result = entity.getMappingInfo().getJavaName().getDecapped() + "." + other.getter() + "().getName() + " + "\"" + "." + result + "\"";
		}else{
			result = "\"" + result + "\"";
		}
		return result;
	}
	protected String calcMapKeyForUid(ICompositionParticipant entity){
		String result = entity.getMappingInfo().getJavaName().getDecapped() + ".uid_";
		// TODO endToComposite needs to work for interfaces
		if(entity.getEndToComposite() != null){
			NakedStructuralFeatureMap other = new NakedStructuralFeatureMap(entity.getEndToComposite());
			result = entity.getMappingInfo().getJavaName().getDecapped() + "." + other.getter() + "().getUid() + " + "\"" + "." + result + "\"";
		}else{
			result = "\"" + result + "\"";
		}
		return result;
	}
	public class IntegerWrapper{
		int count;
		void increment(){
			count++;
		}
	}
}
