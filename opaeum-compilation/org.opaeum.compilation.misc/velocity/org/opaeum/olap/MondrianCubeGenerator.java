package org.opaeum.olap;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import nl.klasse.octopus.codegen.umlToJava.maps.StructuralFeatureMap;

import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Collaboration;
import org.eclipse.uml2.uml.Enumeration;
import org.eclipse.uml2.uml.EnumerationLiteral;
import org.eclipse.uml2.uml.InstanceSpecification;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.PrimitiveType;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Slot;
import org.eclipse.uml2.uml.Stereotype;
import org.eclipse.uml2.uml.ValueSpecification;
import org.opaeum.eclipse.EmfClassifierUtil;
import org.opaeum.eclipse.EmfElementFinder;
import org.opaeum.eclipse.EmfPropertyUtil;
import org.opaeum.eclipse.PersistentNameUtil;
import org.opaeum.emf.extraction.StereotypesHelper;
import org.opaeum.emf.workspace.EmfWorkspace;
import org.opaeum.feature.OpaeumConfig;
import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitAfter;
import org.opaeum.java.metamodel.OJWorkspace;
import org.opaeum.javageneration.JavaTransformationPhase;
import org.opaeum.javageneration.basicjava.AbstractStructureVisitor;
import org.opaeum.javageneration.persistence.JpaUtil;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.metamodel.core.internal.StereotypeNames;
import org.opaeum.metamodel.core.internal.TagNames;
import org.opaeum.metamodel.workspace.ModelWorkspace;
import org.opaeum.name.NameConverter;
import org.opaeum.runtime.domain.TimeUnit;
import org.opaeum.textmetamodel.CharArrayTextSource;
import org.opaeum.textmetamodel.TextFile;
import org.opaeum.textmetamodel.TextSourceFolderIdentifier;
import org.opaeum.textmetamodel.TextWorkspace;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.eclipse.uml2.uml.Class;

@StepDependency(phase = JavaTransformationPhase.class,requires = {},after = {})
public class MondrianCubeGenerator extends AbstractStructureVisitor{
	private Document doc;
	private Element schema;
	@Override
	public void initialize(OJWorkspace pac,OpaeumConfig config,TextWorkspace textWorkspace,EmfWorkspace workspace, OJUtil ojUtil){
		// TODO Auto-generated method stub
		super.initialize(pac, config, textWorkspace, workspace, ojUtil);
		try{
			TextFile textFile = createTextPath(TextSourceFolderIdentifier.INTEGRATED_ADAPTOR_GEN_RESOURCE, Arrays.asList("cube.xml"));
			File f = new File(config.getOutputRoot(), textFile.getWorkspaceRelativePath());
			if(f.exists()){
				this.doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(f);
				this.schema = doc.getDocumentElement();
			}else{
				this.doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
				this.schema = doc.createElement("Schema");
				this.schema.setAttribute("name", workspace.getName());
				doc.appendChild(schema);
			}
		}catch(DOMException e){
			e.printStackTrace();
		}catch(ParserConfigurationException e){
			e.printStackTrace();
		}catch(SAXException e){
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch(IOException e){
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@VisitAfter
	public void afterWorkspace(ModelWorkspace w){
		try{
			TransformerFactory transfac = TransformerFactory.newInstance();
			Transformer trans = transfac.newTransformer();
			trans.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
			trans.setOutputProperty(OutputKeys.INDENT, "yes");
			// create string from xml tree
			StringWriter sw = new StringWriter();
			StreamResult result = new StreamResult(sw);
			DOMSource source = new DOMSource(doc);
			trans.transform(source, result);
			TextFile textFile = createTextPath(TextSourceFolderIdentifier.INTEGRATED_ADAPTOR_GEN_RESOURCE, Arrays.asList(w.getName() + ".xml"));
			textFile.setTextSource(new CharArrayTextSource(sw.toString().toCharArray()));
			textFile = createTextPath(TextSourceFolderIdentifier.INTEGRATED_ADAPTOR_GEN_RESOURCE, Arrays.asList("cube.xml"));
			textFile.setTextSource(new CharArrayTextSource(sw.toString().toCharArray()));
		}catch(TransformerConfigurationException e){
			e.printStackTrace();
		}catch(TransformerFactoryConfigurationError e){
			e.printStackTrace();
		}catch(TransformerException e){
			e.printStackTrace();
		}
	}
	@Override
	protected void visitProperty(Classifier owner,StructuralFeatureMap buildStructuralFeatureMap){
	}
	@Override
	protected void visitComplexStructure(Classifier umlOwner){
		if(EmfClassifierUtil.isCompositionParticipant(umlOwner)){
			Classifier cp = umlOwner;
			if(EmfClassifierUtil.isFact( cp)){
				NodeList elementsByTagName = doc.getElementsByTagName("Cube");
				for(int i = 0;i < elementsByTagName.getLength();i++){
					Element found = (Element) elementsByTagName.item(i);
					if(found.getAttribute("name").equals(umlOwner.getName())){
						schema.removeChild(found);
						break;
					}
				}
				Element cube = doc.createElement("Cube");
				cube.setAttribute("name", umlOwner.getName());
				schema.appendChild(cube);
				Element table = doc.createElement("Table");
				cube.appendChild(table);
				table.setAttribute("name", PersistentNameUtil.getPersistentName( umlOwner).getAsIs());
				String schemaName = JpaUtil.getNearestSchema(umlOwner);
				if(schemaName != null){
					table.setAttribute("schema", schemaName);
				}
				// TODO create view for generalizations
				// Build cube
				addDimensions(cp, cube);
				addMeasures(cp, cube);
				addCalculatedMeasures(cp, cube);
			}
		}
		afterWorkspace(workspace);
	}
	protected void addCalculatedMeasures(Classifier cp,Element cube){
		for(Property p:EmfElementFinder.getPropertiesInScope(cp)){
			if(EmfPropertyUtil.isMeasure( p) && p.isDerived()){
				Element calculateMember = doc.createElement("CalculatedMember");
				calculateMember.setAttribute("name", p.getName());
				calculateMember.setAttribute("dimension", "Measures");
				cube.appendChild(calculateMember);
				Element formula = doc.createElement("Formula");
				calculateMember.appendChild(formula);
				Stereotype attrStereotype = StereotypesHelper.getStereotype( p,StereotypeNames.ATTRIBUTE);
				String sv=(String) p.getValue(attrStereotype, TagNames.DERIVATION_FORMULA);
				if(sv != null){
					String string = translateFormula(sv);
					formula.setTextContent(string);
				}
				Element formatString = doc.createElement("CalculatedMemberProperty");
				calculateMember.appendChild(formatString);
				formatString.setAttribute("name", "FORMAT_STRING");
				formatString.setAttribute("value", getFormatString(p));
			}
		}
	}
	protected static String translateFormula(String sv){
		Set<String> split = new HashSet<String>(Arrays.asList(sv.split("[\\/\\-\\*\\+\\)\\(]")));
		for(String string:split){
			if(isMeasureExpression(string.trim())){
				sv = sv.replaceAll(string, "[Measures].[" + string.trim() + "]");
			}
		}
		return sv;
	}
	public static void main(String[] args){
		System.out.println(translateFormula("asdf/1234-(sdfg*asdf)"));
	}
	// TODO make more sophisticated
	protected static boolean isMeasureExpression(String string){
		boolean valid = string.length() > 1 && !(Character.isDigit(string.charAt(0)));
		if(valid){
			for(char c:string.toCharArray()){
				if(!Character.isJavaIdentifierPart(c)){
					return false;
				}
			}
		}
		return valid;
	}
	protected void addMeasures(Classifier cp,Element cube){
		List<Element> measures = new ArrayList<Element>();
		for(Property p:getLibrary().getEffectiveAttributes(cp)){
			if(EmfPropertyUtil.isMeasure( p) && !p.isDerived()){
				Stereotype stereotype = StereotypesHelper.getStereotype( p,StereotypeNames.ATTRIBUTE);
				if(stereotype != null){
						List<EnumerationLiteral> values = (List<EnumerationLiteral>) p.getValue(stereotype, TagNames.AGGREGATION_FORMULAS);
						for(EnumerationLiteral l:values){
							Element measure = doc.createElement("Measure");
							measures.add(measure);
							measure.setAttribute("name",
									NameConverter.capitalize(l.getName().toLowerCase()) + "Of" + NameConverter.capitalize(p.getName()));
							measure.setAttribute("column", PersistentNameUtil.getPersistentName( p).getAsIs());
							if(l.getName().equalsIgnoreCase("average")){
								measure.setAttribute("aggregator", "avg");
							}else{
								measure.setAttribute("aggregator", l.getName().toLowerCase());
							}
							String format = getFormatString(p);
							measure.setAttribute("formatString", format);
					}
				}
				if(measures.isEmpty()){
				}
			}
		}
		for(Element element:measures){
			cube.appendChild(element);
		}
	}
	protected String getFormatString(Property p){
		String format;
		// TODO create a strategy for this
		if(p.getType() instanceof PrimitiveType && EmfClassifierUtil.comformsToLibraryType(p.getType(), "Real")){
			format = "#.00";
		}else{
			format = "Standard";
		}
		return format;
	}
	protected void addDimensions(Classifier cp,Element cube){
		HashSet<DimensionNode> leaves = new HashSet<DimensionNode>();
		addDimensions(cp, null, leaves);
		for(DimensionNode dimensionNode:leaves){
			Element dimension = doc.createElement("Dimension");
			cube.appendChild(dimension);
			Element hierarchy = doc.createElement("Hierarchy");
			dimension.appendChild(hierarchy);
			hierarchy.setAttribute("hasAll", "true");
			String plural = NameConverter.toPlural(dimensionNode.linkToInnermostDetail().getProperty().getType().getName());
			hierarchy.setAttribute("allMemberName", "All " + NameConverter.separateWords(plural));
			if(EmfClassifierUtil.isCompositionParticipant((Classifier) dimensionNode.getProperty().getType())
					|| dimensionNode.getProperty().getType() instanceof Enumeration){
				hierarchy.setAttribute("primaryKey", getIdColumn((Classifier) dimensionNode.getProperty().getType()));
				hierarchy.setAttribute("primaryKeyTable", PersistentNameUtil.getPersistentName(dimensionNode.getProperty().getType()).getAsIs());
			}
			dimension.setAttribute("foreignKey", PersistentNameUtil.getPersistentName(dimensionNode.getProperty()).getAsIs());
			dimension.setAttribute("name", dimensionNode.getName());
			List<Element> theLevels = null;
			if(dimensionNode.master == null
					|| !(EmfClassifierUtil.isCompositionParticipant((Classifier) dimensionNode.master.getProperty().getType()) || dimensionNode.master
							.getProperty().getType() instanceof Enumeration)){
				// Single table scenario
				List<Element> theLevels1 = new ArrayList<Element>();
				if(EmfClassifierUtil.isCompositionParticipant((Classifier) dimensionNode.getProperty().getType())
						|| dimensionNode.getProperty().getType() instanceof Enumeration){
					appendTable(hierarchy, (Classifier) dimensionNode.getProperty().getType());
				}else if(isDateType((Classifier) dimensionNode.getProperty().getType())){
					Element table = doc.createElement("Table");
					hierarchy.appendChild(table);
					table.setAttribute("name", "date_time_entry");
				}
				addLevels(dimensionNode.getProperty(), theLevels1);
				if(dimensionNode.master != null){
					// SIngle table scenario ending in a dimension column that is NOT a foreign key
					addLevels(dimensionNode.master.getProperty(), theLevels1);
				}
				theLevels = theLevels1;
			}else{
				theLevels = addJoinDimension(dimensionNode, hierarchy);
			}
			for(Element element:theLevels){
				hierarchy.appendChild(element);
			}
		}
	}
	protected List<Element> addDegenerateDimension(DimensionNode dimensionNode){
		List<Element> theLevels = new ArrayList<Element>();
		// Degenerate dimension
		addLevels(dimensionNode.getProperty(), theLevels);
		return theLevels;
	}
	protected List<Element> addJoinDimension(DimensionNode leaf,Element hierarchy){
		List<Element> levels = new ArrayList<Element>();
		DimensionNode curNode = leaf.master;
		Element prevParent = hierarchy;
		while(curNode != null){
			if(EmfClassifierUtil.isCompositionParticipant((Classifier) curNode.getProperty().getType())
					|| curNode.getProperty().getType() instanceof Enumeration){
				Element join = appendJoin(curNode.getProperty(), (Classifier) curNode.getProperty().getType(), prevParent);
				appendTable(join, curNode.getFromClass());
				addLevels(curNode.detail.getProperty(), levels);
				if(curNode.master == null){
					// Scenario where path ends in a foreignKey
					appendTable(join, (Classifier) curNode.getProperty().getType());
					addLevels(curNode.getProperty(), levels);
				}
				prevParent = join;
			}else{
				// curNode.master will be null
				// Scenario where path does NOT end in a foreignKey
				appendTable(prevParent, curNode.getFromClass());
				addLevels(curNode.detail.getProperty(), levels);
				addLevels(curNode.getProperty(), levels);
			}
			curNode = curNode.master;
		}
		// // TODO if property belongs to a general class, introduce extra join to general class
		// // TODO join with date_type table
		Collections.reverse(levels);// Will be in a detail-to-master order - reverse
		return levels;
	}
	protected List<Element> addSingleTable(DimensionNode dimensionNode,Element hierarchy){
		return null;
	}
	private void addLevels(Property property,List<Element> levels){
		Classifier toClass = (Classifier) property.getType();
		if(isDateType(toClass)){
			// TODO support for dimension for week of year and day of week
			switch(getDatePrecision(toClass)){
			case MONTH:
				addTimeLevel(levels, "Year", "TimeYears");
				addTimeLevel(levels, "Quarter", "TimeQuarters");
				addTimeLevel(levels, "MonthOfYear", "TimeMonths");
				break;
			case DAY:
				addTimeLevel(levels, "Year", "TimeYears");
				addTimeLevel(levels, "Quarter", "TimeQuarters");
				addTimeLevel(levels, "MonthOfYear", "TimeMonths");
				addTimeLevel(levels, "DayOfMonth", "TimeDays");
				break;
			case HOUR:
				addTimeLevel(levels, "Year", "TimeYears");
				addTimeLevel(levels, "Quarter", "TimeQuarters");
				addTimeLevel(levels, "MonthOfYear", "TimeMonths");
				addTimeLevel(levels, "DayOfMonth", "TimeDays");
				addTimeLevel(levels, "HourOfDay", null);
				break;
			default:
				break;
			}
			// TODO set formatter
		}else if(EmfClassifierUtil.isCompositionParticipant(toClass)){
			Element result = doc.createElement("Level");
			result.setAttribute("name", toClass.getName());
			result.setAttribute("table", PersistentNameUtil.getPersistentName( toClass).getAsIs());
			result.setAttribute("column", getIdColumn(toClass));
			// result.setAttribute("uniqueMembers", "true");
			Property nameProperty = EmfPropertyUtil.getNameProperty( toClass);
			if(nameProperty != null){
				result.setAttribute("nameColumn", PersistentNameUtil.getPersistentName(nameProperty).getAsIs());
			}else{
				result.setAttribute("nameColumn", "uuid");
			}
			levels.add(result);
		}else if(toClass instanceof Enumeration){
			Element result = doc.createElement("Level");
			result.setAttribute("name", toClass.getName());
			result.setAttribute("column", "id");
			result.setAttribute("table", PersistentNameUtil.getPersistentName( property.getType()).getAsIs());
			result.setAttribute("nameColumn", "name");
			levels.add(result);
			// TODO set formatter
		}else{
			Element result = doc.createElement("Level");
			result.setAttribute("name", NameConverter.separateWords(NameConverter.capitalize(property.getName())));
			result.setAttribute("column", PersistentNameUtil.getPersistentName(property).getAsIs());
			result.setAttribute("table", PersistentNameUtil.getPersistentName((org.eclipse.uml2.uml.Element) EmfElementFinder.getContainer(property)).getAsIs());
			levels.add(result);
		}
	}
	protected void addTimeLevel(List<Element> levels,String name,String levelType){
		Element result = doc.createElement("Level");
		result.setAttribute("name", name);
		result.setAttribute("column", NameConverter.toUnderscoreStyle(name).toLowerCase());
		if(config.shouldBeCm1Compatible()){
			result.setAttribute("table", "dim_date");
		}else{
			result.setAttribute("table", "date_time_entry");
		}
		if(levelType != null){
			result.setAttribute("levelType", levelType);
		}
		result.setAttribute("type", "Numeric");
		levels.add(result);
	}
	// TODO make this a bit more sophisticated
	protected TimeUnit getDatePrecision(Classifier toClass){
		return TimeUnit.DAY;
	}
	// TODO make this a bit more sophisticated
	protected boolean isDateType(Classifier toClass){
		return toClass.getName().contains("Date");
	}
	protected Element appendJoin(Property p,Classifier rightTable,Element curParent){
		Element curJoin;
		curJoin = doc.createElement("Join");
		String idName = getIdColumn(rightTable);
		curJoin.setAttribute("rightKey", idName);
		curJoin.setAttribute("leftKey", PersistentNameUtil.getPersistentName( p).getAsIs());
		curJoin.setAttribute("rightAlias", PersistentNameUtil.getPersistentName(rightTable).getAsIs());
		curParent.appendChild(curJoin);
		return curJoin;
	}
	protected String getIdColumn(Classifier rightTable){
		String idName = "id";
		if(rightTable instanceof Class){
			Collection<Property> primaryKeyProperties = EmfClassifierUtil.getPrimaryKeyProperties((Class) rightTable);
			if(primaryKeyProperties.size()==1){
				idName = PersistentNameUtil.getPersistentName( primaryKeyProperties.iterator().next()).getAsIs();
			}
		}
		return idName;
	}
	protected void appendTable(Element curJoin,Classifier fromClass){
		Element table = doc.createElement("Table");
		curJoin.appendChild(table);
		table.setAttribute("name", PersistentNameUtil.getPersistentName(fromClass).getAsIs());
		String curSchemaName2 = JpaUtil.getNearestSchema(fromClass);
		if(curSchemaName2 != null){
			table.setAttribute("schema", curSchemaName2);
		}
	}
	private boolean addDimensions(Classifier cp,DimensionNode detail,Set<DimensionNode> leaves){
		boolean hasParent = false;
		for(Property p:getLibrary().getEffectiveAttributes(cp)){
			if(EmfPropertyUtil.isDimension( p) && !(p.getType() instanceof Collaboration || p.getType() instanceof Interface)){
				hasParent = true;
				DimensionNode master = new DimensionNode(cp, p);
				if(detail != null){
					detail = detail.getCopy();
					master.detail = detail;
				}
				boolean masterHasMaster = false;
				if(EmfClassifierUtil.isCompositionParticipant((Classifier) p.getType()) || p.getType() instanceof Enumeration){
					masterHasMaster = addDimensions((Classifier) p.getType(), master, leaves);
				}
				if(master.hasRecursion()){
					throw new IllegalStateException("The property path " + master.linkToInnermostDetail().getName()
							+ " is a recursive dimension path");
				}
				if(!masterHasMaster){
					leaves.add(master.linkToInnermostDetail());
				}
			}
		}
		return hasParent;
	}
}
