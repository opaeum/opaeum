package org.opaeum.linkage;

import nl.klasse.octopus.expressions.internal.types.PathName;
import nl.klasse.octopus.model.IClassifier;
import nl.klasse.octopus.model.IOperation;
import nl.klasse.octopus.model.IParameter;
import nl.klasse.octopus.model.ParameterDirectionKind;

import org.opaeum.metamodel.core.INakedTypedElement;

//TODO maybe move to metamodel?
public class SignalParameter implements IParameter{
	private final INakedTypedElement p;
	SignalParameter(INakedTypedElement p){
		this.p = p;
	}
	@Override
	public PathName getPathName(){
		return p.getPathName();
	}
	@Override
	public String getName(){
		return p.getName();
	}
	@Override
	public IClassifier getType(){
		return p.getType();
	}
	@Override
	public IOperation getOwner(){
		return null;
	}
	@Override
	public ParameterDirectionKind getDirection(){
		return ParameterDirectionKind.IN;
	}
}