package jbpm.jbpm.nodedefinition.interaction;

import jbpm.jbpm.nodedefinition.EISConnection;

import org.apache.log4j.Logger;

public class PrintRangeInteractionSpec implements EISInteractionSpec {

	private static final long serialVersionUID = -5574576522851012958L;
	Logger logger = Logger.getLogger(PrintRangeInteractionSpec.class);
	private int start;
	private int end;
	private String rangeGroup;
	
	public PrintRangeInteractionSpec(int start, int end, String rangeGroup) {
		super();
		this.start = start;
		this.end = end;
		this.rangeGroup = rangeGroup;
	}

	@SuppressWarnings("unchecked")
	public PrintIndexedRecord<PrintRecord> execute(EISConnection connection, String command, String suffix) {
		String rangeSuffix;
		PrintIndexedRecord<PrintRecord> indexedRecord = new PrintIndexedRecord<PrintRecord>(command + suffix);
		for (int i = start; i < end; i++) {
			rangeSuffix = suffix.replace(rangeGroup, String.valueOf(i));
			logger.info("executing " + command + rangeSuffix);
			String result = connection.execute(command + rangeSuffix);
			String[] rows = result.split("\n");
			for (String row : rows) {
				PrintRecord printRecord = new PrintRecord(row);
				indexedRecord.add(printRecord);
			}
		}
		return indexedRecord;
	}

}
