package org.opaeum.uim.userinteractionproperties.sections;

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
import org.opaeum.eclipse.EmfElementFinder;
import org.opaeum.eclipse.EmfPropertyUtil;

public class TypedElementContentProposalProvider extends SimpleContentProposalProvider{
	private final ITypedElementProvider codeCompletingSection;
	TypedElementContentProposalProvider(ITypedElementProvider uimFieldBindingSection){
		super(new String[0]);
		this.codeCompletingSection = uimFieldBindingSection;
	}
	public IContentProposal[] getProposals(String contents,int position){
		StringTokenizer st = new StringTokenizer(contents, ".");
		if(st.hasMoreTokens()){
			String teName = st.nextToken();
			TypedElement te = this.codeCompletingSection.getTypedElement(teName);
			if(!st.hasMoreElements()){
				if(te != null){
					if(EmfPropertyUtil.isMany(te)){
						return new IContentProposal[0];
					}else{
						Classifier c = (Classifier) te.getType();
						List<IContentProposal> list = filterTypedElements(null, EmfElementFinder.getTypedElementsInScope(c));
						return (IContentProposal[]) list.toArray(new IContentProposal[list.size()]);
					}
				}else{
					Collection<? extends TypedElement> typedElements = this.codeCompletingSection.getTypedElements();
					List<IContentProposal> list = filterTypedElements(teName, typedElements);
					return (IContentProposal[]) list.toArray(new IContentProposal[list.size()]);
				}
			}else if(te != null){
				Classifier type = (Classifier) te.getType();
				while(st.hasMoreElements()){
					String nextName = st.nextToken();
					Property p = this.codeCompletingSection.getProperty(type, nextName);
					if(p == null){
						if(EmfPropertyUtil.isMany(p)){
							return new IContentProposal[0];
						}else{
							List<IContentProposal> list = filterTypedElements(nextName, EmfElementFinder.getTypedElementsInScope(type));
							return (IContentProposal[]) list.toArray(new IContentProposal[list.size()]);
						}
					}else{
						te = p;
					}
					type = (Classifier) p.getType();
				}
				if(EmfPropertyUtil.isMany(te)){
					return new IContentProposal[0];
				}else{
					List<IContentProposal> list = filterTypedElements(null, EmfElementFinder.getTypedElementsInScope(type));
					return (IContentProposal[]) list.toArray(new IContentProposal[list.size()]);
				}
			}else{
				return new IContentProposal[0];
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