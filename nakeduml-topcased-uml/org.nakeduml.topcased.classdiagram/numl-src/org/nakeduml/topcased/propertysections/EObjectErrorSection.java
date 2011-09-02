package org.nakeduml.topcased.propertysections;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Path;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EValidator;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.forms.widgets.Hyperlink;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.nakeduml.topcased.uml.editor.NakedUmlEditor;
import org.topcased.tabbedproperties.sections.AbstractTabbedPropertySection;

public class EObjectErrorSection extends AbstractTabbedPropertySection implements IResourceChangeListener{
	private Group group;
	private TabbedPropertySheetPage page;
	private IFile file;
	@Override
	protected EStructuralFeature getFeature(){
		return null;
	}
	@Override
	protected String getLabelText(){
		return "Errors";
	}
	@Override
	public void dispose(){
		ResourcesPlugin.getWorkspace().removeResourceChangeListener(this);
		super.dispose();
	};
	@Override
	public void resourceChanged(IResourceChangeEvent event){
		IResourceDelta delta = event.getDelta();
		if(delta.getKind() == IResourceDelta.CHANGED && delta.findMember(file.getFullPath()) != null){
			if(!group.isDisposed()){
				Display.getDefault().syncExec(new Runnable(){
					@Override
					public void run(){
						refresh();
					}
				});
			}
		}
	}
	@Override
	protected void createWidgets(Composite composite){
		ResourcesPlugin.getWorkspace().addResourceChangeListener(this);
		this.group = getWidgetFactory().createGroup(composite, "Errors");
		this.group.setLayout(new GridLayout(1, true));
		hide();
	}
	@Override
	public void createControls(Composite parent,TabbedPropertySheetPage aTabbedPropertySheetPage){
		super.createControls(parent, aTabbedPropertySheetPage);
		this.page = aTabbedPropertySheetPage;
	}
	@Override
	public void setInput(IWorkbenchPart part,org.eclipse.jface.viewers.ISelection selection){
		super.setInput(part, selection);
		this.file = ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(getEObject().eResource().getURI().toPlatformString(false)));
	};
	@Override
	public void refresh(){
		super.refresh();
		Map<EObject,IMarker> markers = new HashMap<EObject,IMarker>();
		if(getEObject().eResource() != null){
			try{
				for(IMarker m:file.findMarkers(EValidator.MARKER, true, 0)){
					String attribute = (String) m.getAttribute(EValidator.URI_ATTRIBUTE);
					if(attribute != null){
						URI createURI = URI.createURI(attribute, false);
						EObject problemElement = getEObject().eResource().getEObject(createURI.fragment());
						EObject eo = problemElement;
						while(eo != null){
							if(eo == getEObject()){
								markers.put(problemElement, m);
								break;
							}else{
								eo = eo.eContainer();
							}
						}
					}
				}
			}catch(CoreException e1){
				e1.printStackTrace();
			}
		}
		for(Control control:group.getChildren()){
			control.dispose();
		}
		if(markers.isEmpty()){
			hide();
		}else{
			for(final Entry<EObject,IMarker> entry:markers.entrySet()){
				try{
					String msg = (String) entry.getValue().getAttribute(IMarker.MESSAGE);
					if(msg != null){
						Hyperlink lbl = getWidgetFactory().createHyperlink(group, msg, SWT.BORDER);
						lbl.addMouseListener(new MouseListener(){
							@Override
							public void mouseUp(MouseEvent e){
							}
							@Override
							public void mouseDown(MouseEvent e){
								if(getActivePage().getActiveEditor() instanceof NakedUmlEditor){
									NakedUmlEditor nakedUmlEditor = (NakedUmlEditor) getActivePage().getActiveEditor();
									nakedUmlEditor.gotoEObject(entry.getKey());
									page.selectionChanged(nakedUmlEditor, new StructuredSelection(entry.getKey()));
								}
							}
							@Override
							public void mouseDoubleClick(MouseEvent e){
							}
						});
						lbl.setForeground(ColorConstants.red);
					}
				}catch(CoreException e){
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			FormData fd = new FormData();
			fd.left = new FormAttachment(0);
			fd.right = new FormAttachment(100);
			fd.bottom = new FormAttachment(100);
			this.group.setLayoutData(fd);
			group.layout();
		}
		getSectionComposite().getParent().getParent().layout();
	}
	protected void hide(){
		FormData fd = new FormData(0, 0);
		fd.left = new FormAttachment(0, 0);
		fd.right = new FormAttachment(0, 0);
		fd.top = new FormAttachment(0, 0);
		fd.bottom = new FormAttachment(0, 0);
		this.group.setSize(0, 0);
		this.group.setLayoutData(fd);
	}
}
