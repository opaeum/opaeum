package net.sf.nakeduml.javageneration.composition;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import net.sf.nakeduml.feature.NakedUmlConfig;
import net.sf.nakeduml.feature.visit.VisitAfter;
import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.javageneration.AbstractTestDataGenerator;
import net.sf.nakeduml.javageneration.NakedStructuralFeatureMap;
import net.sf.nakeduml.javageneration.TestValueStrategy;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.javametamodel.OJPackage;
import net.sf.nakeduml.javametamodel.OJPathName;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedClass;
import net.sf.nakeduml.javametamodel.annotation.OJEnum;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedEntity;
import net.sf.nakeduml.metamodel.core.INakedPrimitiveType;
import net.sf.nakeduml.metamodel.core.INakedProperty;
import net.sf.nakeduml.metamodel.core.INakedSimpleType;
import net.sf.nakeduml.metamodel.models.INakedModel;
import net.sf.nakeduml.metamodel.workspace.INakedModelWorkspace;
import net.sf.nakeduml.textmetamodel.PropertiesSource;
import net.sf.nakeduml.textmetamodel.TextOutputRoot;
import net.sf.nakeduml.textmetamodel.TextWorkspace;
import nl.klasse.octopus.model.IEnumerationType;

public class ConfigurableCompositionPropertiesGenerator extends AbstractTestDataGenerator {

	private Map<String, DataPopulatorPropertyEntry> propertiesMap = new HashMap<String, DataPopulatorPropertyEntry>();
	private Properties props = new Properties();
	private List<DataPopulatorPropertyEntry> rootList;

	public void initialize(INakedModelWorkspace workspace, OJPackage javaModel, NakedUmlConfig config, TextWorkspace textWorkspace,
			Map<String, DataPopulatorPropertyEntry> propertiesMap) {
		super.initialize(workspace, javaModel, config, textWorkspace);
		this.propertiesMap = propertiesMap;
	}

	@VisitBefore(matchSubclasses = true)
	public void visitBefore(INakedModel model) {
		DataPopulatorPropertyEntry anyOne = this.propertiesMap.get(this.propertiesMap.keySet().iterator().next());
		DataPopulatorPropertyEntry root = anyOne.getRoot();
		rootList = new ArrayList<DataPopulatorPropertyEntry>();
		rootList.add(root);
		DataPopulatorPropertyEntry.copyTreeRecursive(rootList, 0, 2);
		for (DataPopulatorPropertyEntry rootX : rootList) {
			rootX.outputCompositeProperties(this);
		}
		DataPopulatorPropertyEntry.outputSizeProperties(rootList, this);		
	}
	
	@VisitBefore(matchSubclasses = true)
	public void visit(INakedEntity entity) {
		for (INakedProperty f : entity.getEffectiveAttributes()) {
			NakedStructuralFeatureMap map = new NakedStructuralFeatureMap(f);
			if (f instanceof INakedProperty) {
				boolean isEndToComposite = f.getOtherEnd() != null && f.getOtherEnd().isComposite();
				if (f.getInitialValue() == null && !isEndToComposite) {
					if ((map.isManyToMany() || map.isOne()) && !(f.isDerived() || f.isReadOnly() || f.isInverse())) {
						if (map.couldBasetypeBePersistent()) {
							if (!map.isManyToMany()) {
								//Need to set the one here
								
								//Get all the entity instances in the tree
								List<DataPopulatorPropertyEntry> entities = new ArrayList<DataPopulatorPropertyEntry>();
								for (DataPopulatorPropertyEntry rootX : rootList) {
									rootX.getEntityInstances(entities, entity.getMappingInfo().getQualifiedJavaName());
								}
								List<DataPopulatorPropertyEntry> ones = new ArrayList<DataPopulatorPropertyEntry>();
								for (DataPopulatorPropertyEntry rootX : rootList) {
									rootX.getEntityInstances(ones, ((INakedClassifier)f.getBaseType()).getMappingInfo().getQualifiedJavaName());
								}
								
								Iterator<DataPopulatorPropertyEntry> needsOneIterator = entities.iterator();
								while (needsOneIterator.hasNext()) {
									DataPopulatorPropertyEntry needsOne = (DataPopulatorPropertyEntry) needsOneIterator.next();
									
									Iterator<DataPopulatorPropertyEntry> iterator = ones.iterator();
									while (iterator.hasNext()) {
										DataPopulatorPropertyEntry one = iterator.next();
										
										DataPopulatorPropertyEntry commonAncestor = needsOne.getCommonAncestor(one);
										if (commonAncestor!=null) {
											needsOneIterator.remove();
											iterator.remove();
											DataPopulatorPropertyEntry newOne = new DataPopulatorPropertyEntry(needsOne.getEntityQualifiedName(), needsOne.getEntityName(), true, f.getMappingInfo().getJavaName().getAsIs(), one.getValue());
											newOne.setValue(needsOne.getValue());
											newOne.setParent(needsOne.getParent());
											break;
										}
									}
									
								}
							}
						}
					}
				}
			}
		}
	}

	@VisitAfter
	public void visit(INakedModel model) {
		for (DataPopulatorPropertyEntry rootX : rootList) {
			rootX.outputToOneProperties(this);
		}
		if (this.config.getDataGeneration()) {
			TextOutputRoot outputRoot = textWorkspace.findOrCreateTextOutputRoot(PropertiesSource.GEN_RESOURCE);
			List<String> path = Arrays.asList("data.generation.properties");
			outputRoot.findOrCreateTextFile(path, new PropertiesSource(props));
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

}
