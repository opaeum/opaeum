package net.sf.nakeduml.javageneration.jbpm5.statemachine;

import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.javageneration.jbpm5.ProcessStepEnumerationImplementor;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.javametamodel.OJPackage;
import net.sf.nakeduml.javametamodel.OJPathName;
import net.sf.nakeduml.javametamodel.annotation.OJEnum;
import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.metamodel.statemachines.INakedState;
import net.sf.nakeduml.metamodel.statemachines.INakedStateMachine;

public class StateEnumerationImplementor extends ProcessStepEnumerationImplementor{
	@Override
	protected INakedElement getEnclosingElement(INakedElement s){
		INakedState state = (INakedState) s;
		if(state.hasEnclosingState()){
			return state.getEnclosingState();
		}else{
			return null;
		}
	}
	@VisitBefore(matchSubclasses = true)
	public void visitClass(INakedStateMachine c){
		boolean hasStateComposition = hasStateComposition(c);
		buildOJEnum(c, hasStateComposition);
	}
	private boolean hasStateComposition(INakedStateMachine sm){
		for(INakedState s:sm.getAllStates()){
			if(s.hasEnclosingState()){
				return true;
			}
		}
		return false;
	}
	@VisitBefore(matchSubclasses = true)
	public void state(INakedState state){
		if(state.getKind().isRestingState()){
			INakedStateMachine sm = state.getStateMachine();
			OJPackage p = findOrCreatePackage(OJUtil.packagePathname(sm.getParent()));
			OJEnum e = (OJEnum) p.findClass(new OJPathName(sm.getMappingInfo().getJavaName().getAsIs() + "State"));
			buildLiteral(state, e);
		}
	}
}
