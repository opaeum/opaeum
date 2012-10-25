package org.opaeum.uim.userinteractionproperties.filters;

import org.eclipse.jface.viewers.IFilter;
import org.opaeum.uim.UserInteractionElement;
import org.opaeum.uim.cube.CubeQuery;

public class CubeQueryFilter extends AbstractFilter implements IFilter{
	@Override
	public boolean select(UserInteractionElement e){
		return e instanceof CubeQuery;
	}
}
