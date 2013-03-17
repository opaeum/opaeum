package org.opaeum.java.metamodel.annotation;

import org.opaeum.java.metamodel.OJBlock;
import org.opaeum.java.metamodel.OJPathName;

public class OJStaticField extends OJAnnotatedField{
	private OJBlock initialization = new OJBlock();
	public OJStaticField(String string,OJPathName ojPathName){
		super(string, ojPathName);
	}
	public OJBlock getInitialization(){
		return initialization;
	}
	@Override
	public String toJavaString(){
		return super.toJavaString() + "\nstatic{\n" + initialization.toJavaString() + "\n}\n";
	}
	@Override
	public boolean isStatic(){
		return true;
	}
}
