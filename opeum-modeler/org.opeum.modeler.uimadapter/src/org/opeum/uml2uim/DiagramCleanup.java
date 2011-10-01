package org.opeum.uml2uim;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.uml2.uml.internal.resource.UMLResourceFactoryImpl;
import org.topcased.modeler.di.model.Diagram;
import org.topcased.modeler.di.model.EMFSemanticModelBridge;
import org.topcased.modeler.di.model.GraphEdge;
import org.topcased.modeler.di.model.GraphElement;
import org.topcased.modeler.di.model.GraphNode;
import org.topcased.modeler.di.model.util.DiagramInterchangeResourceFactoryImpl;
import org.topcased.modeler.diagrams.model.Diagrams;
import org.topcased.modeler.diagrams.model.DiagramsPackage;

public class DiagramCleanup {
	static int count = 0;
	static Map<GraphElement, List<GraphElement>> remove = new HashMap<GraphElement, List<GraphElement>>();

	public static void main(String[] args) throws Exception {
		File file = new File("../cm/cm-ear/src/main/model-version3/cm.umldi");
		System.out.println(file.exists());
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("umldi", new DiagramInterchangeResourceFactoryImpl());
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("uml", new UMLResourceFactoryImpl());
		Map map = new HashMap();
		DiagramsPackage dp = DiagramsPackage.eINSTANCE;
		map.put(dp.getNsURI(), dp);
		ResourceSet resourceSet = new ResourceSetImpl();
		URI uri = URI.createFileURI(file.getAbsolutePath());
		Resource resource = resourceSet.createResource(uri);
		resource.load(map);
		EcoreUtil.resolveAll(resource);
		Diagrams dgm = (Diagrams) resource.getContents().get(0);
		diagrams(dgm);
		String padding = "";
		printContents(dgm, padding);
		Set<Entry<GraphElement, List<GraphElement>>> entries = remove.entrySet();
		for (Entry<GraphElement, List<GraphElement>> entry : entries) {
			if (entry.getKey() != null) {
				// entry.getKey().getContained().removeAll(entry.getValue());
			}
		}
		resource.save(new HashMap());
		System.out.println(count);
	}

	private static void diagrams(Diagrams dgm) {
		Iterator<Diagram> iter = dgm.getDiagrams().iterator();
		while (iter.hasNext()) {
			Diagram diagram = iter.next();
			if (diagram.getName().equals("gsmkkk")) {
				iter.remove();
			}
		}
		for(Diagrams d:dgm.getSubdiagrams()){
			diagrams(d);
		}
	}

	private static void printContents(EObject dgm, String padding) {
		EList<EObject> eContents = dgm.eContents();
		for (EObject eObject : eContents) {
			if (eObject instanceof GraphNode) {
				GraphNode node = (GraphNode) eObject;
				if (node.getSemanticModel() instanceof EMFSemanticModelBridge) {
					EMFSemanticModelBridge bridge = (EMFSemanticModelBridge) node.getSemanticModel();
					if (bridge.getElement() != null && bridge.getElement().eIsProxy()) {
						List<GraphElement> list = getList(node.getContainer());
						list.add(node);
					}
					printDetails(padding, eObject, bridge);
				}
			} else if (eObject instanceof GraphEdge) {
				GraphEdge edge = (GraphEdge) eObject;
				if (edge.getSemanticModel() instanceof EMFSemanticModelBridge) {
					EMFSemanticModelBridge bridge = (EMFSemanticModelBridge) edge.getSemanticModel();
					if (bridge.getElement() == null) {
						List<GraphElement> list = getList(edge.getContainer());
						list.add(edge);
					}
				}
			} else if (eObject instanceof Diagram) {
				Diagram edge = (Diagram) eObject;
				if (edge.getSemanticModel() instanceof EMFSemanticModelBridge) {
					EMFSemanticModelBridge bridge = (EMFSemanticModelBridge) edge.getSemanticModel();
					printDetails(padding, eObject, bridge);
					edge.getContainer().getContained().remove(edge);
				}
			}
			printContents(eObject, padding + " ");
		}
	}

	private static List<GraphElement> getList(GraphElement container) {
		List<GraphElement> result = remove.get(container);
		if (result == null) {
			result = new ArrayList<GraphElement>();
			remove.put(container, result);
		}
		return result;
	}

	private static void printDetails(String padding, EObject eObject, EMFSemanticModelBridge bridge) {
		if (bridge.getElement() != null && bridge.getElement().eIsProxy()) {
			count++;
			System.out.println(padding + eObject.eContainer().getClass().getName());
			System.out.println(padding + "  " + eObject.getClass().getName());
			EObject element = EcoreUtil.resolve(bridge.getElement(), bridge.eResource());
			bridge.setElement(null);
			System.out.println(padding + "  proxy: " + element.getClass());
		} else if (bridge.getElement() == null) {
			System.out.println(padding + eObject.eContainer().getClass().getName());
			System.out.println(padding + "  " + eObject.getClass().getName());
			System.out.println(padding + "originalElement null ");
		}
	}
}
