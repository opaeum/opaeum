package org.opaeum.topcased.propertysections.property.roleincube;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.jface.fieldassist.ContentProposal;
import org.eclipse.jface.fieldassist.IContentProposal;
import org.eclipse.jface.fieldassist.SimpleContentProposalProvider;
import org.eclipse.uml2.uml.Stereotype;
import org.eclipse.uml2.uml.TypedElement;
import org.opaeum.metamodel.core.internal.TagNames;
import org.opaeum.name.NameConverter;

public class TypedElementContentProposalProvider extends SimpleContentProposalProvider{
	private SortedSet<String> options = new TreeSet<String>();
	private Stereotype stereotype;
	public TypedElementContentProposalProvider(Stereotype stereotype,Collection<? extends TypedElement> peers){
		super(new String[0]);
		this.stereotype = stereotype;
		for(TypedElement typedElement:peers){
			if(typedElement.getStereotypeApplication(stereotype) != null){
				Collection<EEnumLiteral> a = (Collection<EEnumLiteral>) typedElement.getValue(stereotype, TagNames.AGGREGATION_FORMULAS);
				for(EEnumLiteral enumerationLiteral:a){
					options.add(NameConverter.capitalize(enumerationLiteral.getName().toLowerCase() + "Of" + NameConverter.capitalize(typedElement.getName())));
				}
			}
		}
	}
	public IContentProposal[] getProposals(String contents,int position){
		List<IContentProposal> list = new ArrayList<IContentProposal>();
		int i = 0;
		for(i = position;i > 0;i--){
			if(!Character.isJavaIdentifierPart(contents.charAt(i-1))){
				break;
			}
		}
		if(position == i||position==0){
			for(String s:options){
				list.add(new ContentProposal(s));
			}
		}else{
			String match = contents.substring(i, position);
			for(String s:options){
				if(s.startsWith(match)){
					list.add(new ContentProposal(s));
				}
			}
		}
		return (IContentProposal[]) list.toArray(new IContentProposal[list.size()]);
	}
}