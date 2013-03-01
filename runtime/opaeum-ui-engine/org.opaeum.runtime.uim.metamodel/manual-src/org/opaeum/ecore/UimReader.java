package org.opaeum.ecore;

import java.beans.PropertyDescriptor;
import java.io.File;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.opaeum.runtime.domain.IntrospectionUtil;
import org.opaeum.runtime.rwt.UimResourceSet;

public class UimReader{
	static PrintWriter out;
	public static void main(String[] args) throws Exception{
		out=new PrintWriter(new File("uim.txt"));
		File dir = new File("/home/ampie/NewWorkspaces/opaeum/examples/org.opaeum.demo1.app/ui");
		UimResourceSet rst = new UimResourceSet(dir);
		for(File file:dir.listFiles()){
			Set<EObject> printed = new HashSet<EObject>();
			out.println("Printing " + file.getName());
			show("", rst.getResource(file.getName()).getRoot(), printed);
		}
		out.flush();
		out.close();
	}
	protected static void show(String padding,EObject e,Set<EObject> printed){
		printed.add(e);
		out.println(padding + e.getClass().getSimpleName());
		for(PropertyDescriptor pd:IntrospectionUtil.getProperties(e.getClass())){
			Object object = IntrospectionUtil.get(pd, e);
			if(object instanceof EObject){
				if(printed.contains(object)){
					out.println(padding + pd.getName() + "=" + ((EObject) object).getUid());
				}else{
					out.println(padding + pd.getName() + "=");
					show(padding + "  ", (EObject) object, printed);
				}
			}else if(object instanceof Collection){
				out.println(padding + pd.getName() + "=");
				Collection<EObject> ch = (Collection<EObject>) object;
				for(EObject eObject:ch){
					out.println(padding + "{");
					show(padding + "  ", eObject, printed);
					out.println(padding +  "}");
				}
			}else{
				out.println(padding + pd.getName() + "=" + object);
			}
		}
	}
}
