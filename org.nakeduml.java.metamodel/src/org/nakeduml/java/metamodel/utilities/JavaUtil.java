/** (c) Copyright 2002, Klasse Objecten
 */
package org.nakeduml.java.metamodel.utilities;

import java.util.Collection;
import java.util.Iterator;

import org.nakeduml.java.metamodel.OJElement;




/** @author  Jos Warmer
 * @version $Id: JavaUtil.java,v 1.1 2006/03/15 13:47:41 jwarmer Exp $
 */
public class JavaUtil {

    /** Creates a new instance of Util
      */
    public JavaUtil() {
    }

	static public String collectionToJavaString(Collection coll, String separator)
	{
//		Check.pre("Util.collectionToJavaString: coll should hold OJElements", 
//				  Check.elementsOfType(coll, OJElement.class));
		if( coll == null ) { return ""; }
		String result = "";
		if( coll != null ){
			Iterator i = coll.iterator();
			while( i.hasNext() ){
				OJElement o = (OJElement) i.next();
				result = result + (o == null ? "<null>" : o.toJavaString());
				if( i.hasNext() ) result = result + separator;
			}
		}
		return result;
	}
	static public String collectionToString(Collection coll, String separator)
	{
		if( coll == null ) { return ""; }
		String result = "";
		if( coll != null ){
			Iterator i = coll.iterator();
			while( i.hasNext() ){
				Object o = i.next();
				result = result + (o == null ? "<null>" : o.toString());
				if( i.hasNext() ) result = result + separator;
			}
		}
		return result;
	}


}
