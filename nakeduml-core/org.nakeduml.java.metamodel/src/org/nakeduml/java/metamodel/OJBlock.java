package org.nakeduml.java.metamodel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.nakeduml.java.metamodel.generated.OJBlockGEN;
import org.nakeduml.java.metamodel.utilities.JavaUtil;



public class OJBlock extends OJBlockGEN {
	
	/******************************************************
	 * The constructor for this classifier.
	*******************************************************/	
	public OJBlock() {
		super();
	}

	/** Constructor for OJBlock
	 * 
	 * @param name 
	 * @param comment 
	 */
	public OJBlock(String name, String comment) {
//		super(name, comment);
	}
	
	public void addToStatements(String str){
		if (str.length() == 0) return;
		OJSimpleStatement stat = new OJSimpleStatement();
		stat.setExpression(str);
		this.addToStatements(stat);
	}
	
	public String toJavaString(){
		String result = "";
		if (getComment().length() != 0) result = "/* " + getComment() + "*/\n";
		if (!getLocals().isEmpty()) {
			result = result + JavaUtil.collectionToJavaString(getLocals(), "\n") + "\n";	
		}
		result = result + JavaUtil.collectionToJavaString(getStatements(), "\n");
		return result;
	}
	/**
	 * @return
	 */
	public OJBlock getCopy() {
		OJBlock newBody = new OJBlock();
		List<OJStatement> stats = new ArrayList<OJStatement>(this.getStatements());
		newBody.setStatements(stats);
		return newBody;
	}
	
	public OJBlock getDeepCopy() {
		OJBlock copy = new OJBlock();
		copyDeepInfoInto(copy);
		return copy;
	}
	
	public void copyDeepInfoInto(OJBlock copy) {
		for(OJStatement statement:getStatements()) {
			copy.addToStatements(statement.getDeepCopy());
		}
		for (OJField ojField : getLocals()) {
			copy.addToLocals(ojField.getDeepCopy());
		}		
	}	
	
	public void renameAll(Map<String, OJPathName> renamePathNames, String newName) {
		for(OJStatement statement:getStatements()) {
			statement.renameAll(renamePathNames, newName);
		}
		for (OJField ojField : getLocals()) {
			ojField.renameAll(renamePathNames, newName);
		}
	}

	public OJStatement findStatement(String name) {
		for(OJStatement statement:getStatements()) {
			if (statement.getName().equals(name)) {
				return statement;
			}
		}
		return null;
		
	}	
	
}