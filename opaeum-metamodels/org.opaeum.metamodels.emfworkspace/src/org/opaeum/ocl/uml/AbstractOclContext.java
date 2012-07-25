package org.opaeum.ocl.uml;

import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.ocl.ParserException;
import org.eclipse.ocl.helper.OCLHelper;
import org.eclipse.ocl.uml.OCLExpression;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Constraint;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Property;

public abstract class AbstractOclContext extends AdapterImpl{
	protected String expressionString;
	protected OCLHelper<Classifier,Operation,Property,Constraint> helper;
	protected OCLExpression expression;
	protected ParserException parseException;

	public AbstractOclContext(){
		super();
	}
	protected void reParse(){
		try{
			this.expressionString = retrieveBody();
			this.parseException=null;
			this.expression = (OCLExpression) helper.createQuery(expressionString);
		}catch(ParserException e){
			this.parseException = e;
		}
	}
	protected abstract String retrieveBody();	public String getExpressionString(){
		return expressionString;
	}

	public OCLHelper<Classifier,Operation,Property,Constraint> getHelper(){
		return helper;
	}

	public OCLExpression getExpression(){
		return expression;
	}

	public boolean hasErrors(){
		if(parseException != null){
			return false;
		}else if(helper.getProblems() == null){
			return false;
		}else{
			return helper.getProblems().getSeverity()==Diagnostic.ERROR;
		}
	}
}