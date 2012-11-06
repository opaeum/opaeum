package org.opaeum.rap.runtime.internal.startup;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.activities.IMutableActivityManager;
import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.eclipse.ui.application.WorkbenchAdvisor;
import org.eclipse.ui.application.WorkbenchWindowAdvisor;
import org.opaeum.rap.runtime.Constants;

public class OpaeumWorkbenchAdvisor extends WorkbenchAdvisor {

	public void preStartup() {
		IWorkbench workbench = PlatformUI.getWorkbench();
		IMutableActivityManager activitySupport = workbench
				.getActivitySupport().createWorkingCopy();
		Set<String> enabledActivityIds = new HashSet<String>();
		enabledActivityIds.add("org.opaeum.rap.runtime");
		activitySupport.setEnabledActivityIds(enabledActivityIds);
	}

	public String getInitialWindowPerspectiveId() {
		return Constants.PERSPECTIVE_ID;
	}

	public WorkbenchWindowAdvisor createWorkbenchWindowAdvisor(
			final IWorkbenchWindowConfigurer configurer) {
		return new OpaeumWorkbenchWindowAdvisor(configurer);
	}
}
