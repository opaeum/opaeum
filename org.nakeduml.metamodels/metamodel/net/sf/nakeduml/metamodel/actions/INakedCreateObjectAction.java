package net.sf.nakeduml.metamodel.actions;

import net.sf.nakeduml.metamodel.activities.INakedAction;
import net.sf.nakeduml.metamodel.activities.INakedOutputPin;
import net.sf.nakeduml.metamodel.core.INakedClassifier;

public interface INakedCreateObjectAction extends INakedAction {
	INakedClassifier getClassifier();
	INakedOutputPin getResult();
	void setClassifier(INakedClassifier  c);
	void setResult(INakedOutputPin  r);
}
