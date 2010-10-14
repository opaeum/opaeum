package net.sf.nakeduml.linkage;

import java.util.List;

import net.sf.nakeduml.feature.InputModel;
import net.sf.nakeduml.feature.NakedUmlConfig;
import net.sf.nakeduml.feature.PhaseDependency;
import net.sf.nakeduml.feature.TransformationPhase;
import net.sf.nakeduml.metamodel.workspace.INakedModelWorkspace;
import nl.klasse.octopus.stdlib.internal.types.OclLibraryImpl;

@PhaseDependency()
public class LinkagePhase implements
		TransformationPhase<AbstractModelElementLinker> {
	private NakedUmlConfig config;
	@InputModel
	private INakedModelWorkspace modelWorkspace;


	public Object[] execute(List<AbstractModelElementLinker> linkers) {
		modelWorkspace.getOclEngine().setOclLibrary(new OclLibraryImpl());
		for (AbstractModelElementLinker d : linkers) {
			d.initialize(modelWorkspace, config);
			d.startVisiting(modelWorkspace);
		}
		return new Object[] {};
	}

	public void initialize(NakedUmlConfig config) {
		this.config = config;
	}
}
