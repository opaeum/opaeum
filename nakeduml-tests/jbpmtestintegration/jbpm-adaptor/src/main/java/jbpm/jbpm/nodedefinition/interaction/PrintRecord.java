package jbpm.jbpm.nodedefinition.interaction;

import javax.resource.cci.Record;

public class PrintRecord implements Record {

	private static final long serialVersionUID = -8306693656872213754L;
	protected String original;
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
	
	public PrintRecord(String original) {
		super();
		this.original = original;
	}

	public String getOriginal() {
		return original;
	}

	public void setOriginal(String original) {
		this.original = original;
	}
	
	@Override
	public String getRecordName() {
		return "MGCEPRecord";
	}

	@Override
	public String getRecordShortDescription() {
		return "A record representing a row returned by the MGCEP command";
	}

	@Override
	public void setRecordName(String arg0) {
		
	}

	@Override
	public void setRecordShortDescription(String arg0) {
		
	}

	public String toString() {
		return getOriginal();
	}
}