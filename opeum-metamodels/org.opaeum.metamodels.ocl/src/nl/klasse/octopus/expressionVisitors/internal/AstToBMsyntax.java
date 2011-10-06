/** (c) Copyright 2002, Klasse Objecten
 */
package nl.klasse.octopus.expressionVisitors.internal;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import nl.klasse.octopus.expressionVisitors.IAstVisitorBackwards;
import nl.klasse.octopus.expressions.IAssociationClassCallExp;
import nl.klasse.octopus.expressions.IAssociationEndCallExp;
import nl.klasse.octopus.expressions.IAttributeCallExp;
import nl.klasse.octopus.expressions.IBooleanLiteralExp;
import nl.klasse.octopus.expressions.ICollectionItem;
import nl.klasse.octopus.expressions.ICollectionLiteralExp;
import nl.klasse.octopus.expressions.ICollectionRange;
import nl.klasse.octopus.expressions.IEnumLiteralExp;
import nl.klasse.octopus.expressions.IIfExp;
import nl.klasse.octopus.expressions.IIntegerLiteralExp;
import nl.klasse.octopus.expressions.IIterateExp;
import nl.klasse.octopus.expressions.IIteratorExp;
import nl.klasse.octopus.expressions.ILetExp;
import nl.klasse.octopus.expressions.INumericLiteralExp;
import nl.klasse.octopus.expressions.IOclStateLiteralExp;
import nl.klasse.octopus.expressions.IOclTypeLiteralExp;
import nl.klasse.octopus.expressions.IOclUndefinedLiteralExp;
import nl.klasse.octopus.expressions.IOperationCallExp;
import nl.klasse.octopus.expressions.IRealLiteralExp;
import nl.klasse.octopus.expressions.IStringLiteralExp;
import nl.klasse.octopus.expressions.ITupleLiteralExp;
import nl.klasse.octopus.expressions.IUnspecifiedValueExp;
import nl.klasse.octopus.expressions.IVariableDeclaration;
import nl.klasse.octopus.expressions.IVariableExp;
import nl.klasse.octopus.model.ICollectionType;
import nl.klasse.octopus.model.IOperation;
import nl.klasse.tools.common.StringHelpers;


/** A VisitorString traverses the OCL AST and constructs a String representation
 * of the AST.  This is used for printing and debugging purposes.
 * Each operation that returns an Object does in fact return a String.
 *
 * @author  Jos Warmer
 * @version $Id: AstToBMsyntax.java,v 1.2 2008/01/19 13:49:18 annekekleppe Exp $
 */
public class AstToBMsyntax implements IAstVisitorBackwards {
	// pretty print attributes, should be read from file or something
	int linewidth = 60;         // the ideal line width
	int indent = 3;             // number spaces per indentation
	String indentStr = "   ";  // the indentation string
	int strlength = 0;          // the length of the current string from the last newline char
	boolean start = true;       // true when a new string representation is constructed
	int indentLevel = 2;        // the level of indentation
	
	// other attributes
	static String 						tmpVarDeclEquals 		= "_VAR_EQUALS_";
    static Map<String, String> 	collOperFirstKeyword 		= new HashMap<String, String>();
    static Map<String, String> 	collOperSecondKeyword 	= new HashMap<String, String>();
    static Map<String, String> 	collOperMainKeyword 	= new HashMap<String, String>();

    /** Creates a new instance of AstToBMsyntax
      */
    public AstToBMsyntax() {
    	// 
        collOperMainKeyword.put("size", "SIZE OF");
        collOperMainKeyword.put("isEmpty", "IS EMPTY");
        collOperMainKeyword.put("notEmpty", "NOT EMPTY");
        collOperMainKeyword.put("sum", "SUM OF");
        collOperMainKeyword.put("flatten", "FLATTEN");
        collOperMainKeyword.put("asSet", "AS SET");
        collOperMainKeyword.put("asSequence", "AS SEQUENCE");
        collOperMainKeyword.put("asBag", "AS BAG");
        collOperMainKeyword.put("first", "FIRST OF");
        collOperMainKeyword.put("last", "LAST OF");
        collOperMainKeyword.put("includes", "IS");
        collOperMainKeyword.put("excludes", "IS");
        collOperMainKeyword.put("count", "COUNT");
        collOperMainKeyword.put("includesAll", "IS ALL OF");
        collOperMainKeyword.put("excludesAll", "IS ALL OF");
        collOperMainKeyword.put("union", "UNION");
        collOperMainKeyword.put("intersection", "INTERSECTION");
        collOperMainKeyword.put("including", "INCLUDE");
        collOperMainKeyword.put("excluding", "EXCLUDE");
        collOperMainKeyword.put("symmetricDifference", "SYMMETRIC DIFFERENCE");
        collOperMainKeyword.put("append", "APPEND");
        collOperMainKeyword.put("prepend", "PREPEND");
        collOperMainKeyword.put("insertAt", "INSERT AT");
        collOperMainKeyword.put("subSequence", "SUBSEQUENCE");
        collOperMainKeyword.put("at", "AT");
        collOperMainKeyword.put("indexOf", "INDEX OF");
    	// 
        collOperFirstKeyword.put("size", "");
        collOperFirstKeyword.put("isEmpty", "");
        collOperFirstKeyword.put("notEmpty", "");
        collOperFirstKeyword.put("sum", "");
        collOperFirstKeyword.put("flatten", "");
        collOperFirstKeyword.put("asSet", "");
        collOperFirstKeyword.put("asSequence", "");
        collOperFirstKeyword.put("asBag", "");
        collOperFirstKeyword.put("first", "");
        collOperFirstKeyword.put("last", "");
        collOperFirstKeyword.put("includes", "INCLUDED IN");
        collOperFirstKeyword.put("excludes", "NOT INCLUDED IN");
        collOperFirstKeyword.put("count", "IN");
        collOperFirstKeyword.put("includesAll", "INCLUDED IN");
        collOperFirstKeyword.put("excludesAll", "NOT INCLUDED IN");
        collOperFirstKeyword.put("union", "WITH");
        collOperFirstKeyword.put("intersection", "WITH");
        collOperFirstKeyword.put("including", "IN");
        collOperFirstKeyword.put("excluding", "FROM");
        collOperFirstKeyword.put("symmetricDifference", "WITH");
        collOperFirstKeyword.put("append", "TO");
        collOperFirstKeyword.put("prepend", "TO");
        collOperFirstKeyword.put("insertAt", "IN");
        collOperFirstKeyword.put("subSequence", "OF");
        collOperFirstKeyword.put("at", "FROM");
        collOperFirstKeyword.put("indexOf", "FROM");
        //
        collOperSecondKeyword.put("size", "");
        collOperSecondKeyword.put("isEmpty", "");
        collOperSecondKeyword.put("notEmpty", "");
        collOperSecondKeyword.put("sum", "");
        collOperSecondKeyword.put("flatten", "");
        collOperSecondKeyword.put("asSet", "");
        collOperSecondKeyword.put("asSequence", "");
        collOperSecondKeyword.put("asBag", "");
        collOperSecondKeyword.put("first", "");
        collOperSecondKeyword.put("last", "");
        collOperSecondKeyword.put("includes", "");
        collOperSecondKeyword.put("excludes", "");
        collOperSecondKeyword.put("count", "");
        collOperSecondKeyword.put("includesAll", "");
        collOperSecondKeyword.put("excludesAll", "");
        collOperSecondKeyword.put("union", "");
        collOperSecondKeyword.put("intersection", "");
        collOperSecondKeyword.put("including", "");
        collOperSecondKeyword.put("excluding", "");
        collOperSecondKeyword.put("symmetricDifference", "");
        collOperSecondKeyword.put("append", "");
        collOperSecondKeyword.put("prepend", "");
        collOperSecondKeyword.put("insertAt", "AT");
        collOperSecondKeyword.put("subSequence", "TO");
        collOperSecondKeyword.put("at", "");
        collOperSecondKeyword.put("indexOf", "");
    }

	public Object collectionLiteralExp(ICollectionLiteralExp exp, List parts){
		if (!(exp.getNodeType() instanceof ICollectionType )) {
			return "";
		} 
		String iters = itersToString(parts);
		String result = ((ICollectionType) exp.getNodeType()).getMetaType().toString();
		result = addString( result, "{ " );
		result = addStringPossibleBreak( result, iters, true );
		result = addStringPossibleBreak( result, " } ", false );
		return result; 
	}

    public Object collectionRange(ICollectionRange exp, Object first, Object last) {
      return (String)first + " .. " + (String)last;
    }
    
	public Object collectionItem(ICollectionItem exp, Object item) {
		return item;
	} 
    
    public Object letExp(ILetExp exp, Object varDecl, Object in) {
      	// replace the temporary coupling string in the var decl
      	String varStr = StringHelpers.replaceFirstSubstring( (String) varDecl, tmpVarDeclEquals, "BE");
      	//start this expression at the right indent
      	String result = "";
     	result = addString( result, "LET " );
     	// cut a lengthy varStr in two and place the part after "BE" on a new line
     	String temp = varStr;
      	int stop = temp.indexOf("BE");
      	if (stop == -1) { result = temp; } // should not occur
      	stop = stop + 2; //  the newline must be placed after "BE"
     	result = addString( result, temp.substring( 0, stop ) );
       	result = addStringPossibleBreak( result, temp.substring( stop ), true );
      	// place a lengthy 'in' on a new line
   		result = addStringWithBreak( result, "IN ", false );
   		result = addStringWithBreak( result, (String) in, true );
        return result;
    }
    
    public Object associationEndCallExp(IAssociationEndCallExp exp, Object source) {
      if ( source.equals("") ) {
      	return exp.getReferredAssociationEnd().getName();
      } else {
        return (String)source + "."+ exp.getReferredAssociationEnd().getName();
      }
    }
    
    public Object attributeCallExp(IAttributeCallExp exp, Object source) {
      if ( exp.getReferredAttribute().hasClassScope() ) {
      	return exp.getReferredAttribute().getOwner().getName() 
      	       + "::" + exp.getReferredAttribute().getName();
      } else if ( source != null && source.equals("") ) {
      	return exp.getReferredAttribute().getName();
      } else {
        return (String)source + "."+ exp.getReferredAttribute().getName();
      }
    }
    
	public Object booleanLiteralExp(IBooleanLiteralExp exp) {
	  return exp.toString();
	}
    
	public Object oclUndefinedLiteralExp(IOclUndefinedLiteralExp exp) {
	  return exp.toString();
	}
    
    public Object ifExp(IIfExp exp, Object condition, Object thenPart, Object elsePart) {
    	String result = "";
    	result = addString( result, "IF " + (String)condition + " THEN" );
    	result = addStringWithBreak( result, (String) thenPart, true );
    	result = addStringWithBreak( result, "ELSE", false );
    	result = addStringWithBreak( result, (String) elsePart, true );
    	result = addStringWithBreak( result, "ENDIF", false );
        return result;
    }
    
    public Object integerLiteralExp(IIntegerLiteralExp exp) {
      return exp.toString();
    }
    
    public Object oclTypeLiteralExp(IOclTypeLiteralExp exp) {
      return exp.getReferredClassifier().getName();
    }
    
	public Object oclStateLiteralExp (IOclStateLiteralExp     exp){
      return exp.getReferredState().getName();
	}
    
    public Object iteratorExp(IIteratorExp exp, Object source, List iterators, Object body) {
      if( exp.getName().equals("select") ) {
        return selectIterator(exp, source, iterators, body);
      } else if( exp.getName().equals("collect") ) {
        return collectIterator(exp, source, iterators, body);
      } else if( exp.getName().equals("collectNested") ) {
        return collectNestedIterator(exp, source, iterators, body);
      } else if( exp.getName().equals("forAll") ) {
        return forAllIterator(exp, source, iterators, body);
      } else if( exp.getName().equals("exists") ) {
        return existsIterator(exp, source, iterators, body);
      } else if( exp.getName().equals("sortedBy") ) {
        return sortedByIterator(exp, source, iterators, body);
      } else if( exp.getName().equals("isUnique") ) {
        return isUniqueIterator(exp, source, iterators, body);
      } else if( exp.getName().equals("any") ) {
        return anyIterator(exp, source, iterators, body);
      } else if( exp.getName().equals("one") ) {
        return oneIterator(exp, source, iterators, body);
      } else if( exp.getName().equals("reject") ) {
        return rejectIterator(exp, source, iterators, body);
      } 
	  // should not be reached
      return iterator(exp, source, iterators, body);
    }

    public Object selectIterator(IIteratorExp exp, Object source, List iterators, Object body) {
        String iters = itersToString(iterators);
        String result = "";
    	result = addString( result, "SELECT " );
        result = addStringPossibleBreak( result, iters, true );
        result = addStringPossibleBreak( result, " FROM ", false ); // no indent
        result = addStringPossibleBreak( result, (String) source, true );
        result = addStringWithBreak( result, "WHERE ", false ); // no indent
        result = addStringPossibleBreak( result, (body == null ? "<nullbody>" : (String)body), true );
        return result;
    }

    public Object rejectIterator(IIteratorExp exp, Object source, List iterators, Object body) {
        String iters = itersToString(iterators);
        String result = "";
    	result = addString( result, "REJECT " );
        result = addStringPossibleBreak( result, iters, true );
        result = addStringPossibleBreak( result, " FROM ", false ); // no indent
        result = addStringPossibleBreak( result, (String) source, true );
        result = addStringWithBreak( result, "WHERE ", false ); // no indent
        result = addStringPossibleBreak( result, (body == null ? "<nullbody>" : (String)body), true );
        return result;
    }

    public Object collectIterator(IIteratorExp exp, Object source, List iterators, Object body) {
        String iters = itersToString(iterators);
        String result = "";
    	result = addString( result, "COLLECT " );
        result = addStringPossibleBreak( result, (body == null ? "<nullbody>" : (String)body), true );
        result = addStringWithBreak( result, "USING ", false ); // no indent
        result = addString( result, iters );
        result = addStringPossibleBreak( result, " FROM ", false ); // no indent
        result = addStringPossibleBreak( result, (String) source, true );
        return result;
    }

    public Object collectNestedIterator(IIteratorExp exp, Object source, List iterators, Object body) {
        String iters = itersToString(iterators);
        String result = "";
    	result = addString( result, "COLLECTNESTED " );
        result = addStringPossibleBreak( result, (body == null ? "<nullbody>" : (String)body), true );
        result = addStringWithBreak( result, "USING ", false ); // no indent
        result = addString( result, iters );
        result = addStringPossibleBreak( result, " FROM ", false ); // no indent
        result = addStringPossibleBreak( result, (String) source, true );
        return result;
    }

    public Object forAllIterator(IIteratorExp exp, Object source, List iterators, Object body) {
        String iters = itersToString(iterators);
        String result = "";
    	result = addString( result, "FORALL " );
        result = addStringPossibleBreak( result, iters, true );
        result = addStringPossibleBreak( result, " IN ", false );
        result = addStringPossibleBreak( result, (String) source, true );
        result = addStringWithBreak( result, "ISTRUE ", false );
        result = addStringPossibleBreak( result, (body == null ? "<nullbody>" : (String)body), true );
        return result;
    }

    public Object existsIterator(IIteratorExp exp, Object source, List iterators, Object body) {
        String iters = itersToString(iterators);
        String result = "";
    	result = addString( result, "EXISTS " );
        result = addStringPossibleBreak( result, iters, true );
        result = addStringPossibleBreak( result, " IN ", false );
        result = addStringPossibleBreak( result, (String) source, true );
        result = addStringWithBreak( result, " WHERE ", false );
        result = addStringPossibleBreak( result, (body == null ? "<nullbody>" : (String)body), true );        return result;
    }

    public Object sortedByIterator(IIteratorExp exp, Object source, List iterators, Object body) {
        String iters = itersToString(iterators);
        String result = "";
    	result = addString( result, "SORT " );
        result = addStringPossibleBreak( result, (String) source, true );
        result = addStringPossibleBreak( result, " USING ", false );
        result = addStringPossibleBreak( result, iters, true );
        result = addStringPossibleBreak( result, " BY ", false );
        result = addStringPossibleBreak( result, (body == null ? "<nullbody>" : (String)body), true ); 
        return result;
    }

    public Object isUniqueIterator(IIteratorExp exp, Object source, List iterators, Object body) {
        String iters = itersToString(iterators);
        String result = "";
    	result = addString( result, "ISUNIQUE " );
        result = addStringPossibleBreak( result, (body == null ? "<nullbody>" : (String)body), true );
        result = addStringWithBreak( result, " USING ", false ); // no indent
        result = addStringPossibleBreak( result, iters, true );
        result = addStringPossibleBreak( result, " IN ", false ); // no indent
        result = addStringPossibleBreak( result, (String) source, true );
        return result;
    }

    public Object anyIterator(IIteratorExp exp, Object source, List iterators, Object body) {
        String iters = itersToString(iterators);
        String result = "";
    	result = addString( result, "SELECTANY " );
        result = addStringPossibleBreak( result, iters, true );
        result = addStringPossibleBreak( result, " FROM ", false ); // no indent
        result = addStringPossibleBreak( result, (String) source, true );
        result = addStringWithBreak( result, "WHERE ", false ); // no indent
        result = addStringPossibleBreak( result, (body == null ? "<nullbody>" : (String)body), true );
        return result;
    }

    public Object oneIterator(IIteratorExp exp, Object source, List iterators, Object body) {
        String iters = itersToString(iterators);
        String result = "";
    	result = addString( result, "EXISTSONE " );
        result = addStringPossibleBreak( result, iters, true );
        result = addStringPossibleBreak( result, " IN ", false );
        result = addStringPossibleBreak( result, (String) source, true );
        result = addStringWithBreak( result, " WHERE ", false );
        result = addStringPossibleBreak( result, (body == null ? "<nullbody>" : (String)body), true ); 
        return result;
    }

	public Object iterator(IIteratorExp exp, Object source, List iterators, Object body) {
		String iters = itersToString(iterators);
		String result = "";
		result = addString( result, "ITERATEOVER " );
		result = addStringPossibleBreak( result, (String) source, true );
		result = addStringPossibleBreak( result, " USING ", false ); // no indent
		result = addStringPossibleBreak( result, iters, true );
		result = addStringWithBreak( result, "IN ", false ); // no indent
		result = addStringPossibleBreak( result, (body == null ? "<nullbody>" : (String)body), true );
		return result;
	}
	
	private String itersToString(List iterators) {
	  String iters = "";
	  boolean first = true;
	  Iterator it = iterators.iterator();
	  while( it.hasNext() ){
	    if( first ) {
	      first = false;
	    } else {
	      iters = iters + ", ";
	    }
	    // change the temporary coupling string between type and init value in any var decl to "="
	    String tmpStr = (String) it.next();
        tmpStr = StringHelpers.replaceFirstSubstring( tmpStr, tmpVarDeclEquals, "=");
	    iters = iters + tmpStr;
	  }
	  return iters;
	}
    
    public Object numericLiteralExp(INumericLiteralExp exp) {
      return exp.toString();
    }

    private Object DOToperationCallExp(IOperationCallExp exp, Object source, List args) {
        String result = "";
        IOperation referedOper = exp.getReferredOperation();
        if (source != "" ) {
            result = addString( result, (String)source );
            if ( !referedOper.isInfix() ) {
            	result = addString( result, "." );
            } else {
            	result = addString( result, " " );
        	}
        } 
        if( referedOper != null ) {
            result = addString( result, referedOper.getName() );
            if ( !referedOper.isInfix() ) {
            	result = addString( result, "(" );
            }
        } else {
            result = addString( result, exp.getName() + "(" );
        }
        boolean first = true;
        Iterator it = args.iterator();
        while( it.hasNext() ){
            if( first ) {
                first = false;
                result = addString( result, " " );
            } else {
                result = addStringPossibleBreak( result, ", ", true );
            }
            result = addString( result, (String) it.next() );
        }
        if ( !referedOper.isInfix() ) {
        	result = addString( result, " )" );
        }
        return result;
    }

    public Object operationCallExp(IOperationCallExp exp, Object source, List args) {
        if ( exp.getReferredOperation().hasClassScope() ) {
      		return exp.getReferredOperation().getOwner().getName() 
      	       + "::" + DOToperationCallExp(exp, "", args);
        } else if( exp.getReferredOperation().getOwner().isCollectionKind() ) {
            return collectionCall(exp, source, args);
        } else {
            return DOToperationCallExp(exp, source, args);
        }
    }

    private Object collectionCall(IOperationCallExp exp, Object source, List args) {
        String result = "";
        String name = "";
        if( exp.getReferredOperation() != null ) {
            name = name + exp.getReferredOperation().getName();
        } else {
            name = name + exp.getName();
        }
        result = (String) collOperMainKeyword.get( name );
        if ( result == null ) return DOToperationCallExp(exp, "(" + source + ")", args); // it wasn't a collection oper after all
        int nrOfParams = args.size();
        Iterator it = args.iterator();
        if( nrOfParams == 2 ) {
            result = addStringPossibleBreak( result + " ", (String) it.next(), true );
            result = addStringWithBreak( result + " ", (String) collOperSecondKeyword.get( name ), false );
            result = addStringPossibleBreak( result + " ", (String) it.next(), true );
            result = addStringWithBreak( result + " ", (String) collOperFirstKeyword.get( name ), false );
        } else if( nrOfParams == 1 ){
            result = addStringPossibleBreak( result + " ", (String) it.next(), true );
            result = addStringWithBreak( result + " ", (String) collOperFirstKeyword.get( name ), false );
        } 
        result = addStringPossibleBreak( result + " ", (String) source, true );        
        return result; 
    }
    
    public Object realLiteralExp(IRealLiteralExp exp) {
      return exp.toString();
    }
    
    public Object stringLiteralExp(IStringLiteralExp exp) {
      return exp.toString();
    }
    
    public Object tupleLiteralExp(ITupleLiteralExp exp, List parts) {
      String result = "TUPLE{ ";
      boolean first = true;
      Iterator it = parts.iterator();
      while( it.hasNext() ){
        if( first ) {
          first = false;
        } else {
          result = result + ", ";
        }
 		// change the temporary coupling string between type and init value in any var decl to "="
		String tmpStr = (String) it.next();
		tmpStr = StringHelpers.replaceFirstSubstring( tmpStr, tmpVarDeclEquals, "=");
		result = result + tmpStr;
      }
      return result + " }";
    }
    
    public Object variableDeclaration(IVariableDeclaration exp, Object initExpression) {
      if( initExpression == null ) {
        return exp.getName() + " : " + (exp.getType() == null ? "<null>" : exp.getType().getName());
      } else {
        return exp.getName() + " : " + (exp.getType() == null ? "<null>" : exp.getType().getName()) 
               + " " + tmpVarDeclEquals + " " + (String) initExpression;
      }
    }
    
    public Object variableExp(IVariableExp exp) {
      if ( exp.isImplicit() ) {
        return "";
      }
      return exp.getReferredVariable().getName();
    }

    public Object enumLiteralExp(IEnumLiteralExp exp) {
      return exp.getReferredEnumLiteral().getEnumeration().getName() + "::" +
             exp.getReferredEnumLiteral().getName();
    }
    
    public Object iterateExp(IIterateExp exp, Object source, Object resultVar, List iterators, Object body) {
		String[] iters = iterateItersToString(iterators);
		String result = "";
		result = addString( result, "ITERATE " );
		for (int i= 0; i< iters.length-1; i++ ) {
			result = addStringPossibleBreak( result, iters[i], true );
		}
		result = addStringPossibleBreak( result, " OVER ", false ); // no indent
		result = addStringPossibleBreak( result, (String) source, true );
		result = addStringWithBreak( result, "RESULT ", false ); // no indent
		result = addStringPossibleBreak( result, iters[iters.length-1], true );
		result = addStringWithBreak( result, "NEXTVALUE ", false ); // no indent
		result = addStringPossibleBreak( result, (body == null ? "<nullbody>" : (String)body), true );
		result = addStringWithBreak( result, "ENDITERATE ", false ); // no indent
		return result;
    }

	private String[] iterateItersToString(List iterators) {
	  String[] result = new String[iterators.size()];
	  int i = 0;
	  Iterator it = iterators.iterator();
	  while( it.hasNext() ){
		// change the temporary coupling string between type and init value in any var decl to "="
		String tmpStr = (String) it.next();
		if (i == iterators.size()-1) {
			tmpStr = StringHelpers.replaceFirstSubstring( tmpStr, tmpVarDeclEquals, "INITIALVALUE");
		} else {
			tmpStr = StringHelpers.replaceFirstSubstring( tmpStr, tmpVarDeclEquals, "=");
		}
		result[i++] = tmpStr;
	  }
	  return result;
	}    
	
    private String addStringPossibleBreak( String res, String add, boolean extraIndent ) {
    	start = false;
    	String result = res + add;
    	strlength = strlength + add.length();
    	if (strlength > linewidth ) {  		// break needed
			result = breakAndIndent(res, add, extraIndent);
    	} 
        return result;
    }

    private String addStringWithBreak( String res, String add, boolean extraIndent ) {
    	start = false;
		String result = breakAndIndent(res, add, extraIndent );
 	    return result;
    }
        
    private String addString( String res, String add ) {
    	start = false;
    	String result = res + add;
    	strlength = strlength + add.length();
    	return result;	
    }

	private String breakAndIndent(String res, String add, boolean extraIndent) {
		String result;
		// first take care of white spaces at the beginning of 'add'
		while ( add.charAt( 0 ) == ' ' ) {
			add = add.substring( 1 );			
		}
		// put in newline when not already at begin of line
		if (res != "" && res.charAt( res.length()-1 ) != StringHelpers.newLineChar && !start) {
			result = res + StringHelpers.newLine;
		} else {
			result = res;
		}
		// take care of indentation
		strlength = 0;
		if ( extraIndent ) indentLevel++;
		for(int i=0; i<indentLevel ;i++) {
		   	result = result + indentStr;
		  	strlength = strlength + indentStr.length();
			add = StringHelpers.replaceAllSubstrings( add, StringHelpers.newLine, StringHelpers.newLine + indentStr );
		}
		result = result + add;
		strlength = strlength + add.length() - add.lastIndexOf( StringHelpers.newLineChar );
		if ( extraIndent ) indentLevel--;
		return result;
	} 
	   
    public void setStart ( boolean st ){
    	start = st;
    }

	/* (non-Javadoc)
	 * @see nl.klasse.octopus.expressionVisitors.IAstVisitorBackwards#associationClassCallExp(nl.klasse.octopus.expressions.IAssociationEndCallExp, java.lang.Object)
	 */
	public Object associationClassCallExp(IAssociationClassCallExp exp, Object source) {
		if ( source.equals("") ) {
		  return exp.getReferredAssociationClass().getName();
		} else {
		  return (String)source + "." + exp.getReferredAssociationClass().getName();
		}
	}

	/* (non-Javadoc)
	 * @see nl.klasse.octopus.expressionVisitors.IAstVisitorBackwards#unspecifiedValueExp(nl.klasse.octopus.expressions.IUnspecifiedValueExp)
	 */
	public Object unspecifiedValueExp(IUnspecifiedValueExp exp) {
		// TODO Auto-generated method stub
		return "? : " + exp.getNodeType().getName();
	}

}
