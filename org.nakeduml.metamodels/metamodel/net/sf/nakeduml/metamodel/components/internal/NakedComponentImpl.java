package net.sf.nakeduml.metamodel.components.internal;

import java.util.ArrayList;
import java.util.Collection;

import net.sf.nakeduml.metamodel.components.INakedComponent;
import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.metamodel.core.INakedPackage;
import net.sf.nakeduml.metamodel.core.internal.NakedEntityImpl;
import nl.klasse.octopus.model.IPackage;

public class NakedComponentImpl extends NakedEntityImpl implements INakedComponent{
	protected Collection<INakedPackage> subPackages = new ArrayList<INakedPackage>();
	private static final long serialVersionUID = -5658739232216672479L;
	public static final String META_CLASS = "component";
	@Override
	public String getMetaClass(){
		return META_CLASS;
	}
	public Collection<IPackage> getSubpackages(){
		return new ArrayList<IPackage>(subPackages);
	}
	@Override
	public void addOwnedElement(INakedElement element){
		super.addOwnedElement(element);
		if(element instanceof INakedPackage){
			this.subPackages.add((INakedPackage) element);
		}
	}
	@Override
	protected boolean isNamedMember(INakedElement e){
		return super.isNamedMember(e) || e instanceof INakedPackage;
	}
}
