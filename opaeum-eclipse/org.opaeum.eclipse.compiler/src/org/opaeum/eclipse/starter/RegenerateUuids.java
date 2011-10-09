package org.opaeum.eclipse.starter;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.Profile;
import org.opaeum.eclipse.NakedUmlElementLinker;
import org.opaeum.eclipse.NakedUmlElementLinker.EmfUmlElementLinker;
import org.opaeum.eclipse.OpaeumEclipsePlugin;
import org.opaeum.eclipse.ProgressMonitorTransformationLog;
import org.opaeum.eclipse.context.OpaeumEclipseContext;
import org.opaeum.emf.extraction.EmfElementVisitor;
import org.opaeum.emf.load.EmfWorkspaceLoader;
import org.opaeum.emf.workspace.EmfWorkspace;
import org.opaeum.feature.OpaeumConfig;
import org.opaeum.feature.visit.VisitBefore;

public class RegenerateUuids extends AbstractOpaeumAction{
	public static final class LinkingVisitor extends EmfElementVisitor{
		private LinkingVisitor(){
		}
		@SuppressWarnings("unchecked")
		@VisitBefore(matchSubclasses = true)
		public void element(Element o){
			EList<EStructuralFeature> s = o.eClass().getEAllStructuralFeatures();
			for(EStructuralFeature sf:s){
				if(sf.isMany()){
					List<Object> list = new ArrayList<Object>((List<Object>) o.eGet(sf));
					for(Object value:list){
						ENotificationImpl notification = new ENotificationImpl((InternalEObject) o, Notification.ADD, sf, null, value);
						EmfUmlElementLinker linker = new EmfUmlElementLinker(notification);
						linker.doSwitch(o);
					}
				}else{
					Object value = o.eGet(sf);
					if(value != null){
						// We're only interested in actual values as we are adding
						ENotificationImpl notification = buildNotification(o, sf, value);
						EmfUmlElementLinker linker = new EmfUmlElementLinker(notification);
						linker.doSwitch(o);
					}
				}
			}
		}
		private ENotificationImpl buildNotification(Element o,EStructuralFeature sf,Object value){
			int featureId = ((InternalEObject) o).eBaseStructuralFeatureID(sf.getFeatureID(), sf.getContainerClass());
			if(value instanceof Boolean){
				return new ENotificationImpl((InternalEObject) o, Notification.SET, featureId, ((Boolean) value).booleanValue(), ((Boolean) value).booleanValue());
			}else if(value instanceof Integer){
				return new ENotificationImpl((InternalEObject) o, Notification.SET, featureId, ((Integer) value).intValue(), ((Integer) value).intValue());
			}else if(value instanceof Float){
				return new ENotificationImpl((InternalEObject) o, Notification.SET, featureId, ((Float) value).floatValue(), ((Float) value).floatValue());
			}else if(value instanceof Double){
				return new ENotificationImpl((InternalEObject) o, Notification.SET, featureId, ((Double) value).doubleValue(), ((Double) value).doubleValue());
			}else if(value instanceof Short){
				return new ENotificationImpl((InternalEObject) o, Notification.SET, featureId, ((Short) value).shortValue(), ((Short) value).shortValue());
			}
			return new ENotificationImpl((InternalEObject) o, Notification.SET, sf, null, value);
		}
		@Override
		protected int getThreadPoolSize(){
			return 1;
		}
	}
	public static final class UuidRemover extends EmfElementVisitor{
		public UuidRemover(EmfWorkspace currentEmfWorkspace){
			super();
			this.currentEmfWorkspace = currentEmfWorkspace;
		}
		protected EmfWorkspace currentEmfWorkspace;
		@VisitBefore(matchSubclasses = true)
		public void element(Element e){
			Set<String> keywords = new HashSet<String>();
			if(!(e instanceof Model || e instanceof Profile)){
				e.getEAnnotations().clear();
//				for(EAnnotation a:new ArrayList<EAnnotation>(e.getEAnnotations())){
//					if(a.getSource().contains("opaeum") || a.getSource().contains("opeum") || a.getSource().contains("nakeduml")){
//						// for(Entry<String,String> entry:a.getDetails().entrySet()){
//						// if(entry.getValue() == null || entry.getValue().trim().length() == 0){
//						// keywords.add(entry.getKey());
//						// }
//						// }
//						e.getEAnnotations().remove(a);
//					}
//				}
			}
			// populateAnnotation(e, keywords);
		}
		// private void populateAnnotation(Element o,Set<String> keywords){
		// // FIrst generate the ID appropriately
		// currentEmfWorkspace.getId(o);
		// // Now the annotation already exists
		// EAnnotation ann = StereotypesHelper.getNumlAnnotation(o);
		// for(String string:keywords){
		// ann.getDetails().put(string, "");
		// }
		// }
		@Override
		protected int getThreadPoolSize(){
			return 1;
		}
	}
	public RegenerateUuids(IStructuredSelection selection2){
		super(selection2, "Regenerate Opaeum UUIDS");
	}
	@Override
	public void run(){
		final IContainer folder = (IContainer) selection.getFirstElement();
		final OpaeumEclipseContext currentContext = OpaeumEclipseContext.findOrCreateContextFor(folder);
		new Job("Regenerating UUIDS"){
			@Override
			protected IStatus run(IProgressMonitor monitor){
				try{
					monitor.beginTask("Regenerating UUIDS", 7);
					monitor.subTask("Removing UUIDS");
					File dir = currentContext.getUmlDirectory().getLocation().toFile();
					OpaeumConfig cfg = currentContext.getConfig();
					ProgressMonitorTransformationLog log = new ProgressMonitorTransformationLog(monitor, 3);
					final EmfWorkspace workspace = EmfWorkspaceLoader.loadDirectory(new ResourceSetImpl(), dir, cfg, log);
					// No cache listening - just linking
					NakedUmlElementLinker linker = new NakedUmlElementLinker();
					workspace.getResourceSet().eAdapters().add(linker);
					UuidRemover v1 = new UuidRemover(workspace);
					for(Element element:workspace.getOwnedElements()){
						v1.visitRecursively(element);
					}
					monitor.subTask("Generating new UUIDS");
					// LinkingVisitor v2 = new LinkingVisitor();
					// for(Element element:workspace.getOwnedElements()){
					// v2.visitRecursively(element);
					// }
					monitor.worked(1);
					monitor.subTask("Saving All Models");
					workspace.saveAll();
					workspace.getResourceSet().eAdapters().remove(linker);
					monitor.worked(1);
					new ClearOpaeumCacheACtion(selection).run();
				}finally{
					monitor.done();
				}
				return new Status(IStatus.OK, OpaeumEclipsePlugin.getId(), "Regenerated");
			}
		}.schedule();
	}
}
