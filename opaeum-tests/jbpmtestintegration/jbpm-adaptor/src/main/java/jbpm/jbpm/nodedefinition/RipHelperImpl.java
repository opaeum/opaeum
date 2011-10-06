package jbpm.jbpm.nodedefinition;

import java.util.Date;
import java.util.Set;

import jbpm.jbpm.rip.MMLCommand;
import jbpm.jbpm.rip.NodeDefinition;
import jbpm.jbpm.rip.RipHelper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RipHelperImpl implements RipHelper {

	private Logger logger = LoggerFactory.getLogger(RipHelperImpl.class);

	@Override
	public boolean rip(NodeDefinition nodeDefinitionToRip_, Set<MMLCommand> mmlCommands) {
		int count = 0;
		logger.info("ripping node definition {}, attempt {}", nodeDefinitionToRip_.getName(), count + 1);
		if (nodeDefinitionToRip_.getSshTunnelSpec().getHost().equals("x.x.x.x")) {
			nodeDefinitionToRip_.setLastUnsuccesfulRip(new Date());
			return false;
		} else {
			nodeDefinitionToRip_.setLastSuccesfulRip(new Date());
			return true;
		}
	}

}
