package jbpm.jbpm.nodedefinition;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.inject.Inject;

import jbpm.jbpm.nodedefinition.interaction.EISInteractionSpec;
import jbpm.jbpm.nodedefinition.interaction.PrintIndexedRecord;
import jbpm.jbpm.nodedefinition.interaction.PrintInteractionSpec;
import jbpm.jbpm.nodedefinition.interaction.PrintRangeInteractionSpec;
import jbpm.jbpm.nodedefinition.interaction.PrintRecord;
import jbpm.jbpm.rip.MMLCommand;
import jbpm.jbpm.rip.NodeDefinition;
import jbpm.jbpm.rip.RipHelper;

import org.apache.commons.pool.impl.GenericKeyedObjectPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Charsets;
import com.google.common.io.Files;

public class RipHelperImpl implements RipHelper {

	@Inject
	private GenericKeyedObjectPool pool;
	private Logger logger = LoggerFactory.getLogger(RipHelperImpl.class);

	@Override
	public boolean rip(NodeDefinition nodeDefinitionToRip_, Set<MMLCommand> mmlCommands) {
		int count = 0;
		while (true) {
			try {
				logger.info("ripping node definition {}, attempt {}", nodeDefinitionToRip_.getName(), count+1);
				ripNodeDefinition(nodeDefinitionToRip_, mmlCommands);
				break;
			} catch (RuntimeException e) {
				count++;
				if (count > 0) {
					logger.warn(e.getMessage(),e);
					return false;
				}
			}
		}
		return true;
	}

	private void ripNodeDefinition(NodeDefinition nodeDefinitionToRip_, Set<MMLCommand> mmlCommands) {
		List<PrintIndexedRecord<PrintRecord>> result = new ArrayList<PrintIndexedRecord<PrintRecord>>();
		NodeDefinitionWrapper key = new NodeDefinitionWrapper(nodeDefinitionToRip_);
		EISConnection connection = null;
		try {
			connection = (EISConnection) pool.borrowObject(key);
			for (MMLCommand mmlCommand : mmlCommands) {
				executeCommands(result, connection, mmlCommand);
			}
			print(nodeDefinitionToRip_, result);
			nodeDefinitionToRip_.setLastSuccesfulRip(new Date());
		} catch (Exception e) {
			nodeDefinitionToRip_.setLastUnsuccesfulRip(new Date());
			throw new RuntimeException(e);
		} finally {
			try {
				pool.returnObject(key, connection);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	}

	private void executeCommands(List<PrintIndexedRecord<PrintRecord>> result, EISConnection connection, MMLCommand mmlCommand) {
		EISInteractionSpec interactionSpec = null;
		Pattern pattern = Pattern.compile("\\[\\d+,\\d+\\]");
		Matcher matcher = pattern.matcher(mmlCommand.getSuffix());
		if (matcher.find()) {
			int start = Integer.valueOf(matcher.group().substring(1, matcher.group().indexOf(",")));
			int end = Integer.valueOf(matcher.group().substring(matcher.group().indexOf(",") + 1, matcher.group().length() - 1));
			interactionSpec = new PrintRangeInteractionSpec(start, end, matcher.group());
		} else {
			interactionSpec = new PrintInteractionSpec();
		}
		PrintIndexedRecord<PrintRecord> output = (PrintIndexedRecord<PrintRecord>) interactionSpec.execute(connection, mmlCommand.getCommand(),
				mmlCommand.getSuffix());
		result.add(output);
	}

	private File print(NodeDefinition connectionSpec, List<PrintIndexedRecord<PrintRecord>> indexedRecords) throws IOException {
		String dirName = getDir(connectionSpec);
		File dir = new File(dirName);
		dir.mkdirs();
		String fileName = getFileName(connectionSpec);
		File file = new File(dir, fileName);
		logger.info("ripping file to {}", file.getAbsolutePath());
		// Ensure file cleared
		Files.write(new String().getBytes(), file);
		String heading = "CM DUMP BEGIN\nTime stamp: %1$tY-%1$tm-%1$td %1$tH:%1$tM:%1$tS:%1$tL\nNode: %2$s\n";
		Files.append(String.format(heading, System.currentTimeMillis(), connectionSpec.getName()), file, Charsets.UTF_8);

		for (PrintIndexedRecord<PrintRecord> indexedRecord : indexedRecords) {
			Files.append(String.format("Command: %1$s\n", indexedRecord.getRecordName()), file, Charsets.UTF_8);
			Files.append("Response:\n", file, Charsets.UTF_8);
			Files.append(indexedRecord.toString(), file, Charsets.UTF_8);
		}
		return file;
	}

	private String getDir(NodeDefinition connectionSpec) {
		// String dataHome = cmProperties.getString("data.root.dir");
		String dataHome = "/tmp/";
		StringBuilder sb = new StringBuilder();
		sb.append(dataHome);
		sb.append(connectionSpec.getNetworkSoftwareVersion().getNetwork().getApplication().getName());
		sb.append("/");
		sb.append(connectionSpec.getNetworkSoftwareVersion().getNetwork().getName());
		sb.append("/");
		sb.append(connectionSpec.getNetworkSoftwareVersion().getSoftwareVersion().getVendorTech().getVendor().name());
		sb.append("_");
		sb.append(connectionSpec.getNetworkSoftwareVersion().getSoftwareVersion().getVendorTech().getTechnology().name());
		sb.append("/");
		sb.append(connectionSpec.getNetworkSoftwareVersion().getSoftwareVersion().name());
		sb.append("/rip");
		return sb.toString();
	}

	private String getFileName(NodeDefinition connectionSpec) {
		StringBuilder sb = new StringBuilder();
		sb.append(connectionSpec.getName());
		sb.append(".");
		sb.append(connectionSpec.getNodeType().name());
		return sb.toString();
	}

	public void setPool(GenericKeyedObjectPool pool) {
		this.pool = pool;
	}
}
