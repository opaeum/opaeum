package net.sf.nakeduml.javageneration.composition;

import java.util.Arrays;
import java.util.HashMap;
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

public class ConfigurableCompositionPropertiesGenerator extends AbstractTestDataGenerator implements OutputProperties {

	private Map<String, DataPopulatorPropertyEntry> propertiesMap = new HashMap<String, DataPopulatorPropertyEntry>();
	private Properties props = new Properties();
	
	public void initialize(INakedModelWorkspace workspace, OJPackage javaModel, NakedUmlConfig config, TextWorkspace textWorkspace,
			Map<String, DataPopulatorPropertyEntry> propertiesMap) {
		super.initialize(workspace, javaModel, config, textWorkspace);
		this.propertiesMap = propertiesMap;
	}

	@VisitBefore(matchSubclasses = true)
	public void visitBefore(INakedModel model) {
		DataPopulatorPropertyEntry anyOne = this.propertiesMap.get(this.propertiesMap.keySet().iterator().next());
		DataPopulatorPropertyEntry root = anyOne.getRoot();
		//populate all the entries
		root.walk(outputProperties);
	}	
	
	@VisitAfter
	public void visit(INakedModel model) {
		if (this.config.getDataGeneration()) {
			DataPopulatorPropertyEntry anyOne = this.propertiesMap.get(this.propertiesMap.keySet().iterator().next());
			DataPopulatorPropertyEntry root = anyOne.getRoot();
			root.walk(this);
		}
		TextOutputRoot outputRoot = textWorkspace.findOrCreateTextOutputRoot(PropertiesSource.GEN_RESOURCE);
		List<String> path = Arrays.asList("data.generation.properties");
		outputRoot.findOrCreateTextFile(path, new PropertiesSource(props));
	}

	public void outputProperties(String name, String value) {
		props.put(name, value);
	}
	
	@VisitBefore(matchSubclasses = true)
	public void visit(INakedEntity entity) {
		
		for (INakedProperty f : entity.getEffectiveAttributes()) {
			NakedStructuralFeatureMap map = new NakedStructuralFeatureMap(f);
			boolean isReadOnly = (f instanceof INakedProperty && (f).isReadOnly());
			if (f instanceof INakedProperty) {
				INakedProperty p = f;
				boolean isEndToComposite = p.getOtherEnd() != null && p.getOtherEnd().isComposite();
				if (p.getInitialValue() == null && !isEndToComposite) {
					if (map.isOne() && !(p.isDerived() || isReadOnly || p.isInverse())) {
						if (!(map.couldBasetypeBePersistent())) {
							String defaultValue = "";
							int count = 0;
							DataPopulatorPropertyEntry currentEntry = propertiesMap.get(entity.getName()+".name_0");
							while (count < Integer.valueOf(config.getTestDataSize())) {
								defaultValue = calculateDefaultStringValue(p);
								
								
								if (currentEntry.isRoot()) {
									if (p.getName().equals("name")) {
										currentEntry.setValue(defaultValue); 
									}
								} else {
									DataPopulatorPropertyEntry parentEntry = currentEntry.getParent();
									DataPopulatorPropertyEntry currentEntry = propertiesMap.get(entity.getName()+"_name" + count);
								}
								
								count++;
							}
						}
					}
				}
			}
		}

	}
	
	private String calculateDefaultStringValue(INakedProperty f) {
		if (f.getNakedBaseType() instanceof IEnumerationType) {
			OJEnum javaType = (OJEnum) findJavaClass(f.getNakedBaseType());
			if (javaType.getLiterals().size() > 0) {
				return javaType.getLiterals().get(0).getName();
			} else {
				return javaType.getName() + ".has no literals!!!!";
			}
		} else if (f.getNakedBaseType() instanceof INakedSimpleType) {
			INakedSimpleType baseType = (INakedSimpleType) f.getNakedBaseType();
			if (baseType.hasStrategy(ConfigurableDataStrategy.class)) {
				return baseType.getStrategy(ConfigurableDataStrategy.class).getDefaultStringValue();
			} else if (f.getNakedBaseType() instanceof INakedPrimitiveType) {
				String calculateDefaultValue = calculateDefaultValue(f);
				if (calculateDefaultValue.startsWith("\"") && calculateDefaultValue.endsWith("\"")) {
					calculateDefaultValue = calculateDefaultValue.substring(1, calculateDefaultValue.length() - 1);
				}
				return calculateDefaultValue;
			} else {
				return "no ConfigurableDataStrategy strategy!!!";
			}
		}
		return "BLASDFASDFadsf";
	}	
	
	protected String calculateDefaultValue(INakedProperty f) {
		double value = Math.random() * 123456;
		NakedStructuralFeatureMap map = OJUtil.buildStructuralFeatureMap(f);
		if (f.getNakedBaseType() instanceof INakedSimpleType) {
			INakedSimpleType baseType = (INakedSimpleType) f.getNakedBaseType();
			if (baseType.hasStrategy(TestValueStrategy.class)) {
				return baseType.getStrategy(TestValueStrategy.class).getDefaultValue();
			} else if (workspace.getMappedTypes().getDateType() != null
					&& f.getNakedBaseType().conformsTo(workspace.getMappedTypes().getDateType())) {
				String javaDate = baseType.getMappingInfo().getQualifiedJavaName();
				if (javaDate.equals("java.util.Date")) {
					return "new Date()";
				} else if (javaDate.equals("java.util.Calendar")) {
					return "Calendar.getInstance()";
				} else {
					return "new Date()";
				}
			} else if (f.getNakedBaseType() instanceof INakedPrimitiveType) {
				INakedPrimitiveType t = (INakedPrimitiveType) f.getNakedBaseType();
				if (t.getOclType().getName().equals("Integer")) {
					return "" + new Double(value).intValue();
				} else if (t.getOclType().getName().equals("Real")) {
					return "" + new Double(value).floatValue();
				} else if (t.getOclType().getName().equals("Boolean")) {
					return "" + ((Math.round(value) % 2) == 1);
				} else if (f.getName().equals("name")) {
					return "\"" + f.getOwner().getName() + value + "\"";
				} else {
					return "\"" + f.getOwner().getName() + "." + f.getName() + value + "\"";
				}
			}
			return "no TestValueStrategy found ";
		} else if (f.getNakedBaseType() instanceof IEnumerationType) {
			OJAnnotatedClass javaType = findJavaClass(f.getNakedBaseType());
			return f.getNakedBaseType().getName() + ".values()[0]";

		} else if (map.couldBasetypeBePersistent()) {
			return lookup(f);
		} else {
			return "\"" + f.getOwner().getName() + "::" + f.getName() + new Double(value).intValue() + "\"";
		}
	}// TODO read default values from tags in the model, either at property or
	
	protected String lookup(INakedProperty f) {
		OJPathName featureTest = getTestDataPath(f.getNakedBaseType());
		if (new NakedStructuralFeatureMap(f).isOneToOne()) {
			return featureTest.getLast() + ".createNew()";
		} else {
			return featureTest.getLast() + ".getInstance()";
		}
	}	

	@Override
	protected String getTestDataName(INakedClassifier child) {
		// TODO Auto-generated method stub
		return null;
	}

}
