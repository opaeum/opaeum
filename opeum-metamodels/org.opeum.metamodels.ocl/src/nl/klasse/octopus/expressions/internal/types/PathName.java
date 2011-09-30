/* (c) Copyright 2002, Klasse Objecten
 */
package nl.klasse.octopus.expressions.internal.types;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/** A PathName represents a qualified name that exists in the OCL concrete syntax
 *  as Part1::part2::part3::part4
 *
 * @author  Jos Warmer
 * @version $Id: PathName.java,v 1.2 2008/01/19 13:53:12 annekekleppe Exp $
 */
public class PathName {

    private List  names = new ArrayList();
    
	/** Creates new PathName  with <arg>s<\arg> as only
	 * element in the name list
	 * @param s
	 */
	public PathName(String s) {
		names.add(s);
	}
	
	/** Creates new PathName  without any element in the name list
	 */
	public PathName() {
	}
    
	/** Creates new PathName  with <arg>ns<\arg> as the
	 * elements in the name list
	 * @param s
	 */
    private PathName(List<String> ns){
        names = ns;
    }

    /** 
     * Adds <arg>s<\arg> as last (i.e. at the right end) element to the name list.
	 * @param s
	 */
	public void addString(String s){
      names.add(s);
    }

    /**
     * Returns all names in this pathname as a Collection
	 * @return the collection of all names
	 */
	public Collection<String> getNames() {
        return names;
    }
    
    /**
     * Returns a pathname holding all names of this pathname except the first (most left)
     * element.
	 * @return the pathname that is the tail of this instance
	 */
    public PathName getTail(){
		PathName result = new PathName("");
		if (names.size() > 0) result = new PathName( names.subList(1, names.size()) );
		return result;
    }
    
    /**
     * Returns a pathname holding all names of this pathname except the last (most right)
     * element.
	 * @return the pathname that is the head of this instance
	 */
    public PathName getHead(){
		PathName result = new PathName("");
    	if (names.size() > 0) result = new PathName( names.subList(0, names.size() - 1) );
        return result;
    }
    
	/**
	 * Returns the last name in the name list as String
	 * 'Last' means the most right element.
	 * @return the last name in the name list
	 */
	public String getLast(){
		String result = "";
		if (names.size() > 0) result = (String) names.get( names.size() - 1 );
		return result;
	}

	/**
	 * Returns the first (most left) name in this pathname as String.
	 * @return the first name in the name list
	 */
	public String getFirst(){
		String result = "";
		if (names.size() > 0) result = (String) names.get(0);
		return result;
	}
   
    /**
     * Returns true if all names in <arg>other<\arg> are in this pathname
     * in the same order, returns false otherwise.
	 * @param other
	 * @return true if <code>other</code> is equal to this instance
	 */
    public boolean equals(PathName other) {
        return this.toString().equals(other.toString());
    }
    
    /**
	 * Returns a copy of this pathname.
	 * @return the copy
	 */
    public PathName getCopy() {
    	ListIterator iter = names.listIterator();
    	ArrayList<String> newList = new ArrayList<String>();
    	while( iter.hasNext() ) {
    		String elem = (String) iter.next();
    		newList.add(elem);
    	}
    	PathName result = new PathName( newList );
        return result;
    }        

    /**
     * Returns true if this pathname contains only one name,
     * returns false otherwise.
	 * @return true if this pathname contains only one name,
	 */
	public boolean isSingleName() {
        return (names.size() == 1);
    }
    
    /* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return toStringWithSeparator("::");
    }

	/**
	 * Method toStringWithSeparator: returns all names in this PathName in a single
	 * String, where the names are separated by 'separator'.
	 * @param separator
	 * @return String
	 */
	public String toStringWithSeparator(String separator) {
		String result = "";		
		Iterator i = names.iterator();
		while( i.hasNext() ){
		    String s = (String) i.next();
		    if( s == null ){
		    	System.err.println("NULL IN PATHNAME");
		    }
		    if( result == null) {
				System.err.println("RESULT NULL IN PATHNAME");
//		    	result = "null" + separator + s;
//		    	continue;
		    }
		    if( result.equals("") ){
		      result = (s==null? "null" :s);
		    } else {
		      result = result + separator + s;
		    }
		}
		return result;
	}
}
