package org.opaeum.uim.userinteractionproperties.uimcontrol;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.common.command.AbstractCommand;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;
import org.eclipse.uml2.uml.TypedElement;
import org.opaeum.eclipse.context.OpaeumEclipseContext;
import org.opaeum.eclipse.context.OpenUmlFile;
import org.opaeum.eclipse.uml.propertysections.common.IChoiceProvider;
import org.opaeum.eclipse.uml.propertysections.common.OpaeumObjectChooser;
import org.opaeum.eclipse.uml.propertysections.core.EObjectNavigationSource;
import org.opaeum.eclipse.uml.propertysections.core.NavigationDecorator;
import org.opaeum.emf.workspace.EmfWorkspace;
import org.opaeum.uim.control.ControlPackage;
import org.opaeum.uim.control.UimLinkControl;
import org.opaeum.uim.editor.InstanceEditor;
import org.opaeum.uim.editor.provider.EditorItemProviderAdapterFactory;
import org.opaeum.uim.uml2uim.FormSynchronizer;
import org.opaeum.uim.util.UmlUimLinks;

public class UimLinkFeaturesComposite extends ControlFeaturesComposite<UimLinkControl> implements EObjectNavigationSource{
	private NavigationDecorator decorator = new NavigationDecorator(this);
	private CLabel label;
	private TabbedPropertySheetWidgetFactory factory;
	private OpaeumObjectChooser objectChooser;
	public UimLinkFeaturesComposite(Composite parent,int style,TabbedPropertySheetWidgetFactory factory){
		super(parent, style);
		setLayout(new GridLayout(2, false));
		this.factory = factory;
	}
	@Override
	public void createContent(){
		this.label = new CLabel(this, SWT.NONE);
		label.setText("Editor to Open:");
		GridData labelData = new GridData(143, 15);
		labelData.verticalAlignment = GridData.FILL;
		label.setLayoutData(labelData);
		this.objectChooser = new OpaeumObjectChooser(this, factory, SWT.BORDER);
		this.objectChooser.setLabelProvider(new AdapterFactoryLabelProvider(new EditorItemProviderAdapterFactory()));
		this.objectChooser.addSelectionChangedListener(new ISelectionChangedListener(){
			@Override
			public void selectionChanged(SelectionChangedEvent event){
				Command cmd = SetCommand.create(editingDomain, control, ControlPackage.eINSTANCE.getUimLinkControl_EditorToOpen(), objectChooser.getSelectedObject());
				editingDomain.getCommandStack().execute(cmd);
			}
		});
		GridData chooserData = new GridData(143, 25);
		chooserData.horizontalAlignment = GridData.FILL;
		chooserData.grabExcessHorizontalSpace = true;
		objectChooser.getControl().setLayoutData(chooserData);
	}
	@Override
	public void refresh(){
		if(control != null){
			final TypedElement te = UmlUimLinks.getCurrentUmlLinks(control).getResultingType(control.getField().getBinding());
			final Resource eResource = control.eResource();
			URI uri = eResource.getURI().trimSegments(1).appendSegment(EmfWorkspace.getId(te.getType())).appendFileExtension("uml");// TODO uml,
																																																															// yeah
			Resource resource = eResource.getResourceSet().getResource(uri, false);
			AbstractCommand cmd = new AbstractCommand(){
				@Override
				public boolean canExecute(){
					return true;
				}
				@Override
				public void execute(){
					EmfWorkspace workspace = UmlUimLinks.getCurrentUmlLinks(control).getEmfWorkspace();
					final FormSynchronizer fs = new FormSynchronizer(workspace, eResource.getResourceSet(), false);
					fs.visitOnly(te.getType());
				}
				@Override
				public void redo(){
				}
			};
			if(resource == null){
				editingDomain.getCommandStack().execute(cmd);
				resource = eResource.getResourceSet().getResource(uri, true);
			}else if(resource.getContents().isEmpty()){
				editingDomain.getCommandStack().execute(cmd);
			}
			TreeIterator<EObject> allContents = resource.getAllContents();
			final Collection<InstanceEditor> choices = new ArrayList<InstanceEditor>();
			while(allContents.hasNext()){
				EObject eObject = (EObject) allContents.next();
				if(eObject instanceof InstanceEditor){
					choices.add((InstanceEditor) eObject);
				}
			}
			this.objectChooser.setChoiceProvider(new IChoiceProvider(){
				@Override
				public Object[] getChoices(){
					return choices.toArray();
				}
			});
			if(control.getEditorToOpen() != null){
				this.objectChooser.setSelection(control.getEditorToOpen());
			}
		}
	}
	@Override
	public EObject getEObjectToGoTo(){
		if(control != null && control.getEditorToOpen() != null){
			return control.getEditorToOpen();
		}else{
			return null;
		}
	}
	@Override
	public CLabel getLabelCombo(){
		return label;
	}
	@Override
	public IWorkbenchPage getActivePage(){
		return PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
	}
	@Override
	public OpenUmlFile getOpenUmlFile(){
		return OpaeumEclipseContext.findOpenUmlFileFor(control);
	}
}
