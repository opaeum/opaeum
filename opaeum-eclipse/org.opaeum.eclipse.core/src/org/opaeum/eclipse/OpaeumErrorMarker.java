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
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Package;
import org.opaeum.eclipse.context.OpenUmlFile;
import org.opaeum.metamodel.validation.BrokenElement;
import org.opaeum.metamodel.validation.BrokenRule;
import org.opaeum.metamodel.validation.IValidationRule;

public class OpaeumErrorMarker implements OpaeumSynchronizationListener{
	public static final String VALIDATION_MARKER_TYPE = "org.eclipse.emf.validation.problem"; //$NON-NLS-1$
	public static final String RULE_ATTRIBUTE = "rule"; //$NON-NLS-1$
	private long lastMarked = 0;
	private Map<EObject,BrokenElement> brokenElements;
	private HashMap<String,IMarker> existingMarkers;
	public OpaeumErrorMarker(){
		super();
	}
	public void maybeSchedule(final OpenUmlFile file){
		// if((lastMarked == 0 || lastMarked < System.currentTimeMillis() - 3000)){
		Display.getDefault().syncExec(new Runnable(){
			@Override
			public void run(){
				existingMarkers = new HashMap<String,IMarker>();
				brokenElements = new HashMap<EObject,BrokenElement>();
				Set<String> brokenUris = calcBrokenElements(file);
				try{
					calcExistingMarkers(brokenUris, file);
					Set<Entry<EObject,BrokenElement>> entrySet = brokenElements.entrySet();
					for(Entry<EObject,BrokenElement> entry:entrySet){
						markFiles(entry.getValue(), entry.getKey(), file);
					}
				}catch(CoreException e){
					e.printStackTrace();
				}finally{
					// if(lastMarked < System.currentTimeMillis() - 3000 && file.getFile().exists()){
					// lastMarked = System.currentTimeMillis();
					// Display.getDefault().timerExec(3001, this);
					// }else{
					// }
				}
			}
		});
		// }
	}
	protected void calcExistingMarkers(Set<String> brokenUris,OpenUmlFile file) throws CoreException{
		IMarker[] mrks = file.getFile().findMarkers(EValidator.MARKER, true, IResource.DEPTH_INFINITE);
		for(IMarker marker:mrks){
			String markerKey = markerKey(marker);
			if(brokenUris.contains(markerKey)){
				existingMarkers.put(markerKey, marker);
				if(marker.getCreationTime() > System.currentTimeMillis() + 20000){
					// Safety net - should rather be recalculated through dependency calculation
					file.addEmfChange(URI.createURI((String) marker.getAttribute(EValidator.URI_ATTRIBUTE)));
				}
			}else{
				marker.delete();
			}
		}
	}
	private String markerKey(IMarker marker) throws CoreException{
		return marker.getResource().getName() + ":" + marker.getAttribute(EValidator.URI_ATTRIBUTE) + ":" + marker.getAttribute(RULE_ATTRIBUTE);
	}
	private Set<String> calcBrokenElements(OpenUmlFile file){
		Set<String> brokenUris = new HashSet<String>();
		Map<String,BrokenElement> errors = file.getEmfWorkspace().getErrorMap().getErrors();
		for(Entry<String,BrokenElement> entry:errors.entrySet()){
			Element o = file.getEmfWorkspace().getModelElement(entry.getKey());
			if(o != null && o.eResource() != null){
				Package rootObject = EmfElementFinder.getRootObject(o);
				if(rootObject == file.getModel()){
					for(Entry<IValidationRule,BrokenRule> entry2:entry.getValue().getBrokenRules().entrySet()){
						this.brokenElements.put(o, entry.getValue());
						IValidationRule key = entry2.getKey();
						brokenUris.add(markerKey(file.getFile(), o, key));
						for(Object object:entry2.getValue().getParameters()){
							if(object instanceof Element){
								if(EmfElementFinder.getRootObject((Element) object) == file.getModel()){
									brokenUris.add(markerKey(file.getFile(), (EObject) object, key));
								}
							}
						}
					}
				}
			}
		}
		return brokenUris;
	}
	protected String markerKey(IFile file,EObject o,IValidationRule key){
		if(file == null){
			return "";// Sometimes the file is not found TODO investigate
		}else{
			String fileName = file.getName();
			String uri = EcoreUtil.getURI(o).toString();
			String keyName = key.name();
			return fileName + ":" + uri + ":" + keyName;
		}
	}
	private void markFiles(BrokenElement entry,final EObject o,OpenUmlFile file){
		for(final BrokenRule brokenRule:getBrokenRulesToAdd(entry)){
			try{
				String messagePattern = brokenRule.getRule().getMessagePattern();
				String message = EmfValidationUtil.replaceArguments(o, brokenRule, messagePattern);
				maybeMarkFile(o, brokenRule, message, file);
				Object[] parameters = brokenRule.getParameters();
				for(Object object:parameters){
					if(object instanceof Element){
						maybeMarkFile((EObject) object, brokenRule, message, file);
					}
				}
			}catch(CoreException e){
				e.printStackTrace();
			}
		}
	}
	protected Set<BrokenRule> getBrokenRulesToAdd(BrokenElement entry){
		return entry.getRulesBrokenSince(new Date(lastMarked));
	}
	protected void maybeMarkFile(final EObject o,final BrokenRule brokenRule,String message,OpenUmlFile ouf) throws CoreException{
		if(EmfElementFinder.getRootObject((Element) o) == ouf.getModel()){
			IFile file = ouf.getFile();
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
	@Override
	public void synchronizationComplete(OpenUmlFile file,Set<Element> affectedElements){
		maybeSchedule(file);
	}
	@Override
	public void onClose(OpenUmlFile openUmlFile){
		// TODO Auto-generated method stub
	}
}
