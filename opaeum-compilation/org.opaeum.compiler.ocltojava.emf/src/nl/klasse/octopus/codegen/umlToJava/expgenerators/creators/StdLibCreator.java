/*
 * Created on Jun 8, 2004
 *
 * Copyright Klasse Objecten
 */
package nl.klasse.octopus.codegen.umlToJava.expgenerators.creators;
/**<octel>
<java>
	%import org.opaeum.javametamodel.OJParameter%;
	%inet.sfsf.opaeum.javametamodelametamodel.model.OJWhileStatementnet.sf.sf.opaeum.javametamodell.javametamodel.model.OJForStanet.sft.sf.opaeum.javametamodelkeduml.javametamodel.model.Onet.sfet.sf.opaeum.javametamodelsf.opaeum.javametamodel.model.OJSimpleStatement%;
</java>
</octel>*/

/* <octel> */ 

/* <java> */ 

/* <inline> */ 
import nl.klasse.octopus.codegen.helpers.CommonNames;
import nl.klasse.octopus.codegen.umlToJava.maps.StdlibMap;

import org.opaeum.java.metamodel.OJBlock;
import org.opaeum.java.metamodel.OJClass;
import org.opaeum.java.metamodel.OJForStatement;
import org.opaeum.java.metamodel.OJIfStatement;
import org.opaeum.java.metamodel.OJOperation;
import org.opaeum.java.metamodel.OJPackage;
import org.opaeum.java.metamodel.OJParameter;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.OJSimpleStatement;
import org.opaeum.java.metamodel.OJVisibilityKind;
import org.opaeum.java.metamodel.OJWhileStatement;
import org.opaeum.java.metamodel.utilities.JavaPathNames;


/**
 * UtilitiesAdder : 
 */
public class StdLibCreator {
	private String stdLibClassName	= "Stdlib";
	private OJClass stdlibCls		= null;
	// TODO use template
	/**
	 * 
	 */
	public StdLibCreator() {
		super();
	}

	public OJClass makeStdLib(OJPackage utilPack) {
		if (utilPack == null) { return null; }
		// create the stdlibCls
		stdlibCls = new OJClass();
		utilPack.addToClasses(stdlibCls);
		// set the basic attributes of the stdlibCls
		stdlibCls.setComment(CommonNames.standardClassComment);
		stdlibCls.setName(stdLibClassName);
		stdlibCls.setVisibility(OJVisibilityKind.PUBLIC);
		stdlibCls.addToImports(StdlibMap.javaSetImplType);
		stdlibCls.addToImports(JavaPathNames.TreeSet);
		// add the operations
		addAsSetOpers();
		addAsBagOpers();
		addAsSeqOpers();
		addAsOrdOpers();
		addInExcludingOpers(); 
		makeIntersection();
		makeInsertAt(StdlibMap.javaSequenceType);
		if (!StdlibMap.javaOrderedSetType.equals(StdlibMap.javaSequenceType)) {
			// don't generate insertAt for OrderedSets if the types are the same, it will be the same as for Sequences
			makeInsertAt(StdlibMap.javaOrderedSetType);
		}
		makeSetFlatten();
		makeSequenceFlatten();
		makeBagFlatten();
		makeOrderedSetFlatten();
		makeSetEquals();
		makeSequenceEquals();
		makeBagEquals();
		makeOrderedSetEquals();
		makeExcludesAll();
		return stdlibCls;
	}

	private void addInExcludingOpers() {
		makeIncludingX( StdlibMap.javaSetType, StdlibMap.javaSetImplType);
		makeExcludingX(StdlibMap.javaSetType, StdlibMap.javaSetImplType);
		// don't generate including or excluding for Bags if the types are the same, it will be the same as for Sets
		// same holds for Sequence and OrderedSet
		if (!StdlibMap.javaBagType.equals(StdlibMap.javaSetType)) { 
			makeIncludingX(StdlibMap.javaBagType, StdlibMap.javaBagImplType);
			makeExcludingX(StdlibMap.javaBagType, StdlibMap.javaBagImplType);
		} 
		if (!(StdlibMap.javaSequenceType.equals(StdlibMap.javaSetType) 
				|| StdlibMap.javaSequenceType.equals(StdlibMap.javaBagType))) {
			makeIncludingX(StdlibMap.javaSequenceType, StdlibMap.javaSequenceImplType);
			makeExcludingX(StdlibMap.javaSequenceType, StdlibMap.javaSequenceImplType);
		} 
		if (!(StdlibMap.javaOrderedSetType.equals(StdlibMap.javaSetType) 
				|| StdlibMap.javaOrderedSetType.equals(StdlibMap.javaBagType)
				|| StdlibMap.javaOrderedSetType.equals(StdlibMap.javaSequenceType))) {
			makeIncludingX(StdlibMap.javaOrderedSetType, StdlibMap.javaOrderedSetImplType);
			makeExcludingX(StdlibMap.javaOrderedSetType, StdlibMap.javaOrderedSetImplType);
		}
	}
	
	private OJOperation makeExcludesAll() {
		OJOperation oper = null;
		String operName = "excludesAll";
		OJPathName type = StdlibMap.javaCollectionType;
		stdlibCls.addToImports(StdlibMap.javaCollectionType);
		/**<octel var="stdlibCls">
			<method type="%JavaPathNames.Bool%" name="%operName%" visibility="%OJVisibilityKind.PUBLIC%" static="%true%" var="oper">
			<comment> implements the excludesAll operation for sets </comment>
			<param type="%type%" name="source"/>
			<param type="%type%" name="arg"/>
			<body>
				Iterator it = arg.iterator();
				<while> it.hasNext() <body>
				Object elem = it.next();
				<if> source.contains(elem) <then>
					return false;
				</then></if>
				</body></while>
				return true;
			</body>
			</method>
		</octel>*/	

/* <octel> */ 

/* <method> */ 
oper = new OJOperation();
stdlibCls.addToOperations(oper);

/* <type> */ 
oper.setReturnType(JavaPathNames.Bool);

/* <type/> */ 

/* <name> */ 
oper.setName(operName);

/* <name/> */ 

/* <visibility> */ 
oper.setVisibility(OJVisibilityKind.PUBLIC);

/* <visibility/> */ 

/* <static> */ 
oper.setStatic(true);

/* <static/> */ 

/* <comment> */ 
oper.setComment("implements the excludesAll operation for sets");

/* <comment/> */ 

/* <param> */ 
OJParameter param1 = new OJParameter();
oper.addToParameters(param1);

/* <type> */ 
param1.setType(type);

/* <type/> */ 

/* <name> */ 
param1.setName("source");

/* <name/> */ 

/* <param/> */ 

/* <param> */ 
OJParameter param2 = new OJParameter();
oper.addToParameters(param2);

/* <type> */ 
param2.setType(type);

/* <type/> */ 

/* <name> */ 
param2.setName("arg");

/* <name/> */ 

/* <param/> */ 

/* <body> */ 
OJBlock body1 = new OJBlock();
oper.setBody(body1);

/* <exp> */ 
OJSimpleStatement exp1 = new OJSimpleStatement("Iterator it = arg.iterator()");
body1.addToStatements( exp1 );

/* <exp/> */ 

/* <while> */ 
OJWhileStatement while1 = new OJWhileStatement();
body1.addToStatements(while1);
while1.setCondition("it.hasNext()");

/* <body> */ 
OJBlock body2 = new OJBlock();
while1.setBody(body2);

/* <exp> */ 
OJSimpleStatement exp2 = new OJSimpleStatement("Object elem = it.next()");
body2.addToStatements( exp2 );

/* <exp/> */ 

/* <if> */ 
OJIfStatement if1 = new OJIfStatement();
if1.setCondition("source.contains(elem)");
body2.addToStatements(if1);

/* <then> */ 
OJBlock then1 = new OJBlock();
if1.setThenPart(then1);

/* <exp> */ 
OJSimpleStatement exp3 = new OJSimpleStatement("return false");
then1.addToStatements( exp3 );

/* <exp/> */ 

/* <then/> */ 

/* <if/> */ 

/* <body/> */ 

/* <while/> */ 

/* <exp> */ 
OJSimpleStatement exp4 = new OJSimpleStatement("return true");
body1.addToStatements( exp4 );

/* <exp/> */ 

/* <body/> */ 

/* <method/> */ 

/* <octel/> */ 


		return oper;		
	} 
	private OJOperation makeSetEquals() {
		OJOperation oper = null;
		String operName = "setEquals";
		OJPathName type = StdlibMap.javaSetType;
		stdlibCls.addToImports(StdlibMap.javaSetType);
		/**<octel var="stdlibCls">
			<method type="%JavaPathNames.Bool%" name="%operName%" visibility="%OJVisibilityKind.PUBLIC%" static="%true%" var="oper">
			<comment> implements the equals operation for sets </comment>
			<param type="%type%" name="source"/>
			<param type="%type%" name="arg"/>
			<body>
				<if> source.size() != arg.size() <then>
					return false;
				</then></if>
				Iterator it = arg.iterator();
				<while> it.hasNext() <body>
				Object elem = it.next();
				<if> !source.contains(elem) <then>
					return false;
				</then></if>
				</body></while>
				return true;
			</body>
			</method>
		</octel>*/	

/* <octel> */ 

/* <method> */ 
oper = new OJOperation();
stdlibCls.addToOperations(oper);

/* <type> */ 
oper.setReturnType(JavaPathNames.Bool);

/* <type/> */ 

/* <name> */ 
oper.setName(operName);

/* <name/> */ 

/* <visibility> */ 
oper.setVisibility(OJVisibilityKind.PUBLIC);

/* <visibility/> */ 

/* <static> */ 
oper.setStatic(true);

/* <static/> */ 

/* <comment> */ 
oper.setComment("implements the equals operation for sets");

/* <comment/> */ 

/* <param> */ 
OJParameter param3 = new OJParameter();
oper.addToParameters(param3);

/* <type> */ 
param3.setType(type);

/* <type/> */ 

/* <name> */ 
param3.setName("source");

/* <name/> */ 

/* <param/> */ 

/* <param> */ 
OJParameter param4 = new OJParameter();
oper.addToParameters(param4);

/* <type> */ 
param4.setType(type);

/* <type/> */ 

/* <name> */ 
param4.setName("arg");

/* <name/> */ 

/* <param/> */ 

/* <body> */ 
OJBlock body3 = new OJBlock();
oper.setBody(body3);

/* <if> */ 
OJIfStatement if2 = new OJIfStatement();
if2.setCondition("source.size() != arg.size()");
body3.addToStatements(if2);

/* <then> */ 
OJBlock then2 = new OJBlock();
if2.setThenPart(then2);

/* <exp> */ 
OJSimpleStatement exp5 = new OJSimpleStatement("return false");
then2.addToStatements( exp5 );

/* <exp/> */ 

/* <then/> */ 

/* <if/> */ 

/* <exp> */ 
OJSimpleStatement exp6 = new OJSimpleStatement("Iterator it = arg.iterator()");
body3.addToStatements( exp6 );

/* <exp/> */ 

/* <while> */ 
OJWhileStatement while2 = new OJWhileStatement();
body3.addToStatements(while2);
while2.setCondition("it.hasNext()");

/* <body> */ 
OJBlock body4 = new OJBlock();
while2.setBody(body4);

/* <exp> */ 
OJSimpleStatement exp7 = new OJSimpleStatement("Object elem = it.next()");
body4.addToStatements( exp7 );

/* <exp/> */ 

/* <if> */ 
OJIfStatement if3 = new OJIfStatement();
if3.setCondition("!source.contains(elem)");
body4.addToStatements(if3);

/* <then> */ 
OJBlock then3 = new OJBlock();
if3.setThenPart(then3);

/* <exp> */ 
OJSimpleStatement exp8 = new OJSimpleStatement("return false");
then3.addToStatements( exp8 );

/* <exp/> */ 

/* <then/> */ 

/* <if/> */ 

/* <body/> */ 

/* <while/> */ 

/* <exp> */ 
OJSimpleStatement exp9 = new OJSimpleStatement("return true");
body3.addToStatements( exp9 );

/* <exp/> */ 

/* <body/> */ 

/* <method/> */ 

/* <octel/> */ 


		return oper;		
	} 
	
	private OJOperation makeSequenceEquals() {
		OJOperation oper = null;
		OJBlock body = null;
		String operName = "sequenceEquals";
		OJPathName type = StdlibMap.javaSequenceType;
		stdlibCls.addToImports(StdlibMap.javaSequenceType);
		/**<octel var="stdlibCls">
			<method type="%JavaPathNames.Bool%" name="%operName%" visibility="%OJVisibilityKind.PUBLIC%" static="%true%" var="oper">
			<comment> implements the equals operation for sequences </comment>
			<param type="%type%" name="source"/>
			<param type="%type%" name="arg"/>
			<body var="body">
				<if> source.size() != arg.size() <then>
					return false;
				</then></if>
				Iterator it1 = source.iterator();
				Iterator it2 = arg.iterator();
			</body>
			</method>
		</octel>*/

/* <octel> */ 

/* <method> */ 
oper = new OJOperation();
stdlibCls.addToOperations(oper);

/* <type> */ 
oper.setReturnType(JavaPathNames.Bool);

/* <type/> */ 

/* <name> */ 
oper.setName(operName);

/* <name/> */ 

/* <visibility> */ 
oper.setVisibility(OJVisibilityKind.PUBLIC);

/* <visibility/> */ 

/* <static> */ 
oper.setStatic(true);

/* <static/> */ 

/* <comment> */ 
oper.setComment("implements the equals operation for sequences");

/* <comment/> */ 

/* <param> */ 
OJParameter param5 = new OJParameter();
oper.addToParameters(param5);

/* <type> */ 
param5.setType(type);

/* <type/> */ 

/* <name> */ 
param5.setName("source");

/* <name/> */ 

/* <param/> */ 

/* <param> */ 
OJParameter param6 = new OJParameter();
oper.addToParameters(param6);

/* <type> */ 
param6.setType(type);

/* <type/> */ 

/* <name> */ 
param6.setName("arg");

/* <name/> */ 

/* <param/> */ 

/* <body> */ 
body = new OJBlock();
oper.setBody(body);

/* <if> */ 
OJIfStatement if4 = new OJIfStatement();
if4.setCondition("source.size() != arg.size()");
body.addToStatements(if4);

/* <then> */ 
OJBlock then4 = new OJBlock();
if4.setThenPart(then4);

/* <exp> */ 
OJSimpleStatement exp10 = new OJSimpleStatement("return false");
then4.addToStatements( exp10 );

/* <exp/> */ 

/* <then/> */ 

/* <if/> */ 

/* <exp> */ 
OJSimpleStatement exp11 = new OJSimpleStatement("Iterator it1 = source.iterator()");
body.addToStatements( exp11 );

/* <exp/> */ 

/* <exp> */ 
OJSimpleStatement exp12 = new OJSimpleStatement("Iterator it2 = arg.iterator()");
body.addToStatements( exp12 );

/* <exp/> */ 

/* <body/> */ 

/* <method/> */ 

/* <octel/> */ 


		makeEqualsCompare(body);			
		return oper;
	}
	
	private OJOperation makeBagEquals() {
		OJOperation oper = null;
		OJBlock body = null;
		String operName = "bagEquals";
		OJPathName type = StdlibMap.javaBagType.getCopy();
		OJPathName implType = StdlibMap.javaBagImplType.getCopy();
		OJPathName genericTypeParam = new OJPathName("T");
		implType.addToElementTypes(genericTypeParam);
		type.addToElementTypes(genericTypeParam);
		
		/**<octel var="stdlibCls">
		    <import> %implType% </import>
		    
			<method genparam="%genericTypeParam%" type="%JavaPathNames.Bool%" name="%operName%" visibility="%OJVisibilityKind.PUBLIC%" static="%true%" var="oper">
			<param type="%type%" name="source"/>
			<param type="%type%" name="arg"/>
			<comment> implements the equals operation for bags </comment>
			<body var="body">
			<comment> make copy of arguments in order to manipulate the collection </comment>
				<if> source.size() != arg.size() <then>
					return false;
				</then></if>
				%type.getCollectionTypeName()% myArg = new %implType.getCollectionTypeName()%( arg );
				<for name="elem" type="%genericTypeParam%">
				<collection>source</collection>
				<body>
					<if>myArg.contains(elem)<then>
						myArg.remove(elem);
					</then><else>
						return false;
					</else></if>  
				</body></for>
				return myArg.isEmpty();
			</body>
			</method>
		</octel>*/

/* <octel> */ 

/* <import> */ 

/* <name> */ 
stdlibCls.addToImports(implType);

/* <name/> */ 

/* <import/> */ 

/* <method> */ 
oper = new OJOperation();
stdlibCls.addToOperations(oper);

/* <genparam> */ 
oper.setGenericTypeParam(genericTypeParam);

/* <genparam/> */ 

/* <type> */ 
oper.setReturnType(JavaPathNames.Bool);

/* <type/> */ 

/* <name> */ 
oper.setName(operName);

/* <name/> */ 

/* <visibility> */ 
oper.setVisibility(OJVisibilityKind.PUBLIC);

/* <visibility/> */ 

/* <static> */ 
oper.setStatic(true);

/* <static/> */ 

/* <param> */ 
OJParameter param7 = new OJParameter();
oper.addToParameters(param7);

/* <type> */ 
param7.setType(type);

/* <type/> */ 

/* <name> */ 
param7.setName("source");

/* <name/> */ 

/* <param/> */ 

/* <param> */ 
OJParameter param8 = new OJParameter();
oper.addToParameters(param8);

/* <type> */ 
param8.setType(type);

/* <type/> */ 

/* <name> */ 
param8.setName("arg");

/* <name/> */ 

/* <param/> */ 

/* <comment> */ 
oper.setComment("implements the equals operation for bags");

/* <comment/> */ 

/* <body> */ 
body = new OJBlock();
oper.setBody(body);

/* <comment> */ 
body.setComment("make copy of arguments in order to manipulate the collection");

/* <comment/> */ 

/* <if> */ 
OJIfStatement if5 = new OJIfStatement();
if5.setCondition("source.size() != arg.size()");
body.addToStatements(if5);

/* <then> */ 
OJBlock then5 = new OJBlock();
if5.setThenPart(then5);

/* <exp> */ 
OJSimpleStatement exp13 = new OJSimpleStatement("return false");
then5.addToStatements( exp13 );

/* <exp/> */ 

/* <then/> */ 

/* <if/> */ 

/* <exp> */ 
OJSimpleStatement exp14 = new OJSimpleStatement(type.getCollectionTypeName() + " myArg = new " + implType.getCollectionTypeName() + "( arg )");
body.addToStatements( exp14 );

/* <exp/> */ 

/* <for> */ 
OJForStatement for1 = new OJForStatement();
body.addToStatements(for1);

/* <name> */ 
for1.setElemName("elem");

/* <name/> */ 

/* <type> */ 
for1.setElemType(genericTypeParam);

/* <type/> */ 

/* <collection> */ 
for1.setCollection("source");

/* <collection/> */ 

/* <body> */ 
OJBlock body5 = new OJBlock();
for1.setBody(body5);

/* <if> */ 
OJIfStatement if6 = new OJIfStatement();
if6.setCondition("myArg.contains(elem)");
body5.addToStatements(if6);

/* <then> */ 
OJBlock then6 = new OJBlock();
if6.setThenPart(then6);

/* <exp> */ 
OJSimpleStatement exp15 = new OJSimpleStatement("myArg.remove(elem)");
then6.addToStatements( exp15 );

/* <exp/> */ 

/* <then/> */ 

/* <else> */ 
OJBlock else1 = new OJBlock();
if6.setElsePart(else1);

/* <exp> */ 
OJSimpleStatement exp16 = new OJSimpleStatement("return false");
else1.addToStatements( exp16 );

/* <exp/> */ 

/* <else/> */ 

/* <if/> */ 

/* <body/> */ 

/* <for/> */ 

/* <exp> */ 
OJSimpleStatement exp17 = new OJSimpleStatement("return myArg.isEmpty()");
body.addToStatements( exp17 );

/* <exp/> */ 

/* <body/> */ 

/* <method/> */ 

/* <octel/> */ 


		return oper;
	}

	private OJOperation makeOrderedSetEquals() {
		OJOperation oper = null;
		OJBlock body = null;
		String operName = "orderedsetEquals";
		OJPathName type = StdlibMap.javaOrderedSetType;
		stdlibCls.addToImports(StdlibMap.javaOrderedSetType);
		/**<octel var="stdlibCls">
			<method type="%JavaPathNames.Bool%" name="%operName%" visibility="%OJVisibilityKind.PUBLIC%" static="%true%" var="oper">
			<comment> implements the equals operation for orderedsets </comment>
			<param type="%type%" name="source"/>
			<param type="%type%" name="arg"/>
			<body var="body">
				<if> source.size() != arg.size() <then>
					return false;
				</then></if>
				Iterator it1 = source.iterator();
				Iterator it2 = arg.iterator();
			</body>
			</method>
		</octel>*/

/* <octel> */ 

/* <method> */ 
oper = new OJOperation();
stdlibCls.addToOperations(oper);

/* <type> */ 
oper.setReturnType(JavaPathNames.Bool);

/* <type/> */ 

/* <name> */ 
oper.setName(operName);

/* <name/> */ 

/* <visibility> */ 
oper.setVisibility(OJVisibilityKind.PUBLIC);

/* <visibility/> */ 

/* <static> */ 
oper.setStatic(true);

/* <static/> */ 

/* <comment> */ 
oper.setComment("implements the equals operation for orderedsets");

/* <comment/> */ 

/* <param> */ 
OJParameter param9 = new OJParameter();
oper.addToParameters(param9);

/* <type> */ 
param9.setType(type);

/* <type/> */ 

/* <name> */ 
param9.setName("source");

/* <name/> */ 

/* <param/> */ 

/* <param> */ 
OJParameter param10 = new OJParameter();
oper.addToParameters(param10);

/* <type> */ 
param10.setType(type);

/* <type/> */ 

/* <name> */ 
param10.setName("arg");

/* <name/> */ 

/* <param/> */ 

/* <body> */ 
body = new OJBlock();
oper.setBody(body);

/* <if> */ 
OJIfStatement if7 = new OJIfStatement();
if7.setCondition("source.size() != arg.size()");
body.addToStatements(if7);

/* <then> */ 
OJBlock then7 = new OJBlock();
if7.setThenPart(then7);

/* <exp> */ 
OJSimpleStatement exp18 = new OJSimpleStatement("return false");
then7.addToStatements( exp18 );

/* <exp/> */ 

/* <then/> */ 

/* <if/> */ 

/* <exp> */ 
OJSimpleStatement exp19 = new OJSimpleStatement("Iterator it1 = source.iterator()");
body.addToStatements( exp19 );

/* <exp/> */ 

/* <exp> */ 
OJSimpleStatement exp20 = new OJSimpleStatement("Iterator it2 = arg.iterator()");
body.addToStatements( exp20 );

/* <exp/> */ 

/* <body/> */ 

/* <method/> */ 

/* <octel/> */ 


		makeEqualsCompare(body);			
		return oper;
	}

	private void makeEqualsCompare(OJBlock body) {
		/**<octel var="body">
			<while> it1.hasNext() <body>
			Object elem1 = it1.next();
			Object elem2 = it2.next();
			<if> elem1 instanceof Integer <then>
				<if> ((Integer)elem1).intValue() != ((Integer)elem2).intValue() <then>
					return false;
				</then></if>
			</then><else> 
				<if> elem1 instanceof Float <then>
					<if> ((Float)elem1).floatValue() != ((Float)elem2).floatValue() <then>
						return false;
					</then></if>
				</then><else> 
					<if> elem1 instanceof Boolean <then>
						<if> ((Boolean)elem1).booleanValue() != ((Boolean)elem2).booleanValue() <then>
							return false;
						</then></if>
					</then><else> 
						<if> !elem1.equals(elem2) <then>
							return false;
						</then></if>
					</else></if>
				</else></if>
			</else></if>
			</body></while>
		</octel>*/

/* <octel> */ 

/* <while> */ 
OJWhileStatement while3 = new OJWhileStatement();
body.addToStatements(while3);
while3.setCondition("it1.hasNext()");

/* <body> */ 
OJBlock body6 = new OJBlock();
while3.setBody(body6);

/* <exp> */ 
OJSimpleStatement exp21 = new OJSimpleStatement("Object elem1 = it1.next()");
body6.addToStatements( exp21 );

/* <exp/> */ 

/* <exp> */ 
OJSimpleStatement exp22 = new OJSimpleStatement("Object elem2 = it2.next()");
body6.addToStatements( exp22 );

/* <exp/> */ 

/* <if> */ 
OJIfStatement if8 = new OJIfStatement();
if8.setCondition("elem1 instanceof Integer");
body6.addToStatements(if8);

/* <then> */ 
OJBlock then8 = new OJBlock();
if8.setThenPart(then8);

/* <if> */ 
OJIfStatement if9 = new OJIfStatement();
if9.setCondition("((Integer)elem1).intValue() != ((Integer)elem2).intValue()");
then8.addToStatements(if9);

/* <then> */ 
OJBlock then9 = new OJBlock();
if9.setThenPart(then9);

/* <exp> */ 
OJSimpleStatement exp23 = new OJSimpleStatement("return false");
then9.addToStatements( exp23 );

/* <exp/> */ 

/* <then/> */ 

/* <if/> */ 

/* <then/> */ 

/* <else> */ 
OJBlock else2 = new OJBlock();
if8.setElsePart(else2);

/* <if> */ 
OJIfStatement if10 = new OJIfStatement();
if10.setCondition("elem1 instanceof Float");
else2.addToStatements(if10);

/* <then> */ 
OJBlock then10 = new OJBlock();
if10.setThenPart(then10);

/* <if> */ 
OJIfStatement if11 = new OJIfStatement();
if11.setCondition("((Float)elem1).floatValue() != ((Float)elem2).floatValue()");
then10.addToStatements(if11);

/* <then> */ 
OJBlock then11 = new OJBlock();
if11.setThenPart(then11);

/* <exp> */ 
OJSimpleStatement exp24 = new OJSimpleStatement("return false");
then11.addToStatements( exp24 );

/* <exp/> */ 

/* <then/> */ 

/* <if/> */ 

/* <then/> */ 

/* <else> */ 
OJBlock else3 = new OJBlock();
if10.setElsePart(else3);

/* <if> */ 
OJIfStatement if12 = new OJIfStatement();
if12.setCondition("elem1 instanceof Boolean");
else3.addToStatements(if12);

/* <then> */ 
OJBlock then12 = new OJBlock();
if12.setThenPart(then12);

/* <if> */ 
OJIfStatement if13 = new OJIfStatement();
if13.setCondition("((Boolean)elem1).booleanValue() != ((Boolean)elem2).booleanValue()");
then12.addToStatements(if13);

/* <then> */ 
OJBlock then13 = new OJBlock();
if13.setThenPart(then13);

/* <exp> */ 
OJSimpleStatement exp25 = new OJSimpleStatement("return false");
then13.addToStatements( exp25 );

/* <exp/> */ 

/* <then/> */ 

/* <if/> */ 

/* <then/> */ 

/* <else> */ 
OJBlock else4 = new OJBlock();
if12.setElsePart(else4);

/* <if> */ 
OJIfStatement if14 = new OJIfStatement();
if14.setCondition("!elem1.equals(elem2)");
else4.addToStatements(if14);

/* <then> */ 
OJBlock then14 = new OJBlock();
if14.setThenPart(then14);

/* <exp> */ 
OJSimpleStatement exp26 = new OJSimpleStatement("return false");
then14.addToStatements( exp26 );

/* <exp/> */ 

/* <then/> */ 

/* <if/> */ 

/* <else/> */ 

/* <if/> */ 

/* <else/> */ 

/* <if/> */ 

/* <else/> */ 

/* <if/> */ 

/* <body/> */ 

/* <while/> */ 

/* <octel/> */ 


		body.addToStatements("return true"); 
	}
	/**
	 * @return
	 */
	private void makeSetFlatten() {
		OJPathName returnType = StdlibMap.javaSetType;
		String myDefault = StdlibMap.javaSetImplType.getLast();
		String operName = "setFlatten";
		makeFlatten(returnType, myDefault, operName, "Sets");
	}
	
	private void makeSequenceFlatten() {
		OJPathName returnType = StdlibMap.javaSequenceType;
		String myDefault = StdlibMap.javaSequenceImplType.getLast();
		String operName = "sequenceFlatten";
		makeFlatten(returnType, myDefault, operName, "Sequences");
	}
	
	private void makeBagFlatten() {
		OJPathName returnType = StdlibMap.javaBagType;
		String myDefault = StdlibMap.javaBagImplType.getLast();
		String operName = "bagFlatten";
		makeFlatten(returnType, myDefault, operName, "Bags");
	}
	
	private void makeOrderedSetFlatten() {
		OJPathName returnType = StdlibMap.javaOrderedSetType;
		String myDefault = StdlibMap.javaOrderedSetImplType.getLast();
		String operName = "orderedsetFlatten";
		makeFlatten(returnType, myDefault, operName, "OrderedSets");
	}
	
	private	void makeFlatten(OJPathName returnType, String myDefault, String operName, String comment) {
		stdlibCls.addToImports(JavaPathNames.Iterator);
		// generate separate operation
		/**<octel var="stdlibCls">
			<method type="%returnType%" name="%operName%" visibility="%OJVisibilityKind.PUBLIC%" static="%true%" needsSuppress="%true%">
			<param type="%JavaPathNames.Object%" name="source"/>
			<comment> implements the standard flatten operation on %comment%.
Because Java generic types are not checked during run-time this operation 
cannot be implemented without compiler warnings.</comment>
			<body>
				%returnType.getCollectionTypeName()% result = new %myDefault%();
				<if> source instanceof %StdlibMap.javaCollectionType.getTypeName()% <then>
					Iterator it = ((%StdlibMap.javaCollectionType.getTypeName()%)source).iterator();
					<while> it.hasNext() <body>
					Object elem = it.next();
					<if> elem instanceof %StdlibMap.javaCollectionType.getTypeName()% <then>
						result.addAll( %operName%(elem));
					</then><else>
						result.add(elem);
					</else></if>
					</body></while>
				</then></if>
				return result;
			</body>
			</method>
		</octel>*/

/* <octel> */ 

/* <method> */ 
OJOperation method1 = new OJOperation();
stdlibCls.addToOperations(method1);

/* <type> */ 
method1.setReturnType(returnType);

/* <type/> */ 

/* <name> */ 
method1.setName(operName);

/* <name/> */ 

/* <visibility> */ 
method1.setVisibility(OJVisibilityKind.PUBLIC);

/* <visibility/> */ 

/* <static> */ 
method1.setStatic(true);

/* <static/> */ 

/* <needsSuppress> */ 
method1.setNeedsSuppress(true);

/* <needsSuppress/> */ 

/* <param> */ 
OJParameter param11 = new OJParameter();
method1.addToParameters(param11);

/* <type> */ 
param11.setType(JavaPathNames.Object);

/* <type/> */ 

/* <name> */ 
param11.setName("source");

/* <name/> */ 

/* <param/> */ 

/* <comment> */ 
method1.setComment("implements the standard flatten operation on " + comment + ".\n"
+ "Because Java generic types are not checked during run-time this operation \n"
+ "cannot be implemented without compiler warnings.");

/* <comment/> */ 

/* <body> */ 
OJBlock body7 = new OJBlock();
method1.setBody(body7);

/* <exp> */ 
OJSimpleStatement exp27 = new OJSimpleStatement(returnType.getCollectionTypeName() + " result = new " + myDefault + "()");
body7.addToStatements( exp27 );

/* <exp/> */ 

/* <if> */ 
OJIfStatement if15 = new OJIfStatement();
if15.setCondition("source instanceof " + StdlibMap.javaCollectionType.getTypeName());
body7.addToStatements(if15);

/* <then> */ 
OJBlock then15 = new OJBlock();
if15.setThenPart(then15);

/* <exp> */ 
OJSimpleStatement exp28 = new OJSimpleStatement("Iterator it = ((" + StdlibMap.javaCollectionType.getTypeName() + ")source).iterator()");
then15.addToStatements( exp28 );

/* <exp/> */ 

/* <while> */ 
OJWhileStatement while4 = new OJWhileStatement();
then15.addToStatements(while4);
while4.setCondition("it.hasNext()");

/* <body> */ 
OJBlock body8 = new OJBlock();
while4.setBody(body8);

/* <exp> */ 
OJSimpleStatement exp29 = new OJSimpleStatement("Object elem = it.next()");
body8.addToStatements( exp29 );

/* <exp/> */ 

/* <if> */ 
OJIfStatement if16 = new OJIfStatement();
if16.setCondition("elem instanceof " + StdlibMap.javaCollectionType.getTypeName());
body8.addToStatements(if16);

/* <then> */ 
OJBlock then16 = new OJBlock();
if16.setThenPart(then16);

/* <exp> */ 
OJSimpleStatement exp30 = new OJSimpleStatement("result.addAll( " + operName + "(elem))");
then16.addToStatements( exp30 );

/* <exp/> */ 

/* <then/> */ 

/* <else> */ 
OJBlock else5 = new OJBlock();
if16.setElsePart(else5);

/* <exp> */ 
OJSimpleStatement exp31 = new OJSimpleStatement("result.add(elem)");
else5.addToStatements( exp31 );

/* <exp/> */ 

/* <else/> */ 

/* <if/> */ 

/* <body/> */ 

/* <while/> */ 

/* <then/> */ 

/* <if/> */ 

/* <exp> */ 
OJSimpleStatement exp32 = new OJSimpleStatement("return result");
body7.addToStatements( exp32 );

/* <exp/> */ 

/* <body/> */ 

/* <method/> */ 

/* <octel/> */ 


	}

	private void addAsBagOpers() {
		OJPathName myType = StdlibMap.javaBagType;
		OJPathName myImplType = StdlibMap.javaBagImplType;
		makeAsCollectionOper("myString", "stringAsBag", StdlibMap.javaStringType, myType, myImplType);
		makeAsCollectionOper("myInt", "intAsBag", StdlibMap.javaIntegerObjectType, myType, myImplType);
		makeAsCollectionOper("myReal", "realAsBag", StdlibMap.javaRealObjectType, myType, myImplType);
		makeAsCollectionOper("myBool", "boolAsBag", StdlibMap.javaBooleanObjectType, myType, myImplType);
		makeAsCollectionOper("myObject", "objectAsBag", new OJPathName("T"), myType, myImplType);
		makeCollAsXOper("myCollection", "collectionAsBag", StdlibMap.javaCollectionType, myType, myImplType);
	}
	private void addAsOrdOpers() {
		OJPathName myType = StdlibMap.javaOrderedSetType;
		OJPathName myImplType = StdlibMap.javaOrderedSetImplType;
		makeAsCollectionOper("myString", "stringAsOrderedSet", StdlibMap.javaStringType, myType, myImplType);
		makeAsCollectionOper("myInt", "intAsOrderedSet", StdlibMap.javaIntegerObjectType, myType, myImplType);
		makeAsCollectionOper("myReal", "realAsOrderedSet", StdlibMap.javaRealObjectType, myType, myImplType);
		makeAsCollectionOper("myBool", "boolAsOrderedSet", StdlibMap.javaBooleanObjectType, myType, myImplType);
		makeAsCollectionOper("myObject", "objectAsOrderedSet", new OJPathName("T"), myType, myImplType);
		makeCollAsXOper("myCollection", "collectionAsOrderedSet", StdlibMap.javaCollectionType, myType, myImplType);
	}
	private void addAsSeqOpers() {
		OJPathName myType = StdlibMap.javaSequenceType;
		OJPathName myImplType = StdlibMap.javaSequenceImplType;
		makeAsCollectionOper("myString", "stringAsSequence", StdlibMap.javaStringType, myType, myImplType);
		makeAsCollectionOper("myInt", "intAsSequence", StdlibMap.javaIntegerObjectType, myType, myImplType);
		makeAsCollectionOper("myReal", "realAsSequence", StdlibMap.javaRealObjectType, myType, myImplType);
		makeAsCollectionOper("myBool", "boolAsSequence", StdlibMap.javaBooleanObjectType, myType, myImplType);
		makeAsCollectionOper("myObject", "objectAsSequence", new OJPathName("T"), myType, myImplType);
		makeCollAsXOper("myCollection", "collectionAsSequence", StdlibMap.javaCollectionType, myType, myImplType);
	}
	private void addAsSetOpers() {
		OJPathName myType = StdlibMap.javaSetType;
		OJPathName myImplType = StdlibMap.javaSetImplType;
		makeAsCollectionOper("myString", "stringAsSet", StdlibMap.javaStringType, myType, myImplType);
		makeAsCollectionOper("myInt", "intAsSet", StdlibMap.javaIntegerObjectType, myType, myImplType);
		makeAsCollectionOper("myReal", "realAsSet", StdlibMap.javaRealObjectType, myType, myImplType);
		makeAsCollectionOper("myBool", "boolAsSet", StdlibMap.javaBooleanObjectType, myType, myImplType);
		makeAsCollectionOper("myObject", "objectAsSet", new OJPathName("T"), myType, myImplType);
		makeCollAsXOper("myCollection", "collectionAsSet", StdlibMap.javaCollectionType, myType, myImplType);
	}

	private void makeCollAsXOper(String paramName, String operName, 
			 							OJPathName elementType, OJPathName myType, OJPathName myImplType) {
		OJPathName RESULT_TYPE = myType.getCopy();
		OJPathName ELEM_TYPE = elementType.getCopy();
		OJPathName IMPL_TYPE = myImplType.getCopy();
		OJPathName genericTypeParam = new OJPathName("T");
		RESULT_TYPE.addToElementTypes(genericTypeParam);
		ELEM_TYPE.addToElementTypes(genericTypeParam);
		IMPL_TYPE.addToElementTypes(genericTypeParam);
		String INIT_VALUE = "new " + IMPL_TYPE.getCollectionTypeName() + "()";
			
		/**<octel var="stdlibCls">
		
 		<method genparam="%genericTypeParam%" static="%true%" type="%RESULT_TYPE%" name ="%operName%">
 		<param type="%ELEM_TYPE%" name="%paramName%"/>
 		<body>
			%RESULT_TYPE.getCollectionTypeName()% result = %INIT_VALUE%;
			<if>%paramName% != null<then>
				result.addAll( %paramName% );
			</then></if>
			return result;
		</body></method>

 		</octel>*/

/* <octel> */ 

/* <method> */ 
OJOperation method2 = new OJOperation();
stdlibCls.addToOperations(method2);

/* <genparam> */ 
method2.setGenericTypeParam(genericTypeParam);

/* <genparam/> */ 

/* <static> */ 
method2.setStatic(true);

/* <static/> */ 

/* <type> */ 
method2.setReturnType(RESULT_TYPE);

/* <type/> */ 

/* <name> */ 
method2.setName(operName);

/* <name/> */ 

/* <param> */ 
OJParameter param12 = new OJParameter();
method2.addToParameters(param12);

/* <type> */ 
param12.setType(ELEM_TYPE);

/* <type/> */ 

/* <name> */ 
param12.setName(paramName);

/* <name/> */ 

/* <param/> */ 

/* <body> */ 
OJBlock body9 = new OJBlock();
method2.setBody(body9);

/* <exp> */ 
OJSimpleStatement exp33 = new OJSimpleStatement(RESULT_TYPE.getCollectionTypeName() + " result = " + INIT_VALUE);
body9.addToStatements( exp33 );

/* <exp/> */ 

/* <if> */ 
OJIfStatement if17 = new OJIfStatement();
if17.setCondition(paramName + " != null");
body9.addToStatements(if17);

/* <then> */ 
OJBlock then17 = new OJBlock();
if17.setThenPart(then17);

/* <exp> */ 
OJSimpleStatement exp34 = new OJSimpleStatement("result.addAll( " + paramName + " )");
then17.addToStatements( exp34 );

/* <exp/> */ 

/* <then/> */ 

/* <if/> */ 

/* <exp> */ 
OJSimpleStatement exp35 = new OJSimpleStatement("return result");
body9.addToStatements( exp35 );

/* <exp/> */ 

/* <body/> */ 

/* <method/> */ 

/* <octel/> */ 


	}

	private void makeAsCollectionOper(String paramName, String operName, 
											 OJPathName elementType, OJPathName myType, OJPathName myImplType) {
		OJPathName RESULT_TYPE = myType.getCopy();
		OJPathName IMPL_TYPE = myImplType.getCopy();

		if (elementType == StdlibMap.javaCollectionType) {
			String INIT_VALUE = "new " + IMPL_TYPE.getCollectionTypeName() + "()";
			/**<octel var="stdlibCls">
			
	 		<method static="%true%" type="%RESULT_TYPE%" name ="%operName%">
	 		<param type="%elementType%" name="%paramName%"/>
	 		<body>
				%RESULT_TYPE.getCollectionTypeName()% result = %INIT_VALUE%;
				<if>%paramName% != null<then>
					result.addAll( %paramName% );
				</then></if>
				return result;
			</body></method>

	 		</octel>*/

/* <octel> */ 

/* <method> */ 
OJOperation method3 = new OJOperation();
stdlibCls.addToOperations(method3);

/* <static> */ 
method3.setStatic(true);

/* <static/> */ 

/* <type> */ 
method3.setReturnType(RESULT_TYPE);

/* <type/> */ 

/* <name> */ 
method3.setName(operName);

/* <name/> */ 

/* <param> */ 
OJParameter param13 = new OJParameter();
method3.addToParameters(param13);

/* <type> */ 
param13.setType(elementType);

/* <type/> */ 

/* <name> */ 
param13.setName(paramName);

/* <name/> */ 

/* <param/> */ 

/* <body> */ 
OJBlock body10 = new OJBlock();
method3.setBody(body10);

/* <exp> */ 
OJSimpleStatement exp36 = new OJSimpleStatement(RESULT_TYPE.getCollectionTypeName() + " result = " + INIT_VALUE);
body10.addToStatements( exp36 );

/* <exp/> */ 

/* <if> */ 
OJIfStatement if18 = new OJIfStatement();
if18.setCondition(paramName + " != null");
body10.addToStatements(if18);

/* <then> */ 
OJBlock then18 = new OJBlock();
if18.setThenPart(then18);

/* <exp> */ 
OJSimpleStatement exp37 = new OJSimpleStatement("result.addAll( " + paramName + " )");
then18.addToStatements( exp37 );

/* <exp/> */ 

/* <then/> */ 

/* <if/> */ 

/* <exp> */ 
OJSimpleStatement exp38 = new OJSimpleStatement("return result");
body10.addToStatements( exp38 );

/* <exp/> */ 

/* <body/> */ 

/* <method/> */ 

/* <octel/> */ 



		} else {
			String elemToAdd;
			boolean nonPrim;
			if (elementType.equals(StdlibMap.javaIntegerType)) {
				elemToAdd = "new " + StdlibMap.javaIntegerObjectType.getLast() + "(" + paramName + ")";
				RESULT_TYPE.addToElementTypes(StdlibMap.javaIntegerObjectType);
				IMPL_TYPE.addToElementTypes(StdlibMap.javaIntegerObjectType);
				nonPrim= false;
			} else if (elementType.equals(StdlibMap.javaRealType)) {
				elemToAdd = "new " + StdlibMap.javaRealObjectType.getLast() + "(" + paramName + ")";
				RESULT_TYPE.addToElementTypes(StdlibMap.javaRealObjectType);
				IMPL_TYPE.addToElementTypes(StdlibMap.javaRealObjectType);
				nonPrim= false;
			} else if (elementType.equals(StdlibMap.javaBooleanType)) {
				elemToAdd = "new " + StdlibMap.javaBooleanObjectType.getLast() + "(" + paramName + ")";
				RESULT_TYPE.addToElementTypes(StdlibMap.javaBooleanObjectType);
				IMPL_TYPE.addToElementTypes(StdlibMap.javaBooleanObjectType);
				nonPrim= false;
			}  else {
				elemToAdd = paramName;
				RESULT_TYPE.addToElementTypes(elementType);
				IMPL_TYPE.addToElementTypes(elementType);
				nonPrim= true;
			}
			String INIT_VALUE = "new " + IMPL_TYPE.getCollectionTypeName() + "()";
				
			/**<octel var="stdlibCls">
			
	 		<method static="%true%" type="%RESULT_TYPE%" name ="%operName%">
	 		<param type="%elementType%" name="%paramName%"/>
	 		<body>
				%RESULT_TYPE.getCollectionTypeName()% result = %INIT_VALUE%;
				<if octel_condition="%nonPrim%">%paramName% != null<then>
					result.add( %elemToAdd% );
				</then></if>
				<exp octel_condition="%!nonPrim%"> result.add( %elemToAdd% )</exp>
				return result;
			</body></method>
	
	 		</octel>*/

/* <octel> */ 

/* <method> */ 
OJOperation method4 = new OJOperation();
stdlibCls.addToOperations(method4);

/* <static> */ 
method4.setStatic(true);

/* <static/> */ 

/* <type> */ 
method4.setReturnType(RESULT_TYPE);

/* <type/> */ 

/* <name> */ 
method4.setName(operName);

/* <name/> */ 

/* <param> */ 
OJParameter param14 = new OJParameter();
method4.addToParameters(param14);

/* <type> */ 
param14.setType(elementType);

/* <type/> */ 

/* <name> */ 
param14.setName(paramName);

/* <name/> */ 

/* <param/> */ 

/* <body> */ 
OJBlock body11 = new OJBlock();
method4.setBody(body11);

/* <exp> */ 
OJSimpleStatement exp39 = new OJSimpleStatement(RESULT_TYPE.getCollectionTypeName() + " result = " + INIT_VALUE);
body11.addToStatements( exp39 );

/* <exp/> */ 

/* <if> */ 
if( nonPrim ) {
OJIfStatement if19 = new OJIfStatement();
if19.setCondition(paramName + " != null");
body11.addToStatements(if19);

/* <then> */ 
OJBlock then19 = new OJBlock();
if19.setThenPart(then19);

/* <exp> */ 
OJSimpleStatement exp40 = new OJSimpleStatement("result.add( " + elemToAdd + " )");
then19.addToStatements( exp40 );

/* <exp/> */ 

/* <then/> */ 

/* <if/> */ 
}

/* <exp> */ 
if( !nonPrim ) {
OJSimpleStatement exp41 = new OJSimpleStatement("result.add( " + elemToAdd + " )");
body11.addToStatements( exp41 );

/* <exp/> */ 
}

/* <exp> */ 
OJSimpleStatement exp42 = new OJSimpleStatement("return result");
body11.addToStatements( exp42 );

/* <exp/> */ 

/* <body/> */ 

/* <method/> */ 

/* <octel/> */ 


		}
		
	}

	/**
	 * @return
	 */
	private void makeIncludingX(OJPathName myType, OJPathName myImplType) {
		String operName = "including";
		OJPathName RESULT_TYPE = myType.getCopy();
		OJPathName IMPL_TYPE = myImplType.getCopy();
		OJPathName genericTypeParam = new OJPathName("T");
		RESULT_TYPE.addToElementTypes(genericTypeParam);
		IMPL_TYPE.addToElementTypes(genericTypeParam);
		String INIT_VALUE = "new " + IMPL_TYPE.getCollectionTypeName() + "(mySource)";
			
		/**<octel var="stdlibCls">
		
 		<method genparam="%genericTypeParam%" static="%true%" type="%RESULT_TYPE%" name ="%operName%">
 		<param type="%RESULT_TYPE%" name="mySource"/>
 		<param type="%genericTypeParam%" name="myElem"/>
 		<body>
			%RESULT_TYPE.getCollectionTypeName()% result = %INIT_VALUE%;
			<if>myElem != null<then>
				result.add(myElem);
			</then></if>
			return result;
		</body></method>

 		</octel>*/

/* <octel> */ 

/* <method> */ 
OJOperation method5 = new OJOperation();
stdlibCls.addToOperations(method5);

/* <genparam> */ 
method5.setGenericTypeParam(genericTypeParam);

/* <genparam/> */ 

/* <static> */ 
method5.setStatic(true);

/* <static/> */ 

/* <type> */ 
method5.setReturnType(RESULT_TYPE);

/* <type/> */ 

/* <name> */ 
method5.setName(operName);

/* <name/> */ 

/* <param> */ 
OJParameter param15 = new OJParameter();
method5.addToParameters(param15);

/* <type> */ 
param15.setType(RESULT_TYPE);

/* <type/> */ 

/* <name> */ 
param15.setName("mySource");

/* <name/> */ 

/* <param/> */ 

/* <param> */ 
OJParameter param16 = new OJParameter();
method5.addToParameters(param16);

/* <type> */ 
param16.setType(genericTypeParam);

/* <type/> */ 

/* <name> */ 
param16.setName("myElem");

/* <name/> */ 

/* <param/> */ 

/* <body> */ 
OJBlock body12 = new OJBlock();
method5.setBody(body12);

/* <exp> */ 
OJSimpleStatement exp43 = new OJSimpleStatement(RESULT_TYPE.getCollectionTypeName() + " result = " + INIT_VALUE);
body12.addToStatements( exp43 );

/* <exp/> */ 

/* <if> */ 
OJIfStatement if20 = new OJIfStatement();
if20.setCondition("myElem != null");
body12.addToStatements(if20);

/* <then> */ 
OJBlock then20 = new OJBlock();
if20.setThenPart(then20);

/* <exp> */ 
OJSimpleStatement exp44 = new OJSimpleStatement("result.add(myElem)");
then20.addToStatements( exp44 );

/* <exp/> */ 

/* <then/> */ 

/* <if/> */ 

/* <exp> */ 
OJSimpleStatement exp45 = new OJSimpleStatement("return result");
body12.addToStatements( exp45 );

/* <exp/> */ 

/* <body/> */ 

/* <method/> */ 

/* <octel/> */ 



//		static public <T> Set<T> including(Set<T> mySource, T myElem) {
//			Set<T> result = new HashSet<T>(mySource);
//			result.add(myElem);
//			return result;
//		}
	}

	private void makeExcludingX(OJPathName myType, OJPathName myImplType) {
		String operName = "excluding";
		OJPathName RESULT_TYPE = myType.getCopy();
		OJPathName IMPL_TYPE = myImplType.getCopy();
		OJPathName genericTypeParam = new OJPathName("T");
		RESULT_TYPE.addToElementTypes(genericTypeParam);
		IMPL_TYPE.addToElementTypes(genericTypeParam);
		String INIT_VALUE = "new " + IMPL_TYPE.getCollectionTypeName() + "(mySource)";
			
		/**<octel var="stdlibCls">
		
 		<method genparam="%genericTypeParam%" static="%true%" type="%RESULT_TYPE%" name ="%operName%">
 		<param type="%RESULT_TYPE%" name="mySource"/>
 		<param type="%genericTypeParam%" name="myElem"/>
 		<body>
			%RESULT_TYPE.getCollectionTypeName()% result = %INIT_VALUE%;
			<if>myElem != null<then>
				result.remove(myElem);
			</then></if>
			return result;
		</body></method>

 		</octel>*/

/* <octel> */ 

/* <method> */ 
OJOperation method6 = new OJOperation();
stdlibCls.addToOperations(method6);

/* <genparam> */ 
method6.setGenericTypeParam(genericTypeParam);

/* <genparam/> */ 

/* <static> */ 
method6.setStatic(true);

/* <static/> */ 

/* <type> */ 
method6.setReturnType(RESULT_TYPE);

/* <type/> */ 

/* <name> */ 
method6.setName(operName);

/* <name/> */ 

/* <param> */ 
OJParameter param17 = new OJParameter();
method6.addToParameters(param17);

/* <type> */ 
param17.setType(RESULT_TYPE);

/* <type/> */ 

/* <name> */ 
param17.setName("mySource");

/* <name/> */ 

/* <param/> */ 

/* <param> */ 
OJParameter param18 = new OJParameter();
method6.addToParameters(param18);

/* <type> */ 
param18.setType(genericTypeParam);

/* <type/> */ 

/* <name> */ 
param18.setName("myElem");

/* <name/> */ 

/* <param/> */ 

/* <body> */ 
OJBlock body13 = new OJBlock();
method6.setBody(body13);

/* <exp> */ 
OJSimpleStatement exp46 = new OJSimpleStatement(RESULT_TYPE.getCollectionTypeName() + " result = " + INIT_VALUE);
body13.addToStatements( exp46 );

/* <exp/> */ 

/* <if> */ 
OJIfStatement if21 = new OJIfStatement();
if21.setCondition("myElem != null");
body13.addToStatements(if21);

/* <then> */ 
OJBlock then21 = new OJBlock();
if21.setThenPart(then21);

/* <exp> */ 
OJSimpleStatement exp47 = new OJSimpleStatement("result.remove(myElem)");
then21.addToStatements( exp47 );

/* <exp/> */ 

/* <then/> */ 

/* <if/> */ 

/* <exp> */ 
OJSimpleStatement exp48 = new OJSimpleStatement("return result");
body13.addToStatements( exp48 );

/* <exp/> */ 

/* <body/> */ 

/* <method/> */ 

/* <octel/> */ 



//		static public <T> Set<T> excluding(Set<T> mySource, Object myElem) {
//			Set<T> result = new HashSet<T>(mySource);
//			result.remove(myElem);
//			return result;
//		}
	}

	/**
	 * @return
	 */
	private void makeIntersection() {
		String operName = "intersection";
		OJPathName RESULT_TYPE = StdlibMap.javaSetType.getCopy();
		OJPathName PARAM_TYPE = StdlibMap.javaCollectionType.getCopy();
		OJPathName IMPL_TYPE = JavaPathNames.TreeSet.getCopy();
		OJPathName genericTypeParam = new OJPathName("T");
		RESULT_TYPE.addToElementTypes(genericTypeParam);
		PARAM_TYPE.addToElementTypes(genericTypeParam);
		IMPL_TYPE.addToElementTypes(genericTypeParam);
		String INIT_VALUE = "new " + IMPL_TYPE.getCollectionTypeName() + "(mySource)";
			
		/**<octel var="stdlibCls">
		
 		<method genparam="%genericTypeParam%" static="%true%" type="%RESULT_TYPE%" name ="%operName%">
 		<param type="%PARAM_TYPE%" name="mySource"/>
 		<param type="%PARAM_TYPE%" name="myElem"/>
 		<body>
			%RESULT_TYPE.getCollectionTypeName()% result = %INIT_VALUE%;
			<if>myElem != null<then>
				result.retainAll(myElem);
			</then></if>
			return result;
		</body></method>

 		</octel>*/

/* <octel> */ 

/* <method> */ 
OJOperation method7 = new OJOperation();
stdlibCls.addToOperations(method7);

/* <genparam> */ 
method7.setGenericTypeParam(genericTypeParam);

/* <genparam/> */ 

/* <static> */ 
method7.setStatic(true);

/* <static/> */ 

/* <type> */ 
method7.setReturnType(RESULT_TYPE);

/* <type/> */ 

/* <name> */ 
method7.setName(operName);

/* <name/> */ 

/* <param> */ 
OJParameter param19 = new OJParameter();
method7.addToParameters(param19);

/* <type> */ 
param19.setType(PARAM_TYPE);

/* <type/> */ 

/* <name> */ 
param19.setName("mySource");

/* <name/> */ 

/* <param/> */ 

/* <param> */ 
OJParameter param20 = new OJParameter();
method7.addToParameters(param20);

/* <type> */ 
param20.setType(PARAM_TYPE);

/* <type/> */ 

/* <name> */ 
param20.setName("myElem");

/* <name/> */ 

/* <param/> */ 

/* <body> */ 
OJBlock body14 = new OJBlock();
method7.setBody(body14);

/* <exp> */ 
OJSimpleStatement exp49 = new OJSimpleStatement(RESULT_TYPE.getCollectionTypeName() + " result = " + INIT_VALUE);
body14.addToStatements( exp49 );

/* <exp/> */ 

/* <if> */ 
OJIfStatement if22 = new OJIfStatement();
if22.setCondition("myElem != null");
body14.addToStatements(if22);

/* <then> */ 
OJBlock then22 = new OJBlock();
if22.setThenPart(then22);

/* <exp> */ 
OJSimpleStatement exp50 = new OJSimpleStatement("result.retainAll(myElem)");
then22.addToStatements( exp50 );

/* <exp/> */ 

/* <then/> */ 

/* <if/> */ 

/* <exp> */ 
OJSimpleStatement exp51 = new OJSimpleStatement("return result");
body14.addToStatements( exp51 );

/* <exp/> */ 

/* <body/> */ 

/* <method/> */ 

/* <octel/> */ 


//		static public <T> Set intersection(Collection<T> mySource, Collection myElem) {
//			Set<T> intersection = new TreeSet<T>( mySource );
//			intersection.retainAll(myElem);
//			return intersection;
//		}
		
	}

	private void makeInsertAt(OJPathName myType) {
//		OJOperation oper = new OJOperation(); 
//		oper.setReturnType(myType);
//		String sourceName = "mySource";
//		String indexName = "myIndex";
//		String paramName = "myElem";
//		oper.setStatic(true);
//		oper.setVisibility(OJVisibilityKind.PUBLIC);
//		oper.addParam(sourceName, myType);	
//		oper.addParam(indexName, "java.lang.int");
//		oper.addParam(paramName, "java.lang.Object");
//		oper.setName("insertAt");
//		oper.getBody().addToStatements("if( " + sourceName + " == null ) return null");
//		oper.getBody().addToStatements(sourceName + ".add(" + indexName + ", " + paramName + ")");
//		oper.getBody().addToStatements("return " + sourceName);
//		return oper;
		String operName = "insertAt";
		OJPathName RESULT_TYPE = myType.getCopy();
		OJPathName PARAM_TYPE = StdlibMap.javaIntegerType;
		OJPathName genericTypeParam = new OJPathName("T");
		RESULT_TYPE.addToElementTypes(genericTypeParam);
			
		/**<octel var="stdlibCls">
		
 		<method genparam="%genericTypeParam%" static="%true%" type="%RESULT_TYPE%" name ="%operName%">
 		<param type="%RESULT_TYPE%" name="mySource"/>
 		<param type="%PARAM_TYPE%" name="myIndex"/>
 		<param type="%genericTypeParam%" name="myElem"/>
 		<body>
			<if>mySource == null<then>
				return null;
			</then></if>
			<if>myElem != null<then>
				mySource.add(myIndex, myElem);
			</then></if>
			return mySource;
		</body></method>

 		</octel>*/

/* <octel> */ 

/* <method> */ 
OJOperation method8 = new OJOperation();
stdlibCls.addToOperations(method8);

/* <genparam> */ 
method8.setGenericTypeParam(genericTypeParam);

/* <genparam/> */ 

/* <static> */ 
method8.setStatic(true);

/* <static/> */ 

/* <type> */ 
method8.setReturnType(RESULT_TYPE);

/* <type/> */ 

/* <name> */ 
method8.setName(operName);

/* <name/> */ 

/* <param> */ 
OJParameter param21 = new OJParameter();
method8.addToParameters(param21);

/* <type> */ 
param21.setType(RESULT_TYPE);

/* <type/> */ 

/* <name> */ 
param21.setName("mySource");

/* <name/> */ 

/* <param/> */ 

/* <param> */ 
OJParameter param22 = new OJParameter();
method8.addToParameters(param22);

/* <type> */ 
param22.setType(PARAM_TYPE);

/* <type/> */ 

/* <name> */ 
param22.setName("myIndex");

/* <name/> */ 

/* <param/> */ 

/* <param> */ 
OJParameter param23 = new OJParameter();
method8.addToParameters(param23);

/* <type> */ 
param23.setType(genericTypeParam);

/* <type/> */ 

/* <name> */ 
param23.setName("myElem");

/* <name/> */ 

/* <param/> */ 

/* <body> */ 
OJBlock body15 = new OJBlock();
method8.setBody(body15);

/* <if> */ 
OJIfStatement if23 = new OJIfStatement();
if23.setCondition("mySource == null");
body15.addToStatements(if23);

/* <then> */ 
OJBlock then23 = new OJBlock();
if23.setThenPart(then23);

/* <exp> */ 
OJSimpleStatement exp52 = new OJSimpleStatement("return null");
then23.addToStatements( exp52 );

/* <exp/> */ 

/* <then/> */ 

/* <if/> */ 

/* <if> */ 
OJIfStatement if24 = new OJIfStatement();
if24.setCondition("myElem != null");
body15.addToStatements(if24);

/* <then> */ 
OJBlock then24 = new OJBlock();
if24.setThenPart(then24);

/* <exp> */ 
OJSimpleStatement exp53 = new OJSimpleStatement("mySource.add(myIndex, myElem)");
then24.addToStatements( exp53 );

/* <exp/> */ 

/* <then/> */ 

/* <if/> */ 

/* <exp> */ 
OJSimpleStatement exp54 = new OJSimpleStatement("return mySource");
body15.addToStatements( exp54 );

/* <exp/> */ 

/* <body/> */ 

/* <method/> */ 

/* <octel/> */ 


//		static public <T> List<T> insertAt(List<T> mySource, int myIndex, T myElem) {
//			if( mySource == null ) return null;
//			mySource.add(myIndex, myElem);
//			return mySource;
//		}
		
	}
}
