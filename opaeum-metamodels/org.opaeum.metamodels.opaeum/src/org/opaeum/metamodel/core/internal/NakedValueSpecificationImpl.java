package org.opaeum.metamodel.core.internal;

import nl.klasse.octopus.model.IClassifier;
import nl.klasse.octopus.oclengine.IOclContext;
import nl.klasse.octopus.oclengine.internal.OclContextImpl;

import org.opaeum.metamodel.core.INakedElement;
import org.opaeum.metamodel.core.INakedInstanceSpecification;
import org.opaeum.metamodel.core.INakedValueSpecification;

public class NakedValueSpecificationImpl extends NakedElementImpl implements INakedValueSpecification{
	private static final long serialVersionUID = 4330871931428233762L;
	private Object value;
	private boolean isElementReference;
	private IClassifier type;
	public NakedValueSpecificationImpl(IOclContext ctx){
		super();
		this.value=ctx;
	}
	public NakedValueSpecificationImpl(){
		super();
	}
	public IClassifier getType(){
		return type;
	}
	public void setType(IClassifier type){
		this.type = type;
	}
	public void setValue(Object value){
		this.value = value;
	}
	public void setValueId(Object valueReference){
		this.value = valueReference;
		this.isElementReference = true;
	}
	public boolean isLiteral(){
		return this.value instanceof Boolean || this.value instanceof String || this.value instanceof Double || this.value instanceof Integer;
	}
	@Override
	public String getName(){
		return super.getName();
	}
	public boolean isOclValue(){
		return this.value instanceof IOclContext;
	}
	public boolean isInstanceValue(){
		return this.value instanceof INakedInstanceSpecification;
	}
	public boolean booleanValue(){
		if(this.value instanceof Boolean){
			return ((Boolean) this.value).booleanValue();
		}else{
			throw new IllegalStateException("Not a Boolean");
		}
	}
	public String stringValue(){
		if(this.value instanceof String){
			return (String) this.value;
		}else if(this.value instanceof INakedElement){
			return ((INakedElement) this.value).getName();
		}else if(this.value == null){
			return null;
		}else{
			throw new IllegalStateException("Could not convert to string");
		}
	}
	public double realValue(){
		if(this.value instanceof Double){
			return ((Double) this.value).doubleValue();
		}else{
			throw new IllegalStateException("Not a Real");
		}
	}
	public int intValue(){
		if(this.value instanceof Integer){
			return ((Integer) this.value).intValue();
		}else{
			throw new IllegalStateException("Not an Integer");
		}
	}
	public boolean isUnlimited(){
		return intValue() == Integer.MAX_VALUE;
	}
	public INakedInstanceSpecification getInstance(){
		if(this.value instanceof INakedInstanceSpecification){
			return((INakedInstanceSpecification) this.value);
		}else{
			throw new IllegalStateException("Not a INakedInstanceSpecification ");
		}
	}
	public IOclContext getOclValue(){
		if(this.value instanceof IOclContext){
			return((IOclContext) this.value);
		}else{
			throw new IllegalStateException("Not a IOclExpression ");
		}
	}
	@Override
	public String getMetaClass(){
		return "valueSpecification";
	}
	public boolean isElementReference(){
		return this.isElementReference;
	}
	public Object getValue(){
		return this.value;
	}
	public boolean isValidOclValue(){
		return isOclValue() && this.value instanceof OclContextImpl;
	}
}
