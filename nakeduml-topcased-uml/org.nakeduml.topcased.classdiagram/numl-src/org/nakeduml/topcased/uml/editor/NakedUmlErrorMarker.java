package org.nakeduml.topcased.uml.editor;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import net.sf.nakeduml.emf.workspace.EmfWorkspace;
import net.sf.nakeduml.metamodel.validation.BrokenElement;
import net.sf.nakeduml.metamodel.validation.BrokenRule;
import net.sf.nakeduml.metamodel.validation.IValidationRule;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EValidator;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.swt.widgets.Display;
import org.nakeduml.eclipse.EmfValidationUtil;

public class NakedUmlErrorMarker implements Runnable{
	public static final String VALIDATION_MARKER_TYPE = "org.eclipse.emf.validation.problem"; //$NON-NLS-1$
	public static final String RULE_ATTRIBUTE = "rule"; //$NON-NLS-1$
	private NakedUmlEclipseContext context;
	private long lastMarked = System.currentTimeMillis() - 10000;
	private Map<EObject,BrokenElement> brokenElements;
	private HashMap<String,IMarker> existingMarkers;
	public NakedUmlErrorMarker(NakedUmlEclipseContext context){
		this.context = context;
	}
	@Override
	public void run(){
		this.existingMarkers = new HashMap<String,IMarker>();
		this.brokenElements = new HashMap<EObject,BrokenElement>();
		Set<String> brokenUris = calcBrokenElements();
		try{
			calcExistingMarkers(brokenUris);
			markFiles();
			lastMarked = System.currentTimeMillis();
		}catch(CoreException e){
			e.printStackTrace();
		}finally{
		}
	}
	public void markFiles(){
		Set<Entry<EObject,BrokenElement>> entrySet = brokenElements.entrySet();
		for(Entry<EObject,BrokenElement> entry:entrySet){
			markFile(entry.getValue(), entry.getKey(), findUmlFile(entry.getKey(), "uml"));
			markFile(entry.getValue(), entry.getKey(), findUmlFile(entry.getKey(), "umldi"));
		}
	}
	public void calcExistingMarkers(Set<String> brokenUris) throws CoreException{
		IMarker[] mrks = context.getUmlDirectory().findMarkers(VALIDATION_MARKER_TYPE, true, IResource.DEPTH_INFINITE);
		for(IMarker marker:mrks){
			String markerKey = markerKey(marker);
			if(brokenUris.contains(markerKey)){
				existingMarkers.put(markerKey, marker);
			}else{
				marker.delete();
			}
		}
	}
	private String markerKey(IMarker marker) throws CoreException{
		return marker.getResource().getName() + ":" + marker.getAttribute(EValidator.URI_ATTRIBUTE) + ":" + marker.getAttribute(RULE_ATTRIBUTE);
	}
	public Set<String> calcBrokenElements(){
		Set<String> brokenUris = new HashSet<String>();
		for(Entry<String,BrokenElement> entry:context.getUmlElementCache().getNakedWorkspace().getErrorMap().getErrors().entrySet()){
			EObject o = findElement(entry.getKey());
			if(o != null && o.eResource() != null){
				for(Entry<IValidationRule,BrokenRule> entry2:entry.getValue().getBrokenRules().entrySet()){
					this.brokenElements.put(o, entry.getValue());
					IValidationRule key = entry2.getKey();
					IFile umlDiFile = findUmlFile(o, "umldi");
					if(umlDiFile != null){
						brokenUris.add(markerKey(umlDiFile, o, key));
						brokenUris.add(markerKey(findUmlFile(o, "uml"), o, key));
					}
				}
			}
		}
		return brokenUris;
	}
	private String markerKey(IFile file,EObject o,IValidationRule key){
		return file.getName() + ":" + EcoreUtil.getURI(o).toString() + ":" + key.name();
	}
	protected void markFile(BrokenElement entry,EObject o,IFile file){
		if(file != null){
			for(BrokenRule brokenRule:entry.getRulesBrokenSince(new Date(lastMarked))){
				try{
					IMarker marker = existingMarkers.get(markerKey(file, o, brokenRule.getRule()));
					if(marker == null){
						marker = file.createMarker(VALIDATION_MARKER_TYPE);
						marker.setAttribute(IMarker.SEVERITY, IMarker.SEVERITY_ERROR);
						marker.setAttribute(IMarker.PRIORITY, IMarker.PRIORITY_HIGH);
						marker.setAttribute(RULE_ATTRIBUTE, brokenRule.getRule().name());
						marker.setAttribute(EValidator.URI_ATTRIBUTE, EcoreUtil.getURI(o).toString());
					}
					String messagePattern = brokenRule.getRule().getMessagePattern();
					String message = EmfValidationUtil.replaceArguments(o, brokenRule, messagePattern);
					marker.setAttribute(IMarker.MESSAGE, message);
				}catch(CoreException e){
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	public IFile findUmlFile(EObject o,String extension){
		IFile file = null;
		try{
			for(IResource r:context.getUmlDirectory().members()){
				if(r.getFileExtension().equals(extension)){
					if(r.getLocation().removeFileExtension().lastSegment().equals(o.eResource().getURI().trimFileExtension().lastSegment())){
						file = (IFile) r;
						break;
					}
				}
			}
		}catch(CoreException e1){
			e1.printStackTrace();
			file = null;
		}
		return file;
	}
	public EObject findElement(String id){
		EObject o = null;
		for(EmfWorkspace emfWorkspace:context.getEmfWorkspaces()){
			// Doesn't matter which workspace it comes from - where after the file really
			o = emfWorkspace.getElementMap().get(id);
			if(o != null){
				break;
			}
		}
		return o;
	}
}
