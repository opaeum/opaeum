package jbpm.jbpm.nodedefinition.interaction;

import jbpm.jbpm.nodedefinition.EISConnection;

import org.apache.log4j.Logger;

public class PrintInteractionSpec implements EISInteractionSpec {

	Logger logger = Logger.getLogger(PrintInteractionSpec.class);
	private static final long serialVersionUID = 2596414373200717127L;

	@SuppressWarnings("unchecked")
	public PrintIndexedRecord<PrintRecord> execute(EISConnection connection, String command, String suffix) {
		PrintIndexedRecord<PrintRecord> indexedRecord = new PrintIndexedRecord<PrintRecord>(command + suffix);
		logger.info("executing " + command + suffix);
		String result = connection.execute(command + suffix);
		String[] rows = result.split("\n");
		for (String row : rows) {
			PrintRecord printRecord = new PrintRecord(row);
			indexedRecord.add(printRecord);
		}
		return indexedRecord;
	}

}
