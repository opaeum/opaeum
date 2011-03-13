/** (c) Copyright 2003, Klasse Objecten
 */

package nl.klasse.octopus.model;

/** OclUsageType holds the constants that indicate whether a certain OclExpression
 * is used as invariant, init expression, derivation rule etc.
 * It is a type safe enumeration according to item 21 in "Effective Java" by
 * Joshua Bloch.
 *
 * @author anneke
 * @version $Id: OclUsageType.java,v 1.2 2008/01/19 13:48:24 annekekleppe Exp $
 */
public class OclUsageType {
	private final String name;
	
	public static final OclUsageType WRONGTYPE = new OclUsageType("no such constraint type");
	public static final OclUsageType INV = new OclUsageType("inv");
	public static final OclUsageType DEF = new OclUsageType("def");
	public static final OclUsageType PRE = new OclUsageType("pre");
	public static final OclUsageType POST = new OclUsageType("post");
	public static final OclUsageType INIT = new OclUsageType("init");
	public static final OclUsageType DERIVE = new OclUsageType("derive");
	public static final OclUsageType BODY = new OclUsageType("body");

	@SuppressWarnings("unused")
	private static final OclUsageType[] ALL
			 = {WRONGTYPE, INV, DEF, PRE, POST, INIT, DERIVE, BODY};

	private OclUsageType(String name) {
			this.name  = name;
		}
		
	/**
	 * Returns the correct name of this usafge type according to the OCL syntax,
	 * that is, "inv", "pre", etc.
	 * @return
	 */
	public String getName() { return name; }

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() { return getName(); }
	
}
