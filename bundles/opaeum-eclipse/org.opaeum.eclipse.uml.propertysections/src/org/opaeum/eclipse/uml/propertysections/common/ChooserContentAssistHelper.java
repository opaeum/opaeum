package org.opaeum.eclipse.uml.propertysections.common;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import org.eclipse.jface.fieldassist.ContentProposal;
import org.eclipse.jface.fieldassist.ContentProposalAdapter;
import org.eclipse.jface.fieldassist.IContentProposal;
import org.eclipse.jface.fieldassist.IContentProposalListener2;
import org.eclipse.jface.fieldassist.SimpleContentProposalProvider;
import org.eclipse.jface.fieldassist.TextContentAdapter;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.fieldassist.ContentAssistCommandAdapter;
import org.eclipse.uml2.uml.NamedElement;

public class ChooserContentAssistHelper extends AbstractContentAssistHelper{
	private ChooserContentProposalProvider proposalProvider;
	public ChooserContentAssistHelper(Text t){
		super(t);
		init();
	}
	@Override
	public char[] getTriggerChars(){
		return new char[]{':',' '};
	}
	@Override
	public ChooserContentProposalProvider createContentProposalProvider(){
		return proposalProvider= new ChooserContentProposalProvider();
	}
	@Override
	public TextContentAdapter createTextContentAdaptor(){
		return new TextContentAdapter(){
			@Override
			public void insertControlContents(Control control,String v,int cursorPosition){
				// super.insertControlContents(control, v, cursorPosition);
				Text txtCtrl = (Text) control;
				// String curValue = txtCtrl.getText();
				// String pre = curValue.substring(0, cursorPosition);
				// String post = curValue.substring(cursorPosition);
				txtCtrl.setText(v);
				setCursorPosition(control, v.length());
			}
		};
	}
	public void setChoiceProvider(IChoiceProvider choiceProvider){
		proposalProvider.setChoiceProvider(choiceProvider);
	}
	public NamedElement getSelectedObject(String e){
		return proposalProvider.getSelectedObject(e);
	}
	class ChooserContentProposalProvider extends SimpleContentProposalProvider{
		private SortedMap<String,NamedElement> options = new TreeMap<String,NamedElement>();
		private IChoiceProvider choiceProvider;
		public ChooserContentProposalProvider(){
			super(new String[0]);
		}
		public void setChoiceProvider(IChoiceProvider choiceProvider){
			this.choiceProvider = choiceProvider;
		}
		public IContentProposal[] getProposals(String contents,int position){
			SortedMap<String,NamedElement> options = getOptions();
			List<IContentProposal> list = new ArrayList<IContentProposal>();
			if(contents.isEmpty()){
				for(String s:options.keySet()){
					list.add(new ContentProposal(s));
				}
			}else{
				String match = contents.substring(0, Math.min(contents.length(), position));// Could be called before value committed
				for(String s:options.keySet()){
					if(s.startsWith(match)){
						list.add(new ContentProposal(s));
					}
				}
			}
			return (IContentProposal[]) list.toArray(new IContentProposal[list.size()]);
		}
		private SortedMap<String,NamedElement> getOptions(){
			if(options == null || options.isEmpty()){
				options = new TreeMap<String,NamedElement>();
				for(Object object:choiceProvider.getChoices()){
					if(object instanceof NamedElement){
						NamedElement ne = (NamedElement) object;
						options.put(ne.getName(), ne);
						options.put(ne.getQualifiedName(), ne);
					}
				}
			}
			return options;
		}
		public NamedElement getSelectedObject(String e){
			return getOptions().get(e);
		}
	}
}