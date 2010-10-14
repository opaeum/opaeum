package net.sf.nakeduml.metamodel.core.internal;

import java.util.ArrayList;
import java.util.List;

import net.sf.nakeduml.metamodel.core.INakedAssociation;
import net.sf.nakeduml.metamodel.core.INakedNameSpace;
import net.sf.nakeduml.metamodel.core.INakedProperty;
import nl.klasse.octopus.expressions.internal.types.PathName;
import nl.klasse.octopus.model.IAssociationEnd;
import nl.klasse.octopus.model.IAttribute;

public class NakedAssociationImpl extends NakedClassifierImpl implements INakedAssociation{
	private static final long serialVersionUID = 4136541106305681924L;
	public static final String META_CLASS = "association";
	private boolean isDerived = false;
	private boolean isClass = false;
	private List<INakedProperty> ends = new ArrayList<INakedProperty>();;
	{
		this.ends.add(null);
		this.ends.add(null);
	}
	public NakedAssociationImpl(){
		super();
	}
	@Override
	public PathName getPathName(){
		PathName o = ((INakedNameSpace) getOwnerElement()).getPathName();
		if(getName() == null){
			o.addString("AynonymousAssocociation");
		}else{
			o.addString(getName());
		}
		return o;
	}
	@Override
	public INakedProperty findEffectiveAttribute(String name){
		IAssociationEnd ae = findAssociationEnd(name);
		if(ae instanceof INakedProperty){
			return (INakedProperty) ae;
		}else{
			IAttribute at = findAttribute(name);
			if(at instanceof INakedProperty){
				return (INakedProperty) at;
			}
		}
		return null;
	}
	public List<INakedProperty> getEnds(){
		return this.ends;
	}
	@Override
	public String getMetaClass(){
		return "Association";
	}
	public void setEnd(int index,INakedProperty np){
		while(this.ends.size() <= index){
			this.ends.add(null);
		}
		this.ends.set(index, np);
		if(this.ends.get(0) != null && this.ends.get(1) != null){
			this.ends.get(0).setOtherEnd(this.ends.get(1));
			this.ends.get(1).setOtherEnd(this.ends.get(0));
			this.ends.get(0).setAssociation(this);
			this.ends.get(1).setAssociation(this);
		}
	}
	public INakedProperty getEnd1(){
		return this.ends.get(0);
	}
	public INakedProperty getEnd2(){
		return this.ends.get(1);
	}
	public IAssociationEnd getOtherEnd(IAssociationEnd assocEnd){
		if(this.ends.indexOf(assocEnd) == 0){
			return getEnd2();
		}else{
			return getEnd1();
		}
	}
	@Override
	public String toString(){
		return getName() + " " + getEnd1() + " <-> " + getEnd2();
	}
	public boolean isDerived(){
		return this.isDerived;
	}
	public boolean isClass(){
		return this.isClass;
	}
	public void setClass(boolean b){
		this.isClass = b;
	}
	public void setDerived(boolean t){
		this.isDerived = t;
	}
}
