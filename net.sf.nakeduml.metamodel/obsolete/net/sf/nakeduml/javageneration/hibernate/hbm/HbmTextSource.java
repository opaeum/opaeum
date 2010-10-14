package net.sf.nakeduml.javageneration.hibernate.hbm;

import net.hibernatehbmmetamodel.HibernateConfiguration;
import net.sf.nakeduml.textmetamodel.TextSource;

public class HbmTextSource implements TextSource {
	public static final String GEN_RESOURCE = "gen-resource";
	HibernateConfiguration hibernateConfiguration;

	public HbmTextSource(HibernateConfiguration hibernateConfiguration) {
		super();
		this.hibernateConfiguration = hibernateConfiguration;
	}

	public char[] toCharArray() {
		String s = hibernateConfiguration.getHbmString2();
		return s.toCharArray();
	}

	public boolean hasContent(){
		StringBuffer sb = new StringBuffer();
		String s = hibernateConfiguration.getHbmString2();
		sb.append(s);
		return sb.length()>0;
	}
}
