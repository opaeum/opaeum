package org.opaeum.java.metamodel.generated;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.opaeum.java.metamodel.OJBlock;
import org.opaeum.java.metamodel.OJElement;
import org.opaeum.java.metamodel.OJField;
import org.opaeum.java.metamodel.OJStatement;

abstract public class OJBlockGEN extends OJStatement{
	private List<OJStatement> statements = new ArrayList<OJStatement>();
	private List<OJField> locals = new ArrayList<OJField>();
	protected OJBlockGEN(){
		super();
	}
	protected OJBlockGEN(String name,String comment){
		super();
		super.setName(name);
		super.setComment(comment);
	}
	public List<OJStatement> getStatements(){
		return statements;
	}
	public void setStatements(List<OJStatement> element){
		if(statements != element){
			statements = element;
		}
	}
	public void addToStatements(OJStatement element){
		if(statements.contains(element)){
			return;
		}
		statements.add(element);
	}
	public void removeFromStatements(OJStatement element){
		statements.remove(element);
	}
	public void addToStatements(Collection<OJStatement> newElems){
		Iterator<OJStatement> it = newElems.iterator();
		while((it.hasNext())){
			Object item = it.next();
			if(item instanceof OJStatement){
				addToStatements((OJStatement) item);
			}
		}
	}
	public void removeFromStatements(Collection<OJStatement> oldElems){
		Iterator<OJStatement> it = oldElems.iterator();
		while((it.hasNext())){
			Object item = it.next();
			if(item instanceof OJStatement){
				removeFromStatements((OJStatement) item);
			}
		}
	}
	public void removeAllFromStatements(){
		Iterator<OJStatement> it = new ArrayList<OJStatement>(getStatements()).iterator();
		while((it.hasNext())){
			Object item = it.next();
			if(item instanceof OJStatement){
				removeFromStatements((OJStatement) item);
			}
		}
	}
	public List<OJField> getLocals(){
		return locals;
	}
	public void setLocals(List<OJField> element){
		if(locals != element){
			locals = element;
		}
	}
	public void addToLocals(OJField element){
		if(locals.contains(element)){
			return;
		}
		locals.add(element);
	}
	public void removeFromLocals(OJField element){
		locals.remove(element);
	}
	public void addToLocals(Collection<OJField> newElems){
		Iterator<OJField> it = newElems.iterator();
		while((it.hasNext())){
			Object item = it.next();
			if(item instanceof OJField){
				addToLocals((OJField) item);
			}
		}
	}
	public void removeFromLocals(Collection<OJField> oldElems){
		Iterator<OJField> it = oldElems.iterator();
		while((it.hasNext())){
			Object item = it.next();
			if(item instanceof OJField){
				removeFromLocals((OJField) item);
			}
		}
	}
	public void removeAllFromLocals(){
		Iterator<OJField> it = new ArrayList<OJField>(getLocals()).iterator();
		while((it.hasNext())){
			Object item = it.next();
			if(item instanceof OJField){
				removeFromLocals((OJField) item);
			}
		}
	}
	public String toString(){
		String result = "";
		result = super.toString();
		return result;
	}
	public String getIdString(){
		String result = "";
		result = super.getIdString();
		return result;
	}
	public OJElement getCopy(){
		OJBlock result = new OJBlock();
		this.copyInfoInto(result);
		return result;
	}
	public void copyInfoInto(OJBlock copy){
		super.copyInfoInto(copy);
		Iterator<OJStatement> statementsIt = new ArrayList<OJStatement>(getStatements()).iterator();
		while(statementsIt.hasNext()){
			OJStatement elem = (OJStatement) statementsIt.next();
			copy.addToStatements(elem);
		}
		Iterator<OJField> localsIt = new ArrayList<OJField>(getLocals()).iterator();
		while(localsIt.hasNext()){
			OJField elem = (OJField) localsIt.next();
			copy.addToLocals(elem);
		}
	}
}