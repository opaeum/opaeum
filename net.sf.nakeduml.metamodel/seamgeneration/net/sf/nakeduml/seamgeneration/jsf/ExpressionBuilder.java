package net.sf.nakeduml.seamgeneration.jsf;

public class ExpressionBuilder {

	private StringBuilder sb;
	private boolean finalized = false;

	private ExpressionBuilder() {
		sb = new StringBuilder();
		sb.append("#{");
	}

	public static ExpressionBuilder instance() {
		return new ExpressionBuilder();
	}
	
	public void finalize() {
		finalized = true;
		sb.append("}");
	}

	public ExpressionBuilder append(String addSecurityCheckEdit) {
		sb.append(addSecurityCheckEdit);
		return this;
	}
	
	public String toString() {
		if (!finalized) {
			finalized=true;
			sb.append("}");
		}
		return sb.toString();
	}
	
	public String getString() {
		return sb.toString().substring(2);
	}
	
	public static void main(String[] args) {
		ExpressionBuilder e = new ExpressionBuilder();
		e.append("pieter");
		String s = e.toString();
		System.out.println(s.substring(2,s.length()-1));
	}

}
