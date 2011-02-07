package net.sf.nakeduml.javageneration.composition;

import java.util.HashMap;
import java.util.Map;

import net.sf.nakeduml.feature.NakedUmlConfig;
import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.javageneration.AbstractJavaProducingVisitor;
import net.sf.nakeduml.javageneration.NakedStructuralFeatureMap;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.javametamodel.OJPackage;
import net.sf.nakeduml.metamodel.core.INakedEntity;
import net.sf.nakeduml.metamodel.core.INakedProperty;
import net.sf.nakeduml.metamodel.workspace.INakedModelWorkspace;
import net.sf.nakeduml.textmetamodel.TextWorkspace;

public class ConfigurableCompositionTreeInitializer extends AbstractJavaProducingVisitor {

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
			DataPopulatorPropertyEntry currentEntry = propertiesMap.get(entity.getName());
			if (parent != null) {
				DataPopulatorPropertyEntry parentEntry = propertiesMap.get(parent.getBaseType().getName());
				currentEntry.setParent(parentEntry);
			}
			populateSelf(entity);
		}
	}

	private void populateSelf(INakedEntity entity) {
		
		INakedProperty nameProperty = entity.findEffectiveAttribute("name");
		DataPopulatorPropertyEntry currentEntry = propertiesMap.get(entity.getName());
		currentEntry.setProperty(nameProperty);
		
		for (INakedProperty f : entity.getEffectiveAttributes()) {
			NakedStructuralFeatureMap map = new NakedStructuralFeatureMap(f);
			boolean isReadOnly = (f instanceof INakedProperty && (f).isReadOnly());
			if (f instanceof INakedProperty) {
				INakedProperty p = f;
				boolean isEndToComposite = p.getOtherEnd() != null && p.getOtherEnd().isComposite();
				if (p.getInitialValue() == null && !isEndToComposite) {
					if (map.isOne() && !(p.isDerived() || isReadOnly || p.isInverse())) {
						if (!(map.couldBasetypeBePersistent())) {
							if (!p.getName().equals("name")) {
								currentEntry.addToOtherProperties(p);
							}
						}
					}
				}
			}
		}
	}

}
