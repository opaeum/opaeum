package org.opaeum.eclipse;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EValidator;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.swt.widgets.Display;
import org.opaeum.eclipse.context.OpaeumEclipseContext;
import org.opaeum.emf.workspace.EmfWorkspace;
import org.opaeum.metamodel.core.INakedElement;
import org.opaeum.metamodel.validation.BrokenElement;
import org.opaeum.metamodel.validation.BrokenRule;
import org.opaeum.metamodel.validation.IValidationRule;
import org.opaeum.metamodel.workspace.INakedModelWorkspace;

public class OpaeumErrorMarker implements OpaeumSynchronizationListener{
	public static final String VALIDATION_MARKER_TYPE = "org.eclipse.emf.validation.problem"; //$NON-NLS-1$
	public static final String RULE_ATTRIBUTE = "rule"; //$NON-NLS-1$
	// TODO eliminate dependency on context
	private OpaeumEclipseContext context;
	private long lastMarked = 0;
	private Map<EObject,BrokenElement> brokenElements;
	private HashMap<String,IMarker> existingMarkers;
	public OpaeumErrorMarker(OpaeumEclipseContext context){
		super();
		this.context = context;
	}
	public void maybeSchedule(){
		System.out.println("OpaeumErrorMarker.maybeSchedule()");
		if(lastMarked == 0 || lastMarked > System.currentTimeMillis() - 30000){
			Display.getDefault().syncExec(new Runnable(){
				@Override
				public void run(){
					System.out.println("OpaeumErrorMarker.maybeSchedule().new Runnable() {...}.run()");
					
					existingMarkers = new HashMap<String,IMarker>();
					brokenElements = new HashMap<EObject,BrokenElement>();
					Set<String> brokenUris = calcBrokenElements();
					try{
						calcExistingMarkers(brokenUris);
						markFiles();
						lastMarked = System.currentTimeMillis();
					}catch(CoreException e){
						e.printStackTrace();
					}finally{
						if(lastMarked > System.currentTimeMillis() - 30000 && context.getUmlDirectory().exists()){
							Display.getDefault().timerExec(30001, this);
						}
					}
				}
			});
		}
	}
	private void markFiles(){
		Set<Entry<EObject,BrokenElement>> entrySet = brokenElements.entrySet();
		for(Entry<EObject,BrokenElement> entry:entrySet){
			markFiles(entry.getValue(), entry.getKey());
		}
	}
	private void calcExistingMarkers(Set<String> brokenUris) throws CoreException{
		IMarker[] mrks = context.getUmlDirectory().findMarkers(EValidator.MARKER, true, IResource.DEPTH_INFINITE);
		for(IMarker marker:mrks){
			String markerKey = markerKey(marker);
			if(brokenUris.contains(markerKey)){
				existingMarkers.put(markerKey, marker);
				if(marker.getCreationTime() > System.currentTimeMillis() + 20000){
					// Safety net - should rather be recalculated through dependency calculation
					context.getEmfToOpaeumSynchronizer().addEmfChange(URI.createURI((String) marker.getAttribute(EValidator.URI_ATTRIBUTE)));
				}
			}else if(context.isOpen((IFile) marker.getResource())){
				marker.delete();
			}
		}
	}
	private String markerKey(IMarker marker) throws CoreException{
		return marker.getResource().getName() + ":" + marker.getAttribute(EValidator.URI_ATTRIBUTE) + ":" + marker.getAttribute(RULE_ATTRIBUTE);
	}
	private Set<String> calcBrokenElements(){
		Set<String> brokenUris = new HashSet<String>();
		Map<String,BrokenElement> errors = context.getEmfToOpaeumSynchronizer().getNakedWorkspace().getErrorMap().getErrors();
		for(Entry<String,BrokenElement> entry:errors.entrySet()){
			EObject o = findElement(entry.getKey());
			if(o != null && o.eResource() != null){
				for(Entry<IValidationRule,BrokenRule> entry2:entry.getValue().getBrokenRules().entrySet()){
					this.brokenElements.put(o, entry.getValue());
					IValidationRule key = entry2.getKey();
					IFile umlFile = findUmlFile(o);
					if(umlFile != null){
						brokenUris.add(markerKey(umlFile, o, key));
						for(Object object:entry2.getValue().getParameters()){
							if(object instanceof EObject){
								EObject eObject = (EObject) object;
								brokenUris.add(markerKey(findUmlFile(eObject), eObject, key));
							}else if(object instanceof INakedElement){
								EObject eObject = findElement(((INakedElement) object).getId());
								if(eObject != null){
									brokenUris.add(markerKey(findUmlFile(eObject), eObject, key));
								}
							}
						}
					}
				}
			}
		}
		return brokenUris;
	}
	private String markerKey(IFile file,EObject o,IValidationRule key){
		return file.getName() + ":" + EcoreUtil.getURI(o).toString() + ":" + key.name();
	}
	private void markFiles(BrokenElement entry,final EObject o){
		for(final BrokenRule brokenRule:entry.getRulesBrokenSince(new Date(lastMarked))){
			try{
				String messagePattern = brokenRule.getRule().getMessagePattern();
				String message = EmfValidationUtil.replaceArguments(o, brokenRule, messagePattern);
				maybeMarkFile(o, brokenRule, message);
				Object[] parameters = brokenRule.getParameters();
				for(Object object:parameters){
					if(object instanceof INakedElement){
						EObject e = findElement(((INakedElement) object).getId());
						maybeMarkFile(e, brokenRule, message);
					}else if(object instanceof EObject){
						maybeMarkFile((EObject) object, brokenRule, message);
					}
				}
			}catch(CoreException e){
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public void maybeMarkFile(final EObject o,final BrokenRule brokenRule,String message) throws CoreException{
		IFile file = findUmlFile(o);
		if(file != null){
			IMarker marker = existingMarkers.get(markerKey(file, o, brokenRule.getRule()));
			if(marker == null){
				marker = file.createMarker(EValidator.MARKER);
				marker.setAttribute(IMarker.SEVERITY, IMarker.SEVERITY_ERROR);
				marker.setAttribute(IMarker.PRIORITY, IMarker.PRIORITY_HIGH);
				marker.setAttribute(RULE_ATTRIBUTE, brokenRule.getRule().name());
				marker.setAttribute(EValidator.URI_ATTRIBUTE, EcoreUtil.getURI(o).toString());
			}
			marker.setAttribute("BROKEN_ELEMENT_ID", brokenRule.getElementId());
			marker.setAttribute(IMarker.MESSAGE, message);
		}
	}
	private IFile findUmlFile(EObject o){
		IFile file = null;
		String expectedFileName = o.eResource().getURI().lastSegment();
		try{
			IResource[] members = context.getUmlDirectory().members();
			for(IResource r:members){
				if(r instanceof IFile && r.getLocation().lastSegment().equals(expectedFileName)){
					file = (IFile) r;
					break;
				}
			}
		}catch(CoreException e1){
			e1.printStackTrace();
			file = null;
		}
		return file;
	}
	private EObject findElement(String id){
		EObject o = null;
		for(EmfWorkspace emfWorkspace:context.getEmfWorkspaces()){
			// Doesn't matter which workspace it comes from - where after the file really
			o = emfWorkspace.getElement(id);
			if(o != null){
				break;
			}
		}
		return o;
	}
	@Override
	public void synchronizationComplete(INakedModelWorkspace workspace,Set<INakedElement> affectedElements){
		maybeSchedule();
	}
}
