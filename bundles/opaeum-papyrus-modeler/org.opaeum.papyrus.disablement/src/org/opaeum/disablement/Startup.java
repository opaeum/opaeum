package org.opaeum.disablement;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.ui.IStartup;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.activities.IActivityManager;
import org.eclipse.ui.activities.IWorkbenchActivitySupport;
import org.eclipse.ui.handlers.HandlerUtil;

public class Startup implements IStartup {

	@Override
	public void earlyStartup() {
		new Thread() {
			@Override
			public void run() {
				try {
					sleep(10000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				IWorkbenchActivitySupport activitySupport = PlatformUI
						.getWorkbench().getActivitySupport();
				IActivityManager activityManager = activitySupport
						.getActivityManager();
				if(activityManager.getActivity("org.opaeum.papyrus.disablement.activity").isDefined()){
					System.out.println();
					
				}else{
					System.out.println();
				}
			}
		}.start();
	}
}
