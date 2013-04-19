package org.opaeum.linkage;

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

@SuppressWarnings("rawtypes")
public class AbstractOclVisitor implements IAstVisitor{
	@Override
	public Object oclExpression(IOclExpression exp,Object expResult,Object appliedProperty){
		return null;
	}
	@Override
	public Object associationEndCallExp(IAssociationEndCallExp exp){
		return null;
	}
	@Override
	public Object associationClassCallExp(IAssociationClassCallExp exp){
		return null;
	}
	@Override
	public Object attributeCallExp(IAttributeCallExp exp){
		return null;
	}
	@Override
	public Object booleanLiteralExp(IBooleanLiteralExp exp){
		return null;
	}
	@Override
	public Object oclUndefinedLiteralExp(IOclUndefinedLiteralExp exp){
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Object collectionItem(ICollectionItem exp,Object item){
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Object collectionLiteralExp(ICollectionLiteralExp exp,List parts){
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Object collectionRange(ICollectionRange exp,Object first,Object last){
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Object enumLiteralExp(IEnumLiteralExp exp){
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Object ifExp(IIfExp exp,Object condition,Object thenPart,Object elsePart){
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Object integerLiteralExp(IIntegerLiteralExp exp){
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Object iterateExp(IIterateExp exp,List iterators,Object result,Object body){
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Object iteratorExp(IIteratorExp exp,List iterators,Object body){
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Object letExp(ILetExp exp,Object varDecl,Object in){
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Object numericLiteralExp(INumericLiteralExp exp){
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Object unspecifiedValueExp(IUnspecifiedValueExp exp){
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Object oclStateLiteralExp(IOclStateLiteralExp exp){
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Object oclTypeLiteralExp(IOclTypeLiteralExp exp){
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Object operationCallExp(IOperationCallExp exp,List args){
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Object propertyCallExp(IPropertyCallExp exp){
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Object realLiteralExp(IRealLiteralExp exp){
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Object stringLiteralExp(IStringLiteralExp exp){
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Object tupleLiteralExp(ITupleLiteralExp exp,List parts){
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Object variableDeclaration(IVariableDeclaration exp,Object initExpression){
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Object variableExp(IVariableExp exp){
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Object oclMessageExp(IOclMessageExp exp,Object target,List args){
		// TODO Auto-generated method stub
		return null;
	}
}
