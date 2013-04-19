package org.opaeum.eclipse.uml.propertysections.base;

import org.eclipse.uml2.uml.LiteralUnlimitedNatural;

public abstract class AbstractIntegerPropertySection extends AbstractStringPropertySection{
	@Override
	protected Object getNewFeatureValue(String newText){
		
		//TODO validate
		Integer integer = null;
		try{
			integer = Integer.valueOf(text.getText());
		}catch(Exception e){
		}
		if(integer == null && "*".equals(text.getText())){
			integer = LiteralUnlimitedNatural.UNLIMITED;
		}
		return integer;
	}
}
