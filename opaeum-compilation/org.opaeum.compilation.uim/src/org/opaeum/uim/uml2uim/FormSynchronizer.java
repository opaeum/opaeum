package org.opaeum.uim.uml2uim;

import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.OpaqueAction;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Property;
import org.opaeum.emf.workspace.EmfWorkspace;
import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitBefore;

@StepDependency(phase = UimSynchronizationPhase.class)
public class FormSynchronizer extends AbstractUimSynchronizer{
	private FormSynchronizer2 delegate;
	private PerspectiveCreator perspectiveCreator;
	public FormSynchronizer(){
	}
	public FormSynchronizer(EmfWorkspace workspace,ResourceSet resourceSet,boolean regenerate){
		super(workspace, resourceSet, regenerate);
		delegate=new FormSynchronizer2(workspace.getDirectoryUri(), resourceSet, regenerate);
		perspectiveCreator=new PerspectiveCreator(workspace.getDirectoryUri(),resourceSet,regenerate);
	}
	@VisitBefore(matchSubclasses = false)
	public void beforeAction(OpaqueAction a){
		delegate.beforeAction(a);
	}
	@VisitBefore(matchSubclasses = true)
	public void beforeClass(Classifier c){
		delegate.beforeClass(c);
		perspectiveCreator.visitClassifier(c);
	}
	@VisitBefore(matchSubclasses = true)
	public void beforeProperty(Property p){
		perspectiveCreator.visitProperty(p);
	}
	@VisitBefore(matchSubclasses = false)
	public void beforeOperation(Operation o){
		delegate.beforeOperation(o);
		perspectiveCreator.visitOperation(o);
	}
	@VisitBefore
	public void visitWorkspace(EmfWorkspace ws){
		perspectiveCreator.visitWorkspace(ws);
	}
	

}
