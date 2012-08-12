package org.opaeum.javageneration.bpm.actions;

import nl.klasse.octopus.codegen.umlToJava.maps.StructuralFeatureMap;

import org.eclipse.uml2.uml.Action;
import org.opaeum.eclipse.EmfActionUtil;
import org.opaeum.javageneration.util.OJUtil;

/**
 * Base class for all action builders that could potentially build a task representing a usertask.
 * 
 * @param <A>
 */
public abstract class PotentialTaskActionBuilder<A extends Action> extends AbstractProtectedNodeBuilder<A>{
	protected PotentialTaskActionBuilder(OJUtil util,A node){
		super(util, node, maybeBuildCallMap(node, util));
	}
	private static StructuralFeatureMap maybeBuildCallMap(Action node,OJUtil ojUtil){
		StructuralFeatureMap callMap = null;
		if(EmfActionUtil.getTargetElement(node)!=null && EmfActionUtil.hasMessageStructure(node)){
			callMap = ojUtil.buildStructuralFeatureMap(node);
		}
		return callMap;
	}
	@Override
	public abstract boolean isLongRunning();
}
