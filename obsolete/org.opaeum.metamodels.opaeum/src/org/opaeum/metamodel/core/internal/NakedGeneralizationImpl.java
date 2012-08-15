package org.opaeum.metamodel.core.internal;
import org.eclipse.uml2.uml.INakedClassifier;
import org.eclipse.uml2.uml.INakedGeneralization;
import org.eclipse.uml2.uml.INakedPowerTypeInstance;
/**
 * @author Ampie Barnard
 */
public class NakedGeneralizationImpl extends NakedElementImpl implements INakedGeneralization {
	private static final long serialVersionUID = 6461786164692252056L;
	protected INakedClassifier supertype;
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
		return (INakedClassifier) this.getOwnerElement();
	}
	public INakedClassifier getGeneral() {
		return this.supertype;
	}
	public void setGeneral(INakedClassifier parent) {
		if(this.supertype!=null){
			this.supertype.removeSubClass(getSpecific());
		}
		this.supertype = parent;
		if(this.supertype!=null){
			this.supertype.addSubClass(getSpecific());
		}
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
