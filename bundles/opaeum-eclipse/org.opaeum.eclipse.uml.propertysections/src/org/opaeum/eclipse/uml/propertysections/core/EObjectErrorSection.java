package org.opaeum.eclipse.uml.propertysections.core;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Set;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.forms.widgets.Hyperlink;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.NamedElement;
import org.opaeum.eclipse.EmfElementFinder;
import org.opaeum.eclipse.context.OpaeumEclipseContext;
import org.opaeum.eclipse.uml.propertysections.base.AbstractOpaeumPropertySection;
import org.opaeum.emf.workspace.EmfWorkspace;
import org.opaeum.metamodel.validation.BrokenElement;
import org.opaeum.metamodel.validation.BrokenRule;

//import org.eclipse.core.resources.ResourcesPlugin;
public class EObjectErrorSection extends AbstractOpaeumPropertySection{
	private Group group;
	@Override
	protected EStructuralFeature getFeature(){
		return null;
	}
	@Override
	public String getLabelText(){
		return null;
	}
	@Override
	public boolean shouldUseExtraSpace(){
		return true;
	}
	@Override
	protected void createWidgets(Composite composite){
		this.group = getWidgetFactory().createGroup(composite, "Errors");
		this.group.setLayout(new GridLayout(1, false));
		hide();
	}
	@Override
	public void createControls(Composite parent,TabbedPropertySheetPage aTabbedPropertySheetPage){
		super.createControls(parent, aTabbedPropertySheetPage);
	}
	@Override
	public void setInput(IWorkbenchPart part,org.eclipse.jface.viewers.ISelection selection){
		super.setInput(part, selection);
	};
	protected void updateMessages(){
		super.updateMessages();
		if(!group.isDisposed()){
			for(Control control:group.getChildren()){
				control.dispose();
			}
			EmfWorkspace ew = getOpenUmlFile().getEmfWorkspace();
			Collection<Entry<String,BrokenElement>> brokenElements = ew.getErrorMap().getErrors().entrySet();
			Set<BrokenRule> brokenRules=new HashSet<BrokenRule>();
			for(Entry<String,BrokenElement> brokenElement:brokenElements){
				//TODO add referenced elements too
				EObject modelElement = ew.getModelElement(brokenElement.getKey());
				while(modelElement!=null){
					if(getEObjectList().contains(modelElement)){
						brokenRules.addAll(brokenElement.getValue().getBrokenRules().values());
						modelElement=null;
					}else{
						modelElement=EmfElementFinder.getContainer(modelElement);
					}
				}
			}
			if(brokenRules.size() > 0){
				Set<BrokenRule> addedRules = new HashSet<BrokenRule>();
				// may have been delete since extraction
				for(BrokenRule brokenRule:brokenRules){
					if(!addedRules.contains(brokenRule)){
						addedRules.add(brokenRule);
						String[] split = brokenRule.getRule().getMessagePattern().split("[\\{\\}]");
						Composite comp = getWidgetFactory().createComposite(group);
						GridLayout gl = new GridLayout(split.length, false);
						comp.setLayout(gl);
						gl.horizontalSpacing = 0;
						gl.marginWidth = 0;
						gl.marginHeight = 0;
						gl.verticalSpacing = 0;
						EObject brokenElement = ew.getModelElement(brokenRule.getElementId());
						if(split.length == 1 && split[0].length() == 0){
							createMessageFragment(brokenElement, comp, brokenRule.getRule().name(), brokenRule.getParameters());
						}else{
							for(int i = 0;i < split.length;i++){
								createMessageFragment(brokenElement, comp, split[i], brokenRule.getParameters());
							}
						}
						comp.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
						comp.layout();
						comp.pack();
					}
				}
				FormData fd = new FormData();
				fd.left = new FormAttachment(0);
				fd.top = new FormAttachment(0);
				fd.right = new FormAttachment(100);
				fd.bottom = new FormAttachment(100);
				this.group.setLayoutData(fd);
				this.group.pack();
				getSectionComposite().getParent().setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
				getSectionComposite().setVisible(true);
				getSectionComposite().getParent().getParent().layout();
			}else{
				hide();
			}
		}
	}
	protected void createMessageFragment(EObject key,Composite comp,String current,Object[] parameters){
		Control txt = null;
		if(current.length() >= 1 && current.length() <= 2 && Character.isDigit(current.charAt(0))){
			int parseInt = Integer.parseInt(current);
			Object o = null;
			if(parseInt == 0){
				o = key;
			}else if(parameters.length > parseInt - 1){
				o = parameters[parseInt - 1];
			}
			if(o instanceof Element){
				Element element = (Element) o;
				txt = createHyperlink(comp, getName((Element) EmfElementFinder.getContainer(element)) + "::" + getName(element), EmfWorkspace.getId(element));
			}else if(o != null){
				txt = getWidgetFactory().createLabel(comp, o.toString());
			}else{
				txt = getWidgetFactory().createLabel(comp, "null");
			}
		}else{
			txt = getWidgetFactory().createLabel(comp, current);
		}
		txt.pack();
		txt.setForeground(ColorConstants.red);
	}
	protected Hyperlink createHyperlink(Composite comp,String text,String id){
		Hyperlink lbl = getWidgetFactory().createHyperlink(comp, text, SWT.NONE);
		final EObject key = OpaeumEclipseContext.findOpenUmlFileFor(getSelectedObject()).getEmfWorkspace().getModelElement(id);
		lbl.addMouseListener(new MouseListener(){
			@Override
			public void mouseUp(MouseEvent e){
			}
			@Override
			public void mouseDown(MouseEvent e){
				getOpenUmlFile().geteObjectSelectorUI().gotoEObject(key);
				getPropertySheetPage().selectionChanged(getActivePage().getActiveEditor(), new StructuredSelection(key));
			}
			@Override
			public void mouseDoubleClick(MouseEvent e){
			}
		});
		return lbl;
	}
	protected String getName(Element element){
		if(element == null){
			return "null";
		}
		return element instanceof NamedElement ? ((NamedElement) element).getName() : element.eClass().getName();
	}
	protected void hide(){
		getSectionComposite().getParent().setLayoutData(new GridData(0, 0));
		getSectionComposite().setVisible(false);
	}
	@Override
	public Control getPrimaryInput(){
		throw new IllegalStateException();
	}
}
