package defaultname.util;

import org.opaeum.runtime.domain.AbstractFormatter;

public class DefaultNameFormatter extends AbstractFormatter implements IDefaultNameFormatter {
	static final private ThreadLocal<DefaultNameFormatter> INSTANCE = new ThreadLocal<DefaultNameFormatter>();


	static public DefaultNameFormatter getInstance() {
		DefaultNameFormatter result = INSTANCE.get();
		if ( result==null ) {
			INSTANCE.set(result=new DefaultNameFormatter());
		}
		return result;
	}

}