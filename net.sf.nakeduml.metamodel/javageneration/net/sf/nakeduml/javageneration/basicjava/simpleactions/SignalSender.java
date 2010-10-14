package net.sf.nakeduml.javageneration.basicjava.simpleactions;

import net.sf.nakeduml.javageneration.NakedClassifierMap;
import net.sf.nakeduml.javageneration.bpm.BpmUtil;
import net.sf.nakeduml.javageneration.bpm.actions.ActionMap;
import net.sf.nakeduml.javametamodel.OJBlock;
import net.sf.nakeduml.javametamodel.OJField;
import net.sf.nakeduml.javametamodel.OJPathName;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedOperation;
import net.sf.nakeduml.metamodel.actions.INakedSendSignalAction;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import nl.klasse.octopus.oclengine.IOclEngine;

public class SignalSender extends SimpleActionBuilder<INakedSendSignalAction>{
	public SignalSender(IOclEngine oclEngine,INakedSendSignalAction action){
		super(oclEngine, action);
	}
	@Override
	public void implementActionOn(OJAnnotatedOperation operation,OJBlock block){
		OJField async = new OJField();
		INakedClassifier targetBaseType = node.getTarget().getNakedBaseType();
		NakedClassifierMap map = new NakedClassifierMap(targetBaseType);
		String asyncName = "async" + map.javaType() + "For" + node.getName();
		async.setName(asyncName);
		OJPathName iasync = BpmUtil.asyncInterfaceOf(targetBaseType);
		async.setType(iasync);
		operation.getOwner().addToImports("org.jboss.seam.Component");
		async.setInitExp("(" + iasync.getLast() + ")Component.getInstance(\"async" + map.javaType() + "\")");
		block.addToLocals(async);
		ActionMap actionMap = new ActionMap(node);
		block=buildLoopThroughTarget(operation, block, actionMap);
		StringBuilder args = populateArguments(operation, node.getArguments());
		block.addToStatements(asyncName + ".process" + node.getSignal().getName() + "(" + actionMap.targetName()+ "," + args + ")");
	}
}
