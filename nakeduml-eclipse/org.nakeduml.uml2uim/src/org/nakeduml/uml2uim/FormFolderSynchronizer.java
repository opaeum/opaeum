package org.nakeduml.uml2uim;

import net.sf.nakeduml.feature.visit.VisitBefore;

import org.eclipse.uml2.uml.Activity;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.StateMachine;
import org.nakeduml.uim.AbstractFolder;
import org.nakeduml.uim.AbstractFormFolder;
import org.nakeduml.uim.ActivityFolder;
import org.nakeduml.uim.EntityFolder;
import org.nakeduml.uim.PackageFolder;
import org.nakeduml.uim.StateMachineFolder;
import org.nakeduml.uim.UIMFactory;
import org.nakeduml.uim.UserInteractionModel;
import org.topcased.modeler.di.model.EMFSemanticModelBridge;
import org.topcased.modeler.diagrams.model.Diagrams;

public class FormFolderSynchronizer extends AbstractUimSynchronizer {

	public FormFolderSynchronizer(UserInteractionModel model, Diagrams d,boolean regenerate) {
		super(model,d,regenerate);
	}

	@VisitBefore(matchSubclasses = false)
	public void beforePackage(Package p) {
		PackageFolder pf = (PackageFolder) map.get(p);
		if (pf == null) {
			pf = UIMFactory.eINSTANCE.createPackageFolder();
			pf.setUmlPackage(p);
		}
		initFolder(p, pf);

	}

	@VisitBefore(matchSubclasses = false)
	public void beforeClass(Class c) {
		EntityFolder ef = (EntityFolder) map.get(c);
		if (ef == null) {
			ef = UIMFactory.eINSTANCE.createEntityFolder();
			ef.setEntity(c);
		}
		initFolder(c, ef);

	}

	@VisitBefore(matchSubclasses = false)
	public void beforeActivity(Activity a) {
		ActivityFolder af = (ActivityFolder) map.get(a);
		if (af == null) {
			af = UIMFactory.eINSTANCE.createActivityFolder();
			af.setActivity(a);
		}
		initFolder(a, af);

	}

	@VisitBefore(matchSubclasses = false)
	public void beforeStateMachine(StateMachine sm) {
		StateMachineFolder smf = (StateMachineFolder) map.get(sm);
		if (smf == null) {
			smf = UIMFactory.eINSTANCE.createStateMachineFolder();
			smf.setStateMachine(sm);
		}
		initFolder(sm, smf);
	}

	private void initFolder(NamedElement p, AbstractFormFolder pf) {
		map.put(p, pf);
		AbstractFolder parentFolder = (AbstractFolder) map.get(p.getOwner());
		if (parentFolder != pf.getParent()) {
			parentFolder.getChildren().add(pf);
		}
		if(diagramMap.containsKey(pf))
		{
			Diagrams diagrams = diagramMap.get(pf);
			diagrams.setModel(pf);
			EMFSemanticModelBridge semanticModel = (EMFSemanticModelBridge) diagrams.getDiagrams().get(0).getSemanticModel();
			semanticModel.setElement(pf);
		}
		pf.setName(p.getName());
	}

}
