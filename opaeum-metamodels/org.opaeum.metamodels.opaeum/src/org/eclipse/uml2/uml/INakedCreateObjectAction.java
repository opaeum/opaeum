package org.eclipse.uml2.uml;


public interface INakedCreateObjectAction extends INakedAction {
	INakedClassifier getClassifier();
	INakedOutputPin getResult();
	void setClassifier(INakedClassifier  c);
	void setResult(INakedOutputPin  r);
}
