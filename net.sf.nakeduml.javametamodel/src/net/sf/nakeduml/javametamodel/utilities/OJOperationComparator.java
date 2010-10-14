/*
 * Created on Nov 1, 2004
 *
 */
package net.sf.nakeduml.javametamodel.utilities;

import java.util.Comparator;

import net.sf.nakeduml.javametamodel.OJOperation;
import net.sf.nakeduml.javametamodel.OJVisibilityKind;


/**
 * @author anneke
 *
 */
public class OJOperationComparator implements Comparator<OJOperation> {

	/* (non-Javadoc)
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	public int compare(OJOperation first, OJOperation second) {
		if (first.getVisibility().equals(second.getVisibility())) {
			return first.getName().compareTo(second.getName());
		} else if (first.getVisibility().equals(OJVisibilityKind.PRIVATE) && second.getVisibility().equals(OJVisibilityKind.PUBLIC)) {
			return 1;
		} else if (first.getVisibility().equals(OJVisibilityKind.PUBLIC) && second.getVisibility().equals(OJVisibilityKind.PRIVATE)) {
			return -1;
		}
		return 0;
	}

}
