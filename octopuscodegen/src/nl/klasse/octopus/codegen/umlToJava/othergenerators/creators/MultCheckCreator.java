package nl.klasse.octopus.codegen.umlToJava.othergenerators.creators;
/**<octel>
<java>
	%import net.sf.nakeduml.javametamodel.OJSimpleStatement%;
	%inet.sfsf.nakeduml.javametamodelametamodel.model.OJIfStatement%;
</java>
</octel>*/

/* <octel> */ 

/* <java> */ 

/* <inline> */ 
import net.sf.nakeduml.javametamodel.OJBlock;
import net.sf.nakeduml.javametamodel.OJClassifier;
import net.sf.nakeduml.javametamodel.OJIfStatement;
import net.sf.nakeduml.javametamodel.OJOperation;
import net.sf.nakeduml.javametamodel.OJPathName;
import net.sf.nakeduml.javametamodel.OJSimpleStatement;
import net.sf.nakeduml.javametamodel.utilities.JavaPathNames;
import nl.klasse.octopus.codegen.helpers.CommonNames;
import nl.klasse.octopus.codegen.umlToJava.expgenerators.visitors.OclUtilityCreator;
import nl.klasse.octopus.codegen.umlToJava.maps.StructuralFeatureMap;
import nl.klasse.octopus.model.IStructuralFeature;
import nl.klasse.octopus.model.internal.types.MultiplicityKindImpl;


public class MultCheckCreator {
	private StructuralFeatureMap FEATURE = null;
	private OJClassifier owner = null;
	private OJBlock body = null;
	private OJPathName ERROR_PATH = null;
	private String getIdStrName = CommonNames.getIdStrName;

	public MultCheckCreator() { 
		super();
	}

	public void createCheckOper(OJClassifier owner) { 
		this.owner = owner;
		String OPER_NAME = "checkMultiplicities";

		// get the error path
		ERROR_PATH = OclUtilityCreator.getInvErrorPath();
		OJPathName myTypePath = JavaPathNames.List.getCopy();
		myTypePath.addToElementTypes(OclUtilityCreator.getInvErrorPath());
		String myType = myTypePath.getCollectionTypeName();
		String myDefault = "ArrayList<" + OclUtilityCreator.getInvErrorPath().getLast() +">";
		
		OJOperation myOper = null;

		/**<octel var="owner">
		<method type="%myTypePath%" name="%OPER_NAME%" var="myOper"> 
		<comment> implements a check on the multiplicities of all attributes and association ends </comment>
	  	<body>
			%myType% result = new %myDefault%();
	  	</body>
	  	</method>
	  	</octel>*/

/* <octel> */ 

/* <method> */ 
myOper = new OJOperation();
owner.addToOperations(myOper);

/* <type> */ 
myOper.setReturnType(myTypePath);

/* <type/> */ 

/* <name> */ 
myOper.setName(OPER_NAME);

/* <name/> */ 

/* <comment> */ 
myOper.setComment("implements a check on the multiplicities of all attributes and association ends");

/* <comment/> */ 

/* <body> */ 
OJBlock body1 = new OJBlock();
myOper.setBody(body1);

/* <exp> */ 
OJSimpleStatement exp1 = new OJSimpleStatement(myType + " result = new " + myDefault + "()");
body1.addToStatements( exp1 );

/* <exp/> */ 

/* <body/> */ 

/* <method/> */ 

/* <octel/> */ 


		
		body = myOper.getBody();
	}

	public void finishCheckOper() {
		/**<octel var="body">
		 	<exp> return result </exp>
		</octel>*/				

/* <octel> */ 

/* <exp> */ 
OJSimpleStatement exp2 = new OJSimpleStatement("return result");
body.addToStatements( exp2 );

/* <exp/> */ 

/* <octel/> */ 


	}
	public void structuralfeature(IStructuralFeature feat) {
		int upper = feat.getMultiplicity().getUpper();
		int lower = feat.getMultiplicity().getLower();
		if (lower == 1 && upper == 1) {
			generateNonOptional(feat);
		} else if (upper > 1) {
			if (upper != MultiplicityKindImpl.MAX) {
				generateUpper(feat, upper);
			}
			if (lower > 0) {
				generateLower(feat, lower);
			}
		}
	}


	private void generateNonOptional(IStructuralFeature att){
		FEATURE = new StructuralFeatureMap(att);
		if (FEATURE.isJavaPrimitive()) return;
		owner.addToImports(ERROR_PATH);
		
		/**<octel var="body">
  			<if> %FEATURE.getter()%() == null <then>
  			String message = "Mandatory feature '%att.getName()%' in object '";
  			message = message + this.%getIdStrName%();
  			message = message + "' of type '" + this.getClass().getName() + "' has no value.";
  			result.add(new %ERROR_PATH.getLast()%(this, message));
  			</then></if>
	  	</octel>*/		

/* <octel> */ 

/* <if> */ 
OJIfStatement if1 = new OJIfStatement();
if1.setCondition(FEATURE.getter() + "() == null");
body.addToStatements(if1);

/* <then> */ 
OJBlock then1 = new OJBlock();
if1.setThenPart(then1);

/* <exp> */ 
OJSimpleStatement exp3 = new OJSimpleStatement("String message = \"Mandatory feature '" + att.getName() + "' in object '\"");
then1.addToStatements( exp3 );

/* <exp/> */ 

/* <exp> */ 
OJSimpleStatement exp4 = new OJSimpleStatement("message = message + this." + getIdStrName + "()");
then1.addToStatements( exp4 );

/* <exp/> */ 

/* <exp> */ 
OJSimpleStatement exp5 = new OJSimpleStatement("message = message + \"' of type '\" + this.getClass().getName() + \"' has no value.\"");
then1.addToStatements( exp5 );

/* <exp/> */ 

/* <exp> */ 
OJSimpleStatement exp6 = new OJSimpleStatement("result.add(new " + ERROR_PATH.getLast() + "(this, message))");
then1.addToStatements( exp6 );

/* <exp/> */ 

/* <then/> */ 

/* <if/> */ 

/* <octel/> */ 


	}
	
	private void generateUpper(IStructuralFeature att, int upper) {
		FEATURE = new StructuralFeatureMap(att);
		owner.addToImports(ERROR_PATH);

		/**<octel var="body">
  			<if> %FEATURE.getter()%().size() &gt; %upper% <then>
  			String message = "Upper bound of feature '%att.getName()%' in object '";
  			message = message + this.%getIdStrName%();
  			message = message + "' of type '" + this.getClass().getName() + "' is %upper%" +
  			", yet it has size " + %FEATURE.getter()%().size() + ".";
  			result.add(new %ERROR_PATH.getLast()%(this, message));
  			</then></if>
	  	</octel>*/		

/* <octel> */ 

/* <if> */ 
OJIfStatement if2 = new OJIfStatement();
if2.setCondition(FEATURE.getter() + "().size() > " + upper);
body.addToStatements(if2);

/* <then> */ 
OJBlock then2 = new OJBlock();
if2.setThenPart(then2);

/* <exp> */ 
OJSimpleStatement exp7 = new OJSimpleStatement("String message = \"Upper bound of feature '" + att.getName() + "' in object '\"");
then2.addToStatements( exp7 );

/* <exp/> */ 

/* <exp> */ 
OJSimpleStatement exp8 = new OJSimpleStatement("message = message + this." + getIdStrName + "()");
then2.addToStatements( exp8 );

/* <exp/> */ 

/* <exp> */ 
OJSimpleStatement exp9 = new OJSimpleStatement("message = message + \"' of type '\" + this.getClass().getName() + \"' is " + upper + "\" +\n"
+ "  			\", yet it has size \" + " + FEATURE.getter() + "().size() + \".\"");
then2.addToStatements( exp9 );

/* <exp/> */ 

/* <exp> */ 
OJSimpleStatement exp10 = new OJSimpleStatement("result.add(new " + ERROR_PATH.getLast() + "(this, message))");
then2.addToStatements( exp10 );

/* <exp/> */ 

/* <then/> */ 

/* <if/> */ 

/* <octel/> */ 


	}

	private void generateLower(IStructuralFeature att, int lower) {
		FEATURE = new StructuralFeatureMap(att);
		owner.addToImports(ERROR_PATH);

		/**<octel var="body">
  			<if> %FEATURE.getter()%().size() &lt; %lower% <then>
  			String message = "Lower bound of feature '%att.getName()%' in object '";
  			message = message + this.%getIdStrName%();
  			message = message + "' of type '" + this.getClass().getName() + "' is %lower%" +
  			", yet it has size " + %FEATURE.getter()%().size() + ".";
  			result.add(new %ERROR_PATH.getLast()%(this, message));
  			</then></if>
	  	</octel>*/		

/* <octel> */ 

/* <if> */ 
OJIfStatement if3 = new OJIfStatement();
if3.setCondition(FEATURE.getter() + "().size() < " + lower);
body.addToStatements(if3);

/* <then> */ 
OJBlock then3 = new OJBlock();
if3.setThenPart(then3);

/* <exp> */ 
OJSimpleStatement exp11 = new OJSimpleStatement("String message = \"Lower bound of feature '" + att.getName() + "' in object '\"");
then3.addToStatements( exp11 );

/* <exp/> */ 

/* <exp> */ 
OJSimpleStatement exp12 = new OJSimpleStatement("message = message + this." + getIdStrName + "()");
then3.addToStatements( exp12 );

/* <exp/> */ 

/* <exp> */ 
OJSimpleStatement exp13 = new OJSimpleStatement("message = message + \"' of type '\" + this.getClass().getName() + \"' is " + lower + "\" +\n"
+ "  			\", yet it has size \" + " + FEATURE.getter() + "().size() + \".\"");
then3.addToStatements( exp13 );

/* <exp/> */ 

/* <exp> */ 
OJSimpleStatement exp14 = new OJSimpleStatement("result.add(new " + ERROR_PATH.getLast() + "(this, message))");
then3.addToStatements( exp14 );

/* <exp/> */ 

/* <then/> */ 

/* <if/> */ 

/* <octel/> */ 


	}

}
