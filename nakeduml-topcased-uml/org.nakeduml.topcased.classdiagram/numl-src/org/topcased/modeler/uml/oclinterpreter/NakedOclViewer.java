package org.topcased.modeler.uml.oclinterpreter;

import org.eclipse.jface.text.contentassist.ContentAssistEvent;
import org.eclipse.jface.text.contentassist.ICompletionListener;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.jface.text.contentassist.IContentAssistant;
import org.eclipse.jface.text.contentassist.IContentAssistantExtension2;
import org.eclipse.jface.text.source.IVerticalRuler;
import org.eclipse.jface.text.source.SourceViewer;
import org.eclipse.swt.widgets.Composite;
import org.topcased.modeler.uml.oclinterpreter.ColorManager;

public class NakedOclViewer extends SourceViewer{
	private long contentAssistLastActive;
	public NakedOclViewer(Composite parent,ColorManager colorManager,int styles){
		this(parent, colorManager, styles, null);
	}
	public NakedOclViewer(Composite parent,ColorManager colorManager,int styles,IVerticalRuler ruler){
		super(parent, ruler, styles);
		configure(new NakedOclConfiguration(colorManager));
		((IContentAssistantExtension2) fContentAssistant).addCompletionListener(new ICompletionListener(){
			public void assistSessionEnded(ContentAssistEvent event){
				contentAssistLastActive = System.currentTimeMillis();
			}
			public void assistSessionStarted(ContentAssistEvent event){
			}
			public void selectionChanged(ICompletionProposal proposal,boolean smartToggle){
			}
		});
	}
	public boolean isContentAssistActive(){
		return System.currentTimeMillis() - contentAssistLastActive < 200L;
	}
	public IContentAssistant getContentAssistant(){
		return fContentAssistant;
	}
}