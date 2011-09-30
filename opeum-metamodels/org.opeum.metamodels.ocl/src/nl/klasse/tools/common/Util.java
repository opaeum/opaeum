/** (c) Copyright 2002, Klasse Objecten
 */
package nl.klasse.tools.common;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

/** @author  Jos Warmer
 * @version $Id: Util.java,v 1.2 2008/01/19 15:02:35 annekekleppe Exp $
 */
public class Util {

    /** Creates a new instance of Util
      */
    private Util() {
    }

    static public String collectToString(Collection<Object> source, String property, String seperator){
    	Collection<Object> tmp = collect(source, property);
    	return collectionToString(tmp, seperator);
    }

    static public Collection<Object> collect(Collection<Object> source, String property){
    	Collection<Object> result = new ArrayList<Object>();
    	Iterator it = source.iterator();
    	while( it.hasNext() ){
    		Object object = it.next();
    		Class itsClass = object.getClass();
    		try {
				Method propertyMethod = itsClass.getMethod(property, (Class[]) null);
				Object collectable = propertyMethod.invoke(object, (Object[]) null);
				if( collectable != null ){
					result.add(collectable);
				}
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	return result;
    }
    
    static public void deepCopyCollection(Collection<Object> source, Collection<Object> target){
        Iterator i = source.iterator();
        while( i.hasNext() ){
            target.add( ((DeepCopy)i.next()).deepCopy() );
        }
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

	/**
	 * Convert the supplied PREFERENCE_DELIMITER delimited
	 * String to a String array.
	 * @return String[]
	 */
	static public String[] convertValuesFromString(String value, String delimiter) {
		StringTokenizer tokenizer =
			new StringTokenizer(value, delimiter);
		int tokenCount = tokenizer.countTokens();
		String[] elements = new String[tokenCount];
	
		for (int i = 0; i < tokenCount; i++) {
			elements[i] = tokenizer.nextToken();
		}
	
		return elements;
	}

	static public List<Object> arrayToList(Object[] a){
		List<Object> result = new ArrayList<Object>(a.length);
		for( int i = 0; i < a.length; i++){
			result.add(a[i]);
		}
		return result;
	}
	/**
	 * Set the bad words preference
	 * @param String [] elements - the Strings to be 
	 * 	converted to the preference value
	 */
	static public String convertValuesToString(String[] elements, String delimiter) {
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < elements.length; i++) {
			buffer.append(elements[i]);
			buffer.append(delimiter);
		}
		return buffer.toString();
	}

	/** Reads the complete file into a StringBuffer.
	 * 
	 * @param fileName
	 * @return
	 */
	static public StringBuffer readFile(String  fileName) {
		BufferedReader inReader;
		StringBuffer inStringBuf = new StringBuffer();
	      
		// read the whole input file into the variable inString
		try {
			inReader = new BufferedReader (new FileReader (fileName));
			String str = inReader.readLine();
			while (str != null) {
				inStringBuf.append (str + "\n");
				str = inReader.readLine();
			}
			inReader.close();
		} catch (IOException ioe) {
			//TODO error mess
			System.out.println ("***** Error: could not read file " +
			 fileName + ";\n" + ioe.toString());
		  return null ;
		}
		return inStringBuf;
	}

	static public String toJavaString(String s){
		StringBuffer buf = new StringBuffer();
		for(int i = 0 ; i<s.length(); i++){
			char ch = s.charAt(i);
			if( (ch == '"')) { // && (i > 0) && (i != s.length()-1) ){
				buf.append("\\\"");
			} else {
				buf.append(ch);
			}
		}
		return buf.toString();
	}
  
}
