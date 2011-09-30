/** Copyright Klasse Objecten
 */
package nl.klasse.octopus.expressions.internal.types;

import nl.klasse.octopus.expressions.INavigationCallExp;
import nl.klasse.octopus.model.IAssociationEnd;


/** @author  Jos Warmer
 * @version $Id: NavigationCallExp.java,v 1.1 2006/03/01 19:13:25 jwarmer Exp $
 */
public class NavigationCallExp extends ModelPropertyCallExp implements INavigationCallExp {
	private IAssociationEnd navigationSource = null;

    /** Creates a new instance of NavigationCallExp */
    public NavigationCallExp() {
    }

	/**
	 * @return
	 */
	public IAssociationEnd getNavigationSource() {
		return navigationSource;
	}

	/**
	 * @param end
	 */
	public void setNavigationSource(IAssociationEnd end) {
		navigationSource = end;
	}

}
