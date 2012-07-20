package org.opaeum.eclipse;

import org.eclipse.uml2.uml.Operation;

public class EmfOperationUtil {
	public static boolean isPrefix(Operation o){
		return o.getName().equals("++")|| o.getName().equals("--");
	}
}
