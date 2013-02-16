package org.opaeum.javageneration.osgi;

import org.opaeum.emf.workspace.EmfWorkspace;
import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitBefore;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opaeum.java.metamodel.annotation.OJAnnotatedField;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.javageneration.AbstractJavaProducingVisitor;
import org.opaeum.javageneration.JavaTransformationPhase;
import org.opaeum.javageneration.basicjava.AttributeImplementor;
import org.opaeum.javageneration.persistence.JpaEnvironmentBuilder;
import org.opaeum.textmetamodel.JavaSourceFolderIdentifier;

@StepDependency(phase = JavaTransformationPhase.class,requires = {AttributeImplementor.class,JpaEnvironmentBuilder.class},after = {AttributeImplementor.class,JpaEnvironmentBuilder.class})
public class ActivatorImplementor extends AbstractJavaProducingVisitor{
	@VisitBefore
	public void visitWorkspace(EmfWorkspace e){
		OJPathName pn = ojUtil.utilClass(e, "Activator");
		OJAnnotatedClass c= new OJAnnotatedClass(pn.getLast());
		findOrCreatePackage(pn.getHead()).addToClasses(c);
		createTextPath(c, JavaSourceFolderIdentifier.INTEGRATED_ADAPTOR_GEN_SRC);
		c.addToImplementedInterfaces(new OJPathName("org.osgi.framework.BundleActivator"));
		OJAnnotatedOperation start = new OJAnnotatedOperation("start");
		start.addParam("bundle", new OJPathName("org.osgi.framework.BundleContext"));
		start.getBody().addToStatements(ojUtil.environmentPathname()+ ".INSTANCE.register()");
		c.addToOperations(start);
		OJAnnotatedOperation stop = new OJAnnotatedOperation("stop");
		stop.addParam("bundle", new OJPathName("org.osgi.framework.BundleContext"));
		c.addToOperations(stop);
		stop.getBody().addToStatements(ojUtil.environmentPathname()+ ".INSTANCE.unregister()");
	}
}
