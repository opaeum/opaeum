package net.sf.nakeduml.javageneration.hibernate.hbm;

import java.util.List;

import net.hibernatehbmmetamodel.HbmElement;
import net.hibernatehbmmetamodel.HibernateConfiguration;
import net.sf.nakeduml.javageneration.AbstractJavaProducingVisitor;
import net.sf.nakeduml.textmetamodel.TextOutputRoot;

public abstract class HbmJavaProducingVisitor extends AbstractJavaProducingVisitor {

	protected void createTextPath(HibernateConfiguration hibernateConfiguration,String outputRoot){
		try{
			TextOutputRoot or = textWorkspace.findOrCreateTextOutputRoot(outputRoot);
			List<String> names = hibernateConfiguration.getPath();
			or.findOrCreateTextFile(names, new HbmTextSource(hibernateConfiguration));
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	protected HbmElement findHbmElementFor(HbmElement hbmElement, String qualifiedName) {
		HbmElement result = hbmElement.findOwnedElement(qualifiedName);
		if (result!=null) {
			return result;
		}
		List<HbmElement> children = hbmElement.getOwnedElement();
		for (HbmElement child : children) {
			result = findHbmElementFor(child, qualifiedName);
			if (result!=null) {
				return result;
			}
		}
		return null;
	}

}
