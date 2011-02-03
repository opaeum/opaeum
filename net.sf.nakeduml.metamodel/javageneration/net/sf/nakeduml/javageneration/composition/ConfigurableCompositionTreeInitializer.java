package net.sf.nakeduml.javageneration.composition;

import java.util.HashMap;
import java.util.Map;

import net.sf.nakeduml.feature.NakedUmlConfig;
import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.javageneration.AbstractTestDataGenerator;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.javametamodel.OJPackage;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedEntity;
import net.sf.nakeduml.metamodel.core.INakedProperty;
import net.sf.nakeduml.metamodel.workspace.INakedModelWorkspace;
import net.sf.nakeduml.textmetamodel.TextWorkspace;

public class ConfigurableCompositionTreeInitializer extends AbstractTestDataGenerator {

	public Map<String, DataPopulatorPropertyEntry> propertiesMap = new HashMap<String, DataPopulatorPropertyEntry>();

	public void initialize(INakedModelWorkspace workspace, OJPackage javaModel, NakedUmlConfig config, TextWorkspace textWorkspace,
			Map<String, DataPopulatorPropertyEntry> propertiesMap) {
		super.initialize(workspace, javaModel, config, textWorkspace);
		this.propertiesMap = propertiesMap;
	}

	@VisitBefore(matchSubclasses = true)
	public void visit(INakedEntity entity) {
		if (OJUtil.hasOJClass(entity) && !entity.getIsAbstract()) {
			INakedProperty parent = entity.getEndToComposite();
			DataPopulatorPropertyEntry currentEntry = propertiesMap.get(entity.getName()+".name_0");
			if (parent != null) {
				DataPopulatorPropertyEntry parentEntry = propertiesMap.get(parent.getBaseType().getName());
				currentEntry.setParent(parentEntry);
			}
		}
	}

	@Override
	protected String getTestDataName(INakedClassifier child) {
		// TODO Auto-generated method stub
		return null;
	}

}
