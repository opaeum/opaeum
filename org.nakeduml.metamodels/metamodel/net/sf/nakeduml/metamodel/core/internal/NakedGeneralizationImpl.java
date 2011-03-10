package net.sf.nakeduml.metamodel.core.internal;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.metamodel.core.INakedGeneralization;
import net.sf.nakeduml.metamodel.core.INakedPowerTypeInstance;
/**
 * @author Ampie Barnard
 */
public class NakedGeneralizationImpl extends NakedModelElementImpl implements INakedElement, INakedGeneralization {
	private static final long serialVersionUID = 6461786164692252056L;
	protected INakedClassifier supertype;
	protected INakedClassifier subtype;
	private INakedPowerTypeInstance powerTypeLiteral;
	public NakedGeneralizationImpl() {
		super();
	}
	@Override
	public String getName() {
		return super.getName();
	}
	public INakedPowerTypeInstance getPowerTypeLiteral() {
		return this.powerTypeLiteral;
	}
	public INakedClassifier getSpecific() {
		return this.subtype;
	}
	public INakedClassifier getGeneral() {
		return this.supertype;
	}
	public void setParentAndChild(INakedClassifier parent, INakedClassifier child) {
		this.supertype = parent;
		this.subtype = child;
		parent.addSubClass(child);
	}
	@Override
	public String getMetaClass() {
		return "Generalization";
	}
	public void setPowerTypeLiteral(INakedPowerTypeInstance powerTypeLiteral) {
		this.powerTypeLiteral = powerTypeLiteral;
		powerTypeLiteral.setRepresentedGeneralization(this);
	}
}
