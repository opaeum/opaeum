package org.opaeum.runtime.hibernate.test;

import java.lang.reflect.Modifier;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtConstructor;
import javassist.CtField;
import javassist.CtMethod;
import javassist.CtNewConstructor;
import javassist.CtNewMethod;

import org.hibernate.intercept.cglib.CGLIBHelper;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.InitializationError;
import org.opaeum.runtime.domain.IPersistentObject;

public class PersistenceInterceptor extends BlockJUnit4ClassRunner {

	private Class<?> instrumentedClass;

	public PersistenceInterceptor(Class<?> klass) throws InitializationError {
		super(instrument(klass));
	}

	// @Override
	// protected Object createTest() throws Exception {
	// return instrument(super.getTestClass().getJavaClass()).newInstance();
	// }

	private static Class<?> instrument(Class<?> klass) {
		// if (instrumentedClass == null) {
		try {
			String name = klass.getName();

			CtClass ctc = ClassPool.getDefault().makeClass(name + "1",
					ClassPool.getDefault().get(name));
			CtClass iPersistentObject = ClassPool.getDefault().get(
					IPersistentObject.class.getName());
			CtMethod[] methods = ctc.getMethods();
			CtField fld = new CtField(
					ClassPool
							.getDefault()
							.get("org.opaeum.runtime.hibernate.test.HibernatePersistenceTestHelper"),
					"helper", ctc);
			ctc.addField(fld);
			CtConstructor dc = CtNewConstructor.defaultConstructor(ctc);
			ctc.addConstructor(dc);
			dc.insertAfter("helper=new org.opaeum.runtime.hibernate.test.HibernatePersistenceTestHelper();");
			for (CtMethod ctMethod : methods) {
				if (ctMethod.getName().startsWith("assert")
						&& ctMethod.getMethodInfo().getCodeAttribute() != null
						&& !Modifier.isFinal(ctMethod.getModifiers())) {

					StringBuilder sb = new StringBuilder("public  ");
					sb.append(ctMethod.getReturnType().getName());
					sb.append(" ");
					sb.append(ctMethod.getName() + "(");
					CtClass[] parameterTypes = ctMethod.getParameterTypes();
					for (int i = 0; i < parameterTypes.length; i++) {
						sb.append(parameterTypes[i].getName());
						sb.append(' ');
						sb.append("param");
						sb.append(i);
						if (i < parameterTypes.length - 1) {
							sb.append(",");
						}

					}
					sb.append("){\n");
					sb.append("System.out.println(\"asdfasdfsadfasdfasdfasdfa\");\n");
					for (int i = 0; i < parameterTypes.length; i++) {
						if (!parameterTypes[i].isPrimitive()
								&& parameterTypes[i]
										.subtypeOf(iPersistentObject)) {
							sb.append("if(param" + i + ".getId()==null){"
									+ "helper.persist(param" + i + ");}\n");
						}
					}
					 sb.append("helper.synch();\n");
					// for (int i = 0; i < parameterTypes.length; i++) {
					// if (!parameterTypes[i].isPrimitive()
					// && parameterTypes[i]
					// .subtypeOf(iPersistentObject)) {
					// sb.append(parameterTypes[i].getName());
					// sb.append(" arg" + i + "=helper.read(param" + (i)
					// + ");\n");
					// }
					// }
					if (!ctMethod.getReturnType().getName().equals("void")) {
						sb.append("return ");
					}
					sb.append("super.");
					sb.append(ctMethod.getName());
					sb.append("(");
					for (int i = 0; i < parameterTypes.length; i++) {
						sb.append("(");
						sb.append(parameterTypes[i].getName());
						sb.append(")helper.read(param" + i + ")");
						if (i < parameterTypes.length - 1) {
							sb.append(",");
						}
					}
					sb.append(");\n}");
					sb.append("\n}");
					System.out.println(sb);
					ctc.addMethod(CtNewMethod.make(sb.toString(), ctc));
				}
			}
//			 instrumentedClass = ctc.toClass();
			return ctc.toClass();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		// }
		// return instrumentedClass;
	}

	@Override
	public void run(RunNotifier notifier) {
		super.run(notifier);
	}
}
