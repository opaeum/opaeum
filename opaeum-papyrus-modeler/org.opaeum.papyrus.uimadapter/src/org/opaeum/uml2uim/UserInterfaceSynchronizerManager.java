package org.opaeum.uml2uim;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IStartup;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.OpaqueAction;
import org.eclipse.uml2.uml.Operation;
import org.opaeum.eclipse.EmfToOpaeumSynchronizer;
import org.opaeum.eclipse.context.OpaeumEclipseContext;
import org.opaeum.eclipse.context.OpaeumEclipseContextListener;
import org.opaeum.eclipse.context.OpenUmlFile;
import org.opaeum.metamodel.workspace.ModelWorkspace;

public class UserInterfaceSynchronizerManager implements IStartup,Runnable{
	Map<OpaeumEclipseContext,UserInterfaceSynchronizer> synchronizers = new HashMap<OpaeumEclipseContext,UserInterfaceSynchronizerManager.UserInterfaceSynchronizer>();
	public static class UserInterfaceSynchronizer implements OpaeumEclipseContextListener{
		OpaeumEclipseContext ctx;
		Set<Element> affectedElements = new HashSet<Element>();
		@Override
		public void synchronizationComplete(OpenUmlFile workspace,Set<Element> affectedElements){
			this.affectedElements.addAll(affectedElements);
		}
		@Override
		public void onSave(IProgressMonitor monitor,OpenUmlFile file){
			for(Element e:affectedElements){
				Element element = e;
				while(element != null){
					if(element instanceof OpaqueAction || element instanceof org.eclipse.uml2.uml.Class || element instanceof Operation){
						SynchronizeAction.doSynchronize(element, file.getEmfWorkspace());
						if(!(element instanceof Operation)){
							break;
						}
					}
					element = element.getOwner();
				}
			}
			affectedElements.clear();
		}
		@Override
		public void onClose(OpenUmlFile openUmlFile){
			
		}
	}
	public void run(){
		try{
			// Continuously associate new contexts with transformation processes
			OpaeumEclipseContext currentContext = OpaeumEclipseContext.getCurrentContext();
			if(currentContext != null && !currentContext.isLoading()){
				for(OpenUmlFile openUmlFile:currentContext.getEditingContexts()){
					if(synchronizers.get(currentContext) == null){
						UserInterfaceSynchronizer uis = new UserInterfaceSynchronizer();
						synchronizers.put(currentContext, uis);
						openUmlFile.addContextListener(uis);
					}
				}
			}
		}catch(Throwable e){
			e.printStackTrace();
			// Activator.getDefault().getLog().log(new Status(Status.WARNING, Activator.PLUGIN_ID, e.getMessage(), e));
		}finally{
			try{
				Display.getDefault().timerExec(10000, this);
			}catch(Throwable e){
				e.printStackTrace();
				// Activator.getDefault().getLog().log(new Status(Status.WARNING, Activator.PLUGIN_ID, e.getMessage(), e));
			}
		}
	}
	@Override
	public void earlyStartup(){
		EmfToOpaeumSynchronizer.schedule(new Runnable(){
			@Override
			public void run(){
				Display.getDefault().syncExec(UserInterfaceSynchronizerManager.this);
			}
		}, 5000);
	}
}
