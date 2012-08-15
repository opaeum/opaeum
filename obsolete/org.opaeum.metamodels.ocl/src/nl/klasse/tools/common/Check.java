package nl.klasse.tools.common;

import java.util.Collection;
import java.util.Iterator;

/** The class Check implements pre- and postconditions on operations by defining 
 *  class-operations for them. Extra is a 'check' operation that can be called to check
 *  any condition somewhere in the code.
 *  <P>
 *  Example:
 * 
 *  <pre>
 *   public void setMealView( MealView m ) {
 *       Check.pre("In CookbookView.setMealView mealView is null.", m != null );
 *       ...
 *   }
 *  </pre>
 *
 * @author    Anneke Kleppe
 * @version   1.0
 */
public class Check {
	static public boolean ENABLED = true;
    
    /** Check a precondition and prints a message if the precondition fails.
     * @param message the error message that is printed
     * @param condition the precondition
     */
    public static void pre(String message, boolean condition){
        if (Check.ENABLED && !condition) {
            System.out.println("precondition failed in " + message);
            throw new RuntimeException("precondition failed in " + message);
        }    
        
    }
    
    /** Check a postcondition and prints a message if the postcondition fails.
     * @param message the error message that is printed
     * @param condition the postcondition
     */
    public static void post(String message, boolean condition){
        if (Check.ENABLED && !condition) {
            System.out.println("postcondition failed in " + message);
            throw new RuntimeException("postcondition failed in " + message);
        }    
    }

    /** Check any condition and prints a message if the condition fails.
     * @param message the error message that is printed
     * @param condition the condition to be checked
     */    
    public static void isTrue(String message, boolean condition){
        if (Check.ENABLED && !condition) {
            System.out.println("check failed in " + message);
            throw new RuntimeException("check failed in " + message);
        }    
    }
    
    public static boolean elementsOfType( Collection coll, Class type){
		Iterator i = coll.iterator();
    	while( i.hasNext() ){ 
    		Object o = i.next();
			if (!type.isInstance(o)) {
				return false;
			} 
    	}
    	return true;
    }
}
