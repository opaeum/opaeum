package net.sf.nakeduml.javageneration.jbpm5;

import java.util.HashSet;
import java.util.Set;

import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.javageneration.AbstractJavaProducingVisitor;
import net.sf.nakeduml.javageneration.JavaTextSource;
import net.sf.nakeduml.javageneration.NakedClassifierMap;
import net.sf.nakeduml.javageneration.NakedStructuralFeatureMap;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.javametamodel.OJBlock;
import net.sf.nakeduml.javametamodel.OJForStatement;
import net.sf.nakeduml.javametamodel.OJPathName;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedClass;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedField;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedInterface;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedOperation;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotationValue;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedReception;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedSignal;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedTriggerContainer;
import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.metamodel.core.INakedEntity;
import net.sf.nakeduml.metamodel.core.INakedProperty;

/**
 * 
 */
// REQUIRES seam but not JBPM - separate the two
public class SignalImplementor extends AbstractJavaProducingVisitor{
	@VisitBefore(matchSubclasses = true)
	public void visitReceptionsFor(INakedEntity e){
		if(e.getEffectiveReceptions().size() > 0){
			OJAnnotatedClass ojEntity = super.findJavaClass(e);
			Set<INakedSignal> coveredSignals = new HashSet<INakedSignal>();
			OJAnnotatedClass asyncService = buildAsyncService(ojEntity);
			OJAnnotatedInterface asyncInterface = buildAsyncInterface(ojEntity, asyncService);
			asyncService.addToImplementedInterfaces(asyncInterface.getPathName());
			// add service annotations,as well as jBPmAware
			// add EntityManager
			boolean buildMergeAll = false;
			for(INakedReception r:e.getEffectiveReceptions()){
				if(!coveredSignals.contains(r.getSignal())){
					coveredSignals.add(r.getSignal());
					buildMergeAll = buildMergeAll || addReception(asyncService, asyncInterface, ojEntity.getPathName(), r.getSignal());
				}
			}
			if(buildMergeAll || true){
				buildMergeAll(asyncService);
			}
			if(e.getClassifierBehavior() != null && e.getClassifierBehavior() instanceof INakedTriggerContainer){
				for(INakedElement el:((INakedTriggerContainer) e.getClassifierBehavior()).getAllMessageTriggers()){
					if(el instanceof INakedSignal && !coveredSignals.contains(el)){
						INakedSignal signal = (INakedSignal) el;
						coveredSignals.add(signal);
						buildMergeAll = buildMergeAll || addReception(asyncService, asyncInterface, ojEntity.getPathName(), signal);
					}
				}
			}
		}
		// TODO impement delegation to implementing behavior somewhere
	}
	private OJAnnotatedClass buildAsyncService(OJAnnotatedClass ojEntity){
		OJAnnotatedClass asyncService = new OJAnnotatedClass();
		asyncService.setName("Async" + ojEntity.getName());
		ojEntity.getMyPackage().addToClasses(asyncService);
		OJAnnotatedField entityManager = new OJAnnotatedField();
		entityManager.setName("entityManager");
		entityManager.setType(new OJPathName("javax.persistence.EntityManager"));
		asyncService.addToFields(entityManager);
		entityManager.addAnnotationIfNew(new OJAnnotationValue(new OJPathName("org.jboss.seam.annotations.In")));
		super.createTextPath(asyncService, JavaTextSource.GEN_SRC);
		asyncService.addAnnotationIfNew(new OJAnnotationValue(new OJPathName("javax.ejb.Stateless")));
		asyncService.addAnnotationIfNew(new OJAnnotationValue(new OJPathName("org.jboss.seam.annotations.Name"), "async" + ojEntity.getName()));
		return asyncService;
	}
	private void buildMergeAll(OJAnnotatedClass asyncService){
		OJAnnotatedOperation mergeAll = new OJAnnotatedOperation();
		asyncService.addToImports("java.util.Collection");
		mergeAll.setGenericTypeParam(new OJPathName("T, C extends Collection<T>"));
		OJPathName type = new OJPathName("C");
		mergeAll.addParam("source", type);
		mergeAll.addParam("target", type);
		mergeAll.setName("mergeAll");
		mergeAll.setReturnType(type);
		OJForStatement forEach = new OJForStatement("", "", "element", "source");
		forEach.setElemType(new OJPathName("T"));
		forEach.setBody(new OJBlock());
		forEach.getBody().addToStatements("target.add(entityManager.merge(element))");
		mergeAll.getBody().addToStatements(forEach);
		mergeAll.getBody().addToStatements("return target");
		asyncService.addToOperations(mergeAll);
	}
	private OJAnnotatedInterface buildAsyncInterface(OJAnnotatedClass ojEntity,OJAnnotatedClass asyncService){
		OJAnnotatedInterface asyncInterface = new OJAnnotatedInterface();
		asyncInterface.setName("I" + asyncService.getName());
		ojEntity.getMyPackage().addToClasses(asyncInterface);
		super.createTextPath(asyncInterface, JavaTextSource.GEN_SRC);
		asyncInterface.addAnnotationIfNew(new OJAnnotationValue(new OJPathName("javax.ejb.Local")));
		return asyncInterface;
	}
	private boolean addReception(OJAnnotatedClass asyncService,OJAnnotatedInterface asyncInterface,OJPathName ojEntity,
			INakedSignal nakedSignal){
		StringBuilder params = new StringBuilder();
		boolean buildMergeAll = false;
		OJAnnotatedOperation receiveOneTarget = new OJAnnotatedOperation();
		NakedClassifierMap map = new NakedClassifierMap(nakedSignal);
		receiveOneTarget.setName("process" + map.javaType());
		receiveOneTarget.addParam("target", ojEntity);
		receiveOneTarget.getBody().addToStatements("target=entityManager.merge(target)");
		for(INakedProperty p:nakedSignal.getOwnedAttributes()){
			if(!OJUtil.isBuiltIn(p)){
				// TODO support inheritance - what does UML say about that?
				NakedStructuralFeatureMap fmap = OJUtil.buildStructuralFeatureMap(p);
				receiveOneTarget.addParam(fmap.umlName(), fmap.javaTypePath());
				if(fmap.couldBasetypeBePersistent()){
					if(fmap.isOne()){
						receiveOneTarget.getBody().addToStatements(fmap.umlName() + "=entityManager.merge(" + fmap.umlName() + ")");
					}else{
						buildMergeAll = true;
						asyncService.addToImports(fmap.javaDefaultTypePath());
						receiveOneTarget.getBody()
								.addToStatements(fmap.umlName() + "=mergeAll(" + fmap.umlName() + "," + fmap.javaDefaultValue() + ")");
					}
				}
				params.append(fmap.umlName());
				params.append(',');
			}
		}
		params.delete(params.length() - 1, params.length());
		receiveOneTarget.getBody().addToStatements("target.on" + map.javaType() + "(" + params.toString() + ")");
		asyncService.addToOperations(receiveOneTarget);
		copyToInterface(asyncInterface, receiveOneTarget);
		return buildMergeAll;
	}
	private void copyToInterface(OJAnnotatedInterface asyncInterface,OJAnnotatedOperation receiveOneTarget){
		receiveOneTarget = receiveOneTarget.getCopy();
		receiveOneTarget.setBody(null);
		receiveOneTarget.addAnnotationIfNew(new OJAnnotationValue(new OJPathName("org.jboss.seam.annotations.async.Asynchronous")));
		asyncInterface.addToOperations(receiveOneTarget);
	}
}
