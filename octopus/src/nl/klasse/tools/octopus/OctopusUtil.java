/** (c) Copyright 2002, Klasse Objecten
 */
package nl.klasse.tools.octopus;

import java.util.Collection;
import java.util.Iterator;

import nl.klasse.octopus.expressions.IOclExpression;
import nl.klasse.tools.common.Check;



/** @author  Jos Warmer
 * @version $Id: OctopusUtil.java,v 1.1 2006/03/01 19:13:36 jwarmer Exp $
 */
public class OctopusUtil {

    /** Creates a new instance of Util
      */
    private OctopusUtil() {
    }

    /** A helper operation 
	 * coll should contain OclExpressions
	  */
	 static public String collectionAsOclString(Collection coll, String separator)
	 {
		 Check.pre("Util.collectionAsOclString: coll should hold IOclExpressions", 
		           Check.elementsOfType(coll, IOclExpression.class));
		 if( coll == null ) { return ""; }
		 String result = "";
		 if( coll != null ){
			 Iterator i = coll.iterator();
			 while( i.hasNext() ){
				 IOclExpression o = (IOclExpression) i.next();
				 result = result + (o == null ? "<null>" : o.asOclString());
				 if( i.hasNext() ) result = result + separator;
			 }
		 }
		 return result;
	 }    
}
