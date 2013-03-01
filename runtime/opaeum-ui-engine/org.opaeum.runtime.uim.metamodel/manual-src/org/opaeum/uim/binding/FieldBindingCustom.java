package org.opaeum.uim.binding;
import org.opaeum.uim.binding.FieldBindingImpl;
import org.opaeum.uim.binding.PropertyRef;
import org.opaeum.uim.binding.UimBinding;


public class FieldBindingCustom extends FieldBindingImpl{
	@Override
	public String getLastPropertyUuid(){
		UimBinding source=this;
		String lastePropertyUuid = getLastePropertyUuid(source);
		if(lastePropertyUuid==null){
			System.out.println();
		}
		return lastePropertyUuid;
	}

	public static String getLastePropertyUuid(UimBinding source){
		if(source.getNext()==null){
			return source.getUmlElementUid();
		}else{
			return getLastPropertyUuid(source.getNext());
		}
	}

	private static String getLastPropertyUuid(PropertyRef next){
		String result=null;
		if(next.getNext()==null){
			result=next.getUmlElementUid();
		}else{
			result=getLastPropertyUuid(next.getNext());
		}
		return result;
	}
}
