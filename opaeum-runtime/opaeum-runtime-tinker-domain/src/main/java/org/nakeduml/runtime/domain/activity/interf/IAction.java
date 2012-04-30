package org.nakeduml.runtime.domain.activity.interf;

import java.util.List;

public interface IAction extends IExecutableNode {
	List<? extends IInputPin<?, ?>> getInput();
	List<? extends IOutputPin<?, ?>> getOutput();
	IClassifier getContext();
}
