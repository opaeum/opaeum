/* (c) Copyright 2002, Klasse Objecten
 */
package nl.klasse.octopus.model;

/** This class representes the enumeration that states the role of
 *  a parameter in an operation. It is a type safe enumeration build 
 *  according to item 21 in "Effective Java" by Joshua Bloch.
 * 
 * @author  Jos Warmer
 * @version $Id: ParameterDirectionKind.java,v 1.2 2008/01/19 13:48:24 annekekleppe Exp $
 */
public class ParameterDirectionKind {

    public static final ParameterDirectionKind IN    = new ParameterDirectionKind("in");
    public static final ParameterDirectionKind OUT   = new ParameterDirectionKind("out");
    public static final ParameterDirectionKind INOUT = new ParameterDirectionKind("inout");

	private final String name;
	
	private ParameterDirectionKind(String name) {
			this.name  = name;
		}
		
	public String getName() { return name; }

}
