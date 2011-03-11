package jbpm.jbpm.rip;

import java.util.Set;

public class RipHelperImpl implements RipHelper {
	
	@Override
	public void rip(NodeDefinition nodeDefinitionToRip_, Set<MMLCommand> mmlCommands) {
		System.out.println("Check it out!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		System.out.println("nodeDefinitionToRip.name = " + nodeDefinitionToRip_.getName());
		for (MMLCommand mmlCommand : mmlCommands) {
			System.out.println(mmlCommand);
		}
	}

}