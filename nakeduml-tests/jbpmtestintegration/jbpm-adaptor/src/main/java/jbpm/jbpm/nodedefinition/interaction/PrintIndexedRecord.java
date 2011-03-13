package jbpm.jbpm.nodedefinition.interaction;

import java.util.ArrayList;
import java.util.Iterator;

import javax.resource.cci.IndexedRecord;

@SuppressWarnings("rawtypes")
public class PrintIndexedRecord<E> extends ArrayList implements IndexedRecord {

	private static final long serialVersionUID = -3721301406837471818L;
	private String recordName;
	private String recordShortDescription;

	public PrintIndexedRecord(String recordName) {
		super();
		this.recordName = recordName;
	}

	@Override
	public String getRecordName() {
		return this.recordName;
	}

	@Override
	public String getRecordShortDescription() {
		return this.recordShortDescription;
	}

	@Override
	public void setRecordName(String arg0) {
		this.recordName = arg0;
	}

	@Override
	public void setRecordShortDescription(String arg0) {
		recordShortDescription = arg0;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		PrintRecord first = (PrintRecord) get(0);
		//Insert the command ripped into the response if it is not there.
		if (first!=null && !first.toString().startsWith(getRecordName())) {
			sb.append(getRecordName());
			sb.append("\n");
		}
		@SuppressWarnings("unchecked")
		Iterator<PrintRecord> iter = iterator();
		while (iter.hasNext()) {
			PrintRecord printRecord = iter.next();
			sb.append(printRecord.toString());
			sb.append("\n");
		}
		return sb.toString();
	}	
}
