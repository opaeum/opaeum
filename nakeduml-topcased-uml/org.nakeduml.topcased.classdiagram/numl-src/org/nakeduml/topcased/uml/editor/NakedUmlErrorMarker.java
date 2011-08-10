package org.nakeduml.topcased.uml.editor;

import java.util.Collection;
import java.util.Map.Entry;

import net.sf.nakeduml.emf.workspace.EmfWorkspace;
import net.sf.nakeduml.emf.workspace.UmlElementCache;
import net.sf.nakeduml.metamodel.validation.BrokenElement;
import net.sf.nakeduml.metamodel.validation.IValidationRule;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EValidator;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.uml2.uml.Element;
import org.nakeduml.eclipse.EmfValidationUtil;

public class NakedUmlErrorMarker implements Runnable{
	public static final String VALIDATION_MARKER_TYPE = "org.eclipse.emf.validation.problem"; //$NON-NLS-1$
	public static final String RULE_ATTRIBUTE = "rule"; //$NON-NLS-1$
	private NakedUmlEclipseContext context;
	private long lastMarked;
	private int noOfErrors;
	public NakedUmlErrorMarker(NakedUmlEclipseContext context){
		this.context = context;
	}
	@Override
	public void run(){
		if(shouldMark()){
			this.noOfErrors = context.getUmlElementCache().getNakedWorkspace().getErrorMap().getErrors().size();
			try{
				context.getUmlDirectory().deleteMarkers(VALIDATION_MARKER_TYPE, true, IResource.DEPTH_INFINITE);
			}catch(CoreException e){
				e.printStackTrace();
			}
			for(Entry<String,BrokenElement> entry:context.getUmlElementCache().getNakedWorkspace().getErrorMap().getErrors().entrySet()){
				EObject o = findElement(entry.getKey());
				if(o != null){
					IFile file = findIFile(o);
					if(file != null){
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
			}
			lastMarked = System.currentTimeMillis();
		}
		if(!(Display.getDefault().isDisposed() || Display.getDefault().isDisposed())){
			Display.getDefault().timerExec(2000, this);
		}
	}
	public IFile findIFile(EObject o){
		IFile file = null;
		try{
			for(IResource r:context.getUmlDirectory().members()){
				if(r.getName().equals(o.eResource().getURI().lastSegment())){
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
	public boolean shouldMark(){
		if(context == null || context.getUmlElementCache() == null || context.getUmlElementCache().getNakedWorkspace() == null){
			return false;
		}else{
			boolean errorsChanged = noOfErrors != context.getUmlElementCache().getNakedWorkspace().getErrorMap().getErrors().size();
			boolean timeForMarking = lastMarked + 30000 < System.currentTimeMillis() && noOfErrors > 0;
			return context.isOpen() && (errorsChanged || timeForMarking);
		}
	}
}
