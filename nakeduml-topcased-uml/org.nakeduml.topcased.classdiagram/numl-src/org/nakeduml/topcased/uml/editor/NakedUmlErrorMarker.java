package org.nakeduml.topcased.uml.editor;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import net.sf.nakeduml.emf.workspace.EmfWorkspace;
import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.metamodel.validation.BrokenElement;
import net.sf.nakeduml.metamodel.validation.BrokenRule;
import net.sf.nakeduml.metamodel.validation.IValidationRule;
import net.sf.nakeduml.metamodel.workspace.INakedModelWorkspace;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EValidator;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.swt.widgets.Display;
import org.nakeduml.eclipse.EmfValidationUtil;

public class NakedUmlErrorMarker implements NakedUmlSynchronizationListener{
	public static final String VALIDATION_MARKER_TYPE = "org.eclipse.emf.validation.problem"; //$NON-NLS-1$
	public static final String RULE_ATTRIBUTE = "rule"; //$NON-NLS-1$
	//TODO eliminate dependency on context
	private NakedUmlEclipseContext context;
	private long nextMarked = 0;
	private long lastMarked = 0;
	private Map<EObject,BrokenElement> brokenElements;
	private HashMap<String,IMarker> existingMarkers;
	public NakedUmlErrorMarker(NakedUmlEclipseContext context){
		super();
		this.context = context;
	}
	public void maybeSchedule(){
		if(lastMarked == 0 || lastMarked > System.currentTimeMillis() + 3000){
			Display.getDefault().syncExec(new Runnable(){
				@Override
				public void run(){
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
						if(System.currentTimeMillis() + 3000 >= nextMarked){
							nextMarked = System.currentTimeMillis() + 3000;
							Display.getDefault().timerExec(3001, this);
						}
					}
				}
			});
		}
	}
	public void markFiles(){
		Set<Entry<EObject,BrokenElement>> entrySet = brokenElements.entrySet();
		for(Entry<EObject,BrokenElement> entry:entrySet){
			markFile(entry.getValue(), entry.getKey(), findUmlFile(entry.getKey()));
		}
	}
	public void calcExistingMarkers(Set<String> brokenUris) throws CoreException{
		IMarker[] mrks = context.getUmlDirectory().findMarkers(EValidator.MARKER, true, IResource.DEPTH_INFINITE);
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
		for(Entry<String,BrokenElement> entry:context.getEmfToNakedUmlSynchronizer().getNakedWorkspace().getErrorMap().getErrors().entrySet()){
			EObject o = findElement(entry.getKey());
			if(o != null && o.eResource() != null){
				for(Entry<IValidationRule,BrokenRule> entry2:entry.getValue().getBrokenRules().entrySet()){
					this.brokenElements.put(o, entry.getValue());
					IValidationRule key = entry2.getKey();
					IFile umlFile = findUmlFile(o);
					if(umlFile != null){
						brokenUris.add(markerKey(umlFile, o, key));
					}
				}
			}
		}
		return brokenUris;
	}
	private String markerKey(IFile file,EObject o,IValidationRule key){
		return file.getName() + ":" + EcoreUtil.getURI(o).toString() + ":" + key.name();
	}
	private void markFile(BrokenElement entry,final EObject o,final IFile file){
		if(file != null){
			for(final BrokenRule brokenRule:entry.getRulesBrokenSince(new Date(lastMarked))){
				try{
					long start = System.currentTimeMillis();
					IMarker marker = existingMarkers.get(markerKey(file, o, brokenRule.getRule()));
					if(marker == null){
						marker = file.createMarker(EValidator.MARKER);
						marker.setAttribute(IMarker.SEVERITY, IMarker.SEVERITY_ERROR);
						marker.setAttribute(IMarker.PRIORITY, IMarker.PRIORITY_HIGH);
						marker.setAttribute(RULE_ATTRIBUTE, brokenRule.getRule().name());
						marker.setAttribute(EValidator.URI_ATTRIBUTE, EcoreUtil.getURI(o).toString());
					}
					String messagePattern = brokenRule.getRule().getMessagePattern();
					String message = EmfValidationUtil.replaceArguments(o, brokenRule, messagePattern);
					marker.setAttribute(IMarker.MESSAGE, message);
					System.out.println("Adding marker took " + (System.currentTimeMillis() - start));
				}catch(CoreException e){
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
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
