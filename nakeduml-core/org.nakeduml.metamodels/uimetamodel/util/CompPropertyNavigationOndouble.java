package util;

import java.util.Comparator;

import net.sf.nakeduml.userinteractionmetamodel.PropertyNavigation;

/** Class ...
 */
public class CompPropertyNavigationOndouble implements Comparator {



	public int compare(Object arg0, Object arg1) {
		if ( arg0 == null || arg1 == null ) {
			return 0;
		}
		int result = 0;
		double value0 = 0;
		double value1 = 0;
		PropertyNavigation i_PropertyNavigation = null;
		if ( arg0 instanceof PropertyNavigation ) {
			i_PropertyNavigation = (PropertyNavigation) arg0;
			value0 = i_PropertyNavigation.getDisplayIndex();
		}
		if ( arg1 instanceof PropertyNavigation ) {
			i_PropertyNavigation = (PropertyNavigation) arg1;
			value1 = i_PropertyNavigation.getDisplayIndex();
		}
		if ( value0>value1 ) {
			result = 1;
		}
		if ( value0 == value1 ) {
			result = 0;
		}
		if ( value0<value1 ) {
			result = -1;
		}
		return result;
	}

}