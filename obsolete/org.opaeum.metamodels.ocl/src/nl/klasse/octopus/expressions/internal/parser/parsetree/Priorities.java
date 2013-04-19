/** Copyright Klasse Objecten
 */
package nl.klasse.octopus.expressions.internal.parser.parsetree;

import java.util.HashMap;
import java.util.Map;

import nl.klasse.octopus.expressions.internal.parser.javacc.OclParserConstants;

/** @author  Jos Warmer
 * @version $Id: Priorities.java,v 1.2 2008/01/19 13:48:05 annekekleppe Exp $
 */
public class Priorities implements OclParserConstants {
	  private static boolean 					isInitialized = false;
	  private static Map<Integer, Integer> 	priorityMap = new HashMap<Integer, Integer>();
	  public static final int 						MAX_PRIORITY = 32000;
	  public static final int 						MIN_PRIORITY = 0;


	  public static void main (String args[]) {
	    System.out.println( "9 EQUALS: " + Priorities.getPriority(EQUALS));
	    System.out.println( "5 MINUS : " + Priorities.getPriority(MINUS ));
	    System.out.println( "7 LESS  : " + Priorities.getPriority(LESS  ));
	  }
	
	  public static int getPriority(int operator){
	    //  System.out.println("getPriority " + tokenImage[operator]);
	    if( ! isInitialized ){
	      isInitialized = true;
	      initialize();
	    }
	    int result = ((Integer) priorityMap.get(new Integer(operator)) ).intValue();
	    return result;
	  }
	
	  private static void initialize(){
	
	    priorityMap.put( new Integer(MULTIPLY)  ,  new Integer(100) );
	    priorityMap.put( new Integer(DIVIDE)    ,  new Integer(100) );
	
	    priorityMap.put( new Integer(PLUS)      ,  new Integer(90) );
	    priorityMap.put( new Integer(MINUS)     ,  new Integer(90) );
	
	    priorityMap.put( new Integer(LESS)      ,  new Integer(80) );
	    priorityMap.put( new Integer(GT)        ,  new Integer(80) );
	    priorityMap.put( new Integer(LESSEQ)    ,  new Integer(80) );
	    priorityMap.put( new Integer(GTEQ)      ,  new Integer(80) );
	
	    priorityMap.put( new Integer(EQUALS)    ,  new Integer(70) );
	    priorityMap.put( new Integer(NOTEQUALS) ,  new Integer(70) );
	
	    priorityMap.put( new Integer(AND)       , new Integer(60) );
	    priorityMap.put( new Integer(OR)        , new Integer(60) );
	    priorityMap.put( new Integer(XOR)       , new Integer(60) );
	
	    priorityMap.put( new Integer(IMPLIES)   , new Integer(50) );
	  }


}
