package org.opaeum.uim.queries;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.facet.infra.query.core.exception.ModelQueryExecutionException;
import org.eclipse.emf.facet.infra.query.core.java.IJavaModelQuery;
import org.eclipse.emf.facet.infra.query.core.java.ParameterValueList;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.opaeum.eclipse.context.OpaeumEclipseContext;
import org.opaeum.eclipse.context.OpenUmlFile;
import org.opaeum.uim.Page;
import org.opaeum.uim.PageOrdering;
import org.opaeum.uim.UserInteractionElement;
import org.opaeum.uim.UserInterfaceRoot;
import org.opaeum.uim.editor.ActionBar;
import org.opaeum.uim.resources.UimModelSet;

/**
 * Get the collection of all contained diagrams FIXME : delete this class when the bug EMF-Facet 365744 will be corrected!
 * 
 * @Deprecated : use oep.infra.gmfdiag.modelexplorer#queries.GetContainedDiagrams
 */
@Deprecated
public class GetContainedUimDiagrams implements IJavaModelQuery<UserInterfaceRoot,Collection<Diagram>>{
	public Collection<Diagram> evaluate(final UserInterfaceRoot context,final ParameterValueList parameterValues) throws ModelQueryExecutionException{
		UimModelSet modelSet = (UimModelSet) context.eResource().getResourceSet();
		EList<EObject> eContents = context.eContents();
		Map<Page,Diagram> map = new HashMap<Page,Diagram>();
		Collection<Diagram> result = new ArrayList<Diagram>();
		for(EObject eObject:eContents){
			if(eObject instanceof Page){
				Page page = (Page) eObject;
				Diagram dgn = modelSet.getInMemoryNotationResource().getDiagram(page);
				if(dgn != null){
					map.put(page, dgn);
				}
			}else if(eObject instanceof ActionBar){
				ActionBar bar = (ActionBar) eObject;
				Diagram dgn = modelSet.getInMemoryNotationResource().getDiagram(bar);
				if(dgn != null){
					result.add(dgn);
				}
			}
		}
		List<PageOrdering> pageOrdering = new ArrayList<PageOrdering>(context.getPageOrdering());
		Collections.sort(pageOrdering, new Comparator<PageOrdering>(){
			@Override
			public int compare(PageOrdering o1,PageOrdering o2){
				return o1.getPosition() - o2.getPosition();
			}
		});
		for(PageOrdering pe:pageOrdering){
			if(map.containsKey(pe.getPage())){
				result.add(map.get(pe.getPage()));
			}
		}
		return result;
	}
}
