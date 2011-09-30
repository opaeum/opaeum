package org.opeum.metamodel.actions;

import org.opeum.metamodel.activities.INakedAction;
import org.opeum.metamodel.activities.INakedOutputPin;
import org.opeum.metamodel.core.INakedClassifier;

public interface INakedCreateObjectAction extends INakedAction {
	INakedClassifier getClassifier();
	INakedOutputPin getResult();
	void setClassifier(INakedClassifier  c);
	void setResult(INakedOutputPin  r);
}
