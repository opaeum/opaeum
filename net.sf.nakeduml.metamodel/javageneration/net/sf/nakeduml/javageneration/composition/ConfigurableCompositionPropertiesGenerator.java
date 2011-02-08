package net.sf.nakeduml.javageneration.composition;

import java.util.ArrayList;
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

	public void initialize(INakedModelWorkspace workspace, OJPackage javaModel, NakedUmlConfig config, TextWorkspace textWorkspace,
			Map<String, DataPopulatorPropertyEntry> propertiesMap) {
		super.initialize(workspace, javaModel, config, textWorkspace);
		this.propertiesMap = propertiesMap;
	}

	@VisitBefore(matchSubclasses = true)
	public void visitBefore(INakedModel model) {
		DataPopulatorPropertyEntry anyOne = this.propertiesMap.get(this.propertiesMap.keySet().iterator().next());
		DataPopulatorPropertyEntry root = anyOne.getRoot();
		List<DataPopulatorPropertyEntry> result = new ArrayList<DataPopulatorPropertyEntry>();
		result.add(root);
		DataPopulatorPropertyEntry.copyTreeRecursive(result, 0, 2);
		for (DataPopulatorPropertyEntry rootX : result) {
			rootX.walk(this);
		}
		DataPopulatorPropertyEntry.walk(result, this);
	}

	@VisitAfter
	public void visit(INakedModel model) {
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
