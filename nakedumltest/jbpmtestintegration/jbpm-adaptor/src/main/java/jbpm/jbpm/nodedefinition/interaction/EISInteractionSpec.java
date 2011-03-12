package jbpm.jbpm.nodedefinition.interaction;

import javax.resource.cci.InteractionSpec;

import jbpm.jbpm.nodedefinition.EISConnection;

public interface EISInteractionSpec extends InteractionSpec {
	PrintIndexedRecord<PrintRecord> execute(EISConnection connection, String command, String suffix);
}
