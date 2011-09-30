package org.opeum.topcased.propertysections;

import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.uml2.uml.Profile;
import org.eclipse.uml2.uml.Signal;
import org.eclipse.uml2.uml.Stereotype;
import org.opeum.eclipse.ProfileApplier;
import org.topcased.richtext.IRichText;
import org.topcased.richtext.RichText;
import org.topcased.richtext.RichTextToolBar;
import org.topcased.richtext.actions.AddImageAction;
import org.topcased.richtext.actions.AddLinkAction;
import org.topcased.richtext.actions.AddOrderedListAction;
import org.topcased.richtext.actions.AddTableAction;
import org.topcased.richtext.actions.AddUnorderedListAction;
import org.topcased.richtext.actions.BoldAction;
import org.topcased.richtext.actions.ClearContentAction;
import org.topcased.richtext.actions.DeleteTableColumnAction;
import org.topcased.richtext.actions.DeleteTableRowAction;
import org.topcased.richtext.actions.FindReplaceAction;
import org.topcased.richtext.actions.FontNameAction;
import org.topcased.richtext.actions.FontSizeAction;
import org.topcased.richtext.actions.IndentAction;
import org.topcased.richtext.actions.InsertTableColumnAction;
import org.topcased.richtext.actions.InsertTableRowAction;
import org.topcased.richtext.actions.ItalicAction;
import org.topcased.richtext.actions.JustifyCenterAction;
import org.topcased.richtext.actions.JustifyLeftAction;
import org.topcased.richtext.actions.JustifyRightAction;
import org.topcased.richtext.actions.OutdentAction;
import org.topcased.richtext.actions.SubscriptAction;
import org.topcased.richtext.actions.SuperscriptAction;
import org.topcased.richtext.actions.TextColorAction;
import org.topcased.richtext.actions.TextHighlightAction;
import org.topcased.richtext.actions.TidyActionGroup;
import org.topcased.richtext.actions.UnderlineAction;
import org.topcased.tabbedproperties.sections.AbstractTabbedPropertySection;

public class NotificationTemplateSection extends AbstractTabbedPropertySection{
	private RichText richText;
	@Override
	protected EStructuralFeature getFeature(){
		return null;
	}
	@Override
	protected void createWidgets(Composite composite){
		Composite toolbarComposite = getWidgetFactory().createComposite(composite, SWT.BORDER);
		GridLayout layout = new GridLayout(2, false);
		layout.marginWidth = 0;
		layout.marginHeight = 0;
		toolbarComposite.setLayout(layout);
		FormData tbfd = new FormData();
		tbfd.left = new FormAttachment(0, 0);
		// tbfd.right=new FormAttachment(100,0);
		tbfd.height = 27;
		toolbarComposite.setLayoutData(tbfd);
		Composite textComposite = getWidgetFactory().createComposite(composite, SWT.BORDER);
		textComposite.setLayout(new GridLayout(1, true));
		toolbarComposite.setBackground(textComposite.getBackground());
		FormData fd = new FormData();
		fd.top = new FormAttachment(toolbarComposite);
		fd.left = new FormAttachment(0, 0);
		fd.right = new FormAttachment(100, 0);
		fd.height = 200;
		textComposite.setLayoutData(fd);
		this.richText = new RichText(textComposite, 0);
		richText.addModifyListener(new ModifyListener(){
			@Override
			public void modifyText(ModifyEvent e){
				updateNotification();
			}
		});
		RichTextToolBar toolBar = new RichTextToolBar(toolbarComposite, SWT.FLAT, richText);
		// toolBar.addAction(new FontStyleAction(richText));
		toolBar.addAction(new FontNameAction(richText));
		toolBar.addAction(new FontSizeAction(richText));
		// toolBar.addSeparator();
		// toolBar.addAction(new CutAction(richText));
		// toolBar.addAction(new CopyAction(richText));
		// toolBar.addAction(new PasteAction(richText));
		// toolBar.addSeparator();
		toolBar.addAction(new ClearContentAction(richText));
		toolBar.addSeparator();
		toolBar.addAction(new BoldAction(richText));
		toolBar.addAction(new ItalicAction(richText));
		toolBar.addAction(new UnderlineAction(richText));
		toolBar.addSeparator();
		toolBar.addAction(new TextColorAction(richText));
		toolBar.addAction(new TextHighlightAction(richText));
		toolBar.addSeparator();
		toolBar.addAction(new SubscriptAction(richText));
		toolBar.addAction(new SuperscriptAction(richText));
		toolBar.addSeparator();
		toolBar.addAction(new TidyActionGroup(richText));
		toolBar.addSeparator();
		toolBar.addAction(new AddOrderedListAction(richText));
		toolBar.addAction(new AddUnorderedListAction(richText));
		toolBar.addSeparator();
		toolBar.addAction(new OutdentAction(richText));
		toolBar.addAction(new IndentAction(richText));
		toolBar.addSeparator();
		toolBar.addAction(new JustifyLeftAction(richText));
		toolBar.addAction(new JustifyCenterAction(richText));
		toolBar.addAction(new JustifyRightAction(richText));
		toolBar.addSeparator();
		toolBar.addAction(new FindReplaceAction(richText){
			/**
			 * @see org.topcased.richtext.actions.FindReplaceAction#execute(org.topcased.richtext.IRichText)
			 */
			@Override
			public void execute(IRichText rText){
				rText.getFindReplaceAction().execute(rText);
			}
		});
		toolBar.addSeparator();
		toolBar.addAction(new AddLinkAction(richText));
		toolBar.addAction(new AddImageAction(richText));
		toolBar.addSeparator();
		toolBar.addAction(new AddTableAction(richText));
		toolBar.addAction(new RichTextUploadAction(getSectionComposite().getShell(), richText){
			@Override
			public void execute(IRichText richText){
				super.execute(richText);
				updateNotification();
			}
		});
		// Only add these actions when IE is used to render the Browser
		if(Platform.getOS().equals("win32")){
			toolBar.addAction(new InsertTableColumnAction(richText));
			toolBar.addAction(new DeleteTableColumnAction(richText));
			toolBar.addAction(new InsertTableRowAction(richText));
			toolBar.addAction(new DeleteTableRowAction(richText));
		}
		toolbarComposite.layout();
		textComposite.layout();
	}
	@Override
	public void setInput(IWorkbenchPart part,ISelection selection){
		super.setInput(part, selection);
		Profile p = ProfileApplier.applyProfile(getSignal().getModel(), "OpeumProfileForBPM.uml");
		Stereotype not = p.getOwnedStereotype("Notification");
		if(getSignal().isStereotypeApplied(not)){
			richText.setText((String) getSignal().getValue(not, "template"));
		}else{
			richText.setText("");
		}
	}
	private void updateNotification(){
		Signal s = (Signal) getEObject();
		Profile p = ProfileApplier.applyProfile(s.getModel(), "OpeumProfileForBPM.uml");
		Stereotype not = p.getOwnedStereotype("Notification");
		if(!s.isStereotypeApplied(not)){
			s.applyStereotype(not);
		}
		s.setValue(not, "template", richText.getText());
	}
	private Signal getSignal(){
		return (Signal) getEObject();
	}
	@Override
	protected String getLabelText(){
		return "e-Mail Template";
	}
}
