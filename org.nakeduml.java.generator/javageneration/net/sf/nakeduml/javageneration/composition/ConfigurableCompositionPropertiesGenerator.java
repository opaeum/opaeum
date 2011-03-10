package net.sf.nakeduml.javageneration.composition;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import net.sf.nakeduml.feature.OutputRoot;
import net.sf.nakeduml.feature.visit.VisitAfter;
import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.javageneration.AbstractTestDataGenerator;
import net.sf.nakeduml.javageneration.CharArrayTextSource;
import net.sf.nakeduml.javageneration.NakedStructuralFeatureMap;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedEntity;
import net.sf.nakeduml.metamodel.core.INakedInterface;
import net.sf.nakeduml.metamodel.core.INakedProperty;
import net.sf.nakeduml.metamodel.models.INakedModel;
import net.sf.nakeduml.textmetamodel.PropertiesSource;
import net.sf.nakeduml.textmetamodel.SourceFolder;

public class ConfigurableCompositionPropertiesGenerator extends AbstractTestDataGenerator {

	private Map<String, DataPopulatorPropertyEntry> propertiesMap;
	private Properties props = new Properties();
	private List<DataPopulatorPropertyEntry> rootList;

	@VisitBefore(matchSubclasses = true)
	public void visitBefore(INakedModel model) {
		DataPopulatorPropertyEntry anyOne = this.propertiesMap.get(this.propertiesMap.keySet().iterator().next());
		DataPopulatorPropertyEntry root = anyOne.getRoot();
		rootList = new ArrayList<DataPopulatorPropertyEntry>();
		rootList.add(root);
		System.out.println("Starting tree copy");
		DataPopulatorPropertyEntry.copyTreeRecursive(rootList, 0, 1);
		System.out.println("End tree copy");
		for (DataPopulatorPropertyEntry rootX : rootList) {
			System.out.println("Root outputCompositeProperties");
			rootX.outputCompositeProperties(this);
		}
		System.out.println("Root outputSizeProperties");
		DataPopulatorPropertyEntry.outputSizeProperties(rootList, this, 2);
	}

	@VisitBefore(matchSubclasses = true)
	public void setToOnes(INakedEntity entity) {
		for (INakedProperty f : entity.getEffectiveAttributes()) {
			NakedStructuralFeatureMap map = new NakedStructuralFeatureMap(f);
			if (f instanceof INakedProperty) {
				boolean isEndToComposite = f.getOtherEnd() != null && f.getOtherEnd().isComposite();
				if (f.getInitialValue() == null && !isEndToComposite) {
					if ((map.isManyToMany() || map.isOne()) && !(f.isDerived() || f.isReadOnly() || f.isInverse())) {
						if (super.getConcreteImplementations(map.getProperty().getNakedBaseType()).size()>0) {
							if (!map.isManyToMany()) {
								// Get all the entity instances in the tree
								List<DataPopulatorPropertyEntry> needsOneEntities = new ArrayList<DataPopulatorPropertyEntry>();
								for (DataPopulatorPropertyEntry root : rootList) {
									root.addEntityInstances(needsOneEntities, entity.getMappingInfo().getQualifiedJavaName());
								}
								List<DataPopulatorPropertyEntry> ones = new ArrayList<DataPopulatorPropertyEntry>();
								for (DataPopulatorPropertyEntry root : rootList) {
									root.addEntityInstances(ones, ((INakedClassifier) f.getBaseType()).getMappingInfo().getQualifiedJavaName());
								}

								Iterator<DataPopulatorPropertyEntry> needsOneIterator = needsOneEntities.iterator();
								while (needsOneIterator.hasNext()) {
									DataPopulatorPropertyEntry needsOne = (DataPopulatorPropertyEntry) needsOneIterator.next();

									Iterator<DataPopulatorPropertyEntry> iterator = ones.iterator();
									while (iterator.hasNext()) {
										DataPopulatorPropertyEntry one = iterator.next();

										DataPopulatorPropertyEntry commonAncestor = needsOne.getCommonAncestor(one);
										if (commonAncestor != null) {
											needsOneIterator.remove();
											iterator.remove();
											DataPopulatorPropertyEntry newOne = new DataPopulatorPropertyEntry(needsOne.getEntityQualifiedName(),
													needsOne.getEntityName(), true, f.getMappingInfo().getJavaName().getAsIs(), one.getValue());
											newOne.setValue(needsOne.getValue());
											newOne.setParent(needsOne.getParent());
											break;
										}
									}
								}
							} else if (map.isManyToMany()) {
								List<DataPopulatorPropertyEntry> needsManyEntities = new ArrayList<DataPopulatorPropertyEntry>();
								for (DataPopulatorPropertyEntry root : rootList) {
									root.addEntityInstances(needsManyEntities, entity.getMappingInfo().getQualifiedJavaName());
								}
								List<DataPopulatorPropertyEntry> otherSideMany = new ArrayList<DataPopulatorPropertyEntry>();
								for (DataPopulatorPropertyEntry root : rootList) {
									root.addEntityInstances(otherSideMany, ((INakedClassifier) f.getBaseType()).getMappingInfo().getQualifiedJavaName());
								}

								Iterator<DataPopulatorPropertyEntry> needsManyIterator = needsManyEntities.iterator();
								while (needsManyIterator.hasNext()) {
									DataPopulatorPropertyEntry needsMany = (DataPopulatorPropertyEntry) needsManyIterator.next();
									Iterator<DataPopulatorPropertyEntry> otherManyIterator = otherSideMany.iterator();
									int i = 0;
									while (otherManyIterator.hasNext()) {
										DataPopulatorPropertyEntry otherMany = otherManyIterator.next();

										DataPopulatorPropertyEntry commonAncestor = needsMany.getCommonAncestor(otherMany);
										if (commonAncestor != null) {
											DataPopulatorPropertyEntry newManyToMany = new DataPopulatorPropertyEntry(needsMany.getEntityQualifiedName(),
													needsMany.getEntityName(), true, "set" + f.getMappingInfo().getJavaName().getCapped() + "_" + i++,
													otherMany.getValue());
											newManyToMany.setValue(needsMany.getValue());
											newManyToMany.setParent(needsMany.getParent());
										}
									}
								}
							}
						}
					}
				}
				
				NakedStructuralFeatureMap compositeMap = new NakedStructuralFeatureMap(f);
				if (f.isComposite() && compositeMap.isOne()) {
					
					INakedEntity toOne = null;
					if (f.getBaseType() instanceof INakedInterface || f.getBaseType().getIsAbstract()) {
						List<INakedEntity> result = getConcreteImplementations(f.getNakedBaseType());
						if(result.isEmpty()){
							return;
						}
						toOne = result.get(0);
					} else {
						toOne = entity; 
					}
					
					//choose one
					// Get all the entity instances in the tree
					List<DataPopulatorPropertyEntry> needsOneEntities = new ArrayList<DataPopulatorPropertyEntry>();
					for (DataPopulatorPropertyEntry root : rootList) {
						root.addEntityInstances(needsOneEntities, entity.getMappingInfo().getQualifiedJavaName());
					}
					
					for (DataPopulatorPropertyEntry dataPopulatorPropertyEntry : needsOneEntities) {
						DataPopulatorPropertyEntry newOne = new DataPopulatorPropertyEntry(dataPopulatorPropertyEntry.getLevel(), dataPopulatorPropertyEntry.getEntityQualifiedName()+"_ToOne",
								dataPopulatorPropertyEntry.getEntityName()+"_ToOne", true, toOne.getMappingInfo().getQualifiedJavaName());
						
						newOne.setValue(dataPopulatorPropertyEntry.getValue() + "." + f.getMappingInfo().getJavaName().getAsIs());
						newOne.setParent(dataPopulatorPropertyEntry.getParent());
					}
				}				
			}
		}
	}

	@VisitAfter
	public void visit(INakedModel model) {
		for (DataPopulatorPropertyEntry rootX : rootList) {
			rootX.outputToOneProperties(this);
			rootX.outputToCompositeOneInterface(this);
		}
		if (this.config.getDataGeneration()) {
			OutputRoot outputRoot = config.getOutputRoot(CharArrayTextSource.OutputRootId.ADAPTOR_GEN_RESOURCE);
			SourceFolder sourceFolder = getSourceFolder(outputRoot);
			List<String> path = Arrays.asList("data.generation.properties");
			sourceFolder.findOrCreateTextFile(path, new PropertiesSource(props),outputRoot.overwriteFiles());
		}
	}

	public void outputProperties(String name, String value) {
		props.put(name, value);
	}

	@Override
	protected String getTestDataName(INakedClassifier child) {
		// TODO Auto-generated method stub
		return null;
	}

	public void setModelInstanceMap(Map<String, DataPopulatorPropertyEntry> modelInstanceMap) {
		this.propertiesMap=modelInstanceMap;
	}

}
