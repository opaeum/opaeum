package org.nakeduml.uim.userinteractionproperties.sections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.StringTokenizer;

import org.eclipse.jface.fieldassist.ContentProposal;
import org.eclipse.jface.fieldassist.IContentProposal;
import org.eclipse.jface.fieldassist.SimpleContentProposalProvider;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.TypedElement;
import org.nakeduml.eclipse.ElementFinder;

public class TypedElementContentProposalProvider extends SimpleContentProposalProvider{
	private final TypedElementCodeCompletingSection codeCompletingSection;
	TypedElementContentProposalProvider(TypedElementCodeCompletingSection uimFieldBindingSection){
		super(new String[0]);
		this.codeCompletingSection = uimFieldBindingSection;
	}

	public IContentProposal[] getProposals(String contents,int position){
		StringTokenizer st = new StringTokenizer(contents, ".");
		if(st.hasMoreTokens()){
			String teName = st.nextToken();
			if(!st.hasMoreElements()){
				TypedElement te = this.codeCompletingSection.getTypedElement(teName);
				if(te != null){
					Classifier c = (Classifier) te.getType();
					List<IContentProposal> list = filterTypedElements(null, ElementFinder.getTypedElementsInScope(c));
					return (IContentProposal[]) list.toArray(new IContentProposal[list.size()]);
				}else{
					Collection<? extends TypedElement> typedElements = this.codeCompletingSection.getTypedElements();
					List<IContentProposal> list = filterTypedElements(teName, typedElements);
					return (IContentProposal[]) list.toArray(new IContentProposal[list.size()]);
				}
			}else{
				Classifier type = (Classifier) this.codeCompletingSection.getTypedElement(teName).getType();
				while(st.hasMoreElements()){
					String nextName = st.nextToken();
					Property p = this.codeCompletingSection.getProperty(type, nextName);
					if(p == null){
						List<IContentProposal> list = filterTypedElements(nextName, ElementFinder.getTypedElementsInScope(type));
						return (IContentProposal[]) list.toArray(new IContentProposal[list.size()]);
					}
					type = (Classifier) p.getType();
				}
				List<IContentProposal> list = filterTypedElements(null, type.getAllAttributes());
				return (IContentProposal[]) list.toArray(new IContentProposal[list.size()]);
			}
		}else{
			List<IContentProposal> list = filterTypedElements(null, this.codeCompletingSection.getTypedElements());
			return (IContentProposal[]) list.toArray(new IContentProposal[list.size()]);
		}
	}
	public List<IContentProposal> filterTypedElements(String teName,Collection<? extends TypedElement> typedElements){
		List<IContentProposal> list = new ArrayList<IContentProposal>();
		for(TypedElement te:typedElements){
			if(teName == null || te.getName().startsWith(teName)){
				list.add(new ContentProposal(te.getName()));
			}
		}
		return list;
	}
}