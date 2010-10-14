package net.sf.nakeduml.metamodel.core.internal;
import net.sf.nakeduml.metamodel.core.INakedElement;
import nl.klasse.octopus.model.IImportedElement;
import nl.klasse.octopus.model.IModelElement;
public class NakedImportedElementImpl extends NakedModelElementImpl implements INakedElement,IImportedElement {
	private static final long serialVersionUID = 1630029513308357488L;
	INakedElement element;
	@Override
	public String getMetaClass() {
		return "import";
	}
	@Override
	public String getName(){
		String n = getElement().getName();
		if(n==null){
			return "dummy";
		}
		return n;
	}
	public IModelElement getElement() {
		return element;
	}
	public boolean isReference() {
		return false;
	}
	public void setElement(INakedElement referenced) {
		this.element=referenced;
	}
}
