package org.nakeduml.topcased.uml.editor;

import java.util.Map.Entry;

import net.sf.nakeduml.emf.workspace.UmlElementCache;
import net.sf.nakeduml.metamodel.validation.BrokenElement;
import net.sf.nakeduml.metamodel.validation.IValidationRule;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EValidator;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.swt.widgets.Shell;
import org.nakeduml.eclipse.EmfValidationUtil;

public class NakedUmlErrorMarker implements Runnable{
	public static final String VALIDATION_MARKER_TYPE = "org.eclipse.emf.validation.problem"; //$NON-NLS-1$
	public static final String RULE_ATTRIBUTE = "rule"; //$NON-NLS-1$
	private NakedUmlEclipseContext context;
	private long lastMarked;
	private int noOfErrors;
	private Shell shell;
	public NakedUmlErrorMarker(Shell shell,NakedUmlEclipseContext context){
		this.context = context;
		this.shell = shell;
	}
	@Override
	public void run(){
		if(shouldMark()){
	
			UmlElementCache cache = context.getUmlElementCache();
			this.noOfErrors = cache.getNakedWorkspace().getErrorMap().getErrors().size();
			try{
				context.getUmlFile().deleteMarkers(VALIDATION_MARKER_TYPE, true, IResource.DEPTH_INFINITE);
			}catch(CoreException e){
				e.printStackTrace();
			}
			for(Entry<String,BrokenElement> entry:cache.getNakedWorkspace().getErrorMap().getErrors().entrySet()){
				EObject o = cache.getElement(entry.getKey());
				if(o != null && o.eResource().equals(cache.getEmfWorkspace().getGeneratingModelsOrProfiles().iterator().next().eResource())){
					IFile file = context.getUmlFile();
					for(Entry<IValidationRule,Object[]> brokenRule:entry.getValue().getBrokenRules().entrySet()){
						String messagePattern = brokenRule.getKey().getMessagePattern();
						String message = EmfValidationUtil.replaceArguments(o, brokenRule, messagePattern);
						try{
							IMarker marker = file.createMarker(VALIDATION_MARKER_TYPE);
							marker.setAttribute(IMarker.SEVERITY, IMarker.SEVERITY_ERROR);
							marker.setAttribute(IMarker.PRIORITY, IMarker.PRIORITY_HIGH);
							marker.setAttribute(IMarker.MESSAGE, message);
							marker.setAttribute(EValidator.URI_ATTRIBUTE, EcoreUtil.getURI(o).toString());
						}catch(CoreException e){
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}
			lastMarked=System.currentTimeMillis();
		}
		if(!(shell.isDisposed() || shell.getDisplay().isDisposed())){
			shell.getDisplay().timerExec(2000, this);
		}
	}
	public boolean shouldMark(){
		if(context == null || context.getUmlElementCache() == null || context.getUmlElementCache().getNakedWorkspace() == null){
			return false;
		}else{
			boolean errorsChanged = noOfErrors != context.getUmlElementCache().getNakedWorkspace().getErrorMap().getErrors().size();
			boolean timeForMarking = lastMarked+30000<System.currentTimeMillis() && noOfErrors>0;
			return context.isOpen() && (errorsChanged || timeForMarking);
		}
	}
}
