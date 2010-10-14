package net.sf.nakeduml.linkage;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedEntity;
import net.sf.nakeduml.metamodel.core.INakedGeneralization;
import net.sf.nakeduml.metamodel.core.INakedInterface;
import net.sf.nakeduml.metamodel.core.INakedInterfaceRealization;

@StepDependency(phase = LinkagePhase.class)
public class UserRepresentationCalculator extends AbstractModelElementLinker{
	@VisitBefore(matchSubclasses = true)
	public void calculatUserRepresentation(INakedInterface i){
		i.setRepresentsUser(representsUser(i));
	}
	@VisitBefore(matchSubclasses = true)
	public void calculatUserRepresentation(INakedEntity e){
		e.setRepresentsUser(representsUser(e));
	}
	private static boolean representsUser(INakedClassifier c){
		if(c instanceof INakedEntity && ((INakedEntity) c).representsUser()){
			return true;
		}else if(c instanceof INakedInterface && ((INakedInterface) c).representsUser()){
			return true;
		}else{
			for(INakedGeneralization g:c.getNakedGeneralizations()){
				if(representsUser(g.getGeneral())){
					return true;
				}
			}
			if(c instanceof INakedEntity){
				INakedEntity e = (INakedEntity) c;
				for(INakedInterfaceRealization r:e.getInterfaceRealizations()){
					if(representsUser(r.getContract())){
						return true;
					}
				}
			}
		}
		return false;
	}
}
