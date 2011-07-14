package org.nakeduml.tinker.passbyvalue;

import java.util.HashSet;
import java.util.Set;

import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.javageneration.JavaTextSource.OutputRootId;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedEntity;
import net.sf.nakeduml.metamodel.core.INakedInterface;
import net.sf.nakeduml.metamodel.models.INakedModel;
import nl.klasse.octopus.codegen.umlToJava.modelgenerators.visitors.UtilityCreator;

import org.nakeduml.java.metamodel.OJPathName;
import org.nakeduml.java.metamodel.OJVisibilityKind;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedClass;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedOperation;
import org.nakeduml.java.metamodel.annotation.OJAnnotationValue;
import org.nakeduml.name.NameConverter;

public class DtoWsControllerImplementor extends AbstractPassByValueImplementor {

	public static final String WEBSERVICE_PUBLICHER = "WebservicePublisher";
	public static final String WEBSERVICE_PUBLICHER_OPER = "publish";
	OJAnnotatedClass webservicePublisher = null;
	OJAnnotatedOperation publish = null;

	@VisitBefore(matchSubclasses = true)
	public void visitModelBefore(INakedModel model) {
		webservicePublisher = new OJAnnotatedClass(WEBSERVICE_PUBLICHER);
		publish = new OJAnnotatedOperation(WEBSERVICE_PUBLICHER_OPER);
		publish.setVisibility(OJVisibilityKind.PUBLIC);
		publish.setStatic(true);
		webservicePublisher.addToOperations(publish);
		UtilityCreator.getUtilPack().addToClasses(webservicePublisher);
		super.createTextPath(webservicePublisher, OutputRootId.ADAPTOR_GEN_SRC);
	}

	@VisitBefore(matchSubclasses = true, match = { INakedEntity.class, INakedInterface.class })
	public void visitFeature(INakedClassifier c) {
		if (OJUtil.hasOJClass(c)) {
			addWsAnnotationToDto(c);
			addWsAnnotationToController(c);
			addWsAnnotationToControllerInterface(c);
			publishController(c);
		}
	}

	private void publishController(INakedClassifier c) {
		publish.getBody().addToStatements(
				PassByValueUtil.classifierControllerPathname(c).getLast() + " " + NameConverter.decapitalize(c.getName() + DtoImplementationStep.CTRL) + " = new "
						+ PassByValueUtil.classifierControllerPathname(c).getLast() + "()");
		publish.getBody().addToStatements(
				"Endpoint.publish(\"/" + c.getName() + DtoImplementationStep.CTRL + DtoImplementationStep.WS + "\", "
						+ NameConverter.decapitalize(c.getName() + DtoImplementationStep.CTRL) + ")");
		webservicePublisher.addToImports(PassByValueUtil.classifierControllerPathname(c));
		webservicePublisher.addToImports(new OJPathName("javax.xml.ws.Endpoint"));
	}

	private void addWsAnnotationToDto(INakedClassifier c) {
		OJAnnotatedClass myClass = findDtoJavaClass(c);
		OJAnnotationValue xmlRootElement = new OJAnnotationValue(new OJPathName("javax.xml.bind.annotation.XmlRootElement"));
		myClass.addAnnotationIfNew(xmlRootElement);
		if (c instanceof INakedInterface) {
			OJAnnotationValue xmlJavaTypeAdapter = new OJAnnotationValue(new OJPathName("javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter"));
			xmlJavaTypeAdapter.addClassValue(new OJPathName("com.sun.xml.bind.AnyTypeAdapter"));
			myClass.addAnnotationIfNew(xmlJavaTypeAdapter);
		}
	}

	private void addWsAnnotationToControllerInterface(INakedClassifier c) {
		OJAnnotatedClass myClass = findWsInterfaceJavaClass(c);
		OJAnnotationValue webService = new OJAnnotationValue(new OJPathName("javax.jws.WebService"));
		myClass.addAnnotationIfNew(webService);

		Set<INakedClassifier> subclasses = new HashSet<INakedClassifier>();
		findAllImplementingClassifiers(subclasses, c);
		if (!subclasses.isEmpty()) {
			OJAnnotationValue xmlSeeAlso = new OJAnnotationValue(new OJPathName("javax.xml.bind.annotation.XmlSeeAlso"));
			for (INakedClassifier classifier : subclasses) {
				xmlSeeAlso.addClassValue(PassByValueUtil.classifierDtoPathname(classifier));
			}
			myClass.addAnnotationIfNew(xmlSeeAlso);
		}
	}

	private void addWsAnnotationToController(INakedClassifier c) {
		OJAnnotatedClass myClass = findControlerJavaClass(c);
		OJAnnotationValue webService = new OJAnnotationValue(new OJPathName("javax.jws.WebService"));
		webService.putAttribute("endpointInterface", PassByValueUtil.classifierWsInterfacePathname(c).toJavaString());
		webService.putAttribute("serviceName", myClass.getName() + DtoImplementationStep.WS);
		myClass.addAnnotationIfNew(webService);
	}

}
