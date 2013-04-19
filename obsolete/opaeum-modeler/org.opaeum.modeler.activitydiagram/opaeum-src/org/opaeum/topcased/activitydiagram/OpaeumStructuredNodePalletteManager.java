package org.opaeum.topcased.activitydiagram;

import java.util.List;

import org.eclipse.gef.palette.PaletteDrawer;
import org.eclipse.gef.palette.PaletteEntry;
import org.eclipse.gef.requests.CreationFactory;
import org.eclipse.uml2.uml.UMLPackage;
import org.topcased.modeler.editor.GraphElementCreationFactory;
import org.topcased.modeler.editor.ICreationUtils;
import org.topcased.modeler.editor.palette.ModelerConnectionCreationToolEntry;
import org.topcased.modeler.editor.palette.ModelerCreationToolEntry;
import org.topcased.modeler.uml.activitydiagram.ActivityImageRegistry;
import org.topcased.modeler.uml.activitydiagram.ActivitySimpleObjectConstants;
import org.topcased.modeler.utils.CustomPaletteArrayList;

public class OpaeumStructuredNodePalletteManager extends OpaeumActivityPaletteManager{
	protected PaletteDrawer clauseDrawer;
	protected OpaeumStructuredNodePalletteManager(ICreationUtils s){
		super(s);
	}
	protected void createClauseDrawer(){
		clauseDrawer = new PaletteDrawer("Clause", null);
		List<PaletteEntry> entries = new CustomPaletteArrayList("org.topcased.modeler.uml.structuredactivitynodediagram");
		CreationFactory factory = new GraphElementCreationFactory(creationUtils, UMLPackage.eINSTANCE.getClause(), "default");
		entries.add(new ModelerCreationToolEntry("Clause", "Clause", factory, ActivityImageRegistry.getImageDescriptor("CLAUSE"), ActivityImageRegistry
				.getImageDescriptor("CLAUSE_LARGE")));
		factory = new GraphElementCreationFactory(creationUtils, ActivitySimpleObjectConstants.SIMPLE_OBJECT_TESTCLAUSELINK, "default", false);
		entries.add(new ModelerConnectionCreationToolEntry("Test Link", "Test Link", factory, ActivityImageRegistry.getImageDescriptor("TESTCLAUSELINK"),
				ActivityImageRegistry.getImageDescriptor("TESTCLAUSELINK_LARGE")));
		factory = new GraphElementCreationFactory(creationUtils, ActivitySimpleObjectConstants.SIMPLE_OBJECT_BODYCLAUSELINK, "default", false);
		entries.add(new ModelerConnectionCreationToolEntry("Body Link", "Body Link", factory, ActivityImageRegistry.getImageDescriptor("BODYCLAUSELINK"),
				ActivityImageRegistry.getImageDescriptor("BODYCLAUSELINK_LARGE")));
		factory = new GraphElementCreationFactory(creationUtils, ActivitySimpleObjectConstants.SIMPLE_OBJECT_SUCCESSORCLAUSELINK, "default", false);
		entries.add(new ModelerConnectionCreationToolEntry("Successor Link", "Successor Link", factory, ActivityImageRegistry
				.getImageDescriptor("SUCCESSORCLAUSELINK"), ActivityImageRegistry.getImageDescriptor("SUCCESSORCLAUSELINK_LARGE")));
		clauseDrawer.addAll(entries);
		if(!clauseDrawer.getChildren().isEmpty()){
			getRoot().add(clauseDrawer);
		}
	}
}