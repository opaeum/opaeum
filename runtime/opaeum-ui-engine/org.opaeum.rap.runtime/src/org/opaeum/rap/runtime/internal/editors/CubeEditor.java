package org.opaeum.rap.runtime.internal.editors;

import java.util.Date;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IPropertyListener;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.forms.editor.FormPage;
import org.eclipse.ui.statushandlers.StatusManager;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySheetPage;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.IPropertySourceProvider;
import org.eclipse.ui.views.properties.PropertyDescriptor;
import org.eclipse.ui.views.properties.PropertySheetPage;
import org.opaeum.annotation.NumlMetaInfo;
import org.opaeum.rap.runtime.IOpaeumApplication;
import org.opaeum.runtime.domain.HibernateEntity;
import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.runtime.domain.IntrospectionUtil;
import org.opaeum.uim.cube.CubeQuery;
import org.opaeum.uim.model.ClassUserInteractionModel;

public class CubeEditor extends FormEditor implements ISelectionListener,IDirtyListener{
	private static final long serialVersionUID = 11231512131231L;
	private FormPage[] cubeQueries;
	private final class SelectionProvider implements ISelectionProvider{
		public void addSelectionChangedListener(final ISelectionChangedListener listener){
		}
		public ISelection getSelection(){
			IEditorInput input = getEditorInput();
			return new StructuredSelection(input.getAdapter(IPersistentObject.class));
		}
		public void removeSelectionChangedListener(final ISelectionChangedListener listener){
		}
		public void setSelection(final ISelection selection){
		}
	}
	@Override
	public void init(final IEditorSite site,final IEditorInput input) throws PartInitException{
		super.init(site, input);
		setPartName(input.getName());
		setTitleImage(input.getImageDescriptor().createImage());
		getSite().getPage().addSelectionListener(this);
		getSite().setSelectionProvider(new SelectionProvider());
		getEditorInput().setDirtyListener(this);
	}
	@Override
	public void dispose(){
		// getSite().getPage().removeSelectionListener(this);
		// IAdaptable entity = (IAdaptable) getEditorInput().getAdapter(IPersistentObject.class);
		// ILock lock = (ILock) entity.getAdapter(ILock.class);
		// lock.unLock();
		super.dispose();
	}
	@Override
	protected void addPages(){
		try{
			Object content = getRootUimObject();
			if(content instanceof ClassUserInteractionModel){
				ClassUserInteractionModel cuim = (ClassUserInteractionModel) content;
				List<CubeQuery> pages2 = cuim.getCubeQueryEditor().getQueries();
				cubeQueries = new FormPage[pages2.size()];
				for(int i = 0;i < cubeQueries.length;i++){
					cubeQueries[i] = new CubeQueryPage(this, pages2.get(i));
				}
			}
			for(int i = 0;i < cubeQueries.length;i++){
				addPage(cubeQueries[i]);
				cubeQueries[i].addPropertyListener(new IPropertyListener(){
					public void propertyChanged(Object source,int propertyId){
						CubeEditor.this.handlePropertyChange(propertyId);
					}
				});
			}
		}catch(final PartInitException pie){
			String id = "org.opaeum.rap.runtime"; //$NON-NLS-1$
			Status status = new Status(IStatus.ERROR, id, pie.getMessage(), pie);
			int style = StatusManager.SHOW | StatusManager.LOG;
			StatusManager.getManager().handle(status, style);
		}
	}
	public Object getRootUimObject(){
		IOpaeumApplication opaeumApplication = getOpaeumApplication();
		Class<IPersistentObject> originalClass = IntrospectionUtil.getOriginalClass(getEditorInput().getPersistentObject());
		String uuid = originalClass.getAnnotation(NumlMetaInfo.class).uuid();
		Resource r = opaeumApplication.getUimResource(uuid);
		Object content = r.getContents().get(0);
		return content;
	}
	public IOpaeumApplication getOpaeumApplication(){
		IOpaeumApplication opaeumApplication = getEditorInput().getOpaeumSession().getApplication();
		return opaeumApplication;
	}
	public EntityEditorInput getEditorInput(){
		EntityEditorInput eei = (EntityEditorInput) super.getEditorInput();
		return eei;
	}
	@Override
	public void doSave(final IProgressMonitor monitor){
		getEditorInput().setDirty(false);
		getEditorInput().getPersistence().flush();
	}
	@Override
	public boolean isDirty(){
		return getEditorInput().isDirty();
	}
	@Override
	public void doSaveAs(){
	}
	@Override
	public boolean isSaveAsAllowed(){
		// TODO deepcopy
		return false;
	}
	@Override
	public Object getAdapter(@SuppressWarnings("rawtypes")
	final Class adapter){
		Object result;
		if(adapter == IPersistentObject.class){
			result = getEditorInput().getPersistentObject();
		}else if(adapter == IPropertySheetPage.class){
			result = createPropertySheetPage();
		}else{
			result = super.getAdapter(adapter);
		}
		return result;
	}
	private PropertySheetPage createPropertySheetPage(){
		PropertySheetPage propertySheetPage = new PropertySheetPage();
		propertySheetPage.setPropertySourceProvider(new IPropertySourceProvider(){
			public IPropertySource getPropertySource(final Object object){
				if(object instanceof IPersistentObject){
					return new IPropertySource(){
						public void setPropertyValue(Object id,Object value){
						}
						public void resetPropertyValue(Object id){
						}
						public boolean isPropertySet(Object id){
							return false;
						}
						public Object getPropertyValue(Object id){
							if(id.equals("ID")){
								return ((IPersistentObject) object).getId().toString();
							}else if(id.equals("Name")){
								return ((IPersistentObject) object).getName() + "Name";
							}else if(id.equals("Version")){
								return ((IPersistentObject) object).getObjectVersion() + "";
							}else if(id.equals("UUID")){
								return ((IPersistentObject) object).getUid() + "";
							}else{
								return "";
							}
						}
						public IPropertyDescriptor[] getPropertyDescriptors(){
							return new IPropertyDescriptor[]{new PropertyDescriptor("ID", "ID"),new PropertyDescriptor("Name", "Name"),
									new PropertyDescriptor("Version", "Version"),new PropertyDescriptor("UUID", "UUID")};
						}
						public Object getEditableValue(){
							return null;
						}
					};
				}
				return null;
			}
		});
		return propertySheetPage;
	}
	public void selectionChanged(final IWorkbenchPart part,final ISelection selection){
		IWorkbenchPage activePage = getSite().getWorkbenchWindow().getActivePage();
		if(activePage.getActiveEditor() != this){
			if(selection instanceof IStructuredSelection){
				IStructuredSelection sselection = (IStructuredSelection) selection;
				Object entity = getEditorInput().getAdapter(IPersistentObject.class);
				if(sselection.getFirstElement() == entity){
					activePage.bringToTop(this);
				}
			}
		}
	}
	protected Composite createPageContainer(Composite parent){
		Composite pc = super.createPageContainer(parent);
		pc.setLayout(new FillLayout(SWT.VERTICAL));
		return pc;
	}
	public void dirtyChanged(boolean dirty){
		firePropertyChange(IEditorPart.PROP_DIRTY);
		if(!dirty && ((HibernateEntity) getEditorInput().getPersistentObject()).getDeletedOn().before(new Date(System.currentTimeMillis() + 1))){
			close(false);
		}
	}
}
