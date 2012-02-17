package org.opaeum.topcased.propertysections.constraints;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.uml2.uml.NamedElement;

public final class Utils{
	public static String getName(NamedElement element){
		return element.getName() == null ? "" : element.getName();
	}
	public static String getValueNotNull(String s){
		return s == null ? "" : s;
	}
	public static void layout(Composite c){
		c.layout();
		Composite parent = c.getParent();
		while(parent != null){
			parent.layout();
			parent.redraw();
			parent.update();
			parent = parent.getParent();
		}
	}
	public static boolean notNull(Object...objects){
		for(Object o:objects){
			if(o == null){
				return false;
			}
		}
		return true;
	}
}
