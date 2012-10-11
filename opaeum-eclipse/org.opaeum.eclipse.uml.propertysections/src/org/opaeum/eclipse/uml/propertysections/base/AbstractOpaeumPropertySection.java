package org.opaeum.eclipse.uml.propertysections.base;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertyConstants;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;
import org.topcased.tabbedproperties.internal.utils.MessageManager;
import org.topcased.tabbedproperties.sections.AbstractTabbedPropertySection;

public abstract class AbstractOpaeumPropertySection extends AbstractTabbedPropertySection{
	private Integer labelWidth;
	protected CLabel labelCombo;
	private TabbedPropertySheetPage propertySheetPage;
	private Composite sectionComposite;
	private MessageManager messageManager;
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
	protected Composite getSectionComposite(){
		return sectionComposite;
	}
	@Override
	public void createControls(Composite parent,TabbedPropertySheetPage page){
		this.propertySheetPage = page;
		if(page instanceof IOpaeumTabbedPropertySheetPage){
			IOpaeumTabbedPropertySheetPage o = (IOpaeumTabbedPropertySheetPage) page;
			labelWidth = o.getLabelWidth();
		}else{
			labelWidth = null;
		}
		sectionComposite = new Composite(parent, SWT.BORDER);// getMainComposite(parent);
		FormLayout layout = new FormLayout();
		layout.marginWidth = ITabbedPropertyConstants.HSPACE + 2;
		layout.marginHeight = ITabbedPropertyConstants.VSPACE;
		layout.spacing = ITabbedPropertyConstants.VMARGIN + 1;
		sectionComposite.setLayout(layout);
		getWidgetFactory().adapt(sectionComposite);
		labelCombo = getWidgetFactory().createCLabel(sectionComposite, getLabelText());
		FormData ld = new FormData();
		ld.left = new FormAttachment(0, 0);
		ld.top=new FormAttachment(0,0);
		ld.bottom=new FormAttachment(100,0);
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
		messageManager = new MessageManager();
		IActionBars actionBars = page.getSite().getActionBars();
		makeContributions(actionBars.getMenuManager(), actionBars.getToolBarManager(), actionBars.getStatusLineManager());
	}
	public void setLabelWidth(int labelWidth){
		this.labelWidth = labelWidth;
	}
	@Override
	protected int getStandardLabelWidth(Composite parent,String[] labels){
		if(labelWidth == null){
			return super.getStandardLabelWidth(parent, labels);
		}
		return labelWidth;
	}
	@Override
	public abstract String getLabelText();
	public abstract Control getPrimaryInput();
	@Override
	protected void clearDecorators(){
		messageManager.removeAllMessages();
	}
	@Override
	protected MessageManager getMessageManager(){
		return messageManager;
	}
	@Override
	protected void setDecorator(Control control,String message,int type){
		messageManager.addMessage("", message, null, type, control); //$NON-NLS-1$
	}
}
