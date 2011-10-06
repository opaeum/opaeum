package org.opaeum.metamodel.actions;

import org.opaeum.metamodel.activities.INakedAction;
import org.opaeum.metamodel.activities.INakedOutputPin;
import org.opaeum.metamodel.core.INakedClassifier;

public interface INakedCreateObjectAction extends INakedAction {
	INakedClassifier getClassifier();
	INakedOutputPin getResult();
	void setClassifier(INakedClassifier  c);
	void setResult(INakedOutputPin  r);
}
