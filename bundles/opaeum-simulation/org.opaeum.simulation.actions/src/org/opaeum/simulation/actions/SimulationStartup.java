package org.opaeum.simulation.actions;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.util.EContentAdapter;
import org.eclipse.emf.ecore.util.ECrossReferenceAdapter;
import org.eclipse.papyrus.editor.PapyrusMultiDiagramEditor;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IPartListener;
import org.eclipse.ui.IStartup;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.InstanceSpecification;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Slot;
import org.eclipse.uml2.uml.UMLPackage;
import org.opaeum.eclipse.EmfPropertyUtil;
import org.opaeum.metamodels.simulation.simulation.ActualInstance;
import org.opaeum.metamodels.simulation.simulation.InstanceSimulation;
import org.opaeum.metamodels.simulation.simulation.SimulatingSlot;
import org.opaeum.metamodels.simulation.simulation.SimulationFactory;

public class SimulationStartup implements IStartup{
	public final class SimulationPartListener implements IPartListener{
		public void partActivated(IWorkbenchPart part){
			maybeSetCurrentEditContext(part);
		}
		public void partBroughtToTop(IWorkbenchPart part){
			maybeSetCurrentEditContext(part);
		}
		private void maybeSetCurrentEditContext(IWorkbenchPart part){
			IEditorPart editor = part.getSite().getWorkbenchWindow().getActivePage().getActiveEditor();
			if(editor instanceof PapyrusMultiDiagramEditor){
				associateOpaeumContext((PapyrusMultiDiagramEditor) editor);
			}
		}
		public void partClosed(IWorkbenchPart part){
		}
		public void partDeactivated(IWorkbenchPart part){
		}
		public void partOpened(IWorkbenchPart part){
			maybeSetCurrentEditContext(part);
		}
	}
	public void earlyStartup(){
		final IWorkbench workbench = PlatformUI.getWorkbench();
		// TODO register on new window creation too
		workbench.getDisplay().asyncExec(new Runnable(){
			public void run(){
				final IWorkbenchWindow window = workbench.getActiveWorkbenchWindow();
				if(window != null && window.getActivePage() != null){
					window.getActivePage().addPartListener(new SimulationPartListener());
					if(window.getActivePage().getActiveEditor() instanceof PapyrusMultiDiagramEditor){
						associateOpaeumContext((PapyrusMultiDiagramEditor) window.getActivePage().getActiveEditor());
					}
				}
			}
		});
	}
	private IFile getUmlFile(final IFileEditorInput fe){
		return fe.getFile().getProject().getFile(fe.getFile().getProjectRelativePath().removeFileExtension().addFileExtension("uml"));
	}
	private void associateOpaeumContext(PapyrusMultiDiagramEditor e){
		final IFile umlFile = getUmlFile((IFileEditorInput) e.getEditorInput());
		if(umlFile.getParent().getName().equals("simulation")){
			// TODO get COMPLETE workspaces
			e.getEditingDomain().getResourceSet().eAdapters().add(new EContentAdapter(){
				private ECrossReferenceAdapter crossReferenceAdapter;
				@Override
				public void notifyChanged(Notification notification){
					super.notifyChanged(notification);
					if(true){
						//TODO sort out the problem that the resources are lazily loaded
						return;
					}
					if(notification.getFeatureID(InstanceSpecification.class) == UMLPackage.INSTANCE_SPECIFICATION__CLASSIFIER){
						if(notification.getNotifier() instanceof InstanceSimulation || notification.getNotifier() instanceof ActualInstance){
							InstanceSpecification is = (InstanceSpecification) notification.getNotifier();
							if(is.getClassifiers().size() == 1){
								synchronizeSlots(is.getClassifiers().get(0), is);
							}
						}
					}
				}
				private void synchronizeSlots(Classifier en,InstanceSpecification newValue){
					List<Property> propertiesInScope = EmfPropertyUtil.getEffectiveProperties(en);
					outer:for(Slot slot:new ArrayList<Slot>(newValue.getSlots())){
						for(Property a:propertiesInScope){
							if(a.equals(slot.getDefiningFeature()) && !(a.isDerived() || a.isDerivedUnion())){
								continue outer;
							}
						}
					}
					for(Property a:propertiesInScope){
						if(!(a.isDerived() || a.isDerivedUnion() || (a.getOtherEnd()!=null && a.getOtherEnd().isComposite()))){
							ensureSlotsPresence(newValue, a);
						}
					}
				}
				private void ensureSlotsPresence(InstanceSpecification is,Property a){
					SimulatingSlot found = null;
					for(Slot slot:new ArrayList<Slot>(is.getSlots())){
						if(slot.getDefiningFeature() != null && slot.getDefiningFeature().equals(a)){
							found = (SimulatingSlot) slot;
						}
					}
					if(found == null){
						found = SimulationFactory.eINSTANCE.createSimulatingSlot();
						found.setDefiningFeature(a);
						is.getSlots().add(found);
						if(a.isMultivalued()){
							found.setSizeDistribution(SimulationFactory.eINSTANCE.createNumericValueDistribution());
						}
						// TODO filter out slots for library types
						// TODO Create an appropriate value
					}
				}
			});
		}
	}
}
