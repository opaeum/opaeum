package org.nakeduml.topcased.uml.editor;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

import javax.print.attribute.standard.MediaSize.ISO;

import net.sf.nakeduml.emf.workspace.EmfWorkspace;
import net.sf.nakeduml.emf.workspace.UmlElementCache;
import net.sf.nakeduml.feature.NakedUmlConfig;
import net.sf.nakeduml.metamodel.core.internal.StereotypeNames;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.Profile;
import org.eclipse.uml2.uml.Stereotype;
import org.nakeduml.eclipse.ApplyProfileAction;
import org.nakeduml.eclipse.NakedUmlConfigDialog;
import org.nakeduml.eclipse.NakedUmlEclipsePlugin;
import org.nakeduml.eclipse.ProgressMonitorTransformationLog;
import org.nakeduml.topcased.uml.NakedUmlPlugin;

public class NakedUmlEclipseContext{
	private Shell shell;
	private UmlElementCache umlElementMap;
	private IFile umlFile;
	private ResourceSet resourceSet;
	private boolean isOpen = false;
	private List<NakedUmlContextListener> listeners = new ArrayList<NakedUmlContextListener>();
	public NakedUmlEclipseContext(Shell shell,ResourceSet resourceSet){
		super();
		isOpen = true;
		this.shell = shell;
		this.resourceSet = resourceSet;
	}
	public IFile getUmlFile(){
		return umlFile;
	}
	public UmlElementCache getUmlElementCache(){
		return umlElementMap;
	}
	public boolean startSynch(final IFile file) throws IOException{
		this.umlFile = file;
		java.io.File file2 = new java.io.File(file.getLocation().toFile().getParentFile(), "nakeduml.properties");
		final NakedUmlConfig cfg;
		if(!file2.exists()){
			NakedUmlConfigDialog dlg = new NakedUmlConfigDialog(shell,file2);
			if(dlg.open()==SWT.OK){
				cfg=dlg.getConfig();
			}else{
				return false;
			}
		}else{
			//Load classes
			NakedUmlEclipsePlugin.getDefault();
			cfg=new NakedUmlConfig(file2);
		}
		new Job("Loading NakedUML Model"){
			@Override
			protected IStatus run(final IProgressMonitor monitor){
				monitor.beginTask("Loading NakedUML Model", 30);
				long start = System.currentTimeMillis();
				try{
					monitor.subTask("Loading EMF Resources");
					umlElementMap = new UmlElementCache(new EclipseEmfResourceHelper(), resourceSet){
						@Override
						public void emfWorkspaceLoaded(EmfWorkspace w){
							monitor.worked(15);
							for(Package pkg:w.getPrimaryModels()){
								if(pkg instanceof Model){
									Model model = (Model) pkg;
									Profile pf = ApplyProfileAction.applyNakedUmlProfile(model);
									Stereotype st = pf.getOwnedStereotype(StereotypeNames.PACKAGE);
									if(!model.isStereotypeApplied(st)){
										model.applyStereotype(st);
									}
									if(model.getValue(st, "mappedImplementationPackage") == null){
										model.setValue(st, "mappedImplementationPackage", cfg.getMavenGroupId() + "." + model.getName().toLowerCase());
									}
									try{
										model.eResource().save(new HashMap());
									}catch(IOException e){
										e.printStackTrace();
									}
								}
							}
						}
					};
					umlElementMap.startSynchronizing(file.getLocation().toFile(), cfg, new ProgressMonitorTransformationLog(monitor));
				}catch(Exception e){
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally{
					monitor.done();
				}
				System.out.println("NakedUmlEclipseContext.startSynch(...).new Job() {...}.run() took " + (System.currentTimeMillis() - start));
				return new Status(IStatus.OK, NakedUmlPlugin.getId(), "NakedUML Model loaded");
			}
		}.schedule();
		return true;
	}
	public boolean isOpen(){
		return isOpen;
	}
	public void onSave(IProgressMonitor monitor){
		for(NakedUmlContextListener l:listeners){
			l.onSave(monitor);
		}
	}
	public void onClose(boolean save){
		for(NakedUmlContextListener l:listeners){
			l.onClose(save);
		}
		this.isOpen = false;
		if(umlElementMap != null){
			umlElementMap.stop();
		}
	}
	public void addContextListener(NakedUmlContextListener l){
		this.listeners.add(l);
	}
}
