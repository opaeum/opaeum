/**
 *  Copyright (c) 2011 Atos.
 *  
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v1.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 *  
 *  Contributors:
 *  Atos - Initial API and implementation
 * 
 */
package org.opaeum.uimodeler.modelexplorer.queries;

import org.eclipse.emf.ecore.EStructuralFeature.Setting;
import org.eclipse.emf.facet.infra.query.core.exception.ModelQueryExecutionException;
import org.eclipse.emf.facet.infra.query.core.java.IJavaModelQuery;
import org.eclipse.emf.facet.infra.query.core.java.ParameterValueList;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.papyrus.views.modelexplorer.NavigatorUtils;
import org.eclipse.papyrus.views.modelexplorer.queries.AbstractEditorContainerQuery;
import org.opaeum.uim.UserInteractionElement;

import com.google.common.base.Predicate;

/**
 * FIXME : delete this class when the bug EMF-Facet 365744 will be corrected!
 * 
 */
public class IsUimDiagramContainer extends AbstractEditorContainerQuery implements IJavaModelQuery<UserInteractionElement, Boolean> {


	public Boolean evaluate(UserInteractionElement context, ParameterValueList parameterValues) throws ModelQueryExecutionException {
		Predicate<Setting> p = new Predicate<Setting>() {

			public boolean apply(Setting arg0) {
				return arg0.getEObject() instanceof Diagram;
			}
		};
		boolean find = NavigatorUtils.find(context, p);
		return find;
	}
}
