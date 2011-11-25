package org.opaeum.javageneration.jbpm5.actions;

import org.opaeum.javageneration.maps.NakedStructuralFeatureMap;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.linkage.BehaviorUtil;
import org.opaeum.metamodel.actions.IActionWithTargetElement;
import org.opaeum.metamodel.activities.INakedAction;
import org.opaeum.metamodel.workspace.OpaeumLibrary;

/**
 * Base class for all action builders that could potentially build a task representing a usertask.
 * 
 * @param <A>
 */
public abstract class PotentialTaskActionBuilder<A extends INakedAction> extends AbstractProtectedNodeBuilder<A>{
	protected PotentialTaskActionBuilder(OpaeumLibrary oclEngine,A node){
		super(oclEngine, node, maybeBuildCallMap(node, oclEngine));
	}
	private static NakedStructuralFeatureMap maybeBuildCallMap(INakedAction node,OpaeumLibrary l){
		NakedStructuralFeatureMap callMap = null;
		if(node instanceof IActionWithTargetElement && BehaviorUtil.hasMessageStructure(node)){
			callMap = OJUtil.buildStructuralFeatureMap((IActionWithTargetElement) node, l);
		}
		return callMap;
	}
	@Override
	public abstract boolean isLongRunning();
}
