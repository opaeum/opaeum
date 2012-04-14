package org.opaeum.rap.runtime.internal.editors;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;
import java.util.Iterator;
import java.util.UUID;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.ValidationStatusProvider;
import org.eclipse.core.databinding.observable.value.AbstractObservableValue;
import org.eclipse.core.databinding.observable.value.ComputedValue;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IPropertyListener;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.forms.HyperlinkSettings;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.IMessage;
import org.eclipse.ui.forms.editor.FormPage;
import org.eclipse.ui.forms.editor.SharedHeaderFormEditor;
import org.eclipse.ui.forms.events.HyperlinkAdapter;
import org.eclipse.ui.forms.events.HyperlinkEvent;
import org.eclipse.ui.forms.widgets.Form;
import org.eclipse.ui.forms.widgets.FormText;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.ui.statushandlers.StatusManager;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySheetPage;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.IPropertySourceProvider;
import org.eclipse.ui.views.properties.PropertyDescriptor;
import org.eclipse.ui.views.properties.PropertySheetPage;
import org.opaeum.annotation.NumlMetaInfo;
import org.opaeum.rap.runtime.IOpaeumApplication;
import org.opaeum.rap.runtime.internal.Activator;
import org.opaeum.runtime.domain.HibernateEntity;
import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.runtime.domain.IntrospectionUtil;
import org.opaeum.uim.ClassUserInteractionModel;
import org.opaeum.uim.UimComponent;
import org.opaeum.uim.editor.EditorPage;

public class EntityEditor extends SharedHeaderFormEditor implements ISelectionListener, IDirtyListener{
	private static final long serialVersionUID = 11231512131231L;
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
		getEditorInput().setDirtyListener(this);
	}
	
	protected void createHeaderContents(final IManagedForm headerForm){
		final FormToolkit toolkit = headerForm.getToolkit();
		Composite headerClient = toolkit.createComposite(headerForm.getForm().getForm().getHead(), SWT.NONE);
		headerForm.getForm().setHeadClient(headerClient);
		headerClient.setLayout(new FillLayout());
		EObject rootUimObject = getRootUimObject();
		final DataBindingContext bc = getEditorInput().getDataBindingContext();
		if(rootUimObject instanceof ClassUserInteractionModel){
			GridLayout gl = new GridLayout();
			headerClient.setLayout(gl);
			gl.numColumns = 20;
			ClassUserInteractionModel cuim = (ClassUserInteractionModel) rootUimObject;
			EList<UimComponent> children = cuim.getPrimaryEditor().getActionBar().getChildren();
			ComponentTreeBuilder builder = new ComponentTreeBuilder(getEditorInput());
			for(UimComponent uimComponent:children){
				builder.addComponent(headerClient, uimComponent, bc);
			}
			headerClient.getParent().layout();
		}
		toolkit.getHyperlinkGroup().setHyperlinkUnderlineMode(HyperlinkSettings.UNDERLINE_HOVER);
		toolkit.decorateFormHeading(headerForm.getForm().getForm());
		IObservableValue errorObservable = new AbstractObservableValue(){
			Object status;
			public Object getValueType(){
				return Object.class;
			}
			@Override
			protected Object doGetValue(){
				return status;
			}
			protected void doSetValue(Object value){
				status = value;
			}
		};
		// This one listenes to all changes
		ComputedValue computedValue = new ComputedValue(bc.getValidationRealm(), Object.class){
			@Override
			protected Object calculate(){
				getHeaderForm().getForm().getForm().getMessageManager().removeAllMessages();
				MultiStatus statuses = new MultiStatus(Activator.ID, Status.ERROR, "Multiple Problems", null);
				for(Iterator it = bc.getValidationStatusProviders().iterator();it.hasNext();){
					ValidationStatusProvider validationStatusProvider = (ValidationStatusProvider) it.next();
					IStatus status = (IStatus) validationStatusProvider.getValidationStatus().getValue();
					if(!status.isOK()){
						addMessages(getHeaderForm(), status);
					}
				}
				return Status.OK;
			}
			private void addMessages(final IManagedForm headerForm,IStatus status2){
				if(status2 instanceof MultiStatus){
					IStatus[] children = status2.getChildren();
					for(IStatus s:children){
						addMessages(headerForm, s);
					}
				}else if(!status2.isOK()){
					headerForm.getForm().getForm().getMessageManager()
							.addMessage(UUID.randomUUID(), getMessage(status2), null, IMessageProvider.ERROR);
				}
			}
			private String getMessage(IStatus status2){
				return status2.getMessage() == null || status2.getMessage().length() == 0 ? "Unkown validation error" : status2.getMessage();
			}
		};
		bc.bindValue(errorObservable, computedValue/* new AggregateValidationStatus(bc, AggregateValidationStatus.MAX_SEVERITY) */, null, null);
		headerForm.getForm().getForm().addMessageHyperlinkListener(new HyperlinkAdapter(){
			public void linkActivated(HyperlinkEvent e){
				String title = e.getLabel();
				Object href = e.getHref();
				if(href instanceof IMessage[]){
				}
				Point hl = ((Control) e.widget).toDisplay(0, 0);
				hl.x += 10;
				hl.y += 10;
				Shell shell = new Shell(headerForm.getForm().getShell(), SWT.ON_TOP | SWT.TOOL);
				shell.setImage(getImage(headerForm.getForm().getForm().getMessageType()));
				shell.setText(title);
				shell.setLayout(new FillLayout());
				FormText text = toolkit.createFormText(shell, true);
				configureFormText(headerForm.getForm().getForm(), text);
				if(href instanceof IMessage[]){
					text.setText(createFormTextContent((IMessage[]) href), true, false);
				}
				shell.setLocation(hl);
				shell.pack();
				shell.open();
			}
		});
	}
	private Image getImage(int type){
		switch(type){
		case IMessageProvider.ERROR:
			return PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJS_ERROR_TSK);
		case IMessageProvider.WARNING:
			return PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJS_WARN_TSK);
		case IMessageProvider.INFORMATION:
			return PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJS_INFO_TSK);
		}
		return null;
	}
	private void configureFormText(final Form form,FormText text){
		text.addHyperlinkListener(new HyperlinkAdapter(){
			public void linkActivated(HyperlinkEvent e){
				String is = (String) e.getHref();
				try{
					int index = Integer.parseInt(is);
					IMessage[] messages = form.getChildrenMessages();
					IMessage message = messages[index];
					Control c = message.getControl();
					((FormText) e.widget).getShell().dispose();
					if(c != null)
						c.setFocus();
				}catch(NumberFormatException ex){
				}
			}
		});
		text.setImage("error", getImage(IMessageProvider.ERROR));
		text.setImage("warning", getImage(IMessageProvider.WARNING));
		text.setImage("info", getImage(IMessageProvider.INFORMATION));
	}
	private String createFormTextContent(IMessage[] messages){
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		pw.println("<form>");
		for(int i = 0;i < messages.length;i++){
			IMessage message = messages[i];
			pw.print("<li vspace=\"false\" style=\"image\" indent=\"16\" value=\"");
			switch(message.getMessageType()){
			case IMessageProvider.ERROR:
				pw.print("error");
				break;
			case IMessageProvider.WARNING:
				pw.print("warning");
				break;
			case IMessageProvider.INFORMATION:
				pw.print("info");
				break;
			}
			pw.print("\"> <a href=\"");
			pw.print(i + "");
			pw.print("\">");
			if(message.getPrefix() != null)
				pw.print(message.getPrefix());
			pw.print(message.getMessage());
			pw.println("</a></li>");
		}
		pw.println("</form>");
		pw.flush();
		return sw.toString();
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
					editorPages[i] = new OpaeumEditorPage(this, pages2.get(i));
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
		IOpaeumApplication opaeumApplication = getOpaeumApplication();
		Class<IPersistentObject> originalClass = IntrospectionUtil.getOriginalClass(getEditorInput().getPersistentObject());
		String uuid = originalClass.getAnnotation(NumlMetaInfo.class).uuid();
		Resource r = opaeumApplication.getUimResource(uuid);
		EObject content = r.getContents().get(0);
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
							return new IPropertyDescriptor[]{new PropertyDescriptor("ID", "ID"),new PropertyDescriptor("Name", "Name"),new PropertyDescriptor("Version", "Version"),new PropertyDescriptor("UUID", "UUID")};
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
		if(!dirty && ((HibernateEntity) getEditorInput().getPersistentObject()).getDeletedOn().before(new Date(System.currentTimeMillis()+1))){
			close(false);
		}
	}
}
