/* (c) Copyright 2003, Klasse Objecten
 */
 
package nl.klasse.octopus.model;

/** IAssociation represents an association in Octopus. 
 *  From UML version 2 Association inherits from Classifier.
 *  Octopus only allows for associations that have two ends.
 * 
 * @author  Jos Warmer
 * @version $Id: IAssociation.java,v 1.1 2006/03/01 19:13:26 jwarmer Exp $
 */
public interface IAssociation extends IClassifier {
    /**
     * Returns one of the ends of the association.
     * Note that <code>getEnd1() <> getEnd2()</code>,
     * even when both ends are connected to the same class.
	 * @return
	 */
	public IAssociationEnd getEnd1();

    /**
     * Returns one of the ends of the association.
     * Note that <code>getEnd1() <> getEnd2()</code>.
     * 
	 * @return
	 */
	public IAssociationEnd getEnd2();

	/**
	 * Returns true when this association is a class.
	 * @return
	 */
	public boolean isClass();

	/**
	 * Returns the other end of the association.
	 * <p>
	 * Specification: <code> if assocEnd = getEnd1() then getEnd2() else getEnd1() endif </code>
	 * @param assocEnd
	 * @return
	 */
	public IAssociationEnd getOtherEnd(IAssociationEnd assocEnd);
}
