package util;

import java.util.Comparator;

import net.sf.nakeduml.userinteractionmetamodel.PropertyField;

/** Class ...
 */
public class CompPropertyFieldOndouble implements Comparator {



	public int compare(Object arg0, Object arg1) {
		if ( arg0 == null || arg1 == null ) {
			return 0;
		}
		int result = 0;
		double value0 = 0;
		double value1 = 0;
		PropertyField i_PropertyField = null;
		if ( arg0 instanceof PropertyField ) {
			i_PropertyField = (PropertyField) arg0;
			value0 = i_PropertyField.getDisplayIndex();
		}
		if ( arg1 instanceof PropertyField ) {
			i_PropertyField = (PropertyField) arg1;
			value1 = i_PropertyField.getDisplayIndex();
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