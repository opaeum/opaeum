package net.sf.nakeduml.metamodel.core.internal;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedInterface;
import net.sf.nakeduml.metamodel.core.INakedInterfaceRealization;
public class NakedInterfaceRealizationImpl extends NakedElementImpl implements INakedInterfaceRealization {
	INakedInterface contract;
	INakedClassifier implementingClassifier;
	@Override
	public String getMetaClass() {
		return "interfaceRealization";
	}
	public INakedInterface getContract() {
		return contract;
	}
	public void setContract(INakedInterface contract) {
		this.contract = contract;
	}
	public INakedClassifier getImplementingClassifier() {
		return implementingClassifier;
	}
	public void setImplementingClassifier(INakedClassifier implementingClassifier) {
		this.implementingClassifier = implementingClassifier;
	}
}
