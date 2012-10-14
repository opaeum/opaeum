package org.opaeum.eclipse.uml.propertysections.base;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IStatusLineManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.forms.IMessage;
import org.eclipse.ui.forms.IMessageManager;
import org.eclipse.ui.part.IPage;
import org.eclipse.ui.part.PageBookView;
import org.eclipse.ui.views.properties.tabbed.AbstractPropertySection;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertyConstants;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;
import org.eclipse.uml2.uml.Element;
import org.opaeum.eclipse.EmfElementFinder;
import org.opaeum.eclipse.EmfValidationUtil;
import org.opaeum.eclipse.OpaeumSynchronizationListener;
import org.opaeum.eclipse.context.OpaeumEclipseContext;
import org.opaeum.eclipse.context.OpenUmlFile;
import org.opaeum.eclipse.uml.propertysections.RecursiveAdapter;
import org.opaeum.eclipse.uml.propertysections.common.PropertySectionMessageManager;
import org.opaeum.metamodel.validation.BrokenRule;

public abstract class AbstractOpaeumPropertySection extends AbstractPropertySection implements OpaeumSynchronizationListener{
	public static final String COMMAND_NAME = "Udate";
	private boolean isRefreshing = false;
	private IStatusLineManager statusLineManager;
	private Composite sectionComposite;
	private EObject eObject;
	private List<EObject> eObjectList;
	private Integer labelWidth;
	protected CLabel labelCombo;
	private TabbedPropertySheetPage propertySheetPage;
	private IMessageManager messageManager;
	private OpenUmlFile openUmlFile;
	private Set<BrokenRule> brokenRules = null;
	private RecursiveAdapter modelListener = new RecursiveAdapter(){
		@Override
		protected void safeNotifyChanged(Notification msg){
			handleModelChanged(msg);
		}
	};
	protected EObject getSelectedObject(){
		return eObject;
	}
	protected abstract EStructuralFeature getFeature();
	protected EObject getFeatureOwner(EObject e){
		return e;
	}
	public int getLabelWidth(){
		return labelWidth;
	}
	public CLabel getLabelCombo(){
		return labelCombo;
	}
	@Override
	public TabbedPropertySheetWidgetFactory getWidgetFactory(){
		return getPropertySheetPage().getWidgetFactory();
	}
	public TabbedPropertySheetPage getPropertySheetPage(){
		return propertySheetPage;
	}
	@Override
	public void createControls(Composite parent,TabbedPropertySheetPage page){
		this.propertySheetPage = page;
		sectionComposite = new Composite(parent, SWT.BORDER);// getMainComposite(parent);
		FormLayout layout = new FormLayout();
		layout.marginWidth = 2;// ITabbedPropertyConstants.HSPACE + 2;
		layout.marginHeight = 2;// ITabbedPropertyConstants.VSPACE;
		layout.spacing = ITabbedPropertyConstants.VMARGIN + 1;
		sectionComposite.setLayout(layout);
		getWidgetFactory().adapt(sectionComposite);
		labelCombo = getWidgetFactory().createCLabel(sectionComposite, getLabelText());
		FormData ld = new FormData();
		ld.left = new FormAttachment(0, 0);
		ld.top = new FormAttachment(0, 0);
		ld.bottom = new FormAttachment(100, 0);
		if(getLabelText() == null || getLabelText().length() == 0){
			ld.right = new FormAttachment(0, 0);
			labelCombo.setVisible(false);
		}else{
			ld.right = new FormAttachment(0, getStandardLabelWidth(sectionComposite, new String[]{getLabelText()}));
		}
		labelCombo.setLayoutData(ld);
		createWidgets(sectionComposite);
		setSectionData(sectionComposite);
		hookListeners();
		messageManager = new PropertySectionMessageManager();
		IActionBars actionBars = page.getSite().getActionBars();
		makeContributions(actionBars.getMenuManager(), actionBars.getToolBarManager(), actionBars.getStatusLineManager());
	}
	public void setLabelWidth(int labelWidth){
		this.labelWidth = labelWidth;
		FormData fd = (FormData) labelCombo.getLayoutData();
		fd.right = new FormAttachment(0, labelWidth);
		labelCombo.setLayoutData(fd);
		sectionComposite.layout();
	}
	public int getMinimumLabelWidth(){
		return getMinimumLabelWidth(sectionComposite, getLabelText());
	}
	protected int getStandardLabelWidth(Composite parent,String...labels){
		if(labelWidth == null){
			int standardLabelWidth = getMinimumLabelWidth(parent, labels);
			return standardLabelWidth;
		}
		return labelWidth;
	}
	protected int getMinimumLabelWidth(Composite parent,String...labels){
		int standardLabelWidth = STANDARD_LABEL_WIDTH + 65;
		GC gc = new GC(parent);
		int indent = gc.textExtent("XXX").x;
		for(int i = 0;i < labels.length;i++){
			int width = gc.textExtent(labels[i]).x;
			if(width + indent > standardLabelWidth){
				standardLabelWidth = width + indent;
			}
		}
		gc.dispose();
		return standardLabelWidth;
	}
	@Override
	public void dispose(){
		super.dispose();
		removeListener();
		if(getSectionComposite() != null){
			getSectionComposite().dispose();
		}
	}
	public abstract String getLabelText();
	public abstract Control getPrimaryInput();
	protected IMessageManager getMessageManager(){
		return messageManager;
	}
	protected void setDecorator(Control control,String message,int type){
		messageManager.addMessage("", message, null, type, control); //$NON-NLS-1$
	}
	protected void setErrorDecorator(Control control,String message){
		setDecorator(control, message, IMessageProvider.ERROR);
	}
	protected void setWarningDecorator(Control control,String message){
		setDecorator(control, message, IMessageProvider.WARNING);
	}
	protected void setInfoDecorator(Control control,String message){
		setDecorator(control, message, IMessageProvider.INFORMATION);
	}
	protected void clearDecorators(){
		messageManager.removeAllMessages();
	}
	protected RecursiveAdapter getModelListener(){
		return modelListener;
	}
	protected void handleModelChanged(Notification msg){
		Object notifier = msg.getNotifier();
		if(notifier.equals(getEObject()) && getFeature() != null){
			if(msg.getFeatureID(getSelectedObject().getClass()) == getFeature().getFeatureID()){
				populateControls();
			}
		}
	}
	protected void addListener(){
		if(getOpenUmlFile() != null){
			getOpenUmlFile().addSynchronizationListener(this);
		}
		if(getSelectedObject() == null){
			return;
		}
		if(!getSelectedObject().eAdapters().contains(getModelListener())){
			getModelListener().subscribeTo(getSelectedObject(), getModelListenerDepth());
		}
	}
	protected int getModelListenerDepth(){
		return 1;
	}
	protected void removeListener(){
		getModelListener().unsubscribe();
		if(getSelectedObject()!=null && getSelectedObject().eResource()!=null && getOpenUmlFile() != null){
			getOpenUmlFile().removeSynchronizationListener(this);
		}
	}
	public OpenUmlFile getOpenUmlFile(){
		if(openUmlFile == null && getSelectedObject() != null){
			openUmlFile = OpaeumEclipseContext.findOpenUmlFileFor(getSelectedObject());
		}
		return openUmlFile;
	}
	public void setInput(IWorkbenchPart part,ISelection selection){
		openUmlFile = null;
		brokenRules = null;
		super.setInput(part, selection);
		if(!(selection instanceof IStructuredSelection)){
			return;
		}
		Object ssel = ((IStructuredSelection) selection).getFirstElement();
		removeListener();
		eObject = EmfElementFinder.adaptObject(ssel);
		eObjectList = new ArrayList<EObject>();
		for(Iterator<?> iter = ((IStructuredSelection) selection).iterator();iter.hasNext();){
			EObject element = EmfElementFinder.adaptObject(iter.next());
			if(element != null){
				eObjectList.add(element);
			}
		}
		addListener();
	}
	@Deprecated
	// REname to selectedObject
	protected EObject getEObject(){
		return eObject;
	}
	protected List<EObject> getEObjectList(){
		return eObjectList;
	}
	public void aboutToBeHidden(){
	}
	public void aboutToBeShown(){
		addListener();
	}
	protected void updateModel(Object newValue){
		if(!isRefreshing){
			EditingDomain editingDomain = getEditingDomain();
			CompoundCommand compoundCommand = new CompoundCommand("Update");
			// apply the property change to all selected elements
			for(EObject nextObject:getEObjectList()){
				EObject owner = getFeatureOwner(nextObject);
				EStructuralFeature f = null;
				Object oldValue = null;
				if(owner != null){
					//Owner could be null for lazy creation
					f = getFeature(owner);
					oldValue = owner.eGet(f);
				}else{
					// Fallback
					f = getFeature();
				}
				maybeAppendCommand(editingDomain, compoundCommand, nextObject, owner, f, oldValue, newValue);
			}
			editingDomain.getCommandStack().execute(compoundCommand);
		}
	}
	protected void maybeAppendCommand(EditingDomain editingDomain,CompoundCommand compoundCommand,Object selectedObject,EObject featureOwner,
			EStructuralFeature f,Object oldValue,Object newValue){
		if(!equals(newValue, oldValue)){
			compoundCommand.append(createSingleCommand(editingDomain, newValue, f, featureOwner));
		}
	}
	protected Command createSingleCommand(EditingDomain editingDomain,Object value,EStructuralFeature f,EObject owner){
		return SetCommand.create(editingDomain, owner, f, value);
	}
	protected boolean equals(Object newValue,Object oldValue){
		if(newValue == null){
			return oldValue == null;
		}else if(oldValue == null){
			return newValue == null;
		}else{
			return oldValue.equals(newValue);
		}
	}
	protected EStructuralFeature getFeature(EObject nextObject){
		return getFeature();
	}
	@Override
	public final void refresh(){
		if(!(sectionComposite.isDisposed() || getSelectedObject().eContainer() == null)){// Hack - eclipse calls refresh even if the object was
																																											// deleted
			isRefreshing = true;
			super.refresh();
			populateControls();
			boolean isEnabled = true;
			if(eObjectList != null && eObjectList.size() > 0){
				Collection<EObject> eobjects = new ArrayList<EObject>(eObjectList.size() + 1);
				eobjects.addAll(eObjectList);
				eobjects.add(eObject);
				eobjects.remove(null);
				for(EObject e:eobjects){
					EditingDomain domain = AdapterFactoryEditingDomain.getEditingDomainFor(e);
					if(domain != null){
						isEnabled &= !domain.isReadOnly(e.eResource());
					}
				}
			}
			setEnabled(isEnabled && calculateEnabled());
			updateMessages();
			isRefreshing = false;
		}
	}
	protected void updateMessages(){
		messageManager.removeAllMessages();
		for(BrokenRule entry:getBrokenRules()){
			String msg = EmfValidationUtil.replaceArguments(getSelectedObject(), entry, entry.getRule().getMessagePattern());
			Control control = findAffectedControl(entry);
			if(control != null){
				messageManager.addMessage(UUID.randomUUID().toString(), msg, entry, IMessage.ERROR, control);
			}
		}
		messageManager.update();
	}
	protected Control findAffectedControl(BrokenRule entry){
		EStructuralFeature feature = getFeature(getFeatureOwner(getSelectedObject()));
		if(feature != null){
			for(EStructuralFeature eStructuralFeature:entry.getRule().getFeatures()){
				if(feature.equals(eStructuralFeature)){
					try{
						return getPrimaryInput();
					}catch(Exception e){
					}
				}
			}
		}
		return null;
	}
	protected boolean calculateEnabled(){
		return true;
	}
	protected void populateControls(){
	}
	public void makeContributions(IMenuManager menuManager,IToolBarManager toolBarManager,IStatusLineManager statLineManager){
		this.statusLineManager = statLineManager;
	}
	public IWorkbenchPage getActivePage(){
		IWorkbenchPage result = null;
		IWorkbench bench = PlatformUI.getWorkbench();
		if(bench != null){
			IWorkbenchWindow window = bench.getActiveWorkbenchWindow();
			if(window != null){
				result = window.getActivePage();
			}
		}
		return result;
	}
	protected Composite getSectionComposite(){
		return sectionComposite;
	}
	protected boolean isReadOnly(){
		Resource resource = getSelectedObject().eResource();
		EditingDomain domain = getEditingDomain();
		if(domain != null && resource != null && domain.isReadOnly(resource)){
			return true;
		}
		return false;
	}
	protected void setErrorMessage(String errorMessage){
		if(statusLineManager != null){
			statusLineManager.setErrorMessage(errorMessage);
		}
	}
	protected void setMessage(String message){
		// show the message
		if(statusLineManager != null){
			statusLineManager.setMessage(message);
		}
	}
	protected EditingDomain getEditingDomain(){
		IWorkbenchPart part = getPart();
		if(part.getAdapter(EditingDomain.class) != null){
			return (EditingDomain) getPart().getAdapter(EditingDomain.class);
		}
		if(part instanceof IEditingDomainProvider){
			return ((IEditingDomainProvider) part).getEditingDomain();
		}
		if(part.getAdapter(IEditingDomainProvider.class) != null){
			return ((IEditingDomainProvider) part.getAdapter(IEditingDomainProvider.class)).getEditingDomain();
		}
		if(part instanceof PageBookView){
			IPage page = ((PageBookView) part).getCurrentPage();
			if(page instanceof IEditingDomainProvider){
				return ((IEditingDomainProvider) page).getEditingDomain();
			}
		}
		throw new IllegalArgumentException();
	}
	protected IStatusLineManager getStatusLineManager(){
		return statusLineManager;
	}
	protected void setEnabled(boolean enabled){
	}
	protected void createWidgets(Composite composite){
		// Implement this method
	}
	protected void setSectionData(Composite composite){
		// Implement this method
	}
	protected void hookListeners(){
		// Implement this method
	}
	protected boolean isRefreshing(){
		return isRefreshing;
	}
	public static List<? extends AdapterFactory> getPrincipalAdapterFactories(){
		List<AdapterFactory> factories = new ArrayList<AdapterFactory>();
		// factories.add(new ResourceItemProviderAdapterFactory());
		// factories.add(new EcoreItemProviderAdapterFactory());
		// factories.add(new GenModelItemProviderAdapterFactory());
		// factories.add(new GenModelItemProviderAdapterFactory());
		return factories;
	}
	public Set<BrokenRule> getBrokenRules(){
		if(brokenRules == null){
			brokenRules = getOpenUmlFile().getEmfWorkspace().getErrorMap().findRelevantBrokenRules(getSelectedObject());
		}
		return brokenRules;
	}
	@Override
	public void onClose(OpenUmlFile openUmlFile){
	}
	@Override
	public void synchronizationComplete(OpenUmlFile openUmlFile,Set<Element> affectedElements){
		if(sectionComposite.isDisposed()){
			openUmlFile.removeSynchronizationListener(this);
		}else{
			Display.getDefault().asyncExec(new Runnable(){
				@Override
				public void run(){
					brokenRules = null;
					updateMessages();
				}
			});
		}
	}
}
