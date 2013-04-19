/** (c) Copyright 2002, Klasse Objecten
 */
package nl.klasse.octopus.expressionVisitors.internal;

import java.util.Iterator;
import java.util.List;

import nl.klasse.octopus.expressionVisitors.IAstVisitor;
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
import nl.klasse.octopus.expressions.IOclExpression;
import nl.klasse.octopus.expressions.IOclMessageExp;
import nl.klasse.octopus.expressions.IOclStateLiteralExp;
import nl.klasse.octopus.expressions.IOclTypeLiteralExp;
import nl.klasse.octopus.expressions.IOclUndefinedLiteralExp;
import nl.klasse.octopus.expressions.IOperationCallExp;
import nl.klasse.octopus.expressions.IPropertyCallExp;
import nl.klasse.octopus.expressions.IRealLiteralExp;
import nl.klasse.octopus.expressions.IStringLiteralExp;
import nl.klasse.octopus.expressions.ITupleLiteralExp;
import nl.klasse.octopus.expressions.IUnspecifiedValueExp;
import nl.klasse.octopus.expressions.IVariableDeclaration;
import nl.klasse.octopus.expressions.IVariableExp;
import nl.klasse.octopus.model.ICollectionType;
import nl.klasse.octopus.model.IOperation;
import nl.klasse.tools.common.StringHelpers;
import nl.klasse.tools.common.Util;


/** A VisitorString traverses the OCL AST and constructs a String representation
 * of the AST.  This is used for printing and debugging purposes.
 * Each operation that returns an Object does in fact return a String.
 *
 * @author  Jos Warmer
 * @version $Id: AstToString.java,v 1.2 2008/01/19 13:49:18 annekekleppe Exp $
 */
public class AstToString implements IAstVisitor {

  /** Creates a new instance of VisitorString
      */
    public AstToString() {
    }

	public Object collectionLiteralExp(ICollectionLiteralExp exp, List parts) {
		String typeStr = "";
		if (exp.getNodeType() instanceof ICollectionType){
			ICollectionType type = (ICollectionType) exp.getNodeType();
			typeStr = type.getMetaType().toString() ;
		}
		return typeStr + "{" + Util.collectionToString(parts, ", ") + "}";
	}
	
    public Object collectionRange(ICollectionRange exp, Object first, Object last) {
      return (String)first + " .. " + (Object)last;
    }
	
	public Object collectionItem(ICollectionItem exp, Object item) {
		return item;
	}
        
    public Object letExp(ILetExp exp, Object varDecl, Object in) {
      return "let " + (String) varDecl + " in " + (String) in + " endlet\n";
    }
    
	public Object associationEndCallExp(IAssociationEndCallExp exp) {
	  return exp.getReferredAssociationEnd().getName();
	}
    
	public Object associationClassCallExp(IAssociationClassCallExp exp) {
	  return exp.getReferredAssociationClass().getName();
	}
    
    public Object attributeCallExp(IAttributeCallExp exp) {
      if( exp.getReferredAttribute().hasClassScope() ) {
          return exp.getReferredAttribute().getOwner().getName() + "::" + exp.getReferredAttribute().getName();
      } else {
          return exp.getReferredAttribute().getName();
      }
    }
    
    public Object booleanLiteralExp(IBooleanLiteralExp exp) {
      return exp.toString();
    }

    public Object oclTypeLiteralExp(IOclTypeLiteralExp exp) {
      return exp.getReferredClassifier().getName();
    }

    public Object oclStateLiteralExp(IOclStateLiteralExp exp) {
      return exp.getReferredState().getName();
    }
    
    public Object ifExp(IIfExp exp, Object condition, Object thenPart, Object elsePart) {
      return "if " + (String)condition + " then\n" +
             (String) thenPart +
             "\nelse\n" + (String) elsePart + "\nendif";
    }
    
    public Object integerLiteralExp(IIntegerLiteralExp exp) {
      return exp.toString();
    }
    
    public Object iteratorExp(IIteratorExp exp, List iterators, Object body) {
      String result = "";
      result = Util.collectionToString(iterators, ", ");
      if( result.equals("") ) {
        result = exp.getName() + "( " + (String)body + " )";
      } else {
        result = exp.getName() + "( " + result + " | " + (String)body + " )";
      }
      return result;
    }
    
    public Object numericLiteralExp(INumericLiteralExp exp) {
      return exp.toString();
    }
    
    public Object operationCallExp(IOperationCallExp exp, List args) {
        String result = "";
        IOperation referedOp = exp.getReferredOperation();
        // get arguments
		String argStr = argumentsToString(args);
		// build result
		if( referedOp != null ) {
			if( referedOp.hasClassScope() ) {
				result = referedOp.getOwner().getName() + ".";
			} 
			if ( referedOp.isInfix() ) {
				result = referedOp.getName() + " " + argStr;
			} else if ( referedOp.isPrefix() ) {
				result = referedOp.getName();
			} else {
				result = referedOp.getName() + "(" + argStr + ")";
			}
		}
        return result ;
    }

	private String argumentsToString(List args) {
		String argStr = "";
		boolean first = true;
		Iterator it = args.iterator();
		while( it.hasNext() ){
			if( first ) {
				first = false;
			} else {
				argStr = argStr + ", ";
			}
			argStr = argStr + (String) it.next();
		}
		return argStr;
	}
    
    public Object realLiteralExp(IRealLiteralExp exp) {
      return exp.toString();
    }
    
    public Object stringLiteralExp(IStringLiteralExp exp) {
      return exp.getSymbol();
    }
    
    public Object tupleLiteralExp(ITupleLiteralExp exp, List parts) {
      return "Tuple{" + Util.collectionToString(parts, ", ") + "}";
    }
    
    public Object variableDeclaration(IVariableDeclaration exp, Object initExpression) {
      if( initExpression == null ) {
        return exp.getName() + " : " + (exp.getType() == null ? "<null>" : exp.getType().getName());
      } else {
        return exp.getName() + " : " + (exp.getType() == null ? "<null>" : exp.getType().getName()) + " = " + (String) initExpression;
      }
    }
    
    public Object variableExp(IVariableExp exp) {
      return exp.getReferredVariable().getName();
    }

    public Object enumLiteralExp(IEnumLiteralExp exp) {
      return exp.getReferredEnumLiteral().getEnumeration().getName() + "::" +
             exp.getReferredEnumLiteral().getName();
    }
    
    public Object iterateExp(IIterateExp exp, List iterators, Object resultStr, Object body) {
    	//TODO find out whether the right var is returned
		String result = "";
		String iterStr = Util.collectionToString(iterators, ", ");
		if( iterStr.equals("") ) {
		  result = exp.getName() + "( " + (String)resultStr + " | " + (String)body + " )";
		} else {
		  result = exp.getName() + "( " + iterStr + "; " + (String)resultStr + " | " + (String)body + " )";
		}
		return result;
    }
    
    public Object oclExpression(IOclExpression exp, Object expResult, Object appliedProperty) {
		IPropertyCallExp propcall = exp.getAppliedProperty();
		if ( propcall instanceof IOperationCallExp &&
			((IOperationCallExp)propcall).getReferredOperation().isInfix() ) 
		{
			expResult = StringHelpers.addBrackets((String) expResult);
			return (String) expResult + " " + (String)appliedProperty;
		} else if ( propcall instanceof IOperationCallExp &&
			((IOperationCallExp)propcall).getReferredOperation().isPrefix() ) 
		{
			expResult = StringHelpers.addBrackets((String) expResult);
			if (((String)appliedProperty).equals("not")) { // add extra space between not and the source exp
				appliedProperty = (String)appliedProperty + " ";
			}
			return (String)appliedProperty + (String) expResult;
		} else {
			if( exp.getNodeType().isCollectionKind() ){
				return (String) expResult + "->" + (String)appliedProperty;
			} else {
				if (!((String)expResult).equals("")) {
					return (String) expResult + "." + (String)appliedProperty;
				} else {
					return (String)appliedProperty;
				}
			}
		}
	}

    public Object propertyCallExp(IPropertyCallExp exp) {
      return exp.getName();
    }

	/* (non-Javadoc)
	 * @see nl.klasse.octopus.expressionVisitors.IAstVisitor#oclMessageArg(nl.klasse.octopus.expressions.IOclMessageArg)
	 */
	public Object unspecifiedValueExp(IUnspecifiedValueExp exp) {
		return "? : " + exp.getNodeType().getName();
	}

	/* (non-Javadoc)
	 * @see nl.klasse.octopus.expressionVisitors.IAstVisitor#oclUndefinedLiteralExp(nl.klasse.octopus.expressions.IOclUndefinedLiteralExp)
	 */
	public Object oclUndefinedLiteralExp(IOclUndefinedLiteralExp exp) {
		  return exp.getSymbol();
	}

	/* (non-Javadoc)
	 * @see nl.klasse.octopus.expressionVisitors.IAstVisitor#oclMessageExp(nl.klasse.octopus.expressions.IOclMessageExp, java.lang.Object, java.util.List)
	 */
	public Object oclMessageExp(IOclMessageExp exp, Object target, List args) {
		String result = "";
		String targetStr = (String)target + "^";
		IOperation referedOp = exp.getCalledOperation();
		// get arguments
		String argStr = argumentsToString(args);
      
		// build result
		if( referedOp != null ) {
//			if( referedOp.isClassOperation() ) {
//				targetStr = referedOp.getOwner().getName() + "^";
//			} 
			if ( referedOp.isInfix() ) {
				result = targetStr + referedOp.getName() + " " + argStr;
			} else if ( referedOp.isPrefix() ) {
				result = targetStr + referedOp.getName();
			} else {
				result = targetStr + referedOp.getName() + "(" + argStr + ")";
			}
		}
		return result ;
	}        
}
