package structuredbusiness.util;

import org.opaeum.runtime.domain.AbstractFormatter;

public class StructuredbusinessFormatter extends AbstractFormatter implements IStructuredbusinessFormatter {
	static final private ThreadLocal<StructuredbusinessFormatter> INSTANCE = new ThreadLocal<StructuredbusinessFormatter>();


	static public StructuredbusinessFormatter getInstance() {
		StructuredbusinessFormatter result = INSTANCE.get();
		if ( result==null ) {
			INSTANCE.set(result=new StructuredbusinessFormatter());
		}
		return result;
	}

}