package org.opaeum.uim.userinteractionproperties.uimcontrol;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import org.eclipse.emf.common.command.AbstractCommand;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.internal.UIPlugin;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;
import org.eclipse.uml2.uml.TypedElement;
import org.opaeum.eclipse.OpaeumElementLinker;
import org.opaeum.emf.workspace.EmfWorkspace;
import org.opaeum.topcased.propertysections.EObjectNavigationSource;
import org.opaeum.topcased.propertysections.NavigationDecorator;
import org.opaeum.uim.control.ControlPackage;
import org.opaeum.uim.control.UimLinkControl;
import org.opaeum.uim.editor.ClassEditor;
import org.opaeum.uim.editor.provider.EditorItemProviderAdapterFactory;
import org.opaeum.uim.uml2uim.FormSynchronizer;
import org.opaeum.uim.uml2uim.UimSynchronizationPhase;
import org.opaeum.uim.util.UmlUimLinks;
import org.topcased.tabbedproperties.sections.widgets.CSingleObjectChooser;

public class UimLinkFeaturesComposite extends ControlFeaturesComposite<UimLinkControl> implements EObjectNavigationSource{
	private NavigationDecorator decorator = new NavigationDecorator(this);
	private CLabel label;
	private TabbedPropertySheetWidgetFactory factory;
	private CSingleObjectChooser objectChooser;
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
		this.objectChooser = new CSingleObjectChooser(this, factory, SWT.BORDER);
		this.objectChooser.setLabelProvider(new AdapterFactoryLabelProvider(new EditorItemProviderAdapterFactory()));
		this.objectChooser.addSelectionListener(new SelectionListener(){
			@Override
			public void widgetSelected(SelectionEvent e){
				Command cmd = SetCommand.create(editingDomain, control, ControlPackage.eINSTANCE.getUimLinkControl_EditorToOpen(),
						objectChooser.getSelection());
				editingDomain.getCommandStack().execute(cmd);
			}
			@Override
			public void widgetDefaultSelected(SelectionEvent e){
			}
		});
		GridData chooserData = new GridData(143, 25);
		chooserData.horizontalAlignment= GridData.FILL;
		chooserData.grabExcessHorizontalSpace=true;
		objectChooser.setLayoutData(chooserData);
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
			Collection<ClassEditor> choices = new ArrayList<ClassEditor>();
			while(allContents.hasNext()){
				EObject eObject = (EObject) allContents.next();
				if(eObject instanceof ClassEditor){
					choices.add((ClassEditor) eObject);
				}
			}
			this.objectChooser.setChoices(choices.toArray());
			if(control.getEditorToOpen()!=null){
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
		return UIPlugin.getDefault().getWorkbench().getActiveWorkbenchWindow().getActivePage();
	}
}
