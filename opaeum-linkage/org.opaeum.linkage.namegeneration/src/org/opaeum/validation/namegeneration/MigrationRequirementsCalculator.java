package org.opaeum.validation.namegeneration;
import java.util.Iterator;

import org.opaeum.metamodel.core.INakedElement;
import org.opaeum.metamodel.core.INakedElementOwner;
import nl.klasse.octopus.model.IModelElement;
/**
 * This class recursively calculates whether a specific model originalElement is new in
 * either the deployed version or the deployed revision. It also has
 * functionality to recursively update the version and revision information of
 * the modelelements
 */
public class MigrationRequirementsCalculator {
	public void calculateMigrationRequirements(INakedElementOwner ns, float deployedVersion, int deployedRevision) {
		ns.getMappingInfo().calculateMigrationRequirements(deployedVersion, deployedRevision);
		Iterator<? extends INakedElement> iter = ns.getOwnedElements().iterator();
		while (iter.hasNext()) {
			IModelElement me = (IModelElement) iter.next();
			if (me instanceof INakedElementOwner) {
				calculateMigrationRequirements((INakedElementOwner) me, deployedVersion, deployedRevision);
			} else if (me instanceof INakedElement) {
				INakedElement nme = (INakedElement) me;
				nme.getMappingInfo().calculateMigrationRequirements(deployedVersion, deployedRevision);
			}
		}
	}
	public void updateVersionInfo(INakedElementOwner ns, float currentVersion, int currentRevision) {
		ns.getMappingInfo().updateVersionInfo(currentVersion, currentRevision);
		Iterator<? extends INakedElement> iter = ns.getOwnedElements().iterator();
		while (iter.hasNext()) {
			IModelElement me = (IModelElement) iter.next();
			if (me instanceof INakedElementOwner) {
				updateVersionInfo((INakedElementOwner) me, currentVersion, currentRevision);
			} else if (me instanceof INakedElement) {
				INakedElement nme = (INakedElement) me;
				nme.getMappingInfo().updateVersionInfo(currentVersion, currentRevision);
			}
		}
	}
}
