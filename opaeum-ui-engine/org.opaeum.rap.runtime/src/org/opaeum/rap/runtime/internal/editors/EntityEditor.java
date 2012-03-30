package org.opaeum.rap.runtime.internal.editors;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IPropertyListener;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.editor.FormPage;
import org.eclipse.ui.forms.editor.SharedHeaderFormEditor;
import org.eclipse.ui.statushandlers.StatusManager;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySheetPage;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.IPropertySourceProvider;
import org.eclipse.ui.views.properties.PropertyDescriptor;
import org.eclipse.ui.views.properties.PropertySheetPage;
import org.opaeum.annotation.NumlMetaInfo;
import org.opaeum.rap.runtime.internal.datamodel.EntityEditorInput;
import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.runtime.domain.IntrospectionUtil;
import org.opaeum.uim.ClassUserInteractionModel;
import org.opaeum.uim.UimComponent;
import org.opaeum.uim.editor.EditorPage;

public class EntityEditor extends SharedHeaderFormEditor implements ISelectionListener{
	private FormPage[] editorPages;
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
	}
	protected void createHeaderContents(IManagedForm headerForm){
		EObject rootUimObject = getRootUimObject();
		if(rootUimObject instanceof ClassUserInteractionModel){
			GridLayout gl = new GridLayout();
			Composite body2 = new Composite(headerForm.getForm().getForm().getHead(), SWT.NONE);
			headerForm.getForm().setHeadClient(body2);
			body2.setLayout(gl);
			gl.numColumns = 20;
			ClassUserInteractionModel cuim = (ClassUserInteractionModel) rootUimObject;
			EList<UimComponent> children = cuim.getPrimaryEditor().getActionBar().getChildren();
			for(UimComponent uimComponent:children){
				OpaeumPage.addComponent(body2, uimComponent);
			}
			body2.getParent().layout();
		}
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
			EObject content = getRootUimObject();
			if(content instanceof ClassUserInteractionModel){
				ClassUserInteractionModel cuim = (ClassUserInteractionModel) content;
				EList<EditorPage> pages2 = cuim.getPrimaryEditor().getPages();
				editorPages = new FormPage[pages2.size()];
				for(int i = 0;i < editorPages.length;i++){
					editorPages[i] = new OpaeumPage(this, pages2.get(i));
				}
			}
			for(int i = 0;i < editorPages.length;i++){
				addPage(editorPages[i]);
				editorPages[i].addPropertyListener(new IPropertyListener(){
					public void propertyChanged(Object source,int propertyId){
						EntityEditor.this.handlePropertyChange(propertyId);
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
	public EObject getRootUimObject(){
		EntityEditorInput eei = getEditorInput();
		Class<IPersistentObject> originalClass = IntrospectionUtil.getOriginalClass(eei.getPersistentObject());
		String uuid = originalClass.getAnnotation(NumlMetaInfo.class).uuid();
		Resource r = eei.getOpaeumSession().getApplication().getUimResource(uuid);
		EObject content = r.getContents().get(0);
		return content;
	}
	public EntityEditorInput getEditorInput(){
		EntityEditorInput eei = (EntityEditorInput) super.getEditorInput();
		return eei;
	}
	@Override
	public void doSave(final IProgressMonitor monitor){
		for(int i = 0;i < editorPages.length;i++){
			editorPages[i].doSave(monitor);
		}
	}
	@Override
	public void doSaveAs(){
	}
	@Override
	public boolean isSaveAsAllowed(){
		return true;
	}
	@SuppressWarnings("unchecked")//$NON-NLS-1$
	@Override
	public Object getAdapter(final Class adapter){
		Object result;
		if(adapter == IPersistentObject.class){
			result = getEditorInput().getAdapter(adapter);
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
				IPropertySource result = null;
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
							}else{
								return "";
							}
						}
						public IPropertyDescriptor[] getPropertyDescriptors(){
							return new IPropertyDescriptor[]{new PropertyDescriptor("ID", "ID"),new PropertyDescriptor("Name", "Name")};
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
}
