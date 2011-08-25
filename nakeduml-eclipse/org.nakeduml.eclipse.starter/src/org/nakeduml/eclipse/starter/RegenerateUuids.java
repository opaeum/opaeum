package org.nakeduml.eclipse.starter;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import net.sf.nakeduml.emf.extraction.EmfElementVisitor;
import net.sf.nakeduml.emf.extraction.StereotypesHelper;
import net.sf.nakeduml.emf.load.EmfWorkspaceLoader;
import net.sf.nakeduml.emf.workspace.EmfWorkspace;
import net.sf.nakeduml.feature.NakedUmlConfig;
import net.sf.nakeduml.feature.visit.VisitBefore;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.Profile;
import org.eclipse.uml2.uml.UMLPackage;
import org.nakeduml.eclipse.ProgressMonitorTransformationLog;
import org.nakeduml.topcased.uml.NakedUmlPlugin;
import org.nakeduml.topcased.uml.editor.EclipseEmfResourceHelper;
import org.nakeduml.topcased.uml.editor.NakedUmlEclipseContext;
import org.nakeduml.topcased.uml.editor.NakedUmlEditor;
import org.nakeduml.topcased.uml.editor.NakedUmlElementLinker;
import org.nakeduml.topcased.uml.editor.NakedUmlElementLinker.EmfUmlElementLinker;

public class RegenerateUuids extends AbstractOpiumAction{
	public static final class LinkingVisitor extends EmfElementVisitor{
		private LinkingVisitor(){
		}
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
			if(sf.getFeatureID()==UMLPackage.PARAMETER__IS_EXCEPTION){
				System.out.println();
			}
			return new ENotificationImpl((InternalEObject) o, Notification.SET, sf, null, value);
		}
	}
	public static final class UuidRemover extends EmfElementVisitor{
		public UuidRemover(EmfWorkspace currentEmfWorkspace){
			super();
			this.currentEmfWorkspace = currentEmfWorkspace;
		}
		private EmfWorkspace currentEmfWorkspace;
		@VisitBefore(matchSubclasses = true)
		public void element(Element e){
			Set<String> keywords = new HashSet<String>();
			for(EAnnotation a:new ArrayList<EAnnotation>(e.getEAnnotations())){
				if(a.getSource().contains("nakeduml")){
					for(Entry<String,String> entry:a.getDetails().entrySet()){
						if(entry.getValue() == null || entry.getValue().trim().length() == 0){
							keywords.add(entry.getKey());
						}
					}
					e.getEAnnotations().remove(a);
				}
			}
			populateAnnotation(e, keywords);
		}
		private void populateAnnotation(Element o,Set<String> keywords){
			// FIrst generate the ID appropriately
			currentEmfWorkspace.getId(o);
			// Now the annotation already exists
			EAnnotation ann = StereotypesHelper.getNumlAnnotation(o);
			for(String string:keywords){
				ann.getDetails().put(string, "");
			}
		}
	}
	public RegenerateUuids(IStructuredSelection selection2){
		super(selection2, "Regenerate Opium UUIDS");
	}
	@Override
	public void run(){
		final IContainer folder = (IContainer) selection.getFirstElement();
		final NakedUmlEclipseContext currentContext = NakedUmlEditor.getNakedUmlEclipseContextFor(folder);
		new Job("Regenerating UUIDS"){
			@Override
			protected IStatus run(IProgressMonitor monitor){
				try{
					monitor.beginTask("Regenerating UUIDS", 7);
					NakedUmlPlugin.saveAllOpenFilesIn(currentContext, new SubProgressMonitor(monitor, 1));
					monitor.subTask("Removing UUIDS");
					File dir = currentContext.getUmlDirectory().getLocation().toFile();
					NakedUmlConfig cfg = currentContext.getUmlElementCache().getConfig();
					ProgressMonitorTransformationLog log = new ProgressMonitorTransformationLog(monitor, 3);
					final EmfWorkspace workspace = EmfWorkspaceLoader.loadDirectory(new ResourceSetImpl(), dir, cfg, log);
					workspace.setResourceHelper(new EclipseEmfResourceHelper());
					// No cache listening - just linking
					NakedUmlElementLinker linker = new NakedUmlElementLinker();
					workspace.getResourceSet().eAdapters().add(linker);
					UuidRemover v1 = new UuidRemover(workspace);
					for(Element element:workspace.getOwnedElements()){
						v1.visitRecursively(element);
					}
					monitor.subTask("Generating new UUIDS");
					LinkingVisitor v2 = new LinkingVisitor();
					for(Element element:workspace.getOwnedElements()){
						v2.visitRecursively(element);
					}
					monitor.worked(1);
					monitor.subTask("Saving All Models");
					workspace.saveAll();
					workspace.getResourceSet().eAdapters().remove(linker);
					monitor.worked(1);
					new ClearOpiumCacheACtion(selection).run();
				}finally{
					monitor.done();
				}
				return new Status(IStatus.OK, NakedUmlPlugin.getId(), "Regenerated");
			}
		}.schedule();
	}
}
