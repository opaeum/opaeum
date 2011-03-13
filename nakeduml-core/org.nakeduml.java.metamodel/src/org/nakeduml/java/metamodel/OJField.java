package org.nakeduml.java.metamodel;

import java.util.Map;

import org.nakeduml.java.metamodel.generated.OJFieldGEN;




public class OJField extends OJFieldGEN {
	/******************************************************
	 * The constructor for this classifier.
	*******************************************************/	
	public OJField() {
		super();
		setVisibility(OJVisibilityKind.PRIVATE);
	}

	public String toJavaString(){
		String result = "";
		if (this.getOwner() != null) { // field is part of block statement
			result = result + visToJava(this);
		}
		if (result.length() > 0) result = result + " ";
		result = result + getType().getCollectionTypeName();
		result = result + " " + getName();
		if (getInitExp() != null && !getInitExp().equals("")){
			result = result + " = " + getInitExp();
		}
		result = result + ";";
		if (!getComment().equals("")){
			result = result + "\t// " + getComment();
		}
		return result;
	}
	
	public OJField getDeepCopy() {
		OJField copy = new OJField();
		copyDeepInfoInto(copy);
		return copy;
	}
	
	public void copyDeepInfoInto(OJField copy) {
		super.copyDeepInfoInto(copy);
		copy.setInitExp(getInitExp());
		if ( getType() != null ) {
			copy.setType(getType().getDeepCopy());
		}		
	}

	public void renameAll(Map<String, OJPathName> renamePathNames, String suffix) {
		getType().renameAll(renamePathNames, suffix);
		//This part sucks as it is just a string so...:-(
		String init = getInitExp();
		for(OJPathName pathName:renamePathNames.values()) {
			if(init!=null && init.length()>0 && getInitExp().contains("<"+pathName.getLast()+">")) {
				setInitExp(getInitExp().replace("<"+pathName.getLast()+">", "<"+pathName.getLast()+suffix+">"));
			}
			//Admin newInstance= new Admin()
			if(init!=null && init.length()>0 && getInitExp().startsWith(pathName.getLast()+" ")) {
				setInitExp(getInitExp().replace(pathName.getLast()+" ", pathName.getLast()+suffix+" "));
			}
			if(init!=null && init.length()>0 && getInitExp().contains(" "+pathName.getLast()+"(")) {
				setInitExp(getInitExp().replace(" "+pathName.getLast()+"(", " "+pathName.getLast()+suffix+"("));
			}
			//copyState((Group)this,result);
			if(init!=null && init.length()>0 && getInitExp().contains("("+pathName.getLast()+")")) {
				setInitExp(getInitExp().replace("("+pathName.getLast()+")", "("+pathName.getLast()+suffix+")"));
			}
		}		
		
	}	
}