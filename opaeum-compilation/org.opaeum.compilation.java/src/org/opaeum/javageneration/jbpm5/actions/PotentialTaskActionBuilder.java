package org.opaeum.javageneration.jbpm5.actions;

import org.eclipse.uml2.uml.Action;
import org.opaeum.eclipse.EmfActionUtil;
import org.opaeum.javageneration.maps.NakedStructuralFeatureMap;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.metamodel.workspace.OpaeumLibrary;

/**
 * Base class for all action builders that could potentially build a task representing a usertask.
 * 
 * @param <A>
 */
public abstract class PotentialTaskActionBuilder<A extends Action> extends AbstractProtectedNodeBuilder<A>{
	protected PotentialTaskActionBuilder(OpaeumLibrary oclEngine,A node){
		super(oclEngine, node, maybeBuildCallMap(node, oclEngine));
	}
	private static NakedStructuralFeatureMap maybeBuildCallMap(Action node,OpaeumLibrary l){
		NakedStructuralFeatureMap callMap = null;
		if(EmfActionUtil.getTargetElement(node)!=null && EmfActionUtil.hasMessageStructure(node)){
			callMap = OJUtil.buildStructuralFeatureMap(node, l);
		}
		return callMap;
	}
	@Override
	public abstract boolean isLongRunning();
}
