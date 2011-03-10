package org.nakeduml.java.metamodel;

import java.util.Map;

import org.nakeduml.java.metamodel.generated.OJSimpleStatementGEN;


public class OJSimpleStatement extends OJSimpleStatementGEN {

/*********************************************************************
 * The constructor
 ********************************************************************/
	public OJSimpleStatement() {
		super();
	}
	public OJSimpleStatement(String expression) {
		super();
		this.setExpression(expression);
	}

/*********************************************************************
 * The operations from the model
 ********************************************************************/
	public OJSimpleStatement getCopy() {
		OJSimpleStatement result = new OJSimpleStatement();
		result.setExpression(this.getExpression());
		return result;
	}
	public String toJavaString() {
		String result = "";
		if (getExpression().length() != 0 ) result = getExpression();
//		if (result.length() > 0 && !(result.charAt(result.length()-1) == '}')) {
		if (result.length() > 0) {
			result = result + ";";
		}
		return result; 	
	}
	
	public OJSimpleStatement getDeepCopy() {
		OJSimpleStatement copy = new OJSimpleStatement();
		copyDeepInfoInto(copy);
		return copy;
	}
	
	public void copyDeepInfoInto(OJSimpleStatement copy) {
		super.copyDeepInfoInto(copy);
		copy.setExpression(this.getExpression());
	}	
	
	public void renameAll(Map<String, OJPathName> renamePathNames, String newName) {
		for(OJPathName pathName:renamePathNames.values()) {
			if(getExpression().contains("<"+pathName.getLast()+">")) {
				setExpression(getExpression().replace("<"+pathName.getLast()+">", "<"+pathName.getLast()+newName+">"));
			}
			//Admin newInstance= new Admin()
			if(getExpression().startsWith(pathName.getLast()+" ")) {
				setExpression(getExpression().replace(pathName.getLast()+" ", pathName.getLast()+newName+" "));
			}
			if(getExpression().contains(" "+pathName.getLast()+"()")) {
				setExpression(getExpression().replace(" "+pathName.getLast()+"()", " "+pathName.getLast()+newName+"()"));
			}
			//copyState((Group)this,result);
			if(getExpression().contains("("+pathName.getLast()+")")) {
				setExpression(getExpression().replace("("+pathName.getLast()+")", "("+pathName.getLast()+newName+")"));
			}
		}
	}	
/*********************************************************************
 * The getters and setters
 ********************************************************************/

/*********************************************************************
 * Some utility operations
 ********************************************************************/

/*********************************************************************
 * Extra operations that implement the OCL expressions used
 ********************************************************************/

}