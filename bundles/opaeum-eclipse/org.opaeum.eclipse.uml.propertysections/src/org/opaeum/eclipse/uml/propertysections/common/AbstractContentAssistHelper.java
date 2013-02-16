package org.opaeum.eclipse.uml.propertysections.common;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.eclipse.jface.fieldassist.ContentProposalAdapter;
import org.eclipse.jface.fieldassist.IContentProposalListener2;
import org.eclipse.jface.fieldassist.SimpleContentProposalProvider;
import org.eclipse.jface.fieldassist.TextContentAdapter;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.fieldassist.ContentAssistCommandAdapter;
import org.opaeum.eclipse.uml.propertysections.common.ChooserContentAssistHelper.ChooserContentProposalProvider;

public abstract class AbstractContentAssistHelper{
	protected Text text;
	public abstract TextContentAdapter createTextContentAdaptor();
	public abstract SimpleContentProposalProvider createContentProposalProvider();
	public abstract char[] getTriggerChars();
	protected ContentAssistCommandAdapter cpa;
	protected Field popupField;
	protected Method asyncRecomputeProposalsMethod;
	protected Window popup;
	private SimpleContentProposalProvider proposalProvider;
	public AbstractContentAssistHelper(Text t){
		super();
		text = t;
	}
	public void init(){
		text.setEditable(true);
		text.addModifyListener(new ModifyListener(){
			@Override
			public void modifyText(ModifyEvent event){
				if(asyncRecomputeProposalsMethod != null && popup != null){
					try{
						// TEmp hack to filter the popup- there seems to be some confusion with where the keystrokes go,perhaps a Linux propblem
						asyncRecomputeProposalsMethod.invoke(popup, text.getText());
					}catch(Exception e){
						e.printStackTrace();
					}
				}
			}
		});
		this.proposalProvider = createContentProposalProvider();
		this.cpa = new ContentAssistCommandAdapter(text, createTextContentAdaptor(), proposalProvider, null, getTriggerChars(), true);
		cpa.setPopupSize(new Point(300, 300));
		cpa.setPropagateKeys(true);
		cpa.setFilterStyle(ContentAssistCommandAdapter.FILTER_CHARACTER);
		cpa.addContentProposalListener(new IContentProposalListener2(){
			@Override
			public void proposalPopupOpened(ContentProposalAdapter adapter){
				try{
					// Hack to filter the popup
					if(popupField == null){
						popupField = ContentProposalAdapter.class.getDeclaredField("popup");
						popupField.setAccessible(true);
					}
					popup = (Window) popupField.get(cpa);
					if(popup != null){
						if(asyncRecomputeProposalsMethod == null){
							asyncRecomputeProposalsMethod = popup.getClass().getDeclaredMethod("asyncRecomputeProposals", String.class);
							asyncRecomputeProposalsMethod.setAccessible(true);
						}
					}
				}catch(Exception e){
					e.printStackTrace();
				}
			}
			@Override
			public void proposalPopupClosed(ContentProposalAdapter adapter){
			}
		});
	}
}