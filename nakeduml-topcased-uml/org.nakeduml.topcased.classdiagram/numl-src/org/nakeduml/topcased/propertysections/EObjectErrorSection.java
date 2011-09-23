package org.nakeduml.topcased.propertysections;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.metamodel.validation.BrokenElement;
import net.sf.nakeduml.metamodel.validation.BrokenRule;
import net.sf.nakeduml.metamodel.validation.ErrorMap;
import net.sf.nakeduml.metamodel.validation.IValidationRule;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Path;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.ToolbarLayout;
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
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.forms.widgets.Hyperlink;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.NamedElement;
import org.nakeduml.eclipse.EmfElementFinder;
import org.nakeduml.topcased.uml.editor.NakedUmlEclipseContext;
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
			if(group.isDisposed()){
				ResourcesPlugin.getWorkspace().removeResourceChangeListener(this);
			}else{
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
		this.group.setLayout(new GridLayout(1, false));
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
		if(getEObject().eResource() != null){
			// could be deleting
			String string = getEObject().eResource().getURI().toPlatformString(false);
			if(string != null){
				Path path = new Path(string);
				// Could be plugin resource
				this.file = ResourcesPlugin.getWorkspace().getRoot().getFile(path);
			}
		}
	};
	@Override
	public void refresh(){
		super.refresh();
		if(file != null){
			Map<EObject,IMarker> markers = extractBrokenDescendants();
			for(Control control:group.getChildren()){
				control.dispose();
			}
			if(markers.isEmpty()){
				hide();
			}else{
				for(final Entry<EObject,IMarker> entry:markers.entrySet()){
					NakedUmlEclipseContext ctx = NakedUmlEditor.getCurrentContext();
					ErrorMap errorMap = ctx.getNakedWorkspace().getErrorMap();
					EObject key = entry.getKey();
					BrokenElement error = errorMap.getErrors().get(ctx.getCurrentEmfWorkspace().getId(key));
					for(Entry<IValidationRule,BrokenRule> brokenRule:error.getBrokenRules().entrySet()){
						String[] split = brokenRule.getKey().getMessagePattern().split("[\\{\\}]");
						Composite comp = getWidgetFactory().createComposite(group);
						GridLayout gl = new GridLayout(split.length, false);
						comp.setLayout(gl);
						gl.horizontalSpacing = 0;
						gl.marginWidth = 0;
						gl.marginHeight = 0;
						gl.verticalSpacing = 0;
						for(int i = 0;i < split.length;i++){
							createMessageFragment(key, comp, split[i], brokenRule.getValue().getParameters());
						}
						comp.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
						comp.layout();
						comp.pack();
					}
				}
				FormData fd = new FormData();
				fd.left = new FormAttachment(0);
				fd.right = new FormAttachment(100);
				fd.bottom = new FormAttachment(100);
				this.group.setLayoutData(fd);
				group.pack();
				group.layout();
			}
		}
	}
	protected void createMessageFragment(EObject key,Composite comp,String current,Object[] parameters){
		Control txt = null;
		if(current.length() >= 1 && current.length() <= 2 && Character.isDigit(current.charAt(0))){
			int parseInt = Integer.parseInt(current);
			Object o = parseInt == 0 ? key: parameters[parseInt-1];
			if(o instanceof INakedElement){
				INakedElement o1 = (INakedElement) o;
				txt = createHyperlink(comp, o1.getOwnerElement().getName() + "::" + o1.getName(), o1.getId());
			}else if(o instanceof Element){
				Element element = (Element) o;
				txt = createHyperlink(comp, getName((Element) EmfElementFinder.getContainer(element)) + "::" + getName(element), NakedUmlEditor.getCurrentContext().getId(element));
			}else{
				txt = getWidgetFactory().createLabel(comp, o.toString());
			}
		}else{
			txt = getWidgetFactory().createLabel(comp, current);
		}
		txt.pack();
		txt.setForeground(ColorConstants.red);
	}
	protected Map<EObject,IMarker> extractBrokenDescendants(){
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
		return markers;
	}
	protected Hyperlink createHyperlink(Composite comp,String text,String id){
		Hyperlink lbl = getWidgetFactory().createHyperlink(comp, text, SWT.NONE);
		final EObject key = NakedUmlEditor.getCurrentContext().getCurrentEmfWorkspace().getElement(id);
		lbl.addMouseListener(new MouseListener(){
			@Override
			public void mouseUp(MouseEvent e){
			}
			@Override
			public void mouseDown(MouseEvent e){
				if(getActivePage().getActiveEditor() instanceof NakedUmlEditor){
					NakedUmlEditor nakedUmlEditor = (NakedUmlEditor) getActivePage().getActiveEditor();
					nakedUmlEditor.gotoEObject(key);
					page.selectionChanged(nakedUmlEditor, new StructuredSelection(key));
				}
			}
			@Override
			public void mouseDoubleClick(MouseEvent e){
			}
		});
		return lbl;
	}
	protected String getName(Element element){
		return element instanceof NamedElement ? ((NamedElement) element).getName() : element.eClass().getName();
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
	public static void main(String[] args){
		String[] split = "asdf{1}dsfg{2}".split("[\\{\\}]");
		for(String string:split){
			System.out.println(string);
		}
	}
}
