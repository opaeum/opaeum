package org.opaeum.papyrus.uml.modelexplorer;

import java.lang.reflect.Field;

import org.eclipse.emf.transaction.ResourceSetChangeEvent;
import org.eclipse.emf.transaction.ResourceSetListenerImpl;
import org.eclipse.emf.transaction.Transaction;
import org.eclipse.papyrus.infra.core.editor.IMultiDiagramEditor;
import org.eclipse.papyrus.views.modelexplorer.ModelExplorerPage;
import org.eclipse.papyrus.views.modelexplorer.ModelExplorerView;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IViewSite;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

public class OpaeumModelExplorerPage extends ModelExplorerPage{
	public OpaeumModelExplorerPage(){
	}
	protected IViewPart createViewer(IWorkbenchPart part){
		ModelExplorerView modelExplorerView = new ModelExplorerView((IMultiDiagramEditor) part){
			@Override
			public void createPartControl(Composite aParent){
				super.createPartControl(aParent);
				getCommonViewer().getTree().getVerticalBar().setVisible(true);
			}
			@Override
			public void init(IViewSite site,IMemento aMemento) throws PartInitException{
				try{
					Field f = ModelExplorerView.class.getDeclaredField("resourceSetListener");
					f.setAccessible(true);
					f.set(this, new ResourceSetListenerImpl(){
						private Object lastTrans;

						/**
						 * {@inheritDoc}
						 */
						@Override
						public void resourceSetChanged(ResourceSetChangeEvent event){
							super.resourceSetChanged(event);
							Transaction curTrans = event.getTransaction();
							if(lastTrans != null && lastTrans.equals(curTrans)) {
								return;
							}
							lastTrans = curTrans;
							PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {
								public void run() {
//									refresh();
								}
							});
						}
					});
				}catch(IllegalArgumentException e){
					// TODO Auto-generated catch block
					e.printStackTrace();
				}catch(SecurityException e){
					// TODO Auto-generated catch block
					e.printStackTrace();
				}catch(IllegalAccessException e){
					// TODO Auto-generated catch block
					e.printStackTrace();
				}catch(NoSuchFieldException e){
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				super.init(site, aMemento);
			}
			@Override
			public void init(IViewSite site) throws PartInitException{
				// TODO Auto-generated method stub
				super.init(site);
			}
		};
		return modelExplorerView;
	}
}
