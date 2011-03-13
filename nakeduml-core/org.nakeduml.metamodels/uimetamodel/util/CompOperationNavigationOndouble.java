package util;

import java.util.Comparator;

import net.sf.nakeduml.userinteractionmetamodel.OperationNavigation;

/** Class ...
 */
public class CompOperationNavigationOndouble implements Comparator {



	public int compare(Object arg0, Object arg1) {
		if ( arg0 == null || arg1 == null ) {
			return 0;
		}
		int result = 0;
		double value0 = 0;
		double value1 = 0;
		OperationNavigation i_OperationNavigation = null;
		if ( arg0 instanceof OperationNavigation ) {
			i_OperationNavigation = (OperationNavigation) arg0;
			value0 = i_OperationNavigation.getDisplayIndex();
		}
		if ( arg1 instanceof OperationNavigation ) {
			i_OperationNavigation = (OperationNavigation) arg1;
			value1 = i_OperationNavigation.getDisplayIndex();
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